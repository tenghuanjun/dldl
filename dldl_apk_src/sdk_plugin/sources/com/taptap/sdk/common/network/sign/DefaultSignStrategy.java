package com.taptap.sdk.common.network.sign;

import com.taptap.sdk.base.network.sign.SignStrategy;
import com.taptap.sdk.common.network.utils.SecretUtil;
import com.taptap.sdk.okhttp3.Headers;
import com.taptap.sdk.okhttp3.HttpUrl;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: DefaultSignStrategy.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0016J$\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0018\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0012*\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/taptap/sdk/common/network/sign/DefaultSignStrategy;", "Lcom/taptap/sdk/base/network/sign/SignStrategy;", "config", "Lcom/taptap/sdk/common/network/sign/DefaultSignStrategy$Config;", "(Lcom/taptap/sdk/common/network/sign/DefaultSignStrategy$Config;)V", "calculate", "", "url", "Lcom/taptap/sdk/okhttp3/HttpUrl;", "method", "headers", "Lcom/taptap/sdk/okhttp3/Headers;", "content", "filterHeaders", "predicate", "Lkotlin/Function1;", "", "toMap", "", "Config", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultSignStrategy implements SignStrategy {
    private final Config config;

    public DefaultSignStrategy(Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    @Override // com.taptap.sdk.base.network.sign.SignStrategy
    public String calculate(HttpUrl url, String method, Headers headers, String content) {
        String str;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(content, "content");
        URL url2 = url.url();
        StringBuilder sb = new StringBuilder();
        sb.append(url2.getPath());
        if (url2.getQuery() != null) {
            str = '?' + url2.getQuery();
        } else {
            str = "";
        }
        sb.append(str);
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{method, sb.toString(), filterHeaders(headers, new Function1<String, Boolean>() { // from class: com.taptap.sdk.common.network.sign.DefaultSignStrategy$calculate$headers$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String key) {
                Intrinsics.checkNotNullParameter(key, "key");
                String lowerCase = key.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                return Boolean.valueOf(StringsKt.startsWith$default(lowerCase, "x-tap-", false, 2, (Object) null));
            }
        }), content});
        SecretUtil secretUtil = SecretUtil.INSTANCE;
        String clientToken = this.config.getClientToken();
        StringBuilder sb2 = new StringBuilder();
        List list = listListOf;
        String strLineSeparator = System.lineSeparator();
        Intrinsics.checkNotNullExpressionValue(strLineSeparator, "lineSeparator()");
        sb2.append(CollectionsKt.joinToString$default(list, strLineSeparator, null, null, 0, null, null, 62, null));
        sb2.append(System.lineSeparator());
        return secretUtil.secret(clientToken, sb2.toString());
    }

    private final String filterHeaders(Headers headers, Function1<? super String, Boolean> predicate) {
        Map<String, String> map = toMap(headers);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (predicate.invoke(entry.getKey()).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        List listSortedWith = CollectionsKt.sortedWith(linkedHashMap.entrySet(), new Comparator() { // from class: com.taptap.sdk.common.network.sign.DefaultSignStrategy$filterHeaders$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = ((String) ((Map.Entry) t).getKey()).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                String lowerCase2 = ((String) ((Map.Entry) t2).getKey()).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        });
        String strLineSeparator = System.lineSeparator();
        Intrinsics.checkNotNullExpressionValue(strLineSeparator, "lineSeparator()");
        return CollectionsKt.joinToString$default(listSortedWith, strLineSeparator, null, null, 0, null, new Function1<Map.Entry<? extends String, ? extends String>, CharSequence>() { // from class: com.taptap.sdk.common.network.sign.DefaultSignStrategy.filterHeaders.3
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final CharSequence invoke2(Map.Entry<String, String> entry2) {
                Intrinsics.checkNotNullParameter(entry2, "<name for destructuring parameter 0>");
                String key = entry2.getKey();
                String value = entry2.getValue();
                StringBuilder sb = new StringBuilder();
                String lowerCase = key.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                sb.append(lowerCase);
                sb.append(AbstractJsonLexerKt.COLON);
                sb.append(value);
                return sb.toString();
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<? extends String, ? extends String> entry2) {
                return invoke2((Map.Entry<String, String>) entry2);
            }
        }, 30, null);
    }

    private final Map<String, String> toMap(Headers headers) {
        Map<String, List<String>> multimap = headers.toMultimap();
        Intrinsics.checkNotNullExpressionValue(multimap, "toMultimap()");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(multimap.size()));
        Iterator<T> it = multimap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "entry.value");
            linkedHashMap.put(key, (String) CollectionsKt.first((List) value));
        }
        return MapsKt.toMap(linkedHashMap);
    }

    /* JADX INFO: compiled from: DefaultSignStrategy.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/taptap/sdk/common/network/sign/DefaultSignStrategy$Config;", "", "clientId", "", "clientToken", "(Ljava/lang/String;Ljava/lang/String;)V", "getClientId", "()Ljava/lang/String;", "getClientToken", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Config {
        private final String clientId;
        private final String clientToken;

        public static /* synthetic */ Config copy$default(Config config, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = config.clientId;
            }
            if ((i & 2) != 0) {
                str2 = config.clientToken;
            }
            return config.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getClientId() {
            return this.clientId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getClientToken() {
            return this.clientToken;
        }

        public final Config copy(String clientId, String clientToken) {
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Intrinsics.checkNotNullParameter(clientToken, "clientToken");
            return new Config(clientId, clientToken);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return Intrinsics.areEqual(this.clientId, config.clientId) && Intrinsics.areEqual(this.clientToken, config.clientToken);
        }

        public int hashCode() {
            return (this.clientId.hashCode() * 31) + this.clientToken.hashCode();
        }

        public String toString() {
            return "Config(clientId=" + this.clientId + ", clientToken=" + this.clientToken + ')';
        }

        public Config(String clientId, String clientToken) {
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Intrinsics.checkNotNullParameter(clientToken, "clientToken");
            this.clientId = clientId;
            this.clientToken = clientToken;
        }

        public final String getClientId() {
            return this.clientId;
        }

        public final String getClientToken() {
            return this.clientToken;
        }
    }
}
