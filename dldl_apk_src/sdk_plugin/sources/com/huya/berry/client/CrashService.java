package com.huya.berry.client;

import com.duowan.auk.ArkValue;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.crash.ICrashService;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.huya.component.login.api.LoginApi;
import com.huya.live.service.AbsService;
import com.tencent.bugly.crashreport.CrashReport;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CrashService extends AbsService implements ICrashService {
    @Override // com.huya.berry.gamesdk.crash.ICrashService
    public void init() {
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(ArkValue.gContext);
        userStrategy.setAppChannel(SdkProperties.appId.get());
        userStrategy.setAppVersion(AppUtils.getVersion());
        CrashReport.initCrashReport(ArkValue.gContext, "ccd26cafc4", false, userStrategy);
        CrashReport.setUserId(String.valueOf(LoginApi.getLastLoginUid()));
    }

    @Override // com.huya.berry.gamesdk.crash.ICrashService
    public void postCatchedException(Throwable th) {
        CrashReport.postCatchedException(th);
    }
}
