package com.alipay.zoloz.toyger.face;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.zoloz.image.ToygerImageUtil;
import com.alipay.zoloz.toyger.ToygerAttr;
import com.alipay.zoloz.toyger.ToygerBaseService;
import com.alipay.zoloz.toyger.ToygerCallback;
import com.alipay.zoloz.toyger.ToygerLog;
import com.alipay.zoloz.toyger.algorithm.Astro;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.algorithm.TGSensorFrame;
import com.alipay.zoloz.toyger.algorithm.Toyger;
import com.alipay.zoloz.toyger.algorithm.ToygerCameraConfig;
import com.alipay.zoloz.toyger.algorithm.ToygerConfig;
import com.alipay.zoloz.toyger.blob.BlobManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_zkfv_1ts_1tj;
import toygerservice.a;
import toygerservice.k;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerFaceService extends ToygerBaseService<ToygerFaceCallback, ToygerFaceState, ToygerFaceAttr, ToygerFaceInfo, ToygerFaceAlgorithmConfig> {
    public static final String KEY_TOYGER_ACTION_TYPE = "toyger_action";
    public static final String KEY_TOYGER_DEPTH_FRAME = "toyger_depth_frame";
    public static final String KEY_TOYGER_FRAME = "toyger_frame";
    public static final String KEY_TOYGER_TIME_CONSUMING = "toygerTimeConsuming";
    public static final String KEY_TOYGER_UID = "uid";
    private static final int QUEUE_LENGTH = 1;
    private static final String TOYGER_ACTION_DEREG = "dereg";
    private static final String TOYGER_ACTION_LOCAL_MATCHING = "local";
    private static final String TOYGER_ACTION_REGISTER = "register";
    private static final String TOYGER_ACTION_REMOTE_MATCHING = "remote";
    public static int TOYGER_CALLBACK_CODE_LOG = 0;
    public static int TOYGER_CALLBACK_CODE_UPDATE = 1;
    public static final int TOYGER_EVENT_INTERRUPT = 1;
    public static final int TOYGER_EVENT_RESUME = 2;
    private static final int TOYGER_LOGLEVEL_SAVE = 2;
    private static String licenses;
    private static byte[] model;
    private static long totalDropFrame;
    private static long totalFrame;
    private static long totalProcessFrame;
    private static Boolean toygerIsBusy;
    private FaceBlobManager blobManager;
    private ToygerRunnable cacheRunnable;
    private ByteBuffer depthImageBuffer;
    private ByteBuffer irImageBuffer;
    private ByteBuffer rgbImagebuffer;
    private HashMap<Integer, Double> frameSizeMap = new HashMap<>(3);
    private ToygerFaceAttr fppAttr = new ToygerFaceAttr();
    private HashMap<String, Object> callBackObject = new HashMap<>();
    private HandlerThread mProcessThread = null;
    private HandlerThread mSensorThread = null;
    private HandlerThread mCallbackThread = null;
    private Handler mCallbackThreadHandler = null;
    private Handler mProcessThreadHandler = null;
    private Handler mSensorThreadHandler = null;
    private final BlockingQueue<TGSensorFrame> mSensorQueue = new LinkedBlockingDeque(1);
    private final FrameProcessor mFrameProcessor = new FrameProcessor();
    private final AtomicBoolean mSensorRunning = new AtomicBoolean(false);
    private final AtomicBoolean mImageProcessing = new AtomicBoolean(false);
    private final Semaphore semaphore = new Semaphore(1);
    private float[] cacheGyroData = null;
    private float[] cacheRotationData = null;
    private long cacheTimeStamp = 0;
    public boolean initResult = false;
    private boolean localMatching = false;
    private String cacheUid = null;
    private String cacheToken = null;
    private Context cacheContext = null;
    private String cacheCommand = null;
    private String cacheIfaaMsg = null;
    private String faceStateSignature = null;
    private String faceAttrSignature = null;
    private String tgFrameSignature = null;
    private boolean isMirror = false;
    private ToygerFaceAlgorithmConfig faceAlgConfig = null;

    /* JADX INFO: renamed from: com.alipay.zoloz.toyger.face.ToygerFaceService$10, reason: invalid class name */
    public class AnonymousClass10 implements Runnable {
        public final /* synthetic */ ToygerFaceAttr val$attr;
        public final /* synthetic */ TGFrame val$frame;

        public AnonymousClass10(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr) {
            this.val$frame = tGFrame;
            this.val$attr = toygerFaceAttr;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageDigest messageDigest;
            System.currentTimeMillis();
            Astro.sub_message_channel_ack(this.val$frame.data, r0.length);
            StringBuilder sb = new StringBuilder();
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                try {
                    messageDigest.update(this.val$frame.data);
                } catch (NoSuchAlgorithmException unused) {
                }
            } catch (NoSuchAlgorithmException unused2) {
                messageDigest = null;
            }
            if (messageDigest != null) {
                for (byte b : messageDigest.digest()) {
                    sb.append(String.format("%02X", Byte.valueOf(b)));
                }
            }
            a.b.add(sb.toString().toLowerCase());
            TGFrame tGFrame = this.val$frame;
            Bitmap bitmapTgFrameToBitmap = ToygerImageUtil.tgFrameToBitmap(tGFrame, tGFrame.width, 1.0f, true ^ ToygerFaceService.this.isMirror);
            ToygerFaceService.this.blobManager.bestLightImage = ToygerImageUtil.tgFrameToBlob(this.val$frame, ToygerFaceService.this.blobManager.desireWidth, ToygerFaceService.this.blobManager.compressRate, ToygerFaceService.this.blobManager.compressFormat, ToygerFaceService.this.isMirror);
            ToygerFaceAttr toygerFaceAttr = new ToygerFaceAttr(this.val$attr, ToygerFaceService.this.isMirror);
            if (ToygerFaceService.this.mToygerCallback != null) {
                ((ToygerFaceCallback) ToygerFaceService.this.mToygerCallback).onHighQualityFrame(bitmapTgFrameToBitmap, toygerFaceAttr);
                if (ToygerFaceService.this.blobManager.isNano) {
                    ((ToygerFaceCallback) ToygerFaceService.this.mToygerCallback).onAsyncUpload(-100, ToygerFaceService.this.blobManager.generateFaceBlob(this.val$frame, this.val$attr), ToygerFaceService.this.blobManager.getKey(), ToygerFaceService.this.blobManager.isUTF8());
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alipay.zoloz.toyger.face.ToygerFaceService$11, reason: invalid class name */
    public class AnonymousClass11 implements Runnable {
        public final /* synthetic */ Map val$extIno;
        public final /* synthetic */ List val$infos;
        public final /* synthetic */ int val$result;

        public AnonymousClass11(List list, Map map, int i) {
            this.val$infos = list;
            this.val$extIno = map;
            this.val$result = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageDigest messageDigest;
            System.currentTimeMillis();
            Astro.sub_message_channel_ack(((ToygerFaceInfo) this.val$infos.get(0)).frame.data, ((ToygerFaceInfo) this.val$infos.get(0)).frame.data.length);
            StringBuilder sb = new StringBuilder();
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                try {
                    messageDigest.update(((ToygerFaceInfo) this.val$infos.get(0)).frame.data);
                } catch (NoSuchAlgorithmException unused) {
                }
            } catch (NoSuchAlgorithmException unused2) {
                messageDigest = null;
            }
            boolean z = true;
            if (messageDigest != null) {
                for (byte b : messageDigest.digest()) {
                    sb.append(String.format("%02X", Byte.valueOf(b)));
                }
            }
            a.b.add(sb.toString().toLowerCase());
            ToygerFaceService.this.blobManager.isMirror = ToygerFaceService.this.isMirror;
            System.currentTimeMillis();
            byte[] bArrGenerateBlob = ToygerFaceService.this.blobManager.generateBlob(this.val$infos, this.val$extIno);
            byte[] key = ToygerFaceService.this.blobManager.getKey();
            boolean z2 = this.val$result < 0;
            if (bArrGenerateBlob != null) {
                int length = bArrGenerateBlob.length;
            }
            long unused3 = ToygerFaceService.totalDropFrame = 0L;
            long unused4 = ToygerFaceService.totalProcessFrame = 0L;
            long unused5 = ToygerFaceService.totalFrame = 0L;
            if (ToygerFaceService.this.mToygerCallback != null) {
                int iIntValue = 0;
                for (int i = 0; i < a.c.size(); i++) {
                    iIntValue += a.c.get(i).intValue();
                }
                for (int i2 = 0; i2 < a.d.size(); i2++) {
                    iIntValue += a.d.get(i2).intValue();
                }
                boolean z3 = iIntValue == 0;
                boolean zSub_message_channel_init = Astro.sub_message_channel_init();
                boolean z4 = k.b.a;
                if (z3 && zSub_message_channel_init && z4) {
                    z = z2;
                } else {
                    a.a.clear();
                    a.b.clear();
                    a.c.clear();
                    a.d.clear();
                }
                ToygerFaceService toygerFaceService = ToygerFaceService.this;
                if (z) {
                    ((ToygerFaceCallback) toygerFaceService.mToygerCallback).onAsyncUpload(-200, bArrGenerateBlob, key, ToygerFaceService.this.blobManager.isUTF8());
                } else {
                    ((ToygerFaceCallback) toygerFaceService.mToygerCallback).onComplete(iIntValue, bArrGenerateBlob, key, ToygerFaceService.this.blobManager.isUTF8());
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alipay.zoloz.toyger.face.ToygerFaceService$4, reason: invalid class name */
    public class AnonymousClass4 extends ToygerRunnable {
        public AnonymousClass4(List list, TGDepthFrame tGDepthFrame, ToygerAttr toygerAttr) {
            super(list, tGDepthFrame, toygerAttr);
        }

        @Override // com.alipay.zoloz.toyger.face.ToygerRunnable, java.lang.Runnable
        public void run() {
            super.run();
            ToygerFaceService.this.mImageProcessing.set(false);
        }
    }

    static {
        java2jni_do_not_delete_this_library_zkfv_1ts_1tj.loadLibrary();
        toygerIsBusy = Boolean.FALSE;
        totalFrame = 0L;
        totalProcessFrame = 0L;
        totalDropFrame = 0L;
    }

    private FaceBlobManager blobManagerByExtInfo(Map<String, Object> map, ToygerFaceBlobConfig toygerFaceBlobConfig) {
        FaceBlobManager faceBlobManager;
        String str = (String) map.get(ToygerBaseService.KEY_META_SERIALIZER);
        String str2 = (str != null ? Integer.parseInt(str) : 2) != 2 ? "com.alipay.zoloz.toyger.face.FaceBlobManagerJson" : "com.alipay.zoloz.toyger.face.FaceBlobManagerPb";
        boolean zBooleanValue = map.containsKey(ToygerBaseService.KEY_ENABLE_ENCUP) ? ((Boolean) map.get(ToygerBaseService.KEY_ENABLE_ENCUP)).booleanValue() : true;
        try {
            Constructor<?> declaredConstructor = Class.forName(str2).getDeclaredConstructor(ToygerFaceBlobConfig.class, Boolean.TYPE, ToygerFaceCallback.class);
            declaredConstructor.setAccessible(true);
            faceBlobManager = (FaceBlobManager) declaredConstructor.newInstance(toygerFaceBlobConfig, Boolean.valueOf(zBooleanValue), this.mToygerCallback);
        } catch (Throwable unused) {
            ToygerLog.w("TOYGER_FLOW_ANDROID", "blobManagerClassName error");
            faceBlobManager = null;
        }
        faceBlobManager.compressFormat = toygerFaceBlobConfig.uploadImageType;
        faceBlobManager.compressRate = toygerFaceBlobConfig.upload_compress_rate;
        return faceBlobManager;
    }

    private double calculateNeedSize(TGFrame tGFrame, TGDepthFrame tGDepthFrame) {
        double d = -1.0d;
        Double dValueOf = Double.valueOf(-1.0d);
        if (tGFrame != null) {
            dValueOf = this.frameSizeMap.get(Integer.valueOf(tGFrame.frameMode));
        }
        if (tGDepthFrame != null) {
            dValueOf = this.frameSizeMap.get(7);
        }
        if (dValueOf != null) {
            return dValueOf.doubleValue();
        }
        if (tGFrame != null) {
            double d2 = tGFrame.width * tGFrame.height;
            double d3 = 0.0d;
            if (tGFrame.frameType != 2) {
                switch (tGFrame.frameMode) {
                    case 0:
                        d3 = 1.5d;
                        break;
                    case 1:
                    case 3:
                        d3 = 4.0d;
                        break;
                    case 2:
                    case 4:
                        d3 = 3.0d;
                        break;
                    case 5:
                        d3 = 1.0d;
                        break;
                    case 6:
                        d3 = 2.0d;
                        break;
                }
                double d4 = d2 * d3;
                dValueOf = new Double(d4);
                d = d4;
            }
            this.frameSizeMap.put(Integer.valueOf(tGFrame.frameMode), dValueOf);
        }
        if (tGDepthFrame == null) {
            return d;
        }
        double d5 = ((double) (tGDepthFrame.width * tGDepthFrame.height)) * 2.0d;
        this.frameSizeMap.put(7, new Double(d5));
        return d5;
    }

    private String collectionStringFromBlobConfig(ToygerFaceBlobConfig toygerFaceBlobConfig) {
        String strConcat = "";
        if (toygerFaceBlobConfig.collection != null) {
            for (int i = 0; i < toygerFaceBlobConfig.collection.size(); i++) {
                strConcat = strConcat.length() > 0 ? strConcat.concat("#").concat(toygerFaceBlobConfig.collection.get(i)) : toygerFaceBlobConfig.collection.get(i);
            }
        }
        return strConcat;
    }

    private String combinationStringFromFaceAlgorithm(ToygerFaceAlgorithmConfig toygerFaceAlgorithmConfig) {
        String strConcat = "";
        for (int i = 0; i < toygerFaceAlgorithmConfig.liveness_combination.size(); i++) {
            strConcat = strConcat.length() > 0 ? strConcat.concat("#").concat(toygerFaceAlgorithmConfig.liveness_combination.get(i)) : toygerFaceAlgorithmConfig.liveness_combination.get(i);
        }
        return strConcat;
    }

    private void deepCopyIRFrame(TGFrame tGFrame) {
        if (tGFrame != null) {
            int iCalculateNeedSize = (int) calculateNeedSize(tGFrame, null);
            if (this.irImageBuffer == null) {
                this.irImageBuffer = Toyger.allocIRFrameData(iCalculateNeedSize);
            }
            ByteBuffer byteBuffer = tGFrame.byteBuffer;
            if (byteBuffer != null) {
                int iLimit = byteBuffer.limit();
                int iMin = Math.min(iCalculateNeedSize, iLimit);
                this.irImageBuffer.rewind();
                if (iLimit == iCalculateNeedSize) {
                    this.irImageBuffer.put(byteBuffer);
                } else {
                    this.irImageBuffer.put(byteBuffer.array(), 0, iMin);
                }
                this.irImageBuffer.rewind();
                Toyger.fetchIRFrameData(tGFrame.byteBuffer, iMin);
            }
        }
    }

    private void deepCopyRGBFrame(TGFrame tGFrame) {
        if (tGFrame != null) {
            int iCalculateNeedSize = (int) calculateNeedSize(tGFrame, null);
            ByteBuffer byteBuffer = tGFrame.byteBuffer;
            if (this.rgbImagebuffer == null) {
                this.rgbImagebuffer = Toyger.allocRGBFrameData(iCalculateNeedSize);
            }
            if (byteBuffer != null) {
                int iLimit = byteBuffer.limit();
                int iMin = Math.min(iLimit, iCalculateNeedSize);
                byteBuffer.position();
                this.rgbImagebuffer.rewind();
                if (iLimit == iCalculateNeedSize) {
                    this.rgbImagebuffer.put(byteBuffer);
                } else {
                    this.rgbImagebuffer.put(byteBuffer.array(), 0, iMin);
                }
                this.rgbImagebuffer.rewind();
                Toyger.fetchRGBFrameData(this.rgbImagebuffer, iMin);
            }
        }
    }

    private void deepCopyTGDepthFrame(TGDepthFrame tGDepthFrame) {
        if (tGDepthFrame != null) {
            int iCalculateNeedSize = (int) calculateNeedSize(null, tGDepthFrame);
            ByteBuffer byteBuffer = tGDepthFrame.byteBuffer;
            if (this.depthImageBuffer == null) {
                this.depthImageBuffer = Toyger.allocDepthFrameData(iCalculateNeedSize);
            }
            if (byteBuffer != null) {
                int iLimit = byteBuffer.limit();
                int iMin = Math.min(iLimit, iCalculateNeedSize);
                this.depthImageBuffer.rewind();
                if (iLimit != iCalculateNeedSize) {
                    this.depthImageBuffer.put(byteBuffer.array(), 0, iMin);
                } else {
                    this.depthImageBuffer.put(byteBuffer);
                }
                this.depthImageBuffer.rewind();
                Toyger.fetchDepthFrameData(this.depthImageBuffer.asShortBuffer(), iMin);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initToygerNative(Context context) {
        HashMap map = new HashMap(4);
        this.faceStateSignature = ToygerFaceState.class.getName().replace(".", "/");
        this.faceAttrSignature = ToygerFaceAttr.class.getName().replace(".", "/");
        this.tgFrameSignature = TGFrame.class.getName().replace(".", "/");
        map.put("Capacity", "3");
        String str = this.faceStateSignature;
        if (str != null && this.faceAttrSignature != null && this.tgFrameSignature != null && model != null) {
            map.put("StateSignature", str);
            map.put("AttrSignature", this.faceAttrSignature);
            map.put("FrameSignature", this.tgFrameSignature);
            map.put("Algorithm", "Face");
            Toyger.loadLibrary(context);
            return Toyger.init(context, model, licenses, context.getPackageName(), map);
        }
        String str2 = str == null ? "faceStateSignature == null;" : "";
        if (this.faceAttrSignature == null) {
            str2 = str2 + "faceAttrSignature == null;";
        }
        if (this.tgFrameSignature == null) {
            String str3 = str2 + "tgFrameSignature == null;";
        }
        byte[] bArr = model;
        return false;
    }

    private static boolean load(Context context) {
        byte[] model2 = readModel(context);
        model = model2;
        return model2 != null;
    }

    private ToygerConfig parseToygerConfig(ToygerFaceAlgorithmConfig toygerFaceAlgorithmConfig, ToygerFaceBlobConfig toygerFaceBlobConfig, Map<String, Object> map) {
        ToygerCameraConfig toygerCameraConfig;
        ToygerConfig toygerConfig = toygerFaceAlgorithmConfig.toToygerConfig();
        toygerConfig.livenessConfig.collection = collectionStringFromBlobConfig(toygerFaceBlobConfig);
        toygerConfig.livenessConfig.livenessCombinations = combinationStringFromFaceAlgorithm(toygerFaceAlgorithmConfig);
        Map<String, List<Float>> map2 = toygerFaceAlgorithmConfig.threshold;
        if (map2 != null) {
            List<Float> list = map2.get(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS);
            if (list != null && list.size() == 1) {
                toygerConfig.livenessConfig.dragonflyMax = list.get(0).floatValue();
            }
            List<Float> list2 = map2.get(ToygerFaceAlgorithmConfig.GEMINI_LIVENESS);
            if (list2 != null) {
                for (int i = 0; i < list2.size(); i++) {
                    float fFloatValue = list2.get(i).floatValue();
                    if (i == 0) {
                        toygerConfig.livenessConfig.geminiMin = fFloatValue;
                    } else if (i == 1) {
                        toygerConfig.livenessConfig.geminiMax = fFloatValue;
                    }
                }
            }
            List<Float> list3 = map2.get(ToygerFaceAlgorithmConfig.BAT_LIVENESS);
            if (list3 != null && list3.size() > 0) {
                toygerConfig.livenessConfig.batLivenessThreshold = list3.get(0).floatValue();
            }
            List<Float> list4 = map2.get(ToygerFaceAlgorithmConfig.ZFACE_BLINK_LIVENESS);
            if (list4 != null && list4.size() == 2) {
                toygerConfig.livenessConfig.eye_blink_threshold = list4.get(0).floatValue();
                toygerConfig.livenessConfig.eye_occlusion_threshold = list4.get(1).floatValue();
            }
            if (map != null && (toygerCameraConfig = (ToygerCameraConfig) map.get(ToygerBaseService.KEY_CAMERA_CONFIG)) != null) {
                toygerConfig.cameraConfig = toygerCameraConfig;
                toygerCameraConfig.isMirror = this.isMirror;
            }
        }
        return toygerConfig;
    }

    public static boolean preLoad(Context context) {
        if (model != null) {
            return true;
        }
        return load(context);
    }

    private static byte[] readFile(Context context, String str) {
        byte[] bArr = null;
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                if (inputStreamOpen.available() != 0) {
                    bArr = new byte[inputStreamOpen.available()];
                    inputStreamOpen.read(bArr);
                }
                inputStreamOpen.close();
            } finally {
            }
        } catch (IOException unused) {
        }
        return bArr;
    }

    private static byte[] readFileByPath(String str) {
        File file = new File(str);
        byte[] bArr = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int iAvailable = fileInputStream.available();
            if (iAvailable <= 0) {
                return null;
            }
            bArr = new byte[iAvailable];
            fileInputStream.read(bArr);
            return bArr;
        } catch (IOException unused) {
            return bArr;
        }
    }

    private static byte[] readModel(Context context) {
        return readFile(context, ToygerBaseService.ASSET_FACE);
    }

    private int setupWorkingThread() {
        a.HandlerC0120a handlerC0120a;
        if (this.mProcessThread == null) {
            a.f = 3000L;
            synchronized (a.e) {
                handlerC0120a = a.g.get("ToygerProcessQueue");
                if (handlerC0120a == null || handlerC0120a.c == null) {
                    a.b bVar = new a.b("ToygerProcessQueue");
                    bVar.start();
                    a.HandlerC0120a handlerC0120a2 = new a.HandlerC0120a("ToygerProcessQueue", bVar);
                    a.g.put("ToygerProcessQueue", handlerC0120a2);
                    handlerC0120a = handlerC0120a2;
                }
                handlerC0120a.removeMessages(0);
                handlerC0120a.b++;
            }
            a.b bVar2 = handlerC0120a.c;
            this.mProcessThread = bVar2;
            if (bVar2.getState() == Thread.State.NEW) {
                this.mProcessThread.start();
            }
            if (this.mProcessThread == null) {
                return -1;
            }
        }
        if (this.mProcessThreadHandler == null) {
            this.mProcessThreadHandler = new Handler(this.mProcessThread.getLooper());
        }
        if (this.mCallbackThread == null) {
            HandlerThread handlerThread = new HandlerThread("ToygerCallbackQueue");
            this.mCallbackThread = handlerThread;
            handlerThread.start();
            if (this.mCallbackThread == null) {
                return -3;
            }
        }
        if (this.mCallbackThreadHandler == null) {
            this.mCallbackThreadHandler = new Handler(this.mCallbackThread.getLooper()) { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i == 0) {
                        return;
                    }
                    if (i != 1) {
                        super.handleMessage(message);
                        return;
                    }
                    HashMap map = (HashMap) message.obj;
                    ToygerFaceState toygerFaceState = (ToygerFaceState) map.get("TOYGER_CALLBACK_STATE_KEY");
                    ToygerFaceAttr toygerFaceAttr = new ToygerFaceAttr((ToygerFaceAttr) map.get("TOYGER_CALLBACK_ATTR_KEY"), ToygerFaceService.this.isMirror);
                    HashMap map2 = new HashMap(2);
                    map2.put(ToygerFaceService.KEY_TOYGER_FRAME, ToygerFaceService.this.mFrameProcessor.getTgFrame());
                    map2.put(ToygerFaceService.KEY_TOYGER_DEPTH_FRAME, ToygerFaceService.this.mFrameProcessor.getTgDepthFrame());
                    if (ToygerFaceService.this.mToygerCallback != null) {
                        ((ToygerFaceCallback) ToygerFaceService.this.mToygerCallback).onStateUpdated(toygerFaceState, toygerFaceAttr, map2);
                    }
                }
            };
        }
        return 0;
    }

    private void startGyroServiceWithFaceBlobConfig(ToygerFaceBlobConfig toygerFaceBlobConfig) {
        if (toygerFaceBlobConfig.collection.contains("Gyro")) {
            if (this.mSensorThread == null) {
                HandlerThread handlerThread = new HandlerThread("ToygerSensorProcessQueue");
                this.mSensorThread = handlerThread;
                handlerThread.start();
            }
            if (this.mSensorThreadHandler == null) {
                this.mSensorThreadHandler = new Handler(this.mSensorThread.getLooper());
            }
            super.handleEventTriggered(-8, "");
        }
    }

    private void startLocalMatching(Context context, Map<String, Object> map, ToygerFaceAlgorithmConfig toygerFaceAlgorithmConfig) {
    }

    public void addMonitorImage(TGFrame tGFrame) {
        FaceBlobManager faceBlobManager = this.blobManager;
        if (faceBlobManager != null) {
            faceBlobManager.addMonitorImage(tGFrame);
        }
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService
    public boolean config(Map<String, Object> map) {
        if (map != null) {
            String str = (String) map.get(ToygerBaseService.KEY_ALGORITHM_CONFIG);
            String str2 = (String) map.get(ToygerBaseService.KEY_PUBLIC_KEY);
            String str3 = (String) map.get(ToygerBaseService.KEY_UPLOAD_CONFIG);
            String str4 = (String) map.get(ToygerBaseService.KEY_IS_MIRROR);
            boolean z = false;
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                return false;
            }
            ToygerFaceBlobConfig toygerFaceBlobConfigFrom = ToygerFaceBlobConfig.from(str3, str2);
            this.blobManager = blobManagerByExtInfo(map, toygerFaceBlobConfigFrom);
            this.faceAlgConfig = ToygerFaceAlgorithmConfig.from(str);
            this.blobManager.isNano = toygerFaceBlobConfigFrom.collection.contains(BlobManager.SUB_TYPE_NANO);
            this.faceAlgConfig.liveness_combination.contains(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS);
            if (this.blobManager.isNano && !this.faceAlgConfig.liveness_combination.contains(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS)) {
                this.faceAlgConfig.liveness_combination.add(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS);
                if (!this.faceAlgConfig.threshold.containsKey(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Float.valueOf(0.5f));
                    this.faceAlgConfig.threshold.put(ToygerFaceAlgorithmConfig.DRAGONFLY_LIVENESS, arrayList);
                }
            }
            if (str2 != null) {
                if (str4 != null && Boolean.parseBoolean(str4)) {
                    z = true;
                }
                this.isMirror = z;
                final ToygerConfig toygerConfig = parseToygerConfig(this.faceAlgConfig, toygerFaceBlobConfigFrom, map);
                startGyroServiceWithFaceBlobConfig(toygerFaceBlobConfigFrom);
                if (toygerConfig.livenessConfig.collection.contains(BlobManager.SUB_TYPE_NANO) || model == null) {
                    model = readModel(this.cacheContext);
                }
                Handler handler = this.mProcessThreadHandler;
                if (handler != null) {
                    handler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (ToygerFaceService.this.localMatching) {
                                return;
                            }
                            ToygerFaceService toygerFaceService = ToygerFaceService.this;
                            toygerFaceService.initResult = toygerFaceService.initToygerNative(toygerFaceService.cacheContext);
                            ToygerFaceService toygerFaceService2 = ToygerFaceService.this;
                            if (toygerFaceService2.initResult) {
                                ((ToygerFaceCallback) toygerFaceService2.mToygerCallback).onEvent(-100, null);
                                Toyger.config(this, toygerConfig);
                            } else {
                                ((ToygerFaceCallback) toygerFaceService2.mToygerCallback).onEvent(-4, null);
                            }
                            byte[] unused = ToygerFaceService.model = null;
                        }
                    });
                }
            }
        }
        return true;
    }

    public native Map<String, Object> generateBlob(Map<String, Object> map);

    public Map<String, Object> generateMonitorBlob() {
        byte[] monitorBlob;
        System.currentTimeMillis();
        FaceBlobManager faceBlobManager = this.blobManager;
        if (faceBlobManager == null || (monitorBlob = faceBlobManager.getMonitorBlob()) == null) {
            return null;
        }
        byte[] key = this.blobManager.getKey();
        HashMap map = new HashMap(3);
        map.put("content", monitorBlob);
        map.put(ToygerBaseService.KEY_RES_9_KEY, key);
        map.put(ToygerBaseService.KEY_RES_9_IS_UTF8, Boolean.valueOf(this.blobManager.isUTF8()));
        return map;
    }

    public Map<String, Object> generateVideoFileBlob(String str) {
        byte[] fileIdBlob;
        System.currentTimeMillis();
        FaceBlobManager faceBlobManager = this.blobManager;
        if (faceBlobManager == null || (fileIdBlob = faceBlobManager.getFileIdBlob(str)) == null) {
            return null;
        }
        byte[] key = this.blobManager.getKey();
        HashMap map = new HashMap(3);
        map.put("content", fileIdBlob);
        map.put(ToygerBaseService.KEY_RES_9_KEY, key);
        map.put(ToygerBaseService.KEY_RES_9_IS_UTF8, Boolean.valueOf(this.blobManager.isUTF8()));
        return map;
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService, com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public native void handleCaptureCompleted(int i, List<ToygerFaceInfo> list, Map<String, Object> map);

    public void handleDepthInfoReady(final ToygerDepthInfo toygerDepthInfo) {
        this.mCallbackThreadHandler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.8
            @Override // java.lang.Runnable
            public void run() {
                FaceBlobManager faceBlobManager = ToygerFaceService.this.blobManager;
                ToygerDepthInfo toygerDepthInfo2 = toygerDepthInfo;
                faceBlobManager.depthInfo = toygerDepthInfo2;
                if (toygerDepthInfo2 != null) {
                    ToygerFaceService.this.blobManager.bestDepthImage = ToygerFaceService.this.blobManager.processDepthInfo(toygerDepthInfo);
                }
            }
        });
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService, com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public void handleEventTriggered(final int i, final String str) {
        this.mCallbackThreadHandler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.7
            @Override // java.lang.Runnable
            public void run() {
                ToygerFaceService.super.handleEventTriggered(i, str);
            }
        });
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService, com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public native void handleInfoReady(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr);

    public void handleInfraRedInfoReady(final TGFrame tGFrame) {
        this.mCallbackThreadHandler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.9
            @Override // java.lang.Runnable
            public void run() {
                FaceBlobManager faceBlobManager = ToygerFaceService.this.blobManager;
                TGFrame tGFrame2 = tGFrame;
                faceBlobManager.irFrame = tGFrame2;
                if (tGFrame2 != null) {
                    ToygerFaceService.this.blobManager.bestIRImage = ToygerFaceService.this.blobManager.processIRFrameInfo(tGFrame, !ToygerFaceService.this.isMirror);
                }
            }
        });
    }

    public void handleLocalMatchingEvent(int i) {
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService, com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public void handleLog(int i, HashMap<String, Object> map) {
        Handler handler = this.mCallbackThreadHandler;
        if (handler != null) {
            this.mCallbackThreadHandler.dispatchMessage(handler.obtainMessage(TOYGER_CALLBACK_CODE_LOG, 0, i, map));
        }
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService, com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public void handleScanCompleted(int i, List<ToygerFaceInfo> list, Map<String, Object> map) {
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService, com.alipay.zoloz.toyger.algorithm.IToygerDelegate
    public void handleStateUpdated(ToygerFaceState toygerFaceState, ToygerFaceAttr toygerFaceAttr) {
        this.callBackObject.put("TOYGER_CALLBACK_STATE_KEY", toygerFaceState);
        this.callBackObject.put("TOYGER_CALLBACK_ATTR_KEY", toygerFaceAttr);
        this.mCallbackThreadHandler.dispatchMessage(this.mCallbackThreadHandler.obtainMessage(TOYGER_CALLBACK_CODE_UPDATE, this.callBackObject));
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService
    public /* bridge */ /* synthetic */ boolean init(Context context, ToygerCallback toygerCallback, String str, String str2, Map map) {
        return init(context, (ToygerFaceCallback) toygerCallback, str, str2, (Map<String, Object>) map);
    }

    public boolean init(Context context, ToygerFaceCallback toygerFaceCallback, String str, String str2, Map<String, Object> map) {
        boolean zInit = init(context, false, toygerFaceCallback);
        if (!zInit) {
            return zInit;
        }
        if (map == null) {
            map = new HashMap<>(2);
        }
        map.put(ToygerBaseService.KEY_ALGORITHM_CONFIG, str);
        map.put(ToygerBaseService.KEY_UPLOAD_CONFIG, str2);
        return config(map);
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService
    public boolean init(Context context, boolean z, ToygerFaceCallback toygerFaceCallback) {
        toygerIsBusy = Boolean.TRUE;
        if (setupWorkingThread() < 0) {
            return false;
        }
        this.localMatching = z;
        this.mToygerCallback = toygerFaceCallback;
        this.cacheContext = context;
        if (model == null) {
            load(context);
        }
        if (this.mProcessThreadHandler != null) {
            return true;
        }
        ((ToygerFaceCallback) this.mToygerCallback).onEvent(-4, null);
        this.initResult = false;
        return true;
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService
    public native boolean processImage(List<TGFrame> list, TGDepthFrame tGDepthFrame);

    public boolean processSensorData(final TGSensorFrame tGSensorFrame) {
        if (tGSensorFrame != null && this.mSensorQueue.offer(tGSensorFrame)) {
            Handler handler = this.mSensorThreadHandler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (ToygerFaceService.this.mSensorRunning.get()) {
                                TGSensorFrame tGSensorFrame2 = (TGSensorFrame) ToygerFaceService.this.mSensorQueue.poll(1L, TimeUnit.SECONDS);
                                if (tGSensorFrame.gyroData != null) {
                                    ToygerFaceService.this.cacheGyroData = tGSensorFrame2.gyroData;
                                    ToygerFaceService.this.cacheTimeStamp = tGSensorFrame2.timeStamp;
                                }
                                if (tGSensorFrame.rotationData != null) {
                                    ToygerFaceService.this.cacheRotationData = tGSensorFrame2.rotationData;
                                }
                                if (tGSensorFrame2 == null || ToygerFaceService.this.cacheGyroData == null) {
                                    return;
                                }
                                Toyger.processSensorData(ToygerFaceService.this.cacheGyroData, null, ToygerFaceService.this.cacheRotationData, ToygerFaceService.this.cacheTimeStamp);
                                ToygerFaceService.this.cacheGyroData = null;
                                ToygerFaceService.this.cacheTimeStamp = 0L;
                            }
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        }
        return true;
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService
    public void release() {
        toygerIsBusy = Boolean.FALSE;
        System.currentTimeMillis();
        this.mSensorRunning.set(false);
        HandlerThread handlerThread = this.mSensorThread;
        if (handlerThread != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                handlerThread.quitSafely();
            } else {
                handlerThread.quit();
            }
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        System.currentTimeMillis();
        Handler handler = this.mProcessThreadHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.6
                @Override // java.lang.Runnable
                public void run() {
                    if (!ToygerFaceService.this.localMatching) {
                        if (ToygerFaceService.this.rgbImagebuffer != null) {
                            Toyger.releaseRGBFrameData(ToygerFaceService.this.rgbImagebuffer);
                            ToygerFaceService.this.rgbImagebuffer = null;
                        }
                        if (ToygerFaceService.this.depthImageBuffer != null) {
                            Toyger.releaseDepthFrameData(ToygerFaceService.this.depthImageBuffer);
                            ToygerFaceService.this.depthImageBuffer = null;
                        }
                        if (ToygerFaceService.this.irImageBuffer != null) {
                            Toyger.releaseIRFrameData(ToygerFaceService.this.irImageBuffer);
                            ToygerFaceService.this.irImageBuffer = null;
                        }
                        Toyger.reset();
                        Toyger.release();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        System.currentTimeMillis();
        try {
            countDownLatch.await(1L, TimeUnit.SECONDS);
            HandlerThread handlerThread2 = this.mProcessThread;
            if (handlerThread2 != null) {
                a.a(handlerThread2);
            }
            this.mProcessThread = null;
            this.mProcessThreadHandler.removeCallbacksAndMessages(null);
            this.mProcessThreadHandler = null;
            HandlerThread handlerThread3 = this.mCallbackThread;
            if (handlerThread3 != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    handlerThread3.quitSafely();
                } else {
                    handlerThread3.quit();
                }
            }
            this.mCallbackThread = null;
            Handler handler2 = this.mCallbackThreadHandler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
                this.mCallbackThreadHandler = null;
            }
        } catch (InterruptedException unused) {
        }
        this.mImageProcessing.set(false);
        this.localMatching = false;
        this.cacheGyroData = null;
        this.cacheRotationData = null;
        this.cacheContext = null;
        this.cacheUid = null;
        this.cacheToken = null;
        this.cacheCommand = null;
        this.mToygerCallback = null;
        this.tgFrameSignature = null;
        this.faceAttrSignature = null;
        this.faceStateSignature = null;
        this.cacheRunnable = null;
    }

    @Override // com.alipay.zoloz.toyger.ToygerBaseService
    public void reset() {
        System.currentTimeMillis();
        this.mImageProcessing.set(false);
        Handler handler = this.mProcessThreadHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.alipay.zoloz.toyger.face.ToygerFaceService.5
                @Override // java.lang.Runnable
                public void run() {
                    Toyger.reset();
                }
            });
        }
    }
}
