package com.shuyu.gsyvideoplayer.video;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.badge.BadgeDrawable;
import com.shuyu.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import java.io.File;
import moe.codeest.enviews.ENDownloadView;
import moe.codeest.enviews.ENPlayView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class StandardGSYVideoPlayer extends GSYVideoPlayer {
    protected Drawable mBottomProgressDrawable;
    protected Drawable mBottomShowProgressDrawable;
    protected Drawable mBottomShowProgressThumbDrawable;
    protected Dialog mBrightnessDialog;
    protected TextView mBrightnessDialogTv;
    protected ImageView mDialogIcon;
    protected ProgressBar mDialogProgressBar;
    protected Drawable mDialogProgressBarDrawable;
    protected int mDialogProgressHighLightColor;
    protected int mDialogProgressNormalColor;
    protected TextView mDialogSeekTime;
    protected TextView mDialogTotalTime;
    protected ProgressBar mDialogVolumeProgressBar;
    protected Dialog mProgressDialog;
    protected Dialog mVolumeDialog;
    protected Drawable mVolumeProgressDrawable;

    public StandardGSYVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
        this.mDialogProgressHighLightColor = -11;
        this.mDialogProgressNormalColor = -11;
    }

    public StandardGSYVideoPlayer(Context context) {
        super(context);
        this.mDialogProgressHighLightColor = -11;
        this.mDialogProgressNormalColor = -11;
    }

    public StandardGSYVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDialogProgressHighLightColor = -11;
        this.mDialogProgressNormalColor = -11;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void init(Context context) {
        super.init(context);
        if (this.mBottomProgressDrawable != null) {
            this.mBottomProgressBar.setProgressDrawable(this.mBottomProgressDrawable);
        }
        if (this.mBottomShowProgressDrawable != null) {
            this.mProgressBar.setProgressDrawable(this.mBottomProgressDrawable);
        }
        if (this.mBottomShowProgressThumbDrawable != null) {
            this.mProgressBar.setThumb(this.mBottomShowProgressThumbDrawable);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public int getLayoutId() {
        return R.layout.video_layout_standard;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public void startPlayLogic() {
        if (this.mVideoAllCallBack != null) {
            Debuger.printfLog("onClickStartThumb");
            this.mVideoAllCallBack.onClickStartThumb(this.mOriginUrl, this.mTitle, this);
        }
        prepareVideo();
        startDismissControlViewTimer();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void showWifiDialog() {
        if (!NetworkUtils.isAvailable(this.mContext)) {
            startPlayLogic();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivityContext());
        builder.setMessage(getResources().getString(R.string.tips_not_wifi));
        builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                StandardGSYVideoPlayer.this.startPlayLogic();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void showProgressDialog(float f, String str, long j, String str2, long j2) {
        ProgressBar progressBar;
        TextView textView;
        TextView textView2;
        if (this.mProgressDialog == null) {
            View viewInflate = LayoutInflater.from(getActivityContext()).inflate(getProgressDialogLayoutId(), (ViewGroup) null);
            if (viewInflate.findViewById(getProgressDialogProgressId()) instanceof ProgressBar) {
                ProgressBar progressBar2 = (ProgressBar) viewInflate.findViewById(getProgressDialogProgressId());
                this.mDialogProgressBar = progressBar2;
                Drawable drawable = this.mDialogProgressBarDrawable;
                if (drawable != null) {
                    progressBar2.setProgressDrawable(drawable);
                }
            }
            if (viewInflate.findViewById(getProgressDialogCurrentDurationTextId()) instanceof TextView) {
                this.mDialogSeekTime = (TextView) viewInflate.findViewById(getProgressDialogCurrentDurationTextId());
            }
            if (viewInflate.findViewById(getProgressDialogAllDurationTextId()) instanceof TextView) {
                this.mDialogTotalTime = (TextView) viewInflate.findViewById(getProgressDialogAllDurationTextId());
            }
            if (viewInflate.findViewById(getProgressDialogImageId()) instanceof ImageView) {
                this.mDialogIcon = (ImageView) viewInflate.findViewById(getProgressDialogImageId());
            }
            Dialog dialog = new Dialog(getActivityContext(), R.style.video_style_dialog_progress);
            this.mProgressDialog = dialog;
            dialog.setContentView(viewInflate);
            this.mProgressDialog.getWindow().addFlags(8);
            this.mProgressDialog.getWindow().addFlags(32);
            this.mProgressDialog.getWindow().addFlags(16);
            this.mProgressDialog.getWindow().setLayout(getWidth(), getHeight());
            int i = this.mDialogProgressNormalColor;
            if (i != -11 && (textView2 = this.mDialogTotalTime) != null) {
                textView2.setTextColor(i);
            }
            int i2 = this.mDialogProgressHighLightColor;
            if (i2 != -11 && (textView = this.mDialogSeekTime) != null) {
                textView.setTextColor(i2);
            }
            WindowManager.LayoutParams attributes = this.mProgressDialog.getWindow().getAttributes();
            attributes.gravity = 48;
            attributes.width = getWidth();
            attributes.height = getHeight();
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            attributes.x = iArr[0];
            attributes.y = iArr[1];
            this.mProgressDialog.getWindow().setAttributes(attributes);
        }
        if (!this.mProgressDialog.isShowing()) {
            this.mProgressDialog.show();
        }
        TextView textView3 = this.mDialogSeekTime;
        if (textView3 != null) {
            textView3.setText(str);
        }
        TextView textView4 = this.mDialogTotalTime;
        if (textView4 != null) {
            textView4.setText(" / " + str2);
        }
        if (j2 > 0 && (progressBar = this.mDialogProgressBar) != null) {
            progressBar.setProgress((int) ((j * 100) / j2));
        }
        if (f > 0.0f) {
            ImageView imageView = this.mDialogIcon;
            if (imageView != null) {
                imageView.setBackgroundResource(R.drawable.video_forward_icon);
                return;
            }
            return;
        }
        ImageView imageView2 = this.mDialogIcon;
        if (imageView2 != null) {
            imageView2.setBackgroundResource(R.drawable.video_backward_icon);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void dismissProgressDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void showVolumeDialog(float f, int i) {
        if (this.mVolumeDialog == null) {
            View viewInflate = LayoutInflater.from(getActivityContext()).inflate(getVolumeLayoutId(), (ViewGroup) null);
            if (viewInflate.findViewById(getVolumeProgressId()) instanceof ProgressBar) {
                ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(getVolumeProgressId());
                this.mDialogVolumeProgressBar = progressBar;
                Drawable drawable = this.mVolumeProgressDrawable;
                if (drawable != null && progressBar != null) {
                    progressBar.setProgressDrawable(drawable);
                }
            }
            Dialog dialog = new Dialog(getActivityContext(), R.style.video_style_dialog_progress);
            this.mVolumeDialog = dialog;
            dialog.setContentView(viewInflate);
            this.mVolumeDialog.getWindow().addFlags(8);
            this.mVolumeDialog.getWindow().addFlags(32);
            this.mVolumeDialog.getWindow().addFlags(16);
            this.mVolumeDialog.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams attributes = this.mVolumeDialog.getWindow().getAttributes();
            attributes.gravity = BadgeDrawable.TOP_START;
            attributes.width = getWidth();
            attributes.height = getHeight();
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            attributes.x = iArr[0];
            attributes.y = iArr[1];
            this.mVolumeDialog.getWindow().setAttributes(attributes);
        }
        if (!this.mVolumeDialog.isShowing()) {
            this.mVolumeDialog.show();
        }
        ProgressBar progressBar2 = this.mDialogVolumeProgressBar;
        if (progressBar2 != null) {
            progressBar2.setProgress(i);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void dismissVolumeDialog() {
        Dialog dialog = this.mVolumeDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mVolumeDialog = null;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void showBrightnessDialog(float f) {
        if (this.mBrightnessDialog == null) {
            View viewInflate = LayoutInflater.from(getActivityContext()).inflate(getBrightnessLayoutId(), (ViewGroup) null);
            if (viewInflate.findViewById(getBrightnessTextId()) instanceof TextView) {
                this.mBrightnessDialogTv = (TextView) viewInflate.findViewById(getBrightnessTextId());
            }
            Dialog dialog = new Dialog(getActivityContext(), R.style.video_style_dialog_progress);
            this.mBrightnessDialog = dialog;
            dialog.setContentView(viewInflate);
            this.mBrightnessDialog.getWindow().addFlags(8);
            this.mBrightnessDialog.getWindow().addFlags(32);
            this.mBrightnessDialog.getWindow().addFlags(16);
            this.mBrightnessDialog.getWindow().getDecorView().setSystemUiVisibility(2);
            this.mBrightnessDialog.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams attributes = this.mBrightnessDialog.getWindow().getAttributes();
            attributes.gravity = BadgeDrawable.TOP_END;
            attributes.width = getWidth();
            attributes.height = getHeight();
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            attributes.x = iArr[0];
            attributes.y = iArr[1];
            this.mBrightnessDialog.getWindow().setAttributes(attributes);
        }
        if (!this.mBrightnessDialog.isShowing()) {
            this.mBrightnessDialog.show();
        }
        TextView textView = this.mBrightnessDialogTv;
        if (textView != null) {
            textView.setText(((int) (f * 100.0f)) + "%");
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void dismissBrightnessDialog() {
        Dialog dialog = this.mBrightnessDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mBrightnessDialog = null;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected void cloneParams(GSYBaseVideoPlayer gSYBaseVideoPlayer, GSYBaseVideoPlayer gSYBaseVideoPlayer2) {
        super.cloneParams(gSYBaseVideoPlayer, gSYBaseVideoPlayer2);
        StandardGSYVideoPlayer standardGSYVideoPlayer = (StandardGSYVideoPlayer) gSYBaseVideoPlayer;
        StandardGSYVideoPlayer standardGSYVideoPlayer2 = (StandardGSYVideoPlayer) gSYBaseVideoPlayer2;
        if (standardGSYVideoPlayer2.mProgressBar != null && standardGSYVideoPlayer.mProgressBar != null) {
            standardGSYVideoPlayer2.mProgressBar.setProgress(standardGSYVideoPlayer.mProgressBar.getProgress());
            standardGSYVideoPlayer2.mProgressBar.setSecondaryProgress(standardGSYVideoPlayer.mProgressBar.getSecondaryProgress());
        }
        if (standardGSYVideoPlayer2.mTotalTimeTextView != null && standardGSYVideoPlayer.mTotalTimeTextView != null) {
            standardGSYVideoPlayer2.mTotalTimeTextView.setText(standardGSYVideoPlayer.mTotalTimeTextView.getText());
        }
        if (standardGSYVideoPlayer2.mCurrentTimeTextView == null || standardGSYVideoPlayer.mCurrentTimeTextView == null) {
            return;
        }
        standardGSYVideoPlayer2.mCurrentTimeTextView.setText(standardGSYVideoPlayer.mCurrentTimeTextView.getText());
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean z, boolean z2) {
        GSYBaseVideoPlayer gSYBaseVideoPlayerStartWindowFullscreen = super.startWindowFullscreen(context, z, z2);
        if (gSYBaseVideoPlayerStartWindowFullscreen != null) {
            StandardGSYVideoPlayer standardGSYVideoPlayer = (StandardGSYVideoPlayer) gSYBaseVideoPlayerStartWindowFullscreen;
            standardGSYVideoPlayer.setLockClickListener(this.mLockClickListener);
            standardGSYVideoPlayer.setNeedLockFull(isNeedLockFull());
            initFullUI(standardGSYVideoPlayer);
        }
        return gSYBaseVideoPlayerStartWindowFullscreen;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void onClickUiToggle(MotionEvent motionEvent) {
        if (this.mIfCurrentIsFullscreen && this.mLockCurScreen && this.mNeedLockFull) {
            setViewShowState(this.mLockScreen, 0);
            return;
        }
        if (this.mIfCurrentIsFullscreen && !this.mSurfaceErrorPlay && this.mCurrentState == 7) {
            if (this.mBottomContainer != null) {
                if (this.mBottomContainer.getVisibility() == 0) {
                    changeUiToPlayingClear();
                    return;
                } else {
                    changeUiToPlayingShow();
                    return;
                }
            }
            return;
        }
        if (this.mCurrentState == 1) {
            if (this.mBottomContainer != null) {
                if (this.mBottomContainer.getVisibility() == 0) {
                    changeUiToPrepareingClear();
                    return;
                } else {
                    changeUiToPreparingShow();
                    return;
                }
            }
            return;
        }
        if (this.mCurrentState == 2) {
            if (this.mBottomContainer != null) {
                if (this.mBottomContainer.getVisibility() == 0) {
                    changeUiToPlayingClear();
                    return;
                } else {
                    changeUiToPlayingShow();
                    return;
                }
            }
            return;
        }
        if (this.mCurrentState == 5) {
            if (this.mBottomContainer != null) {
                if (this.mBottomContainer.getVisibility() == 0) {
                    changeUiToPauseClear();
                    return;
                } else {
                    changeUiToPauseShow();
                    return;
                }
            }
            return;
        }
        if (this.mCurrentState == 6) {
            if (this.mBottomContainer != null) {
                if (this.mBottomContainer.getVisibility() == 0) {
                    changeUiToCompleteClear();
                    return;
                } else {
                    changeUiToCompleteShow();
                    return;
                }
            }
            return;
        }
        if (this.mCurrentState != 3 || this.mBottomContainer == null) {
            return;
        }
        if (this.mBottomContainer.getVisibility() == 0) {
            changeUiToPlayingBufferingClear();
        } else {
            changeUiToPlayingBufferingShow();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void hideAllWidget() {
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomProgressBar, 0);
        setViewShowState(this.mStartButton, 4);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToNormal() {
        Debuger.printfLog("changeUiToNormal");
        setViewShowState(this.mTopContainer, 0);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 0);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 0);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, (this.mIfCurrentIsFullscreen && this.mNeedLockFull) ? 0 : 8);
        updateStartImage();
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToPreparingShow() {
        Debuger.printfLog("changeUiToPreparingShow");
        setViewShowState(this.mTopContainer, 0);
        setViewShowState(this.mBottomContainer, 0);
        setViewShowState(this.mStartButton, 4);
        setViewShowState(this.mLoadingProgressBar, 0);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, 8);
        if ((this.mLoadingProgressBar instanceof ENDownloadView) && this.mLoadingProgressBar.getCurrentState() == 0) {
            this.mLoadingProgressBar.start();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToPlayingShow() {
        Debuger.printfLog("changeUiToPlayingShow");
        setViewShowState(this.mTopContainer, 0);
        setViewShowState(this.mBottomContainer, 0);
        setViewShowState(this.mStartButton, 0);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, (this.mIfCurrentIsFullscreen && this.mNeedLockFull) ? 0 : 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
        updateStartImage();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToPauseShow() {
        Debuger.printfLog("changeUiToPauseShow");
        setViewShowState(this.mTopContainer, 0);
        setViewShowState(this.mBottomContainer, 0);
        setViewShowState(this.mStartButton, 0);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, (this.mIfCurrentIsFullscreen && this.mNeedLockFull) ? 0 : 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
        updateStartImage();
        updatePauseCover();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToPlayingBufferingShow() {
        Debuger.printfLog("changeUiToPlayingBufferingShow");
        setViewShowState(this.mTopContainer, 0);
        setViewShowState(this.mBottomContainer, 0);
        setViewShowState(this.mStartButton, 4);
        setViewShowState(this.mLoadingProgressBar, 0);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, 8);
        if ((this.mLoadingProgressBar instanceof ENDownloadView) && this.mLoadingProgressBar.getCurrentState() == 0) {
            this.mLoadingProgressBar.start();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToCompleteShow() {
        Debuger.printfLog("changeUiToCompleteShow");
        setViewShowState(this.mTopContainer, 0);
        setViewShowState(this.mBottomContainer, 0);
        setViewShowState(this.mStartButton, 0);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 0);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, (this.mIfCurrentIsFullscreen && this.mNeedLockFull) ? 0 : 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
        updateStartImage();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToError() {
        Debuger.printfLog("changeUiToError");
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 0);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, (this.mIfCurrentIsFullscreen && this.mNeedLockFull) ? 0 : 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
        updateStartImage();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissVolumeDialog();
        dismissBrightnessDialog();
    }

    protected int getProgressDialogLayoutId() {
        return R.layout.video_progress_dialog;
    }

    protected int getProgressDialogProgressId() {
        return R.id.duration_progressbar;
    }

    protected int getProgressDialogCurrentDurationTextId() {
        return R.id.tv_current;
    }

    protected int getProgressDialogAllDurationTextId() {
        return R.id.tv_duration;
    }

    protected int getProgressDialogImageId() {
        return R.id.duration_image_tip;
    }

    protected int getVolumeLayoutId() {
        return R.layout.video_volume_dialog;
    }

    protected int getVolumeProgressId() {
        return R.id.volume_progressbar;
    }

    protected int getBrightnessLayoutId() {
        return R.layout.video_brightness;
    }

    protected int getBrightnessTextId() {
        return R.id.app_video_brightness;
    }

    protected void changeUiToPrepareingClear() {
        Debuger.printfLog("changeUiToPrepareingClear");
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 4);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
    }

    protected void changeUiToPlayingClear() {
        Debuger.printfLog("changeUiToPlayingClear");
        changeUiToClear();
        setViewShowState(this.mBottomProgressBar, 0);
    }

    protected void changeUiToPauseClear() {
        Debuger.printfLog("changeUiToPauseClear");
        changeUiToClear();
        setViewShowState(this.mBottomProgressBar, 0);
        updatePauseCover();
    }

    protected void changeUiToPlayingBufferingClear() {
        Debuger.printfLog("changeUiToPlayingBufferingClear");
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 4);
        setViewShowState(this.mLoadingProgressBar, 0);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 0);
        setViewShowState(this.mLockScreen, 8);
        if ((this.mLoadingProgressBar instanceof ENDownloadView) && this.mLoadingProgressBar.getCurrentState() == 0) {
            this.mLoadingProgressBar.start();
        }
        updateStartImage();
    }

    protected void changeUiToClear() {
        Debuger.printfLog("changeUiToClear");
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 4);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 4);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
    }

    protected void changeUiToCompleteClear() {
        Debuger.printfLog("changeUiToCompleteClear");
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 0);
        setViewShowState(this.mLoadingProgressBar, 4);
        setViewShowState(this.mThumbImageViewLayout, 0);
        setViewShowState(this.mBottomProgressBar, 0);
        setViewShowState(this.mLockScreen, (this.mIfCurrentIsFullscreen && this.mNeedLockFull) ? 0 : 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.reset();
        }
        updateStartImage();
    }

    protected void updateStartImage() {
        if (this.mStartButton instanceof ENPlayView) {
            ENPlayView eNPlayView = this.mStartButton;
            eNPlayView.setDuration(500);
            if (this.mCurrentState == 2) {
                eNPlayView.play();
                return;
            } else if (this.mCurrentState == 7) {
                eNPlayView.pause();
                return;
            } else {
                eNPlayView.pause();
                return;
            }
        }
        if (this.mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) this.mStartButton;
            if (this.mCurrentState == 2) {
                imageView.setImageResource(R.drawable.video_click_pause_selector);
            } else if (this.mCurrentState == 7) {
                imageView.setImageResource(R.drawable.video_click_error_selector);
            } else {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            }
        }
    }

    private void initFullUI(StandardGSYVideoPlayer standardGSYVideoPlayer) {
        int i;
        Drawable drawable;
        Drawable drawable2 = this.mBottomProgressDrawable;
        if (drawable2 != null) {
            standardGSYVideoPlayer.setBottomProgressBarDrawable(drawable2);
        }
        Drawable drawable3 = this.mBottomShowProgressDrawable;
        if (drawable3 != null && (drawable = this.mBottomShowProgressThumbDrawable) != null) {
            standardGSYVideoPlayer.setBottomShowProgressBarDrawable(drawable3, drawable);
        }
        Drawable drawable4 = this.mVolumeProgressDrawable;
        if (drawable4 != null) {
            standardGSYVideoPlayer.setDialogVolumeProgressBar(drawable4);
        }
        Drawable drawable5 = this.mDialogProgressBarDrawable;
        if (drawable5 != null) {
            standardGSYVideoPlayer.setDialogProgressBar(drawable5);
        }
        int i2 = this.mDialogProgressHighLightColor;
        if (i2 == -11 || (i = this.mDialogProgressNormalColor) == -11) {
            return;
        }
        standardGSYVideoPlayer.setDialogProgressColor(i2, i);
    }

    public void setBottomShowProgressBarDrawable(Drawable drawable, Drawable drawable2) {
        this.mBottomShowProgressDrawable = drawable;
        this.mBottomShowProgressThumbDrawable = drawable2;
        if (this.mProgressBar != null) {
            this.mProgressBar.setProgressDrawable(drawable);
            this.mProgressBar.setThumb(drawable2);
        }
    }

    public void setBottomProgressBarDrawable(Drawable drawable) {
        this.mBottomProgressDrawable = drawable;
        if (this.mBottomProgressBar != null) {
            this.mBottomProgressBar.setProgressDrawable(drawable);
        }
    }

    public void setDialogVolumeProgressBar(Drawable drawable) {
        this.mVolumeProgressDrawable = drawable;
    }

    public void setDialogProgressBar(Drawable drawable) {
        this.mDialogProgressBarDrawable = drawable;
    }

    public void setDialogProgressColor(int i, int i2) {
        this.mDialogProgressHighLightColor = i;
        this.mDialogProgressNormalColor = i2;
    }

    public void taskShotPic(GSYVideoShotListener gSYVideoShotListener) {
        taskShotPic(gSYVideoShotListener, false);
    }

    public void taskShotPic(GSYVideoShotListener gSYVideoShotListener, boolean z) {
        if (getCurrentPlayer().getRenderProxy() != null) {
            getCurrentPlayer().getRenderProxy().taskShotPic(gSYVideoShotListener, z);
        }
    }

    public void saveFrame(File file, GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        saveFrame(file, false, gSYVideoShotSaveListener);
    }

    public void saveFrame(File file, boolean z, GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        if (getCurrentPlayer().getRenderProxy() != null) {
            getCurrentPlayer().getRenderProxy().saveFrame(file, z, gSYVideoShotSaveListener);
        }
    }

    public void restartTimerTask() {
        startProgressTimer();
        startDismissControlViewTimer();
    }
}
