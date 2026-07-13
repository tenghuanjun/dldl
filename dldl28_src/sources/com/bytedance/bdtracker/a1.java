package com.bytedance.bdtracker;

import com.bytedance.applog.log.EventBus;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f206a = true;
    public static volatile Boolean b;

    public static class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f207a;

        public a(Object obj) {
            this.f207a = obj;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            j3 j3Var;
            String upperCase;
            JSONObject jSONObjectH = ((j3) this.f207a).h();
            JSONObject jSONObject = new JSONObject();
            n0.b(jSONObjectH, jSONObject);
            try {
                jSONObject.put("$$APP_ID", ((j3) this.f207a).m);
                j3Var = (j3) this.f207a;
            } catch (JSONException unused) {
            }
            if (j3Var != null) {
                if ((j3Var instanceof q3) || (j3Var instanceof t3)) {
                    upperCase = "EVENT_V3";
                } else if (j3Var instanceof m3) {
                    upperCase = ((m3) j3Var).s.toUpperCase(Locale.ROOT);
                } else if (j3Var instanceof r3) {
                    upperCase = "LAUNCH";
                } else if (j3Var instanceof w3) {
                    upperCase = "TERMINATE";
                } else if (j3Var instanceof u3) {
                    upperCase = "PROFILE";
                } else if (j3Var instanceof x3) {
                    upperCase = "TRACE";
                }
                jSONObject.put("$$EVENT_TYPE", upperCase);
                jSONObject.put("$$EVENT_LOCAL_ID", ((j3) this.f207a).p);
                return jSONObject;
            }
            upperCase = "";
            jSONObject.put("$$EVENT_TYPE", upperCase);
            jSONObject.put("$$EVENT_LOCAL_ID", ((j3) this.f207a).p);
            return jSONObject;
        }
    }

    public static String a(String str) {
        return "applog_" + str;
    }

    public static void a(String str, EventBus.DataFetcher dataFetcher) {
        if (a() || n0.c(str)) {
            return;
        }
        EventBus.global.get(new Object[0]).emit(a(str), dataFetcher);
    }

    public static void a(String str, Object obj) {
        if (a() || n0.c(str)) {
            return;
        }
        if (obj instanceof j3) {
            EventBus.global.get(new Object[0]).emit(a(str), (EventBus.DataFetcher) new a(obj));
        } else {
            EventBus.global.get(new Object[0]).emit(a(str), obj);
        }
    }

    public static boolean a() {
        return b == null ? !f206a : !b.booleanValue();
    }
}
