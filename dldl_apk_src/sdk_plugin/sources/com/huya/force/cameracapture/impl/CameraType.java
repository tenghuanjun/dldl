package com.huya.force.cameracapture.impl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CameraType {
    public static final int BACK = 1;
    public static final int FRONT = 0;

    public static int switchType(int i) {
        return i == 0 ? 1 : 0;
    }

    public static int toCamera1(int i) {
        return i != 1 ? 1 : 0;
    }

    public static int toCamera2(int i) {
        return i != 1 ? 0 : 1;
    }
}
