package com.taptap.sdk.kit.internal.http.param;

import com.taptap.sdk.kit.internal.http.TapHttp;
import com.taptap.sdk.okhttp3.MediaType;
import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.RequestBody;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: TapHttpFile.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\tH\u0010¢\u0006\u0002\b\nJ\r\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\u000fR\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/param/TapHttpFile;", "Lcom/taptap/sdk/kit/internal/http/param/AbsTapHttpParam;", IDataSource.SCHEME_HTTP_TAG, "Lcom/taptap/sdk/kit/internal/http/TapHttp;", "url", "", "localPath", "(Lcom/taptap/sdk/kit/internal/http/TapHttp;Ljava/lang/String;Ljava/lang/String;)V", "buildRequest", "Lcom/taptap/sdk/okhttp3/Request;", "buildRequest$tap_common_release", "getContentByteArray", "", "getContentByteArray$tap_common_release", "getTechnicalLogParam", "getTechnicalLogParam$tap_common_release", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpFile extends AbsTapHttpParam<TapHttpFile> {
    private final String localPath;

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public byte[] getContentByteArray$tap_common_release() {
        return new byte[0];
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TapHttpFile(TapHttp http, String url, String localPath) {
        super(http, url, "PUT");
        Intrinsics.checkNotNullParameter(http, "http");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(localPath, "localPath");
        this.localPath = localPath;
    }

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public Request buildRequest$tap_common_release() {
        Map mutableMap = MapsKt.toMutableMap(getHeaderParams());
        MediaType mediaType = MediaType.parse("application/octet-stream");
        Request.Builder builderUrl = new Request.Builder().url(getUrl());
        for (Map.Entry entry : mutableMap.entrySet()) {
            builderUrl.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        Request requestBuild = builderUrl.put(RequestBody.create(mediaType, new File(this.localPath))).build();
        Intrinsics.checkNotNullExpressionValue(requestBuild, "Builder()\n            .u…h)))\n            .build()");
        return requestBuild;
    }

    @Override // com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam
    public String getTechnicalLogParam$tap_common_release() {
        return "url=" + getUrl() + ", localPath=" + this.localPath;
    }
}
