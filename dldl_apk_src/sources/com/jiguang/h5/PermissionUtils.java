package com.jiguang.h5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class PermissionUtils {
    public static final int CODE_ACCESS_COARSE_LOCATION = 6;
    public static final int CODE_ACCESS_FINE_LOCATION = 5;
    public static final int CODE_CALL_PHONE = 3;
    public static final int CODE_CAMERA = 4;
    public static final int CODE_GET_ACCOUNTS = 1;
    public static final int CODE_MULTI_PERMISSION = 100;
    public static final int CODE_READ_EXTERNAL_STORAGE = 7;
    public static final int CODE_READ_PHONE_STATE = 2;
    public static final int CODE_RECORD_AUDIO = 0;
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 8;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    public static final String PERMISSION_ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String PERMISSION_CALL_PHONE = "android.permission.CALL_PHONE";
    public static final String PERMISSION_CAMERA = "android.permission.CAMERA";
    public static final String PERMISSION_GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String PERMISSION_READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static final String PERMISSION_RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    private static final String TAG = "PermissionUtils";
    static boolean m_PrePermisstionResult;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String[] requestPermissions = {PERMISSION_WRITE_EXTERNAL_STORAGE};
    public static boolean isPermissionOk = false;
    public static boolean isSetting = false;
    static int pIndex = 0;

    static ArrayList<String> getNoGrantePermission(Activity activity, String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    static boolean IsPermissionGranted(Activity activity, String str) {
        try {
            boolean z = ActivityCompat.checkSelfPermission(activity, str) == 0;
            Log.d("fatlin", "=== IsPermissionGranted：" + z);
            return z;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    static void OpenSettingAcitivty(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(Util.GetStringByName("dtsh5_remind_setting", activity));
        builder.setMessage(Util.GetStringByName("dtsh5_remind_msg" + pIndex, activity));
        builder.setCancelable(false);
        builder.setPositiveButton(Util.GetStringByName("dtsh5_remind_set", activity), new DialogInterface.OnClickListener() { // from class: com.jiguang.h5.PermissionUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + activity.getPackageName())));
                PermissionUtils.isSetting = true;
            }
        });
        builder.create().show();
    }

    static void ShowPermissionTips(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(Util.GetStringByName("dtsh5_remind_title", activity));
        builder.setMessage(Util.GetStringByName("dtsh5_remind_msg" + pIndex, activity));
        builder.setCancelable(false);
        builder.setPositiveButton(Util.GetStringByName("dtsh5_remind_oncemore", activity), new DialogInterface.OnClickListener() { // from class: com.jiguang.h5.PermissionUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                String str = PermissionUtils.requestPermissions[PermissionUtils.pIndex];
                PermissionUtils.m_PrePermisstionResult = ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
                ActivityCompat.requestPermissions(activity, new String[]{str}, 100);
            }
        });
        builder.show();
    }

    public static void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        if (i != 100) {
            return;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            FCheckPermission(activity);
        } else if (strArr.length > 0) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, strArr[0])) {
                OpenSettingAcitivty(activity);
            } else {
                ShowPermissionTips(activity);
            }
        }
    }

    public static void FCheckPermission(final Activity activity) {
        Log.d("fatlin", "===FCheckPermission");
        int i = 0;
        while (true) {
            String[] strArr = requestPermissions;
            if (i < strArr.length) {
                pIndex = i;
                final String str = strArr[pIndex];
                if (!IsPermissionGranted(activity, str)) {
                    Log.d("fatlin", "===IsPermissionGranted false");
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(Util.GetStringByName("dtsh5_remind_title", activity));
                    builder.setMessage(Util.GetStringByName("dtsh5_remind_msg" + i, activity));
                    builder.setCancelable(false);
                    builder.setPositiveButton(Util.GetStringByName("dtsh5_remind_ok", activity), new DialogInterface.OnClickListener() { // from class: com.jiguang.h5.PermissionUtils.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            dialogInterface.dismiss();
                            PermissionUtils.m_PrePermisstionResult = ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
                            ActivityCompat.requestPermissions(activity, new String[]{str}, 100);
                        }
                    });
                    builder.show();
                    return;
                }
                i++;
            } else {
                isPermissionOk = true;
                return;
            }
        }
    }
}
