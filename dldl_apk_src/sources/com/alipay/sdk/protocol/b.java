package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class b {
    private a a;
    private String b;
    private String[] c;

    public b(String str) {
        this.b = str;
    }

    public b(String str, a aVar) {
        this.b = str;
        this.a = aVar;
    }

    public static void a(b bVar) {
        String[] strArrC = bVar.c();
        if (strArrC.length == 3 && TextUtils.equals("tid", strArrC[0])) {
            com.alipay.sdk.tid.b bVarA = com.alipay.sdk.tid.b.a(com.alipay.sdk.sys.b.a().b());
            if (TextUtils.isEmpty(strArrC[1]) || TextUtils.isEmpty(strArrC[2])) {
                return;
            }
            bVarA.a(strArrC[1], strArrC[2]);
        }
    }

    public static List<b> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] strArrB = b(jSONObject.optString("name", ""));
        for (int i = 0; i < strArrB.length; i++) {
            a aVarA = a.a(strArrB[i]);
            if (aVarA != a.None) {
                b bVar = new b(strArrB[i], aVarA);
                bVar.c = a(strArrB[i]);
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private static String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        int iIndexOf = str.indexOf(40);
        int iLastIndexOf = str.lastIndexOf(41);
        if (iIndexOf == -1 || iLastIndexOf == -1 || iLastIndexOf <= iIndexOf) {
            return null;
        }
        for (String str2 : str.substring(iIndexOf + 1, iLastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll("\"", ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static String[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(i.b);
    }

    public String a() {
        return this.b;
    }

    public a b() {
        return this.a;
    }

    public String[] c() {
        return this.c;
    }
}
