package com.sq.webview.permission;

import android.app.Activity;
import android.os.Build;
import com.sq.webview.util.WebLogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqPermissionUtil {
    public static final String TAG = "【Permission】";
    private Activity mActivity;
    private final List<String> mPermissions = new ArrayList();

    public static SqPermissionUtil with(Activity activity) {
        return new SqPermissionUtil(activity);
    }

    private SqPermissionUtil(Activity activity) {
        this.mActivity = activity;
    }

    public SqPermissionUtil permission(String permission) {
        if (this.mPermissions.contains(permission)) {
            return this;
        }
        this.mPermissions.add(permission);
        return this;
    }

    public SqPermissionUtil permissions(List<String> permissions) {
        this.mPermissions.clear();
        this.mPermissions.addAll(permissions);
        return this;
    }

    public void request(OnPermissionCallback permissionCallback) {
        if (this.mActivity == null) {
            WebLogUtil.e("【Permission】activity为空, 无法进行权限申请");
            return;
        }
        if (this.mPermissions.isEmpty()) {
            WebLogUtil.w("【Permission】无权限需要申请, 请添加权限");
            return;
        }
        if (Build.VERSION.SDK_INT < 23) {
            WebLogUtil.w("【Permission】android6.0以下不需要申请权限");
            if (permissionCallback != null) {
                permissionCallback.onGranted(this.mPermissions, true);
                return;
            }
            return;
        }
        if (PermissionUtil.isGrantedPermissions(this.mActivity, this.mPermissions)) {
            WebLogUtil.d("【Permission】已有权限: " + this.mPermissions + ", 无需申请");
            if (permissionCallback != null) {
                permissionCallback.onGranted(this.mPermissions, true);
                return;
            }
            return;
        }
        WebLogUtil.d("【Permission】请求权限: " + this.mPermissions);
        PermissionFragment.launch(this.mActivity, new ArrayList(this.mPermissions), permissionCallback);
    }
}
