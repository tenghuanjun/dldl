package com.ishumei.O000O00000oO;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O00O0000oO {
    private String O0000O000000oO;
    private String O000O00000OoO;
    private String O000O00000o0O;
    private String O000O00000oO;

    private static class O0000O000000oO {
        private static final O00O0000oO O0000O000000oO = new O00O0000oO();
    }

    private O00O0000oO() {
        try {
            this.O0000O000000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("89968d8b8a9e93");
            this.O000O00000OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("89968d8b8a9e939c918b");
            this.O000O00000o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("89968d8b8a9e938a969b");
            this.O000O00000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("89968d8b8a9e938f8d909c");
        } catch (Exception unused) {
        }
    }

    private int O0000O000000oO(boolean z) {
        return z ? 1 : 0;
    }

    public static O00O0000oO O0000O000000oO() {
        return O0000O000000oO.O0000O000000oO;
    }

    private String O0000O000000oO(int i) {
        Method method;
        if (Build.VERSION.SDK_INT > 27) {
            return String.format(Locale.CHINA, "u0_a%d", Integer.valueOf(i - 10000));
        }
        try {
            Field declaredField = Class.forName("libcore.io.Libcore").getDeclaredField("os");
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(null);
            if (obj != null && (method = obj.getClass().getMethod("getpwuid", Integer.TYPE)) != null) {
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                Object objInvoke = method.invoke(obj, Integer.valueOf(i));
                if (objInvoke != null) {
                    Field declaredField2 = objInvoke.getClass().getDeclaredField("pw_name");
                    if (!declaredField2.isAccessible()) {
                        declaredField2.setAccessible(true);
                    }
                    return (String) declaredField2.get(objInvoke);
                }
            }
            return null;
        } catch (Exception unused) {
            return String.format(Locale.CHINA, "u0_a%d", Integer.valueOf(i - 10000));
        }
    }

    private String O0000O000000oO(BufferedInputStream bufferedInputStream) {
        int i;
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[512];
        StringBuilder sb = new StringBuilder();
        do {
            try {
                i = bufferedInputStream.read(bArr);
                if (i > 0) {
                    sb.append(new String(bArr, 0, i));
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
            }
        } while (i >= 512);
        return sb.toString();
    }

    private String O0000O000000oO(String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        Process processExec;
        try {
            processExec = Runtime.getRuntime().exec(str);
            try {
                bufferedInputStream = new BufferedInputStream(processExec.getInputStream());
            } catch (Exception unused) {
                bufferedInputStream = null;
            } catch (Throwable th2) {
                bufferedInputStream = null;
                th = th2;
            }
        } catch (Exception unused2) {
            processExec = null;
            bufferedInputStream = null;
        } catch (Throwable th3) {
            bufferedInputStream = null;
            th = th3;
            processExec = null;
        }
        try {
            processExec.waitFor();
            String strO0000O000000oO = O0000O000000oO(bufferedInputStream);
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
            }
            if (processExec != null) {
                processExec.destroy();
            }
            return strO0000O000000oO;
        } catch (Exception unused3) {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e2);
                }
            }
            if (processExec != null) {
                processExec.destroy();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e3) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e3);
                }
            }
            if (processExec == null) {
                throw th;
            }
            processExec.destroy();
            throw th;
        }
    }

    private String O000O00000OoO() throws Throwable {
        int iIntValue = 0;
        try {
            String strO0000O000000oO = O0000O000000oO("cat /proc/self/cgroup");
            if (!TextUtils.isEmpty(strO0000O000000oO)) {
                int iLastIndexOf = strO0000O000000oO.lastIndexOf("uid");
                int iLastIndexOf2 = strO0000O000000oO.lastIndexOf("/pid");
                if (iLastIndexOf >= 0) {
                    if (iLastIndexOf2 <= 0) {
                        iLastIndexOf2 = strO0000O000000oO.length();
                    }
                    String strReplaceAll = strO0000O000000oO.substring(iLastIndexOf + 4, iLastIndexOf2).replaceAll(ShellAdbUtils.COMMAND_LINE_END, "");
                    if (O000O00000OoO(strReplaceAll)) {
                        iIntValue = Integer.valueOf(strReplaceAll).intValue();
                    }
                }
            }
        } catch (Exception unused) {
        }
        if (iIntValue == 0) {
            try {
                Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
                if (context != null) {
                    iIntValue = context.getApplicationInfo().uid;
                }
            } catch (Exception unused2) {
            }
        }
        if (iIntValue == 0) {
            return null;
        }
        return O0000O000000oO(iIntValue);
    }

    private void O000O00000OoO(Map<String, Object> map) throws Throwable {
        try {
            String strO000O00000OoO = O000O00000OoO();
            if (TextUtils.isEmpty(strO000O00000OoO)) {
                return;
            }
            String strO0000O000000oO = O0000O000000oO("ps");
            if (TextUtils.isEmpty(strO0000O000000oO)) {
                return;
            }
            String[] strArrSplit = strO0000O000000oO.split(ShellAdbUtils.COMMAND_LINE_END);
            if (strArrSplit.length <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                if (strArrSplit[i2].contains(strO000O00000OoO)) {
                    int iLastIndexOf = strArrSplit[i2].lastIndexOf(" ");
                    String strSubstring = strArrSplit[i2].substring(iLastIndexOf <= 0 ? 0 : iLastIndexOf + 1, strArrSplit[i2].length());
                    if (!TextUtils.isEmpty(strSubstring) && new File(String.format("/data/data/%s", strSubstring)).exists()) {
                        arrayList.add(strSubstring);
                        i++;
                    }
                }
            }
            String str = this.O0000O000000oO;
            Locale locale = Locale.CHINA;
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(O0000O000000oO(i > 1));
            map.put(str, String.format(locale, "%d", objArr));
            map.put(this.O000O00000OoO, String.format(Locale.CHINA, "%d", Integer.valueOf(i)));
            map.put(this.O000O00000o0O, strO000O00000OoO);
            map.put(this.O000O00000oO, arrayList);
        } catch (Exception unused) {
        }
    }

    private boolean O000O00000OoO(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void O0000O000000oO(Map<String, Object> map) throws Throwable {
        O000O00000OoO(map);
    }
}
