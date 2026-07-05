package com.huya.berry.gamesdk;

import com.duowan.auk.asignal.Property;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SdkProperties {
    public static final Property<String> appId = new Property<>("");
    public static final Property<String> appKey = new Property<>("");
    public static final Property<Integer> gameId = new Property<>(0);
    public static final Property<SDKMode> sdkMode = new Property<>(SDKMode.CAPTURE_BY_SCREEN);
    public static final Property<Boolean> enableH265 = new Property<>(false);
    public static final Property<String> liveTitle = new Property<>("");
    public static final Property<String> liveAnnouncement = new Property<>("");
    public static final Property<String> notLoginGangUpTip = new Property<>("登录虎牙账号，即可获得直播开黑特权，开播后观众可通过直播间一键加入队伍！");
    public static final Property<String> loginGangUpTip = new Property<>("已开启直播开黑互动特权！使用当前虎牙账号开播，即可快速与观众组队开黑~");
    public static final Property<String> gangUpData = new Property<>("");
    public static final String MarkIsLiving = "isLiving";
    public static final Property<Boolean> isLiving = new Property<>(false, MarkIsLiving);
    public static final Property<Boolean> isLandscape = new Property<>(true);
    public static final Property<Boolean> isOneKeyGangUp = new Property<>(false);
    public static final Property<Boolean> enableHardEncode = new Property<>(true);
    public static final Property<Boolean> isNeedPlay = new Property<>(false);
    public static final Property<Boolean> hidePauseBtn = new Property<>(false);
    public static final String MarkHideMsgNumber = "HideMsgNumber";
    public static Property<Integer> hideMsgNumber = new Property<>(0, MarkHideMsgNumber);
    public static Property<Long> liveId = new Property<>(0L);
    public static Property<Long> multiStreamFlag = new Property<>(0L);
    public static Property<Integer> streamType = new Property<>(0);
    public static Property<Integer> resolution = new Property<>(2);
    public static Property<String> gameAccountID = new Property<>("");
    public static Property<Integer> sendDataSizeLimit = new Property<>(102400);
    public static Property<Integer> sendDataCountLimit = new Property<>(60);
    public static Property<Integer> flowControlPolicy = new Property<>(2);
    public static Property<String> openId = new Property<>("");
    public static Property<String> tencentGameName = new Property<>("");
    public static Property<Long> startLiveTime = new Property<>(0L);
    public static Property<Integer> maxPlayBitrate = new Property<>(40000);
    public static Property<Boolean> enableFullSreenSupport = new Property<>(false);
    public static Property<Boolean> specialOverlayType = new Property<>(false);
    public static final String MarkPLayerFloating = "isPLayerFloating";
    public static final Property<Boolean> isPLayerFloating = new Property<>(false, MarkPLayerFloating);
    public static final String MarkCustomReceiveDanmu = "isCustomReceiveDanmu";
    public static final Property<Boolean> isCustomReceiveDanmu = new Property<>(false, MarkCustomReceiveDanmu);
    public static Property<Boolean> isVideoLandScape = new Property<>(true);
    public static Property<Boolean> isPushStreamOk = new Property<>(false);
    public static Property<Boolean> needPlayerFloat = new Property<>(false);
    public static Property<Boolean> isStopMidea = new Property<>(false);
    public static Property<Boolean> isCertificate = new Property<>(false);
    public static final Property<Boolean> isClickPauseLive = new Property<>(true);
    public static final Property<Boolean> floatSendDanmu = new Property<>(true);
    public static final Property<Boolean> normalSendDanmu = new Property<>(true);
    public static final Property<Boolean> showQuality = new Property<>(true);
    public static final Property<Boolean> showSwitchDanmu = new Property<>(true);
    public static final Property<Boolean> showSwitchVoice = new Property<>(true);
    public static final Property<Boolean> showFullScreen = new Property<>(true);
    public static final Property<Boolean> showLiveInfo = new Property<>(true);
    public static final Property<Boolean> showSubscribe = new Property<>(true);
    public static final String MarkOpenPrivacy = "openPrivacy";
    public static final Property<Boolean> openPrivacy = new Property<>(false, MarkOpenPrivacy);

    public enum SDKMode {
        CAPTURE_BY_SCREEN,
        CAPTURE_BY_CAMERA,
        CAPTURE_BY_OUTSIDE
    }

    public static void resetPersonalInfo() {
        liveTitle.reset();
        liveAnnouncement.reset();
        liveId.reset();
        resolution.reset();
    }
}
