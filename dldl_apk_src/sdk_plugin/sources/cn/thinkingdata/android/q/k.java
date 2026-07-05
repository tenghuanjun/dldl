package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class k extends h<Integer> {
    private final int d;

    public k(Future<SharedPreferences> future, int i) {
        super(future, "flushInterval");
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences.Editor editor, Integer num) {
        editor.putInt(this.b, num.intValue());
        editor.apply();
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Integer] */
    @Override // cn.thinkingdata.android.q.h
    void a(SharedPreferences sharedPreferences) {
        this.a = Integer.valueOf(sharedPreferences.getInt(this.b, this.d));
    }
}
