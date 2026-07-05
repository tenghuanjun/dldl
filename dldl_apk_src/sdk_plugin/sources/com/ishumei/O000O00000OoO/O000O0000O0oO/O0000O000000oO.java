package com.ishumei.O000O00000OoO.O000O0000O0oO;

import android.text.TextUtils;
import com.ishumei.O0000O000000oO.O000O0000Oo0O;
import com.ishumei.O0000O000000oO.O000O0000OoO;
import com.ishumei.O000O00000OoO.O000O0000O0oO.O000O00000OoO;
import com.ishumei.O000O0000OOoO.O000O00000oO;
import com.ishumei.smantifraud.SmAntiFraud;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private AtomicInteger O0000O000000oO;
    private O000O00000OoO O000O00000OoO;
    private Runnable O000O00000o0O;

    /* JADX INFO: renamed from: com.ishumei.O000O00000OoO.O000O0000O0oO.O0000O000000oO$O0000O000000oO, reason: collision with other inner class name */
    private static class C0044O0000O000000oO {
        private static final O0000O000000oO O0000O000000oO = new O0000O000000oO();
    }

    private O0000O000000oO() {
        this.O000O00000o0O = new Runnable() { // from class: com.ishumei.O000O00000OoO.O000O0000O0oO.O0000O000000oO.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.ishumei.O000O00000OoO.O000O00000OoO o000O00000OoOO000O00000o0O = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O();
                    if (o000O00000OoOO000O00000o0O != null && o000O00000OoOO000O00000o0O.O000O0000Oo0O()) {
                        O000O00000oO.O0000O000000oO("UploadChecker", "start check...");
                        if (O0000O000000oO.this.O000O00000OoO == null) {
                            return;
                        }
                        List<O000O00000OoO.O0000O000000oO> listO000O00000OoO = O0000O000000oO.this.O000O00000OoO.O000O00000OoO(2);
                        O000O00000oO.O0000O000000oO("UploadChecker", "load from db, size: " + listO000O00000OoO.size());
                        if (listO000O00000OoO.size() == 0) {
                            O0000O000000oO.this.O0000O000000oO(3);
                            return;
                        }
                        Iterator<O000O00000OoO.O0000O000000oO> it = listO000O00000OoO.iterator();
                        boolean z = true;
                        while (it.hasNext()) {
                            if (!O0000O000000oO.this.O0000O000000oO(it.next())) {
                                z = false;
                            }
                        }
                        O000O00000oO.O0000O000000oO("UploadChecker", "process result: " + z);
                        if (z) {
                            O0000O000000oO.this.O0000O000000oO(1);
                            return;
                        } else {
                            O0000O000000oO.this.O0000O000000oO(2);
                            return;
                        }
                    }
                    O000O00000oO.O0000O000000oO("UploadChecker", "disabled, return.");
                } catch (Throwable th) {
                    O000O00000oO.O0000O000000oO(th);
                }
            }
        };
        this.O0000O000000oO = new AtomicInteger(0);
        if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
            return;
        }
        this.O000O00000OoO = new O000O00000OoO();
    }

    private int O0000O000000oO(String str) {
        try {
            return new JSONObject(str).getInt("code");
        } catch (Exception unused) {
            return -1;
        }
    }

    public static O0000O000000oO O0000O000000oO() {
        return C0044O0000O000000oO.O0000O000000oO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O0000O000000oO(int i) {
        O000O00000oO.O0000O000000oO("UploadChecker", "process finish with state=" + i);
        if (i == 3) {
            O000O00000oO.O0000O000000oO("UploadChecker", "process end because empty db.");
            return;
        }
        if (i == 1) {
            this.O0000O000000oO.set(0);
            O000O00000oO.O0000O000000oO("UploadChecker", "success, process again. retryCount=" + this.O0000O000000oO.get());
        } else {
            if (i != 2) {
                return;
            }
            int iIncrementAndGet = this.O0000O000000oO.incrementAndGet();
            O000O00000oO.O0000O000000oO("UploadChecker", "failure, process again. retryCount=" + this.O0000O000000oO.get());
            if (iIncrementAndGet > 10000) {
                this.O0000O000000oO.set(10);
            }
        }
        O0000O000000oO(O000O00000o0O());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean O0000O000000oO(O000O00000OoO.O0000O000000oO o0000O000000oO) {
        try {
            O000O00000oO.O0000O000000oO("UploadChecker", "process id = " + o0000O000000oO.O0000O000000oO());
            String strO000O00000o0O = o0000O000000oO.O000O00000o0O();
            com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO2 = new com.ishumei.O000O0000O0oO.O0000O000000oO();
            o0000O000000oO2.O0000O000000oO();
            if (strO000O00000o0O.startsWith("https://")) {
                o0000O000000oO2.O0000O000000oO(0);
            } else {
                o0000O000000oO2.O0000O000000oO(1);
            }
            o0000O000000oO2.O0000O000000oO(strO000O00000o0O);
            String strO0000O000000oO = new com.ishumei.O000O0000O0oO.O000O00000OoO().O0000O000000oO(o0000O000000oO2).O0000O000000oO(o0000O000000oO.O000O00000OoO().getBytes("utf-8"), (Map<String, String>) null, strO000O00000o0O);
            String strO0000O000000oO2 = O000O0000Oo0O.O0000O000000oO().O0000O000000oO(strO0000O000000oO, true);
            if (!TextUtils.isEmpty(strO0000O000000oO2)) {
                O000O00000oO.O0000O000000oO("UploadChecker", "process id = " + o0000O000000oO.O0000O000000oO() + ", deviceId: " + strO0000O000000oO2);
                O000O0000OoO.O0000O000000oO().O0000O000000oO(strO0000O000000oO2);
                this.O000O00000OoO.O0000O000000oO(o0000O000000oO.O0000O000000oO());
                SmAntiFraud.IServerSmidCallback serverIdCallback = SmAntiFraud.getServerIdCallback();
                if (serverIdCallback != null) {
                    serverIdCallback.onSuccess(strO0000O000000oO2);
                }
            } else {
                if (O0000O000000oO(strO0000O000000oO) == 1903) {
                    return false;
                }
                this.O000O00000OoO.O0000O000000oO(o0000O000000oO.O0000O000000oO());
            }
            return true;
        } catch (Exception unused) {
            O000O00000oO.O0000O000000oO("UploadChecker", "failed.");
            return false;
        }
    }

    private long O000O00000o0O() {
        if (this.O0000O000000oO.get() > 9) {
            return 30000L;
        }
        if (this.O0000O000000oO.get() > 6) {
            return 15000L;
        }
        if (this.O0000O000000oO.get() > 3) {
            return OAIDHelper.TIMEOUT;
        }
        return 2000L;
    }

    public void O0000O000000oO(long j) {
        com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(this.O000O00000o0O, 4, j, true);
    }

    public void O0000O000000oO(final String str, final String str2) {
        com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(new Runnable() { // from class: com.ishumei.O000O00000OoO.O000O0000O0oO.O0000O000000oO.2
            @Override // java.lang.Runnable
            public void run() {
                com.ishumei.O000O00000OoO.O000O00000OoO o000O00000OoOO000O00000o0O = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O();
                if (o000O00000OoOO000O00000o0O == null || !o000O00000OoOO000O00000o0O.O000O0000Oo0O()) {
                    O000O00000oO.O0000O000000oO("UploadChecker", "disabled, return.");
                    return;
                }
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || O0000O000000oO.this.O000O00000OoO == null) {
                    return;
                }
                String string = str;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.put("retry", 1);
                    string = jSONObject.toString();
                } catch (Exception unused) {
                }
                O0000O000000oO.this.O000O00000OoO.O0000O000000oO(string, str2);
                O0000O000000oO.this.O000O00000OoO();
            }
        }, 4);
    }

    public void O000O00000OoO() {
        this.O0000O000000oO.set(0);
        O0000O000000oO(0L);
    }
}
