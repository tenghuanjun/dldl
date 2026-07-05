package com.sq.diagnostic.assistant.log;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sq.diagnostic.assistant.http.HttpData;
import com.sq.diagnostic.assistant.http.HttpManager;
import com.sq.diagnostic.assistant.http.OnHttpListener;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.other.ThreadPoolManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AutoUploadManager {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    private static final String TAG = "AutoUploadManager";
    private final Map<Integer, StrategyBean> mAllStrategies = new ConcurrentHashMap();
    private final Handler mUiHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.sq.diagnostic.assistant.log.-$$Lambda$AutoUploadManager$hwkjtEFKBPFoUUX6XAvwYlUeY6c
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f$0.lambda$new$0$AutoUploadManager(message);
        }
    });

    public /* synthetic */ boolean lambda$new$0$AutoUploadManager(Message message) {
        if (!(message.obj instanceof BuglessData)) {
            return true;
        }
        BuglessData buglessData = (BuglessData) message.obj;
        recordBuglessActionTypeAtMain(buglessData.throwable, buglessData.msg, buglessData.data, buglessData.actionType);
        return true;
    }

    public void recordBuglessActionType(Throwable th, String str, String str2, int i) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            recordBuglessActionTypeAtMain(th, str, str2, i);
            return;
        }
        this.mUiHandler.removeMessages(i);
        this.mUiHandler.sendMessage(this.mUiHandler.obtainMessage(i, new BuglessData(th, str, str2, i)));
    }

    private void recordBuglessActionTypeAtMain(final Throwable th, final String str, final String str2, final int i) {
        final StrategyBean strategyBean = this.mAllStrategies.get(Integer.valueOf(i));
        if (strategyBean == null || !strategyBean.isValid() || ignore(strategyBean, th, str, str2)) {
            return;
        }
        strategyBean.recordCount++;
        if (strategyBean.needToReport()) {
            final int i2 = strategyBean.recordCount;
            strategyBean.recordCount = 0;
            ThreadPoolManager.getInstance().execute(new Runnable() { // from class: com.sq.diagnostic.assistant.log.-$$Lambda$AutoUploadManager$yTMfFbPXlM0b4nojzBSHCCIV9z4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$recordBuglessActionTypeAtMain$1$AutoUploadManager(strategyBean, th, i, str2, str, i2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$recordBuglessActionTypeAtMain$1$AutoUploadManager(final StrategyBean strategyBean, Throwable th, int i, String str, String str2, final int i2) {
        SQLogUtils.d(TAG, "诊断助手日志触发策略自动上报: " + strategyBean);
        UploadLogRequest uploadLogRequest = new UploadLogRequest();
        uploadLogRequest.logStartDate = FORMAT.format(new Date());
        uploadLogRequest.logEndDate = uploadLogRequest.logStartDate;
        StringBuilder sb = new StringBuilder();
        sb.append("throwable='");
        sb.append(th != null ? th.getMessage() : "'");
        sb.append(", actionType=");
        sb.append(i);
        sb.append(", data='");
        sb.append(str);
        sb.append('\'');
        sb.append(", msg='");
        sb.append(str2);
        sb.append('\'');
        uploadLogRequest.uploadReason = "触发自动上报策略: " + strategyBean + "; " + sb.toString();
        uploadLogRequest.updateLogType = 1;
        LogManager.getInstance().uploadLog(uploadLogRequest, new ILogCallBack<Void>() { // from class: com.sq.diagnostic.assistant.log.AutoUploadManager.1
            @Override // com.sq.diagnostic.assistant.log.ILogCallBack
            public void onSuccess(Void r2) {
                SQLogUtils.i(AutoUploadManager.TAG, "诊断助手日志自动上报成功");
            }

            @Override // com.sq.diagnostic.assistant.log.ILogCallBack
            public void onFailed(int i3, String str3) {
                SQLogUtils.w(AutoUploadManager.TAG, "诊断助手日志自动上报失败, 错误码: " + i3 + ", 错误信息: " + str3);
                strategyBean.recordCount = i2;
            }
        });
    }

    private boolean ignore(StrategyBean strategyBean, Throwable th, String str, String str2) {
        if (strategyBean.ignores == null) {
            return false;
        }
        for (String str3 : strategyBean.ignores) {
            if (str3 != null && !str3.isEmpty()) {
                if (th != null && th.getMessage() != null && th.getMessage().contains(str3)) {
                    return true;
                }
                if (str != null && str.contains(str3)) {
                    return true;
                }
                if (str2 != null && str2.contains(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void fetchStrategy() {
        HttpManager.requestScheduleTaskConfig(new OnHttpListener<JSONArray>() { // from class: com.sq.diagnostic.assistant.log.AutoUploadManager.2
            @Override // com.sq.diagnostic.assistant.http.OnHttpListener
            public void onSuccess(HttpData<JSONArray> httpData) {
                JSONArray jSONArray = httpData.data;
                if (jSONArray == null) {
                    SQLogUtils.w(AutoUploadManager.TAG, "诊断助手日志上报策略为空, 不会自动上报");
                    return;
                }
                SQLogUtils.i(AutoUploadManager.TAG, "诊断助手日志上报策略: " + jSONArray);
                AutoUploadManager.this.updateStrategy(AutoUploadManager.this.parseSchedule(jSONArray));
            }

            @Override // com.sq.diagnostic.assistant.http.OnHttpListener
            public void onFailed(int i, String str) {
                SQLogUtils.w(AutoUploadManager.TAG, "诊断助手日志上报策略请求失败, 错误码: " + i + ", 错误信息: " + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStrategy(List<ScheduleBean> list) {
        this.mAllStrategies.clear();
        for (ScheduleBean scheduleBean : list) {
            if (scheduleBean.strategies != null && !scheduleBean.strategies.isEmpty()) {
                for (StrategyBean strategyBean : scheduleBean.strategies) {
                    if (!strategyBean.isValid()) {
                        SQLogUtils.w(TAG, "诊断助手日志上报策略无效, 忽略: " + strategyBean);
                    } else if (this.mAllStrategies.containsKey(Integer.valueOf(strategyBean.actionType))) {
                        SQLogUtils.w(TAG, "诊断助手日志actionType上报策略已存在, 忽略: " + strategyBean);
                    } else {
                        this.mAllStrategies.put(Integer.valueOf(strategyBean.actionType), strategyBean);
                    }
                }
            }
        }
        if (this.mAllStrategies.isEmpty()) {
            SQLogUtils.w(TAG, "诊断助手无日志上报策略");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ScheduleBean> parseSchedule(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                if ("schedule_report".equalsIgnoreCase(string)) {
                    ScheduleBean scheduleBean = new ScheduleBean();
                    scheduleBean.type = string;
                    scheduleBean.key = jSONObject.getString("key");
                    scheduleBean.strategies = parseContent(jSONObject.getString("content"));
                    scheduleBean.version = jSONObject.getInt("version");
                    arrayList.add(scheduleBean);
                }
            } catch (JSONException e) {
                SQLogUtils.w(TAG, "诊断助手日志上报策略内容解析失败", e);
            }
        }
        return arrayList;
    }

    private List<StrategyBean> parseContent(String str) throws JSONException {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            StrategyBean strategyBean = new StrategyBean();
            strategyBean.actionType = jSONObject.getInt("action_type");
            strategyBean.happenedCount = jSONObject.getInt("happened_count");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ignores");
            if (jSONArrayOptJSONArray != null) {
                strategyBean.ignores = new ArrayList();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    strategyBean.ignores.add(jSONArrayOptJSONArray.getString(i2));
                }
            }
            arrayList.add(strategyBean);
        }
        return arrayList;
    }

    public static class ScheduleBean {
        public String key;
        public List<StrategyBean> strategies;
        public String type;
        public int version;

        public String toString() {
            return "ScheduleBean{type='" + this.type + "', key='" + this.key + "', strategies=" + this.strategies + ", version=" + this.version + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class StrategyBean {
        public int actionType;
        public int happenedCount;
        public List<String> ignores;
        public int recordCount;

        boolean isValid() {
            return this.happenedCount > 0;
        }

        boolean needToReport() {
            int i = this.happenedCount;
            return i > 0 && this.recordCount == i;
        }

        public String toString() {
            return "StrategyBean{actionType=" + this.actionType + ", happenedCount=" + this.happenedCount + ", ignores=" + this.ignores + AbstractJsonLexerKt.END_OBJ;
        }
    }

    private static class BuglessData {
        final int actionType;
        final String data;
        final String msg;
        final Throwable throwable;

        BuglessData(Throwable th, String str, String str2, int i) {
            this.throwable = th;
            this.actionType = i;
            this.data = str2;
            this.msg = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("BuglessData{throwable='");
            Throwable th = this.throwable;
            sb.append(th != null ? th.getMessage() : "'");
            sb.append(", actionType=");
            sb.append(this.actionType);
            sb.append(", data='");
            sb.append(this.data);
            sb.append('\'');
            sb.append(", msg='");
            sb.append(this.msg);
            sb.append('\'');
            sb.append(AbstractJsonLexerKt.END_OBJ);
            return sb.toString();
        }
    }
}
