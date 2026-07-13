package com.cy.yyjia.zhe28.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.volcengine.common.contant.InternalConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes3.dex */
public class IsPhoneTool {
    private static final String[] known_pipes = {"/dev/socket/qemud", "/dev/qemu_pipe"};

    public static boolean isEmulator() {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            String str = (String) declaredMethod.invoke(new Build(), "ro.product.cpu.abi");
            Log.i("Jni", "CPU: " + str);
            return str.contains(InternalConstants.ABI_x86_str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean notHasBlueTooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return true;
        }
        return TextUtils.isEmpty(defaultAdapter.getName());
    }

    public static Boolean notHasLightSensorManager(Context context) {
        return Boolean.valueOf(((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) == null);
    }

    public static boolean isFeatures() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean checkIsNotRealPhone() {
        String cpuInfo = readCpuInfo();
        return cpuInfo.contains("intel") || cpuInfo.contains("amd");
    }

    public static String readCpuInfo() {
        try {
            Process processStart = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream(), StandardCharsets.UTF_8));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    stringBuffer.append(line);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString().toLowerCase();
                }
            }
        } catch (IOException unused) {
            return "";
        }
    }

    public static boolean checkPipes() {
        int i = 0;
        while (true) {
            String[] strArr = known_pipes;
            if (i < strArr.length) {
                if (new File(strArr[i]).exists()) {
                    Log.v("Result:", "Find pipes!");
                    return true;
                }
                i++;
            } else {
                Log.i("Result:", "Not Find pipes!");
                return false;
            }
        }
    }
}
