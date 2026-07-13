package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameDetailChatBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameDetailChatBean;", "", "avatar", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "id", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getAvatar", "()Ljava/lang/String;", "getContent", "getId", "()I", "left", "", "getLeft", "()Z", "setLeft", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GameDetailChatBean {
    public static final int $stable = 8;
    private final String avatar;
    private final String content;
    private final int id;
    private boolean left;

    public static /* synthetic */ GameDetailChatBean copy$default(GameDetailChatBean gameDetailChatBean, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gameDetailChatBean.avatar;
        }
        if ((i2 & 2) != 0) {
            str2 = gameDetailChatBean.content;
        }
        if ((i2 & 4) != 0) {
            i = gameDetailChatBean.id;
        }
        return gameDetailChatBean.copy(str, str2, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAvatar() {
        return this.avatar;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final GameDetailChatBean copy(String avatar, String content, int id) {
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        Intrinsics.checkNotNullParameter(content, "content");
        return new GameDetailChatBean(avatar, content, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameDetailChatBean)) {
            return false;
        }
        GameDetailChatBean gameDetailChatBean = (GameDetailChatBean) other;
        return Intrinsics.areEqual(this.avatar, gameDetailChatBean.avatar) && Intrinsics.areEqual(this.content, gameDetailChatBean.content) && this.id == gameDetailChatBean.id;
    }

    public int hashCode() {
        return (((this.avatar.hashCode() * 31) + this.content.hashCode()) * 31) + this.id;
    }

    public String toString() {
        return "GameDetailChatBean(avatar=" + this.avatar + ", content=" + this.content + ", id=" + this.id + ")";
    }

    public GameDetailChatBean(String avatar, String content, int i) {
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        Intrinsics.checkNotNullParameter(content, "content");
        this.avatar = avatar;
        this.content = content;
        this.id = i;
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getId() {
        return this.id;
    }

    public final boolean getLeft() {
        return this.left;
    }

    public final void setLeft(boolean z) {
        this.left = z;
    }
}
