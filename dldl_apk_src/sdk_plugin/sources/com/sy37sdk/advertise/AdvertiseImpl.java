package com.sy37sdk.advertise;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdLoadType;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.advertise.IAdvertiseMod;
import com.sqwan.common.mod.config.IConfigMod;
import com.sqwan.common.util.AssetsUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.config.ConfigManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AdvertiseImpl implements IAdvertiseMod {
    private static final String KEY_ADVERTISE_APP_ID = "chuanshanjia_app_id";
    private static final String LOG_TAG = "chuanshanjia-AD";
    private boolean isInitSuccess = false;
    private String appId = "";

    @Override // com.sqwan.common.mod.advertise.IAdvertiseMod
    public void init(Context context) {
        this.appId = AssetsUtils.readProperties(context, "multiconfig").getProperty(KEY_ADVERTISE_APP_ID);
        LogUtil.i(LOG_TAG, "init 穿山甲 appID " + this.appId);
        if (!TextUtils.isEmpty(this.appId)) {
            TTAdSdk.init(context, new TTAdConfig.Builder().appId(this.appId).customController(new TTCustomController() { // from class: com.sy37sdk.advertise.AdvertiseImpl.1
                public boolean alist() {
                    return false;
                }

                public boolean isCanUseAndroidId() {
                    return false;
                }

                public boolean isCanUseLocation() {
                    return false;
                }

                public boolean isCanUsePermissionRecordAudio() {
                    return false;
                }

                public boolean isCanUsePhoneState() {
                    return false;
                }

                public boolean isCanUseWriteExternal() {
                    return false;
                }
            }).directDownloadNetworkType(new int[]{4}).supportMultiProcess(false).build());
            TTAdSdk.start(new TTAdSdk.Callback() { // from class: com.sy37sdk.advertise.AdvertiseImpl.2
                public void success() {
                    AdvertiseImpl.this.isInitSuccess = true;
                    LogUtil.i(AdvertiseImpl.LOG_TAG, " 穿山甲 start success");
                }

                public void fail(int i, String str) {
                    LogUtil.i(AdvertiseImpl.LOG_TAG, " 穿山甲 start fail, 原因：" + str + "code " + i);
                }
            });
        } else {
            LogUtil.i(LOG_TAG, "没有配置穿山甲APP_ID，不初始化");
        }
    }

    @Override // com.sqwan.common.mod.advertise.IAdvertiseMod
    public void showAdvertiseReward(final Context context, String str, final SQResultListener sQResultListener) {
        String str2;
        String strOptString;
        String extra = "";
        if (!this.isInitSuccess) {
            sQResultListener.onFailture(201, "穿山甲 初始化失败");
            LogUtil.i(LOG_TAG, "初始化失败，不打开广告页面");
            return;
        }
        if (context instanceof Activity) {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(context);
            try {
                JSONObject jSONObject = new JSONObject(str);
                strOptString = jSONObject.optString("advertise_id");
                try {
                    extra = getExtra(context, jSONObject, this.appId, strOptString);
                    LogUtil.i(LOG_TAG, "穿山甲 extra -> " + extra);
                } catch (JSONException e) {
                    e = e;
                    String str3 = extra;
                    extra = strOptString;
                    str2 = str3;
                    e.printStackTrace();
                    String str4 = extra;
                    extra = str2;
                    strOptString = str4;
                }
            } catch (JSONException e2) {
                e = e2;
                str2 = "";
            }
            if (TextUtils.isEmpty(strOptString)) {
                sQResultListener.onFailture(201, "advertise_id is empty");
                return;
            } else {
                tTAdNativeCreateAdNative.loadRewardVideoAd(new AdSlot.Builder().setCodeId(strOptString).setMediaExtra(extra).setUserID(ModHelper.getConfig().getCommonConfig().getUserId()).setAdLoadType(TTAdLoadType.LOAD).build(), new TTAdNative.RewardVideoAdListener() { // from class: com.sy37sdk.advertise.AdvertiseImpl.3
                    public void onError(int i, String str5) {
                        LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onError " + str5 + " code : " + i);
                        StringBuilder sb = new StringBuilder();
                        sb.append("系统异常，请稍后再试（");
                        sb.append(i);
                        sb.append("）");
                        ToastUtil.showToast(sb.toString());
                    }

                    public void onRewardVideoAdLoad(TTRewardVideoAd tTRewardVideoAd) {
                        tTRewardVideoAd.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() { // from class: com.sy37sdk.advertise.AdvertiseImpl.3.1
                            public void onRewardVerify(boolean z, int i, String str5, int i2, String str6) {
                            }

                            public void onAdShow() {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onAdShow");
                            }

                            public void onAdVideoBarClick() {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onAdVideoBarClick");
                            }

                            public void onAdClose() {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onAdClose");
                            }

                            public void onVideoComplete() {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onVideoComplete");
                            }

                            public void onVideoError() {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onVideoError");
                                ToastUtil.showToast("广告展示出错，请稍后重试");
                            }

                            public void onRewardArrived(boolean z, int i, Bundle bundle) {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 奖励发放 onRewardVerify result : " + z);
                                if (sQResultListener != null) {
                                    if (z) {
                                        sQResultListener.onSuccess(bundle);
                                    } else {
                                        sQResultListener.onFailture(201, "RewardVerify is fail");
                                    }
                                }
                            }

                            public void onSkippedVideo() {
                                LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onSkippedVideo");
                            }
                        });
                        tTRewardVideoAd.showRewardVideoAd((Activity) context);
                    }

                    public void onRewardVideoCached() {
                        LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onRewardVideoCached ");
                    }

                    public void onRewardVideoCached(TTRewardVideoAd tTRewardVideoAd) {
                        LogUtil.i(AdvertiseImpl.LOG_TAG, "穿山甲 onRewardVideoCached ad");
                    }
                });
                return;
            }
        }
        LogUtil.e(LOG_TAG, "传入 context 不是Activity，不展示广告");
        sQResultListener.onFailture(201, "传入 context 不是Activity，不展示广告");
    }

    private String getExtra(Context context, JSONObject jSONObject, String str, String str2) {
        IConfigMod config = ModHelper.getConfig();
        String partner = ConfigManager.getInstance(context).getSQAppConfig().getPartner();
        String gameid = ConfigManager.getInstance(context).getSQAppConfig().getGameid();
        String userId = config.getCommonConfig().getUserId();
        String strOptString = jSONObject.optString(SqConstants.DSID);
        String strOptString2 = jSONObject.optString(SqConstants.DSNAME);
        String strOptString3 = jSONObject.optString(SqConstants.DRID);
        String strOptString4 = jSONObject.optString(SqConstants.DRNAME);
        String strValueOf = String.valueOf(System.currentTimeMillis() / 1000);
        HashMap map = new HashMap();
        map.put("pid", partner);
        map.put("gid", gameid);
        map.put("uid", userId);
        map.put(SqConstants.DSID, strOptString);
        map.put(SqConstants.DSNAME, strOptString2);
        map.put(SqConstants.DRID, strOptString3);
        map.put(SqConstants.DRNAME, strOptString4);
        map.put("time", strValueOf);
        map.put("appid", str);
        map.put("codeid", str2);
        String strSign = sign(buildString(ConfigManager.getInstance(context).getAppKey(), map));
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("pid", partner);
            jSONObject2.put("gid", gameid);
            jSONObject2.put("uid", userId);
            jSONObject2.put(SqConstants.DSID, strOptString);
            jSONObject2.put(SqConstants.DSNAME, strOptString2);
            jSONObject2.put(SqConstants.DRID, strOptString3);
            jSONObject2.put(SqConstants.DRNAME, strOptString4);
            jSONObject2.put("time", strValueOf);
            jSONObject2.put("appid", str);
            jSONObject2.put("codeid", str2);
            jSONObject2.put(SqConstants.SIGN, strSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject2.toString();
    }

    private static StringBuilder buildString(String str, Map<String, String> map) {
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String str2 : arrayList) {
            sb.append(str2);
            sb.append('=');
            sb.append(map.get(str2));
        }
        sb.append(str);
        return sb;
    }

    private static String sign(StringBuilder sb) {
        return MD5Util.Md5(sb.toString()).toLowerCase(Locale.US);
    }

    public static String sign(Map<String, String> map) {
        return sign(ConfigManager.getInstance(SQContextWrapper.getApplicationContext()).getAppKey(), map);
    }

    public static String sign(String str, Map<String, String> map) {
        return (map == null || str == null) ? "" : sign(buildString(str, map));
    }
}
