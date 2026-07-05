package com.sq.tool.network;

import com.sqnetwork.voly.Request;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.config.ConfigManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignInterceptor implements Interceptor {
    private SignV1Interceptor mV1;
    private SignV2Interceptor mV2;
    private SignV3Interceptor mV3;
    private SignV3Interceptor mV4;
    private SignV5Interceptor mV5;

    public enum SignVersion {
        V1,
        V2,
        V3,
        V4,
        V5
    }

    private synchronized void init() {
        if (this.mV1 != null) {
            return;
        }
        String appKey = ConfigManager.getInstance(SQContextWrapper.getApplicationContext()).getAppKey();
        if (appKey == null || appKey.isEmpty()) {
            throw new IllegalStateException("需要在ConfigManager设置key后调用");
        }
        this.mV1 = new SignV1Interceptor(appKey);
        this.mV2 = new SignV2Interceptor(appKey);
        this.mV3 = new SignV3Interceptor(appKey);
        this.mV4 = new SignV3Interceptor("!/DIzcJLYE)@X7UC~b9Pn]}<eAr?|Wlw");
        this.mV5 = new SignV5Interceptor(appKey);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        SignVersion signVersion;
        Request request = (Request) chain.request().tag(Request.class);
        if (request != null && (signVersion = (SignVersion) request.getTag(SignVersion.class)) != null) {
            init();
            int i = AnonymousClass1.$SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[signVersion.ordinal()];
            if (i == 1) {
                return this.mV1.intercept(chain);
            }
            if (i == 2) {
                return this.mV2.intercept(chain);
            }
            if (i == 3) {
                return this.mV3.intercept(chain);
            }
            if (i == 4) {
                return this.mV4.intercept(chain);
            }
            if (i == 5) {
                return this.mV5.intercept(chain);
            }
        }
        return chain.proceed(chain.request());
    }

    /* JADX INFO: renamed from: com.sq.tool.network.SignInterceptor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion;

        static {
            int[] iArr = new int[SignVersion.values().length];
            $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion = iArr;
            try {
                iArr[SignVersion.V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[SignVersion.V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[SignVersion.V3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[SignVersion.V4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sq$tool$network$SignInterceptor$SignVersion[SignVersion.V5.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
