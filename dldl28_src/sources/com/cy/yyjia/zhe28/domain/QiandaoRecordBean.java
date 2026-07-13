package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QiandaoRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0018B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoRecordBean;", "", "allWelfare", "", "list", "Lcom/cy/yyjia/zhe28/domain/PageBean;", "Lcom/cy/yyjia/zhe28/domain/QiandaoRecordBean$Data;", "signAllDays", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/PageBean;Ljava/lang/String;)V", "getAllWelfare", "()Ljava/lang/String;", "getList", "()Lcom/cy/yyjia/zhe28/domain/PageBean;", "getSignAllDays", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Data", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class QiandaoRecordBean {
    public static final int $stable = 8;
    private final String allWelfare;
    private final PageBean<Data> list;
    private final String signAllDays;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ QiandaoRecordBean copy$default(QiandaoRecordBean qiandaoRecordBean, String str, PageBean pageBean, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = qiandaoRecordBean.allWelfare;
        }
        if ((i & 2) != 0) {
            pageBean = qiandaoRecordBean.list;
        }
        if ((i & 4) != 0) {
            str2 = qiandaoRecordBean.signAllDays;
        }
        return qiandaoRecordBean.copy(str, pageBean, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAllWelfare() {
        return this.allWelfare;
    }

    public final PageBean<Data> component2() {
        return this.list;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSignAllDays() {
        return this.signAllDays;
    }

    public final QiandaoRecordBean copy(String allWelfare, PageBean<Data> list, String signAllDays) {
        Intrinsics.checkNotNullParameter(allWelfare, "allWelfare");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(signAllDays, "signAllDays");
        return new QiandaoRecordBean(allWelfare, list, signAllDays);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QiandaoRecordBean)) {
            return false;
        }
        QiandaoRecordBean qiandaoRecordBean = (QiandaoRecordBean) other;
        return Intrinsics.areEqual(this.allWelfare, qiandaoRecordBean.allWelfare) && Intrinsics.areEqual(this.list, qiandaoRecordBean.list) && Intrinsics.areEqual(this.signAllDays, qiandaoRecordBean.signAllDays);
    }

    public int hashCode() {
        return (((this.allWelfare.hashCode() * 31) + this.list.hashCode()) * 31) + this.signAllDays.hashCode();
    }

    public String toString() {
        return "QiandaoRecordBean(allWelfare=" + this.allWelfare + ", list=" + this.list + ", signAllDays=" + this.signAllDays + ")";
    }

    public QiandaoRecordBean(String allWelfare, PageBean<Data> list, String signAllDays) {
        Intrinsics.checkNotNullParameter(allWelfare, "allWelfare");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(signAllDays, "signAllDays");
        this.allWelfare = allWelfare;
        this.list = list;
        this.signAllDays = signAllDays;
    }

    public final String getAllWelfare() {
        return this.allWelfare;
    }

    public final PageBean<Data> getList() {
        return this.list;
    }

    public final String getSignAllDays() {
        return this.signAllDays;
    }

    /* JADX INFO: compiled from: QiandaoRecordBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/QiandaoRecordBean$Data;", "", "createTime", "", "id", "", "signDays", "welfare", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getCreateTime", "()Ljava/lang/String;", "getId", "()I", "getSignDays", "getWelfare", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Data {
        public static final int $stable = 0;
        private final String createTime;
        private final int id;
        private final String signDays;
        private final String welfare;

        public static /* synthetic */ Data copy$default(Data data, String str, int i, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = data.createTime;
            }
            if ((i2 & 2) != 0) {
                i = data.id;
            }
            if ((i2 & 4) != 0) {
                str2 = data.signDays;
            }
            if ((i2 & 8) != 0) {
                str3 = data.welfare;
            }
            return data.copy(str, i, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCreateTime() {
            return this.createTime;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSignDays() {
            return this.signDays;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getWelfare() {
            return this.welfare;
        }

        public final Data copy(String createTime, int id, String signDays, String welfare) {
            Intrinsics.checkNotNullParameter(createTime, "createTime");
            Intrinsics.checkNotNullParameter(signDays, "signDays");
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            return new Data(createTime, id, signDays, welfare);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.createTime, data.createTime) && this.id == data.id && Intrinsics.areEqual(this.signDays, data.signDays) && Intrinsics.areEqual(this.welfare, data.welfare);
        }

        public int hashCode() {
            return (((((this.createTime.hashCode() * 31) + this.id) * 31) + this.signDays.hashCode()) * 31) + this.welfare.hashCode();
        }

        public String toString() {
            return "Data(createTime=" + this.createTime + ", id=" + this.id + ", signDays=" + this.signDays + ", welfare=" + this.welfare + ")";
        }

        public Data(String createTime, int i, String signDays, String welfare) {
            Intrinsics.checkNotNullParameter(createTime, "createTime");
            Intrinsics.checkNotNullParameter(signDays, "signDays");
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            this.createTime = createTime;
            this.id = i;
            this.signDays = signDays;
            this.welfare = welfare;
        }

        public final String getCreateTime() {
            return this.createTime;
        }

        public final int getId() {
            return this.id;
        }

        public final String getSignDays() {
            return this.signDays;
        }

        public final String getWelfare() {
            return this.welfare;
        }
    }
}
