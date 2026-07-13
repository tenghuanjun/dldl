package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsSignBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsSignBean;", "", SocialConstants.PARAM_APP_DESC, "", "list", "", "Lcom/cy/yyjia/zhe28/domain/BbsSignBean$Day;", "scList", "Lcom/cy/yyjia/zhe28/domain/BbsSignBean$Welfare;", "title", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getList", "()Ljava/util/List;", "getScList", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Day", "Welfare", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BbsSignBean {
    public static final int $stable = 8;
    private final String desc;
    private final List<Day> list;
    private final List<Welfare> scList;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BbsSignBean copy$default(BbsSignBean bbsSignBean, String str, List list, List list2, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bbsSignBean.desc;
        }
        if ((i & 2) != 0) {
            list = bbsSignBean.list;
        }
        if ((i & 4) != 0) {
            list2 = bbsSignBean.scList;
        }
        if ((i & 8) != 0) {
            str2 = bbsSignBean.title;
        }
        return bbsSignBean.copy(str, list, list2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final List<Day> component2() {
        return this.list;
    }

    public final List<Welfare> component3() {
        return this.scList;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final BbsSignBean copy(String desc, List<Day> list, List<Welfare> scList, String title) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(scList, "scList");
        Intrinsics.checkNotNullParameter(title, "title");
        return new BbsSignBean(desc, list, scList, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BbsSignBean)) {
            return false;
        }
        BbsSignBean bbsSignBean = (BbsSignBean) other;
        return Intrinsics.areEqual(this.desc, bbsSignBean.desc) && Intrinsics.areEqual(this.list, bbsSignBean.list) && Intrinsics.areEqual(this.scList, bbsSignBean.scList) && Intrinsics.areEqual(this.title, bbsSignBean.title);
    }

    public int hashCode() {
        return (((((this.desc.hashCode() * 31) + this.list.hashCode()) * 31) + this.scList.hashCode()) * 31) + this.title.hashCode();
    }

    public String toString() {
        return "BbsSignBean(desc=" + this.desc + ", list=" + this.list + ", scList=" + this.scList + ", title=" + this.title + ")";
    }

    public BbsSignBean(String desc, List<Day> list, List<Welfare> scList, String title) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(scList, "scList");
        Intrinsics.checkNotNullParameter(title, "title");
        this.desc = desc;
        this.list = list;
        this.scList = scList;
        this.title = title;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final List<Day> getList() {
        return this.list;
    }

    public final List<Welfare> getScList() {
        return this.scList;
    }

    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: compiled from: BbsSignBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsSignBean$Day;", "", "days", "", "isIn", "", "(Ljava/lang/String;Z)V", "getDays", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Day {
        public static final int $stable = 0;
        private final String days;
        private final boolean isIn;

        public static /* synthetic */ Day copy$default(Day day, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = day.days;
            }
            if ((i & 2) != 0) {
                z = day.isIn;
            }
            return day.copy(str, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDays() {
            return this.days;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsIn() {
            return this.isIn;
        }

        public final Day copy(String days, boolean isIn) {
            Intrinsics.checkNotNullParameter(days, "days");
            return new Day(days, isIn);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Day)) {
                return false;
            }
            Day day = (Day) other;
            return Intrinsics.areEqual(this.days, day.days) && this.isIn == day.isIn;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3 */
        public int hashCode() {
            int iHashCode = this.days.hashCode() * 31;
            boolean z = this.isIn;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            return "Day(days=" + this.days + ", isIn=" + this.isIn + ")";
        }

        public Day(String days, boolean z) {
            Intrinsics.checkNotNullParameter(days, "days");
            this.days = days;
            this.isIn = z;
        }

        public final String getDays() {
            return this.days;
        }

        public final boolean isIn() {
            return this.isIn;
        }
    }

    /* JADX INFO: compiled from: BbsSignBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsSignBean$Welfare;", "", "title", "", "operation", SocialConstants.PARAM_APP_DESC, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getOperation", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Welfare {
        public static final int $stable = 0;
        private final String desc;
        private final String operation;
        private final String title;

        public static /* synthetic */ Welfare copy$default(Welfare welfare, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = welfare.title;
            }
            if ((i & 2) != 0) {
                str2 = welfare.operation;
            }
            if ((i & 4) != 0) {
                str3 = welfare.desc;
            }
            return welfare.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getOperation() {
            return this.operation;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        public final Welfare copy(String title, String operation, String desc) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(desc, "desc");
            return new Welfare(title, operation, desc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Welfare)) {
                return false;
            }
            Welfare welfare = (Welfare) other;
            return Intrinsics.areEqual(this.title, welfare.title) && Intrinsics.areEqual(this.operation, welfare.operation) && Intrinsics.areEqual(this.desc, welfare.desc);
        }

        public int hashCode() {
            return (((this.title.hashCode() * 31) + this.operation.hashCode()) * 31) + this.desc.hashCode();
        }

        public String toString() {
            return "Welfare(title=" + this.title + ", operation=" + this.operation + ", desc=" + this.desc + ")";
        }

        public Welfare(String title, String operation, String desc) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.title = title;
            this.operation = operation;
            this.desc = desc;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getOperation() {
            return this.operation;
        }

        public final String getDesc() {
            return this.desc;
        }
    }
}
