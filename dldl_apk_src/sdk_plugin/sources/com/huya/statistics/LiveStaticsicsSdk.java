package com.huya.statistics;

import android.app.Activity;
import android.content.Context;
import com.huya.statistics.core.CommonFieldProvider;
import com.huya.statistics.core.StatisticsContent;
import com.huya.statistics.core.StatisticsOption;
import com.huya.statistics.core.StatisticsUidProvider;
import com.huya.statistics.log.IL;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveStaticsicsSdk {
    public static synchronized void init(Context context, StatisticsOption statisticsOption, StatisticsUidProvider statisticsUidProvider) {
        LiveStatsCompat.getInstance().init(context, statisticsOption, statisticsUidProvider);
    }

    public static void reportStatisticContentAll(StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportStatisticContentAll(statisticsContent);
    }

    public static void reportEvent(String str, String str2, Long l, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportEvent(str, str2, l, statisticsContent);
    }

    public static void reportEvent(String str, String str2, String str3, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportEvent(str, str2, str3, statisticsContent);
    }

    public static void reportEvent(String str, String str2, Map<String, String> map, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportEvent(str, str2, map, statisticsContent);
    }

    public static void reportEvent(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportEvent(str, str2, str3, str4, statisticsContent);
    }

    public static void reportLiveEvent(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportLiveEvent(str, str2, str3, str4, statisticsContent);
    }

    public static void reportLiveEvent(String str, String str2, String str3, Map<String, String> map, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportLiveEvent(str, str2, str3, map, statisticsContent);
    }

    public static void reportLiveEvent(String str, String str2, Long l, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportLiveEvent(str, str2, l, statisticsContent);
    }

    public static void setLoginSuccess(Long l) {
        LiveStatsCompat.getInstance().setLoginSuccess(l);
    }

    public static void setLiveCommonFieldProvider(LiveCommonFieldProvider liveCommonFieldProvider) {
        LiveStatsCompat.getInstance().setLiveCommonFieldProvider(liveCommonFieldProvider);
    }

    public static void setFromApp(String str) {
        LiveStatsCompat.getInstance().setFromApp(str);
    }

    public static void setCountryid(String str) {
        LiveStatsCompat.getInstance().setCountryid(str);
    }

    public static void setGgadid(String str) {
        LiveStatsCompat.getInstance().setGgadid(str);
    }

    public static void setSguid(String str) {
        LiveStatsCompat.getInstance().setSguid(str);
    }

    public static void setRso(String str) {
        LiveStatsCompat.getInstance().setRso(str);
    }

    public static void setExperiment(String str) {
        LiveStatsCompat.getInstance().setExperiment(str);
    }

    public static String getChannel() {
        return LiveStatsCompat.getInstance().getChannel();
    }

    public static void setSessionTimeOut(long j) {
        LiveStatsCompat.getInstance().setSessionTimeOut(j);
    }

    public static void onPause(Activity activity) {
        LiveStatsCompat.getInstance().onPause(activity);
    }

    public static void onResume(Activity activity) {
        LiveStatsCompat.getInstance().onResume(activity);
    }

    public static void pauseReport(long j) {
        LiveStatsCompat.getInstance().pauseReport(j);
    }

    public static void realTimeReport(boolean z) {
        LiveStatsCompat.getInstance().realTimeReport(z);
    }

    public static void setDetailsLogEnable(boolean z) {
        LiveStatsCompat.getInstance().setDetailsLogEnable(z);
    }

    public static void setLogImp(IL il) {
        LiveStatsCompat.getInstance().setLogImp(il);
    }

    public static void chnStartUp() {
        LiveStatsCompat.getInstance().chnStartUp();
    }

    public static void chnEndUp() {
        LiveStatsCompat.getInstance().chnEndUp();
    }

    public static void chnEndUp(long j, long j2) {
        LiveStatsCompat.getInstance().chnEndUp(j, j2);
    }

    public static void registerActivityLifecycleMonitor() {
        LiveStatsCompat.getInstance().registerActivityLifecycleMonitor();
    }

    public static void setPassport(String str) {
        LiveStatsCompat.getInstance().setPassport(str);
    }

    public static String getMid(Context context) {
        return LiveStatsCompat.getInstance().getMid(context);
    }

    public static void setCommonFieldProvider(CommonFieldProvider commonFieldProvider) {
        LiveStatsCompat.getInstance().setCommonFieldProvider(commonFieldProvider);
    }

    public static void putCommonField(String str, String str2) {
        LiveStatsCompat.getInstance().putCommonField(str, str2);
    }

    public static void reportDirectly(List<StatisticsContent> list) {
        LiveStatsCompat.getInstance().reportDirectly(list);
    }

    public static void reportDirectly(List<StatisticsContent> list, boolean z) {
        LiveStatsCompat.getInstance().reportDirectly(list, z);
    }

    public static void reportDirectly(String str, String str2, Long l, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportDirectly(str, str2, l, statisticsContent);
    }

    public static void reportDirectly(String str, String str2, String str3, Map<String, String> map, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().reportDirectly(str, str2, str3, map, statisticsContent);
    }

    public static void nimoReport(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, StatisticsContent statisticsContent) {
        LiveStatsCompat.getInstance().nimoReport(str, str2, str3, str4, str5, str6, str7, str8, statisticsContent);
    }
}
