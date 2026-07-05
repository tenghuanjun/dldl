package com.sq.webview.local;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ConfigItem {
    Boolean _switch;
    Boolean cache;
    String hash;
    String list_url;
    Boolean main_switch;
    String name;
    String package_url;
    String url;

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getList_url() {
        return this.list_url;
    }

    public Boolean getMain_switch() {
        return this.main_switch;
    }

    public String getPackage_url() {
        return this.package_url;
    }

    public Boolean getCache() {
        return this.cache;
    }

    public Boolean getSwitch() {
        return this._switch;
    }

    public String getHash() {
        return this.hash;
    }

    public static ConfigItem parse(JSONObject jsonObject) {
        ConfigItem configItem = new ConfigItem();
        configItem.name = jsonObject.optString("name");
        configItem.list_url = jsonObject.optString("list_url");
        configItem.url = jsonObject.optString("url");
        configItem.package_url = jsonObject.optString("package_url");
        configItem.hash = jsonObject.optString("hash");
        configItem._switch = Boolean.valueOf(jsonObject.optBoolean("switch"));
        configItem.cache = Boolean.valueOf(jsonObject.optBoolean("cache"));
        configItem.main_switch = Boolean.valueOf(jsonObject.optBoolean("main_switch"));
        return configItem;
    }
}
