package com.huya.berry.gamesdk.certicate;

import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.huya.berry.gamesdk.Params;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.certicate.CertificateCallback;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.huya.mtp.utils.StringUtils;
import com.huyaudbunify.HuyaAuth;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.track.SqTrackNetKey;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CerticateHelper {
    private static final String BIND_CREDIT_PARAM = "?isWaitingPage&type=2&result=%d&params=%s&sign=%s&appid=5582&ticket=%s&ticketType=%d&ticketAppid=%s&yyuid=%d&deviceId=%s&deviceData=%s&version=%s&client_ua=%s";
    public static final String CREDIT_SCHME_PREFIX = "huyagame";
    public static final String TAG = "CerticateHelper";

    public static String getSchmeName() {
        return CREDIT_SCHME_PREFIX + SdkProperties.appId.get();
    }

    public static String getCreditBindUrl(int i, String str, String str2) {
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        if (defaultToken == null || defaultToken.getToken() == null || defaultToken.getToken().isEmpty()) {
            L.warn(TAG, "get token empty");
        }
        return String.format(getCreditUrl() + BIND_CREDIT_PARAM, Integer.valueOf(i), str, str2, defaultToken.getToken(), Integer.valueOf(defaultToken.getTokenType()), Params.UDB_VERIFY_APP_ID, LoginProperties.uid.get(), "", AppUtils.getDeviceData(), WupHelper.getVersion(), WupHelper.getClientType());
    }

    public static String getCreditUrl() {
        return ArkValue.debuggable() ? "http://hd.huya.com/huya_auth_h5/sdk/sdk.html" : "https://hd.huya.com/huya_auth_h5/sdk/sdk.html";
    }

    public static class GetCertificateParams {
        public String appId;
        public String appOrderId;
        public String biz_no;
        public boolean isPass;
        public String params;
        public String sign;
        public String sourceType;
        public String zmxyAppId;

        public GetCertificateParams(String str, String str2, String str3, String str4, String str5, String str6) {
            this.zmxyAppId = str;
            this.appId = str2;
            this.appOrderId = str3;
            this.sourceType = str4;
            this.params = str5;
            this.sign = str6;
        }

        public GetCertificateParams(String str, String str2, String str3, String str4, String str5, boolean z) {
            this.zmxyAppId = str;
            this.appId = str2;
            this.appOrderId = str3;
            this.sourceType = str4;
            this.biz_no = str5;
            this.isPass = z;
        }
    }

    public static void getCertificateParams(GetCertificateParams getCertificateParams) {
        String str = ArkValue.debuggable() ? "https://udbapi-test.huya.com/web/rname/reportStatus" : "https://udbsec.huya.com/web/rname/reportStatus";
        String str2 = !StringUtils.isNullOrEmpty(getCertificateParams.zmxyAppId) ? getCertificateParams.zmxyAppId : "1001556";
        String str3 = TextUtils.isEmpty(getCertificateParams.params) ? "0" : "4";
        String h5Info = HuyaAuth.getInstance().getH5Info(LoginProperties.uid.get().longValue(), LoginProperties.passport.get(), "");
        String schmeName = getSchmeName();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", LoginProperties.uid.get());
            jSONObject.put("type", str3);
            jSONObject.put("wupData", h5Info);
            jSONObject.put("passed", String.valueOf(getCertificateParams.isPass));
            jSONObject.put("schema", schmeName);
            jSONObject.put("zmxyAppId", str2);
            jSONObject.put(FeedBackConstants.KEY_FB_APPID, getCertificateParams.appId);
            jSONObject.put("appOrderId", getCertificateParams.appOrderId);
            jSONObject.put("sourceType", getCertificateParams.sourceType);
            jSONObject.put(SqTrackNetKey.params, getCertificateParams.params);
            jSONObject.put(SqConstants.SIGN, getCertificateParams.sign);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.putBody(jSONObject.toString().getBytes());
        requestParams.setBodyContentType("application/json;charset=utf-8");
        L.info(TAG, "getCertificateParams, " + jSONObject.toString());
        HttpClient.post(str, requestParams, new HttpClient.HttpHandler() { // from class: com.huya.berry.gamesdk.certicate.CerticateHelper.1
            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                L.info(CerticateHelper.TAG, "getCertificateParams success %d, %s", Integer.valueOf(i), new String(bArr));
                ArkUtils.send(new CertificateCallback.GetCertificateParams(0));
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                L.info(CerticateHelper.TAG, "getCertificateParams onFailure %d", Integer.valueOf(i));
                ArkUtils.send(new CertificateCallback.GetCertificateParams(1));
            }
        });
    }
}
