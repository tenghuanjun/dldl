package com.sq.diagnostic.assistant;

import android.content.Context;
import com.sq.diagnostic.assistant.http.HttpData;
import com.sq.diagnostic.assistant.http.HttpManager;
import com.sq.diagnostic.assistant.http.OnHttpListener;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.AutoUploadManager;
import com.sq.diagnostic.assistant.log.ILogCallBack;
import com.sq.diagnostic.assistant.log.LogManager;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import com.sq.diagnostic.assistant.other.HttpNetworkConfigManager;
import com.sq.diagnostic.assistant.other.IBusinessParameters;
import com.sq.diagnostic.assistant.other.LogConfig;
import com.sq.diagnostic.assistant.ui.AssistantEntranceDialog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class DiagnosticAssistant {
    public static final String SERVER_HOST_CHINA = "http://bugless.shan-yu-tech.com/";
    public static final String SERVER_HOST_OVERSEAS = "http://api.bugless-tx.com/";
    public static final String TAG = "DiagnosticAssistant";
    private static volatile DiagnosticAssistant sInstance;
    private IBusinessParameters mBusinessParameters;
    private CallBack mCallBack;
    private boolean mInitSuccess;
    private LogConfig mLogConfig;
    private String mServerHost = SERVER_HOST_CHINA;
    private final List<HttpNetworkConfigManager.TcpingBean> mDefaultTcpingList = new ArrayList();
    private final List<HttpNetworkConfigManager.MtrBean> mDefaultMtrList = new ArrayList();
    private final AutoUploadManager mAutoUploadManager = new AutoUploadManager();

    public interface CallBack {
        void onHomePageClose();

        void onHomePageShow();

        void onLogRetrievalFail(int i, String str);

        void onLogRetrievalSuccess();

        void onUpdateLogFail(int i, String str);

        void onUpdateLogSuccess();
    }

    public static DiagnosticAssistant getInstance() {
        if (sInstance == null) {
            synchronized (DiagnosticAssistant.class) {
                if (sInstance == null) {
                    sInstance = new DiagnosticAssistant();
                }
            }
        }
        return sInstance;
    }

    private DiagnosticAssistant() {
    }

    public DiagnosticAssistant setLogConfig(LogConfig logConfig) {
        this.mLogConfig = logConfig;
        return this;
    }

    public LogConfig getLogConfig() {
        return this.mLogConfig;
    }

    public DiagnosticAssistant setServerHost(String str) {
        this.mServerHost = str;
        return this;
    }

    public String getServerHost() {
        return this.mServerHost;
    }

    public DiagnosticAssistant setBusinessParameters(IBusinessParameters iBusinessParameters) {
        this.mBusinessParameters = iBusinessParameters;
        return this;
    }

    public IBusinessParameters getBusinessParameters() {
        return this.mBusinessParameters;
    }

    public DiagnosticAssistant addDefaultTcpingConfig(String str, int i) {
        return addDefaultTcpingConfig(new HttpNetworkConfigManager.TcpingBean(str, i));
    }

    public DiagnosticAssistant addDefaultTcpingConfig(HttpNetworkConfigManager.TcpingBean tcpingBean) {
        if (!this.mDefaultTcpingList.contains(tcpingBean)) {
            this.mDefaultTcpingList.add(tcpingBean);
        }
        return this;
    }

    public DiagnosticAssistant addDefaultMtrConfig(String str, int i) {
        return addDefaultMtrConfig(new HttpNetworkConfigManager.MtrBean(str, i));
    }

    public DiagnosticAssistant addDefaultMtrConfig(HttpNetworkConfigManager.MtrBean mtrBean) {
        if (!this.mDefaultMtrList.contains(mtrBean)) {
            this.mDefaultMtrList.add(mtrBean);
        }
        return this;
    }

    public List<HttpNetworkConfigManager.TcpingBean> getDefaultTcpingList() {
        return this.mDefaultTcpingList;
    }

    public List<HttpNetworkConfigManager.MtrBean> getDefaultMtrList() {
        return this.mDefaultMtrList;
    }

    public DiagnosticAssistant setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
        return this;
    }

    public CallBack getCallBack() {
        return this.mCallBack;
    }

    public boolean isInitSuccess() {
        return this.mInitSuccess;
    }

    public void init(Context context) {
        if (this.mInitSuccess) {
            return;
        }
        boolean zInit = LogManager.getInstance().init(context, this.mLogConfig);
        this.mInitSuccess = zInit;
        if (zInit) {
            this.mAutoUploadManager.fetchStrategy();
        }
    }

    public static void show(Context context) {
        try {
            new AssistantEntranceDialog.Builder(context).show();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void logRetrieval() {
        if (!getInstance().isInitSuccess()) {
            SQLogUtils.w(TAG, "诊断助手初始化失败，无法对日志回捞进行检测");
        } else {
            HttpManager.checkLogRetrievalTask(new OnHttpListener<JSONObject>() { // from class: com.sq.diagnostic.assistant.DiagnosticAssistant.1
                @Override // com.sq.diagnostic.assistant.http.OnHttpListener
                public void onSuccess(HttpData<JSONObject> httpData) {
                    JSONObject data = httpData.getData();
                    if (!data.optBoolean("sdk_log_task", false)) {
                        SQLogUtils.i(DiagnosticAssistant.TAG, "没有日志回捞的请求，无需进行日志回捞");
                        return;
                    }
                    SQLogUtils.i(DiagnosticAssistant.TAG, "触发了日志回捞的请求");
                    String strOptString = data.optString("log_start_date");
                    String strOptString2 = data.optString("log_end_date");
                    String strOptString3 = data.optString("content");
                    UploadLogRequest uploadLogRequest = new UploadLogRequest();
                    uploadLogRequest.logStartDate = strOptString;
                    uploadLogRequest.logEndDate = strOptString2;
                    uploadLogRequest.uploadReason = strOptString3;
                    uploadLogRequest.updateLogType = 1;
                    SQLogUtils.i(DiagnosticAssistant.TAG, "日志回捞的日期范围：" + uploadLogRequest.logStartDate + "-" + uploadLogRequest.logEndDate);
                    StringBuilder sb = new StringBuilder();
                    sb.append("触发日志回捞的原因：");
                    sb.append(uploadLogRequest.uploadReason);
                    SQLogUtils.i(DiagnosticAssistant.TAG, sb.toString());
                    if (!LogManager.getInstance().uploadLog(uploadLogRequest, new ILogCallBack<Void>() { // from class: com.sq.diagnostic.assistant.DiagnosticAssistant.1.1
                        @Override // com.sq.diagnostic.assistant.log.ILogCallBack
                        public void onSuccess(Void r2) {
                            SQLogUtils.i(DiagnosticAssistant.TAG, "日志回捞成功");
                            CallBack callBack = DiagnosticAssistant.getInstance().getCallBack();
                            if (callBack != null) {
                                callBack.onLogRetrievalSuccess();
                            }
                        }

                        @Override // com.sq.diagnostic.assistant.log.ILogCallBack
                        public void onFailed(int i, String str) {
                            SQLogUtils.i(DiagnosticAssistant.TAG, "日志回捞失败，原因如下：" + str);
                            CallBack callBack = DiagnosticAssistant.getInstance().getCallBack();
                            if (callBack != null) {
                                callBack.onLogRetrievalFail(i, str);
                            }
                        }
                    })) {
                        SQLogUtils.i(DiagnosticAssistant.TAG, "日志回捞失败，可能是你当前的 SDK 版本太旧，请尝试升级游戏版本后再试");
                    }
                    SQLogUtils.i(DiagnosticAssistant.TAG, "日志回捞结束");
                }

                @Override // com.sq.diagnostic.assistant.http.OnHttpListener
                public void onFailed(int i, String str) {
                    SQLogUtils.i(DiagnosticAssistant.TAG, "诊断助手日志回捞失败，错误码：" + i + "，错误信息：" + str);
                }
            });
        }
    }

    public void recordBuglessActionType(Throwable th, String str, String str2, int i) {
        if (!getInstance().isInitSuccess()) {
            SQLogUtils.w(TAG, "诊断助手初始化失败，无法记录Bugless上报");
        } else {
            this.mAutoUploadManager.recordBuglessActionType(th, str, str2, i);
        }
    }
}
