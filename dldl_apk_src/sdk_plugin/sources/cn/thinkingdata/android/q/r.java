package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.UUID;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class r extends h<String> {
    public r(Future<SharedPreferences> future) {
        super(future, "randomID");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.thinkingdata.android.q.h
    public String a() {
        return UUID.randomUUID().toString();
    }
}
