package com.duowan.live.one.module.report;

import android.content.Context;
import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.statistics.LiveCommonFieldProvider;
import com.huya.statistics.LiveStaticsicsSdk;
import com.huya.statistics.core.StatisticsContent;
import com.huya.statistics.core.StatisticsOption;
import com.huya.statistics.core.StatisticsUidProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaStatisApi {
    private static final String TAG = "HuyaStatisApi";
    private String appId;
    private String appKey;
    private String from;
    private String mAppVersion;
    private long mChannelId;
    private Context mContext;
    private long mGameId;
    private long mLiveId;
    private String mReferrer;
    private String pro;
    private String rso;
    private String uve;
    private String ver;
    private long yyUid;
    private String sessionId = UUID.randomUUID().toString();
    private volatile boolean mIsLiving = false;

    public void init(Context context, String str, String str2, String str3, String str4, String str5) {
        L.info(TAG, "init (appKey, appId, from, ver, pro) = (%s, %s, %s, %s, %s)", str, str2, str3, str4, str5);
        this.appKey = str;
        this.appId = str2;
        this.from = str3;
        this.ver = str4;
        this.pro = str5;
        this.mContext = context.getApplicationContext();
        LiveStaticsicsSdk.init(context, new StatisticsOption(str5, str3, str4, "live"), new StatisticsUidProvider() { // from class: com.duowan.live.one.module.report.HuyaStatisApi.1
            @Override // com.huya.statistics.core.StatisticsUidProvider
            public long getUid() {
                return HuyaStatisApi.this.yyUid;
            }
        });
        LiveStaticsicsSdk.registerActivityLifecycleMonitor();
        LiveStaticsicsSdk.setSessionTimeOut(604800000L);
        LiveStaticsicsSdk.setLiveCommonFieldProvider(new LiveCommonFieldProvider() { // from class: com.duowan.live.one.module.report.HuyaStatisApi.2
            @Override // com.huya.statistics.LiveCommonFieldProvider
            public Map<String, String> getLiveCommonField() {
                HashMap map = new HashMap();
                map.put("ayyuid", String.valueOf(HuyaStatisApi.this.yyUid));
                map.put("game_id", String.valueOf(HuyaStatisApi.this.mGameId));
                map.put("liveid", String.valueOf(HuyaStatisApi.this.mLiveId));
                if (HuyaStatisApi.this.mChannelId != 0) {
                    map.put("cid", HuyaStatisApi.this.mChannelId + "/" + HuyaStatisApi.this.mChannelId);
                }
                map.put("sdk_ver", HuyaStatisApi.this.mAppVersion);
                map.put("props", "{\"gameid_\":" + HuyaStatisApi.this.mGameId + "}");
                return map;
            }
        });
        LiveStaticsicsSdk.setDetailsLogEnable(false);
    }

    public void install() {
        reportEvent("install", "产品安装", null, null, null);
    }

    public void installApps() {
        Map<String, String> apps = Util.getApps(this.mContext, 2);
        if (apps == null || apps.size() == 0) {
            return;
        }
        int i = 0;
        StringBuilder sb = new StringBuilder(0);
        for (String str : apps.keySet()) {
            int i2 = i + 1;
            if (i > 0) {
                sb.append("|");
            }
            sb.append(apps.get(str));
            i = i2;
        }
        StatisticsContent statisticsContent = new StatisticsContent();
        statisticsContent.put("content", sb.toString());
        reportEvent("installed_apps", "已安装应用", null, null, statisticsContent);
    }

    public void changeSessionId() {
        this.sessionId = UUID.randomUUID().toString();
    }

    public void error(String str) {
        StatisticsContent statisticsContent = new StatisticsContent();
        statisticsContent.put("content", str);
        reportEvent("error", "错误", null, null, statisticsContent);
    }

    public void login() {
        reportEvent("com/huya/component/login", null, null, null, null);
    }

    public void startLive(long j, long j2, long j3, String str) {
        LiveStaticsicsSdk.chnStartUp();
        this.mIsLiving = true;
        this.mGameId = j;
        this.mLiveId = j2;
        this.mChannelId = j3;
        this.mAppVersion = str;
    }

    public void stopLive() {
        LiveStaticsicsSdk.chnEndUp();
        this.mIsLiving = false;
        this.mGameId = 0L;
        this.mLiveId = 0L;
    }

    public void setGameId(long j) {
        this.mGameId = j;
    }

    public void reportEvent(String str, String str2) {
        reportEvent(str, str2, null, null, null);
    }

    public void reportEvent(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
        if (Util.isReportEnabled()) {
            if (!TextUtils.equals(SdkReportConst.HuyaHeartBeart, str)) {
                L.info(TAG, "eventId = %s, desc = %s, type = %s, prop = %s", str, str2, str3, str4);
            }
            reportToStatistic(str, str2, str3, str4, statisticsContent);
        }
    }

    private void reportToStatistic(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
        if (!this.mIsLiving) {
            if (str3 == null && str4 == null) {
                LiveStaticsicsSdk.reportEvent(str, str2, (Long) 0L, statisticsContent);
                return;
            } else {
                LiveStaticsicsSdk.reportEvent(str, str2, str3, str4, statisticsContent);
                return;
            }
        }
        if (str4 != null) {
            LiveStaticsicsSdk.reportEvent(str, str2, str3, str4, statisticsContent);
        } else if (str3 == null) {
            LiveStaticsicsSdk.reportLiveEvent(str, str2, 0L, statisticsContent);
        } else {
            LiveStaticsicsSdk.reportLiveEvent(str, str2, str3, str4, statisticsContent);
        }
    }

    public void setRso(String str) {
        this.rso = str;
    }

    public void setUve(String str) {
        this.uve = str;
    }

    public void setReferrer(String str) {
        L.debug(TAG, "setReferrer: %s", str);
        this.mReferrer = str;
    }

    public void setYyUid(Long l) {
        L.info(TAG, "setYyUid: %d", l);
        this.yyUid = l.longValue();
    }

    public void setGuid(String str) {
        L.info(TAG, "HuyaStatisApi_setGuid: %s", str);
        LiveStaticsicsSdk.setSguid(str);
    }

    public String getMid() {
        return LiveStaticsicsSdk.getMid(ArkValue.gContext);
    }
}
