package com.sqwan.msdk.api.popup;

import android.app.Activity;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.api.IMUrl;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.core.INewUrl;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PopupDialogHttpUtil {
    public static void requestActivePopup(SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("请求激活弹窗");
        SqRequest.of(IMUrl.URL_M_INIT_DIALOG).signV3().addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, String.class);
    }

    public static void requestSubmitRolePopup(HashMap<String, String> map, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("请求进服弹窗");
        Activity activity = SQContextWrapper.getActivity();
        if (activity == null) {
            return;
        }
        SqRequest.of(IMUrl.URL_SUBMIT_ROLE_POPUP).signV3().addParamsTransformer(new CommonParamsV3()).addParam(SqConstants.DSID, map.get(BaseSQwanCore.INFO_SERVERID)).addParam(SqConstants.DRID, map.get(BaseSQwanCore.INFO_ROLEID)).addParam(SqConstants.DSNAME, map.get(BaseSQwanCore.INFO_SERVERNAME)).addParam(SqConstants.DRNAME, map.get(BaseSQwanCore.INFO_SERVERNAME)).addParam(SqConstants.DRLEVEL, map.get(BaseSQwanCore.INFO_ROLELEVEL)).addParam(SqConstants.DVIPLEVEL, map.get(BaseSQwanCore.INFO_VIPLEVEL)).addParam(SqConstants.DRCTIME, map.get(BaseSQwanCore.INFO_ROLE_TIME_CREATE)).addParam(SqConstants.DRLEVELMTIME, map.get(BaseSQwanCore.INFO_ROLE_TIME_LEVEL)).addParam(SqConstants.DPNAME, map.get(BaseSQwanCore.INFO_PARTYNAME)).addParam(SqConstants.DRBALANCE, map.get(BaseSQwanCore.INFO_BALANCE)).addParam("token", AccountCache.getToken(activity)).post(sqHttpCallback, String.class);
    }

    public static void requestPayPopup(String str, String str2, String str3, String str4, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("请求支付弹窗");
        Activity activity = SQContextWrapper.getActivity();
        SqRequest.of(IMUrl.URL_PAY_POPUP).signV3().addParamsTransformer(new CommonParamsV3()).addParam("token", AccountCache.getToken(activity)).addParam(SqConstants.MOID, str).addParam(SqConstants.DOID, str2).addParam(SqConstants.DMONEY, str3).addParam("operate_type", str4).addParam(SqConstants.DSID, MultiSDKUtils.getServerid(activity)).addParam(SqConstants.DRID, MultiSDKUtils.getRoleid(activity)).addParam(SqConstants.DSNAME, MultiSDKUtils.getServerName(activity)).addParam(SqConstants.DRNAME, MultiSDKUtils.getRolename(activity)).post(sqHttpCallback, String.class);
    }

    public static void requestOrderPopups(String str, String str2, String str3, String str4, String str5, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("请求支付前弹窗");
        Activity activity = SQContextWrapper.getActivity();
        SqRequest.of(INewUrl.URL_POPUPS_ORDER).signV3().addParamsTransformer(new CommonParamsV3()).addParam("token", AccountCache.getToken(activity)).addParam(SqConstants.MOID, str).addParam(SqConstants.DOID, str2).addParam(SqConstants.DMONEY, str3).addParam("dpt", str5).addParam("scene", SqTrackCommonKey.sdk).addParam(SqConstants.DSID, MultiSDKUtils.getServerid(activity)).addParam(SqConstants.DRID, MultiSDKUtils.getRoleid(activity)).addParam(SqConstants.DSNAME, MultiSDKUtils.getServerName(activity)).addParam(SqConstants.DRNAME, MultiSDKUtils.getRolename(activity)).addParam(SqConstants.DRLEVEL, str4).post(sqHttpCallback, String.class);
    }
}
