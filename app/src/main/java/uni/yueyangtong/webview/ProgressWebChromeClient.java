package uni.yueyangtong.webview;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import androidx.appcompat.app.AlertDialog;



public class ProgressWebChromeClient extends WebChromeClient {

    private ValueCallback<Uri[]> mFilePathCallback;
    private Handler progressHandler;
    private String mCameraPhotoPath;

    public ProgressWebChromeClient(Handler progressHandler) {
        this.progressHandler = progressHandler;
    }



    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Message message = new Message();
        if (newProgress == 100) {
            message.obj = newProgress;
            progressHandler.sendMessageDelayed(message, 200);
        } else {
            if (newProgress < 10) {
                newProgress = 10;
            }
            message.obj = newProgress;
            progressHandler.sendMessage(message);
        }
        super.onProgressChanged(view, newProgress);
    }



    @Override
    public boolean onJsAlert(final WebView view, String url, String message, JsResult result) {
        new AlertDialog.Builder(view.getContext())
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                //按钮事件
                                Toast.makeText(view.getContext(), view.getContext().getString(android.R.string.ok) + " clicked.", Toast.LENGTH_LONG).show();
                            }
                        }).show();
        //result.confirm();// 不加这行代码，会造成Alert劫持：Alert只会弹出一次，并且WebView会卡死
        return true;
    }



}
