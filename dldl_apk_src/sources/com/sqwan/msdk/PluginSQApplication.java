package com.sqwan.msdk;

import android.app.Application;
import android.content.Context;
import com.plugin.core.Plugin;
import com.plugin.core.tool.PluginLog;
import com.plugin.standard.BaseApplication;
import com.sqwan.msdk.api.SQAppApi;
import com.sqwan.msdk.api.SQMediaReportInterface;
import com.sqwan.msdk.api.SQReportInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PluginSQApplication extends BaseApplication implements SQAppApi {
    final String TAG = "【" + getClass().getSimpleName() + "】";
    private SQAppApi mAppFromPlugin;

    @Override // com.plugin.standard.BaseApplication, com.plugin.standard.IApplicationInterface
    public void insertAppContext(Application application) {
        PluginLog.i(this.TAG + "insertAppContext: " + application);
        super.insertAppContext(application);
        try {
            if (this.mAppFromPlugin != null) {
                this.mAppFromPlugin.insertAppContext(application);
            }
        } catch (Exception e) {
            PluginLog.e(this.TAG + "注入context异常", e);
        }
    }

    @Override // com.plugin.standard.BaseApplication, com.plugin.standard.IApplicationInterface
    public void onCreate() {
        super.onCreate();
        PluginLog.i(this.TAG + "onCreate");
        SQAppApi sQAppApi = this.mAppFromPlugin;
        if (sQAppApi != null) {
            sQAppApi.onCreate();
        }
    }

    @Override // com.plugin.standard.BaseApplication, com.plugin.standard.IApplicationInterface
    public void attachBaseContext(Context context) {
        PluginLog.i(this.TAG + "attachBaseContext: " + context);
        super.attachBaseContext(context);
        Plugin pluginLoad = PluginLoader.getInstance().load(context);
        if (pluginLoad != null) {
            try {
                this.mAppFromPlugin = (SQAppApi) pluginLoad.mClassLoader.loadClass("com.sqwan.msdk.SQApplicationImpl").newInstance();
                PluginLog.d(this.TAG + "反射创建插件中的Application实例: " + this.mAppFromPlugin);
                this.mAppFromPlugin.attachBaseContext(context);
            } catch (Exception e) {
                PluginLog.e(this.TAG + "反射创建插件中的Application实例异常", e);
            }
        }
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void setReporter(SQReportInterface sQReportInterface) {
        PluginLog.d(this.TAG + "setReporter " + sQReportInterface);
        SQAppApi sQAppApi = this.mAppFromPlugin;
        if (sQAppApi != null) {
            sQAppApi.setReporter(sQReportInterface);
        }
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void setMediaReporter(SQMediaReportInterface sQMediaReportInterface) {
        PluginLog.d(this.TAG + "setMediaReporter " + sQMediaReportInterface);
        SQAppApi sQAppApi = this.mAppFromPlugin;
        if (sQAppApi != null) {
            sQAppApi.setMediaReporter(sQMediaReportInterface);
        }
    }
}
