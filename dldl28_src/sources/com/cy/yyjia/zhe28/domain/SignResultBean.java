package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SignResultBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u0015\u001a\u00020\u0003R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SignResultBean;", "", "msg", "", "rewardTip", "linkBtn", "rewardsBg", "rewards", "", "link", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/BtnBean;)V", "getLink", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "getLinkBtn", "()Ljava/lang/String;", "getMsg", "getRewardTip", "getRewards", "()Ljava/util/List;", "getRewardsBg", "getText", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SignResultBean {
    public static final int $stable = 8;
    private final BtnBean link;
    private final String linkBtn;
    private final String msg;
    private final String rewardTip;
    private final List<String> rewards;
    private final String rewardsBg;

    public SignResultBean(String msg, String rewardTip, String linkBtn, String rewardsBg, List<String> rewards, BtnBean link) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(rewardTip, "rewardTip");
        Intrinsics.checkNotNullParameter(linkBtn, "linkBtn");
        Intrinsics.checkNotNullParameter(rewardsBg, "rewardsBg");
        Intrinsics.checkNotNullParameter(rewards, "rewards");
        Intrinsics.checkNotNullParameter(link, "link");
        this.msg = msg;
        this.rewardTip = rewardTip;
        this.linkBtn = linkBtn;
        this.rewardsBg = rewardsBg;
        this.rewards = rewards;
        this.link = link;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final String getRewardTip() {
        return this.rewardTip;
    }

    public final String getLinkBtn() {
        return this.linkBtn;
    }

    public final String getRewardsBg() {
        return this.rewardsBg;
    }

    public final List<String> getRewards() {
        return this.rewards;
    }

    public final BtnBean getLink() {
        return this.link;
    }

    public final String getText() {
        String str = "";
        for (String str2 : this.rewards) {
            str = str.length() == 0 ? str2 : str + StringUtils.LF + str2;
        }
        return str;
    }
}
