package com.volcengine.common.contant;

import android.util.Pair;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface CommonErrorCode {
    public static final Pair<Integer, String> ERROR_CA_CERT_ERROR = Pair.create(80001, "ERROR_CA_CERT_ERROR");
    public static final Pair<Integer, String> ERROR_SDK_LOAD_ENGINE_FAILURE = new Pair<>(30005, "ERROR_SDK_LOAD_ENGINE_FAILURE");
    public static final Pair<Integer, String> ERROR_SDK_LOAD_ENGINE_NETWORK_ERROR = new Pair<>(30006, "ERROR_SDK_LOAD_ENGINE_NETWORK_ERROR");
    public static final Pair<Integer, String> ERROR_CONFIG_PLATFORM_AUTH_FAILED = Pair.create(31000, "ERROR_CONFIG_PLATFORM_AUTH_FAILED");
    public static final Pair<Integer, String> ERROR_REQUEST_INIT_PARAMS_FAILED = Pair.create(31001, "ERROR_REQUEST_INIT_PARAMS_FAILED");
    public static final Pair<Integer, String> ERROR_MATCH_INIT_PARAMS_FAILED = Pair.create(31002, "ERROR_MATCH_INIT_PARAMS_FAILED");
    public static final Pair<Integer, String> ERROR_REQUEST_PLUGIN_CONFIG_FAILED = Pair.create(31003, "ERROR_REQUEST_PLUGIN_CONFIG_FAILED");
    public static final Pair<Integer, String> ERROR_MATCH_PLUGIN_CONFIG_FAILED = Pair.create(31004, "ERROR_MATCH_PLUGIN_CONFIG_FAILED");
    public static final Pair<Integer, String> ERROR_CONFIG_PLATFORM_REQUEST_AUTH_FAILED = Pair.create(31005, "ERROR_CONFIG_PLATFORM_REQUEST_AUTH_FAILED");
    public static final Pair<Integer, String> ERROR_ABI_IS_NOT_SUPPORT = Pair.create(31006, "ERROR_ABI_IS_NOT_SUPPORT");
    public static final Pair<Integer, String> ERROR_DOWNLOAD_PLUGIN_FAILED = Pair.create(30201, "ERROR_DOWNLOAD_PLUGIN_FAILED");
    public static final Pair<Integer, String> ERROR_INJECT_DEX_FAILED = new Pair<>(30203, "ERROR_INJECT_DEX_FAILED");
    public static final Pair<Integer, String> ERROR_CHECK_PLUGIN_FAILED = Pair.create(30204, "ERROR_CHECK_PLUGIN_FAILED");
    public static final Pair<Integer, String> ERROR_LOAD_PLUGIN_FAILED = Pair.create(30205, "ERROR_LOAD_PLUGIN_FAILED");
    public static final Pair<Integer, String> ERROR_LOAD_CLASS_ERROR = Pair.create(30206, "ERROR_LOAD_CLASS_ERROR");
    public static final Pair<Integer, String> ERROR_MULTIPLE_PROCESS_UNSUPPORTED = Pair.create(30300, "ERROR_MULTIPLE_PROCESS_UNSUPPORTED");
    public static final Pair<Integer, String> ERROR_NET_WORK_ERROR = new Pair<>(60001, "ERROR_NET_REQUEST_ERROR");
    public static final Pair<Integer, String> ERROR_INIT_ACCOUNT_ID_ILLEGAL = new Pair<>(30009, "ERROR_INIT_ACCOUNT_ID_ILLEGAL");
}
