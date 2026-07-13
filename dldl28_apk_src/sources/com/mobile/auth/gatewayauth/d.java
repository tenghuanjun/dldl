package com.mobile.auth.gatewayauth;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.activity.AuthWebVeiwActivity;
import com.mobile.auth.gatewayauth.manager.SystemManager;
import com.mobile.auth.gatewayauth.manager.compat.ResultCodeProcessor;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.mobile.auth.gatewayauth.model.UStruct;
import com.mobile.auth.gatewayauth.utils.ReflectionUtils;
import com.mobile.auth.gatewayauth.utils.i;
import com.mobile.auth.gatewayauth.utils.l;
import com.nirvana.tools.core.AppUtils;
import com.nirvana.tools.core.ExecutorManager;
import com.nirvana.tools.core.SupportJarUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class d {
    private final Context c;
    private AuthUIControlClickListener d;
    private volatile WeakReference<Activity> e;
    private volatile WeakReference<Activity> f;
    private AuthUIConfig g;
    private LinkedHashMap<String, AuthRegisterViewConfig> h;
    private LinkedHashMap<String, AuthRegisterViewConfig> i;
    private ArrayList<AuthRegisterXmlConfig> j;
    private ArrayList<AuthRegisterXmlConfig> k;
    private ArrayList<Object> l;
    private ArrayList<Object> m;
    private final com.mobile.auth.p.a o;
    private WeakReference<Activity> p;
    private TokenResultListener q;
    private ActivityResultListener r;
    private final PhoneNumberAuthHelper s;
    private final SystemManager t;
    private ResultCodeProcessor u;
    private final com.mobile.auth.gatewayauth.manager.d v;
    private long w;
    private long x;
    private static final ConcurrentHashMap<Integer, d> b = new ConcurrentHashMap<>(5);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AuthUIConfig f615a = new AuthUIConfig.Builder().create();
    private volatile boolean y = false;
    private volatile boolean z = false;
    private volatile boolean A = false;
    private volatile boolean B = false;
    private volatile boolean C = true;
    private volatile boolean D = false;
    private volatile boolean E = false;
    private final Application.ActivityLifecycleCallbacks F = new Application.ActivityLifecycleCallbacks() { // from class: com.mobile.auth.gatewayauth.d.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            try {
                d.this.a(activity);
            } catch (Throwable th) {
                ExceptionProcessor.processException(th);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            Activity activity2;
            try {
                if ((activity instanceof LoginAuthActivity) && ((LoginAuthActivity) activity).getUIManagerID() == d.a(d.this) && d.b(d.this) != null && (activity2 = (Activity) d.b(d.this).get()) != null && activity2 == activity) {
                    Application application = ReflectionUtils.getApplication();
                    if (application != null) {
                        application.unregisterActivityLifecycleCallbacks(d.c(d.this));
                    }
                    d.a(d.this, (WeakReference) null);
                }
            } catch (Throwable th) {
                ExceptionProcessor.processException(th);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            try {
                if ((activity instanceof LoginAuthActivity) && ((LoginAuthActivity) activity).getUIManagerID() == d.a(d.this)) {
                    d.a(d.this, false);
                }
            } catch (Throwable th) {
                ExceptionProcessor.processException(th);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            try {
                if ((activity instanceof LoginAuthActivity) && ((LoginAuthActivity) activity).getUIManagerID() == d.a(d.this)) {
                    d.a(d.this, true);
                }
            } catch (Throwable th) {
                ExceptionProcessor.processException(th);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    };
    private final int n = hashCode();

    public d(Context context, com.mobile.auth.gatewayauth.manager.d dVar, SystemManager systemManager, PhoneNumberAuthHelper phoneNumberAuthHelper) {
        this.c = context.getApplicationContext();
        this.v = dVar;
        this.o = dVar.a();
        this.t = systemManager;
        this.s = phoneNumberAuthHelper;
    }

    static /* synthetic */ int a(d dVar) {
        try {
            return dVar.n;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return -1;
        }
    }

    static /* synthetic */ AuthUIConfig a(d dVar, AuthUIConfig authUIConfig) {
        try {
            dVar.g = authUIConfig;
            return authUIConfig;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static d a(int i) {
        try {
            return b.get(Integer.valueOf(i));
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ WeakReference a(d dVar, WeakReference weakReference) {
        try {
            dVar.p = weakReference;
            return weakReference;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void a(AuthUIConfig authUIConfig, int i, Activity activity) {
        try {
            if (authUIConfig.isStatusBarHidden()) {
                l.a(activity);
            } else {
                l.c(activity, authUIConfig.getStatusBarUIFlag());
            }
            l.a(activity, i);
            l.b(activity, authUIConfig.getBottomNavBarColor());
            l.a(activity, authUIConfig.isLightColor());
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private void a(final String str, final String str2, final String str3) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.11
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.d(d.this).a(d.h(d.this).b(str, str2, UStruct.newUStruct().startTime(d.j(d.this)).endTime(System.currentTimeMillis()).requestId(d.h(d.this).e()).sessionId(d.h(d.this).c()).authSdkCode(ResultCode.CODE_ERROR_USER_PROTOCOL_CONTROL).carrierUrl(str3).build(), ""), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private void a(final String str, final String str2, final String str3, final String str4, final String str5) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.d(d.this).a(d.h(d.this).b(str, str2, UStruct.newUStruct().startTime(d.j(d.this)).endTime(System.currentTimeMillis()).requestId(d.h(d.this).e()).protocolName(str5).protocolUrl(str4).sessionId(d.h(d.this).c()).authSdkCode(d.i(d.this).convertCode(str3)).build(), ""), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private void a(final String str, final String str2, final boolean z) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.10
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.d(d.this).a(d.h(d.this).b(str, str2, UStruct.newUStruct().startTime(d.j(d.this)).endTime(System.currentTimeMillis()).requestId(d.h(d.this).e()).sessionId(d.h(d.this).c()).authSdkCode(ResultCode.CODE_START_AUTH_PRIVACY).isAuthPageLegal(String.valueOf(z)).build(), ""), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private void a(final String str, final String str2, boolean z, final boolean z2) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.d(d.this).a(d.h(d.this).b(str, str2, UStruct.newUStruct().startTime(d.j(d.this)).endTime(System.currentTimeMillis()).requestId(d.h(d.this).e()).sessionId(d.h(d.this).c()).authSdkCode(ResultCode.CODE_ERROR_USER_LOGIN_BTN).isAuthPageLegal(String.valueOf(z2)).build(), ""), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private void a(final boolean z, final String str, final String str2, final boolean z2) {
        try {
            ExecutorManager.getInstance().postMain(new ExecutorManager.SafeRunnable() { // from class: com.mobile.auth.gatewayauth.d.6
                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void onException(Throwable th) {
                    try {
                        d.d(d.this).e("QuitActivity error!", ExecutorManager.getErrorInfoFromException(th));
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }

                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void onFinal() {
                    try {
                        super.onFinal();
                        if (z2 && d.f(d.this) != null && z) {
                            TokenRet tokenRetConvertErrorInfo = d.this.a().convertErrorInfo(str, str2, d.g(d.this).c());
                            tokenRetConvertErrorInfo.setVendorName(d.g(d.this).d());
                            tokenRetConvertErrorInfo.setRequestId(d.h(d.this).e());
                            d.f(d.this).onTokenFailed(tokenRetConvertErrorInfo.toJsonString());
                        }
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }

                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void safeRun() {
                    Activity activity;
                    try {
                        if (d.b(d.this) != null && (activity = (Activity) d.b(d.this).get()) != null) {
                            activity.finish();
                            d dVar = d.this;
                            d.a(dVar, dVar.q());
                            if (d.e(d.this).getAuthPageActOut() != null && d.e(d.this).getActivityIn() != null) {
                                activity.overridePendingTransition(AppUtils.getAnimResID(activity, d.e(d.this).getAuthPageActOut()), AppUtils.getAnimResID(activity, d.e(d.this).getActivityIn()));
                            }
                        }
                        d.this.A();
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    static /* synthetic */ boolean a(d dVar, boolean z) {
        try {
            dVar.y = z;
            return z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    static /* synthetic */ WeakReference b(d dVar) {
        try {
            return dVar.p;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ WeakReference b(d dVar, WeakReference weakReference) {
        try {
            dVar.f = weakReference;
            return weakReference;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    private void b(final String str, final String str2, final String str3) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.d(d.this).a(d.h(d.this).b(str, str2, UStruct.newUStruct().startTime(d.j(d.this)).endTime(System.currentTimeMillis()).requestId(d.h(d.this).e()).sessionId(d.h(d.this).c()).authSdkCode(d.i(d.this).convertCode(str3)).build(), ""), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    static /* synthetic */ Application.ActivityLifecycleCallbacks c(d dVar) {
        try {
            return dVar.F;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    private void c(final String str, final String str2, final String str3) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.d(d.this).a(d.h(d.this).b(str, str2, UStruct.newUStruct().startTime(d.j(d.this)).endTime(System.currentTimeMillis()).requestId(d.h(d.this).e()).suspendDisMissVC(d.k(d.this)).sessionId(d.h(d.this).c()).authSdkCode(d.i(d.this).convertCode(str3)).build(), ""), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    static /* synthetic */ com.mobile.auth.p.a d(d dVar) {
        try {
            return dVar.o;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ AuthUIConfig e(d dVar) {
        try {
            return dVar.g;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ TokenResultListener f(d dVar) {
        try {
            return dVar.q;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ SystemManager g(d dVar) {
        try {
            return dVar.t;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ com.mobile.auth.gatewayauth.manager.d h(d dVar) {
        try {
            return dVar.v;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ ResultCodeProcessor i(d dVar) {
        try {
            return dVar.u;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ long j(d dVar) {
        try {
            return dVar.w;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return -1L;
        }
    }

    static /* synthetic */ boolean k(d dVar) {
        try {
            return dVar.D;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void A() {
        try {
            b.remove(Integer.valueOf(this.n));
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public com.mobile.auth.p.a B() {
        try {
            return this.o;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public boolean C() {
        try {
            return this.y;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public ResultCodeProcessor a() {
        try {
            if (this.u == null) {
                this.u = new com.mobile.auth.gatewayauth.manager.compat.a();
            }
            return this.u;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void a(long j) {
        try {
            this.w = j;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(long j, String str, String str2, ResultCodeProcessor resultCodeProcessor, e eVar) {
        Context context;
        try {
            this.x = j;
            d();
            Intent intent = new Intent(this.c, (Class<?>) LoginAuthActivity.class);
            intent.putExtra(Constant.LOGIN_ACTIVITY_NUMBER, str);
            intent.putExtra(Constant.LOGIN_ACTIVITY_VENDOR_KEY, str2);
            intent.putExtra(Constant.LOGIN_ACTIVITY_UI_MANAGER_ID, this.n);
            intent.putExtra(Constant.START_TIME, System.currentTimeMillis());
            try {
                a(resultCodeProcessor);
                b.put(Integer.valueOf(this.n), this);
                Activity activity = this.e != null ? this.e.get() : null;
                if (q().getAuthPageActIn() == null || q().getActivityOut() == null) {
                    if (activity != null) {
                        activity.startActivityForResult(intent, 1);
                    } else {
                        intent.addFlags(268435456);
                        context = this.c;
                        context.startActivity(intent);
                    }
                } else if (activity != null) {
                    String authPageActIn = q().getAuthPageActIn();
                    String activityOut = q().getActivityOut();
                    if (TextUtils.isEmpty(authPageActIn) || TextUtils.isEmpty(activityOut)) {
                        SupportJarUtils.startActivityForResult(activity, intent, 1, null, null);
                    } else {
                        SupportJarUtils.startActivityForResult(activity, intent, 1, authPageActIn, activityOut);
                    }
                } else {
                    intent.addFlags(268435456);
                    context = this.c;
                    context.startActivity(intent);
                }
                if (eVar != null) {
                    eVar.a(str2, str);
                    return;
                }
                return;
            } catch (Exception e) {
                String errorInfoFromException = ExecutorManager.getErrorInfoFromException(e);
                i.c(errorInfoFromException);
                eVar.a(errorInfoFromException);
                A();
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public void a(Activity activity) {
        Intent intent;
        try {
            if ((activity instanceof LoginAuthActivity) && (intent = activity.getIntent()) != null && intent.getIntExtra(Constant.LOGIN_ACTIVITY_UI_MANAGER_ID, -1) == this.n) {
                this.p = new WeakReference<>(activity);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(ActivityResultListener activityResultListener) {
        try {
            this.r = activityResultListener;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(AuthRegisterXmlConfig authRegisterXmlConfig) {
        try {
            try {
                if (this.j == null) {
                    this.j = new ArrayList<>();
                }
                this.j.add(authRegisterXmlConfig);
                a((Object) authRegisterXmlConfig);
            } catch (Exception e) {
                i.a(e);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(AuthUIConfig authUIConfig) {
        try {
            this.g = authUIConfig;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(AuthUIControlClickListener authUIControlClickListener) {
        try {
            this.d = authUIControlClickListener;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(TokenResultListener tokenResultListener) {
        try {
            this.q = tokenResultListener;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(ResultCodeProcessor resultCodeProcessor) {
        try {
            this.u = resultCodeProcessor;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(Class<?> cls, int i, int i2) {
        Activity activity;
        try {
            WeakReference<Activity> weakReference = this.p;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                return;
            }
            ((LoginAuthActivity) activity).openUserPage(cls, i, i2);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(Object obj) {
        try {
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            this.l.add(obj);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(String str) {
        try {
            AuthUIControlClickListener authUIControlClickListener = this.d;
            if (authUIControlClickListener != null) {
                authUIControlClickListener.onClick(ResultCode.CODE_ERROR_USER_CANCEL, this.c, null);
            }
            c(str, this.t.j(str), Constant.CODE_ERROR_USER_CANCEL);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(final String str, final long j, final boolean z, final boolean z2) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.d.8
                @Override // java.lang.Runnable
                public void run() {
                    ResultCodeProcessor resultCodeProcessorI;
                    String str2;
                    try {
                        com.mobile.auth.p.a aVarD = d.d(d.this);
                        com.mobile.auth.gatewayauth.manager.d dVarH = d.h(d.this);
                        String str3 = str;
                        String strM = d.g(d.this).m(str);
                        UStruct.Builder builderEndTime = UStruct.newUStruct().isSuccess(z2).isFullScreen(String.valueOf(!d.this.q().isDialog())).isVertical(String.valueOf(z)).isChecked(String.valueOf(d.this.q().isCheckboxHidden() || d.this.q().isPrivacyState())).isCheckboxHidden(String.valueOf(d.this.q().isCheckboxHidden())).requestId(d.h(d.this).e()).sessionId(d.h(d.this).c()).startTime(j).endTime(d.j(d.this));
                        if (z2) {
                            resultCodeProcessorI = d.i(d.this);
                            str2 = Constant.CODE_START_AUTH_PAGE_SUCCESS;
                        } else {
                            resultCodeProcessorI = d.i(d.this);
                            str2 = Constant.CODE_ERROR_START_AUTH_PAGE_FAIL;
                        }
                        aVarD.a(dVarH.b(str3, strM, builderEndTime.authSdkCode(resultCodeProcessorI.convertCode(str2)).build(), ""), 1);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(String str, AuthRegisterViewConfig authRegisterViewConfig) {
        try {
            try {
                if (this.h == null) {
                    this.h = new LinkedHashMap<>();
                }
                this.h.put(str, authRegisterViewConfig);
                if (authRegisterViewConfig.getRootViewId() == 0) {
                    a(authRegisterViewConfig);
                    return;
                }
                return;
            } catch (Exception e) {
                i.a(e);
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public void a(String str, String str2) {
        Activity activity;
        Intent intent;
        try {
            WeakReference<Activity> weakReference = this.p;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                this.o.e("LoginAuthActivity实例被释放");
                return;
            }
            AuthUIConfig authUIConfigQ = q();
            this.g = authUIConfigQ;
            if (TextUtils.isEmpty(authUIConfigQ.getProtocolAction())) {
                intent = new Intent(activity, (Class<?>) AuthWebVeiwActivity.class);
                intent.putExtra("url", str2);
                intent.putExtra("name", str);
                intent.putExtra("orientation", q().getScreenOrientation());
                intent.putExtra(Constant.LOGIN_ACTIVITY_UI_MANAGER_ID, this.n);
            } else {
                intent = new Intent();
                intent.setAction(this.g.getProtocolAction());
                if (!TextUtils.isEmpty(this.g.getPackageName())) {
                    intent.setPackage(this.g.getPackageName());
                }
                intent.putExtra("url", str2);
                intent.putExtra("name", str);
                intent.putExtra("orientation", q().getScreenOrientation());
            }
            activity.startActivity(intent);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(String str, String str2, String str3, boolean z) {
        try {
            if (this.d != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("name", str2);
                    jSONObject.put("url", str3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.d.onClick(ResultCode.CODE_ERROR_USER_PROTOCOL_CONTROL, this.c, jSONObject.toString());
            }
            if (z) {
                a(str, this.t.l(str), str3);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(String str, boolean z, boolean z2) {
        try {
            if (this.d != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("isChecked", z);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.d.onClick(ResultCode.CODE_ERROR_USER_LOGIN_BTN, this.c, jSONObject.toString());
            }
            a(str, this.t.h(str), z, z2);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(boolean z) {
        try {
            this.z = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void a(boolean z, String str, String str2) {
        try {
            a(z, str, str2, true);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(Activity activity) {
        try {
            this.e = new WeakReference<>(activity);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(AuthRegisterXmlConfig authRegisterXmlConfig) {
        try {
            try {
                if (this.k == null) {
                    this.k = new ArrayList<>();
                }
                this.k.add(authRegisterXmlConfig);
            } catch (Exception e) {
                i.a(e);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(ResultCodeProcessor resultCodeProcessor) {
        try {
            this.s.a(this.x, new TokenResultListener() { // from class: com.mobile.auth.gatewayauth.d.4
                @Override // com.mobile.auth.gatewayauth.TokenResultListener
                public void onTokenFailed(String str) {
                    try {
                        if (d.f(d.this) != null) {
                            d.f(d.this).onTokenFailed(str);
                        }
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }

                @Override // com.mobile.auth.gatewayauth.TokenResultListener
                public void onTokenSuccess(String str) {
                    try {
                        if (d.f(d.this) != null) {
                            d.f(d.this).onTokenSuccess(str);
                        }
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            }, resultCodeProcessor);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(Object obj) {
        try {
            if (this.m == null) {
                this.m = new ArrayList<>();
            }
            this.m.add(obj);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(String str) {
        try {
            AuthUIControlClickListener authUIControlClickListener = this.d;
            if (authUIControlClickListener != null) {
                authUIControlClickListener.onClick(ResultCode.CODE_ERROR_USER_CONTROL_CANCEL_BYBTN, this.c, null);
            }
            c(str, this.t.j(str), ResultCode.CODE_ERROR_USER_CONTROL_CANCEL_BYBTN);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(String str, AuthRegisterViewConfig authRegisterViewConfig) {
        try {
            try {
                if (this.i == null) {
                    this.i = new LinkedHashMap<>();
                }
                this.i.put(str, authRegisterViewConfig);
                if (authRegisterViewConfig.getRootViewId() == 0) {
                    b(authRegisterViewConfig);
                    return;
                }
                return;
            } catch (Exception e) {
                i.a(e);
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public void b(String str, String str2, String str3, boolean z) {
        try {
            if (this.d != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("name", str2);
                    jSONObject.put("url", str3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.d.onClick(ResultCode.CODE_CLICK_AUTH_PRIVACY_WEBURL, this.c, jSONObject.toString());
            }
            if (z) {
                a(str, this.t.k(str), ResultCode.CODE_CLICK_AUTH_PRIVACY_WEBURL, str3, str2);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(String str, boolean z, boolean z2) {
        try {
            if (this.d != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("isChecked", z);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.d.onClick(ResultCode.CODE_START_AUTH_PRIVACY, this.c, jSONObject.toString());
            }
            a(str, this.t.i(str), z2);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void b(boolean z) {
        try {
            this.A = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public boolean b() {
        try {
            return this.E;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void c() {
        try {
            this.E = true;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void c(Activity activity) {
        try {
            this.f = new WeakReference<>(activity);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void c(String str) {
        try {
            AuthUIControlClickListener authUIControlClickListener = this.d;
            if (authUIControlClickListener != null) {
                authUIControlClickListener.onClick(ResultCode.CODE_ERROR_USER_CONTROL_CANCEL_BYKEY, this.c, null);
            }
            c(str, this.t.j(str), ResultCode.CODE_ERROR_USER_CONTROL_CANCEL_BYKEY);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void c(boolean z) {
        try {
            this.B = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void d() {
        try {
            Application application = ReflectionUtils.getApplication();
            if (application != null) {
                application.registerActivityLifecycleCallbacks(this.F);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void d(final Activity activity) {
        try {
            ExecutorManager.getInstance().postMain(new ExecutorManager.SafeRunnable() { // from class: com.mobile.auth.gatewayauth.d.7
                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void onException(Throwable th) {
                    try {
                        d.d(d.this).e("QuitActivity error!", ExecutorManager.getErrorInfoFromException(th));
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }

                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void onFinal() {
                    try {
                        super.onFinal();
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }

                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void safeRun() {
                    try {
                        Activity activity2 = activity;
                        if (activity2 != null) {
                            activity2.finish();
                            d.b(d.this, (WeakReference) null);
                            d dVar = d.this;
                            d.a(dVar, dVar.q());
                            if (d.e(d.this).getPrivacyAlertExitAnimation() == null || d.e(d.this).getPrivacyAlertEntryAnimation() == null) {
                                return;
                            }
                            Activity activity3 = activity;
                            activity3.overridePendingTransition(AppUtils.getAnimResID(activity3, d.e(d.this).getPrivacyAlertEntryAnimation()), AppUtils.getAnimResID(activity, d.e(d.this).getPrivacyAlertExitAnimation()));
                        }
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void d(String str) {
        try {
            AuthUIControlClickListener authUIControlClickListener = this.d;
            if (authUIControlClickListener != null) {
                authUIControlClickListener.onClick(ResultCode.CODE_ERROR_USER_SWITCH, this.c, null);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void d(boolean z) {
        try {
            this.D = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void e(String str) {
        try {
            AuthUIControlClickListener authUIControlClickListener = this.d;
            if (authUIControlClickListener != null) {
                authUIControlClickListener.onClick(ResultCode.CODE_CLICK_AUTH_PRIVACY_CONFIRM, this.c, null);
            }
            b(str, Constant.ACTION_CLICK_PRIVACYALERT_CONFIRM, ResultCode.CODE_CLICK_AUTH_PRIVACY_CONFIRM);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void e(boolean z) {
        try {
            this.C = z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public boolean e() {
        try {
            return this.z;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void f(String str) {
        try {
            AuthUIControlClickListener authUIControlClickListener = this.d;
            if (authUIControlClickListener != null) {
                authUIControlClickListener.onClick(ResultCode.CODE_AUTH_PRIVACY_CLOSE, this.c, null);
            }
            b(str, Constant.ACTION_PRIVACYALERT_CLOSE, ResultCode.CODE_AUTH_PRIVACY_CLOSE);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void f(boolean z) {
        Activity activity;
        try {
            WeakReference<Activity> weakReference = this.p;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                return;
            }
            ((LoginAuthActivity) activity).setProtocolChecked(z);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public boolean f() {
        try {
            return this.A;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void g(boolean z) {
        try {
            if (this.d != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("isChecked", z);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.d.onClick(ResultCode.CODE_ERROR_USER_CHECKBOX, this.c, jSONObject.toString());
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public boolean g() {
        try {
            return this.B;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public boolean h() {
        try {
            return this.D;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public boolean i() {
        try {
            return this.C;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void j() {
        try {
            ExecutorManager.getInstance().postMain(new ExecutorManager.SafeRunnable() { // from class: com.mobile.auth.gatewayauth.d.5
                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void onException(Throwable th) {
                    try {
                        d.d(d.this).e("Hide Loading error!", ExecutorManager.getErrorInfoFromException(th));
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }

                @Override // com.nirvana.tools.core.ExecutorManager.SafeRunnable
                protected void safeRun() {
                    Activity activity;
                    try {
                        if (d.b(d.this) == null || (activity = (Activity) d.b(d.this).get()) == null) {
                            return;
                        }
                        ((LoginAuthActivity) activity).hideLoadingDialog();
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void k() {
        Activity activity;
        try {
            WeakReference<Activity> weakReference = this.p;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                return;
            }
            ((LoginAuthActivity) activity).animateProtocolTV();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void l() {
        Activity activity;
        try {
            WeakReference<Activity> weakReference = this.p;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                return;
            }
            ((LoginAuthActivity) activity).animateCheckBox();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public boolean m() {
        Activity activity;
        try {
            WeakReference<Activity> weakReference = this.p;
            if (weakReference == null || (activity = weakReference.get()) == null) {
                return true;
            }
            return ((LoginAuthActivity) activity).queryCheckBoxIsChecked();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void n() {
        try {
            a(false, (String) null, (String) null, false);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public ActivityResultListener o() {
        try {
            return this.r;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void p() {
        try {
            if (this.q != null) {
                TokenRet tokenRet = new TokenRet();
                tokenRet.setVendorName(this.t.d());
                tokenRet.setCode(ResultCode.CODE_ERROR_LOAD_CUSTOM_VIEWS);
                tokenRet.setMsg(ResultCode.MSG_ERROR_LOAD_CUSTOM_VIEWS);
                this.q.onTokenFailed(tokenRet.toJsonString());
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public AuthUIConfig q() {
        try {
            AuthUIConfig authUIConfig = this.g;
            return authUIConfig == null ? f615a : authUIConfig;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public LinkedHashMap<String, AuthRegisterViewConfig> r() {
        try {
            if (this.h == null) {
                this.h = new LinkedHashMap<>();
            }
            return this.h;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public LinkedHashMap<String, AuthRegisterViewConfig> s() {
        try {
            if (this.i == null) {
                this.i = new LinkedHashMap<>();
            }
            return this.i;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public ArrayList<AuthRegisterXmlConfig> t() {
        try {
            if (this.j == null) {
                this.j = new ArrayList<>();
            }
            return this.j;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public ArrayList<AuthRegisterXmlConfig> u() {
        try {
            if (this.k == null) {
                this.k = new ArrayList<>();
            }
            return this.k;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void v() {
        try {
            try {
                LinkedHashMap<String, AuthRegisterViewConfig> linkedHashMap = this.h;
                if (linkedHashMap != null) {
                    ArrayList<Object> arrayList = this.l;
                    if (arrayList != null) {
                        arrayList.removeAll(linkedHashMap.values());
                    }
                    this.h.clear();
                    this.h = null;
                    return;
                }
                return;
            } catch (Exception e) {
                i.a(e);
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public void w() {
        try {
            try {
                LinkedHashMap<String, AuthRegisterViewConfig> linkedHashMap = this.i;
                if (linkedHashMap != null) {
                    ArrayList<Object> arrayList = this.m;
                    if (arrayList != null) {
                        arrayList.removeAll(linkedHashMap.values());
                    }
                    this.i.clear();
                    this.i = null;
                    return;
                }
                return;
            } catch (Exception e) {
                i.a(e);
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public void x() {
        try {
            try {
                ArrayList<AuthRegisterXmlConfig> arrayList = this.k;
                if (arrayList != null) {
                    arrayList.clear();
                    this.k = null;
                    return;
                }
                return;
            } catch (Exception e) {
                i.a(e);
                return;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
        ExceptionProcessor.processException(th);
    }

    public void y() {
        try {
            try {
                ArrayList<AuthRegisterXmlConfig> arrayList = this.j;
                if (arrayList != null) {
                    ArrayList<Object> arrayList2 = this.l;
                    if (arrayList2 != null) {
                        arrayList2.removeAll(arrayList);
                    }
                    this.j.clear();
                    this.j = null;
                }
            } catch (Exception e) {
                i.a(e);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void z() {
        try {
            if (this.f == null || this.f.get() == null || !(this.f.get() instanceof PrivacyDialogActivity)) {
                return;
            }
            ((PrivacyDialogActivity) this.f.get()).cancelPrivacyDialog();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
