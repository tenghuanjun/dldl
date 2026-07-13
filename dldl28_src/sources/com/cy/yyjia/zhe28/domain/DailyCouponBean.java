package com.cy.yyjia.zhe28.domain;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: DailyCouponBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DailyCouponBean;", "", "config", "Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Config;", "statDay", "", "tiers", "", "Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Tier;", "totalMoney", "(Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Config;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Config;", "getStatDay", "()Ljava/lang/String;", "getTiers", "()Ljava/util/List;", "getTotalMoney", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Config", "Tier", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DailyCouponBean {
    public static final int $stable = 8;
    private final Config config;
    private final String statDay;
    private final List<Tier> tiers;
    private final String totalMoney;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DailyCouponBean copy$default(DailyCouponBean dailyCouponBean, Config config, String str, List list, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            config = dailyCouponBean.config;
        }
        if ((i & 2) != 0) {
            str = dailyCouponBean.statDay;
        }
        if ((i & 4) != 0) {
            list = dailyCouponBean.tiers;
        }
        if ((i & 8) != 0) {
            str2 = dailyCouponBean.totalMoney;
        }
        return dailyCouponBean.copy(config, str, list, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Config getConfig() {
        return this.config;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStatDay() {
        return this.statDay;
    }

    public final List<Tier> component3() {
        return this.tiers;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTotalMoney() {
        return this.totalMoney;
    }

    public final DailyCouponBean copy(Config config, String statDay, List<Tier> tiers, String totalMoney) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(statDay, "statDay");
        Intrinsics.checkNotNullParameter(tiers, "tiers");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        return new DailyCouponBean(config, statDay, tiers, totalMoney);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DailyCouponBean)) {
            return false;
        }
        DailyCouponBean dailyCouponBean = (DailyCouponBean) other;
        return Intrinsics.areEqual(this.config, dailyCouponBean.config) && Intrinsics.areEqual(this.statDay, dailyCouponBean.statDay) && Intrinsics.areEqual(this.tiers, dailyCouponBean.tiers) && Intrinsics.areEqual(this.totalMoney, dailyCouponBean.totalMoney);
    }

    public int hashCode() {
        return (((((this.config.hashCode() * 31) + this.statDay.hashCode()) * 31) + this.tiers.hashCode()) * 31) + this.totalMoney.hashCode();
    }

    public String toString() {
        return "DailyCouponBean(config=" + this.config + ", statDay=" + this.statDay + ", tiers=" + this.tiers + ", totalMoney=" + this.totalMoney + ")";
    }

    public DailyCouponBean(Config config, String statDay, List<Tier> tiers, String totalMoney) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(statDay, "statDay");
        Intrinsics.checkNotNullParameter(tiers, "tiers");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        this.config = config;
        this.statDay = statDay;
        this.tiers = tiers;
        this.totalMoney = totalMoney;
    }

    public final Config getConfig() {
        return this.config;
    }

    public final String getStatDay() {
        return this.statDay;
    }

    public final List<Tier> getTiers() {
        return this.tiers;
    }

    public final String getTotalMoney() {
        return this.totalMoney;
    }

    /* JADX INFO: compiled from: DailyCouponBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Config;", "", "bg_image", "", "id", "", "rules", MetricsSQLiteCacheKt.METRICS_START_TIME, "status", "title", "(Ljava/lang/String;ILjava/lang/String;IILjava/lang/String;)V", "getBg_image", "()Ljava/lang/String;", "getId", "()I", "getRules", "getStart_time", "getStatus", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Config {
        public static final int $stable = 0;
        private final String bg_image;
        private final int id;
        private final String rules;
        private final int start_time;
        private final int status;
        private final String title;

        public static /* synthetic */ Config copy$default(Config config, String str, int i, String str2, int i2, int i3, String str3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = config.bg_image;
            }
            if ((i4 & 2) != 0) {
                i = config.id;
            }
            int i5 = i;
            if ((i4 & 4) != 0) {
                str2 = config.rules;
            }
            String str4 = str2;
            if ((i4 & 8) != 0) {
                i2 = config.start_time;
            }
            int i6 = i2;
            if ((i4 & 16) != 0) {
                i3 = config.status;
            }
            int i7 = i3;
            if ((i4 & 32) != 0) {
                str3 = config.title;
            }
            return config.copy(str, i5, str4, i6, i7, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg_image() {
            return this.bg_image;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRules() {
            return this.rules;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getStart_time() {
            return this.start_time;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final Config copy(String bg_image, int id, String rules, int start_time, int status, String title) {
            Intrinsics.checkNotNullParameter(bg_image, "bg_image");
            Intrinsics.checkNotNullParameter(rules, "rules");
            Intrinsics.checkNotNullParameter(title, "title");
            return new Config(bg_image, id, rules, start_time, status, title);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return Intrinsics.areEqual(this.bg_image, config.bg_image) && this.id == config.id && Intrinsics.areEqual(this.rules, config.rules) && this.start_time == config.start_time && this.status == config.status && Intrinsics.areEqual(this.title, config.title);
        }

        public int hashCode() {
            return (((((((((this.bg_image.hashCode() * 31) + this.id) * 31) + this.rules.hashCode()) * 31) + this.start_time) * 31) + this.status) * 31) + this.title.hashCode();
        }

        public String toString() {
            return "Config(bg_image=" + this.bg_image + ", id=" + this.id + ", rules=" + this.rules + ", start_time=" + this.start_time + ", status=" + this.status + ", title=" + this.title + ")";
        }

        public Config(String bg_image, int i, String rules, int i2, int i3, String title) {
            Intrinsics.checkNotNullParameter(bg_image, "bg_image");
            Intrinsics.checkNotNullParameter(rules, "rules");
            Intrinsics.checkNotNullParameter(title, "title");
            this.bg_image = bg_image;
            this.id = i;
            this.rules = rules;
            this.start_time = i2;
            this.status = i3;
            this.title = title;
        }

        public final String getBg_image() {
            return this.bg_image;
        }

        public final int getId() {
            return this.id;
        }

        public final String getRules() {
            return this.rules;
        }

        public final int getStart_time() {
            return this.start_time;
        }

        public final int getStatus() {
            return this.status;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    /* JADX INFO: compiled from: DailyCouponBean.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010(\u001a\u00020\u0007J\t\u0010)\u001a\u00020\u0005HÖ\u0001J\t\u0010*\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006+"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Tier;", "", "coupon", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "coupon_id", "", "done", "", "effect_type", "id", "log_id", "min_amount", "popup_bg", "status", "(Lcom/cy/yyjia/zhe28/domain/CouponBean;ILjava/lang/String;IIILjava/lang/String;Ljava/lang/String;I)V", "getCoupon", "()Lcom/cy/yyjia/zhe28/domain/CouponBean;", "getCoupon_id", "()I", "getDone", "()Ljava/lang/String;", "getEffect_type", "getId", "getLog_id", "getMin_amount", "getPopup_bg", "getStatus", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getBtnText", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Tier {
        public static final int $stable = 8;
        private final CouponBean coupon;
        private final int coupon_id;
        private final String done;
        private final int effect_type;
        private final int id;
        private final int log_id;
        private final String min_amount;
        private final String popup_bg;
        private final int status;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final CouponBean getCoupon() {
            return this.coupon;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCoupon_id() {
            return this.coupon_id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDone() {
            return this.done;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getEffect_type() {
            return this.effect_type;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getLog_id() {
            return this.log_id;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getMin_amount() {
            return this.min_amount;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPopup_bg() {
            return this.popup_bg;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        public final Tier copy(CouponBean coupon, int coupon_id, String done, int effect_type, int id, int log_id, String min_amount, String popup_bg, int status) {
            Intrinsics.checkNotNullParameter(coupon, "coupon");
            Intrinsics.checkNotNullParameter(done, "done");
            Intrinsics.checkNotNullParameter(min_amount, "min_amount");
            Intrinsics.checkNotNullParameter(popup_bg, "popup_bg");
            return new Tier(coupon, coupon_id, done, effect_type, id, log_id, min_amount, popup_bg, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Tier)) {
                return false;
            }
            Tier tier = (Tier) other;
            return Intrinsics.areEqual(this.coupon, tier.coupon) && this.coupon_id == tier.coupon_id && Intrinsics.areEqual(this.done, tier.done) && this.effect_type == tier.effect_type && this.id == tier.id && this.log_id == tier.log_id && Intrinsics.areEqual(this.min_amount, tier.min_amount) && Intrinsics.areEqual(this.popup_bg, tier.popup_bg) && this.status == tier.status;
        }

        public int hashCode() {
            return (((((((((((((((this.coupon.hashCode() * 31) + this.coupon_id) * 31) + this.done.hashCode()) * 31) + this.effect_type) * 31) + this.id) * 31) + this.log_id) * 31) + this.min_amount.hashCode()) * 31) + this.popup_bg.hashCode()) * 31) + this.status;
        }

        public String toString() {
            return "Tier(coupon=" + this.coupon + ", coupon_id=" + this.coupon_id + ", done=" + this.done + ", effect_type=" + this.effect_type + ", id=" + this.id + ", log_id=" + this.log_id + ", min_amount=" + this.min_amount + ", popup_bg=" + this.popup_bg + ", status=" + this.status + ")";
        }

        public Tier(CouponBean coupon, int i, String done, int i2, int i3, int i4, String min_amount, String popup_bg, int i5) {
            Intrinsics.checkNotNullParameter(coupon, "coupon");
            Intrinsics.checkNotNullParameter(done, "done");
            Intrinsics.checkNotNullParameter(min_amount, "min_amount");
            Intrinsics.checkNotNullParameter(popup_bg, "popup_bg");
            this.coupon = coupon;
            this.coupon_id = i;
            this.done = done;
            this.effect_type = i2;
            this.id = i3;
            this.log_id = i4;
            this.min_amount = min_amount;
            this.popup_bg = popup_bg;
            this.status = i5;
        }

        public final CouponBean getCoupon() {
            return this.coupon;
        }

        public final int getCoupon_id() {
            return this.coupon_id;
        }

        public final String getDone() {
            return this.done;
        }

        public final int getEffect_type() {
            return this.effect_type;
        }

        public final int getId() {
            return this.id;
        }

        public final int getLog_id() {
            return this.log_id;
        }

        public final String getMin_amount() {
            return this.min_amount;
        }

        public final String getPopup_bg() {
            return this.popup_bg;
        }

        public final int getStatus() {
            return this.status;
        }

        public final String getBtnText() {
            String str = this.done;
            return Intrinsics.areEqual(str, "not_receive") ? "领取" : Intrinsics.areEqual(str, BooleanUtils.YES) ? "已领取" : "未完成";
        }
    }
}
