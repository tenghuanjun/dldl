package com.cy.yyjia.zhe28.domain;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.lzy.okgo.model.Progress;
import com.volcengine.common.contant.CommonConstants;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: CommentBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\bN\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003Bí\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0007\u0012\u0006\u0010\u0019\u001a\u00020\u0007\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\r\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\u0006\u0010\u001e\u001a\u00020\u0005\u0012\u0006\u0010\u001f\u001a\u00020\u0005\u0012\u0006\u0010 \u001a\u00020\u0005\u0012\u0006\u0010!\u001a\u00020\u0007\u0012\u0006\u0010\"\u001a\u00020\u0007¢\u0006\u0002\u0010#J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0007HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u0007HÆ\u0003J\t\u0010R\u001a\u00020\u0007HÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\t\u0010T\u001a\u00020\u0007HÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0007HÆ\u0003J\t\u0010X\u001a\u00020\u0007HÆ\u0003J\t\u0010Y\u001a\u00020\u0007HÆ\u0003J\u000f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00050\rHÆ\u0003J\t\u0010[\u001a\u00020\u001cHÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\t\u0010]\u001a\u00020\u0005HÆ\u0003J\t\u0010^\u001a\u00020\u0005HÆ\u0003J\t\u0010_\u001a\u00020\u0005HÆ\u0003J\t\u0010`\u001a\u00020\u0007HÆ\u0003J\t\u0010a\u001a\u00020\u0007HÆ\u0003J\t\u0010b\u001a\u00020\u0007HÆ\u0003J\t\u0010c\u001a\u00020\u0005HÆ\u0003J\t\u0010d\u001a\u00020\u0005HÆ\u0003J\t\u0010e\u001a\u00020\u0007HÆ\u0003J\u000f\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00000\rHÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010h\u001a\u00020\u0007HÆ\u0003J§\u0002\u0010i\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00072\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00072\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00072\b\b\u0002\u0010\"\u001a\u00020\u0007HÆ\u0001J\u0013\u0010j\u001a\u00020k2\b\u0010l\u001a\u0004\u0018\u00010mHÖ\u0003J\u000e\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020qJ\u0006\u0010r\u001a\u00020sJ\u0006\u0010t\u001a\u00020sJ\u0006\u0010u\u001a\u00020sJ\b\u0010v\u001a\u0004\u0018\u00010\u0005J\t\u0010w\u001a\u00020\u0007HÖ\u0001J\u000e\u0010x\u001a\u00020o2\u0006\u0010p\u001a\u00020qJ\u000e\u0010y\u001a\u00020o2\u0006\u0010p\u001a\u00020qJ\t\u0010z\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0011\u0010!\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010'R\u0011\u0010\"\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010'R&\u0010+\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u00078G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010'\"\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010'R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u0010'R&\u00102\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00078G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010'\"\u0004\b4\u0010.R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\r¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010%R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b8\u0010'R\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b9\u0010'R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010%R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010%R\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b<\u0010'R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b=\u0010'R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010%R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b?\u0010'R*\u0010@\u001a\u0004\u0018\u00010\u00052\b\u0010@\u001a\u0004\u0018\u00010\u00058G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010%\"\u0004\bB\u0010CR\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bD\u0010%R\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bE\u0010%R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bF\u0010%R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010%R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0011\u0010\u0018\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010'R\u0011\u0010\u0019\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bK\u0010'R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\bL\u00106R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\bM\u0010I¨\u0006{"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CommentBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "dateline", "", CommonConstants.key_gameId, "", "id", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "ipaddress", "level", "list", "", CommonConstants.KEY_MESSAGE, "pid", IjkMediaPlayer.OnNativeInvokeListener.ARG_PORT, "remark", "reply_num", "residue_num", "reward", "score", "status", "title", "to_uid", "uid", "uploadImg", "user", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "toUser", "qualityImg", "tagIcon", "tagName", "is_quality", "is_top", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/lang/String;IILjava/lang/String;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IILjava/util/List;Lcom/cy/yyjia/zhe28/domain/UserBean;Lcom/cy/yyjia/zhe28/domain/UserBean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getDateline", "()Ljava/lang/String;", "getGameId", "()I", "getId", "getIp", "getIpaddress", "islikeNum", "getIslikeNum", "setIslikeNum", "(I)V", "itemType", "getItemType", "getLevel", "like_num", "getLike_num", "setLike_num", "getList", "()Ljava/util/List;", "getMessage", "getPid", "getPort", "getQualityImg", "getRemark", "getReply_num", "getResidue_num", "getReward", "getScore", "showText", "getShowText", "setShowText", "(Ljava/lang/String;)V", "getStatus", "getTagIcon", "getTagName", "getTitle", "getToUser", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "getTo_uid", "getUid", "getUploadImg", "getUser", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", Progress.FOLDER, "", "v", "Landroid/view/View;", "getSecondReply", "Landroid/text/SpannableString;", "getSecondReply1", "getSecondReply2", "getShowTextShort", "hashCode", "praise", "toDetail", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class CommentBean extends BaseObservable implements Serializable, MultiItemEntity {
    public static final int $stable = 8;
    private final String dateline;
    private final int gameId;
    private final int id;
    private final String ip;
    private final String ipaddress;
    private final int is_quality;
    private final int is_top;
    private int islikeNum;
    private final int level;
    private int like_num;
    private final List<CommentBean> list;
    private final String message;
    private final int pid;
    private final int port;
    private final String qualityImg;
    private final String remark;
    private final int reply_num;
    private final int residue_num;
    private final String reward;
    private final int score;
    private String showText;
    private final String status;
    private final String tagIcon;
    private final String tagName;
    private final String title;
    private final UserBean toUser;
    private final int to_uid;
    private final int uid;
    private final List<String> uploadImg;
    private final UserBean user;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getPort() {
        return this.port;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRemark() {
        return this.remark;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getReply_num() {
        return this.reply_num;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getResidue_num() {
        return this.residue_num;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getReward() {
        return this.reward;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final int getTo_uid() {
        return this.to_uid;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    public final List<String> component20() {
        return this.uploadImg;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final UserBean getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final UserBean getToUser() {
        return this.toUser;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getQualityImg() {
        return this.qualityImg;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getTagIcon() {
        return this.tagIcon;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getTagName() {
        return this.tagName;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final int getIs_quality() {
        return this.is_quality;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final int getIs_top() {
        return this.is_top;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getIp() {
        return this.ip;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIpaddress() {
        return this.ipaddress;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getLevel() {
        return this.level;
    }

    public final List<CommentBean> component7() {
        return this.list;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getPid() {
        return this.pid;
    }

    public final CommentBean copy(String dateline, int gameId, int id, String ip, String ipaddress, int level, List<CommentBean> list, String message, int pid, int port, String remark, int reply_num, int residue_num, String reward, int score, String status, String title, int to_uid, int uid, List<String> uploadImg, UserBean user, UserBean toUser, String qualityImg, String tagIcon, String tagName, int is_quality, int is_top) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(ipaddress, "ipaddress");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(reward, "reward");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(uploadImg, "uploadImg");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(qualityImg, "qualityImg");
        Intrinsics.checkNotNullParameter(tagIcon, "tagIcon");
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        return new CommentBean(dateline, gameId, id, ip, ipaddress, level, list, message, pid, port, remark, reply_num, residue_num, reward, score, status, title, to_uid, uid, uploadImg, user, toUser, qualityImg, tagIcon, tagName, is_quality, is_top);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommentBean)) {
            return false;
        }
        CommentBean commentBean = (CommentBean) other;
        return Intrinsics.areEqual(this.dateline, commentBean.dateline) && this.gameId == commentBean.gameId && this.id == commentBean.id && Intrinsics.areEqual(this.ip, commentBean.ip) && Intrinsics.areEqual(this.ipaddress, commentBean.ipaddress) && this.level == commentBean.level && Intrinsics.areEqual(this.list, commentBean.list) && Intrinsics.areEqual(this.message, commentBean.message) && this.pid == commentBean.pid && this.port == commentBean.port && Intrinsics.areEqual(this.remark, commentBean.remark) && this.reply_num == commentBean.reply_num && this.residue_num == commentBean.residue_num && Intrinsics.areEqual(this.reward, commentBean.reward) && this.score == commentBean.score && Intrinsics.areEqual(this.status, commentBean.status) && Intrinsics.areEqual(this.title, commentBean.title) && this.to_uid == commentBean.to_uid && this.uid == commentBean.uid && Intrinsics.areEqual(this.uploadImg, commentBean.uploadImg) && Intrinsics.areEqual(this.user, commentBean.user) && Intrinsics.areEqual(this.toUser, commentBean.toUser) && Intrinsics.areEqual(this.qualityImg, commentBean.qualityImg) && Intrinsics.areEqual(this.tagIcon, commentBean.tagIcon) && Intrinsics.areEqual(this.tagName, commentBean.tagName) && this.is_quality == commentBean.is_quality && this.is_top == commentBean.is_top;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((this.dateline.hashCode() * 31) + this.gameId) * 31) + this.id) * 31) + this.ip.hashCode()) * 31) + this.ipaddress.hashCode()) * 31) + this.level) * 31) + this.list.hashCode()) * 31;
        String str = this.message;
        int iHashCode2 = (((((((((((((((((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.pid) * 31) + this.port) * 31) + this.remark.hashCode()) * 31) + this.reply_num) * 31) + this.residue_num) * 31) + this.reward.hashCode()) * 31) + this.score) * 31) + this.status.hashCode()) * 31) + this.title.hashCode()) * 31) + this.to_uid) * 31) + this.uid) * 31) + this.uploadImg.hashCode()) * 31) + this.user.hashCode()) * 31;
        UserBean userBean = this.toUser;
        return ((((((((((iHashCode2 + (userBean != null ? userBean.hashCode() : 0)) * 31) + this.qualityImg.hashCode()) * 31) + this.tagIcon.hashCode()) * 31) + this.tagName.hashCode()) * 31) + this.is_quality) * 31) + this.is_top;
    }

    public String toString() {
        return "CommentBean(dateline=" + this.dateline + ", gameId=" + this.gameId + ", id=" + this.id + ", ip=" + this.ip + ", ipaddress=" + this.ipaddress + ", level=" + this.level + ", list=" + this.list + ", message=" + this.message + ", pid=" + this.pid + ", port=" + this.port + ", remark=" + this.remark + ", reply_num=" + this.reply_num + ", residue_num=" + this.residue_num + ", reward=" + this.reward + ", score=" + this.score + ", status=" + this.status + ", title=" + this.title + ", to_uid=" + this.to_uid + ", uid=" + this.uid + ", uploadImg=" + this.uploadImg + ", user=" + this.user + ", toUser=" + this.toUser + ", qualityImg=" + this.qualityImg + ", tagIcon=" + this.tagIcon + ", tagName=" + this.tagName + ", is_quality=" + this.is_quality + ", is_top=" + this.is_top + ")";
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getId() {
        return this.id;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getIpaddress() {
        return this.ipaddress;
    }

    public final int getLevel() {
        return this.level;
    }

    public final List<CommentBean> getList() {
        return this.list;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getPid() {
        return this.pid;
    }

    public final int getPort() {
        return this.port;
    }

    public final String getRemark() {
        return this.remark;
    }

    public final int getReply_num() {
        return this.reply_num;
    }

    public final int getResidue_num() {
        return this.residue_num;
    }

    public final String getReward() {
        return this.reward;
    }

    public final int getScore() {
        return this.score;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTo_uid() {
        return this.to_uid;
    }

    public final int getUid() {
        return this.uid;
    }

    public final List<String> getUploadImg() {
        return this.uploadImg;
    }

    public final UserBean getUser() {
        return this.user;
    }

    public final UserBean getToUser() {
        return this.toUser;
    }

    public final String getQualityImg() {
        return this.qualityImg;
    }

    public final String getTagIcon() {
        return this.tagIcon;
    }

    public final String getTagName() {
        return this.tagName;
    }

    public final int is_quality() {
        return this.is_quality;
    }

    public final int is_top() {
        return this.is_top;
    }

    public CommentBean(String dateline, int i, int i2, String ip, String ipaddress, int i3, List<CommentBean> list, String str, int i4, int i5, String remark, int i6, int i7, String reward, int i8, String status, String title, int i9, int i10, List<String> uploadImg, UserBean user, UserBean userBean, String qualityImg, String tagIcon, String tagName, int i11, int i12) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(ipaddress, "ipaddress");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(reward, "reward");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(uploadImg, "uploadImg");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(qualityImg, "qualityImg");
        Intrinsics.checkNotNullParameter(tagIcon, "tagIcon");
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        this.dateline = dateline;
        this.gameId = i;
        this.id = i2;
        this.ip = ip;
        this.ipaddress = ipaddress;
        this.level = i3;
        this.list = list;
        this.message = str;
        this.pid = i4;
        this.port = i5;
        this.remark = remark;
        this.reply_num = i6;
        this.residue_num = i7;
        this.reward = reward;
        this.score = i8;
        this.status = status;
        this.title = title;
        this.to_uid = i9;
        this.uid = i10;
        this.uploadImg = uploadImg;
        this.user = user;
        this.toUser = userBean;
        this.qualityImg = qualityImg;
        this.tagIcon = tagIcon;
        this.tagName = tagName;
        this.is_quality = i11;
        this.is_top = i12;
        this.showText = str;
    }

    @Bindable
    public final int getIslikeNum() {
        return this.islikeNum;
    }

    public final void setIslikeNum(int i) {
        this.islikeNum = i;
        notifyPropertyChanged(47);
    }

    @Bindable
    public final int getLike_num() {
        return this.like_num;
    }

    public final void setLike_num(int i) {
        this.like_num = i;
        notifyPropertyChanged(53);
    }

    @Bindable
    public final String getShowText() {
        return this.showText;
    }

    public final void setShowText(String str) {
        this.showText = str;
        notifyPropertyChanged(102);
    }

    public final String getShowTextShort() {
        if (TextUtils.isEmpty(this.message)) {
            return "";
        }
        String str = this.message;
        Intrinsics.checkNotNull(str);
        if (str.length() > 80) {
            String strSubstring = this.message.substring(0, 80);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring + "...";
        }
        return this.message;
    }

    public final SpannableString getSecondReply() {
        int i = this.level;
        if (i == 1 || i == 2 || i == 3) {
            return getSecondReply1();
        }
        return getSecondReply2();
    }

    public final SpannableString getSecondReply1() {
        SpannableString spannableString = new SpannableString(this.user.getNickName() + "：" + this.message);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0F316A")), 0, this.user.getNickName().length(), 33);
        return spannableString;
    }

    public final SpannableString getSecondReply2() {
        if (this.toUser != null) {
            SpannableString spannableString = new SpannableString(this.user.getNickName() + "回复@" + this.toUser.getNickName() + "：" + this.message);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0F316A")), 0, this.user.getNickName().length(), 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0F316A")), this.user.getNickName().length() + 2, this.user.getNickName().length() + this.toUser.getNickName().length() + 4, 33);
            return spannableString;
        }
        return getSecondReply1();
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return this.is_top;
    }

    public final void praise(final View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Repository.INSTANCE.praiseComments(this.id, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.domain.CommentBean.praise.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                int like_num;
                Intrinsics.checkNotNullParameter(it, "it");
                Toast.makeText(v.getContext(), it.getMsg(), 0).show();
                if (it.getCode() == 200) {
                    this.setIslikeNum(this.getIslikeNum() != 1 ? 1 : 0);
                    CommentBean commentBean = this;
                    Object data = it.getData();
                    Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.Boolean");
                    if (((Boolean) data).booleanValue()) {
                        like_num = this.getLike_num() + 1;
                    } else {
                        like_num = this.getLike_num() - 1;
                    }
                    commentBean.setLike_num(like_num);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.domain.CommentBean.praise.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Toast.makeText(v.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }

    public final void folder(View v) {
        String showTextShort;
        Intrinsics.checkNotNullParameter(v, "v");
        v.setSelected(!v.isSelected());
        if (v.isSelected()) {
            showTextShort = this.message;
        } else {
            showTextShort = getShowTextShort();
        }
        setShowText(showTextShort);
    }

    public final void toDetail(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intent intent = new Intent(v.getContext(), (Class<?>) CommentDetailActivity.class);
        intent.putExtra("data", this);
        v.getContext().startActivity(intent);
    }
}
