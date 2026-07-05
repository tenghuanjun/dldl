package com.sy37sdk.plugin;

import android.content.Context;
import com.sq.eventbus.core.EventBus;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tools.Logger;
import com.sqwan.common.eventbus.PreInitEvent;
import com.sqwan.common.eventbus.SActiveEvent;
import com.sqwan.common.mod.plugin.IPluginMod;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.plugin.data.PluginDownloadManager;
import com.sy37sdk.plugin.data.SpPluginConfig;
import com.sy37sdk.plugin.eventbus.EventBusIndex;
import com.sy37sdk.plugin.net.PluginRequestManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PluginModImpl implements IPluginMod {
    private final Context mContext;
    private SpPluginConfig mPluginConfig;
    private PluginRequestManager mRequestManager;

    @Override // com.sqwan.common.mod.plugin.IPluginMod
    public void refresh(Context context) {
    }

    public PluginModImpl(Context context) {
        this.mContext = context;
        EventBus.getDefault().addIndex(new EventBusIndex());
        EventBus.getDefault().register(this);
        this.mRequestManager = new PluginRequestManager();
        this.mPluginConfig = new SpPluginConfig(context);
        LogUtil.i("PluginModImpl init");
    }

    @Override // com.sqwan.common.mod.plugin.IPluginMod
    public int getPluginVersion() {
        return this.mPluginConfig.getPluginCurrentVersion();
    }

    public void onSActiveEvent(SActiveEvent sActiveEvent) {
        LogUtil.e("插件模块激活请求成功");
    }

    public void onPreInitEvent(PreInitEvent preInitEvent) {
        Logger.info("当前插件版本号：" + this.mPluginConfig.getPluginCurrentVersion(), new Object[0]);
        this.mRequestManager.getPluginConfig(this.mPluginConfig.getPluginCurrentVersion(), new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.plugin.PluginModImpl.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                PluginModImpl.this.parsePluginConfig(jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parsePluginConfig(JSONObject jSONObject) {
        try {
            int iOptInt = jSONObject.optInt("plugin_version");
            this.mPluginConfig.setPluginVersion(iOptInt);
            this.mPluginConfig.setPluginUrl(jSONObject.optString("plugin_url"));
            this.mPluginConfig.setPluginHash(jSONObject.optString("plugin_hash"));
            this.mPluginConfig.setPluginConfId(jSONObject.optInt("conf_id"));
            this.mPluginConfig.setPluginType(jSONObject.optInt("type"));
            int pluginCurrentVersion = this.mPluginConfig.getPluginCurrentVersion();
            if (pluginCurrentVersion != iOptInt) {
                LogUtil.i("当前插件版本: " + pluginCurrentVersion + ", 配置插件版本: " + iOptInt);
                new PluginDownloadManager(this.mContext).handlePlugin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
