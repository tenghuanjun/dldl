package com.mobile.auth.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.mobile.auth.d.e;
import com.mobile.auth.d.i;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.core.MobileNetRequestManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class a {
    private static final String a = a.class.getSimpleName();

    static /* synthetic */ String a() {
        try {
            return a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String a(Context context, String str, String str2, Network network) {
        try {
            return c(context, d.a(context, str, network), str2, network);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private String a(Context context, String str, String str2, String str3, Network network, String str4, String str5) {
        try {
            String strB = b();
            String strA = g.a(context, str, str2, str3, strB);
            com.mobile.auth.a.a.a(a, "request params : " + strA);
            String strA2 = d.a(context, "https://id6.me/auth/preauth.do", strA, network, str4, str5);
            com.mobile.auth.a.a.a(a, "request result : " + strA2);
            String strB2 = b(context, strA2, strB, network);
            if (TextUtils.isEmpty(strB2)) {
                return "{\"result\":80001,\"msg\":\"请求异常\"}";
            }
            com.mobile.auth.c.e.a(str5, strB2, strA);
            return strB2;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private static String a(Context context, List<String> list, String str, Network network) {
        String strA;
        JSONObject jSONObject;
        for (int i = 0; i < list.size(); i++) {
            try {
                try {
                    String str2 = list.get(i);
                    if (!TextUtils.isEmpty(list.get(i)) && context != null && Build.VERSION.SDK_INT < 21) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                        if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            ((Boolean) Class.forName("android.net.ConnectivityManager").getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(connectivityManager, 5, Integer.valueOf(e.a(e.b(str2))))).booleanValue();
                        }
                    }
                    strA = a(context, list.get(i), str, network);
                    try {
                        jSONObject = !TextUtils.isEmpty(strA) ? new JSONObject(strA) : null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (jSONObject != null && jSONObject.getInt("result") == 0) {
                    return strA;
                }
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }
        return null;
    }

    static /* synthetic */ String a(a aVar, Context context, String str, String str2, String str3, Network network, String str4, String str5) {
        try {
            return aVar.a(context, str, str2, str3, network, str4, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String a(String str, String str2) {
        try {
            return com.mobile.auth.b.a.c(str, str2);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private void a(final Context context, final String str, final i.a aVar, final int i, final com.mobile.auth.a.b bVar) {
        try {
            final Future futureB = i.a().b(aVar);
            i.a().a(new Runnable() { // from class: com.mobile.auth.d.a.4
                @Override // java.lang.Runnable
                public void run() {
                    Future future;
                    try {
                        try {
                            futureB.get(i, TimeUnit.MILLISECONDS);
                        } catch (Throwable th) {
                            try {
                                aVar.a(true);
                                if (th instanceof TimeoutException) {
                                    com.mobile.auth.c.e.a(str, "{\"result\":80000,\"msg\":\"请求超时\"}", "");
                                    com.mobile.auth.c.e.a(str).h("submitOnTimeoutInterrupted()");
                                    com.mobile.auth.a.a.b(context, "{\"result\":80000,\"msg\":\"请求超时\"}", str, bVar);
                                } else {
                                    com.mobile.auth.c.e.a(str, "{\"result\":80001,\"msg\":\"请求异常\"}", "");
                                    com.mobile.auth.c.e.a(str).h("submitOnTimeoutInterrupted other exception : " + th.getMessage());
                                    com.mobile.auth.a.a.a(a.a(), "submitOnTimeoutInterrupted other exception", th);
                                    com.mobile.auth.a.a.b(context, "{\"result\":80001,\"msg\":\"请求异常\"}", str, bVar);
                                }
                                if (futureB == null || futureB.isDone()) {
                                    return;
                                } else {
                                    future = futureB;
                                }
                            } catch (Throwable th2) {
                                if (futureB != null && !futureB.isDone()) {
                                    futureB.cancel(true);
                                }
                                throw th2;
                            }
                        }
                        if (futureB == null || futureB.isDone()) {
                            return;
                        }
                        future = futureB;
                        future.cancel(true);
                    } catch (Throwable th3) {
                        try {
                            ExceptionProcessor.processException(th3);
                        } catch (Throwable th4) {
                            ExceptionProcessor.processException(th4);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private String b() {
        try {
            String string = UUID.randomUUID().toString();
            if (TextUtils.isEmpty(string)) {
                return "";
            }
            String strReplace = string.replace("-", "");
            return strReplace.length() >= 16 ? strReplace.substring(0, 16) : strReplace;
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a(a, "generateAesKey error", th);
                return "";
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
    }

    private String b(Context context, String str, String str2, Network network) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt("result");
                String strOptString = jSONObject.optString(com.alipay.sdk.packet.e.k);
                if ((iOptInt == 0 || iOptInt == 30002) && !TextUtils.isEmpty(strOptString)) {
                    String strA = a(strOptString, str2);
                    if (!TextUtils.isEmpty(strA)) {
                        try {
                            jSONObject.put(com.alipay.sdk.packet.e.k, new JSONObject(strA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            jSONObject.put(com.alipay.sdk.packet.e.k, strA);
                        }
                        if (iOptInt != 30002) {
                            return jSONObject.toString();
                        }
                        JSONObject jSONObject2 = (JSONObject) jSONObject.opt(com.alipay.sdk.packet.e.k);
                        ArrayList arrayList = new ArrayList();
                        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("urls");
                        if (jSONArrayOptJSONArray != null) {
                            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                                arrayList.add(jSONArrayOptJSONArray.getString(i));
                            }
                        }
                        if (arrayList.isEmpty()) {
                            return null;
                        }
                        return a(context, arrayList, str2, network);
                    }
                }
                return jSONObject.toString();
            } catch (Throwable th) {
                com.mobile.auth.a.a.a(a, "decryptResult error", th);
                return null;
            }
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

    private static String c(Context context, String str, String str2, Network network) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt("result");
                String strOptString = jSONObject.optString(com.alipay.sdk.packet.e.k);
                if (iOptInt == 0 && !TextUtils.isEmpty(strOptString)) {
                    String strA = a(strOptString, str2);
                    if (!TextUtils.isEmpty(strA)) {
                        try {
                            jSONObject.put(com.alipay.sdk.packet.e.k, new JSONObject(strA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            jSONObject.put(com.alipay.sdk.packet.e.k, strA);
                        }
                    }
                }
                return jSONObject.toString();
            } catch (Throwable th) {
                com.mobile.auth.a.a.a(a, "decryptResult error", th);
                return null;
            }
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

    public void a(final Context context, final String str, final String str2, final String str3, final com.mobile.auth.a.b bVar) {
        try {
            int i = com.mobile.auth.a.a.b <= 0 ? 10000 : com.mobile.auth.a.a.b;
            final String strA = c.a();
            final String strA2 = c.a(context);
            com.mobile.auth.c.e.a(strA).a(str).b(strA2).d("preauth").c(f.f(context)).i(context.getPackageName());
            a(context, strA, new i.a() { // from class: com.mobile.auth.d.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strA3 = a.a(a.this, context, str, str2, str3, null, strA2, strA);
                        if (a()) {
                            return;
                        }
                        com.mobile.auth.a.a.b(context, strA3, strA, bVar);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            }, i, bVar);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void b(final Context context, final String str, final String str2, final String str3, final com.mobile.auth.a.b bVar) {
        try {
            int i = com.mobile.auth.a.a.b <= 0 ? 10000 : com.mobile.auth.a.a.b;
            final String strA = c.a();
            final String strA2 = c.a(context);
            com.mobile.auth.c.e.a(strA).a(str).b(strA2).d("preauth").c(f.f(context)).i(context.getPackageName());
            if (Build.VERSION.SDK_INT < 21) {
                a(context, strA, new i.a() { // from class: com.mobile.auth.d.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (new e().a(context, "https://id6.me/auth/preauth.do")) {
                                if (a()) {
                                    return;
                                }
                                String strA3 = a.a(a.this, context, str, str2, str3, null, strA2, strA);
                                if (a()) {
                                } else {
                                    com.mobile.auth.a.a.b(context, strA3, strA, bVar);
                                }
                            } else if (a()) {
                            } else {
                                com.mobile.auth.a.a.b(context, h.a(MobileNetRequestManager.CODE_SWITCH_TIMEOUT, MobileNetRequestManager.MSG_SWITCH_TIMEOUT), strA, bVar);
                            }
                        } catch (Throwable th) {
                            try {
                                ExceptionProcessor.processException(th);
                            } catch (Throwable th2) {
                                ExceptionProcessor.processException(th2);
                            }
                        }
                    }
                }, i, bVar);
                return;
            }
            e eVar = new e();
            eVar.a(context, new e.a() { // from class: com.mobile.auth.d.a.2
                private boolean i = false;
                private boolean j = false;

                @Override // com.mobile.auth.d.e.a
                public synchronized void a() {
                    try {
                        this.i = true;
                        if (!this.j) {
                            com.mobile.auth.c.e.a(strA, "{\"result\":80000,\"msg\":\"请求超时\"}", "");
                            com.mobile.auth.a.a.b(context, "{\"result\":80000,\"msg\":\"请求超时\"}", strA, bVar);
                        }
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.d.e.a
                public synchronized void a(int i2, String str4, long j) {
                    try {
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                    if (!this.i && !this.j) {
                        this.j = true;
                        com.mobile.auth.c.e.a(strA).h("switchToMobile_L  onFail()  expendTime : " + j).a(i2).f(str4).b(j);
                        com.mobile.auth.a.a.b(context, h.a(i2, str4), strA, bVar);
                        com.mobile.auth.a.a.a(a.a(), "Switching network failed (L), errorMsg :" + str4 + " , expendTime ：" + j);
                    }
                }

                @Override // com.mobile.auth.d.e.a
                public void a(Network network, long j) {
                    try {
                        com.mobile.auth.a.a.a(a.a(), "Switching network successfully (L) , expendTime ：" + j);
                        if (!this.i && !this.j) {
                            com.mobile.auth.c.e.a(strA).b(j);
                            String strA3 = a.a(a.this, context, str, str2, str3, network, strA2, strA);
                            synchronized (this) {
                                if (!this.i && !this.j) {
                                    this.j = true;
                                    com.mobile.auth.a.a.b(context, strA3, strA, bVar);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            });
            eVar.a(i);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
