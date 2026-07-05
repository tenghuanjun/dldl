package com.ishumei.O000O00000oO;

import android.content.Context;
import android.os.Build;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.msdk.api.IMUrl;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000OOoO {
    private static O000O0000OOoO O0000O000000oO;
    private String[] O000O00000OoO = {"/dev/socket/qemud", "/dev/qemu_pipe"};
    private String[] O000O00000o0O = {"goldfish"};
    private String[] O000O00000oO = {"/sys/qemu_trace", "/system/bin/qemu-props"};
    private String[] O000O0000O0oO = {"000000000000000"};
    private String[] O000O0000OOoO = {"310260000000000"};
    private String[] O000O0000Oo0O = {"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};

    private O000O0000OOoO() {
    }

    private int O0000O000000oO(boolean z) {
        return z ? 1 : 0;
    }

    public static O000O0000OOoO O0000O000000oO() {
        if (O0000O000000oO == null) {
            synchronized (O000O0000OOoO.class) {
                if (O0000O000000oO == null) {
                    O0000O000000oO = new O000O0000OOoO();
                }
            }
        }
        return O0000O000000oO;
    }

    private boolean O000O00000o0O() {
        for (int i = 0; i < this.O000O00000OoO.length; i++) {
            try {
                if (new File(this.O000O00000OoO[i]).exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private boolean O000O00000oO() {
        FileInputStream fileInputStream;
        try {
            File file = new File("/proc/tty/drivers");
            if (file.exists() && file.canRead()) {
                byte[] bArr = new byte[(int) file.length()];
                FileInputStream fileInputStream2 = null;
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    fileInputStream.read(bArr);
                    String str = new String(bArr);
                    for (String str2 : this.O000O00000o0O) {
                        if (str.indexOf(str2) != -1) {
                            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
                            return true;
                        }
                    }
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
                } catch (Exception unused2) {
                    fileInputStream2 = fileInputStream;
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream2);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream2);
                    throw th;
                }
            }
        } catch (Throwable unused3) {
        }
        return false;
    }

    private boolean O000O0000O0oO() {
        for (int i = 0; i < this.O000O00000oO.length; i++) {
            try {
                if (new File(this.O000O00000oO[i]).exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private boolean O000O0000OOoO() {
        try {
            Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
            if (context == null || context.checkCallingPermission("android.permission.READ_PHONE_STATE") != 0) {
                return false;
            }
            String strO000O00000OoO = O00O0000o0OO.O0000O000000oO().O000O00000OoO();
            for (String str : this.O000O0000Oo0O) {
                if (str.equalsIgnoreCase(strO000O00000OoO)) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private boolean O000O0000Oo0O() {
        try {
            String strO000O00000o0O = O00O0000o0OO.O0000O000000oO().O000O00000o0O();
            for (String str : this.O000O0000O0oO) {
                if (str.equalsIgnoreCase(strO000O00000o0O)) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private boolean O000O0000OoO() {
        try {
            return O00O0000o0OO.O0000O000000oO().O000O00000oO().toLowerCase().equals(IMUrl.OS);
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean O00O0000OooO() {
        return "unknown".equals(Build.BOARD) || "unknown".equals(Build.BOOTLOADER) || "generic".equals(Build.BRAND) || "generic".equals(Build.DEVICE) || SqTrackCommonKey.sdk.equals(Build.MODEL) || SqTrackCommonKey.sdk.equals(Build.PRODUCT) || "goldfish".equals(Build.HARDWARE);
    }

    public String O000O00000OoO() {
        return String.format("%d%d%d%d%d%d%d", Integer.valueOf(O0000O000000oO(O000O00000o0O())), Integer.valueOf(O0000O000000oO(O000O00000oO())), Integer.valueOf(O0000O000000oO(O000O0000O0oO())), Integer.valueOf(O0000O000000oO(O000O0000OOoO())), Integer.valueOf(O0000O000000oO(O000O0000Oo0O())), Integer.valueOf(O0000O000000oO(O00O0000OooO())), Integer.valueOf(O0000O000000oO(O000O0000OoO())));
    }
}
