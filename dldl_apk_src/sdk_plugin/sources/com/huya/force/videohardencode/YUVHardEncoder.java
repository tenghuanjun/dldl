package com.huya.force.videohardencode;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.videoencode.BaseHardVideoEncoder;
import com.huya.force.export.videoencode.VideoEncodeInput;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class YUVHardEncoder extends BaseHardVideoEncoder {
    private static final String TAG = "YUVHardEncoder";
    private HardVideoEncodeInput mInput;
    private MediaCodec mMediaCodec;
    private long mStartTimestamp;

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void adjustBitrate(int i) {
    }

    @Override // com.huya.force.export.videoencode.BaseHardVideoEncoder
    public void drainData(VideoFrameData videoFrameData) {
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public boolean isHardwareEncoder() {
        return true;
    }

    public YUVHardEncoder(HardVideoEncodeInput hardVideoEncodeInput) {
        super(hardVideoEncodeInput);
        this.mStartTimestamp = -1L;
        this.mInput = hardVideoEncodeInput;
    }

    public void drainData2(byte[] bArr, long j) {
        ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
        ByteBuffer[] outputBuffers = this.mMediaCodec.getOutputBuffers();
        int iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(-1L);
        if (iDequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr, 0, bArr.length);
            this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, bArr.length, j / 1000, 0);
        }
        while (true) {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
            if (iDequeueOutputBuffer < 0) {
                return;
            }
            if (this.mStartTimestamp == -1) {
                this.mStartTimestamp = bufferInfo.presentationTimeUs / 1000;
            }
            ByteBuffer byteBuffer2 = outputBuffers[iDequeueOutputBuffer];
            if (this.mListener != null) {
                byteBuffer2.position(bufferInfo.offset);
                byteBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                byte[] bArr2 = new byte[byteBuffer2.remaining()];
                byteBuffer2.get(bArr2);
                long j2 = (bufferInfo.presentationTimeUs / 1000) - this.mStartTimestamp;
                this.mListener.onEncodedData(bArr2, j2, j2, (bufferInfo.flags & 1) != 0, (bufferInfo.flags & 2) != 0);
            }
            this.mMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
        }
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void init() {
        try {
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(this.mInput.getVideoEncodeType().mineType(), this.mInput.getWidth(), this.mInput.getHeight());
            mediaFormatCreateVideoFormat.setInteger("color-format", 21);
            mediaFormatCreateVideoFormat.setInteger("bitrate", this.mInput.getBitrate());
            mediaFormatCreateVideoFormat.setInteger("bitrate-mode", getBitrateMode(this.mInput.getBitrateMode()));
            mediaFormatCreateVideoFormat.setInteger("frame-rate", this.mInput.getFrameRate());
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 3);
            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(this.mInput.getVideoEncodeType().mineType());
            this.mMediaCodec = mediaCodecCreateEncoderByType;
            mediaCodecCreateEncoderByType.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        } catch (IOException e) {
            Log.e(TAG, "createEncoder error " + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.huya.force.videohardencode.YUVHardEncoder$1, reason: invalid class name */
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

    private int getBitrateMode(VideoEncodeInput.BitrateMode bitrateMode) {
        int i = AnonymousClass1.$SwitchMap$com$huya$force$export$videoencode$VideoEncodeInput$BitrateMode[bitrateMode.ordinal()];
        if (i != 2) {
            return i != 3 ? 0 : 1;
        }
        return 2;
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void start() {
        this.mStartTimestamp = -1L;
        this.mMediaCodec.start();
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void stop() {
        this.mMediaCodec.stop();
    }

    @Override // com.huya.force.export.videoencode.BaseVideoEncoder
    public void uninit() {
        this.mMediaCodec = null;
    }
}
