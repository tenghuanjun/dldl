package com.sq.tool.sqtools.utils;

import android.content.Context;
import android.hardware.SensorManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EmulatorUtils {
    public static boolean isEmulator() {
        try {
            if (!detectorQemu()) {
                if (!isEmulatorFromCpuAbi()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean detectorQemu() {
        return TextUtils.equals("1", getQemu());
    }

    public static String getQemu() {
        return SystemPropertiesUtil.getProperty("ro.kernel.qemu");
    }

    public static boolean isEmulatorFromCpuAbi() {
        String property = SystemPropertiesUtil.getProperty("ro.product.cpu.abi");
        return (property == null || TextUtils.isEmpty(property) || !property.contains("x86")) ? false : true;
    }

    public static String getEmulatorFromCpuAbi() {
        return SystemPropertiesUtil.getProperty("ro.product.cpu.abi");
    }

    public static String checkByHardware() {
        return SystemPropertiesUtil.getProperty("ro.hardware");
    }

    public static String checkFlavor() {
        return SystemPropertiesUtil.getProperty("ro.build.flavor");
    }

    public static String checkModel() {
        return SystemPropertiesUtil.getProperty("ro.product.model");
    }

    public static String checkManufacturer() {
        return SystemPropertiesUtil.getProperty("ro.product.manufacturer");
    }

    public static String checkBoard() {
        return SystemPropertiesUtil.getProperty("ro.product.board");
    }

    public static String checkPlatform() {
        return SystemPropertiesUtil.getProperty("ro.board.platform");
    }

    public static String checkFeaturesByBaseBand() {
        return SystemPropertiesUtil.getProperty("gsm.version.baseband");
    }

    public static int getSensorNumber(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (sensorManager == null) {
            return -1;
        }
        return sensorManager.getSensorList(-1).size();
    }

    public static boolean hasLightSensor(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        return sensorManager == null || sensorManager.getDefaultSensor(5) != null;
    }
}
