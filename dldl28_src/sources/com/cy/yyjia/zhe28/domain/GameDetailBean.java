package com.cy.yyjia.zhe28.domain;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.lzy.okgo.model.Progress;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.open.SocialConstants;
import com.volcengine.common.contant.CommonConstants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0004\u0094\u0001\u0095\u0001B\u00ad\u0003\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0016R\u00020\u00000\u0015\u0012\u0012\u0010\u0017\u001a\u000e\u0012\b\u0012\u00060\u0018R\u00020\u0000\u0018\u00010\u0015\u0012\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015\u0012\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0015\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015\u0012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015\u0012\u0006\u0010 \u001a\u00020\t\u0012\u0006\u0010!\u001a\u00020\t\u0012\u0006\u0010\"\u001a\u00020\u0004\u0012\u0006\u0010#\u001a\u00020\u0004\u0012\u0006\u0010$\u001a\u00020\u0004\u0012\u0006\u0010%\u001a\u00020\u0004\u0012\u0006\u0010&\u001a\u00020\u0007\u0012\u0006\u0010'\u001a\u00020\t\u0012\u0006\u0010(\u001a\u00020\t\u0012\u0006\u0010)\u001a\u00020\t\u0012\u0006\u0010*\u001a\u00020\t\u0012\u0006\u0010+\u001a\u00020\t\u0012\u0006\u0010,\u001a\u00020\u0004\u0012\b\u0010-\u001a\u0004\u0018\u00010.\u0012\b\u0010/\u001a\u0004\u0018\u000100\u0012\u0006\u00101\u001a\u00020\u0004\u0012\u0006\u00102\u001a\u00020\u0004\u0012\u0006\u00103\u001a\u00020\u0004\u0012\u0006\u00104\u001a\u00020\u0004\u0012\u0006\u00105\u001a\u00020\u0004\u0012\u0006\u00106\u001a\u00020\u0004\u0012\u0006\u00107\u001a\u00020\u0004\u0012\f\u00108\u001a\b\u0012\u0004\u0012\u0002090\u0015\u0012\u0006\u0010:\u001a\u00020\t¢\u0006\u0002\u0010;J\u0007\u0010\u0087\u0001\u001a\u00020\u0007J\t\u0010\u0088\u0001\u001a\u00020\tH\u0007J\t\u0010\u0089\u0001\u001a\u00020\u0004H\u0007J\u0007\u0010\u008a\u0001\u001a\u00020\u0004J\u0007\u0010\u008b\u0001\u001a\u00020\u0004J\u0007\u0010\u008c\u0001\u001a\u00020\u0004J\u0011\u0010\u008d\u0001\u001a\f\u0012\b\u0012\u00060\u0016R\u00020\u00000\u0015J\u0007\u0010\u008e\u0001\u001a\u00020\u0004J\u0007\u0010\u008f\u0001\u001a\u00020\u0004J\u000e\u0010\u0090\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\u0015J\u0007\u0010\u0092\u0001\u001a\u00020\u0007J\u0007\u0010\u0093\u0001\u001a\u00020\u0007R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u001a\u00107\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010=\"\u0004\b?\u0010@R\u001a\u00106\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010=\"\u0004\bB\u0010@R \u00108\u001a\b\u0012\u0004\u0012\u0002090\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\bG\u0010DR\u0011\u0010*\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR&\u0010K\u001a\u00020\t2\u0006\u0010J\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010I\"\u0004\bM\u0010NR\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010=R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010=R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010=R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001c\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0011\u0010$\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010=R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b_\u0010DR\u0011\u0010\u0010\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010=R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\ba\u0010IR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bb\u0010IR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015¢\u0006\b\n\u0000\u001a\u0004\bc\u0010DR\u0011\u0010+\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010IR\u001a\u0010:\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010I\"\u0004\bd\u0010NR\u0011\u0010)\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010IR\u0011\u0010&\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010WR\u0011\u0010'\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010IR\u0011\u0010(\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010IR&\u0010e\u001a\u00020\t2\u0006\u0010J\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010I\"\u0004\bg\u0010NR\u0019\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\bh\u0010DR\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010=R*\u0010j\u001a\u0004\u0018\u00010k2\b\u0010j\u001a\u0004\u0018\u00010k8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u0011\u0010\u0012\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010=R\u0011\u0010,\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bq\u0010=R\u001a\u00104\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010=\"\u0004\bs\u0010@R\u001d\u0010\u0017\u001a\u000e\u0012\b\u0012\u00060\u0018R\u00020\u0000\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\bt\u0010DR\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010=R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010=R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\bw\u0010DR\u0011\u0010 \u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bx\u0010IR\u0011\u0010\"\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\by\u0010=R\u001a\u00101\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010=\"\u0004\b{\u0010@R\u0011\u0010!\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b|\u0010IR\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b}\u0010=R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b~\u0010DR\u001b\u00105\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010=\"\u0005\b\u0080\u0001\u0010@R\u0012\u0010\u0013\u001a\u00020\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010=R\u001c\u00102\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010=\"\u0005\b\u0083\u0001\u0010@R\u001c\u00103\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010=\"\u0005\b\u0085\u0001\u0010@R\u001c\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0016R\u00020\u00000\u0015¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010D¨\u0006\u0096\u0001"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameDetailBean;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "category_text", "", "", "gamePic", "favorite", "", "givnNum", "", "givnGiftId", SocialConstants.PARAM_COMMENT, "background", "shortDesc", "newsCount", "couponCount", "giftCount", "serviceTips", "screen_text", "video", "vipQy", "", "Lcom/cy/yyjia/zhe28/domain/GameDetailBean$VipBean;", "serviceList", "Lcom/cy/yyjia/zhe28/domain/GameDetailBean$ServiceBean;", "updatevers", "Lcom/cy/yyjia/zhe28/domain/GameHistoryBean;", "news", "Lcom/cy/yyjia/zhe28/domain/EventBean;", "similarGames", "im_logs", "Lcom/cy/yyjia/zhe28/domain/GameDetailChatBean;", "specialTopic", "tryPlay", "specialTopicImage", "tryPlayImage", "freeTips", "discountTips", "isGameCoupon", "isHour", "isTool", "isExtend", "commentCount", "isCloundTry", "serverName", "detailExtraBean", "Lcom/cy/yyjia/zhe28/domain/GameDetailExtraBean;", "freeLottery", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "teamId", "video_real", "video_real_bg", "server_img", "versLabel", "bbsId", "bbsBtn", "boss_tags", "Lcom/cy/yyjia/zhe28/domain/GameBean$Tag;", "isDown", "(Ljava/util/List;Ljava/util/List;ZIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIIIIILjava/lang/String;Lcom/cy/yyjia/zhe28/domain/GameDetailExtraBean;Lcom/cy/yyjia/zhe28/domain/BtnBean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;I)V", "getBackground", "()Ljava/lang/String;", "getBbsBtn", "setBbsBtn", "(Ljava/lang/String;)V", "getBbsId", "setBbsId", "getBoss_tags", "()Ljava/util/List;", "setBoss_tags", "(Ljava/util/List;)V", "getCategory_text", "getCommentCount", "()I", DBHelper.COL_VALUE, "commentNum", "getCommentNum", "setCommentNum", "(I)V", "getCouponCount", "getDescription", "getDetailExtraBean", "()Lcom/cy/yyjia/zhe28/domain/GameDetailExtraBean;", "setDetailExtraBean", "(Lcom/cy/yyjia/zhe28/domain/GameDetailExtraBean;)V", "getDiscountTips", "getFavorite", "()Z", "setFavorite", "(Z)V", "getFreeLottery", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "setFreeLottery", "(Lcom/cy/yyjia/zhe28/domain/BtnBean;)V", "getFreeTips", "getGamePic", "getGiftCount", "getGivnGiftId", "getGivnNum", "getIm_logs", "setDown", "isreservation", "getIsreservation", "setIsreservation", "getNews", "getNewsCount", NotificationCompat.CATEGORY_PROGRESS, "Lcom/lzy/okgo/model/Progress;", "getProgress", "()Lcom/lzy/okgo/model/Progress;", "setProgress", "(Lcom/lzy/okgo/model/Progress;)V", "getScreen_text", "getServerName", "getServer_img", "setServer_img", "getServiceList", "getServiceTips", "getShortDesc", "getSimilarGames", "getSpecialTopic", "getSpecialTopicImage", "getTeamId", "setTeamId", "getTryPlay", "getTryPlayImage", "getUpdatevers", "getVersLabel", "setVersLabel", "getVideo", "getVideo_real", "setVideo_real", "getVideo_real_bg", "setVideo_real_bg", "getVipQy", "canShowOrder", "getDownloadCurrentProgress", "getDownloadText", "getGameTypeText1", "getGameTypeText2", "getServiceTipsText", "getShortVipList", "getShowCommentCount", "getStartTimeText", "getVideoPicList", "Lcom/cy/yyjia/zhe28/domain/GameBannerBean;", "hidePlay", "hideYun", "ServiceBean", "VipBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameDetailBean extends GameBean {
    public static final int $stable = 8;
    private final String background;
    private String bbsBtn;
    private String bbsId;
    private List<GameBean.Tag> boss_tags;
    private final List<String> category_text;
    private final int commentCount;
    private int commentNum;
    private final String couponCount;
    private final String description;
    private GameDetailExtraBean detailExtraBean;
    private final String discountTips;

    @Bindable
    private boolean favorite;
    private BtnBean freeLottery;
    private final String freeTips;
    private final List<String> gamePic;
    private final String giftCount;
    private final int givnGiftId;
    private final int givnNum;
    private final List<GameDetailChatBean> im_logs;
    private final int isCloundTry;
    private int isDown;
    private final int isExtend;
    private final boolean isGameCoupon;
    private final int isHour;
    private final int isTool;
    private int isreservation;
    private final List<EventBean> news;
    private final String newsCount;
    private Progress progress;
    private final String screen_text;
    private final String serverName;
    private String server_img;
    private final List<ServiceBean> serviceList;
    private final String serviceTips;
    private final String shortDesc;
    private final List<GameBean> similarGames;
    private final int specialTopic;
    private final String specialTopicImage;
    private String teamId;
    private final int tryPlay;
    private final String tryPlayImage;
    private final List<GameHistoryBean> updatevers;
    private String versLabel;
    private final String video;
    private String video_real;
    private String video_real_bg;
    private final List<VipBean> vipQy;

    public final List<String> getCategory_text() {
        return this.category_text;
    }

    public final List<String> getGamePic() {
        return this.gamePic;
    }

    public final boolean getFavorite() {
        return this.favorite;
    }

    public final void setFavorite(boolean z) {
        this.favorite = z;
    }

    public final int getGivnNum() {
        return this.givnNum;
    }

    public final int getGivnGiftId() {
        return this.givnGiftId;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getBackground() {
        return this.background;
    }

    public final String getShortDesc() {
        return this.shortDesc;
    }

    public final String getNewsCount() {
        return this.newsCount;
    }

    public final String getCouponCount() {
        return this.couponCount;
    }

    public final String getGiftCount() {
        return this.giftCount;
    }

    public final String getServiceTips() {
        return this.serviceTips;
    }

    public final String getScreen_text() {
        return this.screen_text;
    }

    public final String getVideo() {
        return this.video;
    }

    public final List<VipBean> getVipQy() {
        return this.vipQy;
    }

    public final List<ServiceBean> getServiceList() {
        return this.serviceList;
    }

    public final List<GameHistoryBean> getUpdatevers() {
        return this.updatevers;
    }

    public final List<EventBean> getNews() {
        return this.news;
    }

    public final List<GameBean> getSimilarGames() {
        return this.similarGames;
    }

    public final List<GameDetailChatBean> getIm_logs() {
        return this.im_logs;
    }

    public final int getSpecialTopic() {
        return this.specialTopic;
    }

    public final int getTryPlay() {
        return this.tryPlay;
    }

    public final String getSpecialTopicImage() {
        return this.specialTopicImage;
    }

    public final String getTryPlayImage() {
        return this.tryPlayImage;
    }

    public final String getFreeTips() {
        return this.freeTips;
    }

    public final String getDiscountTips() {
        return this.discountTips;
    }

    /* JADX INFO: renamed from: isGameCoupon, reason: from getter */
    public final boolean getIsGameCoupon() {
        return this.isGameCoupon;
    }

    /* JADX INFO: renamed from: isHour, reason: from getter */
    public final int getIsHour() {
        return this.isHour;
    }

    /* JADX INFO: renamed from: isTool, reason: from getter */
    public final int getIsTool() {
        return this.isTool;
    }

    /* JADX INFO: renamed from: isExtend, reason: from getter */
    public final int getIsExtend() {
        return this.isExtend;
    }

    public final int getCommentCount() {
        return this.commentCount;
    }

    /* JADX INFO: renamed from: isCloundTry, reason: from getter */
    public final int getIsCloundTry() {
        return this.isCloundTry;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final GameDetailExtraBean getDetailExtraBean() {
        return this.detailExtraBean;
    }

    public final void setDetailExtraBean(GameDetailExtraBean gameDetailExtraBean) {
        this.detailExtraBean = gameDetailExtraBean;
    }

    public final BtnBean getFreeLottery() {
        return this.freeLottery;
    }

    public final void setFreeLottery(BtnBean btnBean) {
        this.freeLottery = btnBean;
    }

    public final String getTeamId() {
        return this.teamId;
    }

    public final void setTeamId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.teamId = str;
    }

    public final String getVideo_real() {
        return this.video_real;
    }

    public final void setVideo_real(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.video_real = str;
    }

    public final String getVideo_real_bg() {
        return this.video_real_bg;
    }

    public final void setVideo_real_bg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.video_real_bg = str;
    }

    public final String getServer_img() {
        return this.server_img;
    }

    public final void setServer_img(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.server_img = str;
    }

    public final String getVersLabel() {
        return this.versLabel;
    }

    public final void setVersLabel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.versLabel = str;
    }

    public final String getBbsId() {
        return this.bbsId;
    }

    public final void setBbsId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bbsId = str;
    }

    public final String getBbsBtn() {
        return this.bbsBtn;
    }

    public final void setBbsBtn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bbsBtn = str;
    }

    public final List<GameBean.Tag> getBoss_tags() {
        return this.boss_tags;
    }

    public final void setBoss_tags(List<GameBean.Tag> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.boss_tags = list;
    }

    /* JADX INFO: renamed from: isDown, reason: from getter */
    public final int getIsDown() {
        return this.isDown;
    }

    public final void setDown(int i) {
        this.isDown = i;
    }

    public GameDetailBean(List<String> category_text, List<String> gamePic, boolean z, int i, int i2, String description, String background, String shortDesc, String newsCount, String couponCount, String giftCount, String serviceTips, String screen_text, String video, List<VipBean> vipQy, List<ServiceBean> list, List<GameHistoryBean> list2, List<EventBean> list3, List<GameBean> similarGames, List<GameDetailChatBean> im_logs, int i3, int i4, String specialTopicImage, String tryPlayImage, String freeTips, String discountTips, boolean z2, int i5, int i6, int i7, int i8, int i9, String serverName, GameDetailExtraBean gameDetailExtraBean, BtnBean btnBean, String teamId, String video_real, String video_real_bg, String server_img, String versLabel, String bbsId, String bbsBtn, List<GameBean.Tag> boss_tags, int i10) {
        Intrinsics.checkNotNullParameter(category_text, "category_text");
        Intrinsics.checkNotNullParameter(gamePic, "gamePic");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(background, "background");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(newsCount, "newsCount");
        Intrinsics.checkNotNullParameter(couponCount, "couponCount");
        Intrinsics.checkNotNullParameter(giftCount, "giftCount");
        Intrinsics.checkNotNullParameter(serviceTips, "serviceTips");
        Intrinsics.checkNotNullParameter(screen_text, "screen_text");
        Intrinsics.checkNotNullParameter(video, "video");
        Intrinsics.checkNotNullParameter(vipQy, "vipQy");
        Intrinsics.checkNotNullParameter(similarGames, "similarGames");
        Intrinsics.checkNotNullParameter(im_logs, "im_logs");
        Intrinsics.checkNotNullParameter(specialTopicImage, "specialTopicImage");
        Intrinsics.checkNotNullParameter(tryPlayImage, "tryPlayImage");
        Intrinsics.checkNotNullParameter(freeTips, "freeTips");
        Intrinsics.checkNotNullParameter(discountTips, "discountTips");
        Intrinsics.checkNotNullParameter(serverName, "serverName");
        Intrinsics.checkNotNullParameter(teamId, "teamId");
        Intrinsics.checkNotNullParameter(video_real, "video_real");
        Intrinsics.checkNotNullParameter(video_real_bg, "video_real_bg");
        Intrinsics.checkNotNullParameter(server_img, "server_img");
        Intrinsics.checkNotNullParameter(versLabel, "versLabel");
        Intrinsics.checkNotNullParameter(bbsId, "bbsId");
        Intrinsics.checkNotNullParameter(bbsBtn, "bbsBtn");
        Intrinsics.checkNotNullParameter(boss_tags, "boss_tags");
        this.category_text = category_text;
        this.gamePic = gamePic;
        this.favorite = z;
        this.givnNum = i;
        this.givnGiftId = i2;
        this.description = description;
        this.background = background;
        this.shortDesc = shortDesc;
        this.newsCount = newsCount;
        this.couponCount = couponCount;
        this.giftCount = giftCount;
        this.serviceTips = serviceTips;
        this.screen_text = screen_text;
        this.video = video;
        this.vipQy = vipQy;
        this.serviceList = list;
        this.updatevers = list2;
        this.news = list3;
        this.similarGames = similarGames;
        this.im_logs = im_logs;
        this.specialTopic = i3;
        this.tryPlay = i4;
        this.specialTopicImage = specialTopicImage;
        this.tryPlayImage = tryPlayImage;
        this.freeTips = freeTips;
        this.discountTips = discountTips;
        this.isGameCoupon = z2;
        this.isHour = i5;
        this.isTool = i6;
        this.isExtend = i7;
        this.commentCount = i8;
        this.isCloundTry = i9;
        this.serverName = serverName;
        this.detailExtraBean = gameDetailExtraBean;
        this.freeLottery = btnBean;
        this.teamId = teamId;
        this.video_real = video_real;
        this.video_real_bg = video_real_bg;
        this.server_img = server_img;
        this.versLabel = versLabel;
        this.bbsId = bbsId;
        this.bbsBtn = bbsBtn;
        this.boss_tags = boss_tags;
        this.isDown = i10;
    }

    @Bindable
    public final Progress getProgress() {
        return this.progress;
    }

    public final void setProgress(Progress progress) {
        this.progress = progress;
        notifyPropertyChanged(79);
    }

    public final String getServiceTipsText() {
        if (Intrinsics.areEqual(getServiceType(), "1")) {
            List<ServiceBean> list = this.serviceList;
            Integer numValueOf = list != null ? Integer.valueOf(list.size()) : null;
            Intrinsics.checkNotNull(numValueOf);
            if (numValueOf.intValue() > 0) {
                return this.serviceList.get(0).getStart_day_text() + this.serviceList.get(0).getStart_time_text();
            }
        }
        if (Intrinsics.areEqual(getServiceType(), "2")) {
            return String.valueOf(this.serviceTips);
        }
        return "暂时没有新服";
    }

    public final List<VipBean> getShortVipList() {
        if (this.vipQy.size() > 4) {
            return this.vipQy.subList(0, 4);
        }
        return this.vipQy;
    }

    @Bindable
    public final int getDownloadCurrentProgress() {
        Progress progress = this.progress;
        if (progress == null) {
            return 100;
        }
        Intrinsics.checkNotNull(progress);
        return (int) (progress.fraction * 100);
    }

    @Bindable
    public final String getDownloadText() {
        Progress progress = this.progress;
        if (progress != null) {
            Intrinsics.checkNotNull(progress);
            int i = progress.status;
            if (i != 0) {
                if (i == 1) {
                    return "等待中";
                }
                if (i == 2) {
                    Progress progress2 = this.progress;
                    Intrinsics.checkNotNull(progress2);
                    return (progress2.fraction * 100) + "%";
                }
                if (i != 3) {
                    if (i == 4) {
                        return "下载出错";
                    }
                    if (i == 5) {
                        Progress progress3 = this.progress;
                        Intrinsics.checkNotNull(progress3);
                        String str = progress3.folder;
                        Progress progress4 = this.progress;
                        Intrinsics.checkNotNull(progress4);
                        if (new File(str, progress4.fileName).exists()) {
                            return "安装";
                        }
                        AppInfo appInfo = getAppInfo();
                        return "下载（" + (appInfo != null ? appInfo.getSize() : null) + "MB）";
                    }
                    if (getAppInfo() != null) {
                        return "下载（" + getAppInfo().getSize() + "MB）";
                    }
                    return "下载";
                }
            }
            return "继续下载";
        }
        AppInfo appInfo2 = getAppInfo();
        return "下载（" + (appInfo2 != null ? appInfo2.getSize() : null) + "MB）";
    }

    @Bindable
    public final int getIsreservation() {
        return this.isreservation;
    }

    public final void setIsreservation(int i) {
        this.isreservation = i;
        notifyPropertyChanged(48);
    }

    @Bindable
    public final int getCommentNum() {
        return this.commentNum;
    }

    public final void setCommentNum(int i) {
        this.commentNum = i;
        notifyPropertyChanged(15);
    }

    public final List<GameBannerBean> getVideoPicList() {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(this.video_real)) {
            GameBannerBean gameBannerBean = new GameBannerBean(this.video_real, this.video_real_bg);
            gameBannerBean.setHorizontal(!Intrinsics.areEqual(getScreen(), "vertical"));
            arrayList.add(gameBannerBean);
        }
        if (!TextUtils.isEmpty(this.video)) {
            arrayList.add(new GameBannerBean(this.video, this.background));
        }
        Iterator<String> it = this.gamePic.iterator();
        while (it.hasNext()) {
            GameBannerBean gameBannerBean2 = new GameBannerBean("", it.next());
            Integer gamePicScreen = getGamePicScreen();
            gameBannerBean2.setHorizontal(gamePicScreen != null && gamePicScreen.intValue() == 0);
            arrayList.add(gameBannerBean2);
        }
        return arrayList;
    }

    public final boolean canShowOrder() {
        return Intrinsics.areEqual(getStatus(), "apply");
    }

    public final boolean hidePlay() {
        return Intrinsics.areEqual(getType(), "gameapp") || !hideYun();
    }

    public final boolean hideYun() {
        return this.isCloundTry == 0;
    }

    public final String getGameTypeText1() {
        if (!this.category_text.isEmpty()) {
            return this.category_text.get(0);
        }
        return "";
    }

    public final String getGameTypeText2() {
        if (this.category_text.size() > 1) {
            return this.category_text.get(1);
        }
        return "";
    }

    public final String getStartTimeText() {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm首发").format(new Date(Long.parseLong(getStartTime() + "000"))));
    }

    public final String getShowCommentCount() {
        int i = this.commentCount;
        return i > 999 ? "999+" : i == 0 ? "" : String.valueOf(i);
    }

    /* JADX INFO: compiled from: GameDetailBean.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameDetailBean$VipBean;", "", "vip", "", "qy", "(Lcom/cy/yyjia/zhe28/domain/GameDetailBean;Ljava/lang/String;Ljava/lang/String;)V", "getQy", "()Ljava/lang/String;", "getVip", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class VipBean {
        private final String qy;
        final /* synthetic */ GameDetailBean this$0;
        private final String vip;

        public VipBean(GameDetailBean gameDetailBean, String vip, String qy) {
            Intrinsics.checkNotNullParameter(vip, "vip");
            Intrinsics.checkNotNullParameter(qy, "qy");
            this.this$0 = gameDetailBean;
            this.vip = vip;
            this.qy = qy;
        }

        public final String getVip() {
            return this.vip;
        }

        public final String getQy() {
            return this.qy;
        }
    }

    /* JADX INFO: compiled from: GameDetailBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0086\u0004\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameDetailBean$ServiceBean;", "", "dateline", "", CommonConstants.key_gameId, "", "id", "platform_id", "recommendType", "", "server", "serviceCode", Constant.START_TIME, "start_day_text", "start_time_text", "status", "(Lcom/cy/yyjia/zhe28/domain/GameDetailBean;JIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDateline", "()J", "getGameId", "()I", "getId", "getPlatform_id", "getRecommendType", "()Ljava/lang/String;", "getServer", "getServiceCode", "getStartTime", "getStart_day_text", "getStart_time_text", "getStatus", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ServiceBean {
        private final long dateline;
        private final int gameId;
        private final int id;
        private final int platform_id;
        private final String recommendType;
        private final String server;
        private final String serviceCode;
        private final long startTime;
        private final String start_day_text;
        private final String start_time_text;
        private final String status;
        final /* synthetic */ GameDetailBean this$0;

        public ServiceBean(GameDetailBean gameDetailBean, long j, int i, int i2, int i3, String recommendType, String server, String serviceCode, long j2, String start_day_text, String start_time_text, String status) {
            Intrinsics.checkNotNullParameter(recommendType, "recommendType");
            Intrinsics.checkNotNullParameter(server, "server");
            Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
            Intrinsics.checkNotNullParameter(start_day_text, "start_day_text");
            Intrinsics.checkNotNullParameter(start_time_text, "start_time_text");
            Intrinsics.checkNotNullParameter(status, "status");
            this.this$0 = gameDetailBean;
            this.dateline = j;
            this.gameId = i;
            this.id = i2;
            this.platform_id = i3;
            this.recommendType = recommendType;
            this.server = server;
            this.serviceCode = serviceCode;
            this.startTime = j2;
            this.start_day_text = start_day_text;
            this.start_time_text = start_time_text;
            this.status = status;
        }

        public final long getDateline() {
            return this.dateline;
        }

        public final int getGameId() {
            return this.gameId;
        }

        public final int getId() {
            return this.id;
        }

        public final int getPlatform_id() {
            return this.platform_id;
        }

        public final String getRecommendType() {
            return this.recommendType;
        }

        public final String getServer() {
            return this.server;
        }

        public final String getServiceCode() {
            return this.serviceCode;
        }

        public final long getStartTime() {
            return this.startTime;
        }

        public final String getStart_day_text() {
            return this.start_day_text;
        }

        public final String getStart_time_text() {
            return this.start_time_text;
        }

        public final String getStatus() {
            return this.status;
        }
    }
}
