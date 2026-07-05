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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StatsCompat {
    private static final long HEART_BEAT_INTERVAL = 60000;
    static final String PARAMS_CURRENT_VIEW = "cur_view";
    private static final long SESSION_TIMEOUT = 30000;
    private Context context;
    private String cref;
    private boolean detailsLogEnable;
    private String fromApp;
    private volatile Counter.Callback heartbeatReport;
    private boolean isStart;
    private String mCurPage;
    private Long mOnResumeTime;
    private String mPrePage;
    private StatisticsOption option;
    private String ref;
    private CommonFieldProvider sCommonFieldProvider;
    private HashMap<String, String> sCommonParams;
    private TaskManager sManager;
    private String sessionId;
    private Long startUpTime;
    private StatisticsUidProvider uidProvider;
    private Long yyUid;
    private final String DEFAULT_URL = "https://ylog.huya.com/m.gif";
    private final Counter heartbeatInvoker = new Counter(StatisticsThread.getTimerHandler(), 0, 60000, true);
    private volatile boolean sInited = false;
    private String TAG = "StatsCompat";
    private String uve = null;
    private String countryid = null;
    private String ggadid = null;
    private String sguid = null;
    private String rso = null;
    private String channel = null;
    private String upChannel = null;
    private String experiment = null;
    private String passport = null;
    private Counter.Callback heartBeatCallBack = null;
    private long sessionTimeOut = SESSION_TIMEOUT;
    private QuitTimer quitTimer = new QuitTimer();
    private boolean autoRegisterActivityLife = false;
    private volatile boolean isInActivity = false;

    public StatsCompat() {
        HashMap<String, String> map = new HashMap<>();
        this.sCommonParams = map;
        this.detailsLogEnable = false;
        map.put(PARAMS_CURRENT_VIEW, "");
    }

    protected synchronized void init(Context context, StatisticsOption statisticsOption, StatisticsUidProvider statisticsUidProvider) {
        if (this.sInited) {
            return;
        }
        if (statisticsOption == null) {
            statisticsOption = new StatisticsOption("https://ylog.huya.com/m.gif");
        }
        if (TextUtils.isEmpty(statisticsOption.getUrl())) {
            statisticsOption.setUrl("https://ylog.huya.com/m.gif");
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
        this.option = statisticsOption;
        this.context = context;
        this.uidProvider = statisticsUidProvider;
        this.sInited = true;
        this.sManager = new TaskManager(context.getApplicationContext(), statisticsOption.getUrl(), statisticsOption.isEncrypted());
        reportForLaunch();
        initChannel(context, statisticsOption.getFrom());
    }

    void setCommonFieldProvider(CommonFieldProvider commonFieldProvider) {
        this.sCommonFieldProvider = commonFieldProvider;
    }

    void putCommonParams(String str, String str2) {
        this.sCommonParams.put(str, str2);
    }

    private void initChannel(Context context, String str) {
        String string = Util.getConfig(context).getString("statistics_sdk_install_channel", null);
        if (TextUtils.isEmpty(string)) {
            this.channel = str;
            Util.getConfig(context).edit().putString("statistics_sdk_install_channel", str).apply();
        } else {
            this.channel = string;
        }
        this.upChannel = str;
    }

    protected boolean reportStatisticContentAll(StatisticsContent statisticsContent) {
        Map<String, String> liveCommonField;
        checkSDKInit();
        statisticsContent.createUUID();
        statisticsContent.addCommonFields();
        CommonFieldProvider commonFieldProvider = this.sCommonFieldProvider;
        if (commonFieldProvider != null && (liveCommonField = commonFieldProvider.getLiveCommonField()) != null && liveCommonField.size() > 0) {
            statisticsContent.putAll(liveCommonField);
        }
        if (this.sCommonParams.size() > 0) {
            statisticsContent.putAll(this.sCommonParams);
        }
        this.sManager.addTask(statisticsContent);
        if (!this.detailsLogEnable) {
            return true;
        }
        printDetailsLog(statisticsContent);
        return true;
    }

    protected void reportDirectly(List<StatisticsContent> list, boolean z) {
        ArrayList<DataInfo> arrayList = new ArrayList<>();
        for (StatisticsContent statisticsContent : list) {
            putTraceIdToExtra(statisticsContent);
            fillCommon(statisticsContent, this.context);
            arrayList.add(new DataInfo(statisticsContent.getRaw()));
        }
        SDKReport sDKReport = new SDKReport();
        sDKReport.setVBody(arrayList);
        sDKReport.setTHeader(new DataInfo());
        NetworkUtil.post("https://ylog.huya.com/m.gif", sDKReport.toByteArray(), 1, new NetConfig.Builder().setEncryption(z).build());
    }

    private void printDetailsLog(StatisticsContent statisticsContent) {
        SLog.info(this.TAG, new JSONObject(statisticsContent.getRaw()).toString(), new Object[0]);
    }

    protected void reportAllEvent(String str, String str2, Long l, StatisticsContent statisticsContent) {
        checkSDKInit(str);
        if (statisticsContent == null) {
            statisticsContent = new StatisticsContent();
        }
        putTraceIdToExtra(statisticsContent);
        fillCommon(statisticsContent, this.context);
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

    protected void setCref(String str) {
        this.cref = str;
    }

    protected void setRef(String str) {
        this.ref = str;
    }

    protected void setFromApp(String str) {
        this.fromApp = str;
    }

    protected Context getContext() {
        return this.context;
    }

    protected synchronized void onResume(Activity activity) {
        if (activity != null) {
            if (!this.autoRegisterActivityLife) {
                onPageStart(activity.getClass().getName());
            }
        }
    }

    protected synchronized void onPause(Activity activity) {
        if (activity != null) {
            if (!this.autoRegisterActivityLife) {
                onPagePause(activity.getClass().getName());
            }
        }
    }

    protected synchronized void onPageStart(String str) {
        checkSDKInit();
        try {
            this.mCurPage = str;
            this.mOnResumeTime = Long.valueOf(System.currentTimeMillis());
            this.isInActivity = true;
            startUpReport();
            this.quitTimer.clearQuitTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected synchronized void onPagePause(String str) {
        checkSDKInit();
        try {
            pageView(str, this.mPrePage, (this.mOnResumeTime != null ? Long.valueOf(System.currentTimeMillis() - this.mOnResumeTime.longValue()) : null).longValue());
            this.mPrePage = str;
            this.isInActivity = false;
            this.quitTimer.startQuitTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void registerActivityLifecycleMonitor() {
        checkSDKInit();
        this.autoRegisterActivityLife = true;
        ((Application) this.context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.huya.statistics.StatsCompat.1
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
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.StatsCompat.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StatsCompat.this.onPageStart(activity.getClass().getName());
                    }
                });
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(final Activity activity) {
                StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.StatsCompat.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        StatsCompat.this.onPagePause(activity.getClass().getName());
                    }
                });
            }
        });
    }

    protected void setLoginSuccess(Long l) {
        Long l2;
        checkSDKInit();
        if ((this.yyUid != null || l == null) && ((l2 = this.yyUid) == null || l2.equals(l))) {
            return;
        }
        if (this.startUpTime != null) {
            endUp();
            this.yyUid = l;
            startUp();
            heartBeat();
            return;
        }
        this.yyUid = l;
    }

    protected void pauseReport(long j) {
        checkSDKInit();
        this.sManager.pauseReport(j);
    }

    protected void realTimeReport(boolean z) {
        this.sManager.realTimeReport(z);
    }

    protected void setDetailsLogEnable(boolean z) {
        this.detailsLogEnable = z;
    }

    protected void setLogImp(IL il) {
        if (il != null) {
            SLog.setLogImp(il);
        }
    }

    protected String getSessionId() {
        return this.sessionId;
    }

    protected void setHeartBeatCallBack(Counter.Callback callback) {
        this.heartBeatCallBack = callback;
    }

    protected void checkSDKInit() {
        checkSDKInit(null);
    }

    protected void checkSDKInit(String str) {
        if (this.sInited) {
            return;
        }
        String str2 = "You have to initialize it.";
        if (!TextUtils.isEmpty(str)) {
            str2 = "You have to initialize it. message:" + str;
        }
        throw new IllegalStateException(str2);
    }

    protected void setUve(String str) {
        this.uve = str;
    }

    protected void setCountryid(String str) {
        this.countryid = str;
    }

    protected void setGgadid(String str) {
        this.ggadid = str;
    }

    protected void setSguid(String str) {
        this.sguid = str;
    }

    protected void setRso(String str) {
        this.rso = str;
    }

    protected void setPassport(String str) {
        this.passport = str;
    }

    protected void setSessionTimeOut(long j) {
        this.sessionTimeOut = j;
        if (this.quitTimer.isRun) {
            this.quitTimer.startQuitTimer();
        }
    }

    private void fillCommon(StatisticsContent statisticsContent, Context context) {
        if (this.option == null) {
            return;
        }
        if (!containsValue(statisticsContent, "ref")) {
            statisticsContent.put("ref", this.ref);
        }
        if (!containsValue(statisticsContent, "cref")) {
            statisticsContent.put("cref", this.cref);
        }
        if (!containsValue(statisticsContent, StatisticsContent.PRO)) {
            statisticsContent.put(StatisticsContent.PRO, this.option.getPro());
        }
        if (!containsValue(statisticsContent, "dty")) {
            statisticsContent.put("dty", this.option.getDty());
        }
        if (!containsValue(statisticsContent, SqConstants.SESSION_ID)) {
            statisticsContent.put(SqConstants.SESSION_ID, this.sessionId);
        }
        StatisticsUidProvider statisticsUidProvider = this.uidProvider;
        if (statisticsUidProvider != null && statisticsUidProvider.getUid() != 0) {
            statisticsContent.put("yyuid", this.uidProvider.getUid());
        }
        if (!TextUtils.isEmpty(this.fromApp)) {
            statisticsContent.put("fromapp", this.fromApp);
        }
        if (!TextUtils.isEmpty(this.countryid)) {
            statisticsContent.put("countryid", this.countryid);
        }
        if (!TextUtils.isEmpty(this.option.getViersionCode())) {
            statisticsContent.put("version_code", this.option.getViersionCode());
        }
        if (!TextUtils.isEmpty(this.ggadid)) {
            statisticsContent.put("ggadid", this.ggadid);
        }
        if (!TextUtils.isEmpty(this.sguid)) {
            statisticsContent.put(NSStatReporter.NS_SGUID, this.sguid);
        }
        if (!TextUtils.isEmpty(this.experiment)) {
            statisticsContent.put("oexp", this.experiment);
        }
        if (!TextUtils.isEmpty(this.passport)) {
            statisticsContent.put("passport", this.passport);
        }
        statisticsContent.put("rid", "ods_action_log");
        statisticsContent.put(StatisticsContent.MID, Util.getAndroidId(context));
        statisticsContent.put(StatisticsContent.CHA, this.channel);
        statisticsContent.put("up_channel", this.upChannel);
        statisticsContent.put("rso", this.rso);
        statisticsContent.put("ive", this.option.getVer());
        String ver = this.uve;
        if (ver == null) {
            ver = this.option.getVer();
        }
        statisticsContent.put("uve", ver);
        statisticsContent.put("sdk_ver", this.option.getVer());
        statisticsContent.put("lla", Util.getLang());
        statisticsContent.put("os", Util.getOS());
        statisticsContent.put(StatisticsContent.SCO, CpuUtils.CPU_ARCHITECTURE_TYPE_32);
        statisticsContent.put(StatisticsContent.SRE, Util.getScreenResolution(context));
        statisticsContent.put("machine", Util.getSjm());
        statisticsContent.put("net_type", Util.getNetworkTypeName(context));
        statisticsContent.put("platform", this.option.getPlatform() == null ? "mobile/andriod" : this.option.getPlatform());
        statisticsContent.put(StatisticsContent.SJP, Util.getSjp());
        statisticsContent.put("ati", Util.getDateString());
        String packageName = Util.getPackageName(context);
        if (packageName != null) {
            statisticsContent.put("pkgname", packageName);
        }
        String cpuType = Util.getCpuType();
        if (cpuType != null) {
            statisticsContent.put("cpu_type", cpuType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportInstall() {
        SharedPreferences config = Util.getConfig(this.context);
        int oldVer = config.getInt("install_reportVer", -1);
        int versionNo = Util.getVersionNo(this.context);
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

    protected void setExperiment(String str) {
        this.experiment = str;
    }

    private int getOldVer() {
        return this.context.getSharedPreferences("huyastatispref", 0).getInt("reportVer", -1);
    }

    private void putTraceIdToExtra(StatisticsContent statisticsContent) {
        if (statisticsContent.getRaw().get("traceid") != null) {
            statisticsContent.put(SqTrackNetDnsKey.extra, String.format(Locale.getDefault(), "[{\"traceid\":\"%s\"}]", statisticsContent.getRaw().get("traceid")));
            statisticsContent.getRaw().remove("traceid");
        }
    }

    protected String getChannel() {
        return this.channel;
    }

    private void pageView(String str, String str2, long j) {
        StatisticsContent statisticsContent = new StatisticsContent();
        statisticsContent.put("curl", str);
        statisticsContent.put("furl", str2);
        reportAllEvent("pageview", "页面浏览", Long.valueOf(j), statisticsContent);
    }

    private void startUpReport() {
        if (this.heartbeatReport != null) {
            SLog.warn(this.TAG, "heart beat as for mbsdkdo has been started.", new Object[0]);
            return;
        }
        startUp();
        Counter.Callback callback = new Counter.Callback() { // from class: com.huya.statistics.StatsCompat.2
            @Override // com.huya.statistics.util.Counter.Callback
            public void onCount(int i) {
                StatsCompat.this.heartBeat();
                if (StatsCompat.this.heartBeatCallBack != null) {
                    StatsCompat.this.heartBeatCallBack.onCount(i);
                }
            }
        };
        this.heartbeatReport = callback;
        this.heartbeatInvoker.setCallback(callback);
        this.heartbeatInvoker.start(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void heartBeat() {
        StatisticsContent statisticsContent = new StatisticsContent();
        Long lValueOf = this.startUpTime != null ? Long.valueOf(System.currentTimeMillis() - this.startUpTime.longValue()) : null;
        addAppStateStaticContent(statisticsContent);
        fillPageInfo(statisticsContent);
        reportAllEvent(SdkReportConst.HuyaHeartBeart, null, lValueOf, statisticsContent);
    }

    private void startUp() {
        StatisticsUidProvider statisticsUidProvider = this.uidProvider;
        if (statisticsUidProvider != null) {
            this.yyUid = Long.valueOf(statisticsUidProvider.getUid());
        }
        SLog.info(this.TAG, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_STARTUP, new Object[0]);
        this.sessionId = Util.generateSession();
        this.startUpTime = Long.valueOf(System.currentTimeMillis());
        StatisticsContent statisticsContent = new StatisticsContent();
        addAppStateStaticContent(statisticsContent);
        fillPageInfo(statisticsContent);
        reportAllEvent("startup", null, null, statisticsContent);
    }

    private void endUp() {
        SLog.info(this.TAG, "endUp", new Object[0]);
        if (this.startUpTime == null) {
            return;
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis() - this.startUpTime.longValue());
        StatisticsContent statisticsContent = new StatisticsContent();
        addAppStateStaticContent(statisticsContent);
        fillPageInfo(statisticsContent);
        reportAllEvent(SdkReportConst.HuyaEndUp, null, lValueOf, statisticsContent);
        this.startUpTime = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void quitApp() {
        if (Util.isForeground(this.context)) {
            return;
        }
        this.heartbeatInvoker.stop();
        endUp();
        this.isStart = false;
        this.mPrePage = null;
        this.mOnResumeTime = null;
        this.heartbeatReport = null;
        this.sessionId = null;
        this.startUpTime = null;
    }

    protected void addAppStateStaticContent(StatisticsContent statisticsContent) {
        statisticsContent.put("isactive", Util.isForeground(this.context) ? 1 : 0);
    }

    protected void fillPageInfo(StatisticsContent statisticsContent) {
        statisticsContent.put("furl", this.mPrePage);
        statisticsContent.put("curl", this.mCurPage);
    }

    private boolean containsValue(StatisticsContent statisticsContent, String str) {
        return statisticsContent.containsKey(str) && !TextUtils.isEmpty(statisticsContent.get(str));
    }

    private void reportForLaunch() {
        StatisticsThread.getTimerHandler().postDelayed(new Runnable() { // from class: com.huya.statistics.StatsCompat.3
            @Override // java.lang.Runnable
            public void run() {
                StatsCompat.this.reportInstall();
            }
        }, 2100L);
    }

    private class QuitTimer {
        private volatile boolean isRun;
        private final Runnable timer;

        private QuitTimer() {
            this.isRun = false;
            this.timer = new Runnable() { // from class: com.huya.statistics.StatsCompat.QuitTimer.1
                @Override // java.lang.Runnable
                public void run() {
                    QuitTimer.this.isRun = false;
                    StatsCompat.this.quitApp();
                }
            };
        }

        public boolean isRun() {
            return this.isRun;
        }

        public synchronized void startQuitTimer() {
            this.isRun = true;
            StatisticsThread.getTimerHandler().removeCallbacks(this.timer);
            StatisticsThread.getTimerHandler().postDelayed(this.timer, StatsCompat.this.sessionTimeOut);
        }

        public synchronized void clearQuitTimer() {
            StatisticsThread.getTimerHandler().removeCallbacks(this.timer);
            this.isRun = false;
        }
    }
}
