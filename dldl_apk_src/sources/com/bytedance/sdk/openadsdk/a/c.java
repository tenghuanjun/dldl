package com.bytedance.sdk.openadsdk.a;

import android.app.Application;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.EventListener;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.a.a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c implements Bridge {
    private static volatile c a;
    private b b;
    private a c = new a();

    private c() {
    }

    public static c a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    public Application.ActivityLifecycleCallbacks b() {
        return this.c;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a().a(10000, 5).b();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        Bridge bridgeA;
        switch (i) {
            case 2:
                return (T) this.c.a();
            case 3:
                return (T) TTAppContextHolder.getContext();
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                b bVar = this.b;
                if (bVar == null || (bridgeA = bVar.a(4)) == null) {
                    return null;
                }
                return (T) bridgeA.call(i, valueSet, cls);
            case 9:
                Object objObjectValue = valueSet.objectValue(0, Object.class);
                if (objObjectValue instanceof EventListener) {
                    a((EventListener) objObjectValue);
                }
                return null;
            case 10:
                b bVar2 = this.b;
                if (bVar2 == null) {
                    return null;
                }
                return (T) bVar2.a(valueSet.intValue(0));
            default:
                return null;
        }
    }

    private void a(final EventListener eventListener) {
        this.c.a(new a.InterfaceC0024a() { // from class: com.bytedance.sdk.openadsdk.a.c.1
            @Override // com.bytedance.sdk.openadsdk.a.a.InterfaceC0024a
            public void a() {
                eventListener.onEvent(0, null);
            }

            @Override // com.bytedance.sdk.openadsdk.a.a.InterfaceC0024a
            public void b() {
                eventListener.onEvent(1, null);
            }
        });
    }
}
