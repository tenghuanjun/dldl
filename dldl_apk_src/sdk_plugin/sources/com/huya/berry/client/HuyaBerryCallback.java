package com.huya.berry.client;

import android.text.TextUtils;
import com.duowan.auk.util.L;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.component.login.LoginProperties;
import com.huya.component.user.UserProperties;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaBerryCallback {
    private static final String TAG = "HuyaBerryCallback";
    private static volatile HuyaBerryCallback mInstance;
    private HuyaBerry.BerryEvent mBerryEventCallback;

    private HuyaBerryCallback() {
    }

    public static HuyaBerryCallback getInstance() {
        if (mInstance == null) {
            synchronized (HuyaBerryCallback.class) {
                if (mInstance == null) {
                    mInstance = new HuyaBerryCallback();
                }
            }
        }
        return mInstance;
    }

    public void setBerryEventListener(HuyaBerry.BerryEvent berryEvent) {
        this.mBerryEventCallback = berryEvent;
    }

    public void callbackInit(boolean z, String str) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackInit:[" + z + "],[" + str + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, "init");
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        map.put("msg", str);
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackStartUp() {
        if (this.mBerryEventCallback == null || TextUtils.isEmpty(SdkProperties.gameAccountID.get())) {
            return;
        }
        L.info(TAG, "callbackStartUp");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_STARTUP);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_UID, String.valueOf(LoginProperties.uid.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_GAMEACCOUNTID, SdkProperties.gameAccountID.get());
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_STARTUPTIME, String.valueOf(System.currentTimeMillis()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ROOMID, String.valueOf(UserProperties.roomId.get()));
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackStartLive() {
        if (this.mBerryEventCallback == null || TextUtils.isEmpty(SdkProperties.gameAccountID.get())) {
            return;
        }
        L.info(TAG, "callbackStartLive");
        SdkProperties.startLiveTime.set(Long.valueOf(System.currentTimeMillis()));
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_STARTLIVE);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_UID, String.valueOf(LoginProperties.uid.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_GAMEACCOUNTID, SdkProperties.gameAccountID.get());
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_STARTLIVETIME, String.valueOf(SdkProperties.startLiveTime.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ROOMID, String.valueOf(UserProperties.roomId.get()));
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackEndLive(boolean z, int i, String str) {
        if (this.mBerryEventCallback == null || TextUtils.isEmpty(SdkProperties.gameAccountID.get())) {
            return;
        }
        L.info(TAG, "callbackEndLive:[" + z + "],[" + i + "],[" + str + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, "endLive");
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_UID, String.valueOf(LoginProperties.uid.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_GAMEACCOUNTID, SdkProperties.gameAccountID.get());
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ENDLIVETIME, String.valueOf(System.currentTimeMillis()));
        map.put("duration", str);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ROOMID, String.valueOf(UserProperties.roomId.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ISLAST, String.valueOf(z));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_LIVEID, String.valueOf(SdkProperties.liveId.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ATTENDANCECOUNT, String.valueOf(i));
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackSendPlayerData(boolean z, String str) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackSendPlayerData:[" + z + "],[" + str + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_SENDPLAYERDATA);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        map.put("msg", str);
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackReceiveDanmu(boolean z, String str, String str2) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackReceiveDanmu:[" + z + "],[" + str + "],[" + str2 + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_RECEIVEDANMU);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_NICKNAME, str);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_DANMUCONTENT, str2);
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackExitFullScreen(boolean z) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackExitFullScreen:[" + z + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_EXITFULLSCREEN);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackFullScreen(boolean z) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackFullScreen:[" + z + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_FULLSCREEN);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackCloseLiveList(boolean z) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackFullScreen:[" + z + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_CLOSELIVELIST);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackReStartLive() {
        if (this.mBerryEventCallback == null || TextUtils.isEmpty(SdkProperties.gameAccountID.get())) {
            return;
        }
        L.info(TAG, "callbackStartLive");
        SdkProperties.startLiveTime.set(Long.valueOf(System.currentTimeMillis()));
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_RESTARTLIVE);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_UID, String.valueOf(LoginProperties.uid.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_GAMEACCOUNTID, SdkProperties.gameAccountID.get());
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_STARTLIVETIME, String.valueOf(SdkProperties.startLiveTime.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_ROOMID, String.valueOf(UserProperties.roomId.get()));
        this.mBerryEventCallback.onEventCallback(map);
    }

    public void callbackShowFloating(boolean z) {
        if (this.mBerryEventCallback == null) {
            return;
        }
        L.info(TAG, "callbackFullScreen:[" + z + "]");
        HashMap map = new HashMap();
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_SHOWFLOATING);
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE, z ? "0" : "-1");
        this.mBerryEventCallback.onEventCallback(map);
    }
}
