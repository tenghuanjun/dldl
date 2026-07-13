package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsEditFastBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001fB3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsEditFastBean;", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "Lcom/cy/yyjia/zhe28/domain/BbsEditFastBean$Content;", "createtime", "", "icon", "id", "", "name", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getContent", "()Ljava/util/List;", "getCreatetime", "()Ljava/lang/String;", "getIcon", "getId", "()I", "getName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Content", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BbsEditFastBean {
    public static final int $stable = 8;
    private final List<Content> content;
    private final String createtime;
    private final String icon;
    private final int id;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BbsEditFastBean copy$default(BbsEditFastBean bbsEditFastBean, List list, String str, String str2, int i, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = bbsEditFastBean.content;
        }
        if ((i2 & 2) != 0) {
            str = bbsEditFastBean.createtime;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            str2 = bbsEditFastBean.icon;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            i = bbsEditFastBean.id;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str3 = bbsEditFastBean.name;
        }
        return bbsEditFastBean.copy(list, str4, str5, i3, str3);
    }

    public final List<Content> component1() {
        return this.content;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCreatetime() {
        return this.createtime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final BbsEditFastBean copy(List<Content> content, String createtime, String icon, int id, String name) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(createtime, "createtime");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        return new BbsEditFastBean(content, createtime, icon, id, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BbsEditFastBean)) {
            return false;
        }
        BbsEditFastBean bbsEditFastBean = (BbsEditFastBean) other;
        return Intrinsics.areEqual(this.content, bbsEditFastBean.content) && Intrinsics.areEqual(this.createtime, bbsEditFastBean.createtime) && Intrinsics.areEqual(this.icon, bbsEditFastBean.icon) && this.id == bbsEditFastBean.id && Intrinsics.areEqual(this.name, bbsEditFastBean.name);
    }

    public int hashCode() {
        return (((((((this.content.hashCode() * 31) + this.createtime.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.id) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "BbsEditFastBean(content=" + this.content + ", createtime=" + this.createtime + ", icon=" + this.icon + ", id=" + this.id + ", name=" + this.name + ")";
    }

    public BbsEditFastBean(List<Content> content, String createtime, String icon, int i, String name) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(createtime, "createtime");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        this.content = content;
        this.createtime = createtime;
        this.icon = icon;
        this.id = i;
        this.name = name;
    }

    public final List<Content> getContent() {
        return this.content;
    }

    public final String getCreatetime() {
        return this.createtime;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: compiled from: BbsEditFastBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsEditFastBean$Content;", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "createtime", "id", "", "tag_id", "(Ljava/lang/String;Ljava/lang/String;II)V", "getContent", "()Ljava/lang/String;", "getCreatetime", "getId", "()I", "getTag_id", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Content {
        public static final int $stable = 0;
        private final String content;
        private final String createtime;
        private final int id;
        private final int tag_id;

        public static /* synthetic */ Content copy$default(Content content, String str, String str2, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = content.content;
            }
            if ((i3 & 2) != 0) {
                str2 = content.createtime;
            }
            if ((i3 & 4) != 0) {
                i = content.id;
            }
            if ((i3 & 8) != 0) {
                i2 = content.tag_id;
            }
            return content.copy(str, str2, i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCreatetime() {
            return this.createtime;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getTag_id() {
            return this.tag_id;
        }

        public final Content copy(String content, String createtime, int id, int tag_id) {
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(createtime, "createtime");
            return new Content(content, createtime, id, tag_id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Content)) {
                return false;
            }
            Content content = (Content) other;
            return Intrinsics.areEqual(this.content, content.content) && Intrinsics.areEqual(this.createtime, content.createtime) && this.id == content.id && this.tag_id == content.tag_id;
        }

        public int hashCode() {
            return (((((this.content.hashCode() * 31) + this.createtime.hashCode()) * 31) + this.id) * 31) + this.tag_id;
        }

        public String toString() {
            return "Content(content=" + this.content + ", createtime=" + this.createtime + ", id=" + this.id + ", tag_id=" + this.tag_id + ")";
        }

        public Content(String content, String createtime, int i, int i2) {
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(createtime, "createtime");
            this.content = content;
            this.createtime = createtime;
            this.id = i;
            this.tag_id = i2;
        }

        public final String getContent() {
            return this.content;
        }

        public final String getCreatetime() {
            return this.createtime;
        }

        public final int getId() {
            return this.id;
        }

        public final int getTag_id() {
            return this.tag_id;
        }
    }
}
