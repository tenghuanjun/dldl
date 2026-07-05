package com.alipay.face.photinus;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.igexin.push.config.c;
import com.igexin.push.core.b;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class VideoWriter {
    private static final int BIT_RATE = 3000000;
    private static final float BPP = 0.25f;
    private static final int FRAME_RATE = 30;
    private static final int IFRAME_INTERVAL = 10;
    private static final String MIME_TYPE = "video/avc";
    private static final String TAG = "ZOLOZ";
    private static final boolean VERBOSE = true;
    private MediaCodec _Encoder;
    private MediaMuxer _Muxer;
    private boolean _MuxerStarted;
    private int _TrackIndex;
    private int _frameIndex;
    private OnVideoListener _listener;
    private int _previewHeight;
    private int _previewWidth;
    private long _tsStart;
    private File _outputFile = null;
    private boolean _running = false;
    private boolean _acceptsNewRequests = false;
    private final ArrayList<Request> _globalRequestQueue = new ArrayList<>();
    private Thread _runLoopThread = new Thread(new Runnable() { // from class: com.alipay.face.photinus.VideoWriter.1
        @Override // java.lang.Runnable
        public void run() {
            Log.d(VideoWriter.TAG, "Started request thread");
            while (VideoWriter.this._running) {
                Request requestPopRequest = VideoWriter.this.popRequest();
                if (requestPopRequest == null) {
                    try {
                        Thread.sleep(10L);
                    } catch (Exception unused) {
                    }
                } else {
                    int i = AnonymousClass2.$SwitchMap$com$alipay$face$photinus$VideoWriter$RequestType[requestPopRequest.type.ordinal()];
                    if (i == 1) {
                        VideoWriter.this._tsStart = System.currentTimeMillis();
                        VideoWriter.this._outputFile = new File(requestPopRequest.fileUri.getPath());
                        VideoWriter.this.createEncoder();
                        VideoWriter.this._frameIndex = 0;
                    } else if (i == 2) {
                        try {
                            requestPopRequest.frame.data = VideoWriter.rotateYUV420Degree270(requestPopRequest.frame.data, VideoWriter.this._previewWidth, VideoWriter.this._previewHeight);
                            VideoWriter.this.encodeNextFrame(VideoWriter.this._frameIndex, requestPopRequest.frame);
                            VideoWriter.access$508(VideoWriter.this);
                            Log.d(VideoWriter.TAG, "VideoWriter encoded frame " + VideoWriter.this._frameIndex);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (i != 3) {
                        continue;
                    } else {
                        try {
                            VideoWriter.this.encodeNextFrame(VideoWriter.this._frameIndex, null);
                            if (VideoWriter.this._Encoder != null) {
                                VideoWriter.this._Encoder.stop();
                                VideoWriter.this._Encoder.release();
                                VideoWriter.this._Encoder = null;
                            }
                            if (VideoWriter.this._Muxer != null) {
                                VideoWriter.this._Muxer.stop();
                                VideoWriter.this._Muxer.release();
                                VideoWriter.this._Muxer = null;
                                VideoWriter.this._MuxerStarted = false;
                            }
                            if (VideoWriter.this._listener != null) {
                                VideoWriter.this._listener.onWriteComplete(this);
                            }
                            Log.d(VideoWriter.TAG, "rCloseMoveFile, took " + (System.currentTimeMillis() - VideoWriter.this._tsStart) + "ms");
                        } finally {
                            try {
                            } finally {
                            }
                        }
                    }
                }
            }
            Log.d(VideoWriter.TAG, "Finished request thread");
        }
    });

    interface OnVideoListener {
        void onWriteComplete(VideoWriter videoWriter);
    }

    private enum RequestType {
        rStartNewMovie,
        rAddMovieFrame,
        rCloseMoveFile
    }

    private static String colorFormatDesc(int i) {
        if (i == 39) {
            return "COLOR_FormatYUV420PackedSemiPlanar";
        }
        if (i == 2130706688) {
            return "COLOR_TI_FormatYUV420PackedSemiPlanar";
        }
        switch (i) {
            case 19:
                return "COLOR_FormatYUV420Planar";
            case 20:
                return "COLOR_FormatYUV420PackedPlanar";
            case 21:
                return "COLOR_FormatYUV420SemiPlanar";
            default:
                return "Unknown color format";
        }
    }

    private static boolean isRecognizedFormat(int i) {
        if (i == 39 || i == 2130706688) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    static /* synthetic */ int access$508(VideoWriter videoWriter) {
        int i = videoWriter._frameIndex;
        videoWriter._frameIndex = i + 1;
        return i;
    }

    private static class Request {
        Uri fileUri;
        Frame frame;
        RequestType type;

        Request(Uri uri) {
            this.fileUri = uri;
            this.type = RequestType.rStartNewMovie;
        }

        Request(Frame frame) {
            this.frame = frame;
            this.type = RequestType.rAddMovieFrame;
        }

        Request() {
            this.type = RequestType.rCloseMoveFile;
        }
    }

    VideoWriter(OnVideoListener onVideoListener) {
        this._listener = onVideoListener;
    }

    /* JADX INFO: renamed from: com.alipay.face.photinus.VideoWriter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$alipay$face$photinus$VideoWriter$RequestType;

        static {
            int[] iArr = new int[RequestType.values().length];
            $SwitchMap$com$alipay$face$photinus$VideoWriter$RequestType = iArr;
            try {
                iArr[RequestType.rStartNewMovie.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alipay$face$photinus$VideoWriter$RequestType[RequestType.rAddMovieFrame.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alipay$face$photinus$VideoWriter$RequestType[RequestType.rCloseMoveFile.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    boolean isRunning() {
        return this._running;
    }

    void openNewFile(Uri uri, int i, int i2) {
        if (this._running) {
            return;
        }
        this._running = true;
        this._acceptsNewRequests = true;
        this._previewWidth = i;
        this._previewHeight = i2;
        pushRequest(new Request(uri));
        this._runLoopThread.start();
    }

    void closeFile() {
        pushRequest(new Request());
    }

    void addFrame(Frame frame) {
        pushRequest(new Request(frame));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Request popRequest() {
        synchronized (this._globalRequestQueue) {
            if (this._globalRequestQueue.isEmpty()) {
                return null;
            }
            return this._globalRequestQueue.remove(0);
        }
    }

    private void pushRequest(Request request) {
        synchronized (this._globalRequestQueue) {
            if (this._acceptsNewRequests) {
                if (request.type == RequestType.rCloseMoveFile) {
                    this._acceptsNewRequests = false;
                }
                this._globalRequestQueue.add(request);
            }
        }
    }

    private static byte[] rotateYUV420Degree180(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (int i6 = i3 - 1; i6 >= 0; i6--) {
            bArr2[i5] = bArr[i6];
            i5++;
        }
        for (int i7 = i4 - 1; i7 >= i3; i7 -= 2) {
            int i8 = i5 + 1;
            bArr2[i5] = bArr[i7];
            i5 = i8 + 1;
            bArr2[i8] = bArr[i7 - 1];
        }
        return bArr2;
    }

    public static byte[] rotateYUV420Degree270(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i * i2;
        int i5 = (i4 * 3) / 2;
        byte[] bArr2 = new byte[i5];
        if (i == 0 && i2 == 0) {
            i4 = 0;
            i3 = 0;
        } else {
            i3 = i2 >> 1;
        }
        int i6 = 0;
        for (int i7 = 0; i7 < i; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i2; i9++) {
                bArr2[i6] = bArr[i8 + i7];
                i6++;
                i8 += i;
            }
        }
        for (int i10 = 0; i10 < i; i10 += 2) {
            int i11 = i4;
            for (int i12 = 0; i12 < i3; i12++) {
                int i13 = i11 + i10;
                if (i13 >= i5 - 2) {
                    break;
                }
                bArr2[i6] = bArr[i13];
                bArr2[i6 + 1] = bArr[i13 + 1];
                i6 += 2;
                i11 += i;
            }
        }
        return rotateYUV420Degree180(bArr2, i, i2);
    }

    private int calcBitRate(int i) {
        int i2 = (int) (i * BPP * this._previewHeight * this._previewWidth);
        Log.i(TAG, String.format("bitrate=%5.2f[Mbps]", Float.valueOf((i2 / 1024.0f) / 1024.0f)));
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createEncoder() {
        try {
            MediaCodecInfo mediaCodecInfoSelectCodec = selectCodec(MIME_TYPE);
            if (mediaCodecInfoSelectCodec == null) {
                Log.e(TAG, "Unable to find an appropriate codec for video/avc");
                return;
            }
            Log.d(TAG, "found codec: " + mediaCodecInfoSelectCodec.getName());
            Log.d(TAG, "found colorFormat: " + colorFormatDesc(21));
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MIME_TYPE, this._previewHeight, this._previewWidth);
            mediaFormatCreateVideoFormat.setInteger("color-format", 21);
            mediaFormatCreateVideoFormat.setInteger("bitrate", BIT_RATE);
            mediaFormatCreateVideoFormat.setInteger("frame-rate", 30);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 10);
            Log.d(TAG, "format: " + mediaFormatCreateVideoFormat);
            MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(mediaCodecInfoSelectCodec.getName());
            this._Encoder = mediaCodecCreateByCodecName;
            mediaCodecCreateByCodecName.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this._Encoder.start();
            this._Muxer = new MediaMuxer(this._outputFile.getAbsolutePath(), 0);
        } catch (Throwable th) {
            th.printStackTrace();
            this._running = false;
            this._acceptsNewRequests = false;
            this._globalRequestQueue.clear();
        }
    }

    private static MediaCodecInfo selectCodec(String str) {
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
        return null;
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

    private static int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        for (int i = 0; i < capabilitiesForType.colorFormats.length; i++) {
            int i2 = capabilitiesForType.colorFormats[i];
            if (isRecognizedFormat(i2)) {
                return i2;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void encodeNextFrame(int i, Frame frame) {
        ByteBuffer[] inputBuffers = this._Encoder.getInputBuffers();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int iDequeueInputBuffer = this._Encoder.dequeueInputBuffer(c.i);
        if (iDequeueInputBuffer >= 0) {
            long jComputePresentationTime = computePresentationTime(i);
            if (frame == null) {
                this._Encoder.queueInputBuffer(iDequeueInputBuffer, 0, 0, jComputePresentationTime, 4);
                drainEncoder(true, bufferInfo);
                return;
            }
            byte[] bArr = frame.data;
            ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr);
            this._Encoder.queueInputBuffer(iDequeueInputBuffer, 0, bArr.length, jComputePresentationTime, 0);
            drainEncoder(false, bufferInfo);
            return;
        }
        Log.i(TAG, "input buffer not available");
    }

    private void drainEncoder(boolean z, MediaCodec.BufferInfo bufferInfo) {
        if (z) {
            try {
                this._Encoder.signalEndOfInputStream();
            } catch (Exception unused) {
            }
        }
        ByteBuffer[] outputBuffers = this._Encoder.getOutputBuffers();
        while (true) {
            int iDequeueOutputBuffer = this._Encoder.dequeueOutputBuffer(bufferInfo, c.i);
            if (iDequeueOutputBuffer == -1) {
                if (!z) {
                    return;
                } else {
                    Log.i(TAG, "no output available, spinning to await EOS");
                }
            } else if (iDequeueOutputBuffer == -3) {
                outputBuffers = this._Encoder.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                if (this._MuxerStarted) {
                    throw new RuntimeException("format changed twice");
                }
                MediaFormat outputFormat = this._Encoder.getOutputFormat();
                Log.i(TAG, "encoder output format changed: " + outputFormat);
                this._TrackIndex = this._Muxer.addTrack(outputFormat);
                this._Muxer.start();
                this._MuxerStarted = true;
            } else if (iDequeueOutputBuffer < 0) {
                Log.i(TAG, "unexpected result from encoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
            } else {
                ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                }
                if ((bufferInfo.flags & 2) != 0) {
                    Log.d(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
                    bufferInfo.size = 0;
                }
                if (bufferInfo.size != 0) {
                    if (!this._MuxerStarted) {
                        throw new RuntimeException("muxer hasn't started");
                    }
                    byteBuffer.position(bufferInfo.offset);
                    byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    Log.d(TAG, "BufferInfo: " + bufferInfo.offset + b.aj + bufferInfo.size + b.aj + bufferInfo.presentationTimeUs);
                    try {
                        this._Muxer.writeSampleData(this._TrackIndex, byteBuffer, bufferInfo);
                    } catch (Exception unused2) {
                        Log.i(TAG, "Too many frames");
                    }
                }
                this._Encoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
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

    public static long computePresentationTime(int i) {
        return ((i * 1000000) / 30) + TTDownloadField.CALL_DOWNLOAD_MODEL_SET_ID;
    }
}
