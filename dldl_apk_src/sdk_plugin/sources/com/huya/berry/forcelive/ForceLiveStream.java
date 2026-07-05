package com.huya.berry.forcelive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.module.ArkProperties;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.util.NetworkUtil;
import com.huya.berry.forcelive.ForceClient;
import com.huya.berry.forcelive.api.IForceLiveStream;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.resolutions.LivingParams;
import com.huya.berry.gamesdk.resolutions.ResolutionOptions;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUI;
import com.huya.berry.gamesdk.utils.SystemUiUtils;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.sdkcamera.event.CameraCallback;
import com.huya.berry.sdkcamera.event.CameraInterface;
import com.huya.berry.sdklive.event.FloatWinInterface;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.force.audioenginecapture.AudioEngineCaptureInput;
import com.huya.force.audioengineencode.AudioEngineEncodeInput;
import com.huya.force.audioengineencode.AudioEngineEncoder;
import com.huya.force.cameracapture.Camera1Capture;
import com.huya.force.cameracapture.Camera1CaptureInput;
import com.huya.force.common.VideoEncodeType;
import com.huya.force.export.cameracapture.BaseCameraCapture;
import com.huya.force.export.imagefilter.BaseImageFilter;
import com.huya.force.export.imagefilter.ImageFilterInput;
import com.huya.force.export.imagefilter.Watermark;
import com.huya.force.export.surface.ISurface;
import com.huya.force.export.upload.UploadInput;
import com.huya.force.export.videocapture.VideoCaptureInput;
import com.huya.force.export.videoencode.VideoEncodeInput;
import com.huya.force.log.ForceLog;
import com.huya.force.rtmpupload.RtmpUpload;
import com.huya.force.rtmpupload.RtmpUploadInput;
import com.huya.force.screencapture.ScreenCaptureInput;
import com.huya.force.videohardencode.HardVideoEncodeInput;
import com.sqwan.liveshow.huya.SqR;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.youme.im.CommonConst;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ForceLiveStream implements IForceLiveStream {
    private static final String TAG = "ForceLiveStream";
    private WeakReference<Activity> mActivity;
    private boolean mIsPause;
    private boolean mIsPauseAudio;
    private boolean mIsResume;
    private boolean mIsResumePreview;
    private boolean mIsStart;
    private ForceClient mMediaClient;
    private boolean mNeedPreviewPush;
    private Watermark mPauseAudioMark;
    private Bitmap mPauseBitmap;
    private Watermark mPauseLiveMark;
    private CameraInterface.PreviewParams mPreviewParams;
    private String mRtmpUrl;
    private boolean rePushHaschange;
    private RtmpUploadInput uploadInput;
    private boolean mIsFrontCamera = true;
    private int mLastSendTime = 0;
    private Runnable mTotalStopMediaTask = new Runnable() { // from class: com.huya.berry.forcelive.ForceLiveStream.1
        @Override // java.lang.Runnable
        public void run() {
            if (ForceLiveStream.this.mMediaClient != null) {
                ForceLiveStream.this.stopMediaClient();
                if (ForceLiveStream.this.isCameraCapture()) {
                    ArkUtils.send(new CameraCallback.onFinishPreview());
                    ForceLiveStream.this.mPreviewParams = null;
                }
                L.info(ForceLiveStream.TAG, "MediaClient stop");
            }
        }
    };
    Runnable mRePushTask = new Runnable() { // from class: com.huya.berry.forcelive.ForceLiveStream.2
        @Override // java.lang.Runnable
        public void run() {
            TaskExecutor.uiHandler().removeCallbacks(ForceLiveStream.this.mRePushTask);
            if (!ArkProperties.networkAvailable.get().booleanValue() || SdkProperties.isPushStreamOk.get().booleanValue() || ForceLiveStream.this.mMediaClient == null) {
                return;
            }
            ForceLiveStream.this.mMediaClient.stop();
            if (ForceLiveStream.this.isCameraCapture() && !ForceLiveStream.this.mIsResumePreview) {
                ForceLiveStream.this.mNeedPreviewPush = true;
                L.info(ForceLiveStream.TAG, "MediaClient stop and mNeedPreviewPush");
            } else {
                ForceLiveStream.this.startPush();
                ArkUtils.send(new CommonEvent.RePushLive());
                L.info(ForceLiveStream.TAG, "MediaClient stop and repush");
            }
        }
    };
    private ForceClient.Listener mMediaListener = new ForceClient.Listener() { // from class: com.huya.berry.forcelive.ForceLiveStream.4
        @Override // com.huya.berry.forcelive.ForceClient.Listener
        public void onAudioCaptureData(byte[] bArr, int i, long j) {
        }

        @Override // com.huya.berry.forcelive.ForceClient.Listener
        public void onAudioUploadFrame() {
        }

        @Override // com.huya.berry.forcelive.ForceClient.Listener
        public void onVideoUploadFrame() {
        }

        @Override // com.huya.berry.forcelive.ForceClient.Listener
        public void onUploadResult(int i) {
            L.info(ForceLiveStream.TAG, "MediaCallback:" + i);
            if (i == RtmpUpload.RtmpResult.kConnectSuccess.ordinal()) {
                SdkProperties.isPushStreamOk.set(true);
                ForceLiveStream.this.rePushHaschange = false;
            } else {
                if (i < RtmpUpload.RtmpResult.kConnectError.ordinal()) {
                    return;
                }
                if ((!NetworkUtil.isWifiActive((Context) ForceLiveStream.this.mActivity.get()) || ForceLiveStream.this.rePushHaschange) && i != RtmpUpload.RtmpResult.kConnectError.ordinal()) {
                    return;
                }
                SdkProperties.isPushStreamOk.set(false);
                TaskExecutor.uiHandler().removeCallbacks(ForceLiveStream.this.mRePushTask);
                TaskExecutor.uiHandler().postDelayed(ForceLiveStream.this.mRePushTask, OAIDHelper.TIMEOUT);
                ForceLiveStream.this.rePushHaschange = true;
            }
        }
    };

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public void test() {
    }

    public ForceLiveStream(Activity activity) {
        this.mActivity = new WeakReference<>(activity);
        SignalCenter.register(this);
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public boolean startPush() {
        if (TextUtils.isEmpty(this.mRtmpUrl)) {
            L.error(TAG, "RtmpUrl is empty");
            return false;
        }
        if (isCameraCapture()) {
            if (this.mIsResumePreview && this.mPreviewParams != null) {
                initMediaClient();
                this.mMediaClient.setPreviewSurface(this.mPreviewParams.previewSurface.getHolder().getSurface());
                this.mMediaClient.setPreviewSize(this.mPreviewParams.previewSurface.getWidth(), this.mPreviewParams.previewSurface.getHeight());
                this.mMediaClient.start();
            } else {
                this.mNeedPreviewPush = true;
            }
        } else {
            initMediaClient();
            this.mMediaClient.start();
        }
        SdkProperties.isStopMidea.set(false);
        if (this.mIsPauseAudio && this.mIsPause) {
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseLiveMark, this.mPauseAudioMark));
            this.mMediaClient.setMute(this.mIsPause);
        } else if (this.mIsPauseAudio) {
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseAudioMark));
            this.mMediaClient.setMute(this.mIsPauseAudio);
        } else if (this.mIsPause) {
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseLiveMark));
            this.mMediaClient.setMute(this.mIsPause);
        }
        return true;
    }

    private void initMediaClient() {
        AudioEngineCaptureInput audioEngineCaptureInput;
        AudioEngineCaptureInput audioEngineCaptureInput2;
        Object obj;
        int i;
        VideoCaptureInput screenCaptureInput;
        String strRtmpCode = RtmpCode.rtmpCode(1, 0);
        LivingParams curLivingParams = ResolutionOptions.getInstance().getCurLivingParams();
        if (this.mMediaClient != null && isCameraCapture()) {
            RtmpUploadInput rtmpUploadInput = this.uploadInput;
            if (rtmpUploadInput != null) {
                rtmpUploadInput.setPath(strRtmpCode);
                this.mMediaClient.setUpload(this.uploadInput);
                return;
            }
            return;
        }
        ForceClient.Builder builder = new ForceClient.Builder();
        int videoWidth = curLivingParams.getVideoWidth();
        int videoHeight = curLivingParams.getVideoHeight();
        int videoFrameRate = curLivingParams.getVideoFrameRate();
        int videoBitrate = curLivingParams.getVideoBitrate() * 1024;
        L.info(TAG, "width=" + videoWidth + ",height:" + videoHeight + ",videoBitrate:" + videoBitrate + ",getVideoBitrate:" + curLivingParams.getVideoBitrate());
        if (isCameraCapture()) {
            audioEngineCaptureInput = new AudioEngineCaptureInput(this.mActivity.get().getApplicationContext(), CommonConst.SAMPLERATE_44K, 2, 16, AudioEngineCaptureInput.AudioStreamUsage.kLocal, 0L);
        } else {
            audioEngineCaptureInput = new AudioEngineCaptureInput(this.mActivity.get().getApplicationContext(), CommonConst.SAMPLERATE_44K, 2, 16, AudioEngineCaptureInput.AudioStreamUsage.kLocal, 0L);
        }
        AudioEngineCaptureInput audioEngineCaptureInput3 = audioEngineCaptureInput;
        if (isCameraCapture()) {
            ISurface.SurfaceType surfaceType = ISurface.SurfaceType.SURFACE_TEXTURE;
            Context applicationContext = this.mActivity.get().getApplicationContext();
            BaseCameraCapture.CameraPosition cameraPosition = this.mIsFrontCamera ? BaseCameraCapture.CameraPosition.kFront : BaseCameraCapture.CameraPosition.kBack;
            audioEngineCaptureInput2 = audioEngineCaptureInput3;
            obj = TAG;
            i = videoBitrate;
            screenCaptureInput = new Camera1CaptureInput(surfaceType, applicationContext, videoWidth, videoHeight, videoFrameRate, cameraPosition);
        } else {
            audioEngineCaptureInput2 = audioEngineCaptureInput3;
            obj = TAG;
            i = videoBitrate;
            screenCaptureInput = new ScreenCaptureInput(this.mActivity.get(), videoWidth, videoHeight, videoFrameRate);
        }
        AudioEngineEncodeInput audioEngineEncodeInput = new AudioEngineEncodeInput(AudioEngineEncoder.AudioQuality.kHighQuality);
        VideoEncodeType videoEncodeType = VideoEncodeType.kH264;
        VideoCaptureInput videoCaptureInput = screenCaptureInput;
        HardVideoEncodeInput hardVideoEncodeInput = new HardVideoEncodeInput(videoWidth, videoHeight, i, videoFrameRate, VideoEncodeInput.BitrateMode.kModeVbr, VideoEncodeType.kH264);
        this.uploadInput = new RtmpUploadInput(this.mRtmpUrl, strRtmpCode, RtmpUploadInput.FlowControlPolicy.kNoPolicy, null, i, null, new UploadInput.VideoParam(videoWidth, videoHeight, videoFrameRate, i, videoEncodeType), new UploadInput.AudioParam(UploadInput.AacPorfile.kLC, 2, CommonConst.SAMPLERATE_44K, 16, 131072));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        if (SdkProperties.isLandscape.get().booleanValue()) {
            this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg_land), options), 0, 0, videoWidth, videoHeight);
        } else {
            this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg), options), 0, 0, videoWidth, videoHeight);
        }
        this.mPauseAudioMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_audio_pause_water), options), SystemUiUtils.getDisplayWidth() - ((int) UIUtil.getDp(110.0f)), 10, 108, 26);
        ForceClient forceClientBuild = builder.setAudioCapture(audioEngineCaptureInput2).setVideoCapture(videoCaptureInput).setImageFilter(new ImageFilterInput(this.mActivity.get(), videoWidth, videoHeight, videoWidth, videoHeight, BaseImageFilter.Type.NATURAL, null)).setAudioEncoder(audioEngineEncodeInput).setVideoEncoder(hardVideoEncodeInput).setUpload(this.uploadInput).build();
        this.mMediaClient = forceClientBuild;
        forceClientBuild.configureEGL(null, null, null);
        this.mMediaClient.setListener(this.mMediaListener);
        ForceLog.instance().setLog(new MediaLog());
        this.mMediaClient.init();
        this.mMediaClient.setListener(this.mMediaListener);
        BaseImageFilter imageFilter = this.mMediaClient.getImageFilter();
        imageFilter.enableBeautyFilter(true);
        imageFilter.setBeautyWhite(0.9f);
        imageFilter.setBeautyDermabrasion(0.9f);
        L.info(obj, "url=" + this.mRtmpUrl + ",rtmpKey:" + strRtmpCode);
        MonitorCenter.getInstance().start(this.mRtmpUrl);
        reportVideoBitrate(curLivingParams.getVideoBitrate());
    }

    private void reportVideoBitrate(int i) {
        if (i <= 800) {
            Report.event(SdkReportConst.STATUS_LIVE_LIVESTART_QUALITY_800);
            return;
        }
        if (i <= 1200) {
            Report.event(SdkReportConst.STATUS_LIVE_LIVESTART_QUALITY_1200);
            return;
        }
        if (i <= 2000) {
            Report.event(SdkReportConst.STATUS_LIVE_LIVESTART_QUALITY_2000);
            return;
        }
        if (i <= 3000) {
            Report.event(SdkReportConst.STATUS_LIVE_LIVESTART_QUALITY_3000);
        } else if (i <= 4000) {
            Report.event(SdkReportConst.STATUS_LIVE_LIVESTART_QUALITY_4000);
        } else if (i > 4000) {
            Report.event(SdkReportConst.STATUS_LIVE_LIVESTART_QUALITY_ABOVE4K);
        }
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public void stopLive() {
        L.info(TAG, "stopLive");
        this.mIsFrontCamera = true;
        TaskExecutor.uiHandler().removeCallbacks(this.mTotalStopMediaTask);
        TaskExecutor.uiHandler().removeCallbacks(this.mRePushTask);
        TaskExecutor.uiHandler().post(this.mTotalStopMediaTask);
        Bitmap bitmap = this.mPauseBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mPauseBitmap = null;
        }
        this.mIsPauseAudio = false;
        this.mIsPause = false;
    }

    private int getPauseAudioY() {
        if (SdkProperties.isLandscape.get().booleanValue()) {
            return SystemUI.dpToPx(6.0f);
        }
        return SystemUI.dpToPx(22.0f);
    }

    private int getPauseLiveX() {
        return UIUtil.enableFullSreen ? -50 : 0;
    }

    private int getPauseLiveY() {
        return UIUtil.enableFullSreen ? -60 : 0;
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public void stopMediaClient() {
        if (this.mMediaClient != null) {
            SdkProperties.isStopMidea.set(true);
            TaskExecutor.proxyHandler().post(new Runnable() { // from class: com.huya.berry.forcelive.ForceLiveStream.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ForceLiveStream.this.mMediaClient != null) {
                        ForceLiveStream.this.mMediaClient.stop();
                        ForceLiveStream.this.mMediaClient.stopPreview();
                        ForceLiveStream.this.mMediaClient.uninit();
                        ForceLiveStream.this.mMediaClient = null;
                    }
                }
            });
        }
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public void uninit() {
        SignalCenter.unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCameraCapture() {
        return SdkProperties.sdkMode.get() == SdkProperties.SDKMode.CAPTURE_BY_CAMERA;
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public void setRtmpUrl(String str) {
        this.mRtmpUrl = str;
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public ForceClient getMediaClient() {
        return this.mMediaClient;
    }

    @Override // com.huya.berry.forcelive.api.IForceLiveStream
    public boolean getIsFrontCamera() {
        return this.mIsFrontCamera;
    }

    @IASlot(mark = {ArkProperties.MarkNetworkAvailable})
    public void onNetworkChange(PropertySet<Boolean> propertySet) {
        L.info(TAG, "onNetworkChange: " + propertySet.oldValue + " &&& " + propertySet.newValue);
        if (!propertySet.oldValue.booleanValue() && propertySet.newValue.booleanValue()) {
            L.info(TAG, "Network Reconnect");
            SdkProperties.isPushStreamOk.set(false);
            TaskExecutor.uiHandler().removeCallbacks(this.mRePushTask);
            TaskExecutor.uiHandler().postDelayed(this.mRePushTask, 2000L);
            return;
        }
        if (this.mIsResume) {
            SdkProperties.isPushStreamOk.set(false);
            TaskExecutor.uiHandler().removeCallbacks(this.mRePushTask);
            TaskExecutor.uiHandler().postDelayed(this.mRePushTask, 2000L);
        }
    }

    @IASlot(executorID = 1)
    public void onPauseLive(FloatWinInterface.onPauseLive onpauselive) {
        this.mIsPause = true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        LivingParams curLivingParams = ResolutionOptions.getInstance().getCurLivingParams();
        if (SdkProperties.isLandscape.get().booleanValue()) {
            this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg_land), options), 0, getPauseLiveY(), curLivingParams.getParamsVideoWidth(), curLivingParams.getParamsVideoHeight());
        } else {
            this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg), options), getPauseLiveX(), 0, curLivingParams.getParamsVideoWidth(), curLivingParams.getParamsVideoHeight());
        }
        this.mPauseAudioMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_audio_pause_water), options), curLivingParams.getVideoWidth() - SystemUI.dpToPx(10.0f), getPauseAudioY(), 108, 26);
        this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseLiveMark));
        this.mMediaClient.setMute(this.mIsPause);
    }

    @IASlot(executorID = 1)
    public void onPauseAudio(FloatWinInterface.onPauseAudio onpauseaudio) {
        this.mIsPauseAudio = true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        LivingParams curLivingParams = ResolutionOptions.getInstance().getCurLivingParams();
        if (this.mIsPause) {
            if (SdkProperties.isLandscape.get().booleanValue()) {
                this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg_land), options), 0, getPauseLiveY(), curLivingParams.getParamsVideoWidth(), curLivingParams.getParamsVideoHeight());
            } else {
                this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg), options), getPauseLiveX(), 0, curLivingParams.getParamsVideoWidth(), curLivingParams.getParamsVideoHeight());
            }
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseLiveMark));
        } else {
            this.mPauseAudioMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_audio_pause_water), options), curLivingParams.getVideoWidth() - 110, getPauseAudioY(), 108, 26);
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseAudioMark));
        }
        this.mMediaClient.setMute(this.mIsPauseAudio);
        ArkToast.show("直播已静音，观众听不见任何声音");
    }

    @IASlot(executorID = 1)
    public void onResumeLive(FloatWinInterface.onResumeLive onresumelive) {
        this.mIsPause = false;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        this.mPauseAudioMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_audio_pause_water), options), ResolutionOptions.getInstance().getCurLivingParams().getVideoWidth() - 110, getPauseAudioY(), 108, 26);
        if (this.mIsPauseAudio) {
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseAudioMark));
        } else {
            this.mMediaClient.getImageFilter().setWatermark(null);
            this.mMediaClient.setMute(this.mIsPause);
        }
    }

    @IASlot(executorID = 1)
    public void onResumeAudio(FloatWinInterface.onResumeAudio onresumeaudio) {
        this.mIsPauseAudio = false;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        LivingParams curLivingParams = ResolutionOptions.getInstance().getCurLivingParams();
        if (SdkProperties.isLandscape.get().booleanValue()) {
            this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg_land), options), 0, getPauseLiveY(), curLivingParams.getParamsVideoWidth(), curLivingParams.getParamsVideoHeight());
        } else {
            this.mPauseLiveMark = new Watermark(BitmapFactory.decodeResource(this.mActivity.get().getResources(), ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_pause_live_bg), options), getPauseLiveX(), 0, curLivingParams.getParamsVideoWidth(), curLivingParams.getParamsVideoHeight());
        }
        if (this.mIsPause) {
            this.mMediaClient.getImageFilter().setWatermark(Arrays.asList(this.mPauseLiveMark));
        } else {
            this.mMediaClient.getImageFilter().setWatermark(null);
            this.mMediaClient.setMute(this.mIsPauseAudio);
        }
    }

    @IASlot(executorID = 1)
    public void onGetPreviewSurface(CameraInterface.GetPreviewSurface getPreviewSurface) {
        if (getPreviewSurface == null) {
            return;
        }
        L.info(TAG, "GetPreviewSurface, previewSurface:" + getPreviewSurface.previewParams.previewSurface + ", previewWidth:" + getPreviewSurface.previewParams.previewWidth + ", previewHeight:" + getPreviewSurface.previewParams.previewHeight);
        this.mPreviewParams = getPreviewSurface.previewParams;
        this.mIsResumePreview = true;
        if (this.mNeedPreviewPush) {
            L.info(TAG, "CameraPreview needPreviewPush");
            stopMediaClient();
            startPush();
            this.mIsStart = true;
        }
    }

    @IASlot(executorID = 1)
    public void onSwitchCamera(CameraInterface.onSwitchCamera onswitchcamera) {
        ((Camera1Capture) this.mMediaClient.getVideoCapture()).switchCamera();
        this.mIsFrontCamera = !this.mIsFrontCamera;
    }

    @IASlot(executorID = 1)
    public void onResume(CameraInterface.onResume onresume) {
        this.mIsResume = true;
    }

    @IASlot(executorID = 1)
    public void onPause(CameraInterface.onPause onpause) {
        this.mIsResume = false;
    }

    @IASlot(executorID = 1)
    public void onSurfaceDestroyed(CameraInterface.onSurfaceDestroyed onsurfacedestroyed) {
        stopMediaClient();
        this.mIsResumePreview = false;
        this.mNeedPreviewPush = true;
        L.info(TAG, "CameraPreview onSurfaceDestroyed onSurfaceDestroyed");
    }

    @IASlot(executorID = 1)
    public void onUpdatePreviewSize(CameraInterface.OnUpdatePreviewSize onUpdatePreviewSize) {
        L.info(TAG, "CameraPreview onUpdatePreviewSize mIsResumePreview " + this.mIsResumePreview + " mIsResume " + this.mIsResume + " mIsStart " + this.mIsStart);
        if (this.mIsStart) {
            this.mIsStart = false;
        } else if (this.mIsResumePreview && this.mIsResume) {
            TaskExecutor.uiHandler().removeCallbacks(this.mRePushTask);
            TaskExecutor.uiHandler().postDelayed(this.mRePushTask, 1000L);
        }
    }

    @IASlot(executorID = 1)
    public void onResumePreview(CameraInterface.onResumePreview onresumepreview) {
        if (onresumepreview == null) {
            return;
        }
        this.mIsResumePreview = true;
        L.info(TAG, "onResumePreview:" + onresumepreview.previewParams.previewWidth + "," + onresumepreview.previewParams.previewHeight);
        this.mPreviewParams = onresumepreview.previewParams;
        if (this.mNeedPreviewPush) {
            L.info(TAG, "CameraPreview onResumePreview needPreviewPush");
            stopMediaClient();
            startPush();
        }
    }

    @IASlot(executorID = 1)
    public void getSendTime(FloatWinInterface.GetSendTime getSendTime) {
        if (this.mMediaClient == null) {
            return;
        }
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int i = iCurrentTimeMillis - this.mLastSendTime;
        this.mLastSendTime = iCurrentTimeMillis;
        ArkUtils.send(new FloatWinInterface.GetSendTimeCallback(i));
    }

    @IASlot(executorID = 1)
    public void onKaLongTime(FloatWinInterface.onKaLongTime onkalongtime) {
        if (this.mMediaClient == null) {
            return;
        }
        L.info(TAG, "onKaLongTime");
        TaskExecutor.uiHandler().removeCallbacks(this.mRePushTask);
        TaskExecutor.uiHandler().postDelayed(this.mRePushTask, 500L);
    }
}
