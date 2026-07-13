package com.cy.yyjia.zhe28.domain;

import com.lzy.okgo.cache.CacheEntity;
import com.tencent.connect.common.Constants;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PtbHomeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001:\u0005\u0017\u0018\u0019\u001a\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbHomeBean;", "", "money", "", "numOfCoupon", "payment", "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Payment;", "(Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Payment;)V", "getMoney", "()Ljava/lang/String;", "getNumOfCoupon", "getPayment", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Payment;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlipayBank", "Info", "Payment", "WxpayBank", "WxpayH5Bank", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class PtbHomeBean {
    public static final int $stable = 0;
    private final String money;
    private final String numOfCoupon;
    private final Payment payment;

    public static /* synthetic */ PtbHomeBean copy$default(PtbHomeBean ptbHomeBean, String str, String str2, Payment payment, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ptbHomeBean.money;
        }
        if ((i & 2) != 0) {
            str2 = ptbHomeBean.numOfCoupon;
        }
        if ((i & 4) != 0) {
            payment = ptbHomeBean.payment;
        }
        return ptbHomeBean.copy(str, str2, payment);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNumOfCoupon() {
        return this.numOfCoupon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Payment getPayment() {
        return this.payment;
    }

    public final PtbHomeBean copy(String money, String numOfCoupon, Payment payment) {
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(numOfCoupon, "numOfCoupon");
        Intrinsics.checkNotNullParameter(payment, "payment");
        return new PtbHomeBean(money, numOfCoupon, payment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PtbHomeBean)) {
            return false;
        }
        PtbHomeBean ptbHomeBean = (PtbHomeBean) other;
        return Intrinsics.areEqual(this.money, ptbHomeBean.money) && Intrinsics.areEqual(this.numOfCoupon, ptbHomeBean.numOfCoupon) && Intrinsics.areEqual(this.payment, ptbHomeBean.payment);
    }

    public int hashCode() {
        return (((this.money.hashCode() * 31) + this.numOfCoupon.hashCode()) * 31) + this.payment.hashCode();
    }

    public String toString() {
        return "PtbHomeBean(money=" + this.money + ", numOfCoupon=" + this.numOfCoupon + ", payment=" + this.payment + ")";
    }

    public PtbHomeBean(String money, String numOfCoupon, Payment payment) {
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(numOfCoupon, "numOfCoupon");
        Intrinsics.checkNotNullParameter(payment, "payment");
        this.money = money;
        this.numOfCoupon = numOfCoupon;
        this.payment = payment;
    }

    public final String getMoney() {
        return this.money;
    }

    public final String getNumOfCoupon() {
        return this.numOfCoupon;
    }

    public final Payment getPayment() {
        return this.payment;
    }

    /* JADX INFO: compiled from: PtbHomeBean.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Payment;", "", "alipay_bank", "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$AlipayBank;", "wxpay_bank", "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayBank;", "wxpay_h5_bank", "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayH5Bank;", "(Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$AlipayBank;Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayBank;Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayH5Bank;)V", "getAlipay_bank", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$AlipayBank;", "getWxpay_bank", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayBank;", "getWxpay_h5_bank", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayH5Bank;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Payment {
        public static final int $stable = 0;
        private final AlipayBank alipay_bank;
        private final WxpayBank wxpay_bank;
        private final WxpayH5Bank wxpay_h5_bank;

        public static /* synthetic */ Payment copy$default(Payment payment, AlipayBank alipayBank, WxpayBank wxpayBank, WxpayH5Bank wxpayH5Bank, int i, Object obj) {
            if ((i & 1) != 0) {
                alipayBank = payment.alipay_bank;
            }
            if ((i & 2) != 0) {
                wxpayBank = payment.wxpay_bank;
            }
            if ((i & 4) != 0) {
                wxpayH5Bank = payment.wxpay_h5_bank;
            }
            return payment.copy(alipayBank, wxpayBank, wxpayH5Bank);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AlipayBank getAlipay_bank() {
            return this.alipay_bank;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final WxpayBank getWxpay_bank() {
            return this.wxpay_bank;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final WxpayH5Bank getWxpay_h5_bank() {
            return this.wxpay_h5_bank;
        }

        public final Payment copy(AlipayBank alipay_bank, WxpayBank wxpay_bank, WxpayH5Bank wxpay_h5_bank) {
            Intrinsics.checkNotNullParameter(alipay_bank, "alipay_bank");
            Intrinsics.checkNotNullParameter(wxpay_bank, "wxpay_bank");
            Intrinsics.checkNotNullParameter(wxpay_h5_bank, "wxpay_h5_bank");
            return new Payment(alipay_bank, wxpay_bank, wxpay_h5_bank);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Payment)) {
                return false;
            }
            Payment payment = (Payment) other;
            return Intrinsics.areEqual(this.alipay_bank, payment.alipay_bank) && Intrinsics.areEqual(this.wxpay_bank, payment.wxpay_bank) && Intrinsics.areEqual(this.wxpay_h5_bank, payment.wxpay_h5_bank);
        }

        public int hashCode() {
            return (((this.alipay_bank.hashCode() * 31) + this.wxpay_bank.hashCode()) * 31) + this.wxpay_h5_bank.hashCode();
        }

        public String toString() {
            return "Payment(alipay_bank=" + this.alipay_bank + ", wxpay_bank=" + this.wxpay_bank + ", wxpay_h5_bank=" + this.wxpay_h5_bank + ")";
        }

        public Payment(AlipayBank alipay_bank, WxpayBank wxpay_bank, WxpayH5Bank wxpay_h5_bank) {
            Intrinsics.checkNotNullParameter(alipay_bank, "alipay_bank");
            Intrinsics.checkNotNullParameter(wxpay_bank, "wxpay_bank");
            Intrinsics.checkNotNullParameter(wxpay_h5_bank, "wxpay_h5_bank");
            this.alipay_bank = alipay_bank;
            this.wxpay_bank = wxpay_bank;
            this.wxpay_h5_bank = wxpay_h5_bank;
        }

        public final AlipayBank getAlipay_bank() {
            return this.alipay_bank;
        }

        public final WxpayBank getWxpay_bank() {
            return this.wxpay_bank;
        }

        public final WxpayH5Bank getWxpay_h5_bank() {
            return this.wxpay_h5_bank;
        }
    }

    /* JADX INFO: compiled from: PtbHomeBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$AlipayBank;", "", "channel", "", CommonConstants.VALUE_LEVEL_INFO, "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "name", "payType", "sortNum", "subChannel", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel", "()Ljava/lang/String;", "getInfo", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "getName", "getPayType", "getSortNum", "getSubChannel", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class AlipayBank {
        public static final int $stable = 0;
        private final String channel;
        private final Info info;
        private final String name;
        private final String payType;
        private final String sortNum;
        private final String subChannel;

        public static /* synthetic */ AlipayBank copy$default(AlipayBank alipayBank, String str, Info info, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = alipayBank.channel;
            }
            if ((i & 2) != 0) {
                info = alipayBank.info;
            }
            Info info2 = info;
            if ((i & 4) != 0) {
                str2 = alipayBank.name;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = alipayBank.payType;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = alipayBank.sortNum;
            }
            String str8 = str4;
            if ((i & 32) != 0) {
                str5 = alipayBank.subChannel;
            }
            return alipayBank.copy(str, info2, str6, str7, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getChannel() {
            return this.channel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Info getInfo() {
            return this.info;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPayType() {
            return this.payType;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSortNum() {
            return this.sortNum;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSubChannel() {
            return this.subChannel;
        }

        public final AlipayBank copy(String channel, Info info, String name, String payType, String sortNum, String subChannel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            Intrinsics.checkNotNullParameter(sortNum, "sortNum");
            Intrinsics.checkNotNullParameter(subChannel, "subChannel");
            return new AlipayBank(channel, info, name, payType, sortNum, subChannel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AlipayBank)) {
                return false;
            }
            AlipayBank alipayBank = (AlipayBank) other;
            return Intrinsics.areEqual(this.channel, alipayBank.channel) && Intrinsics.areEqual(this.info, alipayBank.info) && Intrinsics.areEqual(this.name, alipayBank.name) && Intrinsics.areEqual(this.payType, alipayBank.payType) && Intrinsics.areEqual(this.sortNum, alipayBank.sortNum) && Intrinsics.areEqual(this.subChannel, alipayBank.subChannel);
        }

        public int hashCode() {
            return (((((((((this.channel.hashCode() * 31) + this.info.hashCode()) * 31) + this.name.hashCode()) * 31) + this.payType.hashCode()) * 31) + this.sortNum.hashCode()) * 31) + this.subChannel.hashCode();
        }

        public String toString() {
            return "AlipayBank(channel=" + this.channel + ", info=" + this.info + ", name=" + this.name + ", payType=" + this.payType + ", sortNum=" + this.sortNum + ", subChannel=" + this.subChannel + ")";
        }

        public AlipayBank(String channel, Info info, String name, String payType, String sortNum, String subChannel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            Intrinsics.checkNotNullParameter(sortNum, "sortNum");
            Intrinsics.checkNotNullParameter(subChannel, "subChannel");
            this.channel = channel;
            this.info = info;
            this.name = name;
            this.payType = payType;
            this.sortNum = sortNum;
            this.subChannel = subChannel;
        }

        public final String getChannel() {
            return this.channel;
        }

        public final Info getInfo() {
            return this.info;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPayType() {
            return this.payType;
        }

        public final String getSortNum() {
            return this.sortNum;
        }

        public final String getSubChannel() {
            return this.subChannel;
        }
    }

    /* JADX INFO: compiled from: PtbHomeBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayBank;", "", "channel", "", CommonConstants.VALUE_LEVEL_INFO, "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "name", "payType", "sortNum", "subChannel", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel", "()Ljava/lang/String;", "getInfo", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "getName", "getPayType", "getSortNum", "getSubChannel", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class WxpayBank {
        public static final int $stable = 0;
        private final String channel;
        private final Info info;
        private final String name;
        private final String payType;
        private final String sortNum;
        private final String subChannel;

        public static /* synthetic */ WxpayBank copy$default(WxpayBank wxpayBank, String str, Info info, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = wxpayBank.channel;
            }
            if ((i & 2) != 0) {
                info = wxpayBank.info;
            }
            Info info2 = info;
            if ((i & 4) != 0) {
                str2 = wxpayBank.name;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = wxpayBank.payType;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = wxpayBank.sortNum;
            }
            String str8 = str4;
            if ((i & 32) != 0) {
                str5 = wxpayBank.subChannel;
            }
            return wxpayBank.copy(str, info2, str6, str7, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getChannel() {
            return this.channel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Info getInfo() {
            return this.info;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPayType() {
            return this.payType;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSortNum() {
            return this.sortNum;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSubChannel() {
            return this.subChannel;
        }

        public final WxpayBank copy(String channel, Info info, String name, String payType, String sortNum, String subChannel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            Intrinsics.checkNotNullParameter(sortNum, "sortNum");
            Intrinsics.checkNotNullParameter(subChannel, "subChannel");
            return new WxpayBank(channel, info, name, payType, sortNum, subChannel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WxpayBank)) {
                return false;
            }
            WxpayBank wxpayBank = (WxpayBank) other;
            return Intrinsics.areEqual(this.channel, wxpayBank.channel) && Intrinsics.areEqual(this.info, wxpayBank.info) && Intrinsics.areEqual(this.name, wxpayBank.name) && Intrinsics.areEqual(this.payType, wxpayBank.payType) && Intrinsics.areEqual(this.sortNum, wxpayBank.sortNum) && Intrinsics.areEqual(this.subChannel, wxpayBank.subChannel);
        }

        public int hashCode() {
            return (((((((((this.channel.hashCode() * 31) + this.info.hashCode()) * 31) + this.name.hashCode()) * 31) + this.payType.hashCode()) * 31) + this.sortNum.hashCode()) * 31) + this.subChannel.hashCode();
        }

        public String toString() {
            return "WxpayBank(channel=" + this.channel + ", info=" + this.info + ", name=" + this.name + ", payType=" + this.payType + ", sortNum=" + this.sortNum + ", subChannel=" + this.subChannel + ")";
        }

        public WxpayBank(String channel, Info info, String name, String payType, String sortNum, String subChannel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            Intrinsics.checkNotNullParameter(sortNum, "sortNum");
            Intrinsics.checkNotNullParameter(subChannel, "subChannel");
            this.channel = channel;
            this.info = info;
            this.name = name;
            this.payType = payType;
            this.sortNum = sortNum;
            this.subChannel = subChannel;
        }

        public final String getChannel() {
            return this.channel;
        }

        public final Info getInfo() {
            return this.info;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPayType() {
            return this.payType;
        }

        public final String getSortNum() {
            return this.sortNum;
        }

        public final String getSubChannel() {
            return this.subChannel;
        }
    }

    /* JADX INFO: compiled from: PtbHomeBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$WxpayH5Bank;", "", "channel", "", CommonConstants.VALUE_LEVEL_INFO, "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "name", "payType", "sortNum", "subChannel", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel", "()Ljava/lang/String;", "getInfo", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "getName", "getPayType", "getSortNum", "getSubChannel", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class WxpayH5Bank {
        public static final int $stable = 0;
        private final String channel;
        private final Info info;
        private final String name;
        private final String payType;
        private final String sortNum;
        private final String subChannel;

        public static /* synthetic */ WxpayH5Bank copy$default(WxpayH5Bank wxpayH5Bank, String str, Info info, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = wxpayH5Bank.channel;
            }
            if ((i & 2) != 0) {
                info = wxpayH5Bank.info;
            }
            Info info2 = info;
            if ((i & 4) != 0) {
                str2 = wxpayH5Bank.name;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = wxpayH5Bank.payType;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = wxpayH5Bank.sortNum;
            }
            String str8 = str4;
            if ((i & 32) != 0) {
                str5 = wxpayH5Bank.subChannel;
            }
            return wxpayH5Bank.copy(str, info2, str6, str7, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getChannel() {
            return this.channel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Info getInfo() {
            return this.info;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPayType() {
            return this.payType;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSortNum() {
            return this.sortNum;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSubChannel() {
            return this.subChannel;
        }

        public final WxpayH5Bank copy(String channel, Info info, String name, String payType, String sortNum, String subChannel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            Intrinsics.checkNotNullParameter(sortNum, "sortNum");
            Intrinsics.checkNotNullParameter(subChannel, "subChannel");
            return new WxpayH5Bank(channel, info, name, payType, sortNum, subChannel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WxpayH5Bank)) {
                return false;
            }
            WxpayH5Bank wxpayH5Bank = (WxpayH5Bank) other;
            return Intrinsics.areEqual(this.channel, wxpayH5Bank.channel) && Intrinsics.areEqual(this.info, wxpayH5Bank.info) && Intrinsics.areEqual(this.name, wxpayH5Bank.name) && Intrinsics.areEqual(this.payType, wxpayH5Bank.payType) && Intrinsics.areEqual(this.sortNum, wxpayH5Bank.sortNum) && Intrinsics.areEqual(this.subChannel, wxpayH5Bank.subChannel);
        }

        public int hashCode() {
            return (((((((((this.channel.hashCode() * 31) + this.info.hashCode()) * 31) + this.name.hashCode()) * 31) + this.payType.hashCode()) * 31) + this.sortNum.hashCode()) * 31) + this.subChannel.hashCode();
        }

        public String toString() {
            return "WxpayH5Bank(channel=" + this.channel + ", info=" + this.info + ", name=" + this.name + ", payType=" + this.payType + ", sortNum=" + this.sortNum + ", subChannel=" + this.subChannel + ")";
        }

        public WxpayH5Bank(String channel, Info info, String name, String payType, String sortNum, String subChannel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            Intrinsics.checkNotNullParameter(sortNum, "sortNum");
            Intrinsics.checkNotNullParameter(subChannel, "subChannel");
            this.channel = channel;
            this.info = info;
            this.name = name;
            this.payType = payType;
            this.sortNum = sortNum;
            this.subChannel = subChannel;
        }

        public final String getChannel() {
            return this.channel;
        }

        public final Info getInfo() {
            return this.info;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPayType() {
            return this.payType;
        }

        public final String getSortNum() {
            return this.sortNum;
        }

        public final String getSubChannel() {
            return this.subChannel;
        }
    }

    /* JADX INFO: compiled from: PtbHomeBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003Jm\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006*"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PtbHomeBean$Info;", "", "alipay_public_key", "", Constants.JumpUrlConstants.URL_KEY_APPID, "charset", "gatewayUrl", "merchant_private_key", "sign_type", "appid", CacheEntity.KEY, "merchantid", "secert", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAlipay_public_key", "()Ljava/lang/String;", "getApp_id", "getAppid", "getCharset", "getGatewayUrl", "getKey", "getMerchant_private_key", "getMerchantid", "getSecert", "getSign_type", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Info {
        public static final int $stable = 0;
        private final String alipay_public_key;
        private final String app_id;
        private final String appid;
        private final String charset;
        private final String gatewayUrl;
        private final String key;
        private final String merchant_private_key;
        private final String merchantid;
        private final String secert;
        private final String sign_type;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAlipay_public_key() {
            return this.alipay_public_key;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getSecert() {
            return this.secert;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getApp_id() {
            return this.app_id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCharset() {
            return this.charset;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getGatewayUrl() {
            return this.gatewayUrl;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMerchant_private_key() {
            return this.merchant_private_key;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSign_type() {
            return this.sign_type;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getAppid() {
            return this.appid;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getMerchantid() {
            return this.merchantid;
        }

        public final Info copy(String alipay_public_key, String app_id, String charset, String gatewayUrl, String merchant_private_key, String sign_type, String appid, String key, String merchantid, String secert) {
            Intrinsics.checkNotNullParameter(alipay_public_key, "alipay_public_key");
            Intrinsics.checkNotNullParameter(app_id, "app_id");
            Intrinsics.checkNotNullParameter(charset, "charset");
            Intrinsics.checkNotNullParameter(gatewayUrl, "gatewayUrl");
            Intrinsics.checkNotNullParameter(merchant_private_key, "merchant_private_key");
            Intrinsics.checkNotNullParameter(sign_type, "sign_type");
            Intrinsics.checkNotNullParameter(appid, "appid");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(merchantid, "merchantid");
            Intrinsics.checkNotNullParameter(secert, "secert");
            return new Info(alipay_public_key, app_id, charset, gatewayUrl, merchant_private_key, sign_type, appid, key, merchantid, secert);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Info)) {
                return false;
            }
            Info info = (Info) other;
            return Intrinsics.areEqual(this.alipay_public_key, info.alipay_public_key) && Intrinsics.areEqual(this.app_id, info.app_id) && Intrinsics.areEqual(this.charset, info.charset) && Intrinsics.areEqual(this.gatewayUrl, info.gatewayUrl) && Intrinsics.areEqual(this.merchant_private_key, info.merchant_private_key) && Intrinsics.areEqual(this.sign_type, info.sign_type) && Intrinsics.areEqual(this.appid, info.appid) && Intrinsics.areEqual(this.key, info.key) && Intrinsics.areEqual(this.merchantid, info.merchantid) && Intrinsics.areEqual(this.secert, info.secert);
        }

        public int hashCode() {
            return (((((((((((((((((this.alipay_public_key.hashCode() * 31) + this.app_id.hashCode()) * 31) + this.charset.hashCode()) * 31) + this.gatewayUrl.hashCode()) * 31) + this.merchant_private_key.hashCode()) * 31) + this.sign_type.hashCode()) * 31) + this.appid.hashCode()) * 31) + this.key.hashCode()) * 31) + this.merchantid.hashCode()) * 31) + this.secert.hashCode();
        }

        public String toString() {
            return "Info(alipay_public_key=" + this.alipay_public_key + ", app_id=" + this.app_id + ", charset=" + this.charset + ", gatewayUrl=" + this.gatewayUrl + ", merchant_private_key=" + this.merchant_private_key + ", sign_type=" + this.sign_type + ", appid=" + this.appid + ", key=" + this.key + ", merchantid=" + this.merchantid + ", secert=" + this.secert + ")";
        }

        public Info(String alipay_public_key, String app_id, String charset, String gatewayUrl, String merchant_private_key, String sign_type, String appid, String key, String merchantid, String secert) {
            Intrinsics.checkNotNullParameter(alipay_public_key, "alipay_public_key");
            Intrinsics.checkNotNullParameter(app_id, "app_id");
            Intrinsics.checkNotNullParameter(charset, "charset");
            Intrinsics.checkNotNullParameter(gatewayUrl, "gatewayUrl");
            Intrinsics.checkNotNullParameter(merchant_private_key, "merchant_private_key");
            Intrinsics.checkNotNullParameter(sign_type, "sign_type");
            Intrinsics.checkNotNullParameter(appid, "appid");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(merchantid, "merchantid");
            Intrinsics.checkNotNullParameter(secert, "secert");
            this.alipay_public_key = alipay_public_key;
            this.app_id = app_id;
            this.charset = charset;
            this.gatewayUrl = gatewayUrl;
            this.merchant_private_key = merchant_private_key;
            this.sign_type = sign_type;
            this.appid = appid;
            this.key = key;
            this.merchantid = merchantid;
            this.secert = secert;
        }

        public final String getAlipay_public_key() {
            return this.alipay_public_key;
        }

        public final String getApp_id() {
            return this.app_id;
        }

        public final String getCharset() {
            return this.charset;
        }

        public final String getGatewayUrl() {
            return this.gatewayUrl;
        }

        public final String getMerchant_private_key() {
            return this.merchant_private_key;
        }

        public final String getSign_type() {
            return this.sign_type;
        }

        public final String getAppid() {
            return this.appid;
        }

        public final String getKey() {
            return this.key;
        }

        public final String getMerchantid() {
            return this.merchantid;
        }

        public final String getSecert() {
            return this.secert;
        }
    }
}
