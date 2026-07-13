package com.cy.yyjia.zhe28.domain;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteInfoBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001:\u0005CDEFGB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015¢\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u000bHÆ\u0003J\t\u00107\u001a\u00020\rHÆ\u0003J\t\u00108\u001a\u00020\u000fHÆ\u0003J\u009b\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015HÆ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020>J\t\u0010@\u001a\u00020AHÖ\u0001J\t\u0010B\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018¨\u0006H"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteInfoBean;", "", "bottomBg", "", "invite_info", "Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$InviteInfo;", "invite_qrcode", "invite_qrcode_img", "invite_url", "new_user_url", "payList", "Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$PayList;", "reward", "Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Reward;", "reward_info", "Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$RewardInfo;", "share_ads", "share_desc", "share_icon", "share_title", "labelArr", "", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$InviteInfo;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$PayList;Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Reward;Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$RewardInfo;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getBottomBg", "()Ljava/lang/String;", "getInvite_info", "()Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$InviteInfo;", "getInvite_qrcode", "getInvite_qrcode_img", "getInvite_url", "getLabelArr", "()Ljava/util/List;", "getNew_user_url", "getPayList", "()Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$PayList;", "getReward", "()Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Reward;", "getReward_info", "()Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$RewardInfo;", "getShare_ads", "getShare_desc", "getShare_icon", "getShare_title", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getLabel1", "Landroid/text/SpannableString;", "getLabel2", "hashCode", "", "toString", "InviteInfo", "PayList", "Platform", "Reward", "RewardInfo", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class InviteInfoBean {
    public static final int $stable = 8;
    private final String bottomBg;
    private final InviteInfo invite_info;
    private final String invite_qrcode;
    private final String invite_qrcode_img;
    private final String invite_url;
    private final List<String> labelArr;
    private final String new_user_url;
    private final PayList payList;
    private final Reward reward;
    private final RewardInfo reward_info;
    private final String share_ads;
    private final String share_desc;
    private final String share_icon;
    private final String share_title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getBottomBg() {
        return this.bottomBg;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getShare_ads() {
        return this.share_ads;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getShare_desc() {
        return this.share_desc;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getShare_icon() {
        return this.share_icon;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getShare_title() {
        return this.share_title;
    }

    public final List<String> component14() {
        return this.labelArr;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final InviteInfo getInvite_info() {
        return this.invite_info;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getInvite_qrcode() {
        return this.invite_qrcode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getInvite_qrcode_img() {
        return this.invite_qrcode_img;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getInvite_url() {
        return this.invite_url;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getNew_user_url() {
        return this.new_user_url;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final PayList getPayList() {
        return this.payList;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Reward getReward() {
        return this.reward;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final RewardInfo getReward_info() {
        return this.reward_info;
    }

    public final InviteInfoBean copy(String bottomBg, InviteInfo invite_info, String invite_qrcode, String invite_qrcode_img, String invite_url, String new_user_url, PayList payList, Reward reward, RewardInfo reward_info, String share_ads, String share_desc, String share_icon, String share_title, List<String> labelArr) {
        Intrinsics.checkNotNullParameter(bottomBg, "bottomBg");
        Intrinsics.checkNotNullParameter(invite_info, "invite_info");
        Intrinsics.checkNotNullParameter(invite_qrcode, "invite_qrcode");
        Intrinsics.checkNotNullParameter(invite_qrcode_img, "invite_qrcode_img");
        Intrinsics.checkNotNullParameter(invite_url, "invite_url");
        Intrinsics.checkNotNullParameter(new_user_url, "new_user_url");
        Intrinsics.checkNotNullParameter(payList, "payList");
        Intrinsics.checkNotNullParameter(reward, "reward");
        Intrinsics.checkNotNullParameter(reward_info, "reward_info");
        Intrinsics.checkNotNullParameter(share_ads, "share_ads");
        Intrinsics.checkNotNullParameter(share_desc, "share_desc");
        Intrinsics.checkNotNullParameter(share_icon, "share_icon");
        Intrinsics.checkNotNullParameter(share_title, "share_title");
        Intrinsics.checkNotNullParameter(labelArr, "labelArr");
        return new InviteInfoBean(bottomBg, invite_info, invite_qrcode, invite_qrcode_img, invite_url, new_user_url, payList, reward, reward_info, share_ads, share_desc, share_icon, share_title, labelArr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InviteInfoBean)) {
            return false;
        }
        InviteInfoBean inviteInfoBean = (InviteInfoBean) other;
        return Intrinsics.areEqual(this.bottomBg, inviteInfoBean.bottomBg) && Intrinsics.areEqual(this.invite_info, inviteInfoBean.invite_info) && Intrinsics.areEqual(this.invite_qrcode, inviteInfoBean.invite_qrcode) && Intrinsics.areEqual(this.invite_qrcode_img, inviteInfoBean.invite_qrcode_img) && Intrinsics.areEqual(this.invite_url, inviteInfoBean.invite_url) && Intrinsics.areEqual(this.new_user_url, inviteInfoBean.new_user_url) && Intrinsics.areEqual(this.payList, inviteInfoBean.payList) && Intrinsics.areEqual(this.reward, inviteInfoBean.reward) && Intrinsics.areEqual(this.reward_info, inviteInfoBean.reward_info) && Intrinsics.areEqual(this.share_ads, inviteInfoBean.share_ads) && Intrinsics.areEqual(this.share_desc, inviteInfoBean.share_desc) && Intrinsics.areEqual(this.share_icon, inviteInfoBean.share_icon) && Intrinsics.areEqual(this.share_title, inviteInfoBean.share_title) && Intrinsics.areEqual(this.labelArr, inviteInfoBean.labelArr);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.bottomBg.hashCode() * 31) + this.invite_info.hashCode()) * 31) + this.invite_qrcode.hashCode()) * 31) + this.invite_qrcode_img.hashCode()) * 31) + this.invite_url.hashCode()) * 31) + this.new_user_url.hashCode()) * 31) + this.payList.hashCode()) * 31) + this.reward.hashCode()) * 31) + this.reward_info.hashCode()) * 31) + this.share_ads.hashCode()) * 31) + this.share_desc.hashCode()) * 31) + this.share_icon.hashCode()) * 31) + this.share_title.hashCode()) * 31) + this.labelArr.hashCode();
    }

    public String toString() {
        return "InviteInfoBean(bottomBg=" + this.bottomBg + ", invite_info=" + this.invite_info + ", invite_qrcode=" + this.invite_qrcode + ", invite_qrcode_img=" + this.invite_qrcode_img + ", invite_url=" + this.invite_url + ", new_user_url=" + this.new_user_url + ", payList=" + this.payList + ", reward=" + this.reward + ", reward_info=" + this.reward_info + ", share_ads=" + this.share_ads + ", share_desc=" + this.share_desc + ", share_icon=" + this.share_icon + ", share_title=" + this.share_title + ", labelArr=" + this.labelArr + ")";
    }

    public InviteInfoBean(String bottomBg, InviteInfo invite_info, String invite_qrcode, String invite_qrcode_img, String invite_url, String new_user_url, PayList payList, Reward reward, RewardInfo reward_info, String share_ads, String share_desc, String share_icon, String share_title, List<String> labelArr) {
        Intrinsics.checkNotNullParameter(bottomBg, "bottomBg");
        Intrinsics.checkNotNullParameter(invite_info, "invite_info");
        Intrinsics.checkNotNullParameter(invite_qrcode, "invite_qrcode");
        Intrinsics.checkNotNullParameter(invite_qrcode_img, "invite_qrcode_img");
        Intrinsics.checkNotNullParameter(invite_url, "invite_url");
        Intrinsics.checkNotNullParameter(new_user_url, "new_user_url");
        Intrinsics.checkNotNullParameter(payList, "payList");
        Intrinsics.checkNotNullParameter(reward, "reward");
        Intrinsics.checkNotNullParameter(reward_info, "reward_info");
        Intrinsics.checkNotNullParameter(share_ads, "share_ads");
        Intrinsics.checkNotNullParameter(share_desc, "share_desc");
        Intrinsics.checkNotNullParameter(share_icon, "share_icon");
        Intrinsics.checkNotNullParameter(share_title, "share_title");
        Intrinsics.checkNotNullParameter(labelArr, "labelArr");
        this.bottomBg = bottomBg;
        this.invite_info = invite_info;
        this.invite_qrcode = invite_qrcode;
        this.invite_qrcode_img = invite_qrcode_img;
        this.invite_url = invite_url;
        this.new_user_url = new_user_url;
        this.payList = payList;
        this.reward = reward;
        this.reward_info = reward_info;
        this.share_ads = share_ads;
        this.share_desc = share_desc;
        this.share_icon = share_icon;
        this.share_title = share_title;
        this.labelArr = labelArr;
    }

    public final String getBottomBg() {
        return this.bottomBg;
    }

    public final InviteInfo getInvite_info() {
        return this.invite_info;
    }

    public final String getInvite_qrcode() {
        return this.invite_qrcode;
    }

    public final String getInvite_qrcode_img() {
        return this.invite_qrcode_img;
    }

    public final String getInvite_url() {
        return this.invite_url;
    }

    public final String getNew_user_url() {
        return this.new_user_url;
    }

    public final PayList getPayList() {
        return this.payList;
    }

    public final Reward getReward() {
        return this.reward;
    }

    public final RewardInfo getReward_info() {
        return this.reward_info;
    }

    public final String getShare_ads() {
        return this.share_ads;
    }

    public final String getShare_desc() {
        return this.share_desc;
    }

    public final String getShare_icon() {
        return this.share_icon;
    }

    public final String getShare_title() {
        return this.share_title;
    }

    public final List<String> getLabelArr() {
        return this.labelArr;
    }

    public final SpannableString getLabel1() {
        SpannableString spannableString = new SpannableString("·" + ((Object) this.labelArr.get(0)) + ((Object) this.labelArr.get(1)) + ((Object) this.labelArr.get(2)) + ((Object) this.labelArr.get(3)));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FC4E6F")), this.labelArr.get(0).length() + 1, this.labelArr.get(0).length() + this.labelArr.get(1).length() + 1, 33);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FC4E6F")), spannableString.length() - this.labelArr.get(3).length(), spannableString.length(), 33);
        return spannableString;
    }

    public final SpannableString getLabel2() {
        SpannableString spannableString = new SpannableString("·" + ((Object) this.labelArr.get(4)) + ((Object) this.labelArr.get(5)) + ((Object) this.labelArr.get(6)) + ((Object) this.labelArr.get(7)));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FC4E6F")), this.labelArr.get(4).length() + 1, this.labelArr.get(4).length() + this.labelArr.get(5).length() + 1, 33);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FC4E6F")), spannableString.length() - this.labelArr.get(7).length(), spannableString.length(), 33);
        return spannableString;
    }

    /* JADX INFO: compiled from: InviteInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$InviteInfo;", "", "invite_cash", "", "invite_order_reward", "invite_reward_sum", "invite_user_count", "invite_user_reward", "reward_money", "reward_profit", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInvite_cash", "()Ljava/lang/String;", "getInvite_order_reward", "getInvite_reward_sum", "getInvite_user_count", "getInvite_user_reward", "getReward_money", "getReward_profit", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class InviteInfo {
        public static final int $stable = 0;
        private final String invite_cash;
        private final String invite_order_reward;
        private final String invite_reward_sum;
        private final String invite_user_count;
        private final String invite_user_reward;
        private final String reward_money;
        private final String reward_profit;

        public static /* synthetic */ InviteInfo copy$default(InviteInfo inviteInfo, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = inviteInfo.invite_cash;
            }
            if ((i & 2) != 0) {
                str2 = inviteInfo.invite_order_reward;
            }
            String str8 = str2;
            if ((i & 4) != 0) {
                str3 = inviteInfo.invite_reward_sum;
            }
            String str9 = str3;
            if ((i & 8) != 0) {
                str4 = inviteInfo.invite_user_count;
            }
            String str10 = str4;
            if ((i & 16) != 0) {
                str5 = inviteInfo.invite_user_reward;
            }
            String str11 = str5;
            if ((i & 32) != 0) {
                str6 = inviteInfo.reward_money;
            }
            String str12 = str6;
            if ((i & 64) != 0) {
                str7 = inviteInfo.reward_profit;
            }
            return inviteInfo.copy(str, str8, str9, str10, str11, str12, str7);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getInvite_cash() {
            return this.invite_cash;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getInvite_order_reward() {
            return this.invite_order_reward;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getInvite_reward_sum() {
            return this.invite_reward_sum;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getInvite_user_count() {
            return this.invite_user_count;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getInvite_user_reward() {
            return this.invite_user_reward;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getReward_money() {
            return this.reward_money;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getReward_profit() {
            return this.reward_profit;
        }

        public final InviteInfo copy(String invite_cash, String invite_order_reward, String invite_reward_sum, String invite_user_count, String invite_user_reward, String reward_money, String reward_profit) {
            Intrinsics.checkNotNullParameter(invite_cash, "invite_cash");
            Intrinsics.checkNotNullParameter(invite_order_reward, "invite_order_reward");
            Intrinsics.checkNotNullParameter(invite_reward_sum, "invite_reward_sum");
            Intrinsics.checkNotNullParameter(invite_user_count, "invite_user_count");
            Intrinsics.checkNotNullParameter(invite_user_reward, "invite_user_reward");
            Intrinsics.checkNotNullParameter(reward_money, "reward_money");
            Intrinsics.checkNotNullParameter(reward_profit, "reward_profit");
            return new InviteInfo(invite_cash, invite_order_reward, invite_reward_sum, invite_user_count, invite_user_reward, reward_money, reward_profit);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InviteInfo)) {
                return false;
            }
            InviteInfo inviteInfo = (InviteInfo) other;
            return Intrinsics.areEqual(this.invite_cash, inviteInfo.invite_cash) && Intrinsics.areEqual(this.invite_order_reward, inviteInfo.invite_order_reward) && Intrinsics.areEqual(this.invite_reward_sum, inviteInfo.invite_reward_sum) && Intrinsics.areEqual(this.invite_user_count, inviteInfo.invite_user_count) && Intrinsics.areEqual(this.invite_user_reward, inviteInfo.invite_user_reward) && Intrinsics.areEqual(this.reward_money, inviteInfo.reward_money) && Intrinsics.areEqual(this.reward_profit, inviteInfo.reward_profit);
        }

        public int hashCode() {
            return (((((((((((this.invite_cash.hashCode() * 31) + this.invite_order_reward.hashCode()) * 31) + this.invite_reward_sum.hashCode()) * 31) + this.invite_user_count.hashCode()) * 31) + this.invite_user_reward.hashCode()) * 31) + this.reward_money.hashCode()) * 31) + this.reward_profit.hashCode();
        }

        public String toString() {
            return "InviteInfo(invite_cash=" + this.invite_cash + ", invite_order_reward=" + this.invite_order_reward + ", invite_reward_sum=" + this.invite_reward_sum + ", invite_user_count=" + this.invite_user_count + ", invite_user_reward=" + this.invite_user_reward + ", reward_money=" + this.reward_money + ", reward_profit=" + this.reward_profit + ")";
        }

        public InviteInfo(String invite_cash, String invite_order_reward, String invite_reward_sum, String invite_user_count, String invite_user_reward, String reward_money, String reward_profit) {
            Intrinsics.checkNotNullParameter(invite_cash, "invite_cash");
            Intrinsics.checkNotNullParameter(invite_order_reward, "invite_order_reward");
            Intrinsics.checkNotNullParameter(invite_reward_sum, "invite_reward_sum");
            Intrinsics.checkNotNullParameter(invite_user_count, "invite_user_count");
            Intrinsics.checkNotNullParameter(invite_user_reward, "invite_user_reward");
            Intrinsics.checkNotNullParameter(reward_money, "reward_money");
            Intrinsics.checkNotNullParameter(reward_profit, "reward_profit");
            this.invite_cash = invite_cash;
            this.invite_order_reward = invite_order_reward;
            this.invite_reward_sum = invite_reward_sum;
            this.invite_user_count = invite_user_count;
            this.invite_user_reward = invite_user_reward;
            this.reward_money = reward_money;
            this.reward_profit = reward_profit;
        }

        public final String getInvite_cash() {
            return this.invite_cash;
        }

        public final String getInvite_order_reward() {
            return this.invite_order_reward;
        }

        public final String getInvite_reward_sum() {
            return this.invite_reward_sum;
        }

        public final String getInvite_user_count() {
            return this.invite_user_count;
        }

        public final String getInvite_user_reward() {
            return this.invite_user_reward;
        }

        public final String getReward_money() {
            return this.reward_money;
        }

        public final String getReward_profit() {
            return this.reward_profit;
        }
    }

    /* JADX INFO: compiled from: InviteInfoBean.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013J\u0006\u0010\u0017\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\u0013J\u0006\u0010\u0019\u001a\u00020\u0013J\u0006\u0010\u001a\u001a\u00020\u0013J\u0006\u0010\u001b\u001a\u00020\u0013J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$PayList;", "", "welfare", "Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Platform;", "alipay", Constants.PARAM_PLATFORM, "(Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Platform;Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Platform;Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Platform;)V", "getAlipay", "()Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Platform;", "getPlatform", "getWelfare", "component1", "component2", "component3", "copy", "equals", "", "other", "getDesc1", "", "getDesc2", "getDesc3", "getIcon1", "getIcon2", "getIcon3", "getName1", "getName2", "getName3", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class PayList {
        public static final int $stable = 0;
        private final Platform alipay;
        private final Platform platform;
        private final Platform welfare;

        public static /* synthetic */ PayList copy$default(PayList payList, Platform platform, Platform platform2, Platform platform3, int i, Object obj) {
            if ((i & 1) != 0) {
                platform = payList.welfare;
            }
            if ((i & 2) != 0) {
                platform2 = payList.alipay;
            }
            if ((i & 4) != 0) {
                platform3 = payList.platform;
            }
            return payList.copy(platform, platform2, platform3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Platform getWelfare() {
            return this.welfare;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Platform getAlipay() {
            return this.alipay;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Platform getPlatform() {
            return this.platform;
        }

        public final PayList copy(Platform welfare, Platform alipay, Platform platform) {
            return new PayList(welfare, alipay, platform);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PayList)) {
                return false;
            }
            PayList payList = (PayList) other;
            return Intrinsics.areEqual(this.welfare, payList.welfare) && Intrinsics.areEqual(this.alipay, payList.alipay) && Intrinsics.areEqual(this.platform, payList.platform);
        }

        public int hashCode() {
            Platform platform = this.welfare;
            int iHashCode = (platform == null ? 0 : platform.hashCode()) * 31;
            Platform platform2 = this.alipay;
            int iHashCode2 = (iHashCode + (platform2 == null ? 0 : platform2.hashCode())) * 31;
            Platform platform3 = this.platform;
            return iHashCode2 + (platform3 != null ? platform3.hashCode() : 0);
        }

        public String toString() {
            return "PayList(welfare=" + this.welfare + ", alipay=" + this.alipay + ", platform=" + this.platform + ")";
        }

        public PayList(Platform platform, Platform platform2, Platform platform3) {
            this.welfare = platform;
            this.alipay = platform2;
            this.platform = platform3;
        }

        public final Platform getWelfare() {
            return this.welfare;
        }

        public final Platform getAlipay() {
            return this.alipay;
        }

        public final Platform getPlatform() {
            return this.platform;
        }

        public final String getName1() {
            Platform platform = this.platform;
            if (platform != null) {
                return platform.getName();
            }
            return "";
        }

        public final String getDesc1() {
            Platform platform = this.platform;
            if (platform != null) {
                return platform.getDesc();
            }
            return "";
        }

        public final String getIcon1() {
            Platform platform = this.platform;
            if (platform != null) {
                return platform.getIcon();
            }
            return "";
        }

        public final String getName2() {
            Platform platform = this.alipay;
            if (platform != null) {
                return platform.getName();
            }
            return "";
        }

        public final String getDesc2() {
            Platform platform = this.alipay;
            if (platform != null) {
                return platform.getDesc();
            }
            return "";
        }

        public final String getIcon2() {
            Platform platform = this.alipay;
            if (platform != null) {
                return platform.getIcon();
            }
            return "";
        }

        public final String getName3() {
            Platform platform = this.welfare;
            if (platform != null) {
                return platform.getName();
            }
            return "";
        }

        public final String getDesc3() {
            Platform platform = this.welfare;
            if (platform != null) {
                return platform.getDesc();
            }
            return "";
        }

        public final String getIcon3() {
            Platform platform = this.welfare;
            if (platform != null) {
                return platform.getIcon();
            }
            return "";
        }
    }

    /* JADX INFO: compiled from: InviteInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Reward;", "", "userreward", "", "common", "zhe", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCommon", "()Ljava/lang/String;", "getUserreward", "getZhe", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Reward {
        public static final int $stable = 0;
        private final String common;
        private final String userreward;
        private final String zhe;

        public static /* synthetic */ Reward copy$default(Reward reward, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = reward.userreward;
            }
            if ((i & 2) != 0) {
                str2 = reward.common;
            }
            if ((i & 4) != 0) {
                str3 = reward.zhe;
            }
            return reward.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUserreward() {
            return this.userreward;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCommon() {
            return this.common;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getZhe() {
            return this.zhe;
        }

        public final Reward copy(String userreward, String common, String zhe) {
            Intrinsics.checkNotNullParameter(userreward, "userreward");
            Intrinsics.checkNotNullParameter(common, "common");
            Intrinsics.checkNotNullParameter(zhe, "zhe");
            return new Reward(userreward, common, zhe);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Reward)) {
                return false;
            }
            Reward reward = (Reward) other;
            return Intrinsics.areEqual(this.userreward, reward.userreward) && Intrinsics.areEqual(this.common, reward.common) && Intrinsics.areEqual(this.zhe, reward.zhe);
        }

        public int hashCode() {
            return (((this.userreward.hashCode() * 31) + this.common.hashCode()) * 31) + this.zhe.hashCode();
        }

        public String toString() {
            return "Reward(userreward=" + this.userreward + ", common=" + this.common + ", zhe=" + this.zhe + ")";
        }

        public Reward(String userreward, String common, String zhe) {
            Intrinsics.checkNotNullParameter(userreward, "userreward");
            Intrinsics.checkNotNullParameter(common, "common");
            Intrinsics.checkNotNullParameter(zhe, "zhe");
            this.userreward = userreward;
            this.common = common;
            this.zhe = zhe;
        }

        public final String getUserreward() {
            return this.userreward;
        }

        public final String getCommon() {
            return this.common;
        }

        public final String getZhe() {
            return this.zhe;
        }
    }

    /* JADX INFO: compiled from: InviteInfoBean.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003Jo\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006)"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$RewardInfo;", "", "WithdrawalDesc", "", "activityCourse", "activityRules", "invitee_reward", "", "inviter_reward", "minamount", "rankTimeEnd", "rankTimeStart", "userreward", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getWithdrawalDesc", "()Ljava/lang/String;", "getActivityCourse", "getActivityRules", "getInvitee_reward", "()Ljava/util/List;", "getInviter_reward", "getMinamount", "getRankTimeEnd", "getRankTimeStart", "getUserreward", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class RewardInfo {
        public static final int $stable = 8;
        private final String WithdrawalDesc;
        private final String activityCourse;
        private final String activityRules;
        private final List<String> invitee_reward;
        private final List<String> inviter_reward;
        private final String minamount;
        private final String rankTimeEnd;
        private final String rankTimeStart;
        private final String userreward;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getWithdrawalDesc() {
            return this.WithdrawalDesc;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getActivityCourse() {
            return this.activityCourse;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getActivityRules() {
            return this.activityRules;
        }

        public final List<String> component4() {
            return this.invitee_reward;
        }

        public final List<String> component5() {
            return this.inviter_reward;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMinamount() {
            return this.minamount;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getRankTimeEnd() {
            return this.rankTimeEnd;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getRankTimeStart() {
            return this.rankTimeStart;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getUserreward() {
            return this.userreward;
        }

        public final RewardInfo copy(String WithdrawalDesc, String activityCourse, String activityRules, List<String> invitee_reward, List<String> inviter_reward, String minamount, String rankTimeEnd, String rankTimeStart, String userreward) {
            Intrinsics.checkNotNullParameter(WithdrawalDesc, "WithdrawalDesc");
            Intrinsics.checkNotNullParameter(activityCourse, "activityCourse");
            Intrinsics.checkNotNullParameter(activityRules, "activityRules");
            Intrinsics.checkNotNullParameter(invitee_reward, "invitee_reward");
            Intrinsics.checkNotNullParameter(inviter_reward, "inviter_reward");
            Intrinsics.checkNotNullParameter(minamount, "minamount");
            Intrinsics.checkNotNullParameter(rankTimeEnd, "rankTimeEnd");
            Intrinsics.checkNotNullParameter(rankTimeStart, "rankTimeStart");
            Intrinsics.checkNotNullParameter(userreward, "userreward");
            return new RewardInfo(WithdrawalDesc, activityCourse, activityRules, invitee_reward, inviter_reward, minamount, rankTimeEnd, rankTimeStart, userreward);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RewardInfo)) {
                return false;
            }
            RewardInfo rewardInfo = (RewardInfo) other;
            return Intrinsics.areEqual(this.WithdrawalDesc, rewardInfo.WithdrawalDesc) && Intrinsics.areEqual(this.activityCourse, rewardInfo.activityCourse) && Intrinsics.areEqual(this.activityRules, rewardInfo.activityRules) && Intrinsics.areEqual(this.invitee_reward, rewardInfo.invitee_reward) && Intrinsics.areEqual(this.inviter_reward, rewardInfo.inviter_reward) && Intrinsics.areEqual(this.minamount, rewardInfo.minamount) && Intrinsics.areEqual(this.rankTimeEnd, rewardInfo.rankTimeEnd) && Intrinsics.areEqual(this.rankTimeStart, rewardInfo.rankTimeStart) && Intrinsics.areEqual(this.userreward, rewardInfo.userreward);
        }

        public int hashCode() {
            return (((((((((((((((this.WithdrawalDesc.hashCode() * 31) + this.activityCourse.hashCode()) * 31) + this.activityRules.hashCode()) * 31) + this.invitee_reward.hashCode()) * 31) + this.inviter_reward.hashCode()) * 31) + this.minamount.hashCode()) * 31) + this.rankTimeEnd.hashCode()) * 31) + this.rankTimeStart.hashCode()) * 31) + this.userreward.hashCode();
        }

        public String toString() {
            return "RewardInfo(WithdrawalDesc=" + this.WithdrawalDesc + ", activityCourse=" + this.activityCourse + ", activityRules=" + this.activityRules + ", invitee_reward=" + this.invitee_reward + ", inviter_reward=" + this.inviter_reward + ", minamount=" + this.minamount + ", rankTimeEnd=" + this.rankTimeEnd + ", rankTimeStart=" + this.rankTimeStart + ", userreward=" + this.userreward + ")";
        }

        public RewardInfo(String WithdrawalDesc, String activityCourse, String activityRules, List<String> invitee_reward, List<String> inviter_reward, String minamount, String rankTimeEnd, String rankTimeStart, String userreward) {
            Intrinsics.checkNotNullParameter(WithdrawalDesc, "WithdrawalDesc");
            Intrinsics.checkNotNullParameter(activityCourse, "activityCourse");
            Intrinsics.checkNotNullParameter(activityRules, "activityRules");
            Intrinsics.checkNotNullParameter(invitee_reward, "invitee_reward");
            Intrinsics.checkNotNullParameter(inviter_reward, "inviter_reward");
            Intrinsics.checkNotNullParameter(minamount, "minamount");
            Intrinsics.checkNotNullParameter(rankTimeEnd, "rankTimeEnd");
            Intrinsics.checkNotNullParameter(rankTimeStart, "rankTimeStart");
            Intrinsics.checkNotNullParameter(userreward, "userreward");
            this.WithdrawalDesc = WithdrawalDesc;
            this.activityCourse = activityCourse;
            this.activityRules = activityRules;
            this.invitee_reward = invitee_reward;
            this.inviter_reward = inviter_reward;
            this.minamount = minamount;
            this.rankTimeEnd = rankTimeEnd;
            this.rankTimeStart = rankTimeStart;
            this.userreward = userreward;
        }

        public final String getWithdrawalDesc() {
            return this.WithdrawalDesc;
        }

        public final String getActivityCourse() {
            return this.activityCourse;
        }

        public final String getActivityRules() {
            return this.activityRules;
        }

        public final List<String> getInvitee_reward() {
            return this.invitee_reward;
        }

        public final List<String> getInviter_reward() {
            return this.inviter_reward;
        }

        public final String getMinamount() {
            return this.minamount;
        }

        public final String getRankTimeEnd() {
            return this.rankTimeEnd;
        }

        public final String getRankTimeStart() {
            return this.rankTimeStart;
        }

        public final String getUserreward() {
            return this.userreward;
        }
    }

    /* JADX INFO: compiled from: InviteInfoBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteInfoBean$Platform;", "", SocialConstants.PARAM_APP_DESC, "", "icon", "name", "type", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getDesc", "()Ljava/lang/String;", "getIcon", "getName", "getType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Platform {
        public static final int $stable = 0;
        private final String desc;
        private final String icon;
        private final String name;
        private final int type;

        public static /* synthetic */ Platform copy$default(Platform platform, String str, String str2, String str3, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = platform.desc;
            }
            if ((i2 & 2) != 0) {
                str2 = platform.icon;
            }
            if ((i2 & 4) != 0) {
                str3 = platform.name;
            }
            if ((i2 & 8) != 0) {
                i = platform.type;
            }
            return platform.copy(str, str2, str3, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getType() {
            return this.type;
        }

        public final Platform copy(String desc, String icon, String name, int type) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            return new Platform(desc, icon, name, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Platform)) {
                return false;
            }
            Platform platform = (Platform) other;
            return Intrinsics.areEqual(this.desc, platform.desc) && Intrinsics.areEqual(this.icon, platform.icon) && Intrinsics.areEqual(this.name, platform.name) && this.type == platform.type;
        }

        public int hashCode() {
            return (((((this.desc.hashCode() * 31) + this.icon.hashCode()) * 31) + this.name.hashCode()) * 31) + this.type;
        }

        public String toString() {
            return "Platform(desc=" + this.desc + ", icon=" + this.icon + ", name=" + this.name + ", type=" + this.type + ")";
        }

        public Platform(String desc, String icon, String name, int i) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            this.desc = desc;
            this.icon = icon;
            this.name = name;
            this.type = i;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final String getName() {
            return this.name;
        }

        public final int getType() {
            return this.type;
        }
    }
}
