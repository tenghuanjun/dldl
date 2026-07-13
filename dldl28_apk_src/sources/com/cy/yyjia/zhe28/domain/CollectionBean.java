package com.cy.yyjia.zhe28.domain;

import android.view.View;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.cy.yyjia.zhe28.util.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CollectionBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\r¢\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\t\u0010(\u001a\u00020\rHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u000fHÆ\u0003Ju\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\rHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u00020\rHÖ\u0001J\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u000203J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0010\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017¨\u00065"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "name", "", "img_url", "show_type", "type", "link_url", "video_url", "game_list", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "screen", "", "groupbuy", "Lcom/cy/yyjia/zhe28/domain/GroupBuyBean;", "itemType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILcom/cy/yyjia/zhe28/domain/GroupBuyBean;I)V", "getGame_list", "()Ljava/util/List;", "getGroupbuy", "()Lcom/cy/yyjia/zhe28/domain/GroupBuyBean;", "getImg_url", "()Ljava/lang/String;", "getItemType", "()I", "getLink_url", "getName", "getScreen", "getShow_type", "getType", "getVideo_url", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "onPicClick", "", "v", "Landroid/view/View;", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class CollectionBean implements MultiItemEntity {
    public static final int $stable = 8;
    private final List<GameBean> game_list;
    private final GroupBuyBean groupbuy;
    private final String img_url;
    private final int itemType;
    private final String link_url;
    private final String name;
    private final int screen;
    private final String show_type;
    private final String type;
    private final String video_url;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getItemType() {
        return this.itemType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getImg_url() {
        return this.img_url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getShow_type() {
        return this.show_type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLink_url() {
        return this.link_url;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getVideo_url() {
        return this.video_url;
    }

    public final List<GameBean> component7() {
        return this.game_list;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getScreen() {
        return this.screen;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final GroupBuyBean getGroupbuy() {
        return this.groupbuy;
    }

    public final CollectionBean copy(String name, String img_url, String show_type, String type, String link_url, String video_url, List<GameBean> game_list, int screen, GroupBuyBean groupbuy, int itemType) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(img_url, "img_url");
        Intrinsics.checkNotNullParameter(show_type, "show_type");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(link_url, "link_url");
        Intrinsics.checkNotNullParameter(video_url, "video_url");
        Intrinsics.checkNotNullParameter(game_list, "game_list");
        return new CollectionBean(name, img_url, show_type, type, link_url, video_url, game_list, screen, groupbuy, itemType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionBean)) {
            return false;
        }
        CollectionBean collectionBean = (CollectionBean) other;
        return Intrinsics.areEqual(this.name, collectionBean.name) && Intrinsics.areEqual(this.img_url, collectionBean.img_url) && Intrinsics.areEqual(this.show_type, collectionBean.show_type) && Intrinsics.areEqual(this.type, collectionBean.type) && Intrinsics.areEqual(this.link_url, collectionBean.link_url) && Intrinsics.areEqual(this.video_url, collectionBean.video_url) && Intrinsics.areEqual(this.game_list, collectionBean.game_list) && this.screen == collectionBean.screen && Intrinsics.areEqual(this.groupbuy, collectionBean.groupbuy) && this.itemType == collectionBean.itemType;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((this.name.hashCode() * 31) + this.img_url.hashCode()) * 31) + this.show_type.hashCode()) * 31) + this.type.hashCode()) * 31) + this.link_url.hashCode()) * 31) + this.video_url.hashCode()) * 31) + this.game_list.hashCode()) * 31) + this.screen) * 31;
        GroupBuyBean groupBuyBean = this.groupbuy;
        return ((iHashCode + (groupBuyBean == null ? 0 : groupBuyBean.hashCode())) * 31) + this.itemType;
    }

    public String toString() {
        return "CollectionBean(name=" + this.name + ", img_url=" + this.img_url + ", show_type=" + this.show_type + ", type=" + this.type + ", link_url=" + this.link_url + ", video_url=" + this.video_url + ", game_list=" + this.game_list + ", screen=" + this.screen + ", groupbuy=" + this.groupbuy + ", itemType=" + this.itemType + ")";
    }

    public CollectionBean(String name, String img_url, String show_type, String type, String link_url, String video_url, List<GameBean> game_list, int i, GroupBuyBean groupBuyBean, int i2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(img_url, "img_url");
        Intrinsics.checkNotNullParameter(show_type, "show_type");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(link_url, "link_url");
        Intrinsics.checkNotNullParameter(video_url, "video_url");
        Intrinsics.checkNotNullParameter(game_list, "game_list");
        this.name = name;
        this.img_url = img_url;
        this.show_type = show_type;
        this.type = type;
        this.link_url = link_url;
        this.video_url = video_url;
        this.game_list = game_list;
        this.screen = i;
        this.groupbuy = groupBuyBean;
        this.itemType = i2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getImg_url() {
        return this.img_url;
    }

    public final String getShow_type() {
        return this.show_type;
    }

    public final String getType() {
        return this.type;
    }

    public final String getLink_url() {
        return this.link_url;
    }

    public final String getVideo_url() {
        return this.video_url;
    }

    public final List<GameBean> getGame_list() {
        return this.game_list;
    }

    public final int getScreen() {
        return this.screen;
    }

    public final GroupBuyBean getGroupbuy() {
        return this.groupbuy;
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return this.itemType;
    }

    public final void onPicClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (StringsKt.startsWith$default(this.link_url, "http", false, 2, (Object) null)) {
            Util.openWebWithLogin(v.getContext(), "", this.link_url);
        }
        if (StringsKt.contains$default((CharSequence) this.link_url, (CharSequence) "game-detail", false, 2, (Object) null)) {
            String strSubstring = this.link_url.substring(15);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            Util.gotoGame(v.getContext(), Integer.parseInt(strSubstring));
        }
    }
}
