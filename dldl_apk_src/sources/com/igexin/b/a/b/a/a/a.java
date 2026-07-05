package com.igexin.b.a.b.a.a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class a extends com.igexin.b.a.b.f {
    protected volatile boolean f;
    protected volatile int g;
    protected String h;
    protected volatile boolean i;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.igexin.b.a.b.a.a.a$a, reason: collision with other inner class name */
    protected static final class EnumC0055a {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        private static final /* synthetic */ int[] d = {a, b, c};

        private EnumC0055a(String str, int i) {
        }

        private static int[] a() {
            return (int[]) d.clone();
        }
    }

    public a(int i, com.igexin.b.a.b.d dVar) {
        super(i, null, dVar);
        this.g = EnumC0055a.a;
        this.i = true;
    }

    public abstract void c_();

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d() {
        super.d();
        this.o = true;
    }

    @Override // com.igexin.b.a.d.f
    public final void e() {
        Thread thread = this.K;
        if (!thread.isAlive() || thread.isInterrupted()) {
            return;
        }
        thread.interrupt();
    }

    @Override // com.igexin.b.a.d.f
    public final void f() {
    }

    protected final boolean g() {
        return this.g == EnumC0055a.c;
    }
}
