package com.sqwan.msdk.api;

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
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
class CommonParamsV1 implements RequestBuilder.ParamsTransformer {
    CommonParamsV1() {
    }

    @Override // com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        String gid = MultiSDKUtils.getGID(applicationContext);
        String pid = MultiSDKUtils.getPID(applicationContext);
        String refer = MultiSDKUtils.getRefer(applicationContext);
        String versionName = AppUtils.getVersionName(applicationContext);
        map.put("gid", gid);
        map.put("pid", pid);
        map.put("refer", refer);
        map.put("version", versionName);
        map.put("time", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("dev", DevLogic.getInstance(applicationContext).getValue());
        String androidId = DevManager.getAndroidId(DevLogic.getInstance(applicationContext).isAuthCheck());
        if (androidId == null) {
            androidId = "";
        }
        map.put("android_id", androidId);
        map.put("sversion", VersionUtil.sdkVersion);
        map.put("gwversion", "4.6.7");
        map.put(SqConstants.HOST_SDK_VERSION, VersionUtil.getOriginalVersion());
        map.put(SqConstants.IS_ROOT, RootLogic.getInstance(applicationContext).getValue());
        map.put(SqConstants.IS_SIMULATOR, SimulatorLogic.getInstance(applicationContext).getValue());
        return map;
    }
}
