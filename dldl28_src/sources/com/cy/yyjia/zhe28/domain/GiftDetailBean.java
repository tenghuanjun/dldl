package com.cy.yyjia.zhe28.domain;

import com.mobile.auth.gatewayauth.Constant;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.volcengine.common.contant.CommonConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GiftDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\nHÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\rHÆ\u0003J\t\u0010%\u001a\u00020\rHÆ\u0003Jc\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010*\u001a\u00020\u0006J\t\u0010+\u001a\u00020\u0003HÖ\u0001J\t\u0010,\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006-"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GiftDetailBean;", "", "id", "", CommonConstants.key_gameId, "name", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "howToUser", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "type", Constant.START_TIME, "", "endTime", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/GameBean;Ljava/lang/String;JJ)V", "getContent", "()Ljava/lang/String;", "getEndTime", "()J", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "()I", "getHowToUser", "getId", "getName", "getStartTime", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getAvailableTime", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GiftDetailBean {
    public static final int $stable = 8;
    private final String content;
    private final long endTime;
    private final GameBean game;
    private final int gameId;
    private final String howToUser;
    private final int id;
    private final String name;
    private final long startTime;
    private final String type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getHowToUser() {
        return this.howToUser;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getEndTime() {
        return this.endTime;
    }

    public final GiftDetailBean copy(int id, int gameId, String name, String content, String howToUser, GameBean game, String type, long startTime, long endTime) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(howToUser, "howToUser");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(type, "type");
        return new GiftDetailBean(id, gameId, name, content, howToUser, game, type, startTime, endTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GiftDetailBean)) {
            return false;
        }
        GiftDetailBean giftDetailBean = (GiftDetailBean) other;
        return this.id == giftDetailBean.id && this.gameId == giftDetailBean.gameId && Intrinsics.areEqual(this.name, giftDetailBean.name) && Intrinsics.areEqual(this.content, giftDetailBean.content) && Intrinsics.areEqual(this.howToUser, giftDetailBean.howToUser) && Intrinsics.areEqual(this.game, giftDetailBean.game) && Intrinsics.areEqual(this.type, giftDetailBean.type) && this.startTime == giftDetailBean.startTime && this.endTime == giftDetailBean.endTime;
    }

    public int hashCode() {
        return (((((((((((((((this.id * 31) + this.gameId) * 31) + this.name.hashCode()) * 31) + this.content.hashCode()) * 31) + this.howToUser.hashCode()) * 31) + this.game.hashCode()) * 31) + this.type.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.startTime)) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.endTime);
    }

    public String toString() {
        return "GiftDetailBean(id=" + this.id + ", gameId=" + this.gameId + ", name=" + this.name + ", content=" + this.content + ", howToUser=" + this.howToUser + ", game=" + this.game + ", type=" + this.type + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ")";
    }

    public GiftDetailBean(int i, int i2, String name, String content, String howToUser, GameBean game, String type, long j, long j2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(howToUser, "howToUser");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = i;
        this.gameId = i2;
        this.name = name;
        this.content = content;
        this.howToUser = howToUser;
        this.game = game;
        this.type = type;
        this.startTime = j;
        this.endTime = j2;
    }

    public final int getId() {
        return this.id;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getHowToUser() {
        return this.howToUser;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final String getType() {
        return this.type;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final String getAvailableTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        long j = 1000;
        return simpleDateFormat.format(new Date(this.startTime * j)) + "至" + simpleDateFormat.format(new Date(this.endTime * j));
    }
}
