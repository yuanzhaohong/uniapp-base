package uni.yueyangtong.page;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.android.agoo.common.AgooConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

import uni.yueyangtong.R;
import uni.yueyangtong.push.OfflinePushData;

/**
 * @author guotianhui
 */
public class PushMessageDetailsActivity extends BaseActivity{

    private static String TAG = MipushTestActivity.class.getName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_message);
        Intent intent = getIntent();
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

    private void goHomeActivityAndFinish() {
        Intent goHomeIntent = new Intent(PushMessageDetailsActivity.this, PandoraActivity.class);
        startActivity(goHomeIntent);
        finish();
    }
}
