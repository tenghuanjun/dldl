package com.bytedance.sdk.openadsdk.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Pair;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.EventListener;
import com.bykv.vk.openvk.api.proto.Loader;
import com.bykv.vk.openvk.api.proto.Manager;
import com.bykv.vk.openvk.api.proto.Result;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.downloadnew.core.DownloadBridgeFactory;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class a {
    private TTAdSdk.InitCallback a;

    private interface b<T> {
        void a(T t);
    }

    protected void a(Result result) {
    }

    public abstract boolean a();

    public abstract boolean a(Context context, com.bykv.a.a.a.a.b bVar);

    public abstract c b();

    public abstract void b(Context context, com.bykv.a.a.a.a.b bVar);

    protected boolean b(Context context, AdConfig adConfig, TTAdSdk.InitCallback initCallback) {
        return false;
    }

    protected abstract com.bytedance.sdk.openadsdk.a.b c();

    public void a(final Context context, AdConfig adConfig, TTAdSdk.InitCallback initCallback) {
        com.bytedance.sdk.openadsdk.a.c.a().a(c());
        if (b(context, adConfig, initCallback)) {
            this.a = initCallback;
            final com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(com.bytedance.sdk.openadsdk.c.a.c.a.a(adConfig));
            bVarA.a(1, SystemClock.elapsedRealtime());
            bVarA.a(5, "main");
            bVarA.a(4, true);
            bVarA.a(6, 999);
            bVarA.a(10, 6310);
            bVarA.a(11, "6.3.1.0");
            bVarA.a(12, "com.byted.pangle");
            bVarA.a(14, false);
            bVarA.a(16, com.bytedance.sdk.openadsdk.a.c.a());
            Thread threadCurrentThread = Thread.currentThread();
            bVarA.a(2, threadCurrentThread.getName());
            bVarA.a(3, threadCurrentThread.getPriority());
            bVarA.a(15, new d());
            if (!a(context, bVarA)) {
                com.bytedance.sdk.openadsdk.e.a.a().a(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.b(context, bVarA);
                    }
                });
            }
            b().a(true);
        }
    }

    protected void a(Manager manager) {
        com.bytedance.sdk.openadsdk.api.c.b("_tt_ad_sdk_", "update manager");
        b().a(manager);
        b().register(com.bytedance.sdk.openadsdk.a.c.a());
    }

    protected void b(Result result) {
        a(result);
        if (result.isSuccess()) {
            com.bytedance.sdk.openadsdk.api.c.b("_tt_ad_sdk_", "init sdk success ");
            TTAdSdk.InitCallback initCallback = this.a;
            if (initCallback != null) {
                initCallback.success();
            }
        } else {
            com.bytedance.sdk.openadsdk.api.c.e("_tt_ad_sdk_", "int sdk failed, code: " + result.code() + ", message: " + result.message());
            TTAdSdk.InitCallback initCallback2 = this.a;
            if (initCallback2 != null) {
                initCallback2.fail(result.code(), result.message() != null ? result.message() : "");
            }
        }
        this.a = null;
    }

    private class d implements EventListener {
        private d() {
        }

        @Override // com.bykv.vk.openvk.api.proto.EventListener
        public ValueSet onEvent(int i, Result result) {
            a.this.b(result);
            return null;
        }
    }

    public static abstract class c implements TTAdManager {
        private volatile Manager a;
        private volatile boolean b;
        private List<WeakReference<b<Manager>>> c = new CopyOnWriteArrayList();

        protected Object a(Object obj) {
            return obj;
        }

        protected void a(Throwable th) {
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public String getSDKVersion() {
            return "6.3.1.0";
        }

        public void a(boolean z) {
            this.b = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Manager manager) {
            this.a = manager;
            if (this.a != null) {
                Iterator<WeakReference<b<Manager>>> it = this.c.iterator();
                while (it.hasNext()) {
                    WeakReference<b<Manager>> next = it.next();
                    b<Manager> bVar = next != null ? next.get() : null;
                    if (bVar != null) {
                        bVar.a(manager);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(b<Manager> bVar) {
            this.c.add(new WeakReference<>(bVar));
        }

        /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.api.a$c$1, reason: invalid class name */
        class AnonymousClass1 extends AbstractC0025a<Loader> {
            Loader a;
            final b<Manager> b;
            final /* synthetic */ SoftReference c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(SoftReference softReference) {
                super();
                this.c = softReference;
                this.b = new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.1.1
                    @Override // com.bytedance.sdk.openadsdk.api.a.b
                    public void a(Manager manager) {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        anonymousClass1.a = manager.createLoader((Context) anonymousClass1.c.get());
                    }
                };
            }

            @Override // com.bytedance.sdk.openadsdk.api.a.AbstractC0025a
            public void a(final b<Loader> bVar, int i) {
                Loader loader = this.a;
                if (loader == null) {
                    c.this.a(new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.1.2
                        @Override // com.bytedance.sdk.openadsdk.api.a.b
                        public void a(Manager manager) {
                            c.this.a(AnonymousClass1.this.b);
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            anonymousClass1.a = manager.createLoader((Context) anonymousClass1.c.get());
                            bVar.a(AnonymousClass1.this.a);
                        }
                    }, i + 10000);
                } else {
                    bVar.a(loader);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public TTAdNative createAdNative(Context context) {
            return new e(new AnonymousClass1(new SoftReference(context))).a();
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public void register(final Object obj) {
            a(new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.2
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Manager manager) {
                    manager.getBridge(1).call(4, com.bykv.a.a.a.a.b.a(1).a(8, c.this.a(obj)).b(), Void.class);
                }
            }, 4);
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public void unregister(final Object obj) {
            a(new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.3
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Manager manager) {
                    manager.getBridge(1).call(5, com.bykv.a.a.a.a.b.a(1).a(8, obj).b(), Void.class);
                }
            }, 5);
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public <T> T getExtra(final Class<T> cls, final Bundle bundle) {
            if (this.a != null) {
                return (T) b(this.a, cls, bundle);
            }
            a(new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.4
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Manager manager) {
                    c.b(c.this.a, cls, bundle);
                }
            }, 6);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T b(Manager manager, Class<T> cls, Bundle bundle) {
            return (T) manager.getBridge(1).call(6, com.bykv.a.a.a.a.b.a(2).a(9, cls).a(10, bundle).b(), cls);
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public void requestPermissionIfNecessary(final Context context) {
            a(new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.5
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Manager manager) {
                    manager.getBridge(1).call(3, com.bykv.a.a.a.a.b.a(1).a(7, context).b(), Void.class);
                }
            }, 3);
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public boolean tryShowInstallDialogWhenExit(Activity activity, ExitInstallListener exitInstallListener) {
            HashMap map = new HashMap();
            map.put(TTDownloadField.TT_ACTIVITY, activity);
            map.put(TTDownloadField.TT_EXIT_INSTALL_LISTENER, exitInstallListener);
            return ((Boolean) DownloadBridgeFactory.getDownloadBridge(TTAppContextHolder.getContext()).call(0, com.bykv.a.a.a.a.b.a(1).a(0, map).b(), Boolean.class)).booleanValue();
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public String getPluginVersion() {
            return this.a != null ? this.a.values().stringValue(12) : "";
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public String getBiddingToken(AdSlot adSlot) {
            return getBiddingToken(adSlot, false, adSlot.getAdType() > 0 ? adSlot.getAdType() : adSlot.getNativeAdType());
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public String getBiddingToken(AdSlot adSlot, boolean z, int i) {
            if (i <= 0) {
                i = adSlot.getAdType() > 0 ? adSlot.getAdType() : adSlot.getNativeAdType();
            }
            ValueSet valueSetB = com.bykv.a.a.a.a.b.a(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot)).a(13, z).a(14, i).b();
            if (this.a != null) {
                return (String) this.a.getBridge(1).call(2, valueSetB, String.class);
            }
            return null;
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public void setThemeStatus(final int i) {
            a(new b<Manager>() { // from class: com.bytedance.sdk.openadsdk.api.a.c.6
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Manager manager) {
                    manager.getBridge(1).call(1, com.bykv.a.a.a.a.b.a().a(11, i).b(), Void.class);
                }
            }, 1);
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdManager
        public int getThemeStatus() {
            if (this.a != null) {
                return this.a.values().intValue(11);
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(final b<Manager> bVar, final int i) {
            if (this.a != null) {
                try {
                    bVar.a(this.a);
                    return;
                } catch (Throwable th) {
                    com.bytedance.sdk.openadsdk.api.c.d("_tt_ad_sdk_", "Unexpected manager call error: " + th.getMessage());
                    a(th);
                    return;
                }
            }
            if (!this.b && i > 10000) {
                throw new IllegalStateException("广告SDK未Ready, 请在load(请求广告）之前，先调用init and start方法，以避免无法请求广告");
            }
            com.bytedance.sdk.openadsdk.e.a.a().a(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.a.c.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (c.this.a != null) {
                            bVar.a(c.this.a);
                        } else {
                            com.bytedance.sdk.openadsdk.api.c.d("_tt_ad_sdk_", "Not ready, no manager: " + i);
                        }
                    } catch (Throwable th2) {
                        com.bytedance.sdk.openadsdk.api.c.d("_tt_ad_sdk_", "Unexpected manager call error: " + th2.getMessage());
                        c.this.a(th2);
                    }
                }
            });
        }
    }

    private static class e extends com.bytedance.sdk.openadsdk.c.a.a {
        private AbstractC0025a<Loader> a;

        e(AbstractC0025a<Loader> abstractC0025a) {
            this.a = abstractC0025a;
        }

        private void a(b<Loader> bVar, int i) {
            try {
                com.bytedance.sdk.openadsdk.api.c.b("_tt_ad_sdk_", "load ad slot type: " + i);
                this.a.a(bVar, i);
            } catch (Throwable th) {
                this.a.a(th);
                throw th;
            }
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void a(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.1
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(5, com.bykv.a.a.a.a.b.a(valueSet).a(1, bridge).b(), null);
                }
            }, 5);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void b(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.3
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(6, com.bykv.a.a.a.a.b.a(valueSet).a(1, bridge).b(), null);
                }
            }, 6);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void c(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.4
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(9, com.bykv.a.a.a.a.b.a(valueSet).a(1, bridge).b(), null);
                }
            }, 9);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void d(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.5
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(1, com.bykv.a.a.a.a.b.a(valueSet).a(1, bridge).b(), null);
                }
            }, 1);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void a(final ValueSet valueSet, final Bridge bridge, final int i) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.6
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(3, com.bykv.a.a.a.a.b.a(valueSet).a(3, i).a(1, bridge).b(), null);
                }
            }, 3);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void e(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.7
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(7, com.bykv.a.a.a.a.b.a(valueSet).a(1, bridge).b(), null);
                }
            }, 7);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void f(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.8
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(8, com.bykv.a.a.a.a.b.a(valueSet).a(1, bridge).b(), null);
                }
            }, 8);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void g(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.9
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(5, com.bykv.a.a.a.a.b.a(valueSet).a(2, true).a(1, bridge).b(), null);
                }
            }, 5);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void h(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.10
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(9, com.bykv.a.a.a.a.b.a(valueSet).a(2, true).a(1, bridge).b(), null);
                }
            }, 9);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public void i(final ValueSet valueSet, final Bridge bridge) {
            a(new b<Loader>() { // from class: com.bytedance.sdk.openadsdk.api.a.e.2
                @Override // com.bytedance.sdk.openadsdk.api.a.b
                public void a(Loader loader) {
                    loader.load(1, com.bykv.a.a.a.a.b.a(valueSet).a(2, true).a(1, bridge).b(), null);
                }
            }, 1);
        }

        @Override // com.bytedance.sdk.openadsdk.c.a.a
        public Pair<Integer, String> a(Exception exc) {
            com.bytedance.sdk.openadsdk.api.c.d("_tt_ad_sdk_", "Load ad failed: " + exc.getMessage());
            if ((exc instanceof IllegalStateException) && "广告SDK未Ready, 请在load(请求广告）之前，先调用init and start方法，以避免无法请求广告".equals(exc.getMessage())) {
                return new Pair<>(4208, exc.getMessage());
            }
            return new Pair<>(Integer.valueOf(TTAdConstant.INIT_FAILED_CREATE_INVOKE_FAILED), "Load ad failed: " + exc.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.api.a$a, reason: collision with other inner class name */
    private static abstract class AbstractC0025a<T> {
        abstract void a(b<T> bVar, int i);

        void a(Throwable th) {
        }

        private AbstractC0025a() {
        }
    }
}
