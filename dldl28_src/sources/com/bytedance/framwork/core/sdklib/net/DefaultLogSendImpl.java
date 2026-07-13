package com.bytedance.framwork.core.sdklib.net;

import android.content.Context;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.bytedance.framwork.core.sdklib.config.MonitorConfigure;
import com.bytedance.framwork.core.sdklog.LogHandler;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.volcengine.common.contant.CommonConstants;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultLogSendImpl implements ILogSendImpl {
    String mAid;
    Context mContext;
    LogHandler mLogHandler;
    String mLogType;
    boolean mMoreChannelSwitch;
    int mNetFailCount;
    int mStatusCode;
    volatile long mStopInterval;

    public DefaultLogSendImpl(Context context, final String str) {
        this.mAid = str;
        this.mContext = context;
        this.mLogHandler = new LogHandler(context.getApplicationContext(), new LogHandler.BaseConfig() { // from class: com.bytedance.framwork.core.sdklib.net.DefaultLogSendImpl.1
            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IConfig
            public List<String> getChannels() {
                return MonitorConfigure.getReportUrl(str, MonitorConstants.REPORT_TYPE);
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IConfig
            public String getLogType() {
                return str + MonitorConstants.REPORT_TYPE;
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.BaseConfig, com.bytedance.framwork.core.sdklog.LogHandler.IConfig
            public int getMaxRetryCount() {
                return MonitorConfigure.getReportFailRepeatCount(str);
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.BaseConfig, com.bytedance.framwork.core.sdklog.LogHandler.IConfig
            public long getRetryInterval() {
                return MonitorConfigure.getReportFailBaseTime(str);
            }
        }, new LogHandler.IResponseConfig() { // from class: com.bytedance.framwork.core.sdklib.net.DefaultLogSendImpl.2
            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IResponseConfig
            public boolean getMoreChannelSwitch() {
                return DefaultLogSendImpl.this.mMoreChannelSwitch;
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IResponseConfig
            public boolean getRemoveSwitch() {
                return MonitorConfigure.getLogRemoveSwitch(str);
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IResponseConfig
            public int getStatusCode() {
                return DefaultLogSendImpl.this.mStatusCode;
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IResponseConfig
            public long getStopInterval() {
                return DefaultLogSendImpl.this.mStopInterval;
            }

            @Override // com.bytedance.framwork.core.sdklog.LogHandler.IResponseConfig
            public long getStopMoreChannelInterval() {
                return MonitorConfigure.getStopMoreChannelInterval(str);
            }
        }) { // from class: com.bytedance.framwork.core.sdklib.net.DefaultLogSendImpl.3
            @Override // com.bytedance.framwork.core.sdklog.LogHandler
            protected boolean send(String str2, byte[] bArr) {
                int i;
                long j;
                JSONObject jSONObject;
                if (MonitorLogSender.getISendLog(str) != null) {
                    NetResponse netResponseSendLog = MonitorLogSender.getISendLog(str).sendLog(NetConst.MAX_LENGTH, str2, bArr, 1, NetConst.CONTENT_TYPE);
                    if (netResponseSendLog == null || (i = netResponseSendLog.stateCode) <= 0) {
                        DefaultLogSendImpl.this.mMoreChannelSwitch = true;
                    } else {
                        DefaultLogSendImpl.this.mMoreChannelSwitch = false;
                        if (i == 200 && (jSONObject = netResponseSendLog.responseMsg) != null) {
                            if (jSONObject.optInt("is_crash", 0) == 1) {
                                DefaultLogSendImpl.this.mStopInterval = MonitorCommonConstants.LAST_STOP_INTERVAL;
                                DefaultLogSendImpl.this.mNetFailCount = 3;
                                return false;
                            }
                            if (netResponseSendLog.responseMsg.opt(CommonConstants.KEY_MESSAGE).equals("success")) {
                                DefaultLogSendImpl defaultLogSendImpl = DefaultLogSendImpl.this;
                                defaultLogSendImpl.mNetFailCount = 0;
                                defaultLogSendImpl.mStopInterval = 0L;
                                return true;
                            }
                        }
                        int i2 = netResponseSendLog.stateCode;
                        if (500 <= i2 && i2 <= 600) {
                            DefaultLogSendImpl defaultLogSendImpl2 = DefaultLogSendImpl.this;
                            int i3 = defaultLogSendImpl2.mNetFailCount;
                            if (i3 == 0) {
                                j = MonitorCommonConstants.SECOND_STOP_INTERVAL;
                            } else {
                                if (i3 != 1) {
                                    defaultLogSendImpl2.mStopInterval = MonitorCommonConstants.LAST_STOP_INTERVAL;
                                    DefaultLogSendImpl.this.mNetFailCount++;
                                    return false;
                                }
                                j = MonitorCommonConstants.THIRD_STOP_INTERVAL;
                            }
                            defaultLogSendImpl2.mStopInterval = j;
                            DefaultLogSendImpl.this.mNetFailCount++;
                            return false;
                        }
                    }
                }
                return false;
            }
        };
    }

    @Override // com.bytedance.framwork.core.sdklib.net.ILogSendImpl
    public boolean logStopCollectSwitch() {
        return this.mStopInterval == MonitorCommonConstants.LAST_STOP_INTERVAL;
    }

    @Override // com.bytedance.framwork.core.sdklib.net.ILogSendImpl
    public boolean send(String str) {
        return this.mLogHandler.enqueue(str);
    }
}
