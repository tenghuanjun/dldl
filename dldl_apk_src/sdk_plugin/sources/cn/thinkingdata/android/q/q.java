package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class q extends h<String> {
    public q(Future<SharedPreferences> future) {
        super(future, "randomDeviceID");
    }

    @Override // cn.thinkingdata.android.q.h
    public String a() {
        return cn.thinkingdata.android.utils.r.a(16);
    }

    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences.Editor editor, String str) {
        editor.putString(this.b, str);
        editor.apply();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.String] */
    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences.getString(this.b, "");
    }
}
