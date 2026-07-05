package com.duowan.live.common;

import android.content.Context;
import com.duowan.auk.util.L;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CpuUtils {
    private static final String CPU_ARCHITECTURE_KEY_64 = "ro.product.cpu.abilist64";
    public static final String CPU_ARCHITECTURE_TYPE_32 = "32";
    public static final String CPU_ARCHITECTURE_TYPE_64 = "64";
    private static final int EI_CLASS = 4;
    private static final int ELFCLASS32 = 1;
    private static final int ELFCLASS64 = 2;
    private static boolean LOGENABLE = true;
    private static final String PROC_CPU_INFO_PATH = "/proc/cpuinfo";
    private static final String SYSTEM_LIB_C_PATH = "/system/lib/libc.so";
    private static final String SYSTEM_LIB_C_PATH_64 = "/system/lib64/libc.so";
    private static final String TAG = "CpuUtils";

    public static boolean checkIfCPUx86() {
        return getSystemProperty("ro.product.cpu.abi", "arm").contains("x86");
    }

    public static String getArchType(Context context) {
        if (getSystemProperty(CPU_ARCHITECTURE_KEY_64, "").length() > 0) {
            if (LOGENABLE) {
                L.info(TAG, "getArchType CPU arch is 64bit");
            }
            return CPU_ARCHITECTURE_TYPE_64;
        }
        if (isCPUInfo64() || isLibc64()) {
            return CPU_ARCHITECTURE_TYPE_64;
        }
        if (!LOGENABLE) {
            return CPU_ARCHITECTURE_TYPE_32;
        }
        L.info(TAG, "getArchType return cpu DEFAULT 32bit!");
        return CPU_ARCHITECTURE_TYPE_32;
    }

    private static String getSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str2 = (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception e) {
            if (LOGENABLE) {
                L.error(TAG, "getSystemProperty key = " + str + ", error = " + e.getMessage());
            }
        }
        if (LOGENABLE) {
            L.info(TAG, "getSystemProperty" + str + " = " + str2);
        }
        return str2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x00a3 -> B:85:0x00bd). Please report as a decompilation issue!!! */
    private static boolean isCPUInfo64() {
        FileInputStream fileInputStream;
        Throwable th;
        BufferedReader bufferedReader;
        String line;
        File file = new File(PROC_CPU_INFO_PATH);
        if (!file.exists()) {
            return false;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream), 512);
                    try {
                        line = bufferedReader.readLine();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            if (LOGENABLE) {
                                L.error(TAG, "isCPUInfo64 read /proc/cpuinfo error = " + th.toString());
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fileInputStream == null) {
                                return false;
                            }
                            fileInputStream.close();
                        } finally {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (Throwable th4) {
                fileInputStream = null;
                th = th4;
                bufferedReader = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (line == null || line.length() <= 0 || !line.toLowerCase(Locale.US).contains("arch64")) {
            if (LOGENABLE) {
                L.info(TAG, "isCPUInfo64/proc/cpuinfo is not arch64");
            }
            try {
                bufferedReader.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            fileInputStream.close();
            return false;
        }
        if (LOGENABLE) {
            L.info(TAG, "isCPUInfo64/proc/cpuinfo contains is arch64");
        }
        try {
            bufferedReader.close();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            fileInputStream.close();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return true;
    }

    private static boolean isLibc64() {
        byte[] eLFHeadrIndentArray;
        byte[] eLFHeadrIndentArray2;
        File file = new File(SYSTEM_LIB_C_PATH);
        if (file.exists() && (eLFHeadrIndentArray2 = readELFHeadrIndentArray(file)) != null && eLFHeadrIndentArray2[4] == 2) {
            if (LOGENABLE) {
                L.info(TAG, "isLibc64/system/lib/libc.so is 64bit");
            }
            return true;
        }
        File file2 = new File(SYSTEM_LIB_C_PATH_64);
        if (!file2.exists() || (eLFHeadrIndentArray = readELFHeadrIndentArray(file2)) == null || eLFHeadrIndentArray[4] != 2) {
            return false;
        }
        if (LOGENABLE) {
            L.info(TAG, "isLibc64/system/lib64/libc.so is 64bit");
        }
        return true;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0041 -> B:40:0x0077). Please report as a decompilation issue!!! */
    private static byte[] readELFHeadrIndentArray(File file) {
        FileInputStream fileInputStream;
        if (file != null) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[16];
                        int i = fileInputStream.read(bArr, 0, 16);
                        if (i != 16) {
                            if (LOGENABLE) {
                                L.info(TAG, "readELFHeadrIndentArray Error: e_indent lenght should be 16, but actual is " + i);
                            }
                            fileInputStream.close();
                        } else {
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            return bArr;
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            if (LOGENABLE) {
                                L.error(TAG, "readELFHeadrIndentArray Error:" + th.toString());
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        } catch (Throwable th2) {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                }
            }
        }
        return null;
    }
}
