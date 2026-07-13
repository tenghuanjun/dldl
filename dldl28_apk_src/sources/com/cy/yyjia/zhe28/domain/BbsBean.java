package com.cy.yyjia.zhe28.domain;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.google.gson.annotations.SerializedName;
import com.volcengine.common.contant.CommonConstants;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BbsBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b+\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002BÃ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0006\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\u0002\u0010\u001fJ\t\u0010T\u001a\u00020\u0004HÆ\u0003J\t\u0010U\u001a\u00020\u0004HÆ\u0003J\t\u0010V\u001a\u00020\u0006HÆ\u0003J\t\u0010W\u001a\u00020\u0006HÆ\u0003J\t\u0010X\u001a\u00020\u0006HÆ\u0003J\t\u0010Y\u001a\u00020\u0006HÆ\u0003J\t\u0010Z\u001a\u00020\u0004HÆ\u0003J\t\u0010[\u001a\u00020\u0004HÆ\u0003J\t\u0010\\\u001a\u00020\u0006HÆ\u0003J\u000f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018HÆ\u0003J\u000f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00000\u0018HÆ\u0003J\t\u0010_\u001a\u00020\u0006HÆ\u0003J\t\u0010`\u001a\u00020\u001bHÆ\u0003J\t\u0010a\u001a\u00020\u001bHÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u001eHÆ\u0003J\t\u0010c\u001a\u00020\u0004HÆ\u0003J\t\u0010d\u001a\u00020\tHÆ\u0003J\t\u0010e\u001a\u00020\u0004HÆ\u0003J\t\u0010f\u001a\u00020\u0004HÆ\u0003J\t\u0010g\u001a\u00020\u0004HÆ\u0003J\t\u0010h\u001a\u00020\u0004HÆ\u0003J\t\u0010i\u001a\u00020\u0004HÆ\u0003Jó\u0001\u0010j\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00062\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00182\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÆ\u0001J\u0013\u0010k\u001a\u00020A2\b\u0010l\u001a\u0004\u0018\u00010mHÖ\u0003J\u000e\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020qJ\u000e\u0010r\u001a\u00020o2\u0006\u0010p\u001a\u00020qJ\t\u0010s\u001a\u00020\u0004HÖ\u0001J\u0006\u0010t\u001a\u00020oJ\u000e\u00105\u001a\u00020o2\u0006\u0010u\u001a\u00020vJ\u000e\u00105\u001a\u00020o2\u0006\u0010p\u001a\u00020qJ\u001e\u0010w\u001a\u00020x2\u0006\u0010u\u001a\u00020v2\u0006\u0010y\u001a\u00020\u00062\u0006\u0010z\u001a\u00020\u0006J\t\u0010{\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u001a\u0010(\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010#\"\u0004\b*\u0010+R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010#R\u0011\u0010\u0016\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u0010%R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010#R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010#R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010#R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u0010%R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010#R&\u00105\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010#\"\u0004\b7\u0010+R&\u00108\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00048G@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010#\"\u0004\b:\u0010+R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b;\u0010%R&\u0010<\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00048G@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010#\"\u0004\b>\u0010+R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u0018¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R&\u0010B\u001a\u00020A2\u0006\u00104\u001a\u00020A8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR&\u0010G\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010%\"\u0004\bI\u0010JR&\u0010K\u001a\u00020A2\u0006\u00104\u001a\u00020A8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010D\"\u0004\bM\u0010FR\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bN\u0010%R\u0011\u0010\u001c\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u0011\u0010\u0014\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010#R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018¢\u0006\b\n\u0000\u001a\u0004\bR\u0010@R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\bS\u0010P¨\u0006|"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", "cate", "", "cateName", "", "commentNum", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", CommonConstants.key_gameId, "pid", "isReply", "id", "isHot", "isTop", CommonConstants.KEY_MESSAGE, "title", "qualityImg", "createTime", "uid", "postId", "index", "uploadImg", "", "replyList", "user", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "toUser", "activity", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "(ILjava/lang/String;ILcom/cy/yyjia/zhe28/domain/GameBean;IIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/UserBean;Lcom/cy/yyjia/zhe28/domain/UserBean;Lcom/cy/yyjia/zhe28/domain/TypeBean;)V", "getActivity", "()Lcom/cy/yyjia/zhe28/domain/TypeBean;", "getCate", "()I", "getCateName", "()Ljava/lang/String;", "getCommentNum", "getCreateTime", "foldMaxLength", "getFoldMaxLength", "setFoldMaxLength", "(I)V", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "getId", "getIndex", "getMessage", "getPid", "getPostId", DBHelper.COL_VALUE, "praise", "getPraise", "setPraise", "praised", "getPraised", "setPraised", "getQualityImg", "read", "getRead", "setRead", "getReplyList", "()Ljava/util/List;", "", "selected", "getSelected", "()Z", "setSelected", "(Z)V", "showContent", "getShowContent", "setShowContent", "(Ljava/lang/String;)V", "target", "getTarget", "setTarget", "getTitle", "getToUser", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "getUid", "getUploadImg", "getUser", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "fold", "", "v", "Landroid/view/View;", "go", "hashCode", "initContent", "context", "Landroid/content/Context;", "searchContent", "Landroid/text/SpannableString;", "targetText", "data", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BbsBean extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final TypeBean activity;
    private final int cate;
    private final String cateName;
    private final int commentNum;
    private final String createTime;
    private int foldMaxLength;
    private final GameBean game;
    private final int gameId;
    private final int id;
    private final String index;
    private final int isHot;
    private final int isReply;
    private final int isTop;
    private final String message;
    private final int pid;
    private final int postId;
    private int praise;

    @SerializedName("isPraise")
    private int praised;
    private final String qualityImg;

    @SerializedName("isRead")
    private int read;
    private final List<BbsBean> replyList;
    private boolean selected;
    private String showContent;
    private boolean target;
    private final String title;
    private final UserBean toUser;
    private final int uid;
    private final List<String> uploadImg;
    private final UserBean user;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCate() {
        return this.cate;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getIsTop() {
        return this.isTop;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getQualityImg() {
        return this.qualityImg;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getPostId() {
        return this.postId;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getIndex() {
        return this.index;
    }

    public final List<String> component18() {
        return this.uploadImg;
    }

    public final List<BbsBean> component19() {
        return this.replyList;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCateName() {
        return this.cateName;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final UserBean getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final UserBean getToUser() {
        return this.toUser;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final TypeBean getActivity() {
        return this.activity;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCommentNum() {
        return this.commentNum;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getPid() {
        return this.pid;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getIsReply() {
        return this.isReply;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getIsHot() {
        return this.isHot;
    }

    public final BbsBean copy(int cate, String cateName, int commentNum, GameBean game, int gameId, int pid, int isReply, int id, int isHot, int isTop, String message, String title, String qualityImg, String createTime, int uid, int postId, String index, List<String> uploadImg, List<BbsBean> replyList, UserBean user, UserBean toUser, TypeBean activity) {
        Intrinsics.checkNotNullParameter(cateName, "cateName");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(qualityImg, "qualityImg");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(uploadImg, "uploadImg");
        Intrinsics.checkNotNullParameter(replyList, "replyList");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(toUser, "toUser");
        return new BbsBean(cate, cateName, commentNum, game, gameId, pid, isReply, id, isHot, isTop, message, title, qualityImg, createTime, uid, postId, index, uploadImg, replyList, user, toUser, activity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BbsBean)) {
            return false;
        }
        BbsBean bbsBean = (BbsBean) other;
        return this.cate == bbsBean.cate && Intrinsics.areEqual(this.cateName, bbsBean.cateName) && this.commentNum == bbsBean.commentNum && Intrinsics.areEqual(this.game, bbsBean.game) && this.gameId == bbsBean.gameId && this.pid == bbsBean.pid && this.isReply == bbsBean.isReply && this.id == bbsBean.id && this.isHot == bbsBean.isHot && this.isTop == bbsBean.isTop && Intrinsics.areEqual(this.message, bbsBean.message) && Intrinsics.areEqual(this.title, bbsBean.title) && Intrinsics.areEqual(this.qualityImg, bbsBean.qualityImg) && Intrinsics.areEqual(this.createTime, bbsBean.createTime) && this.uid == bbsBean.uid && this.postId == bbsBean.postId && Intrinsics.areEqual(this.index, bbsBean.index) && Intrinsics.areEqual(this.uploadImg, bbsBean.uploadImg) && Intrinsics.areEqual(this.replyList, bbsBean.replyList) && Intrinsics.areEqual(this.user, bbsBean.user) && Intrinsics.areEqual(this.toUser, bbsBean.toUser) && Intrinsics.areEqual(this.activity, bbsBean.activity);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((((((((((((((((((this.cate * 31) + this.cateName.hashCode()) * 31) + this.commentNum) * 31) + this.game.hashCode()) * 31) + this.gameId) * 31) + this.pid) * 31) + this.isReply) * 31) + this.id) * 31) + this.isHot) * 31) + this.isTop) * 31) + this.message.hashCode()) * 31) + this.title.hashCode()) * 31) + this.qualityImg.hashCode()) * 31) + this.createTime.hashCode()) * 31) + this.uid) * 31) + this.postId) * 31) + this.index.hashCode()) * 31) + this.uploadImg.hashCode()) * 31) + this.replyList.hashCode()) * 31) + this.user.hashCode()) * 31) + this.toUser.hashCode()) * 31;
        TypeBean typeBean = this.activity;
        return iHashCode + (typeBean == null ? 0 : typeBean.hashCode());
    }

    public String toString() {
        return "BbsBean(cate=" + this.cate + ", cateName=" + this.cateName + ", commentNum=" + this.commentNum + ", game=" + this.game + ", gameId=" + this.gameId + ", pid=" + this.pid + ", isReply=" + this.isReply + ", id=" + this.id + ", isHot=" + this.isHot + ", isTop=" + this.isTop + ", message=" + this.message + ", title=" + this.title + ", qualityImg=" + this.qualityImg + ", createTime=" + this.createTime + ", uid=" + this.uid + ", postId=" + this.postId + ", index=" + this.index + ", uploadImg=" + this.uploadImg + ", replyList=" + this.replyList + ", user=" + this.user + ", toUser=" + this.toUser + ", activity=" + this.activity + ")";
    }

    public final int getCate() {
        return this.cate;
    }

    public final String getCateName() {
        return this.cateName;
    }

    public final int getCommentNum() {
        return this.commentNum;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getPid() {
        return this.pid;
    }

    public final int isReply() {
        return this.isReply;
    }

    public final int getId() {
        return this.id;
    }

    public final int isHot() {
        return this.isHot;
    }

    public final int isTop() {
        return this.isTop;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getQualityImg() {
        return this.qualityImg;
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final int getUid() {
        return this.uid;
    }

    public final int getPostId() {
        return this.postId;
    }

    public final String getIndex() {
        return this.index;
    }

    public final List<String> getUploadImg() {
        return this.uploadImg;
    }

    public final List<BbsBean> getReplyList() {
        return this.replyList;
    }

    public final UserBean getUser() {
        return this.user;
    }

    public final UserBean getToUser() {
        return this.toUser;
    }

    public final TypeBean getActivity() {
        return this.activity;
    }

    public BbsBean(int i, String cateName, int i2, GameBean game, int i3, int i4, int i5, int i6, int i7, int i8, String message, String title, String qualityImg, String createTime, int i9, int i10, String index, List<String> uploadImg, List<BbsBean> replyList, UserBean user, UserBean toUser, TypeBean typeBean) {
        Intrinsics.checkNotNullParameter(cateName, "cateName");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(qualityImg, "qualityImg");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(uploadImg, "uploadImg");
        Intrinsics.checkNotNullParameter(replyList, "replyList");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(toUser, "toUser");
        this.cate = i;
        this.cateName = cateName;
        this.commentNum = i2;
        this.game = game;
        this.gameId = i3;
        this.pid = i4;
        this.isReply = i5;
        this.id = i6;
        this.isHot = i7;
        this.isTop = i8;
        this.message = message;
        this.title = title;
        this.qualityImg = qualityImg;
        this.createTime = createTime;
        this.uid = i9;
        this.postId = i10;
        this.index = index;
        this.uploadImg = uploadImg;
        this.replyList = replyList;
        this.user = user;
        this.toUser = toUser;
        this.activity = typeBean;
        this.showContent = "";
    }

    public final int getFoldMaxLength() {
        return this.foldMaxLength;
    }

    public final void setFoldMaxLength(int i) {
        this.foldMaxLength = i;
    }

    @Bindable
    public final boolean getTarget() {
        return this.target;
    }

    public final void setTarget(boolean z) {
        this.target = z;
        notifyPropertyChanged(106);
    }

    @Bindable
    public final int getPraised() {
        return this.praised;
    }

    public final void setPraised(int i) {
        this.praised = i;
        notifyPropertyChanged(76);
    }

    @Bindable
    public final int getPraise() {
        return this.praise;
    }

    public final void setPraise(int i) {
        this.praise = i;
        notifyPropertyChanged(75);
    }

    @Bindable
    public final int getRead() {
        return this.read;
    }

    public final void setRead(int i) {
        this.read = i;
        notifyPropertyChanged(83);
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    @Bindable
    public final String getShowContent() {
        return this.showContent;
    }

    public final void setShowContent(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.showContent = value;
        notifyPropertyChanged(98);
    }

    public final void praise(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Context context = v.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        praise(context);
    }

    public final void praise(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Repository.INSTANCE.praiseBbs(this.id, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.domain.BbsBean.praise.1
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
                Intrinsics.checkNotNullParameter(it, "it");
                Toast.makeText(context, it.getMsg(), 0).show();
                if (this.getPraised() == 1) {
                    this.setPraised(0);
                    BbsBean bbsBean = this;
                    bbsBean.setPraise(bbsBean.getPraise() - 1);
                } else {
                    this.setPraised(1);
                    BbsBean bbsBean2 = this;
                    bbsBean2.setPraise(bbsBean2.getPraise() + 1);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.domain.BbsBean.praise.2
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
                Toast.makeText(context, it.getLocalizedMessage(), 0).show();
                Log.e("praise: ", String.valueOf(it.getLocalizedMessage()));
            }
        });
    }

    public final void go(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intent intent = new Intent(v.getContext(), (Class<?>) BbsDetailActivity.class);
        intent.putExtra("id", this.id);
        v.getContext().startActivity(intent);
    }

    public final SpannableString searchContent(Context context, String targetText, String data) {
        int iIndexOf;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(targetText, "targetText");
        Intrinsics.checkNotNullParameter(data, "data");
        String str = data;
        SpannableString spannableString = new SpannableString(str);
        if (targetText.length() > 0) {
            int color = context.getResources().getColor(R.color.colorPrimary);
            int length = 0;
            while (length < data.length() && (iIndexOf = StringsKt.indexOf((CharSequence) str, targetText, length, true)) != -1) {
                spannableString.setSpan(new ForegroundColorSpan(color), iIndexOf, targetText.length() + iIndexOf, 17);
                length = iIndexOf + targetText.length();
            }
        }
        return spannableString;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void fold(android.view.View r3) {
        /*
            r2 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            boolean r3 = r2.selected
            r3 = r3 ^ 1
            r2.setSelected(r3)
            boolean r3 = r2.selected
            if (r3 != 0) goto L39
            java.lang.String r3 = r2.message
            int r3 = r3.length()
            int r0 = r2.foldMaxLength
            if (r3 > r0) goto L1b
            goto L39
        L1b:
            java.lang.String r3 = r2.message
            r1 = 0
            java.lang.String r3 = r3.substring(r1, r0)
            java.lang.String r0 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r3 = "..."
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            goto L3b
        L39:
            java.lang.String r3 = r2.message
        L3b:
            r2.setShowContent(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.domain.BbsBean.fold(android.view.View):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void initContent() {
        /*
            r3 = this;
            r0 = 108(0x6c, float:1.51E-43)
            r3.foldMaxLength = r0
            boolean r0 = r3.selected
            if (r0 != 0) goto L31
            java.lang.String r0 = r3.message
            int r0 = r0.length()
            int r1 = r3.foldMaxLength
            if (r0 > r1) goto L13
            goto L31
        L13:
            java.lang.String r0 = r3.message
            r2 = 0
            java.lang.String r0 = r0.substring(r2, r1)
            java.lang.String r1 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "..."
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L33
        L31:
            java.lang.String r0 = r3.message
        L33:
            r3.setShowContent(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.domain.BbsBean.initContent():void");
    }
}
