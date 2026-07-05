package com.sy37sdk.core;

import android.content.Context;
import com.sdk.sq.net.RequestBuilder;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.data.cache.DevManager;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.RootLogic;
import com.sqwan.common.dev.SimulatorLogic;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.SQReportCore;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
class SCommonParam implements RequestBuilder.ParamsTransformer {
    SCommonParam() {
    }

    @Override // com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(applicationContext).getSQAppConfig();
        map.put("pid", sQAppConfig.getPartner());
        map.put("gid", sQAppConfig.getGameid());
        map.put("refer", sQAppConfig.getRefer());
        map.put("dev", DevLogic.getInstance(applicationContext).getValue());
        map.put("sversion", VersionUtil.sdkVersion);
        map.put("version", AppUtils.getVersionName(applicationContext));
        map.put("gwversion", "4.6.7");
        String mDev = SQReportCore.getInstance().getMDev();
        if (mDev == null) {
            mDev = "";
        }
        map.put("mdev", mDev);
        String androidId = DevManager.getAndroidId(DevLogic.getInstance(applicationContext).isAuthCheck());
        map.put("android_id", androidId != null ? androidId : "");
        map.put("time", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("scut", getCodeOfLogin(applicationContext));
        map.put(SqConstants.HOST_SDK_VERSION, VersionUtil.getOriginalVersion());
        map.put(SqConstants.IS_ROOT, RootLogic.getInstance(applicationContext).getValue());
        map.put(SqConstants.IS_SIMULATOR, SimulatorLogic.getInstance(applicationContext).getValue());
        return map;
    }

    protected String getCodeOfLogin(Context context) {
        return ConfigManager.getInstance(context).getLoginCode() + "";
    }
}
