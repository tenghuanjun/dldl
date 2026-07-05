package com.nirvana.tools.core;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public abstract class BaseDelegate {
    private static final String TAG = BaseDelegate.class.getName();
    protected static volatile Boolean sComponentClassExist;

    public BaseDelegate() {
        if (sComponentClassExist == null) {
            if (ComponentSdkCore.sApplicationContext == null) {
                Log.e(TAG, "ComponentSdkCore.sApplicationContext is null!");
                sComponentClassExist = Boolean.FALSE;
                return;
            }
            try {
                ComponentSdkCore.sApplicationContext.getClassLoader().loadClass(getSubClassName());
                sComponentClassExist = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                Log.d(TAG, "Load class " + getSubClassName() + " failed!");
                sComponentClassExist = Boolean.FALSE;
            }
        }
    }

    protected abstract String getSubClassName();
}
