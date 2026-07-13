package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/MessageBean;", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "dateline", "id", "", "isRead", "", "title", "(Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getDateline", "getId", "()I", "()Z", "setRead", "(Z)V", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class MessageBean {
    public static final int $stable = 8;
    private final String content;
    private final String dateline;
    private final int id;
    private boolean isRead;
    private final String title;

    public static /* synthetic */ MessageBean copy$default(MessageBean messageBean, String str, String str2, int i, boolean z, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = messageBean.content;
        }
        if ((i2 & 2) != 0) {
            str2 = messageBean.dateline;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            i = messageBean.id;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z = messageBean.isRead;
        }
        boolean z2 = z;
        if ((i2 & 16) != 0) {
            str3 = messageBean.title;
        }
        return messageBean.copy(str, str4, i3, z2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsRead() {
        return this.isRead;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final MessageBean copy(String content, String dateline, int id, boolean isRead, String title) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(title, "title");
        return new MessageBean(content, dateline, id, isRead, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessageBean)) {
            return false;
        }
        MessageBean messageBean = (MessageBean) other;
        return Intrinsics.areEqual(this.content, messageBean.content) && Intrinsics.areEqual(this.dateline, messageBean.dateline) && this.id == messageBean.id && this.isRead == messageBean.isRead && Intrinsics.areEqual(this.title, messageBean.title);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public int hashCode() {
        int iHashCode = ((((this.content.hashCode() * 31) + this.dateline.hashCode()) * 31) + this.id) * 31;
        boolean z = this.isRead;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((iHashCode + r1) * 31) + this.title.hashCode();
    }

    public String toString() {
        return "MessageBean(content=" + this.content + ", dateline=" + this.dateline + ", id=" + this.id + ", isRead=" + this.isRead + ", title=" + this.title + ")";
    }

    public MessageBean(String content, String dateline, int i, boolean z, String title) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(title, "title");
        this.content = content;
        this.dateline = dateline;
        this.id = i;
        this.isRead = z;
        this.title = title;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getId() {
        return this.id;
    }

    public final boolean isRead() {
        return this.isRead;
    }

    public final void setRead(boolean z) {
        this.isRead = z;
    }

    public final String getTitle() {
        return this.title;
    }
}
