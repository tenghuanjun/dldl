package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FudaiIndexBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0002'(BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J_\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006)"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean;", "", SocialConstants.PARAM_APP_DESC, "", "isAdd", "", "isfull", "list", "", "Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$List;", "num", "rule", "maxPrizeMsg", "total", "(Ljava/lang/String;IILjava/util/List;ILjava/lang/String;Ljava/lang/String;I)V", "getDesc", "()Ljava/lang/String;", "()I", "getIsfull", "getList", "()Ljava/util/List;", "getMaxPrizeMsg", "getNum", "getRule", "getTotal", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "List", "User", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class FudaiIndexBean {
    public static final int $stable = 8;
    private final String desc;
    private final int isAdd;
    private final int isfull;
    private final java.util.List<List> list;
    private final String maxPrizeMsg;
    private final int num;
    private final String rule;
    private final int total;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getIsAdd() {
        return this.isAdd;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getIsfull() {
        return this.isfull;
    }

    public final java.util.List<List> component4() {
        return this.list;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getNum() {
        return this.num;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getRule() {
        return this.rule;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMaxPrizeMsg() {
        return this.maxPrizeMsg;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getTotal() {
        return this.total;
    }

    public final FudaiIndexBean copy(String desc, int isAdd, int isfull, java.util.List<List> list, int num, String rule, String maxPrizeMsg, int total) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(maxPrizeMsg, "maxPrizeMsg");
        return new FudaiIndexBean(desc, isAdd, isfull, list, num, rule, maxPrizeMsg, total);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FudaiIndexBean)) {
            return false;
        }
        FudaiIndexBean fudaiIndexBean = (FudaiIndexBean) other;
        return Intrinsics.areEqual(this.desc, fudaiIndexBean.desc) && this.isAdd == fudaiIndexBean.isAdd && this.isfull == fudaiIndexBean.isfull && Intrinsics.areEqual(this.list, fudaiIndexBean.list) && this.num == fudaiIndexBean.num && Intrinsics.areEqual(this.rule, fudaiIndexBean.rule) && Intrinsics.areEqual(this.maxPrizeMsg, fudaiIndexBean.maxPrizeMsg) && this.total == fudaiIndexBean.total;
    }

    public int hashCode() {
        return (((((((((((((this.desc.hashCode() * 31) + this.isAdd) * 31) + this.isfull) * 31) + this.list.hashCode()) * 31) + this.num) * 31) + this.rule.hashCode()) * 31) + this.maxPrizeMsg.hashCode()) * 31) + this.total;
    }

    public String toString() {
        return "FudaiIndexBean(desc=" + this.desc + ", isAdd=" + this.isAdd + ", isfull=" + this.isfull + ", list=" + this.list + ", num=" + this.num + ", rule=" + this.rule + ", maxPrizeMsg=" + this.maxPrizeMsg + ", total=" + this.total + ")";
    }

    public FudaiIndexBean(String desc, int i, int i2, java.util.List<List> list, int i3, String rule, String maxPrizeMsg, int i4) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(maxPrizeMsg, "maxPrizeMsg");
        this.desc = desc;
        this.isAdd = i;
        this.isfull = i2;
        this.list = list;
        this.num = i3;
        this.rule = rule;
        this.maxPrizeMsg = maxPrizeMsg;
        this.total = i4;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int isAdd() {
        return this.isAdd;
    }

    public final int getIsfull() {
        return this.isfull;
    }

    public final java.util.List<List> getList() {
        return this.list;
    }

    public final int getNum() {
        return this.num;
    }

    public final String getRule() {
        return this.rule;
    }

    public final String getMaxPrizeMsg() {
        return this.maxPrizeMsg;
    }

    public final int getTotal() {
        return this.total;
    }

    /* JADX INFO: compiled from: FudaiIndexBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\bHÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$List;", "", "createtime", "", "day", "dayHour", "money", "uid", "", "user", "Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;)V", "getCreatetime", "()Ljava/lang/String;", "getDay", "getDayHour", "getMoney", "getUid", "()I", "getUser", "()Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;", "setUser", "(Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class List {
        public static final int $stable = 8;
        private final String createtime;
        private final String day;
        private final String dayHour;
        private final String money;
        private final int uid;
        private User user;

        public static /* synthetic */ List copy$default(List list, String str, String str2, String str3, String str4, int i, User user, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = list.createtime;
            }
            if ((i2 & 2) != 0) {
                str2 = list.day;
            }
            String str5 = str2;
            if ((i2 & 4) != 0) {
                str3 = list.dayHour;
            }
            String str6 = str3;
            if ((i2 & 8) != 0) {
                str4 = list.money;
            }
            String str7 = str4;
            if ((i2 & 16) != 0) {
                i = list.uid;
            }
            int i3 = i;
            if ((i2 & 32) != 0) {
                user = list.user;
            }
            return list.copy(str, str5, str6, str7, i3, user);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCreatetime() {
            return this.createtime;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDay() {
            return this.day;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDayHour() {
            return this.dayHour;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getMoney() {
            return this.money;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final User getUser() {
            return this.user;
        }

        public final List copy(String createtime, String day, String dayHour, String money, int uid, User user) {
            Intrinsics.checkNotNullParameter(createtime, "createtime");
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(dayHour, "dayHour");
            Intrinsics.checkNotNullParameter(money, "money");
            Intrinsics.checkNotNullParameter(user, "user");
            return new List(createtime, day, dayHour, money, uid, user);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof List)) {
                return false;
            }
            List list = (List) other;
            return Intrinsics.areEqual(this.createtime, list.createtime) && Intrinsics.areEqual(this.day, list.day) && Intrinsics.areEqual(this.dayHour, list.dayHour) && Intrinsics.areEqual(this.money, list.money) && this.uid == list.uid && Intrinsics.areEqual(this.user, list.user);
        }

        public int hashCode() {
            return (((((((((this.createtime.hashCode() * 31) + this.day.hashCode()) * 31) + this.dayHour.hashCode()) * 31) + this.money.hashCode()) * 31) + this.uid) * 31) + this.user.hashCode();
        }

        public String toString() {
            return "List(createtime=" + this.createtime + ", day=" + this.day + ", dayHour=" + this.dayHour + ", money=" + this.money + ", uid=" + this.uid + ", user=" + this.user + ")";
        }

        public List(String createtime, String day, String dayHour, String money, int i, User user) {
            Intrinsics.checkNotNullParameter(createtime, "createtime");
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(dayHour, "dayHour");
            Intrinsics.checkNotNullParameter(money, "money");
            Intrinsics.checkNotNullParameter(user, "user");
            this.createtime = createtime;
            this.day = day;
            this.dayHour = dayHour;
            this.money = money;
            this.uid = i;
            this.user = user;
        }

        public final String getCreatetime() {
            return this.createtime;
        }

        public final String getDay() {
            return this.day;
        }

        public final String getDayHour() {
            return this.dayHour;
        }

        public final String getMoney() {
            return this.money;
        }

        public final int getUid() {
            return this.uid;
        }

        public final User getUser() {
            return this.user;
        }

        public final void setUser(User user) {
            Intrinsics.checkNotNullParameter(user, "<set-?>");
            this.user = user;
        }
    }

    /* JADX INFO: compiled from: FudaiIndexBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;", "", "avatar", "", "uid", "", "userName", "(Ljava/lang/String;ILjava/lang/String;)V", "getAvatar", "()Ljava/lang/String;", "getUid", "()I", "getUserName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class User {
        public static final int $stable = 0;
        private final String avatar;
        private final int uid;
        private final String userName;

        public static /* synthetic */ User copy$default(User user, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = user.avatar;
            }
            if ((i2 & 2) != 0) {
                i = user.uid;
            }
            if ((i2 & 4) != 0) {
                str2 = user.userName;
            }
            return user.copy(str, i, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAvatar() {
            return this.avatar;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUserName() {
            return this.userName;
        }

        public final User copy(String avatar, int uid, String userName) {
            Intrinsics.checkNotNullParameter(avatar, "avatar");
            Intrinsics.checkNotNullParameter(userName, "userName");
            return new User(avatar, uid, userName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof User)) {
                return false;
            }
            User user = (User) other;
            return Intrinsics.areEqual(this.avatar, user.avatar) && this.uid == user.uid && Intrinsics.areEqual(this.userName, user.userName);
        }

        public int hashCode() {
            return (((this.avatar.hashCode() * 31) + this.uid) * 31) + this.userName.hashCode();
        }

        public String toString() {
            return "User(avatar=" + this.avatar + ", uid=" + this.uid + ", userName=" + this.userName + ")";
        }

        public User(String avatar, int i, String userName) {
            Intrinsics.checkNotNullParameter(avatar, "avatar");
            Intrinsics.checkNotNullParameter(userName, "userName");
            this.avatar = avatar;
            this.uid = i;
            this.userName = userName;
        }

        public final String getAvatar() {
            return this.avatar;
        }

        public final int getUid() {
            return this.uid;
        }

        public final String getUserName() {
            return this.userName;
        }
    }
}
