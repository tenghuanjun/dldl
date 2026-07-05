package com.aliyun.aliyunface.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alipay.sdk.widget.j;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.config.AndroidClientConfig;
import com.aliyun.aliyunface.config.OSSConfig;
import com.aliyun.aliyunface.config.Protocol;
import com.aliyun.aliyunface.config.SDKAction;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.NetworkEnv;
import com.aliyun.aliyunface.network.NetworkPresenter;
import com.aliyun.aliyunface.network.ZimInitCallback;
import com.aliyun.aliyunface.ui.widget.iOSLoadingView;
import com.aliyun.aliyunface.utils.EnvCheck;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.jiguang.h5.PermissionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class FaceLoadingActivity extends Activity {
    private static final int TOYGER_PERMISSION_REQUEST_CODE = 1024;
    private static String[] toygerPermissions = {PermissionUtils.PERMISSION_CAMERA};
    private Handler uiHandler = new Handler(new Handler.Callback() { // from class: com.aliyun.aliyunface.ui.FaceLoadingActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (903 == i) {
                FaceLoadingActivity.this.onErrorCode((String) message.obj);
                return false;
            }
            if (909 != i) {
                return false;
            }
            FaceLoadingActivity.this.onInitDeviceSuccess();
            return false;
        }
    });

    private boolean isOcrEnabled() {
        ArrayList<SDKAction> sdkActionList;
        AndroidClientConfig androidClientConfig = ToygerPresenter.getInstance().getAndroidClientConfig();
        if (androidClientConfig != null && (sdkActionList = androidClientConfig.getSdkActionList()) != null && sdkActionList.size() > 0) {
            Iterator<SDKAction> it = sdkActionList.iterator();
            while (it.hasNext()) {
                if ("ocr".equalsIgnoreCase(it.next().actionName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInitDeviceSuccess() {
        Intent intent;
        String stringExtra;
        if (isOcrEnabled()) {
            try {
                intent = new Intent(this, getClassLoader().loadClass("com.aliyun.aliyunface.ui.OcrGuideFrontActivity"));
            } catch (Exception e) {
                e.printStackTrace();
                sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OCR_NOT_EXIST);
                return;
            }
        } else {
            Intent intent2 = getIntent();
            intent = (intent2 == null || (stringExtra = intent2.getStringExtra(ZIMFacade.ZIM_EXT_PARAMS_KEY_SCREEN_ORIENTATION)) == null || TextUtils.isEmpty(stringExtra) || !stringExtra.equalsIgnoreCase(ZIMFacade.ZIM_EXT_PARAMS_VAL_SCREEN_LAND)) ? null : new Intent(this, (Class<?>) ToygerLandActivity.class);
            if (intent == null) {
                intent = new Intent(this, (Class<?>) ToygerPortActivity.class);
            }
        }
        startActivity(intent);
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getResources().getIdentifier("activity_face_loading", "layout", getPackageName()));
        handlePermissionsAndInit();
    }

    private void showiOSLoading(boolean z) {
        iOSLoadingView iosloadingview = (iOSLoadingView) findViewById(getResources().getIdentifier("iOSLoadingView", "id", getPackageName()));
        if (iosloadingview != null) {
            if (z) {
                iosloadingview.setVisibility(0);
            } else {
                iosloadingview.setVisibility(4);
            }
        }
    }

    private List<String> genUnGrantedToygerPermissions() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 23) {
            for (String str : toygerPermissions) {
                if (checkSelfPermission(str) != 0) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    private void handlePermissionsAndInit() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> listGenUnGrantedToygerPermissions = genUnGrantedToygerPermissions();
            if (listGenUnGrantedToygerPermissions.size() > 0) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "androidPermissionFail", "status", "permissions not granted, left size=" + listGenUnGrantedToygerPermissions.size(), "android_sdk", String.valueOf(Build.VERSION.SDK_INT));
                requestPermissions((String[]) listGenUnGrantedToygerPermissions.toArray(new String[0]), 1024);
                return;
            }
        }
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "androidPermssionOK", "status", "permissions already granted, enter sdk", "android_sdk", String.valueOf(Build.VERSION.SDK_INT));
        init();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        List<String> listGenUnGrantedToygerPermissions = genUnGrantedToygerPermissions();
        if (i == 1024 && listGenUnGrantedToygerPermissions.size() <= 0) {
            RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "androidPermissionOK", "status", "permissions granted, after user comfirm, enter sdk", "android_sdk", String.valueOf(Build.VERSION.SDK_INT));
            init();
        } else {
            RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "androidPermissionFail", "status", "permissions not granted after user confirm, exit sdk", "android_sdk", String.valueOf(Build.VERSION.SDK_INT));
            sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NO_CAMERA_PERMISSION);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorCode(String str) {
        Message messageObtain = Message.obtain();
        messageObtain.what = ToygerConst.TOYGER_UI_MSG_ERROR_CODE;
        messageObtain.obj = str;
        this.uiHandler.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onErrorCode(String str) {
        if (TextUtils.isEmpty(str)) {
            str = ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC;
        }
        sendResponseAndFinish(str);
    }

    private void sendResponseAndFinish(String str) {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "LoadingActivityFinish", "status", j.o);
        finish();
        ToygerPresenter.getInstance().sendResAndExit(str);
    }

    private void init() {
        EnvCheck.EnvErrorType envErrorTypeCheck = EnvCheck.check();
        if (EnvCheck.EnvErrorType.ENV_SUCCESS != envErrorTypeCheck) {
            if (EnvCheck.EnvErrorType.ENV_ERROR_LOW_OS == envErrorTypeCheck) {
                sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_OS_VERSION_LOW);
            } else if (EnvCheck.EnvErrorType.ENV_ERROR_NO_FRONT_CAMERA == envErrorTypeCheck) {
                sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NO_FRANT_CAMERA);
            }
            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "enviromentCheckFail", "result", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE);
            return;
        }
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "enviromentCheckOK", "result", "success");
        String zimId = ToygerPresenter.getInstance().getZimId();
        Intent intent = getIntent();
        String stringExtra = (intent == null || !intent.hasExtra(ToygerConst.TOYGER_META_INFO)) ? "" : intent.getStringExtra(ToygerConst.TOYGER_META_INFO);
        System.out.println("Toyger:zimId:" + zimId + "  metaInfo:" + stringExtra);
        showiOSLoading(true);
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "startNetInit", "zimId", zimId, TTDownloadField.TT_META, stringExtra);
        NetworkEnv networkEnv = ToygerPresenter.getInstance().getNetworkEnv();
        if (networkEnv == null) {
            sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC);
        } else {
            NetworkPresenter.zimInit(networkEnv, zimId, stringExtra, new ZimInitCallback() { // from class: com.aliyun.aliyunface.ui.FaceLoadingActivity.2
                @Override // com.aliyun.aliyunface.network.ZimInitCallback
                public void onSuccess(String str, OSSConfig oSSConfig) {
                    System.out.println("Toyger zimInit onSuccess protocol:" + str);
                    if (oSSConfig != null) {
                        System.out.println("Toyger zimInit onSuccess ossConfig: " + oSSConfig.toString());
                    } else {
                        System.out.println("Toyger zimInit onSuccess ossConfig null ");
                    }
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "netInitResOK", "netSuccess", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, "protocol", str, "ossConfig", oSSConfig.toString());
                    try {
                        Protocol protocol = (Protocol) JSON.parseObject(str, Protocol.class);
                        protocol.parse(protocol.content);
                        if (!protocol.isValid()) {
                            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "netInitResParseError", "parseResult", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE, "protocol", str, OSSConstants.RESOURCE_NAME_OSS, oSSConfig.toString());
                            FaceLoadingActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NET_INIT_ERROR);
                        } else {
                            ToygerPresenter.getInstance().setAndroidClientProtocol(protocol);
                            ToygerPresenter.getInstance().setOssConfig(oSSConfig);
                            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "netInitResParseOK", "parseResult", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, "protocol", str, OSSConstants.RESOURCE_NAME_OSS, oSSConfig.toString());
                            FaceLoadingActivity.this.uiHandler.sendEmptyMessage(ToygerConst.TOYGER_UI_MSG_INIT_DEVICE_SUCCESS);
                        }
                    } catch (Exception unused) {
                        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "netInitResException", "parseSuccess", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE, "protocol", str, OSSConstants.RESOURCE_NAME_OSS, oSSConfig.toString());
                        FaceLoadingActivity.this.sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_NET_INIT_ERROR);
                    }
                }

                @Override // com.aliyun.aliyunface.network.ZimInitCallback
                public void onServerError(String str, String str2) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "netInitResServerError", "status", str, "msg", str2);
                    FaceLoadingActivity.this.sendErrorCode(str);
                }

                @Override // com.aliyun.aliyunface.network.ZimInitCallback
                public void onError(String str, String str2) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "netInitResNetError", "netSuccess", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE, "code", String.valueOf(str), "msg", str2);
                    FaceLoadingActivity.this.sendErrorCode(str);
                }
            });
        }
    }
}
