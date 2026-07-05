package com.aliyun.aliyunface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.deviceid.DeviceTokenClient;
import com.alipay.face.photinus.Frame;
import com.alipay.face.photinus.OnVideoWriteListener;
import com.alipay.face.photinus.PhotinusCallbackListener;
import com.alipay.face.photinus.PhotinusEmulator;
import com.alipay.face.photinus.VideoEncoderHelper;
import com.alipay.face.photinus.VideoFormatConfig;
import com.alipay.zoloz.toyger.ToygerAttr;
import com.alipay.zoloz.toyger.ToygerBaseService;
import com.alipay.zoloz.toyger.ToygerLog;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.algorithm.ToygerCameraConfig;
import com.alipay.zoloz.toyger.face.ToygerFaceAttr;
import com.alipay.zoloz.toyger.face.ToygerFaceCallback;
import com.alipay.zoloz.toyger.face.ToygerFaceService;
import com.alipay.zoloz.toyger.face.ToygerFaceState;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.api.ZIMRetCallback;
import com.aliyun.aliyunface.api.ZIMUICustomListener;
import com.aliyun.aliyunface.camera.CameraData;
import com.aliyun.aliyunface.camera.CameraParams;
import com.aliyun.aliyunface.camera.ICameraCallback;
import com.aliyun.aliyunface.camera.ICameraInterface;
import com.aliyun.aliyunface.config.AndroidClientConfig;
import com.aliyun.aliyunface.config.DeviceSetting;
import com.aliyun.aliyunface.config.OSSConfig;
import com.aliyun.aliyunface.config.Protocol;
import com.aliyun.aliyunface.config.ProtocolContent;
import com.aliyun.aliyunface.config.Upload;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.NetworkEnv;
import com.aliyun.aliyunface.network.model.OCRInfo;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.aliyun.aliyunface.utils.ZipUtils;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import toygerservice.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerPresenter implements ToygerFaceCallback, ICameraCallback {
    private static ToygerPresenter s_instance = new ToygerPresenter();
    private String alipayDeviceToken;
    private String aliyunDeviceToken;
    private Protocol androidClientProtocol;
    private Context ctx;
    private ToygerFaceAttr highQualityFaceAttr;
    private byte[] highQualityFaceImage;
    private String highQualityFaceImageMD5;
    private ICameraInterface mCameraInterface;
    private ToygerFaceService mToygerFaceService;
    private NetworkEnv networkEnv;
    private OSSConfig ossConfig;
    private Long photinusCollectionStartTime;
    private int photinusFrameRotation;
    private PhotinusEmulator photinusInstance;
    private String photinusMetadataFilePath;
    private String photinusVideoFilePath;
    private ZIMUICustomListener uiCustomListener;
    private Handler uiHandler;
    private boolean useMsgBox;
    private String videoFilePath;
    private String zimId;
    private ZIMRetCallback zimRetCallback;
    private WorkState mWorkState = WorkState.INIT;
    private AtomicBoolean isProcessingImage = new AtomicBoolean(false);
    private boolean isToygerCameraInited = false;
    private Map<String, Object> toygerParams = new HashMap();
    private boolean useVideo = false;
    private ArrayList<ByteBuffer> videoFrames = new ArrayList<>();
    private boolean usePhotinus = false;
    private int photinusType = 0;
    private boolean useSmoothPhotinus = false;
    private int photinusExpectedResolutionX = 0;
    private boolean forwardFrameToPhotinus = false;
    private boolean photinusIsFirstFrame = true;
    private OCRInfo ocrInfo = null;

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public void onAsyncUpload(int i, byte[] bArr, byte[] bArr2, boolean z) {
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public boolean onHighQualityFrame(Bitmap bitmap, ToygerFaceAttr toygerFaceAttr) {
        return true;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onSurfaceCreated() {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onSurfaceDestroyed() {
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public /* bridge */ /* synthetic */ boolean onStateUpdated(d dVar, ToygerAttr toygerAttr, Map map) {
        return onStateUpdated((ToygerFaceState) dVar, (ToygerFaceAttr) toygerAttr, (Map<String, Object>) map);
    }

    public static ToygerPresenter getInstance() {
        return s_instance;
    }

    public boolean init(Context context, Handler handler, ICameraInterface iCameraInterface) {
        Upload photinusCfg;
        System.out.println("Toyger presenter init");
        clear();
        this.ctx = context;
        this.uiHandler = handler;
        this.mCameraInterface = iCameraInterface;
        ToygerFaceService toygerFaceService = new ToygerFaceService();
        this.mToygerFaceService = toygerFaceService;
        if (!toygerFaceService.init(context, false, (ToygerFaceCallback) this)) {
            System.out.println("Toyger mToygerFaceService init fail");
            return false;
        }
        AndroidClientConfig androidClientConfig = getAndroidClientConfig();
        if (androidClientConfig != null && (photinusCfg = androidClientConfig.getPhotinusCfg()) != null) {
            this.usePhotinus = photinusCfg.photinusVideo;
            this.photinusType = photinusCfg.photinusType;
            this.useSmoothPhotinus = photinusCfg.enableSmoothTransition;
        }
        if (this.usePhotinus) {
            this.photinusInstance = new PhotinusEmulator();
        }
        if (androidClientConfig == null) {
            System.out.println("Toyger clientConfig null");
            RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "ClientConfigError", "status", "ClientCfg null");
            return false;
        }
        System.out.println("Toyger init success");
        initToygerFaceService(androidClientConfig);
        this.mWorkState = WorkState.FACE_CAPTURING;
        return true;
    }

    private void clear() {
        this.highQualityFaceAttr = null;
        this.highQualityFaceImage = null;
        this.mWorkState = WorkState.INIT;
        this.isProcessingImage = new AtomicBoolean(false);
        this.isToygerCameraInited = false;
        this.videoFrames = new ArrayList<>();
        this.videoFilePath = "";
        this.usePhotinus = false;
        this.photinusMetadataFilePath = null;
        this.photinusVideoFilePath = null;
        this.photinusExpectedResolutionX = 0;
        this.forwardFrameToPhotinus = false;
        this.photinusIsFirstFrame = true;
        this.photinusInstance = null;
        this.photinusFrameRotation = 0;
        this.photinusCollectionStartTime = null;
    }

    private void initToygerFaceService(AndroidClientConfig androidClientConfig) {
        this.toygerParams.put("porting", "JRCloud");
        this.toygerParams.put(ToygerBaseService.KEY_PUBLIC_KEY, getPublicKey());
        this.toygerParams.put(ToygerBaseService.KEY_META_SERIALIZER, Integer.toString(1));
        this.toygerParams.put(ToygerBaseService.KEY_LOCAL_MATCHING_COMMAND, androidClientConfig.getVerifyMode());
        this.toygerParams.put(ToygerBaseService.KEY_ALGORITHM_CONFIG, androidClientConfig.getAlgorithm().toJSONString());
        this.toygerParams.put(ToygerBaseService.KEY_UPLOAD_CONFIG, androidClientConfig.getUpload().toJSONString());
    }

    public void onRelease() {
        ToygerFaceService toygerFaceService = this.mToygerFaceService;
        if (toygerFaceService != null) {
            try {
                toygerFaceService.release();
            } catch (Exception unused) {
            }
        }
    }

    private String getPublicKey() {
        return MiscUtil.readAssetsFile(this.ctx, ToygerConst.TOYGER_PUBLIC_KEY_NAME);
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onSurfaceChanged(double d, double d2) {
        Message messageObtain = Message.obtain();
        messageObtain.what = ToygerConst.TOYGER_UI_MSG_SURFACE_CHANGE;
        messageObtain.arg1 = (int) d;
        messageObtain.arg2 = (int) d2;
        this.uiHandler.sendMessage(messageObtain);
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onError(int i) {
        String str;
        switch (i) {
            case 100:
                str = ToygerConst.ZcodeConstants.ZCODE_ERROR_CAMERA_NO_DEVICE;
                break;
            case 101:
                str = ToygerConst.ZcodeConstants.ZCODE_ERROR_CAMERA_OPEN_FAILED;
                break;
            case 102:
                str = ToygerConst.ZcodeConstants.ZCODE_ERROR_CAMERA_STREAM_ERROR;
                break;
            default:
                str = "unkown Camera Code =>" + i;
                break;
        }
        sendError(str);
    }

    private void sendError(String str) {
        Message messageObtain = Message.obtain();
        messageObtain.what = ToygerConst.TOYGER_UI_MSG_ERROR_CODE;
        messageObtain.obj = str;
        this.uiHandler.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zipVideoFrames(int i, int i2, int i3) {
        try {
            String str = this.ctx.getFilesDir().getAbsolutePath() + "/toyger_verify_video.zip";
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            ZipUtils.zipFiles(this.videoFrames, file, i, i2, i3);
            getInstance().setVideoFilePath(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveVideoFrame(CameraData cameraData) {
        if (this.videoFrames.size() > 50) {
            this.videoFrames.remove(0);
        }
        this.videoFrames.add(ByteBuffer.wrap(getCameraByte(cameraData)));
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onPreviewFrame(CameraData cameraData) {
        ArrayList arrayList;
        if (!this.isToygerCameraInited) {
            initToygerCameraParams(cameraData);
            this.isToygerCameraInited = true;
        }
        if (WorkState.PHOTINUS == this.mWorkState && this.forwardFrameToPhotinus) {
            photinusOnPreviewFrame(cameraData);
            return;
        }
        if ((this.mWorkState == WorkState.FACE_CAPTURING || this.mWorkState == WorkState.FACE_CAPTURING_DARK) && !this.isProcessingImage.getAndSet(true)) {
            int iCalcAlgorithAngle = calcAlgorithAngle();
            if (getInstance().getUseVideo()) {
                saveVideoFrame(cameraData);
            }
            ByteBuffer colorData = cameraData.getColorData();
            if (colorData != null) {
                arrayList = new ArrayList();
                arrayList.add(new TGFrame(colorData, cameraData.getColorWidth(), cameraData.getColorHeight(), iCalcAlgorithAngle, cameraData.getColorFrameMode(), this.mWorkState == WorkState.FACE_CAPTURING ? 0 : 1));
            } else {
                arrayList = null;
            }
            ByteBuffer depthData = cameraData.getDepthData();
            TGDepthFrame tGDepthFrame = depthData != null ? new TGDepthFrame(depthData, cameraData.getDepthWidth(), cameraData.getDepthHeight(), iCalcAlgorithAngle) : null;
            ToygerFaceService toygerFaceService = this.mToygerFaceService;
            if (toygerFaceService != null) {
                toygerFaceService.processImage(arrayList, tGDepthFrame);
            }
            this.isProcessingImage.set(false);
        }
    }

    public void onToygerComplete() {
        this.mWorkState = WorkState.FACE_COMPLETED;
        if (this.uiHandler != null) {
            if (getInstance().getUseVideo()) {
                try {
                    VideoEncoderHelper.encode(this.ctx, this.videoFrames, this.mCameraInterface.getCameraRotation(), this.mCameraInterface.getColorWidth(), this.mCameraInterface.getColorHeight(), ToygerConst.TOYGER_VERIFY_VIDEO_NAME_NO_EXT, VideoFormatConfig.S, new OnVideoWriteListener() { // from class: com.aliyun.aliyunface.ToygerPresenter.1
                        @Override // com.alipay.face.photinus.OnVideoWriteListener
                        public void onVideoWriteSuccess(Uri uri) {
                            if (uri != null) {
                                ToygerPresenter.getInstance().setVideoFilePath(uri.getPath());
                            }
                            ToygerPresenter.this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_FACE_COMPLETE);
                        }

                        @Override // com.alipay.face.photinus.OnVideoWriteListener
                        public void onVideoWriteError(String str) {
                            if (ToygerPresenter.this.mCameraInterface != null) {
                                int iCalcAlgorithAngle = ToygerPresenter.this.calcAlgorithAngle();
                                ToygerPresenter.this.zipVideoFrames(ToygerPresenter.this.mCameraInterface.getColorWidth(), ToygerPresenter.this.mCameraInterface.getColorHeight(), iCalcAlgorithAngle);
                            }
                            ToygerPresenter.this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_FACE_COMPLETE);
                        }
                    });
                    return;
                } catch (Exception unused) {
                    this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_FACE_COMPLETE);
                    return;
                }
            }
            this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_FACE_COMPLETE);
        }
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public void onFaceBlobGenerate(String str) {
        try {
            String zimId = getInstance().getZimId();
            DeviceTokenClient deviceTokenClient = DeviceTokenClient.getInstance(getInstance().ctx);
            HashMap map = new HashMap();
            map.put("md5", str);
            map.put(DeviceTokenClient.INARGS_FACE_TRACEID, zimId);
            deviceTokenClient.initToken("zorro", "elBwppCSr9nB1LIQ", map, (DeviceTokenClient.InitResultListener) null);
        } catch (Exception unused) {
        }
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public void onFaceUploadBitmap(byte[] bArr, ToygerFaceAttr toygerFaceAttr, String str) {
        if (bArr == null || toygerFaceAttr == null) {
            return;
        }
        try {
            setHighQualityFaceAttr(toygerFaceAttr);
            setHighQualityFaceImage(bArr);
            setHighQualityFaceImageMD5(str);
        } catch (Exception unused) {
        }
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public boolean onComplete(int i, byte[] bArr, byte[] bArr2, boolean z) {
        if (this.usePhotinus) {
            this.mWorkState = WorkState.PHOTINUS;
            this.forwardFrameToPhotinus = true;
        } else {
            this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_START_LOADING);
            onToygerComplete();
        }
        return true;
    }

    public boolean onStateUpdated(ToygerFaceState toygerFaceState, ToygerFaceAttr toygerFaceAttr, Map<String, Object> map) {
        int i = toygerFaceState.messageCode;
        if (this.uiHandler == null) {
            return true;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = ToygerConst.TOYGER_UI_MSG_SHOW_TIPS;
        messageObtain.arg1 = i;
        this.uiHandler.sendMessage(messageObtain);
        return true;
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public boolean onEvent(int i, Map<String, Object> map) {
        String str;
        if (i == -4) {
            str = ToygerConst.ZcodeConstants.ZCODE_INIT_TOYGER_ERROR;
        } else if (i == -3) {
            str = ToygerConst.ZcodeConstants.ZCODE_LIVENESS_ERROR;
        } else {
            str = i != -2 ? "" : ToygerConst.ZcodeConstants.ZCODE_MODEL_LOAD_ERROR;
        }
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        sendError(str);
        return true;
    }

    @Override // com.alipay.zoloz.toyger.ToygerCallback
    public PointF onAlignDepthPoint(PointF pointF) {
        PointF pointF2 = new PointF(pointF.x, pointF.y);
        ICameraInterface iCameraInterface = this.mCameraInterface;
        if (iCameraInterface != null) {
            int colorWidth = iCameraInterface.getColorWidth();
            int colorHeight = this.mCameraInterface.getColorHeight();
            int depthWidth = this.mCameraInterface.getDepthWidth();
            int depthHeight = this.mCameraInterface.getDepthHeight();
            PointF pointF3 = new PointF();
            pointF3.x = pointF.x * colorWidth;
            pointF3.y = pointF.y * colorHeight;
            PointF pointFColorToDepth = this.mCameraInterface.colorToDepth(pointF3);
            pointF2.x = pointFColorToDepth.x / depthWidth;
            pointF2.y = pointFColorToDepth.y / depthHeight;
        }
        return pointF2;
    }

    private void initToygerCameraParams(CameraData cameraData) {
        ToygerCameraConfig toygerCameraConfig = new ToygerCameraConfig();
        ICameraInterface iCameraInterface = this.mCameraInterface;
        if (iCameraInterface != null) {
            this.toygerParams.put(ToygerBaseService.KEY_IS_MIRROR, Boolean.toString(iCameraInterface.isMirror()));
            CameraParams cameraParams = this.mCameraInterface.getCameraParams();
            if (cameraParams != null) {
                toygerCameraConfig.colorIntrin = cameraParams.color_intrin;
                toygerCameraConfig.depthIntrin = cameraParams.depth_intrin;
                toygerCameraConfig.color2depthExtrin = cameraParams.extrin;
                toygerCameraConfig.isAligned = cameraParams.isAligned;
            }
            toygerCameraConfig.roiRect = this.mCameraInterface.getROI();
        }
        this.toygerParams.put(ToygerBaseService.KEY_CAMERA_CONFIG, toygerCameraConfig);
        ToygerFaceService toygerFaceService = this.mToygerFaceService;
        if (toygerFaceService == null || toygerFaceService.config(this.toygerParams)) {
            return;
        }
        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "faceServiceConfig", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE);
        sendError(ToygerConst.ZcodeConstants.ZCODE_INIT_TOYGER_ERROR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calcAlgorithAngle() {
        int cameraViewRotation;
        ICameraInterface iCameraInterface = this.mCameraInterface;
        if (iCameraInterface != null) {
            cameraViewRotation = iCameraInterface.getCameraViewRotation();
            if (!isBackCamera()) {
                cameraViewRotation = (360 - cameraViewRotation) % 360;
            }
        } else {
            cameraViewRotation = 0;
        }
        AndroidClientConfig androidClientConfig = getAndroidClientConfig();
        if (androidClientConfig == null) {
            return cameraViewRotation;
        }
        DeviceSetting[] deviceSettings = androidClientConfig.getDeviceSettings();
        if (deviceSettings.length <= 0) {
            return cameraViewRotation;
        }
        DeviceSetting deviceSetting = deviceSettings[0];
        if (deviceSetting.isAlgorithmAuto()) {
            ICameraInterface iCameraInterface2 = this.mCameraInterface;
            if (iCameraInterface2 == null) {
                return cameraViewRotation;
            }
            int cameraViewRotation2 = iCameraInterface2.getCameraViewRotation();
            return !isBackCamera() ? (360 - cameraViewRotation2) % 360 : cameraViewRotation2;
        }
        return deviceSetting.getAlgorithmAngle();
    }

    private boolean isBackCamera() {
        DeviceSetting deviceSetting;
        AndroidClientConfig androidClientConfig = getAndroidClientConfig();
        if (androidClientConfig == null) {
            return false;
        }
        DeviceSetting[] deviceSettings = androidClientConfig.getDeviceSettings();
        return deviceSettings.length > 0 && (deviceSetting = deviceSettings[0]) != null && !deviceSetting.isCameraAuto() && deviceSetting.getCameraID() == 0;
    }

    private byte[] getCameraByte(CameraData cameraData) {
        ByteBuffer colorData = cameraData.getColorData();
        byte[] bArr = null;
        try {
            byte[] bArrArray = colorData.array();
            bArr = new byte[bArrArray.length];
            System.arraycopy(bArrArray, 0, bArr, 0, bArrArray.length);
            return bArr;
        } catch (ReadOnlyBufferException unused) {
            if (bArr != null) {
                return bArr;
            }
            byte[] bArr2 = new byte[colorData.remaining()];
            colorData.get(bArr2);
            return bArr2;
        } catch (UnsupportedOperationException unused2) {
            if (bArr != null) {
                return bArr;
            }
            byte[] bArr3 = new byte[colorData.remaining()];
            colorData.get(bArr3);
            return bArr3;
        } catch (Throwable th) {
            if (bArr == null) {
                colorData.get(new byte[colorData.remaining()]);
            }
            throw th;
        }
    }

    private void photinusOnPreviewFrame(CameraData cameraData) {
        if (this.photinusIsFirstFrame) {
            photinusInitialize(cameraData.getColorWidth(), cameraData.getColorHeight());
            this.photinusIsFirstFrame = false;
        }
        Frame frame = new Frame(getCameraByte(cameraData));
        frame.rotation = this.photinusFrameRotation;
        this.photinusInstance.addFrame(frame);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void photinusChangeColor(int i) {
        Message messageObtain = Message.obtain();
        messageObtain.what = ToygerConst.TOYGER_UI_MSG_CHANGE_PHOTINUS_COLOR;
        messageObtain.arg1 = i;
        this.uiHandler.sendMessage(messageObtain);
    }

    private boolean photinusInitialize(int i, int i2) {
        this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_START_PHOTINUS);
        if (!this.photinusInstance.initialize(this.ctx, i, i2, this.photinusType, 5, 1, this.useSmoothPhotinus)) {
            return false;
        }
        this.photinusFrameRotation = this.mCameraInterface.getCameraViewRotation();
        this.photinusCollectionStartTime = Long.valueOf(System.currentTimeMillis());
        this.photinusInstance.setCallbackListener(new PhotinusCallbackListener() { // from class: com.aliyun.aliyunface.ToygerPresenter.2
            @Override // com.alipay.face.photinus.PhotinusCallbackListener
            public void onDisplayRGB(int i3) {
                ToygerPresenter.this.photinusChangeColor(i3);
            }

            @Override // com.alipay.face.photinus.PhotinusCallbackListener
            public void onHasEnoughFrames() {
                ToygerLog.e("onHasEnoughFrames");
                ToygerPresenter.this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_START_LOADING);
                ToygerPresenter.this.photinusTakePictureAndGetExif();
            }

            @Override // com.alipay.face.photinus.PhotinusCallbackListener
            public void onFilesReady(Uri uri, Uri uri2) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "photinusFileReadyCost", "cost", Long.toString(System.currentTimeMillis() - ToygerPresenter.this.photinusCollectionStartTime.longValue()));
                if (uri != null) {
                    ToygerPresenter.this.photinusVideoFilePath = uri.getPath();
                }
                if (uri2 != null) {
                    ToygerPresenter.this.photinusMetadataFilePath = uri2.getPath();
                }
                ToygerPresenter.this.forwardFrameToPhotinus = false;
                ToygerPresenter.this.onToygerComplete();
            }

            @Override // com.alipay.face.photinus.PhotinusCallbackListener
            public void onLockCameraParameterRequest() {
                if (ToygerPresenter.this.mCameraInterface != null) {
                    ToygerPresenter.this.mCameraInterface.lockCameraWhiteBalanceAndExposure();
                }
                ToygerLog.e("onLockCameraParameterRequest");
            }

            @Override // com.alipay.face.photinus.PhotinusCallbackListener
            public void onEncoderErrorReport(String str) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "photinusEncoderError", MediationConstant.KEY_REASON, str);
            }

            @Override // com.alipay.face.photinus.PhotinusCallbackListener
            public void onTakePhotoErrorReport(String str) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "photinusTakePhotoError", MediationConstant.KEY_REASON, str);
            }
        });
        this.photinusInstance.begin();
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "photinusStart", "usePhotinus", String.valueOf(this.usePhotinus));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void photinusTakePictureAndGetExif() {
        this.photinusInstance.takePhoto(this.mCameraInterface.getCamera(), this.ctx);
    }

    public void sendResAndExit(String str) {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "sendErrorCode", "errCode", str);
        RecordService.getInstance().flush();
        if (WorkState.RET != getInstance().getWorkState()) {
            getInstance().setWorkState(WorkState.RET);
            ZIMRetCallback zimRetCallback = getInstance().getZimRetCallback();
            if (zimRetCallback != null) {
                zimRetCallback.onZimFinish(str);
            }
            this.highQualityFaceImage = null;
            this.highQualityFaceAttr = null;
            this.ctx = null;
        }
    }

    public byte[] getHighQualityFaceImage() {
        return this.highQualityFaceImage;
    }

    public void setHighQualityFaceImage(byte[] bArr) {
        this.highQualityFaceImage = bArr;
    }

    public ToygerFaceAttr getHighQualityFaceAttr() {
        return this.highQualityFaceAttr;
    }

    public void setHighQualityFaceAttr(ToygerFaceAttr toygerFaceAttr) {
        this.highQualityFaceAttr = toygerFaceAttr;
    }

    public String getHighQualityFaceImageMD5() {
        return this.highQualityFaceImageMD5;
    }

    public void setHighQualityFaceImageMD5(String str) {
        this.highQualityFaceImageMD5 = str;
    }

    public String getZimId() {
        return this.zimId;
    }

    public void setZimId(String str) {
        this.zimId = str;
    }

    public AndroidClientConfig getAndroidClientConfig() {
        Protocol protocol = this.androidClientProtocol;
        if (protocol == null || protocol.protocolContent == null) {
            return null;
        }
        return this.androidClientProtocol.protocolContent.androidClientConfig;
    }

    public void setAndroidClientProtocol(Protocol protocol) {
        this.androidClientProtocol = protocol;
    }

    public ProtocolContent getClientProtocolContent() {
        Protocol protocol = this.androidClientProtocol;
        if (protocol != null) {
            return protocol.protocolContent;
        }
        return null;
    }

    public ZIMRetCallback getZimRetCallback() {
        return this.zimRetCallback;
    }

    public void setZimRetCallback(ZIMRetCallback zIMRetCallback) {
        this.zimRetCallback = zIMRetCallback;
    }

    public OSSConfig getOssConfig() {
        return this.ossConfig;
    }

    public void setOssConfig(OSSConfig oSSConfig) {
        this.ossConfig = oSSConfig;
    }

    public boolean isUseMsgBox() {
        return this.useMsgBox;
    }

    public void setUseMsgBox(boolean z) {
        this.useMsgBox = z;
    }

    public void setNetworkEnv(NetworkEnv networkEnv) {
        this.networkEnv = networkEnv;
    }

    public NetworkEnv getNetworkEnv() {
        return this.networkEnv;
    }

    public void setUseVideo(boolean z) {
        this.useVideo = z;
    }

    public boolean getUseVideo() {
        return this.useVideo;
    }

    public void setVideoFilePath(String str) {
        this.videoFilePath = str;
    }

    public String getVideoFilePath() {
        return this.videoFilePath;
    }

    public String getPhotinusMetadataFilePath() {
        return this.photinusMetadataFilePath;
    }

    public String getPhotinusVideoFilePath() {
        return this.photinusVideoFilePath;
    }

    public void setUsePhotinus(boolean z) {
        this.usePhotinus = z;
    }

    public boolean isUsePhotinus() {
        return this.usePhotinus;
    }

    public WorkState getWorkState() {
        return this.mWorkState;
    }

    public WorkState setWorkState(WorkState workState) {
        WorkState workState2 = this.mWorkState;
        this.mWorkState = workState;
        return workState2;
    }

    public OCRInfo getOcrInfo() {
        return this.ocrInfo;
    }

    public void setOcrInfo(OCRInfo oCRInfo) {
        this.ocrInfo = oCRInfo;
    }

    public String getAlipayDeviceToken() {
        return this.alipayDeviceToken;
    }

    public void setAlipayDeviceToken(String str) {
        this.alipayDeviceToken = str;
    }

    public String getAliyunDeviceToken() {
        return this.aliyunDeviceToken;
    }

    public void setAliyunDeviceToken(String str) {
        this.aliyunDeviceToken = str;
    }

    public ZIMUICustomListener getUiCustomListener() {
        return this.uiCustomListener;
    }

    public void setUiCustomListener(ZIMUICustomListener zIMUICustomListener) {
        this.uiCustomListener = zIMUICustomListener;
    }
}
