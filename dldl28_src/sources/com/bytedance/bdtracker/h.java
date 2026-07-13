package com.bytedance.bdtracker;

import android.app.Application;
import android.content.SharedPreferences;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SharedPreferences f255a;

    public h(Application applicationContext, String spName) {
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(spName, "spName");
        this.f255a = v3.a(applicationContext, spName, 0);
    }

    public final <T extends o> T a(String key, Class<T> clazz) {
        String string;
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorRemove;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        try {
            SharedPreferences sharedPreferences = this.f255a;
            if (sharedPreferences == null || (string = sharedPreferences.getString(key, null)) == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(string);
            long jOptLong = jSONObject.optLong("expire_ts");
            if (jOptLong != -1 && (jOptLong <= 0 || System.currentTimeMillis() >= jOptLong)) {
                SharedPreferences sharedPreferences2 = this.f255a;
                if (sharedPreferences2 == null || (editorEdit = sharedPreferences2.edit()) == null || (editorRemove = editorEdit.remove(key)) == null) {
                    return null;
                }
                editorRemove.apply();
                return null;
            }
            return (T) o.f299a.a(jSONObject, clazz);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final String a(String key) {
        String string;
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorRemove;
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            SharedPreferences sharedPreferences = this.f255a;
            if (sharedPreferences == null || (string = sharedPreferences.getString(key, null)) == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(string);
            long jOptLong = jSONObject.optLong("expire_ts");
            if (jOptLong != -1 && (jOptLong <= 0 || System.currentTimeMillis() >= jOptLong)) {
                SharedPreferences sharedPreferences2 = this.f255a;
                if (sharedPreferences2 == null || (editorEdit = sharedPreferences2.edit()) == null || (editorRemove = editorEdit.remove(key)) == null) {
                    return null;
                }
                editorRemove.apply();
                return null;
            }
            return jSONObject.optString("data");
        } catch (Throwable unused) {
            return null;
        }
    }

    public final void a(String key, o data, long j) throws JSONException {
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorPutString;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(data, "data");
        JSONObject jSONObjectA = data.a();
        jSONObjectA.put("expire_ts", j != -1 ? System.currentTimeMillis() + j : -1L);
        SharedPreferences sharedPreferences = this.f255a;
        if (sharedPreferences == null || (editorEdit = sharedPreferences.edit()) == null || (editorPutString = editorEdit.putString(key, jSONObjectA.toString())) == null) {
            return;
        }
        editorPutString.apply();
    }

    public final void a(String key, String str, long j) throws JSONException {
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorPutString;
        Intrinsics.checkParameterIsNotNull(key, "key");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", str);
        jSONObject.put("expire_ts", j != -1 ? System.currentTimeMillis() + j : -1L);
        SharedPreferences sharedPreferences = this.f255a;
        if (sharedPreferences == null || (editorEdit = sharedPreferences.edit()) == null || (editorPutString = editorEdit.putString(key, jSONObject.toString())) == null) {
            return;
        }
        editorPutString.apply();
    }
}
