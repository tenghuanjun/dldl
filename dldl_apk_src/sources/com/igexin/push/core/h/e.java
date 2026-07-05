package com.igexin.push.core.h;

import android.content.ContentValues;
import com.igexin.b.a.b.g;
import com.igexin.b.a.d.f;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import com.igexin.push.core.e.e.AnonymousClass3;
import com.igexin.push.f.k;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e extends com.igexin.push.e.a.b {
    public static final String a = "UploadBiLogPlugin";
    public boolean b;
    private boolean c;
    private int m;

    public e(String str, byte[] bArr) {
        super(str);
        this.c = false;
        this.c = false;
        this.m = 10;
        a(bArr, 10);
    }

    private e(byte[] bArr, int i) {
        super(SDKUrlConfig.getBiUploadServiceUrl());
        this.c = false;
        a(bArr, i);
    }

    private void a(byte[] bArr, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", "upload_BI");
            jSONObject.put("BIType", String.valueOf(i));
            jSONObject.put("cid", com.igexin.push.core.e.x);
            jSONObject.put("BIData", new String(g.c(bArr), "UTF-8"));
            this.f = jSONObject.toString().getBytes();
        } catch (Exception unused) {
        }
    }

    @Override // com.igexin.push.e.a.b
    public final void a(byte[] bArr) throws Exception {
        JSONObject jSONObject = new JSONObject(new String(bArr));
        if (jSONObject.has("result") && com.igexin.push.core.b.x.equals(jSONObject.getString("result"))) {
            this.b = true;
            if (this.m == 10) {
                k.g();
            }
            if (this.c) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("type", "0");
                d.a.a.j.a(com.igexin.push.core.b.ac, contentValues, new String[]{"type"}, new String[]{"2"});
                com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (com.igexin.push.core.e.R != jCurrentTimeMillis) {
                    com.igexin.push.core.e.R = jCurrentTimeMillis;
                    com.igexin.b.a.b.e.a().a((f) eVarA.new AnonymousClass3(), false, true);
                }
            }
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return 0;
    }
}
