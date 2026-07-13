package com.bytedance.applog.exposure;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.bdtracker.a;
import com.bytedance.bdtracker.l0;
import com.bytedance.bdtracker.l3;
import com.bytedance.bdtracker.n0;
import com.bytedance.bdtracker.o0;
import com.bytedance.bdtracker.p0;
import com.bytedance.bdtracker.q0;
import com.bytedance.bdtracker.r0;
import com.bytedance.bdtracker.t4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\bJ\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\bJ\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u0016\u0010\u001e\u001a\u00020\u00172\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170 H\u0002J\u001a\u0010!\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\"\u001a\u00020\u0017H\u0002R&\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/bytedance/applog/exposure/ViewExposureManager;", "", "appLog", "Lcom/bytedance/applog/AppLogInstance;", "(Lcom/bytedance/applog/AppLogInstance;)V", "activitiesMap", "Ljava/util/WeakHashMap;", "Landroid/app/Activity;", "Landroid/view/View;", "Lcom/bytedance/applog/exposure/ViewExposureHolder;", "getAppLog", "()Lcom/bytedance/applog/AppLogInstance;", "checkTask", "Ljava/lang/Runnable;", "globalConfig", "Lcom/bytedance/applog/exposure/ViewExposureConfig;", "mainHandler", "Landroid/os/Handler;", "started", "", "viewTreeChangeObserver", "Lcom/bytedance/applog/exposure/ViewTreeChangeObserver;", "checkViewExposureFromActivity", "", "activity", "disposeViewExposure", "view", "observeViewExposure", "data", "Lcom/bytedance/applog/exposure/ViewExposureData;", "runSafely", "task", "Lkotlin/Function0;", "sendViewExposureEvent", "start", "Companion", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
public final class ViewExposureManager {
    public static final ViewExposureConfig h = new ViewExposureConfig(Float.valueOf(1.0f), null, 2, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakHashMap<Activity, WeakHashMap<View, o0>> f187a;
    public boolean b;
    public r0 c;
    public final ViewExposureConfig d;
    public final Handler e;
    public final Runnable f;
    public final com.bytedance.bdtracker.d g;

    public static final class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Activity activity = ViewExposureManager.this.c.f313a.get();
            if (activity != null) {
                ViewExposureManager.this.a(activity);
            }
        }
    }

    public static final class c extends Lambda implements Function0<Unit> {
        public final /* synthetic */ Activity b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Activity activity) {
            super(0);
            this.b = activity;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            boolean z;
            WeakHashMap weakHashMap = (WeakHashMap) ViewExposureManager.this.f187a.get(this.b);
            if (weakHashMap != null) {
                Intrinsics.checkExpressionValueIsNotNull(weakHashMap, "activitiesMap[activity] ?: return@runSafely");
                for (Map.Entry entry : weakHashMap.entrySet()) {
                    View view = (View) entry.getKey();
                    o0 o0Var = (o0) entry.getValue();
                    ViewExposureData viewExposureDataA = o0Var.a();
                    boolean z2 = o0Var.b;
                    Intrinsics.checkExpressionValueIsNotNull(view, "view");
                    ViewExposureConfig config = viewExposureDataA.getConfig();
                    if (z2 != n0.a(view, config != null ? config.getAreaRatio() : null)) {
                        if (o0Var.b) {
                            z = false;
                        } else {
                            ViewExposureManager.this.a(view, viewExposureDataA);
                            z = true;
                        }
                        o0Var.b = z;
                        ViewExposureConfig config2 = viewExposureDataA.getConfig();
                        if (Intrinsics.areEqual((Object) (config2 != null ? config2.getVisualDiagnosis() : null), (Object) true)) {
                            int i = o0Var.b ? SupportMenu.CATEGORY_MASK : InputDeviceCompat.SOURCE_ANY;
                            if (view instanceof ImageView) {
                                ImageView imageView = (ImageView) view;
                                if (imageView.getDrawable() instanceof l0) {
                                    Drawable drawable = imageView.getDrawable();
                                    if (drawable == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type com.bytedance.applog.exposure.DebugDrawable");
                                    }
                                    ((l0) drawable).a(i);
                                }
                            }
                            if (view.getBackground() instanceof l0) {
                                Drawable background = view.getBackground();
                                if (background == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.bytedance.applog.exposure.DebugDrawable");
                                }
                                ((l0) background).a(i);
                            }
                            view.invalidate();
                        }
                        IAppLogLogger iAppLogLogger = ViewExposureManager.this.getG().D;
                        StringBuilder sbA = a.a("[ViewExposure] visible change to ");
                        sbA.append(o0Var.b);
                        sbA.append(", config=");
                        sbA.append(viewExposureDataA.getConfig());
                        sbA.append(" view=");
                        sbA.append(view);
                        iAppLogLogger.debug(7, sbA.toString(), new Object[0]);
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function0<Unit> {
        public final /* synthetic */ View b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(View view) {
            super(0);
            this.b = view;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            o0 o0Var;
            Activity activityA = n0.a(this.b);
            if (activityA != null) {
                Intrinsics.checkExpressionValueIsNotNull(activityA, "ActivityUtil.findActivit…view) ?: return@runSafely");
                WeakHashMap weakHashMap = (WeakHashMap) ViewExposureManager.this.f187a.get(activityA);
                if (weakHashMap != null && (o0Var = (o0) weakHashMap.remove(this.b)) != null) {
                    Intrinsics.checkExpressionValueIsNotNull(o0Var, "activitiesMap[activity]?…view) ?: return@runSafely");
                    ViewExposureConfig config = o0Var.a().getConfig();
                    if (Intrinsics.areEqual((Object) (config != null ? config.getVisualDiagnosis() : null), (Object) true)) {
                        View view = this.b;
                        if (view instanceof ImageView) {
                            ImageView imageView = (ImageView) view;
                            if (imageView.getDrawable() instanceof l0) {
                                Drawable drawable = imageView.getDrawable();
                                if (drawable == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.bytedance.applog.exposure.DebugDrawable");
                                }
                                imageView.setImageDrawable(((l0) drawable).a());
                            }
                        }
                        if (view.getBackground() instanceof l0) {
                            Drawable background = view.getBackground();
                            if (background == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.bytedance.applog.exposure.DebugDrawable");
                            }
                            view.setBackground(((l0) background).a());
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class e extends Lambda implements Function0<Unit> {
        public final /* synthetic */ View b;
        public final /* synthetic */ ViewExposureData c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(View view, ViewExposureData viewExposureData) {
            super(0);
            this.b = view;
            this.c = viewExposureData;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            Float areaRatio;
            Boolean visualDiagnosis;
            InitConfig initConfig = ViewExposureManager.this.getG().getInitConfig();
            if (initConfig == null || !initConfig.isExposureEnabled()) {
                ViewExposureManager.this.getG().D.warn(7, "[ViewExposure] observe failed: InitConfig.exposureEnabled is not true.", new Object[0]);
            } else {
                Activity activityA = n0.a(this.b);
                if (activityA == null) {
                    ViewExposureManager.this.getG().D.warn(7, "[ViewExposure] observe failed: The view context is not Activity.", new Object[0]);
                } else if (t4.b(this.b)) {
                    ViewExposureManager.this.getG().D.warn(7, "[ViewExposure] observe failed: The view is ignored.", new Object[0]);
                } else {
                    WeakHashMap weakHashMap = (WeakHashMap) ViewExposureManager.this.f187a.get(activityA);
                    if (weakHashMap == null) {
                        weakHashMap = new WeakHashMap();
                        ViewExposureManager.this.f187a.put(activityA, weakHashMap);
                    }
                    ViewExposureConfig copyWith = ViewExposureManager.this.d;
                    ViewExposureData viewExposureData = this.c;
                    ViewExposureConfig config = viewExposureData != null ? viewExposureData.getConfig() : null;
                    Intrinsics.checkParameterIsNotNull(copyWith, "$this$copyWith");
                    if (config == null || (areaRatio = config.getAreaRatio()) == null) {
                        areaRatio = copyWith.getAreaRatio();
                    }
                    if (config == null || (visualDiagnosis = config.getVisualDiagnosis()) == null) {
                        visualDiagnosis = copyWith.getVisualDiagnosis();
                    }
                    ViewExposureConfig viewExposureConfig = new ViewExposureConfig(areaRatio, visualDiagnosis);
                    ViewExposureData viewExposureData2 = this.c;
                    String eventName = viewExposureData2 != null ? viewExposureData2.getEventName() : null;
                    ViewExposureData viewExposureData3 = this.c;
                    weakHashMap.put(this.b, new o0(new ViewExposureData(eventName, viewExposureData3 != null ? viewExposureData3.getProperties() : null, viewExposureConfig), false, 2));
                    if (Intrinsics.areEqual((Object) viewExposureConfig.getVisualDiagnosis(), (Object) true)) {
                        View view = this.b;
                        if (view instanceof ImageView) {
                            ImageView imageView = (ImageView) view;
                            imageView.setImageDrawable(new l0(imageView.getDrawable()));
                        }
                        view.setBackground(new l0(view.getBackground()));
                    }
                    ViewExposureManager.this.a(activityA);
                    IAppLogLogger iAppLogLogger = ViewExposureManager.this.getG().D;
                    StringBuilder sbA = a.a("[ViewExposure] observe successful, data=");
                    sbA.append(this.c);
                    sbA.append(", view=");
                    sbA.append(this.b);
                    iAppLogLogger.debug(7, sbA.toString(), new Object[0]);
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class f extends Lambda implements Function0<Unit> {
        public final /* synthetic */ ViewExposureData b;
        public final /* synthetic */ View c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(ViewExposureData viewExposureData, View view) {
            super(0);
            this.b = viewExposureData;
            this.c = view;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            String eventName;
            JSONObject properties;
            ViewExposureData viewExposureData = this.b;
            if (viewExposureData == null || (eventName = viewExposureData.getEventName()) == null) {
                eventName = "$bav2b_exposure";
            }
            l3 l3VarA = n0.a(this.c, true);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("page_key", l3VarA.v);
                jSONObject.put("page_title", l3VarA.w);
                jSONObject.put("element_path", l3VarA.x);
                jSONObject.put("element_width", l3VarA.C);
                jSONObject.put("element_height", l3VarA.D);
                jSONObject.put("element_id", l3VarA.y);
                jSONObject.put("element_type", l3VarA.z);
                ArrayList<String> arrayList = l3VarA.B;
                if (arrayList != null && !arrayList.isEmpty()) {
                    jSONObject.put("positions", new JSONArray((Collection) l3VarA.B));
                }
                ArrayList<String> arrayList2 = l3VarA.A;
                if (arrayList2 != null && !arrayList2.isEmpty()) {
                    jSONObject.put("texts", new JSONArray((Collection) l3VarA.A));
                }
                ViewExposureData viewExposureData2 = this.b;
                if (viewExposureData2 != null && (properties = viewExposureData2.getProperties()) != null) {
                    n0.b(properties, jSONObject);
                }
            } catch (Exception e) {
                ViewExposureManager.this.getG().D.error(7, "JSON handle failed", e, new Object[0]);
            }
            ViewExposureManager.this.getG().onEventV3(eventName, jSONObject, 0);
            return Unit.INSTANCE;
        }
    }

    public ViewExposureManager(com.bytedance.bdtracker.d appLog) {
        ViewExposureConfig exposureConfig;
        Intrinsics.checkParameterIsNotNull(appLog, "appLog");
        this.g = appLog;
        this.f187a = new WeakHashMap<>();
        Application application = appLog.n;
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
        }
        this.c = new r0(application);
        InitConfig initConfig = appLog.getInitConfig();
        this.d = (initConfig == null || (exposureConfig = initConfig.getExposureConfig()) == null) ? h : exposureConfig;
        this.e = new Handler(Looper.getMainLooper());
        this.f = new b();
        InitConfig initConfig2 = appLog.getInitConfig();
        if (initConfig2 == null || !initConfig2.isExposureEnabled() || this.b) {
            return;
        }
        this.c.b(new p0(this));
        this.c.a(new q0(this));
        this.b = true;
    }

    public final void a(Activity activity) {
        a(new c(activity));
    }

    public final void a(View view, ViewExposureData viewExposureData) {
        a(new f(viewExposureData, view));
    }

    public final void a(Function0<Unit> function0) {
        try {
            function0.invoke();
        } catch (Throwable th) {
            this.g.D.error(7, "Run task failed", th, new Object[0]);
        }
    }

    public final void disposeViewExposure(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        a(new d(view));
    }

    /* JADX INFO: renamed from: getAppLog, reason: from getter */
    public final com.bytedance.bdtracker.d getG() {
        return this.g;
    }

    public final void observeViewExposure(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        observeViewExposure(view, null);
    }

    public final void observeViewExposure(View view, ViewExposureData data) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        a(new e(view, data));
    }
}
