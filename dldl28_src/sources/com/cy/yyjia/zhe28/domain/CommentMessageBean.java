package com.cy.yyjia.zhe28.domain;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.volcengine.common.contant.CommonConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CommentMessageBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b3\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0093\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003¢\u0006\u0002\u0010\u0017J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00010\rHÆ\u0003J\t\u0010<\u001a\u00020\u0007HÆ\u0003J¹\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u0003HÆ\u0001J\u0013\u0010>\u001a\u00020\u000b2\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010@\u001a\u00020AJ\t\u0010B\u001a\u00020\u0007HÖ\u0001J\t\u0010C\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010 R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019¨\u0006D"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CommentMessageBean;", "", "avatar", "", "dateline", "gameIcon", CommonConstants.key_gameId, "", "icon", "id", "isRead", "", "level", "", "level_count", "like_num", CommonConstants.KEY_MESSAGE, "pid", "title", "type", "uid", "userName", "userSex", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IZLjava/util/List;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAvatar", "()Ljava/lang/String;", "getDateline", "getGameIcon", "getGameId", "()I", "getIcon", "getId", "()Z", "getLevel", "()Ljava/util/List;", "getLevel_count", "getLike_num", "getMessage", "getPid", "getTitle", "getType", "getUid", "getUserName", "getUserSex", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getContentText", "Landroid/text/SpannableString;", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class CommentMessageBean {
    public static final int $stable = 8;
    private final String avatar;
    private final String dateline;
    private final String gameIcon;
    private final int gameId;
    private final String icon;
    private final int id;
    private final boolean isRead;
    private final List<Object> level;
    private final int level_count;
    private final int like_num;
    private final String message;
    private final int pid;
    private final String title;
    private final String type;
    private final int uid;
    private final String userName;
    private final String userSex;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAvatar() {
        return this.avatar;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getLike_num() {
        return this.like_num;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getPid() {
        return this.pid;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getUserSex() {
        return this.userSex;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getGameIcon() {
        return this.gameIcon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsRead() {
        return this.isRead;
    }

    public final List<Object> component8() {
        return this.level;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getLevel_count() {
        return this.level_count;
    }

    public final CommentMessageBean copy(String avatar, String dateline, String gameIcon, int gameId, String icon, int id, boolean isRead, List<? extends Object> level, int level_count, int like_num, String message, int pid, String title, String type, int uid, String userName, String userSex) {
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(gameIcon, "gameIcon");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(userSex, "userSex");
        return new CommentMessageBean(avatar, dateline, gameIcon, gameId, icon, id, isRead, level, level_count, like_num, message, pid, title, type, uid, userName, userSex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommentMessageBean)) {
            return false;
        }
        CommentMessageBean commentMessageBean = (CommentMessageBean) other;
        return Intrinsics.areEqual(this.avatar, commentMessageBean.avatar) && Intrinsics.areEqual(this.dateline, commentMessageBean.dateline) && Intrinsics.areEqual(this.gameIcon, commentMessageBean.gameIcon) && this.gameId == commentMessageBean.gameId && Intrinsics.areEqual(this.icon, commentMessageBean.icon) && this.id == commentMessageBean.id && this.isRead == commentMessageBean.isRead && Intrinsics.areEqual(this.level, commentMessageBean.level) && this.level_count == commentMessageBean.level_count && this.like_num == commentMessageBean.like_num && Intrinsics.areEqual(this.message, commentMessageBean.message) && this.pid == commentMessageBean.pid && Intrinsics.areEqual(this.title, commentMessageBean.title) && Intrinsics.areEqual(this.type, commentMessageBean.type) && this.uid == commentMessageBean.uid && Intrinsics.areEqual(this.userName, commentMessageBean.userName) && Intrinsics.areEqual(this.userSex, commentMessageBean.userSex);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    public int hashCode() {
        int iHashCode = ((((((((((this.avatar.hashCode() * 31) + this.dateline.hashCode()) * 31) + this.gameIcon.hashCode()) * 31) + this.gameId) * 31) + this.icon.hashCode()) * 31) + this.id) * 31;
        boolean z = this.isRead;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((((((((((((((((((((iHashCode + r1) * 31) + this.level.hashCode()) * 31) + this.level_count) * 31) + this.like_num) * 31) + this.message.hashCode()) * 31) + this.pid) * 31) + this.title.hashCode()) * 31) + this.type.hashCode()) * 31) + this.uid) * 31) + this.userName.hashCode()) * 31) + this.userSex.hashCode();
    }

    public String toString() {
        return "CommentMessageBean(avatar=" + this.avatar + ", dateline=" + this.dateline + ", gameIcon=" + this.gameIcon + ", gameId=" + this.gameId + ", icon=" + this.icon + ", id=" + this.id + ", isRead=" + this.isRead + ", level=" + this.level + ", level_count=" + this.level_count + ", like_num=" + this.like_num + ", message=" + this.message + ", pid=" + this.pid + ", title=" + this.title + ", type=" + this.type + ", uid=" + this.uid + ", userName=" + this.userName + ", userSex=" + this.userSex + ")";
    }

    public CommentMessageBean(String avatar, String dateline, String gameIcon, int i, String icon, int i2, boolean z, List<? extends Object> level, int i3, int i4, String message, int i5, String title, String type, int i6, String userName, String userSex) {
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(gameIcon, "gameIcon");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(userSex, "userSex");
        this.avatar = avatar;
        this.dateline = dateline;
        this.gameIcon = gameIcon;
        this.gameId = i;
        this.icon = icon;
        this.id = i2;
        this.isRead = z;
        this.level = level;
        this.level_count = i3;
        this.like_num = i4;
        this.message = message;
        this.pid = i5;
        this.title = title;
        this.type = type;
        this.uid = i6;
        this.userName = userName;
        this.userSex = userSex;
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final String getGameIcon() {
        return this.gameIcon;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final int getId() {
        return this.id;
    }

    public final boolean isRead() {
        return this.isRead;
    }

    public final List<Object> getLevel() {
        return this.level;
    }

    public final int getLevel_count() {
        return this.level_count;
    }

    public final int getLike_num() {
        return this.like_num;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getPid() {
        return this.pid;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public final int getUid() {
        return this.uid;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getUserSex() {
        return this.userSex;
    }

    public final SpannableString getContentText() {
        SpannableString spannableString = new SpannableString("回复了您：" + this.message + StringUtils.SPACE + this.dateline);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), spannableString.length() - this.dateline.length(), spannableString.length(), 33);
        return spannableString;
    }
}
