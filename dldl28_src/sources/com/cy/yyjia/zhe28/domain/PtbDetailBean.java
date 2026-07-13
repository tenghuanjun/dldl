package com.cy.yyjia.zhe28.domain;

import com.tencent.connect.common.Constants;
import com.volcengine.common.contant.CommonConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PtbDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001:\u00016B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003Jw\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0005HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u00100\u001a\u00020\u0005J\u0006\u00101\u001a\u00020\u0005J\u0006\u00102\u001a\u00020\u0005J\u0006\u00103\u001a\u00020\u0005J\t\u00104\u001a\u00020\tHÖ\u0001J\t\u00105\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015¨\u00067"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbDetailBean;", "", "dateline", "", "discountedMoney", "", "game", "Lcom/cy/yyjia/zhe28/domain/PtbDetailBean$Game;", CommonConstants.key_gameId, "", "goodsName", "money", "orderId", "payType", "platMoney", "realMoney", "type", "(JLjava/lang/String;Lcom/cy/yyjia/zhe28/domain/PtbDetailBean$Game;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDateline", "()J", "getDiscountedMoney", "()Ljava/lang/String;", "getGame", "()Lcom/cy/yyjia/zhe28/domain/PtbDetailBean$Game;", "getGameId", "()I", "getGoodsName", "getMoney", "getOrderId", "getPayType", "getPlatMoney", "getRealMoney", "getType", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getName1", "getOrderTypeText", "getResult", "getShowTime", "hashCode", "toString", "Game", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class PtbDetailBean {
    public static final int $stable = 0;
    private final long dateline;
    private final String discountedMoney;
    private final Game game;
    private final int gameId;
    private final String goodsName;
    private final String money;
    private final String orderId;
    private final String payType;
    private final String platMoney;
    private final String realMoney;
    private final String type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getRealMoney() {
        return this.realMoney;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDiscountedMoney() {
        return this.discountedMoney;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Game getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getGoodsName() {
        return this.goodsName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOrderId() {
        return this.orderId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPayType() {
        return this.payType;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPlatMoney() {
        return this.platMoney;
    }

    public final PtbDetailBean copy(long dateline, String discountedMoney, Game game, int gameId, String goodsName, String money, String orderId, String payType, String platMoney, String realMoney, String type) {
        Intrinsics.checkNotNullParameter(discountedMoney, "discountedMoney");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(goodsName, "goodsName");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(payType, "payType");
        Intrinsics.checkNotNullParameter(platMoney, "platMoney");
        Intrinsics.checkNotNullParameter(realMoney, "realMoney");
        Intrinsics.checkNotNullParameter(type, "type");
        return new PtbDetailBean(dateline, discountedMoney, game, gameId, goodsName, money, orderId, payType, platMoney, realMoney, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PtbDetailBean)) {
            return false;
        }
        PtbDetailBean ptbDetailBean = (PtbDetailBean) other;
        return this.dateline == ptbDetailBean.dateline && Intrinsics.areEqual(this.discountedMoney, ptbDetailBean.discountedMoney) && Intrinsics.areEqual(this.game, ptbDetailBean.game) && this.gameId == ptbDetailBean.gameId && Intrinsics.areEqual(this.goodsName, ptbDetailBean.goodsName) && Intrinsics.areEqual(this.money, ptbDetailBean.money) && Intrinsics.areEqual(this.orderId, ptbDetailBean.orderId) && Intrinsics.areEqual(this.payType, ptbDetailBean.payType) && Intrinsics.areEqual(this.platMoney, ptbDetailBean.platMoney) && Intrinsics.areEqual(this.realMoney, ptbDetailBean.realMoney) && Intrinsics.areEqual(this.type, ptbDetailBean.type);
    }

    public int hashCode() {
        return (((((((((((((((((((GMTitleBean$$ExternalSyntheticBackport0.m(this.dateline) * 31) + this.discountedMoney.hashCode()) * 31) + this.game.hashCode()) * 31) + this.gameId) * 31) + this.goodsName.hashCode()) * 31) + this.money.hashCode()) * 31) + this.orderId.hashCode()) * 31) + this.payType.hashCode()) * 31) + this.platMoney.hashCode()) * 31) + this.realMoney.hashCode()) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "PtbDetailBean(dateline=" + this.dateline + ", discountedMoney=" + this.discountedMoney + ", game=" + this.game + ", gameId=" + this.gameId + ", goodsName=" + this.goodsName + ", money=" + this.money + ", orderId=" + this.orderId + ", payType=" + this.payType + ", platMoney=" + this.platMoney + ", realMoney=" + this.realMoney + ", type=" + this.type + ")";
    }

    public PtbDetailBean(long j, String discountedMoney, Game game, int i, String goodsName, String money, String orderId, String payType, String platMoney, String realMoney, String type) {
        Intrinsics.checkNotNullParameter(discountedMoney, "discountedMoney");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(goodsName, "goodsName");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(payType, "payType");
        Intrinsics.checkNotNullParameter(platMoney, "platMoney");
        Intrinsics.checkNotNullParameter(realMoney, "realMoney");
        Intrinsics.checkNotNullParameter(type, "type");
        this.dateline = j;
        this.discountedMoney = discountedMoney;
        this.game = game;
        this.gameId = i;
        this.goodsName = goodsName;
        this.money = money;
        this.orderId = orderId;
        this.payType = payType;
        this.platMoney = platMoney;
        this.realMoney = realMoney;
        this.type = type;
    }

    public final long getDateline() {
        return this.dateline;
    }

    public final String getDiscountedMoney() {
        return this.discountedMoney;
    }

    public final Game getGame() {
        return this.game;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getGoodsName() {
        return this.goodsName;
    }

    public final String getMoney() {
        return this.money;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final String getPayType() {
        return this.payType;
    }

    public final String getPlatMoney() {
        return this.platMoney;
    }

    public final String getRealMoney() {
        return this.realMoney;
    }

    public final String getType() {
        return this.type;
    }

    public final String getShowTime() {
        String str = new SimpleDateFormat("yyyy-MM-dd ").format(new Date(this.dateline * ((long) 1000)));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String getResult() {
        String str = this.payType;
        return Intrinsics.areEqual(str, Constants.PARAM_PLATFORM) ? this.platMoney : Intrinsics.areEqual(str, "coupon") ? this.money : this.realMoney;
    }

    public final String getName1() {
        String str;
        String str2 = this.type;
        if (Intrinsics.areEqual(str2, "game")) {
            str = "游戏消费";
        } else {
            str = Intrinsics.areEqual(str2, "coupon") ? "代金券" : "平台币";
        }
        return str + "：" + getOrderTypeText();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005e A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getOrderTypeText() {
        /*
            r2 = this;
            java.lang.String r0 = r2.payType
            int r1 = r0.hashCode()
            switch(r1) {
                case -1688718575: goto L55;
                case -1414960566: goto L4c;
                case -1403427754: goto L40;
                case -1354573786: goto L34;
                case -632962587: goto L2b;
                case -347208044: goto L1f;
                case 1608176980: goto L16;
                case 1874684019: goto La;
                default: goto L9;
            }
        L9:
            goto L61
        La:
            java.lang.String r1 = "platform"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L13
            goto L61
        L13:
            java.lang.String r0 = "平台币"
            goto L63
        L16:
            java.lang.String r1 = "wxpay_bank"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L49
            goto L61
        L1f:
            java.lang.String r1 = "backend"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L28
            goto L61
        L28:
            java.lang.String r0 = "后台充值"
            goto L63
        L2b:
            java.lang.String r1 = "wxpay_h5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L49
            goto L61
        L34:
            java.lang.String r1 = "coupon"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3d
            goto L61
        L3d:
            java.lang.String r0 = "代金券"
            goto L63
        L40:
            java.lang.String r1 = "wxpay_h5_bank"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L49
            goto L61
        L49:
            java.lang.String r0 = "微信"
            goto L63
        L4c:
            java.lang.String r1 = "alipay"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L5e
            goto L61
        L55:
            java.lang.String r1 = "alipay_bank"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L5e
            goto L61
        L5e:
            java.lang.String r0 = "支付宝"
            goto L63
        L61:
            java.lang.String r0 = ""
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.domain.PtbDetailBean.getOrderTypeText():java.lang.String");
    }

    /* JADX INFO: compiled from: PtbDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbDetailBean$Game;", "", "icon", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getIcon", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Game {
        public static final int $stable = 0;
        private final String icon;
        private final String name;

        public static /* synthetic */ Game copy$default(Game game, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = game.icon;
            }
            if ((i & 2) != 0) {
                str2 = game.name;
            }
            return game.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Game copy(String icon, String name) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            return new Game(icon, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Game)) {
                return false;
            }
            Game game = (Game) other;
            return Intrinsics.areEqual(this.icon, game.icon) && Intrinsics.areEqual(this.name, game.name);
        }

        public int hashCode() {
            return (this.icon.hashCode() * 31) + this.name.hashCode();
        }

        public String toString() {
            return "Game(icon=" + this.icon + ", name=" + this.name + ")";
        }

        public Game(String icon, String name) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            this.icon = icon;
            this.name = name;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final String getName() {
            return this.name;
        }
    }
}
