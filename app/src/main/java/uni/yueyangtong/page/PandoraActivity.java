package uni.yueyangtong.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

import io.dcloud.PandoraEntry;
import io.dcloud.PandoraEntryActivity;
import io.dcloud.WebAppActivity;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import uni.yueyangtong.MyApplication;
import uni.yueyangtong.R;
import uni.yueyangtong.push.PushHelper;
import uni.yueyangtong.safe.SignCheck;
import uni.yueyangtong.utils.SPUtils;
import uni.yueyangtong.view.HintDialog;
import uni.yueyangtong.view.PrivatePolicyDialog;
import uni.yueyangtong.view.PrivatePolicyPop;

/**
 * author : JZ_CHEN on 2020/9/21 0021 10:17
 * e-mail : 3152981452@qq.com
 * desc   :
 */
public class PandoraActivity extends BaseActivity {
    private static final String TAG = PandoraActivity.class.getSimpleName();
    private PrivatePolicyDialog mPrivatePolicyDialog;
    private HintDialog mHintDialog;

    public PandoraActivity() {
    }

    @Override
    protected void onCreate(Bundle var1) {
        super.onCreate(var1);
        Log.e(">>>>>>>>>>>>>>>", "BaseActivity");
        View decorView = getWindow().getDecorView();
        boolean agreed = SPUtils.getInstance().getBoolean(MyApplication.KEY_IS_AGREED_PRIVATE_POLICY);
        Log.e(">>>>>>>>>>>>>>", "agreed:" + agreed);

        SignCheck signCheck = new SignCheck(this, getString(R.string.sha1));
        if (!signCheck.check()) {
            // 签名异常
            Log.e(TAG, "签名异常");
            signCheck.showCheckErrorTips();
            return;
        }

        if (!agreed) {
            decorView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mPrivatePolicyDialog == null) {
                        mPrivatePolicyDialog = new PrivatePolicyDialog(PandoraActivity.this);
                    }
                    mPrivatePolicyDialog.setOnConfirmPolicyClickListener(new PrivatePolicyDialog.OnConfirmPolicyClickListener() {
                        @Override
                        public void onCanclePolicyClick() {
                            mPrivatePolicyDialog.dismiss();
                            if (mHintDialog == null) {
                                mHintDialog = new HintDialog();
                            }
                            mHintDialog.setTitle("隐私政策提示")
                                    .setContent("请放心，我们坚决保障您的隐私信息安全，您的信息仅用于为您提供服务或改善用户体验。如果您确定无法认同此政策，可点击“不同意”并退出应用。")
                                    .setOnConfirmBtnText("再想想")
                                    .setOnCancelBtnText("不同意")
                                    .setOnCancelClickListener(() -> {
                                        mHintDialog.dismiss();
                                        finish();
                                    })
                                    .setOnConfirmClickListener(() -> {
                                        mHintDialog.dismiss();
                                        mPrivatePolicyDialog.show();
                                    })
                                    .show(getSupportFragmentManager(), "PandoraActivity");
                        }

                        @Override
                        public void onConfirmPolicyClick() {
                            mPrivatePolicyDialog.dismiss();
                            initUniAppContext();
                        }
                    });
                    mPrivatePolicyDialog.show();
                }
            }, 50);
        } else {
            initUniAppContext();
        }
    }

    private void initUniAppContext() {
        Intent var2 = this.getIntent();
        BaseInfo.parseControl();
        if (SDK.isUniMPSDK() && var2.hasExtra("appid")) {
            BaseInfo.sDefaultBootApp = var2.getStringExtra("appid");
        }

        if (SDK.isUniMPSDK()) {
            this.a(var2);
        } else if (BaseInfo.SyncDebug) {
            this.a(var2);
        }

        boolean var3 = false;

        try {
            var3 = var2.getBooleanExtra("is_stream_app", var3);
        } catch (Exception var5) {
            var5.printStackTrace();
            this.finish();
            return;
        }

        if (var2.hasExtra("__start_from_to_class__")) {
            String var4 = var2.getStringExtra("__start_from_to_class__");
            var2.setClassName(this.getPackageName(), var4);
            var2.removeExtra("__start_from_to_class__");
        } else if (var3) {
            var2.setClass(this, WebAppActivity.class);
            var2.putExtra("is_stream_app", true);
        } else {
            var2.putExtra("short_cut_class_name", PandoraEntry.class.getName());
            var2.setClass(this, PandoraEntryActivity.class);
        }

        this.startActivity(var2);
        if (SDK.isUniMPSDK()) {
            this.finish();
        } else {
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    PandoraActivity.this.finish();
                }
            }, 20L);
        }
    }

    private void a(Intent var1) {
        try {
            boolean var2 = SDK.isUniMPSDK();
            String var3 = BaseInfo.sDefaultBootApp + "/www/manifest.json";
            InputStream var5 = null;
            File var4;
            String var6;
            if (var2) {
                var6 = BaseInfo.sCacheFsAppsPath + var3;
                var4 = new File(var6);
                if (var4.exists()) {
                    var5 = DHFile.getInputStream(var4);
                } else {
                    var5 = this.getResources().getAssets().open(var3);
                }
            } else {
                File var9 = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                String var7 = var9.getAbsolutePath().replace("files/Pictures", "apps/") + var3;
                var4 = new File(var7);
                if (var4.exists()) {
                    var5 = DHFile.getInputStream(var4);
                }
            }

            if (var5 == null) {
                return;
            }

            var6 = IOUtil.toString(var5);
            if (TextUtils.isEmpty(var6)) {
                return;
            }

            int var10 = PdrUtil.getConfigOrientation(new JSONObject(var6));
            var1.putExtra("__intetn_orientation__", var10);
        } catch (Exception var8) {
        }

    }

    /**
     * 再按一次退出程序
     */
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    boolean isExiting = true;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isExiting) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_LONG).show();
            ;
            isExiting = false;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExiting = true;
                }
            }, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPrivatePolicyDialog != null) {
            mPrivatePolicyDialog.dismiss();
        }
        if (mHintDialog != null) {
            mHintDialog.dismiss();
        }
    }
}
