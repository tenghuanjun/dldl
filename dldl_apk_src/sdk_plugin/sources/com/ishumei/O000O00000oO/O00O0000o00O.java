package com.ishumei.O000O00000oO;

import android.content.Context;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O00O0000o00O {
    private static O00O0000o00O O000O00000OoO;
    private Context O0000O000000oO;

    private O00O0000o00O() {
        this.O0000O000000oO = null;
        try {
            this.O0000O000000oO = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
        } catch (Exception unused) {
        }
    }

    public static O00O0000o00O O0000O000000oO() {
        if (O000O00000OoO == null) {
            synchronized (O00O0000o00O.class) {
                if (O000O00000OoO == null) {
                    O000O00000OoO = new O00O0000o00O();
                }
            }
        }
        return O000O00000OoO;
    }

    public String O000O00000OoO() {
        try {
            if (this.O0000O000000oO == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O000O00000OoO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd18f8d9089969b9a8dd1ac9a8b8b9691988cdbac9a9c8a8d9a")).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac8b8d969198")).O0000O000000oO(this.O0000O000000oO.getContentResolver(), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969ba0969b"));
            return str == null ? "" : str;
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
            return "";
        }
    }

    public long O000O00000o0O() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public String O000O00000oO() {
        int i;
        int i2;
        int i3;
        Context context = this.O0000O000000oO;
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            try {
                i2 = displayMetrics.heightPixels;
                try {
                    i3 = displayMetrics.densityDpi;
                } catch (Exception unused) {
                    i3 = 0;
                }
            } catch (Exception unused2) {
                i2 = 0;
                i3 = 0;
                return String.format(Locale.CHINA, "%d,%d,%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            }
        } catch (Exception unused3) {
            i = 0;
        }
        return String.format(Locale.CHINA, "%d,%d,%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public int O000O0000O0oO() {
        if (this.O0000O000000oO == null) {
            return -1;
        }
        try {
            return ((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O000O00000OoO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd18f8d9089969b9a8dd1ac9a8b8b9691988cdbac868c8b9a92")).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb6918b")).O0000O000000oO(this.O0000O000000oO.getContentResolver(), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c9c8d9a9a91a09d8d9698978b919a8c8c"))).intValue();
        } catch (SecurityException | net.vidageek.O0000O000000oO.O000O00000o0O.O000O00000OoO unused) {
            return -1001;
        } catch (Exception unused2) {
            return -1;
        }
    }
}
