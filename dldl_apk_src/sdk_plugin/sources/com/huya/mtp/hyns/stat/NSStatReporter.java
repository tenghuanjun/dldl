package com.huya.mtp.hyns.stat;

import com.huya.data.MonitorReqData;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.NoAvailableNetworkException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.http.HttpTransporter;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.NSErrorUtil;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSInnerConfig;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.api.NSLaunchApi;
import com.huya.mtp.hyns.api.NSTimeSyncApi;
import com.huya.mtp.hyns.hysignal.HalConfigWrapper;
import com.huya.mtp.hyns.wup.WupFuncApi;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSStatReporter {
    private static final String KEY_RETCODE = "retcode";
    private static final String KEY_SUCCESS = "success";
    public static final String NS_APPID = "appid";
    public static final String NS_COMPAT = "ns_compat";
    public static final String NS_OPERATIONNAME = "operationname";
    public static final String NS_PATH = "path";
    public static final String NS_POLICY_TYPE = "policy_type";
    public static final String NS_RESPONSE_TIME = "response_time";
    public static final String NS_RETCODE = "retcode";
    public static final String NS_SGUID = "sguid";
    public static final String NS_SPANID = "spanid";
    public static final String NS_START_TIME = "start_time";
    public static final String NS_SUCCESS = "success";
    public static final String NS_SUSPEND_TIME = "suspend_time";
    public static final String NS_TRACE_ID = "traceId";
    public static final String NS_VERSION = "ns_version";
    public static final String TAG = "NetService-NSStatReporter";
    private long mBeginTime;
    private MonitorReqData mMonitorReqData;
    private long mServerTime;

    private boolean isNeedReport(long j) {
        return j <= 30000;
    }

    public void setBeginTime() {
        this.mBeginTime = System.currentTimeMillis();
        this.mServerTime = ((NSTimeSyncApi) NS.get(NSTimeSyncApi.class)).getEpochTime();
    }

    public void reportTxApiStatSuccess(NSFunction nSFunction, Transporter<?, ?> transporter, NSResponse nSResponse) {
        if (transporter instanceof HttpTransporter) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            reportTxApiStat(nSFunction, NSStatUtil.getTransportType((HttpTransporter) transporter), 0, nSResponse != null ? nSResponse.getCode() : 0, 0, null, (int) (jCurrentTimeMillis - this.mBeginTime), true, NSStatManager.getInstance().getSuspendDuration(this.mBeginTime, jCurrentTimeMillis));
        }
    }

    public void reportTxApiStatError(NSFunction nSFunction, DataException dataException, Transporter<?, ?> transporter) {
        if (!(transporter instanceof HttpTransporter) || (dataException instanceof NoAvailableNetworkException)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long suspendDuration = NSStatManager.getInstance().getSuspendDuration(this.mBeginTime, jCurrentTimeMillis);
        int i = (int) (jCurrentTimeMillis - this.mBeginTime);
        int transportType = NSStatUtil.getTransportType((HttpTransporter) transporter);
        Throwable throwable = NSErrorUtil.parseThrowable(dataException);
        reportTxApiStat(nSFunction, transportType, NSErrorUtil.parseErrorType(throwable), NSErrorUtil.parseErrorCode(throwable), 0, null, i, true, suspendDuration);
        if (!isNeedReport(suspendDuration) || this.mMonitorReqData == null) {
            return;
        }
        combineToReport(nSFunction);
    }

    public void reportTxApiStat(NSFunction nSFunction, int i, int i2, int i3, int i4, String str, int i5, boolean z, long j) {
        String funcName;
        String str2;
        NSStatData nSStatData;
        int bodyLength = nSFunction.getBodyLength();
        String url = nSFunction.getUrl();
        String reportId = nSFunction.getReportId();
        NSStatData nSStatDataCreate = NSStatManager.getInstance().create();
        if (nSFunction.getNSMethod() instanceof WupFuncApi) {
            String servantName = ((WupFuncApi) nSFunction.getNSMethod()).getServantName();
            funcName = ((WupFuncApi) nSFunction.getNSMethod()).getFuncName();
            str2 = servantName;
        } else {
            funcName = "";
            str2 = funcName;
        }
        String str3 = funcName;
        String str4 = str2;
        NSStatUtil.initStat(bodyLength, url, reportId, str2, funcName, i, i2, i3, i4, str, i5, z, nSStatDataCreate, j);
        if (nSStatDataCreate == null) {
            return;
        }
        if (NSStatUtil.mEnabled && NSStatUtil.shouldReport(nSStatDataCreate) && nSStatDataCreate.responseTime > 0) {
            nSStatData = nSStatDataCreate;
            if (!isNeedReport(nSStatDataCreate.suspendTime)) {
                return;
            }
            String str5 = '/' + str4 + '/' + str3;
            String string = nSFunction.getCacheType() != null ? nSFunction.getCacheType().toString() : "default";
            MonitorReqData monitorReqDataCreateMetricDetail = NSStatUtil.createMetricDetail();
            this.mMonitorReqData = monitorReqDataCreateMetricDetail;
            monitorReqDataCreateMetricDetail.vDimension = NSStatUtil.createApiDimension(nSStatData);
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("path", str5));
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NS_POLICY_TYPE, string));
            String[] strArrSplit = HalConfigWrapper.Builder.getAppSrc().split("&");
            if (strArrSplit.length >= 1) {
                this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("appid", strArrSplit[0]));
            }
            String[] strArrSplit2 = nSFunction.getReportId() != null ? nSFunction.getReportId().split("-") : null;
            if (strArrSplit2 != null && strArrSplit2.length >= 2) {
                this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NS_TRACE_ID, strArrSplit2[0]));
                this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NS_SPANID, strArrSplit2[1]));
            }
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NS_OPERATIONNAME, str3));
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("success", String.valueOf(nSStatData.success)));
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("retcode", String.valueOf(nSStatData.retCode)));
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NS_COMPAT, "0"));
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NS_SGUID, ((NSLaunchApi) NS.get(NSLaunchApi.class)).getGuid()));
            this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("ns_version", "1.9.105-exvolley"));
            this.mMonitorReqData.vExLog.add(new MonitorReqData.DimensionWrapper(NS_SUSPEND_TIME, String.valueOf(nSStatData.suspendTime)));
            this.mMonitorReqData.vField.add(new MonitorReqData.FieldWrapper(NS_RESPONSE_TIME, nSStatData.responseTime));
        } else {
            nSStatData = nSStatDataCreate;
        }
        MTPApi.LOGGER.debug(TAG, "NS request api: %s, success:%d, retCode:%d, time:%d", String.valueOf(nSFunction.getCgi()), Integer.valueOf(nSStatData.success), Integer.valueOf(nSStatData.retCode), Integer.valueOf(nSStatData.responseTime));
        NSStatManager.getInstance().recycle(nSStatData);
    }

    public void reportApiDetail(NSFunction nSFunction, Map<String, Integer> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        if (this.mMonitorReqData == null) {
            this.mMonitorReqData = NSStatUtil.createMetricDetail();
        }
        for (String str : map.keySet()) {
            if (map.get(str).intValue() >= 0) {
                this.mMonitorReqData.vField.add(new MonitorReqData.FieldWrapper(str, r2.intValue()));
            }
        }
        combineToReport(nSFunction);
    }

    private void combineToReport(final NSFunction nSFunction) {
        if (NSInnerConfig.getInstance().isNSStatOpen()) {
            new Runnable() { // from class: com.huya.mtp.hyns.stat.NSStatReporter.1
                @Override // java.lang.Runnable
                public void run() {
                    if (NSStatManager.getInstance().getMonitorData(nSFunction.getReportId()) != null || !NSStatManager.getInstance().waitForMonitoDataCache(nSFunction.getReportId())) {
                        NSStatReporter.this.sendReportData(nSFunction);
                    } else {
                        NSStatManager.getInstance().getReportHandler().postDelayed(this, 2000L);
                    }
                }
            }.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendReportData(NSFunction nSFunction) {
        MonitorReqData monitorData = NSStatManager.getInstance().getMonitorData(nSFunction.getReportId());
        NSStatManager.getInstance().removeMonotorDataFromCache(nSFunction.getReportId());
        if (monitorData != null) {
            if (monitorData.vDimension != null) {
                for (MonitorReqData.DimensionWrapper dimensionWrapper : monitorData.vDimension) {
                    if ("success".equals(dimensionWrapper.sName)) {
                        this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("marssuc", dimensionWrapper.sValue));
                    } else if (!"retcode".equals(dimensionWrapper.sName)) {
                        this.mMonitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(dimensionWrapper.sName, dimensionWrapper.sValue));
                    }
                }
            }
            this.mMonitorReqData.vField.addAll(monitorData.vField);
            MTPApi.LOGGER.debug(TAG, "read reportData from cache: %s", String.valueOf(nSFunction.getCgi()));
        }
        this.mMonitorReqData.vField.add(new MonitorReqData.FieldWrapper(NS_START_TIME, this.mServerTime));
        MTPApi.LOGGER.debug(TAG, "sendReportData for api: %s", String.valueOf(nSFunction.getCgi()));
        MTPApi.MONITOR.request(this.mMonitorReqData);
        if (NSInnerConfig.getInstance().isNSErrorDetectOpen() || NSInnerConfig.getInstance().isTestEnv()) {
            NSDetectNetMgr.getInstance().addNewFailedRequestData(this.mMonitorReqData);
        }
    }
}
