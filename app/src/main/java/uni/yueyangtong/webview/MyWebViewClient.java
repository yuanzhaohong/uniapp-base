package uni.yueyangtong.webview;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Map;

import androidx.annotation.RequiresApi;
import uni.yueyangtong.R;

/**
 * <pre>
 *     author : GaoXu
 *     e-mail : 511527070@qq.com
 *     time   : 2021/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyWebViewClient extends WebViewClient {

    private static final String TAG = MyWebViewClient.class.getSimpleName();
    public static final String SCHEME_SMS = "sms:";
    private WebView webView;
    boolean isReady;
    private Map<String, String> mHeaders;

    public MyWebViewClient(WebView webView, Map<String, String> headers){
        this.webView = webView;
        this.mHeaders = headers;
    }

    /**
     * url重定向会执行此方法以及点击页面某些链接也会执行此方法
     *
     * @return true:表示当前url已经加载完成，即使url还会重定向都不会再进行加载 false 表示此url默认由系统处理，该重定向还是重定向，直到加载完成
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e(TAG, "shouldOverrideUrlLoading url: " + url);
        // 如果链接跟当前链接一样，表示刷新
        if (webView.getUrl().equals(url)) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        if (handleLinked(url)) {
            return true;
        }
        // 控制页面中点开新的链接在当前webView中打开
        view.loadUrl(url, mHeaders);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.e(TAG, "shouldOverrideUrlLoading url: "+ request.getUrl());
        // 如果链接跟当前链接一样，表示刷新
        if (webView.getUrl().equals(request.getUrl().toString())) {
            return super.shouldOverrideUrlLoading(view, request);
        }
        if (handleLinked(request.getUrl().toString())) {
            return true;
        }
        // 控制页面中点开新的链接在当前webView中打开
        view.loadUrl(request.getUrl().toString(), mHeaders);
        return true;
    }


    private boolean handleLinked(String url) {
        if (url.startsWith(WebView.SCHEME_TEL)
                || url.startsWith(SCHEME_SMS)
                || url.startsWith(WebView.SCHEME_MAILTO)
                || url.startsWith(WebView.SCHEME_GEO)) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                webView.getContext().startActivity(intent);
            } catch (ActivityNotFoundException ignored) {
                ignored.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
        String channel = "";
        if (!TextUtils.isEmpty(channel) && channel.equals("play.google.com")) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            String message = "SSL证书错误";
            switch (error.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    message = "SSL证书颁发机构未受信任";
                    break;
                case SslError.SSL_EXPIRED:
                    message = "SSL证书已过期";
                    break;
                case SslError.SSL_IDMISMATCH:
                    message = "SSL安全证书域名与网站网址不一致";
                    break;
                case SslError.SSL_NOTYETVALID:
                    message = "SSL证书还未生效";
                    break;
            }
            message += "是否继续打开该网页?";

            builder.setTitle("SSL证书错误");
            builder.setMessage(message);
            builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            handler.proceed();
        }
    }
} 
