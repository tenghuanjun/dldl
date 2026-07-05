package com.sqwan.bugless.core;

import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.bugless.model.AppExtension;
import com.sqwan.bugless.model.UserInfo;
import com.sqwan.bugless.net.BuglessHttpClient;
import com.sqwan.bugless.net.SignHelper;
import com.sqwan.bugless.util.DateUtil;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.bugless.util.LogUtil;
import com.sqwan.bugless.util.NetworkUtil;
import com.sqwan.bugless.util.SharedPreferencesUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BugHandler {
    private static BugHandler instance;

    public void handleEvent(String type, String content) {
    }

    private BugHandler() {
    }

    public static BugHandler getInstance() {
        if (instance == null) {
            synchronized (Bugless.class) {
                if (instance == null) {
                    instance = new BugHandler();
                }
            }
        }
        return instance;
    }

    public void handleUncatchException(Thread thread, Throwable ex) {
        postLogSafe(thread, prepareEx(ex), 1, null, null, false);
    }

    public void handleCatchedException(Throwable ex, String msg, String data) {
        handleCatchedException(ex, msg, data, 2, false);
    }

    public void handleCatchedException(Throwable ex, String msg, String data, int actionType, Boolean isHasPermission) {
        postLogSafe(null, prepareEx(ex), actionType, msg, data, isHasPermission);
    }

    public void handleCacheException(String cacheLog) {
        if (NetworkUtil.isNetworkConnected(Bugless.getInstance().getContext())) {
            postLog(cacheLog);
            SharedPreferencesUtil.getInstance().setValue(Constant.LOG_CACHE, "");
        }
    }

    private void postLogSafe(Thread thread, JSONObject content, final int type, String msg, String data, Boolean isHasPermission) {
        try {
            postLog(thread, content, type, msg, data, isHasPermission);
        } catch (Exception e) {
            Log.w("Bugless", "上报失败", e);
        }
    }

    private void postLog(Thread thread, JSONObject content, final int type, String msg, String data, Boolean isHasPermission) {
        Map<String, Object> mapPrepareLog = prepareLog(thread, content, type, msg, data, isHasPermission);
        LogUtil.i("上报日志..");
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : mapPrepareLog.entrySet()) {
                LogUtil.d("key : " + entry.getKey() + " value : " + entry.getValue());
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jSONObject = SignHelper.sign(jSONObject, ParamsManager.getInstance().getAppSecret());
        } catch (IOException e2) {
            LogUtil.e("数据签名出错" + e2.getMessage());
        }
        String string = jSONObject.toString();
        if (NetworkUtil.isNetworkConnected(Bugless.getInstance().getContext())) {
            postLog(string);
        } else {
            LogUtil.e("无网络连接，写日志到文件");
            SharedPreferencesUtil.getInstance().setValue(Constant.LOG_CACHE, string);
        }
    }

    private void postLog(String content) {
        BuglessHttpClient.post(content, null);
    }

    private void startPostService(Map<String, Object> params, final int type, final String fileName) {
        LogUtil.i("启动服务..");
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                LogUtil.d("key : " + entry.getKey() + " value : " + entry.getValue());
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jSONObject = SignHelper.sign(jSONObject, ParamsManager.getInstance().getAppSecret());
        } catch (IOException e2) {
            LogUtil.e("数据签名出错" + e2.getMessage());
        }
        String string = jSONObject.toString();
        Intent intent = new Intent(Bugless.getInstance().getContext(), (Class<?>) LogUploadService.class);
        intent.putExtra(LogUploadService.KEY_POST_BODY, string);
        intent.putExtra(LogUploadService.KEY_LOG_NAME, fileName);
        Bugless.getInstance().getContext().startService(intent);
    }

    private String saveCrashInfoFile(String content) throws Throwable {
        LogUtil.i("保存错误信息到文件中...");
        String strWriteFile = writeFile(content);
        saveCache(strWriteFile);
        return strWriteFile;
    }

    private Map<String, Object> prepareLog(Thread thread, JSONObject content, int type, String msg, String data, Boolean isHasPermission) {
        HashMap map = new HashMap(16);
        map.put(Constant.ACTION_TYPE, Integer.valueOf(type));
        AppExtension extension = ParamsManager.getInstance().getExtension();
        if (extension != null) {
            map.put("channel", extension.getChannel());
            map.put("pid", extension.getPid());
            map.put("gid", extension.getGid());
            map.put("refer", extension.getRefer());
            map.put("sversion", extension.getSversion());
            map.put(Constant.DEVICE_ID, extension.getDeviceID());
        }
        if (type != 1 && msg != null && data != null) {
            map.put(Constant.INFO_BUSINESS_MSG, msg);
            map.put(Constant.INFO_BUSINESS_DATA, data);
        }
        UserInfo userInfo = ParamsManager.getInstance().getUserInfo();
        if (userInfo != null) {
            map.put("uname", userInfo.getName());
            map.put("uid", userInfo.getId());
        }
        Map<String, String> deviceInfo = ParamsManager.getInstance().getDeviceInfo(isHasPermission);
        if (deviceInfo != null && !deviceInfo.isEmpty()) {
            map.putAll(deviceInfo);
        }
        map.put(Constant.INFO_HAPPEN_TIME, Long.valueOf(System.currentTimeMillis()));
        map.put(Constant.INFO_REPORT_TIME, Long.valueOf(System.currentTimeMillis()));
        map.put("content", content);
        map.put("duration", Long.valueOf(ParamsManager.getInstance().getDuration()));
        if (thread != null) {
            map.put(Constant.THREAD_ID, thread.getName());
        }
        String aPPId = ParamsManager.getInstance().getAPPId();
        if (aPPId != null && !"".equals(aPPId)) {
            map.put("appid", aPPId);
        }
        return map;
    }

    private JSONObject prepareEx(Throwable ex) {
        LogUtil.i("处理异常，输出日志内容...");
        LogUtil.w("异常堆栈", ex);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", ex.getClass().getName());
            jSONObject.put("message", ex.getMessage());
            StringBuffer stringBuffer = new StringBuffer();
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                if (1 == stackTrace.length - 1) {
                    stringBuffer.append(stackTrace[i].toString());
                } else {
                    stringBuffer.append(stackTrace[i].toString() + ShellAdbUtils.COMMAND_LINE_END);
                }
            }
            jSONObject.put(Constant.CRASH_STACK, stringBuffer.toString());
            LogUtil.i("日志内容 ： " + jSONObject.toString());
            return jSONObject;
        } catch (Exception unused) {
            LogUtil.e("an error occured while prepare log content...");
            return null;
        }
    }

    private String writeFile(String sb) throws Throwable {
        BufferedWriter bufferedWriter;
        LogUtil.i("开始将日志内容写入文件...");
        String str = "crash-" + (System.currentTimeMillis() + "") + ".log";
        LogUtil.d("文件名称 ：" + str);
        if (FileUtil.hasSdcard()) {
            BufferedWriter bufferedWriter2 = null;
            try {
                try {
                    try {
                        String str2 = getGlobalpath() + File.separator + str;
                        File file = new File(str2);
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        LogUtil.d("写入文件全路径 --> " + str2);
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str2, true)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                bufferedWriter.write(sb);
                bufferedWriter.close();
            } catch (FileNotFoundException e4) {
                e = e4;
                bufferedWriter2 = bufferedWriter;
                LogUtil.e("写入文件出错 FileNotFoundException");
                e.printStackTrace();
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
                return str;
            } catch (IOException e5) {
                e = e5;
                bufferedWriter2 = bufferedWriter;
                LogUtil.e("写入文件出错 IOException");
                e.printStackTrace();
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter2 = bufferedWriter;
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return str;
    }

    private String getTimeStampFromFileName(String fileName) {
        LogUtil.i("getTimeStampFromFileName --> fileName : " + fileName);
        if (fileName != null && !"".equals(fileName)) {
            try {
                String strSubstring = fileName.substring(fileName.indexOf("-") + 1, fileName.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
                LogUtil.i("getTimeStampFromFileName --> timeStemp : " + strSubstring);
                return strSubstring;
            } catch (Exception e) {
                LogUtil.i("从日志文件名获取时间戳出错");
                e.printStackTrace();
            }
        }
        return "";
    }

    private String readFile(String fileName) {
        LogUtil.i("开始从文件读取日志文件...日志名称" + fileName);
        try {
            String str = getGlobalpath() + File.separator + fileName;
            LogUtil.i("读取文件全路径 --> " + str);
            String file = FileUtil.readFile(str);
            LogUtil.i("读取到日志content = " + file);
            return file;
        } catch (Exception unused) {
            LogUtil.e("an error occured while read log file content...");
            return "";
        }
    }

    public boolean deleteFile(String fileName) {
        LogUtil.i("删除日志文件 ： " + fileName);
        deleteCache();
        File file = new File(getGlobalpath() + File.separator + fileName);
        return file.isFile() && file.exists() && file.delete();
    }

    private void saveCache(String fileName) {
        LogUtil.i("存储文件名称到缓存 fileName = " + fileName);
        SharedPreferencesUtil.getInstance().setValue(Constant.LOG_FILE_NAME, fileName);
    }

    public void deleteCache() {
        LogUtil.i("删除缓存的文件名");
        SharedPreferencesUtil.getInstance().setValue(Constant.LOG_FILE_NAME, "");
    }

    private static String getGlobalpath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "crash";
    }

    private void autoClear(final int autoClearDay) {
        FileUtil.delete(getGlobalpath(), new FilenameFilter() { // from class: com.sqwan.bugless.core.BugHandler.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String filename) {
                String fileNameWithoutExtension = FileUtil.getFileNameWithoutExtension(filename);
                int i = autoClearDay;
                if (i >= 0) {
                    i *= -1;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("crash-");
                sb.append(DateUtil.getOtherDay(i));
                return sb.toString().compareTo(fileNameWithoutExtension) >= 0;
            }
        });
    }
}
