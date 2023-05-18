package uni.yueyangtong.page;

import android.content.Context;
import android.content.Intent;

import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

import org.android.agoo.mezu.MeizuPushReceiver;

/**
 * @author guotianhui
 */
public class MeizuTestReceiver extends MeizuPushReceiver {

    @Override
    public void onRegister(Context context, String pushId) {
        super.onRegister(context, pushId);
    }

    @Override
    public void onUnRegister(Context context, boolean b) {
        super.onUnRegister(context, b);
    }

    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        super.onPushStatus(context, pushSwitchStatus);
    }

    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        super.onRegisterStatus(context, registerStatus);
    }

    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        super.onUnRegisterStatus(context, unRegisterStatus);
    }

    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        super.onSubTagsStatus(context, subTagsStatus);
    }

    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        super.onSubAliasStatus(context, subAliasStatus);
    }

    @Override
    public void onMessage(Context context, String message, String platformExtra) {
        super.onMessage(context, message, platformExtra);
    }

    @Override
    public void onMessage(Context context, Intent intent) {
        super.onMessage(context, intent);
    }
}