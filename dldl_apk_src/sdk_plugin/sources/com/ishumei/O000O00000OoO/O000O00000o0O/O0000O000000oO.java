package com.ishumei.O000O00000OoO.O000O00000o0O;

import android.content.SharedPreferences;
import com.ishumei.O000O00000OoO.O000O00000oO;
import com.ishumei.O000O0000OOoO.O000O0000OOoO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private SharedPreferences O0000O000000oO;
    private List<String> O000O00000OoO;

    /* JADX INFO: renamed from: com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO$O0000O000000oO, reason: collision with other inner class name */
    private static class C0043O0000O000000oO {
        private static final O0000O000000oO O0000O000000oO = new O0000O000000oO();
    }

    private O0000O000000oO() {
        this.O000O00000OoO = null;
        try {
            if (O000O00000oO.O0000O000000oO != null) {
                this.O0000O000000oO = O000O00000oO.O0000O000000oO.getSharedPreferences("fc_times", 0);
            }
        } catch (Throwable unused) {
        }
    }

    private int O0000O000000oO(int i) {
        int iAbs = Math.abs(i & 1048575);
        if (iAbs > 2880) {
            return 2880;
        }
        return iAbs;
    }

    public static O0000O000000oO O0000O000000oO() {
        return C0043O0000O000000oO.O0000O000000oO;
    }

    private int O000O00000OoO(int i) {
        int i2 = i >> 20;
        if (i2 > 100) {
            return 100;
        }
        return i2;
    }

    private int O000O00000OoO(int i, int i2) {
        return (i2 << 20) + i;
    }

    private void O000O00000oO() {
        HashSet hashSet = new HashSet(this.O0000O000000oO.getStringSet("t", new HashSet()));
        hashSet.add(String.valueOf(System.currentTimeMillis()));
        O000O0000OOoO.O0000O000000oO(this.O0000O000000oO, "t", hashSet);
    }

    private void O000O0000O0oO() {
        try {
            this.O000O00000OoO = new ArrayList(this.O0000O000000oO.getStringSet("t", new HashSet()));
            O000O0000OOoO.O0000O000000oO(this.O0000O000000oO, "l", System.currentTimeMillis());
            O000O0000OOoO.O0000O000000oO(this.O0000O000000oO, "t", new HashSet());
        } catch (Exception unused) {
        }
    }

    public synchronized void O0000O000000oO(int i, int i2) {
        try {
            if (this.O0000O000000oO == null) {
                return;
            }
            if (i <= 0 || i2 <= 0) {
                O000O0000OOoO.O0000O000000oO(this.O0000O000000oO, "n", 0);
            } else {
                O000O0000OOoO.O0000O000000oO(this.O0000O000000oO, "n", O000O00000OoO(i, i2));
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized boolean O000O00000OoO() {
        try {
            if (this.O0000O000000oO == null) {
                return true;
            }
            int i = this.O0000O000000oO.getInt("n", 0);
            if (i == 0) {
                O000O0000O0oO();
                return true;
            }
            if (((long) O0000O000000oO(i)) * 60000 < Math.abs(System.currentTimeMillis() - this.O0000O000000oO.getLong("l", 0L))) {
                O000O0000O0oO();
                return true;
            }
            if (this.O0000O000000oO.getStringSet("t", new HashSet()).size() >= O000O00000OoO(i)) {
                O000O0000O0oO();
                return true;
            }
            O000O00000oO();
            return false;
        } catch (Throwable unused) {
            O000O0000O0oO();
            return true;
        }
    }

    public synchronized List<String> O000O00000o0O() {
        ArrayList arrayList = new ArrayList();
        if (this.O000O00000OoO == null) {
            return arrayList;
        }
        arrayList.addAll(this.O000O00000OoO);
        this.O000O00000OoO = null;
        return arrayList;
    }
}
