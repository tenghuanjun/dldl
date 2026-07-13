package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0003678B}\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u0091\u0001\u0010/\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u00069"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/HomeBean;", "", "top_banner", "", "Lcom/cy/yyjia/zhe28/domain/BannerBean;", "top_nav", "Lcom/cy/yyjia/zhe28/domain/FunBean;", "recommend_list", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "nav_category", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "heavy_recommend", "Lcom/cy/yyjia/zhe28/domain/HomeBean$Game1;", "topic", "Lcom/cy/yyjia/zhe28/domain/TopicBean;", "tryList", "Lcom/cy/yyjia/zhe28/domain/HomeBean$TryBlock;", "gameMore", "Lcom/cy/yyjia/zhe28/domain/HomeBean$CateBlock;", "ad_card", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/HomeBean$Game1;Lcom/cy/yyjia/zhe28/domain/TopicBean;Lcom/cy/yyjia/zhe28/domain/HomeBean$TryBlock;Ljava/util/List;Ljava/lang/String;)V", "getAd_card", "()Ljava/lang/String;", "getGameMore", "()Ljava/util/List;", "getHeavy_recommend", "()Lcom/cy/yyjia/zhe28/domain/HomeBean$Game1;", "setHeavy_recommend", "(Lcom/cy/yyjia/zhe28/domain/HomeBean$Game1;)V", "getNav_category", "getRecommend_list", "getTop_banner", "getTop_nav", "getTopic", "()Lcom/cy/yyjia/zhe28/domain/TopicBean;", "getTryList", "()Lcom/cy/yyjia/zhe28/domain/HomeBean$TryBlock;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "CateBlock", "Game1", "TryBlock", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class HomeBean {
    public static final int $stable = 8;
    private final String ad_card;
    private final List<CateBlock> gameMore;
    private Game1 heavy_recommend;
    private final List<TypeBean> nav_category;
    private final List<CollectionBean> recommend_list;
    private final List<BannerBean> top_banner;
    private final List<FunBean> top_nav;
    private final TopicBean topic;
    private final TryBlock tryList;

    public final List<BannerBean> component1() {
        return this.top_banner;
    }

    public final List<FunBean> component2() {
        return this.top_nav;
    }

    public final List<CollectionBean> component3() {
        return this.recommend_list;
    }

    public final List<TypeBean> component4() {
        return this.nav_category;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Game1 getHeavy_recommend() {
        return this.heavy_recommend;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TopicBean getTopic() {
        return this.topic;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TryBlock getTryList() {
        return this.tryList;
    }

    public final List<CateBlock> component8() {
        return this.gameMore;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getAd_card() {
        return this.ad_card;
    }

    public final HomeBean copy(List<BannerBean> top_banner, List<FunBean> top_nav, List<CollectionBean> recommend_list, List<TypeBean> nav_category, Game1 heavy_recommend, TopicBean topic, TryBlock tryList, List<CateBlock> gameMore, String ad_card) {
        Intrinsics.checkNotNullParameter(nav_category, "nav_category");
        return new HomeBean(top_banner, top_nav, recommend_list, nav_category, heavy_recommend, topic, tryList, gameMore, ad_card);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeBean)) {
            return false;
        }
        HomeBean homeBean = (HomeBean) other;
        return Intrinsics.areEqual(this.top_banner, homeBean.top_banner) && Intrinsics.areEqual(this.top_nav, homeBean.top_nav) && Intrinsics.areEqual(this.recommend_list, homeBean.recommend_list) && Intrinsics.areEqual(this.nav_category, homeBean.nav_category) && Intrinsics.areEqual(this.heavy_recommend, homeBean.heavy_recommend) && Intrinsics.areEqual(this.topic, homeBean.topic) && Intrinsics.areEqual(this.tryList, homeBean.tryList) && Intrinsics.areEqual(this.gameMore, homeBean.gameMore) && Intrinsics.areEqual(this.ad_card, homeBean.ad_card);
    }

    public int hashCode() {
        List<BannerBean> list = this.top_banner;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<FunBean> list2 = this.top_nav;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<CollectionBean> list3 = this.recommend_list;
        int iHashCode3 = (((iHashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31) + this.nav_category.hashCode()) * 31;
        Game1 game1 = this.heavy_recommend;
        int iHashCode4 = (iHashCode3 + (game1 == null ? 0 : game1.hashCode())) * 31;
        TopicBean topicBean = this.topic;
        int iHashCode5 = (iHashCode4 + (topicBean == null ? 0 : topicBean.hashCode())) * 31;
        TryBlock tryBlock = this.tryList;
        int iHashCode6 = (iHashCode5 + (tryBlock == null ? 0 : tryBlock.hashCode())) * 31;
        List<CateBlock> list4 = this.gameMore;
        int iHashCode7 = (iHashCode6 + (list4 == null ? 0 : list4.hashCode())) * 31;
        String str = this.ad_card;
        return iHashCode7 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "HomeBean(top_banner=" + this.top_banner + ", top_nav=" + this.top_nav + ", recommend_list=" + this.recommend_list + ", nav_category=" + this.nav_category + ", heavy_recommend=" + this.heavy_recommend + ", topic=" + this.topic + ", tryList=" + this.tryList + ", gameMore=" + this.gameMore + ", ad_card=" + this.ad_card + ")";
    }

    public HomeBean(List<BannerBean> list, List<FunBean> list2, List<CollectionBean> list3, List<TypeBean> nav_category, Game1 game1, TopicBean topicBean, TryBlock tryBlock, List<CateBlock> list4, String str) {
        Intrinsics.checkNotNullParameter(nav_category, "nav_category");
        this.top_banner = list;
        this.top_nav = list2;
        this.recommend_list = list3;
        this.nav_category = nav_category;
        this.heavy_recommend = game1;
        this.topic = topicBean;
        this.tryList = tryBlock;
        this.gameMore = list4;
        this.ad_card = str;
    }

    public final List<BannerBean> getTop_banner() {
        return this.top_banner;
    }

    public final List<FunBean> getTop_nav() {
        return this.top_nav;
    }

    public final List<CollectionBean> getRecommend_list() {
        return this.recommend_list;
    }

    public final List<TypeBean> getNav_category() {
        return this.nav_category;
    }

    public final Game1 getHeavy_recommend() {
        return this.heavy_recommend;
    }

    public final void setHeavy_recommend(Game1 game1) {
        this.heavy_recommend = game1;
    }

    public final TopicBean getTopic() {
        return this.topic;
    }

    public final TryBlock getTryList() {
        return this.tryList;
    }

    public final List<CateBlock> getGameMore() {
        return this.gameMore;
    }

    public /* synthetic */ HomeBean(List list, List list2, List list3, List list4, Game1 game1, TopicBean topicBean, TryBlock tryBlock, List list5, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, list3, list4, game1, topicBean, tryBlock, list5, (i & 256) != 0 ? "" : str);
    }

    public final String getAd_card() {
        return this.ad_card;
    }

    /* JADX INFO: compiled from: HomeBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/HomeBean$Game1;", "", "title", "", "detail", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Ljava/lang/String;Ljava/util/List;)V", "getDetail", "()Ljava/util/List;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Game1 {
        public static final int $stable = 8;
        private final List<GameBean> detail;
        private final String title;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Game1 copy$default(Game1 game1, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = game1.title;
            }
            if ((i & 2) != 0) {
                list = game1.detail;
            }
            return game1.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final List<GameBean> component2() {
            return this.detail;
        }

        public final Game1 copy(String title, List<GameBean> detail) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(detail, "detail");
            return new Game1(title, detail);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Game1)) {
                return false;
            }
            Game1 game1 = (Game1) other;
            return Intrinsics.areEqual(this.title, game1.title) && Intrinsics.areEqual(this.detail, game1.detail);
        }

        public int hashCode() {
            return (this.title.hashCode() * 31) + this.detail.hashCode();
        }

        public String toString() {
            return "Game1(title=" + this.title + ", detail=" + this.detail + ")";
        }

        public Game1(String title, List<GameBean> detail) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(detail, "detail");
            this.title = title;
            this.detail = detail;
        }

        public final String getTitle() {
            return this.title;
        }

        public final List<GameBean> getDetail() {
            return this.detail;
        }
    }

    /* JADX INFO: compiled from: HomeBean.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/HomeBean$TryBlock;", "", "awardType", "", "maxRewardValue", "gameList", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAwardType", "()Ljava/lang/String;", "getGameList", "()Ljava/util/List;", "getMaxRewardValue", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class TryBlock {
        public static final int $stable = 8;
        private final String awardType;
        private final List<GameBean> gameList;
        private final String maxRewardValue;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TryBlock copy$default(TryBlock tryBlock, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tryBlock.awardType;
            }
            if ((i & 2) != 0) {
                str2 = tryBlock.maxRewardValue;
            }
            if ((i & 4) != 0) {
                list = tryBlock.gameList;
            }
            return tryBlock.copy(str, str2, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAwardType() {
            return this.awardType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMaxRewardValue() {
            return this.maxRewardValue;
        }

        public final List<GameBean> component3() {
            return this.gameList;
        }

        public final TryBlock copy(String awardType, String maxRewardValue, List<GameBean> gameList) {
            Intrinsics.checkNotNullParameter(awardType, "awardType");
            Intrinsics.checkNotNullParameter(maxRewardValue, "maxRewardValue");
            Intrinsics.checkNotNullParameter(gameList, "gameList");
            return new TryBlock(awardType, maxRewardValue, gameList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TryBlock)) {
                return false;
            }
            TryBlock tryBlock = (TryBlock) other;
            return Intrinsics.areEqual(this.awardType, tryBlock.awardType) && Intrinsics.areEqual(this.maxRewardValue, tryBlock.maxRewardValue) && Intrinsics.areEqual(this.gameList, tryBlock.gameList);
        }

        public int hashCode() {
            return (((this.awardType.hashCode() * 31) + this.maxRewardValue.hashCode()) * 31) + this.gameList.hashCode();
        }

        public String toString() {
            return "TryBlock(awardType=" + this.awardType + ", maxRewardValue=" + this.maxRewardValue + ", gameList=" + this.gameList + ")";
        }

        public TryBlock(String awardType, String maxRewardValue, List<GameBean> gameList) {
            Intrinsics.checkNotNullParameter(awardType, "awardType");
            Intrinsics.checkNotNullParameter(maxRewardValue, "maxRewardValue");
            Intrinsics.checkNotNullParameter(gameList, "gameList");
            this.awardType = awardType;
            this.maxRewardValue = maxRewardValue;
            this.gameList = gameList;
        }

        public final String getAwardType() {
            return this.awardType;
        }

        public final String getMaxRewardValue() {
            return this.maxRewardValue;
        }

        public final List<GameBean> getGameList() {
            return this.gameList;
        }
    }

    /* JADX INFO: compiled from: HomeBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/HomeBean$CateBlock;", "", "cateId", "", "bg", "", "name", "gamelist", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getBg", "()Ljava/lang/String;", "getCateId", "()I", "getGamelist", "()Ljava/util/List;", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CateBlock {
        public static final int $stable = 8;
        private final String bg;
        private final int cateId;
        private final List<GameBean> gamelist;
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CateBlock copy$default(CateBlock cateBlock, int i, String str, String str2, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = cateBlock.cateId;
            }
            if ((i2 & 2) != 0) {
                str = cateBlock.bg;
            }
            if ((i2 & 4) != 0) {
                str2 = cateBlock.name;
            }
            if ((i2 & 8) != 0) {
                list = cateBlock.gamelist;
            }
            return cateBlock.copy(i, str, str2, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCateId() {
            return this.cateId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<GameBean> component4() {
            return this.gamelist;
        }

        public final CateBlock copy(int cateId, String bg, String name, List<GameBean> gamelist) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(gamelist, "gamelist");
            return new CateBlock(cateId, bg, name, gamelist);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CateBlock)) {
                return false;
            }
            CateBlock cateBlock = (CateBlock) other;
            return this.cateId == cateBlock.cateId && Intrinsics.areEqual(this.bg, cateBlock.bg) && Intrinsics.areEqual(this.name, cateBlock.name) && Intrinsics.areEqual(this.gamelist, cateBlock.gamelist);
        }

        public int hashCode() {
            return (((((this.cateId * 31) + this.bg.hashCode()) * 31) + this.name.hashCode()) * 31) + this.gamelist.hashCode();
        }

        public String toString() {
            return "CateBlock(cateId=" + this.cateId + ", bg=" + this.bg + ", name=" + this.name + ", gamelist=" + this.gamelist + ")";
        }

        public CateBlock(int i, String bg, String name, List<GameBean> gamelist) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(gamelist, "gamelist");
            this.cateId = i;
            this.bg = bg;
            this.name = name;
            this.gamelist = gamelist;
        }

        public final int getCateId() {
            return this.cateId;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getName() {
            return this.name;
        }

        public final List<GameBean> getGamelist() {
            return this.gamelist;
        }
    }
}
