package com.huya.force.videohardencode;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import com.huya.force.collect.FpsCounter;
import com.huya.force.export.videoencode.BaseVideoEncoder;
import com.huya.force.export.videoencode.VideoEncodeInput;
import com.huya.force.log.ForceLog;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoEncoderCore {
    private static final int IFRAME_INTERVAL = 3;
    private static final String TAG = "VideoEncoderCore";
    private static final boolean VERBOSE = false;
    private MediaCodec mEncoder;
    private Surface mInputSurface;
    BaseVideoEncoder.Listener mListener;
    private FpsCounter mFpsCounter = new FpsCounter(TAG);
    private MediaCodec.BufferInfo mBufferInfo = new MediaCodec.BufferInfo();

    public VideoEncoderCore(VideoEncodeInput videoEncodeInput, BaseVideoEncoder.Listener listener) throws IOException {
        this.mListener = listener;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(videoEncodeInput.getVideoEncodeType().mineType(), videoEncodeInput.getWidth(), videoEncodeInput.getHeight());
        mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
        mediaFormatCreateVideoFormat.setInteger("bitrate", videoEncodeInput.getBitrate());
        mediaFormatCreateVideoFormat.setInteger("bitrate-mode", getBitrateMode(videoEncodeInput.getBitrateMode()));
        mediaFormatCreateVideoFormat.setInteger("frame-rate", videoEncodeInput.getFrameRate());
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 3);
        MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(videoEncodeInput.getVideoEncodeType().mineType());
        this.mEncoder = mediaCodecCreateEncoderByType;
        ForceLog.info(TAG, String.format("encoderName=%s", mediaCodecCreateEncoderByType.getName()));
        this.mEncoder.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mInputSurface = this.mEncoder.createInputSurface();
        this.mEncoder.start();
    }

    /* JADX INFO: renamed from: com.huya.force.videohardencode.VideoEncoderCore$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$force$export$videoencode$VideoEncodeInput$BitrateMode;

        static {
            int[] iArr = new int[VideoEncodeInput.BitrateMode.values().length];
            $SwitchMap$com$huya$force$export$videoencode$VideoEncodeInput$BitrateMode = iArr;
            try {
                iArr[VideoEncodeInput.BitrateMode.kModeNone.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$force$export$videoencode$VideoEncodeInput$BitrateMode[VideoEncodeInput.BitrateMode.kModeCbr.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huya$force$export$videoencode$VideoEncodeInput$BitrateMode[VideoEncodeInput.BitrateMode.kModeVbr.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    int getBitrateMode(VideoEncodeInput.BitrateMode bitrateMode) {
        int i = AnonymousClass1.$SwitchMap$com$huya$force$export$videoencode$VideoEncodeInput$BitrateMode[bitrateMode.ordinal()];
        if (i != 2) {
            return i != 3 ? 0 : 1;
        }
        return 2;
    }

    public Surface getInputSurface() {
        return this.mInputSurface;
    }

    public void release() {
        MediaCodec mediaCodec = this.mEncoder;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.mEncoder.release();
            this.mEncoder = null;
        }
    }

    public boolean drainEncoder(boolean z) {
        boolean z2;
        if (z) {
            this.mEncoder.signalEndOfInputStream();
        }
        ByteBuffer[] outputBuffers = this.mEncoder.getOutputBuffers();
        while (true) {
            int iDequeueOutputBuffer = this.mEncoder.dequeueOutputBuffer(this.mBufferInfo, 10L);
            if (iDequeueOutputBuffer == -1) {
                Log.d(TAG, "encoderStatus is INFO_TRY_AGAIN_LATER");
                if (!z) {
                    return false;
                }
            } else if (iDequeueOutputBuffer == -3) {
                Log.d(TAG, "encoderStatus is INFO_OUTPUT_BUFFERS_CHANGED");
                outputBuffers = this.mEncoder.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                Log.d(TAG, "encoderStatus is INFO_OUTPUT_FORMAT_CHANGED");
                Log.d(TAG, "encoder output format changed: " + this.mEncoder.getOutputFormat());
            } else if (iDequeueOutputBuffer < 0) {
                Log.d(TAG, "encoderStatus is less than 0");
                Log.w(TAG, "unexpected result from encoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
            } else {
                ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                }
                if (this.mBufferInfo.size != 0) {
                    byteBuffer.position(this.mBufferInfo.offset);
                    byteBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                    long j = this.mBufferInfo.presentationTimeUs / 1000;
                    z2 = (this.mBufferInfo.flags & 2) == 0;
                    int iRemaining = byteBuffer.remaining();
                    this.mFpsCounter.addFrame(iRemaining * 8);
                    if (this.mListener != null) {
                        byte[] bArr = new byte[iRemaining];
                        byteBuffer.get(bArr);
                        this.mListener.onEncodedData(bArr, j, j, (this.mBufferInfo.flags & 1) != 0, (this.mBufferInfo.flags & 2) != 0);
                    }
                } else {
                    z2 = false;
                }
                this.mEncoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
                return z2;
            }
        }
    }

    public void adjustBitRate(int i) {
        if (this.mEncoder == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", i);
            this.mEncoder.setParameters(bundle);
            ForceLog.info(TAG, String.format("succeed to adjustBitRate=%d", Integer.valueOf(i)));
            return;
        }
        ForceLog.error(TAG, "adjustBitRate is only available on Android API 19+");
    }
}
