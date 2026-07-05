package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DoubleField extends NumberField {
    private double data;

    DoubleField(double d, int i) {
        super(i);
        this.data = d;
    }

    @Override // com.duowan.taf.jce.dynamic.NumberField
    public Number getNumber() {
        return Double.valueOf(this.data);
    }

    public double get() {
        return this.data;
    }

    public void set(double d) {
        this.data = d;
    }
}
