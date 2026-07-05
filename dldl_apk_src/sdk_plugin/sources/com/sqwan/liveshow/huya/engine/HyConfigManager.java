package com.sqwan.liveshow.huya.engine;

import android.text.TextUtils;
import com.sqwan.base.L;
import com.sqwan.common.util.AESUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.liveshow.huya.bean.ConfigBean;
import com.sqwan.liveshow.huya.engine.bean.HyConfig;
import com.sqwan.msdk.config.ConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class HyConfigManager {
    private static final HyConfigManager ourInstance = new HyConfigManager();
    public HyConfig hyConfig;

    public static HyConfigManager getInstance() {
        return ourInstance;
    }

    private HyConfigManager() {
    }

    public boolean init(ConfigBean.ItemsBean itemsBean) {
        if (itemsBean == null) {
            return false;
        }
        this.hyConfig = new HyConfig();
        this.hyConfig.hyUiConfig = new HyConfig.HyUiConfig();
        String strDecodeAccessKey = decodeAccessKey(itemsBean.getApp_key());
        this.hyConfig.gameId = Integer.parseInt(itemsBean.getGame_id());
        this.hyConfig.appId = itemsBean.getApp_id();
        this.hyConfig.appKey = strDecodeAccessKey;
        LogUtil.i(this.hyConfig.toString());
        return true;
    }

    private String decodeAccessKey(String str) {
        String strSubstring;
        String appKey = ConfigManager.getInstance(L.getApplicationContext()).getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            strSubstring = "";
        } else {
            int length = appKey.length();
            if (length < 16) {
                StringBuilder sb = new StringBuilder(appKey);
                for (int i = 0; i < 16 - length; i++) {
                    sb.append("0");
                }
                strSubstring = sb.toString();
            } else {
                strSubstring = appKey.substring(0, 16);
            }
        }
        LogUtil.i("解密key：" + strSubstring);
        String strDecryptString = AESUtil.decryptString(str, strSubstring);
        LogUtil.i("解密后：" + strDecryptString);
        return strDecryptString;
    }
}
