package uni.yueyangtong.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Map;


public class ProgressWebView extends WebView {
    private Map<String, String> mHeaders;

    public ProgressWebView(Context context) {
        super(context);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private IndicatorHandler indicatorHandler;
    private WebProgressBar progressBar;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int progress = (int) msg.obj;
            indicatorHandler.progress(progress);
        }
    };

    public void setHeaders(Map<String, String> mHeaders) {
        this.mHeaders = mHeaders;
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @SuppressLint("JavascriptInterface")
    private void init(Context context) {
        WebViewDefaultSetting.getInstance().toSetting(this);
        addJavascriptInterface(this, "webview");
        setWebViewClient(new MyWebViewClient(this,mHeaders));
        progressBar = new WebProgressBar(context);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        progressBar.setVisibility(GONE);
        addView(progressBar);
        indicatorHandler = IndicatorHandler.getInstance().inJectProgressView(progressBar);
        setWebChromeClient(new ProgressWebChromeClient(handler));
    }
}
