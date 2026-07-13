package com.volcengine.common.innerapi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface ConfigService {
    public static final String daemon_config = "daemon_config";
    public static final String download_config = "download_config";
    public static final String engine_config = "engine_config";
    public static final String file_channel_config = "file_channel_config";
    public static final String key_code_config = "key_code_config";
    public static final String logger_config = "logger_config";
    public static final String monitor_config = "monitor_config";
    public static final String network_config = "network_config";
    public static final String plugin_config = "plugin_config";
    public static final String sensor_config = "sensor_config";
    public static final String switch_config = "switch_config";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConfigName {
    }

    public interface ConfigObserver {
        void onReceiveConfig(String str, String str2);
    }

    void dispatchConfig(String str, String str2);

    String getConfig(String str);

    JSONObject getConfigJson(String str);

    void register(String str, ConfigObserver configObserver);

    void storeConfig(String str, String str2);

    void unregister(String str, ConfigObserver configObserver);
}
