package com.nirvana.tools.logger;

import android.content.Context;
import com.aaid.aidin.AAIDManager;
import com.aaid.aidin.IAAIDCompletionCallback;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class UmaaidSdk {
    public static String getAaid(Context context) {
        return AAIDManager.getInstance().getAAID(context);
    }

    public static void init(Context context) {
        AAIDManager.getInstance().init(context, new IAAIDCompletionCallback() { // from class: com.nirvana.tools.logger.UmaaidSdk.1
            public final void onFailure(String str, String str2) {
            }

            public final void onSuccess(String str) {
            }
        });
    }

    public static boolean isUmAaidSdkAble() {
        try {
            return Class.forName("com.aaid.aidin.AAIDManager") != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
