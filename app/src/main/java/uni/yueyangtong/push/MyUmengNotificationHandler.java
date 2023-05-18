package uni.yueyangtong.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

import uni.yueyangtong.page.MipushTestActivity;
import uni.yueyangtong.page.PushMessageDetailsActivity;
import uni.yueyangtong.utils.SPUtils;

/**
 * @author guotianhui
 */
public class MyUmengNotificationHandler extends UmengNotificationClickHandler {


    @Override
    public void dismissNotification(Context context, UMessage uMessage) {
        super.dismissNotification(context, uMessage);
        Log.e(">>>>>>>>>>","dismissNotification");
    }

    @Override
    public void autoUpdate(Context context, UMessage uMessage) {
        super.autoUpdate(context, uMessage);
        Log.e(">>>>>>>>>>","autoUpdate");
    }

    @Override
    public void openUrl(Context context, UMessage uMessage) {
        super.openUrl(context, uMessage);
        Log.e(">>>>>>>>>>","openUrl");
    }

    @Override
    public void openActivity(Context context, UMessage uMessage) {
        super.openActivity(context, uMessage);
        Log.e(">>>>>>>>>>","openActivity");
    }

    @Override
    public void launchApp(Context context, UMessage uMessage) {
        super.launchApp(context, uMessage);
        Log.e(">>>>>>>>>>","launchApp");
    }

    @Override
    public void dealWithCustomAction(Context context, UMessage uMessage) {
        super.dealWithCustomAction(context, uMessage);
        Log.e(">>>>>>>>>>","dealWithCustomAction");
    }

    @Override
    public void handleMessage(Context context, UMessage uMessage) {
        super.handleMessage(context, uMessage);
        try {
            JSONObject jsonObject = uMessage.getRaw();
            Log.e(">>>>>>>>>>", "handleMessage" + jsonObject.toString());
            Intent intent = new Intent(context, PushMessageDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(AgooConstants.MESSAGE_BODY, jsonObject.toString());
            context.startActivity(intent);
        }catch (Exception e){
            Log.e(">>>>>>>>>>","e:"+e);
        }

    }
}
