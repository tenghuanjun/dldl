package com.taptap.sdk.kit.internal.utils.localize;

import com.sy37sdk.account.db.LoginTriggerTable;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: TapLanguageInternal.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/localize/TapLanguageInternal;", "", "language", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLanguage", "()Ljava/lang/String;", "AUTO", "ZH_HANS", "EN", "ZH_HANT", "JA", "KO", "TH", LoginTriggerTable.ID, "DE", "ES", "FR", "PT", "RU", "TR", "VI", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum TapLanguageInternal {
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

    TapLanguageInternal(String str) {
        this.language = str;
    }

    public final String getLanguage() {
        return this.language;
    }
}
