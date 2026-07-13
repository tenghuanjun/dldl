package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.mobile.auth.gatewayauth.Constant;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GiftBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0007HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u008b\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001J\u0013\u00105\u001a\u00020 2\b\u00106\u001a\u0004\u0018\u000107HÖ\u0003J\u0006\u00108\u001a\u00020\u0003J\u0006\u00109\u001a\u00020\u0003J\t\u0010:\u001a\u00020\u0007HÖ\u0001J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R&\u0010\u001f\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0013¨\u0006<"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GiftBean;", "Landroidx/databinding/BaseObservable;", "account_name", "", "endTime", "game_icon", "game_id", "", "game_name", "gift_code", "gift_code_id", "gift_id", "gift_name", "content_text", Constant.START_TIME, "times", "percent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount_name", "()Ljava/lang/String;", "getContent_text", "getEndTime", "getGame_icon", "getGame_id", "()I", "getGame_name", "getGift_code", "getGift_code_id", "getGift_id", "getGift_name", "getPercent", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getStartTime", "getTimes", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "getAvailableTime", "getPercentStr", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GiftBean extends BaseObservable {
    public static final int $stable = 8;
    private final String account_name;

    @SerializedName(alternate = {DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT}, value = "content_text")
    private final String content_text;
    private final String endTime;

    @SerializedName(alternate = {"gameIcon"}, value = "game_icon")
    private final String game_icon;

    @SerializedName(alternate = {CommonConstants.key_gameId}, value = "game_id")
    private final int game_id;
    private final String game_name;
    private final String gift_code;
    private final int gift_code_id;

    @SerializedName(alternate = {"id"}, value = "gift_id")
    private final int gift_id;

    @SerializedName(alternate = {"gift_name"}, value = "name")
    private final String gift_name;
    private final String percent;
    private boolean selected;
    private final String startTime;
    private final String times;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccount_name() {
        return this.account_name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getContent_text() {
        return this.content_text;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTimes() {
        return this.times;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getPercent() {
        return this.percent;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getGame_icon() {
        return this.game_icon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getGame_id() {
        return this.game_id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getGame_name() {
        return this.game_name;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getGift_code() {
        return this.gift_code;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getGift_code_id() {
        return this.gift_code_id;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getGift_id() {
        return this.gift_id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getGift_name() {
        return this.gift_name;
    }

    public final GiftBean copy(String account_name, String endTime, String game_icon, int game_id, String game_name, String gift_code, int gift_code_id, int gift_id, String gift_name, String content_text, String startTime, String times, String percent) {
        Intrinsics.checkNotNullParameter(account_name, "account_name");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(game_icon, "game_icon");
        Intrinsics.checkNotNullParameter(game_name, "game_name");
        Intrinsics.checkNotNullParameter(gift_code, "gift_code");
        Intrinsics.checkNotNullParameter(gift_name, "gift_name");
        Intrinsics.checkNotNullParameter(content_text, "content_text");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(times, "times");
        Intrinsics.checkNotNullParameter(percent, "percent");
        return new GiftBean(account_name, endTime, game_icon, game_id, game_name, gift_code, gift_code_id, gift_id, gift_name, content_text, startTime, times, percent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GiftBean)) {
            return false;
        }
        GiftBean giftBean = (GiftBean) other;
        return Intrinsics.areEqual(this.account_name, giftBean.account_name) && Intrinsics.areEqual(this.endTime, giftBean.endTime) && Intrinsics.areEqual(this.game_icon, giftBean.game_icon) && this.game_id == giftBean.game_id && Intrinsics.areEqual(this.game_name, giftBean.game_name) && Intrinsics.areEqual(this.gift_code, giftBean.gift_code) && this.gift_code_id == giftBean.gift_code_id && this.gift_id == giftBean.gift_id && Intrinsics.areEqual(this.gift_name, giftBean.gift_name) && Intrinsics.areEqual(this.content_text, giftBean.content_text) && Intrinsics.areEqual(this.startTime, giftBean.startTime) && Intrinsics.areEqual(this.times, giftBean.times) && Intrinsics.areEqual(this.percent, giftBean.percent);
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.account_name.hashCode() * 31) + this.endTime.hashCode()) * 31) + this.game_icon.hashCode()) * 31) + this.game_id) * 31) + this.game_name.hashCode()) * 31) + this.gift_code.hashCode()) * 31) + this.gift_code_id) * 31) + this.gift_id) * 31) + this.gift_name.hashCode()) * 31) + this.content_text.hashCode()) * 31) + this.startTime.hashCode()) * 31) + this.times.hashCode()) * 31) + this.percent.hashCode();
    }

    public String toString() {
        return "GiftBean(account_name=" + this.account_name + ", endTime=" + this.endTime + ", game_icon=" + this.game_icon + ", game_id=" + this.game_id + ", game_name=" + this.game_name + ", gift_code=" + this.gift_code + ", gift_code_id=" + this.gift_code_id + ", gift_id=" + this.gift_id + ", gift_name=" + this.gift_name + ", content_text=" + this.content_text + ", startTime=" + this.startTime + ", times=" + this.times + ", percent=" + this.percent + ")";
    }

    public final String getAccount_name() {
        return this.account_name;
    }

    public final String getEndTime() {
        return this.endTime;
    }

    public final String getGame_icon() {
        return this.game_icon;
    }

    public final int getGame_id() {
        return this.game_id;
    }

    public final String getGame_name() {
        return this.game_name;
    }

    public final String getGift_code() {
        return this.gift_code;
    }

    public final int getGift_code_id() {
        return this.gift_code_id;
    }

    public final int getGift_id() {
        return this.gift_id;
    }

    public final String getGift_name() {
        return this.gift_name;
    }

    public final String getContent_text() {
        return this.content_text;
    }

    public final String getStartTime() {
        return this.startTime;
    }

    public final String getTimes() {
        return this.times;
    }

    public final String getPercent() {
        return this.percent;
    }

    public GiftBean(String account_name, String endTime, String game_icon, int i, String game_name, String gift_code, int i2, int i3, String gift_name, String content_text, String startTime, String times, String percent) {
        Intrinsics.checkNotNullParameter(account_name, "account_name");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(game_icon, "game_icon");
        Intrinsics.checkNotNullParameter(game_name, "game_name");
        Intrinsics.checkNotNullParameter(gift_code, "gift_code");
        Intrinsics.checkNotNullParameter(gift_name, "gift_name");
        Intrinsics.checkNotNullParameter(content_text, "content_text");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(times, "times");
        Intrinsics.checkNotNullParameter(percent, "percent");
        this.account_name = account_name;
        this.endTime = endTime;
        this.game_icon = game_icon;
        this.game_id = i;
        this.game_name = game_name;
        this.gift_code = gift_code;
        this.gift_code_id = i2;
        this.gift_id = i3;
        this.gift_name = gift_name;
        this.content_text = content_text;
        this.startTime = startTime;
        this.times = times;
        this.percent = percent;
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    public final String getAvailableTime() {
        return this.startTime + "至" + this.endTime;
    }

    public final String getPercentStr() {
        return "剩余：" + this.percent + "%";
    }
}
