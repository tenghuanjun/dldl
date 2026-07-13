package a.a.a.b;

import a.a.a.b.e;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: AutomaticManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f65a;
    public int b;
    public boolean c;
    public boolean d;
    public Application e;
    public long f;
    public final Handler g;

    /* JADX INFO: renamed from: a.a.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutomaticManager.java */
    public class HandlerC0000a extends Handler {
        public HandlerC0000a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 4096) {
                a aVar = a.this;
                aVar.f += 10000;
                a.a(aVar);
            }
        }
    }

    /* JADX INFO: compiled from: AutomaticManager.java */
    public enum b {
        INSTANCE;


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f67a = new a(null);

        b() {
        }
    }

    public /* synthetic */ a(HandlerC0000a handlerC0000a) {
        this();
    }

    public a() {
        this.f65a = 0;
        this.b = 0;
        this.c = true;
        this.d = true;
        this.f = 0L;
        this.g = new HandlerC0000a(Looper.getMainLooper());
    }

    public static /* synthetic */ void a(a aVar) {
        aVar.g.removeCallbacksAndMessages(null);
        Message messageObtain = Message.obtain();
        messageObtain.what = 4096;
        aVar.g.sendMessageDelayed(messageObtain, 10000L);
        d.a(aVar.e, "ks_active_duration", aVar.f);
        a.a.a.g.b.a("AutomaticManager", "activeTime:" + aVar.f);
    }

    public void a(Context context) {
        try {
            e.c.INSTANCE.f74a.a(new a.a.a.d.a("EVENT_ACTIVE_AUTOMATIC"));
            d.a(this.e);
            SharedPreferences sharedPreferences = d.d;
            long j = sharedPreferences != null ? sharedPreferences.getLong("ks_active_duration", 0L) : 0L;
            if (j > 0) {
                a.a.a.d.a aVar = new a.a.a.d.a("EVENT_GAME_REPORT_DURATION_AUTOMATIC");
                aVar.c = j;
                e.c.INSTANCE.f74a.a(aVar);
            }
            if (context instanceof Application) {
                Application application = (Application) context;
                this.e = application;
                application.registerActivityLifecycleCallbacks(new a.a.a.b.b(this));
            }
        } catch (Exception e) {
            a.a.a.g.b.a("AutomaticManager", e.getMessage());
        }
    }
}
