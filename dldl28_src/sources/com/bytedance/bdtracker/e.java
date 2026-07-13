package com.bytedance.bdtracker;

import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.convert.BuildConfig;
import com.bytedance.applog.event.AutoTrackEventType;
import com.bytedance.applog.exception.AppCrashType;
import com.bytedance.applog.log.EventBus;
import com.volcengine.common.contant.CommonConstants;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class e implements EventBus.DataFetcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ InitConfig f243a;
    public final /* synthetic */ d b;

    public e(d dVar, InitConfig initConfig) {
        this.b = dVar;
        this.f243a = initConfig;
    }

    @Override // com.bytedance.applog.log.EventBus.DataFetcher
    public Object fetch() {
        String strJoin;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommonConstants.key_appId, this.f243a.getAid());
            jSONObject.put("channel", this.f243a.getChannel());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("AppLog版本号", BuildConfig.VERSION_NAME);
            jSONObject2.put("AppLog版本地区", "国内");
            jSONObject2.put("接口加密开关", this.b.E);
            jSONObject2.put("日志开关", this.f243a.isLogEnable());
            jSONObject2.put("自定义日志打印", this.f243a.getLogger() != null);
            jSONObject2.put("AB实验开关", this.f243a.isAbEnable());
            jSONObject2.put("自动启动图开关", this.f243a.autoStart());
            jSONObject2.put("自动激活开关", this.f243a.isAutoActive());
            jSONObject2.put("H5打通开关", this.f243a.isH5BridgeEnable());
            if (this.f243a.getH5BridgeAllowlist() != null && !this.f243a.getH5BridgeAllowlist().isEmpty()) {
                jSONObject2.put("H5域名白名单", TextUtils.join("、", this.f243a.getH5BridgeAllowlist()));
            }
            jSONObject2.put("不过滤H5域名开关", this.f243a.isH5BridgeAllowAll());
            jSONObject2.put("全埋点开关", this.f243a.isAutoTrackEnabled());
            ArrayList arrayList = new ArrayList();
            if (AutoTrackEventType.a(this.f243a.getAutoTrackEventType(), 4)) {
                arrayList.add("点击事件");
            }
            if (AutoTrackEventType.a(this.f243a.getAutoTrackEventType(), 2)) {
                arrayList.add("页面事件");
            }
            if (AutoTrackEventType.a(this.f243a.getAutoTrackEventType(), 8)) {
                arrayList.add("页面离开事件");
            }
            if (!arrayList.isEmpty()) {
                jSONObject.put("全埋点类型", TextUtils.join("、", arrayList));
            }
            jSONObject2.put("视图曝光开关", this.f243a.isExposureEnabled());
            jSONObject2.put("内部监控开关", this.f243a.isMonitorEnabled());
            jSONObject2.put("采集屏幕方向开关", this.f243a.isScreenOrientationEnabled());
            jSONObject2.put("初始化UUID", this.f243a.getUserUniqueId());
            jSONObject2.put("初始化UUID类型", this.f243a.getUserUniqueIdType());
            jSONObject2.put("采集OAID开关", this.f243a.isOaidEnabled());
            jSONObject2.put("补偿OAID开关", this.f243a.isReportOaidEnable());
            jSONObject2.put("采集ANDROID ID开关", this.f243a.isAndroidIdEnabled());
            jSONObject2.put("采集运营商信息开关", this.f243a.isOperatorInfoEnabled());
            jSONObject2.put("自动采集FRAGMENT开关", this.f243a.isAutoTrackFragmentEnabled());
            jSONObject2.put("后台静默开关", this.f243a.isSilenceInBackground());
            jSONObject2.put("隐私模式开关", this.b.x);
            jSONObject2.put("禁止采集详细信息开关", !this.b.reportPhoneDetailInfo());
            jSONObject2.put("采集Crash", AppCrashType.hasCrashType(this.f243a.getTrackCrashType(), 1) ? "JAVA" : "不采集");
            jSONObject2.put("ALINK监听", this.b.z != null);
            jSONObject2.put("自定义激活参数", this.b.A != null);
            jSONObject2.put("延迟深度链接开关", this.f243a.isDeferredALinkEnabled());
            jSONObject2.put("缓存文件名称", this.f243a.getSpName());
            jSONObject2.put("数据库文件名称", this.f243a.getDbName());
            jSONObject2.put("监听生命周期", this.f243a.isHandleLifeCycle());
            jSONObject2.put("小版本号", this.f243a.getVersionMinor());
            jSONObject2.put("版本号编码", String.valueOf(this.f243a.getVersionCode()));
            jSONObject2.put("版本号", this.f243a.getVersion());
            jSONObject2.put("应用名称", this.f243a.getAppName());
            jSONObject2.put("圈选配置", this.f243a.getPicker() != null);
            jSONObject2.put("当前进程", this.f243a.getProcess() == 1 ? "主进程" : "子进程");
            jSONObject2.put("地区", this.f243a.getRegion());
            jSONObject2.put("语言", this.f243a.getLanguage());
            jSONObject2.put("PLAY开关", this.f243a.isPlayEnable());
            if (this.f243a.getUriConfig() != null) {
                ArrayList arrayList2 = new ArrayList();
                if (this.f243a.getUriConfig().getSendUris() != null) {
                    arrayList2.addAll(Arrays.asList(this.f243a.getUriConfig().getSendUris()));
                }
                if (n0.d(this.f243a.getUriConfig().getRegisterUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getRegisterUri());
                }
                if (n0.d(this.f243a.getUriConfig().getSettingUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getSettingUri());
                }
                if (n0.d(this.f243a.getUriConfig().getAbUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getAbUri());
                }
                if (n0.d(this.f243a.getUriConfig().getActiveUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getActiveUri());
                }
                if (n0.d(this.f243a.getUriConfig().getSettingUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getSettingUri());
                }
                if (n0.d(this.f243a.getUriConfig().getBusinessUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getBusinessUri());
                }
                if (n0.d(this.f243a.getUriConfig().getProfileUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getProfileUri());
                }
                if (n0.d(this.f243a.getUriConfig().getReportOaidUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getReportOaidUri());
                }
                if (n0.d(this.f243a.getUriConfig().getAlinkAttributionUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getAlinkAttributionUri());
                }
                if (n0.d(this.f243a.getUriConfig().getAlinkQueryUri())) {
                    arrayList2.add(this.f243a.getUriConfig().getAlinkQueryUri());
                }
                strJoin = TextUtils.join("、", arrayList2);
            } else {
                strJoin = "SaaS默认";
            }
            jSONObject2.put("服务域名配置", strJoin);
            jSONObject.put("config", jSONObject2);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
