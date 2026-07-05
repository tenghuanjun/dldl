package com.alipay.zoloz.toyger.algorithm;

import java.util.ArrayList;
import java.util.List;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_zkfv_1ts_1tj;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Nautilus {
    public static List<Integer> check_image_frame_result;
    public static List<Integer> check_raw_frame_result;
    public static Nautilus mInstance;
    public static List<String> result_image_frame_hash;
    public static List<String> result_raw_frame_hash;

    static {
        java2jni_do_not_delete_this_library_zkfv_1ts_1tj.loadLibrary();
        result_raw_frame_hash = new ArrayList();
        result_image_frame_hash = new ArrayList();
        check_raw_frame_result = new ArrayList();
        check_image_frame_result = new ArrayList();
    }

    public Nautilus() {
        result_raw_frame_hash = new ArrayList();
        result_image_frame_hash = new ArrayList();
        check_raw_frame_result = new ArrayList();
        check_image_frame_result = new ArrayList();
    }

    public static native synchronized Nautilus createInstance();

    public static native Nautilus getInstance();

    public static native String get_hash(byte[] bArr);

    public native void java_check_image_data(byte[] bArr, long j);

    public native boolean java_get_sonar_data_result();

    public native void java_process_image_data(byte[] bArr, long j);

    public native void java_process_raw_data();

    public native void reset();
}
