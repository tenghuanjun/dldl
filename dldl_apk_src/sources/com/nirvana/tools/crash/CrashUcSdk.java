package com.nirvana.tools.crash;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import com.nirvana.tools.core.BaseDelegate;
import com.uc.crashsdk.export.CrashApi;
import com.uc.crashsdk.export.ICrashClient;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class CrashUcSdk extends BaseDelegate {
    private static final int DEFAULT_INIT_TIME = 5000;
    private static final String KEY_SDK_VERSION = "nirvana_sdk_version";
    private static final String LINE_SEP = "\n";
    private static final String LOG_TYPE = "CrashShield";
    private Context mContext;
    private Map<String, Object> mCustomInfo;
    private OnCrashCallbackProxy onCrashCallbackProxy;
    private SingleThreadExecutor singleThreadExecutor;
    private String mAppPackageName = null;
    private SdkInfo mUploadCrashSdk = null;
    int initDelayTime = -1;
    private StackAnalyzer mStackAnalyzer = new StackAnalyzer();
    private boolean isAppRegister = false;
    private String initTime = "";
    private boolean isInitFinished = false;
    private volatile boolean isFeatureEnable = true;
    private boolean isUcInitReadied = false;
    private List<SdkInfo> mPendingSdk = new ArrayList();
    private OnCrashCallback mInnerCrashCallback = new OnCrashCallback() { // from class: com.nirvana.tools.crash.CrashUcSdk.1
        @Override // com.nirvana.tools.crash.OnCrashCallback
        public void onCrashOccurred(String str, String str2, String str3, String str4, boolean z, String str5) {
            if (CrashUcSdk.this.onCrashCallbackProxy != null) {
                CrashUcSdk.this.onCrashCallbackProxy.onCrashOccurred(str, str2, str3, str4, z, str5);
            }
        }

        @Override // com.nirvana.tools.crash.OnCrashCallback
        public void onCrashUploadFailed(String str, String str2, String str3) {
            if (CrashUcSdk.this.onCrashCallbackProxy != null) {
                CrashUcSdk.this.onCrashCallbackProxy.onCrashUploadFailed(str, str2, str3);
            }
        }
    };

    class CrashCallback implements ICrashClient {
        private OnCrashCallback mCrashCallback;

        public CrashCallback(OnCrashCallback onCrashCallback) {
            this.mCrashCallback = onCrashCallback;
        }

        public void onAddCrashStats(String str, int i, int i2) {
        }

        public File onBeforeUploadLog(File file) {
            if (file != null) {
                String absolutePath = file.getAbsolutePath();
                if (!absolutePath.endsWith(".gz")) {
                    String str = absolutePath + ".gz";
                    if (FileUtils.gzipCompress(file, str)) {
                        File file2 = new File(str);
                        FileUtils.deleteFile(file);
                        return file2;
                    }
                }
            }
            return file;
        }

        public void onClientProcessLogGenerated(String str, File file, String str2) {
        }

        public void onCrashRestarting(boolean z) {
        }

        public String onGetCallbackInfo(String str, boolean z) {
            return null;
        }

        public void onLogGenerated(File file, String str) {
        }
    }

    CrashUcSdk() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkModifySymbolFile(String str, SdkInfo sdkInfo) {
        String[] strArrSplit;
        String name = FileUtils.getName(str);
        if (TextUtils.isEmpty(name) || name.contains(sdkInfo.getAppId()) || (strArrSplit = name.split("_")) == null || strArrSplit.length < 2) {
            return;
        }
        FileUtils.renameFile(str, str.replace(strArrSplit[0], sdkInfo.getAppId()).replace(strArrSplit[1], sdkInfo.getSdkVersion()));
    }

    private void doRegisterUc(Context context, SdkInfo sdkInfo) {
        if (this.isFeatureEnable) {
            this.mStackAnalyzer.initAddSdkConfig(sdkInfo);
            if (CrashApi.getInstance() == null) {
                initUcSdk(context, sdkInfo);
            } else {
                this.isAppRegister = true;
            }
            this.isInitFinished = true;
            CrashApi.getInstance().registerCallback(1, new ValueCallback<Bundle>() { // from class: com.nirvana.tools.crash.CrashUcSdk.3
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Bundle bundle) {
                    if (bundle == null) {
                        return;
                    }
                    String string = bundle.getString("filePathName");
                    String string2 = bundle.getString("logType");
                    if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                        return;
                    }
                    File file = new File(string);
                    if (file.exists()) {
                        if (CustomLogInfoBuilder.LOG_TYPE.equals(string2)) {
                            if (CrashUcSdk.this.mUploadCrashSdk != null) {
                                CrashUcSdk crashUcSdk = CrashUcSdk.this;
                                crashUcSdk.checkModifySymbolFile(string, crashUcSdk.mUploadCrashSdk);
                                CrashUcSdk.this.mUploadCrashSdk = null;
                                return;
                            }
                            return;
                        }
                        if (CrashSdk.CRASH_TYPE_JAVA.equals(string2)) {
                            String stringFromFile = FileUtils.readStringFromFile(file);
                            String javaStackTracingFromLog = UcLogProcessor.getJavaStackTracingFromLog(stringFromFile);
                            SdkInfo sdkInfoCheckJavaCrashInSdk = CrashUcSdk.this.mStackAnalyzer.checkJavaCrashInSdk(javaStackTracingFromLog);
                            if (TextUtils.isEmpty(javaStackTracingFromLog) || sdkInfoCheckJavaCrashInSdk == null) {
                                if (CrashUcSdk.this.isAppRegister) {
                                    return;
                                }
                                FileUtils.deleteFile(file);
                                return;
                            } else {
                                if (CrashUcSdk.this.onCrashCallbackProxy == null || sdkInfoCheckJavaCrashInSdk == null) {
                                    return;
                                }
                                CrashUcSdk.this.updateVersionInfo(sdkInfoCheckJavaCrashInSdk.getSdkVersion());
                                CrashUcSdk.this.checkModifySymbolFile(string, sdkInfoCheckJavaCrashInSdk);
                                CrashUcSdk.this.onCrashCallbackProxy.onCrashOccurred(null, sdkInfoCheckJavaCrashInSdk.getSdkName(), stringFromFile, UUID.randomUUID().toString(), false, CrashSdk.CRASH_TYPE_JAVA);
                                return;
                            }
                        }
                        if (!CrashSdk.CRASH_TYPE_JNI.equals(string2)) {
                            if (CrashUcSdk.this.isAppRegister) {
                                return;
                            }
                            FileUtils.deleteFile(file);
                            return;
                        }
                        String stringFromFile2 = FileUtils.readStringFromFile(file);
                        String nativeStackTracingFromLog = UcLogProcessor.getNativeStackTracingFromLog(stringFromFile2);
                        SdkInfo sdkInfoCheckNativeCrashInSdk = CrashUcSdk.this.mStackAnalyzer.checkNativeCrashInSdk(nativeStackTracingFromLog);
                        if (TextUtils.isEmpty(nativeStackTracingFromLog) || sdkInfoCheckNativeCrashInSdk == null) {
                            if (CrashUcSdk.this.isAppRegister) {
                                return;
                            }
                            FileUtils.deleteFile(file);
                        } else {
                            if (CrashUcSdk.this.onCrashCallbackProxy == null || sdkInfoCheckNativeCrashInSdk == null) {
                                return;
                            }
                            CrashUcSdk.this.updateVersionInfo(sdkInfoCheckNativeCrashInSdk.getSdkVersion());
                            CrashUcSdk.this.checkModifySymbolFile(string, sdkInfoCheckNativeCrashInSdk);
                            CrashUcSdk.this.onCrashCallbackProxy.onCrashOccurred(null, sdkInfoCheckNativeCrashInSdk.getSdkName(), stringFromFile2, UUID.randomUUID().toString(), false, CrashSdk.CRASH_TYPE_JNI);
                        }
                    }
                }
            });
        }
    }

    private void initUcSdk(Context context, SdkInfo sdkInfo) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("mDebug", true);
        bundle.putBoolean("mZipLog", false);
        bundle.putBoolean("mEncryptLog", false);
        bundle.putBoolean("mSyncUploadLogs", true);
        bundle.putBoolean("mCallJavaDefaultHandler", true);
        bundle.putBoolean("mCallNativeDefaultHandler", true);
        bundle.putBoolean("mAddLogcat", true);
        bundle.putBoolean("mAddThreadsDump", true);
        bundle.putInt("mMaxUploadCustomLogCountPerDay", 10000);
        bundle.putInt("mMaxCustomLogCountPerTypePerDay", 10000);
        CrashApi.createInstanceEx(context, sdkInfo.getAppId(), true, bundle, new CrashCallback(this.mInnerCrashCallback));
        CrashApi.getInstance().updateCustomInfo(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void lazyRegisterUc() {
        this.isUcInitReadied = true;
        Iterator<SdkInfo> it = this.mPendingSdk.iterator();
        while (it.hasNext()) {
            doRegisterUc(this.mContext, it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVersionInfo(String str) {
        if (isUcUsable()) {
            new Bundle().putString(KEY_SDK_VERSION, str);
            CrashApi.getInstance().addHeaderInfo(KEY_SDK_VERSION, str);
        }
    }

    public void generateCustomLogUploadItrace(final SdkInfo sdkInfo, final Thread thread, final Throwable th, final Map<String, String> map) {
        final String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        final String str2 = sdkInfo.getAppId() + "_" + sdkInfo.getSdkVersion() + "__" + Build.MODEL + "_" + Build.VERSION.RELEASE + "__" + str + "_fg_java.log";
        if (this.singleThreadExecutor == null) {
            this.singleThreadExecutor = new SingleThreadExecutor("crashUpload");
        }
        final String str3 = (map == null || map.isEmpty()) ? "" : map.get("uuid");
        this.singleThreadExecutor.execute(new Runnable() { // from class: com.nirvana.tools.crash.CrashUcSdk.5
            @Override // java.lang.Runnable
            public void run() {
                String strWriteSelfJavaLog = FileUtils.writeSelfJavaLog(CrashUcSdk.this.mContext, sdkInfo, thread, th, str, CrashUcSdk.this.initTime, str2, map);
                String str4 = strWriteSelfJavaLog + ".gz";
                if (FileUtils.gzipCompress(new File(strWriteSelfJavaLog), str4)) {
                    FileUtils.deleteFile(new File(strWriteSelfJavaLog));
                }
                HashMap map2 = new HashMap();
                map2.put(str2, new File(str4));
                try {
                    if (CrashUploadUtils.post(BuildConfig.UC_SERVER_UPLOAD_URL, new HashMap(), map2) || CrashUcSdk.this.onCrashCallbackProxy == null) {
                        return;
                    }
                    CrashUcSdk.this.onCrashCallbackProxy.onCrashUploadFailed(sdkInfo.getSdkName(), Log.getStackTraceString(th), str3);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (CrashUcSdk.this.onCrashCallbackProxy != null) {
                        CrashUcSdk.this.onCrashCallbackProxy.onCrashUploadFailed(sdkInfo.getSdkName(), Log.getStackTraceString(th), str3);
                    }
                }
            }
        });
    }

    @Override // com.nirvana.tools.core.BaseDelegate
    public String getSubClassName() {
        return "com.uc.crashsdk.export.CrashApi";
    }

    public void init(Context context) {
        this.initTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        this.mAppPackageName = context.getPackageName();
        this.mContext = context;
        if (this.initDelayTime < 0) {
            this.initDelayTime = 5000;
        }
        if (sComponentClassExist.booleanValue()) {
            int i = this.initDelayTime;
            if (i > 0) {
                new Timer().schedule(new TimerTask() { // from class: com.nirvana.tools.crash.CrashUcSdk.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        CrashUcSdk.this.lazyRegisterUc();
                    }
                }, this.initDelayTime);
            } else if (i == 0) {
                lazyRegisterUc();
            }
        }
    }

    public void initAddSdkConfig(SdkInfo sdkInfo) {
        this.mStackAnalyzer.initAddSdkConfig(sdkInfo);
    }

    public boolean isUcUsable() {
        return sComponentClassExist.booleanValue() && this.isInitFinished && this.isFeatureEnable;
    }

    public synchronized void registerUc(SdkInfo sdkInfo) {
        if (this.isUcInitReadied) {
            doRegisterUc(this.mContext, sdkInfo);
        } else {
            this.mPendingSdk.add(sdkInfo);
        }
    }

    public void setCrashCallback(OnCrashCallbackProxy onCrashCallbackProxy) {
        this.onCrashCallbackProxy = onCrashCallbackProxy;
    }

    public void setFeatureEnable(boolean z) {
        this.isFeatureEnable = z;
    }

    public void setUcCrashDelayTime(int i) {
        this.initDelayTime = i;
    }

    public void updateConfig(Map<String, Object> map) {
        Map<String, Object> map2;
        this.mCustomInfo = map;
        if (!isUcUsable() || (map2 = this.mCustomInfo) == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                CrashApi.getInstance().addHeaderInfo(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    public boolean uploadException(SdkInfo sdkInfo, final Thread thread, final Throwable th, final Map<String, String> map) {
        if (!isUcUsable()) {
            generateCustomLogUploadItrace(sdkInfo, thread, th, map);
            return true;
        }
        this.mUploadCrashSdk = sdkInfo;
        if (this.singleThreadExecutor == null) {
            this.singleThreadExecutor = new SingleThreadExecutor("crashUpload");
        }
        this.singleThreadExecutor.execute(new Runnable() { // from class: com.nirvana.tools.crash.CrashUcSdk.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CustomLogInfoBuilder customLogInfoBuilderStack = new CustomLogInfoBuilder(CrashUcSdk.LOG_TYPE).uploadNow(true).put("processName", CrashUcSdk.this.mAppPackageName).put("threadName", thread.getName()).stack(th);
                    if (map != null && map.size() > 0) {
                        for (Map.Entry entry : map.entrySet()) {
                            customLogInfoBuilderStack.put((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    CrashApi.getInstance().generateCustomLog(customLogInfoBuilderStack.build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }
}
