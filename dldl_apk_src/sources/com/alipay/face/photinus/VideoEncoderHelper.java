package com.alipay.face.photinus;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import com.igexin.push.config.c;
import com.igexin.push.core.b;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class VideoEncoderHelper {
    private static final int IFRAME_INTERVAL = 10;
    private static final String MIME_TYPE = "video/avc";
    private static final String TAG = "VideoEncoderHelper";
    private static final boolean VERBOSE = true;

    static class MediaWrapper {
        MediaCodec _encoder;
        MediaMuxer _muxer;
        boolean _muxerStarted;
        File _outputFile;
        int _trackIndex;

        MediaWrapper() {
        }

        boolean isValid() {
            return (this._encoder == null || this._muxer == null || this._outputFile == null) ? false : true;
        }
    }

    public static void encode(Context context, List<ByteBuffer> list, int i, int i2, int i3, String str, VideoFormatConfig videoFormatConfig, OnVideoWriteListener onVideoWriteListener) {
        MediaWrapper mediaWrapperPrepareEncoder;
        try {
            mediaWrapperPrepareEncoder = prepareEncoder(context, i2, i3, str, videoFormatConfig);
        } catch (Exception e) {
            onVideoWriteListener.onVideoWriteError(e.getMessage());
            mediaWrapperPrepareEncoder = null;
        }
        if (mediaWrapperPrepareEncoder == null || !mediaWrapperPrepareEncoder.isValid()) {
            return;
        }
        try {
            Iterator<ByteBuffer> it = list.iterator();
            int i4 = 0;
            while (it.hasNext()) {
                encodeNextFrame(i4, VideoWriter.rotateYUV420Degree270(it.next().array(), i2, i3), mediaWrapperPrepareEncoder);
                i4++;
            }
            encodeNextFrame(i4, null, mediaWrapperPrepareEncoder);
            if (mediaWrapperPrepareEncoder._encoder != null) {
                mediaWrapperPrepareEncoder._encoder.stop();
                mediaWrapperPrepareEncoder._encoder.release();
                mediaWrapperPrepareEncoder._encoder = null;
            }
            if (mediaWrapperPrepareEncoder._muxer != null) {
                mediaWrapperPrepareEncoder._muxer.stop();
                mediaWrapperPrepareEncoder._muxer.release();
                mediaWrapperPrepareEncoder._muxer = null;
                mediaWrapperPrepareEncoder._muxerStarted = false;
            }
            onVideoWriteListener.onVideoWriteSuccess(Uri.fromFile(mediaWrapperPrepareEncoder._outputFile));
        } catch (Exception e2) {
            onVideoWriteListener.onVideoWriteError(e2.getMessage());
        }
    }

    private static void encodeNextFrame(int i, byte[] bArr, MediaWrapper mediaWrapper) throws Exception {
        ByteBuffer[] inputBuffers = mediaWrapper._encoder.getInputBuffers();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int iDequeueInputBuffer = mediaWrapper._encoder.dequeueInputBuffer(c.i);
        if (iDequeueInputBuffer >= 0) {
            long jComputePresentationTime = VideoWriter.computePresentationTime(i);
            if (bArr == null) {
                mediaWrapper._encoder.queueInputBuffer(iDequeueInputBuffer, 0, 0, jComputePresentationTime, 4);
                drainEncoder(true, bufferInfo, mediaWrapper);
                return;
            }
            ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr);
            mediaWrapper._encoder.queueInputBuffer(iDequeueInputBuffer, 0, bArr.length, jComputePresentationTime, 0);
            drainEncoder(false, bufferInfo, mediaWrapper);
            return;
        }
        Log.i(TAG, "input buffer not available");
    }

    private static void drainEncoder(boolean z, MediaCodec.BufferInfo bufferInfo, MediaWrapper mediaWrapper) throws Exception {
        if (z) {
            try {
                mediaWrapper._encoder.signalEndOfInputStream();
            } catch (Exception unused) {
            }
        }
        ByteBuffer[] outputBuffers = mediaWrapper._encoder.getOutputBuffers();
        while (true) {
            int iDequeueOutputBuffer = mediaWrapper._encoder.dequeueOutputBuffer(bufferInfo, c.i);
            if (iDequeueOutputBuffer == -1) {
                if (!z) {
                    return;
                } else {
                    Log.i(TAG, "no output available, spinning to await EOS");
                }
            } else if (iDequeueOutputBuffer == -3) {
                outputBuffers = mediaWrapper._encoder.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                if (mediaWrapper._muxerStarted) {
                    throw new Exception("format changed twice");
                }
                MediaFormat outputFormat = mediaWrapper._encoder.getOutputFormat();
                Log.i(TAG, "encoder output format changed: " + outputFormat);
                mediaWrapper._trackIndex = mediaWrapper._muxer.addTrack(outputFormat);
                mediaWrapper._muxer.start();
                mediaWrapper._muxerStarted = true;
            } else if (iDequeueOutputBuffer < 0) {
                Log.i(TAG, "unexpected result from encoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
            } else {
                ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new Exception("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                }
                if ((bufferInfo.flags & 2) != 0) {
                    Log.d(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
                    bufferInfo.size = 0;
                }
                if (bufferInfo.size != 0) {
                    if (!mediaWrapper._muxerStarted) {
                        throw new Exception("muxer hasn't started");
                    }
                    byteBuffer.position(bufferInfo.offset);
                    byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    Log.d(TAG, "BufferInfo: " + bufferInfo.offset + b.aj + bufferInfo.size + b.aj + bufferInfo.presentationTimeUs);
                    try {
                        mediaWrapper._muxer.writeSampleData(mediaWrapper._trackIndex, byteBuffer, bufferInfo);
                    } catch (Exception unused2) {
                        Log.i(TAG, "Too many frames");
                    }
                }
                mediaWrapper._encoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
                if ((bufferInfo.flags & 4) != 0) {
                    if (!z) {
                        Log.i(TAG, "reached end of stream unexpectedly");
                        return;
                    } else {
                        Log.i(TAG, "end of stream reached");
                        return;
                    }
                }
            }
        }
    }

    private static MediaWrapper prepareEncoder(Context context, int i, int i2, String str, VideoFormatConfig videoFormatConfig) throws Exception {
        MediaCodecInfo mediaCodecInfoSelectCodec = selectCodec(MIME_TYPE);
        MediaWrapper mediaWrapper = new MediaWrapper();
        Uri uriWithAppendedPath = Uri.withAppendedPath(Uri.fromFile(context.getCacheDir()), "ZLZPhontinus");
        File file = new File(uriWithAppendedPath.getPath());
        if (!file.exists()) {
            file.mkdir();
        }
        mediaWrapper._outputFile = new File(Uri.withAppendedPath(uriWithAppendedPath, str + ".mp4").getPath());
        if (mediaWrapper._outputFile.exists()) {
            mediaWrapper._outputFile.delete();
        }
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MIME_TYPE, i2, i);
        mediaFormatCreateVideoFormat.setInteger("color-format", 21);
        mediaFormatCreateVideoFormat.setInteger("bitrate", videoFormatConfig.getBitRate());
        mediaFormatCreateVideoFormat.setInteger("frame-rate", videoFormatConfig.getFrameRate());
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 10);
        Log.d(TAG, "format: " + mediaFormatCreateVideoFormat);
        try {
            mediaWrapper._encoder = MediaCodec.createByCodecName(mediaCodecInfoSelectCodec.getName());
            mediaWrapper._encoder.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            mediaWrapper._encoder.start();
            try {
                mediaWrapper._muxer = new MediaMuxer(mediaWrapper._outputFile.getAbsolutePath(), 0);
                return mediaWrapper;
            } catch (IOException e) {
                throw new Exception("create muxer error, msg = " + e.getMessage());
            }
        } catch (IOException e2) {
            throw new Exception("create codec by name error, msg = " + e2.getMessage());
        }
    }

    private static MediaCodecInfo selectCodec(String str) throws Exception {
        MediaCodecInfo mediaCodecInfoSelectGoogleCodec = selectGoogleCodec(str);
        if (mediaCodecInfoSelectGoogleCodec != null) {
            return mediaCodecInfoSelectGoogleCodec;
        }
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
            }
        }
        throw new Exception("not support mimeType");
    }

    private static MediaCodecInfo selectGoogleCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                String name = codecInfoAt.getName();
                if (name == null) {
                    name = "";
                }
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str) && name.contains("google")) {
                        return codecInfoAt;
                    }
                }
            }
        }
        return null;
    }
}
