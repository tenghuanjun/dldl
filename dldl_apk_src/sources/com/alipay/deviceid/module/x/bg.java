package com.alipay.deviceid.module.x;

import android.os.Looper;
import com.alipay.deviceid.module.rpc.mrpc.core.RpcException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class bg {
    private static final ThreadLocal<Object> a = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, Object>> b = new ThreadLocal<>();
    private byte c = 0;
    private AtomicInteger d = new AtomicInteger();
    private be e;

    public bg(be beVar) {
        this.e = beVar;
    }

    public final Object a(Method method, Object[] objArr) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        am amVar = (am) method.getAnnotation(am.class);
        boolean z = method.getAnnotation(an.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        a.set(null);
        b.set(null);
        if (amVar == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String strA = amVar.a();
        int iIncrementAndGet = this.d.incrementAndGet();
        try {
            if (this.c == 0) {
                bp bpVar = new bp(iIncrementAndGet, strA, objArr);
                if (b.get() != null) {
                    bpVar.a(b.get());
                }
                byte[] bArr = (byte[]) new as(this.e.a, method, iIncrementAndGet, strA, bpVar.a(), z).a();
                b.set(null);
                Object objA = new bo(genericReturnType, bArr).a();
                if (genericReturnType != Void.TYPE) {
                    a.set(objA);
                }
            }
            return a.get();
        } catch (RpcException e) {
            e.setOperationType(strA);
            throw e;
        }
    }
}
