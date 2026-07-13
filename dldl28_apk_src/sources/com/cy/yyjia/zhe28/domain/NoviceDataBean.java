package com.cy.yyjia.zhe28.domain;

import com.cy.yyjia.zhe28.domain.QiandaoBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoviceDataBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001e\u001fBE\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u001c\u001a\u00020\u0004J\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NoviceDataBean;", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/NoviceDataBean$CouponBean;", "rule", "", "adpic", "receiveRule", "sign", "Lcom/cy/yyjia/zhe28/domain/NoviceDataBean$Sign;", "awardYhq", "Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;", "gift_live", "Lcom/cy/yyjia/zhe28/domain/GameToolBean;", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/NoviceDataBean$Sign;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;Lcom/cy/yyjia/zhe28/domain/GameToolBean;)V", "getAdpic", "()Ljava/lang/String;", "getAwardYhq", "()Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;", "getGift_live", "()Lcom/cy/yyjia/zhe28/domain/GameToolBean;", "getList", "()Ljava/util/List;", "getReceiveRule", "getRule", "getSign", "()Lcom/cy/yyjia/zhe28/domain/NoviceDataBean$Sign;", "getCoupon", "getCouponList", "CouponBean", "Sign", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class NoviceDataBean {
    public static final int $stable = 8;
    private final String adpic;
    private final QiandaoBean.YhqBean awardYhq;
    private final GameToolBean gift_live;
    private final List<CouponBean> list;
    private final String receiveRule;
    private final String rule;
    private final Sign sign;

    public NoviceDataBean(List<CouponBean> list, String rule, String adpic, String receiveRule, Sign sign, QiandaoBean.YhqBean awardYhq, GameToolBean gameToolBean) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(adpic, "adpic");
        Intrinsics.checkNotNullParameter(receiveRule, "receiveRule");
        Intrinsics.checkNotNullParameter(sign, "sign");
        Intrinsics.checkNotNullParameter(awardYhq, "awardYhq");
        this.list = list;
        this.rule = rule;
        this.adpic = adpic;
        this.receiveRule = receiveRule;
        this.sign = sign;
        this.awardYhq = awardYhq;
        this.gift_live = gameToolBean;
    }

    public final List<CouponBean> getList() {
        return this.list;
    }

    public final String getRule() {
        return this.rule;
    }

    public final String getAdpic() {
        return this.adpic;
    }

    public final String getReceiveRule() {
        return this.receiveRule;
    }

    public final Sign getSign() {
        return this.sign;
    }

    public final QiandaoBean.YhqBean getAwardYhq() {
        return this.awardYhq;
    }

    public final GameToolBean getGift_live() {
        return this.gift_live;
    }

    public final CouponBean getCoupon() {
        return this.list.get(0);
    }

    public final List<CouponBean> getCouponList() {
        if (this.list.size() > 1) {
            List<CouponBean> list = this.list;
            return list.subList(1, list.size());
        }
        return new ArrayList();
    }

    /* JADX INFO: compiled from: NoviceDataBean.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NoviceDataBean$CouponBean;", "", "id", "", "useCondition", "", "amount", "isGet", "validity", "signDays", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/String;", "getId", "()I", "getSignDays", "getUseCondition", "getValidity", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CouponBean {
        public static final int $stable = 0;
        private final String amount;
        private final int id;
        private final int isGet;
        private final String signDays;
        private final String useCondition;
        private final String validity;

        public CouponBean(int i, String useCondition, String amount, int i2, String validity, String signDays) {
            Intrinsics.checkNotNullParameter(useCondition, "useCondition");
            Intrinsics.checkNotNullParameter(amount, "amount");
            Intrinsics.checkNotNullParameter(validity, "validity");
            Intrinsics.checkNotNullParameter(signDays, "signDays");
            this.id = i;
            this.useCondition = useCondition;
            this.amount = amount;
            this.isGet = i2;
            this.validity = validity;
            this.signDays = signDays;
        }

        public final int getId() {
            return this.id;
        }

        public final String getUseCondition() {
            return this.useCondition;
        }

        public final String getAmount() {
            return this.amount;
        }

        /* JADX INFO: renamed from: isGet, reason: from getter */
        public final int getIsGet() {
            return this.isGet;
        }

        public final String getValidity() {
            return this.validity;
        }

        public final String getSignDays() {
            return this.signDays;
        }
    }

    /* JADX INFO: compiled from: NoviceDataBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NoviceDataBean$Sign;", "", "signDays", "", "lastTime", "todaySign", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLastTime", "()Ljava/lang/String;", "getSignDays", "getTodaySign", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Sign {
        public static final int $stable = 0;
        private final String lastTime;
        private final String signDays;
        private final String todaySign;

        public Sign(String signDays, String lastTime, String todaySign) {
            Intrinsics.checkNotNullParameter(signDays, "signDays");
            Intrinsics.checkNotNullParameter(lastTime, "lastTime");
            Intrinsics.checkNotNullParameter(todaySign, "todaySign");
            this.signDays = signDays;
            this.lastTime = lastTime;
            this.todaySign = todaySign;
        }

        public final String getSignDays() {
            return this.signDays;
        }

        public final String getLastTime() {
            return this.lastTime;
        }

        public final String getTodaySign() {
            return this.todaySign;
        }
    }
}
