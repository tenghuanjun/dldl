package com.bytedance.sdk.openadsdk.live;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.android.live.base.api.ILiveHostContextParam;
import com.bytedance.android.live.base.api.ILiveInitCallback;
import com.bytedance.android.live.base.api.IOuterLiveService;
import com.bytedance.android.live.base.api.MethodChannelService;
import com.bytedance.android.openliveplugin.LivePluginHelper;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.a.c;
import com.bytedance.sdk.openadsdk.c.a.a.i;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b implements Bridge {
    private static final b a = new b();
    private ITTLiveTokenInjectionAuth b;
    private volatile Bridge c;
    private Map<String, String> d;
    private volatile C0030b e;

    private b() {
    }

    public static b a() {
        return a;
    }

    public static Bridge a(ILiveAdCustomConfig iLiveAdCustomConfig) {
        return new a(iLiveAdCustomConfig);
    }

    public void a(ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth) {
        this.b = iTTLiveTokenInjectionAuth;
    }

    public void a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        this.d = map;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a().a(10000, 2).b();
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        if (i == 5) {
            b((Map) valueSet.objectValue(0, Map.class));
            return null;
        }
        if (i == 9) {
            this.c = (Bridge) valueSet.objectValue(0, Bridge.class);
            if (this.e != null) {
                if (this.e.a != 2 && this.e.a != -3) {
                    if (this.e.a != 0) {
                        this.e.a(this.e.a, null, null, null, true);
                    }
                } else {
                    this.e.a(this.e.a, this.e.b, true);
                }
            } else {
                this.e = new C0030b();
                Bridge bridge = (Bridge) c.a().call(10, com.bykv.a.a.a.a.b.a(1).a(0, 4).b(), Bridge.class);
                ValueSet valueSetB = com.bykv.a.a.a.a.b.a(1).a(0, this.e).b();
                if (bridge != null) {
                    bridge.call(106, valueSetB, Void.class);
                }
            }
            return null;
        }
        return (T) a(cls, i, (Map) valueSet.objectValue(0, Map.class));
    }

    private void b(Map map) {
        ILiveHostContextParam.Builder hostActionParam = new ILiveHostContextParam.Builder().setAppName(String.valueOf(map.get("app_name"))).setChannel(String.valueOf(map.get("channel"))).setIsDebug(Boolean.valueOf(String.valueOf(map.get("debug"))).booleanValue()).setECHostAppId(String.valueOf(map.get("ec_host_appid"))).setPartner(String.valueOf(map.get(com.alipay.sdk.app.statistic.c.ab))).provideMethodChannel(new MethodChannelService() { // from class: com.bytedance.sdk.openadsdk.live.b.1
            @Override // com.bytedance.android.live.base.api.MethodChannelService
            public String identity() {
                return MediationConstant.ADN_PANGLE;
            }

            @Override // com.bytedance.android.live.base.api.MethodChannelService
            public Object invokeMethod(String str, Map<String, String> map2) {
                if (b.this.c != null) {
                    return b.this.c.call(0, com.bykv.a.a.a.a.b.a().a(0, str).a(1, map2).b(), Object.class);
                }
                return null;
            }
        }).setPartnerSecret("p_secret").setHostPermission(new com.bytedance.sdk.openadsdk.live.a.a(c(map))).setHostActionParam(new com.bytedance.sdk.openadsdk.live.a.b(this.c));
        ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth = this.b;
        if (iTTLiveTokenInjectionAuth != null) {
            hostActionParam.setInjectionAuth(new com.bytedance.sdk.openadsdk.live.a.c(iTTLiveTokenInjectionAuth));
        }
        Map<String, String> map2 = this.d;
        if (map2 != null) {
            hostActionParam.addHostInitExtra(map2);
        }
        ILiveInitCallback iLiveInitCallback = new ILiveInitCallback() { // from class: com.bytedance.sdk.openadsdk.live.b.2
            @Override // com.bytedance.android.live.base.api.ILiveInitCallback
            public void onLiveInitFinish() {
                com.bytedance.sdk.openadsdk.api.c.b("TTLiveSDkBridge", "onLiveInitFinish!");
                com.bytedance.sdk.openadsdk.live.a.a();
                if (b.this.e != null) {
                    b.this.e.a(2, null, false);
                }
                b.this.d = null;
                b.this.b = null;
            }

            @Override // com.bytedance.android.live.base.api.ILiveInitCallback
            public void onLiveInitFailed(String str) {
                com.bytedance.sdk.openadsdk.api.c.a("TTLiveSDkBridge", "onLiveInitFailed! ", str);
                if (b.this.e != null) {
                    b.this.e.a(-3, str, false);
                }
            }
        };
        if (TTAppContextHolder.getContext() instanceof Application) {
            hostActionParam.setContext((Application) TTAppContextHolder.getContext());
        }
        boolean zBooleanValue = Boolean.valueOf(String.valueOf(map.get("sub_process"))).booleanValue();
        com.bytedance.sdk.openadsdk.api.c.a("TTLiveSDkBridge", "execute live sdk initLive method end, (方法顺利执行结果)result: ", Boolean.valueOf(com.bytedance.sdk.openadsdk.live.a.a(TTAppContextHolder.getContext(), String.valueOf(map.get("g_appid")), hostActionParam, iLiveInitCallback, zBooleanValue)), " subProcess=", Boolean.valueOf(zBooleanValue));
    }

    public <T> T a(Class<T> cls, int i, Map<String, Object> map) {
        if (i == 0) {
            if (!com.bytedance.sdk.openadsdk.live.a.a(a(map.get("context")), b(map.get("bundle")))) {
                return (T) 2;
            }
            return (T) 0;
        }
        if (i == 7) {
            return (T) d(map);
        }
        if (i != 8) {
            return null;
        }
        return (T) e(map);
    }

    private TTCustomController c(Map map) {
        Object obj = map.get("c_control");
        if (obj instanceof Bridge) {
            return new i((Bridge) obj);
        }
        return null;
    }

    private Context a(Object obj) {
        if (obj instanceof Context) {
            return (Context) obj;
        }
        return null;
    }

    private Bundle b(Object obj) {
        if (obj instanceof Bundle) {
            return (Bundle) obj;
        }
        return null;
    }

    private Object d(Map<String, Object> map) {
        try {
            String str = (String) map.get("expand_method_name");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            IOuterLiveService liveRoomService = LivePluginHelper.getLiveRoomService();
            Object[] objArr = (Object[]) map.get("expand_method_param");
            if (objArr != null) {
                return liveRoomService.callExpandMethod(str, objArr);
            }
            return liveRoomService.callExpandMethod(str, new Object[0]);
        } catch (Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.b("TTLiveSDkBridge", th);
            return null;
        }
    }

    private Boolean e(Map<String, Object> map) {
        try {
            String str = (String) map.get("scheme_uri");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Context contextA = a(map.get("context"));
            Uri uri = Uri.parse(str);
            if (uri != null && contextA != null) {
                return Boolean.valueOf(com.bytedance.sdk.openadsdk.live.a.a(contextA, uri));
            }
            return false;
        } catch (Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.b("TTLiveSDkBridge", th);
            return false;
        }
    }

    private static final class a implements Bridge {
        private ILiveAdCustomConfig a;

        a(ILiveAdCustomConfig iLiveAdCustomConfig) {
            this.a = iLiveAdCustomConfig;
        }

        @Override // com.bykv.vk.openvk.api.proto.Bridge
        public ValueSet values() {
            return com.bykv.a.a.a.a.b.a().a(10000, 1).b();
        }

        @Override // com.bykv.vk.openvk.api.proto.Caller
        public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
            if (i == 0) {
                return (T) Integer.valueOf(this.a.openLR(valueSet.stringValue(0)));
            }
            if (i == 1) {
                return (T) this.a.convertToEnterFromMerge(valueSet.intValue(0));
            }
            if (i == 2) {
                return (T) this.a.convertToEnterMethod(valueSet.intValue(0), valueSet.booleanValue(1));
            }
            if (i == 3) {
                return (T) this.a.invoke(valueSet.intValue(0), (Bundle) valueSet.objectValue(1, Bundle.class));
            }
            if (i != 4) {
                return null;
            }
            this.a.onEventV3(valueSet.stringValue(0), (JSONObject) valueSet.objectValue(1, JSONObject.class));
            return null;
        }
    }

    /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.live.b$b, reason: collision with other inner class name */
    private final class C0030b implements TTPluginListener {
        int a;
        String b;

        @Override // com.bytedance.sdk.openadsdk.TTPluginListener
        public Bundle config() {
            return null;
        }

        @Override // com.bytedance.sdk.openadsdk.TTPluginListener
        public String packageName() {
            return "com.byted.live.lite";
        }

        private C0030b() {
        }

        @Override // com.bytedance.sdk.openadsdk.TTPluginListener
        public void onPluginListener(int i, ClassLoader classLoader, Resources resources, Bundle bundle) {
            a(i, classLoader, resources, bundle, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, ClassLoader classLoader, Resources resources, Bundle bundle, boolean z) {
            this.a = i;
            if (b.this.c != null) {
                b.this.c.call(3, com.bykv.a.a.a.a.b.a().a(0, i).a(1, classLoader).a(2, resources).a(3, bundle).a(4, b.this.a(z)).b(), null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, String str, boolean z) {
            this.a = i;
            this.b = str;
            if (b.this.c != null) {
                com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a().a(0, i);
                if (str != null) {
                    bVarA.a(1, str);
                }
                bVarA.a(2, b.this.a(z));
                b.this.c.call(2, bVarA.b(), null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map a(boolean z) {
        HashMap map = new HashMap();
        map.put("onlyUpdateState", Boolean.valueOf(z));
        return map;
    }
}
