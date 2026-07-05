package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
abstract class h<T> {
    protected T a;
    final String b;
    private final Future<SharedPreferences> c;

    h(Future<SharedPreferences> future, String str) {
        this.c = future;
        this.b = str;
    }

    private SharedPreferences.Editor c() {
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = this.c.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            sharedPreferences = null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            sharedPreferences = null;
        }
        if (sharedPreferences != null) {
            return sharedPreferences.edit();
        }
        return null;
    }

    T a() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void a(SharedPreferences.Editor editor, T t) {
        editor.putString(this.b, (String) t);
        editor.apply();
    }

    void a(SharedPreferences sharedPreferences) {
        T t = (T) sharedPreferences.getString(this.b, null);
        if (t == null) {
            a(a());
        } else {
            this.a = t;
        }
    }

    public void a(T t) {
        this.a = t;
        synchronized (this.c) {
            SharedPreferences.Editor editorC = c();
            if (editorC != null) {
                a(editorC, this.a);
            }
        }
    }

    public T b() throws ExecutionException, InterruptedException {
        if (this.a == null) {
            synchronized (this.c) {
                SharedPreferences sharedPreferences = null;
                try {
                    sharedPreferences = this.c.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                }
                if (sharedPreferences != null) {
                    a(sharedPreferences);
                }
            }
        }
        return this.a;
    }
}
