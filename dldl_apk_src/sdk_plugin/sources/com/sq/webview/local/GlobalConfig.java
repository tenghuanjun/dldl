package com.sq.webview.local;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GlobalConfig {
    private List<ConfigItem> list;
    private boolean main_switch;

    public boolean getMain_switch() {
        return this.main_switch;
    }

    public List<ConfigItem> getList() {
        return this.list;
    }

    public static GlobalConfig parse(JSONObject jsonObject) {
        JSONArray jSONArrayOptJSONArray = jsonObject.optJSONArray("list");
        boolean zOptBoolean = jsonObject.optBoolean("main_switch");
        GlobalConfig globalConfig = new GlobalConfig();
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(ConfigItem.parse(jSONArrayOptJSONArray.getJSONObject(i)));
            }
            globalConfig.list = arrayList;
            globalConfig.main_switch = zOptBoolean;
        } catch (Exception unused) {
        }
        return globalConfig;
    }
}
