package com.shuyu.gsyvideoplayer;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class GSYVideoManager extends GSYVideoBaseManager {
    private static GSYVideoManager videoManager;
    public static final int SMALL_ID = R.id.small_id;
    public static final int FULLSCREEN_ID = R.id.full_id;
    public static String TAG = "GSYVideoManager";

    private GSYVideoManager() {
        init();
    }

    public static synchronized GSYVideoManager instance() {
        if (videoManager == null) {
            videoManager = new GSYVideoManager();
        }
        return videoManager;
    }

    public static synchronized GSYVideoManager tmpInstance(GSYMediaPlayerListener gSYMediaPlayerListener) {
        GSYVideoManager gSYVideoManager;
        gSYVideoManager = new GSYVideoManager();
        gSYVideoManager.bufferPoint = videoManager.bufferPoint;
        gSYVideoManager.optionModelList = videoManager.optionModelList;
        gSYVideoManager.playTag = videoManager.playTag;
        gSYVideoManager.currentVideoWidth = videoManager.currentVideoWidth;
        gSYVideoManager.currentVideoHeight = videoManager.currentVideoHeight;
        gSYVideoManager.context = videoManager.context;
        gSYVideoManager.lastState = videoManager.lastState;
        gSYVideoManager.playPosition = videoManager.playPosition;
        gSYVideoManager.timeOut = videoManager.timeOut;
        gSYVideoManager.needMute = videoManager.needMute;
        gSYVideoManager.needTimeOutOther = videoManager.needTimeOutOther;
        gSYVideoManager.setListener(gSYMediaPlayerListener);
        return gSYVideoManager;
    }

    public static synchronized void changeManager(GSYVideoManager gSYVideoManager) {
        videoManager = gSYVideoManager;
    }

    public static boolean backFromWindowFull(Context context) {
        if (((ViewGroup) CommonUtil.scanForActivity(context).findViewById(android.R.id.content)).findViewById(FULLSCREEN_ID) == null) {
            return false;
        }
        CommonUtil.hideNavKey(context);
        if (instance().lastListener() != null) {
            instance().lastListener().onBackFullscreen();
        }
        return true;
    }

    public static void releaseAllVideos() {
        if (instance().listener() != null) {
            instance().listener().onCompletion();
        }
        instance().releaseMediaPlayer();
    }

    public static void onPause() {
        if (instance().listener() != null) {
            instance().listener().onVideoPause();
        }
    }

    public static void onResume() {
        if (instance().listener() != null) {
            instance().listener().onVideoResume();
        }
    }

    public static void onResume(boolean z) {
        if (instance().listener() != null) {
            instance().listener().onVideoResume(z);
        }
    }

    public static boolean isFullState(Activity activity) {
        View viewFindViewById = ((ViewGroup) CommonUtil.scanForActivity(activity).findViewById(android.R.id.content)).findViewById(FULLSCREEN_ID);
        return (viewFindViewById != null ? (GSYVideoPlayer) viewFindViewById : null) != null;
    }
}
