package com.duowan.networkmars.wup;

import com.duowan.auk.ArkValue;
import com.duowan.auk.http.v2.executor.FunctionExecutor;
import com.duowan.auk.http.v2.executor.MultiFunctionExecutor;
import com.duowan.networkmars.http.HttpExecutor;
import com.duowan.networkmars.hysignal.HySignalExecutor;
import com.duowan.networkmars.hysignal.HySignalSDK;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.api.NSLaunchApi;
import com.huya.mtp.utils.DeviceUtils;
import com.sqwan.msdk.api.IMUrl;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class KiwiWupFunction<Req extends JceStruct, Rsp extends JceStruct> extends HaWupFunction<Req, Rsp> {
    private static final String DEFAULT_REQ_KEY = "tReq";
    private static final String DEFAULT_RSP_KEY = "tRsp";
    static final String KEY_CHANNEL = "channel";
    private static final String KEY_CODE = "";
    static final String KEY_IMEI = "imei";
    static final String KEY_PLATFORM = "platform";
    static final String KEY_UID = "uid";
    static final String KEY_VERSION = "version";
    static final String KEY_YY_UID = "yyuid";
    private static HttpExecutor mHttpExecutor;
    private static HySignalExecutor mHySignalExecutor;
    private static MultiFunctionExecutor mMultiFunctionExecutor;

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public String getCodeKey() {
        return "";
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public String getRequestKey() {
        return DEFAULT_REQ_KEY;
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public String getResponseKey() {
        return DEFAULT_RSP_KEY;
    }

    protected boolean needUserInfo() {
        return false;
    }

    public static MultiFunctionExecutor getMultiFunctionExecutor() {
        return mMultiFunctionExecutor;
    }

    static {
        if (0 == 0) {
            synchronized (KiwiWupFunction.class) {
                if (mMultiFunctionExecutor == null) {
                    mMultiFunctionExecutor = new MultiFunctionExecutor(new FunctionExecutor[0]);
                    addHySignalExecutor();
                }
            }
        }
    }

    private static void addHySignalExecutor() {
        HySignalExecutor hySignalExecutor = new HySignalExecutor();
        mHySignalExecutor = hySignalExecutor;
        mMultiFunctionExecutor.addExecutor(hySignalExecutor);
    }

    private static void addHttpExecutor() {
        HttpExecutor httpExecutor = new HttpExecutor();
        mHttpExecutor = httpExecutor;
        mMultiFunctionExecutor.addExecutor(httpExecutor);
    }

    private static void removeHySignalExecutor() {
        HySignalExecutor hySignalExecutor = mHySignalExecutor;
        if (hySignalExecutor == null || !mMultiFunctionExecutor.hasExecutor(hySignalExecutor)) {
            return;
        }
        mMultiFunctionExecutor.removeExecutor(mHySignalExecutor);
    }

    public KiwiWupFunction(Req req) {
        super(req);
        setFunctionExecutor(mMultiFunctionExecutor);
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getUrl() {
        return ((NSLaunchApi) NS.get(NSLaunchApi.class)).getClientIp();
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public Map<String, Object> getOtherParams() {
        HashMap map = new HashMap();
        putCommonHeaders(map, needUserInfo());
        return map;
    }

    protected void putCommonHeaders(Map<String, Object> map, boolean z) {
        map.put("platform", IMUrl.OS);
        map.put("version", HySignalSDK.getInstance().getAppVersion());
        map.put("channel", HySignalSDK.getInstance().getChannel());
        if (z) {
            map.put(KEY_YY_UID, String.valueOf(HySignalSDK.getInstance().getUid()));
            map.put("uid", String.valueOf(HySignalSDK.getInstance().getUid()));
            map.put("imei", DeviceUtils.getImei(HySignalSDK.getInstance().getAppContext()));
        }
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public String getCacheKey() {
        return !ArkValue.debuggable() ? super.getCacheKey() : String.format("%s%s", "debug_", super.getCacheKey());
    }
}
