package com.mobile.auth.z;

import android.content.Context;
import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.crypto.Cipher;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes3.dex */
public final class k {
    public l b;
    private ExecutorService c = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ScheduledExecutorService f821a = Executors.newScheduledThreadPool(1);

    static String a(Context context) {
        String string = "";
        try {
            try {
                String strC = u.c();
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis());
                String string2 = sb.toString();
                String strB = p.b();
                String packageName = context.getPackageName();
                String strG = v.g();
                String strA = v.a();
                String strA2 = v.a(strG, strA.substring(0, 16), strA.substring(16, 32));
                PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(j.b("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVc1ecjpc5k7TkabF935iQONDZ0/E5XWPVv9FEsI59XTRW0+BCMK1MODRSWMvHFrPMh9ZilnRr7qXuAKCBEynQEghmpIVvMYhFu48FAI9bKfkI5lKuQK+tc4X0+zTbNrpedNoKXK4C7dDjTETBH6prwWE9j5WsAf0gbjUbIs3FxwIDAQAB")));
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, publicKeyGeneratePublic);
                String strA3 = j.a(cipher.doFinal(strA.getBytes()));
                String strA4 = v.a(context, context.getPackageName(), p.b);
                String strB2 = u.b();
                if (!TextUtils.isEmpty(strB2)) {
                    strB2 = "0";
                }
                String str = strA4 + StringUtils.LF + strC + "\n2.1\njson\n" + strB2 + StringUtils.LF + packageName + StringUtils.LF + strA2 + StringUtils.LF + strB + StringUtils.LF + strA3 + StringUtils.LF + string2;
                String strA5 = v.a(str.replaceAll(StringUtils.LF, ""));
                t.a("unSignDebugInfo=".concat(String.valueOf(str)));
                String strA6 = j.a(strA2);
                String strA7 = j.a(strA3);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("androidMd5", strA4);
                jSONObject.put("apiKey", strC);
                jSONObject.put("apiVersion", "2.1");
                jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, "json");
                jSONObject.put("operator", strB2);
                jSONObject.put("packName", packageName);
                jSONObject.put("privateIp", strA6);
                jSONObject.put("sdkVersion", strB);
                jSONObject.put("secretKey", strA7);
                jSONObject.put("timeStamp", string2);
                jSONObject.put("sign", strA5);
                string = jSONObject.toString();
                t.c("getPreCheckParam_CU_Oath: param ok  \n");
                return string;
            } catch (Exception e) {
                e.printStackTrace();
                return string;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ void a(k kVar) {
        try {
            try {
                ScheduledExecutorService scheduledExecutorService = kVar.f821a;
                if (scheduledExecutorService != null) {
                    scheduledExecutorService.shutdownNow();
                    kVar.f821a = null;
                    return;
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public final void a(final Context context, final int i, final Object obj, final m mVar) {
        try {
            synchronized (this) {
                try {
                    this.c.submit(new Runnable() { // from class: com.mobile.auth.z.k.4
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                try {
                                    String str = "";
                                    int i2 = i;
                                    if (i2 != 2) {
                                        mVar.a(i2, 410009, "410009no this type");
                                    } else {
                                        str = u.a() + s.a(k.a(context), "&");
                                    }
                                    n nVar = new n();
                                    Context context2 = context;
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("user-agent", "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1");
                                    map.put("netType", "2");
                                    map.put("os", PodInfo.GAME_TYPE_ANDROID);
                                    map.put(HttpHeaders.HEAD_KEY_ACCEPT, "*/*");
                                    String strA = nVar.a(context2, str, map, obj);
                                    if (u.h() == 1) {
                                        try {
                                            q.a().b();
                                            t.c("\n  WIFI + 流量 \n call releaseNetwork() \n");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (TextUtils.isEmpty(strA)) {
                                        mVar.a(i, 410002, "网络请求响应为空");
                                    } else {
                                        mVar.a(i, 1, strA);
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            } catch (Throwable th) {
                                ExceptionProcessor.processException(th);
                            }
                        }
                    });
                } catch (Exception e) {
                    mVar.a(i, 410009, "410009" + e.getMessage());
                }
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
