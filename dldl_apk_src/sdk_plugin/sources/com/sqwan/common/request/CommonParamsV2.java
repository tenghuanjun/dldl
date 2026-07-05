package com.sqwan.common.request;

import android.content.Context;
import com.huya.statistics.core.StatisticsContent;
import com.sdk.sq.net.RequestBuilder;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.RootLogic;
import com.sqwan.common.dev.SimulatorLogic;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.IMUrl;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonParamsV2 implements RequestBuilder.ParamsTransformer {
    private final boolean mBeforeActive;

    public CommonParamsV2() {
        this(false);
    }

    public CommonParamsV2(boolean z) {
        this.mBeforeActive = z;
    }

    @Override // com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(applicationContext).getSQAppConfig();
        String oaid = DeviceUtils.getOaid(applicationContext);
        map.put("pid", sQAppConfig.getPartner());
        map.put("gid", sQAppConfig.getGameid());
        map.put("refer", sQAppConfig.getRefer());
        map.put("dev", DevLogic.getInstance(applicationContext).getValue());
        map.put("sversion", VersionUtil.sdkVersion);
        map.put("version", AppUtils.getVersionName(applicationContext));
        map.put("gwversion", "4.6.7");
        map.put("time", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("scut", getCodeOfLogin(applicationContext));
        map.put("oaid", oaid);
        if (MultiSdkManager.getInstance().isScut3()) {
            map.put("scut3", MultiSdkManager.getInstance().getScut3());
        }
        map.put(StatisticsContent.FROM, IMUrl.OS);
        map.put(SqConstants.HOST_SDK_VERSION, VersionUtil.getOriginalVersion());
        map.put(SqConstants.IS_ROOT, RootLogic.getInstance(applicationContext).getValue());
        map.put(SqConstants.IS_SIMULATOR, SimulatorLogic.getInstance(applicationContext).getValue());
        return map;
    }

    protected String getCodeOfLogin(Context context) {
        if (this.mBeforeActive) {
            return "";
        }
        return ConfigManager.getInstance(context).getLoginCode() + "";
    }
}
