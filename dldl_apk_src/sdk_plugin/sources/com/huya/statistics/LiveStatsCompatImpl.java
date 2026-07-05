package com.huya.statistics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.statistics.core.CommonFieldProvider;
import com.huya.statistics.core.StatisticsContent;
import com.huya.statistics.core.StatisticsOption;
import com.huya.statistics.core.StatisticsUidProvider;
import com.huya.statistics.log.IL;
import com.huya.statistics.log.SLog;
import com.huya.statistics.util.Counter;
import com.huya.statistics.util.StatisticsThread;
import com.huya.statistics.util.Util;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.common.constants.SqConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveStatsCompatImpl extends LiveStatsCompat {
    private static String TAG = "LiveStatsCompatImpl";
    private boolean autoRegisterActivityLife;
    private long chnStartUpTime;
    private LiveCommonFieldProvider liveCommonFieldProvider;
    private String subSessionId;
    private static long CHANNEL_HEART_BEAT_INTERVAL = 60000;
    private static final Counter channleHeartbeatInvoker = new Counter(StatisticsThread.getTimerHandler(), 0, CHANNEL_HEART_BEAT_INTERVAL, true);
    private StatsCompat mStatsCompat = new StatsCompat();
    private Object lock = new Object();
    private final Counter.Callback channelHeartBeatCallback = new Counter.Callback() { // from class: com.huya.statistics.LiveStatsCompatImpl.1
        @Override // com.huya.statistics.util.Counter.Callback
        public void onCount(int i) {
            try {
                LiveStatsCompatImpl.this.channelHeartBeat();
            } catch (Exception unused) {
            }
        }
    };

    @Override // com.huya.statistics.LiveStatsCompat
    public synchronized void init(Context context, StatisticsOption statisticsOption, StatisticsUidProvider statisticsUidProvider) {
        this.mStatsCompat.init(context, statisticsOption, statisticsUidProvider);
        channleHeartbeatInvoker.setCallback(this.channelHeartBeatCallback);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportStatisticContentAll(StatisticsContent statisticsContent) {
        if (statisticsContent == null) {
            return;
        }
        this.mStatsCompat.reportStatisticContentAll(statisticsContent);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportEvent(final String str, final String str2, final Long l, StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        final StatisticsContent statisticsContent2 = new StatisticsContent();
        if (statisticsContent != null) {
            statisticsContent2.putAll(statisticsContent.getRaw());
        }
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.2
            @Override // java.lang.Runnable
            public void run() {
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent(str, str2, l, statisticsContent2);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportEvent(String str, String str2, String str3, StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        reportEvent(str, null, str2, str3, statisticsContent);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportEvent(final String str, final String str2, final Map<String, String> map, final StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.3
            @Override // java.lang.Runnable
            public void run() {
                Map map2 = map;
                LiveStatsCompatImpl.this.reportEvent(str, null, str2, (map2 == null || map2.size() <= 0) ? null : new JSONObject(map).toString(), statisticsContent);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportEvent(final String str, final String str2, final String str3, final String str4, StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        final StatisticsContent statisticsContent2 = new StatisticsContent();
        if (statisticsContent != null) {
            statisticsContent2.putAll(statisticsContent.getRaw());
        }
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.4
            @Override // java.lang.Runnable
            public void run() {
                statisticsContent2.put(StatisticsContent.EVENT_LABEL, str3);
                statisticsContent2.put(StatisticsContent.EVENT_PROP, str4);
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent(str, str2, null, statisticsContent2);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportLiveEvent(final String str, final String str2, final String str3, final String str4, StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        final StatisticsContent statisticsContent2 = new StatisticsContent();
        if (statisticsContent != null) {
            statisticsContent2.putAll(statisticsContent.getRaw());
        }
        safeFillLiveSessionId(statisticsContent2);
        fillLiveCommon(statisticsContent2);
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.5
            @Override // java.lang.Runnable
            public void run() {
                statisticsContent2.put(StatisticsContent.EVENT_LABEL, str3);
                statisticsContent2.put(StatisticsContent.EVENT_PROP, str4);
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent(str, str2, null, statisticsContent2);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportLiveEvent(final String str, String str2, final String str3, final Map<String, String> map, final StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.6
            @Override // java.lang.Runnable
            public void run() {
                Map map2 = map;
                LiveStatsCompatImpl.this.reportLiveEvent(str, (String) null, str3, (map2 == null || map2.size() <= 0) ? null : new JSONObject(map).toString(), statisticsContent);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportLiveEvent(final String str, final String str2, Long l, StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        final StatisticsContent statisticsContent2 = new StatisticsContent();
        if (statisticsContent != null) {
            statisticsContent2.putAll(statisticsContent.getRaw());
        }
        safeFillLiveSessionId(statisticsContent2);
        fillLiveCommon(statisticsContent2);
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.7
            @Override // java.lang.Runnable
            public void run() {
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent(str, str2, null, statisticsContent2);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setLoginSuccess(Long l) {
        this.mStatsCompat.setLoginSuccess(l);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setLiveCommonFieldProvider(LiveCommonFieldProvider liveCommonFieldProvider) {
        this.liveCommonFieldProvider = liveCommonFieldProvider;
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setFromApp(String str) {
        this.mStatsCompat.setFromApp(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setCountryid(String str) {
        this.mStatsCompat.setCountryid(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setGgadid(String str) {
        this.mStatsCompat.setGgadid(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setSguid(String str) {
        this.mStatsCompat.setSguid(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setRso(String str) {
        this.mStatsCompat.setRso(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setExperiment(String str) {
        this.mStatsCompat.setExperiment(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public String getChannel() {
        return this.mStatsCompat.getChannel();
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setSessionTimeOut(long j) {
        this.mStatsCompat.setSessionTimeOut(j);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void onPause(Activity activity) {
        if (activity == null || this.autoRegisterActivityLife) {
            return;
        }
        whenPause(activity.getClass().getName());
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void onResume(Activity activity) {
        if (activity == null || this.autoRegisterActivityLife) {
            return;
        }
        whenResume(activity.getClass().getName());
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void pauseReport(long j) {
        this.mStatsCompat.pauseReport(j);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void realTimeReport(boolean z) {
        this.mStatsCompat.realTimeReport(z);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setDetailsLogEnable(boolean z) {
        this.mStatsCompat.setDetailsLogEnable(z);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setLogImp(IL il) {
        this.mStatsCompat.setLogImp(il);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void chnStartUp() {
        final StatisticsContent statisticsContentCreateStartUpData = createStartUpData();
        fillLiveCommon(statisticsContentCreateStartUpData);
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.8
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                LiveStatsCompatImpl.this.mStatsCompat.checkSDKInit();
                SLog.info(LiveStatsCompatImpl.TAG, "chnStartUp", new Object[0]);
                statisticsContentCreateStartUpData.put("isactive", Util.isForeground(LiveStatsCompatImpl.this.mStatsCompat.getContext()) ? 1 : 0);
                LiveStatsCompatImpl.this.mStatsCompat.fillPageInfo(statisticsContentCreateStartUpData);
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent("startup", null, null, statisticsContentCreateStartUpData);
                LiveStatsCompatImpl.channleHeartbeatInvoker.start(0L);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void chnEndUp() {
        chnEndUp(0L, 0L);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void chnEndUp(long j, long j2) {
        final StatisticsContent statisticsContentCreateEndUpData = createEndUpData();
        if (statisticsContentCreateEndUpData == null) {
            return;
        }
        fillLiveCommon(statisticsContentCreateEndUpData);
        if (j > 0) {
            statisticsContentCreateEndUpData.put("ayyuid", j);
        }
        if (j2 > 0) {
            statisticsContentCreateEndUpData.put("game_id", j2);
        }
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.9
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                LiveStatsCompatImpl.this.mStatsCompat.checkSDKInit();
                SLog.info(LiveStatsCompatImpl.TAG, "chnEndUp", new Object[0]);
                statisticsContentCreateEndUpData.put("isactive", Util.isForeground(LiveStatsCompatImpl.this.mStatsCompat.getContext()) ? 1 : 0);
                LiveStatsCompatImpl.this.mStatsCompat.fillPageInfo(statisticsContentCreateEndUpData);
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent(SdkReportConst.HuyaEndUp, null, null, statisticsContentCreateEndUpData);
                LiveStatsCompatImpl.channleHeartbeatInvoker.stop();
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void registerActivityLifecycleMonitor() {
        this.mStatsCompat.checkSDKInit();
        this.autoRegisterActivityLife = true;
        ((Application) this.mStatsCompat.getContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.huya.statistics.LiveStatsCompatImpl.10
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(final Activity activity) {
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LiveStatsCompatImpl.this.whenStart(activity.getClass().getName());
                    }
                });
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(final Activity activity) {
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.10.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LiveStatsCompatImpl.this.whenResume(activity.getClass().getName());
                    }
                });
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(final Activity activity) {
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.10.3
                    @Override // java.lang.Runnable
                    public void run() {
                        LiveStatsCompatImpl.this.whenPause(activity.getClass().getName());
                    }
                });
            }
        });
    }

    public void onStart(String str) {
        if (this.autoRegisterActivityLife) {
            return;
        }
        whenStart(str);
    }

    public void onResume(String str) {
        if (this.autoRegisterActivityLife) {
            return;
        }
        whenResume(str);
    }

    public void onPause(String str) {
        if (this.autoRegisterActivityLife) {
            return;
        }
        whenPause(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void whenStart(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mStatsCompat.putCommonParams("cur_view", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void whenResume(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mStatsCompat.onPageStart(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void whenPause(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LiveCommonFieldProvider liveCommonFieldProvider = this.liveCommonFieldProvider;
        if (liveCommonFieldProvider != null && liveCommonFieldProvider.getLiveCommonField() != null) {
            String str2 = this.liveCommonFieldProvider.getLiveCommonField().get("ayyuid");
            if (!TextUtils.isEmpty(str) && !"0".equals(str2) && !TextUtils.isEmpty(str2)) {
                str = str + FileUtil.FILE_EXTENSION_SEPARATOR + str2;
            }
        }
        this.mStatsCompat.onPagePause(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setPassport(String str) {
        this.mStatsCompat.setPassport(str);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public String getMid(Context context) {
        return Util.getAndroidId(context);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void setCommonFieldProvider(CommonFieldProvider commonFieldProvider) {
        this.mStatsCompat.setCommonFieldProvider(commonFieldProvider);
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void putCommonField(String str, String str2) {
        this.mStatsCompat.putCommonParams(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void channelHeartBeat() {
        StatisticsContent statisticsContentCreateHeartBeatData = createHeartBeatData();
        if (statisticsContentCreateHeartBeatData == null) {
            return;
        }
        this.mStatsCompat.fillPageInfo(statisticsContentCreateHeartBeatData);
        fillLiveCommon(statisticsContentCreateHeartBeatData);
        statisticsContentCreateHeartBeatData.put("isactive", Util.isForeground(this.mStatsCompat.getContext()) ? 1 : 0);
        this.mStatsCompat.reportAllEvent(SdkReportConst.HuyaHeartBeart, null, null, statisticsContentCreateHeartBeatData);
    }

    private StatisticsContent createStartUpData() {
        StatisticsContent statisticsContent;
        synchronized (this.lock) {
            statisticsContent = new StatisticsContent();
            this.subSessionId = Util.generateSession();
            this.chnStartUpTime = SystemClock.uptimeMillis();
            fillLiveSessionId(statisticsContent);
        }
        return statisticsContent;
    }

    private StatisticsContent createHeartBeatData() {
        synchronized (this.lock) {
            if (this.chnStartUpTime <= 0) {
                return null;
            }
            StatisticsContent statisticsContent = new StatisticsContent();
            statisticsContent.put("dur", SystemClock.uptimeMillis() - this.chnStartUpTime);
            fillLiveSessionId(statisticsContent);
            return statisticsContent;
        }
    }

    private StatisticsContent createEndUpData() {
        synchronized (this.lock) {
            if (this.chnStartUpTime <= 0) {
                return null;
            }
            StatisticsContent statisticsContent = new StatisticsContent();
            statisticsContent.put("dur", SystemClock.uptimeMillis() - this.chnStartUpTime);
            fillLiveSessionId(statisticsContent);
            this.subSessionId = null;
            this.chnStartUpTime = 0L;
            return statisticsContent;
        }
    }

    private void fillLiveCommon(StatisticsContent statisticsContent) {
        Map<String, String> liveCommonField;
        LiveCommonFieldProvider liveCommonFieldProvider = this.liveCommonFieldProvider;
        if (liveCommonFieldProvider == null || (liveCommonField = liveCommonFieldProvider.getLiveCommonField()) == null || liveCommonField.size() <= 0) {
            return;
        }
        statisticsContent.putAll(liveCommonField);
    }

    private void fillLiveSessionId(StatisticsContent statisticsContent) {
        String sessionId = this.mStatsCompat.getSessionId();
        if (this.subSessionId == null || sessionId == null) {
            return;
        }
        statisticsContent.put(SqConstants.SESSION_ID, sessionId + "/" + this.subSessionId);
    }

    private void safeFillLiveSessionId(StatisticsContent statisticsContent) {
        synchronized (this.lock) {
            fillLiveSessionId(statisticsContent);
        }
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportDirectly(final List<StatisticsContent> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StatisticsThread.executorWorkTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.11
            @Override // java.lang.Runnable
            public void run() {
                LiveStatsCompatImpl.this.mStatsCompat.reportDirectly(list, false);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportDirectly(final List<StatisticsContent> list, final boolean z) {
        if (list == null || list.size() == 0) {
            return;
        }
        StatisticsThread.executorWorkTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.12
            @Override // java.lang.Runnable
            public void run() {
                LiveStatsCompatImpl.this.mStatsCompat.reportDirectly(list, z);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportDirectly(final String str, final String str2, final Long l, final StatisticsContent statisticsContent) {
        if (str == null) {
            return;
        }
        StatisticsThread.executorWorkTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.13
            @Override // java.lang.Runnable
            public void run() {
                StatisticsContent statisticsContent2 = new StatisticsContent();
                StatisticsContent statisticsContent3 = statisticsContent;
                if (statisticsContent3 != null) {
                    statisticsContent2.putAll(statisticsContent3.getRaw());
                }
                statisticsContent2.put(StatisticsContent.EVENT_id, str.trim());
                if (!TextUtils.isEmpty(str2)) {
                    statisticsContent2.put(StatisticsContent.EVENT_DESC, str2);
                }
                Long l2 = l;
                if (l2 != null) {
                    statisticsContent.put("dur", l2.longValue());
                }
                statisticsContent2.put(StatisticsContent.ACT, "hyevent");
                ArrayList arrayList = new ArrayList();
                arrayList.add(statisticsContent2);
                LiveStatsCompatImpl.this.mStatsCompat.reportDirectly(arrayList, false);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void reportDirectly(final String str, final String str2, final String str3, final Map<String, String> map, final StatisticsContent statisticsContent) {
        if (str == null) {
            return;
        }
        StatisticsThread.executorWorkTask(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.14
            @Override // java.lang.Runnable
            public void run() {
                StatisticsContent statisticsContent2 = new StatisticsContent();
                StatisticsContent statisticsContent3 = statisticsContent;
                if (statisticsContent3 != null) {
                    statisticsContent2.putAll(statisticsContent3.getRaw());
                }
                statisticsContent2.put(StatisticsContent.EVENT_id, str.trim());
                if (!TextUtils.isEmpty(str2)) {
                    statisticsContent2.put(StatisticsContent.EVENT_DESC, str2);
                }
                statisticsContent2.put(StatisticsContent.EVENT_LABEL, str3);
                statisticsContent2.put(StatisticsContent.ACT, "hyevent");
                Map map2 = map;
                if (map2 != null && map2.size() > 0) {
                    statisticsContent2.put(StatisticsContent.EVENT_PROP, new JSONObject(map).toString());
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(statisticsContent2);
                LiveStatsCompatImpl.this.mStatsCompat.reportDirectly(arrayList, false);
            }
        });
    }

    @Override // com.huya.statistics.LiveStatsCompat
    public void nimoReport(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, StatisticsContent statisticsContent) {
        this.mStatsCompat.checkSDKInit(str);
        final StatisticsContent statisticsContent2 = new StatisticsContent();
        if (statisticsContent != null) {
            statisticsContent2.putAll(statisticsContent.getRaw());
        }
        fillLiveCommon(statisticsContent2);
        StatisticsThread.asyncRun(new Runnable() { // from class: com.huya.statistics.LiveStatsCompatImpl.15
            @Override // java.lang.Runnable
            public void run() {
                statisticsContent2.put(StatisticsContent.EVENT_LABEL, str3);
                statisticsContent2.put(StatisticsContent.EVENT_PROP, str8);
                statisticsContent2.put("cref", TextUtils.isEmpty(str4) ? "not_fill" : str4);
                statisticsContent2.put("ref", TextUtils.isEmpty(str5) ? "not_fill" : str5);
                statisticsContent2.put("crefId", TextUtils.isEmpty(str6) ? "not_fill" : str6);
                statisticsContent2.put("refId", TextUtils.isEmpty(str7) ? "not_fill" : str7);
                LiveStatsCompatImpl.this.mStatsCompat.reportAllEvent(str, str2, null, statisticsContent2);
            }
        });
    }
}
