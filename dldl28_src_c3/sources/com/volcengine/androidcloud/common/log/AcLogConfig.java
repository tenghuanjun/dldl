package com.volcengine.androidcloud.common.log;

import android.content.Context;
import android.text.TextUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.j.i;
import java.io.File;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AcLogConfig {
    private static final boolean DEFAULT_ALOG = true;
    private static final boolean DEFAULT_SALVAGE = true;
    private static final String KEY_ALOG = "alog";
    private static final String KEY_DEBUG = "debug";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_FORMAT = "format";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_REPLACE = "replace";
    private static final String KEY_SALVAGE = "salvage";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_TARGETS = "targets";
    private static final String LOCAL_CONFIG_NAME = "logger.config";
    private static final String TAG = "AcLogConfig";

    public static boolean alog(Context context) {
        try {
            JSONObject cloudConfig = getCloudConfig(context);
            boolean z = cloudConfig.has(KEY_ALOG) ? cloudConfig.getBoolean(KEY_ALOG) : true;
            JSONObject localConfig = getLocalConfig(context);
            return (localConfig == null || !localConfig.has(KEY_ALOG)) ? z : cloudConfig.getBoolean(KEY_ALOG);
        } catch (Exception e) {
            AcLog.e(TAG, e.getMessage());
            return true;
        }
    }

    private static JSONObject getCloudConfig(Context context) {
        return SDKContext.getConfigService().getConfigJson(ConfigService.logger_config);
    }

    private static JSONObject getLocalConfig(Context context) {
        try {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                String strF = i.f(new File(externalCacheDir, LOCAL_CONFIG_NAME).getAbsolutePath());
                if (!TextUtils.isEmpty(strF)) {
                    return new JSONObject(strF);
                }
            }
        } catch (Exception e) {
            AcLog.e(TAG, e.getMessage());
        }
        return null;
    }

    public static void init(Context context) {
        initConfig(AcLogConfigSource.CloudConfig, getCloudConfig(context));
        initConfig(AcLogConfigSource.LocalConfig, getLocalConfig(context));
    }

    private static void initConfig(AcLogConfigSource acLogConfigSource, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(KEY_DEBUG)) {
                    try {
                        AcLog.setDebug(acLogConfigSource, jSONObject.getBoolean(KEY_DEBUG));
                    } catch (JSONException e) {
                        AcLog.e(TAG, "Debug config fail ", e);
                    }
                }
                if (jSONObject.has("level")) {
                    try {
                        AcLog.setLevel(AcLog.Level.getLevel(jSONObject.getString("level")));
                    } catch (IllegalArgumentException e2) {
                        AcLog.e(TAG, "Level config fail ", e2);
                    }
                }
                if (jSONObject.has(KEY_START_TIME) && jSONObject.has(KEY_END_TIME)) {
                    try {
                        AcLog.setRange(jSONObject.getLong(KEY_START_TIME), jSONObject.getLong(KEY_END_TIME));
                    } catch (JSONException e3) {
                        AcLog.e(TAG, "Range config fail ", e3);
                    }
                }
                if (jSONObject.has(KEY_REPLACE)) {
                    try {
                        AcLog.setReplace(jSONObject.getString(KEY_REPLACE));
                    } catch (JSONException e4) {
                        AcLog.e(TAG, "Format config fail ", e4);
                    }
                }
                if (jSONObject.has(KEY_TAGS)) {
                    try {
                        JSONArray jSONArray = jSONObject.getJSONArray(KEY_TAGS);
                        HashSet hashSet = new HashSet();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            hashSet.add(jSONArray.get(i).toString());
                        }
                        if (hashSet.size() > 0) {
                            AcLog.setTags(hashSet);
                        }
                    } catch (JSONException e5) {
                        AcLog.e(TAG, "Tags config fail ", e5);
                    }
                }
                if (jSONObject.has(KEY_FORMAT)) {
                    try {
                        AcLog.setFormat(jSONObject.getBoolean(KEY_FORMAT));
                    } catch (JSONException e6) {
                        AcLog.e(TAG, "Format config fail ", e6);
                    }
                }
                if (jSONObject.has(KEY_TARGETS)) {
                    try {
                        int i2 = jSONObject.getInt(KEY_TARGETS);
                        if (i2 >= 0) {
                            AcLog.setTargets(i2);
                        }
                    } catch (JSONException e7) {
                        AcLog.e(TAG, "Targets config fail ", e7);
                    }
                }
            } catch (Exception e8) {
                AcLog.e(TAG, e8.getMessage());
            }
        }
    }

    public static boolean salvage(Context context) {
        try {
            JSONObject cloudConfig = getCloudConfig(context);
            boolean z = cloudConfig.has(KEY_SALVAGE) ? cloudConfig.getBoolean(KEY_SALVAGE) : true;
            JSONObject localConfig = getLocalConfig(context);
            return (localConfig == null || !localConfig.has(KEY_SALVAGE)) ? z : cloudConfig.getBoolean(KEY_SALVAGE);
        } catch (Exception e) {
            AcLog.e(TAG, e.getMessage());
            return true;
        }
    }
}
