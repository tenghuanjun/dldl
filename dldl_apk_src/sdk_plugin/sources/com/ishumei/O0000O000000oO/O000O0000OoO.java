package com.ishumei.O0000O000000oO;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import com.ishumei.dfp.SMSDK;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000OoO {
    private static O000O0000OoO O000O0000OOoO;
    private Map<String, Integer> O0000O000000oO = new HashMap();
    private Map<String, Integer> O000O00000OoO = new HashMap();
    private String O000O00000o0O = null;
    private String O000O00000oO = null;
    private List<O000O00000oO> O000O0000O0oO = new LinkedList();
    private com.ishumei.O000O00000o0O.O000O00000OoO O000O0000Oo0O = new com.ishumei.O000O00000o0O.O000O00000OoO(true, 2, true, 0, true) { // from class: com.ishumei.O0000O000000oO.O000O0000OoO.2
        int O0000O000000oO = 0;

        @Override // java.lang.Runnable
        public void run() {
            int i;
            synchronized (O000O0000OoO.this) {
                for (O000O00000oO o000O00000oO : O000O0000OoO.this.O000O0000O0oO) {
                    try {
                        o000O00000oO.O0000O000000oO(O000O0000OoO.this.O000O00000oO);
                        O000O0000OoO.this.O0000O000000oO.put(o000O00000oO.O000O00000o0O, 0);
                    } catch (Exception unused) {
                        O000O0000OoO.this.O0000O000000oO.put(o000O00000oO.O000O00000o0O, 1);
                    }
                }
                i = this.O0000O000000oO + 1;
                this.O0000O000000oO = i;
            }
            if (i < 3) {
                this.O000O00000oO = true;
                this.O000O0000O0oO = false;
                this.O000O0000OOoO = 15000L;
                this.O000O0000Oo0O = false;
                O0000O000000oO();
                return;
            }
            this.O000O00000oO = false;
            this.O000O0000O0oO = true;
            this.O000O0000OOoO = 0L;
            this.O000O0000Oo0O = true;
            this.O0000O000000oO = 0;
        }
    };

    private class O0000O000000oO extends O000O00000oO {
        public O0000O000000oO() {
            super();
            this.O000O00000OoO = 4;
            this.O000O00000o0O = "sdcard";
            this.O000O00000oO = 4;
        }

        private String O000O00000oO() {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        private String O000O0000O0oO() {
            String strO000O00000oO = O000O00000oO();
            File file = new File(strO000O00000oO, "shumei.txt");
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("SmidManager", "exter store:" + file.getAbsolutePath());
            try {
                return com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(file);
            } catch (Exception unused) {
                File file2 = new File(strO000O00000oO);
                if (!file2.canRead()) {
                    return "";
                }
                File[] fileArrListFiles = file2.listFiles();
                int length = fileArrListFiles.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    File file3 = fileArrListFiles[i];
                    int i3 = i2 + 1;
                    if (i2 < 30 && file3.isDirectory() && file3.canWrite()) {
                        File file4 = new File(file3, ".thumbcache_idx0");
                        if (file4.canRead()) {
                            try {
                                return com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(file4);
                            } catch (Exception unused2) {
                                continue;
                                i++;
                                i2 = i3;
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                    i2 = i3;
                }
                return "";
            }
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public String O0000O000000oO() {
            return O000O0000O0oO();
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public void O0000O000000oO(String str) throws Exception {
            O000O00000OoO(str);
        }

        public void O000O00000OoO(String str) throws Exception {
            String strO000O00000oO = O000O00000oO();
            File file = new File(strO000O00000oO);
            if (!file.canWrite() || !file.canRead()) {
                throw new Exception("sv failed");
            }
            int i = 0;
            for (File file2 : file.listFiles()) {
                if (i < 10) {
                    i++;
                } else if (i < 15 && file2.isDirectory() && file2.canWrite()) {
                    try {
                        com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(new File(file2, ".thumbcache_idx0"), str);
                    } catch (Exception unused) {
                    }
                    i++;
                }
            }
            try {
                com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(new File(strO000O00000oO, "shumei.txt"), str);
            } catch (Exception unused2) {
                throw new Exception("sv failed");
            }
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public boolean O000O00000OoO() {
            return O000O00000o0O();
        }

        public boolean O000O00000o0O() {
            String strO000O00000oO = O000O00000oO();
            File file = new File(strO000O00000oO);
            if (!file.canWrite()) {
                return false;
            }
            int i = 0;
            for (File file2 : file.listFiles()) {
                if (i < 30 && file2.isDirectory() && file2.canWrite()) {
                    try {
                        new File(file2, ".thumbcache_idx0").delete();
                    } catch (Exception unused) {
                    }
                    i++;
                }
            }
            try {
                new File(strO000O00000oO, "shumei.txt").delete();
                return true;
            } catch (Exception unused2) {
                return false;
            }
        }
    }

    private class O000O00000OoO extends O000O00000oO {
        public O000O00000OoO() {
            super();
            this.O000O00000OoO = 3;
            this.O000O00000o0O = "setting";
            this.O000O00000oO = 1;
        }

        private String O000O00000oO() {
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SmidManager", "mContext == null:\n" + Thread.getAllStackTraces());
            }
            String string = "";
            try {
                string = Settings.System.getString(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getContentResolver(), "com.shumei.deviceid");
                com.ishumei.O000O0000OOoO.O000O0000Oo0O.O0000O000000oO(string);
                return string;
            } catch (Exception unused) {
                return string;
            }
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public String O0000O000000oO() {
            return O000O00000oO();
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public void O0000O000000oO(String str) throws Exception {
            O000O00000OoO(str);
        }

        public void O000O00000OoO(String str) throws Exception {
            if (Build.VERSION.SDK_INT >= 23) {
                return;
            }
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
                throw new Exception("sv failed");
            }
            try {
                Settings.System.putString(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getContentResolver(), "com.shumei.deviceid", str);
            } catch (Exception unused) {
                throw new Exception("sv failed");
            }
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public boolean O000O00000OoO() {
            return O000O00000o0O();
        }

        public boolean O000O00000o0O() {
            if (Build.VERSION.SDK_INT >= 23) {
                return true;
            }
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
                return false;
            }
            try {
                Settings.System.putString(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getContentResolver(), "com.shumei.deviceid", null);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    private class O000O00000o0O extends O000O00000oO {
        public O000O00000o0O() {
            super();
            this.O000O00000OoO = 2;
            this.O000O00000o0O = "sharedpref";
            this.O000O00000oO = 2;
        }

        private String O000O00000oO() {
            int i = Build.VERSION.SDK_INT < 23 ? 3 : 0;
            Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
            String string = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getSharedPreferences("com.shumei", i).getString("deviceid", "");
            com.ishumei.O000O0000OOoO.O000O0000Oo0O.O0000O000000oO(string);
            return string;
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public String O0000O000000oO() {
            return O000O00000oO();
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public void O0000O000000oO(String str) throws Exception {
            O000O00000OoO(str);
        }

        public void O000O00000OoO(String str) throws Exception {
            int i = Build.VERSION.SDK_INT < 23 ? 2 : 0;
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
                throw new Exception("sv failed");
            }
            SharedPreferences.Editor editorEdit = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getSharedPreferences("com.shumei", i).edit();
            editorEdit.putString("deviceid", str);
            if (!editorEdit.commit()) {
                throw new Exception("sv failed");
            }
        }

        @Override // com.ishumei.O0000O000000oO.O000O0000OoO.O000O00000oO
        public boolean O000O00000OoO() {
            return O000O00000o0O();
        }

        public boolean O000O00000o0O() {
            int i = Build.VERSION.SDK_INT < 23 ? 2 : 0;
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
                return false;
            }
            SharedPreferences.Editor editorEdit = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getSharedPreferences("com.shumei", i).edit();
            editorEdit.remove("deviceid");
            return editorEdit.commit();
        }
    }

    private static abstract class O000O00000oO implements Comparable {
        public int O000O00000OoO;
        public String O000O00000o0O;
        public int O000O00000oO;

        private O000O00000oO() {
            this.O000O00000OoO = 0;
            this.O000O00000o0O = null;
            this.O000O00000oO = 0;
        }

        public abstract String O0000O000000oO();

        public abstract void O0000O000000oO(String str);

        public abstract boolean O000O00000OoO();

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            if (obj instanceof O000O00000oO) {
                return ((O000O00000oO) obj).O000O00000OoO - this.O000O00000OoO;
            }
            return 0;
        }
    }

    private O000O0000OoO() {
        try {
            O0000O000000oO(new O000O00000OoO());
            O0000O000000oO(new O000O00000o0O());
            O0000O000000oO(new O0000O000000oO());
            O000O0000Oo0O();
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SmidManager", "SmidManager constructor failed: " + e);
        }
    }

    public static O000O0000OoO O0000O000000oO() {
        if (O000O0000OOoO == null) {
            synchronized (O000O0000OoO.class) {
                if (O000O0000OOoO == null) {
                    O000O0000OOoO = new O000O0000OoO();
                }
            }
        }
        return O000O0000OOoO;
    }

    private synchronized void O0000O000000oO(O000O00000oO o000O00000oO) {
        this.O000O0000O0oO.add(o000O00000oO);
    }

    private void O000O0000Oo0O() {
        Collections.sort(this.O000O0000O0oO, new Comparator<O000O00000oO>() { // from class: com.ishumei.O0000O000000oO.O000O0000OoO.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: O0000O000000oO, reason: merged with bridge method [inline-methods] */
            public int compare(O000O00000oO o000O00000oO, O000O00000oO o000O00000oO2) {
                return o000O00000oO2.O000O00000OoO - o000O00000oO.O000O00000OoO;
            }
        });
    }

    public synchronized void O0000O000000oO(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                if (!TextUtils.equals(this.O000O00000oO, str)) {
                    this.O000O00000oO = str;
                    this.O000O0000Oo0O.O0000O000000oO();
                }
            }
        }
    }

    public Map<String, Object> O000O00000OoO() {
        HashMap map = new HashMap();
        map.put("smid", O000O00000o0O());
        map.put("smidFrom", this.O000O00000o0O);
        map.put("smidReads", this.O000O00000OoO);
        map.put("smidWrites", this.O0000O000000oO);
        return map;
    }

    public synchronized String O000O00000o0O() {
        if (!TextUtils.isEmpty(this.O000O00000oO)) {
            return this.O000O00000oO;
        }
        for (O000O00000oO o000O00000oO : this.O000O0000O0oO) {
            try {
                String strO0000O000000oO = o000O00000oO.O0000O000000oO();
                if (TextUtils.isEmpty(strO0000O000000oO)) {
                    this.O000O00000OoO.put(o000O00000oO.O000O00000o0O, 1);
                } else if (strO0000O000000oO.length() == 62) {
                    this.O000O00000oO = strO0000O000000oO;
                    this.O000O00000o0O = "read";
                    this.O000O00000OoO.put(o000O00000oO.O000O00000o0O, 0);
                    return strO0000O000000oO;
                }
            } catch (Throwable th) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SmidManager", "getSmid failed: " + th);
                this.O000O00000OoO.put(o000O00000oO.O000O00000o0O, 1);
            }
        }
        return "";
    }

    public synchronized Map<String, List<Integer>> O000O00000oO() {
        HashMap map;
        List arrayList;
        map = new HashMap();
        Iterator<O000O00000oO> it = this.O000O0000O0oO.iterator();
        while (it.hasNext()) {
            O000O00000oO next = it.next();
            String strO0000O000000oO = null;
            try {
                strO0000O000000oO = next.O0000O000000oO();
                if (TextUtils.isEmpty(strO0000O000000oO)) {
                    this.O000O00000OoO.put(next.O000O00000o0O, 1);
                    arrayList = (List) map.get(strO0000O000000oO);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(Integer.valueOf(next.O000O00000oO));
                } else if (strO0000O000000oO.length() != 62) {
                    arrayList = (List) map.get(strO0000O000000oO);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(Integer.valueOf(next.O000O00000oO));
                } else {
                    this.O000O00000OoO.put(next.O000O00000o0O, 0);
                    this.O000O00000o0O = "read";
                    arrayList = (List) map.get(strO0000O000000oO);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(Integer.valueOf(next.O000O00000oO));
                }
            } finally {
                try {
                } finally {
                }
            }
            map.put(strO0000O000000oO, arrayList);
        }
        return map;
    }

    public boolean O000O0000O0oO() {
        boolean z;
        synchronized (this) {
            z = true;
            for (O000O00000oO o000O00000oO : this.O000O0000O0oO) {
                try {
                    if (o000O00000oO.O000O00000OoO()) {
                        this.O0000O000000oO.put(o000O00000oO.O000O00000o0O, 1);
                        this.O000O00000OoO.put(o000O00000oO.O000O00000o0O, 1);
                    } else {
                        z = false;
                    }
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SmidManager", "delete smid failed: " + e);
                }
            }
        }
        this.O000O00000oO = "";
        return z;
    }

    public String O000O0000OOoO() throws IOException {
        String strZ2 = SMSDK.z2(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO);
        if (!com.ishumei.O000O0000OOoO.O000O0000Oo0O.O0000O000000oO(strZ2) && com.ishumei.O000O0000OOoO.O000O0000Oo0O.O0000O000000oO(this.O000O00000oO)) {
            this.O000O00000o0O = "gen";
        }
        return strZ2;
    }
}
