package com.sqwan.common.mod;

import android.content.Context;
import com.sqwan.base.L;
import com.sqwan.common.mod.liveshow.BaseBean;
import com.sqwan.common.util.SpUtils;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonConfigs {
    private static final CommonConfigs ourInstance = new CommonConfigs();
    private BaseBean baseUserInfo;
    private SQAppConfig sqAppConfig;
    private String session_id = "";
    private String appKey = "";
    private String spkey = "";

    public static CommonConfigs getInstance() {
        return ourInstance;
    }

    private CommonConfigs() {
        setAppKey(ConfigManager.getInstance(L.getApplicationContext()).getAppKey());
        this.sqAppConfig = ConfigManager.getInstance(L.getApplicationContext()).getSQAppConfig();
    }

    public String getSession_id() {
        return this.session_id;
    }

    public void setSession_id(String str) {
        this.session_id = str;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public String getSpkey() {
        return this.spkey;
    }

    public void setSpkey(String str) {
        this.spkey = str;
    }

    public SQAppConfig getSqAppConfig() {
        return this.sqAppConfig;
    }

    public BaseBean getBaseUserInfo() {
        return this.baseUserInfo;
    }

    public void setBaseUserInfo(BaseBean baseBean) {
        this.baseUserInfo = baseBean;
    }

    public long getCurrentTime() {
        return ModHelper.getConfig().getCommonConfig().getCurrentTime();
    }

    public String getUserId() {
        return ModHelper.getConfig().getCommonConfig().getUserId();
    }

    public boolean getIsNewRole(Context context) {
        SpUtils spUtils = new SpUtils(context, "new_guide");
        getInstance().setSpkey("guide_" + getBaseUserInfo().roleId);
        return !spUtils.getBoolean(getInstance().getSpkey());
    }

    public void setNewRoleId(Context context) {
        SpUtils spUtils = new SpUtils(context, "new_guide");
        getInstance().setSpkey("guide_" + getBaseUserInfo().roleId);
        spUtils.put(getInstance().getSpkey(), true);
    }
}
