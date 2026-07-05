package com.sqwan.msdk.api;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.sq.tool.network.SignInterceptor;
import com.sq.tool.network.SignV2Interceptor;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.Logger;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.bugless.core.Constant;
import com.sqwan.common.FakeActive;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.sqwan.common.dialog.CommonProgressDialog;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.AESUtil;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqRequestCallBack;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.utils.ZipString;
import com.sqwan.order.base.PayInfoModel;
import com.sy37sdk.account.device.DevicesInfo;
import com.sy37sdk.utils.Util;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MRequestManager {
    private static final String REQUEST_TRANS_INFO_KEY = "request_trans_info_key";
    private boolean isInitRequest = false;
    private Context mContext;
    private CommonProgressDialog waitDialog;

    public MRequestManager(Context context) {
        this.mContext = context;
    }

    public void active(final SqHttpCallback<JSONObject> sqHttpCallback) {
        this.isInitRequest = true;
        HashMap map = new HashMap();
        map.put("mac", MacLogic.getInstance(this.mContext).getValue());
        map.put("imei", ImeiLogic.getInstance(this.mContext).getValue());
        map.put("wpi", "" + MultiSDKUtils.getWpixels(this.mContext) + "");
        map.put("hpi", "" + MultiSDKUtils.getHpixels(this.mContext) + "");
        map.put(SqConstants.MODE, Build.MODEL);
        map.put("os", IMUrl.OS);
        map.put(SqTrackCommonKey.os_desc, DeviceUtils.getOs());
        map.put("over", IMUrl.OSVER + "");
        map.put("brand", Build.BRAND);
        map.put("phone", MultiSDKUtils.getNumber(this.mContext) + "");
        map.put(SqConstants.DPGN, this.mContext.getPackageName() + "");
        map.put(SqConstants.NWK, MultiSDKUtils.getNetType(this.mContext) + "");
        map.put(SqConstants.SUA, "1");
        map.put(Constant.PKG_VERSION_CODE, Util.getVersionCode(this.mContext) + "");
        map.put(SqTrackCommonKey.battery_level, String.valueOf(DeviceUtils.getBatteryLevel(this.mContext)));
        map.put(SqTrackCommonKey.battery_status, String.valueOf(DeviceUtils.getBatteryStatus(this.mContext)));
        map.put("ssid", DeviceUtils.getWifiSSID(this.mContext));
        map.put(SqTrackCommonKey.bssid, DeviceUtils.getWifiBSSID(this.mContext));
        map.put("display_name", AppUtils.getAppName(this.mContext));
        map.put("pluginVersion", String.valueOf(VersionUtil.getPluginVersion(this.mContext)));
        map.put(SqConstants.TRANS_INFO, getActiveTransInfo(this.mContext));
        DevicesInfo.setDeviceInfoFromMap(map);
        final String str = IMUrl.URL_M_INIT + "?t=" + System.currentTimeMillis() + "";
        final SqRequest sqRequestAddParamsTransformer = SqRequest.of(str).signV5().formParams(map).addParamsTransformer(new MInitParams());
        sqRequestAddParamsTransformer.post(new SqHttpCallback<JSONObject>() { // from class: com.sqwan.msdk.api.MRequestManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                FakeActive fakeActive = FakeActive.INSTANCE;
                Context context = MRequestManager.this.mContext;
                String responseStr = getResponseStr();
                FakeActive.INSTANCE.getClass();
                fakeActive.saveContent(context, responseStr, "sq_m_fake_active_content");
                sqHttpCallback.onSuccess(jSONObject);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                sqHttpCallback.onResponseStateError(i, i2, str2, str3);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                JSONObject jSONObject;
                HashMap map2 = new HashMap();
                map2.put(SqTrackKey.fail_code, i + "");
                map2.put(SqTrackKey.reason_fail, str2);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init_fail, map2);
                FakeActive fakeActive = FakeActive.INSTANCE;
                Context context = MRequestManager.this.mContext;
                FakeActive.INSTANCE.getClass();
                try {
                    jSONObject = new JSONObject(fakeActive.requireContent(context, "sq_m_fake_active_content", str, sqRequestAddParamsTransformer.getRequestParams(), SignInterceptor.SignVersion.V1)).getJSONObject(FunctionRouter.KEY_DATA);
                } catch (Exception unused) {
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    Logger.info("本地m层激活有缓存, 本地激活成功", new Object[0]);
                    sqHttpCallback.onSuccess(jSONObject);
                    return;
                }
                try {
                    Logger.warning("本地m层激活未读到缓存, 返回默认结果", new Object[0]);
                    sqHttpCallback.onSuccess(buildFakeData());
                } catch (Exception unused2) {
                    Logger.error("默认结果构建失败, m层激活失败", new Object[0]);
                    sqHttpCallback.onFailure(i, "网络异常，请稍候再试", volleyError);
                }
            }

            private JSONObject buildFakeData() throws Exception {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dev", DevLogic.getInstance(MRequestManager.this.mContext).getValue());
                return jSONObject;
            }
        });
    }

    public String getActiveTransInfo(Context context) {
        String string = MultiSDKUtils.getString(context, REQUEST_TRANS_INFO_KEY);
        MultiSDKUtils.removeString(context, REQUEST_TRANS_INFO_KEY);
        LogUtil.d("SqRequest#getTransInfo: " + string);
        return !TextUtils.isEmpty(string) ? Base64.encodeToString(string.getBytes(), 2) : "";
    }

    public void checkActivationCodeRequest(String str, final MRequestCallBack mRequestCallBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext) + "");
        map.put("mac", MacLogic.getInstance(this.mContext).getValue());
        map.put("imei", ImeiLogic.getInstance(this.mContext).getValue());
        map.put(SqConstants.IDFA, IMUrl.OS);
        map.put("betac", str + "");
        post(IMUrl.URL_ACTIVATION_CODE_CHECK, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str), new SqRequestCallBack() { // from class: com.sqwan.msdk.api.MRequestManager.2
            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestSuccess(String str2) {
                mRequestCallBack.onRequestSuccess(str2);
            }

            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestError(String str2) {
                mRequestCallBack.onRequestError(str2);
            }
        }, true, true);
    }

    public void orderRequest(PayInfoModel payInfoModel, String str, SqRequestCallBack sqRequestCallBack) {
        String userid = MultiSDKUtils.getUserid(this.mContext);
        String username = MultiSDKUtils.getUsername(this.mContext);
        String token = MultiSDKUtils.getToken(this.mContext);
        HashMap<String, String> map = new HashMap<>();
        map.put(SqConstants.DOID, payInfoModel.getOrderId());
        map.put(SqConstants.DSID, payInfoModel.getServerId());
        map.put(SqConstants.DSNAME, payInfoModel.getServerName());
        map.put(SqConstants.DEXT, payInfoModel.getExtend());
        map.put(SqConstants.DRID, payInfoModel.getRoleId());
        map.put(SqConstants.DRNAME, payInfoModel.getRoleName());
        map.put(SqConstants.DRLEVEL, payInfoModel.getRoleLevel() + "");
        map.put(SqConstants.DMONEY, payInfoModel.getMoney() + "");
        map.put(SqConstants.DRADIO, payInfoModel.getRadio() + "");
        map.put("uid", userid);
        map.put("uname", username);
        map.put(SqConstants.PDATA, str);
        map.put(SqConstants.SUA, "1");
        map.put("os", "1");
        map.put(SqTrackCommonKey.os_desc, DeviceUtils.getOs());
        map.put("token", token);
        String transInfo = SqRequest.getTransInfo();
        map.put(SqConstants.TRANS_INFO, transInfo);
        post(IMUrl.URL_M_ORDER, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(payInfoModel.getOrderId()).append(payInfoModel.getServerId()).append(userid).append(username).append(token).append(transInfo), sqRequestCallBack, true, false);
    }

    public void submitRoleInfoRequst(HashMap<String, String> map, final MRequestCallBack mRequestCallBack) {
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null) {
            map2.put(SqConstants.DSID, map.get(BaseSQwanCore.INFO_SERVERID) + "");
            map2.put(SqConstants.DSNAME, map.get(BaseSQwanCore.INFO_SERVERNAME) + "");
            map2.put(SqConstants.DRID, map.get(BaseSQwanCore.INFO_ROLEID) + "");
            map2.put(SqConstants.DRNAME, map.get(BaseSQwanCore.INFO_ROLENAME) + "");
            map2.put(SqConstants.DRLEVEL, map.get(BaseSQwanCore.INFO_ROLELEVEL) + "");
            map2.put(SqConstants.DRBALANCE, map.get(BaseSQwanCore.INFO_BALANCE) + "");
            map2.put(SqConstants.DPNAME, map.get(BaseSQwanCore.INFO_PARTYNAME) + "");
            map2.put(SqConstants.DVIPLEVEL, map.get(BaseSQwanCore.INFO_VIPLEVEL) + "");
            map2.put(SqConstants.DRCTIME, map.get(BaseSQwanCore.INFO_ROLE_TIME_CREATE) + "");
            map2.put(SqConstants.DRLEVELMTIME, map.get(BaseSQwanCore.INFO_ROLE_TIME_LEVEL) + "");
        }
        map2.put("uid", MultiSDKUtils.getUserid(this.mContext) + "");
        map2.put("uname", MultiSDKUtils.getUsername(this.mContext) + "");
        map2.put("token", MultiSDKUtils.getToken(this.mContext) + "");
        map2.put("display_name", AppUtils.getAppName(this.mContext));
        SignV2Interceptor.SignExt signExt = new SignV2Interceptor.SignExt("");
        if (map != null) {
            signExt.append(map.get(BaseSQwanCore.INFO_SERVERID) + "");
        }
        signExt.append(VersionUtil.sdkVersion);
        String str = IMUrl.URL_M_ENTER;
        if ("".equals(str.trim())) {
            str = IMUrl.URL_M_ENTER;
        }
        post(str, map2, SignInterceptor.SignVersion.V2, signExt, new SqRequestCallBack() { // from class: com.sqwan.msdk.api.MRequestManager.3
            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestSuccess(String str2) {
                mRequestCallBack.onRequestSuccess(str2);
            }

            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestError(String str2) {
                mRequestCallBack.onRequestError(str2);
            }
        }, false, true);
    }

    public void statisticsRequst(String str, String str2, final MRequestCallBack mRequestCallBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("k", str + "");
        map.put("v", str2 + "");
        map.put("uid", MultiSDKUtils.getUserid(this.mContext) + "");
        map.put("uname", MultiSDKUtils.getUsername(this.mContext) + "");
        post(IMUrl.URL_M_SUBMIT, map, SignInterceptor.SignVersion.V2, null, new SqRequestCallBack() { // from class: com.sqwan.msdk.api.MRequestManager.4
            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestSuccess(String str3) {
                mRequestCallBack.onRequestSuccess(str3);
            }

            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestError(String str3) {
                mRequestCallBack.onRequestError(str3);
            }
        }, false, true);
    }

    public void payQueryRequst(String str, String str2, final MRequestCallBack mRequestCallBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("oid", str2 + "");
        map.put("puid", MultiSDKUtils.getPlatUserid(this.mContext) + "");
        map.put("puname", MultiSDKUtils.getPlatUsername(this.mContext) + "");
        map.put("uid", MultiSDKUtils.getUserid(this.mContext) + "");
        map.put("uname", MultiSDKUtils.getUsername(this.mContext) + "");
        post(str, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str2), new SqRequestCallBack() { // from class: com.sqwan.msdk.api.MRequestManager.5
            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestSuccess(String str3) {
                mRequestCallBack.onRequestSuccess(str3);
            }

            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestError(String str3) {
                mRequestCallBack.onRequestError(str3);
            }
        }, false, true);
    }

    public void uploadDeviceInfo(String str, String str2) {
        LogUtil.i("upload device info-->" + str);
        try {
            SqRequest.of(str2).signV3().addParam(FunctionRouter.KEY_DATA, Base64.encodeToString(AESUtil.encrypt(str), 2)).addParamsTransformer(new CommonParamsV3()).post(null, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void post(String str, HashMap<String, String> map, SignInterceptor.SignVersion signVersion, SignV2Interceptor.SignExt signExt, final SqRequestCallBack sqRequestCallBack, boolean z, final boolean z2) {
        if (z) {
            CommonProgressDialog commonProgressDialog = new CommonProgressDialog(this.mContext);
            this.waitDialog = commonProgressDialog;
            commonProgressDialog.setCancelable(false);
            this.waitDialog.setMessage("加载中...");
            this.waitDialog.show();
        } else {
            CommonProgressDialog commonProgressDialog2 = this.waitDialog;
            if (commonProgressDialog2 != null) {
                commonProgressDialog2.dismiss();
            }
        }
        SqRequest.of(str).sign(signVersion, signExt).formParams(map).addParamsTransformer(new CommonParamsV1()).post(new SqHttpCallback<JSONObject>() { // from class: com.sqwan.msdk.api.MRequestManager.6
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                if (MRequestManager.this.waitDialog != null && MRequestManager.this.waitDialog.isShowing()) {
                    MRequestManager.this.waitDialog.dismiss();
                }
                SqRequestCallBack sqRequestCallBack2 = sqRequestCallBack;
                if (sqRequestCallBack2 != null) {
                    sqRequestCallBack2.onRequestSuccess(getResponseStr());
                }
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                if (MRequestManager.this.waitDialog != null && MRequestManager.this.waitDialog.isShowing()) {
                    MRequestManager.this.waitDialog.dismiss();
                }
                SqRequestCallBack sqRequestCallBack2 = sqRequestCallBack;
                if (sqRequestCallBack2 != null) {
                    sqRequestCallBack2.onRequestSuccess(getResponseStr());
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                if (MRequestManager.this.waitDialog != null && MRequestManager.this.waitDialog.isShowing()) {
                    MRequestManager.this.waitDialog.dismiss();
                }
                SqRequestCallBack sqRequestCallBack2 = sqRequestCallBack;
                if (sqRequestCallBack2 != null) {
                    sqRequestCallBack2.onRequestError(i, "网络异常，请稍候再试");
                }
                if (z2) {
                    MultiSDKUtils.showTips(MRequestManager.this.mContext, "网络请求失败，请重试");
                }
            }
        });
    }

    public void reportDev(final Context context, final String str) {
        HashMap map = new HashMap();
        map.put("oaid", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_oaid_request, map);
        String str2 = IMUrl.URL_REPORT_MDEV;
        try {
            JSONObject jSONObject = new JSONObject(str);
            TreeMap treeMap = new TreeMap();
            treeMap.put("pid", Integer.valueOf(Integer.parseInt(MultiSDKUtils.getPID(context))));
            treeMap.put("gid", Integer.valueOf(Integer.parseInt(MultiSDKUtils.getGID(context))));
            treeMap.put("refer", MultiSDKUtils.getRefer(context));
            treeMap.put("dev", DevLogic.getInstance(this.mContext).getValue());
            treeMap.put("sversion", VersionUtil.sdkVersion);
            treeMap.put("version", AppUtils.getVersionName(context));
            treeMap.put("identify", jSONObject);
            String str3 = getSecondTimestampTwo(new Date()) + "";
            treeMap.put("time", str3);
            String str4 = mapToJson(treeMap).toString() + ZipString.zipString2Json(MultiSDKUtils.getKey(context));
            LogUtil.w("originSign: " + str4);
            String strMd5 = Util.Md5(str4);
            TreeMap treeMap2 = new TreeMap();
            treeMap2.put("pid", Integer.valueOf(Integer.parseInt(MultiSDKUtils.getPID(context))));
            treeMap2.put("gid", Integer.valueOf(Integer.parseInt(MultiSDKUtils.getGID(context))));
            treeMap2.put("refer", MultiSDKUtils.getRefer(context));
            treeMap2.put("dev", DevLogic.getInstance(context).getValue());
            treeMap2.put("sversion", VersionUtil.sdkVersion);
            treeMap2.put("version", AppUtils.getVersionName(context));
            treeMap2.put("identify", jSONObject);
            treeMap2.put("time", str3);
            treeMap2.put(SqConstants.SIGN, strMd5);
            SqRequest.of(str2).jsonParams(treeMap2).post(new SqHttpCallback<Void>() { // from class: com.sqwan.msdk.api.MRequestManager.7
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(Void r2) {
                    LogUtil.w("reportDev success");
                    MultiSDKUtils.setMDevIds(context, str);
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str5, VolleyError volleyError) {
                    LogUtil.w("reportDev fail: " + str5);
                }

                @Override // com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str5, String str6) {
                    LogUtil.w("reportDev fail: " + str5);
                }
            }, Void.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject mapToJson(TreeMap<String, Object> treeMap) {
        if (treeMap == null || treeMap.isEmpty()) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : treeMap.keySet()) {
            try {
                jSONObject.put(str, treeMap.get(str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public static int getSecondTimestampTwo(Date date) {
        if (date == null) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(date.getTime() / 1000)).intValue();
    }
}
