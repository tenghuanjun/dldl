package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.lzy.okgo.model.Progress;
import com.tencent.open.SocialConstants;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CardInfoBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001:\u0005MNOPQB\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00100\u0010\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010¢\u0006\u0002\u0010\u0017J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u0005HÆ\u0003J\u0015\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00100\u0010HÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0007HÆ\u0003J\t\u0010@\u001a\u00020\tHÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010HÆ\u0003J¥\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00100\u00102\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010HÆ\u0001J\u0013\u0010G\u001a\u00020.2\b\u0010H\u001a\u0004\u0018\u00010IHÖ\u0003J\u0006\u0010J\u001a\u00020\u0005J\t\u0010K\u001a\u00020\u0003HÖ\u0001J\t\u0010L\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00100\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010 R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R&\u0010$\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00168G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R&\u0010)\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010 \"\u0004\b+\u0010,R&\u0010-\u001a\u00020.2\u0006\u0010-\u001a\u00020.8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010 R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010 R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u00108¨\u0006R"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardInfoBean;", "Landroidx/databinding/BaseObservable;", "id", "", "title", "", "config", "Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Config;", "repairSign", "Lcom/cy/yyjia/zhe28/domain/CardInfoBean$RepairSign;", "today", "total_money", "isRreceive", "userCard", "Lcom/cy/yyjia/zhe28/domain/CardInfoBean$UserCard;", "cardTags", "", "canCion", "tags", "days", "Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Day;", "list", "Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Price;", "(ILjava/lang/String;Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Config;Lcom/cy/yyjia/zhe28/domain/CardInfoBean$RepairSign;IIILcom/cy/yyjia/zhe28/domain/CardInfoBean$UserCard;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getCanCion", "()Ljava/lang/String;", "getCardTags", "()Ljava/util/List;", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Config;", "getDays", "getId", "()I", "getList", "getRepairSign", "()Lcom/cy/yyjia/zhe28/domain/CardInfoBean$RepairSign;", "selectPrice", "getSelectPrice", "()Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Price;", "setSelectPrice", "(Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Price;)V", "selectPricePosition", "getSelectPricePosition", "setSelectPricePosition", "(I)V", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTags", "getTitle", "getToday", "getTotal_money", "getUserCard", "()Lcom/cy/yyjia/zhe28/domain/CardInfoBean$UserCard;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "getCardText", "hashCode", "toString", "Config", "Day", "Price", "RepairSign", "UserCard", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class CardInfoBean extends BaseObservable {
    public static final int $stable = 8;
    private final String canCion;
    private final List<String> cardTags;
    private final Config config;
    private final List<List<Day>> days;
    private final int id;
    private final int isRreceive;
    private final List<Price> list;
    private final RepairSign repairSign;
    private Price selectPrice;
    private int selectPricePosition;
    private boolean selected;
    private final String tags;
    private final String title;
    private final int today;
    private final int total_money;
    private final UserCard userCard;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getCanCion() {
        return this.canCion;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTags() {
        return this.tags;
    }

    public final List<List<Day>> component12() {
        return this.days;
    }

    public final List<Price> component13() {
        return this.list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Config getConfig() {
        return this.config;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final RepairSign getRepairSign() {
        return this.repairSign;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getToday() {
        return this.today;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getTotal_money() {
        return this.total_money;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getIsRreceive() {
        return this.isRreceive;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final UserCard getUserCard() {
        return this.userCard;
    }

    public final List<String> component9() {
        return this.cardTags;
    }

    public final CardInfoBean copy(int id, String title, Config config, RepairSign repairSign, int today, int total_money, int isRreceive, UserCard userCard, List<String> cardTags, String canCion, String tags, List<List<Day>> days, List<Price> list) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(repairSign, "repairSign");
        Intrinsics.checkNotNullParameter(cardTags, "cardTags");
        Intrinsics.checkNotNullParameter(canCion, "canCion");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(list, "list");
        return new CardInfoBean(id, title, config, repairSign, today, total_money, isRreceive, userCard, cardTags, canCion, tags, days, list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardInfoBean)) {
            return false;
        }
        CardInfoBean cardInfoBean = (CardInfoBean) other;
        return this.id == cardInfoBean.id && Intrinsics.areEqual(this.title, cardInfoBean.title) && Intrinsics.areEqual(this.config, cardInfoBean.config) && Intrinsics.areEqual(this.repairSign, cardInfoBean.repairSign) && this.today == cardInfoBean.today && this.total_money == cardInfoBean.total_money && this.isRreceive == cardInfoBean.isRreceive && Intrinsics.areEqual(this.userCard, cardInfoBean.userCard) && Intrinsics.areEqual(this.cardTags, cardInfoBean.cardTags) && Intrinsics.areEqual(this.canCion, cardInfoBean.canCion) && Intrinsics.areEqual(this.tags, cardInfoBean.tags) && Intrinsics.areEqual(this.days, cardInfoBean.days) && Intrinsics.areEqual(this.list, cardInfoBean.list);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((this.id * 31) + this.title.hashCode()) * 31) + this.config.hashCode()) * 31) + this.repairSign.hashCode()) * 31) + this.today) * 31) + this.total_money) * 31) + this.isRreceive) * 31;
        UserCard userCard = this.userCard;
        return ((((((((((iHashCode + (userCard == null ? 0 : userCard.hashCode())) * 31) + this.cardTags.hashCode()) * 31) + this.canCion.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.days.hashCode()) * 31) + this.list.hashCode();
    }

    public String toString() {
        return "CardInfoBean(id=" + this.id + ", title=" + this.title + ", config=" + this.config + ", repairSign=" + this.repairSign + ", today=" + this.today + ", total_money=" + this.total_money + ", isRreceive=" + this.isRreceive + ", userCard=" + this.userCard + ", cardTags=" + this.cardTags + ", canCion=" + this.canCion + ", tags=" + this.tags + ", days=" + this.days + ", list=" + this.list + ")";
    }

    public final int getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Config getConfig() {
        return this.config;
    }

    public final RepairSign getRepairSign() {
        return this.repairSign;
    }

    public final int getToday() {
        return this.today;
    }

    public final int getTotal_money() {
        return this.total_money;
    }

    public final int isRreceive() {
        return this.isRreceive;
    }

    public final UserCard getUserCard() {
        return this.userCard;
    }

    public final List<String> getCardTags() {
        return this.cardTags;
    }

    public final String getCanCion() {
        return this.canCion;
    }

    public final String getTags() {
        return this.tags;
    }

    public final List<List<Day>> getDays() {
        return this.days;
    }

    public final List<Price> getList() {
        return this.list;
    }

    public CardInfoBean(int i, String title, Config config, RepairSign repairSign, int i2, int i3, int i4, UserCard userCard, List<String> cardTags, String canCion, String tags, List<List<Day>> days, List<Price> list) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(repairSign, "repairSign");
        Intrinsics.checkNotNullParameter(cardTags, "cardTags");
        Intrinsics.checkNotNullParameter(canCion, "canCion");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(list, "list");
        this.id = i;
        this.title = title;
        this.config = config;
        this.repairSign = repairSign;
        this.today = i2;
        this.total_money = i3;
        this.isRreceive = i4;
        this.userCard = userCard;
        this.cardTags = cardTags;
        this.canCion = canCion;
        this.tags = tags;
        this.days = days;
        this.list = list;
        this.selectPrice = list.get(0);
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
    public final Price getSelectPrice() {
        return this.selectPrice;
    }

    public final void setSelectPrice(Price selectPrice) {
        Intrinsics.checkNotNullParameter(selectPrice, "selectPrice");
        this.selectPrice = selectPrice;
        notifyPropertyChanged(91);
    }

    @Bindable
    public final int getSelectPricePosition() {
        return this.selectPricePosition;
    }

    public final void setSelectPricePosition(int i) {
        this.selectPricePosition = i;
        notifyPropertyChanged(92);
    }

    public final String getCardText() {
        Iterator<String> it = this.cardTags.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + "/" + it.next();
        }
        String strSubstring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    /* JADX INFO: compiled from: CardInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b]\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bõ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003¢\u0006\u0002\u0010!J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0003HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0003HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\t\u0010X\u001a\u00020\u0003HÆ\u0003J\t\u0010Y\u001a\u00020\u0003HÆ\u0003J\t\u0010Z\u001a\u00020\u0003HÆ\u0003J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\t\u0010\\\u001a\u00020\u0003HÆ\u0003J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003Jµ\u0002\u0010_\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u0003HÆ\u0001J\u0013\u0010`\u001a\u00020a2\b\u0010b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010c\u001a\u00020\u0003J\u0006\u0010d\u001a\u00020\u0003J\u0006\u0010e\u001a\u00020\u0003J\u0006\u0010f\u001a\u00020\u0003J\t\u0010g\u001a\u00020hHÖ\u0001J\t\u0010i\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010#R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u0011\u0010\u001e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010#R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010#R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010#R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010#R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010#R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010#R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010#R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010#R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010#R\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010#R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010#R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b<\u0010#R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b=\u0010#R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010#R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b?\u0010#R\u0011\u0010 \u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b@\u0010#¨\u0006j"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Config;", "", "blacklist", "", "coin", "couponId", SocialConstants.PARAM_APP_DESC, "month_deliver", "month_deliver_day", "month_desc", "month_original_price", "month_price", "year_deliver", "year_deliver_day", "year_desc", "year_original_price", "year_price", "season_deliver", "season_deliver_day", "season_desc", "season_original_price", "season_price", "trade_point", "week_deliver", "week_deliver_day", "week_desc", "week_original_price", "week_price", "publicize", "week_publicize", "month_publicize", "season_publicize", "year_publicize", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBlacklist", "()Ljava/lang/String;", "getCoin", "getCouponId", "getDesc", "getMonth_deliver", "getMonth_deliver_day", "getMonth_desc", "getMonth_original_price", "getMonth_price", "getMonth_publicize", "getPublicize", "getSeason_deliver", "getSeason_deliver_day", "getSeason_desc", "getSeason_original_price", "getSeason_price", "getSeason_publicize", "getTrade_point", "getWeek_deliver", "getWeek_deliver_day", "getWeek_desc", "getWeek_original_price", "getWeek_price", "getWeek_publicize", "getYear_deliver", "getYear_deliver_day", "getYear_desc", "getYear_original_price", "getYear_price", "getYear_publicize", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getMonthSavingNumber", "getSeasonSavingNumber", "getWeekSavingNumber", "getYearSavingNumber", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Config {
        public static final int $stable = 0;
        private final String blacklist;
        private final String coin;
        private final String couponId;
        private final String desc;
        private final String month_deliver;
        private final String month_deliver_day;
        private final String month_desc;
        private final String month_original_price;
        private final String month_price;
        private final String month_publicize;
        private final String publicize;
        private final String season_deliver;
        private final String season_deliver_day;
        private final String season_desc;
        private final String season_original_price;
        private final String season_price;
        private final String season_publicize;
        private final String trade_point;
        private final String week_deliver;
        private final String week_deliver_day;
        private final String week_desc;
        private final String week_original_price;
        private final String week_price;
        private final String week_publicize;
        private final String year_deliver;
        private final String year_deliver_day;
        private final String year_desc;
        private final String year_original_price;
        private final String year_price;
        private final String year_publicize;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBlacklist() {
            return this.blacklist;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getYear_deliver() {
            return this.year_deliver;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getYear_deliver_day() {
            return this.year_deliver_day;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getYear_desc() {
            return this.year_desc;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getYear_original_price() {
            return this.year_original_price;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getYear_price() {
            return this.year_price;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getSeason_deliver() {
            return this.season_deliver;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getSeason_deliver_day() {
            return this.season_deliver_day;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getSeason_desc() {
            return this.season_desc;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getSeason_original_price() {
            return this.season_original_price;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getSeason_price() {
            return this.season_price;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCoin() {
            return this.coin;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getTrade_point() {
            return this.trade_point;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final String getWeek_deliver() {
            return this.week_deliver;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final String getWeek_deliver_day() {
            return this.week_deliver_day;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final String getWeek_desc() {
            return this.week_desc;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final String getWeek_original_price() {
            return this.week_original_price;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final String getWeek_price() {
            return this.week_price;
        }

        /* JADX INFO: renamed from: component26, reason: from getter */
        public final String getPublicize() {
            return this.publicize;
        }

        /* JADX INFO: renamed from: component27, reason: from getter */
        public final String getWeek_publicize() {
            return this.week_publicize;
        }

        /* JADX INFO: renamed from: component28, reason: from getter */
        public final String getMonth_publicize() {
            return this.month_publicize;
        }

        /* JADX INFO: renamed from: component29, reason: from getter */
        public final String getSeason_publicize() {
            return this.season_publicize;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCouponId() {
            return this.couponId;
        }

        /* JADX INFO: renamed from: component30, reason: from getter */
        public final String getYear_publicize() {
            return this.year_publicize;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMonth_deliver() {
            return this.month_deliver;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMonth_deliver_day() {
            return this.month_deliver_day;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getMonth_desc() {
            return this.month_desc;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getMonth_original_price() {
            return this.month_original_price;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getMonth_price() {
            return this.month_price;
        }

        public final Config copy(String blacklist, String coin, String couponId, String desc, String month_deliver, String month_deliver_day, String month_desc, String month_original_price, String month_price, String year_deliver, String year_deliver_day, String year_desc, String year_original_price, String year_price, String season_deliver, String season_deliver_day, String season_desc, String season_original_price, String season_price, String trade_point, String week_deliver, String week_deliver_day, String week_desc, String week_original_price, String week_price, String publicize, String week_publicize, String month_publicize, String season_publicize, String year_publicize) {
            Intrinsics.checkNotNullParameter(blacklist, "blacklist");
            Intrinsics.checkNotNullParameter(coin, "coin");
            Intrinsics.checkNotNullParameter(couponId, "couponId");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(month_deliver, "month_deliver");
            Intrinsics.checkNotNullParameter(month_deliver_day, "month_deliver_day");
            Intrinsics.checkNotNullParameter(month_desc, "month_desc");
            Intrinsics.checkNotNullParameter(month_original_price, "month_original_price");
            Intrinsics.checkNotNullParameter(month_price, "month_price");
            Intrinsics.checkNotNullParameter(year_deliver, "year_deliver");
            Intrinsics.checkNotNullParameter(year_deliver_day, "year_deliver_day");
            Intrinsics.checkNotNullParameter(year_desc, "year_desc");
            Intrinsics.checkNotNullParameter(year_original_price, "year_original_price");
            Intrinsics.checkNotNullParameter(year_price, "year_price");
            Intrinsics.checkNotNullParameter(season_deliver, "season_deliver");
            Intrinsics.checkNotNullParameter(season_deliver_day, "season_deliver_day");
            Intrinsics.checkNotNullParameter(season_desc, "season_desc");
            Intrinsics.checkNotNullParameter(season_original_price, "season_original_price");
            Intrinsics.checkNotNullParameter(season_price, "season_price");
            Intrinsics.checkNotNullParameter(trade_point, "trade_point");
            Intrinsics.checkNotNullParameter(week_deliver, "week_deliver");
            Intrinsics.checkNotNullParameter(week_deliver_day, "week_deliver_day");
            Intrinsics.checkNotNullParameter(week_desc, "week_desc");
            Intrinsics.checkNotNullParameter(week_original_price, "week_original_price");
            Intrinsics.checkNotNullParameter(week_price, "week_price");
            Intrinsics.checkNotNullParameter(publicize, "publicize");
            Intrinsics.checkNotNullParameter(week_publicize, "week_publicize");
            Intrinsics.checkNotNullParameter(month_publicize, "month_publicize");
            Intrinsics.checkNotNullParameter(season_publicize, "season_publicize");
            Intrinsics.checkNotNullParameter(year_publicize, "year_publicize");
            return new Config(blacklist, coin, couponId, desc, month_deliver, month_deliver_day, month_desc, month_original_price, month_price, year_deliver, year_deliver_day, year_desc, year_original_price, year_price, season_deliver, season_deliver_day, season_desc, season_original_price, season_price, trade_point, week_deliver, week_deliver_day, week_desc, week_original_price, week_price, publicize, week_publicize, month_publicize, season_publicize, year_publicize);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return Intrinsics.areEqual(this.blacklist, config.blacklist) && Intrinsics.areEqual(this.coin, config.coin) && Intrinsics.areEqual(this.couponId, config.couponId) && Intrinsics.areEqual(this.desc, config.desc) && Intrinsics.areEqual(this.month_deliver, config.month_deliver) && Intrinsics.areEqual(this.month_deliver_day, config.month_deliver_day) && Intrinsics.areEqual(this.month_desc, config.month_desc) && Intrinsics.areEqual(this.month_original_price, config.month_original_price) && Intrinsics.areEqual(this.month_price, config.month_price) && Intrinsics.areEqual(this.year_deliver, config.year_deliver) && Intrinsics.areEqual(this.year_deliver_day, config.year_deliver_day) && Intrinsics.areEqual(this.year_desc, config.year_desc) && Intrinsics.areEqual(this.year_original_price, config.year_original_price) && Intrinsics.areEqual(this.year_price, config.year_price) && Intrinsics.areEqual(this.season_deliver, config.season_deliver) && Intrinsics.areEqual(this.season_deliver_day, config.season_deliver_day) && Intrinsics.areEqual(this.season_desc, config.season_desc) && Intrinsics.areEqual(this.season_original_price, config.season_original_price) && Intrinsics.areEqual(this.season_price, config.season_price) && Intrinsics.areEqual(this.trade_point, config.trade_point) && Intrinsics.areEqual(this.week_deliver, config.week_deliver) && Intrinsics.areEqual(this.week_deliver_day, config.week_deliver_day) && Intrinsics.areEqual(this.week_desc, config.week_desc) && Intrinsics.areEqual(this.week_original_price, config.week_original_price) && Intrinsics.areEqual(this.week_price, config.week_price) && Intrinsics.areEqual(this.publicize, config.publicize) && Intrinsics.areEqual(this.week_publicize, config.week_publicize) && Intrinsics.areEqual(this.month_publicize, config.month_publicize) && Intrinsics.areEqual(this.season_publicize, config.season_publicize) && Intrinsics.areEqual(this.year_publicize, config.year_publicize);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.blacklist.hashCode() * 31) + this.coin.hashCode()) * 31) + this.couponId.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.month_deliver.hashCode()) * 31) + this.month_deliver_day.hashCode()) * 31) + this.month_desc.hashCode()) * 31) + this.month_original_price.hashCode()) * 31) + this.month_price.hashCode()) * 31) + this.year_deliver.hashCode()) * 31) + this.year_deliver_day.hashCode()) * 31) + this.year_desc.hashCode()) * 31) + this.year_original_price.hashCode()) * 31) + this.year_price.hashCode()) * 31) + this.season_deliver.hashCode()) * 31) + this.season_deliver_day.hashCode()) * 31) + this.season_desc.hashCode()) * 31) + this.season_original_price.hashCode()) * 31) + this.season_price.hashCode()) * 31) + this.trade_point.hashCode()) * 31) + this.week_deliver.hashCode()) * 31) + this.week_deliver_day.hashCode()) * 31) + this.week_desc.hashCode()) * 31) + this.week_original_price.hashCode()) * 31) + this.week_price.hashCode()) * 31) + this.publicize.hashCode()) * 31) + this.week_publicize.hashCode()) * 31) + this.month_publicize.hashCode()) * 31) + this.season_publicize.hashCode()) * 31) + this.year_publicize.hashCode();
        }

        public String toString() {
            return "Config(blacklist=" + this.blacklist + ", coin=" + this.coin + ", couponId=" + this.couponId + ", desc=" + this.desc + ", month_deliver=" + this.month_deliver + ", month_deliver_day=" + this.month_deliver_day + ", month_desc=" + this.month_desc + ", month_original_price=" + this.month_original_price + ", month_price=" + this.month_price + ", year_deliver=" + this.year_deliver + ", year_deliver_day=" + this.year_deliver_day + ", year_desc=" + this.year_desc + ", year_original_price=" + this.year_original_price + ", year_price=" + this.year_price + ", season_deliver=" + this.season_deliver + ", season_deliver_day=" + this.season_deliver_day + ", season_desc=" + this.season_desc + ", season_original_price=" + this.season_original_price + ", season_price=" + this.season_price + ", trade_point=" + this.trade_point + ", week_deliver=" + this.week_deliver + ", week_deliver_day=" + this.week_deliver_day + ", week_desc=" + this.week_desc + ", week_original_price=" + this.week_original_price + ", week_price=" + this.week_price + ", publicize=" + this.publicize + ", week_publicize=" + this.week_publicize + ", month_publicize=" + this.month_publicize + ", season_publicize=" + this.season_publicize + ", year_publicize=" + this.year_publicize + ")";
        }

        public Config(String blacklist, String coin, String couponId, String desc, String month_deliver, String month_deliver_day, String month_desc, String month_original_price, String month_price, String year_deliver, String year_deliver_day, String year_desc, String year_original_price, String year_price, String season_deliver, String season_deliver_day, String season_desc, String season_original_price, String season_price, String trade_point, String week_deliver, String week_deliver_day, String week_desc, String week_original_price, String week_price, String publicize, String week_publicize, String month_publicize, String season_publicize, String year_publicize) {
            Intrinsics.checkNotNullParameter(blacklist, "blacklist");
            Intrinsics.checkNotNullParameter(coin, "coin");
            Intrinsics.checkNotNullParameter(couponId, "couponId");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(month_deliver, "month_deliver");
            Intrinsics.checkNotNullParameter(month_deliver_day, "month_deliver_day");
            Intrinsics.checkNotNullParameter(month_desc, "month_desc");
            Intrinsics.checkNotNullParameter(month_original_price, "month_original_price");
            Intrinsics.checkNotNullParameter(month_price, "month_price");
            Intrinsics.checkNotNullParameter(year_deliver, "year_deliver");
            Intrinsics.checkNotNullParameter(year_deliver_day, "year_deliver_day");
            Intrinsics.checkNotNullParameter(year_desc, "year_desc");
            Intrinsics.checkNotNullParameter(year_original_price, "year_original_price");
            Intrinsics.checkNotNullParameter(year_price, "year_price");
            Intrinsics.checkNotNullParameter(season_deliver, "season_deliver");
            Intrinsics.checkNotNullParameter(season_deliver_day, "season_deliver_day");
            Intrinsics.checkNotNullParameter(season_desc, "season_desc");
            Intrinsics.checkNotNullParameter(season_original_price, "season_original_price");
            Intrinsics.checkNotNullParameter(season_price, "season_price");
            Intrinsics.checkNotNullParameter(trade_point, "trade_point");
            Intrinsics.checkNotNullParameter(week_deliver, "week_deliver");
            Intrinsics.checkNotNullParameter(week_deliver_day, "week_deliver_day");
            Intrinsics.checkNotNullParameter(week_desc, "week_desc");
            Intrinsics.checkNotNullParameter(week_original_price, "week_original_price");
            Intrinsics.checkNotNullParameter(week_price, "week_price");
            Intrinsics.checkNotNullParameter(publicize, "publicize");
            Intrinsics.checkNotNullParameter(week_publicize, "week_publicize");
            Intrinsics.checkNotNullParameter(month_publicize, "month_publicize");
            Intrinsics.checkNotNullParameter(season_publicize, "season_publicize");
            Intrinsics.checkNotNullParameter(year_publicize, "year_publicize");
            this.blacklist = blacklist;
            this.coin = coin;
            this.couponId = couponId;
            this.desc = desc;
            this.month_deliver = month_deliver;
            this.month_deliver_day = month_deliver_day;
            this.month_desc = month_desc;
            this.month_original_price = month_original_price;
            this.month_price = month_price;
            this.year_deliver = year_deliver;
            this.year_deliver_day = year_deliver_day;
            this.year_desc = year_desc;
            this.year_original_price = year_original_price;
            this.year_price = year_price;
            this.season_deliver = season_deliver;
            this.season_deliver_day = season_deliver_day;
            this.season_desc = season_desc;
            this.season_original_price = season_original_price;
            this.season_price = season_price;
            this.trade_point = trade_point;
            this.week_deliver = week_deliver;
            this.week_deliver_day = week_deliver_day;
            this.week_desc = week_desc;
            this.week_original_price = week_original_price;
            this.week_price = week_price;
            this.publicize = publicize;
            this.week_publicize = week_publicize;
            this.month_publicize = month_publicize;
            this.season_publicize = season_publicize;
            this.year_publicize = year_publicize;
        }

        public final String getBlacklist() {
            return this.blacklist;
        }

        public final String getCoin() {
            return this.coin;
        }

        public final String getCouponId() {
            return this.couponId;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getMonth_deliver() {
            return this.month_deliver;
        }

        public final String getMonth_deliver_day() {
            return this.month_deliver_day;
        }

        public final String getMonth_desc() {
            return this.month_desc;
        }

        public final String getMonth_original_price() {
            return this.month_original_price;
        }

        public final String getMonth_price() {
            return this.month_price;
        }

        public final String getYear_deliver() {
            return this.year_deliver;
        }

        public final String getYear_deliver_day() {
            return this.year_deliver_day;
        }

        public final String getYear_desc() {
            return this.year_desc;
        }

        public final String getYear_original_price() {
            return this.year_original_price;
        }

        public final String getYear_price() {
            return this.year_price;
        }

        public final String getSeason_deliver() {
            return this.season_deliver;
        }

        public final String getSeason_deliver_day() {
            return this.season_deliver_day;
        }

        public final String getSeason_desc() {
            return this.season_desc;
        }

        public final String getSeason_original_price() {
            return this.season_original_price;
        }

        public final String getSeason_price() {
            return this.season_price;
        }

        public final String getTrade_point() {
            return this.trade_point;
        }

        public final String getWeek_deliver() {
            return this.week_deliver;
        }

        public final String getWeek_deliver_day() {
            return this.week_deliver_day;
        }

        public final String getWeek_desc() {
            return this.week_desc;
        }

        public final String getWeek_original_price() {
            return this.week_original_price;
        }

        public final String getWeek_price() {
            return this.week_price;
        }

        public final String getPublicize() {
            return this.publicize;
        }

        public final String getWeek_publicize() {
            return this.week_publicize;
        }

        public final String getMonth_publicize() {
            return this.month_publicize;
        }

        public final String getSeason_publicize() {
            return this.season_publicize;
        }

        public final String getYear_publicize() {
            return this.year_publicize;
        }

        public final String getWeekSavingNumber() {
            return "立省" + new BigDecimal(this.week_original_price).subtract(new BigDecimal(this.week_price)) + "元";
        }

        public final String getMonthSavingNumber() {
            return "立省" + new BigDecimal(this.month_original_price).subtract(new BigDecimal(this.month_price)) + "元";
        }

        public final String getSeasonSavingNumber() {
            return "立省" + new BigDecimal(this.season_original_price).subtract(new BigDecimal(this.season_price)) + "元";
        }

        public final String getYearSavingNumber() {
            return "立省" + new BigDecimal(this.year_original_price).subtract(new BigDecimal(this.year_price)) + "元";
        }
    }

    /* JADX INFO: compiled from: CardInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0012HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardInfoBean$RepairSign;", "", "my", "", "total", "(II)V", "getMy", "()I", "setMy", "(I)V", "getTotal", "component1", "component2", "copy", "equals", "", "other", "getText", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class RepairSign {
        public static final int $stable = 8;
        private int my;
        private final int total;

        public static /* synthetic */ RepairSign copy$default(RepairSign repairSign, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = repairSign.my;
            }
            if ((i3 & 2) != 0) {
                i2 = repairSign.total;
            }
            return repairSign.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMy() {
            return this.my;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        public final RepairSign copy(int my, int total) {
            return new RepairSign(my, total);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RepairSign)) {
                return false;
            }
            RepairSign repairSign = (RepairSign) other;
            return this.my == repairSign.my && this.total == repairSign.total;
        }

        public int hashCode() {
            return (this.my * 31) + this.total;
        }

        public String toString() {
            return "RepairSign(my=" + this.my + ", total=" + this.total + ")";
        }

        public RepairSign(int i, int i2) {
            this.my = i;
            this.total = i2;
        }

        public final int getMy() {
            return this.my;
        }

        public final void setMy(int i) {
            this.my = i;
        }

        public final int getTotal() {
            return this.total;
        }

        public final String getText() {
            return "补签卡（" + this.my + "/" + this.total + "）";
        }
    }

    /* JADX INFO: compiled from: CardInfoBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u008b\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u00102\u001a\u00020\u0005J\t\u00103\u001a\u00020\u0003HÖ\u0001J\t\u00104\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013¨\u00065"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardInfoBean$UserCard;", "", "channelUid", "", "create_time", "", MetricsSQLiteCacheKt.METRICS_END_TIME, "format_end_time", "id", "isstop", "module", "money", "orderId", "payType", MetricsSQLiteCacheKt.METRICS_START_TIME, "type", "uid", "(ILjava/lang/String;ILjava/lang/String;IIILjava/lang/String;Ljava/lang/String;IIII)V", "getChannelUid", "()I", "getCreate_time", "()Ljava/lang/String;", "getEnd_time", "getFormat_end_time", "getId", "getIsstop", "getModule", "getMoney", "getOrderId", "getPayType", "getStart_time", "getType", "getUid", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getCardTimeStr", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class UserCard {
        public static final int $stable = 0;
        private final int channelUid;
        private final String create_time;
        private final int end_time;
        private final String format_end_time;
        private final int id;
        private final int isstop;
        private final int module;
        private final String money;
        private final String orderId;
        private final int payType;
        private final int start_time;
        private final int type;
        private final int uid;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getChannelUid() {
            return this.channelUid;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getPayType() {
            return this.payType;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getStart_time() {
            return this.start_time;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCreate_time() {
            return this.create_time;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getEnd_time() {
            return this.end_time;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFormat_end_time() {
            return this.format_end_time;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getIsstop() {
            return this.isstop;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getModule() {
            return this.module;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getMoney() {
            return this.money;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getOrderId() {
            return this.orderId;
        }

        public final UserCard copy(int channelUid, String create_time, int end_time, String format_end_time, int id, int isstop, int module, String money, String orderId, int payType, int start_time, int type, int uid) {
            Intrinsics.checkNotNullParameter(create_time, "create_time");
            Intrinsics.checkNotNullParameter(format_end_time, "format_end_time");
            Intrinsics.checkNotNullParameter(money, "money");
            Intrinsics.checkNotNullParameter(orderId, "orderId");
            return new UserCard(channelUid, create_time, end_time, format_end_time, id, isstop, module, money, orderId, payType, start_time, type, uid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserCard)) {
                return false;
            }
            UserCard userCard = (UserCard) other;
            return this.channelUid == userCard.channelUid && Intrinsics.areEqual(this.create_time, userCard.create_time) && this.end_time == userCard.end_time && Intrinsics.areEqual(this.format_end_time, userCard.format_end_time) && this.id == userCard.id && this.isstop == userCard.isstop && this.module == userCard.module && Intrinsics.areEqual(this.money, userCard.money) && Intrinsics.areEqual(this.orderId, userCard.orderId) && this.payType == userCard.payType && this.start_time == userCard.start_time && this.type == userCard.type && this.uid == userCard.uid;
        }

        public int hashCode() {
            return (((((((((((((((((((((((this.channelUid * 31) + this.create_time.hashCode()) * 31) + this.end_time) * 31) + this.format_end_time.hashCode()) * 31) + this.id) * 31) + this.isstop) * 31) + this.module) * 31) + this.money.hashCode()) * 31) + this.orderId.hashCode()) * 31) + this.payType) * 31) + this.start_time) * 31) + this.type) * 31) + this.uid;
        }

        public String toString() {
            return "UserCard(channelUid=" + this.channelUid + ", create_time=" + this.create_time + ", end_time=" + this.end_time + ", format_end_time=" + this.format_end_time + ", id=" + this.id + ", isstop=" + this.isstop + ", module=" + this.module + ", money=" + this.money + ", orderId=" + this.orderId + ", payType=" + this.payType + ", start_time=" + this.start_time + ", type=" + this.type + ", uid=" + this.uid + ")";
        }

        public UserCard(int i, String create_time, int i2, String format_end_time, int i3, int i4, int i5, String money, String orderId, int i6, int i7, int i8, int i9) {
            Intrinsics.checkNotNullParameter(create_time, "create_time");
            Intrinsics.checkNotNullParameter(format_end_time, "format_end_time");
            Intrinsics.checkNotNullParameter(money, "money");
            Intrinsics.checkNotNullParameter(orderId, "orderId");
            this.channelUid = i;
            this.create_time = create_time;
            this.end_time = i2;
            this.format_end_time = format_end_time;
            this.id = i3;
            this.isstop = i4;
            this.module = i5;
            this.money = money;
            this.orderId = orderId;
            this.payType = i6;
            this.start_time = i7;
            this.type = i8;
            this.uid = i9;
        }

        public final int getChannelUid() {
            return this.channelUid;
        }

        public final String getCreate_time() {
            return this.create_time;
        }

        public final int getEnd_time() {
            return this.end_time;
        }

        public final String getFormat_end_time() {
            return this.format_end_time;
        }

        public final int getId() {
            return this.id;
        }

        public final int getIsstop() {
            return this.isstop;
        }

        public final int getModule() {
            return this.module;
        }

        public final String getMoney() {
            return this.money;
        }

        public final String getOrderId() {
            return this.orderId;
        }

        public final int getPayType() {
            return this.payType;
        }

        public final int getStart_time() {
            return this.start_time;
        }

        public final int getType() {
            return this.type;
        }

        public final int getUid() {
            return this.uid;
        }

        public final String getCardTimeStr() {
            return this.format_end_time + "到期";
        }
    }

    /* JADX INFO: compiled from: CardInfoBean.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003Je\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010(\u001a\u00020\u00172\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020\fHÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000f¨\u0006-"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Price;", "Landroidx/databinding/BaseObservable;", "name", "", "price", "original_price", SocialConstants.PARAM_APP_DESC, "publicize", "type", "reduction", Progress.TAG, "isNew", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getDesc", "()Ljava/lang/String;", "()I", "getName", "getOriginal_price", "getPrice", "getPublicize", "getReduction", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTag", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Price extends BaseObservable {
        public static final int $stable = 8;
        private final String desc;
        private final int isNew;
        private final String name;
        private final String original_price;
        private final String price;
        private final String publicize;
        private final String reduction;
        private boolean selected;
        private final String tag;
        private final String type;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPrice() {
            return this.price;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getOriginal_price() {
            return this.original_price;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPublicize() {
            return this.publicize;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getReduction() {
            return this.reduction;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getIsNew() {
            return this.isNew;
        }

        public final Price copy(String name, String price, String original_price, String desc, String publicize, String type, String reduction, String tag, int isNew) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(price, "price");
            Intrinsics.checkNotNullParameter(original_price, "original_price");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(publicize, "publicize");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(reduction, "reduction");
            return new Price(name, price, original_price, desc, publicize, type, reduction, tag, isNew);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Price)) {
                return false;
            }
            Price price = (Price) other;
            return Intrinsics.areEqual(this.name, price.name) && Intrinsics.areEqual(this.price, price.price) && Intrinsics.areEqual(this.original_price, price.original_price) && Intrinsics.areEqual(this.desc, price.desc) && Intrinsics.areEqual(this.publicize, price.publicize) && Intrinsics.areEqual(this.type, price.type) && Intrinsics.areEqual(this.reduction, price.reduction) && Intrinsics.areEqual(this.tag, price.tag) && this.isNew == price.isNew;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((this.name.hashCode() * 31) + this.price.hashCode()) * 31) + this.original_price.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.publicize.hashCode()) * 31) + this.type.hashCode()) * 31) + this.reduction.hashCode()) * 31;
            String str = this.tag;
            return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.isNew;
        }

        public String toString() {
            return "Price(name=" + this.name + ", price=" + this.price + ", original_price=" + this.original_price + ", desc=" + this.desc + ", publicize=" + this.publicize + ", type=" + this.type + ", reduction=" + this.reduction + ", tag=" + this.tag + ", isNew=" + this.isNew + ")";
        }

        public final String getName() {
            return this.name;
        }

        public final String getPrice() {
            return this.price;
        }

        public final String getOriginal_price() {
            return this.original_price;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getPublicize() {
            return this.publicize;
        }

        public final String getType() {
            return this.type;
        }

        public final String getReduction() {
            return this.reduction;
        }

        public final String getTag() {
            return this.tag;
        }

        public final int isNew() {
            return this.isNew;
        }

        public Price(String name, String price, String original_price, String desc, String publicize, String type, String reduction, String str, int i) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(price, "price");
            Intrinsics.checkNotNullParameter(original_price, "original_price");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(publicize, "publicize");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(reduction, "reduction");
            this.name = name;
            this.price = price;
            this.original_price = original_price;
            this.desc = desc;
            this.publicize = publicize;
            this.type = type;
            this.reduction = reduction;
            this.tag = str;
            this.isNew = i;
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

    /* JADX INFO: compiled from: CardInfoBean.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010&\u001a\u00020\u0003J\u0006\u0010'\u001a\u00020\u0003J\t\u0010(\u001a\u00020\u0007HÖ\u0001J\u0006\u0010)\u001a\u00020\u0005J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015¨\u0006+"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardInfoBean$Day;", "", "day", "", "isReceived", "", "isUse", "", "renewal", "time", "coin", "reward", "(Ljava/lang/String;ZIIILjava/lang/String;Ljava/lang/String;)V", "getCoin", "()Ljava/lang/String;", "setCoin", "(Ljava/lang/String;)V", "getDay", "()Z", "setReceived", "(Z)V", "()I", "setUse", "(I)V", "getRenewal", "getReward", "setReward", "getTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "getRewardName", "getShowText", "hashCode", "isEnabled", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Day {
        public static final int $stable = 8;
        private String coin;
        private final String day;
        private boolean isReceived;
        private int isUse;
        private final int renewal;
        private String reward;
        private final int time;

        public static /* synthetic */ Day copy$default(Day day, String str, boolean z, int i, int i2, int i3, String str2, String str3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = day.day;
            }
            if ((i4 & 2) != 0) {
                z = day.isReceived;
            }
            boolean z2 = z;
            if ((i4 & 4) != 0) {
                i = day.isUse;
            }
            int i5 = i;
            if ((i4 & 8) != 0) {
                i2 = day.renewal;
            }
            int i6 = i2;
            if ((i4 & 16) != 0) {
                i3 = day.time;
            }
            int i7 = i3;
            if ((i4 & 32) != 0) {
                str2 = day.coin;
            }
            String str4 = str2;
            if ((i4 & 64) != 0) {
                str3 = day.reward;
            }
            return day.copy(str, z2, i5, i6, i7, str4, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDay() {
            return this.day;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReceived() {
            return this.isReceived;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getIsUse() {
            return this.isUse;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getRenewal() {
            return this.renewal;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTime() {
            return this.time;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCoin() {
            return this.coin;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getReward() {
            return this.reward;
        }

        public final Day copy(String day, boolean isReceived, int isUse, int renewal, int time, String coin, String reward) {
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(coin, "coin");
            Intrinsics.checkNotNullParameter(reward, "reward");
            return new Day(day, isReceived, isUse, renewal, time, coin, reward);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Day)) {
                return false;
            }
            Day day = (Day) other;
            return Intrinsics.areEqual(this.day, day.day) && this.isReceived == day.isReceived && this.isUse == day.isUse && this.renewal == day.renewal && this.time == day.time && Intrinsics.areEqual(this.coin, day.coin) && Intrinsics.areEqual(this.reward, day.reward);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v9 */
        public int hashCode() {
            int iHashCode = this.day.hashCode() * 31;
            boolean z = this.isReceived;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return ((((((((((iHashCode + r1) * 31) + this.isUse) * 31) + this.renewal) * 31) + this.time) * 31) + this.coin.hashCode()) * 31) + this.reward.hashCode();
        }

        public String toString() {
            return "Day(day=" + this.day + ", isReceived=" + this.isReceived + ", isUse=" + this.isUse + ", renewal=" + this.renewal + ", time=" + this.time + ", coin=" + this.coin + ", reward=" + this.reward + ")";
        }

        public Day(String day, boolean z, int i, int i2, int i3, String coin, String reward) {
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(coin, "coin");
            Intrinsics.checkNotNullParameter(reward, "reward");
            this.day = day;
            this.isReceived = z;
            this.isUse = i;
            this.renewal = i2;
            this.time = i3;
            this.coin = coin;
            this.reward = reward;
        }

        public final String getDay() {
            return this.day;
        }

        public final boolean isReceived() {
            return this.isReceived;
        }

        public final void setReceived(boolean z) {
            this.isReceived = z;
        }

        public final int isUse() {
            return this.isUse;
        }

        public final void setUse(int i) {
            this.isUse = i;
        }

        public final int getRenewal() {
            return this.renewal;
        }

        public final int getTime() {
            return this.time;
        }

        public final String getCoin() {
            return this.coin;
        }

        public final void setCoin(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.coin = str;
        }

        public final String getReward() {
            return this.reward;
        }

        public final void setReward(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.reward = str;
        }

        public final boolean isEnabled() {
            return !(Intrinsics.areEqual(getShowText(), "已领") ? true : Intrinsics.areEqual(r0, "已过期"));
        }

        public final String getRewardName() {
            if (this.isUse == 1) {
                return this.reward;
            }
            return "已过期";
        }

        public final String getShowText() {
            if (this.isReceived) {
                return "已领";
            }
            String str = new SimpleDateFormat("yyyyMMdd").format(new Date());
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            int i = Integer.parseInt(str);
            int i2 = this.time;
            if (i > i2) {
                return "已过期";
            }
            if (i == i2) {
                return "今日";
            }
            if (this.renewal == 1) {
                return "续购";
            }
            return this.day;
        }
    }
}
