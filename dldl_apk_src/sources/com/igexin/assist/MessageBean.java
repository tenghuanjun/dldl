package com.igexin.assist;

import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class MessageBean {
    private String a;
    private String b;
    private Object c;
    private Context d;
    public final Bundle extra = new Bundle();

    public MessageBean(Context context, String str, Object obj) {
        this.b = str;
        this.c = obj;
        this.d = context;
    }

    public Context getContext() {
        return this.d;
    }

    public Object getMessage() {
        return this.c;
    }

    public String getMessageSource() {
        return this.a;
    }

    public String getMessageType() {
        return this.b;
    }

    public Object getObjectMessage() {
        return this.c;
    }

    public String getStringMessage() {
        Object obj = this.c;
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public void setMessageSource(String str) {
        this.a = str;
    }
}
