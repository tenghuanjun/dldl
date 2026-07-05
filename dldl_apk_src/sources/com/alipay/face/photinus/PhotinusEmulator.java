package com.alipay.face.photinus;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alipay.face.photinus.VideoWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PhotinusEmulator implements VideoWriter.OnVideoListener {
    private static final String TAG = "ZOLOZ";
    private int _capturedIndex;
    private int[] _colorSequence;
    private int _currentColorIndex;
    private LightSensorListener _lightSensorListener;
    private PhotinusCallbackListener _listener;
    private Uri _metadataFileUri;
    private int[] _originalColorSequence;
    private int _padding;
    private int _repeatCount;
    private int _sequenceIndex;
    private boolean _smoothTransition;
    private long _tsStart;
    private Uri _videoFileUri;
    private int _videoHeight;
    private int _videoWidth;
    private VideoWriter _videoWriter;
    private final String[] photinusExtraExifTags = {"ApertureValue", "Contrast", "CustomRendered", "DefaultCropSize", "DeviceSettingDescription", "DigitalZoomRatio", "DateTime", "ExifVersion", "ExposureBiasValue", "ExposureIndex", "ExposureMode", "ExposureProgram", "FocalLength", "FocalLengthIn35mmFilm", "FocalPlaneResolutionUnit", "FocalPlaneXResolution", "FocalPlaneYResolution", "GainControl", "Make", "MeteringMode", "ReferenceBlackWhite", "Saturation", "ShutterSpeedValue", "SpectralSensitivity", "WhiteBalance", "WhitePoint", "BrightnessValue", "ExposureTime", "FNumber", "ISOSpeedRatings"};
    private final Object STATE_LOCK_TOKEN = new Object();
    private float _colorMagnitude = 1.0f;
    private float _colorOffset = 0.0f;
    private State _currentState = State.INVALID;
    private ArrayList<FrameMetadata> _metadata = new ArrayList<>();
    private FrameMetadata _referenceMetadata = new FrameMetadata();
    private HashMap<String, String> _extraExifData = new HashMap<>();
    private AtomicBoolean isCalledFileReady = new AtomicBoolean(false);
    private final Handler _mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable _videoEncodingTimeoutBlock = new Runnable() { // from class: com.alipay.face.photinus.PhotinusEmulator.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (PhotinusEmulator.this.STATE_LOCK_TOKEN) {
                if (PhotinusEmulator.this._currentState == State.COMPLETED) {
                    return;
                }
                PhotinusEmulator.this._currentState = State.AT_FAULT;
                if (PhotinusEmulator.this._listener == null || !PhotinusEmulator.this.isCalledFileReady.compareAndSet(false, true)) {
                    return;
                }
                PhotinusEmulator.this._listener.onEncoderErrorReport("Timeout");
                PhotinusEmulator.this._listener.onFilesReady(null, null);
            }
        }
    };

    private static int colorChannelTransform(int i, float f, float f2) {
        return (int) ((((i / 255.0f) * f) + f2) * 255.0f);
    }

    public enum State {
        INVALID(false, true),
        READY(false, false),
        AWAITING_FRAMES(false, false),
        AWAITING_COMPLETION(false, false),
        IN_COMPLETION(true, false),
        AT_FAULT(false, true),
        COMPLETED(true, true);

        public final boolean isComplete;
        public final boolean isTerminalState;

        State(boolean z, boolean z2) {
            this.isComplete = z;
            this.isTerminalState = z2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Float photinusGetExifNullableFloat(ExifInterface exifInterface, String str) {
        String attribute = exifInterface.getAttribute(str);
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return Float.valueOf((float) exifInterface.getAttributeDouble(str, Double.NaN));
    }

    public void takePhoto(Camera camera, final Context context) {
        if (camera == null) {
            complete();
            this._listener.onTakePhotoErrorReport("NullCameraInstance");
        } else {
            final ConditionVariable conditionVariable = new ConditionVariable();
            PhotinusHandler.getInstance().postTask(new Runnable() { // from class: com.alipay.face.photinus.PhotinusEmulator.2
                @Override // java.lang.Runnable
                public void run() {
                    conditionVariable.block(800L);
                    Log.e("kaifu", "block ");
                    PhotinusEmulator.this.complete();
                }
            });
            camera.takePicture(null, null, new Camera.PictureCallback() { // from class: com.alipay.face.photinus.PhotinusEmulator.3
                @Override // android.hardware.Camera.PictureCallback
                public void onPictureTaken(byte[] bArr, Camera camera2) {
                    try {
                        try {
                            File file = new File(context.getCacheDir(), "probe.jpg");
                            new FileOutputStream(file).write(bArr);
                            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
                            FrameMetadata frameMetadata = new FrameMetadata();
                            frameMetadata.exifISOSpeed = PhotinusEmulator.photinusGetExifNullableFloat(exifInterface, "ISOSpeedRatings");
                            frameMetadata.exifExposureTime = PhotinusEmulator.photinusGetExifNullableFloat(exifInterface, "ExposureTime");
                            frameMetadata.exifFNumber = PhotinusEmulator.photinusGetExifNullableFloat(exifInterface, "FNumber");
                            frameMetadata.exifBrightnessValue = PhotinusEmulator.photinusGetExifNullableFloat(exifInterface, "BrightnessValue");
                            frameMetadata.cameraHorizontalViewAngle = camera2.getParameters().getHorizontalViewAngle();
                            frameMetadata.cameraVerticalViewAngle = camera2.getParameters().getVerticalViewAngle();
                            HashMap<String, String> map = new HashMap<>();
                            for (String str : PhotinusEmulator.this.photinusExtraExifTags) {
                                String attribute = exifInterface.getAttribute(str);
                                if (attribute != null && !attribute.isEmpty()) {
                                    map.put(str, attribute);
                                }
                            }
                            if (!map.containsKey("DateTime")) {
                                map.put("DateTime", new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US).format(new Date()));
                            }
                            PhotinusEmulator.this.setReferenceMetadata(frameMetadata);
                            PhotinusEmulator.this.setExtraExifData(map);
                        } catch (FileNotFoundException unused) {
                            PhotinusEmulator.this._listener.onTakePhotoErrorReport("ReadSampleFailure");
                        } catch (IOException unused2) {
                            PhotinusEmulator.this._listener.onTakePhotoErrorReport("saveSampleFailure");
                        }
                    } finally {
                        Log.e("kaifu", "open ");
                        conditionVariable.open();
                    }
                }
            });
        }
    }

    public boolean initialize(Context context, int i, int i2, int i3, int i4, int i5, boolean z) {
        synchronized (this.STATE_LOCK_TOKEN) {
            boolean z2 = false;
            if (!this._currentState.isTerminalState) {
                return false;
            }
            PhotinusHandler.getInstance();
            Uri workingDirectory = getWorkingDirectory(context);
            File file = new File(workingDirectory.getPath());
            if ((!file.exists() || file.delete()) && !file.mkdir()) {
                z2 = true;
            }
            this._repeatCount = i5;
            this._padding = i4;
            this._videoWidth = i;
            this._videoHeight = i2;
            this._sequenceIndex = i3;
            this._smoothTransition = z;
            int[] iArrColorSequenceLookup = colorSequenceLookup(i3);
            this._originalColorSequence = iArrColorSequenceLookup;
            if (this._smoothTransition) {
                this._originalColorSequence = ColorHelper.arrayFromList(ColorHelper.smoothTransitionOfList(ColorHelper.appendGrayPaddingsToList(ColorHelper.prepareListForSmoothTransition(iArrColorSequenceLookup, 3), i4), 3));
            } else {
                this._originalColorSequence = addPaddingsToColorSequence(iArrColorSequenceLookup, this._padding);
            }
            this._colorSequence = this._originalColorSequence;
            String strCreateDataNameWithIndex = createDataNameWithIndex(this._sequenceIndex);
            this._videoFileUri = Uri.withAppendedPath(workingDirectory, strCreateDataNameWithIndex + ".mp4");
            this._metadataFileUri = Uri.withAppendedPath(workingDirectory, strCreateDataNameWithIndex + ".json");
            VideoWriter videoWriter = new VideoWriter(this);
            this._videoWriter = videoWriter;
            if (!z2) {
                videoWriter.openNewFile(this._videoFileUri, this._videoWidth, this._videoHeight);
            }
            this._lightSensorListener = new LightSensorListener(context);
            this._referenceMetadata = new FrameMetadata();
            this._extraExifData = new HashMap<>();
            this._currentState = State.READY;
            return true;
        }
    }

    public void discard() {
        synchronized (this.STATE_LOCK_TOKEN) {
            if (this._lightSensorListener != null) {
                this._lightSensorListener.discard();
            }
            if (this._videoWriter != null) {
                this._videoWriter.closeFile();
                this._videoWriter = null;
            }
            this._currentState = State.INVALID;
        }
    }

    public State getCurrentState() {
        State state;
        synchronized (this.STATE_LOCK_TOKEN) {
            state = this._currentState;
        }
        return state;
    }

    public void setCallbackListener(PhotinusCallbackListener photinusCallbackListener) {
        this._listener = photinusCallbackListener;
    }

    public void setReferenceMetadata(FrameMetadata frameMetadata) {
        this._referenceMetadata = frameMetadata;
    }

    public void setExtraExifData(HashMap<String, String> map) {
        this._extraExifData = map;
    }

    public void transformColorSequence(float f, float f2) {
        if (f2 < 0.0f || f + f2 > 1.0f) {
            Log.e(TAG, "Invalid color sequence transformation");
            return;
        }
        synchronized (this.STATE_LOCK_TOKEN) {
            if (this._currentState == State.READY) {
                int[] iArr = this._originalColorSequence;
                this._colorMagnitude = f;
                this._colorOffset = f2;
                this._colorSequence = colorSequenceTransform(iArr, f, f2);
            }
        }
    }

    public void begin() {
        synchronized (this.STATE_LOCK_TOKEN) {
            if (this._currentState != State.READY) {
                return;
            }
            this._currentColorIndex = 0;
            this._capturedIndex = -3;
            this._metadata.clear();
            this._currentState = State.AWAITING_FRAMES;
            this._tsStart = System.currentTimeMillis();
            PhotinusCallbackListener photinusCallbackListener = this._listener;
            if (photinusCallbackListener != null) {
                photinusCallbackListener.onLockCameraParameterRequest();
            }
        }
    }

    public void complete() {
        boolean z = !this._videoWriter.isRunning();
        synchronized (this.STATE_LOCK_TOKEN) {
            if (this._currentState == State.AWAITING_COMPLETION) {
                this._currentState = State.IN_COMPLETION;
                if (!z) {
                    this._videoWriter.closeFile();
                    this._mainHandler.postDelayed(this._videoEncodingTimeoutBlock, 5000L);
                }
            }
        }
        if (z && this._listener != null && this.isCalledFileReady.compareAndSet(false, true)) {
            this._listener.onEncoderErrorReport("AtFault");
            this._listener.onFilesReady(null, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0051 A[PHI: r4
  0x0051: PHI (r4v1 java.lang.Integer) = (r4v0 java.lang.Integer), (r4v3 java.lang.Integer) binds: [B:5:0x000a, B:13:0x0045] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addFrame(com.alipay.face.photinus.Frame r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.STATE_LOCK_TOKEN
            monitor-enter(r0)
            com.alipay.face.photinus.PhotinusEmulator$State r1 = r6._currentState     // Catch: java.lang.Throwable -> L68
            com.alipay.face.photinus.PhotinusEmulator$State r2 = com.alipay.face.photinus.PhotinusEmulator.State.AWAITING_FRAMES     // Catch: java.lang.Throwable -> L68
            r3 = 1
            r4 = 0
            r5 = 0
            if (r1 != r2) goto L51
            int r1 = r6._capturedIndex     // Catch: java.lang.Throwable -> L68
            if (r1 < 0) goto L26
            com.alipay.face.photinus.FrameMetadata r1 = r7.metadata     // Catch: java.lang.Throwable -> L68
            com.alipay.face.photinus.LightSensorListener r2 = r6._lightSensorListener     // Catch: java.lang.Throwable -> L68
            float r2 = r2.getReading()     // Catch: java.lang.Throwable -> L68
            r1.lightSensorValue = r2     // Catch: java.lang.Throwable -> L68
            com.alipay.face.photinus.VideoWriter r1 = r6._videoWriter     // Catch: java.lang.Throwable -> L68
            r1.addFrame(r7)     // Catch: java.lang.Throwable -> L68
            java.util.ArrayList<com.alipay.face.photinus.FrameMetadata> r1 = r6._metadata     // Catch: java.lang.Throwable -> L68
            com.alipay.face.photinus.FrameMetadata r7 = r7.metadata     // Catch: java.lang.Throwable -> L68
            r1.add(r7)     // Catch: java.lang.Throwable -> L68
        L26:
            int r7 = r6._currentColorIndex     // Catch: java.lang.Throwable -> L68
            int[] r1 = r6._colorSequence     // Catch: java.lang.Throwable -> L68
            int r1 = r1.length     // Catch: java.lang.Throwable -> L68
            if (r7 >= r1) goto L37
            int[] r7 = r6._colorSequence     // Catch: java.lang.Throwable -> L68
            int r1 = r6._currentColorIndex     // Catch: java.lang.Throwable -> L68
            r7 = r7[r1]     // Catch: java.lang.Throwable -> L68
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L68
        L37:
            int r7 = r6._capturedIndex     // Catch: java.lang.Throwable -> L68
            int r7 = r7 + r3
            r6._capturedIndex = r7     // Catch: java.lang.Throwable -> L68
            int r7 = r6._currentColorIndex     // Catch: java.lang.Throwable -> L68
            int r7 = r7 + r3
            r6._currentColorIndex = r7     // Catch: java.lang.Throwable -> L68
            boolean r7 = r6.privateHasEnoughFrames()     // Catch: java.lang.Throwable -> L68
            if (r7 == 0) goto L51
            r7 = -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L68
            com.alipay.face.photinus.PhotinusEmulator$State r7 = com.alipay.face.photinus.PhotinusEmulator.State.AWAITING_COMPLETION     // Catch: java.lang.Throwable -> L68
            r6._currentState = r7     // Catch: java.lang.Throwable -> L68
            goto L52
        L51:
            r3 = 0
        L52:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L68
            com.alipay.face.photinus.PhotinusCallbackListener r7 = r6._listener
            if (r7 == 0) goto L67
            if (r4 == 0) goto L60
            int r0 = r4.intValue()
            r7.onDisplayRGB(r0)
        L60:
            if (r3 == 0) goto L67
            com.alipay.face.photinus.PhotinusCallbackListener r7 = r6._listener
            r7.onHasEnoughFrames()
        L67:
            return
        L68:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L68
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.face.photinus.PhotinusEmulator.addFrame(com.alipay.face.photinus.Frame):void");
    }

    @Override // com.alipay.face.photinus.VideoWriter.OnVideoListener
    public void onWriteComplete(VideoWriter videoWriter) {
        synchronized (this.STATE_LOCK_TOKEN) {
            if (videoWriter == this._videoWriter || this._currentState == State.IN_COMPLETION) {
                this._mainHandler.removeCallbacks(this._videoEncodingTimeoutBlock);
                writeFrameMetadataToFile();
                this._currentState = State.COMPLETED;
                if (this._listener == null || !this.isCalledFileReady.compareAndSet(false, true)) {
                    return;
                }
                this._listener.onFilesReady(this._videoFileUri, this._metadataFileUri);
            }
        }
    }

    private boolean privateHasEnoughFrames() {
        return this._capturedIndex - this._colorSequence.length >= 0;
    }

    private void writeFrameMetadataToFile() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this._tsStart;
        HashMap map = new HashMap();
        map.put("data-source", "antfincloud-production-android-2");
        map.put("device-name", Build.MODEL);
        map.put("total-time-ms", Long.valueOf(jCurrentTimeMillis));
        map.put("sequence-index", Integer.valueOf(this._sequenceIndex));
        map.put("sequence-length", 5);
        map.put("sequence-periods", 3);
        map.put("sequence-repeat", Integer.valueOf(this._repeatCount));
        map.put("sequence-margin", Integer.valueOf(this._padding));
        map.put("sequence-extra", 0);
        map.put("color-magnitude", Float.valueOf(this._colorMagnitude));
        map.put("color-offset", Float.valueOf(this._colorOffset));
        map.put("video-width", Integer.valueOf(this._videoHeight));
        map.put("video-height", Integer.valueOf(this._videoWidth));
        if (this._smoothTransition) {
            map.put("smooth-transition-length", 3);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<FrameMetadata> it = this._metadata.iterator();
        while (it.hasNext()) {
            arrayList.add(mendFrameMetadata(it.next(), this._referenceMetadata));
        }
        map.put("frame-metadata", arrayList);
        map.put("extra-exif", this._extraExifData);
        writeBytesToPathUri(this._metadataFileUri, JSON.toJSONString(map).getBytes());
    }

    private static String createDataNameWithIndex(int i) {
        return new SimpleDateFormat("yy.M.dd.HH.mm.ss.SSS", Locale.US).format(new Date()) + String.format(Locale.US, "_n%d_k%d_d%d_p%d_i%d", 5, 2, 3, 3, Integer.valueOf(i));
    }

    private static Uri getWorkingDirectory(Context context) {
        return Uri.withAppendedPath(Uri.fromFile(context.getCacheDir()), "ZLZPhontinus");
    }

    private static int[] addPaddingsToColorSequence(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr2[i2] = -7829368;
        }
        int[] iArr3 = new int[iArr.length + i + i];
        System.arraycopy(iArr2, 0, iArr3, 0, i);
        System.arraycopy(iArr, 0, iArr3, iArr.length, i);
        System.arraycopy(iArr2, 0, iArr3, iArr.length + i, i);
        return iArr3;
    }

    private static int[] colorSequenceLookup(int i) {
        return new int[]{-16776961, -256, -256, -1, -16711936};
    }

    private static HashMap<String, Object> mendFrameMetadata(FrameMetadata frameMetadata, FrameMetadata frameMetadata2) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("light-sensor", Float.valueOf(frameMetadata.lightSensorValue));
        map.put("horizontal-view-angle", Float.valueOf(frameMetadata2.cameraHorizontalViewAngle));
        map.put("vertical-view-angle", Float.valueOf(frameMetadata2.cameraVerticalViewAngle));
        map.put("brightness-value", frameMetadata2.exifBrightnessValue);
        map.put("f-number", frameMetadata2.exifFNumber);
        map.put("iso-speed", frameMetadata2.exifISOSpeed);
        map.put("exposure-time", frameMetadata2.exifExposureTime);
        return map;
    }

    private static int[] colorSequenceTransform(int[] iArr, float f, float f2) {
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            iArr[i] = Color.rgb(colorChannelTransform(Color.red(i2), f, f2), colorChannelTransform(Color.green(i2), f, f2), colorChannelTransform(Color.blue(i2), f, f2));
        }
        return iArr;
    }

    private static void writeBytesToPathUri(Uri uri, byte[] bArr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(uri.getPath());
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
