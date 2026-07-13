package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SanbaoRuleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u0001:\u0005\u001c\u001d\u001e\u001f B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean;", "", SocialConstants.PARAM_APP_DESC, "", "topic", "Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic;", "hour", "Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Hour;", "welfare648", "Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Welfare648;", "welfareUsers", "", "Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Message;", "specialTopic", "Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic2;", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic;Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Hour;Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Welfare648;Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic2;)V", "getDesc", "()Ljava/lang/String;", "getHour", "()Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Hour;", "getSpecialTopic", "()Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic2;", "getTopic", "()Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic;", "getWelfare648", "()Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Welfare648;", "getWelfareUsers", "()Ljava/util/List;", "Hour", "Message", "Topic", "Topic2", "Welfare648", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SanbaoRuleBean {
    public static final int $stable = 8;
    private final String desc;
    private final Hour hour;
    private final Topic2 specialTopic;
    private final Topic topic;
    private final Welfare648 welfare648;
    private final List<Message> welfareUsers;

    public SanbaoRuleBean(String desc, Topic topic, Hour hour, Welfare648 welfare648, List<Message> welfareUsers, Topic2 specialTopic) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(hour, "hour");
        Intrinsics.checkNotNullParameter(welfare648, "welfare648");
        Intrinsics.checkNotNullParameter(welfareUsers, "welfareUsers");
        Intrinsics.checkNotNullParameter(specialTopic, "specialTopic");
        this.desc = desc;
        this.topic = topic;
        this.hour = hour;
        this.welfare648 = welfare648;
        this.welfareUsers = welfareUsers;
        this.specialTopic = specialTopic;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final Topic getTopic() {
        return this.topic;
    }

    public final Hour getHour() {
        return this.hour;
    }

    public final Welfare648 getWelfare648() {
        return this.welfare648;
    }

    public final List<Message> getWelfareUsers() {
        return this.welfareUsers;
    }

    public final Topic2 getSpecialTopic() {
        return this.specialTopic;
    }

    /* JADX INFO: compiled from: SanbaoRuleBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic;", "", "rule", "", "(Ljava/lang/String;)V", "getRule", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Topic {
        public static final int $stable = 0;
        private final String rule;

        public Topic(String rule) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            this.rule = rule;
        }

        public final String getRule() {
            return this.rule;
        }
    }

    /* JADX INFO: compiled from: SanbaoRuleBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Hour;", "", "rule", "", "(Ljava/lang/String;)V", "getRule", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Hour {
        public static final int $stable = 0;
        private final String rule;

        public Hour(String rule) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            this.rule = rule;
        }

        public final String getRule() {
            return this.rule;
        }
    }

    /* JADX INFO: compiled from: SanbaoRuleBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Welfare648;", "", "rule", "", "(Ljava/lang/String;)V", "getRule", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Welfare648 {
        public static final int $stable = 0;
        private final String rule;

        public Welfare648(String rule) {
            Intrinsics.checkNotNullParameter(rule, "rule");
            this.rule = rule;
        }

        public final String getRule() {
            return this.rule;
        }
    }

    /* JADX INFO: compiled from: SanbaoRuleBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Topic2;", "", "title", "", "adpic", "(Ljava/lang/String;Ljava/lang/String;)V", "getAdpic", "()Ljava/lang/String;", "getTitle", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Topic2 {
        public static final int $stable = 0;
        private final String adpic;
        private final String title;

        public Topic2(String title, String adpic) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(adpic, "adpic");
            this.title = title;
            this.adpic = adpic;
        }

        public final String getAdpic() {
            return this.adpic;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    /* JADX INFO: compiled from: SanbaoRuleBean.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoRuleBean$Message;", "", "uid", "", "name", "", "time", "nickName", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getNickName", "getTime", "getUid", "()I", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Message {
        public static final int $stable = 0;
        private final String name;
        private final String nickName;
        private final String time;
        private final int uid;

        public Message(int i, String name, String time, String nickName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(time, "time");
            Intrinsics.checkNotNullParameter(nickName, "nickName");
            this.uid = i;
            this.name = name;
            this.time = time;
            this.nickName = nickName;
        }

        public final int getUid() {
            return this.uid;
        }

        public final String getName() {
            return this.name;
        }

        public final String getTime() {
            return this.time;
        }

        public final String getNickName() {
            return this.nickName;
        }
    }
}
