package com.bun.miitmdid.utilsforrequestpermission;

import android.content.Intent;
import android.os.Bundle;
import com.bun.miitmdid.c;
import com.bun.miitmdid.interfaces.IPermissionCallbackListener;
import com.bun.miitmdid.m0;
import com.bun.miitmdid.p;
import com.bun.miitmdid.p0;
import com.plugin.standard.BaseActivity;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PermissionTransparentActivity extends BaseActivity {
    public int a = 1111;
    public String b = "1";

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public native void run();
    }

    public native void finish();

    /* JADX WARN: Multi-variable type inference failed */
    public void onActivityResult(int i, int i2, Intent intent) {
        super/*android.app.Activity*/.onActivityResult(i, i2, intent);
        m0.a("PermissionTransparentActivity", "onActivityResult---------,requestCode: " + i + ", resultCode: " + i2);
        if (this.a == i && -1 == i2 && intent != null) {
            IPermissionCallbackListener iPermissionCallbackListenerB = p0.a().b();
            String stringExtra = intent.getStringExtra("permissionCode");
            String str = p.a().b(this).B;
            m0.a("PermissionTransparentActivity", "providerName: " + str);
            if (str.equals(c.VIVO.B)) {
                m0.a("PermissionTransparentActivity", "providerName is vivo");
                if (this.b.equals(stringExtra)) {
                    m0.a("PermissionTransparentActivity", "onGranted");
                    iPermissionCallbackListenerB.onGranted(new String[]{"VIVO_OAID_STATE_ENABLE"});
                } else {
                    m0.a("PermissionTransparentActivity", "onDenied");
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add("VIVO_OAID_STATE_DISABLE");
                    iPermissionCallbackListenerB.onDenied(arrayList);
                }
            }
        }
        finish();
    }

    public native void onCreate(Bundle bundle);

    public native void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
}
