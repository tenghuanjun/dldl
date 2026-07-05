package com.sy37sdk.account;

import android.content.Context;
import android.text.TextUtils;
import com.huya.component.login.LoginProperties;
import com.huya.statistics.core.StatisticsContent;
import com.sdk.sq.net.SqVerifyError;
import com.sq.tool.network.SignInterceptor;
import com.sq.tool.network.SignV2Interceptor;
import com.sq.tool.network.SignV3Interceptor;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.DevicesFingerprint;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.bugless.core.Constant;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.request.CommonParamsV2;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.MD5Util;
import com.sqwan.msdk.api.IMUrl;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.order.nat.trade.NativePayWay;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountRequestManager {
    private final Context mContext;

    public AccountRequestManager(Context context) {
        this.mContext = context;
    }

    public void loginRequest(String str, String str2, boolean z, SqHttpCallback<String> sqHttpCallback) {
        SqRequest sqRequestAddParamsTransformer = SqRequest.of(UrlConstant.LOGIN).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(this.mContext)).addParam("uname", str).addParam(SqConstants.UPWD, str2).addParam("signType", SqConstants.PURCHASE_DETAIL_ALL).addParam("display_name", AppUtils.getAppName(this.mContext)).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV2());
        if (z) {
            sqRequestAddParamsTransformer.addParam(LoginProperties.MarkLoginType, "phone");
        }
        sqRequestAddParamsTransformer.post(sqHttpCallback, String.class);
    }

    public void checkAccountList(String str, String str2, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.CHECK_ACCOUNT_LIST).signV3().addParam("uname", str).addParam("os", IMUrl.OS).addParam(SqConstants.UPWD, str2).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    public void registerRequest(String str, String str2, SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(UrlConstant.REG).signV2(new SignV2Interceptor.SignExt(str).append(str2)).addHeader("D-Token", DevicesFingerprint.getDevToken(this.mContext)).addParam("uname", str).addParam(SqConstants.UPWD, str2).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback, String.class);
    }

    public void reportDevDuration(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_REPORT_DEV_DURATION).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("type", isLandScape(this.mContext) ? "1" : "2").addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    public void reportUserDuration(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_REPORT_USER_DURATION).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("type", isLandScape(this.mContext) ? "1" : "2").addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    public void antiIndulge(String str, SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(UrlConstant.CHECK_ANTI_AUTHENT).signV3().addParam("uname", AccountCache.getUsername(this.mContext)).addParam(SqConstants.PDATA, "").addParam("type", isLandScape(this.mContext) ? "1" : "2").addParam(StatisticsContent.FROM, IMUrl.OS).addParam(Constant.ACTION_TYPE, (TextUtils.isEmpty(str) || !str.equals(LoginTractionManager.TRACK_LOGIN_TYPE_REGISTER)) ? "login" : "reg").addParam("token", AccountCache.getToken(this.mContext)).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback, String.class);
    }

    public void autoAccountRequest(SqHttpCallback<String> sqHttpCallback) {
        SQAppConfig sQAppConfig = ConfigManager.getInstance(this.mContext).getSQAppConfig();
        String gameid = sQAppConfig.getGameid();
        String partner = sQAppConfig.getPartner();
        String refer = sQAppConfig.getRefer();
        String value = DevLogic.getInstance(this.mContext).getValue();
        String strValueOf = String.valueOf(System.currentTimeMillis() / 1000);
        HashMap map = new HashMap();
        map.put("pid", partner);
        map.put("gid", gameid);
        map.put("refer", refer);
        map.put("dev", value);
        map.put("time", strValueOf);
        map.put("scut", ConfigManager.getInstance(this.mContext).getLoginCode() + "");
        map.put("gwversion", "4.6.7");
        map.put(SqConstants.SIGN, MD5Util.Md5(partner + gameid + refer + value + strValueOf).toLowerCase());
        SqRequest.of(UrlConstant.AUTO_SET_ACCOUNT).formParams(map).post(sqHttpCallback, String.class);
    }

    public void wxAuthRequest(String str, final SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(UrlConstant.WX_AUTH).signV3().addParam("authApp", NativePayWay.PWAY_KEY_WECHAT).addParam("oauthCode", str).addParam(SqConstants.LOCALE, "zh-cn").addParam("scene", SqTrackCommonKey.sdk).addParam("reqFrom", IMUrl.OS).addParamsTransformer(new CommonParamsV2()).post(new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountRequestManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                if (sqHttpCallback == null) {
                    return;
                }
                if (AccountRequestManager.verifySign(str2)) {
                    sqHttpCallback.onSuccess(str2);
                } else {
                    sqHttpCallback.onFailure(-1, "验证服务端返回数据sign值失败", new SqVerifyError("验证服务端返回数据sign值失败"));
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 == null) {
                    return;
                }
                sqHttpCallback2.onFailure(i, str2, volleyError);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 == null) {
                    return;
                }
                sqHttpCallback2.onResponseStateError(i, i2, str2, str3);
            }
        }, String.class);
    }

    public void appropriateAgeProtocol(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.APPROPRIATE_AGE_PROTOCOL).signV3().addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    public void floatWindow(SqHttpCallback<JSONObject> sqHttpCallback) {
        Context context = this.mContext;
        SqRequest.of(UrlConstant.FLOAT_WINDOW_V2).signV3().addParam("token", AccountCache.getToken(context)).addParam("scut", Integer.valueOf(ConfigManager.getInstance(context).getLoginCode())).addParamsTransformer(new CommonParamsV3()).get(sqHttpCallback);
    }

    public void getPtUserInfo(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_GET_PT_USER_INFO).sign(SignInterceptor.SignVersion.V4, null).addParam("appid", "float_window").addParam("token", AccountCache.getToken(this.mContext)).addParam(SqConstants.LOCALE, "zh-cn").addParamsTransformer(new CommonParamsV3()).get(new UserHttpCallback(sqHttpCallback));
    }

    public void floatRedPoint(String str, String str2, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest sqRequestAddParamsTransformer = SqRequest.of(UrlConstant.URL_GET_RED_POINT).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("scut", Integer.valueOf(ConfigManager.getInstance(this.mContext).getLoginCode())).addParamsTransformer(new CommonParamsV3());
        if (!TextUtils.isEmpty(str)) {
            sqRequestAddParamsTransformer.addParam("page_uuid", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sqRequestAddParamsTransformer.addParam("red_dot_title", str2);
        }
        sqRequestAddParamsTransformer.get(sqHttpCallback);
    }

    public void getFloatWarning(String str, String str2, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest sqRequestAddParamsTransformer = SqRequest.of(UrlConstant.URL_GET_FLOAT_WARNING).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("scut", Integer.valueOf(ConfigManager.getInstance(this.mContext).getLoginCode())).addParamsTransformer(new CommonParamsV3());
        if (!TextUtils.isEmpty(str)) {
            sqRequestAddParamsTransformer.addParam("page_uuid", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sqRequestAddParamsTransformer.addParam("red_dot_title", str2);
        }
        sqRequestAddParamsTransformer.post(sqHttpCallback);
    }

    public void floatRedCalled(String str, String str2, SqHttpCallback<Void> sqHttpCallback) {
        SqRequest sqRequestAddParamsTransformer = SqRequest.of(UrlConstant.URL_GET_RED_CALLED).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("scut", Integer.valueOf(ConfigManager.getInstance(this.mContext).getLoginCode())).addParamsTransformer(new CommonParamsV3());
        if (!TextUtils.isEmpty(str)) {
            sqRequestAddParamsTransformer.addParam("page_uuid", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sqRequestAddParamsTransformer.addParam("red_dot_title", str2);
        }
        sqRequestAddParamsTransformer.post(sqHttpCallback, Void.class);
    }

    public void requestCertifyId(String str, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_ALI_CERTIFY_FACE_ID).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("meta_info", str).addParam("scut", Integer.valueOf(ConfigManager.getInstance(this.mContext).getLoginCode())).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback);
    }

    public void needFaceVerify(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_NEED_ALI_CERTIFY_FACE).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("scene_id", "1").addParam("scut", Integer.valueOf(ConfigManager.getInstance(this.mContext).getLoginCode())).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback);
    }

    public void checkValidCertify(String str, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_CHECK_VALIDATE_VERIFY).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("certify_id", str).addParam("scut", Integer.valueOf(ConfigManager.getInstance(this.mContext).getLoginCode())).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback);
    }

    public void getAvatarList(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_GET_AVATAR_LIST).sign(SignInterceptor.SignVersion.V4, null).addParam("appid", "float_window").addParamsTransformer(new CommonParamsV3()).get(new UserHttpCallback(sqHttpCallback));
    }

    public void modifyPersonInfo(String str, String str2, SqHttpCallback<Void> sqHttpCallback) {
        SqRequest sqRequestAddParamsTransformer = SqRequest.of(UrlConstant.URL_MODIFY_PERSON_INFO).sign(SignInterceptor.SignVersion.V4, null).addParam("appid", "float_window").addParam("token", AccountCache.getToken(this.mContext)).addParam(SqConstants.LOCALE, "zh-cn").addParamsTransformer(new CommonParamsV3());
        if (!TextUtils.isEmpty(str)) {
            sqRequestAddParamsTransformer.addParam("nickname", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sqRequestAddParamsTransformer.addParam("avatar", str2);
        }
        sqRequestAddParamsTransformer.post(new UserHttpCallback(sqHttpCallback), Void.class);
    }

    public void getRedPacketSwitch(String str, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.REDPACKET_FLOATVIEW_SWITCH).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam("start_service_ts", str).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    public void reportOnlineDuration(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_REPORT_DEV_ONLINE).signV3().addParam("token", AccountCache.getToken(this.mContext)).addParam(StatisticsContent.FROM, IMUrl.OS).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    private static boolean isLandScape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private static class UserHttpCallback<Data> extends SqHttpCallback<Data> {
        final SqHttpCallback<Data> mCallback;

        @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
        protected String getDataKey() {
            return FunctionRouter.KEY_DATA;
        }

        @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
        protected String getMsgKey() {
            return "errmsg";
        }

        @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
        protected int getOkState() {
            return 0;
        }

        @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
        protected String getStateKey() {
            return "errcode";
        }

        UserHttpCallback(SqHttpCallback<Data> sqHttpCallback) {
            this.mCallback = sqHttpCallback;
        }

        @Override // com.sdk.sq.net.SqRequestCallback
        public void onResponseStateError(int i, int i2, String str, String str2) {
            SqHttpCallback<Data> sqHttpCallback = this.mCallback;
            if (sqHttpCallback != null) {
                sqHttpCallback.onResponseStateError(i, i2, str, str2);
            }
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onSuccess(Data data) {
            SqHttpCallback<Data> sqHttpCallback = this.mCallback;
            if (sqHttpCallback != null) {
                sqHttpCallback.onSuccess(data);
            }
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onFailure(int i, String str, VolleyError volleyError) {
            SqHttpCallback<Data> sqHttpCallback = this.mCallback;
            if (sqHttpCallback != null) {
                sqHttpCallback.onFailure(i, str, volleyError);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean verifySign(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(SqConstants.SIGN);
            HashMap map = new HashMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!SqConstants.SIGN.equals(next)) {
                    map.put(next, jSONObject.getString(next));
                }
            }
            String strSign = SignV3Interceptor.sign(map);
            LogUtil.i("本地计算出来的签名值为：" + strSign);
            return strSign.equals(string);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
