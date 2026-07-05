package com.huya.berry.sdkplayer.floats;

import com.duowan.HUYA.ScreenType;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.sdkplayer.floats.listener.OrientationListener;
import com.huya.berry.sdkplayer.floats.utils.FloatingPreferences;
import com.huya.berry.sdkplayer.floats.utils.ScreenObserver;
import com.huya.berry.sdkplayer.floats.view.FloatingWindowMgr;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FloatingVideoMgr implements ScreenObserver.ScreenStateListener {
    private static final String TAG = FloatingVideoMgr.class.getSimpleName();
    private static FloatingVideoMgr mFloatingVideoMgr = null;
    private ScreenType mScreenType;
    private Status mFloatingStatus = Status.INVALID;
    private boolean mScreenOff = false;
    private ScreenObserver mScreenObserver = new ScreenObserver(ArkValue.gContext);

    private enum Status {
        INVALID,
        PRE_START,
        STARTED,
        STOPPED,
        ONLY_VOICE
    }

    @Override // com.huya.berry.sdkplayer.floats.utils.ScreenObserver.ScreenStateListener
    public void onScreenOff() {
    }

    public static FloatingVideoMgr getInstance() {
        if (mFloatingVideoMgr == null) {
            mFloatingVideoMgr = new FloatingVideoMgr();
        }
        return mFloatingVideoMgr;
    }

    private FloatingVideoMgr() {
    }

    public synchronized void destroy() {
        L.info(TAG, "enter destroy, 1");
        FloatingWindowMgr.stopVideo();
        ArkUtils.unregister(this);
        OrientationListener.getInstance().disable();
        this.mScreenObserver.shutdownObserver();
        FloatingWindowMgr.destroy();
        setFloatingStatus(Status.STOPPED);
    }

    public boolean isShown() {
        return FloatingWindowMgr.isShown();
    }

    private boolean isFloatingPrepare() {
        return this.mFloatingStatus == Status.PRE_START;
    }

    private boolean isFloatingStart() {
        return this.mFloatingStatus == Status.STARTED;
    }

    public boolean inFloating() {
        L.info(TAG, "mFloatingStatus = " + this.mFloatingStatus);
        return isFloatingPrepare() || isFloatingStart() || isShown();
    }

    private void setFloatingStatus(Status status) {
        this.mFloatingStatus = status;
    }

    public synchronized void start(ScreenType screenType) {
        if (screenType == null) {
            return;
        }
        this.mScreenType = screenType;
        L.info(TAG, "enter start %s", screenType);
        ArkUtils.register(this);
        OrientationListener.getInstance().enable();
        FloatingWindowMgr.initWindow(ArkValue.gContext, this.mScreenType);
        FloatingWindowMgr.initFloating(this.mScreenType, true);
        this.mScreenObserver.startObserver(this);
        setFloatingStatus(Status.STARTED);
        FloatingWindowMgr.showFloatingVideo(true);
        ArkUtils.send(new CommonEvent.ShowFloating());
    }

    public void createPlayer() {
        FloatingWindowMgr.createPlayer();
    }

    public void fullScreen(boolean z) {
        FloatingWindowMgr.fullScreen(z);
    }

    public void onVideoSizeChanged(int i, int i2) {
        L.info(TAG, "onVideoSizeChanged, width=%d, height=%d", Integer.valueOf(i), Integer.valueOf(i2));
        if (isShown()) {
            FloatingWindowMgr.onVideoSizeChanged(i, i2);
        }
    }

    public void addView() {
        FloatingWindowMgr.addView();
    }

    public void removeView() {
        FloatingWindowMgr.removeView();
    }

    @Override // com.huya.berry.sdkplayer.floats.utils.ScreenObserver.ScreenStateListener
    public void onScreenOn() {
        L.info(TAG, "enter onScreenOn");
        showFloatingIfScreenOnOrUserPresent(this.mScreenOff);
        this.mScreenOff = false;
    }

    @Override // com.huya.berry.sdkplayer.floats.utils.ScreenObserver.ScreenStateListener
    public void onUserPresent() {
        L.info(TAG, "enter onUserPresent");
        showFloatingIfScreenOnOrUserPresent(this.mScreenOff);
        this.mScreenOff = false;
    }

    public void showFloatingIfScreenOnOrUserPresent(boolean z) {
        boolean zIsFloatingShowOtherApp = FloatingPreferences.isFloatingShowOtherApp();
        if (z && zIsFloatingShowOtherApp) {
            FloatingWindowMgr.showFloatingVideo(true);
        }
    }
}
