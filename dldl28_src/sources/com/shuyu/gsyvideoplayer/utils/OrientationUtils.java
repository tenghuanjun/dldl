package com.shuyu.gsyvideoplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.OrientationEventListener;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class OrientationUtils {
    private static final int LAND_TYPE_NORMAL = 1;
    private static final int LAND_TYPE_NULL = 0;
    private static final int LAND_TYPE_REVERSE = 2;
    private WeakReference<Activity> mActivity;
    private boolean mClick;
    private boolean mClickLand;
    private boolean mClickPort;
    private boolean mEnable;
    private int mIsLand;
    private boolean mIsOnlyRotateLand;
    private boolean mIsPause;
    private OrientationEventListener mOrientationEventListener;
    private OrientationOption mOrientationOption;
    private boolean mRotateWithSystem;
    private int mScreenType;
    private GSYBaseVideoPlayer mVideoPlayer;

    public OrientationUtils(Activity activity, GSYBaseVideoPlayer gSYBaseVideoPlayer) {
        this(activity, gSYBaseVideoPlayer, null);
    }

    public OrientationUtils(Activity activity, GSYBaseVideoPlayer gSYBaseVideoPlayer, OrientationOption orientationOption) {
        this.mScreenType = 1;
        this.mIsLand = 0;
        this.mClick = false;
        this.mClickLand = false;
        this.mEnable = true;
        this.mRotateWithSystem = true;
        this.mIsPause = false;
        this.mIsOnlyRotateLand = false;
        this.mActivity = new WeakReference<>(activity);
        this.mVideoPlayer = gSYBaseVideoPlayer;
        if (orientationOption == null) {
            this.mOrientationOption = new OrientationOption();
        } else {
            this.mOrientationOption = orientationOption;
        }
        initGravity(activity);
        init();
    }

    protected void init() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        final Context applicationContext = activity.getApplicationContext();
        OrientationEventListener orientationEventListener = new OrientationEventListener(applicationContext) { // from class: com.shuyu.gsyvideoplayer.utils.OrientationUtils.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                if (Settings.System.getInt(applicationContext.getContentResolver(), "accelerometer_rotation", 0) != 1 && OrientationUtils.this.mRotateWithSystem && (!OrientationUtils.this.mIsOnlyRotateLand || OrientationUtils.this.getIsLand() == 0)) {
                    return;
                }
                if ((OrientationUtils.this.mVideoPlayer == null || !OrientationUtils.this.mVideoPlayer.isVerticalFullByVideoSize()) && !OrientationUtils.this.mIsPause) {
                    if ((i < 0 || i > OrientationUtils.this.mOrientationOption.getNormalPortraitAngleStart()) && i < OrientationUtils.this.mOrientationOption.getNormalPortraitAngleEnd()) {
                        if (i < OrientationUtils.this.mOrientationOption.getNormalLandAngleStart() || i > OrientationUtils.this.mOrientationOption.getNormalLandAngleEnd()) {
                            if (i <= OrientationUtils.this.mOrientationOption.getReverseLandAngleStart() || i >= OrientationUtils.this.mOrientationOption.getReverseLandAngleEnd()) {
                                return;
                            }
                            if (OrientationUtils.this.mClick) {
                                if (OrientationUtils.this.mIsLand == 2 || OrientationUtils.this.mClickPort) {
                                    OrientationUtils.this.mClickLand = true;
                                    OrientationUtils.this.mClick = false;
                                    OrientationUtils.this.mIsLand = 2;
                                    return;
                                }
                                return;
                            }
                            if (OrientationUtils.this.mIsLand != 2) {
                                OrientationUtils.this.mScreenType = 0;
                                OrientationUtils.this.setRequestedOrientation(8);
                                if (OrientationUtils.this.mVideoPlayer.getFullscreenButton() != null) {
                                    OrientationUtils.this.mVideoPlayer.getFullscreenButton().setImageResource(OrientationUtils.this.mVideoPlayer.getShrinkImageRes());
                                }
                                OrientationUtils.this.mIsLand = 2;
                                OrientationUtils.this.mClick = false;
                                return;
                            }
                            return;
                        }
                        if (OrientationUtils.this.mClick) {
                            if (OrientationUtils.this.mIsLand == 1 || OrientationUtils.this.mClickPort) {
                                OrientationUtils.this.mClickLand = true;
                                OrientationUtils.this.mClick = false;
                                OrientationUtils.this.mIsLand = 1;
                                return;
                            }
                            return;
                        }
                        if (OrientationUtils.this.mIsLand != 1) {
                            OrientationUtils.this.mScreenType = 0;
                            OrientationUtils.this.setRequestedOrientation(0);
                            if (OrientationUtils.this.mVideoPlayer.getFullscreenButton() != null) {
                                OrientationUtils.this.mVideoPlayer.getFullscreenButton().setImageResource(OrientationUtils.this.mVideoPlayer.getShrinkImageRes());
                            }
                            OrientationUtils.this.mIsLand = 1;
                            OrientationUtils.this.mClick = false;
                            return;
                        }
                        return;
                    }
                    if (OrientationUtils.this.mClick) {
                        if (OrientationUtils.this.mIsLand <= 0 || OrientationUtils.this.mClickLand) {
                            OrientationUtils.this.mClickPort = true;
                            OrientationUtils.this.mClick = false;
                            OrientationUtils.this.mIsLand = 0;
                            return;
                        }
                        return;
                    }
                    if (OrientationUtils.this.mIsLand > 0) {
                        if (!OrientationUtils.this.mIsOnlyRotateLand) {
                            OrientationUtils.this.mScreenType = 1;
                            OrientationUtils.this.setRequestedOrientation(1);
                            if (OrientationUtils.this.mVideoPlayer.getFullscreenButton() != null) {
                                if (OrientationUtils.this.mVideoPlayer.isIfCurrentIsFullscreen()) {
                                    OrientationUtils.this.mVideoPlayer.getFullscreenButton().setImageResource(OrientationUtils.this.mVideoPlayer.getShrinkImageRes());
                                } else {
                                    OrientationUtils.this.mVideoPlayer.getFullscreenButton().setImageResource(OrientationUtils.this.mVideoPlayer.getEnlargeImageRes());
                                }
                            }
                            OrientationUtils.this.mIsLand = 0;
                        }
                        OrientationUtils.this.mClick = false;
                    }
                }
            }
        };
        this.mOrientationEventListener = orientationEventListener;
        orientationEventListener.enable();
    }

    private void initGravity(Activity activity) {
        if (this.mIsLand == 0) {
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            if (rotation == 0) {
                this.mIsLand = 0;
                this.mScreenType = 1;
            } else if (rotation == 3) {
                this.mIsLand = 2;
                this.mScreenType = 8;
            } else {
                this.mIsLand = 1;
                this.mScreenType = 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestedOrientation(int i) {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        try {
            activity.setRequestedOrientation(i);
        } catch (IllegalStateException e) {
            if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
                Debuger.printfError("OrientationUtils", e);
            } else {
                e.printStackTrace();
            }
        }
    }

    public void resolveByClick() {
        GSYBaseVideoPlayer gSYBaseVideoPlayer;
        if (this.mIsLand == 0 && (gSYBaseVideoPlayer = this.mVideoPlayer) != null && gSYBaseVideoPlayer.isVerticalFullByVideoSize()) {
            return;
        }
        this.mClick = true;
        Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        if (this.mIsLand == 0) {
            if (activity.getRequestedOrientation() == 8) {
                this.mScreenType = 8;
            } else {
                this.mScreenType = 0;
            }
            setRequestedOrientation(this.mScreenType);
            if (this.mVideoPlayer.getFullscreenButton() != null) {
                this.mVideoPlayer.getFullscreenButton().setImageResource(this.mVideoPlayer.getShrinkImageRes());
            }
            this.mIsLand = 1;
            this.mClickLand = false;
            return;
        }
        this.mScreenType = 1;
        setRequestedOrientation(1);
        if (this.mVideoPlayer.getFullscreenButton() != null) {
            if (this.mVideoPlayer.isIfCurrentIsFullscreen()) {
                this.mVideoPlayer.getFullscreenButton().setImageResource(this.mVideoPlayer.getShrinkImageRes());
            } else {
                this.mVideoPlayer.getFullscreenButton().setImageResource(this.mVideoPlayer.getEnlargeImageRes());
            }
        }
        this.mIsLand = 0;
        this.mClickPort = false;
    }

    public int backToProtVideo() {
        if (this.mIsLand <= 0) {
            return 0;
        }
        this.mClick = true;
        setRequestedOrientation(1);
        GSYBaseVideoPlayer gSYBaseVideoPlayer = this.mVideoPlayer;
        if (gSYBaseVideoPlayer != null && gSYBaseVideoPlayer.getFullscreenButton() != null) {
            this.mVideoPlayer.getFullscreenButton().setImageResource(this.mVideoPlayer.getEnlargeImageRes());
        }
        this.mIsLand = 0;
        this.mClickPort = false;
        return 500;
    }

    public boolean isEnable() {
        return this.mEnable;
    }

    public void setEnable(boolean z) {
        this.mEnable = z;
        if (z) {
            this.mOrientationEventListener.enable();
        } else {
            this.mOrientationEventListener.disable();
        }
    }

    public void releaseListener() {
        OrientationEventListener orientationEventListener = this.mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    public boolean isClick() {
        return this.mClick;
    }

    public void setClick(boolean z) {
        this.mClick = z;
    }

    public boolean isClickLand() {
        return this.mClickLand;
    }

    public void setClickLand(boolean z) {
        this.mClickLand = z;
    }

    public int getIsLand() {
        return this.mIsLand;
    }

    public void setIsLand(int i) {
        this.mIsLand = i;
    }

    public boolean isClickPort() {
        return this.mClickPort;
    }

    public void setClickPort(boolean z) {
        this.mClickPort = z;
    }

    public int getScreenType() {
        return this.mScreenType;
    }

    public void setScreenType(int i) {
        this.mScreenType = i;
    }

    public boolean isRotateWithSystem() {
        return this.mRotateWithSystem;
    }

    public boolean isOnlyRotateLand() {
        return this.mIsOnlyRotateLand;
    }

    public void setOnlyRotateLand(boolean z) {
        this.mIsOnlyRotateLand = z;
    }

    public void setRotateWithSystem(boolean z) {
        this.mRotateWithSystem = z;
    }

    public boolean isPause() {
        return this.mIsPause;
    }

    public void setIsPause(boolean z) {
        this.mIsPause = z;
    }

    public OrientationOption getOrientationOption() {
        return this.mOrientationOption;
    }

    public void setOrientationOption(OrientationOption orientationOption) {
        this.mOrientationOption = orientationOption;
    }
}
