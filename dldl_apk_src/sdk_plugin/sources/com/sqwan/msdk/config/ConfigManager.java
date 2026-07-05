package com.sqwan.msdk.config;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQAppConfigUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ConfigManager {
    private static ConfigManager instance;
    private String appKey;
    private int loginCode = 1;
    private Context mContext;
    private HashMap<String, String> roleInfo;
    private int scode;
    private SQAppConfig sqAppConfig;

    private ConfigManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static ConfigManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager(context);
                }
            }
        }
        return instance;
    }

    public void initConfig() {
        SQAppConfigUtil sQAppConfigUtil = new SQAppConfigUtil(this.mContext);
        sQAppConfigUtil.init();
        this.sqAppConfig = sQAppConfigUtil.getSQAppConfig();
    }

    public SQAppConfig getSQAppConfig() {
        return this.sqAppConfig;
    }

    public void setAppKey(String str) {
        LogUtil.i("ConfigManager--> set app key" + str);
        this.appKey = str;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setLoginCode(int i) {
        this.loginCode = i;
    }

    public int getLoginCode() {
        return this.loginCode;
    }

    public boolean isSplashSDK() {
        return !TextUtils.isEmpty(MultiSdkManager.getInstance().getScut3()) || this.loginCode == 1;
    }

    public boolean isSimplifiedSDK() {
        return this.loginCode == 1;
    }

    public void setLessFunctionCode(int i) {
        this.scode = i;
    }

    public int getLessFunctionCode() {
        return this.scode;
    }

    public boolean isLessFunction() {
        return this.scode == 1;
    }

    public void setRoleInfo(HashMap<String, String> map) {
        this.roleInfo = map;
    }

    public HashMap<String, String> getRoleInfo() {
        return this.roleInfo;
    }

    public boolean isSqSDK() {
        return TextUtils.isEmpty(MultiSdkManager.getInstance().getScut3());
    }
}
