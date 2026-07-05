package com.duowan.live.one.module.uploadLog;

import android.os.Build;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.module.ArkModule;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.uploadLog.FeedBackInterface;
import com.duowan.live.one.module.uploadLog.Response.AddDeviceDetailsRsp;
import com.duowan.live.one.module.uploadLog.Response.AddFeedBackRsp;
import com.duowan.live.one.module.uploadLog.Response.IsNeedUploadLogRsp;
import com.duowan.live.one.module.uploadLog.function.UploadFileTask;
import com.duowan.live.one.module.uploadLog.function.UploadLogTask;
import com.duowan.live.one.util.AppStatusReportUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.huya.live.utils.DeviceUtils;
import com.huya.mtp.utils.ResourceUtils;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FeedBackModule extends ArkModule {
    private static final String DEVICE = "%s %s %s";
    private static final String NEED = "1";
    private static final String TAG = FeedBackModule.class.getSimpleName();
    private String appId;

    @Override // com.duowan.auk.module.ArkModule
    public void onStart() {
        super.onStart();
        init();
    }

    @Override // com.duowan.auk.module.ArkModule
    public void onStop() {
        super.onStop();
    }

    private void init() {
        String metaValue = ResourceUtils.getMetaValue(ArkValue.gContext, "FB_APPID");
        this.appId = metaValue;
        if (TextUtils.isEmpty(metaValue)) {
            ArkUtils.crashIfDebug("FeedBackModule appid is null!!!", new Object[0]);
            return;
        }
        String strTrim = this.appId.trim();
        this.appId = strTrim;
        L.info(TAG, "appId:%s", strTrim);
    }

    @IASlot(executorID = 1)
    public void onAddFeedBack(final FeedBackInterface.AddFeedBack addFeedBack) {
        L.info(TAG, "onAddFeedBack...");
        if (ArkValue.gIsSnapshot) {
            addFeedBack.mDetail = "【测试版本】" + addFeedBack.mDetail;
        }
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.putBody(FeedBackConstants.KEY_FB_TYPE, addFeedBack.mFeedBackType);
        requestParams.putBody(FeedBackConstants.KEY_FB_DETAILS, addFeedBack.mDetail);
        if (!TextUtils.isEmpty(addFeedBack.mSsid)) {
            requestParams.putBody("ssid", addFeedBack.mSsid);
        }
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        requestParams.putBody("uid", String.valueOf(LoginProperties.uid.get()));
        requestParams.putBody(FeedBackConstants.KEY_FB_ANCHORID, String.valueOf(LoginProperties.uid.get()));
        requestParams.putBody("gid", DeviceUtils.getImei(ArkValue.gContext));
        requestParams.putBody("device", String.format(DEVICE, Build.BRAND, Build.MODEL, Build.VERSION.RELEASE));
        requestParams.putBody(FeedBackConstants.KEY_FB_DEVICETYPE, "2");
        requestParams.putBody(FeedBackConstants.KEY_FB_APPID, this.appId);
        requestParams.putBody("token", defaultToken.getToken());
        requestParams.putBody(FeedBackConstants.KEY_FB_TICKETTYPE, String.valueOf(defaultToken.getTokenType()));
        requestParams.putBody(FeedBackConstants.KEY_FB_APPVERSION, AppStatusReportUtil.getVersion());
        HttpClient.post(FeedBackConstants.FEEDBACK_URL, requestParams, new HttpClient.HttpHandler() { // from class: com.duowan.live.one.module.uploadLog.FeedBackModule.1
            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                AddFeedBackRsp addFeedBackRsp;
                String str = new String(bArr);
                try {
                    addFeedBackRsp = (AddFeedBackRsp) new Gson().fromJson(str, AddFeedBackRsp.class);
                } catch (JsonSyntaxException e) {
                    L.error(FeedBackModule.TAG, "addFeedBack json parse error " + e.getMessage() + " json = " + str);
                    addFeedBackRsp = null;
                }
                if (addFeedBackRsp == null) {
                    L.error(FeedBackModule.TAG, "addFeedBack fail... AddFeedBackRsp == null");
                    return;
                }
                if (!"1".equals(addFeedBackRsp.getResult())) {
                    L.error(FeedBackModule.TAG, "addFeedBack fail... %s", addFeedBackRsp.getDescription());
                    return;
                }
                if ("1".equals(addFeedBackRsp.getIsRequireLog())) {
                    long logBeginTime = addFeedBackRsp.getLogBeginTime();
                    if (addFeedBack.startTime != 0 && addFeedBack.startTime < logBeginTime) {
                        logBeginTime = addFeedBack.startTime;
                    }
                    ArkUtils.send(new FeedBackInterface.UploadLog(addFeedBackRsp.getFbId(), logBeginTime, addFeedBackRsp.getLogEndTime(), addFeedBackRsp.getMaxFileSize()));
                }
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                L.error(FeedBackModule.TAG, "addFeedBack onFailure ->%d", Integer.valueOf(i));
            }
        });
    }

    @IASlot(executorID = 1)
    public void onUploadLog(FeedBackInterface.UploadLog uploadLog) {
        new UploadLogTask(uploadLog.mFbId, uploadLog.mLogBeginTime, uploadLog.mLogEndTime, uploadLog.mMaxFileSize, uploadLog.mIsReload).execute();
    }

    @IASlot(executorID = 1)
    public void onIsNeedUploadLog(FeedBackInterface.IsNeedUploadLog isNeedUploadLog) {
        long jLongValue = LoginProperties.uid.get().longValue();
        String imei = DeviceUtils.getImei(ArkValue.gContext);
        if (jLongValue == 0 && TextUtils.isEmpty(imei)) {
            return;
        }
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.putBody("uid", String.valueOf(jLongValue));
        requestParams.putBody("gid", imei);
        requestParams.putBody(FeedBackConstants.KEY_FB_DEVICETYPE, "2");
        requestParams.putBody(FeedBackConstants.KEY_FB_APPID, this.appId);
        requestParams.putBody(FeedBackConstants.KEY_FB_APPVERSION, AppStatusReportUtil.getVersion());
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        requestParams.putBody("token", defaultToken.getToken());
        requestParams.putBody(FeedBackConstants.KEY_FB_TICKETTYPE, String.valueOf(defaultToken.getTokenType()));
        HttpClient.post(FeedBackConstants.QUERY_IS_NEED_UPLOAD_LOG, requestParams, new HttpClient.HttpHandler() { // from class: com.duowan.live.one.module.uploadLog.FeedBackModule.2
            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                IsNeedUploadLogRsp.FeedbackData[] feedback;
                IsNeedUploadLogRsp isNeedUploadLogRsp = (IsNeedUploadLogRsp) new Gson().fromJson(new String(bArr), IsNeedUploadLogRsp.class);
                if (isNeedUploadLogRsp == null) {
                    L.error(FeedBackModule.TAG, "addFeedBack fail... AddFeedBackRsp == null");
                    return;
                }
                if (!"1".equals(isNeedUploadLogRsp.getIsRequireLog()) || (feedback = isNeedUploadLogRsp.getFeedback()) == null || feedback.length <= 0) {
                    return;
                }
                for (IsNeedUploadLogRsp.FeedbackData feedbackData : feedback) {
                    if (feedbackData.isRequireSupplementary()) {
                        ArkUtils.send(new FeedBackInterface.AddDeviceDetails(feedbackData.getFbId(), feedbackData.getLogBeginTime(), feedbackData.getLogEndTime(), isNeedUploadLogRsp.getMaxFileSize(), "1"));
                    } else {
                        ArkUtils.send(new FeedBackInterface.UploadLog(feedbackData.getFbId(), feedbackData.getLogBeginTime(), feedbackData.getLogEndTime(), isNeedUploadLogRsp.getMaxFileSize(), true));
                    }
                }
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                L.error(FeedBackModule.TAG, "addFeedBack onFailure ->%d", Integer.valueOf(i));
            }
        });
    }

    @IASlot(executorID = 1)
    public void onAddDeviceDetails(final FeedBackInterface.AddDeviceDetails addDeviceDetails) {
        long jLongValue = LoginProperties.uid.get().longValue();
        if (jLongValue == 0 || TextUtils.isEmpty(addDeviceDetails.mFbId)) {
            return;
        }
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.putBody("uid", String.valueOf(jLongValue));
        requestParams.putBody("gid", DeviceUtils.getImei(ArkValue.gContext));
        requestParams.putBody("device", Build.BRAND + " " + Build.MODEL + Build.VERSION.RELEASE);
        requestParams.putBody(FeedBackConstants.KEY_LOG_FBID, addDeviceDetails.mFbId);
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        requestParams.putBody("token", defaultToken.getToken());
        requestParams.putBody(FeedBackConstants.KEY_FB_TICKETTYPE, String.valueOf(defaultToken.getTokenType()));
        HttpClient.post(FeedBackConstants.LOG_ADD_DEVICE_DETAILS, requestParams, new HttpClient.HttpHandler() { // from class: com.duowan.live.one.module.uploadLog.FeedBackModule.3
            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                AddDeviceDetailsRsp addDeviceDetailsRsp = (AddDeviceDetailsRsp) new Gson().fromJson(new String(bArr), AddDeviceDetailsRsp.class);
                if (addDeviceDetailsRsp == null) {
                    return;
                }
                if (!addDeviceDetailsRsp.getResult().equals("1")) {
                    L.debug(FeedBackModule.TAG, "onAddDeviceDetails fail...%s", addDeviceDetailsRsp.getDescription());
                } else {
                    ArkUtils.send(new FeedBackInterface.UploadLog(addDeviceDetails.mFbId, addDeviceDetails.mLogBeginTime, addDeviceDetails.mLogEndTime, addDeviceDetails.mMaxFileSize, "1".equals(addDeviceDetails.mIsReload)));
                }
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                L.error(FeedBackModule.TAG, "addFeedBack onFailure ->%d", Integer.valueOf(i));
            }
        });
    }

    @IASlot
    public void onUploadBeautyBitmap(FeedBackInterface.UploadBeautyBitmap uploadBeautyBitmap) {
        new UploadFileTask(uploadBeautyBitmap.mFbId, uploadBeautyBitmap.mMaxFileSize, uploadBeautyBitmap.mFilePaths).execute();
    }
}
