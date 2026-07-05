package com.sy37sdk.plugin.data;

import android.content.Context;
import com.sqwan.common.util.SpUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SpPluginConfig {
    private SpUtils mSpUtils;
    private final String SQ__PLUGIN_CONFIG = "sq_plugin_config";
    private final String KEY_PLUGIN_VERSION = "plugin_version";
    private final String KEY_PLUGIN_URL = "plugin_url";
    private final String KEY_PLUGIN_HASH = "plugin_hash";
    private final String KEY_CONF_ID = "plugin_conf_id";
    private final String KEY_TAYE = "plugin_type";
    private final String KEY_CURRENT_VERSION = "plugin_current_version";
    private final String KEY_LATEST_VERSION = "plugin_latest_version";

    public SpPluginConfig(Context context) {
        this.mSpUtils = new SpUtils(context, "sq_plugin_config");
    }

    public void setPluginVersion(int i) {
        this.mSpUtils.put("plugin_version", i);
    }

    public int getPluginVersion() {
        return this.mSpUtils.getInt("plugin_version");
    }

    public void setPluginUrl(String str) {
        this.mSpUtils.put("plugin_url", str);
    }

    public String getPluginUrl() {
        return this.mSpUtils.getString("plugin_url");
    }

    public void setPluginHash(String str) {
        this.mSpUtils.put("plugin_hash", str);
    }

    public String getPluginHash() {
        return this.mSpUtils.getString("plugin_hash");
    }

    public void setPluginConfId(int i) {
        this.mSpUtils.put("plugin_conf_id", i);
    }

    public int getPluginConfId() {
        return this.mSpUtils.getInt("plugin_conf_id");
    }

    public void setPluginType(int i) {
        this.mSpUtils.put("plugin_type", i);
    }

    public int getPluginType() {
        return this.mSpUtils.getInt("plugin_type");
    }

    public void setPluginCurrentVersion(int i) {
        this.mSpUtils.put("plugin_current_version", i);
    }

    public int getPluginCurrentVersion() {
        return this.mSpUtils.getInt("plugin_current_version");
    }

    public void setPluginLatestVersion(int i) {
        this.mSpUtils.put("plugin_latest_version", i);
    }

    public int getPluginLatestVersion() {
        return this.mSpUtils.getInt("plugin_latest_version");
    }
}
