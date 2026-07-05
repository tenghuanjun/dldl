package com.sq.tools.utils;

import android.os.Bundle;
import android.text.TextUtils;
import com.plugin.standard.BaseActivity;
import com.sq.tools.Logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PermissionActivity extends BaseActivity {
    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super/*android.app.Activity*/.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Logger.error("Internal error, request permission but no permission bundle passed", new Object[0]);
            finish();
        } else {
            extras.setClassLoader(PermissionActivity.class.getClassLoader());
            dealPermission(extras);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void dealPermission(Bundle bundle) {
        String string = bundle.getString("permission_request");
        int i = bundle.getInt("permission_code");
        if (TextUtils.isEmpty(string) || i == 0 || string == null) {
            Logger.error("Internal error, request permission with illegal request code, or empty permission transferred", new Object[0]);
            PermissionUtils.onRequestPermissionsResult(this, i, new String[0], new int[]{-1});
            finish();
        } else {
            if (PermissionUtils.hasAndroidPermission(this, string)) {
                Logger.warning("Request a granted permission %s, grant by default", string);
                PermissionUtils.onRequestPermissionsResult(this, i, new String[0], new int[]{0});
                finish();
                return;
            }
            requestPermissions(new String[]{string}, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr == null) {
            strArr = new String[0];
        }
        if (iArr == null) {
            iArr = new int[0];
        }
        PermissionUtils.onRequestPermissionsResult(this, i, strArr, iArr);
        finish();
    }
}
