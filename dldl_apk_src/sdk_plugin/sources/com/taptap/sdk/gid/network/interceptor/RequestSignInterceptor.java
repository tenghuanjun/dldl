package com.taptap.sdk.gid.network.interceptor;

import com.taptap.sdk.base.network.sign.SignStrategy;
import com.taptap.sdk.common.network.utils.OkHttpUtils;
import com.taptap.sdk.okhttp3.Headers;
import com.taptap.sdk.okhttp3.HttpUrl;
import com.taptap.sdk.okhttp3.Interceptor;
import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.Response;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RequestSignInterceptor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/gid/network/interceptor/RequestSignInterceptor;", "Lcom/taptap/sdk/okhttp3/Interceptor;", "config", "Lcom/taptap/sdk/gid/network/interceptor/RequestSignInterceptor$Config;", "(Lcom/taptap/sdk/gid/network/interceptor/RequestSignInterceptor$Config;)V", "intercept", "Lcom/taptap/sdk/okhttp3/Response;", "chain", "Lcom/taptap/sdk/okhttp3/Interceptor$Chain;", "Config", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class RequestSignInterceptor implements Interceptor {
    private final Config config;

    public RequestSignInterceptor(Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    @Override // com.taptap.sdk.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request originRequest = chain.request();
        Request.Builder builderNewBuilder = originRequest.newBuilder();
        SignStrategy signStrategy = this.config.getSignStrategy();
        HttpUrl httpUrlUrl = originRequest.url();
        Intrinsics.checkNotNullExpressionValue(httpUrlUrl, "originRequest.url()");
        String strMethod = originRequest.method();
        Intrinsics.checkNotNullExpressionValue(strMethod, "originRequest.method()");
        Headers headers = originRequest.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "originRequest.headers()");
        OkHttpUtils okHttpUtils = OkHttpUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(originRequest, "originRequest");
        String strConvertBody2String = okHttpUtils.convertBody2String(originRequest);
        if (strConvertBody2String != null) {
            builderNewBuilder.header("X-Tap-Sign", signStrategy.calculate(httpUrlUrl, strMethod, headers, strConvertBody2String));
            Response responseProceed = chain.proceed(builderNewBuilder.build());
            Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(newRequestBuilder.build())");
            return responseProceed;
        }
        throw new IllegalStateException("Request body is null".toString());
    }

    /* JADX INFO: compiled from: RequestSignInterceptor.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/gid/network/interceptor/RequestSignInterceptor$Config;", "", "signStrategy", "Lcom/taptap/sdk/base/network/sign/SignStrategy;", "(Lcom/taptap/sdk/base/network/sign/SignStrategy;)V", "getSignStrategy", "()Lcom/taptap/sdk/base/network/sign/SignStrategy;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Config {
        private final SignStrategy signStrategy;

        public static /* synthetic */ Config copy$default(Config config, SignStrategy signStrategy, int i, Object obj) {
            if ((i & 1) != 0) {
                signStrategy = config.signStrategy;
            }
            return config.copy(signStrategy);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SignStrategy getSignStrategy() {
            return this.signStrategy;
        }

        public final Config copy(SignStrategy signStrategy) {
            Intrinsics.checkNotNullParameter(signStrategy, "signStrategy");
            return new Config(signStrategy);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Config) && Intrinsics.areEqual(this.signStrategy, ((Config) other).signStrategy);
        }

        public int hashCode() {
            return this.signStrategy.hashCode();
        }

        public String toString() {
            return "Config(signStrategy=" + this.signStrategy + ')';
        }

        public Config(SignStrategy signStrategy) {
            Intrinsics.checkNotNullParameter(signStrategy, "signStrategy");
            this.signStrategy = signStrategy;
        }

        public final SignStrategy getSignStrategy() {
            return this.signStrategy;
        }
    }
}
