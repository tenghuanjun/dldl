package com.sqwan.liveshow.request;

import android.content.Context;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.mod.liveshow.BaseBean;
import com.sqwan.common.request.CommonParamsV2;
import com.sqwan.common.util.SQContextWrapper;
import com.sy37sdk.account.AccountCache;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveShowParams extends CommonParamsV2 {
    protected static final String DRID = "drid";
    protected static final String DSID = "dsid";
    protected static final String TOKEN = "token";

    @Override // com.sqwan.common.request.CommonParamsV2, com.sdk.sq.net.RequestBuilder.ParamsTransformer
    public Map<String, Object> transform(Map<String, Object> map) {
        Map<String, Object> mapTransform = super.transform(map);
        if (mapTransform == null) {
            mapTransform = new HashMap<>();
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        BaseBean baseUserInfo = CommonConfigs.getInstance().getBaseUserInfo();
        if (baseUserInfo != null) {
            mapTransform.put("drid", baseUserInfo.roleId);
            mapTransform.put("dsid", baseUserInfo.serverId);
        }
        mapTransform.put("token", AccountCache.getToken(applicationContext));
        return mapTransform;
    }
}
