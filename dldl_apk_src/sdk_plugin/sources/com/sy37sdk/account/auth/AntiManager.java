package com.sy37sdk.account.auth;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.TimeTools;
import com.sqwan.common.util.task.Task;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.auth.AuthDialog;
import com.sy37sdk.account.policy.PolicyManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AntiManager {
    private static AntiManager sInstance;
    private Context mContext;
    private AccountRequestManager requestManager;
    private int currentTimeTick = 0;
    private boolean isReporting = false;
    private Task reportTask = Task.create();

    /* JADX INFO: Access modifiers changed from: private */
    public boolean testForceStopReportUserDuration() throws JSONException {
        return false;
    }

    public static AntiManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AntiManager.class) {
                if (sInstance == null) {
                    sInstance = new AntiManager(context);
                }
            }
        }
        return sInstance;
    }

    private AntiManager(Context context) {
        this.mContext = context;
        this.requestManager = new AccountRequestManager(context);
    }

    public void report() {
        LogUtil.i("上报心跳");
        startReport();
    }

    private void startReport() {
        if (this.isReporting) {
            return;
        }
        SQLog.i("启动防沉迷上报");
        this.reportTask.repeat(0L, AuthConfigCache.getInterval() * 60 * 1000, new Task.TaskFunc() { // from class: com.sy37sdk.account.auth.AntiManager.1
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                StringBuilder sb = new StringBuilder();
                sb.append("reportAnti currentTimeTick=");
                sb.append(AntiManager.this.currentTimeTick);
                sb.append(", interval=");
                sb.append(AuthConfigCache.getInterval());
                sb.append(", 当前时间：");
                sb.append(TimeTools.stampToDate(System.currentTimeMillis() + ""));
                SQLog.v(sb.toString());
                AntiManager.this.reportAnti();
                return null;
            }
        });
        this.isReporting = true;
    }

    public void stopReport() {
        SQLog.w("停止防沉迷上报");
        if (this.isReporting) {
            this.reportTask.stop();
            this.isReporting = false;
        }
    }

    private void showAntiDialog(String str, boolean z) {
        LogUtil.i("显示防沉迷弹窗，focus: " + z + ", url:" + str);
        AuthDialog authDialog = new AuthDialog(this.mContext);
        authDialog.setFocus(z);
        authDialog.setUrl(AppUtils.constructWebUrlParam(this.mContext, str));
        authDialog.setCloseListener(new AuthDialog.CloseListener() { // from class: com.sy37sdk.account.auth.AntiManager.2
            @Override // com.sy37sdk.account.auth.AuthDialog.CloseListener
            public void onClose(String str2, String str3) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                if (str2.equals("0") || str2.equals("exitGame")) {
                    AntiManager.this.stopReport();
                    LogUtil.i("退出游戏");
                    ((Activity) AntiManager.this.mContext).finish();
                    System.exit(0);
                }
            }
        });
        authDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAnti() {
        this.requestManager.reportUserDuration(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.auth.AntiManager.3
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                SQLog.e("轮询上报接口失败 msg:" + str);
                PolicyManager.getInstance().handleReportGuarantee(AntiManager.this.mContext);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                if (AntiManager.this.isReporting) {
                    SQLog.v("轮询上报接口: " + jSONObject);
                    try {
                        if (AntiManager.this.testForceStopReportUserDuration()) {
                            return;
                        }
                        PopConfig fromJson = PopConfig.parseFromJson(jSONObject);
                        if (fromJson.getTimestamp() == 0) {
                            SQLog.w("处理reportUser服务端请求服务超时");
                            PolicyManager.getInstance().handleReportGuarantee(AntiManager.this.mContext);
                        } else if (!TextUtils.isEmpty(fromJson.getUrl()) && fromJson.isShow()) {
                            SQLog.w("心跳接口判断要弹窗");
                            PolicyManager.getInstance().showPolicyDialog(AntiManager.this.mContext, fromJson.getUrl(), fromJson.isFocus());
                        } else {
                            SQLog.v("心跳接口判断是否在时间段内");
                            PolicyManager.getInstance().handleTimeLimit(AntiManager.this.mContext, fromJson.getTimestamp() * 1000, fromJson.isFocus());
                        }
                    } catch (JSONException e) {
                        SQLog.w("心跳接口解析异常", e);
                        PolicyManager.getInstance().handleReportGuarantee(AntiManager.this.mContext);
                    }
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                SQLog.e("轮询上报接口异常 errorMsg:" + str);
                PolicyManager.getInstance().handleReportGuarantee(AntiManager.this.mContext);
            }
        });
    }

    public void reset() {
        stopReport();
        this.currentTimeTick = 0;
    }
}
