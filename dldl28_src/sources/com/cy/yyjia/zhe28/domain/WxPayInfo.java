package com.cy.yyjia.zhe28.domain;

import androidx.core.provider.FontsContractCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WxPayInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WxPayInfo;", "", "paydata", "Lcom/cy/yyjia/zhe28/domain/WxPayInfo$Paydata;", "paytype", "", "msg", "(Lcom/cy/yyjia/zhe28/domain/WxPayInfo$Paydata;Ljava/lang/String;Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "getPaydata", "()Lcom/cy/yyjia/zhe28/domain/WxPayInfo$Paydata;", "getPaytype", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Paydata", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WxPayInfo {
    public static final int $stable = 0;
    private final String msg;
    private final Paydata paydata;
    private final String paytype;

    public static /* synthetic */ WxPayInfo copy$default(WxPayInfo wxPayInfo, Paydata paydata, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            paydata = wxPayInfo.paydata;
        }
        if ((i & 2) != 0) {
            str = wxPayInfo.paytype;
        }
        if ((i & 4) != 0) {
            str2 = wxPayInfo.msg;
        }
        return wxPayInfo.copy(paydata, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Paydata getPaydata() {
        return this.paydata;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPaytype() {
        return this.paytype;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final WxPayInfo copy(Paydata paydata, String paytype, String msg) {
        Intrinsics.checkNotNullParameter(paydata, "paydata");
        Intrinsics.checkNotNullParameter(paytype, "paytype");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return new WxPayInfo(paydata, paytype, msg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WxPayInfo)) {
            return false;
        }
        WxPayInfo wxPayInfo = (WxPayInfo) other;
        return Intrinsics.areEqual(this.paydata, wxPayInfo.paydata) && Intrinsics.areEqual(this.paytype, wxPayInfo.paytype) && Intrinsics.areEqual(this.msg, wxPayInfo.msg);
    }

    public int hashCode() {
        return (((this.paydata.hashCode() * 31) + this.paytype.hashCode()) * 31) + this.msg.hashCode();
    }

    public String toString() {
        return "WxPayInfo(paydata=" + this.paydata + ", paytype=" + this.paytype + ", msg=" + this.msg + ")";
    }

    public WxPayInfo(Paydata paydata, String paytype, String msg) {
        Intrinsics.checkNotNullParameter(paydata, "paydata");
        Intrinsics.checkNotNullParameter(paytype, "paytype");
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.paydata = paydata;
        this.paytype = paytype;
        this.msg = msg;
    }

    public final Paydata getPaydata() {
        return this.paydata;
    }

    public final String getPaytype() {
        return this.paytype;
    }

    public final String getMsg() {
        return this.msg;
    }

    /* JADX INFO: compiled from: WxPayInfo.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003Jw\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006-"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WxPayInfo$Paydata;", "", "appid", "", "code_url", "mch_id", "nonce_str", "prepay_id", FontsContractCompat.Columns.RESULT_CODE, "return_code", "return_msg", "sign", "mweb_url", "trade_type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppid", "()Ljava/lang/String;", "getCode_url", "getMch_id", "getMweb_url", "getNonce_str", "getPrepay_id", "getResult_code", "getReturn_code", "getReturn_msg", "getSign", "getTrade_type", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Paydata {
        public static final int $stable = 0;
        private final String appid;
        private final String code_url;
        private final String mch_id;
        private final String mweb_url;
        private final String nonce_str;
        private final String prepay_id;
        private final String result_code;
        private final String return_code;
        private final String return_msg;
        private final String sign;
        private final String trade_type;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAppid() {
            return this.appid;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getMweb_url() {
            return this.mweb_url;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getTrade_type() {
            return this.trade_type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCode_url() {
            return this.code_url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMch_id() {
            return this.mch_id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getNonce_str() {
            return this.nonce_str;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPrepay_id() {
            return this.prepay_id;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getResult_code() {
            return this.result_code;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getReturn_code() {
            return this.return_code;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getReturn_msg() {
            return this.return_msg;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSign() {
            return this.sign;
        }

        public final Paydata copy(String appid, String code_url, String mch_id, String nonce_str, String prepay_id, String result_code, String return_code, String return_msg, String sign, String mweb_url, String trade_type) {
            Intrinsics.checkNotNullParameter(appid, "appid");
            Intrinsics.checkNotNullParameter(code_url, "code_url");
            Intrinsics.checkNotNullParameter(mch_id, "mch_id");
            Intrinsics.checkNotNullParameter(nonce_str, "nonce_str");
            Intrinsics.checkNotNullParameter(prepay_id, "prepay_id");
            Intrinsics.checkNotNullParameter(result_code, "result_code");
            Intrinsics.checkNotNullParameter(return_code, "return_code");
            Intrinsics.checkNotNullParameter(return_msg, "return_msg");
            Intrinsics.checkNotNullParameter(sign, "sign");
            Intrinsics.checkNotNullParameter(mweb_url, "mweb_url");
            Intrinsics.checkNotNullParameter(trade_type, "trade_type");
            return new Paydata(appid, code_url, mch_id, nonce_str, prepay_id, result_code, return_code, return_msg, sign, mweb_url, trade_type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Paydata)) {
                return false;
            }
            Paydata paydata = (Paydata) other;
            return Intrinsics.areEqual(this.appid, paydata.appid) && Intrinsics.areEqual(this.code_url, paydata.code_url) && Intrinsics.areEqual(this.mch_id, paydata.mch_id) && Intrinsics.areEqual(this.nonce_str, paydata.nonce_str) && Intrinsics.areEqual(this.prepay_id, paydata.prepay_id) && Intrinsics.areEqual(this.result_code, paydata.result_code) && Intrinsics.areEqual(this.return_code, paydata.return_code) && Intrinsics.areEqual(this.return_msg, paydata.return_msg) && Intrinsics.areEqual(this.sign, paydata.sign) && Intrinsics.areEqual(this.mweb_url, paydata.mweb_url) && Intrinsics.areEqual(this.trade_type, paydata.trade_type);
        }

        public int hashCode() {
            return (((((((((((((((((((this.appid.hashCode() * 31) + this.code_url.hashCode()) * 31) + this.mch_id.hashCode()) * 31) + this.nonce_str.hashCode()) * 31) + this.prepay_id.hashCode()) * 31) + this.result_code.hashCode()) * 31) + this.return_code.hashCode()) * 31) + this.return_msg.hashCode()) * 31) + this.sign.hashCode()) * 31) + this.mweb_url.hashCode()) * 31) + this.trade_type.hashCode();
        }

        public String toString() {
            return "Paydata(appid=" + this.appid + ", code_url=" + this.code_url + ", mch_id=" + this.mch_id + ", nonce_str=" + this.nonce_str + ", prepay_id=" + this.prepay_id + ", result_code=" + this.result_code + ", return_code=" + this.return_code + ", return_msg=" + this.return_msg + ", sign=" + this.sign + ", mweb_url=" + this.mweb_url + ", trade_type=" + this.trade_type + ")";
        }

        public Paydata(String appid, String code_url, String mch_id, String nonce_str, String prepay_id, String result_code, String return_code, String return_msg, String sign, String mweb_url, String trade_type) {
            Intrinsics.checkNotNullParameter(appid, "appid");
            Intrinsics.checkNotNullParameter(code_url, "code_url");
            Intrinsics.checkNotNullParameter(mch_id, "mch_id");
            Intrinsics.checkNotNullParameter(nonce_str, "nonce_str");
            Intrinsics.checkNotNullParameter(prepay_id, "prepay_id");
            Intrinsics.checkNotNullParameter(result_code, "result_code");
            Intrinsics.checkNotNullParameter(return_code, "return_code");
            Intrinsics.checkNotNullParameter(return_msg, "return_msg");
            Intrinsics.checkNotNullParameter(sign, "sign");
            Intrinsics.checkNotNullParameter(mweb_url, "mweb_url");
            Intrinsics.checkNotNullParameter(trade_type, "trade_type");
            this.appid = appid;
            this.code_url = code_url;
            this.mch_id = mch_id;
            this.nonce_str = nonce_str;
            this.prepay_id = prepay_id;
            this.result_code = result_code;
            this.return_code = return_code;
            this.return_msg = return_msg;
            this.sign = sign;
            this.mweb_url = mweb_url;
            this.trade_type = trade_type;
        }

        public final String getAppid() {
            return this.appid;
        }

        public final String getCode_url() {
            return this.code_url;
        }

        public final String getMch_id() {
            return this.mch_id;
        }

        public final String getNonce_str() {
            return this.nonce_str;
        }

        public final String getPrepay_id() {
            return this.prepay_id;
        }

        public final String getResult_code() {
            return this.result_code;
        }

        public final String getReturn_code() {
            return this.return_code;
        }

        public final String getReturn_msg() {
            return this.return_msg;
        }

        public final String getSign() {
            return this.sign;
        }

        public final String getMweb_url() {
            return this.mweb_url;
        }

        public final String getTrade_type() {
            return this.trade_type;
        }
    }
}
