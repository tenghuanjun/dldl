package com.aliyun.aliyunface.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.api.ZIMResponseCode;
import com.aliyun.aliyunface.config.OSSConfig;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.NetworkEnv;
import com.aliyun.aliyunface.network.NetworkPresenter;
import com.aliyun.aliyunface.network.OssClientHelper;
import com.aliyun.aliyunface.network.ZimOcrIdentifyCallback;
import com.aliyun.aliyunface.network.model.OCRInfo;
import com.aliyun.aliyunface.ui.overlay.CommAlertOverlay;
import com.aliyun.aliyunface.ui.overlay.OcrIdentityErrorOverlay;
import com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay;
import com.aliyun.aliyunface.ui.overlay.OcrLoadingOverlay;
import com.aliyun.aliyunface.ui.overlay.OcrPhotoRequireOverlay;
import com.aliyun.aliyunface.ui.widget.OcrGuideStageView;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrGuideBaseActivity extends Activity {
    private final int UI_MSG_UPLOAD_IDCARD_BASE = 1000;
    private final int UI_MSG_UPLOAD_IDCARD_SUCCESS = 1001;
    private final int UI_MSG_UPLOAD_IDCARD_NET_ERROR = 1002;
    private final int UI_MSG_IDENTITY_IDCARD_SUCCESS = 1003;
    private final int UI_MSG_IDENTITY_IDCARD_FAIL = 1004;
    private final int UI_MSG_IDENTITY_IDCARD_NET_ERROR = 1005;
    private final int UI_MSG_ON_ERROR_CODE = 1006;
    private OCRInfo ocrIdentityInfo = null;
    private byte[] roiBitmapContent = null;
    private byte[] ocrBitmapContent = null;
    private int retryMaxCnt = 10;
    private Handler uiHandler = new Handler(new Handler.Callback() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (1001 == i) {
                OcrGuideBaseActivity.this.onMsgUploadSuccess();
                return false;
            }
            if (1002 == i) {
                OcrGuideBaseActivity.this.onMsgUploadNetError();
                return false;
            }
            if (1003 == i) {
                OcrGuideBaseActivity.this.onMsgIdentitySuccess();
                return false;
            }
            if (1004 == i) {
                OcrGuideBaseActivity.this.onMsgIdentityFail();
                return false;
            }
            if (1005 == i) {
                OcrGuideBaseActivity.this.onMsgIdentityNetError();
                return false;
            }
            if (1006 != i) {
                return false;
            }
            OcrGuideBaseActivity.this.onErrorCode((String) message.obj);
            return false;
        }
    });

    public String getBottomTips() {
        return "";
    }

    public String getTopTips() {
        return "";
    }

    public boolean isIDCardBack() {
        return false;
    }

    public boolean isIDCardFront() {
        return false;
    }

    public void onClickClose() {
    }

    public void onClickNext() {
    }

    public void onClickTakePhoto() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMsgUploadSuccess() {
        String uploadFileName;
        NetworkEnv networkEnv = ToygerPresenter.getInstance().getNetworkEnv();
        if (networkEnv == null) {
            sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC);
            return;
        }
        String zimId = ToygerPresenter.getInstance().getZimId();
        boolean zIsIDCardFront = isIDCardFront();
        if (zIsIDCardFront) {
            uploadFileName = OssClientHelper.getInstance().getUploadFileName(3);
        } else {
            uploadFileName = OssClientHelper.getInstance().getUploadFileName(4);
        }
        NetworkPresenter.zimOCRIdentify(networkEnv, zimId, uploadFileName, zIsIDCardFront, new ZimOcrIdentifyCallback() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.2
            @Override // com.aliyun.aliyunface.network.ZimOcrIdentifyCallback
            public void onSuccess(OCRInfo oCRInfo) {
                OcrGuideBaseActivity.this.ocrIdentityInfo = oCRInfo;
                OcrGuideBaseActivity.this.uiHandler.sendEmptyMessage(1003);
            }

            @Override // com.aliyun.aliyunface.network.ZimOcrIdentifyCallback
            public void onServerError(String str, String str2) {
                if (!String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID).equalsIgnoreCase(str)) {
                    OcrGuideBaseActivity.this.uiHandler.sendEmptyMessage(1004);
                } else {
                    OcrGuideBaseActivity.this.sendErrorCode(String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID));
                }
            }

            @Override // com.aliyun.aliyunface.network.ZimOcrIdentifyCallback
            public void onError(String str, String str2) {
                OcrGuideBaseActivity.this.uiHandler.sendEmptyMessage(1005);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMsgUploadNetError() {
        showLoadingOverlay(false);
        showNetowrkError(true, new OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.3
            @Override // com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener
            public void onExit() {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ossFrontError", "status", "user cancel on net error");
                OcrGuideBaseActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OCR_NET_ERROR);
            }

            @Override // com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener
            public void onRetry() {
                OcrGuideBaseActivity.this.showNetowrkError(false, null);
                OcrGuideBaseActivity.this.showLoadingOverlay(true);
                OcrGuideBaseActivity.this.startOCRIdentify();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMsgIdentitySuccess() {
        showLoadingOverlay(false);
        this.ocrBitmapContent = this.roiBitmapContent;
        if (isIDCardFront()) {
            setOcrInfo(this.ocrIdentityInfo);
            ToygerPresenter.getInstance().setOcrInfo(this.ocrIdentityInfo);
        }
        byte[] bArr = this.ocrBitmapContent;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (bitmapDecodeByteArray != null) {
            setOcrRoiImage(bitmapDecodeByteArray);
        }
        enableNext(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMsgIdentityFail() {
        showLoadingOverlay(false);
        int i = this.retryMaxCnt;
        if (i <= 0) {
            showIdnentityTryCntAlert(true, new CommAlertOverlay.CommAlertOverlayListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.4
                @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                public void onCancel() {
                }

                @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                public void onConfirm() {
                    OcrGuideBaseActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OCR_IDENTITY_MAX_COUNT);
                }
            });
        } else {
            this.retryMaxCnt = i - 1;
            showIdentityError(true, new OcrIdentityErrorOverlay.OcrIdentityErrorOverlayListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.5
                @Override // com.aliyun.aliyunface.ui.overlay.OcrIdentityErrorOverlay.OcrIdentityErrorOverlayListener
                public void onClose() {
                }

                @Override // com.aliyun.aliyunface.ui.overlay.OcrIdentityErrorOverlay.OcrIdentityErrorOverlayListener
                public void onRetry() {
                    OcrGuideBaseActivity.this.showIdentityError(false, null);
                    OcrGuideBaseActivity.this.onClickTakePhoto();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMsgIdentityNetError() {
        showLoadingOverlay(false);
        showNetowrkError(true, new OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.6
            @Override // com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener
            public void onExit() {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ocrNetError", "status", "user cancel on net error");
                OcrGuideBaseActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OCR_NET_ERROR);
            }

            @Override // com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener
            public void onRetry() {
                OcrGuideBaseActivity.this.showNetowrkError(false, null);
                OcrGuideBaseActivity.this.showLoadingOverlay(true);
                OcrGuideBaseActivity.this.onMsgUploadSuccess();
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ocr_guide);
        initEvents();
        initUIState();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("roiPicture");
            this.roiBitmapContent = byteArrayExtra;
            if (byteArrayExtra != null) {
                startOCRIdentify();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startOCRIdentify() {
        OSSConfig ossConfig = ToygerPresenter.getInstance().getOssConfig();
        if (ossConfig != null) {
            showLoadingOverlay(true);
            OssClientHelper.getInstance().init();
            if (isIDCardFront()) {
                OssClientHelper.getInstance().addUploadFile(3, ossConfig.BucketName, MiscUtil.genOssFileName(ossConfig.FileNamePrefix, "ocridface", "jpeg"), this.roiBitmapContent);
            } else {
                OssClientHelper.getInstance().addUploadFile(4, ossConfig.BucketName, MiscUtil.genOssFileName(ossConfig.FileNamePrefix, "ocridnationalemblem", "jpeg"), this.roiBitmapContent);
            }
            OssClientHelper.getInstance().startUploadFiles(this, ossConfig.OssEndPoint, ossConfig.AccessKeyId, ossConfig.AccessKeySecret, ossConfig.SecurityToken, new OssClientHelper.OssClientUploadListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.7
                @Override // com.aliyun.aliyunface.network.OssClientHelper.OssClientUploadListener
                public boolean onUploadSuccess(int i, String str, String str2) {
                    return false;
                }

                @Override // com.aliyun.aliyunface.network.OssClientHelper.OssClientUploadListener
                public boolean onUploadError(int i, String str, String str2, String str3) {
                    if (!"InvalidAccessKeyId".equalsIgnoreCase(str3)) {
                        OcrGuideBaseActivity.this.uiHandler.sendEmptyMessage(1002);
                        return false;
                    }
                    OcrGuideBaseActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OSS_TOKEN_INVALID);
                    return false;
                }

                @Override // com.aliyun.aliyunface.network.OssClientHelper.OssClientUploadListener
                public void onFinish(int i, int i2) {
                    if (i == i2) {
                        OcrGuideBaseActivity.this.uiHandler.sendEmptyMessage(1001);
                    }
                }
            });
            return;
        }
        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "ocrIdentityError", "type", "oss invalid");
        sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC);
    }

    public void sendErrorCode(String str) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1006;
        messageObtain.obj = str;
        this.uiHandler.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onErrorCode(String str) {
        this.ocrIdentityInfo = null;
        this.roiBitmapContent = null;
        finish();
        ToygerPresenter.getInstance().sendResAndExit(str);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        showExitAlert(true, new CommAlertOverlay.CommAlertOverlayListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.8
            @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
            public void onCancel() {
            }

            @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
            public void onConfirm() {
                OcrGuideBaseActivity.this.onClickClose();
                OcrGuideBaseActivity.super.onBackPressed();
            }
        });
    }

    private void initEvents() {
        TextView textView = (TextView) findViewById(R.id.ocr_take_photo_require_button);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrGuideBaseActivity.this.showTakePhotoOverlay(true);
                }
            });
        }
        Button button = (Button) findViewById(R.id.ocr_comm_next_button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrGuideBaseActivity.this.onClickNext();
                }
            });
        }
        View viewFindViewById = findViewById(R.id.ocr_comm_back_button);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrGuideBaseActivity.this.showExitAlert(true, new CommAlertOverlay.CommAlertOverlayListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.11.1
                        @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                        public void onCancel() {
                        }

                        @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                        public void onConfirm() {
                            OcrGuideBaseActivity.this.onClickClose();
                        }
                    });
                }
            });
        }
        ImageView imageView = (ImageView) findViewById(R.id.ocr_take_photo_take_button);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrGuideBaseActivity.this.onClickTakePhoto();
                }
            });
        }
    }

    public void showTakePhotoButton(boolean z) {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_take_photo_take_button);
        if (imageView != null) {
            if (z) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(4);
            }
        }
    }

    private void initUIState() {
        OcrGuideStageView ocrGuideStageView = (OcrGuideStageView) findViewById(R.id.ocr_guide_stage_view);
        if (ocrGuideStageView != null) {
            if (isIDCardFront()) {
                ocrGuideStageView.setStage(0);
            } else if (isIDCardBack()) {
                ocrGuideStageView.setStage(1);
            }
        }
        TextView textView = (TextView) findViewById(R.id.ocr_take_photo_top_tips);
        if (textView != null) {
            textView.setText(getTopTips());
        }
        TextView textView2 = (TextView) findViewById(R.id.ocr_take_photo_bottom_tips);
        if (textView2 != null) {
            textView2.setText(getBottomTips());
        }
        enableNext(false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            initUILayout();
        }
    }

    private void initUILayout() {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_take_photo_img_content);
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = (int) (imageView.getWidth() * 0.60655737f);
            imageView.setLayoutParams(layoutParams);
            if (isIDCardFront()) {
                imageView.setImageResource(R.mipmap.ocr_idcard_front_default);
            } else {
                imageView.setImageResource(R.mipmap.ocr_idcad_back_default);
            }
            OCRInfo ocrInfo = ToygerPresenter.getInstance().getOcrInfo();
            byte[] bArr = this.ocrBitmapContent;
            if (bArr == null || ocrInfo == null) {
                return;
            }
            setOcrRoiImage(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
        }
    }

    public void showLoadingOverlay(boolean z) {
        OcrLoadingOverlay ocrLoadingOverlay = (OcrLoadingOverlay) findViewById(R.id.ocr_loading_overlay);
        if (ocrLoadingOverlay != null) {
            ocrLoadingOverlay.setVisibility(z ? 0 : 4);
        }
    }

    public void showTakePhotoOverlay(boolean z) {
        OcrPhotoRequireOverlay ocrPhotoRequireOverlay = (OcrPhotoRequireOverlay) findViewById(R.id.ocr_take_photo_require_overlay);
        if (ocrPhotoRequireOverlay != null) {
            ocrPhotoRequireOverlay.setVisibility(z ? 0 : 4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showNetowrkError(boolean r8, com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay.OcrIdentityNetErrorListener r9) {
        /*
            r7 = this;
            int r0 = com.aliyun.aliyunocr.R.id.ocr_identity_net_error_overlay     // Catch: java.lang.Exception -> L8d
            android.view.View r0 = r7.findViewById(r0)     // Catch: java.lang.Exception -> L8d
            com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay r0 = (com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay) r0     // Catch: java.lang.Exception -> L8d
            if (r0 == 0) goto L8d
            com.aliyun.aliyunface.ToygerPresenter r1 = com.aliyun.aliyunface.ToygerPresenter.getInstance()     // Catch: java.lang.Exception -> L8d
            com.aliyun.aliyunface.api.ZIMUICustomListener r1 = r1.getUiCustomListener()     // Catch: java.lang.Exception -> L8d
            int r2 = com.aliyun.aliyunocr.R.string.ocr_idcard_identity_timeout     // Catch: java.lang.Exception -> L8d
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Exception -> L8d
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L29
            java.lang.String r5 = r1.onAlertTitle(r2)     // Catch: java.lang.Exception -> L8d
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L8d
            if (r6 != 0) goto L29
            r2 = r5
            r5 = 1
            goto L2a
        L29:
            r5 = 0
        L2a:
            r0.setTitleText(r2, r5)     // Catch: java.lang.Exception -> L8d
            int r2 = com.aliyun.aliyunocr.R.string.ocr_idcard_identity_timeout_tips     // Catch: java.lang.Exception -> L8d
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Exception -> L8d
            if (r1 == 0) goto L42
            java.lang.String r5 = r1.onAlertMessage(r2)     // Catch: java.lang.Exception -> L8d
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L8d
            if (r6 != 0) goto L42
            r2 = r5
            r5 = 1
            goto L43
        L42:
            r5 = 0
        L43:
            r0.setMessageText(r2, r5)     // Catch: java.lang.Exception -> L8d
            int r2 = com.aliyun.aliyunocr.R.string.message_box_btn_exit     // Catch: java.lang.Exception -> L8d
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Exception -> L8d
            if (r1 == 0) goto L61
            int r5 = com.aliyun.aliyunocr.R.string.ocr_idcard_identity_timeout_tips     // Catch: java.lang.Exception -> L8d
            java.lang.String r5 = r7.getString(r5)     // Catch: java.lang.Exception -> L8d
            java.lang.String r5 = r1.onAlertCancelButton(r5)     // Catch: java.lang.Exception -> L8d
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L8d
            if (r6 != 0) goto L61
            r2 = r5
            r5 = 1
            goto L62
        L61:
            r5 = 0
        L62:
            r0.setCancelText(r2, r5)     // Catch: java.lang.Exception -> L8d
            int r2 = com.aliyun.aliyunocr.R.string.ocr_idcard_re_identity     // Catch: java.lang.Exception -> L8d
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Exception -> L8d
            if (r1 == 0) goto L7f
            int r5 = com.aliyun.aliyunocr.R.string.ocr_idcard_identity_timeout_tips     // Catch: java.lang.Exception -> L8d
            java.lang.String r5 = r7.getString(r5)     // Catch: java.lang.Exception -> L8d
            java.lang.String r1 = r1.onAlertOKButton(r5)     // Catch: java.lang.Exception -> L8d
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L8d
            if (r5 != 0) goto L7f
            r2 = r1
            goto L80
        L7f:
            r3 = 0
        L80:
            r0.setConfirmText(r2, r3)     // Catch: java.lang.Exception -> L8d
            if (r8 == 0) goto L86
            goto L87
        L86:
            r4 = 4
        L87:
            r0.setVisibility(r4)     // Catch: java.lang.Exception -> L8d
            r0.setOnNetworkErrorListener(r9)     // Catch: java.lang.Exception -> L8d
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.showNetowrkError(boolean, com.aliyun.aliyunface.ui.overlay.OcrIdentityNetErrorOverlay$OcrIdentityNetErrorListener):void");
    }

    public void showIdentityError(boolean z, OcrIdentityErrorOverlay.OcrIdentityErrorOverlayListener ocrIdentityErrorOverlayListener) {
        OcrIdentityErrorOverlay ocrIdentityErrorOverlay = (OcrIdentityErrorOverlay) findViewById(R.id.ocr_identity_error_overlay);
        if (ocrIdentityErrorOverlay != null) {
            ocrIdentityErrorOverlay.setVisibility(z ? 0 : 4);
            ocrIdentityErrorOverlay.setOcrIdentityErrorOverlayListener(ocrIdentityErrorOverlayListener);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showExitAlert(boolean r7, com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener r8) {
        /*
            r6 = this;
            int r0 = com.aliyun.aliyunocr.R.id.ocr_exit_alert_overlay     // Catch: java.lang.Exception -> L8d
            android.view.View r0 = r6.findViewById(r0)     // Catch: java.lang.Exception -> L8d
            com.aliyun.aliyunface.ui.overlay.CommAlertOverlay r0 = (com.aliyun.aliyunface.ui.overlay.CommAlertOverlay) r0     // Catch: java.lang.Exception -> L8d
            if (r0 == 0) goto L8d
            r0.setCommAlertOverlayListener(r8)     // Catch: java.lang.Exception -> L8d
            com.aliyun.aliyunface.ToygerPresenter r8 = com.aliyun.aliyunface.ToygerPresenter.getInstance()     // Catch: java.lang.Exception -> L8d
            com.aliyun.aliyunface.api.ZIMUICustomListener r8 = r8.getUiCustomListener()     // Catch: java.lang.Exception -> L8d
            int r1 = com.aliyun.aliyunocr.R.string.message_box_title_exit_tip     // Catch: java.lang.Exception -> L8d
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L8d
            r2 = 1
            r3 = 0
            if (r8 == 0) goto L2c
            java.lang.String r4 = r8.onAlertTitle(r1)     // Catch: java.lang.Exception -> L8d
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L8d
            if (r5 != 0) goto L2c
            r1 = r4
            r4 = 1
            goto L2d
        L2c:
            r4 = 0
        L2d:
            r0.setTitleText(r1, r4)     // Catch: java.lang.Exception -> L8d
            int r1 = com.aliyun.aliyunocr.R.string.ocr_exit_tip_message     // Catch: java.lang.Exception -> L8d
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L8d
            if (r8 == 0) goto L45
            java.lang.String r4 = r8.onAlertMessage(r1)     // Catch: java.lang.Exception -> L8d
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L8d
            if (r5 != 0) goto L45
            r1 = r4
            r4 = 1
            goto L46
        L45:
            r4 = 0
        L46:
            r0.setMessageText(r1, r4)     // Catch: java.lang.Exception -> L8d
            int r1 = com.aliyun.aliyunocr.R.string.message_box_btn_cancel_tip     // Catch: java.lang.Exception -> L8d
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L8d
            if (r8 == 0) goto L64
            int r4 = com.aliyun.aliyunocr.R.string.ocr_exit_tip_message     // Catch: java.lang.Exception -> L8d
            java.lang.String r4 = r6.getString(r4)     // Catch: java.lang.Exception -> L8d
            java.lang.String r4 = r8.onAlertCancelButton(r4)     // Catch: java.lang.Exception -> L8d
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L8d
            if (r5 != 0) goto L64
            r1 = r4
            r4 = 1
            goto L65
        L64:
            r4 = 0
        L65:
            r0.setCancelText(r1, r4)     // Catch: java.lang.Exception -> L8d
            int r1 = com.aliyun.aliyunocr.R.string.message_box_btn_ok_tip     // Catch: java.lang.Exception -> L8d
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L8d
            if (r8 == 0) goto L82
            int r4 = com.aliyun.aliyunocr.R.string.ocr_exit_tip_message     // Catch: java.lang.Exception -> L8d
            java.lang.String r4 = r6.getString(r4)     // Catch: java.lang.Exception -> L8d
            java.lang.String r8 = r8.onAlertOKButton(r4)     // Catch: java.lang.Exception -> L8d
            boolean r4 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> L8d
            if (r4 != 0) goto L82
            r1 = r8
            goto L83
        L82:
            r2 = 0
        L83:
            r0.setConfirmText(r1, r2)     // Catch: java.lang.Exception -> L8d
            if (r7 == 0) goto L89
            goto L8a
        L89:
            r3 = 4
        L8a:
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L8d
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.showExitAlert(boolean, com.aliyun.aliyunface.ui.overlay.CommAlertOverlay$CommAlertOverlayListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showIdnentityTryCntAlert(boolean r7, com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener r8) {
        /*
            r6 = this;
            int r0 = com.aliyun.aliyunocr.R.id.ocr_exit_alert_overlay     // Catch: java.lang.Exception -> L71
            android.view.View r0 = r6.findViewById(r0)     // Catch: java.lang.Exception -> L71
            com.aliyun.aliyunface.ui.overlay.CommAlertOverlay r0 = (com.aliyun.aliyunface.ui.overlay.CommAlertOverlay) r0     // Catch: java.lang.Exception -> L71
            if (r0 == 0) goto L71
            r0.setCommAlertOverlayListener(r8)     // Catch: java.lang.Exception -> L71
            com.aliyun.aliyunface.ToygerPresenter r8 = com.aliyun.aliyunface.ToygerPresenter.getInstance()     // Catch: java.lang.Exception -> L71
            com.aliyun.aliyunface.api.ZIMUICustomListener r8 = r8.getUiCustomListener()     // Catch: java.lang.Exception -> L71
            int r1 = com.aliyun.aliyunocr.R.string.ocr_identity_too_many_try     // Catch: java.lang.Exception -> L71
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L71
            r2 = 1
            r3 = 0
            if (r8 == 0) goto L2c
            java.lang.String r4 = r8.onAlertTitle(r1)     // Catch: java.lang.Exception -> L71
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L71
            if (r5 != 0) goto L2c
            r1 = r4
            r4 = 1
            goto L2d
        L2c:
            r4 = 0
        L2d:
            r0.setTitleText(r1, r4)     // Catch: java.lang.Exception -> L71
            int r1 = com.aliyun.aliyunocr.R.string.ocr_exit_and_retry     // Catch: java.lang.Exception -> L71
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L71
            if (r8 == 0) goto L45
            java.lang.String r4 = r8.onAlertMessage(r1)     // Catch: java.lang.Exception -> L71
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L71
            if (r5 != 0) goto L45
            r1 = r4
            r4 = 1
            goto L46
        L45:
            r4 = 0
        L46:
            r0.setMessageText(r1, r4)     // Catch: java.lang.Exception -> L71
            r0.setButtonType(r3)     // Catch: java.lang.Exception -> L71
            int r1 = com.aliyun.aliyunocr.R.string.message_box_btn_retry_exit     // Catch: java.lang.Exception -> L71
            java.lang.String r1 = r6.getString(r1)     // Catch: java.lang.Exception -> L71
            if (r8 == 0) goto L66
            int r4 = com.aliyun.aliyunocr.R.string.ocr_exit_and_retry     // Catch: java.lang.Exception -> L71
            java.lang.String r4 = r6.getString(r4)     // Catch: java.lang.Exception -> L71
            java.lang.String r8 = r8.onAlertOKButton(r4)     // Catch: java.lang.Exception -> L71
            boolean r4 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> L71
            if (r4 != 0) goto L66
            r1 = r8
            goto L67
        L66:
            r2 = 0
        L67:
            r0.setConfirmText(r1, r2)     // Catch: java.lang.Exception -> L71
            if (r7 == 0) goto L6d
            goto L6e
        L6d:
            r3 = 4
        L6e:
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L71
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aliyun.aliyunface.ui.OcrGuideBaseActivity.showIdnentityTryCntAlert(boolean, com.aliyun.aliyunface.ui.overlay.CommAlertOverlay$CommAlertOverlayListener):void");
    }

    public void setOcrRoiImage(Bitmap bitmap) {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_take_photo_img_content);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setOcrInfo(OCRInfo oCRInfo) {
        View viewFindViewById = findViewById(R.id.ocr_idcard_infos_page);
        if (viewFindViewById != null) {
            if (oCRInfo != null) {
                viewFindViewById.setVisibility(0);
            } else {
                viewFindViewById.setVisibility(4);
                return;
            }
        }
        TextView textView = (TextView) findViewById(R.id.ocr_identity_info_name);
        if (textView != null) {
            textView.setText(oCRInfo.certName);
        }
        TextView textView2 = (TextView) findViewById(R.id.ocr_identity_info_idcard);
        if (textView2 != null) {
            textView2.setText(oCRInfo.certNo);
        }
    }

    public void enableNext(boolean z) {
        Resources resources;
        int i;
        int color;
        Button button = (Button) findViewById(R.id.ocr_comm_next_button);
        if (button != null) {
            button.setEnabled(z);
            if (UICustomParams.OCR_BOTTOM_COLOR != null) {
                color = z ? Color.parseColor(UICustomParams.OCR_BOTTOM_COLOR) : getResources().getColor(R.color.ocr_gray_line);
            } else {
                if (z) {
                    resources = getResources();
                    i = R.color.ocr_orange;
                } else {
                    resources = getResources();
                    i = R.color.ocr_gray_line;
                }
                color = resources.getColor(i);
            }
            button.setBackgroundColor(color);
        }
    }

    public String getCertName() {
        EditText editText = (EditText) findViewById(R.id.ocr_identity_info_name);
        return editText != null ? editText.getText().toString() : "";
    }

    public String getCertNo() {
        EditText editText = (EditText) findViewById(R.id.ocr_identity_info_idcard);
        return editText != null ? editText.getText().toString() : "";
    }
}
