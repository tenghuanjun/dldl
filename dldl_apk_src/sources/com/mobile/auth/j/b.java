package com.mobile.auth.j;

import android.content.SharedPreferences;
import com.mobile.auth.k.d;
import com.mobile.auth.k.f;
import com.mobile.auth.k.p;
import com.mobile.auth.k.u;
import com.tencent.open.SocialConstants;
import com.unionpay.tsmservice.mini.data.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public interface b {

    class a extends u.a {
        final /* synthetic */ JSONObject a;

        a(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        @Override // com.mobile.auth.k.u.a
        protected void a() {
            b.a(b.this, this.a);
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.j.b$b, reason: collision with other inner class name */
    class C0078b implements com.mobile.auth.i.b {
        C0078b(b bVar) {
        }

        public void a(String str, String str2, JSONObject jSONObject) {
        }
    }

    class c implements d.b {
        final /* synthetic */ String a;
        final /* synthetic */ com.mobile.auth.i.b b;

        c(String str, com.mobile.auth.i.b bVar) {
            this.a = str;
            this.b = bVar;
        }

        @Override // com.mobile.auth.k.d.b
        public void a(String str, String str2) {
            f.a("SendLog", "request success , url : " + this.a + ">>>>result : " + str);
            SharedPreferences.Editor editorA = p.a();
            editorA.putInt("logFailTimes", 0);
            editorA.commit();
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.b.a(jSONObject.optString(Constant.KEY_RESULT_CODE), jSONObject.optString(SocialConstants.PARAM_APP_DESC), jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                a("200021", "数据解析异常", str2);
            }
        }

        @Override // com.mobile.auth.k.d.b
        public void a(String str, String str2, String str3) {
            com.mobile.auth.f.a aVarA = b.a(b.this).a();
            if (aVarA.q() != 0 && aVarA.p() != 0) {
                int iA = p.a("logFailTimes", 0) + 1;
                SharedPreferences.Editor editorA = p.a();
                if (iA >= aVarA.p()) {
                    editorA.putInt("logFailTimes", 0);
                    editorA.putLong("logCloseTime", System.currentTimeMillis());
                } else {
                    editorA.putInt("logFailTimes", iA);
                }
                editorA.commit();
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constant.KEY_RESULT_CODE, str);
                jSONObject.put(SocialConstants.PARAM_APP_DESC, str2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            f.a("SendLog", "request failed , url : " + this.a + ">>>>>errorMsg : " + jSONObject.toString());
            com.mobile.auth.i.b bVar = this.b;
            if (bVar != null) {
                bVar.a(str, str2, jSONObject);
            }
        }
    }

    void a(com.mobile.auth.l.c cVar, com.mobile.auth.m.c cVar2, com.cmic.sso.sdk.a aVar);
}
