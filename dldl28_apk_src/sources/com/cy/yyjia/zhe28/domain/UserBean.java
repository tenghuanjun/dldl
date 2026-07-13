package com.cy.yyjia.zhe28.domain;

import android.text.TextUtils;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import com.cy.yyjia.zhe28.util.Constant;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: UserBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\bU\n\u0002\u0010\u0000\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B¹\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u0017\u001a\u00020\u0004\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\u0006\u0010\u0019\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u0004\u0012\u0006\u0010\u001d\u001a\u00020\u0007\u0012\u0006\u0010\u001e\u001a\u00020\u0007\u0012\u0006\u0010\u001f\u001a\u00020\u0007\u0012\b\b\u0002\u0010 \u001a\u00020\u0004\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010#\u001a\u00020$\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010)J\u0006\u0010R\u001a\u00020$J\t\u0010S\u001a\u00020\u0004HÆ\u0003J\t\u0010T\u001a\u00020\u0004HÆ\u0003J\t\u0010U\u001a\u00020\u0004HÆ\u0003J\t\u0010V\u001a\u00020\u0004HÆ\u0003J\t\u0010W\u001a\u00020\u0004HÆ\u0003J\t\u0010X\u001a\u00020\u0004HÆ\u0003J\t\u0010Y\u001a\u00020\u0004HÆ\u0003J\t\u0010Z\u001a\u00020\u0004HÆ\u0003J\t\u0010[\u001a\u00020\u0007HÆ\u0003J\t\u0010\\\u001a\u00020\u0004HÆ\u0003J\t\u0010]\u001a\u00020\u0004HÆ\u0003J\t\u0010^\u001a\u00020\u0004HÆ\u0003J\t\u0010_\u001a\u00020\u0004HÆ\u0003J\t\u0010`\u001a\u00020\u0004HÆ\u0003J\t\u0010a\u001a\u00020\u0004HÆ\u0003J\t\u0010b\u001a\u00020\u0004HÆ\u0003J\t\u0010c\u001a\u00020\u0004HÆ\u0003J\t\u0010d\u001a\u00020\u0007HÆ\u0003J\t\u0010e\u001a\u00020\u0007HÆ\u0003J\t\u0010f\u001a\u00020\u0007HÆ\u0003J\t\u0010g\u001a\u00020\u0004HÆ\u0003J\u0010\u0010h\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00106J\t\u0010i\u001a\u00020\u0007HÆ\u0003J\u0010\u0010j\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00106J\t\u0010k\u001a\u00020$HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010p\u001a\u00020\u0007HÆ\u0003J\t\u0010q\u001a\u00020\u0004HÆ\u0003J\t\u0010r\u001a\u00020\u0007HÆ\u0003J\t\u0010s\u001a\u00020\u0004HÆ\u0003J\t\u0010t\u001a\u00020\u0004HÆ\u0003J\t\u0010u\u001a\u00020\u0004HÆ\u0003Jø\u0002\u0010v\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010#\u001a\u00020$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010wJ\u0013\u0010x\u001a\u00020$2\b\u0010y\u001a\u0004\u0018\u00010zHÖ\u0003J\u0006\u0010{\u001a\u00020\u0004J\u0006\u0010|\u001a\u00020\u0004J\t\u0010}\u001a\u00020\u0007HÖ\u0001J\u0006\u0010~\u001a\u00020$J\u0006\u0010\u007f\u001a\u00020$J\u0007\u0010\u0080\u0001\u001a\u00020$J\u0007\u0010\u0081\u0001\u001a\u00020$J\n\u0010\u0082\u0001\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010+R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010(\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010+R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010+R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b2\u0010.R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010+R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010+R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0011\u0010\u001e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010.R\u0011\u0010\u001f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010.R\u0015\u0010\"\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u00107\u001a\u0004\b\"\u00106R\u0011\u0010\u001d\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010.R\u0013\u0010&\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010+R\u001a\u0010\r\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010+\"\u0004\b:\u0010;R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010+R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010+R\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u0013\u0010'\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010+R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010+R\u0011\u0010\u0012\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010+R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010+R\u0011\u0010 \u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010+R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010+R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bE\u0010.R\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0015\u0010!\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u00107\u001a\u0004\bH\u00106R\u0011\u0010\u0016\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010+R\u001a\u0010\u001c\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010+\"\u0004\bK\u0010;R\u001e\u0010\u0017\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010+\"\u0004\bM\u0010;R\u0011\u0010\u001a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010+R\u0013\u0010%\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010+R\u0011\u0010\u0018\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010+R\u0011\u0010\u0014\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010+¨\u0006\u0083\u0001"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/UserBean;", "Ljava/io/Serializable;", "()V", "AliAccount", "", "age", "allExperience", "", "channelUid", "coin", "dateline", NotificationCompat.CATEGORY_EMAIL, "idCard", "nickName", HintConstants.AUTOFILL_HINT_PASSWORD, "points_balance", "realName", "status", "telphone", "totalCoupon", "welfare", "uid", "userName", "user_avatar", "wallet_balance", "trade_pin", "vieLevel", "giveMoney", "userSex", "is_official", "isMonthCard", "isSaveingCard", "totalYhq", "unset_status", "isSign", "unread_msg", "", "vieLevelImg", "monthCardImg", "savingCardImg", "birthDay", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAliAccount", "()Ljava/lang/String;", "getAge", "getAllExperience", "()I", "getBirthDay", "getChannelUid", "getCoin", "getDateline", "getEmail", "getGiveMoney", "getIdCard", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMonthCardImg", "getNickName", "setNickName", "(Ljava/lang/String;)V", "getPassword", "getPoints_balance", "getRealName", "getSavingCardImg", "getStatus", "getTelphone", "getTotalCoupon", "getTotalYhq", "getTrade_pin", "getUid", "getUnread_msg", "()Z", "getUnset_status", "getUserName", "getUserSex", "setUserSex", "getUser_avatar", "setUser_avatar", "getVieLevel", "getVieLevelImg", "getWallet_balance", "getWelfare", "checkUserIsVip", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/cy/yyjia/zhe28/domain/UserBean;", "equals", "other", "", "getEncodePhone", "getUserVipStr", "hashCode", "hideMonthCard", "hideSqk", "isAuth", "isMe", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class UserBean implements Serializable {
    public static final int $stable = 8;
    private final String AliAccount;
    private final String age;
    private final int allExperience;
    private final String birthDay;
    private final int channelUid;
    private final String coin;
    private final int dateline;
    private final String email;
    private final String giveMoney;
    private final String idCard;
    private final int isMonthCard;
    private final int isSaveingCard;
    private final Integer isSign;
    private final int is_official;
    private final String monthCardImg;
    private String nickName;
    private final String password;
    private final String points_balance;
    private final String realName;
    private final String savingCardImg;
    private final String status;
    private final String telphone;
    private final String totalCoupon;
    private final String totalYhq;
    private final String trade_pin;
    private final int uid;
    private final boolean unread_msg;
    private final Integer unset_status;
    private final String userName;
    private String userSex;

    @SerializedName(alternate = {"avatar"}, value = "user_avatar")
    private String user_avatar;
    private final String vieLevel;
    private final String vieLevelImg;
    private final String wallet_balance;
    private final String welfare;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAliAccount() {
        return this.AliAccount;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPoints_balance() {
        return this.points_balance;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getRealName() {
        return this.realName;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTelphone() {
        return this.telphone;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getTotalCoupon() {
        return this.totalCoupon;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getWelfare() {
        return this.welfare;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getUser_avatar() {
        return this.user_avatar;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAge() {
        return this.age;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getWallet_balance() {
        return this.wallet_balance;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getTrade_pin() {
        return this.trade_pin;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getVieLevel() {
        return this.vieLevel;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getGiveMoney() {
        return this.giveMoney;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getUserSex() {
        return this.userSex;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final int getIs_official() {
        return this.is_official;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final int getIsMonthCard() {
        return this.isMonthCard;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final int getIsSaveingCard() {
        return this.isSaveingCard;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final String getTotalYhq() {
        return this.totalYhq;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final Integer getUnset_status() {
        return this.unset_status;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getAllExperience() {
        return this.allExperience;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final Integer getIsSign() {
        return this.isSign;
    }

    /* JADX INFO: renamed from: component31, reason: from getter */
    public final boolean getUnread_msg() {
        return this.unread_msg;
    }

    /* JADX INFO: renamed from: component32, reason: from getter */
    public final String getVieLevelImg() {
        return this.vieLevelImg;
    }

    /* JADX INFO: renamed from: component33, reason: from getter */
    public final String getMonthCardImg() {
        return this.monthCardImg;
    }

    /* JADX INFO: renamed from: component34, reason: from getter */
    public final String getSavingCardImg() {
        return this.savingCardImg;
    }

    /* JADX INFO: renamed from: component35, reason: from getter */
    public final String getBirthDay() {
        return this.birthDay;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getChannelUid() {
        return this.channelUid;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCoin() {
        return this.coin;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getIdCard() {
        return this.idCard;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getNickName() {
        return this.nickName;
    }

    public final UserBean copy(String AliAccount, String age, int allExperience, int channelUid, String coin, int dateline, String email, String idCard, String nickName, String password, String points_balance, String realName, String status, String telphone, String totalCoupon, String welfare, int uid, String userName, String user_avatar, String wallet_balance, String trade_pin, String vieLevel, String giveMoney, String userSex, int is_official, int isMonthCard, int isSaveingCard, String totalYhq, Integer unset_status, Integer isSign, boolean unread_msg, String vieLevelImg, String monthCardImg, String savingCardImg, String birthDay) {
        Intrinsics.checkNotNullParameter(AliAccount, "AliAccount");
        Intrinsics.checkNotNullParameter(age, "age");
        Intrinsics.checkNotNullParameter(coin, "coin");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(idCard, "idCard");
        Intrinsics.checkNotNullParameter(nickName, "nickName");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(points_balance, "points_balance");
        Intrinsics.checkNotNullParameter(realName, "realName");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(telphone, "telphone");
        Intrinsics.checkNotNullParameter(totalCoupon, "totalCoupon");
        Intrinsics.checkNotNullParameter(welfare, "welfare");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(user_avatar, "user_avatar");
        Intrinsics.checkNotNullParameter(wallet_balance, "wallet_balance");
        Intrinsics.checkNotNullParameter(trade_pin, "trade_pin");
        Intrinsics.checkNotNullParameter(vieLevel, "vieLevel");
        Intrinsics.checkNotNullParameter(giveMoney, "giveMoney");
        Intrinsics.checkNotNullParameter(userSex, "userSex");
        Intrinsics.checkNotNullParameter(totalYhq, "totalYhq");
        return new UserBean(AliAccount, age, allExperience, channelUid, coin, dateline, email, idCard, nickName, password, points_balance, realName, status, telphone, totalCoupon, welfare, uid, userName, user_avatar, wallet_balance, trade_pin, vieLevel, giveMoney, userSex, is_official, isMonthCard, isSaveingCard, totalYhq, unset_status, isSign, unread_msg, vieLevelImg, monthCardImg, savingCardImg, birthDay);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserBean)) {
            return false;
        }
        UserBean userBean = (UserBean) other;
        return Intrinsics.areEqual(this.AliAccount, userBean.AliAccount) && Intrinsics.areEqual(this.age, userBean.age) && this.allExperience == userBean.allExperience && this.channelUid == userBean.channelUid && Intrinsics.areEqual(this.coin, userBean.coin) && this.dateline == userBean.dateline && Intrinsics.areEqual(this.email, userBean.email) && Intrinsics.areEqual(this.idCard, userBean.idCard) && Intrinsics.areEqual(this.nickName, userBean.nickName) && Intrinsics.areEqual(this.password, userBean.password) && Intrinsics.areEqual(this.points_balance, userBean.points_balance) && Intrinsics.areEqual(this.realName, userBean.realName) && Intrinsics.areEqual(this.status, userBean.status) && Intrinsics.areEqual(this.telphone, userBean.telphone) && Intrinsics.areEqual(this.totalCoupon, userBean.totalCoupon) && Intrinsics.areEqual(this.welfare, userBean.welfare) && this.uid == userBean.uid && Intrinsics.areEqual(this.userName, userBean.userName) && Intrinsics.areEqual(this.user_avatar, userBean.user_avatar) && Intrinsics.areEqual(this.wallet_balance, userBean.wallet_balance) && Intrinsics.areEqual(this.trade_pin, userBean.trade_pin) && Intrinsics.areEqual(this.vieLevel, userBean.vieLevel) && Intrinsics.areEqual(this.giveMoney, userBean.giveMoney) && Intrinsics.areEqual(this.userSex, userBean.userSex) && this.is_official == userBean.is_official && this.isMonthCard == userBean.isMonthCard && this.isSaveingCard == userBean.isSaveingCard && Intrinsics.areEqual(this.totalYhq, userBean.totalYhq) && Intrinsics.areEqual(this.unset_status, userBean.unset_status) && Intrinsics.areEqual(this.isSign, userBean.isSign) && this.unread_msg == userBean.unread_msg && Intrinsics.areEqual(this.vieLevelImg, userBean.vieLevelImg) && Intrinsics.areEqual(this.monthCardImg, userBean.monthCardImg) && Intrinsics.areEqual(this.savingCardImg, userBean.savingCardImg) && Intrinsics.areEqual(this.birthDay, userBean.birthDay);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v54, types: [int] */
    /* JADX WARN: Type inference failed for: r1v68 */
    /* JADX WARN: Type inference failed for: r1v71 */
    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((this.AliAccount.hashCode() * 31) + this.age.hashCode()) * 31) + this.allExperience) * 31) + this.channelUid) * 31) + this.coin.hashCode()) * 31) + this.dateline) * 31) + this.email.hashCode()) * 31) + this.idCard.hashCode()) * 31) + this.nickName.hashCode()) * 31) + this.password.hashCode()) * 31) + this.points_balance.hashCode()) * 31) + this.realName.hashCode()) * 31) + this.status.hashCode()) * 31) + this.telphone.hashCode()) * 31) + this.totalCoupon.hashCode()) * 31) + this.welfare.hashCode()) * 31) + this.uid) * 31) + this.userName.hashCode()) * 31) + this.user_avatar.hashCode()) * 31) + this.wallet_balance.hashCode()) * 31) + this.trade_pin.hashCode()) * 31) + this.vieLevel.hashCode()) * 31) + this.giveMoney.hashCode()) * 31) + this.userSex.hashCode()) * 31) + this.is_official) * 31) + this.isMonthCard) * 31) + this.isSaveingCard) * 31) + this.totalYhq.hashCode()) * 31;
        Integer num = this.unset_status;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.isSign;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        boolean z = this.unread_msg;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode3 + r1) * 31;
        String str = this.vieLevelImg;
        int iHashCode4 = (i + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.monthCardImg;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.savingCardImg;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.birthDay;
        return iHashCode6 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "UserBean(AliAccount=" + this.AliAccount + ", age=" + this.age + ", allExperience=" + this.allExperience + ", channelUid=" + this.channelUid + ", coin=" + this.coin + ", dateline=" + this.dateline + ", email=" + this.email + ", idCard=" + this.idCard + ", nickName=" + this.nickName + ", password=" + this.password + ", points_balance=" + this.points_balance + ", realName=" + this.realName + ", status=" + this.status + ", telphone=" + this.telphone + ", totalCoupon=" + this.totalCoupon + ", welfare=" + this.welfare + ", uid=" + this.uid + ", userName=" + this.userName + ", user_avatar=" + this.user_avatar + ", wallet_balance=" + this.wallet_balance + ", trade_pin=" + this.trade_pin + ", vieLevel=" + this.vieLevel + ", giveMoney=" + this.giveMoney + ", userSex=" + this.userSex + ", is_official=" + this.is_official + ", isMonthCard=" + this.isMonthCard + ", isSaveingCard=" + this.isSaveingCard + ", totalYhq=" + this.totalYhq + ", unset_status=" + this.unset_status + ", isSign=" + this.isSign + ", unread_msg=" + this.unread_msg + ", vieLevelImg=" + this.vieLevelImg + ", monthCardImg=" + this.monthCardImg + ", savingCardImg=" + this.savingCardImg + ", birthDay=" + this.birthDay + ")";
    }

    public UserBean(String AliAccount, String age, int i, int i2, String coin, int i3, String email, String idCard, String nickName, String password, String points_balance, String realName, String status, String telphone, String totalCoupon, String welfare, int i4, String userName, String user_avatar, String wallet_balance, String trade_pin, String vieLevel, String giveMoney, String userSex, int i5, int i6, int i7, String totalYhq, Integer num, Integer num2, boolean z, String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(AliAccount, "AliAccount");
        Intrinsics.checkNotNullParameter(age, "age");
        Intrinsics.checkNotNullParameter(coin, "coin");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(idCard, "idCard");
        Intrinsics.checkNotNullParameter(nickName, "nickName");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(points_balance, "points_balance");
        Intrinsics.checkNotNullParameter(realName, "realName");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(telphone, "telphone");
        Intrinsics.checkNotNullParameter(totalCoupon, "totalCoupon");
        Intrinsics.checkNotNullParameter(welfare, "welfare");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(user_avatar, "user_avatar");
        Intrinsics.checkNotNullParameter(wallet_balance, "wallet_balance");
        Intrinsics.checkNotNullParameter(trade_pin, "trade_pin");
        Intrinsics.checkNotNullParameter(vieLevel, "vieLevel");
        Intrinsics.checkNotNullParameter(giveMoney, "giveMoney");
        Intrinsics.checkNotNullParameter(userSex, "userSex");
        Intrinsics.checkNotNullParameter(totalYhq, "totalYhq");
        this.AliAccount = AliAccount;
        this.age = age;
        this.allExperience = i;
        this.channelUid = i2;
        this.coin = coin;
        this.dateline = i3;
        this.email = email;
        this.idCard = idCard;
        this.nickName = nickName;
        this.password = password;
        this.points_balance = points_balance;
        this.realName = realName;
        this.status = status;
        this.telphone = telphone;
        this.totalCoupon = totalCoupon;
        this.welfare = welfare;
        this.uid = i4;
        this.userName = userName;
        this.user_avatar = user_avatar;
        this.wallet_balance = wallet_balance;
        this.trade_pin = trade_pin;
        this.vieLevel = vieLevel;
        this.giveMoney = giveMoney;
        this.userSex = userSex;
        this.is_official = i5;
        this.isMonthCard = i6;
        this.isSaveingCard = i7;
        this.totalYhq = totalYhq;
        this.unset_status = num;
        this.isSign = num2;
        this.unread_msg = z;
        this.vieLevelImg = str;
        this.monthCardImg = str2;
        this.savingCardImg = str3;
        this.birthDay = str4;
    }

    public final String getAliAccount() {
        return this.AliAccount;
    }

    public final String getAge() {
        return this.age;
    }

    public final int getAllExperience() {
        return this.allExperience;
    }

    public final int getChannelUid() {
        return this.channelUid;
    }

    public final String getCoin() {
        return this.coin;
    }

    public final int getDateline() {
        return this.dateline;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getIdCard() {
        return this.idCard;
    }

    public final String getNickName() {
        return this.nickName;
    }

    public final void setNickName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nickName = str;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getPoints_balance() {
        return this.points_balance;
    }

    public final String getRealName() {
        return this.realName;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTelphone() {
        return this.telphone;
    }

    public final String getTotalCoupon() {
        return this.totalCoupon;
    }

    public final String getWelfare() {
        return this.welfare;
    }

    public final int getUid() {
        return this.uid;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final String getUser_avatar() {
        return this.user_avatar;
    }

    public final void setUser_avatar(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.user_avatar = str;
    }

    public final String getWallet_balance() {
        return this.wallet_balance;
    }

    public final String getTrade_pin() {
        return this.trade_pin;
    }

    public final String getVieLevel() {
        return this.vieLevel;
    }

    public final String getGiveMoney() {
        return this.giveMoney;
    }

    public final String getUserSex() {
        return this.userSex;
    }

    public final void setUserSex(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userSex = str;
    }

    public final int is_official() {
        return this.is_official;
    }

    public final int isMonthCard() {
        return this.isMonthCard;
    }

    public final int isSaveingCard() {
        return this.isSaveingCard;
    }

    public /* synthetic */ UserBean(String str, String str2, int i, int i2, String str3, int i3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i4, String str14, String str15, String str16, String str17, String str18, String str19, String str20, int i5, int i6, int i7, String str21, Integer num, Integer num2, boolean z, String str22, String str23, String str24, String str25, int i8, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, i2, str3, i3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, i4, str14, str15, str16, str17, str18, str19, str20, i5, i6, i7, (i8 & 134217728) != 0 ? "--" : str21, (i8 & 268435456) != 0 ? 0 : num, (i8 & 536870912) != 0 ? 0 : num2, (i8 & 1073741824) != 0 ? false : z, (i8 & Integer.MIN_VALUE) != 0 ? "" : str22, (i9 & 1) != 0 ? "" : str23, (i9 & 2) != 0 ? "" : str24, (i9 & 4) != 0 ? "" : str25);
    }

    public final String getTotalYhq() {
        return this.totalYhq;
    }

    public final Integer getUnset_status() {
        return this.unset_status;
    }

    public final Integer isSign() {
        return this.isSign;
    }

    public final boolean getUnread_msg() {
        return this.unread_msg;
    }

    public final String getVieLevelImg() {
        return this.vieLevelImg;
    }

    public final String getMonthCardImg() {
        return this.monthCardImg;
    }

    public final String getSavingCardImg() {
        return this.savingCardImg;
    }

    public final String getBirthDay() {
        return this.birthDay;
    }

    public UserBean() {
        this("", "", 0, 0, "0", 0, "", "", "", "", "", "", "", "", "--", "--", 0, "", "", "--", "", "", "", BooleanUtils.NO, 0, 0, 0, null, null, null, false, null, null, null, null, -134217728, 7, null);
    }

    public final boolean isAuth() {
        return this.realName.length() > 0;
    }

    public final String getEncodePhone() {
        if (TextUtils.isEmpty(this.telphone)) {
            return "";
        }
        String strSubstring = this.telphone.substring(0, 3);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String strSubstring2 = this.telphone.substring(r2.length() - 4, this.telphone.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return strSubstring + "****" + strSubstring2;
    }

    public final boolean checkUserIsVip() {
        return (TextUtils.isEmpty(this.vieLevel) || Intrinsics.areEqual(this.vieLevel, "V0")) ? false : true;
    }

    public final String getUserVipStr() {
        if (checkUserIsVip()) {
            return this.vieLevel + "会员";
        }
        return "开通会员获得更多权限";
    }

    public final boolean hideSqk() {
        return this.isSaveingCard != 1;
    }

    public final boolean hideMonthCard() {
        return this.isMonthCard != 1;
    }

    public final boolean isMe() {
        return this.uid == Constant.INSTANCE.getId();
    }
}
