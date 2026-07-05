package com.taptap.sdk.common.network.interceptor;

import com.huya.statistics.core.StatisticsContent;
import com.sqwan.bugless.core.Constant;
import com.taptap.sdk.base.network.annotation.Module;
import com.taptap.sdk.common.network.utils.FixedTimeUtil;
import com.taptap.sdk.okhttp3.Headers;
import com.taptap.sdk.okhttp3.HttpUrl;
import com.taptap.sdk.okhttp3.Interceptor;
import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.Response;
import com.taptap.sdk.retrofit2.Invocation;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DefaultCommonArgsInterceptor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0002J \u0010\u0015\u001a\u00020\u0016*\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/taptap/sdk/common/network/interceptor/DefaultCommonArgsInterceptor;", "Lcom/taptap/sdk/okhttp3/Interceptor;", "config", "Lcom/taptap/sdk/common/network/interceptor/DefaultCommonArgsInterceptor$Config;", "(Lcom/taptap/sdk/common/network/interceptor/DefaultCommonArgsInterceptor$Config;)V", "getCommonHeaders", "Lcom/taptap/sdk/okhttp3/Headers;", "invocation", "Lcom/taptap/sdk/retrofit2/Invocation;", "getModuleFromInvocation", "Lcom/taptap/sdk/base/network/annotation/Module;", "intercept", "Lcom/taptap/sdk/okhttp3/Response;", "chain", "Lcom/taptap/sdk/okhttp3/Interceptor$Chain;", "shouldAddAuthorization", "", "url", "", "paths", "", StatisticsContent.FROM, "Lcom/taptap/sdk/okhttp3/HttpUrl;", "queryMap", "", "Config", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultCommonArgsInterceptor implements Interceptor {
    private final Config config;

    public DefaultCommonArgsInterceptor(Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    @Override // com.taptap.sdk.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Request.Builder builderNewBuilder = request.newBuilder();
        HttpUrl httpUrlUrl = request.url();
        Intrinsics.checkNotNullExpressionValue(httpUrlUrl, "originRequest.url()");
        builderNewBuilder.url(from(httpUrlUrl, MapsKt.hashMapOf(TuplesKt.to("client_id", this.config.getClientId()))));
        builderNewBuilder.headers(getCommonHeaders((Invocation) request.tag(Invocation.class)));
        String strEncodedPath = request.url().encodedPath();
        Intrinsics.checkNotNullExpressionValue(strEncodedPath, "originRequest.url().encodedPath()");
        if (shouldAddAuthorization(strEncodedPath, this.config.getPaths())) {
            String authorization = this.config.getAuthorization();
            if (authorization != null) {
                builderNewBuilder.header("Authorization", authorization);
            }
            Response responseProceed = chain.proceed(builderNewBuilder.build());
            Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(newRequestBuilder.build())");
            return responseProceed;
        }
        Response responseProceed2 = chain.proceed(builderNewBuilder.build());
        Intrinsics.checkNotNullExpressionValue(responseProceed2, "chain.proceed(newRequestBuilder.build())");
        return responseProceed2;
    }

    private final HttpUrl from(HttpUrl httpUrl, Map<String, String> map) {
        List<Pair> list = MapsKt.toList(map);
        HttpUrl.Builder builderNewBuilder = httpUrl.newBuilder();
        for (Pair pair : list) {
            builderNewBuilder.addQueryParameter((String) pair.component1(), (String) pair.component2());
        }
        HttpUrl httpUrlBuild = builderNewBuilder.build();
        Intrinsics.checkNotNullExpressionValue(httpUrlBuild, "queryMap.toList()\n      …   }\n            .build()");
        return httpUrlBuild;
    }

    private final Headers getCommonHeaders(Invocation invocation) {
        HashMap map = new HashMap();
        Pair pair = TuplesKt.to("X-Tap-PN", "TapSDK");
        map.put(pair.getFirst(), pair.getSecond());
        Pair pair2 = TuplesKt.to("X-Tap-Device-Id", this.config.getDeviceId());
        map.put(pair2.getFirst(), pair2.getSecond());
        Pair pair3 = TuplesKt.to("X-Tap-Platform", "Android");
        map.put(pair3.getFirst(), pair3.getSecond());
        Pair pair4 = TuplesKt.to("X-Tap-SDK-Artifact", this.config.getSdkArtifact());
        map.put(pair4.getFirst(), pair4.getSecond());
        Pair pair5 = TuplesKt.to("X-Tap-Ts", String.valueOf(FixedTimeUtil.INSTANCE.getCurrentTimeInMillis() / ((long) 1000)));
        map.put(pair5.getFirst(), pair5.getSecond());
        Pair pair6 = TuplesKt.to("X-Tap-Nonce", this.config.getNonce());
        map.put(pair6.getFirst(), pair6.getSecond());
        Pair pair7 = TuplesKt.to("X-Tap-Lang", this.config.getLanguage());
        map.put(pair7.getFirst(), pair7.getSecond());
        Pair pair8 = TuplesKt.to("User-Agent", this.config.getUserAgent());
        map.put(pair8.getFirst(), pair8.getSecond());
        Module moduleFromInvocation = getModuleFromInvocation(invocation);
        if (moduleFromInvocation != null) {
            Pair pair9 = TuplesKt.to("X-Tap-SDK-Module", moduleFromInvocation.name());
            map.put(pair9.getFirst(), pair9.getSecond());
            Pair pair10 = TuplesKt.to("X-Tap-SDK-Module-Version", moduleFromInvocation.version());
            map.put(pair10.getFirst(), pair10.getSecond());
        }
        Headers headersOf = Headers.of(map);
        Intrinsics.checkNotNullExpressionValue(headersOf, "of(params)");
        return headersOf;
    }

    private final boolean shouldAddAuthorization(String url, Set<String> paths) {
        Set<String> set = paths;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (StringsKt.contains$default((CharSequence) url, (CharSequence) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final Module getModuleFromInvocation(Invocation invocation) {
        Method method;
        Class<?> declaringClass;
        if (invocation == null || (method = invocation.method()) == null || (declaringClass = method.getDeclaringClass()) == null) {
            return null;
        }
        return (Module) declaringClass.getAnnotation(Module.class);
    }

    /* JADX INFO: compiled from: DefaultCommonArgsInterceptor.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\fJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003Ja\u0010\u001f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006&"}, d2 = {"Lcom/taptap/sdk/common/network/interceptor/DefaultCommonArgsInterceptor$Config;", "", "paths", "", "", "clientId", "language", "userAgent", Constant.DEVICE_ID, "sdkArtifact", "nonce", "authorization", "(Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthorization", "()Ljava/lang/String;", "getClientId", "getDeviceId", "getLanguage", "getNonce", "getPaths", "()Ljava/util/Set;", "getSdkArtifact", "getUserAgent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Config {
        private final String authorization;
        private final String clientId;
        private final String deviceId;
        private final String language;
        private final String nonce;
        private final Set<String> paths;
        private final String sdkArtifact;
        private final String userAgent;

        public final Set<String> component1() {
            return this.paths;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getClientId() {
            return this.clientId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUserAgent() {
            return this.userAgent;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDeviceId() {
            return this.deviceId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSdkArtifact() {
            return this.sdkArtifact;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getNonce() {
            return this.nonce;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getAuthorization() {
            return this.authorization;
        }

        public final Config copy(Set<String> paths, String clientId, String language, String userAgent, String deviceId, String sdkArtifact, String nonce, String authorization) {
            Intrinsics.checkNotNullParameter(paths, "paths");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(userAgent, "userAgent");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(sdkArtifact, "sdkArtifact");
            Intrinsics.checkNotNullParameter(nonce, "nonce");
            return new Config(paths, clientId, language, userAgent, deviceId, sdkArtifact, nonce, authorization);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return Intrinsics.areEqual(this.paths, config.paths) && Intrinsics.areEqual(this.clientId, config.clientId) && Intrinsics.areEqual(this.language, config.language) && Intrinsics.areEqual(this.userAgent, config.userAgent) && Intrinsics.areEqual(this.deviceId, config.deviceId) && Intrinsics.areEqual(this.sdkArtifact, config.sdkArtifact) && Intrinsics.areEqual(this.nonce, config.nonce) && Intrinsics.areEqual(this.authorization, config.authorization);
        }

        public int hashCode() {
            int iHashCode = ((((((((((((this.paths.hashCode() * 31) + this.clientId.hashCode()) * 31) + this.language.hashCode()) * 31) + this.userAgent.hashCode()) * 31) + this.deviceId.hashCode()) * 31) + this.sdkArtifact.hashCode()) * 31) + this.nonce.hashCode()) * 31;
            String str = this.authorization;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "Config(paths=" + this.paths + ", clientId=" + this.clientId + ", language=" + this.language + ", userAgent=" + this.userAgent + ", deviceId=" + this.deviceId + ", sdkArtifact=" + this.sdkArtifact + ", nonce=" + this.nonce + ", authorization=" + this.authorization + ')';
        }

        public Config(Set<String> paths, String clientId, String language, String userAgent, String deviceId, String sdkArtifact, String nonce, String str) {
            Intrinsics.checkNotNullParameter(paths, "paths");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(userAgent, "userAgent");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(sdkArtifact, "sdkArtifact");
            Intrinsics.checkNotNullParameter(nonce, "nonce");
            this.paths = paths;
            this.clientId = clientId;
            this.language = language;
            this.userAgent = userAgent;
            this.deviceId = deviceId;
            this.sdkArtifact = sdkArtifact;
            this.nonce = nonce;
            this.authorization = str;
        }

        public /* synthetic */ Config(Set set, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(set, str, str2, str3, str4, str5, str6, (i & 128) != 0 ? null : str7);
        }

        public final Set<String> getPaths() {
            return this.paths;
        }

        public final String getClientId() {
            return this.clientId;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final String getUserAgent() {
            return this.userAgent;
        }

        public final String getDeviceId() {
            return this.deviceId;
        }

        public final String getSdkArtifact() {
            return this.sdkArtifact;
        }

        public final String getNonce() {
            return this.nonce;
        }

        public final String getAuthorization() {
            return this.authorization;
        }
    }
}
