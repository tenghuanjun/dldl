package com.youme.voiceengine.mgr;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.youme.voiceengine.AppPara;
import com.youme.voiceengine.AudioMgr;
import com.youme.voiceengine.NetUtil;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YouMeManager {
    private static final String TAG = "YOUME";
    public static final String YOU_ME_LIB_NAME_STRING = "youme_voice_engine";
    private static String mCachePath;
    private static String mStrDownloadURL;
    private static String mStrFileMD5;
    private static Boolean m_bStopDownload = false;
    private static Context mContext = null;
    public static Boolean mInited = false;
    private static Boolean soLoaded = false;

    public YouMeManager(Context context) {
        mContext = context;
    }

    public static boolean LoadSO(Context context) {
        String str = mCachePath + "/lib" + YOU_ME_LIB_NAME_STRING + ".so";
        try {
            int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            String string = context.getSharedPreferences("YouMeUpdate", 0).getString("UpdateVercode", "");
            if (!string.equals(String.valueOf(i))) {
                Log.i(TAG, "没有当前版本的更新，忽略已经下载的so 应用versioncode: " + i + "可以更新的版本:" + string);
                new File(str).delete();
            } else {
                Log.i(TAG, "应用versioncode: " + i + "可以更新的版本:" + string);
            }
            try {
                if (fileIsExists(str)) {
                    Log.i(TAG, "load " + str);
                    System.load(str);
                    return true;
                }
                System.loadLibrary(YOU_ME_LIB_NAME_STRING);
                return true;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            new File(str).delete();
            return false;
        }
    }

    public static boolean Init(Context context) {
        Log.i(TAG, "调用init 函数 开始");
        if (mInited.booleanValue()) {
            Log.e(TAG, "Init: Already initialzed");
            if (context instanceof Activity) {
                mContext = context;
                AudioMgr.init(context);
            }
            return true;
        }
        if (!soLoaded.booleanValue()) {
            m_bStopDownload = false;
            mContext = context;
            mCachePath = context.getDir("libs", 0).getAbsolutePath();
            Log.i(TAG, "调用init 函数 开始，目录:" + mCachePath);
            if (!LoadSO(context)) {
                Log.i(TAG, "Failed to load so");
                return false;
            }
            Log.i(TAG, "动态库加载完成");
            soLoaded = true;
        }
        try {
            AppPara.initPara(context);
            AudioMgr.init(context);
            Log.i(TAG, "调用init 函数 结束");
            mInited = true;
            if (!(context instanceof Activity)) {
                Log.e(TAG, "context is not Activity");
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean Init(Context context, String str) {
        Log.i(TAG, "调用init 函数 加载指定动态库:" + str);
        if (mInited.booleanValue()) {
            Log.e(TAG, "Init: Already initialzed");
            if (context instanceof Activity) {
                mContext = context;
                AudioMgr.init(context);
            }
            return true;
        }
        if (!soLoaded.booleanValue()) {
            mContext = context;
            Log.i(TAG, "开始加载指定动态库:" + str);
            try {
                if (fileIsExists(str)) {
                    Log.i(TAG, "指定动态库存在，正在加载: " + str);
                    System.load(str);
                } else {
                    Log.w(TAG, "指定动态库不存在，加载默认so库: youme_voice_engine");
                    System.loadLibrary(YOU_ME_LIB_NAME_STRING);
                }
                Log.i(TAG, "动态库加载完成");
                soLoaded = true;
            } catch (Throwable th) {
                Log.i(TAG, "Failed to load so");
                th.printStackTrace();
                return false;
            }
        }
        try {
            AppPara.initPara(context);
            AudioMgr.init(context);
            Log.i(TAG, "调用init 加载指定动态库 结束");
            mInited = true;
            if (!(context instanceof Activity)) {
                Log.e(TAG, "context is not Activity");
            }
            return true;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return false;
        }
    }

    public static void Uninit() {
        try {
            m_bStopDownload = true;
            AudioMgr.uinit();
            mInited = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean fileIsExists(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean DownloadFile(String str, String str2) {
        File file = new File(str2);
        file.delete();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(0L);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            if (inputStream != null) {
                byte[] bArr = new byte[65536];
                while (!m_bStopDownload.booleanValue()) {
                    int i = inputStream.read(bArr);
                    if (i <= 0) {
                        inputStream.close();
                        httpURLConnection.disconnect();
                        randomAccessFile.close();
                        return true;
                    }
                    randomAccessFile.write(bArr, 0, i);
                }
            }
            inputStream.close();
            httpURLConnection.disconnect();
            randomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
        return false;
    }

    public static void TriggerNetChange() {
        AppPara.onNetWorkChange(NetUtil.getNetworkState(mContext));
    }

    public static void SaveLogcat(String str) {
        LogUtil.SaveLogcat(str);
    }

    public static void UpdateSelf(String str, String str2) {
        if (mStrDownloadURL != null) {
            return;
        }
        mStrDownloadURL = str + "/android/" + Build.CPU_ABI + "/libyoume_voice_engine.so";
        mStrFileMD5 = str2;
        Log.i(TAG, "UpdateSelf " + mStrDownloadURL + "\n MD5: " + str2);
        new Thread(new Runnable() { // from class: com.youme.voiceengine.mgr.YouMeManager.1
            @Override // java.lang.Runnable
            public void run() {
                String str3 = YouMeManager.mCachePath + "/lib" + YouMeManager.YOU_ME_LIB_NAME_STRING + ".so";
                String str4 = str3 + ".tmp";
                if (YouMeManager.DownloadFile(YouMeManager.mStrDownloadURL, str4)) {
                    try {
                        String fileMD5String = FileMD5.getFileMD5String(str4);
                        Log.i(YouMeManager.TAG, "下载成功，MD5: " + fileMD5String);
                        if (fileMD5String.equalsIgnoreCase(YouMeManager.mStrFileMD5)) {
                            if (new File(str3).delete()) {
                                Log.i(YouMeManager.TAG, "删除当前so 成功:" + str3);
                            } else {
                                Log.i(YouMeManager.TAG, "删除当前so 失败:" + str3);
                            }
                            if (new File(str4).renameTo(new File(str3))) {
                                Log.i(YouMeManager.TAG, "重命名成功: " + str3);
                                SharedPreferences sharedPreferences = YouMeManager.mContext.getSharedPreferences("YouMeUpdate", 0);
                                int i = YouMeManager.mContext.getPackageManager().getPackageInfo(YouMeManager.mContext.getPackageName(), 0).versionCode;
                                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                editorEdit.putString("UpdateVercode", String.valueOf(i));
                                editorEdit.commit();
                            } else {
                                Log.i(YouMeManager.TAG, "重命名失败: " + str3);
                            }
                        } else {
                            Log.i(YouMeManager.TAG, "MD5 不正确，删掉已经下载的文件: " + fileMD5String + "  " + YouMeManager.mStrFileMD5);
                            new File(str4).delete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i(YouMeManager.TAG, "下载失败了 URL:" + YouMeManager.mStrDownloadURL);
                }
                String unused = YouMeManager.mStrDownloadURL = null;
            }
        }).start();
    }
}
