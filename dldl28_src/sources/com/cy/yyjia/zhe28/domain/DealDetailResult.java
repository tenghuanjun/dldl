package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealDetailResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealDetailResult;", "", "detail", "Lcom/cy/yyjia/zhe28/domain/DealBean;", "tradeInfo", "Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;", "(Lcom/cy/yyjia/zhe28/domain/DealBean;Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;)V", "getDetail", "()Lcom/cy/yyjia/zhe28/domain/DealBean;", "getTradeInfo", "()Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;", "TradeInfo", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealDetailResult {
    public static final int $stable = 8;
    private final DealBean detail;
    private final TradeInfo tradeInfo;

    public DealDetailResult(DealBean detail, TradeInfo tradeInfo) {
        Intrinsics.checkNotNullParameter(detail, "detail");
        Intrinsics.checkNotNullParameter(tradeInfo, "tradeInfo");
        this.detail = detail;
        this.tradeInfo = tradeInfo;
    }

    public final DealBean getDetail() {
        return this.detail;
    }

    public final TradeInfo getTradeInfo() {
        return this.tradeInfo;
    }

    /* JADX INFO: compiled from: DealDetailResult.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;", "", "title", "", "context", SocialConstants.PARAM_APP_DESC, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContext", "()Ljava/lang/String;", "getDesc", "getTitle", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class TradeInfo {
        public static final int $stable = 0;
        private final String context;
        private final String desc;
        private final String title;

        public TradeInfo(String title, String context, String desc) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.title = title;
            this.context = context;
            this.desc = desc;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getContext() {
            return this.context;
        }

        public final String getDesc() {
            return this.desc;
        }
    }
}
