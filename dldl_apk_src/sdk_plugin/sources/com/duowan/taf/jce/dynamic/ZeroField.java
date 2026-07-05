package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ZeroField extends NumberField {
    @Override // com.duowan.taf.jce.dynamic.NumberField
    public byte byteValue() {
        return (byte) 0;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public double doubleValue() {
        return 0.0d;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public float floatValue() {
        return 0.0f;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public int intValue() {
        return 0;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public long longValue() {
        return 0L;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public short shortValue() {
        return (short) 0;
    }

    ZeroField(int i) {
        super(i);
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public Number getNumber() {
        return 0;
    }
}
