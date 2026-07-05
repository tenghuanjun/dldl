package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StringField extends JceField {
    private String data;

    StringField(String str, int i) {
        super(i);
        this.data = str;
    }

    public String get() {
        return this.data;
    }

    public void set(String str) {
        this.data = str;
    }
}
