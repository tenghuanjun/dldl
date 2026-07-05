package com.bytedance.sdk.openadsdk.c.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTAdNative;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTAdNative.CSJSplashAdListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public b(TTAdNative.CSJSplashAdListener cSJSplashAdListener) {
        this.b = cSJSplashAdListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        if (this.b == null) {
            return null;
        }
        switch (i) {
            case 114102:
                Bridge bridge = (Bridge) valueSet.objectValue(0, Bridge.class);
                com.bytedance.sdk.openadsdk.c.a.a.b bVar = new com.bytedance.sdk.openadsdk.c.a.a.b(bridge);
                if (bridge != null) {
                    try {
                        if (bridge.values().intValue(1) >= 5700) {
                            this.b.onSplashLoadSuccess(bVar);
                        } else {
                            Method declaredMethod = this.b.getClass().getDeclaredMethod("onSplashLoadSuccess", null);
                            if (declaredMethod != null) {
                                declaredMethod.invoke(this.b, new Object[0]);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 114103:
                this.b.onSplashLoadFail(new com.bytedance.sdk.openadsdk.c.a.a.a((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
            case 114104:
                this.b.onSplashRenderSuccess(new com.bytedance.sdk.openadsdk.c.a.a.b((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
            case 114105:
                this.b.onSplashRenderFail(new com.bytedance.sdk.openadsdk.c.a.a.b((Bridge) valueSet.objectValue(0, Bridge.class)), new com.bytedance.sdk.openadsdk.c.a.a.a((Bridge) valueSet.objectValue(1, Bridge.class)));
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
