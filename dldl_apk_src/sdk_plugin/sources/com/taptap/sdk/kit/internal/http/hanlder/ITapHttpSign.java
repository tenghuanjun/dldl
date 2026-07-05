package com.taptap.sdk.kit.internal.http.hanlder;

import com.sqwan.common.route.FunctionRouter;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapHttpSign.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\rJ\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H&J,\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpSign;", "", "getFixQueryParams", "", "", "getHeaders", "moduleName", "moduleVersion", "method", "handle", "", FunctionRouter.KEY_DATA, "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpSign$HandleData;", "HandleData", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ITapHttpSign {
    Map<String, String> getFixQueryParams();

    Map<String, String> getHeaders(String moduleName, String moduleVersion, String method);

    void handle(HandleData data);

    /* JADX INFO: compiled from: TapHttpSign.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003JQ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015¨\u0006#"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpSign$HandleData;", "", "url", "", "method", "enableAuthorization", "", "content", "", "compressContent", "headers", "", "(Ljava/lang/String;Ljava/lang/String;Z[B[BLjava/util/Map;)V", "getCompressContent", "()[B", "getContent", "getEnableAuthorization", "()Z", "getHeaders", "()Ljava/util/Map;", "getMethod", "()Ljava/lang/String;", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class HandleData {
        private final byte[] compressContent;
        private final byte[] content;
        private final boolean enableAuthorization;
        private final Map<String, String> headers;
        private final String method;
        private final String url;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HandleData copy$default(HandleData handleData, String str, String str2, boolean z, byte[] bArr, byte[] bArr2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = handleData.url;
            }
            if ((i & 2) != 0) {
                str2 = handleData.method;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                z = handleData.enableAuthorization;
            }
            boolean z2 = z;
            if ((i & 8) != 0) {
                bArr = handleData.content;
            }
            byte[] bArr3 = bArr;
            if ((i & 16) != 0) {
                bArr2 = handleData.compressContent;
            }
            byte[] bArr4 = bArr2;
            if ((i & 32) != 0) {
                map = handleData.headers;
            }
            return handleData.copy(str, str3, z2, bArr3, bArr4, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMethod() {
            return this.method;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getEnableAuthorization() {
            return this.enableAuthorization;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final byte[] getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final byte[] getCompressContent() {
            return this.compressContent;
        }

        public final Map<String, String> component6() {
            return this.headers;
        }

        public final HandleData copy(String url, String method, boolean enableAuthorization, byte[] content, byte[] compressContent, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(compressContent, "compressContent");
            Intrinsics.checkNotNullParameter(headers, "headers");
            return new HandleData(url, method, enableAuthorization, content, compressContent, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HandleData)) {
                return false;
            }
            HandleData handleData = (HandleData) other;
            return Intrinsics.areEqual(this.url, handleData.url) && Intrinsics.areEqual(this.method, handleData.method) && this.enableAuthorization == handleData.enableAuthorization && Intrinsics.areEqual(this.content, handleData.content) && Intrinsics.areEqual(this.compressContent, handleData.compressContent) && Intrinsics.areEqual(this.headers, handleData.headers);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v3, types: [int] */
        public int hashCode() {
            int iHashCode = ((this.url.hashCode() * 31) + this.method.hashCode()) * 31;
            boolean z = this.enableAuthorization;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return ((((((iHashCode + r1) * 31) + Arrays.hashCode(this.content)) * 31) + Arrays.hashCode(this.compressContent)) * 31) + this.headers.hashCode();
        }

        public String toString() {
            return "HandleData(url=" + this.url + ", method=" + this.method + ", enableAuthorization=" + this.enableAuthorization + ", content=" + Arrays.toString(this.content) + ", compressContent=" + Arrays.toString(this.compressContent) + ", headers=" + this.headers + ')';
        }

        public HandleData(String url, String method, boolean z, byte[] content, byte[] compressContent, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(compressContent, "compressContent");
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.url = url;
            this.method = method;
            this.enableAuthorization = z;
            this.content = content;
            this.compressContent = compressContent;
            this.headers = headers;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getMethod() {
            return this.method;
        }

        public final boolean getEnableAuthorization() {
            return this.enableAuthorization;
        }

        public final byte[] getContent() {
            return this.content;
        }

        public final byte[] getCompressContent() {
            return this.compressContent;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }
    }
}
