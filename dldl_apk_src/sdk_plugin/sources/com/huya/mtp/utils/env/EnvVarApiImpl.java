package com.huya.mtp.utils.env;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.huya.mtp.api.EnvVarApi;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.utils.Config;
import com.huya.mtp.utils.ResourceUtils;
import com.huya.mtp.utils.Utils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EnvVarApiImpl implements EnvVarApi {
    private static final int CONFIG_IS_TEST_ENV_DEFAULT = -1;
    private static final String CONFIG_IS_TEST_ENV_KEY = "ark_is_test_env";
    private static final String DEBUGGABLE_KEY = "constant_debuggable";
    private static final String FORCE_CLOSE_DEBUGGABLE_KEY = "force_close_debuggable";
    private static final String FORCE_TEST_ENV_KEY = "force_test_env";
    private static final String FORCE_TRUNK_AS_SNAPSHOT_KEY = "trunk_as_snapshot";
    private static final String TAG = "EnvVarApiImpl";
    private static EnvVarApiImpl sInstance = new EnvVarApiImpl();
    private String gChannelName;
    private int sHotfixVersion;
    private int sVersionCode;
    private String sVersionName;
    private boolean gIsForceCloseDebuggable = false;
    private boolean gIsDebuggable = false;
    private boolean gIsTestEnv = false;
    private boolean gIsTestEnvByCfg = false;
    private boolean gIsLocalBuild = false;
    private boolean gIsSnapshot = false;
    private boolean gIsPreRelease = false;
    private EnvExtConfig gExtConfig = null;

    public static EnvVarApiImpl getsInstance() {
        return sInstance;
    }

    private EnvVarApiImpl() {
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public String getVersionName() {
        return this.sVersionName;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public int getVersionCode() {
        return this.sVersionCode;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public int getHotFixVersionCode() {
        return this.sHotfixVersion;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public String getChannelName() {
        return this.gChannelName;
    }

    public EnvVarApiImpl init(EnvVarParams envVarParams) {
        Application application = MTPApi.CONTEXT.getApplication();
        String processName = Utils.getProcessName(application);
        EnvExtConfig envExtConfig = new EnvExtConfig(envVarParams.extCfgPath);
        this.gExtConfig = envExtConfig;
        if (envExtConfig.exists()) {
            try {
                if (!TextUtils.isEmpty(processName) && !processName.contains(":")) {
                    if (25 != Build.VERSION.SDK_INT) {
                        Toast.makeText(application, "ark.config is exists", 0).show();
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ark.config is exists for ");
                    if (TextUtils.isEmpty(processName)) {
                        processName = "unknown";
                    }
                    sb.append(processName);
                    Log.i("ark.config", sb.toString());
                }
            } catch (Exception e) {
                Log.e(TAG, "[ArkValue]show toast occurred error", e);
            }
        }
        initDebugValue(application, envVarParams);
        initCommonValue(application, envVarParams);
        initBuildType();
        initTrunkValue(application);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initCommonValue(android.content.Context r4, com.huya.mtp.utils.env.EnvVarParams r5) {
        /*
            r3 = this;
            java.lang.String r0 = "official"
            java.lang.String r1 = r5.channelName
            if (r1 == 0) goto Lb
            java.lang.String r0 = r5.channelName
            r3.gChannelName = r0
            goto L2b
        Lb:
            android.content.pm.ApplicationInfo r1 = r4.getApplicationInfo()     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            java.lang.String r1 = r1.sourceDir     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            java.lang.String r1 = com.mcxiaoke.packer.common.PackerCommon.readChannel(r2)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r3.gChannelName = r1     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            if (r1 != 0) goto L2b
            goto L29
        L1f:
            r4 = move-exception
            goto L5a
        L21:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L1f
            java.lang.String r1 = r3.gChannelName
            if (r1 != 0) goto L2b
        L29:
            r3.gChannelName = r0
        L2b:
            java.lang.String r0 = r4.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            r1 = 0
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r0, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            int r0 = r4.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            r3.sVersionCode = r0     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            java.lang.String r4 = r4.versionName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            r3.sVersionName = r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41
            goto L45
        L41:
            r4 = move-exception
            r4.printStackTrace()
        L45:
            java.lang.String r4 = r3.sVersionName
            if (r4 == 0) goto L51
            java.lang.String r0 = "-SNAPSHOT"
            boolean r4 = r4.contains(r0)
            r3.gIsSnapshot = r4
        L51:
            int r4 = r5.hotFixVersionCode
            if (r4 <= 0) goto L59
            int r4 = r5.hotFixVersionCode
            r3.sHotfixVersion = r4
        L59:
            return
        L5a:
            java.lang.String r5 = r3.gChannelName
            if (r5 != 0) goto L60
            r3.gChannelName = r0
        L60:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.env.EnvVarApiImpl.initCommonValue(android.content.Context, com.huya.mtp.utils.env.EnvVarParams):void");
    }

    private void initTrunkValue(Context context) {
        if (this.gIsSnapshot && "1".equals(ResourceUtils.getMetaValue(context, "IS_PRE_RELEASE", ""))) {
            this.gIsPreRelease = true;
        }
    }

    private boolean isJenkinsBuild() {
        return this.sVersionCode > 0;
    }

    public boolean isLocalBuild() {
        return !isJenkinsBuild();
    }

    private void initBuildType() {
        this.gIsLocalBuild = !isJenkinsBuild();
    }

    private void initDebugValue(Context context, EnvVarParams envVarParams) {
        this.gIsDebuggable = Utils.isDebugMode(context);
        if (envVarParams.hasSetForceRelease) {
            this.gIsForceCloseDebuggable = envVarParams.forceRelease;
        } else if (Config.getInstance(context).getInt(FORCE_CLOSE_DEBUGGABLE_KEY, -1) != -1) {
            this.gIsForceCloseDebuggable = true;
        }
        if (this.gExtConfig.exists()) {
            this.gIsDebuggable = true;
        }
        JSONObject jSONObjectData = this.gExtConfig.data();
        if (jSONObjectData != null) {
            this.gIsDebuggable = true;
            try {
                if (jSONObjectData.has(DEBUGGABLE_KEY)) {
                    this.gIsDebuggable = jSONObjectData.getBoolean(DEBUGGABLE_KEY);
                }
                if (jSONObjectData.has(FORCE_TEST_ENV_KEY)) {
                    this.gIsTestEnvByCfg = jSONObjectData.getBoolean(FORCE_TEST_ENV_KEY);
                }
            } catch (Exception unused) {
                Utils.dwAssert(false);
            }
        }
        int i = Config.getInstance(context).getInt(CONFIG_IS_TEST_ENV_KEY, -1);
        if (i != -1) {
            this.gIsTestEnvByCfg = i > 0 || this.gIsTestEnvByCfg;
        }
        if (envVarParams.hasSettestEnv) {
            this.gIsTestEnv = envVarParams.isTestEnv;
        } else {
            this.gIsTestEnv = isDebuggable() && this.gIsTestEnvByCfg;
        }
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public boolean isTestEnv() {
        return this.gIsTestEnv;
    }

    public void switchTestEnvCfg(boolean z) {
        if (MTPApi.CONTEXT == null) {
            return;
        }
        Config.getInstance(MTPApi.CONTEXT.getApplication()).setInt(CONFIG_IS_TEST_ENV_KEY, z ? 1 : 0);
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public boolean isSnapshot() {
        if (MTPApi.CONTEXT.getApplication() == null) {
            MTPApi.LOGGER.warn("mtp-api not init yet!");
        }
        return this.gIsSnapshot && (!this.gIsPreRelease || this.gExtConfig.exists());
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public boolean isDebuggable() {
        if (MTPApi.CONTEXT.getApplication() == null) {
            MTPApi.LOGGER.warn("mtp-api not init yet!");
        }
        if (this.gIsForceCloseDebuggable) {
            return false;
        }
        if (!this.gIsPreRelease || this.gExtConfig.exists()) {
            return this.gIsSnapshot || this.gIsLocalBuild || this.gIsDebuggable;
        }
        return false;
    }
}
