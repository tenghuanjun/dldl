package com.cy.yyjia.zhe28.domain;

import android.view.View;
import com.cy.yyjia.zhe28.util.Util;
import com.volcengine.common.contant.CommonConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoviceGameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NoviceGameBean;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "yhq", "", "Lcom/cy/yyjia/zhe28/domain/NoviceGameBean$CouponBean;", "gift", "Lcom/cy/yyjia/zhe28/domain/NoviceGameBean$GiftBean;", "(Ljava/util/List;Ljava/util/List;)V", "getGift", "()Ljava/util/List;", "getYhq", "component1", "component2", "copy", "equals", "", "other", "", "gotoGame", "", "v", "Landroid/view/View;", "hashCode", "", "toString", "", "CouponBean", "GiftBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class NoviceGameBean extends GameBean {
    public static final int $stable = 8;
    private final List<GiftBean> gift;
    private final List<CouponBean> yhq;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NoviceGameBean copy$default(NoviceGameBean noviceGameBean, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = noviceGameBean.yhq;
        }
        if ((i & 2) != 0) {
            list2 = noviceGameBean.gift;
        }
        return noviceGameBean.copy(list, list2);
    }

    public final List<CouponBean> component1() {
        return this.yhq;
    }

    public final List<GiftBean> component2() {
        return this.gift;
    }

    public final NoviceGameBean copy(List<CouponBean> yhq, List<GiftBean> gift) {
        Intrinsics.checkNotNullParameter(yhq, "yhq");
        Intrinsics.checkNotNullParameter(gift, "gift");
        return new NoviceGameBean(yhq, gift);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NoviceGameBean)) {
            return false;
        }
        NoviceGameBean noviceGameBean = (NoviceGameBean) other;
        return Intrinsics.areEqual(this.yhq, noviceGameBean.yhq) && Intrinsics.areEqual(this.gift, noviceGameBean.gift);
    }

    public int hashCode() {
        return (this.yhq.hashCode() * 31) + this.gift.hashCode();
    }

    public String toString() {
        return "NoviceGameBean(yhq=" + this.yhq + ", gift=" + this.gift + ")";
    }

    public final List<CouponBean> getYhq() {
        return this.yhq;
    }

    public final List<GiftBean> getGift() {
        return this.gift;
    }

    public NoviceGameBean(List<CouponBean> yhq, List<GiftBean> gift) {
        Intrinsics.checkNotNullParameter(yhq, "yhq");
        Intrinsics.checkNotNullParameter(gift, "gift");
        this.yhq = yhq;
        this.gift = gift;
    }

    @Override // com.cy.yyjia.zhe28.domain.GameBean
    public void gotoGame(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Util.gotoGame(v.getContext(), getId());
    }

    /* JADX INFO: compiled from: NoviceGameBean.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\b\u0007\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0014\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NoviceGameBean$CouponBean;", "", "amount", "", CommonConstants.key_gameId, "", "id", "isGet", "isUse", "isbig", "leftPercent", "num", "name", "totalNum", "type", "useCondition", "(Ljava/lang/String;IIIIIIILjava/lang/String;IILjava/lang/String;)V", "getAmount", "()Ljava/lang/String;", "getGameId", "()I", "getId", "setGet", "(I)V", "getIsbig", "getLeftPercent", "getName", "getNum", "getTotalNum", "getType", "getUseCondition", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CouponBean {
        public static final int $stable = 8;
        private final String amount;
        private final int gameId;
        private final int id;
        private int isGet;
        private final int isUse;
        private final int isbig;
        private final int leftPercent;
        private final String name;
        private final int num;
        private final int totalNum;
        private final int type;
        private final String useCondition;

        public CouponBean(String amount, int i, int i2, int i3, int i4, int i5, int i6, int i7, String name, int i8, int i9, String useCondition) {
            Intrinsics.checkNotNullParameter(amount, "amount");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(useCondition, "useCondition");
            this.amount = amount;
            this.gameId = i;
            this.id = i2;
            this.isGet = i3;
            this.isUse = i4;
            this.isbig = i5;
            this.leftPercent = i6;
            this.num = i7;
            this.name = name;
            this.totalNum = i8;
            this.type = i9;
            this.useCondition = useCondition;
        }

        public final String getAmount() {
            return this.amount;
        }

        public final int getGameId() {
            return this.gameId;
        }

        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: isGet, reason: from getter */
        public final int getIsGet() {
            return this.isGet;
        }

        public final void setGet(int i) {
            this.isGet = i;
        }

        /* JADX INFO: renamed from: isUse, reason: from getter */
        public final int getIsUse() {
            return this.isUse;
        }

        public final int getIsbig() {
            return this.isbig;
        }

        public final int getLeftPercent() {
            return this.leftPercent;
        }

        public final int getNum() {
            return this.num;
        }

        public final String getName() {
            return this.name;
        }

        public final int getTotalNum() {
            return this.totalNum;
        }

        public final int getType() {
            return this.type;
        }

        public final String getUseCondition() {
            return this.useCondition;
        }
    }

    /* JADX INFO: compiled from: NoviceGameBean.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u000f\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NoviceGameBean$GiftBean;", "", CommonConstants.key_gameId, "", "id", "isGet", "isUse", "leftPercent", "name", "", "num", "totalNum", "type", "(IIIIILjava/lang/String;IILjava/lang/String;)V", "getGameId", "()I", "getId", "setGet", "(I)V", "getLeftPercent", "getName", "()Ljava/lang/String;", "getNum", "getTotalNum", "getType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class GiftBean {
        public static final int $stable = 8;
        private final int gameId;
        private final int id;
        private int isGet;
        private final int isUse;
        private final int leftPercent;
        private final String name;
        private final int num;
        private final int totalNum;
        private final String type;

        public GiftBean(int i, int i2, int i3, int i4, int i5, String name, int i6, int i7, String type) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            this.gameId = i;
            this.id = i2;
            this.isGet = i3;
            this.isUse = i4;
            this.leftPercent = i5;
            this.name = name;
            this.num = i6;
            this.totalNum = i7;
            this.type = type;
        }

        public final int getGameId() {
            return this.gameId;
        }

        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: isGet, reason: from getter */
        public final int getIsGet() {
            return this.isGet;
        }

        public final void setGet(int i) {
            this.isGet = i;
        }

        /* JADX INFO: renamed from: isUse, reason: from getter */
        public final int getIsUse() {
            return this.isUse;
        }

        public final int getLeftPercent() {
            return this.leftPercent;
        }

        public final String getName() {
            return this.name;
        }

        public final int getNum() {
            return this.num;
        }

        public final int getTotalNum() {
            return this.totalNum;
        }

        public final String getType() {
            return this.type;
        }
    }
}
