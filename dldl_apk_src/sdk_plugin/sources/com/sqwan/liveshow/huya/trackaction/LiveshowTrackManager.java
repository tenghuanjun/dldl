package com.sqwan.liveshow.huya.trackaction;

import android.os.SystemClock;
import com.sqwan.common.mod.liveshow.IAudioLiveshowTrackManager;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.liveshow.huya.trackaction.bean.BuiltinHalfOffTrackBean;
import com.sqwan.liveshow.huya.trackaction.bean.BuiltinHalfOnTrackBean;
import com.sqwan.liveshow.huya.trackaction.bean.BuiltinLiveListeningOffTrackBean;
import com.sqwan.liveshow.huya.trackaction.bean.BuiltinLiveListeningOnTrackBean;
import com.sqwan.liveshow.huya.trackaction.bean.BulletChatTrackBean;
import com.sqwan.liveshow.huya.trackaction.bean.LiveOffActtion;
import com.sqwan.liveshow.huya.trackaction.bean.LiveWatchAction;
import com.sqwan.liveshow.trackaction.LiveshowTrackBaseManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowTrackManager extends LiveshowTrackBaseManager implements IAudioLiveshowTrackManager {
    private long mFullScreenOnTime;
    private long mHalfScreenOnTime;
    private long mListeningOnTime;

    public static LiveshowTrackManager getInstance() {
        return (LiveshowTrackManager) LiveshowEngine.getInstance().getLiveshowTrackManager();
    }

    public void liveIconAction() {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_ICON);
    }

    public void trackFullScreenOnAction() {
        if (this.mFullScreenOnTime != 0) {
            return;
        }
        this.mFullScreenOnTime = SystemClock.uptimeMillis();
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_WATCH, new LiveWatchAction().toMap());
    }

    public void trackFullScreenOffAction() {
        if (this.mFullScreenOnTime == 0) {
            return;
        }
        long jUptimeMillis = (SystemClock.uptimeMillis() - this.mFullScreenOnTime) / 1000;
        this.mFullScreenOnTime = 0L;
        LiveOffActtion liveOffActtion = new LiveOffActtion();
        liveOffActtion.watch_time = String.valueOf(jUptimeMillis);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_OFF, liveOffActtion.toMap());
    }

    public void trackHalfScreenOnAction() {
        if (this.mHalfScreenOnTime != 0) {
            return;
        }
        this.mHalfScreenOnTime = SystemClock.uptimeMillis();
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_HALF_WATCH, new BuiltinHalfOnTrackBean().toMap());
    }

    public void trackHalfScreenOffAction() {
        if (this.mHalfScreenOnTime == 0) {
            return;
        }
        long jUptimeMillis = (SystemClock.uptimeMillis() - this.mHalfScreenOnTime) / 1000;
        this.mHalfScreenOnTime = 0L;
        BuiltinHalfOffTrackBean builtinHalfOffTrackBean = new BuiltinHalfOffTrackBean();
        builtinHalfOffTrackBean.watch_time = String.valueOf(jUptimeMillis);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_HALF_OFF, builtinHalfOffTrackBean.toMap());
    }

    public void bulletChatAction(String str, String str2, int i, boolean z) {
        BulletChatTrackBean bulletChatTrackBean = new BulletChatTrackBean();
        bulletChatTrackBean.content = str;
        bulletChatTrackBean.forbidden_or_not = str2;
        bulletChatTrackBean.state = i + "";
        if (z) {
            bulletChatTrackBean.speaking_position = "1";
        } else {
            bulletChatTrackBean.speaking_position = "2";
        }
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BULLET_CHAT, bulletChatTrackBean.toMap());
    }

    public void trackListeningOnAction() {
        if (this.mListeningOnTime != 0) {
            return;
        }
        this.mListeningOnTime = SystemClock.uptimeMillis();
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_LISTENING, new BuiltinLiveListeningOnTrackBean().toMap());
    }

    public void trackListeningOffAction() {
        if (this.mListeningOnTime == 0) {
            return;
        }
        long jUptimeMillis = (SystemClock.uptimeMillis() - this.mListeningOnTime) / 1000;
        this.mListeningOnTime = 0L;
        BuiltinLiveListeningOffTrackBean builtinLiveListeningOffTrackBean = new BuiltinLiveListeningOffTrackBean();
        builtinLiveListeningOffTrackBean.listen_time = String.valueOf(jUptimeMillis);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.BUILTIN_LIVE_LISTENING_OFF, builtinLiveListeningOffTrackBean.toMap());
    }
}
