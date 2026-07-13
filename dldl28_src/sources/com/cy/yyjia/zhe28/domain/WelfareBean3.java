package com.cy.yyjia.zhe28.domain;

import com.cy.yyjia.zhe28.util.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: WelfareBean3.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0013\u0014B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\u0010\u000bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean3;", "", "user", "Lcom/cy/yyjia/zhe28/domain/WelfareBean3$User;", "vip", "Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip;", "top_module", "", "Lcom/cy/yyjia/zhe28/domain/GameToolBean;", "package_service_game_list", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Lcom/cy/yyjia/zhe28/domain/WelfareBean3$User;Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip;Ljava/util/List;Ljava/util/List;)V", "getPackage_service_game_list", "()Ljava/util/List;", "getTop_module", "getUser", "()Lcom/cy/yyjia/zhe28/domain/WelfareBean3$User;", "getVip", "()Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip;", "User", "Vip", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WelfareBean3 {
    public static final int $stable = 8;
    private final List<GameBean> package_service_game_list;
    private final List<GameToolBean> top_module;
    private final User user;
    private final Vip vip;

    public WelfareBean3(User user, Vip vip, List<GameToolBean> top_module, List<GameBean> package_service_game_list) {
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(vip, "vip");
        Intrinsics.checkNotNullParameter(top_module, "top_module");
        Intrinsics.checkNotNullParameter(package_service_game_list, "package_service_game_list");
        this.user = user;
        this.vip = vip;
        this.top_module = top_module;
        this.package_service_game_list = package_service_game_list;
    }

    public final User getUser() {
        return this.user;
    }

    public final Vip getVip() {
        return this.vip;
    }

    public final List<GameToolBean> getTop_module() {
        return this.top_module;
    }

    public final List<GameBean> getPackage_service_game_list() {
        return this.package_service_game_list;
    }

    /* JADX INFO: compiled from: WelfareBean3.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u001b\u001a\u00020\tJ\u0006\u0010\u001c\u001a\u00020\tJ\u0006\u0010\u001d\u001a\u00020\u0003J\t\u0010\u001e\u001a\u00020\tHÖ\u0001J\u0006\u0010\u001f\u001a\u00020\u0019J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean3$User;", "", "welfare", "", "allExperience", "", "minExperience", "maxExperience", "isNewUser", "", "(Ljava/lang/String;DDDI)V", "getAllExperience", "()D", "()I", "getMaxExperience", "getMinExperience", "getWelfare", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "getMax", "getProgress", "getText", "hashCode", "showLogo", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class User {
        public static final int $stable = 0;
        private final double allExperience;
        private final int isNewUser;
        private final double maxExperience;
        private final double minExperience;
        private final String welfare;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getWelfare() {
            return this.welfare;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final double getAllExperience() {
            return this.allExperience;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final double getMinExperience() {
            return this.minExperience;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final double getMaxExperience() {
            return this.maxExperience;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getIsNewUser() {
            return this.isNewUser;
        }

        public final User copy(String welfare, double allExperience, double minExperience, double maxExperience, int isNewUser) {
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            return new User(welfare, allExperience, minExperience, maxExperience, isNewUser);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof User)) {
                return false;
            }
            User user = (User) other;
            return Intrinsics.areEqual(this.welfare, user.welfare) && Double.compare(this.allExperience, user.allExperience) == 0 && Double.compare(this.minExperience, user.minExperience) == 0 && Double.compare(this.maxExperience, user.maxExperience) == 0 && this.isNewUser == user.isNewUser;
        }

        public int hashCode() {
            return (((((((this.welfare.hashCode() * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.allExperience)) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.minExperience)) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.maxExperience)) * 31) + this.isNewUser;
        }

        public String toString() {
            return "User(welfare=" + this.welfare + ", allExperience=" + this.allExperience + ", minExperience=" + this.minExperience + ", maxExperience=" + this.maxExperience + ", isNewUser=" + this.isNewUser + ")";
        }

        public User(String welfare, double d, double d2, double d3, int i) {
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            this.welfare = welfare;
            this.allExperience = d;
            this.minExperience = d2;
            this.maxExperience = d3;
            this.isNewUser = i;
        }

        public final String getWelfare() {
            return this.welfare;
        }

        public final double getAllExperience() {
            return this.allExperience;
        }

        public final double getMinExperience() {
            return this.minExperience;
        }

        public final double getMaxExperience() {
            return this.maxExperience;
        }

        public final int isNewUser() {
            return this.isNewUser;
        }

        public final boolean showLogo() {
            return Constant.INSTANCE.getLogged() && this.isNewUser == 1;
        }

        public final int getMax() {
            return MathKt.roundToInt(this.maxExperience - this.minExperience);
        }

        public final int getProgress() {
            return MathKt.roundToInt(this.allExperience - this.minExperience);
        }

        public final String getText() {
            int max = getMax() - getProgress();
            if (max < 0) {
                return "28手游感谢义父支持";
            }
            return "离升级下一等级还差" + max + "成长值";
        }
    }

    /* JADX INFO: compiled from: WelfareBean3.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip;", "", "detail", "Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip$Detail;", "(Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip$Detail;)V", "getDetail", "()Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip$Detail;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Detail", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Vip {
        public static final int $stable = 0;
        private final Detail detail;

        public static /* synthetic */ Vip copy$default(Vip vip, Detail detail, int i, Object obj) {
            if ((i & 1) != 0) {
                detail = vip.detail;
            }
            return vip.copy(detail);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Detail getDetail() {
            return this.detail;
        }

        public final Vip copy(Detail detail) {
            Intrinsics.checkNotNullParameter(detail, "detail");
            return new Vip(detail);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Vip) && Intrinsics.areEqual(this.detail, ((Vip) other).detail);
        }

        public int hashCode() {
            return this.detail.hashCode();
        }

        public String toString() {
            return "Vip(detail=" + this.detail + ")";
        }

        /* JADX INFO: compiled from: WelfareBean3.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean3$Vip$Detail;", "", "pic", "", "(Ljava/lang/String;)V", "getPic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Detail {
            public static final int $stable = 0;
            private final String pic;

            public static /* synthetic */ Detail copy$default(Detail detail, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = detail.pic;
                }
                return detail.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getPic() {
                return this.pic;
            }

            public final Detail copy(String pic) {
                Intrinsics.checkNotNullParameter(pic, "pic");
                return new Detail(pic);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Detail) && Intrinsics.areEqual(this.pic, ((Detail) other).pic);
            }

            public int hashCode() {
                return this.pic.hashCode();
            }

            public String toString() {
                return "Detail(pic=" + this.pic + ")";
            }

            public Detail(String pic) {
                Intrinsics.checkNotNullParameter(pic, "pic");
                this.pic = pic;
            }

            public final String getPic() {
                return this.pic;
            }
        }

        public Vip(Detail detail) {
            Intrinsics.checkNotNullParameter(detail, "detail");
            this.detail = detail;
        }

        public final Detail getDetail() {
            return this.detail;
        }
    }
}
