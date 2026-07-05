package com.sy37sdk.account.alifast;

import android.text.TextUtils;
import com.huya.hyhttpdns.dns.NetworkUtil;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.DevicesFingerprint;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.SQContextWrapper;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.UserInfo;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FastLoginHttpUtil {
    public static void requestFastConfig(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_REQUEST_FAST_CONFIG).signV3().addParam("package_name", SQContextWrapper.getApplicationContext().getPackageName()).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback);
    }

    public static void verifyFastToken(String str, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_VERIFY_FAST_TOKEN).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParam("shan_yan_token", str).addParam("display_name", AppUtils.getAppName(SQContextWrapper.getActivity())).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, JSONObject.class);
    }

    public static void fastLogin(UserInfo userInfo, SqHttpCallback<JSONObject> sqHttpCallback) {
        String token = userInfo.getToken();
        String refreshToken = userInfo.getRefreshToken();
        String uname = userInfo.getUname();
        String loginType = TextUtils.isEmpty(userInfo.getLoginType()) ? "" : userInfo.getLoginType();
        byte b = -1;
        int iHashCode = loginType.hashCode();
        if (iHashCode != 50) {
            if (iHashCode == 51 && loginType.equals("3")) {
                b = 1;
            }
        } else if (loginType.equals("2")) {
            b = 0;
        }
        SqRequest sqRequestAddParamsTransformer = SqRequest.of(UrlConstant.URL_LOGIN_FAST).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParam("token", token).addParam(SqConstants.REFRESH_TOKEN, refreshToken).addParam("login_type", b != 0 ? b != 1 ? NetworkUtil.NET_TYPE_COMMON : "wx" : "phone").addParam("display_name", AppUtils.getAppName(SQContextWrapper.getActivity())).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV3());
        if (loginType.equals("2")) {
            sqRequestAddParamsTransformer.addParam("replacement", userInfo.getMobile());
        } else {
            sqRequestAddParamsTransformer.addParam("uname", uname);
        }
        sqRequestAddParamsTransformer.post(sqHttpCallback);
    }
}
