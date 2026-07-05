package com.alipay.deviceid;

import android.content.Context;
import com.alipay.deviceid.module.senative.DeviceIdUtil;
import com.alipay.deviceid.module.x.a;
import com.alipay.deviceid.module.x.b;
import com.alipay.deviceid.module.x.bw;
import com.alipay.deviceid.module.x.bx;
import com.alipay.deviceid.module.x.c;
import com.alipay.deviceid.module.x.cc;
import com.alipay.deviceid.module.x.e;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class DeviceTokenClient {
    public static final String INARGS_FACE_MD5 = "md5";
    public static final String INARGS_FACE_TRACEID = "traceid";
    private static String appKeyClientCache = "";
    private static String appNameCache = "";
    private static DeviceTokenClient deviceTokenClientInstance;
    private Context context;
    private AtomicBoolean isRepInitializing = new AtomicBoolean(false);

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    public static class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
    }

    private DeviceTokenClient(Context context) {
        this.context = null;
        if (context == null) {
            throw new IllegalArgumentException("DeviceTokenClient initialization error: context is null.");
        }
        this.context = context;
    }

    public static DeviceTokenClient getInstance(Context context) {
        if (deviceTokenClientInstance == null) {
            synchronized (DeviceTokenClient.class) {
                if (deviceTokenClientInstance == null) {
                    deviceTokenClientInstance = new DeviceTokenClient(context);
                }
            }
        }
        return deviceTokenClientInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeSo() {
        if (this.isRepInitializing.getAndSet(true)) {
            return;
        }
        DeviceIdUtil.getInstance(this.context).initialize();
    }

    public static void setEnvConfig(int i) {
        c.a().a = i;
    }

    public static void setGatewayAddress(String str) {
        b.a = str;
    }

    public String getExactID(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return bx.b(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public TokenResult getTokenResult() {
        TokenResult tokenResult = new TokenResult();
        tokenResult.apdid = "";
        tokenResult.clientKey = "";
        tokenResult.apdidToken = "";
        tokenResult.apdidToken = bw.a(this.context, appNameCache);
        if (tokenResult.apdidToken != null && !"".equals(tokenResult.apdidToken)) {
            return tokenResult;
        }
        tokenResult.apdidToken = bw.a(this.context, "public");
        if (tokenResult.apdidToken != null && !"".equals(tokenResult.apdidToken)) {
            return tokenResult;
        }
        String str = appNameCache;
        if (str == null || appKeyClientCache == null || "".equals(str) || "".equals(appKeyClientCache)) {
            tokenResult.apdidToken = "";
            return tokenResult;
        }
        initToken(appNameCache, appKeyClientCache, new InitResultListener() { // from class: com.alipay.deviceid.DeviceTokenClient.2
            @Override // com.alipay.deviceid.DeviceTokenClient.InitResultListener
            public final void onResult(String str2, int i) {
            }
        });
        return tokenResult;
    }

    public void initToken(String str, String str2, InitResultListener initResultListener) {
        initToken(str, str2, null, initResultListener);
    }

    public void initToken(final String str, String str2, Map<String, String> map, final InitResultListener initResultListener) {
        if (e.a(str)) {
            if (initResultListener != null) {
                initResultListener.onResult("", 2);
                return;
            }
            return;
        }
        if (e.a(str2)) {
            if (initResultListener != null) {
                initResultListener.onResult("", 3);
                return;
            }
            return;
        }
        appKeyClientCache = str2;
        appNameCache = str;
        final HashMap map2 = new HashMap();
        map2.put("appName", str);
        map2.put("appKeyClient", str2);
        map2.put("rpcVersion", Constants.VIA_SHARE_TYPE_INFO);
        map2.put("appchannel", "openapi");
        if (map != null && map.size() > 0) {
            String str3 = map.get("md5");
            String str4 = map.get(INARGS_FACE_TRACEID);
            if (str3 == null) {
                str3 = "";
            }
            map2.put("md5", str3);
            map2.put(INARGS_FACE_TRACEID, str4 != null ? str4 : "");
        }
        cc.a().a(new Runnable() { // from class: com.alipay.deviceid.DeviceTokenClient.1
            @Override // java.lang.Runnable
            public final void run() {
                DeviceTokenClient.this.initializeSo();
                try {
                    int iA = new a(DeviceTokenClient.this.context).a(map2);
                    if (initResultListener == null) {
                        return;
                    }
                    if (iA != 0) {
                        initResultListener.onResult("", iA);
                    } else {
                        initResultListener.onResult(a.a(DeviceTokenClient.this.context, str), 0);
                    }
                } catch (Throwable unused) {
                    initResultListener.onResult("", 5);
                }
            }
        });
    }
}
