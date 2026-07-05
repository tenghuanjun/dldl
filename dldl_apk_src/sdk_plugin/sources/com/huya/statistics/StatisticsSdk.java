package com.huya.statistics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.duowan.live.common.CpuUtils;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.mtp.httputils.NetConfig;
import com.huya.mtp.httputils.NetworkUtil;
import com.huya.mtp.hyns.stat.NSStatReporter;
import com.huya.statistics.core.CommonFieldProvider;
import com.huya.statistics.core.StatisticsContent;
import com.huya.statistics.core.StatisticsOption;
import com.huya.statistics.core.StatisticsUidProvider;
import com.huya.statistics.core.TaskManager;
import com.huya.statistics.jce.DataInfo;
import com.huya.statistics.jce.SDKReport;
import com.huya.statistics.log.IL;
import com.huya.statistics.log.SLog;
import com.huya.statistics.util.Counter;
import com.huya.statistics.util.StatisticsThread;
import com.huya.statistics.util.Util;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.track.SqTrackNetDnsKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public class StatisticsSdk {
    private static final String DEFAULT_URL = "https://ylog.huya.com/m.gif";
    private static final long HEART_BEAT_INTERVAL = 60000;
    static final String PARAMS_CURRENT_VIEW = "cur_view";
    private static Context context;
    private static String cref;
    private static boolean detailsLogEnable;
    private static String fromApp;
    private static volatile Counter.Callback heartbeatReport;
    private static boolean isStart;
    private static String mCurPage;
    private static Long mOnResumeTime;
    private static String mPrePage;
    private static StatisticsOption option;
    private static String ref;
    private static CommonFieldProvider sCommonFieldProvider;
    private static HashMap<String, String> sCommonParams;
    private static TaskManager sManager;
    private static String sessionId;
    private static Long startUpTime;
    private static StatisticsUidProvider uidProvider;
    private static Long yyUid;
    private static final Counter heartbeatInvoker = new Counter(StatisticsThread.getTimerHandler(), 0, 60000, true);
    private static volatile boolean sInited = false;
    private static String TAG = "StatisticsSdk";
    private static String uve = null;
    private static String countryid = null;
    private static String ggadid = null;
    private static String sguid = null;
    private static String rso = null;
    private static String channel = null;
    private static String upChannel = null;
    private static String experiment = null;
    private static String passport = null;
    private static Counter.Callback heartBeatCallBack = null;
    private static final long SESSION_TIMEOUT = 30000;
    private static long sessionTimeOut = SESSION_TIMEOUT;
    private static QuitTimer quitTimer = new QuitTimer();
    private static boolean autoRegisterActivityLife = false;
    private static volatile boolean isInActivity = false;

    static {
        HashMap<String, String> map = new HashMap<>();
        sCommonParams = map;
        detailsLogEnable = false;
        map.put(PARAMS_CURRENT_VIEW, "");
    }

    protected static synchronized void init(Context context2, StatisticsOption statisticsOption, StatisticsUidProvider statisticsUidProvider) {
        if (sInited) {
            return;
        }
        if (statisticsOption == null) {
            statisticsOption = new StatisticsOption(DEFAULT_URL);
        }
        if (TextUtils.isEmpty(statisticsOption.getUrl())) {
            statisticsOption.setUrl(DEFAULT_URL);
        }
        if (TextUtils.isEmpty(statisticsOption.getVer())) {
            throw new IllegalStateException("You have ver");
        }
        if (TextUtils.isEmpty(statisticsOption.getFrom())) {
            throw new IllegalStateException("You have from");
        }
        if (TextUtils.isEmpty(statisticsOption.getVer())) {
            throw new IllegalStateException("You have ver");
        }
        if (TextUtils.isEmpty(statisticsOption.getDty())) {
            throw new IllegalStateException("You have Dty");
        }
        sManager = new TaskManager(context2.getApplicationContext(), statisticsOption.getUrl(), statisticsOption.isEncrypted());
        option = statisticsOption;
        context = context2;
        uidProvider = statisticsUidProvider;
        reportForLaunch();
        initChannel(context2, statisticsOption.getFrom());
        sInited = true;
    }

    static void setCommonFieldProvider(CommonFieldProvider commonFieldProvider) {
        sCommonFieldProvider = commonFieldProvider;
    }

    static void putCommonParams(String str, String str2) {
        sCommonParams.put(str, str2);
    }

    private static void initChannel(Context context2, String str) {
        String string = Util.getConfig(context2).getString("statistics_sdk_install_channel", null);
        if (TextUtils.isEmpty(string)) {
            channel = str;
            Util.getConfig(context2).edit().putString("statistics_sdk_install_channel", str).apply();
        } else {
            channel = string;
        }
        upChannel = str;
    }

    protected static boolean reportStatisticContentAll(StatisticsContent statisticsContent) {
        Map<String, String> liveCommonField;
        checkSDKInit();
        statisticsContent.createUUID();
        statisticsContent.addCommonFields();
        CommonFieldProvider commonFieldProvider = sCommonFieldProvider;
        if (commonFieldProvider != null && (liveCommonField = commonFieldProvider.getLiveCommonField()) != null && liveCommonField.size() > 0) {
            statisticsContent.putAll(liveCommonField);
        }
        if (sCommonParams.size() > 0) {
            statisticsContent.putAll(sCommonParams);
        }
        sManager.addTask(statisticsContent);
        if (!detailsLogEnable) {
            return true;
        }
        printDetailsLog(statisticsContent);
        return true;
    }

    protected static void reportDirectly(List<StatisticsContent> list, boolean z) {
        ArrayList<DataInfo> arrayList = new ArrayList<>();
        Iterator<StatisticsContent> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new DataInfo(it.next().getRaw()));
        }
        SDKReport sDKReport = new SDKReport();
        sDKReport.setVBody(arrayList);
        sDKReport.setTHeader(new DataInfo());
        NetworkUtil.post(DEFAULT_URL, sDKReport.toByteArray(), 1, new NetConfig.Builder().setEncryption(z).build());
    }

    private static void printDetailsLog(StatisticsContent statisticsContent) {
        SLog.info(TAG, new JSONObject(statisticsContent.getRaw()).toString(), new Object[0]);
    }

    protected static void reportAllEvent(String str, String str2, Long l, StatisticsContent statisticsContent) {
        checkSDKInit(str);
        if (statisticsContent == null) {
            statisticsContent = new StatisticsContent();
        }
        putTraceIdToExtra(statisticsContent);
        fillCommon(statisticsContent, context);
        statisticsContent.put(StatisticsContent.EVENT_id, str.trim());
        if (!TextUtils.isEmpty(str2)) {
            statisticsContent.put(StatisticsContent.EVENT_DESC, str2);
        }
        if (l != null) {
            statisticsContent.put("dur", l.longValue());
        }
        statisticsContent.put(StatisticsContent.ACT, "hyevent");
        reportStatisticContentAll(statisticsContent);
    }

    protected static void setCref(String str) {
        cref = str;
    }

    protected static void setRef(String str) {
        ref = str;
    }

    protected static void setFromApp(String str) {
        fromApp = str;
    }

    protected static Context getContext() {
        return context;
    }

    protected static synchronized void onResume(Activity activity) {
        if (activity != null) {
            if (!autoRegisterActivityLife) {
                onPageStart(activity.getClass().getName());
            }
        }
    }

    protected static synchronized void onPause(Activity activity) {
        if (activity != null) {
            if (!autoRegisterActivityLife) {
                onPagePause(activity.getClass().getName());
            }
        }
    }

    protected static synchronized void onPageStart(String str) {
        checkSDKInit();
        try {
            mCurPage = str;
            mOnResumeTime = Long.valueOf(System.currentTimeMillis());
            isInActivity = true;
            startUpReport();
            quitTimer.clearQuitTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static synchronized void onPagePause(String str) {
        checkSDKInit();
        try {
            pageView(str, mPrePage, (mOnResumeTime != null ? Long.valueOf(System.currentTimeMillis() - mOnResumeTime.longValue()) : null).longValue());
            mPrePage = str;
            isInActivity = false;
            quitTimer.startQuitTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void registerActivityLifecycleMonitor() {
        checkSDKInit();
        autoRegisterActivityLife = true;
        ((Application) context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.huya.statistics.StatisticsSdk.1
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
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(final Activity activity) {
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.StatisticsSdk.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StatisticsSdk.onPageStart(activity.getClass().getName());
                    }
                });
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(final Activity activity) {
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.StatisticsSdk.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        StatisticsSdk.onPagePause(activity.getClass().getName());
                    }
                });
            }
        });
    }

    protected static void setLoginSuccess(Long l) {
        Long l2;
        checkSDKInit();
        if ((yyUid != null || l == null) && ((l2 = yyUid) == null || l2.equals(l))) {
            return;
        }
        if (startUpTime != null) {
            endUp();
            yyUid = l;
            startUp();
            heartBeat();
            return;
        }
        yyUid = l;
    }

    protected static void pauseReport(long j) {
        checkSDKInit();
        sManager.pauseReport(j);
    }

    protected static void realTimeReport(boolean z) {
        sManager.realTimeReport(z);
    }

    protected static void setDetailsLogEnable(boolean z) {
        detailsLogEnable = z;
    }

    protected static void setLogImp(IL il) {
        if (il != null) {
            SLog.setLogImp(il);
        }
    }

    protected static String getSessionId() {
        return sessionId;
    }

    protected static void setHeartBeatCallBack(Counter.Callback callback) {
        heartBeatCallBack = callback;
    }

    protected static void checkSDKInit() {
        checkSDKInit(null);
    }

    protected static void checkSDKInit(String str) {
        if (sInited) {
            return;
        }
        String str2 = "You have to initialize it.";
        if (!TextUtils.isEmpty(str)) {
            str2 = "You have to initialize it. message:" + str;
        }
        throw new IllegalStateException(str2);
    }

    protected static void setUve(String str) {
        uve = str;
    }

    protected static void setCountryid(String str) {
        countryid = str;
    }

    protected static void setGgadid(String str) {
        ggadid = str;
    }

    protected static void setSguid(String str) {
        sguid = str;
    }

    protected static void setRso(String str) {
        rso = str;
    }

    protected static void setPassport(String str) {
        passport = str;
    }

    protected static void setSessionTimeOut(long j) {
        sessionTimeOut = j;
        if (quitTimer.isRun) {
            quitTimer.startQuitTimer();
        }
    }

    private static void fillCommon(StatisticsContent statisticsContent, Context context2) {
        if (option == null) {
            return;
        }
        if (!containsValue(statisticsContent, "ref")) {
            statisticsContent.put("ref", ref);
        }
        if (!containsValue(statisticsContent, "cref")) {
            statisticsContent.put("cref", cref);
        }
        if (!containsValue(statisticsContent, StatisticsContent.PRO)) {
            statisticsContent.put(StatisticsContent.PRO, option.getPro());
        }
        if (!containsValue(statisticsContent, "dty")) {
            statisticsContent.put("dty", option.getDty());
        }
        if (!containsValue(statisticsContent, SqConstants.SESSION_ID)) {
            statisticsContent.put(SqConstants.SESSION_ID, sessionId);
        }
        StatisticsUidProvider statisticsUidProvider = uidProvider;
        if (statisticsUidProvider != null && statisticsUidProvider.getUid() != 0) {
            statisticsContent.put("yyuid", uidProvider.getUid());
        }
        if (!TextUtils.isEmpty(fromApp)) {
            statisticsContent.put("fromapp", fromApp);
        }
        if (!TextUtils.isEmpty(countryid)) {
            statisticsContent.put("countryid", countryid);
        }
        if (!TextUtils.isEmpty(option.getViersionCode())) {
            statisticsContent.put("version_code", option.getViersionCode());
        }
        if (!TextUtils.isEmpty(ggadid)) {
            statisticsContent.put("ggadid", ggadid);
        }
        if (!TextUtils.isEmpty(sguid)) {
            statisticsContent.put(NSStatReporter.NS_SGUID, sguid);
        }
        if (!TextUtils.isEmpty(experiment)) {
            statisticsContent.put("oexp", experiment);
        }
        if (!TextUtils.isEmpty(passport)) {
            statisticsContent.put("passport", passport);
        }
        statisticsContent.put("rid", "ods_action_log");
        statisticsContent.put(StatisticsContent.MID, Util.getAndroidId(context2));
        statisticsContent.put(StatisticsContent.CHA, channel);
        statisticsContent.put("up_channel", upChannel);
        statisticsContent.put("rso", rso);
        statisticsContent.put("ive", option.getVer());
        String ver = uve;
        if (ver == null) {
            ver = option.getVer();
        }
        statisticsContent.put("uve", ver);
        statisticsContent.put("sdk_ver", option.getVer());
        statisticsContent.put("lla", Util.getLang());
        statisticsContent.put("os", Util.getOS());
        statisticsContent.put(StatisticsContent.SCO, CpuUtils.CPU_ARCHITECTURE_TYPE_32);
        statisticsContent.put(StatisticsContent.SRE, Util.getScreenResolution(context2));
        statisticsContent.put("machine", Util.getSjm());
        statisticsContent.put("net_type", Util.getNetworkTypeName(context2));
        statisticsContent.put("platform", option.getPlatform() == null ? "mobile/andriod" : option.getPlatform());
        statisticsContent.put(StatisticsContent.SJP, Util.getSjp());
        statisticsContent.put("ati", Util.getDateString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportInstall() {
        SharedPreferences config = Util.getConfig(context);
        int oldVer = config.getInt("install_reportVer", -1);
        int versionNo = Util.getVersionNo(context);
        if (oldVer == -1) {
            oldVer = getOldVer();
        }
        if (oldVer == -1) {
            reportAllEvent("install/new", "产品安装", null, null);
            config.edit().putInt("install_reportVer", versionNo).apply();
        } else if (oldVer != versionNo) {
            reportAllEvent("install/update", "产品升级", null, null);
            config.edit().putInt("install_reportVer", versionNo).apply();
        }
    }

    protected static void setExperiment(String str) {
        experiment = str;
    }

    private static int getOldVer() {
        return context.getSharedPreferences("huyastatispref", 0).getInt("reportVer", -1);
    }

    private static void putTraceIdToExtra(StatisticsContent statisticsContent) {
        if (statisticsContent.getRaw().get("traceid") != null) {
            statisticsContent.put(SqTrackNetDnsKey.extra, String.format(Locale.getDefault(), "[{\"traceid\":\"%s\"}]", statisticsContent.getRaw().get("traceid")));
            statisticsContent.getRaw().remove("traceid");
        }
    }

    protected static String getChannel() {
        return channel;
    }

    private static void pageView(String str, String str2, long j) {
        StatisticsContent statisticsContent = new StatisticsContent();
        statisticsContent.put("curl", str);
        statisticsContent.put("furl", str2);
        reportAllEvent("pageview", "页面浏览", Long.valueOf(j), statisticsContent);
    }

    private static void startUpReport() {
        if (heartbeatReport != null) {
            SLog.warn(TAG, "heart beat as for mbsdkdo has been started.", new Object[0]);
            return;
        }
        startUp();
        Counter.Callback callback = new Counter.Callback() { // from class: com.huya.statistics.StatisticsSdk.2
            @Override // com.huya.statistics.util.Counter.Callback
            public void onCount(int i) {
                StatisticsSdk.heartBeat();
                if (StatisticsSdk.heartBeatCallBack != null) {
                    StatisticsSdk.heartBeatCallBack.onCount(i);
                }
            }
        };
        heartbeatReport = callback;
        heartbeatInvoker.setCallback(callback);
        heartbeatInvoker.start(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void heartBeat() {
        StatisticsContent statisticsContent = new StatisticsContent();
        Long lValueOf = startUpTime != null ? Long.valueOf(System.currentTimeMillis() - startUpTime.longValue()) : null;
        addAppStateStaticContent(statisticsContent);
        fillPageInfo(statisticsContent);
        reportAllEvent(SdkReportConst.HuyaHeartBeart, null, lValueOf, statisticsContent);
    }

    private static void startUp() {
        StatisticsUidProvider statisticsUidProvider = uidProvider;
        if (statisticsUidProvider != null) {
            yyUid = Long.valueOf(statisticsUidProvider.getUid());
        }
        SLog.info(TAG, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_STARTUP, new Object[0]);
        sessionId = Util.generateSession();
        startUpTime = Long.valueOf(System.currentTimeMillis());
        StatisticsContent statisticsContent = new StatisticsContent();
        addAppStateStaticContent(statisticsContent);
        fillPageInfo(statisticsContent);
        reportAllEvent("startup", null, null, statisticsContent);
    }

    private static void endUp() {
        SLog.info(TAG, "endUp", new Object[0]);
        if (startUpTime == null) {
            return;
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis() - startUpTime.longValue());
        StatisticsContent statisticsContent = new StatisticsContent();
        addAppStateStaticContent(statisticsContent);
        fillPageInfo(statisticsContent);
        reportAllEvent(SdkReportConst.HuyaEndUp, null, lValueOf, statisticsContent);
        startUpTime = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void quitApp() {
        if (Util.isForeground(context)) {
            return;
        }
        heartbeatInvoker.stop();
        endUp();
        isStart = false;
        mPrePage = null;
        mOnResumeTime = null;
        heartbeatReport = null;
        sessionId = null;
        startUpTime = null;
    }

    protected static void addAppStateStaticContent(StatisticsContent statisticsContent) {
        statisticsContent.put("isactive", Util.isForeground(context) ? 1 : 0);
    }

    protected static void fillPageInfo(StatisticsContent statisticsContent) {
        statisticsContent.put("furl", mPrePage);
        statisticsContent.put("curl", mCurPage);
    }

    private static boolean containsValue(StatisticsContent statisticsContent, String str) {
        return statisticsContent.containsKey(str) && !TextUtils.isEmpty(statisticsContent.get(str));
    }

    private static void reportForLaunch() {
        StatisticsThread.getTimerHandler().postDelayed(new Runnable() { // from class: com.huya.statistics.StatisticsSdk.3
            @Override // java.lang.Runnable
            public void run() {
                StatisticsSdk.reportInstall();
            }
        }, 2100L);
    }

    private static class QuitTimer {
        private volatile boolean isRun;
        private final Runnable timer;

        private QuitTimer() {
            this.isRun = false;
            this.timer = new Runnable() { // from class: com.huya.statistics.StatisticsSdk.QuitTimer.1
                @Override // java.lang.Runnable
                public void run() {
                    QuitTimer.this.isRun = false;
                    StatisticsSdk.quitApp();
                }
            };
        }

        public boolean isRun() {
            return this.isRun;
        }

        public synchronized void startQuitTimer() {
            this.isRun = true;
            StatisticsThread.getTimerHandler().removeCallbacks(this.timer);
            StatisticsThread.getTimerHandler().postDelayed(this.timer, StatisticsSdk.sessionTimeOut);
        }

        public synchronized void clearQuitTimer() {
            StatisticsThread.getTimerHandler().removeCallbacks(this.timer);
            this.isRun = false;
        }
    }
}
