package com.ishumei.O000O00000oO;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000O0oO {
    private static O000O0000O0oO O000O00000oO;
    private Context O000O00000o0O;
    private String O0000O000000oO = "";
    private String O000O00000OoO = "";
    private final FileFilter O000O0000O0oO = new FileFilter() { // from class: com.ishumei.O000O00000oO.O000O0000O0oO.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            try {
                if (name.startsWith(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c8f8a"))) {
                    for (int i = 3; i < name.length(); i++) {
                        if (!Character.isDigit(name.charAt(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception unused) {
            }
            return false;
        }
    };

    private O000O0000O0oO() {
        this.O000O00000o0O = null;
        try {
            this.O000O00000o0O = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
            O000O0000Oo0O();
        } catch (Exception unused) {
        }
    }

    private int O0000O000000oO(String str) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            } catch (IOException unused) {
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (IOException unused2) {
            fileInputStream = null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            bufferedReader = null;
        }
        try {
            int iO000O00000OoO = O000O00000OoO(bufferedReader.readLine());
            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) bufferedReader);
            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
            return iO000O00000OoO;
        } catch (IOException unused3) {
            bufferedReader2 = bufferedReader;
            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) bufferedReader2);
            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
            return -1;
        } catch (Throwable th4) {
            th = th4;
            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) bufferedReader);
            com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
            throw th;
        }
    }

    private int O0000O000000oO(String str, FileInputStream fileInputStream) {
        byte[] bArr = new byte[1024];
        try {
            int i = fileInputStream.read(bArr);
            int i2 = 0;
            while (i2 < i) {
                if (bArr[i2] == 10 || i2 == 0) {
                    if (bArr[i2] == 10) {
                        i2++;
                    }
                    for (int i3 = i2; i3 < i; i3++) {
                        int i4 = i3 - i2;
                        if (bArr[i3] != str.charAt(i4)) {
                            break;
                        }
                        if (i4 == str.length() - 1) {
                            return O0000O000000oO(bArr, i3);
                        }
                    }
                }
                i2++;
            }
            return -1;
        } catch (IOException | NumberFormatException unused) {
            return -1;
        }
    }

    private int O0000O000000oO(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != 10) {
            if (Character.isDigit(bArr[i])) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }

    public static O000O0000O0oO O0000O000000oO() {
        if (O000O00000oO == null) {
            synchronized (O000O0000O0oO.class) {
                if (O000O00000oO == null) {
                    O000O00000oO = new O000O0000O0oO();
                }
            }
        }
        return O000O00000oO;
    }

    private int O000O00000OoO(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    private void O000O0000Oo0O() {
        try {
            Iterator<String> it = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O00000o0O(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08f8d909cd09c8f8a96919990")).iterator();
            while (it.hasNext()) {
                String[] strArrSplit = it.next().split(":");
                if (2 == strArrSplit.length) {
                    String strTrim = strArrSplit[0].trim();
                    String strTrim2 = strArrSplit[1].trim();
                    if (TextUtils.equals(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("979e8d9b889e8d9a"), strTrim) || TextUtils.equals(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("899a919b908da0969b"), strTrim)) {
                        this.O000O00000OoO = strTrim2;
                    } else if (TextUtils.equals(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("af8d909c9a8c8c908d"), strTrim) || TextUtils.equals(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("92909b9a93df919e929a"), strTrim)) {
                        this.O0000O000000oO = strTrim2;
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private int O000O0000OoO() {
        try {
            return new File(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad08f908c8c969d939a")).listFiles(this.O000O0000O0oO).length;
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
            return 0;
        }
    }

    public String O000O00000OoO() {
        return this.O0000O000000oO;
    }

    public String O000O00000o0O() {
        return this.O000O00000OoO;
    }

    public int O000O00000oO() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            int iO0000O000000oO = O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad08f908c8c969d939a"));
            if (iO0000O000000oO == -1) {
                iO0000O000000oO = O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad08f8d9a8c9a918b"));
            }
            return iO0000O000000oO == -1 ? O000O0000OoO() : iO0000O000000oO;
        } catch (SecurityException | Exception unused) {
            return -1;
        }
    }

    public int O000O0000O0oO() throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        int iIntValue = -1;
        for (int i = 0; i < O000O00000oO(); i++) {
            try {
                File file = new File(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08c868cd09b9a89969c9a8cd08c868c8b9a92d09c8f8ad09c8f8a") + i + com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d09c8f8a998d9a8ed09c8f8a96919990a0929e87a0998d9a8e"));
                if (file.exists()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        fileInputStream2.read(bArr);
                        int i2 = 0;
                        while (Character.isDigit(bArr[i2]) && i2 < 128) {
                            i2++;
                        }
                        Integer numValueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i2)));
                        if (numValueOf.intValue() > iIntValue) {
                            iIntValue = numValueOf.intValue();
                        }
                    } catch (NumberFormatException unused) {
                    } catch (Throwable th2) {
                        com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream2);
                        throw th2;
                    }
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream2);
                }
            } catch (Exception unused2) {
                return -1;
            }
        }
        if (iIntValue == -1) {
            try {
                fileInputStream = new FileInputStream(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08f8d909cd09c8f8a96919990"));
                try {
                    int iO0000O000000oO = O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c8f8adfb2b785"), fileInputStream) * 1000;
                    if (iO0000O000000oO > iIntValue) {
                        iIntValue = iO0000O000000oO;
                    }
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
                } catch (Throwable th3) {
                    th = th3;
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                fileInputStream = null;
                th = th4;
            }
        }
        return iIntValue;
    }

    public long O000O0000OOoO() throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        if (this.O000O00000o0O == null) {
            return 0L;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                ((ActivityManager) this.O000O00000o0O.getSystemService("activity")).getMemoryInfo(memoryInfo);
                return memoryInfo.totalMem;
            } catch (Exception unused) {
                return 0L;
            }
        }
        long jO0000O000000oO = -1;
        try {
            try {
                fileInputStream = new FileInputStream(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("d08f8d909cd0929a9296919990"));
                try {
                    jO0000O000000oO = ((long) O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("b29a92ab908b9e93"), fileInputStream)) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Closeable) fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
            }
        } catch (Exception unused2) {
        }
        return jO0000O000000oO;
    }
}
