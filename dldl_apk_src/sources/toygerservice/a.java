package toygerservice;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class a {
    public static List<String> a = new ArrayList();
    public static List<String> b = new ArrayList();
    public static List<Integer> c = new ArrayList();
    public static List<Integer> d = new ArrayList();
    public static Object e = new Object();
    public static long f = 0;
    public static HashMap<String, HandlerC0120a> g = new HashMap<>();

    /* JADX INFO: renamed from: toygerservice.a$a, reason: collision with other inner class name */
    public static class HandlerC0120a extends Handler {
        public String a;
        public int b;
        public b c;

        public HandlerC0120a(String str, b bVar) {
            super(bVar.getLooper());
            this.a = str;
            this.c = bVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            if (message.what == 0) {
                synchronized (a.e) {
                    if (this.b == 0) {
                        a.g.remove(this.a);
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    if (Build.VERSION.SDK_INT >= 18) {
                        b bVar = this.c;
                        bVar.a = true;
                        bVar.quitSafely();
                        bVar.a = false;
                    } else {
                        b bVar2 = this.c;
                        bVar2.a = true;
                        bVar2.quit();
                        bVar2.a = false;
                    }
                    this.c = null;
                }
            }
        }
    }

    public static class b extends HandlerThread {
        public boolean a;

        public b(String str) {
            super(str);
            this.a = false;
        }

        @Override // android.os.HandlerThread
        public boolean quit() {
            if (this.a) {
                return super.quit();
            }
            throw new IllegalStateException("HandlerThread borrowed from HandlerThreadPool cannot call quit directory, use HandlerThreadPool.returnThread() instead");
        }

        @Override // android.os.HandlerThread
        public boolean quitSafely() {
            if (this.a) {
                return super.quitSafely();
            }
            throw new IllegalStateException("HandlerThread borrowed from HandlerThreadPool cannot call quitSafely directly, use HandlerThreadPool.returnThread() instead");
        }
    }

    public static void a(HandlerThread handlerThread) {
        if (handlerThread == null) {
            return;
        }
        String name = handlerThread.getName();
        synchronized (e) {
            HandlerC0120a handlerC0120a = g.get(name);
            if (handlerC0120a == null) {
                return;
            }
            int i = handlerC0120a.b - 1;
            handlerC0120a.b = i;
            if (i < 0) {
                throw new IllegalStateException("defRef called on dead thread");
            }
            if (i == 0) {
                handlerC0120a.sendEmptyMessageDelayed(0, f);
            }
        }
    }
}
