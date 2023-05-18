package uni.yueyangtong.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import uni.yueyangtong.MyApplication;
import uni.yueyangtong.R;
import uni.yueyangtong.page.WebActivity;
import uni.yueyangtong.push.PushHelper;
import uni.yueyangtong.utils.SPUtils;
import uni.yueyangtong.utils.SpanUtils;

/**
 * <pre>
 *     author : GaoXu
 *     e-mail : 511527070@qq.com
 *     time   : 2021/10/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PrivatePolicyDialog extends Dialog {

    private OnConfirmPolicyClickListener mConfirmPolicyClickListener;

    public PrivatePolicyDialog(@NonNull Context context) {
        this(context, R.style.Theme_AppCompat_Dialog);
    }

    public PrivatePolicyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_private_policy, null);
        Window window = getWindow();
        assert window != null;
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        initView(view, context);
    }

    private void initView(View view, Context context) {
        TextView tv_policy_content = view.findViewById(R.id.tv_policy_content);
          tv_policy_content.setText(new SpanUtils()
                .append("感谢您信任并使用“岳办岳好”！")
                .appendLine()
                .appendLine()
                .append("我们非常注重您的个人信息和隐私保护，并依据最新法律要求更新了")
                .append("《用户服务协议》")
                .setBold()
               .setClickSpan(ContextCompat.getColor(context,R.color.color_006AFA), false, new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       startWebActivity(context,"用户服务协议","file:///android_asset/userAgreement.html");
                   }
               })
                .append("和")
                .append("《隐私政策》")
                .setBold()
                .setClickSpan(ContextCompat.getColor(context,R.color.color_006AFA), false, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        startWebActivity(context,"隐私政策","file:///android_asset/privacyPolicy_huawei.html");
                       startWebActivity(context,"隐私政策","file:///android_asset/privacyPolicy.html");
                    }
                })
                .append("，特向您推送本提示。请您仔细阅读并充分理解相关条款，其中的重点条款已为您标注，方便您了解自己的权利。我们将严格按照经您同意的条款使用您的个人信息，以便为您提供更好的服务。")
                .appendLine()
                .appendLine()
                .append("如果您同意此政策，请点击")
                .append("“同意”")
                .setBold()
                .append("并开始使用我们的产品和服务。我们将尽全力保护您的个人信息及合法权益，再次感谢您的信任！")
                .append("")
                .create());
        tv_policy_content.setMovementMethod(LinkMovementMethod.getInstance());
        view.findViewById(R.id.tv_cancle_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmPolicyClickListener != null) {
                    mConfirmPolicyClickListener.onCanclePolicyClick();
                }
            }
        });
        view.findViewById(R.id.tv_confirm_agress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.getInstance().put(MyApplication.KEY_IS_AGREED_PRIVATE_POLICY, true);
                PushHelper.init(context);
                if (mConfirmPolicyClickListener != null) {
                    mConfirmPolicyClickListener.onConfirmPolicyClick();
                }
            }
        });
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view);
    }

    public void setOnConfirmPolicyClickListener(OnConfirmPolicyClickListener policyClickListener) {
        mConfirmPolicyClickListener = policyClickListener;
    }

    public interface OnConfirmPolicyClickListener {
        void onCanclePolicyClick();

        void onConfirmPolicyClick();
    }

    private void startWebActivity(Context context,String title,String url) {
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(WebActivity.TITLE,title);
        intent.putExtra(WebActivity.URL,url);
        context.startActivity(intent);
    }
}
