package com.snail.antifake.deviceid.emulator;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.snail.antifake.deviceid.AndroidDeviceIMEIUtil;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.snail.antifake.deviceid.deviceid.DeviceIdUtil;
import com.snail.antifake.deviceid.deviceid.IPhoneSubInfoUtil;
import com.snail.antifake.deviceid.deviceid.ITelephonyUtil;
import com.snail.antifake.jni.PropertiesGet;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EmuCheckUtil {

    public interface CheckEmulatorCallBack {
        void onCheckFaild();

        void onCheckSuccess(boolean z);
    }

    public static boolean mayOnEmulator(Context context) {
        return mayOnEmulatorViaQEMU(context) || isEmulatorViaBuild(context) || isEmulatorFromAbi() || isEmulatorFromCpu();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean checkPermissionGranted(android.content.Context r5, java.lang.String r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            r2 = 1
            if (r0 < r1) goto L29
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch: java.lang.Exception -> L29
            java.lang.String r3 = r5.getPackageName()     // Catch: java.lang.Exception -> L29
            r4 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r4)     // Catch: java.lang.Exception -> L29
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch: java.lang.Exception -> L29
            int r0 = r0.targetSdkVersion     // Catch: java.lang.Exception -> L29
            if (r0 < r1) goto L23
            int r5 = r5.checkSelfPermission(r6)     // Catch: java.lang.Exception -> L29
            if (r5 != 0) goto L21
            goto L29
        L21:
            r2 = 0
            goto L29
        L23:
            int r5 = android.support.v4.content.PermissionChecker.checkSelfPermission(r5, r6)     // Catch: java.lang.Exception -> L29
            if (r5 != 0) goto L21
        L29:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.snail.antifake.deviceid.emulator.EmuCheckUtil.checkPermissionGranted(android.content.Context, java.lang.String):boolean");
    }

    public static boolean isEmulatorViaBuild(Context context) {
        if (!TextUtils.isEmpty(PropertiesGet.getString("ro.product.model")) && PropertiesGet.getString("ro.product.model").toLowerCase().contains(SqTrackCommonKey.sdk)) {
            return true;
        }
        if (TextUtils.isEmpty(PropertiesGet.getString("ro.product.manufacturer")) || !PropertiesGet.getString("ro.product.manufacture").toLowerCase().contains("unknown")) {
            return !TextUtils.isEmpty(PropertiesGet.getString("ro.product.device")) && PropertiesGet.getString("ro.product.device").toLowerCase().contains("generic");
        }
        return true;
    }

    public static boolean mayOnEmulatorViaQEMU(Context context) {
        return "1".equals(PropertiesGet.getString("ro.kernel.qemu"));
    }

    public static boolean isFakeEmulatorFromIMEI(Context context) {
        String deviceId;
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            deviceId = null;
        }
        return !TextUtils.isEmpty(deviceId) && TextUtils.isEmpty(IPhoneSubInfoUtil.getDeviceId(context)) && TextUtils.isEmpty(ITelephonyUtil.getDeviceId(context));
    }

    public static String getFinalIMEI(Context context) {
        return DeviceIdUtil.getDeviceId(context);
    }

    public static String getIMEIT(Context context) {
        return ITelephonyUtil.getDeviceId(context);
    }

    public static String getIMEIP(Context context) {
        return IPhoneSubInfoUtil.getDeviceId(context);
    }

    public static boolean hasQemuSocket() {
        return new File("/dev/socket/qemud").exists();
    }

    public static boolean hasQemuPipe() {
        return new File("/dev/socket/qemud").exists();
    }

    public static String getEmulatorQEMUKernel() {
        return PropertiesGet.getString("ro.kernel.qemu");
    }

    private static boolean isEmulatorFromCpu() {
        ShellAdbUtils.CommandResult commandResultExecCommand = ShellAdbUtils.execCommand("cat /proc/cpuinfo", false);
        String str = commandResultExecCommand == null ? "" : commandResultExecCommand.successMsg;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().contains("intel") || str.toLowerCase().contains("amd");
    }

    private static boolean isEmulatorFromAbi() {
        String cpuAbi = AndroidDeviceIMEIUtil.getCpuAbi();
        return !TextUtils.isEmpty(cpuAbi) && cpuAbi.contains("x86");
    }

    public static String getCpuInfo() {
        ShellAdbUtils.CommandResult commandResultExecCommand = ShellAdbUtils.execCommand("cat /proc/cpuinfo", false);
        return commandResultExecCommand == null ? "" : commandResultExecCommand.successMsg;
    }

    public static String getQEmuDriverFileString() {
        File file = new File("/proc/tty/drivers");
        StringBuilder sb = new StringBuilder();
        if (!file.exists() || !file.canRead()) {
            return "";
        }
        try {
            char[] cArr = new char[1024];
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            while (true) {
                int i = inputStreamReader.read(cArr, 0, 1024);
                if (i < 0) {
                    break;
                }
                sb.append(cArr, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
