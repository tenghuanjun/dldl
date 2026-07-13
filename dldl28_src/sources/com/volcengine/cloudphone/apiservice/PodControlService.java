package com.volcengine.cloudphone.apiservice;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface PodControlService {
    public static final int NAV_BAR_STATUS_HIDDEN = 0;
    public static final int NAV_BAR_STATUS_SHOW = 1;

    public interface BackgroundSwitchListener {
        void onBackgroundSwitched(boolean z);
    }

    public interface FocusedWindowAppListener {
        void onFocusedWindowAppChanged(String str);

        void onResult(int i, String str, String str2);
    }

    public interface GetAutoRecycleTimeCallback {
        void onResult(int i, long j);
    }

    public interface GetUserProfilePathListener {
        void onResult(List<String> list);
    }

    public @interface NavBarStatus {
    }

    public interface NavBarStatusChangeListener {
        void onNavBarStatus(int i, int i2);
    }

    public interface ScreenRecordListener {
        void onRecordingStatus(int i, String str, String str2, String str3);
    }

    public interface ScreenShotListener {
        void onScreenShot(int i, String str, String str2, String str3);
    }

    public interface SetAutoRecycleTimeCallback {
        void onResult(int i, long j);
    }

    public interface UserProfilePathListener {
        void onResult(boolean z, int i);
    }

    int getAutoRecycleTime(GetAutoRecycleTimeCallback getAutoRecycleTimeCallback);

    int getFocusedWindowApp();

    int getNavBarStatus();

    int getUserProfilePath(GetUserProfilePathListener getUserProfilePathListener);

    int screenShot();

    int screenShot(boolean z);

    int setAutoRecycleTime(int i, SetAutoRecycleTimeCallback setAutoRecycleTimeCallback);

    void setBackgroundSwitchListener(BackgroundSwitchListener backgroundSwitchListener);

    void setFocusedWindowAppListener(FocusedWindowAppListener focusedWindowAppListener);

    int setIdleTime(long j);

    int setNavBarStatus(int i);

    void setNavBarStatusChangeListener(NavBarStatusChangeListener navBarStatusChangeListener);

    void setScreenRecordListener(ScreenRecordListener screenRecordListener);

    void setScreenShotListener(ScreenShotListener screenShotListener);

    int setUserProfilePath(List<String> list);

    void setUserProfilePathListener(UserProfilePathListener userProfilePathListener);

    int startRecording(int i);

    int startRecording(int i, boolean z);

    int stopRecording();

    int switchBackground(boolean z);
}
