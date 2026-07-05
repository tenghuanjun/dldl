package com.igexin.push.core;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.igexin.push.config.a.AnonymousClass3;
import com.igexin.push.config.a.AnonymousClass4;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class n {
    private static final String a = "PushController";
    private static n b;

    private n() {
    }

    public static n a() {
        if (b == null) {
            b = new n();
        }
        return b;
    }

    private static void a(int i) {
        com.igexin.push.config.d.d = i;
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.config.a.a().new AnonymousClass3(), false, true);
        if (e.r) {
            System.currentTimeMillis();
            com.igexin.b.a.c.a.a("setHeartbeatInterval heartbeatReq", new Object[0]);
            if (System.currentTimeMillis() - e.W > 5000) {
                e.W = System.currentTimeMillis();
                com.igexin.push.core.a.b.d();
                com.igexin.push.core.a.b.f();
            }
        }
    }

    public static void a(int i, int i2) {
        com.igexin.push.config.d.a = i;
        com.igexin.push.config.d.b = i2;
        com.igexin.push.config.a.a().c();
        com.igexin.push.e.e.c().d();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.os.Bundle r15) {
        /*
            Method dump skipped, instruction units count: 1558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.n.a(android.os.Bundle):void");
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Uri uri = Uri.parse(str);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter(com.igexin.push.core.d.c.c);
            if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(queryParameter)) {
                if (!com.igexin.push.config.d.I) {
                    com.igexin.b.a.c.a.a("PushController|isApplinkFeedback is false, not feedback", new Object[0]);
                    return;
                }
                if (!com.igexin.push.f.c.c(host)) {
                    com.igexin.b.a.c.a.a("PushController|checkIsWhiteApplinkDomain is false, not feedback", new Object[0]);
                    return;
                }
                com.igexin.b.a.c.a.a("PushController|isApplinkFeedback is true and checkIsWhiteApplinkDomain is true, to feedback", new Object[0]);
                PushTaskBean pushTaskBean = new PushTaskBean();
                pushTaskBean.setTaskId("getuiapplinkup");
                pushTaskBean.setMessageId(queryParameter);
                pushTaskBean.setAppid(e.a);
                FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, PushConsts.SEND_MESSAGE_ERROR);
                return;
            }
            com.igexin.b.a.c.a.a("PushController|url " + str + " is invalid", new Object[0]);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("PushController|" + e.toString(), new Object[0]);
        }
    }

    private static void a(String str, String str2) {
        if (TextUtils.isEmpty(e.x)) {
            com.igexin.b.a.c.a.d.a().a("setTag : " + str + ", failed, has not get clientid");
            m.a().a(str2, "20008");
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", "set_tag");
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.x);
                jSONObject.put("appid", e.a);
                jSONObject.put("tags", URLEncoder.encode(str, "utf-8"));
                jSONObject.put("sn", str2);
            } catch (Exception unused) {
            }
            String string = jSONObject.toString();
            com.igexin.push.core.e.d.a().b(new com.igexin.push.core.b.i(jCurrentTimeMillis, string, (byte) 2, e.r ? jCurrentTimeMillis : 0L));
            com.igexin.push.c.c.o oVar = new com.igexin.push.c.c.o();
            oVar.c = 128;
            oVar.e = b.K;
            oVar.f = string;
            d.a.a.i.a("C-" + e.x, oVar, false);
            com.igexin.b.a.c.a.a("settag", new Object[0]);
        } catch (Exception unused2) {
        }
    }

    private static void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(e.x)) {
            com.igexin.b.a.c.a.d.a().a("unbindAlias : " + str + ", failed, has not get clientid");
            m.a().c(str2, "30005");
            return;
        }
        if (z && TextUtils.isEmpty(e.x)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = e.Y;
        if (jCurrentTimeMillis - e.Y <= 1000) {
            com.igexin.b.a.c.a.a("PushController|unbindAlias frequently called", new Object[0]);
            return;
        }
        String str3 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(jCurrentTimeMillis));
        String str4 = e.X;
        if (!str3.equals(e.X)) {
            com.igexin.push.core.e.e.a().d(str3);
            com.igexin.push.core.e.e.a().a(0);
        }
        int i = e.Z;
        if (e.Z < 100) {
            com.igexin.b.a.c.a.a("start unbindAlias ###", new Object[0]);
            e.Y = jCurrentTimeMillis;
            com.igexin.push.core.e.e.a().a(e.Z + 1);
            a(str, str2, true, z);
            return;
        }
        com.igexin.b.a.c.a.a("PushController|unbindAlias times exceed", new Object[0]);
        com.igexin.b.a.c.a.d.a().a("unbindAlias : " + str + ", failed, , the number of calls per day cannot exceed 100");
        m.a().c(str2, "30003");
    }

    public static void a(String str, String str2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(e.x)) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            String str3 = z ? "unbind_alias" : "bind_alias";
            byte b2 = z ? (byte) 8 : (byte) 7;
            try {
                jSONObject.put("action", str3);
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.x);
                jSONObject.put("appid", e.a);
                jSONObject.put("alias", str);
                jSONObject.put("sn", str2);
                if (z) {
                    jSONObject.put("is_self", z2);
                }
            } catch (Exception unused) {
            }
            String string = jSONObject.toString();
            com.igexin.push.core.e.d.a().b(new com.igexin.push.core.b.i(jCurrentTimeMillis, string, b2, e.r ? jCurrentTimeMillis : 0L));
            com.igexin.push.c.c.o oVar = new com.igexin.push.c.c.o();
            oVar.c = 128;
            oVar.e = b.K;
            oVar.f = string;
            d.a.a.i.a("C-" + e.x, oVar, false);
            com.igexin.b.a.c.a.a(str3 + " = " + string, new Object[0]);
        } catch (Exception unused2) {
        }
    }

    private static void a(String str, byte[] bArr) {
        if (e.x != null) {
            JSONObject jSONObject = new JSONObject();
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                jSONObject.put("action", "sendmessage");
                jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
                jSONObject.put("cid", e.x);
                jSONObject.put("appid", e.a);
                jSONObject.put("taskid", str);
                jSONObject.put("extraData", Base64.encodeToString(bArr, 0));
                String string = jSONObject.toString();
                com.igexin.push.core.e.d.a().b(new com.igexin.push.core.b.i(jCurrentTimeMillis, string, (byte) 6, jCurrentTimeMillis));
                com.igexin.push.c.c.b bVar = new com.igexin.push.c.c.b();
                bVar.c = 128;
                bVar.b = (int) jCurrentTimeMillis;
                bVar.e = e.x;
                bVar.f = string;
                bVar.g = bArr;
                bVar.h = e.x;
                d.a.a.i.a("C-" + e.x, bVar, false);
                if (str == null || !str.startsWith("4T5@S_")) {
                    return;
                }
                com.igexin.b.a.c.a.a("PushController sending lbs report message : ".concat(String.valueOf(string)), new Object[0]);
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("PushController|" + th.toString(), new Object[0]);
            }
        }
    }

    private static void b(int i) {
        com.igexin.push.config.d.e = i;
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.config.a.a().new AnonymousClass4(), false, true);
    }

    private static void b(String str, String str2) {
        if (TextUtils.isEmpty(e.x)) {
            com.igexin.b.a.c.a.d.a().a("bindAlias : " + str + ", failed, has not get clientid");
            m.a().b(str2, "30005");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = e.Y;
        if (jCurrentTimeMillis - e.Y <= 1000) {
            com.igexin.b.a.c.a.a("PushController|bindAlias frequently called", new Object[0]);
            return;
        }
        String str3 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(jCurrentTimeMillis));
        if (e.X != null) {
            String str4 = e.X;
        }
        if (!str3.equals(e.X)) {
            com.igexin.push.core.e.e.a().d(str3);
            com.igexin.push.core.e.e.a().a(0);
        }
        int i = e.Z;
        com.igexin.b.a.c.a.a("-> CoreRuntimeInfo.opAliasTimes:" + e.Z, new Object[0]);
        if (e.Z < 100) {
            com.igexin.b.a.c.a.a("start bindAlias ###", new Object[0]);
            e.Y = jCurrentTimeMillis;
            com.igexin.push.core.e.e.a().a(e.Z + 1);
            a(str, str2, false, true);
            return;
        }
        com.igexin.b.a.c.a.a("PushController|bindAlias times exceed", new Object[0]);
        com.igexin.b.a.c.a.d.a().a("bindAlias : " + str + ", failed, , the number of calls per day cannot exceed 100");
        m.a().b(str2, "30003");
    }
}
