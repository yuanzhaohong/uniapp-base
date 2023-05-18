package uni.yueyangtong.page;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.message.UmengNotifyClickActivity;

import org.android.agoo.common.AgooConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

import uni.yueyangtong.R;
import uni.yueyangtong.push.OfflinePushData;

/**
 * @author guotianhui
 */
public class MipushTestActivity extends UmengNotifyClickActivity {

    private static String TAG = MipushTestActivity.class.getName();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_push_message);
    }
    @Override

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    @Override
    public void onMessage(Intent intent) {
        //此方法必须调用，否则无法统计打开数
        super.onMessage(intent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
                if(intent == null || "".equals(body)){
                    goHomeActivityAndFinish();
                    return;
                }
                Log.e(TAG, body);
                findViewById(R.id.rl_back_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goHomeActivityAndFinish();
                            }
                });
                Gson gson = new Gson();
                OfflinePushData offlinePushData = gson.fromJson(body, OfflinePushData.class);
                OfflinePushData.BodyBean offlinePushDataBody = offlinePushData.getBody();
                TextView tv_push_message_title = findViewById(R.id.tv_push_message_title);
                TextView tv_current_timer = findViewById(R.id.tv_current_timer);
                TextView tv_push_message_details = findViewById(R.id.tv_push_message_details);
                String title = offlinePushDataBody.getTitle();
                String text = offlinePushDataBody.getText();
                if("".equals(title) || "".equals(text) || title == null || text == null ){
                    goHomeActivityAndFinish();
                }
                tv_push_message_title.setText(title);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String currentTimer = dateFormat.format(new Date(System.currentTimeMillis()));
                tv_current_timer.setText(currentTimer);
                tv_push_message_details.setText(text);
            }
        });
    }

    private void goHomeActivityAndFinish() {
        Intent goHomeIntent = new Intent(MipushTestActivity.this, PandoraActivity.class);
        startActivity(goHomeIntent);
        finish();
    }
}