package com.sqwan.msdk.api;

import android.content.Context;
import com.huya.statistics.core.StatisticsContent;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.utils.ZipString;
import java.io.File;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class InitBeanUtil {
    private static final String DEBUG_FILE = "sqwan-test";

    public static InitBean inflactBean(Context context, Properties properties) {
        if (properties != null) {
            InitBean initBean = new InitBean();
            if (properties.getProperty("usesdk") == null) {
                initBean.setUsesdk(1);
            } else {
                initBean.setUsesdk(Integer.parseInt(properties.getProperty("usesdk")));
            }
            initBean.setAppid(properties.getProperty("appid") != null ? ZipString.zipString2Json(properties.getProperty("appid")) : "");
            initBean.setAppkey(properties.getProperty(StatisticsContent.APPKEY) != null ? ZipString.zipString2Json(properties.getProperty(StatisticsContent.APPKEY)) : "");
            initBean.setRefer(properties.getProperty("refer") != null ? properties.getProperty("refer") : "");
            initBean.setMerchantId(properties.getProperty("merchantId"));
            initBean.setServerSeqNum(properties.getProperty("serverSeqNum"));
            initBean.setGameId(properties.getProperty("gameId"));
            initBean.setServerId(properties.getProperty(BaseSQwanCore.INFO_SERVERID));
            initBean.setRate(properties.getProperty("rate"));
            initBean.setGameName(properties.getProperty("gameName"));
            initBean.setChannel(properties.getProperty("channel"));
            initBean.setLandScape(Integer.parseInt(properties.getProperty("isLandScape") == null ? "0" : properties.getProperty("isLandScape")));
            initBean.setIsFixed(Integer.parseInt(properties.getProperty("isFixed") == null ? "1" : properties.getProperty("isFixed")));
            initBean.setIsTencentPayTest(Integer.parseInt(properties.getProperty("isTencentPayTest") == null ? "0" : properties.getProperty("isTencentPayTest")));
            initBean.setDebug(isDebug(context, properties));
            initBean.setTestTag(Integer.parseInt(properties.getProperty("testTag") == null ? "0" : properties.getProperty("testTag")));
            initBean.setUcDebug(Integer.parseInt(properties.getProperty("ucDebug") == null ? "0" : properties.getProperty("ucDebug")));
            initBean.setPayid(properties.getProperty("payid"));
            initBean.setPaykey(properties.getProperty("paykey"));
            initBean.setWxAppid(properties.getProperty("wxAppid"));
            initBean.setWxAppkey(properties.getProperty("wxAppkey"));
            initBean.setIsSplashShow(Integer.parseInt(properties.getProperty("isSplashShow") == null ? "0" : properties.getProperty("isSplashShow")));
            initBean.setUsePlatformExit(Integer.parseInt(properties.getProperty("usePlatformExit") == null ? "0" : properties.getProperty("usePlatformExit")));
            initBean.setUseSQExit(Integer.parseInt(properties.getProperty("useSQExit") == null ? "1" : properties.getProperty("useSQExit")));
            initBean.setIsPushDelay(Integer.parseInt(properties.getProperty("isPushDelay") != null ? properties.getProperty("isPushDelay") : "1"));
            initBean.setIsSDK202(Integer.parseInt(properties.getProperty("isSDK202") == null ? "0" : properties.getProperty("isSDK202")));
            initBean.setIsSDK210(Integer.parseInt(properties.getProperty("isSDK210") == null ? "0" : properties.getProperty("isSDK210")));
            initBean.setIsLogDetect(Integer.parseInt(properties.getProperty("isLogDetect") != null ? properties.getProperty("isLogDetect") : "0"));
            return initBean;
        }
        LogUtil.w("prop配置文件为null");
        return null;
    }

    private static boolean isDebugModeByFile(Context context) {
        String str = EnvironmentUtils.getCommonDirPath(context) + File.separator + DEBUG_FILE;
        LogUtil.i(str);
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            LogUtil.i("sqwan-test directory is exists");
            return true;
        }
        LogUtil.i("sqwan-test directory is not exists");
        return false;
    }

    private static int isDebug(Context context, Properties properties) {
        return isDebugModeByFile(context) ? 1 : 0;
    }
}
