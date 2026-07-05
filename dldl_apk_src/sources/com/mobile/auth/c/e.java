package com.mobile.auth.c;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class e {
    private static final String a = e.class.getSimpleName();
    private static int b = 0;
    private static Map<String, b> c = new HashMap();
    private static List<String> d = new ArrayList();

    public static synchronized b a(String str) {
        b bVar;
        try {
            bVar = c.containsKey(str) ? c.get(str) : null;
            if (bVar == null) {
                bVar = new b(str);
                c.put(str, bVar);
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                return new b(str);
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return null;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return null;
                }
            }
        }
        return bVar;
    }

    static /* synthetic */ void a(Context context) {
        try {
            b(context);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public static void a(final Context context, String str) {
        try {
            synchronized (e.class) {
                if (c.containsKey(str)) {
                    d.add(c.get(str).toString());
                    c.remove(str);
                }
                if (b != 1 && !d.isEmpty()) {
                    b = 1;
                    new Timer().schedule(new TimerTask() { // from class: com.mobile.auth.c.e.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            try {
                                e.a(context);
                            } catch (Throwable th) {
                                try {
                                    ExceptionProcessor.processException(th);
                                } catch (Throwable th2) {
                                    ExceptionProcessor.processException(th2);
                                }
                            }
                        }
                    }, 8000L);
                }
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                }
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        int i = -1;
        String strOptString = "";
        try {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject = new JSONObject(str2);
                    i = jSONObject.getInt("result");
                    strOptString = jSONObject.optString("msg");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                a(str).a(i).f(strOptString);
            } else {
                a(str).a(i).f(strOptString).e(str3);
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            synchronized (e.class) {
                arrayList.addAll(d);
                b = 0;
                d.clear();
            }
            if (arrayList.isEmpty()) {
                return;
            }
            d.a(context, arrayList);
        } catch (Throwable th) {
            try {
                th.printStackTrace();
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                }
            }
        }
    }

    public static void b(Context context, String str) {
        try {
            d.a(context, str);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
