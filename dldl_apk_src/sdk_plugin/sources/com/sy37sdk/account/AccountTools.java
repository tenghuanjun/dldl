package com.sy37sdk.account;

import android.content.Context;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.ZipString;
import com.sqwan.msdk.config.MultiSdkManager;
import com.sy37sdk.account.entrance.EntranceManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountTools {
    private static String getDir(Context context) {
        File file = new File(getSDPath(context) + "/" + MultiSdkManager.getInstance().getAccountDir());
        if (!file.exists()) {
            file.mkdir();
        }
        return getSDPath(context) + "/" + MultiSdkManager.getInstance().getAccountDir() + "/";
    }

    private static File getAccountFile(Context context) {
        try {
            File file = new File(getDir(context) + "/" + MultiSdkManager.getInstance().getAccountFile());
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            System.err.println("无SDCard，获取AF失败");
            e.printStackTrace();
            return new File("");
        }
    }

    public static List<UserInfo> getAccountFromFile(Context context) {
        return getAccountFromFile(context, !EntranceManager.getInstance().isAccountLoginEntrance());
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(3:46|3|4)|(7:53|5|(1:7)(1:56)|47|9|21|(1:23)(5:24|49|25|(3:27|(4:30|(2:35|58)(2:34|59)|36|28)|57)|39))|8|47|9|21|(0)(0)|(1:(0))) */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0081: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:41:0x0081 */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.sy37sdk.account.UserInfo> getAccountFromFile(android.content.Context r5, boolean r6) throws java.lang.Throwable {
        /*
            java.lang.String r0 = ""
            java.io.File r5 = getAccountFile(r5)
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r5 = r0
        L12:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
            if (r3 == 0) goto L28
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
            r4.<init>()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
            r4.append(r5)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
            r4.append(r3)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
            java.lang.String r5 = r4.toString()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
            goto L12
        L28:
            r2.close()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L80
        L2b:
            r2.close()     // Catch: java.lang.Exception -> L2f
            goto L3e
        L2f:
            goto L3e
        L31:
            r3 = move-exception
            goto L38
        L33:
            r5 = move-exception
            goto L82
        L35:
            r3 = move-exception
            r5 = r0
            r2 = r1
        L38:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L80
            if (r2 == 0) goto L3e
            goto L2b
        L3e:
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L45
            return r1
        L45:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r5 = com.sqwan.common.util.ZipString.zipString2Json(r5)     // Catch: java.lang.Exception -> L7b
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> L7b
            r1.<init>(r5)     // Catch: java.lang.Exception -> L7b
            int r5 = r1.length()     // Catch: java.lang.Exception -> L7b
            if (r5 <= 0) goto L7f
            r5 = 0
        L5a:
            int r2 = r1.length()     // Catch: java.lang.Exception -> L7b
            if (r5 >= r2) goto L7f
            org.json.JSONObject r2 = r1.getJSONObject(r5)     // Catch: java.lang.Exception -> L7b
            com.sy37sdk.account.UserInfo r2 = com.sy37sdk.account.UserInfo.decodeFromJson(r2)     // Catch: java.lang.Exception -> L7b
            if (r6 == 0) goto L75
            java.lang.String r3 = r2.getMobile()     // Catch: java.lang.Exception -> L7b
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L7b
            if (r3 == 0) goto L75
            goto L78
        L75:
            r0.add(r2)     // Catch: java.lang.Exception -> L7b
        L78:
            int r5 = r5 + 1
            goto L5a
        L7b:
            r5 = move-exception
            r5.printStackTrace()
        L7f:
            return r0
        L80:
            r5 = move-exception
            r1 = r2
        L82:
            if (r1 == 0) goto L87
            r1.close()     // Catch: java.lang.Exception -> L87
        L87:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.AccountTools.getAccountFromFile(android.content.Context, boolean):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setAccountToFile(android.content.Context r8, com.sy37sdk.account.UserInfo r9) {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.AccountTools.setAccountToFile(android.content.Context, com.sy37sdk.account.UserInfo):void");
    }

    public static void delAccountFromFile(Context context, String str) throws Throwable {
        List<UserInfo> accountFromFile = getAccountFromFile(context, false);
        File accountFile = getAccountFile(context);
        if (accountFromFile == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (UserInfo userInfo : accountFromFile) {
            if (userInfo.getUname() != null && str != null && !userInfo.getUname().equals(str)) {
                arrayList.add(userInfo);
            }
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(UserInfo.encodeToJson((UserInfo) it.next()));
            }
            FileWriter fileWriter = new FileWriter(accountFile.getAbsolutePath(), false);
            fileWriter.write(ZipString.json2ZipString(jSONArray.toString()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonDirPathEndWithSprit(context);
    }

    public static void cleanAccountInfoCache(Context context) {
        getAccountFile(context).delete();
    }
}
