package com.igexin.sdk.main;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SdkInitSwitch {
    private String mSdkSwitchPath;

    public SdkInitSwitch(Context context) {
        if (context == null) {
            return;
        }
        this.mSdkSwitchPath = context.getFilesDir().getPath() + "/init.pid";
    }

    public void delete() {
        File file = new File(this.mSdkSwitchPath);
        if (file.exists()) {
            file.delete();
        }
    }

    public boolean isSwitchOn() {
        String str = this.mSdkSwitchPath;
        if (str != null) {
            return new File(str).exists();
        }
        return false;
    }

    public void switchOn() {
        String str;
        if (isSwitchOn() || (str = this.mSdkSwitchPath) == null) {
            return;
        }
        try {
            new File(str).createNewFile();
        } catch (IOException unused) {
        }
    }
}
