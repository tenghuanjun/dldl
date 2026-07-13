package com.shuyu.gsyvideoplayer;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class GSYVideoADManager extends GSYVideoBaseManager {
    private static GSYVideoADManager videoManager;
    public static final int SMALL_ID = R.id.ad_small_id;
    public static final int FULLSCREEN_ID = R.id.ad_full_id;
    public static String TAG = "GSYVideoADManager";

    private GSYVideoADManager() {
        init();
    }

    public static synchronized GSYVideoADManager instance() {
        if (videoManager == null) {
            videoManager = new GSYVideoADManager();
        }
        return videoManager;
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
        if (GSYVideoManager.instance().listener() != null) {
            GSYVideoManager.instance().listener().onVideoResume(z);
        }
    }

    public static boolean isFullState(Activity activity) {
        View viewFindViewById = ((ViewGroup) CommonUtil.scanForActivity(activity).findViewById(android.R.id.content)).findViewById(FULLSCREEN_ID);
        return (viewFindViewById != null ? (GSYVideoPlayer) viewFindViewById : null) != null;
    }
}
