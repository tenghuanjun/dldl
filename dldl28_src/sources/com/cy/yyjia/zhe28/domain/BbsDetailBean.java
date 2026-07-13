package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J/\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsDetailBean;", "Ljava/io/Serializable;", CommonConstants.VALUE_LEVEL_INFO, "Lcom/cy/yyjia/zhe28/domain/BbsDetailBean$Info;", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "topList", "", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "(Lcom/cy/yyjia/zhe28/domain/BbsDetailBean$Info;Lcom/cy/yyjia/zhe28/domain/GameBean;Ljava/util/List;)V", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "setGame", "(Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "getInfo", "()Lcom/cy/yyjia/zhe28/domain/BbsDetailBean$Info;", "getTopList", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Cate", "Info", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BbsDetailBean implements Serializable {
    public static final int $stable = 8;
    private GameBean game;
    private final Info info;
    private final List<BbsBean> topList;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BbsDetailBean copy$default(BbsDetailBean bbsDetailBean, Info info, GameBean gameBean, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            info = bbsDetailBean.info;
        }
        if ((i & 2) != 0) {
            gameBean = bbsDetailBean.game;
        }
        if ((i & 4) != 0) {
            list = bbsDetailBean.topList;
        }
        return bbsDetailBean.copy(info, gameBean, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Info getInfo() {
        return this.info;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    public final List<BbsBean> component3() {
        return this.topList;
    }

    public final BbsDetailBean copy(Info info, GameBean game, List<BbsBean> topList) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(topList, "topList");
        return new BbsDetailBean(info, game, topList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BbsDetailBean)) {
            return false;
        }
        BbsDetailBean bbsDetailBean = (BbsDetailBean) other;
        return Intrinsics.areEqual(this.info, bbsDetailBean.info) && Intrinsics.areEqual(this.game, bbsDetailBean.game) && Intrinsics.areEqual(this.topList, bbsDetailBean.topList);
    }

    public int hashCode() {
        int iHashCode = this.info.hashCode() * 31;
        GameBean gameBean = this.game;
        return ((iHashCode + (gameBean == null ? 0 : gameBean.hashCode())) * 31) + this.topList.hashCode();
    }

    public String toString() {
        return "BbsDetailBean(info=" + this.info + ", game=" + this.game + ", topList=" + this.topList + ")";
    }

    public BbsDetailBean(Info info, GameBean gameBean, List<BbsBean> topList) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(topList, "topList");
        this.info = info;
        this.game = gameBean;
        this.topList = topList;
    }

    public final Info getInfo() {
        return this.info;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final void setGame(GameBean gameBean) {
        this.game = gameBean;
    }

    public final List<BbsBean> getTopList() {
        return this.topList;
    }

    /* JADX INFO: compiled from: BbsDetailBean.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003Jk\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020\tHÖ\u0001J\t\u0010/\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001c¨\u00060"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsDetailBean$Info;", "Ljava/io/Serializable;", "createTime", "", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "icon", "", "id", "", "name", "bg", "status", "views", "cates", "", "Lcom/cy/yyjia/zhe28/domain/BbsDetailBean$Cate;", "(JLcom/cy/yyjia/zhe28/domain/GameBean;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IILjava/util/List;)V", "getBg", "()Ljava/lang/String;", "getCates", "()Ljava/util/List;", "getCreateTime", "()J", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getIcon", "getId", "()I", "getName", "getStatus", "getViews", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Info implements Serializable {
        public static final int $stable = 8;
        private final String bg;
        private final List<Cate> cates;
        private final long createTime;
        private final GameBean game;
        private final String icon;
        private final int id;
        private final String name;
        private final int status;
        private final int views;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getCreateTime() {
            return this.createTime;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final GameBean getGame() {
            return this.game;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getViews() {
            return this.views;
        }

        public final List<Cate> component9() {
            return this.cates;
        }

        public final Info copy(long createTime, GameBean game, String icon, int id, String name, String bg, int status, int views, List<Cate> cates) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(cates, "cates");
            return new Info(createTime, game, icon, id, name, bg, status, views, cates);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Info)) {
                return false;
            }
            Info info = (Info) other;
            return this.createTime == info.createTime && Intrinsics.areEqual(this.game, info.game) && Intrinsics.areEqual(this.icon, info.icon) && this.id == info.id && Intrinsics.areEqual(this.name, info.name) && Intrinsics.areEqual(this.bg, info.bg) && this.status == info.status && this.views == info.views && Intrinsics.areEqual(this.cates, info.cates);
        }

        public int hashCode() {
            int iM = GMTitleBean$$ExternalSyntheticBackport0.m(this.createTime) * 31;
            GameBean gameBean = this.game;
            return ((((((((((((((iM + (gameBean == null ? 0 : gameBean.hashCode())) * 31) + this.icon.hashCode()) * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.bg.hashCode()) * 31) + this.status) * 31) + this.views) * 31) + this.cates.hashCode();
        }

        public String toString() {
            return "Info(createTime=" + this.createTime + ", game=" + this.game + ", icon=" + this.icon + ", id=" + this.id + ", name=" + this.name + ", bg=" + this.bg + ", status=" + this.status + ", views=" + this.views + ", cates=" + this.cates + ")";
        }

        public Info(long j, GameBean gameBean, String icon, int i, String name, String bg, int i2, int i3, List<Cate> cates) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(cates, "cates");
            this.createTime = j;
            this.game = gameBean;
            this.icon = icon;
            this.id = i;
            this.name = name;
            this.bg = bg;
            this.status = i2;
            this.views = i3;
            this.cates = cates;
        }

        public final long getCreateTime() {
            return this.createTime;
        }

        public final GameBean getGame() {
            return this.game;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getBg() {
            return this.bg;
        }

        public final int getStatus() {
            return this.status;
        }

        public final int getViews() {
            return this.views;
        }

        public final List<Cate> getCates() {
            return this.cates;
        }
    }

    /* JADX INFO: compiled from: BbsDetailBean.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsDetailBean$Cate;", "Ljava/io/Serializable;", "name", "", "id", "", "(Ljava/lang/String;I)V", "getId", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Cate implements Serializable {
        public static final int $stable = 0;
        private final int id;
        private final String name;

        public static /* synthetic */ Cate copy$default(Cate cate, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = cate.name;
            }
            if ((i2 & 2) != 0) {
                i = cate.id;
            }
            return cate.copy(str, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final Cate copy(String name, int id) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Cate(name, id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Cate)) {
                return false;
            }
            Cate cate = (Cate) other;
            return Intrinsics.areEqual(this.name, cate.name) && this.id == cate.id;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.id;
        }

        public String toString() {
            return "Cate(name=" + this.name + ", id=" + this.id + ")";
        }

        public Cate(String name, int i) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.id = i;
        }

        public final String getName() {
            return this.name;
        }

        public final int getId() {
            return this.id;
        }
    }
}
