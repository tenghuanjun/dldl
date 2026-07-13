package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DownloadResult;", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "type", "(Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DownloadResult {
    public static final int $stable = 0;
    private final String content;
    private final String type;

    public static /* synthetic */ DownloadResult copy$default(DownloadResult downloadResult, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = downloadResult.content;
        }
        if ((i & 2) != 0) {
            str2 = downloadResult.type;
        }
        return downloadResult.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final DownloadResult copy(String content, String type) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(type, "type");
        return new DownloadResult(content, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadResult)) {
            return false;
        }
        DownloadResult downloadResult = (DownloadResult) other;
        return Intrinsics.areEqual(this.content, downloadResult.content) && Intrinsics.areEqual(this.type, downloadResult.type);
    }

    public int hashCode() {
        return (this.content.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "DownloadResult(content=" + this.content + ", type=" + this.type + ")";
    }

    public DownloadResult(String content, String type) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(type, "type");
        this.content = content;
        this.type = type;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getType() {
        return this.type;
    }
}
