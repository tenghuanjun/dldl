package com.cy.yyjia.zhe28.domain;

import android.content.Intent;
import android.view.View;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.cy.yyjia.zhe28.ui.activity.DealDetailActivity;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.volcengine.androidcloud.common.pod.PodInfo;
import com.volcengine.common.contant.CommonConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\bf\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002BÁ\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0006\u0012\u0006\u0010\u0017\u001a\u00020\u0006\u0012\u0006\u0010\u0018\u001a\u00020\u0006\u0012\u0006\u0010\u0019\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0006\u0012\u0006\u0010\u001b\u001a\u00020\u0006\u0012\u0006\u0010\u001c\u001a\u00020\u0006\u0012\u0006\u0010\u001d\u001a\u00020\u0006\u0012\u0006\u0010\u001e\u001a\u00020\u0006\u0012\u0006\u0010\u001f\u001a\u00020\u0004\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\"\u001a\u00020#\u0012\b\b\u0002\u0010$\u001a\u00020\u0004\u0012\b\b\u0002\u0010%\u001a\u00020&\u0012\b\b\u0002\u0010'\u001a\u00020&\u0012\b\b\u0002\u0010(\u001a\u00020&\u0012\b\b\u0002\u0010)\u001a\u00020\u0004\u0012\b\b\u0002\u0010*\u001a\u00020\u0004¢\u0006\u0002\u0010+J\u0006\u0010e\u001a\u00020&J\t\u0010f\u001a\u00020\u0004HÆ\u0003J\t\u0010g\u001a\u00020\u0006HÆ\u0003J\u0011\u0010h\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010HÆ\u0003J\t\u0010i\u001a\u00020\u0006HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010k\u001a\u00020\u0006HÆ\u0003J\t\u0010l\u001a\u00020\u0006HÆ\u0003J\t\u0010m\u001a\u00020\u0006HÆ\u0003J\t\u0010n\u001a\u00020\u0006HÆ\u0003J\t\u0010o\u001a\u00020\u0006HÆ\u0003J\t\u0010p\u001a\u00020\u0006HÆ\u0003J\t\u0010q\u001a\u00020\u0006HÆ\u0003J\t\u0010r\u001a\u00020\u0004HÆ\u0003J\t\u0010s\u001a\u00020\u0006HÆ\u0003J\t\u0010t\u001a\u00020\u0006HÆ\u0003J\t\u0010u\u001a\u00020\u0006HÆ\u0003J\t\u0010v\u001a\u00020\u0006HÆ\u0003J\t\u0010w\u001a\u00020\u0006HÆ\u0003J\t\u0010x\u001a\u00020\u0004HÆ\u0003J\u0010\u0010y\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010KJ\u0010\u0010z\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010KJ\t\u0010{\u001a\u00020#HÆ\u0003J\t\u0010|\u001a\u00020\u0006HÆ\u0003J\t\u0010}\u001a\u00020\u0004HÆ\u0003J\t\u0010~\u001a\u00020&HÆ\u0003J\t\u0010\u007f\u001a\u00020&HÆ\u0003J\n\u0010\u0080\u0001\u001a\u00020&HÆ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0082\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0083\u0001\u001a\u00020\u0006HÆ\u0003J\n\u0010\u0084\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0085\u0001\u001a\u00020\u0006HÆ\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0088\u0001\u001a\u00020\u0004HÆ\u0003Jü\u0002\u0010\u0089\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00062\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020&2\b\b\u0002\u0010(\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u0004HÆ\u0001¢\u0006\u0003\u0010\u008a\u0001J\u0016\u0010\u008b\u0001\u001a\u00020&2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001HÖ\u0003J\u0007\u0010\u008e\u0001\u001a\u00020\u0006J\u0007\u0010\u008f\u0001\u001a\u00020\u0006J\u0007\u0010\u0090\u0001\u001a\u00020\u0004J\u0007\u0010\u0091\u0001\u001a\u00020\u0006J\n\u0010\u0092\u0001\u001a\u00020\u0004HÖ\u0001J\u0007\u0010\u0093\u0001\u001a\u00020&J\u0012\u0010\u0094\u0001\u001a\u00030\u0095\u00012\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001J\n\u0010\u0098\u0001\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010*\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u00100R\u001e\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00100\"\u0004\b3\u00104R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010-R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00100\"\u0004\b;\u00104R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010-R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010-R\u001e\u0010$\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010-\"\u0004\b>\u0010?R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010-R\u001a\u0010(\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010-R\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bE\u00100R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bH\u00100R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\bI\u00100R\u0015\u0010 \u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010L\u001a\u0004\bJ\u0010KR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bM\u00100R\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u00100\"\u0004\bO\u00104R\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bP\u00100R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010A\"\u0004\bR\u0010CR\u001e\u0010\u0016\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u00100\"\u0004\bT\u00104R\u001e\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u00100\"\u0004\bV\u00104R\u0015\u0010!\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010L\u001a\u0004\bW\u0010KR\u001a\u0010'\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010A\"\u0004\bY\u0010CR\u0011\u0010\u0018\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bZ\u00100R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010-R\u0011\u0010\u001a\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\\\u00100R\u0011\u0010\u001b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b]\u00100R\u0011\u0010\u001c\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b^\u00100R\u001a\u0010_\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010A\"\u0004\ba\u0010CR\u0011\u0010\u001d\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bb\u00100R\u0011\u0010\u001e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bc\u00100R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010-¨\u0006\u0099\u0001"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", CommonConstants.key_accountId, "", "chkstatus", "", "dateline", SocialConstants.PARAM_COMMENT, "endTime", "gameIcon", CommonConstants.key_gameId, "id", "lastTime", "name", "pic", "", Constants.PARAM_PLATFORM, "productName", "remark", "roleName", "security", "sellMoney", "service", "siteaccount", Constant.START_TIME, "status", "status_str", "tellphone", "totalMoney", "treadType", "uid", "regdays", "servicedays", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "isDicker", "select", "", "showGame", "iscollect", "isDolo", "atId", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IIILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Lcom/cy/yyjia/zhe28/domain/GameBean;IZZZII)V", "getAccountId", "()I", "getAtId", "getChkstatus", "()Ljava/lang/String;", "getDateline", "getDescription", "setDescription", "(Ljava/lang/String;)V", "getEndTime", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "setGame", "(Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "getGameIcon", "setGameIcon", "getGameId", "getId", "setDicker", "(I)V", "getIscollect", "()Z", "setIscollect", "(Z)V", "getLastTime", "getName", "getPic", "()Ljava/util/List;", "getPlatform", "getProductName", "getRegdays", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRemark", "getRoleName", "setRoleName", "getSecurity", "getSelect", "setSelect", "getSellMoney", "setSellMoney", "getService", "setService", "getServicedays", "getShowGame", "setShowGame", "getSiteaccount", "getStartTime", "getStatus", "getStatus_str", "getTellphone", "topButton", "getTopButton", "setTopButton", "getTotalMoney", "getTreadType", "getUid", "canOffset", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IIILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Lcom/cy/yyjia/zhe28/domain/GameBean;IZZZII)Lcom/cy/yyjia/zhe28/domain/DealBean;", "equals", "other", "", "getAccountTimeStr", "getDescStr", "getPlatformType", "getServerTimeStr", "hashCode", "showStatus", "toDetail", "", "v", "Landroid/view/View;", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DealBean extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final int accountId;
    private final int atId;
    private final String chkstatus;
    private final String dateline;

    @Bindable
    private String description;
    private final int endTime;
    private GameBean game;
    private String gameIcon;
    private final int gameId;
    private final int id;

    @Bindable
    private int isDicker;
    private final int isDolo;
    private boolean iscollect;
    private final int lastTime;
    private final String name;
    private final List<String> pic;
    private final String platform;
    private final String productName;
    private final Integer regdays;
    private final String remark;
    private String roleName;
    private final String security;

    @Bindable
    private boolean select;

    @Bindable
    private String sellMoney;

    @Bindable
    private String service;
    private final Integer servicedays;
    private boolean showGame;
    private final String siteaccount;
    private final int startTime;
    private final String status;
    private final String status_str;
    private final String tellphone;
    private boolean topButton;
    private final String totalMoney;
    private final String treadType;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component11() {
        return this.pic;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getRemark() {
        return this.remark;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getSecurity() {
        return this.security;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getSellMoney() {
        return this.sellMoney;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getService() {
        return this.service;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getSiteaccount() {
        return this.siteaccount;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getChkstatus() {
        return this.chkstatus;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final int getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getStatus_str() {
        return this.status_str;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getTellphone() {
        return this.tellphone;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getTotalMoney() {
        return this.totalMoney;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getTreadType() {
        return this.treadType;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final Integer getRegdays() {
        return this.regdays;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final Integer getServicedays() {
        return this.servicedays;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final int getIsDicker() {
        return this.isDicker;
    }

    /* JADX INFO: renamed from: component31, reason: from getter */
    public final boolean getSelect() {
        return this.select;
    }

    /* JADX INFO: renamed from: component32, reason: from getter */
    public final boolean getShowGame() {
        return this.showGame;
    }

    /* JADX INFO: renamed from: component33, reason: from getter */
    public final boolean getIscollect() {
        return this.iscollect;
    }

    /* JADX INFO: renamed from: component34, reason: from getter */
    public final int getIsDolo() {
        return this.isDolo;
    }

    /* JADX INFO: renamed from: component35, reason: from getter */
    public final int getAtId() {
        return this.atId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getGameIcon() {
        return this.gameIcon;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getLastTime() {
        return this.lastTime;
    }

    public final DealBean copy(int accountId, String chkstatus, String dateline, String description, int endTime, String gameIcon, int gameId, int id, int lastTime, String name, List<String> pic, String platform, String productName, String remark, String roleName, String security, String sellMoney, String service, String siteaccount, int startTime, String status, String status_str, String tellphone, String totalMoney, String treadType, int uid, Integer regdays, Integer servicedays, GameBean game, int isDicker, boolean select, boolean showGame, boolean iscollect, int isDolo, int atId) {
        Intrinsics.checkNotNullParameter(chkstatus, "chkstatus");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(gameIcon, "gameIcon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(security, "security");
        Intrinsics.checkNotNullParameter(sellMoney, "sellMoney");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(siteaccount, "siteaccount");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(status_str, "status_str");
        Intrinsics.checkNotNullParameter(tellphone, "tellphone");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        Intrinsics.checkNotNullParameter(treadType, "treadType");
        Intrinsics.checkNotNullParameter(game, "game");
        return new DealBean(accountId, chkstatus, dateline, description, endTime, gameIcon, gameId, id, lastTime, name, pic, platform, productName, remark, roleName, security, sellMoney, service, siteaccount, startTime, status, status_str, tellphone, totalMoney, treadType, uid, regdays, servicedays, game, isDicker, select, showGame, iscollect, isDolo, atId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DealBean)) {
            return false;
        }
        DealBean dealBean = (DealBean) other;
        return this.accountId == dealBean.accountId && Intrinsics.areEqual(this.chkstatus, dealBean.chkstatus) && Intrinsics.areEqual(this.dateline, dealBean.dateline) && Intrinsics.areEqual(this.description, dealBean.description) && this.endTime == dealBean.endTime && Intrinsics.areEqual(this.gameIcon, dealBean.gameIcon) && this.gameId == dealBean.gameId && this.id == dealBean.id && this.lastTime == dealBean.lastTime && Intrinsics.areEqual(this.name, dealBean.name) && Intrinsics.areEqual(this.pic, dealBean.pic) && Intrinsics.areEqual(this.platform, dealBean.platform) && Intrinsics.areEqual(this.productName, dealBean.productName) && Intrinsics.areEqual(this.remark, dealBean.remark) && Intrinsics.areEqual(this.roleName, dealBean.roleName) && Intrinsics.areEqual(this.security, dealBean.security) && Intrinsics.areEqual(this.sellMoney, dealBean.sellMoney) && Intrinsics.areEqual(this.service, dealBean.service) && Intrinsics.areEqual(this.siteaccount, dealBean.siteaccount) && this.startTime == dealBean.startTime && Intrinsics.areEqual(this.status, dealBean.status) && Intrinsics.areEqual(this.status_str, dealBean.status_str) && Intrinsics.areEqual(this.tellphone, dealBean.tellphone) && Intrinsics.areEqual(this.totalMoney, dealBean.totalMoney) && Intrinsics.areEqual(this.treadType, dealBean.treadType) && this.uid == dealBean.uid && Intrinsics.areEqual(this.regdays, dealBean.regdays) && Intrinsics.areEqual(this.servicedays, dealBean.servicedays) && Intrinsics.areEqual(this.game, dealBean.game) && this.isDicker == dealBean.isDicker && this.select == dealBean.select && this.showGame == dealBean.showGame && this.iscollect == dealBean.iscollect && this.isDolo == dealBean.isDolo && this.atId == dealBean.atId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v54, types: [int] */
    /* JADX WARN: Type inference failed for: r1v56, types: [int] */
    /* JADX WARN: Type inference failed for: r1v60 */
    /* JADX WARN: Type inference failed for: r1v61 */
    /* JADX WARN: Type inference failed for: r1v65 */
    /* JADX WARN: Type inference failed for: r1v66 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    public int hashCode() {
        int iHashCode = ((((((((((((((((((this.accountId * 31) + this.chkstatus.hashCode()) * 31) + this.dateline.hashCode()) * 31) + this.description.hashCode()) * 31) + this.endTime) * 31) + this.gameIcon.hashCode()) * 31) + this.gameId) * 31) + this.id) * 31) + this.lastTime) * 31) + this.name.hashCode()) * 31;
        List<String> list = this.pic;
        int iHashCode2 = (((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.platform.hashCode()) * 31;
        String str = this.productName;
        int iHashCode3 = (((((((((((((((((((((((((((iHashCode2 + (str == null ? 0 : str.hashCode())) * 31) + this.remark.hashCode()) * 31) + this.roleName.hashCode()) * 31) + this.security.hashCode()) * 31) + this.sellMoney.hashCode()) * 31) + this.service.hashCode()) * 31) + this.siteaccount.hashCode()) * 31) + this.startTime) * 31) + this.status.hashCode()) * 31) + this.status_str.hashCode()) * 31) + this.tellphone.hashCode()) * 31) + this.totalMoney.hashCode()) * 31) + this.treadType.hashCode()) * 31) + this.uid) * 31;
        Integer num = this.regdays;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.servicedays;
        int iHashCode5 = (((((iHashCode4 + (num2 != null ? num2.hashCode() : 0)) * 31) + this.game.hashCode()) * 31) + this.isDicker) * 31;
        boolean z = this.select;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode5 + r1) * 31;
        boolean z2 = this.showGame;
        ?? r12 = z2;
        if (z2) {
            r12 = 1;
        }
        int i2 = (i + r12) * 31;
        boolean z3 = this.iscollect;
        return ((((i2 + (z3 ? 1 : z3)) * 31) + this.isDolo) * 31) + this.atId;
    }

    public String toString() {
        return "DealBean(accountId=" + this.accountId + ", chkstatus=" + this.chkstatus + ", dateline=" + this.dateline + ", description=" + this.description + ", endTime=" + this.endTime + ", gameIcon=" + this.gameIcon + ", gameId=" + this.gameId + ", id=" + this.id + ", lastTime=" + this.lastTime + ", name=" + this.name + ", pic=" + this.pic + ", platform=" + this.platform + ", productName=" + this.productName + ", remark=" + this.remark + ", roleName=" + this.roleName + ", security=" + this.security + ", sellMoney=" + this.sellMoney + ", service=" + this.service + ", siteaccount=" + this.siteaccount + ", startTime=" + this.startTime + ", status=" + this.status + ", status_str=" + this.status_str + ", tellphone=" + this.tellphone + ", totalMoney=" + this.totalMoney + ", treadType=" + this.treadType + ", uid=" + this.uid + ", regdays=" + this.regdays + ", servicedays=" + this.servicedays + ", game=" + this.game + ", isDicker=" + this.isDicker + ", select=" + this.select + ", showGame=" + this.showGame + ", iscollect=" + this.iscollect + ", isDolo=" + this.isDolo + ", atId=" + this.atId + ")";
    }

    public final int getAccountId() {
        return this.accountId;
    }

    public final String getChkstatus() {
        return this.chkstatus;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    public final int getEndTime() {
        return this.endTime;
    }

    public final String getGameIcon() {
        return this.gameIcon;
    }

    public final void setGameIcon(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.gameIcon = str;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getId() {
        return this.id;
    }

    public final int getLastTime() {
        return this.lastTime;
    }

    public final String getName() {
        return this.name;
    }

    public /* synthetic */ DealBean(int i, String str, String str2, String str3, int i2, String str4, int i3, int i4, int i5, String str5, List list, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i6, String str14, String str15, String str16, String str17, String str18, int i7, Integer num, Integer num2, GameBean gameBean, int i8, boolean z, boolean z2, boolean z3, int i9, int i10, int i11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, i2, str4, i3, i4, i5, str5, (i11 & 1024) != 0 ? new ArrayList() : list, str6, (i11 & 4096) != 0 ? "" : str7, str8, str9, str10, (65536 & i11) != 0 ? "" : str11, str12, str13, i6, str14, str15, str16, str17, str18, i7, (67108864 & i11) != 0 ? 0 : num, (134217728 & i11) != 0 ? 0 : num2, gameBean, (536870912 & i11) != 0 ? 0 : i8, (1073741824 & i11) != 0 ? false : z, (i11 & Integer.MIN_VALUE) != 0 ? false : z2, (i12 & 1) != 0 ? false : z3, (i12 & 2) != 0 ? 0 : i9, (i12 & 4) != 0 ? 0 : i10);
    }

    public final List<String> getPic() {
        return this.pic;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final String getRemark() {
        return this.remark;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final void setRoleName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.roleName = str;
    }

    public final String getSecurity() {
        return this.security;
    }

    public final String getSellMoney() {
        return this.sellMoney;
    }

    public final void setSellMoney(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sellMoney = str;
    }

    public final String getService() {
        return this.service;
    }

    public final void setService(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.service = str;
    }

    public final String getSiteaccount() {
        return this.siteaccount;
    }

    public final int getStartTime() {
        return this.startTime;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getStatus_str() {
        return this.status_str;
    }

    public final String getTellphone() {
        return this.tellphone;
    }

    public final String getTotalMoney() {
        return this.totalMoney;
    }

    public final String getTreadType() {
        return this.treadType;
    }

    public final int getUid() {
        return this.uid;
    }

    public final Integer getRegdays() {
        return this.regdays;
    }

    public final Integer getServicedays() {
        return this.servicedays;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final void setGame(GameBean gameBean) {
        Intrinsics.checkNotNullParameter(gameBean, "<set-?>");
        this.game = gameBean;
    }

    public final int isDicker() {
        return this.isDicker;
    }

    public final void setDicker(int i) {
        this.isDicker = i;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public final boolean getShowGame() {
        return this.showGame;
    }

    public final void setShowGame(boolean z) {
        this.showGame = z;
    }

    public final boolean getIscollect() {
        return this.iscollect;
    }

    public final void setIscollect(boolean z) {
        this.iscollect = z;
    }

    public final int isDolo() {
        return this.isDolo;
    }

    public final int getAtId() {
        return this.atId;
    }

    public DealBean(int i, String chkstatus, String dateline, String description, int i2, String gameIcon, int i3, int i4, int i5, String name, List<String> list, String platform, String str, String remark, String roleName, String security, String sellMoney, String service, String siteaccount, int i6, String status, String status_str, String tellphone, String totalMoney, String treadType, int i7, Integer num, Integer num2, GameBean game, int i8, boolean z, boolean z2, boolean z3, int i9, int i10) {
        Intrinsics.checkNotNullParameter(chkstatus, "chkstatus");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(gameIcon, "gameIcon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(security, "security");
        Intrinsics.checkNotNullParameter(sellMoney, "sellMoney");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(siteaccount, "siteaccount");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(status_str, "status_str");
        Intrinsics.checkNotNullParameter(tellphone, "tellphone");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        Intrinsics.checkNotNullParameter(treadType, "treadType");
        Intrinsics.checkNotNullParameter(game, "game");
        this.accountId = i;
        this.chkstatus = chkstatus;
        this.dateline = dateline;
        this.description = description;
        this.endTime = i2;
        this.gameIcon = gameIcon;
        this.gameId = i3;
        this.id = i4;
        this.lastTime = i5;
        this.name = name;
        this.pic = list;
        this.platform = platform;
        this.productName = str;
        this.remark = remark;
        this.roleName = roleName;
        this.security = security;
        this.sellMoney = sellMoney;
        this.service = service;
        this.siteaccount = siteaccount;
        this.startTime = i6;
        this.status = status;
        this.status_str = status_str;
        this.tellphone = tellphone;
        this.totalMoney = totalMoney;
        this.treadType = treadType;
        this.uid = i7;
        this.regdays = num;
        this.servicedays = num2;
        this.game = game;
        this.isDicker = i8;
        this.select = z;
        this.showGame = z2;
        this.iscollect = z3;
        this.isDolo = i9;
        this.atId = i10;
    }

    public final boolean getTopButton() {
        return this.topButton;
    }

    public final void setTopButton(boolean z) {
        this.topButton = z;
    }

    public final void toDetail(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intent intent = new Intent(v.getContext(), (Class<?>) DealDetailActivity.class);
        int i = this.atId;
        if (i == 0) {
            i = this.id;
        }
        intent.putExtra("id", i);
        v.getContext().startActivity(intent);
    }

    public final boolean showStatus() {
        return com.cy.yyjia.zhe28.util.Constant.INSTANCE.getId() == this.uid;
    }

    public final boolean canOffset() {
        return showStatus() && (Intrinsics.areEqual(this.status, "sell") || Intrinsics.areEqual(this.status, "pending"));
    }

    public final String getDescStr() {
        return this.service + " 丨 已建号：" + this.regdays + "天";
    }

    public final String getServerTimeStr() {
        Integer num = this.servicedays;
        if (num != null && num.intValue() == 0) {
            return "";
        }
        return "(已开服" + this.servicedays + "天)";
    }

    public final String getAccountTimeStr() {
        Integer num = this.regdays;
        if (num != null && num.intValue() == 0) {
            return "";
        }
        return this.regdays + "天";
    }

    public final int getPlatformType() {
        String str = this.platform;
        if (Intrinsics.areEqual(str, PodInfo.GAME_TYPE_ANDROID)) {
            return 1;
        }
        return Intrinsics.areEqual(str, "ios") ? 2 : 0;
    }
}
