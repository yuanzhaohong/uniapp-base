package io.dcloud.uniplugin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

/**
 * <pre>
 *     author : GaoXu
 *     e-mail : 511527070@qq.com
 *     time   : 2021/9/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PermissionModule extends UniModule {
    public String PERMISSIONS = "Permissions";


    /**
     * 获取权限状态
     *
     * @param options
     * @return
     */
    @UniJSMethod(uiThread = false)
    public JSONObject isGranted(JSONObject options) {
        JSONObject data = new JSONObject();
        boolean isGranted = true;
        StringBuffer denied = new StringBuffer();
        StringBuffer granted = new StringBuffer();
        String permissionsStr = options.getString(PERMISSIONS);
        String[] permissions = permissionsStr.split(",");

        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (!isGranted(permission, mUniSDKInstance.getContext())) {
                denied.append(permission);
                denied.append(",");
                isGranted = false;
            } else {
                granted.append(permission);
                granted.append(",");
            }
        }
        data.put("isGranted", isGranted);
        data.put("GrantedList", TextUtils.isEmpty(granted.toString()) ? "" : granted.toString().substring(0, granted.toString().lastIndexOf(',')));
        data.put("DeniedList", TextUtils.isEmpty(denied.toString()) ? "" : denied.toString().substring(0, denied.toString().lastIndexOf(',')));
        return data;
    }

    /**
     * 权限是否被永远拒绝
     *
     * @return
     */
    @UniJSMethod(uiThread = false)
    public JSONObject isDeniedForever(JSONObject options) {
        JSONObject data = new JSONObject();
        boolean isDeniedForever = true;
        String permissionsStr = options.getString(PERMISSIONS);
        String[] permissions = permissionsStr.split(",");
        StringBuffer denied = new StringBuffer();
        StringBuffer deniedForever = new StringBuffer();
        for (String permission : permissions) {
            if (mUniSDKInstance != null && mUniSDKInstance.getContext() instanceof Activity) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) mUniSDKInstance.getContext(), permission)) {
                    isDeniedForever = false;
                    deniedForever.append(permission);
                    deniedForever.append(",");
                } else {
                    denied.append(permission);
                    denied.append(",");
                }
            }
        }
        data.put("isDeniedForever", isDeniedForever);
        data.put("DeniedForeverList", TextUtils.isEmpty(deniedForever.toString()) ? "" : deniedForever.toString().substring(0, deniedForever.toString().lastIndexOf(',')));
        data.put("DeniedList", TextUtils.isEmpty(denied.toString()) ? "" : denied.toString().substring(0, denied.toString().lastIndexOf(',')));
        return data;
    }


    /**
     * 获取权限状态
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    @UniJSMethod(uiThread = false)
    public void isGrantedAsync(JSONObject options, UniJSCallback callback) {
        boolean isGranted = true;
        String permissionsStr = options.getString(PERMISSIONS);
        String[] permissions = permissionsStr.split(",");
        for (String permission : permissions) {
            if (!isGranted(permission, mUniSDKInstance.getContext())) {
                isGranted = false;
                break;
            }
        }
        if (callback != null) {
            JSONObject data = new JSONObject();
            data.put("isGranted", isGranted);
            callback.invoke(callback);
        }
    }

    private boolean isGranted(final String permission, Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PackageManager.PERMISSION_GRANTED
                == ContextCompat.checkSelfPermission(context, permission);
    }
}
