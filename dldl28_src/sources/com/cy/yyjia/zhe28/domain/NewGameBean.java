package com.cy.yyjia.zhe28.domain;

import com.mobile.auth.gatewayauth.Constant;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NewGameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B7\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\bHÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/NewGameBean;", "", "new_service_date", "", "(Ljava/lang/String;)V", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "id", "", CommonConstants.key_gameId, "serverName", Constant.START_TIME, "", "(Lcom/cy/yyjia/zhe28/domain/GameBean;IILjava/lang/String;JLjava/lang/String;)V", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "()I", "getId", "getNew_service_date", "()Ljava/lang/String;", "getServerName", "getStartTime", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class NewGameBean {
    public static final int $stable = 8;
    private final GameBean game;
    private final int gameId;
    private final int id;
    private final String new_service_date;
    private final String serverName;
    private final long startTime;

    public static /* synthetic */ NewGameBean copy$default(NewGameBean newGameBean, GameBean gameBean, int i, int i2, String str, long j, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            gameBean = newGameBean.game;
        }
        if ((i3 & 2) != 0) {
            i = newGameBean.id;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            i2 = newGameBean.gameId;
        }
        int i5 = i2;
        if ((i3 & 8) != 0) {
            str = newGameBean.serverName;
        }
        String str3 = str;
        if ((i3 & 16) != 0) {
            j = newGameBean.startTime;
        }
        long j2 = j;
        if ((i3 & 32) != 0) {
            str2 = newGameBean.new_service_date;
        }
        return newGameBean.copy(gameBean, i4, i5, str3, j2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getServerName() {
        return this.serverName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getNew_service_date() {
        return this.new_service_date;
    }

    public final NewGameBean copy(GameBean game, int id, int gameId, String serverName, long startTime, String new_service_date) {
        Intrinsics.checkNotNullParameter(serverName, "serverName");
        Intrinsics.checkNotNullParameter(new_service_date, "new_service_date");
        return new NewGameBean(game, id, gameId, serverName, startTime, new_service_date);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NewGameBean)) {
            return false;
        }
        NewGameBean newGameBean = (NewGameBean) other;
        return Intrinsics.areEqual(this.game, newGameBean.game) && this.id == newGameBean.id && this.gameId == newGameBean.gameId && Intrinsics.areEqual(this.serverName, newGameBean.serverName) && this.startTime == newGameBean.startTime && Intrinsics.areEqual(this.new_service_date, newGameBean.new_service_date);
    }

    public int hashCode() {
        GameBean gameBean = this.game;
        return ((((((((((gameBean == null ? 0 : gameBean.hashCode()) * 31) + this.id) * 31) + this.gameId) * 31) + this.serverName.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.startTime)) * 31) + this.new_service_date.hashCode();
    }

    public String toString() {
        return "NewGameBean(game=" + this.game + ", id=" + this.id + ", gameId=" + this.gameId + ", serverName=" + this.serverName + ", startTime=" + this.startTime + ", new_service_date=" + this.new_service_date + ")";
    }

    public NewGameBean(GameBean gameBean, int i, int i2, String serverName, long j, String new_service_date) {
        Intrinsics.checkNotNullParameter(serverName, "serverName");
        Intrinsics.checkNotNullParameter(new_service_date, "new_service_date");
        this.game = gameBean;
        this.id = i;
        this.gameId = i2;
        this.serverName = serverName;
        this.startTime = j;
        this.new_service_date = new_service_date;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final int getId() {
        return this.id;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final String getNew_service_date() {
        return this.new_service_date;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NewGameBean(String new_service_date) {
        this(null, -1, -1, "", 0L, new_service_date);
        Intrinsics.checkNotNullParameter(new_service_date, "new_service_date");
    }
}
