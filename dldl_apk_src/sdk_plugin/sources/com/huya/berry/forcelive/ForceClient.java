package com.huya.berry.forcelive;

import android.content.Intent;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Surface;
import com.huya.berry.forcelive.api.IForceClient;
import com.huya.force.audioenginecapture.AudioEngineCapture;
import com.huya.force.audioengineencode.AudioEngineEncodeInput;
import com.huya.force.audioengineencode.AudioEngineEncoder;
import com.huya.force.audiohardencode.AudioAndroidEncodeInput;
import com.huya.force.audiohardencode.AudioAndroidEncoder;
import com.huya.force.audiorecordcapture.AudioRecordCapture;
import com.huya.force.cameracapture.Camera1Capture;
import com.huya.force.client.SurfacePreview;
import com.huya.force.common.AudioData;
import com.huya.force.common.VideoData;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.audiocapture.AudioCaptureInput;
import com.huya.force.export.audiocapture.BaseAudioCapture;
import com.huya.force.export.audioencode.BaseAudioEncoder;
import com.huya.force.export.audioencode.IAudioEncodeInput;
import com.huya.force.export.audiofilter.AudioFilterInput;
import com.huya.force.export.imagefilter.BaseImageFilter;
import com.huya.force.export.imagefilter.ImageFilterInput;
import com.huya.force.export.screencapture.BaseScreenCapture;
import com.huya.force.export.upload.BaseUpload;
import com.huya.force.export.upload.UploadInput;
import com.huya.force.export.videocapture.BaseVideoCapture;
import com.huya.force.export.videocapture.VideoCaptureInput;
import com.huya.force.export.videoencode.BaseVideoEncoder;
import com.huya.force.export.videoencode.VideoEncodeInput;
import com.huya.force.gles.EglCore;
import com.huya.force.imagefilter.VideoBeauty;
import com.huya.force.log.ForceLog;
import com.huya.force.log.ILog;
import com.huya.force.rtmpupload.RtmpUpload;
import com.huya.force.rtmpupload.RtmpUploadInput;
import com.huya.force.screencapture.ScreenCapture;
import com.huya.force.videohardencode.HardVideoEncodeInput;
import com.huya.force.videohardencode.HardVideoEncoder;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ForceClient implements BaseAudioCapture.Listener, BaseVideoCapture.Listener, BaseImageFilter.Listener, BaseAudioEncoder.Listener, BaseVideoEncoder.Listener, BaseUpload.Listener, ILog, IForceClient {
    static final String TAG = "ForceClient";
    private BaseAudioCapture mAudioCapture;
    private BaseAudioEncoder mAudioEncoder;
    private EGLSurface mEGLSurface;
    private EglCore mEglCore;
    private Handler mEglHander;
    private HandlerThread mEglThread;
    private int mHeight;
    private BaseImageFilter mImageFilter;
    private Listener mListener;
    private ByteBuffer mRGBABuffer;
    private SurfacePreview mSurfacePreview;
    private BaseUpload mUpload;
    private BaseVideoCapture mVideoCapture;
    private BaseVideoEncoder mVideoEncoder;
    private int mWidth;
    private byte[] mYUVBuffer;
    private boolean mMute = false;
    private boolean mPause = false;
    private boolean mStopFlag = false;

    public interface Listener {
        void onAudioCaptureData(byte[] bArr, int i, long j);

        void onAudioUploadFrame();

        void onUploadResult(int i);

        void onVideoUploadFrame();
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder.Listener, com.huya.force.export.videoencode.BaseVideoEncoder.Listener
    public void onEncodeError(int i) {
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture.Listener
    public void onStartResult(BaseVideoCapture.VideoCaptureStartResult videoCaptureStartResult) {
    }

    public void init() {
        ForceLog.instance().setLog(this);
        ForceLog.info(TAG, "init");
        this.mAudioCapture.init();
        this.mAudioEncoder.init();
        this.mVideoEncoder.init();
        this.mUpload.init();
        this.mAudioCapture.setListener(this);
        this.mVideoCapture.setListener(this);
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.setListener(this);
        }
        this.mAudioEncoder.setListener(this);
        this.mVideoEncoder.setListener(this);
        this.mUpload.setListener(this);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setPreviewSurface(Surface surface) {
        this.mSurfacePreview = new SurfacePreview(this.mEglHander, this.mEglCore, surface, this.mVideoCapture.getInput().getWidth(), this.mVideoCapture.getInput().getHeight());
    }

    public void setPreviewSize(int i, int i2) {
        SurfacePreview surfacePreview = this.mSurfacePreview;
        if (surfacePreview != null) {
            surfacePreview.updateSize(i, i2);
        }
    }

    public void configureEGL(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLConfig eGLConfig) {
        if (eGLConfig != null) {
            this.mEglHander = new Handler();
            this.mEglCore = new EglCore(eGLDisplay, eGLContext, eGLConfig);
        } else {
            this.mEglCore = new EglCore(null, 1);
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mEglThread = handlerThread;
            handlerThread.start();
            this.mEglHander = new Handler(this.mEglThread.getLooper());
        }
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.getInput().setEglHandler(this.mEglHander);
        }
        BaseVideoCapture baseVideoCapture = this.mVideoCapture;
        if (baseVideoCapture != null) {
            VideoCaptureInput input = baseVideoCapture.getInput();
            this.mEGLSurface = this.mEglCore.createOffscreenSurface(input.getWidth(), input.getHeight());
            this.mEglHander.post(new Runnable() { // from class: com.huya.berry.forcelive.ForceClient.1
                @Override // java.lang.Runnable
                public void run() {
                    ForceClient.this.mEglCore.makeCurrent(ForceClient.this.mEGLSurface);
                }
            });
            input.setEglHandler(this.mEglHander);
        }
        BaseVideoEncoder baseVideoEncoder = this.mVideoEncoder;
        if (baseVideoEncoder == null || !baseVideoEncoder.isHardwareEncoder()) {
            return;
        }
        HardVideoEncodeInput hardVideoEncodeInput = (HardVideoEncodeInput) this.mVideoEncoder.getInput();
        hardVideoEncodeInput.setEglCore(this.mEglCore);
        hardVideoEncodeInput.setEglHander(this.mEglHander);
    }

    public void setMute(boolean z) {
        this.mMute = z;
        BaseAudioCapture baseAudioCapture = this.mAudioCapture;
        if (baseAudioCapture != null) {
            baseAudioCapture.setMute(z);
        }
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.enableWaterMark(z);
        }
    }

    public void setPause(boolean z) {
        this.mPause = z;
    }

    public void uninit() {
        ForceLog.info(TAG, "uninit");
        this.mAudioCapture.uninit();
        this.mAudioEncoder.uninit();
        this.mVideoEncoder.uninit();
        this.mUpload.uninit();
    }

    public void start() {
        ForceLog.info(TAG, "start");
        this.mUpload.start();
        this.mAudioCapture.start();
        this.mVideoCapture.start();
    }

    public void stop() {
        if (this.mStopFlag) {
            return;
        }
        ForceLog.info(TAG, "stop");
        this.mStopFlag = true;
        this.mUpload.stop();
        this.mAudioEncoder.stop();
        this.mVideoEncoder.stop();
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.stop();
        }
        this.mAudioCapture.stop();
        this.mVideoCapture.stop();
    }

    public void stopPreview() {
        SurfacePreview surfacePreview = this.mSurfacePreview;
        if (surfacePreview != null) {
            surfacePreview.stop();
            this.mSurfacePreview = null;
        }
    }

    @Override // com.huya.berry.forcelive.api.IForceClient
    public void onActivityResult(int i, int i2, Intent intent) {
        BaseVideoCapture baseVideoCapture = this.mVideoCapture;
        if (baseVideoCapture instanceof BaseScreenCapture) {
            ((BaseScreenCapture) baseVideoCapture).onActivityResult(i, i2, intent);
        }
    }

    public BaseAudioCapture createAudioCapture(AudioCaptureInput audioCaptureInput) {
        AudioCaptureInput.AudioCaptureType audioCaptureType = audioCaptureInput.getAudioCaptureType();
        if (audioCaptureType == AudioCaptureInput.AudioCaptureType.kAudioEngineCapture) {
            this.mAudioCapture = new AudioEngineCapture(audioCaptureInput);
        } else if (audioCaptureType == AudioCaptureInput.AudioCaptureType.kAudioHardCapture) {
            this.mAudioCapture = new AudioRecordCapture(audioCaptureInput);
        }
        return this.mAudioCapture;
    }

    public BaseVideoCapture createVideoCapture(VideoCaptureInput videoCaptureInput) {
        VideoCaptureInput.VideoCaptureType videoCaptureType = videoCaptureInput.getVideoCaptureType();
        if (videoCaptureType != VideoCaptureInput.VideoCaptureType.kCamera2Capture) {
            if (videoCaptureType == VideoCaptureInput.VideoCaptureType.kCameraCapture) {
                this.mVideoCapture = new Camera1Capture(videoCaptureInput);
            } else if (videoCaptureType == VideoCaptureInput.VideoCaptureType.kScreenCapture) {
                this.mVideoCapture = new ScreenCapture(videoCaptureInput);
            }
        }
        return this.mVideoCapture;
    }

    public BaseImageFilter createImageFilter(ImageFilterInput imageFilterInput) {
        if (imageFilterInput != null) {
            this.mImageFilter = new VideoBeauty(imageFilterInput);
        }
        return this.mImageFilter;
    }

    public BaseAudioEncoder createAudioEncoder(IAudioEncodeInput iAudioEncodeInput) {
        if (iAudioEncodeInput instanceof AudioEngineEncodeInput) {
            this.mAudioEncoder = new AudioEngineEncoder(iAudioEncodeInput);
        } else if (iAudioEncodeInput instanceof AudioAndroidEncodeInput) {
            this.mAudioEncoder = new AudioAndroidEncoder(iAudioEncodeInput);
        }
        return this.mAudioEncoder;
    }

    public BaseVideoEncoder createVideoEncoder(VideoEncodeInput videoEncodeInput) {
        if (videoEncodeInput instanceof HardVideoEncodeInput) {
            this.mVideoEncoder = new HardVideoEncoder(videoEncodeInput);
        }
        return this.mVideoEncoder;
    }

    public BaseUpload createUpload(UploadInput uploadInput) {
        if (uploadInput instanceof RtmpUploadInput) {
            this.mUpload = new RtmpUpload(uploadInput);
        }
        return this.mUpload;
    }

    public void setAudioCapture(BaseAudioCapture baseAudioCapture) {
        this.mAudioCapture = baseAudioCapture;
    }

    public BaseAudioCapture getAudioCapture() {
        return this.mAudioCapture;
    }

    public void setVideoCapture(BaseVideoCapture baseVideoCapture) {
        this.mVideoCapture = baseVideoCapture;
    }

    public BaseVideoCapture getVideoCapture() {
        return this.mVideoCapture;
    }

    public void setImageFilter(BaseImageFilter baseImageFilter) {
        this.mImageFilter = baseImageFilter;
    }

    public BaseImageFilter getImageFilter() {
        return this.mImageFilter;
    }

    public void setAudioEncoder(BaseAudioEncoder baseAudioEncoder) {
        this.mAudioEncoder = baseAudioEncoder;
    }

    public void setVideoEncoder(BaseVideoEncoder baseVideoEncoder) {
        this.mVideoEncoder = baseVideoEncoder;
    }

    public void setUpload(BaseUpload baseUpload) {
        this.mUpload = baseUpload;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture.Listener
    public void onCaptureData(byte[] bArr, int i, long j) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onAudioCaptureData(bArr, i, j);
        }
        this.mAudioEncoder.pushPcmData(bArr, i, j);
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture.Listener
    public void onFrameAvailable(VideoFrameData videoFrameData) {
        SurfacePreview surfacePreview = this.mSurfacePreview;
        if (surfacePreview != null) {
            surfacePreview.put(videoFrameData);
        }
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.put(videoFrameData);
        } else {
            encodeVideo(videoFrameData);
        }
    }

    private void encodeVideo(VideoFrameData videoFrameData) {
        if (this.mVideoEncoder.isHardwareEncoder()) {
            ((HardVideoEncoder) this.mVideoEncoder).drainData(videoFrameData);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter.Listener
    public void onImageFilterResult(VideoFrameData videoFrameData) {
        encodeVideo(videoFrameData);
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder.Listener
    public void onEncodedData(byte[] bArr, long j, boolean z) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onAudioUploadFrame();
        }
        this.mUpload.sendAudioData(new AudioData(bArr, j));
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder.Listener
    public void onEncodedData(byte[] bArr, long j, long j2, boolean z, boolean z2) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onVideoUploadFrame();
        }
        if (z2) {
            this.mUpload.setVideoHeader(bArr);
        } else {
            this.mUpload.sendVideoData(new VideoData(bArr, bArr.length, j, j2, z, z2));
        }
    }

    @Override // com.huya.force.export.upload.BaseUpload.Listener
    public void onUploadResult(int i) {
        if (this.mUpload instanceof RtmpUpload) {
            if (i == RtmpUpload.RtmpResult.kConnectSuccess.ordinal()) {
                SurfacePreview surfacePreview = this.mSurfacePreview;
                if (surfacePreview != null) {
                    surfacePreview.start(this.mVideoCapture.getInput().getWidth(), this.mVideoCapture.getInput().getHeight());
                }
                BaseImageFilter baseImageFilter = this.mImageFilter;
                if (baseImageFilter != null) {
                    baseImageFilter.start();
                }
                this.mAudioEncoder.start();
                this.mVideoEncoder.start();
                this.mStopFlag = false;
            }
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onUploadResult(i);
            }
        }
    }

    public BaseUpload setUpload(UploadInput uploadInput) {
        if (uploadInput instanceof RtmpUploadInput) {
            RtmpUpload rtmpUpload = new RtmpUpload(uploadInput);
            this.mUpload = rtmpUpload;
            rtmpUpload.init();
            this.mUpload.setListener(this);
        }
        return this.mUpload;
    }

    public void startVideoPart() {
        this.mVideoCapture.start();
        SurfacePreview surfacePreview = this.mSurfacePreview;
        if (surfacePreview != null) {
            surfacePreview.start(this.mVideoCapture.getInput().getWidth(), this.mVideoCapture.getInput().getHeight());
        }
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.start();
        }
        this.mVideoEncoder.start();
    }

    public void stopVideoPart() {
        this.mVideoEncoder.stop();
        BaseImageFilter baseImageFilter = this.mImageFilter;
        if (baseImageFilter != null) {
            baseImageFilter.stop();
        }
        SurfacePreview surfacePreview = this.mSurfacePreview;
        if (surfacePreview != null) {
            surfacePreview.stop();
            this.mSurfacePreview = null;
        }
        this.mVideoCapture.stop();
    }

    @Override // com.huya.force.log.ILog
    public void info(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void error(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void debug(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void warn(String str, String str2) {
        Log.w(str, str2);
    }

    public static class Builder {
        ForceClient mClient = new ForceClient();

        public Builder setAudioFilter(AudioFilterInput audioFilterInput) {
            return this;
        }

        public Builder setAudioCapture(AudioCaptureInput audioCaptureInput) {
            this.mClient.createAudioCapture(audioCaptureInput);
            return this;
        }

        public Builder setVideoCapture(VideoCaptureInput videoCaptureInput) {
            this.mClient.createVideoCapture(videoCaptureInput);
            return this;
        }

        public Builder setImageFilter(ImageFilterInput imageFilterInput) {
            this.mClient.createImageFilter(imageFilterInput);
            return this;
        }

        public Builder setAudioEncoder(IAudioEncodeInput iAudioEncodeInput) {
            this.mClient.createAudioEncoder(iAudioEncodeInput);
            return this;
        }

        public Builder setVideoEncoder(VideoEncodeInput videoEncodeInput) {
            this.mClient.createVideoEncoder(videoEncodeInput);
            return this;
        }

        public Builder setUpload(UploadInput uploadInput) {
            this.mClient.createUpload(uploadInput);
            return this;
        }

        public ForceClient build() {
            return this.mClient;
        }
    }
}
