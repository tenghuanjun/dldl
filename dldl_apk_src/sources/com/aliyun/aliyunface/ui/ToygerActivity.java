package com.aliyun.aliyunface.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.deviceid.DeviceTokenClient;
import com.alipay.zoloz.toyger.ToygerLog;
import com.alipay.zoloz.toyger.face.ToygerFaceAttr;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.WorkState;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.api.ZIMUICustomListener;
import com.aliyun.aliyunface.camera.CameraConstants;
import com.aliyun.aliyunface.camera.CameraSurfaceView;
import com.aliyun.aliyunface.camera.ICameraInterface;
import com.aliyun.aliyunface.config.AndroidClientConfig;
import com.aliyun.aliyunface.config.DeviceSetting;
import com.aliyun.aliyunface.config.OSSConfig;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.NetworkEnv;
import com.aliyun.aliyunface.network.NetworkPresenter;
import com.aliyun.aliyunface.network.OssClientHelper;
import com.aliyun.aliyunface.network.ZimValidateCallback;
import com.aliyun.aliyunface.network.model.OCRInfo;
import com.aliyun.aliyunface.ui.overlay.CommAlertOverlay;
import com.aliyun.aliyunface.ui.widget.CircleHoleView;
import com.aliyun.aliyunface.ui.widget.RoundProgressBar;
import com.aliyun.aliyunface.ui.widget.RoundProgressCallback;
import com.aliyun.aliyunface.utils.Avatar;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import net.security.device.api.SecurityDevice;
import net.security.device.api.SecuritySession;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerActivity extends Activity {
    private CameraSurfaceView mCameraSurfaceView;
    private final int TG_TIPS_DO_PHOTIUS = 100;
    private int faceScanRetryCnt = 0;
    private boolean isActivityPaused = false;
    private Button btnClose = null;
    private long faceScanStartTime = System.currentTimeMillis();
    private Handler uiHandler = new Handler(new Handler.Callback() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) throws Throwable {
            switch (message.what) {
                case ToygerConst.TOYGER_UI_MSG_SURFACE_CHANGE /* 901 */:
                    ToygerActivity.this.onSurfaceChanged(message.arg1, message.arg2);
                    break;
                case ToygerConst.TOYGER_UI_MSG_FACE_COMPLETE /* 902 */:
                    ToygerActivity.this.onFaceComplete();
                    break;
                case ToygerConst.TOYGER_UI_MSG_ERROR_CODE /* 903 */:
                    ToygerActivity.this.onErrorCode((String) message.obj);
                    break;
                case ToygerConst.TOYGER_UI_MSG_SHOW_TIPS /* 904 */:
                    ToygerActivity.this.showFaceTips(message.arg1);
                    break;
                case ToygerConst.TOYGER_UI_MSG_GUID_FACE_AUTH /* 905 */:
                    ToygerActivity.this.onGuidAuth();
                    break;
                case ToygerConst.TOYGER_UI_MSG_GUID_CLOSE /* 906 */:
                    ToygerActivity.this.onGuidClose();
                    break;
                case ToygerConst.TOYGER_UI_MSG_GUID_LOG /* 907 */:
                    ToygerActivity.this.onGuidLog((String) message.obj);
                    break;
                case ToygerConst.TOYGER_UI_MSG_GUID_LOAD_LOCAL /* 908 */:
                    ToygerActivity.this.onGuidLoadLocal();
                    break;
                case ToygerConst.TOYGER_UI_MSG_RETRY_FACE_SCAN /* 910 */:
                    ToygerActivity.this.retryFaceScan();
                    break;
                case ToygerConst.TOYGER_UI_MSG_START_PHOTINUS /* 911 */:
                    ToygerActivity.this.onStartPhotinus();
                    break;
                case ToygerConst.TOYGER_UI_MSG_CHANGE_PHOTINUS_COLOR /* 912 */:
                    ToygerActivity.this.onChangePhotinusColor(message);
                    break;
                case ToygerConst.TOYGER_UI_MSG_START_LOADING /* 913 */:
                    ToygerActivity.this.onStartLoading();
                    break;
            }
            return true;
        }
    });
    private WorkState prevWorkState = null;

    private interface MessageBoxCB {
        void onCancel();

        void onOK();
    }

    static /* synthetic */ int access$1508(ToygerActivity toygerActivity) {
        int i = toygerActivity.faceScanRetryCnt;
        toygerActivity.faceScanRetryCnt = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartLoading() {
        showAvatar(true);
        startFaceUploadProcess();
    }

    private int getIdByType(String str, String str2) {
        return getResources().getIdentifier(str, str2, getPackageName());
    }

    private int getId(String str) {
        return getIdByType(str, "id");
    }

    private int getDimension(String str) {
        return getIdByType(str, "dimen");
    }

    private int getLayout(String str) {
        return getIdByType(str, "layout");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getString(String str) {
        return getIdByType(str, "string");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onChangePhotinusColor(Message message) {
        int i = message.arg1;
        LinearLayout linearLayout = (LinearLayout) findViewById(getId("toyger_main_page"));
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(i);
        }
        CircleHoleView circleHoleView = (CircleHoleView) findViewById(getId("toyger_face_circle_hole_view"));
        if (circleHoleView != null) {
            circleHoleView.changeBackColor(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartPhotinus() {
        hideFaceTips();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGuidLoadLocal() {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "guidPage", "action", "load local page");
        ToygerWebView toygerWebView = (ToygerWebView) findViewById(getId("guid_web_page"));
        if (toygerWebView != null) {
            toygerWebView.setVisibility(0);
            toygerWebView.loadUrl("file:///android_asset/nav/facewelcome.html");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGuidAuth() {
        ToygerWebView toygerWebView = (ToygerWebView) findViewById(getId("guid_web_page"));
        if (toygerWebView != null) {
            toygerWebView.setVisibility(8);
        }
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "guidPage", "action", "click auth, and start toyger");
        initToyger();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGuidClose() {
        showMessageBox(getString("message_box_title_exit_tip"), getString("message_box_message_exit_tip"), getString("message_box_btn_ok_tip"), getString("message_box_btn_cancel_tip"), new MessageBoxCB() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.2
            @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
            public void onCancel() {
            }

            @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
            public void onOK() {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "userBack", "type", "guidPageClose");
                ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_USER_BACK);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGuidLog(String str) {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "guidPageLog", "guid_log", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFaceComplete() throws Throwable {
        byte[] fileContent;
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScanCost", "cost", String.valueOf(System.currentTimeMillis() - this.faceScanStartTime));
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScanComplete", "status", "face completed");
        ICameraInterface cameraInterface = getCameraInterface();
        if (cameraInterface != null) {
            cameraInterface.stopPreview();
        }
        stopFaceScanProcess(true);
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "uploadFaceImage", "status", "start upload face image");
        byte[] highQualityFaceImage = ToygerPresenter.getInstance().getHighQualityFaceImage();
        if (highQualityFaceImage == null) {
            sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_FACE_IMAGE_ERROR);
            return;
        }
        OSSConfig ossConfig = ToygerPresenter.getInstance().getOssConfig();
        if (ossConfig == null) {
            RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "uploadFaceImageFail", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE, "errMsg", "ossConfig is invalid");
            sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NET_INIT_ERROR);
            return;
        }
        OssClientHelper.getInstance().init();
        OssClientHelper.getInstance().addUploadFile(0, ossConfig.BucketName, ossConfig.FileNamePrefix + "_0.jpeg", highQualityFaceImage);
        boolean zIsUsePhotinus = ToygerPresenter.getInstance().isUsePhotinus();
        String str = ToygerConst.TOYGER_VERIFY_FILE_EXT_MP4;
        if (zIsUsePhotinus) {
            byte[] fileContent2 = MiscUtil.readFileContent(ToygerPresenter.getInstance().getPhotinusMetadataFilePath());
            byte[] fileContent3 = MiscUtil.readFileContent(ToygerPresenter.getInstance().getPhotinusVideoFilePath());
            if (fileContent2 != null && fileContent3 != null) {
                OssClientHelper.getInstance().addUploadFile(1, ossConfig.BucketName, MiscUtil.genOssFileName(ossConfig.FileNamePrefix, "colorinfo", "json"), fileContent2);
                OssClientHelper.getInstance().addUploadFile(2, ossConfig.BucketName, MiscUtil.genOssFileName(ossConfig.FileNamePrefix, "colorvideo", ToygerConst.TOYGER_VERIFY_FILE_EXT_MP4), fileContent3);
            } else {
                ToygerPresenter.getInstance().setUsePhotinus(false);
            }
        }
        String videoFilePath = ToygerPresenter.getInstance().getVideoFilePath();
        if (ToygerPresenter.getInstance().getUseVideo() && videoFilePath != null && !TextUtils.isEmpty(videoFilePath) && (fileContent = MiscUtil.readFileContent(videoFilePath)) != null && fileContent.length > 2) {
            if (fileContent[0] == 80 && fileContent[1] == 75) {
                str = ToygerConst.TOYGER_VERIFY_FILE_EXT_ZIP;
            }
            OssClientHelper.getInstance().addUploadFile(5, ossConfig.BucketName, MiscUtil.genOssFileName(ossConfig.FileNamePrefix, "verifyvideo", str), fileContent);
        }
        OssClientHelper.getInstance().startUploadFiles(this, ossConfig.OssEndPoint, ossConfig.AccessKeyId, ossConfig.AccessKeySecret, ossConfig.SecurityToken, new OssClientHelper.OssClientUploadListener() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.3
            @Override // com.aliyun.aliyunface.network.OssClientHelper.OssClientUploadListener
            public boolean onUploadSuccess(int i, String str2, String str3) {
                return true;
            }

            @Override // com.aliyun.aliyunface.network.OssClientHelper.OssClientUploadListener
            public boolean onUploadError(int i, String str2, String str3, String str4) {
                RecordService recordService = RecordService.getInstance();
                RecordLevel recordLevel = RecordLevel.LOG_ERROR;
                StringBuilder sb = new StringBuilder();
                String str5 = "";
                sb.append("");
                sb.append(i);
                recordService.recordEvent(recordLevel, "ossUploadFileError", "idx", sb.toString(), TTDownloadField.TT_FILE_NAME, str3, "errMsg", str4);
                if ("InvalidAccessKeyId".equalsIgnoreCase(str4)) {
                    ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OSS_TOKEN_INVALID);
                    return false;
                }
                if (i == 0) {
                    str5 = ToygerConst.ZcodeConstants.ZCODE_NET_UPLOAD_IMAGE_ERROR;
                } else if (1 == i) {
                    str5 = ToygerConst.ZcodeConstants.ZCODE_NET_UPLOAD_PHOTINUS_META_ERROR;
                } else if (2 == i) {
                    str5 = ToygerConst.ZcodeConstants.ZCODE_NET_UPLOAD_PHOTINUS_VIDEO_ERROR;
                } else if (5 == i) {
                    str5 = ToygerConst.ZcodeConstants.ZCODE_NET_UPLOAD_VERIFY_VIDEO_ERROR;
                }
                ToygerActivity.this.sendErrorCode(str5);
                return false;
            }

            @Override // com.aliyun.aliyunface.network.OssClientHelper.OssClientUploadListener
            public void onFinish(int i, int i2) throws Throwable {
                if (i == i2) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ossUploadFileSuccess", "count", "" + i2);
                    ToygerActivity.this.onFilesUploadSuccess();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorCode(String str) {
        Message messageObtain = Message.obtain();
        messageObtain.what = ToygerConst.TOYGER_UI_MSG_ERROR_CODE;
        messageObtain.obj = str;
        this.uiHandler.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFilesUploadSuccess() throws Throwable {
        String uploadFileName;
        String fileMd5;
        String str;
        String str2;
        String zimId = ToygerPresenter.getInstance().getZimId();
        byte[] highQualityFaceImage = ToygerPresenter.getInstance().getHighQualityFaceImage();
        ToygerFaceAttr highQualityFaceAttr = ToygerPresenter.getInstance().getHighQualityFaceAttr();
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "startNetVerify", "status", "start net verify");
        if (ToygerPresenter.getInstance().getUseVideo()) {
            fileMd5 = MiscUtil.getFileMd5(ToygerPresenter.getInstance().getVideoFilePath());
            uploadFileName = OssClientHelper.getInstance().getUploadFileName(5);
        } else {
            uploadFileName = "";
            fileMd5 = uploadFileName;
        }
        OSSConfig ossConfig = ToygerPresenter.getInstance().getOssConfig();
        String str3 = "/";
        if (ossConfig != null) {
            str3 = "/" + ossConfig.BucketName + "/";
        }
        String uploadFileName2 = OssClientHelper.getInstance().getUploadFileName(0);
        if (ToygerPresenter.getInstance().isUsePhotinus()) {
            str = str3 + OssClientHelper.getInstance().getUploadFileName(1);
            str2 = str3 + OssClientHelper.getInstance().getUploadFileName(2);
        } else {
            str = "";
            str2 = str;
        }
        OCRInfo ocrInfo = ToygerPresenter.getInstance().getOcrInfo();
        NetworkEnv networkEnv = ToygerPresenter.getInstance().getNetworkEnv();
        if (networkEnv == null) {
            sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC);
        } else {
            NetworkPresenter.zimValidate(networkEnv, zimId, ZIMFacade.getMetaInfos(this), uploadFileName, uploadFileName2, str, str2, highQualityFaceImage, highQualityFaceAttr, fileMd5, ocrInfo, new ZimValidateCallback() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.4
                @Override // com.aliyun.aliyunface.network.ZimValidateCallback
                public void onSuccess() {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "netVerifyRes", "status", "success", "verify", "success");
                    ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NET_VERIFY_SUCCESS);
                }

                @Override // com.aliyun.aliyunface.network.ZimValidateCallback
                public void onValidateFail(String str4, String str5, String str6) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "netVerifyRes", "status", "success", "verify", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE, "msg", "Face Compare onValidateFail, retCodeSub=" + str4 + " retMessageSub=" + str5 + " srvRes=" + str6);
                    ToygerActivity toygerActivity = ToygerActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append(ToygerConst.ZcodeConstants.ZCODE_VERIFY_FAIL_PREFIX);
                    sb.append(str4);
                    toygerActivity.sendErrorCode(sb.toString());
                }

                @Override // com.aliyun.aliyunface.network.ZimValidateCallback
                public void onServerError(String str4, String str5) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "netVerifyRes", "status", "fail", "msg", "Server Internal onError, code=" + str4 + " errMsg=" + str5);
                    ToygerActivity.this.sendErrorCode(str4);
                }

                @Override // com.aliyun.aliyunface.network.ZimValidateCallback
                public void onError(String str4, String str5) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "netVerifyRes", "status", "fail", "msg", "Face Compare onError, code=" + str4 + " errMsg=" + str5);
                    ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NET_VERIFY_ERROR);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onErrorCode(final String str) {
        if (TextUtils.isEmpty(str)) {
            str = ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC;
        }
        stopFaceScanProcess(true);
        stopFaceUploadProcess();
        if (ToygerPresenter.getInstance().isUseMsgBox()) {
            if (showErrorMsgBox(str, new MessageBoxCB() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.5
                @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                public void onCancel() {
                }

                @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                public void onOK() {
                    ToygerActivity.this.sendResponseAndFinish(str);
                }
            })) {
                return;
            }
            sendResponseAndFinish(str);
            return;
        }
        sendResponseAndFinish(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendResponseAndFinish(String str) {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ToygerActivityClose", "errCode", str);
        finish();
        ToygerPresenter.getInstance().sendResAndExit(str);
    }

    private boolean showErrorMsgBox(String str, MessageBoxCB messageBoxCB) {
        ToygerLog.e("showErrorMsgBox=>" + str);
        if (!str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_INIT_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_UPLOAD_IMAGE_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_VERIFY_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NETWORK_TIMEOUT) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NETWORK_ERROR)) {
            if (!str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_INIT_TOYGER_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_LIVENESS_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_MODEL_LOAD_ERROR)) {
                if (!str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_ERROR_CAMERA_NO_DEVICE) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_ERROR_CAMERA_OPEN_FAILED) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_ERROR_CAMERA_STREAM_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NO_FRANT_CAMERA) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_OS_VERSION_LOW) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_UNSUPPORTED_CPU)) {
                    return false;
                }
                showMessageBox(getString("message_box_title_not_support"), getString("message_box_message_not_support"), getString("message_box_btn_ok_tip"), -1, messageBoxCB);
                return true;
            }
            showMessageBox(getString("message_box_title_sys_error"), getString("message_box_message_sys_error"), getString("message_box_btn_ok_tip"), -1, messageBoxCB);
            return true;
        }
        showMessageBox(getString("message_box_title_network"), getString("message_box_message_network"), getString("message_box_btn_ok_tip"), -1, messageBoxCB);
        return true;
    }

    private void hideFaceTips() {
        TextView textView = (TextView) findViewById(getId("messageCode"));
        if (textView != null) {
            textView.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFaceTips(int i) {
        String string;
        if (i != 100) {
            switch (i) {
                case 1:
                    string = getString(getString("no_face"));
                    break;
                case 2:
                    string = getString(getString("distance_too_far"));
                    break;
                case 3:
                    string = getString(getString("distance_too_close"));
                    break;
                case 4:
                    string = getString(getString("face_not_in_center"));
                    break;
                case 5:
                case 6:
                    string = getString(getString("bad_pitch"));
                    break;
                case 7:
                    string = getString(getString("is_moving"));
                    break;
                case 8:
                    string = getString(getString("bad_brightness"));
                    break;
                case 9:
                    string = getString(getString("bad_quality"));
                    break;
                case 10:
                    string = getString(getString("bad_eye_openness"));
                    break;
                case 11:
                    string = getString(getString("blink_openness"));
                    break;
                case 12:
                    string = getString(getString("stack_time"));
                    break;
                default:
                    string = "";
                    break;
            }
        } else {
            string = getString(getString("topText_do_photinus"));
        }
        TextView textView = (TextView) findViewById(getId("messageCode"));
        if (textView == null || TextUtils.isEmpty(string)) {
            return;
        }
        textView.setText(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryFaceScan() {
        startFaceScanProcess(new RoundProgressCallback() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.6
            @Override // com.aliyun.aliyunface.ui.widget.RoundProgressCallback
            public void onProgress(int i) {
            }

            @Override // com.aliyun.aliyunface.ui.widget.RoundProgressCallback
            public void onFinish() {
                WorkState workState = ToygerPresenter.getInstance().getWorkState();
                if (WorkState.FACE_COMPLETED == workState || WorkState.PHOTINUS == workState) {
                    return;
                }
                if (ToygerActivity.this.faceScanRetryCnt < 4) {
                    int string = ToygerActivity.this.getString("message_box_title_retry_face_scan");
                    if (ToygerActivity.this.isActivityPaused) {
                        string = ToygerActivity.this.getString("message_box_title_operation_fail");
                    }
                    ToygerActivity toygerActivity = ToygerActivity.this;
                    toygerActivity.showMessageBox(string, toygerActivity.getString("message_box_message_retry_face_scan"), ToygerActivity.this.getString("message_box_btn_retry_ok"), -1, new MessageBoxCB() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.6.2
                        @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                        public void onOK() {
                            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScan", "status", "time out, user retry:" + ToygerActivity.this.faceScanRetryCnt);
                            ToygerActivity.access$1508(ToygerActivity.this);
                            try {
                                DeviceTokenClient.getInstance(ToygerActivity.this).initToken("zorro", "elBwppCSr9nB1LIQ", (DeviceTokenClient.InitResultListener) null);
                            } catch (Exception unused) {
                            }
                            ToygerActivity.this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_RETRY_FACE_SCAN);
                        }

                        @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                        public void onCancel() {
                            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScan", "status", "time out, user back");
                            ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OVER_TIME);
                        }
                    });
                    return;
                }
                ToygerActivity toygerActivity2 = ToygerActivity.this;
                toygerActivity2.showMessageBox(toygerActivity2.getString("message_box_title_retry_face_scan_time_out"), ToygerActivity.this.getString("message_box_message_retry_face_scan_time_out"), ToygerActivity.this.getString("message_box_message_btn_retry_ok_time_out"), -1, new MessageBoxCB() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.6.1
                    @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                    public void onCancel() {
                    }

                    @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                    public void onOK() {
                        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScan", "status", "time out, not success");
                        ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OVER_TIME);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSurfaceChanged(double d, double d2) {
        Log.e("Toyger", "surfaceChanged, w=" + d + " h=" + d2);
        if (this.mCameraSurfaceView != null) {
            if (d <= d2) {
                onPortUIInit(d, d2);
            } else {
                onLandUIInit(d, d2);
            }
        }
    }

    private void onPortUIInit(double d, double d2) {
        View viewFindViewById = findViewById(getId("screen_main_frame"));
        if (viewFindViewById != null) {
            int width = viewFindViewById.getWidth();
            FrameLayout frameLayout = (FrameLayout) findViewById(getId("toger_main_scan_frame"));
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            layoutParams.width = (int) (((double) width) * 0.6600000262260437d);
            layoutParams.height = (int) ((((double) layoutParams.width) / (d * 1.0d)) * d2);
            frameLayout.setLayoutParams(layoutParams);
            CircleHoleView circleHoleView = (CircleHoleView) findViewById(getId("toyger_face_circle_hole_view"));
            if (circleHoleView != null) {
                ViewGroup.LayoutParams layoutParams2 = circleHoleView.getLayoutParams();
                layoutParams2.width = layoutParams.width;
                layoutParams2.height = layoutParams.height;
                circleHoleView.setLayoutParams(layoutParams2);
                circleHoleView.widthAttr = layoutParams.width;
                circleHoleView.heightAttr = layoutParams.width;
                circleHoleView.invalidate();
            }
            RoundProgressBar roundProgressBar = (RoundProgressBar) findViewById(getId("scan_progress"));
            if (roundProgressBar != null) {
                ViewGroup.LayoutParams layoutParams3 = roundProgressBar.getLayoutParams();
                layoutParams3.width = layoutParams.width;
                layoutParams3.height = layoutParams.width;
                roundProgressBar.setLayoutParams(layoutParams3);
            }
            TextView textView = (TextView) findViewById(getId("messageCode"));
            if (textView != null) {
                ViewGroup.LayoutParams layoutParams4 = textView.getLayoutParams();
                layoutParams4.width = layoutParams.width;
                textView.setLayoutParams(layoutParams4);
            }
            ImageView imageView = (ImageView) findViewById(getId("faceAvatar"));
            if (imageView != null) {
                ViewGroup.LayoutParams layoutParams5 = imageView.getLayoutParams();
                layoutParams5.width = layoutParams.width;
                layoutParams5.height = layoutParams.width;
                imageView.setLayoutParams(layoutParams5);
            }
            ToygerLog.e("屏幕宽度=>" + width + " 预览宽度=>" + layoutParams.width);
        }
        this.mCameraSurfaceView.setBackgroundColor(0);
    }

    private void onLandUIInit(double d, double d2) {
        View viewFindViewById = findViewById(getId("screen_main_frame"));
        if (viewFindViewById != null) {
            int height = viewFindViewById.getHeight();
            double dimension = getResources().getDimension(getDimension("comm_margin_size_60"));
            FrameLayout frameLayout = (FrameLayout) findViewById(getId("toger_main_scan_frame"));
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            layoutParams.height = (int) ((((double) height) - dimension) * 0.6600000262260437d);
            layoutParams.width = (int) ((((double) layoutParams.height) / (d2 * 1.0d)) * d);
            frameLayout.setLayoutParams(layoutParams);
            CircleHoleView circleHoleView = (CircleHoleView) findViewById(getId("toyger_face_circle_hole_view"));
            if (circleHoleView != null) {
                ViewGroup.LayoutParams layoutParams2 = circleHoleView.getLayoutParams();
                layoutParams2.width = layoutParams.width;
                layoutParams2.height = layoutParams.height;
                circleHoleView.setLayoutParams(layoutParams2);
                circleHoleView.widthAttr = layoutParams.height;
                circleHoleView.heightAttr = layoutParams.height;
                circleHoleView.invalidate();
            }
            ImageView imageView = (ImageView) findViewById(getId("faceAvatar"));
            if (imageView != null) {
                ViewGroup.LayoutParams layoutParams3 = imageView.getLayoutParams();
                layoutParams3.width = layoutParams.height;
                layoutParams3.height = layoutParams.height;
                imageView.setLayoutParams(layoutParams3);
            }
            TextView textView = (TextView) findViewById(getId("messageCode"));
            if (textView != null) {
                ViewGroup.LayoutParams layoutParams4 = textView.getLayoutParams();
                layoutParams4.width = layoutParams.height;
                textView.setLayoutParams(layoutParams4);
            }
            RoundProgressBar roundProgressBar = (RoundProgressBar) findViewById(getId("scan_progress"));
            if (roundProgressBar != null) {
                ViewGroup.LayoutParams layoutParams5 = roundProgressBar.getLayoutParams();
                layoutParams5.width = layoutParams.height;
                layoutParams5.height = layoutParams.height;
                roundProgressBar.setLayoutParams(layoutParams5);
            }
            ToygerLog.e("屏幕宽度=>" + height + " 预览宽度=>" + layoutParams.height);
        }
        this.mCameraSurfaceView.setBackgroundColor(0);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.isActivityPaused = false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.isActivityPaused = true;
    }

    private void showLeftCloseButton() {
        Button button = (Button) findViewById(getId("close_toyger_btn_right"));
        if (button != null) {
            button.setVisibility(4);
        }
        TextView textView = (TextView) findViewById(getId("toyger_face_scan_close_btn_right"));
        if (textView != null) {
            textView.setVisibility(4);
        }
        Button button2 = (Button) findViewById(getId("close_toyger_btn_left"));
        if (button2 != null) {
            button2.setVisibility(0);
        }
        TextView textView2 = (TextView) findViewById(getId("toyger_face_scan_close_btn_left"));
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        int iOnPageScanCloseImage;
        TextView textView;
        super.onCreate(bundle);
        setContentView(getLayout("activity_toyger"));
        this.btnClose = (Button) findViewById(getId("close_toyger_btn_right"));
        if (!TextUtils.isEmpty(UICustomParams.TOP_TIP_TEXT) && (textView = (TextView) findViewById(getId("top_tip_firm_text"))) != null) {
            textView.setTextSize(getResources().getDimension(getDimension("comm_normal_small2_font_size")));
            textView.setText(UICustomParams.TOP_TIP_TEXT);
        }
        MiscUtil.setActivityScreenBrightness(this, 1.0f);
        try {
            new Thread(new Runnable() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        SecuritySession session = SecurityDevice.getInstance().getSession();
                        if (10000 == session.code) {
                            ToygerPresenter.getInstance().setAliyunDeviceToken(session.session);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception unused) {
        }
        try {
            ZIMUICustomListener uiCustomListener = ToygerPresenter.getInstance().getUiCustomListener();
            if (uiCustomListener != null && uiCustomListener.onIsPageScanCloseImageLeft()) {
                this.btnClose = (Button) findViewById(getId("close_toyger_btn_left"));
                showLeftCloseButton();
            }
        } catch (Exception unused2) {
        }
        if (this.btnClose != null) {
            try {
                ZIMUICustomListener uiCustomListener2 = ToygerPresenter.getInstance().getUiCustomListener();
                if (uiCustomListener2 != null && (iOnPageScanCloseImage = uiCustomListener2.onPageScanCloseImage()) > 0) {
                    this.btnClose.setBackgroundResource(iOnPageScanCloseImage);
                }
            } catch (Exception unused3) {
            }
            this.btnClose.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ToygerActivity.this.isMessageBoxShow()) {
                        return;
                    }
                    ToygerActivity toygerActivity = ToygerActivity.this;
                    toygerActivity.showMessageBox(toygerActivity.getString("message_box_title_exit_tip"), ToygerActivity.this.getString("message_box_message_exit_tip"), ToygerActivity.this.getString("message_box_btn_ok_tip"), ToygerActivity.this.getString("message_box_btn_cancel_tip"), new MessageBoxCB() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.8.1
                        @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                        public void onCancel() {
                        }

                        @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
                        public void onOK() {
                            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "userBack", "type", "pressCloseButton");
                            ToygerActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_USER_BACK);
                        }
                    });
                }
            });
        }
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ToygerActivityStart", "name", "ToygerActivity");
        initToygerUI();
    }

    private void showAvatar(boolean z) {
        ImageView imageView = (ImageView) findViewById(getId("faceAvatar"));
        RoundProgressBar roundProgressBar = (RoundProgressBar) findViewById(getId("scan_progress"));
        if (imageView == null || roundProgressBar == null) {
            return;
        }
        if (!z) {
            imageView.setVisibility(8);
            roundProgressBar.setVisibility(0);
            return;
        }
        imageView.setVisibility(0);
        roundProgressBar.setVisibility(8);
        try {
            Bitmap bitmapGenAvatar = Avatar.genAvatar(MiscUtil.bytes2Bitmap(ToygerPresenter.getInstance().getHighQualityFaceImage()), ToygerPresenter.getInstance().getHighQualityFaceAttr());
            if (bitmapGenAvatar != null) {
                imageView.setImageBitmap(bitmapGenAvatar);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initToygerUI() {
        /*
            r9 = this;
            com.aliyun.aliyunface.ToygerPresenter r0 = com.aliyun.aliyunface.ToygerPresenter.getInstance()
            com.aliyun.aliyunface.config.AndroidClientConfig r0 = r0.getAndroidClientConfig()
            r1 = 1
            java.lang.String r2 = "startGuid"
            java.lang.String r3 = "initToygerUI"
            r4 = 0
            if (r0 == 0) goto L62
            com.aliyun.aliyunface.config.NavigatePage r5 = r0.getNavi()
            if (r5 == 0) goto L62
            com.aliyun.aliyunface.config.NavigatePage r5 = r0.getNavi()
            boolean r5 = r5.isEnable()
            if (r5 == 0) goto L62
            com.aliyun.aliyunface.config.NavigatePage r0 = r0.getNavi()
            java.lang.String r0 = r0.getUrl()
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L62
            java.lang.String r5 = "guid_web_page"
            int r5 = r9.getId(r5)
            android.view.View r5 = r9.findViewById(r5)
            com.aliyun.aliyunface.ui.ToygerWebView r5 = (com.aliyun.aliyunface.ui.ToygerWebView) r5
            if (r5 == 0) goto L62
            r5.setVisibility(r4)
            android.os.Handler r6 = r9.uiHandler
            r5.setHandler(r6)
            r5.loadUrl(r0)
            com.aliyun.aliyunface.log.RecordService r5 = com.aliyun.aliyunface.log.RecordService.getInstance()
            com.aliyun.aliyunface.log.RecordLevel r6 = com.aliyun.aliyunface.log.RecordLevel.LOG_INFO
            r7 = 4
            java.lang.String[] r7 = new java.lang.String[r7]
            r7[r4] = r2
            java.lang.String r4 = "true"
            r7[r1] = r4
            r4 = 2
            java.lang.String r8 = "url"
            r7[r4] = r8
            r4 = 3
            r7[r4] = r0
            r5.recordEvent(r6, r3, r7)
            goto L63
        L62:
            r1 = 0
        L63:
            if (r1 != 0) goto L77
            com.aliyun.aliyunface.log.RecordService r0 = com.aliyun.aliyunface.log.RecordService.getInstance()
            com.aliyun.aliyunface.log.RecordLevel r1 = com.aliyun.aliyunface.log.RecordLevel.LOG_INFO
            java.lang.String r4 = "false"
            java.lang.String[] r2 = new java.lang.String[]{r2, r4}
            r0.recordEvent(r1, r3, r2)
            r9.initToyger()
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aliyun.aliyunface.ui.ToygerActivity.initToygerUI():void");
    }

    private void initToyger() {
        showAvatar(false);
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScan", "status", "start preview");
        LinearLayout linearLayout = (LinearLayout) findViewById(getId("toyger_main_page"));
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        Button button = this.btnClose;
        if (button != null) {
            button.setVisibility(0);
        }
        ToygerPresenter toygerPresenter = ToygerPresenter.getInstance();
        if (toygerPresenter != null) {
            CameraSurfaceView cameraSurfaceView = (CameraSurfaceView) findViewById(getId("cameraSurfaceView"));
            this.mCameraSurfaceView = cameraSurfaceView;
            cameraSurfaceView.setVisibility(0);
            CameraConstants.CAMERA_MAX_WIDTH = AdBaseConstants.DEFAULT_BROADCAST_CHECK_TIME;
            this.mCameraSurfaceView.init(this, true, true, (DeviceSetting[]) null);
            this.mCameraSurfaceView.setCameraCallback(toygerPresenter);
            if (!toygerPresenter.init(this, this.uiHandler, this.mCameraSurfaceView.getCameraInterface())) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScan", "status", "init toyger presenter fail");
                sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_INIT_TOYGER_ERROR);
            } else {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "faceScan", "status", "faceScan init Success");
                this.faceScanRetryCnt = 0;
                this.faceScanStartTime = System.currentTimeMillis();
                retryFaceScan();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseFaceScanProcess(boolean z) {
        RoundProgressBar roundProgressBar = (RoundProgressBar) findViewById(getId("scan_progress"));
        if (roundProgressBar != null) {
            roundProgressBar.pauseProcess(z);
        }
        if (z) {
            this.prevWorkState = ToygerPresenter.getInstance().setWorkState(WorkState.PAUSE);
        } else {
            ToygerPresenter.getInstance().setWorkState(this.prevWorkState);
        }
    }

    private void startFaceScanProcess(RoundProgressCallback roundProgressCallback) {
        int time;
        RoundProgressBar roundProgressBar = (RoundProgressBar) findViewById(getId("scan_progress"));
        if (roundProgressBar != null) {
            int i = 20;
            AndroidClientConfig androidClientConfig = ToygerPresenter.getInstance().getAndroidClientConfig();
            if (androidClientConfig != null && androidClientConfig.getColl() != null && (time = androidClientConfig.getColl().getTime()) > 0) {
                i = time;
            }
            if (UICustomParams.FACE_PROGRESS_COLOR != null) {
                roundProgressBar.setGradientColor(Color.parseColor(UICustomParams.FACE_PROGRESS_COLOR));
            }
            roundProgressBar.startProcess(i * 1000, roundProgressCallback);
        }
    }

    private void stopFaceScanProcess(boolean z) {
        RoundProgressBar roundProgressBar = (RoundProgressBar) findViewById(getId("scan_progress"));
        if (roundProgressBar != null) {
            roundProgressBar.stopProcess();
            if (z) {
                roundProgressBar.setProgress(0);
            }
        }
    }

    private void startFaceUploadProcess() {
        LinearLayout linearLayout = (LinearLayout) findViewById(getId("toyger_face_eye_loading_page"));
        if (linearLayout != null) {
            if (linearLayout.getVisibility() == 0) {
                return;
            } else {
                linearLayout.setVisibility(0);
            }
        }
        Button button = this.btnClose;
        if (button != null) {
            button.setEnabled(false);
        }
    }

    private void stopFaceUploadProcess() {
        LinearLayout linearLayout = (LinearLayout) findViewById(getId("toyger_face_eye_loading_page"));
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        Button button = this.btnClose;
        if (button != null) {
            button.setEnabled(true);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        ToygerPresenter.getInstance().onRelease();
        OssClientHelper.getInstance().release();
        stopFaceScanProcess(true);
        super.onDestroy();
    }

    private ICameraInterface getCameraInterface() {
        CameraSurfaceView cameraSurfaceView = this.mCameraSurfaceView;
        if (cameraSurfaceView != null) {
            return cameraSurfaceView.getCameraInterface();
        }
        return null;
    }

    public boolean isMessageBoxShow() {
        CommAlertOverlay commAlertOverlay = (CommAlertOverlay) findViewById(getId("message_box_overlay"));
        return commAlertOverlay != null && commAlertOverlay.getVisibility() == 0;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (isMessageBoxShow()) {
            return;
        }
        showMessageBox(getString("message_box_title_exit_tip"), getString("message_box_message_exit_tip"), getString("message_box_btn_ok_tip"), getString("message_box_btn_cancel_tip"), new MessageBoxCB() { // from class: com.aliyun.aliyunface.ui.ToygerActivity.9
            @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
            public void onCancel() {
            }

            @Override // com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB
            public void onOK() {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "userBack", "type", "homeBack");
                ToygerActivity.this.onErrorCode(ToygerConst.ZcodeConstants.ZCODE_USER_BACK);
                ToygerActivity.super.onBackPressed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showMessageBox(int r7, int r8, int r9, int r10, final com.aliyun.aliyunface.ui.ToygerActivity.MessageBoxCB r11) {
        /*
            r6 = this;
            android.widget.Button r0 = r6.btnClose     // Catch: java.lang.Exception -> L9e
            r1 = 0
            r0.setEnabled(r1)     // Catch: java.lang.Exception -> L9e
            java.lang.String r0 = "message_box_overlay"
            int r0 = r6.getId(r0)     // Catch: java.lang.Exception -> L9e
            android.view.View r0 = r6.findViewById(r0)     // Catch: java.lang.Exception -> L9e
            com.aliyun.aliyunface.ui.overlay.CommAlertOverlay r0 = (com.aliyun.aliyunface.ui.overlay.CommAlertOverlay) r0     // Catch: java.lang.Exception -> L9e
            if (r0 == 0) goto L9e
            com.aliyun.aliyunface.ToygerPresenter r2 = com.aliyun.aliyunface.ToygerPresenter.getInstance()     // Catch: java.lang.Exception -> L9e
            com.aliyun.aliyunface.api.ZIMUICustomListener r2 = r2.getUiCustomListener()     // Catch: java.lang.Exception -> L9e
            r3 = 1
            if (r7 <= 0) goto L36
            java.lang.String r7 = r6.getString(r7)     // Catch: java.lang.Exception -> L9e
            if (r2 == 0) goto L32
            java.lang.String r4 = r2.onAlertTitle(r7)     // Catch: java.lang.Exception -> L9e
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L9e
            if (r5 != 0) goto L32
            r7 = r4
            r4 = 1
            goto L33
        L32:
            r4 = 0
        L33:
            r0.setTitleText(r7, r4)     // Catch: java.lang.Exception -> L9e
        L36:
            if (r8 <= 0) goto L4f
            java.lang.String r7 = r6.getString(r8)     // Catch: java.lang.Exception -> L9e
            if (r2 == 0) goto L4b
            java.lang.String r4 = r2.onAlertMessage(r7)     // Catch: java.lang.Exception -> L9e
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L9e
            if (r5 != 0) goto L4b
            r7 = r4
            r4 = 1
            goto L4c
        L4b:
            r4 = 0
        L4c:
            r0.setMessageText(r7, r4)     // Catch: java.lang.Exception -> L9e
        L4f:
            if (r10 <= 0) goto L70
            r0.setButtonType(r3)     // Catch: java.lang.Exception -> L9e
            java.lang.String r7 = r6.getString(r10)     // Catch: java.lang.Exception -> L9e
            if (r2 == 0) goto L6b
            java.lang.String r10 = r6.getString(r8)     // Catch: java.lang.Exception -> L9e
            java.lang.String r10 = r2.onAlertCancelButton(r10)     // Catch: java.lang.Exception -> L9e
            boolean r4 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L9e
            if (r4 != 0) goto L6b
            r7 = r10
            r10 = 1
            goto L6c
        L6b:
            r10 = 0
        L6c:
            r0.setCancelText(r7, r10)     // Catch: java.lang.Exception -> L9e
            goto L73
        L70:
            r0.setButtonType(r1)     // Catch: java.lang.Exception -> L9e
        L73:
            if (r9 <= 0) goto L90
            java.lang.String r7 = r6.getString(r9)     // Catch: java.lang.Exception -> L9e
            if (r2 == 0) goto L8c
            java.lang.String r8 = r6.getString(r8)     // Catch: java.lang.Exception -> L9e
            java.lang.String r8 = r2.onAlertOKButton(r8)     // Catch: java.lang.Exception -> L9e
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> L9e
            if (r9 != 0) goto L8c
            r7 = r8
            r8 = 1
            goto L8d
        L8c:
            r8 = 0
        L8d:
            r0.setConfirmText(r7, r8)     // Catch: java.lang.Exception -> L9e
        L90:
            r0.setVisibility(r1)     // Catch: java.lang.Exception -> L9e
            r6.pauseFaceScanProcess(r3)     // Catch: java.lang.Exception -> L9e
            com.aliyun.aliyunface.ui.ToygerActivity$10 r7 = new com.aliyun.aliyunface.ui.ToygerActivity$10     // Catch: java.lang.Exception -> L9e
            r7.<init>()     // Catch: java.lang.Exception -> L9e
            r0.setCommAlertOverlayListener(r7)     // Catch: java.lang.Exception -> L9e
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aliyun.aliyunface.ui.ToygerActivity.showMessageBox(int, int, int, int, com.aliyun.aliyunface.ui.ToygerActivity$MessageBoxCB):void");
    }
}
