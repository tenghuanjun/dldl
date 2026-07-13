package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GroupBuyBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0002IJB\u009f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007¢\u0006\u0002\u0010\u001bJ\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010>\u001a\u00020\nHÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\nHÆ\u0003J\t\u0010A\u001a\u00020\u000eHÆ\u0003J\t\u0010B\u001a\u00020\u0010HÆ\u0003JÅ\u0001\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00032\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007HÆ\u0001J\u0013\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010G\u001a\u00020\nHÖ\u0001J\t\u0010H\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001dR\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0007¢\u0006\b\n\u0000\u001a\u0004\b/\u0010!R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001dR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u0010!¨\u0006K"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GroupBuyBean;", "", SocialConstants.PARAM_APP_DESC, "", "endtime", "endtimeText", "games", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "id", "", "introduce", "level", "link", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "next", "Lcom/cy/yyjia/zhe28/domain/GroupBuyBean$Next;", "nums", "payConfirm", "price", "starttime", "starttimeText", "steps", "Lcom/cy/yyjia/zhe28/domain/GroupBuyBean$Step;", "title", "users", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;ILcom/cy/yyjia/zhe28/domain/BtnBean;Lcom/cy/yyjia/zhe28/domain/GroupBuyBean$Next;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V", "getDesc", "()Ljava/lang/String;", "getEndtime", "getEndtimeText", "getGames", "()Ljava/util/List;", "getId", "()I", "getIntroduce", "getLevel", "getLink", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "getNext", "()Lcom/cy/yyjia/zhe28/domain/GroupBuyBean$Next;", "getNums", "getPayConfirm", "getPrice", "getStarttime", "getStarttimeText", "getSteps", "getTitle", "getUsers", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "Next", "Step", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GroupBuyBean {
    public static final int $stable = 8;
    private final String desc;
    private final String endtime;
    private final String endtimeText;
    private final List<GameBean> games;
    private final int id;
    private final String introduce;
    private final int level;
    private final BtnBean link;
    private final Next next;
    private final int nums;
    private final String payConfirm;
    private final String price;
    private final String starttime;
    private final String starttimeText;
    private final List<Step> steps;
    private final String title;
    private final List<UserBean> users;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getNums() {
        return this.nums;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPayConfirm() {
        return this.payConfirm;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getStarttime() {
        return this.starttime;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getStarttimeText() {
        return this.starttimeText;
    }

    public final List<Step> component15() {
        return this.steps;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final List<UserBean> component17() {
        return this.users;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEndtime() {
        return this.endtime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEndtimeText() {
        return this.endtimeText;
    }

    public final List<GameBean> component4() {
        return this.games;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getIntroduce() {
        return this.introduce;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getLevel() {
        return this.level;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final BtnBean getLink() {
        return this.link;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Next getNext() {
        return this.next;
    }

    public final GroupBuyBean copy(String desc, String endtime, String endtimeText, List<GameBean> games, int id, String introduce, int level, BtnBean link, Next next, int nums, String payConfirm, String price, String starttime, String starttimeText, List<Step> steps, String title, List<UserBean> users) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(endtime, "endtime");
        Intrinsics.checkNotNullParameter(endtimeText, "endtimeText");
        Intrinsics.checkNotNullParameter(games, "games");
        Intrinsics.checkNotNullParameter(introduce, "introduce");
        Intrinsics.checkNotNullParameter(link, "link");
        Intrinsics.checkNotNullParameter(next, "next");
        Intrinsics.checkNotNullParameter(payConfirm, "payConfirm");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(starttime, "starttime");
        Intrinsics.checkNotNullParameter(starttimeText, "starttimeText");
        Intrinsics.checkNotNullParameter(steps, "steps");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(users, "users");
        return new GroupBuyBean(desc, endtime, endtimeText, games, id, introduce, level, link, next, nums, payConfirm, price, starttime, starttimeText, steps, title, users);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GroupBuyBean)) {
            return false;
        }
        GroupBuyBean groupBuyBean = (GroupBuyBean) other;
        return Intrinsics.areEqual(this.desc, groupBuyBean.desc) && Intrinsics.areEqual(this.endtime, groupBuyBean.endtime) && Intrinsics.areEqual(this.endtimeText, groupBuyBean.endtimeText) && Intrinsics.areEqual(this.games, groupBuyBean.games) && this.id == groupBuyBean.id && Intrinsics.areEqual(this.introduce, groupBuyBean.introduce) && this.level == groupBuyBean.level && Intrinsics.areEqual(this.link, groupBuyBean.link) && Intrinsics.areEqual(this.next, groupBuyBean.next) && this.nums == groupBuyBean.nums && Intrinsics.areEqual(this.payConfirm, groupBuyBean.payConfirm) && Intrinsics.areEqual(this.price, groupBuyBean.price) && Intrinsics.areEqual(this.starttime, groupBuyBean.starttime) && Intrinsics.areEqual(this.starttimeText, groupBuyBean.starttimeText) && Intrinsics.areEqual(this.steps, groupBuyBean.steps) && Intrinsics.areEqual(this.title, groupBuyBean.title) && Intrinsics.areEqual(this.users, groupBuyBean.users);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.desc.hashCode() * 31) + this.endtime.hashCode()) * 31) + this.endtimeText.hashCode()) * 31) + this.games.hashCode()) * 31) + this.id) * 31) + this.introduce.hashCode()) * 31) + this.level) * 31) + this.link.hashCode()) * 31) + this.next.hashCode()) * 31) + this.nums) * 31) + this.payConfirm.hashCode()) * 31) + this.price.hashCode()) * 31) + this.starttime.hashCode()) * 31) + this.starttimeText.hashCode()) * 31) + this.steps.hashCode()) * 31) + this.title.hashCode()) * 31) + this.users.hashCode();
    }

    public String toString() {
        return "GroupBuyBean(desc=" + this.desc + ", endtime=" + this.endtime + ", endtimeText=" + this.endtimeText + ", games=" + this.games + ", id=" + this.id + ", introduce=" + this.introduce + ", level=" + this.level + ", link=" + this.link + ", next=" + this.next + ", nums=" + this.nums + ", payConfirm=" + this.payConfirm + ", price=" + this.price + ", starttime=" + this.starttime + ", starttimeText=" + this.starttimeText + ", steps=" + this.steps + ", title=" + this.title + ", users=" + this.users + ")";
    }

    public GroupBuyBean(String desc, String endtime, String endtimeText, List<GameBean> games, int i, String introduce, int i2, BtnBean link, Next next, int i3, String payConfirm, String price, String starttime, String starttimeText, List<Step> steps, String title, List<UserBean> users) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(endtime, "endtime");
        Intrinsics.checkNotNullParameter(endtimeText, "endtimeText");
        Intrinsics.checkNotNullParameter(games, "games");
        Intrinsics.checkNotNullParameter(introduce, "introduce");
        Intrinsics.checkNotNullParameter(link, "link");
        Intrinsics.checkNotNullParameter(next, "next");
        Intrinsics.checkNotNullParameter(payConfirm, "payConfirm");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(starttime, "starttime");
        Intrinsics.checkNotNullParameter(starttimeText, "starttimeText");
        Intrinsics.checkNotNullParameter(steps, "steps");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(users, "users");
        this.desc = desc;
        this.endtime = endtime;
        this.endtimeText = endtimeText;
        this.games = games;
        this.id = i;
        this.introduce = introduce;
        this.level = i2;
        this.link = link;
        this.next = next;
        this.nums = i3;
        this.payConfirm = payConfirm;
        this.price = price;
        this.starttime = starttime;
        this.starttimeText = starttimeText;
        this.steps = steps;
        this.title = title;
        this.users = users;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getEndtime() {
        return this.endtime;
    }

    public final String getEndtimeText() {
        return this.endtimeText;
    }

    public final List<GameBean> getGames() {
        return this.games;
    }

    public final int getId() {
        return this.id;
    }

    public final String getIntroduce() {
        return this.introduce;
    }

    public final int getLevel() {
        return this.level;
    }

    public final BtnBean getLink() {
        return this.link;
    }

    public final Next getNext() {
        return this.next;
    }

    public final int getNums() {
        return this.nums;
    }

    public final String getPayConfirm() {
        return this.payConfirm;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getStarttime() {
        return this.starttime;
    }

    public final String getStarttimeText() {
        return this.starttimeText;
    }

    public final List<Step> getSteps() {
        return this.steps;
    }

    public final String getTitle() {
        return this.title;
    }

    public final List<UserBean> getUsers() {
        return this.users;
    }

    /* JADX INFO: compiled from: GroupBuyBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GroupBuyBean$Next;", "", SocialConstants.PARAM_APP_DESC, "", "discount", "discountDesc", "minNums", "", "nums", "percent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getDiscount", "getDiscountDesc", "getMinNums", "()I", "getNums", "getPercent", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Next {
        public static final int $stable = 0;
        private final String desc;
        private final String discount;
        private final String discountDesc;
        private final int minNums;
        private final String nums;
        private final String percent;

        public static /* synthetic */ Next copy$default(Next next, String str, String str2, String str3, int i, String str4, String str5, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = next.desc;
            }
            if ((i2 & 2) != 0) {
                str2 = next.discount;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                str3 = next.discountDesc;
            }
            String str7 = str3;
            if ((i2 & 8) != 0) {
                i = next.minNums;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                str4 = next.nums;
            }
            String str8 = str4;
            if ((i2 & 32) != 0) {
                str5 = next.percent;
            }
            return next.copy(str, str6, str7, i3, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDiscount() {
            return this.discount;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDiscountDesc() {
            return this.discountDesc;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getMinNums() {
            return this.minNums;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getNums() {
            return this.nums;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPercent() {
            return this.percent;
        }

        public final Next copy(String desc, String discount, String discountDesc, int minNums, String nums, String percent) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(discount, "discount");
            Intrinsics.checkNotNullParameter(discountDesc, "discountDesc");
            Intrinsics.checkNotNullParameter(nums, "nums");
            Intrinsics.checkNotNullParameter(percent, "percent");
            return new Next(desc, discount, discountDesc, minNums, nums, percent);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Next)) {
                return false;
            }
            Next next = (Next) other;
            return Intrinsics.areEqual(this.desc, next.desc) && Intrinsics.areEqual(this.discount, next.discount) && Intrinsics.areEqual(this.discountDesc, next.discountDesc) && this.minNums == next.minNums && Intrinsics.areEqual(this.nums, next.nums) && Intrinsics.areEqual(this.percent, next.percent);
        }

        public int hashCode() {
            return (((((((((this.desc.hashCode() * 31) + this.discount.hashCode()) * 31) + this.discountDesc.hashCode()) * 31) + this.minNums) * 31) + this.nums.hashCode()) * 31) + this.percent.hashCode();
        }

        public String toString() {
            return "Next(desc=" + this.desc + ", discount=" + this.discount + ", discountDesc=" + this.discountDesc + ", minNums=" + this.minNums + ", nums=" + this.nums + ", percent=" + this.percent + ")";
        }

        public Next(String desc, String discount, String discountDesc, int i, String nums, String percent) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(discount, "discount");
            Intrinsics.checkNotNullParameter(discountDesc, "discountDesc");
            Intrinsics.checkNotNullParameter(nums, "nums");
            Intrinsics.checkNotNullParameter(percent, "percent");
            this.desc = desc;
            this.discount = discount;
            this.discountDesc = discountDesc;
            this.minNums = i;
            this.nums = nums;
            this.percent = percent;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getDiscount() {
            return this.discount;
        }

        public final String getDiscountDesc() {
            return this.discountDesc;
        }

        public final int getMinNums() {
            return this.minNums;
        }

        public final String getNums() {
            return this.nums;
        }

        public final String getPercent() {
            return this.percent;
        }
    }

    /* JADX INFO: compiled from: GroupBuyBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003Jc\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0006HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GroupBuyBean$Step;", "", SocialConstants.PARAM_APP_DESC, "", "icon", "id", "", "isLast", "level", "max", "min", "money", "name", "(Ljava/lang/String;Ljava/lang/String;IIIIIILjava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getIcon", "getId", "()I", "getLevel", "getMax", "getMin", "getMoney", "getName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Step {
        public static final int $stable = 0;
        private final String desc;
        private final String icon;
        private final int id;
        private final int isLast;
        private final int level;
        private final int max;
        private final int min;
        private final int money;
        private final String name;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getIsLast() {
            return this.isLast;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getLevel() {
            return this.level;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getMax() {
            return this.max;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getMin() {
            return this.min;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getMoney() {
            return this.money;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Step copy(String desc, String icon, int id, int isLast, int level, int max, int min, int money, String name) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            return new Step(desc, icon, id, isLast, level, max, min, money, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Step)) {
                return false;
            }
            Step step = (Step) other;
            return Intrinsics.areEqual(this.desc, step.desc) && Intrinsics.areEqual(this.icon, step.icon) && this.id == step.id && this.isLast == step.isLast && this.level == step.level && this.max == step.max && this.min == step.min && this.money == step.money && Intrinsics.areEqual(this.name, step.name);
        }

        public int hashCode() {
            return (((((((((((((((this.desc.hashCode() * 31) + this.icon.hashCode()) * 31) + this.id) * 31) + this.isLast) * 31) + this.level) * 31) + this.max) * 31) + this.min) * 31) + this.money) * 31) + this.name.hashCode();
        }

        public String toString() {
            return "Step(desc=" + this.desc + ", icon=" + this.icon + ", id=" + this.id + ", isLast=" + this.isLast + ", level=" + this.level + ", max=" + this.max + ", min=" + this.min + ", money=" + this.money + ", name=" + this.name + ")";
        }

        public Step(String desc, String icon, int i, int i2, int i3, int i4, int i5, int i6, String name) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            this.desc = desc;
            this.icon = icon;
            this.id = i;
            this.isLast = i2;
            this.level = i3;
            this.max = i4;
            this.min = i5;
            this.money = i6;
            this.name = name;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final int getId() {
            return this.id;
        }

        public final int isLast() {
            return this.isLast;
        }

        public final int getLevel() {
            return this.level;
        }

        public final int getMax() {
            return this.max;
        }

        public final int getMin() {
            return this.min;
        }

        public final int getMoney() {
            return this.money;
        }

        public final String getName() {
            return this.name;
        }
    }
}
