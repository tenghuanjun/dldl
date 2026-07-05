package com.sqwan.common.request;

import android.content.Context;
import com.sdk.sq.net.RequestBuilder;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.RootLogic;
import com.sqwan.common.dev.SimulatorLogic;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonParamsV1 implements RequestBuilder.ParamsTransformer {
    @Override // com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(applicationContext).getSQAppConfig();
        String gameid = sQAppConfig.getGameid();
        String partner = sQAppConfig.getPartner();
        String oaid = DeviceUtils.getOaid(applicationContext);
        String refer = sQAppConfig.getRefer();
        String versionName = AppUtils.getVersionName(applicationContext);
        map.put("gid", gameid);
        map.put("pid", partner);
        map.put("refer", refer);
        map.put("version", versionName);
        map.put("time", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("dev", DevLogic.getInstance(applicationContext).getValue());
        map.put("oaid", oaid);
        map.put("sversion", VersionUtil.sdkVersion);
        map.put("gwversion", "4.6.7");
        map.put(SqConstants.IS_ROOT, RootLogic.getInstance(applicationContext).getValue());
        map.put(SqConstants.IS_SIMULATOR, SimulatorLogic.getInstance(applicationContext).getValue());
        return map;
    }
}
