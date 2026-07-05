package com.huya.force.videohardencode;

import android.opengl.GLES20;
import android.os.SystemClock;
import android.util.Log;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.videoencode.BaseHardVideoEncoder;
import com.huya.force.export.videoencode.VideoEncodeInput;
import com.huya.force.gles.FullFrameRect;
import com.huya.force.gles.Texture2dProgram;
import com.huya.force.gles.WindowSurface;
import com.huya.force.log.ForceLog;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HardVideoEncoder extends BaseHardVideoEncoder {
    static final int MESSAGE_DRAIN_DATA = 1;
    static final int MESSAGE_START = 0;
    static final int MESSAGE_STOP = 2;
    private static final String TAG = "HardVideoEncoder";
    private FullFrameRect m2DFullScreen;
    private VideoEncoderCore mEncoderCore;
    private FullFrameRect mExtFullScreen;
    private boolean mFirstOutput;
    private HardVideoEncodeInput mHardVideoEncodeInput;
    private WindowSurface mWindowSurface;

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public boolean isHardwareEncoder() {
        return true;
    }

    public HardVideoEncoder(VideoEncodeInput videoEncodeInput) {
        super(videoEncodeInput);
        this.mFirstOutput = true;
        this.mHardVideoEncodeInput = (HardVideoEncodeInput) videoEncodeInput;
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public VideoEncodeInput getInput() {
        return this.mHardVideoEncodeInput;
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void start() {
        ForceLog.info(TAG, "start");
        if (this.mHardVideoEncodeInput.getEglHander() != null) {
            this.mHardVideoEncodeInput.getEglHander().post(new Runnable() { // from class: com.huya.force.videohardencode.HardVideoEncoder.1
                @Override // java.lang.Runnable
                public void run() {
                    HardVideoEncoder.this.startImpl();
                }
            });
        } else {
            startImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImpl() {
        try {
            this.mEncoderCore = new VideoEncoderCore(this.mHardVideoEncodeInput, this.mListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WindowSurface windowSurface = new WindowSurface(this.mHardVideoEncodeInput.getEglCore(), this.mEncoderCore.getInputSurface(), false);
        this.mWindowSurface = windowSurface;
        windowSurface.makeCurrent();
        this.mExtFullScreen = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_EXT));
        this.m2DFullScreen = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_2D));
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void adjustBitrate(int i) {
        VideoEncoderCore videoEncoderCore = this.mEncoderCore;
        if (videoEncoderCore != null) {
            videoEncoderCore.adjustBitRate(i);
        }
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void stop() {
        ForceLog.info(TAG, "stop");
        if (this.mHardVideoEncodeInput.getEglHander() != null) {
            this.mHardVideoEncodeInput.getEglHander().post(new Runnable() { // from class: com.huya.force.videohardencode.HardVideoEncoder.2
                @Override // java.lang.Runnable
                public void run() {
                    HardVideoEncoder.this.stopImpl();
                }
            });
        } else {
            stopImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopImpl() {
        VideoEncoderCore videoEncoderCore = this.mEncoderCore;
        if (videoEncoderCore != null) {
            videoEncoderCore.release();
            this.mEncoderCore = null;
        }
        WindowSurface windowSurface = this.mWindowSurface;
        if (windowSurface != null) {
            windowSurface.release();
            this.mWindowSurface = null;
        }
        FullFrameRect fullFrameRect = this.mExtFullScreen;
        if (fullFrameRect != null) {
            fullFrameRect.release(false);
            this.mExtFullScreen = null;
        }
        FullFrameRect fullFrameRect2 = this.m2DFullScreen;
        if (fullFrameRect2 != null) {
            fullFrameRect2.release(false);
            this.m2DFullScreen = null;
        }
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void uninit() {
        ForceLog.info(TAG, "uninit");
    }

    @Override // com.huya.force.export.videoencode.BaseHardVideoEncoder
    public void drainData(final VideoFrameData videoFrameData) {
        if (this.mHardVideoEncodeInput.getEglHander() != null) {
            this.mHardVideoEncodeInput.getEglHander().post(new Runnable() { // from class: com.huya.force.videohardencode.HardVideoEncoder.3
                @Override // java.lang.Runnable
                public void run() {
                    HardVideoEncoder.this.drainDataImpl(videoFrameData);
                }
            });
        } else {
            drainDataImpl(videoFrameData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drainDataImpl(VideoFrameData videoFrameData) {
        if (this.mEncoderCore == null) {
            ForceLog.error(TAG, "mEncoderCore is null");
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mWindowSurface.makeCurrent();
        GLES20.glViewport(0, 0, this.mWindowSurface.getWidth(), this.mWindowSurface.getHeight());
        if (videoFrameData.textureTarget == 36197) {
            this.mExtFullScreen.drawFrame(videoFrameData.textureId, videoFrameData.transform);
        } else {
            this.m2DFullScreen.drawFrame(videoFrameData.textureId, videoFrameData.transform);
        }
        this.mWindowSurface.setPresentationTime(videoFrameData.timestamp);
        this.mWindowSurface.swapBuffers();
        if (this.mEncoderCore.drainEncoder(false) && this.mFirstOutput) {
            this.mFirstOutput = false;
            Log.d(TAG, "first timestamp output=" + SystemClock.uptimeMillis());
        }
        Log.d(TAG, "encode time=" + (SystemClock.uptimeMillis() - jUptimeMillis));
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void init() {
        ForceLog.info(TAG, "init");
    }
}
