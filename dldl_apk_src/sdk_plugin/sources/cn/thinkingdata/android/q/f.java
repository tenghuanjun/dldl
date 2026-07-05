package cn.thinkingdata.android.q;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class f extends cn.thinkingdata.android.q.a {
    private n c;
    private r d;
    private m e;
    private q f;

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
                a[g.RANDOM_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.LAST_INSTALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.DEVICE_ID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public f(Context context) {
        super(context, "com.thinkingdata.analyse");
    }

    @Override // cn.thinkingdata.android.q.a
    protected void a() {
        this.d = new r(this.b);
        this.c = new n(this.b);
        this.e = new m(this.b);
        this.f = new q(this.b);
    }

    @Override // cn.thinkingdata.android.q.a
    protected <T> h<T> b(g gVar) {
        int i = a.a[gVar.ordinal()];
        if (i == 1) {
            return this.c;
        }
        if (i == 2) {
            return this.d;
        }
        if (i == 3) {
            return this.e;
        }
        if (i != 4) {
            return null;
        }
        return this.f;
    }
}
