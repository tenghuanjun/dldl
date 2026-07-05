package com.huya.ciku.apm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.core.UserInfoProvider;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.jce.Field;
import com.duowan.monitor.jce.Metric;
import com.duowan.monitor.jce.MetricDetail;
import com.duowan.monitor.jce.UserId;
import com.duowan.monitor.utility.MonitorThread;
import com.huya.ciku.apm.collector.AudioCollector;
import com.huya.ciku.apm.collector.BeginLiveCollector;
import com.huya.ciku.apm.collector.HuyaCpuCollector;
import com.huya.ciku.apm.collector.HuyaMarsStatusCollector;
import com.huya.ciku.apm.collector.HuyaMemoryCollector;
import com.huya.ciku.apm.collector.HuyaNetworkTrafficCollector;
import com.huya.ciku.apm.collector.LinkMicCollector;
import com.huya.ciku.apm.collector.RouterCollector;
import com.huya.ciku.apm.collector.ScreenCastCollector;
import com.huya.ciku.apm.collector.VideoCollector;
import com.huya.ciku.apm.collector.VivoPhoneInfoCollector;
import com.huya.ciku.apm.model.LinkMicData;
import com.huya.ciku.apm.model.QoSData;
import com.huya.ciku.apm.model.ReclaimData;
import com.huya.ciku.apm.model.ShengYuanQosData;
import com.huya.ciku.apm.provider.IAudioStatisticsProvider;
import com.huya.ciku.apm.util.DeviceInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MonitorCenter implements Application.ActivityLifecycleCallbacks {
    private static final String CONFIG_URL = "https://configapi.huya.com";
    public static final String DEFAULT_SCENE = "0";
    public static final String LIVE_INTERRUPT_REASON_CONGESTION = "拥塞";
    public static final String LIVE_INTERRUPT_REASON_INTERRUPT = "断开";
    public static final String METRIC_APP_LAUNCH_TIME = "aftermaintime";
    public static final String NAMESPACE = "apm";
    public static final String SCENE_CAMERA_LIVE = "7";
    public static final String SCENE_PROJECTION_LIVE = "8";
    public static final String SERVICE_URL = "https://statwup.huya.com";
    private static MonitorCenter mInstance = new MonitorCenter();
    private Context mContext;
    private UserInfoProvider mUserInfoProvider;
    private final String APP_ID = "show";
    private final VideoCollector mVideoCollector = new VideoCollector();
    private final RouterCollector mRouterCollector = new RouterCollector();
    private final VivoPhoneInfoCollector mVivoPhoneInfoCollector = new VivoPhoneInfoCollector();
    private final HuyaMarsStatusCollector mHuyaMarsStatusCollector = new HuyaMarsStatusCollector();
    private final LinkMicCollector mLinkMicCollector = new LinkMicCollector();
    private final ScreenCastCollector mScreenCastCollector = new ScreenCastCollector();
    private final BeginLiveCollector mBeginLiveCollector = new BeginLiveCollector();
    private final AudioCollector mAudioCollector = new AudioCollector();
    private int mActivityCounter = 0;
    private boolean isBackground = false;
    private long mAppStartTime = 0;
    private boolean isLaunchTimeReported = false;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    private MonitorCenter() {
    }

    public static MonitorCenter getInstance() {
        return mInstance;
    }

    public void initMonitorSDK(Application application, UserInfoProvider userInfoProvider) {
        this.mAppStartTime = SystemClock.elapsedRealtime();
        this.isLaunchTimeReported = false;
        application.registerActivityLifecycleCallbacks(this);
        this.mUserInfoProvider = userInfoProvider;
        this.mContext = application;
        MonitorSDK.init(new MonitorSDK.MonitorConfig(application, "show", CONFIG_URL, SERVICE_URL, userInfoProvider));
        MonitorSDK.addListener("zsCpuCollector", new HuyaCpuCollector());
        MonitorSDK.addListener("zsMemoryCollector", new HuyaMemoryCollector());
        MonitorSDK.addListener("zsNetworkTrafficCollector", new HuyaNetworkTrafficCollector());
        MonitorSDK.addListener("mediaCollector", this.mVideoCollector);
        MonitorSDK.addListener("routerCollector", this.mRouterCollector);
        MonitorSDK.addListener("vivoPhoneCollector", this.mVivoPhoneInfoCollector);
        MonitorSDK.addListener("marsStatusCollector", this.mHuyaMarsStatusCollector);
        MonitorSDK.addListener("linkMicCollector", this.mLinkMicCollector);
        MonitorSDK.addListener("beginLiveCollector", this.mBeginLiveCollector);
        MonitorSDK.addListener("screenCastCollector", this.mScreenCastCollector);
        MonitorSDK.addListener("zsAudioCollector", this.mAudioCollector);
        DeviceInfo.init(application);
    }

    public void setVivoScene(String str) {
        VivoPhoneInfoCollector vivoPhoneInfoCollector = this.mVivoPhoneInfoCollector;
        if (vivoPhoneInfoCollector != null) {
            vivoPhoneInfoCollector.setScene(str);
        }
    }

    public UserInfoProvider getUserInfoProvider() {
        return this.mUserInfoProvider;
    }

    public void setUserInfoProvider(UserInfoProvider userInfoProvider) {
        this.mUserInfoProvider = userInfoProvider;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void reportAppLaunchTime() {
        if (this.isLaunchTimeReported) {
            return;
        }
        this.isLaunchTimeReported = true;
        final long jElapsedRealtime = SystemClock.elapsedRealtime() - this.mAppStartTime;
        MonitorThread.postDelayed(new Runnable() { // from class: com.huya.ciku.apm.MonitorCenter.1
            @Override // java.lang.Runnable
            public void run() {
                MonitorCenter.this.request(MonitorCenter.METRIC_APP_LAUNCH_TIME, jElapsedRealtime, EUnit.EUnit_Milliseconds);
            }
        }, 20000L);
    }

    public void accumulateBeautyProcessTime(long j) {
        this.mVideoCollector.accumulateBeautyProcessTime(j);
    }

    public void accumulateBigEyeProcessTime(long j) {
        this.mVideoCollector.accumulateBigEyeProcessTime(j);
    }

    public void accumulateVideoLinkProcessTime(long j) {
        this.mVideoCollector.accumulateVideoLinkProcessTime(j);
    }

    public void reportBeginLive(int i, int i2, String str) {
        UserInfoProvider userInfoProvider = this.mUserInfoProvider;
        if (userInfoProvider == null || userInfoProvider.getUserId() == null) {
            return;
        }
        this.mBeginLiveCollector.report(i, i2, str, this.mUserInfoProvider.getUserId().lUid);
    }

    public void reportScreenCast(int i, int i2, String str) {
        UserInfoProvider userInfoProvider = this.mUserInfoProvider;
        if (userInfoProvider == null || userInfoProvider.getUserId() == null) {
            return;
        }
        this.mScreenCastCollector.report(i, i2, str, this.mUserInfoProvider.getUserId().lUid);
    }

    public void reportInternetLag() {
        this.mVideoCollector.reportInternetLag(true);
    }

    public void reportTotalStartLive() {
        this.mVideoCollector.reportTotalStartLive(true);
    }

    public void start(String str) {
        reportTotalStartLive();
        this.mVideoCollector.start();
        this.mRouterCollector.start(str);
        this.mHuyaMarsStatusCollector.start();
        this.mAudioCollector.start();
    }

    public void reportTotalEndLive() {
        this.mVideoCollector.reportTotalEndLive(true);
    }

    public void reportLiveInterrupt(String str) {
        this.mVideoCollector.reportLiveInterrupt(true, str);
    }

    public void accumulateAnimationProcessTime(long j) {
        this.mVideoCollector.accumulateAnimationProcessTime(j);
    }

    public void reportLinkMicTotalCount() {
        this.mVideoCollector.reportLinkMicTotalCount(true);
    }

    public void reportLinkMicSuccessCount() {
        this.mVideoCollector.reportLinkMicSuccessCount(true);
    }

    public void reportPKTotalCount() {
        this.mVideoCollector.reportPKTotalCount(true);
    }

    public void reportPKSuccessTotalCount() {
        this.mVideoCollector.reportPKSuccessTotalCount(true);
    }

    public void stopReport() {
        reportTotalEndLive();
        this.mVideoCollector.stop();
        this.mRouterCollector.onStop();
        this.mVivoPhoneInfoCollector.setScene("0");
        this.mHuyaMarsStatusCollector.onStop();
        this.mLinkMicCollector.clearOnEndLive();
        this.mAudioCollector.stop();
    }

    public void collectVideoFrameRate() {
        this.mVideoCollector.collectVideoFrameRate();
    }

    public void collectAudioFrameRate() {
        this.mVideoCollector.collectAudioFrameRate();
    }

    public void collectStatus(int i) {
        this.mHuyaMarsStatusCollector.collectStatus(i);
    }

    public void reportLinkMicData(LinkMicData linkMicData) {
        this.mLinkMicCollector.reportLinkMicData(linkMicData);
    }

    public void markLinkMic(String str, String str2) {
        this.mLinkMicCollector.markLinkMic(str, str2);
    }

    public void updateStatus(String str, String str2, int i) {
        this.mLinkMicCollector.updateStatus(str, str2, i);
    }

    public String getLinkMicId(String str, String str2) {
        return this.mLinkMicCollector.getLinkMicId(this.mLinkMicCollector.getSessionId(str, str2));
    }

    public void reportPKStartData(String str, String str2, String str3) {
        this.mLinkMicCollector.reportPKStartData(str, str2, str3);
    }

    public void startLive(String str) {
        this.mLinkMicCollector.markLinkMic(null, null);
        this.mLinkMicCollector.scene(str);
    }

    public void reportQoSData(QoSData qoSData) {
        if (qoSData == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Dimension("correlationId", qoSData.correlationId));
        arrayList.add(new Dimension("destinationIp", qoSData.destinationIp));
        arrayList.add(new Dimension("msg", qoSData.msg));
        arrayList.add(new Dimension("phoneNumber", qoSData.phoneNumber));
        arrayList.add(new Dimension("sourceIp", qoSData.sourceIp));
        arrayList.add(new Dimension("bitrate", String.valueOf(qoSData.bitrate)));
        arrayList.add(new Dimension("respCode", String.valueOf(qoSData.respCode)));
        arrayList.add(new Dimension("reqType", String.valueOf(qoSData.reqType)));
        arrayList.add(new Dimension("speedupType", qoSData.speedupType));
        getInstance().request(QoSData.METRIC_NAME_QOS, 0.0d, EUnit.EUnit_Count, arrayList);
    }

    public void reportShengyuanQoSData(ShengYuanQosData shengYuanQosData) {
        if (shengYuanQosData == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Dimension("correlationId", shengYuanQosData.correlationId));
        arrayList.add(new Dimension("destinationIp", shengYuanQosData.destinationIp));
        arrayList.add(new Dimension("phoneNumber", shengYuanQosData.phoneNumber));
        arrayList.add(new Dimension("sim", shengYuanQosData.sim));
        arrayList.add(new Dimension("privateip", shengYuanQosData.privateIP));
        arrayList.add(new Dimension("publicip", shengYuanQosData.publicIP));
        arrayList.add(new Dimension("token", shengYuanQosData.token));
        arrayList.add(new Dimension("state", shengYuanQosData.state));
        arrayList.add(new Dimension("respcode", String.valueOf(shengYuanQosData.respCode)));
        arrayList.add(new Dimension("speedrate", shengYuanQosData.speedRate));
        arrayList.add(new Dimension("success", String.valueOf(shengYuanQosData.success ? 1 : 0)));
        arrayList.add(new Dimension("location", shengYuanQosData.location));
        arrayList.add(new Dimension("scene", shengYuanQosData.scene));
        arrayList.add(new Dimension("roomid", String.valueOf(shengYuanQosData.roomId)));
        getInstance().request(ShengYuanQosData.METRIC_NAME_QOS, shengYuanQosData.value, EUnit.EUnit_Count, arrayList);
    }

    public boolean isValidCanceled() {
        return this.mLinkMicCollector.isValidCanceled();
    }

    public void reportReclaimData(ReclaimData reclaimData) {
        if (reclaimData == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Dimension("liveType", String.valueOf(reclaimData.liveType.type)));
        arrayList.add(new Dimension("liveTypeStr", reclaimData.liveType.typeStr));
        arrayList.add(new Dimension("voice", String.valueOf(reclaimData.voice)));
        arrayList.add(new Dimension("reclaimType", String.valueOf(reclaimData.reclaimType.type)));
        arrayList.add(new Dimension("reclaimTypeStr", String.valueOf(reclaimData.reclaimType.typeStr)));
        if (!TextUtils.isEmpty(reclaimData.anchoruid)) {
            arrayList.add(new Dimension("anchoruid", reclaimData.anchoruid));
        }
        getInstance().requestWithoutUserId(ReclaimData.METRIC_NAME_RECLAIM, 1.0d, EUnit.EUnit_Count, arrayList);
    }

    public void reportTotalLiveCount(ReclaimData.TotalLiveType totalLiveType) {
        if (totalLiveType == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Dimension("liveType", String.valueOf(totalLiveType.type)));
        getInstance().request(ReclaimData.METRIC_NAME_START_LIVE_TOTAL_COUNT, 1.0d, EUnit.EUnit_Count, arrayList);
    }

    public void request(Metric metric) {
        if (metric != null) {
            MonitorSDK.request(metric);
        }
    }

    public void request(MetricDetail metricDetail) {
        if (metricDetail != null) {
            MonitorSDK.request(metricDetail);
        }
    }

    public void request(String str, double d, EUnit eUnit) {
        request(str, d, eUnit, null);
    }

    public void request(String str, double d, EUnit eUnit, List<Dimension> list) {
        try {
            if (this.mUserInfoProvider != null && this.mUserInfoProvider.getUserId() != null) {
                UserId userId = this.mUserInfoProvider.getUserId();
                ArrayList<Dimension> arrayList = new ArrayList<>();
                MetricDetail metricDetail = new MetricDetail();
                metricDetail.setITS(System.currentTimeMillis());
                metricDetail.setSMetricName(getMetricName(str));
                if (userId != null) {
                    arrayList.add(new Dimension("anchoruid", String.valueOf(userId.lUid)));
                }
                if (list != null) {
                    arrayList.addAll(list);
                }
                metricDetail.vDimension = arrayList;
                ArrayList<Field> arrayList2 = new ArrayList<>();
                arrayList2.add(new Field("value", d));
                metricDetail.setVFiled(arrayList2);
                MonitorSDK.request(metricDetail);
            }
        } catch (Exception unused) {
        }
    }

    public void requestWithoutUserId(String str, double d, EUnit eUnit, List<Dimension> list) {
        ArrayList<Dimension> arrayList = new ArrayList<>();
        if (list != null) {
            arrayList.addAll(list);
        }
        MetricDetail metricDetail = new MetricDetail();
        metricDetail.setITS(System.currentTimeMillis());
        metricDetail.setSMetricName(getMetricName(str));
        metricDetail.vDimension = arrayList;
        ArrayList<Field> arrayList2 = new ArrayList<>();
        arrayList2.add(new Field("value", d));
        metricDetail.setVFiled(arrayList2);
        MonitorSDK.request(metricDetail);
    }

    private String getMetricName(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("metricName should not be empty");
        }
        return String.format("%s.%s.%s", "show", NAMESPACE, str);
    }

    public boolean isLiving() {
        return this.mVideoCollector.isLiving();
    }

    public boolean isBackground() {
        return this.isBackground;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.mActivityCounter++;
        this.isBackground = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i = this.mActivityCounter - 1;
        this.mActivityCounter = i;
        if (i <= 0) {
            this.isBackground = true;
        }
    }

    public void setAudioStatisticsProvider(IAudioStatisticsProvider iAudioStatisticsProvider) {
        this.mAudioCollector.setAudioStatisticsProvider(iAudioStatisticsProvider);
    }
}
