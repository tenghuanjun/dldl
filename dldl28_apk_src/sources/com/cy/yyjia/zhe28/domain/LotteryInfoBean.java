package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LotteryInfoBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001:\u0003567Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0007¢\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u000eHÆ\u0003J}\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0007HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\u0005HÖ\u0001J\t\u00104\u001a\u00020\u0007HÖ\u0001R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001b¨\u00068"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean;", "", "config", "Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Config;", "id", "", "lottery_name", "", "user_credit", "lottery_reward_type", "lottery_status", "lottery_type", "lottery_use_type", "prize_module", "Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Prize;", "bottom_module", "", "Lcom/cy/yyjia/zhe28/domain/GameToolBean;", "rule", "(Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Config;ILjava/lang/String;Ljava/lang/String;IIIILcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Prize;Ljava/util/List;Ljava/lang/String;)V", "getBottom_module", "()Ljava/util/List;", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Config;", "getId", "()I", "getLottery_name", "()Ljava/lang/String;", "getLottery_reward_type", "getLottery_status", "getLottery_type", "getLottery_use_type", "getPrize_module", "()Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Prize;", "getRule", "getUser_credit", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "Config", "Prize", "Use", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class LotteryInfoBean {
    public static final int $stable = 8;
    private final List<GameToolBean> bottom_module;
    private final Config config;
    private final int id;
    private final String lottery_name;
    private final int lottery_reward_type;
    private final int lottery_status;
    private final int lottery_type;
    private final int lottery_use_type;
    private final Prize prize_module;
    private final String rule;
    private final String user_credit;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Config getConfig() {
        return this.config;
    }

    public final List<GameToolBean> component10() {
        return this.bottom_module;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRule() {
        return this.rule;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLottery_name() {
        return this.lottery_name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUser_credit() {
        return this.user_credit;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getLottery_reward_type() {
        return this.lottery_reward_type;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getLottery_status() {
        return this.lottery_status;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getLottery_type() {
        return this.lottery_type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getLottery_use_type() {
        return this.lottery_use_type;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Prize getPrize_module() {
        return this.prize_module;
    }

    public final LotteryInfoBean copy(Config config, int id, String lottery_name, String user_credit, int lottery_reward_type, int lottery_status, int lottery_type, int lottery_use_type, Prize prize_module, List<GameToolBean> bottom_module, String rule) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(lottery_name, "lottery_name");
        Intrinsics.checkNotNullParameter(user_credit, "user_credit");
        Intrinsics.checkNotNullParameter(prize_module, "prize_module");
        Intrinsics.checkNotNullParameter(bottom_module, "bottom_module");
        Intrinsics.checkNotNullParameter(rule, "rule");
        return new LotteryInfoBean(config, id, lottery_name, user_credit, lottery_reward_type, lottery_status, lottery_type, lottery_use_type, prize_module, bottom_module, rule);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LotteryInfoBean)) {
            return false;
        }
        LotteryInfoBean lotteryInfoBean = (LotteryInfoBean) other;
        return Intrinsics.areEqual(this.config, lotteryInfoBean.config) && this.id == lotteryInfoBean.id && Intrinsics.areEqual(this.lottery_name, lotteryInfoBean.lottery_name) && Intrinsics.areEqual(this.user_credit, lotteryInfoBean.user_credit) && this.lottery_reward_type == lotteryInfoBean.lottery_reward_type && this.lottery_status == lotteryInfoBean.lottery_status && this.lottery_type == lotteryInfoBean.lottery_type && this.lottery_use_type == lotteryInfoBean.lottery_use_type && Intrinsics.areEqual(this.prize_module, lotteryInfoBean.prize_module) && Intrinsics.areEqual(this.bottom_module, lotteryInfoBean.bottom_module) && Intrinsics.areEqual(this.rule, lotteryInfoBean.rule);
    }

    public int hashCode() {
        return (((((((((((((((((((this.config.hashCode() * 31) + this.id) * 31) + this.lottery_name.hashCode()) * 31) + this.user_credit.hashCode()) * 31) + this.lottery_reward_type) * 31) + this.lottery_status) * 31) + this.lottery_type) * 31) + this.lottery_use_type) * 31) + this.prize_module.hashCode()) * 31) + this.bottom_module.hashCode()) * 31) + this.rule.hashCode();
    }

    public String toString() {
        return "LotteryInfoBean(config=" + this.config + ", id=" + this.id + ", lottery_name=" + this.lottery_name + ", user_credit=" + this.user_credit + ", lottery_reward_type=" + this.lottery_reward_type + ", lottery_status=" + this.lottery_status + ", lottery_type=" + this.lottery_type + ", lottery_use_type=" + this.lottery_use_type + ", prize_module=" + this.prize_module + ", bottom_module=" + this.bottom_module + ", rule=" + this.rule + ")";
    }

    public LotteryInfoBean(Config config, int i, String lottery_name, String user_credit, int i2, int i3, int i4, int i5, Prize prize_module, List<GameToolBean> bottom_module, String rule) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(lottery_name, "lottery_name");
        Intrinsics.checkNotNullParameter(user_credit, "user_credit");
        Intrinsics.checkNotNullParameter(prize_module, "prize_module");
        Intrinsics.checkNotNullParameter(bottom_module, "bottom_module");
        Intrinsics.checkNotNullParameter(rule, "rule");
        this.config = config;
        this.id = i;
        this.lottery_name = lottery_name;
        this.user_credit = user_credit;
        this.lottery_reward_type = i2;
        this.lottery_status = i3;
        this.lottery_type = i4;
        this.lottery_use_type = i5;
        this.prize_module = prize_module;
        this.bottom_module = bottom_module;
        this.rule = rule;
    }

    public final Config getConfig() {
        return this.config;
    }

    public final int getId() {
        return this.id;
    }

    public final String getLottery_name() {
        return this.lottery_name;
    }

    public final String getUser_credit() {
        return this.user_credit;
    }

    public final int getLottery_reward_type() {
        return this.lottery_reward_type;
    }

    public final int getLottery_status() {
        return this.lottery_status;
    }

    public final int getLottery_type() {
        return this.lottery_type;
    }

    public final int getLottery_use_type() {
        return this.lottery_use_type;
    }

    public final Prize getPrize_module() {
        return this.prize_module;
    }

    public final List<GameToolBean> getBottom_module() {
        return this.bottom_module;
    }

    public final String getRule() {
        return this.rule;
    }

    /* JADX INFO: compiled from: LotteryInfoBean.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Config;", "", "use", "Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Use;", "(Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Use;)V", "getUse", "()Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Use;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Config {
        public static final int $stable = 0;
        private final Use use;

        public static /* synthetic */ Config copy$default(Config config, Use use, int i, Object obj) {
            if ((i & 1) != 0) {
                use = config.use;
            }
            return config.copy(use);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Use getUse() {
            return this.use;
        }

        public final Config copy(Use use) {
            Intrinsics.checkNotNullParameter(use, "use");
            return new Config(use);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Config) && Intrinsics.areEqual(this.use, ((Config) other).use);
        }

        public int hashCode() {
            return this.use.hashCode();
        }

        public String toString() {
            return "Config(use=" + this.use + ")";
        }

        public Config(Use use) {
            Intrinsics.checkNotNullParameter(use, "use");
            this.use = use;
        }

        public final Use getUse() {
            return this.use;
        }
    }

    /* JADX INFO: compiled from: LotteryInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Use;", "", "one", "", "ten", "(Ljava/lang/String;Ljava/lang/String;)V", "getOne", "()Ljava/lang/String;", "getTen", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Use {
        public static final int $stable = 0;
        private final String one;
        private final String ten;

        public static /* synthetic */ Use copy$default(Use use, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = use.one;
            }
            if ((i & 2) != 0) {
                str2 = use.ten;
            }
            return use.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOne() {
            return this.one;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTen() {
            return this.ten;
        }

        public final Use copy(String one, String ten) {
            Intrinsics.checkNotNullParameter(one, "one");
            Intrinsics.checkNotNullParameter(ten, "ten");
            return new Use(one, ten);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Use)) {
                return false;
            }
            Use use = (Use) other;
            return Intrinsics.areEqual(this.one, use.one) && Intrinsics.areEqual(this.ten, use.ten);
        }

        public int hashCode() {
            return (this.one.hashCode() * 31) + this.ten.hashCode();
        }

        public String toString() {
            return "Use(one=" + this.one + ", ten=" + this.ten + ")";
        }

        public Use(String one, String ten) {
            Intrinsics.checkNotNullParameter(one, "one");
            Intrinsics.checkNotNullParameter(ten, "ten");
            this.one = one;
            this.ten = ten;
        }

        public final String getOne() {
            return this.one;
        }

        public final String getTen() {
            return this.ten;
        }
    }

    /* JADX INFO: compiled from: LotteryInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010 \u001a\u00020\u0003J\u0006\u0010!\u001a\u00020\u001eJ\t\u0010\"\u001a\u00020\u0007HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LotteryInfoBean$Prize;", "", "title", "", SocialConstants.PARAM_APP_DESC, "coninType", "total", "", "target", "status", "yhqId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIII)V", "getConinType", "()Ljava/lang/String;", "getDesc", "getStatus", "()I", "getTarget", "getTitle", "getTotal", "getYhqId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "getBtnStr", "getEnable", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Prize {
        public static final int $stable = 0;
        private final String coninType;
        private final String desc;
        private final int status;
        private final int target;
        private final String title;
        private final int total;
        private final int yhqId;

        public static /* synthetic */ Prize copy$default(Prize prize, String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                str = prize.title;
            }
            if ((i5 & 2) != 0) {
                str2 = prize.desc;
            }
            String str4 = str2;
            if ((i5 & 4) != 0) {
                str3 = prize.coninType;
            }
            String str5 = str3;
            if ((i5 & 8) != 0) {
                i = prize.total;
            }
            int i6 = i;
            if ((i5 & 16) != 0) {
                i2 = prize.target;
            }
            int i7 = i2;
            if ((i5 & 32) != 0) {
                i3 = prize.status;
            }
            int i8 = i3;
            if ((i5 & 64) != 0) {
                i4 = prize.yhqId;
            }
            return prize.copy(str, str4, str5, i6, i7, i8, i4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getConinType() {
            return this.coninType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTarget() {
            return this.target;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getYhqId() {
            return this.yhqId;
        }

        public final Prize copy(String title, String desc, String coninType, int total, int target, int status, int yhqId) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(coninType, "coninType");
            return new Prize(title, desc, coninType, total, target, status, yhqId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Prize)) {
                return false;
            }
            Prize prize = (Prize) other;
            return Intrinsics.areEqual(this.title, prize.title) && Intrinsics.areEqual(this.desc, prize.desc) && Intrinsics.areEqual(this.coninType, prize.coninType) && this.total == prize.total && this.target == prize.target && this.status == prize.status && this.yhqId == prize.yhqId;
        }

        public int hashCode() {
            return (((((((((((this.title.hashCode() * 31) + this.desc.hashCode()) * 31) + this.coninType.hashCode()) * 31) + this.total) * 31) + this.target) * 31) + this.status) * 31) + this.yhqId;
        }

        public String toString() {
            return "Prize(title=" + this.title + ", desc=" + this.desc + ", coninType=" + this.coninType + ", total=" + this.total + ", target=" + this.target + ", status=" + this.status + ", yhqId=" + this.yhqId + ")";
        }

        public Prize(String title, String desc, String coninType, int i, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(coninType, "coninType");
            this.title = title;
            this.desc = desc;
            this.coninType = coninType;
            this.total = i;
            this.target = i2;
            this.status = i3;
            this.yhqId = i4;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getConinType() {
            return this.coninType;
        }

        public final int getTotal() {
            return this.total;
        }

        public final int getTarget() {
            return this.target;
        }

        public final int getStatus() {
            return this.status;
        }

        public final int getYhqId() {
            return this.yhqId;
        }

        public final boolean getEnable() {
            return this.total >= this.target && this.status != 1;
        }

        public final String getBtnStr() {
            if (this.status == 1) {
                return "已领取";
            }
            return getEnable() ? "领取" : "未达成";
        }
    }
}
