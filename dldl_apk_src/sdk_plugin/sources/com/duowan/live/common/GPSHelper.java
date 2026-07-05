package com.duowan.live.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GPSHelper {
    private static final double EARTH_RADIUS = 6378137.0d;

    public static double gps2m(double d, double d2, double d3, double d4) {
        double d5 = (d * 3.141592653589793d) / 180.0d;
        double d6 = (d3 * 3.141592653589793d) / 180.0d;
        return Math.round(((Math.asin(Math.sqrt(Math.pow(Math.sin((d5 - d6) / 2.0d), 2.0d) + ((Math.cos(d5) * Math.cos(d6)) * Math.pow(Math.sin((((d2 - d4) * 3.141592653589793d) / 180.0d) / 2.0d), 2.0d)))) * 2.0d) * EARTH_RADIUS) * 10000.0d) / 10000;
    }
}
