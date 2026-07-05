package com.taptap.sdk.kit.internal.http.param;

import com.taptap.sdk.kit.internal.http.TapHttp;
import com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff;
import com.taptap.sdk.kit.internal.http.hanlder.ITapHttpSign;
import com.taptap.sdk.kit.internal.http.hanlder.TapHttpBackoff;
import com.taptap.sdk.okhttp3.Request;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: TapHttpNoBodyParam.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/param/TapHttpNoBodyParam;", "Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", IDataSource.SCHEME_HTTP_TAG, "Lcom/taptap/sdk/kit/internal/http/TapHttp;", "url", "", "(Lcom/taptap/sdk/kit/internal/http/TapHttp;Ljava/lang/String;)V", "backoffHandler", "Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "getBackoffHandler", "()Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "setBackoffHandler", "(Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;)V", "buildRequest", "Lcom/taptap/sdk/okhttp3/Request;", "buildRequest$tap_common_release", "getContentByteArray", "", "getContentByteArray$tap_common_release", "getTechnicalLogParam", "getTechnicalLogParam$tap_common_release", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpNoBodyParam extends AbsTapHttpParam<TapHttpNoBodyParam> {
    private AbsTapHttpBackoff backoffHandler;

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public byte[] getContentByteArray$tap_common_release() {
        return new byte[0];
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TapHttpNoBodyParam(TapHttp http, String url) {
        super(http, url, "GET");
        Intrinsics.checkNotNullParameter(http, "http");
        Intrinsics.checkNotNullParameter(url, "url");
        this.backoffHandler = new TapHttpBackoff.Fixed(0, 1, null);
    }

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public AbsTapHttpBackoff getBackoffHandler() {
        return this.backoffHandler;
    }

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public void setBackoffHandler(AbsTapHttpBackoff absTapHttpBackoff) {
        Intrinsics.checkNotNullParameter(absTapHttpBackoff, "<set-?>");
        this.backoffHandler = absTapHttpBackoff;
    }

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public Request buildRequest$tap_common_release() {
        Map mutableMap = MapsKt.toMutableMap(getHeaderParams());
        mutableMap.putAll(getTapHttp().getSignHandler().getHeaders(getTapHttp().getModuleName(), getTapHttp().getModuleVersion(), getMethod()));
        String strBuildUrlString = buildUrlString();
        getTapHttp().getSignHandler().handle(new ITapHttpSign.HandleData(strBuildUrlString, getMethod(), getTapHttp().getEnableAuthorization(), new byte[0], new byte[0], mutableMap));
        Request.Builder builderUrl = new Request.Builder().url(strBuildUrlString);
        for (Map.Entry entry : mutableMap.entrySet()) {
            builderUrl.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        Request requestBuild = builderUrl.get().build();
        Intrinsics.checkNotNullExpressionValue(requestBuild, "Builder()\n            .u…et()\n            .build()");
        return requestBuild;
    }

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public String getTechnicalLogParam$tap_common_release() {
        return "getUrl=" + getUrl();
    }
}
