package com.sqwan.msdk.api;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.msdk.views.SQConfirmDialog;
import com.sqwan.msdk.views.SQUpdateDialog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQUpdateManager {
    public static final String TYPE_FORCE = "3";
    public static final String TYPE_NORMAL = "1";
    public static final String TYPE_UPDATE = "2";
    public static final String UPDATE_PREF = "update_pref";
    public static String updateType = "1";

    public static void checkUpdateConfig(Context context, JSONObject jSONObject) throws Exception {
        if (jSONObject.isNull("utype")) {
            return;
        }
        updateType = jSONObject.getString("utype");
        String string = jSONObject.getString("uurl");
        String string2 = jSONObject.getString("uct");
        if ("1".equals(updateType)) {
            return;
        }
        if ("2".equals(updateType)) {
            checkUpdate(context, false, string2, string, "1.0");
        } else if ("3".equals(updateType)) {
            checkUpdate(context, true, string2, string, "1.0");
        }
    }

    public static void checkUpdate(Context context, boolean z, String str, String str2, String str3) {
        String fileNameOfUrl = getFileNameOfUrl(context, str2, str3);
        File file = new File(getSDPath(context) + fileNameOfUrl);
        if (file.exists() && readFileLength(context, fileNameOfUrl) == file.length()) {
            checkAndInstall(z, context, file);
        } else {
            new SQUpdateDialog(context, z, str, str2, str3).show();
        }
    }

    public static void saveFileLength(Context context, String str, long j) {
        context.getSharedPreferences(UPDATE_PREF, 0).edit().putLong(str, j).commit();
    }

    public static long readFileLength(Context context, String str) {
        return context.getSharedPreferences(UPDATE_PREF, 0).getLong(str, 0L);
    }

    public static String getFileNameOfUrl(Context context, String str, String str2) {
        if (str == null || "".equals(str)) {
            return null;
        }
        String strSubstring = str.substring(str.lastIndexOf("/") + 1);
        if (!"".equals(strSubstring) && strSubstring != null) {
            if (!strSubstring.contains(".apk")) {
                strSubstring = strSubstring + ".apk";
            }
        } else {
            strSubstring = MultiSDKUtils.getGID(context) + "_" + MultiSDKUtils.getPID(context) + "_" + MultiSDKUtils.getRefer(context) + ".apk";
        }
        String str3 = str2 + "_" + strSubstring;
        System.out.println("下载的文件名：" + str3);
        return str3;
    }

    public static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonSubDirPath(context, "download");
    }

    public static boolean installApk(Context context, File file) {
        if (!file.exists()) {
            return false;
        }
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        LogUtil.e("安装apk");
        if (Build.VERSION.SDK_INT >= 24) {
            LogUtil.e("安装apk，使用provider");
            intent.setDataAndType(FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file), "application/vnd.android.package-archive");
            intent.addFlags(1);
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
        return true;
    }

    public static void checkAndInstall(final boolean z, final Context context, final File file) {
        if (file != null) {
            if (file.getAbsolutePath().endsWith(".apk")) {
                final SQConfirmDialog sQConfirmDialog = new SQConfirmDialog(context, "更新已完成,是否安装？");
                sQConfirmDialog.setConfirmListenr(new SQConfirmDialog.ConfirmListener() { // from class: com.sqwan.msdk.api.SQUpdateManager.1
                    @Override // com.sqwan.msdk.views.SQConfirmDialog.ConfirmListener
                    public void onConfirm() {
                        if (SQUpdateManager.installApk(context, file)) {
                            return;
                        }
                        SQUpdateManager.showTips(context, "APK 文件不存在");
                        sQConfirmDialog.dismiss();
                    }

                    @Override // com.sqwan.msdk.views.SQConfirmDialog.ConfirmListener
                    public void onCancel() {
                        if (z) {
                            SQUpdateManager.showTips(context, "此次更新为强制更新。\n为了给您更好的游戏体验，更新后才能进入游戏。");
                        } else {
                            sQConfirmDialog.dismiss();
                        }
                    }
                });
                if (z) {
                    sQConfirmDialog.setCancelable(false);
                }
                sQConfirmDialog.show();
                return;
            }
            showTips(context, "安装失败：文件格式不对。");
            return;
        }
        showTips(context, "安装失败：安装文件为空，请重新下载");
    }

    public static void showTips(Context context, String str) {
        try {
            ToastUtil.showToast(context, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

    @Deprecated
    public static int getIdByName(String str, String str2, String str3, Context context) {
        return SqResUtils.getIdByName(str, str2, context);
    }

    @Deprecated
    public static String getCurrentDate() {
        String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        LogUtil.w("获取的时间是：" + str);
        return str;
    }
}
