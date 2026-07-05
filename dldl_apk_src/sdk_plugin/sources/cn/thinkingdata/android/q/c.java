package cn.thinkingdata.android.q;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class c extends cn.thinkingdata.android.q.a {
    private n c;
    private l d;
    private i e;
    private o f;
    private p g;
    private s h;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.values().length];
            a = iArr;
            try {
                iArr[g.LOGIN_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.IDENTIFY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.SUPER_PROPERTIES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.OPT_OUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[g.ENABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[g.PAUSE_POST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public c(Context context, String str) {
        super(context, "com.thinkingdata.analyse_" + str);
    }

    @Override // cn.thinkingdata.android.q.a
    protected void a() {
        this.c = new n(this.b);
        this.d = new l(this.b);
        this.h = new s(this.b);
        this.f = new o(this.b);
        this.e = new i(this.b);
        this.g = new p(this.b);
    }

    @Override // cn.thinkingdata.android.q.a
    protected <T> h<T> b(g gVar) {
        switch (a.a[gVar.ordinal()]) {
            case 1:
                return this.c;
            case 2:
                return this.d;
            case 3:
                return this.h;
            case 4:
                return this.f;
            case 5:
                return this.e;
            case 6:
                return this.g;
            default:
                return null;
        }
    }
}
