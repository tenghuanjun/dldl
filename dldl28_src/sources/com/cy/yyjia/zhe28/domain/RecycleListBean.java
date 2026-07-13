package com.cy.yyjia.zhe28.domain;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.volcengine.common.contant.CommonConstants;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecycleListBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0001SB\u0093\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\t\u0012\u0006\u0010\u0013\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u0018J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u00108\u001a\u00020\tHÆ\u0003J\t\u00109\u001a\u00020\tHÆ\u0003J\t\u0010:\u001a\u00020\tHÆ\u0003J\t\u0010;\u001a\u00020\tHÆ\u0003J\t\u0010<\u001a\u00020\tHÆ\u0003J\t\u0010=\u001a\u00020\u0006HÆ\u0003J\t\u0010>\u001a\u00020\u0006HÆ\u0003J\t\u0010?\u001a\u00020\u0017HÆ\u0003J\t\u0010@\u001a\u00020\u0006HÆ\u0003J\t\u0010A\u001a\u00020\u0006HÆ\u0003J\t\u0010B\u001a\u00020\tHÆ\u0003J\t\u0010C\u001a\u00020\u0006HÆ\u0003J\t\u0010D\u001a\u00020\u0006HÆ\u0003J\t\u0010E\u001a\u00020\tHÆ\u0003J\t\u0010F\u001a\u00020\tHÆ\u0003J\t\u0010G\u001a\u00020\u0006HÆ\u0003J¹\u0001\u0010H\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u0017HÆ\u0001J\u0013\u0010I\u001a\u0002002\b\u0010J\u001a\u0004\u0018\u00010KHÖ\u0003J\u0006\u0010L\u001a\u00020\tJ\u0006\u0010M\u001a\u00020NJ\u0006\u0010O\u001a\u00020NJ\u0006\u0010P\u001a\u00020NJ\t\u0010Q\u001a\u00020\u0006HÖ\u0001J\t\u0010R\u001a\u00020\tHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u001cR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0011\u0010\u0011\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010!R&\u0010+\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001c\"\u0004\b-\u0010.R&\u0010/\u001a\u0002002\u0006\u0010/\u001a\u0002008G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001cR\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001c¨\u0006T"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/RecycleListBean;", "Landroidx/databinding/BaseObservable;", "accounts", "", "Lcom/cy/yyjia/zhe28/domain/RecycleListBean$Account;", "createTime", "", CommonConstants.key_gameId, "gamePayType", "", "id", "isHot", "maxMoney", "minMoney", "planId", "rate", "rate_text", "money_text", "max_money_text", "returnType", "sort", "status", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Ljava/util/List;IILjava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILcom/cy/yyjia/zhe28/domain/GameBean;)V", "getAccounts", "()Ljava/util/List;", "getCreateTime", "()I", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "getGamePayType", "()Ljava/lang/String;", "getId", "getMaxMoney", "getMax_money_text", "getMinMoney", "getMoney_text", "getPlanId", "getRate", "getRate_text", "getReturnType", "selectType", "getSelectType", "setSelectType", "(I)V", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getSort", "getStatus", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "getRecycleMoney", "getText1", "Landroid/text/SpannableString;", "getText2", "getText3", "hashCode", "toString", "Account", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class RecycleListBean extends BaseObservable {
    public static final int $stable = 8;
    private final List<Account> accounts;
    private final int createTime;
    private final GameBean game;
    private final int gameId;
    private final String gamePayType;
    private final int id;
    private final int isHot;
    private final String maxMoney;
    private final String max_money_text;
    private final String minMoney;
    private final String money_text;
    private final int planId;
    private final String rate;
    private final String rate_text;
    private final String returnType;
    private int selectType;
    private boolean selected;
    private final int sort;
    private final int status;

    public final List<Account> component1() {
        return this.accounts;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getRate() {
        return this.rate;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRate_text() {
        return this.rate_text;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getMoney_text() {
        return this.money_text;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getMax_money_text() {
        return this.max_money_text;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getReturnType() {
        return this.returnType;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getSort() {
        return this.sort;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGamePayType() {
        return this.gamePayType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getIsHot() {
        return this.isHot;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMaxMoney() {
        return this.maxMoney;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getMinMoney() {
        return this.minMoney;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getPlanId() {
        return this.planId;
    }

    public final RecycleListBean copy(List<Account> accounts, int createTime, int gameId, String gamePayType, int id, int isHot, String maxMoney, String minMoney, int planId, String rate, String rate_text, String money_text, String max_money_text, String returnType, int sort, int status, GameBean game) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(gamePayType, "gamePayType");
        Intrinsics.checkNotNullParameter(maxMoney, "maxMoney");
        Intrinsics.checkNotNullParameter(minMoney, "minMoney");
        Intrinsics.checkNotNullParameter(rate, "rate");
        Intrinsics.checkNotNullParameter(rate_text, "rate_text");
        Intrinsics.checkNotNullParameter(money_text, "money_text");
        Intrinsics.checkNotNullParameter(max_money_text, "max_money_text");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(game, "game");
        return new RecycleListBean(accounts, createTime, gameId, gamePayType, id, isHot, maxMoney, minMoney, planId, rate, rate_text, money_text, max_money_text, returnType, sort, status, game);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecycleListBean)) {
            return false;
        }
        RecycleListBean recycleListBean = (RecycleListBean) other;
        return Intrinsics.areEqual(this.accounts, recycleListBean.accounts) && this.createTime == recycleListBean.createTime && this.gameId == recycleListBean.gameId && Intrinsics.areEqual(this.gamePayType, recycleListBean.gamePayType) && this.id == recycleListBean.id && this.isHot == recycleListBean.isHot && Intrinsics.areEqual(this.maxMoney, recycleListBean.maxMoney) && Intrinsics.areEqual(this.minMoney, recycleListBean.minMoney) && this.planId == recycleListBean.planId && Intrinsics.areEqual(this.rate, recycleListBean.rate) && Intrinsics.areEqual(this.rate_text, recycleListBean.rate_text) && Intrinsics.areEqual(this.money_text, recycleListBean.money_text) && Intrinsics.areEqual(this.max_money_text, recycleListBean.max_money_text) && Intrinsics.areEqual(this.returnType, recycleListBean.returnType) && this.sort == recycleListBean.sort && this.status == recycleListBean.status && Intrinsics.areEqual(this.game, recycleListBean.game);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.accounts.hashCode() * 31) + this.createTime) * 31) + this.gameId) * 31) + this.gamePayType.hashCode()) * 31) + this.id) * 31) + this.isHot) * 31) + this.maxMoney.hashCode()) * 31) + this.minMoney.hashCode()) * 31) + this.planId) * 31) + this.rate.hashCode()) * 31) + this.rate_text.hashCode()) * 31) + this.money_text.hashCode()) * 31) + this.max_money_text.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.sort) * 31) + this.status) * 31) + this.game.hashCode();
    }

    public String toString() {
        return "RecycleListBean(accounts=" + this.accounts + ", createTime=" + this.createTime + ", gameId=" + this.gameId + ", gamePayType=" + this.gamePayType + ", id=" + this.id + ", isHot=" + this.isHot + ", maxMoney=" + this.maxMoney + ", minMoney=" + this.minMoney + ", planId=" + this.planId + ", rate=" + this.rate + ", rate_text=" + this.rate_text + ", money_text=" + this.money_text + ", max_money_text=" + this.max_money_text + ", returnType=" + this.returnType + ", sort=" + this.sort + ", status=" + this.status + ", game=" + this.game + ")";
    }

    public final List<Account> getAccounts() {
        return this.accounts;
    }

    public final int getCreateTime() {
        return this.createTime;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getGamePayType() {
        return this.gamePayType;
    }

    public final int getId() {
        return this.id;
    }

    public final int isHot() {
        return this.isHot;
    }

    public final String getMaxMoney() {
        return this.maxMoney;
    }

    public final String getMinMoney() {
        return this.minMoney;
    }

    public final int getPlanId() {
        return this.planId;
    }

    public final String getRate() {
        return this.rate;
    }

    public final String getRate_text() {
        return this.rate_text;
    }

    public final String getMoney_text() {
        return this.money_text;
    }

    public final String getMax_money_text() {
        return this.max_money_text;
    }

    public final String getReturnType() {
        return this.returnType;
    }

    public final int getSort() {
        return this.sort;
    }

    public final int getStatus() {
        return this.status;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public RecycleListBean(List<Account> accounts, int i, int i2, String gamePayType, int i3, int i4, String maxMoney, String minMoney, int i5, String rate, String rate_text, String money_text, String max_money_text, String returnType, int i6, int i7, GameBean game) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(gamePayType, "gamePayType");
        Intrinsics.checkNotNullParameter(maxMoney, "maxMoney");
        Intrinsics.checkNotNullParameter(minMoney, "minMoney");
        Intrinsics.checkNotNullParameter(rate, "rate");
        Intrinsics.checkNotNullParameter(rate_text, "rate_text");
        Intrinsics.checkNotNullParameter(money_text, "money_text");
        Intrinsics.checkNotNullParameter(max_money_text, "max_money_text");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(game, "game");
        this.accounts = accounts;
        this.createTime = i;
        this.gameId = i2;
        this.gamePayType = gamePayType;
        this.id = i3;
        this.isHot = i4;
        this.maxMoney = maxMoney;
        this.minMoney = minMoney;
        this.planId = i5;
        this.rate = rate;
        this.rate_text = rate_text;
        this.money_text = money_text;
        this.max_money_text = max_money_text;
        this.returnType = returnType;
        this.sort = i6;
        this.status = i7;
        this.game = game;
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
    public final int getSelectType() {
        return this.selectType;
    }

    public final void setSelectType(int i) {
        this.selectType = i;
        notifyPropertyChanged(93);
    }

    public final SpannableString getText1() {
        SpannableString spannableString = new SpannableString(this.rate_text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), 0, this.rate_text.length() - 2, 33);
        return spannableString;
    }

    public final SpannableString getText2() {
        SpannableString spannableString = new SpannableString(this.money_text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), 4, this.money_text.length() - 1, 33);
        return spannableString;
    }

    public final SpannableString getText3() {
        SpannableString spannableString = new SpannableString(this.max_money_text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), 8, this.max_money_text.length(), 33);
        return spannableString;
    }

    public final String getRecycleMoney() {
        BigDecimal bigDecimal = new BigDecimal(0);
        Iterator<Account> it = this.accounts.iterator();
        while (it.hasNext()) {
            bigDecimal = bigDecimal.add(new BigDecimal(it.next().getRecycleMoney()));
            Intrinsics.checkNotNullExpressionValue(bigDecimal, "add(...)");
        }
        return "+" + bigDecimal;
    }

    /* JADX INFO: compiled from: RecycleListBean.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0013\u0010#\u001a\u00020\u00142\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006("}, d2 = {"Lcom/cy/yyjia/zhe28/domain/RecycleListBean$Account;", "Landroidx/databinding/BaseObservable;", "disabled", "", "id", "maxMoney", "", "name", "recycleMoney", "totalMoney", "userMoney", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDisabled", "()I", "getId", "getMaxMoney", "()Ljava/lang/String;", "getName", "getRecycleMoney", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTotalMoney", "getUserMoney", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Account extends BaseObservable {
        public static final int $stable = 8;
        private final int disabled;
        private final int id;
        private final String maxMoney;
        private final String name;
        private final String recycleMoney;
        private boolean selected;
        private final String totalMoney;
        private final String userMoney;

        public static /* synthetic */ Account copy$default(Account account, int i, int i2, String str, String str2, String str3, String str4, String str5, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = account.disabled;
            }
            if ((i3 & 2) != 0) {
                i2 = account.id;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                str = account.maxMoney;
            }
            String str6 = str;
            if ((i3 & 8) != 0) {
                str2 = account.name;
            }
            String str7 = str2;
            if ((i3 & 16) != 0) {
                str3 = account.recycleMoney;
            }
            String str8 = str3;
            if ((i3 & 32) != 0) {
                str4 = account.totalMoney;
            }
            String str9 = str4;
            if ((i3 & 64) != 0) {
                str5 = account.userMoney;
            }
            return account.copy(i, i4, str6, str7, str8, str9, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getDisabled() {
            return this.disabled;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMaxMoney() {
            return this.maxMoney;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getRecycleMoney() {
            return this.recycleMoney;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getTotalMoney() {
            return this.totalMoney;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getUserMoney() {
            return this.userMoney;
        }

        public final Account copy(int disabled, int id, String maxMoney, String name, String recycleMoney, String totalMoney, String userMoney) {
            Intrinsics.checkNotNullParameter(maxMoney, "maxMoney");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(recycleMoney, "recycleMoney");
            Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
            Intrinsics.checkNotNullParameter(userMoney, "userMoney");
            return new Account(disabled, id, maxMoney, name, recycleMoney, totalMoney, userMoney);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Account)) {
                return false;
            }
            Account account = (Account) other;
            return this.disabled == account.disabled && this.id == account.id && Intrinsics.areEqual(this.maxMoney, account.maxMoney) && Intrinsics.areEqual(this.name, account.name) && Intrinsics.areEqual(this.recycleMoney, account.recycleMoney) && Intrinsics.areEqual(this.totalMoney, account.totalMoney) && Intrinsics.areEqual(this.userMoney, account.userMoney);
        }

        public int hashCode() {
            return (((((((((((this.disabled * 31) + this.id) * 31) + this.maxMoney.hashCode()) * 31) + this.name.hashCode()) * 31) + this.recycleMoney.hashCode()) * 31) + this.totalMoney.hashCode()) * 31) + this.userMoney.hashCode();
        }

        public String toString() {
            return "Account(disabled=" + this.disabled + ", id=" + this.id + ", maxMoney=" + this.maxMoney + ", name=" + this.name + ", recycleMoney=" + this.recycleMoney + ", totalMoney=" + this.totalMoney + ", userMoney=" + this.userMoney + ")";
        }

        public final int getDisabled() {
            return this.disabled;
        }

        public final int getId() {
            return this.id;
        }

        public final String getMaxMoney() {
            return this.maxMoney;
        }

        public final String getName() {
            return this.name;
        }

        public final String getRecycleMoney() {
            return this.recycleMoney;
        }

        public final String getTotalMoney() {
            return this.totalMoney;
        }

        public final String getUserMoney() {
            return this.userMoney;
        }

        public Account(int i, int i2, String maxMoney, String name, String recycleMoney, String totalMoney, String userMoney) {
            Intrinsics.checkNotNullParameter(maxMoney, "maxMoney");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(recycleMoney, "recycleMoney");
            Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
            Intrinsics.checkNotNullParameter(userMoney, "userMoney");
            this.disabled = i;
            this.id = i2;
            this.maxMoney = maxMoney;
            this.name = name;
            this.recycleMoney = recycleMoney;
            this.totalMoney = totalMoney;
            this.userMoney = userMoney;
        }

        @Bindable
        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
            notifyPropertyChanged(94);
        }
    }
}
