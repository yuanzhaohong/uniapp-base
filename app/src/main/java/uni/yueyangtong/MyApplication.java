package uni.yueyangtong;


import android.util.Log;

import com.umeng.commonsdk.utils.UMUtils;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.oppo.OppoRegister;
import org.android.agoo.vivo.VivoRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

import io.dcloud.application.DCloudApplication;
import uni.yueyangtong.push.PushConstants;
import uni.yueyangtong.push.PushHelper;
import uni.yueyangtong.safe.DebuggerUtils;
import uni.yueyangtong.utils.SPUtils;
import uni.yueyangtong.utils.Utils;

/**
 * @author guotianhui
 */
public class MyApplication extends DCloudApplication {

    public static final String KEY_IS_AGREED_PRIVATE_POLICY = "is_agreed_private_policy";
    @Override
    public void onCreate() {
        super.onCreate();
        DebuggerUtils.checkDebuggableInNotDebugModel(this);
        Utils.init(this);
        PushHelper.preInit(this);
        initPushSDK();
    }
    /**
     * 初始化推送SDK，在用户隐私政策协议同意后，再做初始化
     * 判断用户是否已同意隐私政策
     * * 当同意时，直接进行初始化；
     * * 当未同意时，待用户同意后，通过PushHelper.init(...)方法进行初始化。
     */
    private void initPushSDK() {
        boolean agreed = SPUtils.getInstance().getBoolean(KEY_IS_AGREED_PRIVATE_POLICY);
        Log.e(">>>>>>>>>>>>>>>","agreed:"+agreed);
        Log.e(">>>>>>>>>>>>>>>","UMUtils.isMainProgress(this):"+UMUtils.isMainProgress(this));
        if (agreed && UMUtils.isMainProgress(this)) {
            //建议在线程中执行初始化
            PushHelper.init(getApplicationContext());
          /*  new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();*/
        }
    }
}
