package com.igexin.sdk.main;

import android.content.Context;
import com.igexin.b.a.c.a;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SdkPushSwitch {
    private String mSdkSwitchPath;

    public SdkPushSwitch(Context context) {
        if (context == null) {
            return;
        }
        this.mSdkSwitchPath = context.getFilesDir().getPath() + "/push.pid";
    }

    public void delete() {
        switchOff();
    }

    public boolean isSwitchOn() {
        String str = this.mSdkSwitchPath;
        if (str != null) {
            return new File(str).exists();
        }
        return false;
    }

    public void switchOff() {
        String str;
        if (!isSwitchOn() || (str = this.mSdkSwitchPath) == null || new File(str).delete()) {
            return;
        }
        a.a("SdkPushSwitch | switchOff, delete file = " + this.mSdkSwitchPath + " failed !!!!!!!!!!!!", new Object[0]);
    }

    public void switchOn() {
        String str;
        if (isSwitchOn() || (str = this.mSdkSwitchPath) == null) {
            return;
        }
        try {
            new File(str).createNewFile();
        } catch (IOException e) {
            a.a("SdkPushSwitch | switchOn, create file = " + this.mSdkSwitchPath + " exception, " + e.toString(), new Object[0]);
        }
    }
}
