package com.huya.berry.module;

import android.os.Build;
import android.text.TextUtils;
import com.duowan.HUYA.GetConfigRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.HuyaStatisAgent;
import com.duowan.networkmars.hysignal.HySignalDynamicParams;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.gameid.GameIdOptions;
import com.huya.berry.gamesdk.resolutions.LivingParams;
import com.huya.berry.gamesdk.resolutions.ResolutionOptions;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.module.live.LiveInterface;
import com.huya.component.login.LoginProperties;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PresenterConfigHelper {
    private static final String TAG = "PresenterConfigHelper";

    public static void requestPresenterConfig() {
        HashMap map = new HashMap();
        map.put("client_ver", WupHelper.getVersion());
        map.put("client_channel", SdkProperties.appId.get());
        map.put("client_ua", "adr_game_sdk");
        map.put("use_id", String.valueOf(LoginProperties.uid.get()));
        map.put("device", Build.MODEL.toLowerCase());
        map.put("gemeid", String.valueOf(SdkProperties.gameId.get()));
        map.put("isLandscape", String.valueOf(CommonUtil.isScreenLandScape()));
        map.put("android_version", String.valueOf(Build.VERSION.SDK_INT));
        map.put("client_mid ", HuyaStatisAgent.getInstance().getHuyaStatisApi().getMid());
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        L.info(TAG, "onGetPresenterConfig sdkLiveService:" + iSdkLiveService);
        if (iSdkLiveService != null) {
            iSdkLiveService.getConfig(map).compose(SchedulerUtils.ioio()).subscribe(new WupObserver<GetConfigRsp>() { // from class: com.huya.berry.module.PresenterConfigHelper.1
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetConfigRsp getConfigRsp) {
                    PresenterConfigHelper.onGetPresenterConfig(getConfigRsp.mpConfig);
                    L.info(PresenterConfigHelper.TAG, "onGetPresenterConfig sdkLiveService...");
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    PresenterConfigHelper.onGetPresenterConfig(null);
                }
            });
        }
    }

    public static void onGetPresenterConfig(Map<String, String> map) {
        L.info(TAG, "onGetPresenterConfig11:");
        if (map == null) {
            ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_get_presenter_config_fail));
            return;
        }
        L.info(TAG, "onGetPresenterConfig22:");
        String str = map.get("enableUseChannel");
        PreferenceUtil.setIsUseChannel(TextUtils.isEmpty(str) || Boolean.parseBoolean(str));
        String str2 = map.get("enableHardEncode");
        SdkProperties.enableHardEncode.set(Boolean.valueOf(TextUtils.isEmpty(str2) || Boolean.parseBoolean(str2)));
        SdkProperties.sendDataSizeLimit.set(Integer.valueOf(parseInt(map.get("sendDataSizeLimit"), 102400)));
        SdkProperties.sendDataCountLimit.set(Integer.valueOf(parseInt(map.get("sendDataCountLimit"), 60)));
        SdkProperties.flowControlPolicy.set(Integer.valueOf(parseInt(map.get("flowControlPolicy"), 2)));
        String str3 = map.get("isNeedLaunch");
        PreferenceUtil.setIsNeedLaunch(!TextUtils.isEmpty(str3) && Boolean.parseBoolean(str3));
        String str4 = map.get("enableFullSreenSupport");
        SdkProperties.enableFullSreenSupport.set(Boolean.valueOf(!TextUtils.isEmpty(str4) && Boolean.parseBoolean(str4)));
        initUseNormalSize(map.get("specialOverlayType"));
        String str5 = map.get("isForbidUdbLog");
        PreferenceUtil.setIsForbidUdbLog(TextUtils.isEmpty(str5) || Boolean.parseBoolean(str5));
        SdkProperties.maxPlayBitrate.set(Integer.valueOf(parseInt(map.get("max_play_bitrate"), 40000)));
        initResolutionOptions(map);
        initGameIdOptions(map);
        HySignalDynamicParams.getInstance().init(map);
    }

    private static void initUseNormalSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] strArrSplit = str.split("\\|");
        if (strArrSplit.length == 0) {
            return;
        }
        String lowerCase = Build.MODEL.toLowerCase();
        for (String str2 : strArrSplit) {
            if (!TextUtils.isEmpty(lowerCase) && lowerCase.contains(str2)) {
                L.info(TAG, "specialOverlayType:" + lowerCase + " contains " + str2);
                SdkProperties.specialOverlayType.set(true);
            }
        }
    }

    private static void initGameIdOptions(Map<String, String> map) {
        if (map.containsKey("gameIdKey")) {
            String str = map.get("gameIdKey");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String[] strArrSplit = str.split("\\|");
            String lowerCase = Build.MANUFACTURER.toLowerCase();
            for (String str2 : strArrSplit) {
                if (SdkProperties.appKey.get().equals(str2) || str2.contains(SdkProperties.appKey.get())) {
                    L.info(TAG, "gameIdArr:" + lowerCase + " contains " + str2);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("GameIdList");
                    String str3 = map.get(sb.toString());
                    if (TextUtils.isEmpty(str3)) {
                        return;
                    } else {
                        GameIdOptions.getInstance().gameIdArr = str3.split("\\|");
                    }
                }
            }
        }
    }

    private static void initResolutionOptions(Map<String, String> map) {
        LivingParams resoltionValue;
        if (map.containsKey("resKey")) {
            String str = map.get("resKey");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String[] strArrSplit = str.split("\\|");
            ArrayList arrayList = new ArrayList();
            for (String str2 : strArrSplit) {
                if (!TextUtils.isEmpty(str2) && (resoltionValue = ResolutionOptions.getResoltionValue(map.get(str2))) != null) {
                    if (!SdkProperties.enableHardEncode.get().booleanValue() && resoltionValue.getResolution() >= 8) {
                        L.error(TAG, "软编开播不支持蓝光开播..." + resoltionValue);
                    } else {
                        arrayList.add(resoltionValue);
                    }
                }
            }
            L.info(TAG, "onGetPresenterConfig33:");
            if (arrayList.isEmpty()) {
                return;
            }
            ResolutionOptions.getInstance().setResolution(arrayList);
            ArkUtils.send(new LiveInterface.UpdateResolutionList());
            L.info(TAG, "onGetPresenterConfig44:");
        }
    }

    public static int parseInt(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }
}
