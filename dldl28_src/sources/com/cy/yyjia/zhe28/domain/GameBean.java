package com.cy.yyjia.zhe28.domain;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Util;
import com.google.gson.annotations.SerializedName;
import com.mobile.auth.gatewayauth.Constant;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: GameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002:\u0002 \u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006Bñ\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\b\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\b\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\u0005\u0012\u0006\u0010\u001c\u001a\u00020\u0005\u0012\u0006\u0010\u001d\u001a\u00020\u0005\u0012\u0006\u0010\u001e\u001a\u00020\b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0012\u0012\u0006\u0010#\u001a\u00020\u0005\u0012\u0006\u0010$\u001a\u00020\u0005\u0012\u0006\u0010%\u001a\u00020\u0005\u0012\u0006\u0010&\u001a\u00020\u0005\u0012\u0006\u0010'\u001a\u00020\u0005\u0012\u0006\u0010(\u001a\u00020\u0005\u0012\u0006\u0010)\u001a\u00020\u0005\u0012\u0006\u0010*\u001a\u00020\u0005\u0012\u0006\u0010+\u001a\u00020\u0005\u0012\u0006\u0010,\u001a\u00020\u0005\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u000103\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010>J\u0007\u0010\u0088\u0001\u001a\u00020]J\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u0007\u0010\u008b\u0001\u001a\u00020\u0005J\b\u0010\u008c\u0001\u001a\u00030\u008a\u0001J\u0007\u0010\u008d\u0001\u001a\u00020\u0005J\b\u0010\u008e\u0001\u001a\u00030\u008a\u0001J\b\u0010\u008f\u0001\u001a\u00030\u008a\u0001J\u0007\u0010\u0090\u0001\u001a\u00020\u0005J\u0007\u0010\u0091\u0001\u001a\u00020\u0005J\u000f\u0010\u0092\u0001\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\bJ\u0007\u0010\u0093\u0001\u001a\u00020\u0005J\u0007\u0010\u0094\u0001\u001a\u00020\u0005J\u0007\u0010\u0095\u0001\u001a\u00020\u0005J\b\u0010\u0096\u0001\u001a\u00030\u008a\u0001J\u0007\u0010\u0097\u0001\u001a\u00020\u0005J\u0007\u0010\u0098\u0001\u001a\u00020\u0005J\u0007\u0010\u0099\u0001\u001a\u00020\u0005J\u0014\u0010\u009a\u0001\u001a\u00030\u009b\u00012\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0016J\u0007\u0010\u009e\u0001\u001a\u00020]J\u0007\u0010\u009f\u0001\u001a\u00020]R\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0013\u00102\u001a\u0004\u0018\u000103¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bE\u0010DR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bF\u0010DR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bI\u0010@R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010@R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u0010@R\u0011\u0010\u000e\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bL\u0010DR\u0013\u00100\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010@R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bN\u0010@R\u0016\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010@R\u0013\u00105\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bP\u0010@R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010HR\u0015\u0010<\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\bR\u0010SR\u001e\u0010.\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010T\u001a\u0004\bU\u0010S\"\u0004\bV\u0010WR\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010@\"\u0004\bY\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010DR\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b[\u0010@R\u0015\u00106\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\b6\u0010SR\u0015\u00107\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\b7\u0010SR&\u0010\\\u001a\u00020]2\u0006\u0010\\\u001a\u00020]8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010^\"\u0004\b_\u0010`R\u0015\u0010:\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\ba\u0010SR\u0015\u00108\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\bb\u0010SR\u0015\u0010;\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\bc\u0010SR\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010@\"\u0004\be\u0010\u0006R\u001a\u0010+\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010@\"\u0004\bg\u0010\u0006R\u001a\u0010*\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010@\"\u0004\bi\u0010\u0006R\u001a\u0010)\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010@\"\u0004\bk\u0010\u0006R\u0015\u0010/\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\bl\u0010SR\u0011\u0010\u0017\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bm\u0010DR\u001a\u0010(\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010@\"\u0004\bo\u0010\u0006R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bp\u0010@R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bq\u0010@R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\br\u0010@R&\u0010s\u001a\u00020]2\u0006\u0010s\u001a\u00020]8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010^\"\u0004\bu\u0010`R\u0013\u0010=\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bv\u0010@R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bw\u0010@R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bx\u0010@R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\by\u0010@R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bz\u0010@R\u0015\u00109\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010T\u001a\u0004\b{\u0010SR\u0011\u0010\u001e\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b|\u0010DR\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b}\u0010~R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010@R\u0014\u00104\u001a\u0004\u0018\u00010\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010@R\u001a\u0010!\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0012¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010HR \u0010-\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0012\n\u0002\u0010T\u001a\u0005\b\u0082\u0001\u0010S\"\u0005\b\u0083\u0001\u0010WR\u0012\u0010#\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010@R\u0016\u00101\u001a\u0004\u0018\u00010\b¢\u0006\u000b\n\u0002\u0010T\u001a\u0005\b\u0085\u0001\u0010SR\u0012\u0010%\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010@R\u0012\u0010&\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010@¨\u0006¡\u0001"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", "()V", "startTimeStr", "", "(Ljava/lang/String;)V", "calculateType", "", "catagoryId", "catagoryId2", "category_name", "continueRechargeDiscount", "couponAmount", "couponNum", "discount", "firstRechargeDiscount", "gamePicList", "", "category_list", "icon", "id", "name", "playNum", "scdiscount", "score", "serviceType", "service_tip", "showDiscount", "showZhekou", "star", Constant.START_TIME, "", "tags", "Lcom/cy/yyjia/zhe28/domain/GameBean$Tag;", "type", "indexPic", "videoCoverUrl", "videoUrl", "androidSize", "recommendType", "name_suffix", "name_prefix", "name_mid", "screen", "task_id", "groupType", "num", "customGameId", "unclaimed", "appInfo", "Lcom/cy/yyjia/zhe28/domain/AppInfo;", "status", "free_tip", "isFree", "isGm", "isjiasu", "show_jiasu", "isbeta", "isopen", "gamePicScreen", "serverText", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lcom/cy/yyjia/zhe28/domain/AppInfo;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getAndroidSize", "()Ljava/lang/String;", "getAppInfo", "()Lcom/cy/yyjia/zhe28/domain/AppInfo;", "getCalculateType", "()I", "getCatagoryId", "getCatagoryId2", "getCategory_list", "()Ljava/util/List;", "getCategory_name", "getContinueRechargeDiscount", "getCouponAmount", "getCouponNum", "getCustomGameId", "getDiscount", "getFirstRechargeDiscount", "getFree_tip", "getGamePicList", "getGamePicScreen", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGroupType", "setGroupType", "(Ljava/lang/Integer;)V", "getIcon", "setIcon", "getId", "getIndexPic", "isOrder", "", "()Z", "setOrder", "(Z)V", "getIsbeta", "getIsjiasu", "getIsopen", "getName", "setName", "getName_mid", "setName_mid", "getName_prefix", "setName_prefix", "getName_suffix", "setName_suffix", "getNum", "getPlayNum", "getRecommendType", "setRecommendType", "getScdiscount", "getScore", "getScreen", "selected", "getSelected", "setSelected", "getServerText", "getServiceType", "getService_tip", "getShowDiscount", "getShowZhekou", "getShow_jiasu", "getStar", "getStartTime", "()J", "getStartTimeStr", "getStatus", "getTags", "getTask_id", "setTask_id", "getType", "getUnclaimed", "getVideoCoverUrl", "getVideoUrl", "canShowDiscount", "getBannerStr", "Landroid/text/SpannableString;", "getBtnStr", "getDescNew", "getDescShort", "getDescStr", "getDescStr2", "getFirstPayText", "getHeadStr", "getLimitName", "getOnlineText", "getOtherPayText", "getPlayNumStr", "getReservedStr", "getScoreStr", "getShowName", "getTagStr", "gotoGame", "", "v", "Landroid/view/View;", "isOnline", "showRecommendDiscount", "Tag", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class GameBean extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final String androidSize;
    private final AppInfo appInfo;
    private final int calculateType;
    private final int catagoryId;
    private final int catagoryId2;
    private final List<String> category_list;
    private final String category_name;

    @SerializedName(alternate = {"showxcdiscount"}, value = "continueRechargeDiscount")
    private final String continueRechargeDiscount;
    private final String couponAmount;
    private final int couponNum;
    private final String customGameId;
    private final String discount;

    @SerializedName(alternate = {"showscdiscount"}, value = "firstRechargeDiscount")
    private final String firstRechargeDiscount;
    private final String free_tip;
    private final List<String> gamePicList;
    private final Integer gamePicScreen;
    private Integer groupType;
    private String icon;
    private final int id;
    private final String indexPic;
    private final Integer isFree;
    private final Integer isGm;
    private boolean isOrder;
    private final Integer isbeta;
    private final Integer isjiasu;
    private final Integer isopen;
    private String name;
    private String name_mid;
    private String name_prefix;
    private String name_suffix;
    private final Integer num;
    private final int playNum;
    private String recommendType;
    private final String scdiscount;
    private final String score;
    private final String screen;
    private boolean selected;
    private final String serverText;
    private final String serviceType;
    private final String service_tip;
    private final String showDiscount;
    private final String showZhekou;
    private final Integer show_jiasu;
    private final int star;
    private final long startTime;
    private final String startTimeStr;
    private final String status;
    private final List<Tag> tags;
    private Integer task_id;
    private final String type;
    private final Integer unclaimed;
    private final String videoCoverUrl;
    private final String videoUrl;

    public final int getCalculateType() {
        return this.calculateType;
    }

    public final int getCatagoryId() {
        return this.catagoryId;
    }

    public final int getCatagoryId2() {
        return this.catagoryId2;
    }

    public final String getCategory_name() {
        return this.category_name;
    }

    public final String getContinueRechargeDiscount() {
        return this.continueRechargeDiscount;
    }

    public final String getCouponAmount() {
        return this.couponAmount;
    }

    public final int getCouponNum() {
        return this.couponNum;
    }

    public final String getDiscount() {
        return this.discount;
    }

    public final String getFirstRechargeDiscount() {
        return this.firstRechargeDiscount;
    }

    public final List<String> getGamePicList() {
        return this.gamePicList;
    }

    public final List<String> getCategory_list() {
        return this.category_list;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final void setIcon(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.icon = str;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final int getPlayNum() {
        return this.playNum;
    }

    public final String getScdiscount() {
        return this.scdiscount;
    }

    public final String getScore() {
        return this.score;
    }

    public final String getServiceType() {
        return this.serviceType;
    }

    public final String getService_tip() {
        return this.service_tip;
    }

    public final String getShowDiscount() {
        return this.showDiscount;
    }

    public final String getShowZhekou() {
        return this.showZhekou;
    }

    public final int getStar() {
        return this.star;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final String getStartTimeStr() {
        return this.startTimeStr;
    }

    public final List<Tag> getTags() {
        return this.tags;
    }

    public final String getType() {
        return this.type;
    }

    public final String getIndexPic() {
        return this.indexPic;
    }

    public final String getVideoCoverUrl() {
        return this.videoCoverUrl;
    }

    public final String getVideoUrl() {
        return this.videoUrl;
    }

    public final String getAndroidSize() {
        return this.androidSize;
    }

    public final String getRecommendType() {
        return this.recommendType;
    }

    public final void setRecommendType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.recommendType = str;
    }

    public final String getName_suffix() {
        return this.name_suffix;
    }

    public final void setName_suffix(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name_suffix = str;
    }

    public final String getName_prefix() {
        return this.name_prefix;
    }

    public final void setName_prefix(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name_prefix = str;
    }

    public final String getName_mid() {
        return this.name_mid;
    }

    public final void setName_mid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name_mid = str;
    }

    public final String getScreen() {
        return this.screen;
    }

    public /* synthetic */ GameBean(int i, int i2, int i3, String str, String str2, String str3, int i4, String str4, String str5, List list, List list2, String str6, int i5, String str7, int i6, String str8, String str9, String str10, String str11, String str12, String str13, int i7, long j, String str14, List list3, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, Integer num, Integer num2, Integer num3, String str25, Integer num4, AppInfo appInfo, String str26, String str27, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, String str28, int i8, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str, str2, str3, i4, str4, str5, list, list2, str6, i5, str7, i6, str8, str9, str10, str11, str12, str13, i7, j, str14, list3, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, (i9 & 8) != 0 ? 0 : num, (i9 & 16) != 0 ? 0 : num2, (i9 & 32) != 0 ? 0 : num3, (i9 & 64) != 0 ? "0" : str25, (i9 & 128) != 0 ? 0 : num4, (i9 & 256) != 0 ? null : appInfo, (i9 & 512) != 0 ? "" : str26, (i9 & 1024) != 0 ? "" : str27, (i9 & 2048) != 0 ? 0 : num5, (i9 & 4096) != 0 ? 0 : num6, (i9 & 8192) != 0 ? 0 : num7, (i9 & 16384) != 0 ? 0 : num8, (32768 & i9) != 0 ? 0 : num9, (65536 & i9) != 0 ? 0 : num10, (131072 & i9) != 0 ? 0 : num11, (i9 & 262144) != 0 ? "" : str28);
    }

    public final Integer getTask_id() {
        return this.task_id;
    }

    public final void setTask_id(Integer num) {
        this.task_id = num;
    }

    public final Integer getGroupType() {
        return this.groupType;
    }

    public final void setGroupType(Integer num) {
        this.groupType = num;
    }

    public final Integer getNum() {
        return this.num;
    }

    public final String getCustomGameId() {
        return this.customGameId;
    }

    public final Integer getUnclaimed() {
        return this.unclaimed;
    }

    public final AppInfo getAppInfo() {
        return this.appInfo;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getFree_tip() {
        return this.free_tip;
    }

    /* JADX INFO: renamed from: isFree, reason: from getter */
    public final Integer getIsFree() {
        return this.isFree;
    }

    /* JADX INFO: renamed from: isGm, reason: from getter */
    public final Integer getIsGm() {
        return this.isGm;
    }

    public final Integer getIsjiasu() {
        return this.isjiasu;
    }

    public final Integer getShow_jiasu() {
        return this.show_jiasu;
    }

    public final Integer getIsbeta() {
        return this.isbeta;
    }

    public final Integer getIsopen() {
        return this.isopen;
    }

    public final Integer getGamePicScreen() {
        return this.gamePicScreen;
    }

    public final String getServerText() {
        return this.serverText;
    }

    public GameBean(int i, int i2, int i3, String category_name, String continueRechargeDiscount, String couponAmount, int i4, String discount, String firstRechargeDiscount, List<String> gamePicList, List<String> category_list, String icon, int i5, String name, int i6, String scdiscount, String score, String serviceType, String service_tip, String showDiscount, String showZhekou, int i7, long j, String startTimeStr, List<Tag> list, String type, String indexPic, String videoCoverUrl, String videoUrl, String androidSize, String recommendType, String name_suffix, String name_prefix, String name_mid, String screen, Integer num, Integer num2, Integer num3, String str, Integer num4, AppInfo appInfo, String str2, String str3, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, String str4) {
        Intrinsics.checkNotNullParameter(category_name, "category_name");
        Intrinsics.checkNotNullParameter(continueRechargeDiscount, "continueRechargeDiscount");
        Intrinsics.checkNotNullParameter(couponAmount, "couponAmount");
        Intrinsics.checkNotNullParameter(discount, "discount");
        Intrinsics.checkNotNullParameter(firstRechargeDiscount, "firstRechargeDiscount");
        Intrinsics.checkNotNullParameter(gamePicList, "gamePicList");
        Intrinsics.checkNotNullParameter(category_list, "category_list");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(scdiscount, "scdiscount");
        Intrinsics.checkNotNullParameter(score, "score");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(service_tip, "service_tip");
        Intrinsics.checkNotNullParameter(showDiscount, "showDiscount");
        Intrinsics.checkNotNullParameter(showZhekou, "showZhekou");
        Intrinsics.checkNotNullParameter(startTimeStr, "startTimeStr");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(indexPic, "indexPic");
        Intrinsics.checkNotNullParameter(videoCoverUrl, "videoCoverUrl");
        Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
        Intrinsics.checkNotNullParameter(androidSize, "androidSize");
        Intrinsics.checkNotNullParameter(recommendType, "recommendType");
        Intrinsics.checkNotNullParameter(name_suffix, "name_suffix");
        Intrinsics.checkNotNullParameter(name_prefix, "name_prefix");
        Intrinsics.checkNotNullParameter(name_mid, "name_mid");
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.calculateType = i;
        this.catagoryId = i2;
        this.catagoryId2 = i3;
        this.category_name = category_name;
        this.continueRechargeDiscount = continueRechargeDiscount;
        this.couponAmount = couponAmount;
        this.couponNum = i4;
        this.discount = discount;
        this.firstRechargeDiscount = firstRechargeDiscount;
        this.gamePicList = gamePicList;
        this.category_list = category_list;
        this.icon = icon;
        this.id = i5;
        this.name = name;
        this.playNum = i6;
        this.scdiscount = scdiscount;
        this.score = score;
        this.serviceType = serviceType;
        this.service_tip = service_tip;
        this.showDiscount = showDiscount;
        this.showZhekou = showZhekou;
        this.star = i7;
        this.startTime = j;
        this.startTimeStr = startTimeStr;
        this.tags = list;
        this.type = type;
        this.indexPic = indexPic;
        this.videoCoverUrl = videoCoverUrl;
        this.videoUrl = videoUrl;
        this.androidSize = androidSize;
        this.recommendType = recommendType;
        this.name_suffix = name_suffix;
        this.name_prefix = name_prefix;
        this.name_mid = name_mid;
        this.screen = screen;
        this.task_id = num;
        this.groupType = num2;
        this.num = num3;
        this.customGameId = str;
        this.unclaimed = num4;
        this.appInfo = appInfo;
        this.status = str2;
        this.free_tip = str3;
        this.isFree = num5;
        this.isGm = num6;
        this.isjiasu = num7;
        this.show_jiasu = num8;
        this.isbeta = num9;
        this.isopen = num10;
        this.gamePicScreen = num11;
        this.serverText = str4;
    }

    public GameBean() {
        this(0, 0, 0, "", "", "", 0, "", "", CollectionsKt.emptyList(), CollectionsKt.emptyList(), "", -1, "", 0, "", "", "", "", "", "", 0, 0L, "", CollectionsKt.emptyList(), "", "", "", "", "", "", "", "", "", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 524280, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GameBean(String startTimeStr) {
        this(0, 0, 0, "", "", "", 0, "", "", CollectionsKt.emptyList(), CollectionsKt.emptyList(), "", -1, "", 0, "", "", "", "", "", "", 0, 0L, startTimeStr, CollectionsKt.emptyList(), "", "", "", "", "", "", "", "", "", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 524280, null);
        Intrinsics.checkNotNullParameter(startTimeStr, "startTimeStr");
    }

    @Bindable
    /* JADX INFO: renamed from: isOrder, reason: from getter */
    public final boolean getIsOrder() {
        return this.isOrder;
    }

    public final void setOrder(boolean z) {
        this.isOrder = z;
        notifyPropertyChanged(69);
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    public final String getShowName() {
        if (TextUtils.isEmpty(this.name_prefix)) {
            return this.name;
        }
        return this.name_prefix;
    }

    public final boolean canShowDiscount() {
        Integer num = this.isFree;
        if (num != null && num.intValue() == 1) {
            return true;
        }
        return (Intrinsics.areEqual(this.showDiscount, "0") || Intrinsics.areEqual(this.showDiscount, "100")) ? false : true;
    }

    public void gotoGame(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Integer num = this.task_id;
        if (num == null || num.intValue() != 0) {
            Util.openWebWithLogin(v.getContext(), "任务详情", NetUtil.BASE_URL3 + "dist/task-detail?taskId=" + this.task_id + "}");
            return;
        }
        Util.gotoGame(v.getContext(), this.id);
    }

    public final String getScoreStr() {
        String string = new BigDecimal(this.score).divide(new BigDecimal(10)).setScale(1, RoundingMode.HALF_UP).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final SpannableString getDescStr() {
        String str;
        if (this.service_tip.length() == 0) {
            str = this.category_name + " | " + getPlayNumStr();
        } else {
            str = this.category_name + " | " + this.service_tip + " | " + getPlayNumStr();
        }
        SpannableString spannableString = new SpannableString(str);
        if (this.service_tip.length() > 0) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFBB00")), this.category_name.length(), this.category_name.length() + this.service_tip.length() + 6, 33);
        }
        return spannableString;
    }

    public final SpannableString getBannerStr() {
        String strValueOf;
        if (this.service_tip.length() == 0) {
            strValueOf = String.valueOf(getPlayNumStr());
        } else {
            strValueOf = this.service_tip + " | " + getPlayNumStr();
        }
        SpannableString spannableString = new SpannableString(strValueOf);
        if (this.service_tip.length() > 0) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFBB00")), 0, this.service_tip.length(), 33);
        }
        return spannableString;
    }

    public final SpannableString getDescStr2() {
        SpannableString spannableString = new SpannableString(this.category_name + " | " + this.androidSize + "MB | " + getPlayNumStr());
        if (TextUtils.isEmpty(this.category_name)) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFBB00")), 4, this.androidSize.length() + 12, 33);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFBB00")), this.category_name.length(), this.category_name.length() + this.androidSize.length() + 8, 33);
        }
        return spannableString;
    }

    public final String getDescShort() {
        return this.category_name + " | " + getPlayNumStr();
    }

    public final SpannableString getDescNew() {
        String str = this.category_name + StringUtils.SPACE + getPlayNumStr();
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(this.category_name)) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), 4, str.length(), 33);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), this.category_name.length(), str.length(), 33);
        }
        return spannableString;
    }

    public final String getTagStr() {
        List<Tag> list = this.tags;
        String str = "";
        if (list == null || list.isEmpty()) {
            return "";
        }
        Iterator<Tag> it = this.tags.iterator();
        while (it.hasNext()) {
            str = str + it.next().getName() + "丨";
        }
        String strSubstring = str.substring(0, str.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final SpannableString getReservedStr() {
        String str = this.category_name + StringUtils.SPACE + this.num + "人已预约";
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(this.category_name)) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), 4, str.length(), 33);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F86938")), this.category_name.length(), str.length(), 33);
        }
        return spannableString;
    }

    public final String getFirstPayText() {
        String string = new BigDecimal(this.scdiscount).divide(new BigDecimal(10)).setScale(2, RoundingMode.HALF_UP).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String strReplace$default = StringsKt.replace$default(string, ".00", "", false, 4, (Object) null);
        if (StringsKt.endsWith$default(strReplace$default, "0", false, 2, (Object) null)) {
            strReplace$default = strReplace$default.substring(0, strReplace$default.length() - 1);
            Intrinsics.checkNotNullExpressionValue(strReplace$default, "substring(...)");
        }
        return strReplace$default + "折";
    }

    public final String getOtherPayText() {
        String string = new BigDecimal(this.discount).divide(new BigDecimal(10)).setScale(2, RoundingMode.HALF_UP).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String strReplace$default = StringsKt.replace$default(string, ".00", "", false, 4, (Object) null);
        if (StringsKt.endsWith$default(strReplace$default, "0", false, 2, (Object) null)) {
            strReplace$default = strReplace$default.substring(0, strReplace$default.length() - 1);
            Intrinsics.checkNotNullExpressionValue(strReplace$default, "substring(...)");
        }
        return strReplace$default + "折";
    }

    public final String getLimitName(int num) {
        if (this.name.length() > num) {
            String strSubstring = this.name.substring(0, num);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring + "...";
        }
        return this.name;
    }

    public final String getHeadStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        String str = simpleDateFormat.format(new Date());
        long j = 86400000;
        String str2 = simpleDateFormat.format(new Date(new Date().getTime() - j));
        String str3 = simpleDateFormat.format(new Date(new Date().getTime() + j));
        String str4 = simpleDateFormat.format(new Date(new Date().getTime() + ((long) 172800000)));
        String str5 = this.startTimeStr;
        if (Intrinsics.areEqual(str5, str)) {
            return this.startTimeStr + " 今天上线";
        }
        if (Intrinsics.areEqual(str5, str2)) {
            return this.startTimeStr + " 昨天上线";
        }
        if (Intrinsics.areEqual(str5, str3)) {
            return this.startTimeStr + " 明天上线";
        }
        if (!Intrinsics.areEqual(str5, str4)) {
            return this.startTimeStr;
        }
        return this.startTimeStr + " 后天上线";
    }

    public final String getPlayNumStr() {
        int iIntValue = this.playNum;
        if (iIntValue == 0) {
            Integer num = this.num;
            Intrinsics.checkNotNull(num);
            iIntValue = num.intValue();
        }
        if (iIntValue > 9999) {
            return GMTitleBean$$ExternalSyntheticBackport0.m(new BigDecimal(iIntValue).divide(new BigDecimal("10000")).setScale(1, RoundingMode.HALF_UP)) + "万人在玩";
        }
        return iIntValue + "人在玩";
    }

    public final boolean showRecommendDiscount() {
        return Intrinsics.areEqual("recommend", this.recommendType);
    }

    public final String getBtnStr() {
        return Intrinsics.areEqual(this.type, "gameapp") ? "下载" : "开始玩";
    }

    public final boolean isOnline() {
        long j = this.startTime;
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("000");
        return Long.parseLong(sb.toString()) < System.currentTimeMillis();
    }

    public final String getOnlineText() {
        return new SimpleDateFormat("MM月dd日").format(new Date(Long.parseLong(this.startTime + "000"))) + " 上线";
    }

    /* JADX INFO: compiled from: GameBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameBean$Tag;", "", "id", "", "name", "", "title", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "subName", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getId", "()I", "getName", "getSubName", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Tag {
        public static final int $stable = 0;
        private final String content;
        private final int id;
        private final String name;
        private final String subName;
        private final String title;

        public static /* synthetic */ Tag copy$default(Tag tag, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = tag.id;
            }
            if ((i2 & 2) != 0) {
                str = tag.name;
            }
            String str5 = str;
            if ((i2 & 4) != 0) {
                str2 = tag.title;
            }
            String str6 = str2;
            if ((i2 & 8) != 0) {
                str3 = tag.content;
            }
            String str7 = str3;
            if ((i2 & 16) != 0) {
                str4 = tag.subName;
            }
            return tag.copy(i, str5, str6, str7, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSubName() {
            return this.subName;
        }

        public final Tag copy(int id, String name, String title, String content, String subName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(subName, "subName");
            return new Tag(id, name, title, content, subName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Tag)) {
                return false;
            }
            Tag tag = (Tag) other;
            return this.id == tag.id && Intrinsics.areEqual(this.name, tag.name) && Intrinsics.areEqual(this.title, tag.title) && Intrinsics.areEqual(this.content, tag.content) && Intrinsics.areEqual(this.subName, tag.subName);
        }

        public int hashCode() {
            return (((((((this.id * 31) + this.name.hashCode()) * 31) + this.title.hashCode()) * 31) + this.content.hashCode()) * 31) + this.subName.hashCode();
        }

        public String toString() {
            return "Tag(id=" + this.id + ", name=" + this.name + ", title=" + this.title + ", content=" + this.content + ", subName=" + this.subName + ")";
        }

        public Tag(int i, String name, String title, String content, String subName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(subName, "subName");
            this.id = i;
            this.name = name;
            this.title = title;
            this.content = content;
            this.subName = subName;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getContent() {
            return this.content;
        }

        public final String getSubName() {
            return this.subName;
        }
    }
}
