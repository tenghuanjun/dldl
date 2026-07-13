package com.cy.yyjia.zhe28.domain;

import com.lzy.okgo.model.Progress;
import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WelfareBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0004\u001e\u001f !BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean;", "", "user", "Lcom/cy/yyjia/zhe28/domain/WelfareBean$User;", "invite_info", "Lcom/cy/yyjia/zhe28/domain/WelfareBean$InviteInfo;", "top_nav", "", "Lcom/cy/yyjia/zhe28/domain/FunBean;", "task", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "cardList", "Lcom/cy/yyjia/zhe28/domain/WelfareBean$Card;", "adpic", "", "banner", "Lcom/cy/yyjia/zhe28/domain/WelfareBean$Banner;", "(Lcom/cy/yyjia/zhe28/domain/WelfareBean$User;Lcom/cy/yyjia/zhe28/domain/WelfareBean$InviteInfo;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/WelfareBean$Banner;)V", "getAdpic", "()Ljava/lang/String;", "getBanner", "()Lcom/cy/yyjia/zhe28/domain/WelfareBean$Banner;", "getCardList", "()Ljava/util/List;", "getInvite_info", "()Lcom/cy/yyjia/zhe28/domain/WelfareBean$InviteInfo;", "getTask", "getTop_nav", "getUser", "()Lcom/cy/yyjia/zhe28/domain/WelfareBean$User;", "Banner", "Card", "InviteInfo", "User", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WelfareBean {
    public static final int $stable = 8;
    private final String adpic;
    private final Banner banner;
    private final List<Card> cardList;
    private final InviteInfo invite_info;
    private final List<TaskBean> task;
    private final List<FunBean> top_nav;
    private final User user;

    public WelfareBean(User user, InviteInfo invite_info, List<FunBean> top_nav, List<TaskBean> task, List<Card> cardList, String adpic, Banner banner) {
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(invite_info, "invite_info");
        Intrinsics.checkNotNullParameter(top_nav, "top_nav");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(cardList, "cardList");
        Intrinsics.checkNotNullParameter(adpic, "adpic");
        this.user = user;
        this.invite_info = invite_info;
        this.top_nav = top_nav;
        this.task = task;
        this.cardList = cardList;
        this.adpic = adpic;
        this.banner = banner;
    }

    public final User getUser() {
        return this.user;
    }

    public final InviteInfo getInvite_info() {
        return this.invite_info;
    }

    public final List<FunBean> getTop_nav() {
        return this.top_nav;
    }

    public final List<TaskBean> getTask() {
        return this.task;
    }

    public final List<Card> getCardList() {
        return this.cardList;
    }

    public final String getAdpic() {
        return this.adpic;
    }

    public final Banner getBanner() {
        return this.banner;
    }

    /* JADX INFO: compiled from: WelfareBean.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean$Banner;", "", "banner_img", "", "banner_game_id", "", "(Ljava/lang/String;I)V", "getBanner_game_id", "()I", "getBanner_img", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Banner {
        public static final int $stable = 0;
        private final int banner_game_id;
        private final String banner_img;

        public Banner(String banner_img, int i) {
            Intrinsics.checkNotNullParameter(banner_img, "banner_img");
            this.banner_img = banner_img;
            this.banner_game_id = i;
        }

        public final String getBanner_img() {
            return this.banner_img;
        }

        public final int getBanner_game_id() {
            return this.banner_game_id;
        }
    }

    /* JADX INFO: compiled from: WelfareBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean$InviteInfo;", "", "reward_profit", "", "invite_user_count", "invite_reward_sum", "invite_cash", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInvite_cash", "()Ljava/lang/String;", "getInvite_reward_sum", "getInvite_user_count", "getReward_profit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class InviteInfo {
        public static final int $stable = 0;
        private final String invite_cash;
        private final String invite_reward_sum;
        private final String invite_user_count;
        private final String reward_profit;

        public InviteInfo(String reward_profit, String invite_user_count, String invite_reward_sum, String invite_cash) {
            Intrinsics.checkNotNullParameter(reward_profit, "reward_profit");
            Intrinsics.checkNotNullParameter(invite_user_count, "invite_user_count");
            Intrinsics.checkNotNullParameter(invite_reward_sum, "invite_reward_sum");
            Intrinsics.checkNotNullParameter(invite_cash, "invite_cash");
            this.reward_profit = reward_profit;
            this.invite_user_count = invite_user_count;
            this.invite_reward_sum = invite_reward_sum;
            this.invite_cash = invite_cash;
        }

        public final String getReward_profit() {
            return this.reward_profit;
        }

        public final String getInvite_user_count() {
            return this.invite_user_count;
        }

        public final String getInvite_reward_sum() {
            return this.invite_reward_sum;
        }

        public final String getInvite_cash() {
            return this.invite_cash;
        }
    }

    /* JADX INFO: compiled from: WelfareBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean$User;", "", "allCredit", "", "welfare", "(Ljava/lang/String;Ljava/lang/String;)V", "getAllCredit", "()Ljava/lang/String;", "getWelfare", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class User {
        public static final int $stable = 0;
        private final String allCredit;
        private final String welfare;

        public User(String allCredit, String welfare) {
            Intrinsics.checkNotNullParameter(allCredit, "allCredit");
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            this.allCredit = allCredit;
            this.welfare = welfare;
        }

        public final String getAllCredit() {
            return this.allCredit;
        }

        public final String getWelfare() {
            return this.welfare;
        }
    }

    /* JADX INFO: compiled from: WelfareBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareBean$Card;", "", "title", "", Progress.TAG, SocialConstants.PARAM_APP_DESC, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getTag", "getTitle", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Card {
        public static final int $stable = 0;
        private final String desc;
        private final String tag;
        private final String title;

        public Card(String title, String tag, String desc) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(desc, "desc");
            this.title = title;
            this.tag = tag;
            this.desc = desc;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getTag() {
            return this.tag;
        }

        public final String getDesc() {
            return this.desc;
        }
    }
}
