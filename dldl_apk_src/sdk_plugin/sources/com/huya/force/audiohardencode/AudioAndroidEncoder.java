package com.huya.force.audiohardencode;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.huya.force.export.audioencode.BaseAudioEncoder;
import com.huya.force.export.audioencode.IAudioEncodeInput;
import com.huya.force.log.ForceLog;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioAndroidEncoder extends BaseAudioEncoder {
    private static final String TAG = "AudioAndroidEncoder";
    private MediaCodec encoder;
    private ByteBuffer[] inputBuffers;
    AudioAndroidEncodeInput mAudioAndroidEncodeInput;
    private BaseAudioEncoder.Listener mListener;
    private ByteBuffer[] outputBuffers;

    public AudioAndroidEncoder(IAudioEncodeInput iAudioEncodeInput) {
        super(iAudioEncodeInput);
        this.mAudioAndroidEncodeInput = (AudioAndroidEncodeInput) iAudioEncodeInput;
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void init() {
        ForceLog.info(TAG, "init");
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void setListener(BaseAudioEncoder.Listener listener) {
        this.mListener = listener;
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void start() {
        ForceLog.info(TAG, "start");
        createAudioMediaCodec(this.mAudioAndroidEncodeInput);
        this.inputBuffers = this.encoder.getInputBuffers();
        this.outputBuffers = this.encoder.getOutputBuffers();
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void pushPcmData(byte[] bArr, int i, long j) {
        int iDequeueOutputBuffer;
        int iDequeueInputBuffer = this.encoder.dequeueInputBuffer(-1L);
        if (iDequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = this.inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            if (bArr.length == i) {
                byteBuffer.put(bArr);
            } else {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                byteBuffer.put(bArr2);
            }
            this.encoder.queueInputBuffer(iDequeueInputBuffer, 0, i, j * 1000, 0);
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        while (true) {
            iDequeueOutputBuffer = this.encoder.dequeueOutputBuffer(bufferInfo, 0L);
            if (iDequeueOutputBuffer != -2) {
                break;
            }
            ByteBuffer byteBuffer2 = this.encoder.getOutputFormat().getByteBuffer("csd-0");
            byte[] bArr3 = new byte[byteBuffer2.remaining()];
            byteBuffer2.get(bArr3);
            BaseAudioEncoder.Listener listener = this.mListener;
            if (listener != null) {
                listener.onEncodedData(bArr3, 0L, true);
            }
        }
        if (iDequeueOutputBuffer < 0) {
            Log.d(TAG, "outputBufferIndex < 0, outputBufferIndex=" + iDequeueOutputBuffer);
            return;
        }
        if ((bufferInfo.flags & 2) != 0) {
            this.encoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
            return;
        }
        ByteBuffer byteBuffer3 = this.outputBuffers[iDequeueOutputBuffer];
        byteBuffer3.position(bufferInfo.offset);
        byteBuffer3.limit(bufferInfo.offset + bufferInfo.size);
        byte[] bArr4 = new byte[bufferInfo.size];
        byteBuffer3.get(bArr4);
        Log.d(TAG, "audio encode pts=" + j);
        BaseAudioEncoder.Listener listener2 = this.mListener;
        if (listener2 != null) {
            listener2.onEncodedData(bArr4, j, false);
        }
        this.encoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void stop() {
        ForceLog.info(TAG, "stop");
        MediaCodec mediaCodec = this.encoder;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.encoder.release();
            this.encoder = null;
        }
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void uninit() {
        ForceLog.info(TAG, "uninit");
    }

    private void createAudioMediaCodec(AudioAndroidEncodeInput audioAndroidEncodeInput) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("aac-profile", 2);
        mediaFormat.setInteger("sample-rate", audioAndroidEncodeInput.getSampleRate());
        mediaFormat.setInteger("channel-count", audioAndroidEncodeInput.getChannels());
        mediaFormat.setInteger("bitrate", audioAndroidEncodeInput.getBitrate());
        mediaFormat.setInteger("max-input-size", 8820);
        ForceLog.debug(TAG, "creatingAudioEncoder,format=" + mediaFormat.toString());
        try {
            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(mediaFormat.getString("mime"));
            this.encoder = mediaCodecCreateEncoderByType;
            mediaCodecCreateEncoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
            this.encoder.start();
        } catch (Exception e) {
            ForceLog.error(TAG, String.format("can`t create audioEncoder! %s", e.getMessage()));
        }
    }
}
