package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ShortField extends NumberField {
    private short data;

    ShortField(short s, int i) {
        super(i);
        this.data = s;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public Number getNumber() {
        return Short.valueOf(this.data);
    }

    public short get() {
        return this.data;
    }

    public void set(short s) {
        this.data = s;
    }
}
