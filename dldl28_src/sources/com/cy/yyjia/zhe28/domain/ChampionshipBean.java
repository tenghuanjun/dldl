package com.cy.yyjia.zhe28.domain;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChampionshipBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0004\u001f !\"B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\nHÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ChampionshipBean;", "", "activity_info", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$ActivityInfo;", "prizes", "", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Prize;", "tasks", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task;", "user_status", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$UserStatus;", "(Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$ActivityInfo;Ljava/util/List;Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$UserStatus;)V", "getActivity_info", "()Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$ActivityInfo;", "getPrizes", "()Ljava/util/List;", "getTasks", "getUser_status", "()Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$UserStatus;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ActivityInfo", "Prize", "Task", "UserStatus", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ChampionshipBean {
    public static final int $stable = 8;
    private final ActivityInfo activity_info;
    private final List<Prize> prizes;
    private final List<Task> tasks;
    private final UserStatus user_status;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ChampionshipBean copy$default(ChampionshipBean championshipBean, ActivityInfo activityInfo, List list, List list2, UserStatus userStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            activityInfo = championshipBean.activity_info;
        }
        if ((i & 2) != 0) {
            list = championshipBean.prizes;
        }
        if ((i & 4) != 0) {
            list2 = championshipBean.tasks;
        }
        if ((i & 8) != 0) {
            userStatus = championshipBean.user_status;
        }
        return championshipBean.copy(activityInfo, list, list2, userStatus);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ActivityInfo getActivity_info() {
        return this.activity_info;
    }

    public final List<Prize> component2() {
        return this.prizes;
    }

    public final List<Task> component3() {
        return this.tasks;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final UserStatus getUser_status() {
        return this.user_status;
    }

    public final ChampionshipBean copy(ActivityInfo activity_info, List<Prize> prizes, List<Task> tasks, UserStatus user_status) {
        Intrinsics.checkNotNullParameter(activity_info, "activity_info");
        Intrinsics.checkNotNullParameter(prizes, "prizes");
        Intrinsics.checkNotNullParameter(tasks, "tasks");
        Intrinsics.checkNotNullParameter(user_status, "user_status");
        return new ChampionshipBean(activity_info, prizes, tasks, user_status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChampionshipBean)) {
            return false;
        }
        ChampionshipBean championshipBean = (ChampionshipBean) other;
        return Intrinsics.areEqual(this.activity_info, championshipBean.activity_info) && Intrinsics.areEqual(this.prizes, championshipBean.prizes) && Intrinsics.areEqual(this.tasks, championshipBean.tasks) && Intrinsics.areEqual(this.user_status, championshipBean.user_status);
    }

    public int hashCode() {
        return (((((this.activity_info.hashCode() * 31) + this.prizes.hashCode()) * 31) + this.tasks.hashCode()) * 31) + this.user_status.hashCode();
    }

    public String toString() {
        return "ChampionshipBean(activity_info=" + this.activity_info + ", prizes=" + this.prizes + ", tasks=" + this.tasks + ", user_status=" + this.user_status + ")";
    }

    public ChampionshipBean(ActivityInfo activity_info, List<Prize> prizes, List<Task> tasks, UserStatus user_status) {
        Intrinsics.checkNotNullParameter(activity_info, "activity_info");
        Intrinsics.checkNotNullParameter(prizes, "prizes");
        Intrinsics.checkNotNullParameter(tasks, "tasks");
        Intrinsics.checkNotNullParameter(user_status, "user_status");
        this.activity_info = activity_info;
        this.prizes = prizes;
        this.tasks = tasks;
        this.user_status = user_status;
    }

    public final ActivityInfo getActivity_info() {
        return this.activity_info;
    }

    public final List<Prize> getPrizes() {
        return this.prizes;
    }

    public final List<Task> getTasks() {
        return this.tasks;
    }

    public final UserStatus getUser_status() {
        return this.user_status;
    }

    /* JADX INFO: compiled from: ChampionshipBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003Jw\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005HÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013¨\u0006."}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$ActivityInfo;", "", ConstantsAPI.WXWebPage.KEY_ACTIVITY_ID, "", SocialConstants.PARAM_COMMENT, "", "draw_time", "name", "participant_count", "post_id", "post_title", MetricsSQLiteCacheKt.METRICS_START_TIME, "status", "rule", "status_text", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActivity_id", "()I", "getDescription", "()Ljava/lang/String;", "getDraw_time", "getName", "getParticipant_count", "getPost_id", "getPost_title", "getRule", "getStart_time", "getStatus", "getStatus_text", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ActivityInfo {
        public static final int $stable = 0;
        private final int activity_id;
        private final String description;
        private final String draw_time;
        private final String name;
        private final int participant_count;
        private final int post_id;
        private final String post_title;
        private final String rule;
        private final String start_time;
        private final String status;
        private final String status_text;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getActivity_id() {
            return this.activity_id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getRule() {
            return this.rule;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getStatus_text() {
            return this.status_text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDraw_time() {
            return this.draw_time;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getParticipant_count() {
            return this.participant_count;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getPost_id() {
            return this.post_id;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getPost_title() {
            return this.post_title;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getStart_time() {
            return this.start_time;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final ActivityInfo copy(int activity_id, String description, String draw_time, String name, int participant_count, int post_id, String post_title, String start_time, String status, String rule, String status_text) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(draw_time, "draw_time");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(post_title, "post_title");
            Intrinsics.checkNotNullParameter(start_time, "start_time");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(status_text, "status_text");
            return new ActivityInfo(activity_id, description, draw_time, name, participant_count, post_id, post_title, start_time, status, rule, status_text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActivityInfo)) {
                return false;
            }
            ActivityInfo activityInfo = (ActivityInfo) other;
            return this.activity_id == activityInfo.activity_id && Intrinsics.areEqual(this.description, activityInfo.description) && Intrinsics.areEqual(this.draw_time, activityInfo.draw_time) && Intrinsics.areEqual(this.name, activityInfo.name) && this.participant_count == activityInfo.participant_count && this.post_id == activityInfo.post_id && Intrinsics.areEqual(this.post_title, activityInfo.post_title) && Intrinsics.areEqual(this.start_time, activityInfo.start_time) && Intrinsics.areEqual(this.status, activityInfo.status) && Intrinsics.areEqual(this.rule, activityInfo.rule) && Intrinsics.areEqual(this.status_text, activityInfo.status_text);
        }

        public int hashCode() {
            return (((((((((((((((((((this.activity_id * 31) + this.description.hashCode()) * 31) + this.draw_time.hashCode()) * 31) + this.name.hashCode()) * 31) + this.participant_count) * 31) + this.post_id) * 31) + this.post_title.hashCode()) * 31) + this.start_time.hashCode()) * 31) + this.status.hashCode()) * 31) + this.rule.hashCode()) * 31) + this.status_text.hashCode();
        }

        public String toString() {
            return "ActivityInfo(activity_id=" + this.activity_id + ", description=" + this.description + ", draw_time=" + this.draw_time + ", name=" + this.name + ", participant_count=" + this.participant_count + ", post_id=" + this.post_id + ", post_title=" + this.post_title + ", start_time=" + this.start_time + ", status=" + this.status + ", rule=" + this.rule + ", status_text=" + this.status_text + ")";
        }

        public ActivityInfo(int i, String description, String draw_time, String name, int i2, int i3, String post_title, String start_time, String status, String rule, String status_text) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(draw_time, "draw_time");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(post_title, "post_title");
            Intrinsics.checkNotNullParameter(start_time, "start_time");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(rule, "rule");
            Intrinsics.checkNotNullParameter(status_text, "status_text");
            this.activity_id = i;
            this.description = description;
            this.draw_time = draw_time;
            this.name = name;
            this.participant_count = i2;
            this.post_id = i3;
            this.post_title = post_title;
            this.start_time = start_time;
            this.status = status;
            this.rule = rule;
            this.status_text = status_text;
        }

        public final int getActivity_id() {
            return this.activity_id;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getDraw_time() {
            return this.draw_time;
        }

        public final String getName() {
            return this.name;
        }

        public final int getParticipant_count() {
            return this.participant_count;
        }

        public final int getPost_id() {
            return this.post_id;
        }

        public final String getPost_title() {
            return this.post_title;
        }

        public final String getStart_time() {
            return this.start_time;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getRule() {
            return this.rule;
        }

        public final String getStatus_text() {
            return this.status_text;
        }
    }

    /* JADX INFO: compiled from: ChampionshipBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$UserStatus;", "", "all_tasks_completed", "", "completed_count", "has_participated", "total_count", "(IIII)V", "getAll_tasks_completed", "()I", "getCompleted_count", "getHas_participated", "getTotal_count", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class UserStatus {
        public static final int $stable = 0;
        private final int all_tasks_completed;
        private final int completed_count;
        private final int has_participated;
        private final int total_count;

        public static /* synthetic */ UserStatus copy$default(UserStatus userStatus, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = userStatus.all_tasks_completed;
            }
            if ((i5 & 2) != 0) {
                i2 = userStatus.completed_count;
            }
            if ((i5 & 4) != 0) {
                i3 = userStatus.has_participated;
            }
            if ((i5 & 8) != 0) {
                i4 = userStatus.total_count;
            }
            return userStatus.copy(i, i2, i3, i4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getAll_tasks_completed() {
            return this.all_tasks_completed;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCompleted_count() {
            return this.completed_count;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getHas_participated() {
            return this.has_participated;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getTotal_count() {
            return this.total_count;
        }

        public final UserStatus copy(int all_tasks_completed, int completed_count, int has_participated, int total_count) {
            return new UserStatus(all_tasks_completed, completed_count, has_participated, total_count);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserStatus)) {
                return false;
            }
            UserStatus userStatus = (UserStatus) other;
            return this.all_tasks_completed == userStatus.all_tasks_completed && this.completed_count == userStatus.completed_count && this.has_participated == userStatus.has_participated && this.total_count == userStatus.total_count;
        }

        public int hashCode() {
            return (((((this.all_tasks_completed * 31) + this.completed_count) * 31) + this.has_participated) * 31) + this.total_count;
        }

        public String toString() {
            return "UserStatus(all_tasks_completed=" + this.all_tasks_completed + ", completed_count=" + this.completed_count + ", has_participated=" + this.has_participated + ", total_count=" + this.total_count + ")";
        }

        public UserStatus(int i, int i2, int i3, int i4) {
            this.all_tasks_completed = i;
            this.completed_count = i2;
            this.has_participated = i3;
            this.total_count = i4;
        }

        public final int getAll_tasks_completed() {
            return this.all_tasks_completed;
        }

        public final int getCompleted_count() {
            return this.completed_count;
        }

        public final int getHas_participated() {
            return this.has_participated;
        }

        public final int getTotal_count() {
            return this.total_count;
        }
    }

    /* JADX INFO: compiled from: ChampionshipBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001dB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task;", "", "config", "Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task$Config;", "is_completed", "", "task_id", "task_name", "", "task_type", "(Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task$Config;IILjava/lang/String;I)V", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task$Config;", "()I", "getTask_id", "getTask_name", "()Ljava/lang/String;", "getTask_type", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Config", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Task {
        public static final int $stable = 0;
        private final Config config;
        private final int is_completed;
        private final int task_id;
        private final String task_name;
        private final int task_type;

        public static /* synthetic */ Task copy$default(Task task, Config config, int i, int i2, String str, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                config = task.config;
            }
            if ((i4 & 2) != 0) {
                i = task.is_completed;
            }
            int i5 = i;
            if ((i4 & 4) != 0) {
                i2 = task.task_id;
            }
            int i6 = i2;
            if ((i4 & 8) != 0) {
                str = task.task_name;
            }
            String str2 = str;
            if ((i4 & 16) != 0) {
                i3 = task.task_type;
            }
            return task.copy(config, i5, i6, str2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Config getConfig() {
            return this.config;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getIs_completed() {
            return this.is_completed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getTask_id() {
            return this.task_id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTask_name() {
            return this.task_name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTask_type() {
            return this.task_type;
        }

        public final Task copy(Config config, int is_completed, int task_id, String task_name, int task_type) {
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(task_name, "task_name");
            return new Task(config, is_completed, task_id, task_name, task_type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Task)) {
                return false;
            }
            Task task = (Task) other;
            return Intrinsics.areEqual(this.config, task.config) && this.is_completed == task.is_completed && this.task_id == task.task_id && Intrinsics.areEqual(this.task_name, task.task_name) && this.task_type == task.task_type;
        }

        public int hashCode() {
            return (((((((this.config.hashCode() * 31) + this.is_completed) * 31) + this.task_id) * 31) + this.task_name.hashCode()) * 31) + this.task_type;
        }

        public String toString() {
            return "Task(config=" + this.config + ", is_completed=" + this.is_completed + ", task_id=" + this.task_id + ", task_name=" + this.task_name + ", task_type=" + this.task_type + ")";
        }

        public Task(Config config, int i, int i2, String task_name, int i3) {
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(task_name, "task_name");
            this.config = config;
            this.is_completed = i;
            this.task_id = i2;
            this.task_name = task_name;
            this.task_type = i3;
        }

        public final Config getConfig() {
            return this.config;
        }

        public final int is_completed() {
            return this.is_completed;
        }

        public final int getTask_id() {
            return this.task_id;
        }

        public final String getTask_name() {
            return this.task_name;
        }

        public final int getTask_type() {
            return this.task_type;
        }

        /* JADX INFO: compiled from: ChampionshipBean.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Task$Config;", "", "min_comment_length", "", "require_image", "(II)V", "getMin_comment_length", "()I", "getRequire_image", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Config {
            public static final int $stable = 0;
            private final int min_comment_length;
            private final int require_image;

            public static /* synthetic */ Config copy$default(Config config, int i, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = config.min_comment_length;
                }
                if ((i3 & 2) != 0) {
                    i2 = config.require_image;
                }
                return config.copy(i, i2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getMin_comment_length() {
                return this.min_comment_length;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getRequire_image() {
                return this.require_image;
            }

            public final Config copy(int min_comment_length, int require_image) {
                return new Config(min_comment_length, require_image);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Config)) {
                    return false;
                }
                Config config = (Config) other;
                return this.min_comment_length == config.min_comment_length && this.require_image == config.require_image;
            }

            public int hashCode() {
                return (this.min_comment_length * 31) + this.require_image;
            }

            public String toString() {
                return "Config(min_comment_length=" + this.min_comment_length + ", require_image=" + this.require_image + ")";
            }

            public Config(int i, int i2) {
                this.min_comment_length = i;
                this.require_image = i2;
            }

            public final int getMin_comment_length() {
                return this.min_comment_length;
            }

            public final int getRequire_image() {
                return this.require_image;
            }
        }
    }

    /* JADX INFO: compiled from: ChampionshipBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ChampionshipBean$Prize;", "", "prize_id", "", "prize_name", "", "reward_content", "reward_icon", "winner_count", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getPrize_id", "()I", "getPrize_name", "()Ljava/lang/String;", "getReward_content", "getReward_icon", "getWinner_count", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Prize {
        public static final int $stable = 0;
        private final int prize_id;
        private final String prize_name;
        private final String reward_content;
        private final String reward_icon;
        private final int winner_count;

        public static /* synthetic */ Prize copy$default(Prize prize, int i, String str, String str2, String str3, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = prize.prize_id;
            }
            if ((i3 & 2) != 0) {
                str = prize.prize_name;
            }
            String str4 = str;
            if ((i3 & 4) != 0) {
                str2 = prize.reward_content;
            }
            String str5 = str2;
            if ((i3 & 8) != 0) {
                str3 = prize.reward_icon;
            }
            String str6 = str3;
            if ((i3 & 16) != 0) {
                i2 = prize.winner_count;
            }
            return prize.copy(i, str4, str5, str6, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getPrize_id() {
            return this.prize_id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPrize_name() {
            return this.prize_name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getReward_content() {
            return this.reward_content;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getReward_icon() {
            return this.reward_icon;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getWinner_count() {
            return this.winner_count;
        }

        public final Prize copy(int prize_id, String prize_name, String reward_content, String reward_icon, int winner_count) {
            Intrinsics.checkNotNullParameter(prize_name, "prize_name");
            Intrinsics.checkNotNullParameter(reward_content, "reward_content");
            Intrinsics.checkNotNullParameter(reward_icon, "reward_icon");
            return new Prize(prize_id, prize_name, reward_content, reward_icon, winner_count);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Prize)) {
                return false;
            }
            Prize prize = (Prize) other;
            return this.prize_id == prize.prize_id && Intrinsics.areEqual(this.prize_name, prize.prize_name) && Intrinsics.areEqual(this.reward_content, prize.reward_content) && Intrinsics.areEqual(this.reward_icon, prize.reward_icon) && this.winner_count == prize.winner_count;
        }

        public int hashCode() {
            return (((((((this.prize_id * 31) + this.prize_name.hashCode()) * 31) + this.reward_content.hashCode()) * 31) + this.reward_icon.hashCode()) * 31) + this.winner_count;
        }

        public String toString() {
            return "Prize(prize_id=" + this.prize_id + ", prize_name=" + this.prize_name + ", reward_content=" + this.reward_content + ", reward_icon=" + this.reward_icon + ", winner_count=" + this.winner_count + ")";
        }

        public Prize(int i, String prize_name, String reward_content, String reward_icon, int i2) {
            Intrinsics.checkNotNullParameter(prize_name, "prize_name");
            Intrinsics.checkNotNullParameter(reward_content, "reward_content");
            Intrinsics.checkNotNullParameter(reward_icon, "reward_icon");
            this.prize_id = i;
            this.prize_name = prize_name;
            this.reward_content = reward_content;
            this.reward_icon = reward_icon;
            this.winner_count = i2;
        }

        public final int getPrize_id() {
            return this.prize_id;
        }

        public final String getPrize_name() {
            return this.prize_name;
        }

        public final String getReward_content() {
            return this.reward_content;
        }

        public final String getReward_icon() {
            return this.reward_icon;
        }

        public final int getWinner_count() {
            return this.winner_count;
        }
    }
}
