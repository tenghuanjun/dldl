package com.plugin.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import com.plugin.core.loader.ApkClassLoader;
import com.plugin.core.manifest.AndroidManifestParser;
import com.plugin.core.resources.MixResources;
import com.plugin.core.resources.SuperHostResources;
import com.plugin.core.tool.ApkTools;
import com.plugin.core.tool.PluginLog;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PluginManager {
    private static final String KEY_HOTTER_CONSUMING = "hotter_consuming";
    private static final String MULTI_SDK = "multi_sdk";
    private static final String PRO_KEY_DEFAULT_PLUGIN = "defaultPlugin";
    public static final String SQ_PLUGIN_CONFIG = "sq_plugin_config";
    private static PluginManager sInstance;
    private Context mContext;
    private String mPluginPath;
    private final String TAG = "PluginManager";
    private final String KEY_LATEST_VERSION = "plugin_latest_version";
    private final String KEY_CURRENT_VERSION = "plugin_current_version";
    private final String KEY_HOTTER_EFFECT = "hotter_hotter_effect";
    private final String KEY_HOTTER_ROLLBACK_EFFECT = "hotter_rollback_effect";
    private String defaultPluginApkName = "default_plugin.apk";

    private PluginManager(Context context) {
        this.mContext = context;
    }

    public static PluginManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PluginManager(context);
        }
        return sInstance;
    }

    public Plugin loadPlugin() throws Throwable {
        PluginLog.d("开始加载插件");
        PluginMultiSdkManager.getInstance().initMultiSdk(this.mContext);
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(SQ_PLUGIN_CONFIG, 0);
        int i = sharedPreferences.getInt("plugin_latest_version", -1);
        int defaultPluginVersion = PluginMultiSdkManager.getInstance().getDefaultPluginVersion();
        PluginLog.d("默认插件: " + defaultPluginVersion + ", 上次插件: " + i);
        if (i > defaultPluginVersion) {
            String strGenerateConfigPluginPath = generateConfigPluginPath(i);
            if (new File(strGenerateConfigPluginPath).exists() && isApkValid(strGenerateConfigPluginPath)) {
                PluginLog.i("使用下发的插件: " + i + ", " + strGenerateConfigPluginPath);
                int i2 = sharedPreferences.getInt("plugin_current_version", -1);
                PluginLog.d("当前插件: " + i2 + ", 即将加载的插件: " + i);
                this.mPluginPath = strGenerateConfigPluginPath;
                if (i != i2) {
                    sharedPreferences.edit().putString("hotter_hotter_effect", i2 + "").apply();
                }
                sharedPreferences.edit().putInt("plugin_current_version", i).apply();
                return loadPlugin(this.mPluginPath);
            }
        }
        if (!TextUtils.isEmpty(PluginMultiSdkManager.getInstance().getDefaultPlugin())) {
            this.defaultPluginApkName = PluginMultiSdkManager.getInstance().getDefaultPlugin() + ".apk";
        }
        PluginLog.i("使用默认插件: " + this.defaultPluginApkName);
        this.mPluginPath = copyAssetPlugin(this.defaultPluginApkName, "plugin");
        sharedPreferences.edit().putInt("plugin_current_version", defaultPluginVersion).apply();
        return loadPlugin(this.mPluginPath);
    }

    private boolean isApkValid(String str) {
        return ApkTools.isApkValid(str);
    }

    private String generateConfigPluginPath(int i) {
        return this.mContext.getDir("plugin", 0).getAbsolutePath() + File.separator + "plugin_" + i + ".apk";
    }

    private Plugin loadPlugin(String str) throws Throwable {
        PluginLog.i("加载插件: " + str);
        Plugin plugin = new Plugin();
        plugin.setPluginPath(str);
        File file = new File(str);
        String absolutePath = new File(file.getParent(), "lib").getAbsolutePath();
        PluginLog.d("处理So");
        SoLibUtil.releaseSoFile(this.mContext, file, absolutePath);
        long jUptimeMillis = SystemClock.uptimeMillis();
        ApkClassLoader apkClassLoader = new ApkClassLoader(str, this.mContext.getDir("plugin-opti", 0).getAbsolutePath(), absolutePath, this.mContext.getClassLoader(), new String[]{"com.sqwan.msdk.api", "com.sqwan.msdk.api.tool", "com.sq.standard"});
        long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
        PluginLog.d("加载apk耗时" + jUptimeMillis2 + "ms");
        this.mContext.getSharedPreferences(SQ_PLUGIN_CONFIG, 0).edit().putString(KEY_HOTTER_CONSUMING, jUptimeMillis2 + "").apply();
        PluginLog.d("原始class loader: " + this.mContext.getClassLoader());
        PluginLog.d("插件class loader: " + apkClassLoader);
        plugin.setClassLoader(apkClassLoader);
        try {
            SuperHostResources superHostResources = new SuperHostResources(this.mContext, this.mPluginPath);
            MixResources mixResources = new MixResources(superHostResources.get(), this.mContext, this.mPluginPath);
            plugin.setResources(mixResources);
            PluginLog.d("插件resources: " + mixResources);
            Integer pluginApkCookie = superHostResources.getPluginApkCookie();
            if (pluginApkCookie != null) {
                AndroidManifestParser.initializingComponent(this.mContext, apkClassLoader, AndroidManifestParser.parseAndroidManifest(this.mContext, pluginApkCookie.intValue()));
            }
        } catch (Exception e) {
            PluginLog.e("加载插件异常", e);
        }
        PluginLog.i("加载插件完成: " + plugin);
        return plugin;
    }

    String copyAssetPlugin(String str, String str2) throws Throwable {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = this.mContext.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            inputStreamOpen = null;
        }
        if (inputStreamOpen == null) {
            PluginLog.e("assets文件不存在: " + str);
            return null;
        }
        File dir = this.mContext.getDir(str2, 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, str);
        writeInputStream(file.getAbsolutePath(), inputStreamOpen);
        return file.getAbsolutePath();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x005c A[Catch: IOException -> 0x0058, TRY_LEAVE, TryCatch #2 {IOException -> 0x0058, blocks: (B:34:0x0054, B:38:0x005c), top: B:43:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void writeInputStream(java.lang.String r4, java.io.InputStream r5) throws java.lang.Throwable {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            r4 = 0
            boolean r1 = r0.exists()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L40
            if (r1 != 0) goto L2a
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L40
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L40
            int r4 = r5.available()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L51
            byte[] r4 = new byte[r4]     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L51
        L17:
            int r0 = r5.read(r4)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L51
            r2 = -1
            if (r0 == r2) goto L23
            r2 = 0
            r1.write(r4, r2, r0)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L51
            goto L17
        L23:
            r1.flush()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L51
            r4 = r1
            goto L2a
        L28:
            r4 = move-exception
            goto L43
        L2a:
            if (r4 == 0) goto L32
            r4.close()     // Catch: java.io.IOException -> L30
            goto L32
        L30:
            r4 = move-exception
            goto L38
        L32:
            if (r5 == 0) goto L50
            r5.close()     // Catch: java.io.IOException -> L30
            goto L50
        L38:
            r4.printStackTrace()
            goto L50
        L3c:
            r0 = move-exception
            r1 = r4
            r4 = r0
            goto L52
        L40:
            r0 = move-exception
            r1 = r4
            r4 = r0
        L43:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L4b
            r1.close()     // Catch: java.io.IOException -> L30
        L4b:
            if (r5 == 0) goto L50
            r5.close()     // Catch: java.io.IOException -> L30
        L50:
            return
        L51:
            r4 = move-exception
        L52:
            if (r1 == 0) goto L5a
            r1.close()     // Catch: java.io.IOException -> L58
            goto L5a
        L58:
            r5 = move-exception
            goto L60
        L5a:
            if (r5 == 0) goto L63
            r5.close()     // Catch: java.io.IOException -> L58
            goto L63
        L60:
            r5.printStackTrace()
        L63:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.plugin.core.PluginManager.writeInputStream(java.lang.String, java.io.InputStream):void");
    }

    public HashMap<String, String> getMultiSdkConfig() throws Throwable {
        HashMap<String, String> map = new HashMap<>();
        Properties properties = readProperties(this.mContext);
        if (properties == null) {
            PluginLog.e("multi_sdk配置不存在");
            return null;
        }
        for (Map.Entry entry : properties.entrySet()) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.InputStream] */
    private Properties readProperties(Context e) throws Throwable {
        ?? properties;
        IOException e2;
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                e = e.getAssets().open(MULTI_SDK);
                try {
                    properties = new Properties();
                } catch (IOException e3) {
                    properties = 0;
                    e2 = e3;
                }
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
            }
            try {
                properties.load(e);
            } catch (IOException e5) {
                e2 = e5;
                e2.printStackTrace();
                if (e != 0) {
                    e.close();
                    properties = properties;
                    e = e;
                }
                return properties;
            }
        } catch (IOException e6) {
            properties = 0;
            e2 = e6;
            e = 0;
        } catch (Throwable th2) {
            th = th2;
            e = 0;
            if (e != 0) {
                try {
                    e.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
        if (e != 0) {
            e.close();
            properties = properties;
            e = e;
        }
        return properties;
    }
}
