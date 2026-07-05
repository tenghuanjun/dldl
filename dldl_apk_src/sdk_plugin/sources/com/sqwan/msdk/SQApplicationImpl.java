package com.sqwan.msdk;

import android.content.Context;
import android.util.Log;
import com.plugin.standard.BaseApplication;
import com.sq.oaid.sq_oaid.SqOAIDHelper;
import com.sq.sdk.tool.util.SpUtils;
import com.sq.tool.logger.AndroidLogAdapter;
import com.sq.tool.logger.SQLog;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.data.cache.SpRequestInfo;
import com.sqwan.common.track.SqTrackActionManager;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.SQAppApi;
import com.sqwan.msdk.api.SQMediaReportInterface;
import com.sqwan.msdk.api.SQReportInterface;
import com.sqwan.msdk.config.MultiSdkManager;
import com.sqwan.msdk.utils.SqAtyLifecycle;
import com.sy37sdk.account.binding.GameBindingManager;
import com.sy37sdk.utils.Util;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQApplicationImpl extends BaseApplication implements SQAppApi {
    final String TAG = "【" + getClass().getSimpleName() + "】";

    @Override // com.sqwan.msdk.api.SQAppApi
    public void onCreate() {
        super.onCreate();
        SQLog.replaceLogAdapter(new AndroidLogAdapter("sqsdk") { // from class: com.sqwan.msdk.SQApplicationImpl.1
            @Override // com.sq.tool.logger.AndroidLogAdapter, com.sq.tool.logger.LogAdapter
            public boolean isLoggable(int i, String str) {
                return Util.isLogPrintEnable();
            }
        }, AndroidLogAdapter.class);
        SQLog.i(this.TAG + "onCreate, " + getClass().getClassLoader());
        SQContextWrapper.init(getApplicationContext());
        SpUtils.getInstance().setContext(getApplicationContext());
        SpRequestInfo.setRequestLiveId(getApplicationContext());
        SQReportCore.getInstance().init(getApplicationContext());
        BuglessAction.init(getApplicationContext());
        ActivityLifeCycleUtils.getInstance().init(getApplicationContext());
        SqAtyLifecycle.onApplication(getApplicationContext());
        MultiSDKUtils.setLogined(getApplicationContext(), false);
        SqTrackActionManager.getInstance().init(getApplicationContext(), VersionUtil.sdkVersion);
        SqTrackActionManager2.getInstance().init(getApplicationContext());
        GameBindingManager.getInstance().init(getApplicationContext());
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void setReporter(SQReportInterface sQReportInterface) {
        SQReportCore.getInstance().setReporter(sQReportInterface);
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void setMediaReporter(SQMediaReportInterface sQMediaReportInterface) {
        SQReportCore.getInstance().setMediaReporter(sQMediaReportInterface);
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void attachBaseContext(Context context) throws Throwable {
        super.attachBaseContext(context);
        Log.i("sqsdk", this.TAG + "attachBaseContext: " + context);
        MultiSdkManager.getInstance().initMultiSdk(context);
        SQContextWrapper.init(context);
        initOAIDLib(context);
    }

    private void initOAIDLib(Context context) {
        try {
            Properties propertites = MultiSDKUtils.readPropertites(context, MultiSdkManager.getInstance().getInfo());
            if (propertites != null) {
                String strReplace = (propertites.getProperty("sdkverion") == null ? "" : propertites.getProperty("sdkverion")).replace(FileUtil.FILE_EXTENSION_SEPARATOR, "");
                if (strReplace.length() > 4) {
                    strReplace = strReplace.substring(0, 4);
                }
                if (Integer.parseInt(strReplace) >= 3791) {
                    SqOAIDHelper.init();
                    return;
                }
                Log.e("sqsdk", this.TAG + "attachBaseContext: initOAIDLib 老版本不可以用新库获取OAID");
            }
        } catch (Exception e) {
            Log.e("sqsdk", this.TAG + "attachBaseContext: initOAIDLib " + e.getMessage());
        }
    }
}
