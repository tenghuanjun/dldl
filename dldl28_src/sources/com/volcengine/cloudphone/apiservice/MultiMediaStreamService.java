package com.volcengine.cloudphone.apiservice;

import android.view.MotionEvent;
import com.volcengine.androidcloud.common.model.StreamStats;
import com.volcengine.cloudcore.common.mode.StreamType;
import com.volcengine.cloudphone.base.VeAudioFrame;
import com.volcengine.cloudphone.base.VeDisplay;

/* JADX INFO: loaded from: classes3.dex */
public interface MultiMediaStreamService {
    public static final int BAR_STATUS_CLOSE = 0;
    public static final int BAR_STATUS_OPEN = 1;

    public interface AppStateListener {
        void onAppDisplayIdChanged(String str, String str2, String str3);
    }

    public interface AudioZoneListener {
        void onAudioZoneChanged(int i, String str);

        void onGetResult(int i, int i2, String str, String str2);

        void onSetResult(int i, int i2, String str, String str2);
    }

    public interface MultiAudioFocusAppListener {
        void onAudioFocusAppChanged(int i, String str, String str2, int i2);

        void onResult(int i, int i2, String str, String str2, String str3);
    }

    public interface MultiFocusedWindowAppListener {
        void onFocusedWindowAppChanged(String str, String str2);

        void onResult(String str, int i, String str2, String str3);
    }

    public interface MultiScreenStateListener {
        void onScreenStateChanged(String str, ScreenState screenState);
    }

    public interface MultiStreamListener {

        /* JADX INFO: renamed from: com.volcengine.cloudphone.apiservice.MultiMediaStreamService$MultiStreamListener$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: D8$$SyntheticClass */
        public final /* synthetic */ class CC {
            public static void $default$onFirstAudioFrame(MultiStreamListener _this, String str) {
            }

            public static void $default$onFirstRemoteVideoFrame(MultiStreamListener _this, String str) {
            }

            public static void $default$onNetworkQuality(MultiStreamListener _this, String str, int i) {
            }

            public static void $default$onReceivedRemoteAudioFrame(MultiStreamListener _this, String str, VeAudioFrame veAudioFrame) {
            }

            public static void $default$onRotate(MultiStreamListener _this, String str, int i) {
            }

            public static void $default$onStreamProfileChanged(MultiStreamListener _this, String str, int i, int i2) {
            }

            public static void $default$onStreamStats(MultiStreamListener _this, String str, StreamStats streamStats) {
            }
        }

        void onFirstAudioFrame(String str);

        void onFirstRemoteVideoFrame(String str);

        void onNetworkQuality(String str, int i);

        void onPlaySuccess(String str, int i, String str2);

        void onReceivedRemoteAudioFrame(String str, VeAudioFrame veAudioFrame);

        void onRotate(String str, int i);

        void onStreamError(int i, String str);

        void onStreamProfileChanged(String str, int i, int i2);

        void onStreamStats(String str, StreamStats streamStats);
    }

    public static class ScreenState {
        public final String appPackageName;
        public final int navigationBarStatus;
        public final int statusBarStatus;

        public ScreenState(String str, int i, int i2) {
            this.appPackageName = str;
            this.navigationBarStatus = i;
            this.statusBarStatus = i2;
        }

        public String toString() {
            return "ScreenState{appPackageName='" + this.appPackageName + "', navigationBarStatus=" + this.navigationBarStatus + ", statusBarStatus=" + this.statusBarStatus + '}';
        }
    }

    int closeApp(String str, String str2);

    void enableExternalAudioPlayback(boolean z);

    int getAudioFocusApp(int i);

    int getFocusedWindowApp(String str);

    int getStreamForAudioZone(int i);

    boolean isAudioMuted(String str);

    boolean isInterceptTouchEvent(String str);

    boolean isMainScreen(String str);

    boolean isVideoMuted(String str);

    int launchApp(String str, String str2);

    void muteAudio(String str, boolean z);

    void muteVideo(String str, boolean z);

    void pauseStream(String str);

    void resumeStream(String str);

    int rotate(String str, int i);

    int sendKeyEvent(String str, int i);

    int sendKeyEvent(String str, int i, int i2);

    int sendMotionEvent(String str, int i, float f, float f2);

    int sendMotionEvent(String str, MotionEvent motionEvent);

    void setAppStateListener(AppStateListener appStateListener);

    void setAudioZoneListener(AudioZoneListener audioZoneListener);

    void setInterceptTouchEvent(String str, boolean z);

    void setMultiAudioFocusAppListener(MultiAudioFocusAppListener multiAudioFocusAppListener);

    void setMultiAudioPlaybackStatus(boolean z);

    void setMultiFocusedWindowAppListener(MultiFocusedWindowAppListener multiFocusedWindowAppListener);

    void setMultiScreenStateListener(MultiScreenStateListener multiScreenStateListener);

    void setMultiStreamListener(MultiStreamListener multiStreamListener);

    int setStreamForAudioZone(int i, String str);

    void subscribeStream(String str, VeDisplay veDisplay);

    void subscribeStream(String str, VeDisplay veDisplay, StreamType streamType);

    int switchVideoStreamProfileId(String str, int i);

    void unsubscribeStream(String str);

    void unsubscribeStream(String str, StreamType streamType);
}
