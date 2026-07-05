package com.huya.berry.client;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.widget.FrameLayout;
import com.huya.berry.client.customui.CustomUICallback;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class HuyaBerry {
    private static volatile HuyaBerry mInstance;

    public interface BerryEvent {
        public static final String BERRYEVENT_ATTENDANCECOUNT = "attendanceCount";
        public static final String BERRYEVENT_DANMUCONTENT = "danmuContent";
        public static final String BERRYEVENT_DURATION = "duration";
        public static final String BERRYEVENT_ENDLIVETIME = "endLiveTime";
        public static final String BERRYEVENT_EVENTTYPE = "eventType";
        public static final String BERRYEVENT_EVENTTYPE_CLOSELIVELIST = "closeLiveList";
        public static final String BERRYEVENT_EVENTTYPE_ENDLIVE = "endLive";
        public static final String BERRYEVENT_EVENTTYPE_EXITFULLSCREEN = "exitFullScreen";
        public static final String BERRYEVENT_EVENTTYPE_FULLSCREEN = "fullScreen";
        public static final String BERRYEVENT_EVENTTYPE_INIT = "init";
        public static final String BERRYEVENT_EVENTTYPE_RECEIVEDANMU = "receiveDanmu";
        public static final String BERRYEVENT_EVENTTYPE_RESTARTLIVE = "reStartLive";
        public static final String BERRYEVENT_EVENTTYPE_SENDPLAYERDATA = "sendPlayerData";
        public static final String BERRYEVENT_EVENTTYPE_SHOWFLOATING = "showFloating";
        public static final String BERRYEVENT_EVENTTYPE_STARTLIVE = "startLive";
        public static final String BERRYEVENT_EVENTTYPE_STARTUP = "startUp";
        public static final String BERRYEVENT_GAMEACCOUNTID = "gameAccountID";
        public static final String BERRYEVENT_ISLAST = "isLast";
        public static final String BERRYEVENT_LIVEID = "liveId";
        public static final String BERRYEVENT_NICKNAME = "nickName";
        public static final String BERRYEVENT_RESULTCODE = "resultCode";
        public static final String BERRYEVENT_RESULTCODE_FAIL = "-1";
        public static final String BERRYEVENT_RESULTCODE_SUCCESS = "0";
        public static final String BERRYEVENT_RESULTMSG = "msg";
        public static final String BERRYEVENT_ROOMID = "roomId";
        public static final String BERRYEVENT_STARTLIVETIME = "startLiveTime";
        public static final String BERRYEVENT_STARTUPTIME = "startUpTime";
        public static final String BERRYEVENT_UID = "huyaUid";

        void onEventCallback(Map<String, String> map);
    }

    public abstract void changeGame(int i, CustomUICallback customUICallback);

    public abstract void changeGame(String str, CustomUICallback customUICallback);

    public abstract void changeLandscapeMode(boolean z);

    public abstract void closeFloat();

    public abstract void customUIGetAuthorInfo(Activity activity, CustomUICallback customUICallback);

    public abstract void customUIGetResolution(Activity activity, CustomUICallback customUICallback);

    public abstract void customUILogin(Activity activity, CustomUICallback customUICallback);

    public abstract void customUILogout(Activity activity, CustomUICallback customUICallback);

    public abstract void customUIModifyAnnouncement(Activity activity, CustomUICallback customUICallback, String str);

    public abstract void customUIModifyNickname(Activity activity, CustomUICallback customUICallback);

    public abstract void customUIModifyTitle(Activity activity, CustomUICallback customUICallback, String str);

    public abstract void customUIOpenQuality(Activity activity, CustomUICallback customUICallback);

    public abstract void customUIOpenSendDanmu(Activity activity, CustomUICallback customUICallback);

    public abstract void customUISetResolution(Activity activity, CustomUICallback customUICallback, int i);

    public abstract void customUIStartLive(Activity activity, CustomUICallback customUICallback);

    public abstract void fullScreenPlay();

    public abstract void fullScreenPlay(long j);

    public abstract void getLiveData(long j, CustomUICallback customUICallback);

    public abstract void getLiveDataByRoomId(long j, CustomUICallback customUICallback);

    public abstract void getLiveListData(boolean z, CustomUICallback customUICallback);

    public abstract void getLiveListDataByTag(String str, boolean z, CustomUICallback customUICallback);

    public abstract void getTagListData(CustomUICallback customUICallback);

    public abstract void hideDanmuView();

    public abstract void init(Application application, HuyaBerryConfig huyaBerryConfig);

    public abstract void joinChannel(long j, int i);

    public abstract void onActivityResult(int i, int i2, Intent intent);

    public abstract void pauseLive(boolean z);

    public abstract void pauseVideoPlay();

    public abstract void querySubscribeStatus(long j, CustomUICallback customUICallback);

    public abstract void rtmpPushLive(Activity activity, String str, CustomUICallback customUICallback);

    public abstract void sendDanmu(long j, long j2, long j3, Activity activity, String str, CustomUICallback customUICallback);

    public abstract void sendGameUpData(String str);

    public abstract void sendPlayerData(BerryPlayerDataHelper berryPlayerDataHelper);

    public abstract void setBerryEventDelegate(BerryEvent berryEvent);

    public abstract void setGameAccountID(String str);

    public abstract void setGangUpTip(String str, String str2);

    public abstract void setPlayConfig(HuyaBerryPlayConfig huyaBerryPlayConfig);

    public abstract void setReceiveDanmuData(boolean z, long j);

    public abstract void showDanmuView(FrameLayout frameLayout, long j);

    public abstract void smallWindowPlay(Activity activity);

    public abstract void startLive(Activity activity, StartLiveConfig startLiveConfig);

    public abstract void startLive(Activity activity, String str, String str2);

    public abstract void startVideoPlay();

    public abstract void stopRtmpLive(Activity activity, CustomUICallback customUICallback);

    public abstract void subscribe(long j, CustomUICallback customUICallback);

    public abstract void switchDanmu(boolean z);

    public abstract void switchVoice(boolean z);

    public abstract void unSubscribe(long j, CustomUICallback customUICallback);

    public abstract void uninit();

    public abstract void watchLive(long j, Activity activity, CustomUICallback customUICallback);

    public abstract void watchLiveByUid(long j, Activity activity, CustomUICallback customUICallback);

    public static HuyaBerry instance() {
        if (mInstance == null) {
            synchronized (HuyaBerry.class) {
                if (mInstance == null) {
                    mInstance = new HuyaBerryImpl();
                }
            }
        }
        return mInstance;
    }

    public static class BerryPlayerDataHelper {
        public String customJson;
        public String playerLevel;
        public String roleName;
        public String serverID;

        public long getSize() {
            return getStrSize(this.roleName, this.serverID, this.playerLevel, this.customJson);
        }

        private long getStrSize(String... strArr) {
            long length = 0;
            for (String str : strArr) {
                if (str != null) {
                    length += (long) str.getBytes().length;
                }
            }
            return length;
        }
    }
}
