package com.huya.component.login.report;

import android.os.Handler;
import android.os.Looper;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.util.NetworkUtil;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginEvent;
import com.huya.component.login.api.LoginInterface;
import com.huya.mtp.utils.NetworkUtils;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginReport {
    private static final String TAG = "LoginReport";
    private Runnable mLoginTimeoutReport;
    private boolean mLogging = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void onstart() {
        SignalCenter.register(this);
    }

    public void onStop() {
        SignalCenter.unregister(this);
    }

    @IASlot(mark = {LoginProperties.MarkLoginState})
    public void onLoginState(PropertySet<LoginProperties.LoginState> propertySet) {
        if (propertySet.newValue == LoginProperties.LoginState.Logining) {
            startTimeoutReportTask();
        } else {
            cancelTimeoutReportTask();
        }
    }

    public LoginReport() {
        Report.event(ReportKey.NEW_STATUS_LOGIN_USER, "", (LoginProperties.loginInfo.isDefault() && LoginProperties.lastLoginOffNetwork.isDefault()) ? "noLogin" : "Login");
        Report.event(ReportKey.STATUS_LOGIN_USER_V3, "", (LoginProperties.loginInfo.isDefault() && LoginProperties.lastLoginOffNetwork.isDefault()) ? "noLogin" : "Login");
    }

    @IASlot
    public void onStartLogin(LoginInterface.Login login) {
        L.debug(TAG, "onStartLogin, %s", login.loginInfo.account);
        this.mLogging = true;
    }

    @IASlot
    public void onLoginFail(LoginEvent.LoginFail loginFail) {
        L.debug(TAG, "onLoginFail, reason: %s", loginFail.reason.toString());
        this.mLogging = false;
    }

    private void startTimeoutReportTask() {
        cancelTimeoutReportTask();
        Runnable runnable = new Runnable() { // from class: com.huya.component.login.report.LoginReport.1
            @Override // java.lang.Runnable
            public void run() {
                if (NetworkUtils.isNetworkAvailable()) {
                    LoginReport.this.reportLoginEx("no_event_timeout");
                }
            }
        };
        this.mLoginTimeoutReport = runnable;
        this.mHandler.postDelayed(runnable, TimeUnit.SECONDS.toMillis(getLoginTimeoutInterval()));
    }

    private int getLoginTimeoutInterval() {
        L.info(TAG, "login timeout interval: %d", 30);
        return 30;
    }

    private void cancelTimeoutReportTask() {
        Runnable runnable = this.mLoginTimeoutReport;
        if (runnable == null) {
            return;
        }
        this.mHandler.removeCallbacks(runnable);
        this.mLoginTimeoutReport = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportLoginEx(String str) {
        Report.event("login_ex", "login_ex", str);
        if (NetworkUtil.isWifiActive(ArkValue.gContext)) {
            Report.event(ReportKey.LoginUnifyWifi, str);
            if (this.mLogging) {
                Report.event(ReportKey.LoginUnifySelfWifi, ReportKey.LoginUnifySelfWifi, str);
                return;
            }
            return;
        }
        Report.event(ReportKey.LoginUnifyNotWifi, ReportKey.LoginUnifyNotWifi, str);
        if (this.mLogging) {
            Report.event(ReportKey.LoginUnifySelfNotWifi, ReportKey.LoginUnifySelfNotWifi, str);
            String netWorkSubType = NetworkUtil.getNetWorkSubType(ArkValue.gContext);
            if (netWorkSubType.equals("2G")) {
                Report.event(ReportKey.LoginUnifySelf2G, ReportKey.LoginUnifySelf2G, str);
                return;
            }
            if (!netWorkSubType.equals("unknown")) {
                Report.event(ReportKey.LoginUnifySelf3GAND4G, ReportKey.LoginUnifySelf3GAND4G, str);
                if (netWorkSubType.equals("3G")) {
                    Report.event(ReportKey.LoginUnifySelf3G, ReportKey.LoginUnifySelf3G, str);
                    return;
                } else {
                    if (netWorkSubType.equals("4G")) {
                        Report.event(ReportKey.LoginUnifySelf4G, ReportKey.LoginUnifySelf4G, str);
                        return;
                    }
                    return;
                }
            }
            Report.event(ReportKey.LoginUnifySelfUnknown, ReportKey.LoginUnifySelfUnknown, str);
        }
    }
}
