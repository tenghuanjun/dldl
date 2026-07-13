package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.lzy.okgo.model.Progress;
import java.text.SimpleDateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleTimeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ScheduleTimeBean;", "", "day", "", "Lcom/cy/yyjia/zhe28/domain/ScheduleTimeBean$Day;", "(Ljava/util/List;)V", "getDay", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Day", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ScheduleTimeBean {
    public static final int $stable = 8;
    private final List<Day> day;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ScheduleTimeBean copy$default(ScheduleTimeBean scheduleTimeBean, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = scheduleTimeBean.day;
        }
        return scheduleTimeBean.copy(list);
    }

    public final List<Day> component1() {
        return this.day;
    }

    public final ScheduleTimeBean copy(List<Day> day) {
        Intrinsics.checkNotNullParameter(day, "day");
        return new ScheduleTimeBean(day);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ScheduleTimeBean) && Intrinsics.areEqual(this.day, ((ScheduleTimeBean) other).day);
    }

    public int hashCode() {
        return this.day.hashCode();
    }

    public String toString() {
        return "ScheduleTimeBean(day=" + this.day + ")";
    }

    public ScheduleTimeBean(List<Day> day) {
        Intrinsics.checkNotNullParameter(day, "day");
        this.day = day;
    }

    public final List<Day> getDay() {
        return this.day;
    }

    /* JADX INFO: compiled from: ScheduleTimeBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0006\u0010\u001a\u001a\u00020\u000bJ\u0006\u0010\u001b\u001a\u00020\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR&\u0010\n\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ScheduleTimeBean$Day;", "Landroidx/databinding/BaseObservable;", "day", "", Progress.DATE, "week", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDate", "()Ljava/lang/String;", "getDay", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getWeek", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "isToday", "isTomorrow", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Day extends BaseObservable {
        public static final int $stable = 8;
        private final String date;
        private final String day;
        private boolean selected;
        private final String week;

        public static /* synthetic */ Day copy$default(Day day, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = day.day;
            }
            if ((i & 2) != 0) {
                str2 = day.date;
            }
            if ((i & 4) != 0) {
                str3 = day.week;
            }
            return day.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDay() {
            return this.day;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDate() {
            return this.date;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getWeek() {
            return this.week;
        }

        public final Day copy(String day, String date, String week) {
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(week, "week");
            return new Day(day, date, week);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Day)) {
                return false;
            }
            Day day = (Day) other;
            return Intrinsics.areEqual(this.day, day.day) && Intrinsics.areEqual(this.date, day.date) && Intrinsics.areEqual(this.week, day.week);
        }

        public int hashCode() {
            return (((this.day.hashCode() * 31) + this.date.hashCode()) * 31) + this.week.hashCode();
        }

        public String toString() {
            return "Day(day=" + this.day + ", date=" + this.date + ", week=" + this.week + ")";
        }

        public final String getDay() {
            return this.day;
        }

        public final String getDate() {
            return this.date;
        }

        public final String getWeek() {
            return this.week;
        }

        public Day(String day, String date, String week) {
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(week, "week");
            this.day = day;
            this.date = date;
            this.week = week;
        }

        @Bindable
        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
            notifyPropertyChanged(94);
        }

        public final boolean isToday() {
            return Intrinsics.areEqual(new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(System.currentTimeMillis())), this.date);
        }

        public final boolean isTomorrow() {
            return Intrinsics.areEqual(new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(System.currentTimeMillis() + ((long) 86400000))), this.date);
        }
    }
}
