package uni.yueyangtong.page;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import uni.yueyangtong.R;
import uni.yueyangtong.utils.StatusBarUtil;
import uni.yueyangtong.webview.ProgressWebView;

/**
 * <pre>
 *     author : GaoXu
 *     e-mail : 511527070@qq.com
 *     time   : 2021/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class WebActivity extends BaseActivity{
    public static String URL = "Url";
    public static String TITLE = "Title";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            getWindow().setStatusBarColor(0x00000000); // transparent

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

            getWindow().addFlags(flags);

        }
        StatusBarUtil.setLightMode(this);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        ProgressWebView webView = findViewById(R.id.web_view);
        ConstraintLayout constraintLayout = findViewById(R.id.in_toolbar);
        TextView tvTitle = constraintLayout.findViewById(R.id.toolbar_title);
        ImageView ivBack = constraintLayout.findViewById(R.id.iv_back);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            constraintLayout.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0);
        }

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(TITLE);
            String url = intent.getStringExtra(URL);
            if (!TextUtils.isEmpty(url)) {
                webView.loadUrl(url);
            }
            if (!TextUtils.isEmpty(url)) {
                tvTitle.setText(title);
            }
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebActivity.this.onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StatusBarUtil.setDarkMode(this);
    }
}
