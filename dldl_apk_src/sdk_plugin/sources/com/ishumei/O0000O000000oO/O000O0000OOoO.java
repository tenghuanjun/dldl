package com.ishumei.O0000O000000oO;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000OOoO {
    private static O000O0000OOoO O000O00000o0O;
    private Context O000O00000OoO;
    private int O0000O000000oO = 0;
    private com.ishumei.O000O00000o0O.O000O00000OoO O000O00000oO = new com.ishumei.O000O00000o0O.O000O00000OoO(true, 2, false, 10000, true) { // from class: com.ishumei.O0000O000000oO.O000O0000OOoO.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                O000O0000OOoO.this.O0000O000000oO("" + O000O0000OOoO.this.O0000O000000oO);
            } catch (Throwable th) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SeqManager", "setSettingSeq failed: " + th);
            }
            try {
                O000O0000OOoO.this.O000O00000OoO("" + O000O0000OOoO.this.O0000O000000oO);
            } catch (Throwable th2) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SeqManager", "setSharedPreferencesSeq failed: " + th2);
            }
        }
    };

    private O000O0000OOoO() {
        this.O000O00000OoO = null;
        this.O000O00000OoO = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
    }

    public static O000O0000OOoO O0000O000000oO() {
        if (O000O00000o0O == null) {
            synchronized (O000O0000OOoO.class) {
                if (O000O00000o0O == null) {
                    O000O00000o0O = new O000O0000OOoO();
                }
            }
        }
        return O000O00000o0O;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O0000O000000oO(String str) throws Exception {
        if (Build.VERSION.SDK_INT < 23) {
            try {
                if (this.O000O00000OoO == null) {
                    throw new Exception("mContext == null");
                }
                Settings.System.putString(this.O000O00000OoO.getContentResolver(), "com.shumei.seq", str);
                return;
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        throw new IOException("sdk " + Build.VERSION.SDK_INT + " less then 23");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O000O00000OoO(String str) throws Exception {
        int i = Build.VERSION.SDK_INT < 23 ? 2 : 0;
        try {
            if (this.O000O00000OoO == null) {
                throw new Exception("mContext == null");
            }
            SharedPreferences.Editor editorEdit = this.O000O00000OoO.getSharedPreferences("seq", i).edit();
            editorEdit.putString("seq", str);
            if (!editorEdit.commit()) {
                throw new IOException("editor commit failed");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private String O000O00000o0O() throws Exception {
        String string = "";
        try {
            if (this.O000O00000OoO == null) {
                throw new Exception("mContext = null");
            }
            try {
                string = Settings.System.getString(this.O000O00000OoO.getContentResolver(), "com.shumei.seq");
                if (com.ishumei.O000O0000OOoO.O000O0000Oo0O.O0000O000000oO(string)) {
                    throw new IOException("from setting empty id");
                }
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("SeqManager", "get seq from Settings failed: " + e);
            }
            return string;
        } catch (Exception e2) {
            throw new Exception(e2);
        }
    }

    private String O000O00000oO() throws Exception {
        try {
            int i = Build.VERSION.SDK_INT < 23 ? 3 : 0;
            if (this.O000O00000OoO == null) {
                throw new Exception("mContext == null");
            }
            String string = this.O000O00000OoO.getSharedPreferences("seq", i).getString("seq", null);
            if (com.ishumei.O000O0000OOoO.O000O0000Oo0O.O0000O000000oO(string)) {
                throw new Exception("from shared preference empty id");
            }
            return string;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private String O000O0000O0oO() {
        String strO000O00000oO;
        try {
            strO000O00000oO = O000O00000o0O();
            try {
                if (com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O00000OoO(strO000O00000oO)) {
                    return strO000O00000oO;
                }
            } catch (Exception unused) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("SeqManager", "getSeq from setting failed");
            }
        } catch (Exception unused2) {
            strO000O00000oO = null;
        }
        try {
            strO000O00000oO = O000O00000oO();
        } catch (Exception unused3) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("SeqManager", "getSeq sfrom shared perferences failed");
        }
        return com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O00000OoO(strO000O00000oO) ? strO000O00000oO : strO000O00000oO;
    }

    public synchronized String O000O00000OoO() {
        if (this.O0000O000000oO == 0) {
            String strO000O0000O0oO = null;
            try {
                strO000O0000O0oO = O000O0000O0oO();
            } catch (Exception unused) {
            }
            if (com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O00000OoO(strO000O0000O0oO)) {
                try {
                    this.O0000O000000oO = Integer.parseInt(strO000O0000O0oO);
                } catch (Exception unused2) {
                }
            }
        }
        this.O0000O000000oO++;
        this.O000O00000oO.O0000O000000oO();
        return "" + this.O0000O000000oO;
    }
}
