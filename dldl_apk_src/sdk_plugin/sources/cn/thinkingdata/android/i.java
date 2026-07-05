package cn.thinkingdata.android;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class i {
    private final Executor a = Executors.newSingleThreadExecutor();

    private static class a implements Callable<SharedPreferences> {
        private final Context a;
        private final String b;

        public a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public SharedPreferences call() {
            return this.a.getSharedPreferences(this.b, 0);
        }
    }

    public Future<SharedPreferences> a(Context context, String str) {
        FutureTask futureTask = new FutureTask(new a(context, str));
        this.a.execute(futureTask);
        return futureTask;
    }
}
