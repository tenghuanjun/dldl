package com.cy.yyjia.zhe28.domain;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.by.sjlr.cq28.wxapi.WXEntryActivity$$ExternalSyntheticApiModelOutline0;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: TaskResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0003567Bg\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003¢\u0006\u0002\u0010\u0012J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0004HÆ\u0003J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\rHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003HÆ\u0003J\u007f\u0010,\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\rHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001d¨\u00068"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TaskResult;", "", "cate", "", "Lcom/cy/yyjia/zhe28/domain/TaskResult$Daily;", "growup", "daily", "rookie", "sign", "Lcom/cy/yyjia/zhe28/domain/TaskResult$Sign;", "user", "Lcom/cy/yyjia/zhe28/domain/TaskResult$User;", "totalUser", "", "userRealMoney", "userList", "gameList", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/TaskResult$Daily;Lcom/cy/yyjia/zhe28/domain/TaskResult$Daily;Lcom/cy/yyjia/zhe28/domain/TaskResult$Daily;Lcom/cy/yyjia/zhe28/domain/TaskResult$Sign;Lcom/cy/yyjia/zhe28/domain/TaskResult$User;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getCate", "()Ljava/util/List;", "getDaily", "()Lcom/cy/yyjia/zhe28/domain/TaskResult$Daily;", "getGameList", "getGrowup", "getRookie", "getSign", "()Lcom/cy/yyjia/zhe28/domain/TaskResult$Sign;", "getTotalUser", "()Ljava/lang/String;", "getUser", "()Lcom/cy/yyjia/zhe28/domain/TaskResult$User;", "getUserList", "getUserRealMoney", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getAllTask", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "hashCode", "", "toString", "Daily", "Sign", "User", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TaskResult {
    public static final int $stable = 8;
    private final List<Daily> cate;
    private final Daily daily;
    private final List<GameBean> gameList;
    private final Daily growup;
    private final Daily rookie;
    private final Sign sign;
    private final String totalUser;
    private final User user;
    private final List<User> userList;
    private final String userRealMoney;

    public final List<Daily> component1() {
        return this.cate;
    }

    public final List<GameBean> component10() {
        return this.gameList;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Daily getGrowup() {
        return this.growup;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Daily getDaily() {
        return this.daily;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Daily getRookie() {
        return this.rookie;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Sign getSign() {
        return this.sign;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final User getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getTotalUser() {
        return this.totalUser;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUserRealMoney() {
        return this.userRealMoney;
    }

    public final List<User> component9() {
        return this.userList;
    }

    public final TaskResult copy(List<Daily> cate, Daily growup, Daily daily, Daily rookie, Sign sign, User user, String totalUser, String userRealMoney, List<User> userList, List<GameBean> gameList) {
        Intrinsics.checkNotNullParameter(cate, "cate");
        Intrinsics.checkNotNullParameter(growup, "growup");
        Intrinsics.checkNotNullParameter(daily, "daily");
        Intrinsics.checkNotNullParameter(rookie, "rookie");
        Intrinsics.checkNotNullParameter(sign, "sign");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(totalUser, "totalUser");
        Intrinsics.checkNotNullParameter(userRealMoney, "userRealMoney");
        Intrinsics.checkNotNullParameter(userList, "userList");
        Intrinsics.checkNotNullParameter(gameList, "gameList");
        return new TaskResult(cate, growup, daily, rookie, sign, user, totalUser, userRealMoney, userList, gameList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskResult)) {
            return false;
        }
        TaskResult taskResult = (TaskResult) other;
        return Intrinsics.areEqual(this.cate, taskResult.cate) && Intrinsics.areEqual(this.growup, taskResult.growup) && Intrinsics.areEqual(this.daily, taskResult.daily) && Intrinsics.areEqual(this.rookie, taskResult.rookie) && Intrinsics.areEqual(this.sign, taskResult.sign) && Intrinsics.areEqual(this.user, taskResult.user) && Intrinsics.areEqual(this.totalUser, taskResult.totalUser) && Intrinsics.areEqual(this.userRealMoney, taskResult.userRealMoney) && Intrinsics.areEqual(this.userList, taskResult.userList) && Intrinsics.areEqual(this.gameList, taskResult.gameList);
    }

    public int hashCode() {
        return (((((((((((((((((this.cate.hashCode() * 31) + this.growup.hashCode()) * 31) + this.daily.hashCode()) * 31) + this.rookie.hashCode()) * 31) + this.sign.hashCode()) * 31) + this.user.hashCode()) * 31) + this.totalUser.hashCode()) * 31) + this.userRealMoney.hashCode()) * 31) + this.userList.hashCode()) * 31) + this.gameList.hashCode();
    }

    public String toString() {
        return "TaskResult(cate=" + this.cate + ", growup=" + this.growup + ", daily=" + this.daily + ", rookie=" + this.rookie + ", sign=" + this.sign + ", user=" + this.user + ", totalUser=" + this.totalUser + ", userRealMoney=" + this.userRealMoney + ", userList=" + this.userList + ", gameList=" + this.gameList + ")";
    }

    public TaskResult(List<Daily> cate, Daily growup, Daily daily, Daily rookie, Sign sign, User user, String totalUser, String userRealMoney, List<User> userList, List<GameBean> gameList) {
        Intrinsics.checkNotNullParameter(cate, "cate");
        Intrinsics.checkNotNullParameter(growup, "growup");
        Intrinsics.checkNotNullParameter(daily, "daily");
        Intrinsics.checkNotNullParameter(rookie, "rookie");
        Intrinsics.checkNotNullParameter(sign, "sign");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(totalUser, "totalUser");
        Intrinsics.checkNotNullParameter(userRealMoney, "userRealMoney");
        Intrinsics.checkNotNullParameter(userList, "userList");
        Intrinsics.checkNotNullParameter(gameList, "gameList");
        this.cate = cate;
        this.growup = growup;
        this.daily = daily;
        this.rookie = rookie;
        this.sign = sign;
        this.user = user;
        this.totalUser = totalUser;
        this.userRealMoney = userRealMoney;
        this.userList = userList;
        this.gameList = gameList;
    }

    public final List<Daily> getCate() {
        return this.cate;
    }

    public final Daily getGrowup() {
        return this.growup;
    }

    public final Daily getDaily() {
        return this.daily;
    }

    public final Daily getRookie() {
        return this.rookie;
    }

    public final Sign getSign() {
        return this.sign;
    }

    public final User getUser() {
        return this.user;
    }

    public final String getTotalUser() {
        return this.totalUser;
    }

    public final String getUserRealMoney() {
        return this.userRealMoney;
    }

    public final List<User> getUserList() {
        return this.userList;
    }

    public final List<GameBean> getGameList() {
        return this.gameList;
    }

    public final List<TaskBean> getAllTask() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.daily.getList());
        arrayList.addAll(this.rookie.getList());
        return arrayList;
    }

    /* JADX INFO: compiled from: TaskResult.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J_\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020\"J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006*"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TaskResult$Daily;", "", SocialConstants.PARAM_COMMENT, "", SocialConstants.PARAM_APP_DESC, "done", "list", "", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "name", "totalNum", "finishNum", "pic", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getDescription", "getDone", "getFinishNum", "getList", "()Ljava/util/List;", "getName", "getPic", "getTotalNum", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "getFormatTitle", "Landroid/text/Spanned;", "hasReward", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Daily {
        public static final int $stable = 8;
        private final String desc;
        private final String description;
        private final String done;
        private final String finishNum;
        private final List<TaskBean> list;
        private final String name;
        private final String pic;
        private final String totalNum;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDone() {
            return this.done;
        }

        public final List<TaskBean> component4() {
            return this.list;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getTotalNum() {
            return this.totalNum;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getFinishNum() {
            return this.finishNum;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        public final Daily copy(String description, String desc, String done, List<TaskBean> list, String name, String totalNum, String finishNum, String pic) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(done, "done");
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(totalNum, "totalNum");
            Intrinsics.checkNotNullParameter(finishNum, "finishNum");
            Intrinsics.checkNotNullParameter(pic, "pic");
            return new Daily(description, desc, done, list, name, totalNum, finishNum, pic);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Daily)) {
                return false;
            }
            Daily daily = (Daily) other;
            return Intrinsics.areEqual(this.description, daily.description) && Intrinsics.areEqual(this.desc, daily.desc) && Intrinsics.areEqual(this.done, daily.done) && Intrinsics.areEqual(this.list, daily.list) && Intrinsics.areEqual(this.name, daily.name) && Intrinsics.areEqual(this.totalNum, daily.totalNum) && Intrinsics.areEqual(this.finishNum, daily.finishNum) && Intrinsics.areEqual(this.pic, daily.pic);
        }

        public int hashCode() {
            return (((((((((((((this.description.hashCode() * 31) + this.desc.hashCode()) * 31) + this.done.hashCode()) * 31) + this.list.hashCode()) * 31) + this.name.hashCode()) * 31) + this.totalNum.hashCode()) * 31) + this.finishNum.hashCode()) * 31) + this.pic.hashCode();
        }

        public String toString() {
            return "Daily(description=" + this.description + ", desc=" + this.desc + ", done=" + this.done + ", list=" + this.list + ", name=" + this.name + ", totalNum=" + this.totalNum + ", finishNum=" + this.finishNum + ", pic=" + this.pic + ")";
        }

        public Daily(String description, String desc, String done, List<TaskBean> list, String name, String totalNum, String finishNum, String pic) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(done, "done");
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(totalNum, "totalNum");
            Intrinsics.checkNotNullParameter(finishNum, "finishNum");
            Intrinsics.checkNotNullParameter(pic, "pic");
            this.description = description;
            this.desc = desc;
            this.done = done;
            this.list = list;
            this.name = name;
            this.totalNum = totalNum;
            this.finishNum = finishNum;
            this.pic = pic;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getDone() {
            return this.done;
        }

        public final List<TaskBean> getList() {
            return this.list;
        }

        public final String getName() {
            return this.name;
        }

        public final String getTotalNum() {
            return this.totalNum;
        }

        public final String getFinishNum() {
            return this.finishNum;
        }

        public final String getPic() {
            return this.pic;
        }

        public final Spanned getFormatTitle() {
            SpannableString spannableString = new SpannableString(this.name + StringUtils.SPACE + this.finishNum + "/" + this.totalNum);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, this.name.length(), 33);
            spannableString.setSpan(new RelativeSizeSpan(1.16f), 0, this.name.length(), 33);
            if (Build.VERSION.SDK_INT >= 28) {
                WXEntryActivity$$ExternalSyntheticApiModelOutline0.m();
                spannableString.setSpan(WXEntryActivity$$ExternalSyntheticApiModelOutline0.m(Typeface.DEFAULT_BOLD), 0, this.name.length(), 33);
            }
            return spannableString;
        }

        public final boolean hasReward() {
            Iterator<TaskBean> it = this.list.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(it.next().getDone(), "not_receive")) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: TaskResult.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010 \u001a\u00020\u0005J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\"\u001a\u00020\u001eJ\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TaskResult$Sign;", "", "credit", "", SocialConstants.PARAM_COMMENT, "", "done", "name", "nextNum", "opName", "pic", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getCredit", "()I", "getDescription", "()Ljava/lang/String;", "getDone", "getName", "getNextNum", "getOpName", "getPic", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "getDay", "hashCode", "isSign", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Sign {
        public static final int $stable = 0;
        private final int credit;
        private final String description;
        private final String done;
        private final String name;
        private final int nextNum;
        private final String opName;
        private final String pic;

        public static /* synthetic */ Sign copy$default(Sign sign, int i, String str, String str2, String str3, int i2, String str4, String str5, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = sign.credit;
            }
            if ((i3 & 2) != 0) {
                str = sign.description;
            }
            String str6 = str;
            if ((i3 & 4) != 0) {
                str2 = sign.done;
            }
            String str7 = str2;
            if ((i3 & 8) != 0) {
                str3 = sign.name;
            }
            String str8 = str3;
            if ((i3 & 16) != 0) {
                i2 = sign.nextNum;
            }
            int i4 = i2;
            if ((i3 & 32) != 0) {
                str4 = sign.opName;
            }
            String str9 = str4;
            if ((i3 & 64) != 0) {
                str5 = sign.pic;
            }
            return sign.copy(i, str6, str7, str8, i4, str9, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCredit() {
            return this.credit;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDone() {
            return this.done;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getNextNum() {
            return this.nextNum;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getOpName() {
            return this.opName;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        public final Sign copy(int credit, String description, String done, String name, int nextNum, String opName, String pic) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(done, "done");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(opName, "opName");
            Intrinsics.checkNotNullParameter(pic, "pic");
            return new Sign(credit, description, done, name, nextNum, opName, pic);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Sign)) {
                return false;
            }
            Sign sign = (Sign) other;
            return this.credit == sign.credit && Intrinsics.areEqual(this.description, sign.description) && Intrinsics.areEqual(this.done, sign.done) && Intrinsics.areEqual(this.name, sign.name) && this.nextNum == sign.nextNum && Intrinsics.areEqual(this.opName, sign.opName) && Intrinsics.areEqual(this.pic, sign.pic);
        }

        public int hashCode() {
            return (((((((((((this.credit * 31) + this.description.hashCode()) * 31) + this.done.hashCode()) * 31) + this.name.hashCode()) * 31) + this.nextNum) * 31) + this.opName.hashCode()) * 31) + this.pic.hashCode();
        }

        public String toString() {
            return "Sign(credit=" + this.credit + ", description=" + this.description + ", done=" + this.done + ", name=" + this.name + ", nextNum=" + this.nextNum + ", opName=" + this.opName + ", pic=" + this.pic + ")";
        }

        public Sign(int i, String description, String done, String name, int i2, String opName, String pic) {
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(done, "done");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(opName, "opName");
            Intrinsics.checkNotNullParameter(pic, "pic");
            this.credit = i;
            this.description = description;
            this.done = done;
            this.name = name;
            this.nextNum = i2;
            this.opName = opName;
            this.pic = pic;
        }

        public final int getCredit() {
            return this.credit;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getDone() {
            return this.done;
        }

        public final String getName() {
            return this.name;
        }

        public final int getNextNum() {
            return this.nextNum;
        }

        public final String getOpName() {
            return this.opName;
        }

        public final String getPic() {
            return this.pic;
        }

        public final String getDay() {
            return String.valueOf(this.nextNum - 1);
        }

        public final boolean isSign() {
            return Intrinsics.areEqual(BooleanUtils.YES, this.done);
        }
    }

    /* JADX INFO: compiled from: TaskResult.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TaskResult$User;", "", "userName", "", "avatar", "allCredit", "welfare", "zheCredit", SocialConstants.PARAM_APP_DESC, "hunt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAllCredit", "()Ljava/lang/String;", "getAvatar", "getDesc", "getHunt", "getUserName", "getWelfare", "getZheCredit", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class User {
        public static final int $stable = 0;
        private final String allCredit;
        private final String avatar;
        private final String desc;
        private final String hunt;
        private final String userName;
        private final String welfare;
        private final String zheCredit;

        public static /* synthetic */ User copy$default(User user, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = user.userName;
            }
            if ((i & 2) != 0) {
                str2 = user.avatar;
            }
            String str8 = str2;
            if ((i & 4) != 0) {
                str3 = user.allCredit;
            }
            String str9 = str3;
            if ((i & 8) != 0) {
                str4 = user.welfare;
            }
            String str10 = str4;
            if ((i & 16) != 0) {
                str5 = user.zheCredit;
            }
            String str11 = str5;
            if ((i & 32) != 0) {
                str6 = user.desc;
            }
            String str12 = str6;
            if ((i & 64) != 0) {
                str7 = user.hunt;
            }
            return user.copy(str, str8, str9, str10, str11, str12, str7);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUserName() {
            return this.userName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAvatar() {
            return this.avatar;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAllCredit() {
            return this.allCredit;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getWelfare() {
            return this.welfare;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getZheCredit() {
            return this.zheCredit;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getHunt() {
            return this.hunt;
        }

        public final User copy(String userName, String avatar, String allCredit, String welfare, String zheCredit, String desc, String hunt) {
            Intrinsics.checkNotNullParameter(userName, "userName");
            Intrinsics.checkNotNullParameter(avatar, "avatar");
            Intrinsics.checkNotNullParameter(allCredit, "allCredit");
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            Intrinsics.checkNotNullParameter(zheCredit, "zheCredit");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(hunt, "hunt");
            return new User(userName, avatar, allCredit, welfare, zheCredit, desc, hunt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof User)) {
                return false;
            }
            User user = (User) other;
            return Intrinsics.areEqual(this.userName, user.userName) && Intrinsics.areEqual(this.avatar, user.avatar) && Intrinsics.areEqual(this.allCredit, user.allCredit) && Intrinsics.areEqual(this.welfare, user.welfare) && Intrinsics.areEqual(this.zheCredit, user.zheCredit) && Intrinsics.areEqual(this.desc, user.desc) && Intrinsics.areEqual(this.hunt, user.hunt);
        }

        public int hashCode() {
            return (((((((((((this.userName.hashCode() * 31) + this.avatar.hashCode()) * 31) + this.allCredit.hashCode()) * 31) + this.welfare.hashCode()) * 31) + this.zheCredit.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.hunt.hashCode();
        }

        public String toString() {
            return "User(userName=" + this.userName + ", avatar=" + this.avatar + ", allCredit=" + this.allCredit + ", welfare=" + this.welfare + ", zheCredit=" + this.zheCredit + ", desc=" + this.desc + ", hunt=" + this.hunt + ")";
        }

        public User(String userName, String avatar, String allCredit, String welfare, String zheCredit, String desc, String hunt) {
            Intrinsics.checkNotNullParameter(userName, "userName");
            Intrinsics.checkNotNullParameter(avatar, "avatar");
            Intrinsics.checkNotNullParameter(allCredit, "allCredit");
            Intrinsics.checkNotNullParameter(welfare, "welfare");
            Intrinsics.checkNotNullParameter(zheCredit, "zheCredit");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(hunt, "hunt");
            this.userName = userName;
            this.avatar = avatar;
            this.allCredit = allCredit;
            this.welfare = welfare;
            this.zheCredit = zheCredit;
            this.desc = desc;
            this.hunt = hunt;
        }

        public final String getUserName() {
            return this.userName;
        }

        public final String getAvatar() {
            return this.avatar;
        }

        public final String getAllCredit() {
            return this.allCredit;
        }

        public final String getWelfare() {
            return this.welfare;
        }

        public final String getZheCredit() {
            return this.zheCredit;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getHunt() {
            return this.hunt;
        }
    }
}
