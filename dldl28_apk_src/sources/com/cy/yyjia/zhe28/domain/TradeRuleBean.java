package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TradeRuleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TradeRuleBean;", "", "open", "", "dolo", "regress", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDolo", "()Ljava/lang/String;", "getOpen", "getRegress", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TradeRuleBean {
    public static final int $stable = 0;
    private final String dolo;
    private final String open;
    private final String regress;

    public TradeRuleBean(String open, String dolo, String regress) {
        Intrinsics.checkNotNullParameter(open, "open");
        Intrinsics.checkNotNullParameter(dolo, "dolo");
        Intrinsics.checkNotNullParameter(regress, "regress");
        this.open = open;
        this.dolo = dolo;
        this.regress = regress;
    }

    public final String getOpen() {
        return this.open;
    }

    public final String getDolo() {
        return this.dolo;
    }

    public final String getRegress() {
        return this.regress;
    }
}
