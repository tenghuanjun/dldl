package com.sqwan.msdk.config;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.sqwan.common.util.LogUtil;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MultiSdkManager {
    public static String APP_HOST = "37.com.cn";
    private static final String MULTI_SDK = "multi_sdk";
    private static final String PRO_KEY_ACCOUNT_DIR = "accountDir";
    private static final String PRO_KEY_ACCOUNT_FILE = "accountFile";
    private static final String PRO_KEY_CONFIG = "config";
    private static final String PRO_KEY_HOST = "host";
    private static final String PRO_KEY_INFO = "info";
    private static final String PRO_KEY_SCUT3 = "scut3";
    private static final String PRO_KEY_TAG = "logTag";
    public static final String SECURE_SUFFIX = "-secure.";
    private static final String TAG = "MultiSdkManager";
    private static volatile MultiSdkManager instance;
    private String mAccountDir;
    private String mAccountFile;
    private String mConfig;
    private String mHost;
    private String mInfo;
    private String mScut3;
    private String mTag;

    private MultiSdkManager() {
    }

    public static MultiSdkManager getInstance() {
        if (instance == null) {
            synchronized (MultiSdkManager.class) {
                if (instance == null) {
                    instance = new MultiSdkManager();
                }
            }
        }
        return instance;
    }

    public void initMultiSdk(Context context) throws Throwable {
        Properties properties = readProperties(context);
        if (properties == null) {
            Log.e(TAG, "multi_sdk config is null!");
            return;
        }
        this.mConfig = properties.getProperty(PRO_KEY_CONFIG);
        this.mInfo = properties.getProperty("info");
        this.mHost = properties.getProperty("host");
        this.mTag = properties.getProperty(PRO_KEY_TAG);
        this.mAccountDir = properties.getProperty(PRO_KEY_ACCOUNT_DIR);
        this.mAccountFile = properties.getProperty(PRO_KEY_ACCOUNT_FILE);
        this.mScut3 = properties.getProperty(PRO_KEY_SCUT3);
        Log.e(TAG, "multi_sdk config: " + this.mConfig + ", info:" + this.mInfo + ", host:" + this.mHost + ", tag:" + this.mTag + ", accountDir:" + this.mAccountDir + ", accountFile:" + this.mAccountFile + ", scut3:" + this.mScut3);
        refreshStaticConfig();
    }

    private void refreshStaticConfig() {
        LogUtil.setTag(this.mTag);
        APP_HOST = this.mHost;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.Properties readProperties(android.content.Context r4) throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            android.content.res.Resources r4 = r4.getResources()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            java.lang.String r1 = "multi_sdk"
            java.io.InputStream r4 = r4.open(r1)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            java.util.Properties r1 = new java.util.Properties     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L3b
            r1.<init>()     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L3b
            r1.load(r4)     // Catch: java.io.IOException -> L22 java.lang.Throwable -> L3b
            if (r4 == 0) goto L3a
            r4.close()     // Catch: java.io.IOException -> L1d
            goto L3a
        L1d:
            r4 = move-exception
            r4.printStackTrace()
            goto L3a
        L22:
            r0 = move-exception
            goto L32
        L24:
            r1 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L32
        L29:
            r4 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
            goto L3c
        L2e:
            r4 = move-exception
            r1 = r0
            r0 = r4
            r4 = r1
        L32:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3b
            if (r4 == 0) goto L3a
            r4.close()     // Catch: java.io.IOException -> L1d
        L3a:
            return r1
        L3b:
            r0 = move-exception
        L3c:
            if (r4 == 0) goto L46
            r4.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r4 = move-exception
            r4.printStackTrace()
        L46:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.config.MultiSdkManager.readProperties(android.content.Context):java.util.Properties");
    }

    public String getConfig() {
        return this.mConfig;
    }

    public String getInfo() {
        return this.mInfo;
    }

    public String getHost() {
        return this.mHost;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getAccountDir() {
        return this.mAccountDir;
    }

    public String getAccountFile() {
        return this.mAccountFile;
    }

    public String getScut3() {
        return this.mScut3;
    }

    public boolean isScut3() {
        return !TextUtils.isEmpty(this.mScut3);
    }
}
