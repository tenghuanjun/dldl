package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class o extends h<Boolean> {
    public o(Future<SharedPreferences> future) {
        super(future, "optOutFlag");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences.Editor editor, Boolean bool) {
        editor.putBoolean(this.b, bool.booleanValue());
        editor.apply();
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Boolean] */
    @Override // cn.thinkingdata.android.q.h
    protected void a(SharedPreferences sharedPreferences) {
        this.a = Boolean.valueOf(sharedPreferences.getBoolean(this.b, false));
    }
}
