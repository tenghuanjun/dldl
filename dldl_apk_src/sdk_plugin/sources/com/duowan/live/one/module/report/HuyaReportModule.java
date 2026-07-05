package com.duowan.live.one.module.report;

import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.ReportInterface;
import com.huya.live.common.api.BaseApi;
import com.huya.mtp.utils.StringUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaReportModule {
    public static final String KEY_PRODUCT = "KEY_PRODUCT";
    public static final String KeyReportSdkChannel = "channel";
    public static final String KeyReportSdkKey = "hiidoKey";
    private static final String TAG = "HuyaReportModule";

    public void init(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str3)) {
            str3 = ArkValue.channelName();
        }
        String str5 = str3;
        L.debug(TAG, "appkey: %s", str);
        if (StringUtils.isNullOrEmpty(str) || StringUtils.isNullOrEmpty(str5)) {
            BaseApi.crashIfDebug("app key or channel name not valid!", new Object[0]);
        }
        HuyaStatisAgent.getInstance().init(ArkValue.gContext, str, ArkValue.gContext.getPackageName(), str5, str4, str2);
    }

    public void onLoginSuccess(ReportInterface.LoginSuccess loginSuccess) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().login();
        HuyaStatisAgent.getInstance().getHuyaStatisApi().setYyUid(Long.valueOf(loginSuccess.uid));
    }

    public void onReportGuid(ReportInterface.ReportGuid reportGuid) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().setGuid(reportGuid.guid);
    }

    public void onReportEvent(ReportInterface.ReportEvent reportEvent) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().reportEvent(reportEvent.id, reportEvent.desc, reportEvent.label, reportEvent.prop, reportEvent.content);
    }

    public void onReportEvent(ReportInterface.ReportHuyaEvent reportHuyaEvent) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().reportEvent(reportHuyaEvent.eid, reportHuyaEvent.eDes, null, null, null);
    }

    public void onRepotStartLive(ReportInterface.StartLive startLive) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().startLive(startLive.mGameId, startLive.mLiveId, startLive.mChannelId, startLive.mAppVersion);
    }

    public void onRepotStopLive(ReportInterface.StopLive stopLive) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().stopLive();
    }

    public void onChangeSessionID(ReportInterface.ChangeHuyaSessionId changeHuyaSessionId) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().changeSessionId();
    }

    public void stopReport(String str, String str2) {
        HuyaStatisAgent.getInstance().getHuyaStatisApi().reportEvent(str, str2);
    }
}
