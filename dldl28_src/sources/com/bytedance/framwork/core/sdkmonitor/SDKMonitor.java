package com.bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.bytedance.framwork.core.sdklib.LogReportManager;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.bytedance.framwork.core.sdklib.MonitorManager;
import com.bytedance.framwork.core.sdklib.config.IMonitorConfigure;
import com.bytedance.framwork.core.sdklib.config.MonitorConfigure;
import com.bytedance.framwork.core.sdklib.net.ISendLog;
import com.bytedance.framwork.core.sdklib.net.MonitorLogSender;
import com.bytedance.framwork.core.sdklib.net.NetResponse;
import com.bytedance.framwork.core.sdklib.thread.AsyncEventManager;
import com.bytedance.framwork.core.sdklib.util.DecodeUtils;
import com.bytedance.framwork.core.sdklib.util.NetUtils;
import com.bytedance.framwork.core.sdklog.LogLib;
import com.bytedance.framwork.core.sdkmonitor.MonitorNetUtil;
import com.tencent.connect.common.Constants;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class SDKMonitor implements IMonitorConfigure, ISendLog, AsyncEventManager.IMonitorTimeTask {
    private static final int BIT_API_ALL_SAMPLE = 1;
    private static final int SDK_VERSION = 400;
    private static ConcurrentHashMap<String, List<String>> sAidToConfigUrl = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, List<String>> sAidToDefaultReportUrl = new ConcurrentHashMap<>();
    private String mAid;
    private volatile JSONObject mAllowLogType;
    private volatile List<String> mAllowReportList;
    private volatile List<Pattern> mAllowReportPatterns;
    private volatile JSONObject mAllowService;
    private volatile List<String> mBlackReportList;
    private volatile List<Pattern> mBlackReportPatterns;
    private Map<String, String> mCommonParams;
    private Context mContext;
    private volatile int mDisableReportApiError;
    private volatile long mFetchSettingInterval;
    private volatile boolean mHasInit;
    private JSONObject mHeaderInfo;
    private IGetExtendParams mIGetExtendParams;
    private volatile boolean mLogRemoveNet;
    private volatile boolean mLogRemoveSwitch;
    private LogReportManager mLogReportManager;
    private MonitorManager mMonitorManager;
    private volatile long mMonitorMaxRowCount;
    private volatile int mReportCount;
    private volatile int mReportFailBaseTime;
    private volatile int mReportFailRepeatCount;
    private volatile int mReportInterval;
    private volatile int mReportSLA;
    private volatile long mStopMoreChannelInterval;
    private volatile long mLastFetchSettingTime = 0;
    private volatile int mLogSendSwitch = 1;
    private List<String> mConfigUrls = new ArrayList();
    private volatile long mStopCollectLogTime = 0;
    private volatile long mStopCollectInterval = 0;
    private volatile int mStopIntervalTimes = 0;
    private volatile boolean mIsEncrypt = true;
    private List<String> mReportUrlList = new ArrayList();
    private volatile boolean mConfigExit = false;
    private CacheData mCacheData = new CacheData();

    public interface IGetCommonParams {
        String getSessionId();
    }

    public interface IGetExtendParams {
        Map<String, String> getCommonParams();

        String getSessionId();
    }

    protected SDKMonitor(String str) {
        this.mAid = str;
        this.mConfigUrls.add("https://mon.snssdk.com/monitor/appmonitor/v2/settings");
        this.mConfigUrls.add("https://monsetting.toutiao.com/monitor/appmonitor/v2/settings");
        this.mReportUrlList.add("https://mon.snssdk.com/monitor/collect/");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String addParamsToURL(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        if (!TextUtils.isDigitsOnly(str) && this.mCommonParams != null) {
            if (str.indexOf("?") < 0) {
                str = str + "?";
            }
            if (str.endsWith("?")) {
                sb = new StringBuilder();
                sb.append(str);
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append("&");
            }
            sb.append(encode(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, "UTF-8"));
            sb.append("=");
            sb.append(encode(String.valueOf(SDK_VERSION), "UTF-8"));
            str = sb.toString();
            Map<String, String> map = this.mCommonParams;
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (map.get(entry.getKey()) != null) {
                        if (str.endsWith("?")) {
                            sb2 = new StringBuilder();
                            sb2.append(str);
                        } else {
                            sb2 = new StringBuilder();
                            sb2.append(str);
                            sb2.append("&");
                        }
                        sb2.append(encode(entry.getKey().toString(), "UTF-8"));
                        sb2.append("=");
                        sb2.append(encode(map.get(entry.getKey()).toString(), "UTF-8"));
                        str = sb2.toString();
                    }
                }
            }
        }
        return str;
    }

    private void combineJson(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null || jSONObject2.length() <= 0) {
            return;
        }
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String encode(String str, String str2) {
        if (str2 == null) {
            str2 = "UTF-8";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String getSessionId() {
        IGetExtendParams iGetExtendParams = this.mIGetExtendParams;
        if (iGetExtendParams != null) {
            return iGetExtendParams.getSessionId();
        }
        return null;
    }

    private SharedPreferences getSp() {
        String shortProcessName = MonitorHelper.getShortProcessName(this.mContext);
        return this.mContext.getSharedPreferences("monitor_config" + this.mAid + shortProcessName, 0);
    }

    private void handleResponseResult(JSONObject jSONObject) {
    }

    private void initConfig() {
        SharedPreferences sp = getSp();
        String string = sp.getString(MonitorConstants.MONITOR_NET_CONFIG, null);
        this.mLastFetchSettingTime = sp.getLong(MonitorConstants.MONITOR_CONFIG_REFRESH_TIME, 0L);
        if (string != null && !TextUtils.isEmpty(string)) {
            try {
                this.mConfigExit = true;
                updateConfig(new JSONObject(string));
            } catch (Exception unused) {
                Log.e("monitor_config", "配置信息读取失败");
            }
        }
        updateConfigFromNet(false);
    }

    private void initLogLib() {
        LogLib.init(new LogLib.ILogDelegate() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.6
            @Override // com.bytedance.framwork.core.sdklog.LogLib.ILogDelegate
            public boolean isNetworkAvailable(Context context) {
                return MonitorNetUtil.isNetworkAvailable(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMonitor(Context context, JSONObject jSONObject, IGetExtendParams iGetExtendParams) {
        PackageInfo packageInfo;
        this.mHasInit = true;
        this.mContext = context.getApplicationContext();
        this.mHeaderInfo = jSONObject;
        try {
            jSONObject.put("aid", this.mAid);
            this.mHeaderInfo.put("os", "Android");
            this.mHeaderInfo.put("device_platform", PodInfo.GAME_TYPE_ANDROID);
            this.mHeaderInfo.put("os_version", Build.VERSION.RELEASE);
            this.mHeaderInfo.put("os_api", Build.VERSION.SDK_INT);
            this.mHeaderInfo.put("device_model", Build.MODEL);
            this.mHeaderInfo.put("device_brand", Build.BRAND);
            this.mHeaderInfo.put("device_manufacturer", Build.MANUFACTURER);
            this.mHeaderInfo.put(MonitorConstants.KEY_MONITOR_VERSION, "2.0.9");
            this.mHeaderInfo.put(MonitorConstants.KEY_MONITOR_FROM, MonitorConstants.MONITOR_FROM_SDK);
            if (jSONObject.isNull("region")) {
                jSONObject.put("region", Locale.getDefault().getCountry());
            }
            if (TextUtils.isEmpty(this.mHeaderInfo.optString(MonitorConstants.HOST_APP_PACKAGE_NAME))) {
                this.mHeaderInfo.put(MonitorConstants.HOST_APP_PACKAGE_NAME, context.getPackageName());
            }
            if (TextUtils.isEmpty(this.mHeaderInfo.optString("version_name"))) {
                packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
                this.mHeaderInfo.put("version_name", packageInfo.versionName);
            } else {
                packageInfo = null;
            }
            if (TextUtils.isEmpty(this.mHeaderInfo.optString("version_code"))) {
                if (packageInfo == null) {
                    packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
                }
                this.mHeaderInfo.put("version_code", packageInfo.versionCode);
            }
            this.mIGetExtendParams = iGetExtendParams;
            if (iGetExtendParams == null) {
                this.mIGetExtendParams = new IGetExtendParams() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.4
                    @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.IGetExtendParams
                    public Map<String, String> getCommonParams() {
                        return null;
                    }

                    @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.IGetExtendParams
                    public String getSessionId() {
                        return null;
                    }
                };
            }
            Map<String, String> commonParams = this.mIGetExtendParams.getCommonParams();
            this.mCommonParams = commonParams;
            if (commonParams == null) {
                this.mCommonParams = new HashMap();
            }
            this.mCommonParams.put("aid", this.mAid);
            this.mCommonParams.put(MonitorConstants.KEY_DEVICE_ID, this.mHeaderInfo.optString(MonitorConstants.KEY_DEVICE_ID));
            this.mCommonParams.put("device_platform", PodInfo.GAME_TYPE_ANDROID);
            this.mCommonParams.put(MonitorConstants.HOST_APP_PACKAGE_NAME, this.mHeaderInfo.optString(MonitorConstants.HOST_APP_PACKAGE_NAME));
            this.mCommonParams.put("channel", this.mHeaderInfo.optString("channel"));
            this.mCommonParams.put("app_version", this.mHeaderInfo.optString("app_version"));
            this.mCommonParams.put(MonitorConstants.KEY_MONITOR_VERSION, "2.0.9");
            MonitorConfigure.setCommonConfig(this.mAid, this);
            MonitorLogSender.setISendLog(this.mAid, this);
            initLogLib();
            MonitorManager monitorManager = new MonitorManager(this.mContext, this.mAid);
            this.mMonitorManager = monitorManager;
            monitorManager.init();
            List<String> list = sAidToConfigUrl.get(this.mAid);
            if (!ListUtils.isEmpty(list)) {
                this.mConfigUrls.clear();
                this.mConfigUrls.addAll(list);
            }
            List<String> list2 = sAidToDefaultReportUrl.get(this.mAid);
            if (!ListUtils.isEmpty(list2)) {
                this.mReportUrlList.clear();
                this.mReportUrlList.addAll(list2);
            }
            sAidToConfigUrl.clear();
            sAidToDefaultReportUrl.clear();
            initConfig();
        } catch (Exception unused) {
        }
    }

    private boolean isMatch(String str, List<String> list, List<Pattern> list2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!ListUtils.isEmpty(list)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.contains(it.next())) {
                    return true;
                }
            }
        }
        try {
            String path = new URI(str).getPath();
            if (!ListUtils.isEmpty(list2)) {
                Iterator<Pattern> it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (it2.next().matcher(path).matches()) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private boolean isMatchAllowURI(String str) {
        return isMatch(str, this.mAllowReportList, this.mAllowReportPatterns);
    }

    private boolean isMatchBlackURI(String str) {
        return isMatch(str, this.mBlackReportList, this.mBlackReportPatterns);
    }

    private boolean needUpdateConfigFromNet() {
        return (System.currentTimeMillis() - this.mLastFetchSettingTime) / 1000 > this.mFetchSettingInterval;
    }

    private JSONObject packLog(String str, long j, long j2, String str2, String str3, String str4, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MonitorCommonConstants.KEY_LOG_TYPE, str);
            jSONObject.put("duration", j);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("uri", Uri.parse(str2));
            }
            if (j2 > 0) {
                jSONObject.put("timestamp", j2);
            }
            jSONObject.put("status", i);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, str3);
            }
            if (TextUtils.isEmpty(str4)) {
                jSONObject.put("trace_code", "");
            } else {
                jSONObject.put("trace_code", str4);
            }
            jSONObject.put(MonitorCommonConstants.KEY_NET_TYPE, MonitorNetUtil.getNetworkType(this.mContext).getValue());
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }

    protected static void setConfigUrl(String str, List<String> list) {
        sAidToConfigUrl.put(str, list);
    }

    protected static void setDeafultReportUrl(String str, List<String> list) {
        sAidToDefaultReportUrl.put(str, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateToSP(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        try {
            updateConfig(jSONObject.getJSONObject("data"));
            SharedPreferences.Editor editorEdit = getSp().edit();
            editorEdit.putLong(MonitorConstants.MONITOR_CONFIG_REFRESH_TIME, System.currentTimeMillis());
            editorEdit.putString(MonitorConstants.MONITOR_NET_CONFIG, jSONObject.getJSONObject("data").toString());
            editorEdit.apply();
            if (this.mConfigExit) {
                return;
            }
            this.mConfigExit = true;
            this.mCacheData.handleCacheData(this);
        } catch (Throwable unused) {
        }
    }

    public void flushBuffer() {
        AsyncEventManager.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.8
            @Override // java.lang.Runnable
            public void run() {
                if (SDKMonitor.this.mMonitorManager != null) {
                    SDKMonitor.this.mMonitorManager.processPendingQueue(0L, true);
                }
            }
        });
    }

    public void flushReport() {
        AsyncEventManager.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.9
            @Override // java.lang.Runnable
            public void run() {
                if (SDKMonitor.this.mMonitorManager == null || SDKMonitor.this.mLogReportManager == null) {
                    return;
                }
                SDKMonitor.this.mLogReportManager.packAndSendLog(true);
            }
        });
    }

    boolean getLogSendSwitch() {
        return this.mLogSendSwitch == 1;
    }

    public boolean getLogTypeSwitch(String str) {
        return (this.mAllowLogType == null || TextUtils.isEmpty(str) || this.mAllowLogType.opt(str) == null) ? false : true;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public long getMonitorLogMaxSaveCount() {
        return this.mMonitorMaxRowCount;
    }

    public int getNetWorkType() {
        return NetUtils.getNetworkType(this.mContext).getValue();
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public boolean getRemoveSwitch() {
        return this.mLogRemoveNet ? this.mLogRemoveNet : this.mLogRemoveSwitch;
    }

    public boolean getServiceSwitch(String str) {
        return (this.mAllowService == null || TextUtils.isEmpty(str) || this.mAllowService.opt(str) == null) ? false : true;
    }

    void handleApiError(long j, long j2, String str, String str2, String str3, int i, JSONObject jSONObject) {
        MonitorManager monitorManager;
        if (this.mDisableReportApiError == 1 || isMatchBlackURI(str)) {
            return;
        }
        JSONObject jSONObjectPackLog = packLog(MonitorConstants.MONITOR_TYPE_API_ERROR, j, j2, str, str2, str3, i);
        combineJson(jSONObjectPackLog, jSONObject);
        if (jSONObjectPackLog == null || jSONObjectPackLog.length() <= 0 || (monitorManager = this.mMonitorManager) == null) {
            return;
        }
        monitorManager.logSend(MonitorConstants.MONITOR_TYPE_API_ERROR, MonitorConstants.MONITOR_TYPE_API_ERROR, jSONObjectPackLog);
    }

    void handleNetSLA(long j, long j2, String str, String str2, String str3, int i, JSONObject jSONObject) {
        if (isMatchBlackURI(str) || !MonitorNetUtil.isNetworkAvailable(this.mContext)) {
            return;
        }
        JSONObject jSONObjectPackLog = packLog(MonitorConstants.MONITOR_TYPE_API_ALL, j, j2, str, str2, str3, i);
        combineJson(jSONObjectPackLog, jSONObject);
        if ((jSONObjectPackLog == null || !isMatchAllowURI(str)) && this.mReportSLA == 0) {
            return;
        }
        try {
            jSONObjectPackLog.put(MonitorCommonConstants.KEY_HIT_RULES, 1);
            this.mMonitorManager.logSend(MonitorConstants.MONITOR_TYPE_API_ALL, MonitorConstants.MONITOR_TYPE_API_ALL, jSONObjectPackLog);
        } catch (JSONException unused) {
        }
    }

    public boolean init(Context context, JSONObject jSONObject, final IGetCommonParams iGetCommonParams) {
        return iGetCommonParams == null ? init(context, jSONObject, new IGetExtendParams() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.1
            @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.IGetExtendParams
            public Map<String, String> getCommonParams() {
                return null;
            }

            @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.IGetExtendParams
            public String getSessionId() {
                return null;
            }
        }) : init(context, jSONObject, new IGetExtendParams() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.2
            @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.IGetExtendParams
            public Map<String, String> getCommonParams() {
                return null;
            }

            @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.IGetExtendParams
            public String getSessionId() {
                return iGetCommonParams.getSessionId();
            }
        });
    }

    public boolean init(final Context context, final JSONObject jSONObject, final IGetExtendParams iGetExtendParams) {
        this.mHasInit = true;
        AsyncEventManager.getInstance().postDelay(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.3
            @Override // java.lang.Runnable
            public void run() {
                SDKMonitor.this.initMonitor(context, jSONObject, iGetExtendParams);
                if (SDKMonitor.this.mConfigExit) {
                    SDKMonitor.this.mCacheData.handleCacheData(SDKMonitor.this);
                }
            }
        }, 5000L);
        return true;
    }

    protected boolean isHasInit() {
        return this.mHasInit;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public boolean isLogSendSwitch() {
        return this.mLogSendSwitch == 1;
    }

    public void monitorApiError(long j, long j2, String str, String str2, String str3, int i, JSONObject jSONObject) {
        try {
            if (this.mConfigExit) {
                handleApiError(j, j2, str, str2, str3, i, jSONObject);
            } else {
                this.mCacheData.insertApiData(new ApiData(MonitorConstants.MONITOR_TYPE_API_ALL, j, j2, str, str2, str3, i, jSONObject));
            }
        } catch (Throwable unused) {
        }
    }

    public void monitorCommonLog(String str, JSONObject jSONObject) {
        try {
            if (this.mConfigExit) {
                monitorCommonLogAsync(str, jSONObject, System.currentTimeMillis());
            } else {
                this.mCacheData.insertCommonLogData(new CommonLogData(str, jSONObject, System.currentTimeMillis()));
            }
        } catch (Throwable unused) {
        }
    }

    void monitorCommonLogAsync(final String str, final JSONObject jSONObject, final long j) {
        AsyncEventManager.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.10
            @Override // java.lang.Runnable
            public void run() {
                SDKMonitor.this.monitorCommonLogInternal(str, jSONObject, j);
            }
        });
    }

    void monitorCommonLogInternal(String str, JSONObject jSONObject, long j) {
        try {
            jSONObject.put(MonitorCommonConstants.KEY_LOG_TYPE, str);
            jSONObject.put(MonitorCommonConstants.KEY_NET_TYPE, getNetWorkType());
            if (!TextUtils.isEmpty(getSessionId())) {
                jSONObject.put(MonitorCommonConstants.KEY_SESSION_ID, getSessionId());
            }
            if (jSONObject.isNull("timestamp")) {
                jSONObject.put("timestamp", j);
            }
            if (this.mMonitorManager == null || !getLogTypeSwitch(str)) {
                return;
            }
            this.mMonitorManager.logSend(MonitorCommonConstants.MONITOR_LOG_TYPE_COMMON_LOG, MonitorCommonConstants.MONITOR_LOG_TYPE_COMMON_LOG, jSONObject);
        } catch (Throwable unused) {
        }
    }

    public void monitorDuration(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        monitorStatusAndDuration(str, 0, jSONObject, jSONObject2);
    }

    public void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        try {
            if (this.mConfigExit) {
                monitorServiceAsync(str, 0, null, jSONObject, jSONObject2, jSONObject3, System.currentTimeMillis());
            } else {
                this.mCacheData.insertServiceMonitorData(new ServiceMonitorData(str, 0, null, jSONObject, jSONObject2, jSONObject3, System.currentTimeMillis()));
            }
        } catch (Throwable unused) {
        }
    }

    public void monitorSLA(long j, long j2, String str, String str2, String str3, int i, JSONObject jSONObject) {
        try {
            if (this.mConfigExit) {
                handleNetSLA(j, j2, str, str2, str3, i, jSONObject);
            } else {
                this.mCacheData.insertApiData(new ApiData(MonitorConstants.MONITOR_TYPE_API_ALL, j, j2, str, str2, str3, i, jSONObject));
            }
        } catch (Throwable unused) {
        }
    }

    void monitorService(String str, int i, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, long j) {
        if (jSONObject4 == null) {
            try {
                jSONObject4 = new JSONObject();
            } catch (Throwable unused) {
                return;
            }
        }
        jSONObject4.put(MonitorCommonConstants.KEY_LOG_TYPE, MonitorConstants.MONITOR_TYPE_SERVICE_MONITOR);
        jSONObject4.put("service", str);
        jSONObject4.put("status", i);
        jSONObject4.put(MonitorCommonConstants.KEY_NET_TYPE, getNetWorkType());
        jSONObject4.put(DBHelper.COL_VALUE, jSONObject);
        if (jSONObject2 != null) {
            jSONObject4.put("category", jSONObject2);
        }
        if (jSONObject3 != null) {
            jSONObject4.put("metric", jSONObject3);
        }
        if (jSONObject4.isNull("timestamp")) {
            jSONObject4.put("timestamp", j);
        }
        if (!TextUtils.isEmpty(getSessionId())) {
            jSONObject4.put(MonitorCommonConstants.KEY_SESSION_ID, getSessionId());
        }
        if (this.mMonitorManager == null || !getServiceSwitch(str)) {
            return;
        }
        this.mMonitorManager.logSend(MonitorConstants.MONITOR_TYPE_SERVICE_MONITOR, MonitorConstants.MONITOR_TYPE_SERVICE_MONITOR, jSONObject4);
    }

    void monitorServiceAsync(final String str, final int i, final JSONObject jSONObject, final JSONObject jSONObject2, final JSONObject jSONObject3, final JSONObject jSONObject4, final long j) {
        AsyncEventManager.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.7
            @Override // java.lang.Runnable
            public void run() {
                SDKMonitor.this.monitorService(str, i, jSONObject, jSONObject2, jSONObject3, jSONObject4, j);
            }
        });
    }

    public void monitorStatusAndDuration(String str, int i, JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (this.mConfigExit) {
                monitorServiceAsync(str, i, jSONObject, null, null, jSONObject2, System.currentTimeMillis());
            } else {
                this.mCacheData.insertServiceMonitorData(new ServiceMonitorData(str, i, jSONObject, null, null, jSONObject2, System.currentTimeMillis()));
            }
        } catch (Throwable unused) {
        }
    }

    public void monitorStatusRate(String str, int i, JSONObject jSONObject) {
        monitorStatusAndDuration(str, i, null, jSONObject);
    }

    @Override // com.bytedance.framwork.core.sdklib.thread.AsyncEventManager.IMonitorTimeTask
    public void onTimeEvent(long j) {
        if (System.currentTimeMillis() - this.mStopCollectLogTime > this.mStopCollectInterval) {
            this.mLogRemoveNet = false;
            MonitorManager monitorManager = this.mMonitorManager;
            if (monitorManager != null) {
                monitorManager.setReportLogSwitch(getLogSendSwitch());
            }
        }
        if (this.mFetchSettingInterval <= 0) {
            return;
        }
        updateConfigFromNet(false);
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportCount() {
        if (this.mReportCount <= 0) {
            return 100;
        }
        return this.mReportCount;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportFailRepeatBaseTime() {
        if (this.mReportFailBaseTime <= 0) {
            return 15;
        }
        return this.mReportFailBaseTime;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportFailRepeatCount() {
        if (this.mReportFailRepeatCount <= 0) {
            return 4;
        }
        return this.mReportFailRepeatCount;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportInterval() {
        if (this.mReportInterval <= 0) {
            return 120;
        }
        return this.mReportInterval;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public JSONObject reportJsonHeaderInfo() {
        return this.mHeaderInfo;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public List<String> reportUrl(String str) {
        return this.mReportUrlList;
    }

    @Override // com.bytedance.framwork.core.sdklib.net.ISendLog
    public NetResponse sendLog(long j, String str, byte[] bArr, int i, String str2) {
        NetResponse netResponse = new NetResponse();
        try {
            String strAddParamsToURL = addParamsToURL(str);
            byte[] bArrExcutePost = i == 1 ? MonitorNetUtil.excutePost(j, strAddParamsToURL, bArr, MonitorNetUtil.CompressType.GZIP, str2, this.mIsEncrypt) : MonitorNetUtil.excutePost(j, strAddParamsToURL, bArr, MonitorNetUtil.CompressType.NONE, str2, this.mIsEncrypt);
            this.mStopIntervalTimes = 0;
            this.mStopCollectInterval = 0L;
            netResponse.stateCode = 200;
            JSONObject jSONObject = new JSONObject(new String(bArrExcutePost));
            try {
                String strOptString = jSONObject.optString("data");
                if (!strOptString.isEmpty()) {
                    jSONObject = new JSONObject(DecodeUtils.decodeData(strOptString.getBytes()));
                }
                handleResponseResult(jSONObject);
                netResponse.responseMsg = jSONObject;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                handleResponseResult(jSONObject);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            netResponse.responseMsg = jSONObject;
            return netResponse;
        } catch (Throwable th3) {
            netResponse.stateCode = th3 instanceof HttpResponseException ? th3.getStatusCode() : -1;
            int i2 = netResponse.stateCode;
            if (i2 == 503 || i2 == 509) {
                this.mLogRemoveNet = true;
                this.mStopCollectLogTime = System.currentTimeMillis();
                this.mMonitorManager.setReportLogSwitch(false);
                LogReportManager logReportManager = this.mLogReportManager;
                if (logReportManager != null) {
                    logReportManager.setCollectLogSwitch(false);
                }
                this.mStopCollectInterval = this.mStopIntervalTimes == 0 ? MonitorCommonConstants.SECOND_STOP_INTERVAL : this.mStopIntervalTimes == 1 ? MonitorCommonConstants.THIRD_STOP_INTERVAL : MonitorCommonConstants.LAST_STOP_INTERVAL;
                this.mStopIntervalTimes++;
            }
            return netResponse;
        }
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public long stopMoreChannelInterval() {
        return this.mStopMoreChannelInterval == 0 ? MonitorCommonConstants.LAST_STOP_INTERVAL : this.mStopMoreChannelInterval * 1000;
    }

    synchronized void updateConfig(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (jSONObject.length() > 0) {
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("report_host_new");
                    if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                        int length = jSONArrayOptJSONArray.length();
                        for (int i = 0; i < length; i++) {
                            String string = jSONArrayOptJSONArray.getString(i);
                            if (!TextUtils.isEmpty(string) && string.indexOf(46) > 0) {
                                arrayList.add(string);
                            }
                        }
                    }
                } catch (Exception unused) {
                }
                if (!arrayList.isEmpty()) {
                    this.mReportUrlList = arrayList;
                }
                this.mFetchSettingInterval = jSONObject.optLong("fetch_setting_interval", MonitorConstants.FETCH_SETTING_INTERVAL);
                this.mReportInterval = jSONObject.optInt("polling_interval", 120);
                this.mReportCount = jSONObject.optInt("once_max_count", 100);
                this.mReportFailRepeatCount = jSONObject.optInt("max_retry_count", 4);
                this.mReportFailBaseTime = jSONObject.optInt("report_fail_base_time", 15);
                this.mLogSendSwitch = jSONObject.optInt("log_send_switch", 1);
                this.mStopMoreChannelInterval = jSONObject.optLong("more_channel_stop_interval", MonitorConstants.STOP_MORE_CHANNEL_INTERVAL);
                this.mLogRemoveSwitch = jSONObject.optBoolean("log_remvove_switch", false);
                this.mAllowService = jSONObject.optJSONObject("allow_service_name");
                this.mAllowLogType = jSONObject.optJSONObject("allow_log_type");
                this.mIsEncrypt = jSONObject.optBoolean("monitor_encrypt_switch", true);
                this.mMonitorMaxRowCount = jSONObject.optLong("monitor_log_max_save_count", 2000L);
                this.mDisableReportApiError = jSONObject.optInt("disable_report_error", 0);
                this.mReportSLA = jSONObject.optInt("enable_net_stats", 0);
                this.mBlackReportList = ListUtils.parseList(jSONObject, "api_black_list");
                this.mBlackReportPatterns = ListUtils.parsePatterns(jSONObject, "api_black_list");
                this.mAllowReportList = ListUtils.parseList(jSONObject, "api_allow_list");
                this.mAllowReportPatterns = ListUtils.parsePatterns(jSONObject, "api_allow_list");
                if (this.mLogReportManager == null) {
                    LogReportManager logReportManager = new LogReportManager(this.mContext, this.mAid);
                    this.mLogReportManager = logReportManager;
                    logReportManager.init();
                }
                this.mLogReportManager.updateConfig();
                return;
            }
        }
        this.mReportUrlList = null;
    }

    void updateConfigFromNet(boolean z) {
        if (this.mFetchSettingInterval < 600) {
            this.mFetchSettingInterval = 600L;
        }
        if ((z || needUpdateConfigFromNet()) && MonitorNetUtil.isNetworkAvailable(this.mContext)) {
            synchronized (SDKMonitor.class) {
                this.mLastFetchSettingTime = System.currentTimeMillis();
            }
            try {
                AsyncEventManager.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.5
                    /* JADX WARN: Removed duplicated region for block: B:18:0x0060  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void run() {
                        /*
                            r5 = this;
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r0 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this     // Catch: java.lang.Throwable -> L57
                            java.util.Map r0 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$300(r0)     // Catch: java.lang.Throwable -> L57
                            if (r0 == 0) goto L74
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r0 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this     // Catch: java.lang.Throwable -> L57
                            java.util.List r0 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$400(r0)     // Catch: java.lang.Throwable -> L57
                            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L57
                        L12:
                            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L57
                            if (r1 == 0) goto L74
                            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L57
                            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L57
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r2 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this     // Catch: java.lang.Throwable -> L57
                            java.lang.String r1 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$500(r2, r1)     // Catch: java.lang.Throwable -> L57
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57
                            r2.<init>()     // Catch: java.lang.Throwable -> L57
                            r2.append(r1)     // Catch: java.lang.Throwable -> L57
                            java.lang.String r1 = "&encrypt=close"
                            r2.append(r1)     // Catch: java.lang.Throwable -> L57
                            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L57
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r2 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this     // Catch: java.lang.Throwable -> L52
                            boolean r2 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$600(r2)     // Catch: java.lang.Throwable -> L52
                            r3 = 0
                            byte[] r1 = com.bytedance.framwork.core.sdkmonitor.MonitorNetUtil.getRequest(r1, r3, r2)     // Catch: java.lang.Throwable -> L52
                            if (r1 == 0) goto L74
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r2 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this     // Catch: java.lang.Throwable -> L52
                            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L52
                            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L52
                            r4.<init>(r1)     // Catch: java.lang.Throwable -> L52
                            r3.<init>(r4)     // Catch: java.lang.Throwable -> L52
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$700(r2, r3)     // Catch: java.lang.Throwable -> L52
                            goto L74
                        L52:
                            r1 = move-exception
                            r1.printStackTrace()     // Catch: java.lang.Throwable -> L57
                            goto L12
                        L57:
                            r0 = move-exception
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r1 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this
                            boolean r1 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$100(r1)
                            if (r1 != 0) goto L71
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r1 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this
                            r2 = 1
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$102(r1, r2)
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r1 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this
                            com.bytedance.framwork.core.sdkmonitor.CacheData r1 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.access$200(r1)
                            com.bytedance.framwork.core.sdkmonitor.SDKMonitor r2 = com.bytedance.framwork.core.sdkmonitor.SDKMonitor.this
                            r1.handleCacheData(r2)
                        L71:
                            r0.printStackTrace()
                        L74:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.framwork.core.sdkmonitor.SDKMonitor.AnonymousClass5.run():void");
                    }
                });
            } catch (Throwable unused) {
            }
        }
    }
}
