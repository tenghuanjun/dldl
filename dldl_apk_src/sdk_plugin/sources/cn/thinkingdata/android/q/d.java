package cn.thinkingdata.android.q;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class d extends cn.thinkingdata.android.q.a {
    private k c;
    private j d;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.values().length];
            a = iArr;
            try {
                iArr[g.FLUSH_INTERVAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.FLUSH_SIZE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public d(Context context, String str) {
        super(context, "cn.thinkingdata.android.config_" + str);
    }

    @Override // cn.thinkingdata.android.q.a
    protected void a() {
        this.c = new k(this.b, 15000);
        this.d = new j(this.b, 20);
    }

    @Override // cn.thinkingdata.android.q.a
    protected <T> h<T> b(g gVar) {
        int i = a.a[gVar.ordinal()];
        if (i == 1) {
            return this.c;
        }
        if (i != 2) {
            return null;
        }
        return this.d;
    }
}
