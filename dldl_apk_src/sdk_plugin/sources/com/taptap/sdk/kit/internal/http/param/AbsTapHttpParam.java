package com.taptap.sdk.kit.internal.http.param;

import android.net.Uri;
import com.sqwan.common.constants.SqConstants;
import com.taptap.sdk.kit.internal.http.TapHttp;
import com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff;
import com.taptap.sdk.kit.internal.http.hanlder.TapHttpBackoff;
import com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam;
import com.taptap.sdk.okhttp3.Call;
import com.taptap.sdk.okhttp3.Request;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: AbsTapHttpParam.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ%\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\r\u0010 \u001a\u00020!H ¢\u0006\u0002\b\"J\b\u0010#\u001a\u00020\u0006H\u0004J\r\u0010$\u001a\u00020\u001dH\u0000¢\u0006\u0002\b%J\r\u0010&\u001a\u00020'H ¢\u0006\u0002\b(J\r\u0010)\u001a\u00020\u0006H ¢\u0006\u0002\b*J\r\u0010+\u001a\u00020\u0006H\u0000¢\u0006\u0002\b,J\r\u0010-\u001a\u00020\u0006H\u0000¢\u0006\u0002\b.J\r\u0010/\u001a\u000200H\u0000¢\u0006\u0002\b1J\u0014\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00103\u001a\u00020\nJ\r\u00104\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00105R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0010X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014¨\u00066"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", "R", "", "tapHttp", "Lcom/taptap/sdk/kit/internal/http/TapHttp;", "url", "", "method", "(Lcom/taptap/sdk/kit/internal/http/TapHttp;Ljava/lang/String;Ljava/lang/String;)V", "backoffHandler", "Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "getBackoffHandler", "()Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "setBackoffHandler", "(Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;)V", "headerParams", "", "getHeaderParams", "()Ljava/util/Map;", "getMethod", "()Ljava/lang/String;", "queryParams", "getTapHttp$tap_common_release", "()Lcom/taptap/sdk/kit/internal/http/TapHttp;", "getUrl", "addHeader", "key", "value", "add", "", "(Ljava/lang/String;Ljava/lang/String;Z)Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", "addQuery", "buildRequest", "Lcom/taptap/sdk/okhttp3/Request;", "buildRequest$tap_common_release", "buildUrlString", "enableTechnicalLog", "enableTechnicalLog$tap_common_release", "getContentByteArray", "", "getContentByteArray$tap_common_release", "getTechnicalLogParam", "getTechnicalLogParam$tap_common_release", "moduleName", "moduleName$tap_common_release", "moduleVersion", "moduleVersion$tap_common_release", "newCall", "Lcom/taptap/sdk/okhttp3/Call;", "newCall$tap_common_release", "retryBackoff", "backoff", SqConstants.OAUTH_SQ, "()Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class AbsTapHttpParam<R extends AbsTapHttpParam<R>> {
    private AbsTapHttpBackoff backoffHandler;
    private final Map<String, String> headerParams;
    private final String method;
    private final Map<String, String> queryParams;
    private final TapHttp tapHttp;
    private final String url;

    public abstract Request buildRequest$tap_common_release();

    public abstract byte[] getContentByteArray$tap_common_release();

    public abstract String getTechnicalLogParam$tap_common_release();

    public AbsTapHttpParam(TapHttp tapHttp, String url, String method) {
        Intrinsics.checkNotNullParameter(tapHttp, "tapHttp");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        this.tapHttp = tapHttp;
        this.url = url;
        this.method = method;
        this.headerParams = new LinkedHashMap();
        this.queryParams = new LinkedHashMap();
        this.backoffHandler = new TapHttpBackoff.None();
    }

    /* JADX INFO: renamed from: getTapHttp$tap_common_release, reason: from getter */
    public final TapHttp getTapHttp() {
        return this.tapHttp;
    }

    public final String getMethod() {
        return this.method;
    }

    protected final String getUrl() {
        return this.url;
    }

    protected final Map<String, String> getHeaderParams() {
        return this.headerParams;
    }

    public AbsTapHttpBackoff getBackoffHandler() {
        return this.backoffHandler;
    }

    public void setBackoffHandler(AbsTapHttpBackoff absTapHttpBackoff) {
        Intrinsics.checkNotNullParameter(absTapHttpBackoff, "<set-?>");
        this.backoffHandler = absTapHttpBackoff;
    }

    public static /* synthetic */ AbsTapHttpParam addHeader$default(AbsTapHttpParam absTapHttpParam, String str, String str2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addHeader");
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return absTapHttpParam.addHeader(str, str2, z);
    }

    public final R addHeader(String key, String value, boolean add) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (add) {
            this.headerParams.put(key, value);
        }
        return (R) self();
    }

    public static /* synthetic */ AbsTapHttpParam addQuery$default(AbsTapHttpParam absTapHttpParam, String str, String str2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addQuery");
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return absTapHttpParam.addQuery(str, str2, z);
    }

    public final R addQuery(String key, String value, boolean add) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (add) {
            this.queryParams.put(key, value);
        }
        return (R) self();
    }

    public final AbsTapHttpParam<R> retryBackoff(AbsTapHttpBackoff backoff) {
        Intrinsics.checkNotNullParameter(backoff, "backoff");
        AbsTapHttpParam<R> absTapHttpParam = this;
        absTapHttpParam.setBackoffHandler(backoff);
        return absTapHttpParam;
    }

    public final Call newCall$tap_common_release() {
        return this.tapHttp.newCall(buildRequest$tap_common_release());
    }

    public final String moduleVersion$tap_common_release() {
        return this.tapHttp.getModuleVersion();
    }

    public final String moduleName$tap_common_release() {
        return this.tapHttp.getModuleName();
    }

    public final boolean enableTechnicalLog$tap_common_release() {
        return this.tapHttp.getEnableTechnicalLog();
    }

    private final R self() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type R of com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam");
        return this;
    }

    protected final String buildUrlString() {
        Uri.Builder builderPath;
        Map<String, String> fixQueryParams = this.tapHttp.getSignHandler().getFixQueryParams();
        if (StringsKt.startsWith$default(this.url, IDataSource.SCHEME_HTTP_TAG, false, 2, (Object) null)) {
            builderPath = Uri.parse(this.url).buildUpon();
            Intrinsics.checkNotNullExpressionValue(builderPath, "{\n            Uri.parse(url).buildUpon()\n        }");
        } else {
            builderPath = new Uri.Builder().scheme(IDataSource.SCHEME_HTTPS_TAG).authority(this.tapHttp.getDomain$tap_common_release()).path(this.url);
            Intrinsics.checkNotNullExpressionValue(builderPath, "{\n            Uri.Builde…in()).path(url)\n        }");
        }
        Map mutableMap = MapsKt.toMutableMap(this.queryParams);
        mutableMap.putAll(fixQueryParams);
        for (Map.Entry entry : mutableMap.entrySet()) {
            builderPath.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        String string = builderPath.build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.build().toString()");
        return string;
    }
}
