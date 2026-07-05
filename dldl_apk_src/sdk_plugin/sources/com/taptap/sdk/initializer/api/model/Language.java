package com.taptap.sdk.initializer.api.model;

import com.huya.statistics.core.StatisticsContent;
import com.sy37sdk.account.db.LoginTriggerTable;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.EnumsKt;

/* JADX INFO: compiled from: Language.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0087\u0001\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0017"}, d2 = {"Lcom/taptap/sdk/initializer/api/model/Language;", "", "language", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLanguage", "()Ljava/lang/String;", "AUTO", "ZH_HANS", "EN", "ZH_HANT", "JA", "KO", "TH", LoginTriggerTable.ID, "DE", "ES", "FR", "PT", "RU", "TR", "VI", "Companion", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public enum Language {
    AUTO(DebugKt.DEBUG_PROPERTY_VALUE_AUTO),
    ZH_HANS("zh_CN"),
    EN("en_US"),
    ZH_HANT("zh_TW"),
    JA("ja_JP"),
    KO("ko_KR"),
    TH("th_TH"),
    ID("id_ID"),
    DE("de"),
    ES("es_ES"),
    FR("fr"),
    PT("pt_PT"),
    RU("ru"),
    TR("tr"),
    VI("vi_VN");

    private final String language;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.taptap.sdk.initializer.api.model.Language$Companion$$cachedSerializer$delegate$1
        @Override // kotlin.jvm.functions.Function0
        public final KSerializer<Object> invoke() {
            return EnumsKt.createSimpleEnumSerializer("com.taptap.sdk.initializer.api.model.Language", Language.values());
        }
    });

    Language(String str) {
        this.language = str;
    }

    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: compiled from: Language.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nHÆ\u0001¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/initializer/api/model/Language$Companion;", "", "()V", StatisticsContent.FROM, "Lcom/taptap/sdk/initializer/api/model/Language;", "index", "", "value", "", "serializer", "Lkotlinx/serialization/KSerializer;", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final /* synthetic */ Lazy get$cachedSerializer$delegate() {
            return Language.$cachedSerializer$delegate;
        }

        public final KSerializer<Language> serializer() {
            return (KSerializer) get$cachedSerializer$delegate().getValue();
        }

        public final Language from(int index) {
            Language[] languageArrValues = Language.values();
            return (index < 0 || index > ArraysKt.getLastIndex(languageArrValues)) ? Language.AUTO : languageArrValues[index];
        }

        public final Language from(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            Language[] languageArrValues = Language.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(languageArrValues.length), 16));
            for (Language language : languageArrValues) {
                linkedHashMap.put(language.getLanguage(), language);
            }
            Object obj = linkedHashMap.get(value);
            if (obj == null) {
                obj = Language.AUTO;
            }
            return (Language) obj;
        }
    }
}
