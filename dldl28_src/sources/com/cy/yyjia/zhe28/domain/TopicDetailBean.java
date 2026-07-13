package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TopicDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001:\rMNOPQRSTUVWXYB\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0014\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u0007¢\u0006\u0002\u0010\u001cJ\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0007HÆ\u0003J\t\u00109\u001a\u00020\u0014HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0017HÆ\u0003J\t\u0010<\u001a\u00020\u0014HÆ\u0003J\t\u0010=\u001a\u00020\u001aHÆ\u0003J\t\u0010>\u001a\u00020\u0007HÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0007HÆ\u0003J\t\u0010A\u001a\u00020\tHÆ\u0003J\t\u0010B\u001a\u00020\u000bHÆ\u0003J\t\u0010C\u001a\u00020\u0007HÆ\u0003J\t\u0010D\u001a\u00020\u000eHÆ\u0003J\t\u0010E\u001a\u00020\u0007HÆ\u0003J\t\u0010F\u001a\u00020\u0011HÆ\u0003J©\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020\u0007HÖ\u0001J\t\u0010L\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\"R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u0018\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b3\u0010/R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u001b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\"¨\u0006Z"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean;", "", "common", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Common;", "coupon", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Coupon;", "createtime", "", "feature", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Feature;", "game", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Game;", "game_id", "header", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Header;", "id", "lottery", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Lottery;", "lottery_id", "rule", "", "status", "task", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Task;", "title", "video", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Video;", "yhq_id", "(Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Common;Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Coupon;ILcom/cy/yyjia/zhe28/domain/TopicDetailBean$Feature;Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Game;ILcom/cy/yyjia/zhe28/domain/TopicDetailBean$Header;ILcom/cy/yyjia/zhe28/domain/TopicDetailBean$Lottery;ILjava/lang/String;ILcom/cy/yyjia/zhe28/domain/TopicDetailBean$Task;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Video;I)V", "getCommon", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Common;", "getCoupon", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Coupon;", "getCreatetime", "()I", "getFeature", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Feature;", "getGame", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Game;", "getGame_id", "getHeader", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Header;", "getId", "getLottery", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Lottery;", "getLottery_id", "getRule", "()Ljava/lang/String;", "getStatus", "getTask", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Task;", "getTitle", "getVideo", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Video;", "getYhq_id", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "Common", "Config", "Coupon", "Detail", "Feature", "Game", "Header", "Lottery", "Prize", "Task", "TaskList", "Use", "Video", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TopicDetailBean {
    public static final int $stable = 8;
    private final Common common;
    private final Coupon coupon;
    private final int createtime;
    private final Feature feature;
    private final Game game;
    private final int game_id;
    private final Header header;
    private final int id;
    private final Lottery lottery;
    private final int lottery_id;
    private final String rule;
    private final int status;
    private final Task task;
    private final String title;
    private final Video video;
    private final int yhq_id;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Common getCommon() {
        return this.common;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getLottery_id() {
        return this.lottery_id;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRule() {
        return this.rule;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Task getTask() {
        return this.task;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final Video getVideo() {
        return this.video;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getYhq_id() {
        return this.yhq_id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Coupon getCoupon() {
        return this.coupon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCreatetime() {
        return this.createtime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Feature getFeature() {
        return this.feature;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Game getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getGame_id() {
        return this.game_id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Header getHeader() {
        return this.header;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Lottery getLottery() {
        return this.lottery;
    }

    public final TopicDetailBean copy(Common common, Coupon coupon, int createtime, Feature feature, Game game, int game_id, Header header, int id, Lottery lottery, int lottery_id, String rule, int status, Task task, String title, Video video, int yhq_id) {
        Intrinsics.checkNotNullParameter(common, "common");
        Intrinsics.checkNotNullParameter(coupon, "coupon");
        Intrinsics.checkNotNullParameter(feature, "feature");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(lottery, "lottery");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(video, "video");
        return new TopicDetailBean(common, coupon, createtime, feature, game, game_id, header, id, lottery, lottery_id, rule, status, task, title, video, yhq_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TopicDetailBean)) {
            return false;
        }
        TopicDetailBean topicDetailBean = (TopicDetailBean) other;
        return Intrinsics.areEqual(this.common, topicDetailBean.common) && Intrinsics.areEqual(this.coupon, topicDetailBean.coupon) && this.createtime == topicDetailBean.createtime && Intrinsics.areEqual(this.feature, topicDetailBean.feature) && Intrinsics.areEqual(this.game, topicDetailBean.game) && this.game_id == topicDetailBean.game_id && Intrinsics.areEqual(this.header, topicDetailBean.header) && this.id == topicDetailBean.id && Intrinsics.areEqual(this.lottery, topicDetailBean.lottery) && this.lottery_id == topicDetailBean.lottery_id && Intrinsics.areEqual(this.rule, topicDetailBean.rule) && this.status == topicDetailBean.status && Intrinsics.areEqual(this.task, topicDetailBean.task) && Intrinsics.areEqual(this.title, topicDetailBean.title) && Intrinsics.areEqual(this.video, topicDetailBean.video) && this.yhq_id == topicDetailBean.yhq_id;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.common.hashCode() * 31) + this.coupon.hashCode()) * 31) + this.createtime) * 31) + this.feature.hashCode()) * 31) + this.game.hashCode()) * 31) + this.game_id) * 31) + this.header.hashCode()) * 31) + this.id) * 31) + this.lottery.hashCode()) * 31) + this.lottery_id) * 31) + this.rule.hashCode()) * 31) + this.status) * 31) + this.task.hashCode()) * 31) + this.title.hashCode()) * 31) + this.video.hashCode()) * 31) + this.yhq_id;
    }

    public String toString() {
        return "TopicDetailBean(common=" + this.common + ", coupon=" + this.coupon + ", createtime=" + this.createtime + ", feature=" + this.feature + ", game=" + this.game + ", game_id=" + this.game_id + ", header=" + this.header + ", id=" + this.id + ", lottery=" + this.lottery + ", lottery_id=" + this.lottery_id + ", rule=" + this.rule + ", status=" + this.status + ", task=" + this.task + ", title=" + this.title + ", video=" + this.video + ", yhq_id=" + this.yhq_id + ")";
    }

    public TopicDetailBean(Common common, Coupon coupon, int i, Feature feature, Game game, int i2, Header header, int i3, Lottery lottery, int i4, String rule, int i5, Task task, String title, Video video, int i6) {
        Intrinsics.checkNotNullParameter(common, "common");
        Intrinsics.checkNotNullParameter(coupon, "coupon");
        Intrinsics.checkNotNullParameter(feature, "feature");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(lottery, "lottery");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(video, "video");
        this.common = common;
        this.coupon = coupon;
        this.createtime = i;
        this.feature = feature;
        this.game = game;
        this.game_id = i2;
        this.header = header;
        this.id = i3;
        this.lottery = lottery;
        this.lottery_id = i4;
        this.rule = rule;
        this.status = i5;
        this.task = task;
        this.title = title;
        this.video = video;
        this.yhq_id = i6;
    }

    public final Common getCommon() {
        return this.common;
    }

    public final Coupon getCoupon() {
        return this.coupon;
    }

    public final int getCreatetime() {
        return this.createtime;
    }

    public final Feature getFeature() {
        return this.feature;
    }

    public final Game getGame() {
        return this.game;
    }

    public final int getGame_id() {
        return this.game_id;
    }

    public final Header getHeader() {
        return this.header;
    }

    public final int getId() {
        return this.id;
    }

    public final Lottery getLottery() {
        return this.lottery;
    }

    public final int getLottery_id() {
        return this.lottery_id;
    }

    public final String getRule() {
        return this.rule;
    }

    public final int getStatus() {
        return this.status;
    }

    public final Task getTask() {
        return this.task;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Video getVideo() {
        return this.video;
    }

    public final int getYhq_id() {
        return this.yhq_id;
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Common;", "", "bg", "", "bottom_download_btn", "reward_bg", "reward_log_bg", "rule_bg", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBg", "()Ljava/lang/String;", "getBottom_download_btn", "getReward_bg", "getReward_log_bg", "getRule_bg", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Common {
        public static final int $stable = 0;
        private final String bg;
        private final String bottom_download_btn;
        private final String reward_bg;
        private final String reward_log_bg;
        private final String rule_bg;

        public static /* synthetic */ Common copy$default(Common common, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = common.bg;
            }
            if ((i & 2) != 0) {
                str2 = common.bottom_download_btn;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = common.reward_bg;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = common.reward_log_bg;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = common.rule_bg;
            }
            return common.copy(str, str6, str7, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getBottom_download_btn() {
            return this.bottom_download_btn;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getReward_bg() {
            return this.reward_bg;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getReward_log_bg() {
            return this.reward_log_bg;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getRule_bg() {
            return this.rule_bg;
        }

        public final Common copy(String bg, String bottom_download_btn, String reward_bg, String reward_log_bg, String rule_bg) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(bottom_download_btn, "bottom_download_btn");
            Intrinsics.checkNotNullParameter(reward_bg, "reward_bg");
            Intrinsics.checkNotNullParameter(reward_log_bg, "reward_log_bg");
            Intrinsics.checkNotNullParameter(rule_bg, "rule_bg");
            return new Common(bg, bottom_download_btn, reward_bg, reward_log_bg, rule_bg);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Common)) {
                return false;
            }
            Common common = (Common) other;
            return Intrinsics.areEqual(this.bg, common.bg) && Intrinsics.areEqual(this.bottom_download_btn, common.bottom_download_btn) && Intrinsics.areEqual(this.reward_bg, common.reward_bg) && Intrinsics.areEqual(this.reward_log_bg, common.reward_log_bg) && Intrinsics.areEqual(this.rule_bg, common.rule_bg);
        }

        public int hashCode() {
            return (((((((this.bg.hashCode() * 31) + this.bottom_download_btn.hashCode()) * 31) + this.reward_bg.hashCode()) * 31) + this.reward_log_bg.hashCode()) * 31) + this.rule_bg.hashCode();
        }

        public String toString() {
            return "Common(bg=" + this.bg + ", bottom_download_btn=" + this.bottom_download_btn + ", reward_bg=" + this.reward_bg + ", reward_log_bg=" + this.reward_log_bg + ", rule_bg=" + this.rule_bg + ")";
        }

        public Common(String bg, String bottom_download_btn, String reward_bg, String reward_log_bg, String rule_bg) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(bottom_download_btn, "bottom_download_btn");
            Intrinsics.checkNotNullParameter(reward_bg, "reward_bg");
            Intrinsics.checkNotNullParameter(reward_log_bg, "reward_log_bg");
            Intrinsics.checkNotNullParameter(rule_bg, "rule_bg");
            this.bg = bg;
            this.bottom_download_btn = bottom_download_btn;
            this.reward_bg = reward_bg;
            this.reward_log_bg = reward_log_bg;
            this.rule_bg = rule_bg;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getBottom_download_btn() {
            return this.bottom_download_btn;
        }

        public final String getReward_bg() {
            return this.reward_bg;
        }

        public final String getReward_log_bg() {
            return this.reward_log_bg;
        }

        public final String getRule_bg() {
            return this.rule_bg;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Coupon;", "", "bg", "", SocialConstants.PARAM_IMG_URL, "img_received", "is_received", "", "status", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getBg", "()Ljava/lang/String;", "getImg", "getImg_received", "()I", "getStatus", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Coupon {
        public static final int $stable = 0;
        private final String bg;
        private final String img;
        private final String img_received;
        private final int is_received;
        private final int status;

        public static /* synthetic */ Coupon copy$default(Coupon coupon, String str, String str2, String str3, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = coupon.bg;
            }
            if ((i3 & 2) != 0) {
                str2 = coupon.img;
            }
            String str4 = str2;
            if ((i3 & 4) != 0) {
                str3 = coupon.img_received;
            }
            String str5 = str3;
            if ((i3 & 8) != 0) {
                i = coupon.is_received;
            }
            int i4 = i;
            if ((i3 & 16) != 0) {
                i2 = coupon.status;
            }
            return coupon.copy(str, str4, str5, i4, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getImg() {
            return this.img;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getImg_received() {
            return this.img_received;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getIs_received() {
            return this.is_received;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        public final Coupon copy(String bg, String img, String img_received, int is_received, int status) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(img, "img");
            Intrinsics.checkNotNullParameter(img_received, "img_received");
            return new Coupon(bg, img, img_received, is_received, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Coupon)) {
                return false;
            }
            Coupon coupon = (Coupon) other;
            return Intrinsics.areEqual(this.bg, coupon.bg) && Intrinsics.areEqual(this.img, coupon.img) && Intrinsics.areEqual(this.img_received, coupon.img_received) && this.is_received == coupon.is_received && this.status == coupon.status;
        }

        public int hashCode() {
            return (((((((this.bg.hashCode() * 31) + this.img.hashCode()) * 31) + this.img_received.hashCode()) * 31) + this.is_received) * 31) + this.status;
        }

        public String toString() {
            return "Coupon(bg=" + this.bg + ", img=" + this.img + ", img_received=" + this.img_received + ", is_received=" + this.is_received + ", status=" + this.status + ")";
        }

        public Coupon(String bg, String img, String img_received, int i, int i2) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(img, "img");
            Intrinsics.checkNotNullParameter(img_received, "img_received");
            this.bg = bg;
            this.img = img;
            this.img_received = img_received;
            this.is_received = i;
            this.status = i2;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getImg() {
            return this.img;
        }

        public final String getImg_received() {
            return this.img_received;
        }

        public final int is_received() {
            return this.is_received;
        }

        public final int getStatus() {
            return this.status;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Feature;", "", "bg", "", "urls", "", "(Ljava/lang/String;Ljava/util/List;)V", "getBg", "()Ljava/lang/String;", "getUrls", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Feature {
        public static final int $stable = 8;
        private final String bg;
        private final List<String> urls;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Feature copy$default(Feature feature, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = feature.bg;
            }
            if ((i & 2) != 0) {
                list = feature.urls;
            }
            return feature.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        public final List<String> component2() {
            return this.urls;
        }

        public final Feature copy(String bg, List<String> urls) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(urls, "urls");
            return new Feature(bg, urls);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Feature)) {
                return false;
            }
            Feature feature = (Feature) other;
            return Intrinsics.areEqual(this.bg, feature.bg) && Intrinsics.areEqual(this.urls, feature.urls);
        }

        public int hashCode() {
            return (this.bg.hashCode() * 31) + this.urls.hashCode();
        }

        public String toString() {
            return "Feature(bg=" + this.bg + ", urls=" + this.urls + ")";
        }

        public Feature(String bg, List<String> urls) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(urls, "urls");
            this.bg = bg;
            this.urls = urls;
        }

        public final String getBg() {
            return this.bg;
        }

        public final List<String> getUrls() {
            return this.urls;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Game;", "", "name", "", "packageName", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPackageName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Game {
        public static final int $stable = 0;
        private final String name;
        private final String packageName;

        public static /* synthetic */ Game copy$default(Game game, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = game.name;
            }
            if ((i & 2) != 0) {
                str2 = game.packageName;
            }
            return game.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        public final Game copy(String name, String packageName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            return new Game(name, packageName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Game)) {
                return false;
            }
            Game game = (Game) other;
            return Intrinsics.areEqual(this.name, game.name) && Intrinsics.areEqual(this.packageName, game.packageName);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.packageName.hashCode();
        }

        public String toString() {
            return "Game(name=" + this.name + ", packageName=" + this.packageName + ")";
        }

        public Game(String name, String packageName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            this.name = name;
            this.packageName = packageName;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPackageName() {
            return this.packageName;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Header;", "", "banner", "", "effect", "download_btn", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBanner", "()Ljava/lang/String;", "getDownload_btn", "getEffect", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Header {
        public static final int $stable = 0;
        private final String banner;
        private final String download_btn;
        private final String effect;

        public static /* synthetic */ Header copy$default(Header header, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = header.banner;
            }
            if ((i & 2) != 0) {
                str2 = header.effect;
            }
            if ((i & 4) != 0) {
                str3 = header.download_btn;
            }
            return header.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBanner() {
            return this.banner;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEffect() {
            return this.effect;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDownload_btn() {
            return this.download_btn;
        }

        public final Header copy(String banner, String effect, String download_btn) {
            Intrinsics.checkNotNullParameter(banner, "banner");
            Intrinsics.checkNotNullParameter(effect, "effect");
            Intrinsics.checkNotNullParameter(download_btn, "download_btn");
            return new Header(banner, effect, download_btn);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Header)) {
                return false;
            }
            Header header = (Header) other;
            return Intrinsics.areEqual(this.banner, header.banner) && Intrinsics.areEqual(this.effect, header.effect) && Intrinsics.areEqual(this.download_btn, header.download_btn);
        }

        public int hashCode() {
            return (((this.banner.hashCode() * 31) + this.effect.hashCode()) * 31) + this.download_btn.hashCode();
        }

        public String toString() {
            return "Header(banner=" + this.banner + ", effect=" + this.effect + ", download_btn=" + this.download_btn + ")";
        }

        public Header(String banner, String effect, String download_btn) {
            Intrinsics.checkNotNullParameter(banner, "banner");
            Intrinsics.checkNotNullParameter(effect, "effect");
            Intrinsics.checkNotNullParameter(download_btn, "download_btn");
            this.banner = banner;
            this.effect = effect;
            this.download_btn = download_btn;
        }

        public final String getBanner() {
            return this.banner;
        }

        public final String getEffect() {
            return this.effect;
        }

        public final String getDownload_btn() {
            return this.download_btn;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003Ji\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\tHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\t\u0010,\u001a\u00020\tHÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018¨\u0006."}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Lottery;", "", "bg", "", "grid_bg", "grid_draw_btn", "detail", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Detail;", "left_num", "", "prizes", "", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Prize;", "total_num", "used_num", "status", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Detail;ILjava/util/List;III)V", "getBg", "()Ljava/lang/String;", "getDetail", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Detail;", "getGrid_bg", "getGrid_draw_btn", "getLeft_num", "()I", "getPrizes", "()Ljava/util/List;", "getStatus", "getTotal_num", "getUsed_num", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getList", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Lottery {
        public static final int $stable = 8;
        private final String bg;
        private final Detail detail;
        private final String grid_bg;
        private final String grid_draw_btn;
        private final int left_num;
        private final List<Prize> prizes;
        private final int status;
        private final int total_num;
        private final int used_num;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getGrid_bg() {
            return this.grid_bg;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getGrid_draw_btn() {
            return this.grid_draw_btn;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Detail getDetail() {
            return this.detail;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getLeft_num() {
            return this.left_num;
        }

        public final List<Prize> component6() {
            return this.prizes;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getTotal_num() {
            return this.total_num;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getUsed_num() {
            return this.used_num;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        public final Lottery copy(String bg, String grid_bg, String grid_draw_btn, Detail detail, int left_num, List<Prize> prizes, int total_num, int used_num, int status) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(grid_bg, "grid_bg");
            Intrinsics.checkNotNullParameter(grid_draw_btn, "grid_draw_btn");
            Intrinsics.checkNotNullParameter(detail, "detail");
            Intrinsics.checkNotNullParameter(prizes, "prizes");
            return new Lottery(bg, grid_bg, grid_draw_btn, detail, left_num, prizes, total_num, used_num, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Lottery)) {
                return false;
            }
            Lottery lottery = (Lottery) other;
            return Intrinsics.areEqual(this.bg, lottery.bg) && Intrinsics.areEqual(this.grid_bg, lottery.grid_bg) && Intrinsics.areEqual(this.grid_draw_btn, lottery.grid_draw_btn) && Intrinsics.areEqual(this.detail, lottery.detail) && this.left_num == lottery.left_num && Intrinsics.areEqual(this.prizes, lottery.prizes) && this.total_num == lottery.total_num && this.used_num == lottery.used_num && this.status == lottery.status;
        }

        public int hashCode() {
            return (((((((((((((((this.bg.hashCode() * 31) + this.grid_bg.hashCode()) * 31) + this.grid_draw_btn.hashCode()) * 31) + this.detail.hashCode()) * 31) + this.left_num) * 31) + this.prizes.hashCode()) * 31) + this.total_num) * 31) + this.used_num) * 31) + this.status;
        }

        public String toString() {
            return "Lottery(bg=" + this.bg + ", grid_bg=" + this.grid_bg + ", grid_draw_btn=" + this.grid_draw_btn + ", detail=" + this.detail + ", left_num=" + this.left_num + ", prizes=" + this.prizes + ", total_num=" + this.total_num + ", used_num=" + this.used_num + ", status=" + this.status + ")";
        }

        public Lottery(String bg, String grid_bg, String grid_draw_btn, Detail detail, int i, List<Prize> prizes, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(grid_bg, "grid_bg");
            Intrinsics.checkNotNullParameter(grid_draw_btn, "grid_draw_btn");
            Intrinsics.checkNotNullParameter(detail, "detail");
            Intrinsics.checkNotNullParameter(prizes, "prizes");
            this.bg = bg;
            this.grid_bg = grid_bg;
            this.grid_draw_btn = grid_draw_btn;
            this.detail = detail;
            this.left_num = i;
            this.prizes = prizes;
            this.total_num = i2;
            this.used_num = i3;
            this.status = i4;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getGrid_bg() {
            return this.grid_bg;
        }

        public final String getGrid_draw_btn() {
            return this.grid_draw_btn;
        }

        public final Detail getDetail() {
            return this.detail;
        }

        public final int getLeft_num() {
            return this.left_num;
        }

        public final List<Prize> getPrizes() {
            return this.prizes;
        }

        public final int getTotal_num() {
            return this.total_num;
        }

        public final int getUsed_num() {
            return this.used_num;
        }

        public final int getStatus() {
            return this.status;
        }

        public final List<Prize> getList() {
            Prize prize = new Prize(0, 0, 0, "", "", this.grid_draw_btn);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.prizes);
            arrayList.add(4, prize);
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J_\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Task;", "", "bg", "", "desc_bg", "finish_btn", "go_btn", "reward_notice_bg", "topic_desc_btn", "view_reward_btn", "list", "", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$TaskList;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getBg", "()Ljava/lang/String;", "getDesc_bg", "getFinish_btn", "getGo_btn", "getList", "()Ljava/util/List;", "getReward_notice_bg", "getTopic_desc_btn", "getView_reward_btn", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Task {
        public static final int $stable = 8;
        private final String bg;
        private final String desc_bg;
        private final String finish_btn;
        private final String go_btn;
        private final List<TaskList> list;
        private final String reward_notice_bg;
        private final String topic_desc_btn;
        private final String view_reward_btn;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDesc_bg() {
            return this.desc_bg;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getFinish_btn() {
            return this.finish_btn;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getGo_btn() {
            return this.go_btn;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getReward_notice_bg() {
            return this.reward_notice_bg;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getTopic_desc_btn() {
            return this.topic_desc_btn;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getView_reward_btn() {
            return this.view_reward_btn;
        }

        public final List<TaskList> component8() {
            return this.list;
        }

        public final Task copy(String bg, String desc_bg, String finish_btn, String go_btn, String reward_notice_bg, String topic_desc_btn, String view_reward_btn, List<TaskList> list) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(desc_bg, "desc_bg");
            Intrinsics.checkNotNullParameter(finish_btn, "finish_btn");
            Intrinsics.checkNotNullParameter(go_btn, "go_btn");
            Intrinsics.checkNotNullParameter(reward_notice_bg, "reward_notice_bg");
            Intrinsics.checkNotNullParameter(topic_desc_btn, "topic_desc_btn");
            Intrinsics.checkNotNullParameter(view_reward_btn, "view_reward_btn");
            Intrinsics.checkNotNullParameter(list, "list");
            return new Task(bg, desc_bg, finish_btn, go_btn, reward_notice_bg, topic_desc_btn, view_reward_btn, list);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Task)) {
                return false;
            }
            Task task = (Task) other;
            return Intrinsics.areEqual(this.bg, task.bg) && Intrinsics.areEqual(this.desc_bg, task.desc_bg) && Intrinsics.areEqual(this.finish_btn, task.finish_btn) && Intrinsics.areEqual(this.go_btn, task.go_btn) && Intrinsics.areEqual(this.reward_notice_bg, task.reward_notice_bg) && Intrinsics.areEqual(this.topic_desc_btn, task.topic_desc_btn) && Intrinsics.areEqual(this.view_reward_btn, task.view_reward_btn) && Intrinsics.areEqual(this.list, task.list);
        }

        public int hashCode() {
            return (((((((((((((this.bg.hashCode() * 31) + this.desc_bg.hashCode()) * 31) + this.finish_btn.hashCode()) * 31) + this.go_btn.hashCode()) * 31) + this.reward_notice_bg.hashCode()) * 31) + this.topic_desc_btn.hashCode()) * 31) + this.view_reward_btn.hashCode()) * 31) + this.list.hashCode();
        }

        public String toString() {
            return "Task(bg=" + this.bg + ", desc_bg=" + this.desc_bg + ", finish_btn=" + this.finish_btn + ", go_btn=" + this.go_btn + ", reward_notice_bg=" + this.reward_notice_bg + ", topic_desc_btn=" + this.topic_desc_btn + ", view_reward_btn=" + this.view_reward_btn + ", list=" + this.list + ")";
        }

        public Task(String bg, String desc_bg, String finish_btn, String go_btn, String reward_notice_bg, String topic_desc_btn, String view_reward_btn, List<TaskList> list) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(desc_bg, "desc_bg");
            Intrinsics.checkNotNullParameter(finish_btn, "finish_btn");
            Intrinsics.checkNotNullParameter(go_btn, "go_btn");
            Intrinsics.checkNotNullParameter(reward_notice_bg, "reward_notice_bg");
            Intrinsics.checkNotNullParameter(topic_desc_btn, "topic_desc_btn");
            Intrinsics.checkNotNullParameter(view_reward_btn, "view_reward_btn");
            Intrinsics.checkNotNullParameter(list, "list");
            this.bg = bg;
            this.desc_bg = desc_bg;
            this.finish_btn = finish_btn;
            this.go_btn = go_btn;
            this.reward_notice_bg = reward_notice_bg;
            this.topic_desc_btn = topic_desc_btn;
            this.view_reward_btn = view_reward_btn;
            this.list = list;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getDesc_bg() {
            return this.desc_bg;
        }

        public final String getFinish_btn() {
            return this.finish_btn;
        }

        public final String getGo_btn() {
            return this.go_btn;
        }

        public final String getReward_notice_bg() {
            return this.reward_notice_bg;
        }

        public final String getTopic_desc_btn() {
            return this.topic_desc_btn;
        }

        public final String getView_reward_btn() {
            return this.view_reward_btn;
        }

        public final List<TaskList> getList() {
            return this.list;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$TaskList;", "", SocialConstants.PARAM_APP_DESC, "", "type", "games", "target", "url", "status", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getDesc", "()Ljava/lang/String;", "getGames", "getStatus", "()I", "getTarget", "getType", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class TaskList {
        public static final int $stable = 0;
        private final String desc;
        private final String games;
        private final int status;
        private final String target;
        private final String type;
        private final String url;

        public static /* synthetic */ TaskList copy$default(TaskList taskList, String str, String str2, String str3, String str4, String str5, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = taskList.desc;
            }
            if ((i2 & 2) != 0) {
                str2 = taskList.type;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                str3 = taskList.games;
            }
            String str7 = str3;
            if ((i2 & 8) != 0) {
                str4 = taskList.target;
            }
            String str8 = str4;
            if ((i2 & 16) != 0) {
                str5 = taskList.url;
            }
            String str9 = str5;
            if ((i2 & 32) != 0) {
                i = taskList.status;
            }
            return taskList.copy(str, str6, str7, str8, str9, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getGames() {
            return this.games;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTarget() {
            return this.target;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        public final TaskList copy(String desc, String type, String games, String target, String url, int status) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(games, "games");
            Intrinsics.checkNotNullParameter(target, "target");
            Intrinsics.checkNotNullParameter(url, "url");
            return new TaskList(desc, type, games, target, url, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TaskList)) {
                return false;
            }
            TaskList taskList = (TaskList) other;
            return Intrinsics.areEqual(this.desc, taskList.desc) && Intrinsics.areEqual(this.type, taskList.type) && Intrinsics.areEqual(this.games, taskList.games) && Intrinsics.areEqual(this.target, taskList.target) && Intrinsics.areEqual(this.url, taskList.url) && this.status == taskList.status;
        }

        public int hashCode() {
            return (((((((((this.desc.hashCode() * 31) + this.type.hashCode()) * 31) + this.games.hashCode()) * 31) + this.target.hashCode()) * 31) + this.url.hashCode()) * 31) + this.status;
        }

        public String toString() {
            return "TaskList(desc=" + this.desc + ", type=" + this.type + ", games=" + this.games + ", target=" + this.target + ", url=" + this.url + ", status=" + this.status + ")";
        }

        public TaskList(String desc, String type, String games, String target, String url, int i) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(games, "games");
            Intrinsics.checkNotNullParameter(target, "target");
            Intrinsics.checkNotNullParameter(url, "url");
            this.desc = desc;
            this.type = type;
            this.games = games;
            this.target = target;
            this.url = url;
            this.status = i;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getType() {
            return this.type;
        }

        public final String getGames() {
            return this.games;
        }

        public final String getTarget() {
            return this.target;
        }

        public final String getUrl() {
            return this.url;
        }

        public final int getStatus() {
            return this.status;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Video;", "", "bg", "", "border", "cover", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBg", "()Ljava/lang/String;", "getBorder", "getCover", "getUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Video {
        public static final int $stable = 0;
        private final String bg;
        private final String border;
        private final String cover;
        private final String url;

        public static /* synthetic */ Video copy$default(Video video, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = video.bg;
            }
            if ((i & 2) != 0) {
                str2 = video.border;
            }
            if ((i & 4) != 0) {
                str3 = video.cover;
            }
            if ((i & 8) != 0) {
                str4 = video.url;
            }
            return video.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getBorder() {
            return this.border;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final Video copy(String bg, String border, String cover, String url) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(border, "border");
            Intrinsics.checkNotNullParameter(cover, "cover");
            Intrinsics.checkNotNullParameter(url, "url");
            return new Video(bg, border, cover, url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Video)) {
                return false;
            }
            Video video = (Video) other;
            return Intrinsics.areEqual(this.bg, video.bg) && Intrinsics.areEqual(this.border, video.border) && Intrinsics.areEqual(this.cover, video.cover) && Intrinsics.areEqual(this.url, video.url);
        }

        public int hashCode() {
            return (((((this.bg.hashCode() * 31) + this.border.hashCode()) * 31) + this.cover.hashCode()) * 31) + this.url.hashCode();
        }

        public String toString() {
            return "Video(bg=" + this.bg + ", border=" + this.border + ", cover=" + this.cover + ", url=" + this.url + ")";
        }

        public Video(String bg, String border, String cover, String url) {
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(border, "border");
            Intrinsics.checkNotNullParameter(cover, "cover");
            Intrinsics.checkNotNullParameter(url, "url");
            this.bg = bg;
            this.border = border;
            this.cover = cover;
            this.url = url;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getBorder() {
            return this.border;
        }

        public final String getCover() {
            return this.cover;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u0005¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\bHÆ\u0003Jm\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u0005HÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0005HÖ\u0001J\t\u0010,\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013¨\u0006-"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Detail;", "", "config", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Config;", "endtime", "", "id", "lottery_name", "", "lottery_reward_type", "lottery_status", "lottery_type", "lottery_use_type", "rule", "starttime", "(Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Config;IILjava/lang/String;IIIILjava/lang/String;I)V", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Config;", "getEndtime", "()I", "getId", "getLottery_name", "()Ljava/lang/String;", "getLottery_reward_type", "getLottery_status", "getLottery_type", "getLottery_use_type", "getRule", "getStarttime", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Detail {
        public static final int $stable = 0;
        private final Config config;
        private final int endtime;
        private final int id;
        private final String lottery_name;
        private final int lottery_reward_type;
        private final int lottery_status;
        private final int lottery_type;
        private final int lottery_use_type;
        private final String rule;
        private final int starttime;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Config getConfig() {
            return this.config;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getStarttime() {
            return this.starttime;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getEndtime() {
            return this.endtime;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLottery_name() {
            return this.lottery_name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getLottery_reward_type() {
            return this.lottery_reward_type;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getLottery_status() {
            return this.lottery_status;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getLottery_type() {
            return this.lottery_type;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getLottery_use_type() {
            return this.lottery_use_type;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getRule() {
            return this.rule;
        }

        public final Detail copy(Config config, int endtime, int id, String lottery_name, int lottery_reward_type, int lottery_status, int lottery_type, int lottery_use_type, String rule, int starttime) {
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(lottery_name, "lottery_name");
            Intrinsics.checkNotNullParameter(rule, "rule");
            return new Detail(config, endtime, id, lottery_name, lottery_reward_type, lottery_status, lottery_type, lottery_use_type, rule, starttime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Detail)) {
                return false;
            }
            Detail detail = (Detail) other;
            return Intrinsics.areEqual(this.config, detail.config) && this.endtime == detail.endtime && this.id == detail.id && Intrinsics.areEqual(this.lottery_name, detail.lottery_name) && this.lottery_reward_type == detail.lottery_reward_type && this.lottery_status == detail.lottery_status && this.lottery_type == detail.lottery_type && this.lottery_use_type == detail.lottery_use_type && Intrinsics.areEqual(this.rule, detail.rule) && this.starttime == detail.starttime;
        }

        public int hashCode() {
            return (((((((((((((((((this.config.hashCode() * 31) + this.endtime) * 31) + this.id) * 31) + this.lottery_name.hashCode()) * 31) + this.lottery_reward_type) * 31) + this.lottery_status) * 31) + this.lottery_type) * 31) + this.lottery_use_type) * 31) + this.rule.hashCode()) * 31) + this.starttime;
        }

        public String toString() {
            return "Detail(config=" + this.config + ", endtime=" + this.endtime + ", id=" + this.id + ", lottery_name=" + this.lottery_name + ", lottery_reward_type=" + this.lottery_reward_type + ", lottery_status=" + this.lottery_status + ", lottery_type=" + this.lottery_type + ", lottery_use_type=" + this.lottery_use_type + ", rule=" + this.rule + ", starttime=" + this.starttime + ")";
        }

        public Detail(Config config, int i, int i2, String lottery_name, int i3, int i4, int i5, int i6, String rule, int i7) {
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(lottery_name, "lottery_name");
            Intrinsics.checkNotNullParameter(rule, "rule");
            this.config = config;
            this.endtime = i;
            this.id = i2;
            this.lottery_name = lottery_name;
            this.lottery_reward_type = i3;
            this.lottery_status = i4;
            this.lottery_type = i5;
            this.lottery_use_type = i6;
            this.rule = rule;
            this.starttime = i7;
        }

        public final Config getConfig() {
            return this.config;
        }

        public final int getEndtime() {
            return this.endtime;
        }

        public final int getId() {
            return this.id;
        }

        public final String getLottery_name() {
            return this.lottery_name;
        }

        public final int getLottery_reward_type() {
            return this.lottery_reward_type;
        }

        public final int getLottery_status() {
            return this.lottery_status;
        }

        public final int getLottery_type() {
            return this.lottery_type;
        }

        public final int getLottery_use_type() {
            return this.lottery_use_type;
        }

        public final String getRule() {
            return this.rule;
        }

        public final int getStarttime() {
            return this.starttime;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Prize;", "", "credit", "", "id", "lottery_id", "name", "", "pic", "bg", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBg", "()Ljava/lang/String;", "getCredit", "()I", "getId", "getLottery_id", "getName", "getPic", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Prize {
        public static final int $stable = 0;
        private final String bg;
        private final int credit;
        private final int id;
        private final int lottery_id;
        private final String name;
        private final String pic;

        public static /* synthetic */ Prize copy$default(Prize prize, int i, int i2, int i3, String str, String str2, String str3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = prize.credit;
            }
            if ((i4 & 2) != 0) {
                i2 = prize.id;
            }
            int i5 = i2;
            if ((i4 & 4) != 0) {
                i3 = prize.lottery_id;
            }
            int i6 = i3;
            if ((i4 & 8) != 0) {
                str = prize.name;
            }
            String str4 = str;
            if ((i4 & 16) != 0) {
                str2 = prize.pic;
            }
            String str5 = str2;
            if ((i4 & 32) != 0) {
                str3 = prize.bg;
            }
            return prize.copy(i, i5, i6, str4, str5, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCredit() {
            return this.credit;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getLottery_id() {
            return this.lottery_id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        public final Prize copy(int credit, int id, int lottery_id, String name, String pic, String bg) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(bg, "bg");
            return new Prize(credit, id, lottery_id, name, pic, bg);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Prize)) {
                return false;
            }
            Prize prize = (Prize) other;
            return this.credit == prize.credit && this.id == prize.id && this.lottery_id == prize.lottery_id && Intrinsics.areEqual(this.name, prize.name) && Intrinsics.areEqual(this.pic, prize.pic) && Intrinsics.areEqual(this.bg, prize.bg);
        }

        public int hashCode() {
            return (((((((((this.credit * 31) + this.id) * 31) + this.lottery_id) * 31) + this.name.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.bg.hashCode();
        }

        public String toString() {
            return "Prize(credit=" + this.credit + ", id=" + this.id + ", lottery_id=" + this.lottery_id + ", name=" + this.name + ", pic=" + this.pic + ", bg=" + this.bg + ")";
        }

        public Prize(int i, int i2, int i3, String name, String pic, String bg) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(bg, "bg");
            this.credit = i;
            this.id = i2;
            this.lottery_id = i3;
            this.name = name;
            this.pic = pic;
            this.bg = bg;
        }

        public final int getCredit() {
            return this.credit;
        }

        public final int getId() {
            return this.id;
        }

        public final int getLottery_id() {
            return this.lottery_id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPic() {
            return this.pic;
        }

        public final String getBg() {
            return this.bg;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Config;", "", "use", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Use;", "(Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Use;)V", "getUse", "()Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Use;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Config {
        public static final int $stable = 0;
        private final Use use;

        public static /* synthetic */ Config copy$default(Config config, Use use, int i, Object obj) {
            if ((i & 1) != 0) {
                use = config.use;
            }
            return config.copy(use);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Use getUse() {
            return this.use;
        }

        public final Config copy(Use use) {
            Intrinsics.checkNotNullParameter(use, "use");
            return new Config(use);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Config) && Intrinsics.areEqual(this.use, ((Config) other).use);
        }

        public int hashCode() {
            return this.use.hashCode();
        }

        public String toString() {
            return "Config(use=" + this.use + ")";
        }

        public Config(Use use) {
            Intrinsics.checkNotNullParameter(use, "use");
            this.use = use;
        }

        public final Use getUse() {
            return this.use;
        }
    }

    /* JADX INFO: compiled from: TopicDetailBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Use;", "", "one", "", "ten", "(Ljava/lang/String;Ljava/lang/String;)V", "getOne", "()Ljava/lang/String;", "getTen", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Use {
        public static final int $stable = 0;
        private final String one;
        private final String ten;

        public static /* synthetic */ Use copy$default(Use use, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = use.one;
            }
            if ((i & 2) != 0) {
                str2 = use.ten;
            }
            return use.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOne() {
            return this.one;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTen() {
            return this.ten;
        }

        public final Use copy(String one, String ten) {
            Intrinsics.checkNotNullParameter(one, "one");
            Intrinsics.checkNotNullParameter(ten, "ten");
            return new Use(one, ten);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Use)) {
                return false;
            }
            Use use = (Use) other;
            return Intrinsics.areEqual(this.one, use.one) && Intrinsics.areEqual(this.ten, use.ten);
        }

        public int hashCode() {
            return (this.one.hashCode() * 31) + this.ten.hashCode();
        }

        public String toString() {
            return "Use(one=" + this.one + ", ten=" + this.ten + ")";
        }

        public Use(String one, String ten) {
            Intrinsics.checkNotNullParameter(one, "one");
            Intrinsics.checkNotNullParameter(ten, "ten");
            this.one = one;
            this.ten = ten;
        }

        public final String getOne() {
            return this.one;
        }

        public final String getTen() {
            return this.ten;
        }
    }
}
