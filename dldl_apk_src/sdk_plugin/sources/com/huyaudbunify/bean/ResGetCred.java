package com.huyaudbunify.bean;

import android.util.Base64;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResGetCred {
    String hyCred;
    String yyCred;

    public String getHyCred() {
        return this.hyCred;
    }

    public void setHyCred(String str) {
        this.hyCred = str;
    }

    public String getYyCred() {
        return this.yyCred;
    }

    public String getCreditYyOri() {
        return new String(Base64.decode(this.yyCred, 0));
    }

    public void setYyCred(String str) {
        this.yyCred = str;
    }
}
