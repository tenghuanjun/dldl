package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SearchHistory;", "", "text", "", "time", "", "(Ljava/lang/String;J)V", "getText", "()Ljava/lang/String;", "getTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class SearchHistory {
    public static final int $stable = 0;
    private final String text;
    private final long time;

    public static /* synthetic */ SearchHistory copy$default(SearchHistory searchHistory, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchHistory.text;
        }
        if ((i & 2) != 0) {
            j = searchHistory.time;
        }
        return searchHistory.copy(str, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    public final SearchHistory copy(String text, long time) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new SearchHistory(text, time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHistory)) {
            return false;
        }
        SearchHistory searchHistory = (SearchHistory) other;
        return Intrinsics.areEqual(this.text, searchHistory.text) && this.time == searchHistory.time;
    }

    public int hashCode() {
        return (this.text.hashCode() * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.time);
    }

    public String toString() {
        return "SearchHistory(text=" + this.text + ", time=" + this.time + ")";
    }

    public SearchHistory(String text, long j) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.time = j;
    }

    public final String getText() {
        return this.text;
    }

    public final long getTime() {
        return this.time;
    }
}
