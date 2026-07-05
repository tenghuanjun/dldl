package com.duowan.kiwi.barrage.report;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageLost {
    private static int sBarrageLostTimes;

    public static void recordLostBarrage() {
        sBarrageLostTimes++;
    }

    public static void clearLostBarrage() {
        sBarrageLostTimes = 0;
    }

    public static int getLastBarrageTimes() {
        return sBarrageLostTimes;
    }
}
