package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemTradeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BO\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010,\u001a\u00020\u0004HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0004HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0004HÆ\u0003J\t\u00102\u001a\u00020\u0004HÆ\u0003J\t\u00103\u001a\u00020\u0004HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u000eHÆ\u0003Je\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\u0006HÖ\u0001J\t\u0010:\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u001a\u0010\u001f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0011\"\u0004\b!\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0011\"\u0004\b#\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001dR\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0011\"\u0004\b'\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0011\"\u0004\b)\u0010\u0015R\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0011\"\u0004\b+\u0010\u0015¨\u0006;"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemTradeBean;", "", "()V", "createTime", "", CommonConstants.key_gameId, "", "id", "name", "num", "pic", "price", "serviceCode", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "getCreateTime", "()Ljava/lang/String;", SocialConstants.PARAM_COMMENT, "getDescription", "setDescription", "(Ljava/lang/String;)V", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "setGame", "(Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "getGameId", "()I", "setGameId", "(I)V", "getId", "inputNum", "getInputNum", "setInputNum", "getName", "setName", "getNum", "setNum", "getPic", "setPic", "getPrice", "setPrice", "getServiceCode", "setServiceCode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ItemTradeBean {
    public static final int $stable = 8;
    private final String createTime;
    private String description;
    private GameBean game;
    private int gameId;
    private final int id;
    private String inputNum;
    private String name;
    private int num;
    private String pic;
    private String price;
    private String serviceCode;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getNum() {
        return this.num;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getServiceCode() {
        return this.serviceCode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    public final ItemTradeBean copy(String createTime, int gameId, int id, String name, int num, String pic, String price, String serviceCode, GameBean game) {
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        return new ItemTradeBean(createTime, gameId, id, name, num, pic, price, serviceCode, game);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemTradeBean)) {
            return false;
        }
        ItemTradeBean itemTradeBean = (ItemTradeBean) other;
        return Intrinsics.areEqual(this.createTime, itemTradeBean.createTime) && this.gameId == itemTradeBean.gameId && this.id == itemTradeBean.id && Intrinsics.areEqual(this.name, itemTradeBean.name) && this.num == itemTradeBean.num && Intrinsics.areEqual(this.pic, itemTradeBean.pic) && Intrinsics.areEqual(this.price, itemTradeBean.price) && Intrinsics.areEqual(this.serviceCode, itemTradeBean.serviceCode) && Intrinsics.areEqual(this.game, itemTradeBean.game);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((this.createTime.hashCode() * 31) + this.gameId) * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.num) * 31) + this.pic.hashCode()) * 31) + this.price.hashCode()) * 31) + this.serviceCode.hashCode()) * 31;
        GameBean gameBean = this.game;
        return iHashCode + (gameBean == null ? 0 : gameBean.hashCode());
    }

    public String toString() {
        return "ItemTradeBean(createTime=" + this.createTime + ", gameId=" + this.gameId + ", id=" + this.id + ", name=" + this.name + ", num=" + this.num + ", pic=" + this.pic + ", price=" + this.price + ", serviceCode=" + this.serviceCode + ", game=" + this.game + ")";
    }

    public ItemTradeBean(String createTime, int i, int i2, String name, int i3, String pic, String price, String serviceCode, GameBean gameBean) {
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        this.createTime = createTime;
        this.gameId = i;
        this.id = i2;
        this.name = name;
        this.num = i3;
        this.pic = pic;
        this.price = price;
        this.serviceCode = serviceCode;
        this.game = gameBean;
        this.inputNum = "0";
        this.description = "";
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final void setGameId(int i) {
        this.gameId = i;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final int getNum() {
        return this.num;
    }

    public final void setNum(int i) {
        this.num = i;
    }

    public final String getPic() {
        return this.pic;
    }

    public final void setPic(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pic = str;
    }

    public final String getPrice() {
        return this.price;
    }

    public final void setPrice(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.price = str;
    }

    public final String getServiceCode() {
        return this.serviceCode;
    }

    public final void setServiceCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serviceCode = str;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final void setGame(GameBean gameBean) {
        this.game = gameBean;
    }

    public final String getInputNum() {
        return this.inputNum;
    }

    public final void setInputNum(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.inputNum = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    public ItemTradeBean() {
        this("", 0, 0, "", 1, "", "", "", null);
    }
}
