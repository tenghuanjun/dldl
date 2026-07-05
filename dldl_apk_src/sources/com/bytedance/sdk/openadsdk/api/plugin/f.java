package com.bytedance.sdk.openadsdk.api.plugin;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.Initializer;
import com.bykv.vk.openvk.api.proto.Result;
import com.bytedance.android.openliveplugin.process.LiveProcessUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.api.a;
import com.bytedance.sdk.openadsdk.api.plugin.e;
import com.bytedance.sdk.openadsdk.live.ILiveAdCustomConfig;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import dalvik.system.BaseDexClassLoader;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class f extends com.bytedance.sdk.openadsdk.api.a {
    private static final a a = new a();
    private volatile Initializer b;
    private d c;
    private com.bytedance.sdk.openadsdk.a.b d = new com.bytedance.sdk.openadsdk.a.b() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.1
        @Override // com.bytedance.sdk.openadsdk.a.b
        public Bridge a(int i) {
            return f.this.a(i);
        }
    };

    @Override // com.bytedance.sdk.openadsdk.api.a
    protected void a(Result result) {
        if (result.isSuccess()) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", 0);
            ExecutorService executorService = (ExecutorService) TTAdSdk.getAdManager().getExtra(ExecutorService.class, bundle);
            if (executorService != null) {
                com.bytedance.sdk.openadsdk.e.a.a().a(executorService);
            }
            c.a();
            return;
        }
        c.a(result.code(), result.message(), 0L);
    }

    @Override // com.bytedance.sdk.openadsdk.api.a
    protected com.bytedance.sdk.openadsdk.a.b c() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bridge a(int i) {
        if (i == 2) {
            return com.bytedance.sdk.openadsdk.live.b.a();
        }
        if (i == 3) {
            return com.bytedance.sdk.openadsdk.downloadnew.d.a(TTAppContextHolder.getContext());
        }
        if (i != 4) {
            return null;
        }
        return com.bytedance.sdk.openadsdk.api.plugin.a.a.a();
    }

    @Override // com.bytedance.sdk.openadsdk.api.a
    protected boolean b(Context context, AdConfig adConfig, TTAdSdk.InitCallback initCallback) {
        super.b(context, adConfig, initCallback);
        this.c = d.a(MediationConstant.EXTRA_DURATION);
        if (LiveProcessUtils.inLiveProcess(TTAppContextHolder.getContext()).booleanValue()) {
            return false;
        }
        c.a(adConfig);
        if (Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        b(com.bykv.a.a.a.a.a.a().a(false).a(4204).a("Only support >= 5.0").b());
        return false;
    }

    @Override // com.bytedance.sdk.openadsdk.api.a
    public boolean a(Context context, com.bykv.a.a.a.a.b bVar) {
        if (this.b == null) {
            return false;
        }
        this.b.init(context, bVar.b());
        return true;
    }

    @Override // com.bytedance.sdk.openadsdk.api.a
    public void b(Context context, com.bykv.a.a.a.a.b bVar) {
        d dVarA = this.c;
        if (dVarA == null) {
            dVarA = d.a(MediationConstant.EXTRA_DURATION);
        }
        dVarA.b("wait_asyn_cost");
        try {
            Initializer initializerA = a(dVarA);
            try {
                if (initializerA != null) {
                    a(initializerA.getManager());
                    try {
                        dVarA.a();
                        JSONObject jSONObject = new JSONObject();
                        dVarA.a(jSONObject, 20L);
                        jSONObject.put("zeus", e.a(TTAppContextHolder.getContext()).a());
                        initializerA.init(context, bVar.a(9, jSONObject).b());
                        if (context != null) {
                            Zeus.hookHuaWeiVerifier((Application) context.getApplicationContext());
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        Zeus.unInstallPlugin("com.byted.pangle");
                        b(com.bykv.a.a.a.a.a.a().a(false).a(4207).a("Init error").b());
                        throw e;
                    }
                }
                b(com.bykv.a.a.a.a.a.a().a(false).a(TTAdConstant.INIT_FAILED_CREATE_INITIALIZER_FAILED).a("Init error").b());
            } catch (Throwable th) {
                th.printStackTrace();
                b(com.bykv.a.a.a.a.a.a().a(false).a(4203).a("UnExpected initializer error :" + th.getMessage()).b());
            }
        } catch (com.bytedance.sdk.openadsdk.api.plugin.a e2) {
            e2.printStackTrace();
            b(com.bykv.a.a.a.a.a.a().a(false).a(e2.a()).a(e2.getMessage()).b());
        }
    }

    @Override // com.bytedance.sdk.openadsdk.api.a
    public boolean a() {
        if (this.b != null) {
            return this.b.isInitSuccess();
        }
        return false;
    }

    @Override // com.bytedance.sdk.openadsdk.api.a
    public a.c b() {
        return a;
    }

    private Initializer a(d dVar) throws com.bytedance.sdk.openadsdk.api.plugin.a {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "Create initializer");
                    this.b = b(dVar);
                }
            }
        }
        return this.b;
    }

    private static Initializer b(d dVar) throws com.bytedance.sdk.openadsdk.api.plugin.a {
        try {
            dVar.b("call_create_initializer");
            BaseDexClassLoader baseDexClassLoaderA = e.a(TTAppContextHolder.getContext()).a(dVar);
            if (baseDexClassLoaderA == null) {
                throw new com.bytedance.sdk.openadsdk.api.plugin.a(4205, "Get initializer failed");
            }
            Class<?> clsLoadClass = baseDexClassLoaderA.loadClass(TTAdSdk.INITIALIZER_CLASS_NAME);
            dVar.b("get_init_class_cost");
            Bundle bundle = new Bundle();
            bundle.putLong("call_init_time", dVar.b());
            bundle.putSerializable(PluginConstants.KEY_PL_UPDATE_EVENT_LISTENER, new e.c());
            dVar.b("create_bundle_cost");
            Method declaredMethod = clsLoadClass.getDeclaredMethod("getNewInstance", Bundle.class);
            dVar.b("get_init_method_cost");
            try {
                Initializer initializer = (Initializer) declaredMethod.invoke(null, bundle);
                dVar.b("get_init_instance_cost");
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "Create initializer success");
                return initializer;
            } catch (Throwable th) {
                Zeus.unInstallPlugin("com.byted.pangle");
                throw th;
            }
        } catch (Throwable th2) {
            if (th2 instanceof com.bytedance.sdk.openadsdk.api.plugin.a) {
                throw new com.bytedance.sdk.openadsdk.api.plugin.a(4205, "(" + th2.a() + ", " + th2.getMessage() + ")");
            }
            throw new com.bytedance.sdk.openadsdk.api.plugin.a(4206, th2.getMessage());
        }
    }

    private static final class a extends a.c {
        private a() {
        }

        @Override // com.bytedance.sdk.openadsdk.api.a.c
        protected void a(Throwable th) {
            e.a(th);
        }

        @Override // com.bytedance.sdk.openadsdk.api.a.c
        protected Object a(Object obj) {
            boolean z = obj instanceof TTPluginListener;
            if (z) {
                e.a(TTAppContextHolder.getContext()).a((TTPluginListener) obj);
            }
            if (!z) {
                return obj instanceof ILiveAdCustomConfig ? com.bytedance.sdk.openadsdk.live.b.a((ILiveAdCustomConfig) obj) : obj;
            }
            TTPluginListener tTPluginListener = (TTPluginListener) obj;
            return e.a(TTAppContextHolder.getContext()).a(tTPluginListener.packageName(), tTPluginListener.config());
        }
    }
}
