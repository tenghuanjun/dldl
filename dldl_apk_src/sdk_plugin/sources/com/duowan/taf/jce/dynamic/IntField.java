package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class IntField extends NumberField {
    private int data;

    IntField(int i, int i2) {
        super(i2);
        this.data = i;
    }

    public int get() {
        return this.data;
    }

    public void set(int i) {
        this.data = i;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public Number getNumber() {
        return Integer.valueOf(this.data);
    }
}
