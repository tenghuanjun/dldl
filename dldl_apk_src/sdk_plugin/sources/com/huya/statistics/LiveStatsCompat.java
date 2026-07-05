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
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class LiveStatsCompat {
    private static final String DEFAULT_KEY = "default";
    private static Map<String, LiveStatsCompat> sInstanceMap = new ConcurrentHashMap();

    public void chnEndUp() {
    }

    public void chnEndUp(long j, long j2) {
    }

    public void chnStartUp() {
    }

    public String getChannel() {
        return null;
    }

    public String getMid(Context context) {
        return null;
    }

    public void nimoReport(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, StatisticsContent statisticsContent) {
    }

    public void onPause(Activity activity) {
    }

    public void onResume(Activity activity) {
    }

    public void pauseReport(long j) {
    }

    public void putCommonField(String str, String str2) {
    }

    public void realTimeReport(boolean z) {
    }

    public void registerActivityLifecycleMonitor() {
    }

    public void reportDirectly(String str, String str2, Long l, StatisticsContent statisticsContent) {
    }

    public void reportDirectly(String str, String str2, String str3, Map<String, String> map, StatisticsContent statisticsContent) {
    }

    public void reportDirectly(List<StatisticsContent> list) {
    }

    public void reportDirectly(List<StatisticsContent> list, boolean z) {
    }

    public void reportEvent(String str, String str2, Long l, StatisticsContent statisticsContent) {
    }

    public void reportEvent(String str, String str2, String str3, StatisticsContent statisticsContent) {
    }

    public void reportEvent(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
    }

    public void reportEvent(String str, String str2, Map<String, String> map, StatisticsContent statisticsContent) {
    }

    public void reportLiveEvent(String str, String str2, Long l, StatisticsContent statisticsContent) {
    }

    public void reportLiveEvent(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
    }

    public void reportLiveEvent(String str, String str2, String str3, Map<String, String> map, StatisticsContent statisticsContent) {
    }

    public void reportStatisticContentAll(StatisticsContent statisticsContent) {
    }

    public void setCommonFieldProvider(CommonFieldProvider commonFieldProvider) {
    }

    public void setCountryid(String str) {
    }

    public void setDetailsLogEnable(boolean z) {
    }

    public void setExperiment(String str) {
    }

    public void setFromApp(String str) {
    }

    public void setGgadid(String str) {
    }

    public void setLiveCommonFieldProvider(LiveCommonFieldProvider liveCommonFieldProvider) {
    }

    public void setLogImp(IL il) {
    }

    public void setLoginSuccess(Long l) {
    }

    public void setPassport(String str) {
    }

    public void setRso(String str) {
    }

    public void setSessionTimeOut(long j) {
    }

    public void setSguid(String str) {
    }

    public static LiveStatsCompat getInstance() {
        return _getInstance(DEFAULT_KEY);
    }

    public static LiveStatsCompat getInstance(String str) {
        return _getInstance(str);
    }

    private static LiveStatsCompat _getInstance(String str) {
        LiveStatsCompat liveStatsCompat;
        synchronized (sInstanceMap) {
            if (!sInstanceMap.containsKey(str)) {
                sInstanceMap.put(str, new LiveStatsCompatImpl());
            }
            liveStatsCompat = sInstanceMap.get(str);
        }
        return liveStatsCompat;
    }

    public synchronized void init(Context context, StatisticsOption statisticsOption, StatisticsUidProvider statisticsUidProvider) {
    }
}
