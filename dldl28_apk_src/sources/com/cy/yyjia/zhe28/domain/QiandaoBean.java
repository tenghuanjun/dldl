package com.cy.yyjia.zhe28.domain;

import com.google.gson.annotations.SerializedName;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QiandaoBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0004\u0019\u001a\u001b\u001cB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoBean;", "", "configsigns", "Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigsignsBean;", "config", "Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigBean;", "list", "Lcom/cy/yyjia/zhe28/domain/TaskResult;", "awardYhq", "Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;", "taskDesc", "", SocialConstants.PARAM_APP_DESC, "(Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigsignsBean;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigBean;Lcom/cy/yyjia/zhe28/domain/TaskResult;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;Ljava/lang/String;Ljava/lang/String;)V", "getAwardYhq", "()Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigBean;", "getConfigsigns", "()Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigsignsBean;", "getDesc", "()Ljava/lang/String;", "getList", "()Lcom/cy/yyjia/zhe28/domain/TaskResult;", "getTaskDesc", "ConfigBean", "ConfigsignsBean", "Day", "YhqBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class QiandaoBean {
    public static final int $stable = 8;
    private final YhqBean awardYhq;
    private final ConfigBean config;
    private final ConfigsignsBean configsigns;
    private final String desc;
    private final TaskResult list;
    private final String taskDesc;

    public QiandaoBean(ConfigsignsBean configsigns, ConfigBean config, TaskResult list, YhqBean awardYhq, String taskDesc, String desc) {
        Intrinsics.checkNotNullParameter(configsigns, "configsigns");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(awardYhq, "awardYhq");
        Intrinsics.checkNotNullParameter(taskDesc, "taskDesc");
        Intrinsics.checkNotNullParameter(desc, "desc");
        this.configsigns = configsigns;
        this.config = config;
        this.list = list;
        this.awardYhq = awardYhq;
        this.taskDesc = taskDesc;
        this.desc = desc;
    }

    public final ConfigsignsBean getConfigsigns() {
        return this.configsigns;
    }

    public final ConfigBean getConfig() {
        return this.config;
    }

    public final TaskResult getList() {
        return this.list;
    }

    public final YhqBean getAwardYhq() {
        return this.awardYhq;
    }

    public final String getTaskDesc() {
        return this.taskDesc;
    }

    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: compiled from: QiandaoBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoBean$YhqBean;", "", "yhqId", "", SocialConstants.PARAM_IMG_URL, "", "isReceive", "(ILjava/lang/String;I)V", "getImg", "()Ljava/lang/String;", "()I", "getYhqId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class YhqBean {
        public static final int $stable = 0;
        private final String img;
        private final int isReceive;
        private final int yhqId;

        public static /* synthetic */ YhqBean copy$default(YhqBean yhqBean, int i, String str, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = yhqBean.yhqId;
            }
            if ((i3 & 2) != 0) {
                str = yhqBean.img;
            }
            if ((i3 & 4) != 0) {
                i2 = yhqBean.isReceive;
            }
            return yhqBean.copy(i, str, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getYhqId() {
            return this.yhqId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getImg() {
            return this.img;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getIsReceive() {
            return this.isReceive;
        }

        public final YhqBean copy(int yhqId, String img, int isReceive) {
            Intrinsics.checkNotNullParameter(img, "img");
            return new YhqBean(yhqId, img, isReceive);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof YhqBean)) {
                return false;
            }
            YhqBean yhqBean = (YhqBean) other;
            return this.yhqId == yhqBean.yhqId && Intrinsics.areEqual(this.img, yhqBean.img) && this.isReceive == yhqBean.isReceive;
        }

        public int hashCode() {
            return (((this.yhqId * 31) + this.img.hashCode()) * 31) + this.isReceive;
        }

        public String toString() {
            return "YhqBean(yhqId=" + this.yhqId + ", img=" + this.img + ", isReceive=" + this.isReceive + ")";
        }

        public YhqBean(int i, String img, int i2) {
            Intrinsics.checkNotNullParameter(img, "img");
            this.yhqId = i;
            this.img = img;
            this.isReceive = i2;
        }

        public final int getYhqId() {
            return this.yhqId;
        }

        public final String getImg() {
            return this.img;
        }

        public final int isReceive() {
            return this.isReceive;
        }
    }

    /* JADX INFO: compiled from: QiandaoBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fJ\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigsignsBean;", "", "day1", "Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;", "day2", "day3", "day4", "day5", "day6", "day7", "(Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;)V", "getDay1", "()Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;", "getDay2", "getDay3", "getDay4", "getDay5", "getDay6", "getDay7", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "getList", "", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ConfigsignsBean {
        public static final int $stable = 0;
        private final Day day1;
        private final Day day2;
        private final Day day3;
        private final Day day4;
        private final Day day5;
        private final Day day6;
        private final Day day7;

        public static /* synthetic */ ConfigsignsBean copy$default(ConfigsignsBean configsignsBean, Day day, Day day2, Day day3, Day day4, Day day5, Day day6, Day day7, int i, Object obj) {
            if ((i & 1) != 0) {
                day = configsignsBean.day1;
            }
            if ((i & 2) != 0) {
                day2 = configsignsBean.day2;
            }
            Day day8 = day2;
            if ((i & 4) != 0) {
                day3 = configsignsBean.day3;
            }
            Day day9 = day3;
            if ((i & 8) != 0) {
                day4 = configsignsBean.day4;
            }
            Day day10 = day4;
            if ((i & 16) != 0) {
                day5 = configsignsBean.day5;
            }
            Day day11 = day5;
            if ((i & 32) != 0) {
                day6 = configsignsBean.day6;
            }
            Day day12 = day6;
            if ((i & 64) != 0) {
                day7 = configsignsBean.day7;
            }
            return configsignsBean.copy(day, day8, day9, day10, day11, day12, day7);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Day getDay1() {
            return this.day1;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Day getDay2() {
            return this.day2;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Day getDay3() {
            return this.day3;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Day getDay4() {
            return this.day4;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Day getDay5() {
            return this.day5;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Day getDay6() {
            return this.day6;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Day getDay7() {
            return this.day7;
        }

        public final ConfigsignsBean copy(Day day1, Day day2, Day day3, Day day4, Day day5, Day day6, Day day7) {
            Intrinsics.checkNotNullParameter(day1, "day1");
            Intrinsics.checkNotNullParameter(day2, "day2");
            Intrinsics.checkNotNullParameter(day3, "day3");
            Intrinsics.checkNotNullParameter(day4, "day4");
            Intrinsics.checkNotNullParameter(day5, "day5");
            Intrinsics.checkNotNullParameter(day6, "day6");
            Intrinsics.checkNotNullParameter(day7, "day7");
            return new ConfigsignsBean(day1, day2, day3, day4, day5, day6, day7);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConfigsignsBean)) {
                return false;
            }
            ConfigsignsBean configsignsBean = (ConfigsignsBean) other;
            return Intrinsics.areEqual(this.day1, configsignsBean.day1) && Intrinsics.areEqual(this.day2, configsignsBean.day2) && Intrinsics.areEqual(this.day3, configsignsBean.day3) && Intrinsics.areEqual(this.day4, configsignsBean.day4) && Intrinsics.areEqual(this.day5, configsignsBean.day5) && Intrinsics.areEqual(this.day6, configsignsBean.day6) && Intrinsics.areEqual(this.day7, configsignsBean.day7);
        }

        public int hashCode() {
            return (((((((((((this.day1.hashCode() * 31) + this.day2.hashCode()) * 31) + this.day3.hashCode()) * 31) + this.day4.hashCode()) * 31) + this.day5.hashCode()) * 31) + this.day6.hashCode()) * 31) + this.day7.hashCode();
        }

        public String toString() {
            return "ConfigsignsBean(day1=" + this.day1 + ", day2=" + this.day2 + ", day3=" + this.day3 + ", day4=" + this.day4 + ", day5=" + this.day5 + ", day6=" + this.day6 + ", day7=" + this.day7 + ")";
        }

        public ConfigsignsBean(Day day1, Day day2, Day day3, Day day4, Day day5, Day day6, Day day7) {
            Intrinsics.checkNotNullParameter(day1, "day1");
            Intrinsics.checkNotNullParameter(day2, "day2");
            Intrinsics.checkNotNullParameter(day3, "day3");
            Intrinsics.checkNotNullParameter(day4, "day4");
            Intrinsics.checkNotNullParameter(day5, "day5");
            Intrinsics.checkNotNullParameter(day6, "day6");
            Intrinsics.checkNotNullParameter(day7, "day7");
            this.day1 = day1;
            this.day2 = day2;
            this.day3 = day3;
            this.day4 = day4;
            this.day5 = day5;
            this.day6 = day6;
            this.day7 = day7;
        }

        public final Day getDay1() {
            return this.day1;
        }

        public final Day getDay2() {
            return this.day2;
        }

        public final Day getDay3() {
            return this.day3;
        }

        public final Day getDay4() {
            return this.day4;
        }

        public final Day getDay5() {
            return this.day5;
        }

        public final Day getDay6() {
            return this.day6;
        }

        public final Day getDay7() {
            return this.day7;
        }

        public final List<Day> getList() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.day1);
            arrayList.add(this.day2);
            arrayList.add(this.day3);
            arrayList.add(this.day4);
            arrayList.add(this.day5);
            arrayList.add(this.day6);
            arrayList.add(this.day7);
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: QiandaoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoBean$ConfigBean;", "", "rule", "", "rule_task", "(Ljava/lang/String;Ljava/lang/String;)V", "getRule", "()Ljava/lang/String;", "getRule_task", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ConfigBean {
        public static final int $stable = 0;
        private final String rule;
        private final String rule_task;

        public static /* synthetic */ ConfigBean copy$default(ConfigBean configBean, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = configBean.rule;
            }
            if ((i & 2) != 0) {
                str2 = configBean.rule_task;
            }
            return configBean.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRule() {
            return this.rule;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRule_task() {
            return this.rule_task;
        }

        public final ConfigBean copy(String rule, String rule_task) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(rule_task, "rule_task");
            return new ConfigBean(rule, rule_task);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConfigBean)) {
                return false;
            }
            ConfigBean configBean = (ConfigBean) other;
            return Intrinsics.areEqual(this.rule, configBean.rule) && Intrinsics.areEqual(this.rule_task, configBean.rule_task);
        }

        public int hashCode() {
            return (this.rule.hashCode() * 31) + this.rule_task.hashCode();
        }

        public String toString() {
            return "ConfigBean(rule=" + this.rule + ", rule_task=" + this.rule_task + ")";
        }

        public ConfigBean(String rule, String rule_task) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(rule_task, "rule_task");
            this.rule = rule;
            this.rule_task = rule_task;
        }

        public final String getRule() {
            return this.rule;
        }

        public final String getRule_task() {
            return this.rule_task;
        }
    }

    /* JADX INFO: compiled from: QiandaoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0012\u001a\u00020\u0003J\u0006\u0010\u0013\u001a\u00020\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0006\u0010\u0016\u001a\u00020\u0010J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoBean$Day;", "", "status", "", "day", "week", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDay", "()Ljava/lang/String;", "getStatus", "getWeek", "component1", "component2", "component3", "copy", "equals", "", "other", "getText1", "getText2", "hashCode", "", "show2", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Day {
        public static final int $stable = 0;
        private final String day;

        @SerializedName("class")
        private final String status;
        private final String week;

        public static /* synthetic */ Day copy$default(Day day, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = day.status;
            }
            if ((i & 2) != 0) {
                str2 = day.day;
            }
            if ((i & 4) != 0) {
                str3 = day.week;
            }
            return day.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDay() {
            return this.day;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getWeek() {
            return this.week;
        }

        public final Day copy(String status, String day, String week) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(week, "week");
            return new Day(status, day, week);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Day)) {
                return false;
            }
            Day day = (Day) other;
            return Intrinsics.areEqual(this.status, day.status) && Intrinsics.areEqual(this.day, day.day) && Intrinsics.areEqual(this.week, day.week);
        }

        public int hashCode() {
            return (((this.status.hashCode() * 31) + this.day.hashCode()) * 31) + this.week.hashCode();
        }

        public String toString() {
            return "Day(status=" + this.status + ", day=" + this.day + ", week=" + this.week + ")";
        }

        public Day(String status, String day, String week) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(week, "week");
            this.status = status;
            this.day = day;
            this.week = week;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getDay() {
            return this.day;
        }

        public final String getWeek() {
            return this.week;
        }

        public final String getText1() {
            return this.day + "天";
        }

        public final String getText2() {
            return this.day + "积分";
        }

        public final boolean show2() {
            return Integer.parseInt(this.day) > 3;
        }
    }
}
