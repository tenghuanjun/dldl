package com.alipay.deviceid.module.x;

import com.alipay.deviceid.module.rpc.json.JSONException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class w {
    public ArrayList a;

    public w() {
        this.a = new ArrayList();
    }

    public w(y yVar) throws JSONException {
        char c;
        ArrayList arrayList;
        Object objD;
        this();
        char c2 = yVar.c();
        if (c2 == '[') {
            c = ']';
        } else {
            if (c2 != '(') {
                throw yVar.a("A JSONArray text must start with '['");
            }
            c = ')';
        }
        if (yVar.c() == ']') {
            return;
        }
        do {
            yVar.a();
            char c3 = yVar.c();
            yVar.a();
            if (c3 == ',') {
                arrayList = this.a;
                objD = null;
            } else {
                arrayList = this.a;
                objD = yVar.d();
            }
            arrayList.add(objD);
            char c4 = yVar.c();
            if (c4 != ')') {
                if (c4 != ',' && c4 != ';') {
                    if (c4 != ']') {
                        throw yVar.a("Expected a ',' or ']'");
                    }
                }
            }
            if (c == c4) {
                return;
            }
            throw yVar.a("Expected a '" + new Character(c) + "'");
        } while (yVar.c() != ']');
    }

    public w(Object obj) throws JSONException {
        this();
        if (!obj.getClass().isArray()) {
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.a.add(Array.get(obj, i));
        }
    }

    public w(String str) {
        this(new y(str));
    }

    public w(Collection collection) {
        this.a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    private String a(String str) {
        int size = this.a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(x.a(this.a.get(i)));
        }
        return stringBuffer.toString();
    }

    public final Object a(int i) throws JSONException {
        Object obj = (i < 0 || i >= this.a.size()) ? null : this.a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return "[" + a(com.igexin.push.core.b.aj) + ']';
        } catch (Exception unused) {
            return null;
        }
    }
}
