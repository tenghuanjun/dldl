package com.aliyun.aliyunface.api;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.deviceid.DeviceTokenClient;
import com.alipay.zoloz.toyger.ToygerLog;
import com.alipay.zoloz.toyger.algorithm.Toyger;
import com.alipay.zoloz.toyger.face.ToygerFaceService;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.WorkState;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.NetworkEnv;
import com.aliyun.aliyunface.network.NetworkStore;
import com.aliyun.aliyunface.ui.UICustomParams;
import com.igexin.assist.sdk.AssistPushConsts;
import java.util.HashMap;
import java.util.Map;
import net.security.device.api.SecurityDevice;
import net.security.device.api.SecurityInitListener;
import net.security.device.api.SecuritySession;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZIMFacade {
    public static final String ZIM_EXT_PARAMS_KEY_FACE_PROGRESS_COLOR = "ext_params_key_face_progress_color";
    public static final String ZIM_EXT_PARAMS_KEY_OCR_BOTTOM_BUTTON_COLOR = "ext_params_key_ocr_bottom_button_color";
    public static final String ZIM_EXT_PARAMS_KEY_SCREEN_ORIENTATION = "ext_params_key_screen_orientation";
    public static final String ZIM_EXT_PARAMS_KEY_TIP_INDEX_TT = "ext_params_key_tip_index_tt";
    public static final String ZIM_EXT_PARAMS_KEY_TOP_TIP_INDEX = "ext_params_key_top_tip_index";
    public static final String ZIM_EXT_PARAMS_KEY_USE_VIDEO = "ext_params_key_use_video";
    public static final String ZIM_EXT_PARAMS_VAL_SCREEN_LAND = "ext_params_val_screen_land";
    public static final String ZIM_EXT_PARAMS_VAL_SCREEN_PORT = "ext_params_val_screen_port";
    public static final String ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE = "false";
    public static final String ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE = "true";
    private Context ctx;
    private boolean isBusy = false;
    private ZIMCallback zimCallback = null;

    public void destroy() {
    }

    protected ZIMFacade(Context context) {
        this.ctx = context;
    }

    public ZIMSession getSession() {
        SecuritySession session = SecurityDevice.getInstance().getSession();
        String str = (session == null || 10000 != session.code) ? "" : session.session;
        int i = session != null ? session.code : -1;
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "deviceTokenGetSession", "code", "" + i, "session", str);
        ZIMSession zIMSession = new ZIMSession();
        zIMSession.code = i;
        zIMSession.session = str;
        return zIMSession;
    }

    private static String validateSdk() {
        try {
            System.loadLibrary("stlport_shared");
            try {
                System.loadLibrary("aliyunaf");
                try {
                    System.loadLibrary("deviceid_607");
                    try {
                        System.loadLibrary("zkfv_ts_tj");
                        try {
                            System.loadLibrary("toyger");
                            return "";
                        } catch (Throwable unused) {
                            return ToygerConst.ZcodeConstants.ZCODE_SDK_VALIDATE_ERROR_TOGYER;
                        }
                    } catch (Throwable unused2) {
                        return ToygerConst.ZcodeConstants.ZCODE_SDK_VALIDATE_ERROR_ANT_SEC_ENHANCE;
                    }
                } catch (Throwable unused3) {
                    return ToygerConst.ZcodeConstants.ZCODE_SDK_VALIDATE_ERROR_ANT_ID;
                }
            } catch (Throwable unused4) {
                return ToygerConst.ZcodeConstants.ZCODE_SDK_VALIDATE_ERROR_AF;
            }
        } catch (Throwable unused5) {
            return ToygerConst.ZcodeConstants.ZCODE_SDK_VALIDATE_ERROR_SHARED;
        }
    }

    public void verify(String str, boolean z, ZIMCallback zIMCallback) {
        verify(str, z, (HashMap) null, zIMCallback);
    }

    public void verify(String str, boolean z, HashMap<String, String> map, ZIMCallback zIMCallback) {
        this.zimCallback = zIMCallback;
        String strValidateSdk = validateSdk();
        if (!TextUtils.isEmpty(strValidateSdk)) {
            sendResponse(strValidateSdk);
            return;
        }
        ToygerPresenter.getInstance().setWorkState(WorkState.INIT);
        try {
            Toyger.loadLibrary((Context) null);
        } catch (Throwable unused) {
        }
        if (this.ctx == null) {
            sendResponse(ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC);
            return;
        }
        if (this.isBusy) {
            RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "zimBusy", "status", "busy");
            sendResponse(ToygerConst.ZcodeConstants.ZCODE_ZIM_IS_BUSY);
            return;
        }
        this.isBusy = true;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        System.out.println("Toyger: zim init zimID:" + str);
        ToygerPresenter.getInstance().setZimId(str);
        ToygerPresenter.getInstance().setUseMsgBox(z);
        try {
            DeviceTokenClient.getInstance(this.ctx).initToken("zorro", "elBwppCSr9nB1LIQ", (DeviceTokenClient.InitResultListener) null);
        } catch (Exception unused2) {
        }
        RecordService.getInstance().init(this.ctx, str);
        ToygerPresenter.getInstance().setZimRetCallback(new ZIMRetCallback() { // from class: com.aliyun.aliyunface.api.ZIMFacade.1
            @Override // com.aliyun.aliyunface.api.ZIMRetCallback
            public void onZimFinish(String str2) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "verifyCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                ZIMFacade.this.sendResponse(str2);
            }
        });
        if (map != null && map.containsKey(ZIM_EXT_PARAMS_KEY_USE_VIDEO) && ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equalsIgnoreCase(map.get(ZIM_EXT_PARAMS_KEY_USE_VIDEO))) {
            ToygerPresenter.getInstance().setUseVideo(true);
        }
        if (map != null && map.containsKey(ZIM_EXT_PARAMS_KEY_OCR_BOTTOM_BUTTON_COLOR) && map.containsKey(ZIM_EXT_PARAMS_KEY_FACE_PROGRESS_COLOR)) {
            String str2 = map.get(ZIM_EXT_PARAMS_KEY_OCR_BOTTOM_BUTTON_COLOR);
            if (!TextUtils.isEmpty(str2)) {
                UICustomParams.OCR_BOTTOM_COLOR = str2;
            }
            String str3 = map.get(ZIM_EXT_PARAMS_KEY_FACE_PROGRESS_COLOR);
            if (!TextUtils.isEmpty(str3)) {
                UICustomParams.FACE_PROGRESS_COLOR = str3;
            }
        }
        if (map != null && map.containsKey(ZIM_EXT_PARAMS_KEY_TOP_TIP_INDEX)) {
            String str4 = map.get(ZIM_EXT_PARAMS_KEY_TOP_TIP_INDEX);
            if (!TextUtils.isEmpty(str4) && str4 != null && str4.equalsIgnoreCase(ZIM_EXT_PARAMS_KEY_TIP_INDEX_TT)) {
                Context context = this.ctx;
                UICustomParams.TOP_TIP_TEXT = context.getString(context.getResources().getIdentifier("tantan_top_tip_text", "string", this.ctx.getPackageName()));
            }
        }
        String metaInfos = getMetaInfos(this.ctx);
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader != null) {
            try {
                Class<?> clsLoadClass = classLoader.loadClass("com.aliyun.aliyunface.ui.FaceLoadingActivity");
                if (clsLoadClass != null) {
                    Intent intent = new Intent(this.ctx, clsLoadClass);
                    intent.putExtra(ToygerConst.TOYGER_META_INFO, metaInfos);
                    if (map != null && map.containsKey(ZIM_EXT_PARAMS_KEY_SCREEN_ORIENTATION)) {
                        intent.putExtra(ZIM_EXT_PARAMS_KEY_SCREEN_ORIENTATION, map.get(ZIM_EXT_PARAMS_KEY_SCREEN_ORIENTATION));
                    }
                    this.ctx.startActivity(intent);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sendResponse(ToygerConst.ZcodeConstants.ZCODE_FACE_LOADING_NOT_EXIST);
    }

    public void setCustomUIListener(ZIMUICustomListener zIMUICustomListener) {
        ToygerPresenter.getInstance().setUiCustomListener(zIMUICustomListener);
    }

    public static void installIPv6(Context context) {
        if (context == null || !TextUtils.isEmpty(validateSdk())) {
            return;
        }
        if (ToygerPresenter.getInstance().getNetworkEnv() == null) {
            NetworkEnv networkEnv = new NetworkEnv();
            networkEnv.safUrl = "https://cloudauth-dualstack.aliyuncs.com";
            networkEnv.safBackupUrl = "https://cloudauth-dualstack.cn-beijing.aliyuncs.com";
            networkEnv.appKey = "LTAI4FnprqBfKVt1yjs23kY9";
            networkEnv.appSecret = "hKDBzWfxSNRSq0K8MVTJyCsJ1HeR93hfnxyRkcam9YY=";
            ToygerPresenter.getInstance().setNetworkEnv(networkEnv);
        }
        RecordService.getInstance().init(context, context.getPackageName());
        final long jCurrentTimeMillis = System.currentTimeMillis();
        DeviceTokenClient.getInstance(context).initToken("zorro", "elBwppCSr9nB1LIQ", new DeviceTokenClient.InitResultListener() { // from class: com.aliyun.aliyunface.api.ZIMFacade.2
            @Override // com.alipay.deviceid.DeviceTokenClient.InitResultListener
            public void onResult(String str, int i) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "AlipayDeviceInitCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "AlipayDevice", "code", "" + i, AssistPushConsts.MSG_TYPE_TOKEN, "" + str);
                if (i == 0) {
                    ToygerPresenter.getInstance().setAlipayDeviceToken(str);
                } else {
                    ToygerPresenter.getInstance().setAlipayDeviceToken("");
                }
            }
        });
        final long jCurrentTimeMillis2 = System.currentTimeMillis();
        try {
            SecurityDevice.getInstance().initV6(context, "3f99f9a44d7bfd84458637ae6be5000a", new SecurityInitListener() { // from class: com.aliyun.aliyunface.api.ZIMFacade.3
                @Override // net.security.device.api.SecurityInitListener
                public void onInitFinish(int i) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "AliyunDeviceInitCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis2));
                    if (10000 == i) {
                        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "deviceTokenInitSuccess", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE);
                    } else {
                        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "deviceTokenInitFail", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, "errCode", String.valueOf(i));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        ToygerFaceService.preLoad(context);
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "toygerModelLoadCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis3));
    }

    public static void install(Context context) {
        if (context == null || !TextUtils.isEmpty(validateSdk())) {
            return;
        }
        if (ToygerPresenter.getInstance().getNetworkEnv() == null) {
            NetworkEnv networkEnv = new NetworkEnv();
            networkEnv.safUrl = "https://cloudauth.aliyuncs.com";
            networkEnv.safBackupUrl = "https://cloudauth.cn-beijing.aliyuncs.com";
            networkEnv.appKey = "LTAI4FnprqBfKVt1yjs23kY9";
            networkEnv.appSecret = "hKDBzWfxSNRSq0K8MVTJyCsJ1HeR93hfnxyRkcam9YY=";
            ToygerPresenter.getInstance().setNetworkEnv(networkEnv);
        }
        RecordService.getInstance().init(context, context.getPackageName());
        final long jCurrentTimeMillis = System.currentTimeMillis();
        DeviceTokenClient.getInstance(context).initToken("zorro", "elBwppCSr9nB1LIQ", new DeviceTokenClient.InitResultListener() { // from class: com.aliyun.aliyunface.api.ZIMFacade.4
            @Override // com.alipay.deviceid.DeviceTokenClient.InitResultListener
            public void onResult(String str, int i) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "AlipayDeviceInitCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "AlipayDevice", "code", "" + i, AssistPushConsts.MSG_TYPE_TOKEN, "" + str);
                if (i == 0) {
                    ToygerPresenter.getInstance().setAlipayDeviceToken(str);
                } else {
                    ToygerPresenter.getInstance().setAlipayDeviceToken("");
                }
            }
        });
        final long jCurrentTimeMillis2 = System.currentTimeMillis();
        try {
            SecurityDevice.getInstance().init(context, "3f99f9a44d7bfd84458637ae6be5000a", new SecurityInitListener() { // from class: com.aliyun.aliyunface.api.ZIMFacade.5
                @Override // net.security.device.api.SecurityInitListener
                public void onInitFinish(int i) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "AliyunDeviceInitCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis2));
                    if (10000 == i) {
                        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "deviceTokenInitSuccess", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE);
                    } else {
                        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "deviceTokenInitFail", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, "errCode", String.valueOf(i));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        ToygerFaceService.preLoad(context);
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "toygerModelLoadCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis3));
    }

    private static ZIMMetaInfo getZimMetaInfo(Context context) {
        return getZimMetaInfo(context, (Map) null);
    }

    private static ZIMMetaInfo getZimMetaInfo(Context context, Map<String, Object> map) {
        AppDataProvider appDataProvider = new AppDataProvider() { // from class: com.aliyun.aliyunface.api.ZIMFacade.6
            @Override // com.aliyun.aliyunface.api.AppDataProvider
            public String getDeviceType() {
                return "android";
            }

            @Override // com.aliyun.aliyunface.api.AppDataProvider
            public String getApdidToken(Context context2) {
                return ZIMFacade.getApDidToken(context2);
            }

            @Override // com.aliyun.aliyunface.api.AppDataProvider
            public String getDeviceModel() {
                return Build.MODEL;
            }

            @Override // com.aliyun.aliyunface.api.AppDataProvider
            public String getAppName(Context context2) {
                return context2 == null ? "" : context2.getPackageName();
            }

            @Override // com.aliyun.aliyunface.api.AppDataProvider
            public String getAppVersion(Context context2) {
                try {
                    return context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    ToygerLog.w(e);
                    return "";
                }
            }

            @Override // com.aliyun.aliyunface.api.AppDataProvider
            public String getOsVersion() {
                return Build.VERSION.RELEASE;
            }
        };
        ZIMMetaInfo zIMMetaInfo = new ZIMMetaInfo();
        zIMMetaInfo.setApdidToken(appDataProvider.getApdidToken(context));
        zIMMetaInfo.setAppName(appDataProvider.getAppName(context));
        zIMMetaInfo.setAppVersion(appDataProvider.getAppVersion(context));
        zIMMetaInfo.setDeviceModel(appDataProvider.getDeviceModel());
        zIMMetaInfo.setDeviceType(appDataProvider.getDeviceType());
        zIMMetaInfo.setOsVersion(appDataProvider.getOsVersion());
        zIMMetaInfo.setBioMetaInfo(ToygerConst.TOYGER_BIO_META_INFO);
        zIMMetaInfo.setZimVer(ToygerConst.TOYGER_ZIM_VERSION);
        zIMMetaInfo.setSdkVersion(ToygerConst.TOYGER_SDK_VERSION);
        return zIMMetaInfo;
    }

    public static String getMetaInfos(Context context) {
        return getMetaInfos(context, (Map) null);
    }

    public static String getMetaInfos(Context context, Map<String, Object> map) {
        return getMetaInfos(context, map, true);
    }

    private static String getMetaInfos(Context context, Map<String, Object> map, boolean z) {
        try {
            return JSON.toJSONString(getZimMetaInfo(context, map));
        } catch (Throwable th) {
            ToygerLog.e(th);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getApDidToken(Context context) {
        String alipayDeviceToken = ToygerPresenter.getInstance().getAlipayDeviceToken();
        return TextUtils.isEmpty(alipayDeviceToken) ? "" : alipayDeviceToken;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendResponse(String str) {
        if (TextUtils.isEmpty(str)) {
            str = ToygerConst.ZcodeConstants.ZCODE_SYSTEM_EXC;
        }
        if (this.zimCallback != null) {
            ZIMResponse zIMResponse = new ZIMResponse();
            zIMResponse.reason = str;
            zIMResponse.deviceToken = ToygerPresenter.getInstance().getAliyunDeviceToken();
            if (ToygerPresenter.getInstance().getUseVideo()) {
                zIMResponse.videoFilePath = ToygerPresenter.getInstance().getVideoFilePath();
            }
            if (str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_VERIFY_SUCCESS)) {
                zIMResponse.code = 1000;
            } else if (str.startsWith(ToygerConst.ZcodeConstants.ZCODE_VERIFY_FAIL_PREFIX)) {
                String[] strArrSplit = str.split("_");
                if (2 == strArrSplit.length) {
                    zIMResponse.reason = strArrSplit[1];
                }
                zIMResponse.code = ZIMResponseCode.ZIM_RESPONSE_FAIL;
            } else if (!str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_INIT_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_UPLOAD_IMAGE_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NET_VERIFY_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NETWORK_TIMEOUT) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_NETWORK_ERROR) && !str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_OSS_TOKEN_INVALID)) {
                if (str.equalsIgnoreCase(ToygerConst.ZcodeConstants.ZCODE_USER_BACK)) {
                    zIMResponse.code = 1003;
                } else if (str.equalsIgnoreCase(String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID))) {
                    zIMResponse.code = ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID;
                } else {
                    zIMResponse.code = 1001;
                }
            } else {
                zIMResponse.code = ZIMResponseCode.ZIM_RESPONSE_NETWORK_FAIL;
            }
            this.zimCallback.response(zIMResponse);
        }
        this.isBusy = false;
    }

    public static void reportCrash(String str, ZIMCrashCallback zIMCrashCallback) {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "appCrash", "crashInfo", str);
        RecordService.getInstance().reportCrash(zIMCrashCallback);
    }

    public static Map getNetStore() {
        return NetworkStore.getInstance().get();
    }

    public static void setNetworkEnv(NetworkEnv networkEnv) {
        ToygerPresenter.getInstance().setNetworkEnv(networkEnv);
    }

    public static NetworkEnv getNetworkEnv() {
        return ToygerPresenter.getInstance().getNetworkEnv();
    }
}
