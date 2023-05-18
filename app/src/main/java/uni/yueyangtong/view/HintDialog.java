package uni.yueyangtong.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import uni.yueyangtong.R;

/**
 * <pre>
 *     author : GaoXu
 *     e-mail : 511527070@qq.com
 *     time   : 2021/10/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HintDialog extends DialogFragment {
    private TextView tvTitle; // 标题
    private TextView tvContent; // 内容
    private Button btnCancel; // 取消
    private Button btnConfirm; // 确定

    /**
     * 确认回调
     */
    private HintConfirmCallback confirmCallback;
    /**
     * 取消回调
     */
    private HintCancelCallback cancelCallback;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    private String confirm = "确定";  //  确定
    private String cancel = "取消";   //  取消


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 去掉标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        View hintView = inflater.inflate(R.layout.dialog_hint, null);
        initView(hintView);
        return hintView;
    }

    private void initView(View hintView) {
        tvTitle = hintView.findViewById(R.id.tv_dialog_title);
        tvContent = hintView.findViewById(R.id.tv_dialog_content);
        if (title != null && !"".equals(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }

        if (content != null && !"".equals(content)) {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        } else {
            tvContent.setVisibility(View.GONE);
        }

        btnCancel = hintView.findViewById(R.id.btn_dialog_cancel);
        btnConfirm = hintView.findViewById(R.id.btn_dialog_confirm);
        if (!"".equals(confirm) && confirm != null) {
            btnConfirm.setText(confirm);
        }
        if (!"".equals(cancel) && cancel != null) {
            btnCancel.setText(cancel);
        }
        btnConfirm.setOnClickListener(view -> {
                    if (null != confirmCallback) {
                        confirmCallback.onClick();
                    }
                }
        );
        btnCancel.setOnClickListener(view -> {
            if (null != cancelCallback) {
                cancelCallback.onClick();
            } else {
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        clearListener();
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public HintDialog setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
        return this;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public HintDialog setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 设置确定按钮内容
     *
     * @param confirmMsg
     * @return
     */
    public HintDialog setOnConfirmBtnText(String confirmMsg) {
        this.confirm = confirmMsg;
        return this;
    }


    /**
     * 设置取消按钮内容
     *
     * @param cancelMsg
     * @return
     */
    public HintDialog setOnCancelBtnText(String cancelMsg) {
        this.cancel = cancelMsg;
        return this;
    }


    /**
     * 确定点击事件
     *
     * @param confirmCallback
     * @return
     */
    public HintDialog setOnConfirmClickListener(HintConfirmCallback confirmCallback) {
        this.confirmCallback = confirmCallback;
        return this;
    }

    /**
     * 取消点击事件
     *
     * @param cancelCallback
     * @return
     */
    public HintDialog setOnCancelClickListener(HintCancelCallback cancelCallback) {
        this.cancelCallback = cancelCallback;
        return this;
    }


    /**
     * 确认回调
     */
    public interface HintConfirmCallback {
        void onClick();
    }

    /**
     * 取消回调
     */
    public interface HintCancelCallback {
        void onClick();
    }

    /**
     * 删除监听
     */
    public void clearListener() {
        if (this != null) {
            this.setOnCancelClickListener(null);
            this.setOnConfirmClickListener(null);
        }
    }

}
