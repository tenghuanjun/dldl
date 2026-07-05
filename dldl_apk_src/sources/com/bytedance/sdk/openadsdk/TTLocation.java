package com.bytedance.sdk.openadsdk;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class TTLocation implements LocationProvider {
    private double a;
    private double b;

    public TTLocation(double d, double d2) {
        this.a = 0.0d;
        this.b = 0.0d;
        this.a = d;
        this.b = d2;
    }

    @Override // com.bytedance.sdk.openadsdk.LocationProvider
    public double getLatitude() {
        return this.a;
    }

    public void setLatitude(double d) {
        this.a = d;
    }

    @Override // com.bytedance.sdk.openadsdk.LocationProvider
    public double getLongitude() {
        return this.b;
    }

    public void setLongitude(double d) {
        this.b = d;
    }
}
