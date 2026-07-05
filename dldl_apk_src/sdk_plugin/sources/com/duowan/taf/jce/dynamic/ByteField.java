package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ByteField extends NumberField {
    private byte data;

    ByteField(byte b, int i) {
        super(i);
        this.data = b;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public Number getNumber() {
        return Byte.valueOf(this.data);
    }

    public byte get() {
        return this.data;
    }

    public void set(byte b) {
        this.data = b;
    }
}
