package com.huya.berry.gamesdk.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.hardware.Camera;
import android.media.AudioRecord;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.liveshow.huya.SqR;
import com.tencent.bugly.Bugly;
import com.youme.im.CommonConst;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PermissionTool {
    public static final String CACHE_CHECK_PERMISSION = "check_permission";
    public static final String CACHE_NULL_PERMISSION = "null_permission";
    public static final String IGNORE_DRAW_OVERLAYS = "ignoreDrawOverlays";
    private static final String TAG = "PermissionTool";

    public static List<PermissionState> getPermisson(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            PackageManager packageManager = context.getPackageManager();
            String str = packageManager.getPackageInfo(context.getPackageName(), 0).packageName;
            String[] strArr = packageManager.getPackageInfo(str, 4096).requestedPermissions;
            if (strArr == null) {
                return null;
            }
            for (String str2 : strArr) {
                arrayList.add(new PermissionState(str2, packageManager.getPermissionGroupInfo(packageManager.getPermissionInfo(str2, 0).group, 0).loadLabel(packageManager).toString(), packageManager.checkPermission(str2, str) == 0));
            }
        } catch (PackageManager.NameNotFoundException e) {
            L.error(TAG, "Could'nt retrieve permissions for package" + e.getMessage());
        }
        return arrayList;
    }

    public static boolean checkAVPermission() {
        int minBufferSize = AudioRecord.getMinBufferSize(CommonConst.SAMPLERATE_44K, 12, 2);
        boolean z = false;
        if (minBufferSize > 0) {
            try {
                AudioRecord audioRecord = new AudioRecord(1, CommonConst.SAMPLERATE_44K, 12, 2, minBufferSize);
                boolean z2 = audioRecord.getState() == 1;
                try {
                    audioRecord.startRecording();
                    if (audioRecord.getRecordingState() != 3) {
                        L.error(TAG, "no recoud permission");
                    } else {
                        z = true;
                    }
                    audioRecord.stop();
                    audioRecord.release();
                } catch (Exception e) {
                    e = e;
                    z = z2;
                    L.error(TAG, "" + e);
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        L.info(TAG, "checkAVPermission->:" + z);
        return z;
    }

    public static boolean testAVPermission() {
        AudioRecord audioRecord;
        boolean z;
        int minBufferSize = AudioRecord.getMinBufferSize(CommonConst.SAMPLERATE_44K, 12, 2);
        boolean z2 = false;
        if (minBufferSize > 0) {
            try {
                audioRecord = new AudioRecord(1, CommonConst.SAMPLERATE_44K, 12, 2, minBufferSize);
                z = audioRecord.getState() == 1;
            } catch (Exception e) {
                e = e;
            }
            try {
                audioRecord.startRecording();
                if (audioRecord.getRecordingState() != 3) {
                    L.error(TAG, "no recoud permission");
                } else {
                    z2 = true;
                }
            } catch (Exception e2) {
                e = e2;
                z2 = z;
                L.error(TAG, "" + e);
            }
        }
        L.info(TAG, "checkAVPermission->:" + z2);
        return z2;
    }

    public static String checkSelfPermission(Context context) {
        String str;
        String str2 = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            L.info(TAG, context.getPackageName() + " - Perssiom Count:" + strArr.length);
            str = "";
            for (int i = 0; i < strArr.length; i++) {
                try {
                    int iCheckSelfPermission = ContextCompat.checkSelfPermission(context, strArr[i]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(strArr[i]);
                    sb.append(" GRANTED:");
                    sb.append(iCheckSelfPermission == 0 ? "true" : Bugly.SDK_IS_DEV);
                    L.info(TAG, sb.toString());
                    if (iCheckSelfPermission != 0) {
                        try {
                            PermissionInfo permissionInfo = packageManager.getPermissionInfo(strArr[i], 0);
                            String string = packageManager.getPermissionGroupInfo(permissionInfo.group, 0).loadLabel(packageManager).toString();
                            String string2 = permissionInfo.loadLabel(packageManager).toString();
                            CharSequence charSequenceLoadDescription = permissionInfo.loadDescription(packageManager);
                            str = (str + string + "-" + string2 + "-" + (charSequenceLoadDescription == null ? "" : charSequenceLoadDescription.toString())) + ShellAdbUtils.COMMAND_LINE_END;
                        } catch (PackageManager.NameNotFoundException e) {
                            L.error(TAG, " NameNotFoundException> Name" + strArr[i]);
                            L.error(TAG, "" + e);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    e = e2;
                    str2 = str;
                    L.info(TAG, " checkSelfPermission->PERMISSION Name Not Found: " + e.toString());
                    str = (str2 + "NameNotFoundException") + ShellAdbUtils.COMMAND_LINE_END;
                    e.printStackTrace();
                    Config.getInstance(ArkValue.gContext).setString(CACHE_CHECK_PERMISSION, "1");
                    return str;
                }
            }
        } catch (PackageManager.NameNotFoundException e3) {
            e = e3;
        }
        Config.getInstance(ArkValue.gContext).setString(CACHE_CHECK_PERMISSION, "1");
        return str;
    }

    public static void checkInitPermission(Context context) {
        String strCheckSelfPermission = checkSelfPermission(context);
        String string = Config.getInstance(ArkValue.gContext).getString(CACHE_NULL_PERMISSION, "");
        try {
            if (!string.isEmpty()) {
                string = new String(string.getBytes(), "UTF-8");
            }
            if ((Integer.parseInt(Config.getInstance(ArkValue.gContext).getString(CACHE_CHECK_PERMISSION, "")) == 1 && string.compareTo(strCheckSelfPermission) == 0) || strCheckSelfPermission.isEmpty()) {
                return;
            }
            L.info(TAG, (context.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_perssiom_tips)) + ShellAdbUtils.COMMAND_LINE_END) + strCheckSelfPermission);
            String str = new String(strCheckSelfPermission.getBytes("UTF-8"), "UTF-8");
            if (str.isEmpty()) {
                return;
            }
            Config.getInstance(ArkValue.gContext).setString(CACHE_NULL_PERMISSION, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isIgnoreDrawOverlays() {
        return Config.getInstance(ArkValue.gContext).getBoolean(IGNORE_DRAW_OVERLAYS, false);
    }

    public static void setIgnoreDrawOverlays(boolean z) {
        Config.getInstance(ArkValue.gContext).setBoolean(IGNORE_DRAW_OVERLAYS, z);
    }

    public static boolean checkDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        boolean zCheckOp = checkOp(context, 24);
        if (VivoNotifyUtils.isVivo()) {
            return false;
        }
        return zCheckOp;
    }

    public static void showAlertDialog(Activity activity, String str) {
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(activity).setMessage(str).setPositiveButton(activity.getResources().getString(ResourceUtil.getStringResIDByName("hyberry_ok")), new DialogInterface.OnClickListener() { // from class: com.huya.berry.gamesdk.utils.PermissionTool.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        positiveButton.setCancelable(false);
        positiveButton.create().show();
    }

    public static boolean checkOp(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            try {
                Class.forName(appOpsManager.getClass().getName());
                return ((Integer) appOpsManager.getClass().getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception unused) {
            }
        }
        return true;
    }

    public static boolean checkCameraPermission(int i, int i2, boolean z, boolean z2) {
        try {
            Camera cameraOpenCamera = openCamera(i, i2, z, z2);
            if (!isHasPermission(cameraOpenCamera) || cameraOpenCamera == null) {
                return false;
            }
            releaseCamera(cameraOpenCamera);
            return true;
        } catch (Exception e) {
            L.error(TAG, "checkCameraPermission fail, e:" + e);
            return false;
        }
    }

    public static Camera openCamera(int i, int i2, boolean z, boolean z2) throws Exception {
        Camera cameraOpen;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int i3 = 0;
        while (true) {
            if (i3 >= numberOfCameras) {
                cameraOpen = null;
                break;
            }
            Camera.getCameraInfo(i3, cameraInfo);
            if (z) {
                if (cameraInfo.facing == 1) {
                    cameraOpen = Camera.open(i3);
                    break;
                }
                i3++;
            } else {
                if (cameraInfo.facing == 0) {
                    cameraOpen = Camera.open(i3);
                    break;
                }
                i3++;
            }
        }
        if (cameraOpen == null) {
            cameraOpen = Camera.open();
        }
        if (cameraOpen == null) {
            throw new RuntimeException("unable to open camera");
        }
        Camera.Parameters parameters = cameraOpen.getParameters();
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        cameraOpen.setParameters(parameters);
        cameraOpen.setDisplayOrientation(z2 ? 0 : 90);
        return cameraOpen;
    }

    public static void releaseCamera(Camera camera) {
        if (camera != null) {
            try {
                camera.stopPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
            camera.release();
        }
    }

    public static boolean isHasPermission(Camera camera) {
        try {
            Field declaredField = camera.getClass().getDeclaredField("mHasPermission");
            declaredField.setAccessible(true);
            return ((Boolean) declaredField.get(camera)).booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }
}
