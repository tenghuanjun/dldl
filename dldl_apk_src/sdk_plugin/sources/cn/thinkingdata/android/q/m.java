package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class m extends h<Long> {
    public m(Future<SharedPreferences> future) {
        super(future, "lastInstallTime");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.thinkingdata.android.q.h
    public Long a() {
        return 0L;
    }

    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences.Editor editor, Long l) {
        editor.putLong(this.b, l.longValue());
        editor.apply();
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.Long] */
    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences sharedPreferences) {
        this.a = Long.valueOf(sharedPreferences.getLong(this.b, 0L));
    }
}
