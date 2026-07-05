package com.mobile.auth.aa;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.zoloz.toyger.ToygerBaseService;
import com.mobile.auth.aa.a;
import com.mobile.auth.ab.e;
import com.mobile.auth.ab.f;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.tencent.connect.common.Constants;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b {
    private static volatile b a;
    private Handler b = new Handler(Looper.getMainLooper());
    private a c = new a();

    private b() {
    }

    public static b a() {
        try {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b();
                    }
                }
            }
            return a;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private String a(Context context, int i, String str) {
        try {
            try {
                String packageName = context.getPackageName();
                String strB = f.b(context, context.getPackageName());
                if (packageName == null) {
                    packageName = "";
                }
                if (strB == null) {
                    strB = "";
                }
                String strA = e.a();
                String str2 = i != 2 ? "1" : "";
                String str3 = "" + System.currentTimeMillis();
                String strA2 = com.mobile.auth.y.b.a(f.b(context).getBytes());
                String strDecode = URLDecoder.decode(f.d(str), "utf-8");
                String strA3 = f.a(str2 + strA + "30100jsonp" + strA2 + strDecode + packageName + strB + str3 + "4.5.0AR02B1217" + e.b());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constants.PARAM_CLIENT_ID, strA);
                jSONObject.put("client_type", "30100");
                jSONObject.put("format", "jsonp");
                jSONObject.put("version", "4.5.0AR02B1217");
                if (i != 2) {
                    jSONObject.put("business_type", str2);
                }
                jSONObject.put("packname", packageName);
                jSONObject.put("packsign", URLEncoder.encode(strB, "utf-8"));
                jSONObject.put("timeStamp", str3);
                jSONObject.put(ToygerBaseService.KEY_RES_9_KEY, strDecode);
                jSONObject.put("fp", strA2);
                jSONObject.put("sign", strA3);
                return jSONObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private HashMap<String, String> a(Context context) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("model", Build.MODEL);
            map.put("system", Build.VERSION.RELEASE);
            map.put(MonitorConstants.EXTRA_DEVICE_ID, f.c(context));
            return map;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private void a(Context context, int i, String str, String str2, Network network, final c cVar) {
        try {
            try {
                this.c.a(str2 + com.mobile.auth.ab.b.a(a(context, i, str), com.alipay.sdk.sys.a.b), a(context), network, new a.InterfaceC0062a() { // from class: com.mobile.auth.aa.b.1
                    @Override // com.mobile.auth.aa.a.InterfaceC0062a
                    public void a(String str3) {
                        try {
                            com.mobile.auth.ab.a.a().b();
                            if (TextUtils.isEmpty(str3)) {
                                cVar.a(2, "");
                            } else {
                                cVar.a(1, str3);
                            }
                        } catch (Throwable th) {
                            try {
                                com.mobile.auth.gatewayauth.a.a(th);
                            } catch (Throwable th2) {
                                com.mobile.auth.gatewayauth.a.a(th2);
                            }
                        }
                    }
                });
            } catch (Exception unused) {
                cVar.a(2, "");
                com.mobile.auth.ab.a.a().b();
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ void a(b bVar, Context context, int i, String str, String str2, Network network, c cVar) {
        try {
            bVar.a(context, i, str, str2, network, cVar);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:13:0x0043
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public void a(final android.content.Context r10, final int r11, final java.lang.String r12, final com.mobile.auth.aa.c r13) {
        /*
            r9 = this;
            r0 = 2
            android.content.Context r1 = r10.getApplicationContext()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            int r1 = com.mobile.auth.ab.f.a(r1)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2 = 1
            if (r1 != r2) goto L1d
            android.os.Handler r1 = r9.b     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            com.mobile.auth.aa.b$2 r8 = new com.mobile.auth.aa.b$2     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r2.<init>()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r1.post(r8)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L58
        L1d:
            android.content.Context r1 = r10.getApplicationContext()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            int r1 = com.mobile.auth.ab.f.a(r1)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            if (r1 != 0) goto L33
            java.lang.String r6 = "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?"
            r7 = 0
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r8 = r13
            r2.a(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L58
        L33:
            java.lang.String r10 = "获取鉴权信息失败"
            r13.a(r0, r10)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            com.mobile.auth.ab.a r10 = com.mobile.auth.ab.a.a()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r10.b()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L58
        L41:
            r10 = move-exception
            goto L50
        L43:
            java.lang.String r10 = ""
            r13.a(r0, r10)     // Catch: java.lang.Throwable -> L41
            com.mobile.auth.ab.a r10 = com.mobile.auth.ab.a.a()     // Catch: java.lang.Throwable -> L41
            r10.b()     // Catch: java.lang.Throwable -> L41
            goto L58
        L50:
            com.mobile.auth.gatewayauth.a.a(r10)     // Catch: java.lang.Throwable -> L54
            goto L58
        L54:
            r10 = move-exception
            com.mobile.auth.gatewayauth.a.a(r10)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.aa.b.a(android.content.Context, int, java.lang.String, com.mobile.auth.aa.c):void");
    }
}
