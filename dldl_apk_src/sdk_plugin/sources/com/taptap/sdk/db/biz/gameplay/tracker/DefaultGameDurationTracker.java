package com.taptap.sdk.db.biz.gameplay.tracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleTracker;
import com.taptap.sdk.db.EventContext;
import com.taptap.sdk.db.biz.gameplay.reporter.GameDurationReporter;
import com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000U\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007*\u0001\u0010\u0018\u0000 *2\u00020\u0001:\u0001*B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0002J\b\u0010\u001f\u001a\u00020\u001aH\u0016J#\u0010 \u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001a\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\"2\b\u0010(\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010)\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/tracker/DefaultGameDurationTracker;", "Lcom/taptap/sdk/db/biz/gameplay/tracker/GameDurationTracker;", "storage", "Lcom/taptap/sdk/db/biz/gameplay/storage/GameDurationStorage;", "reporter", "Lcom/taptap/sdk/db/biz/gameplay/reporter/GameDurationReporter;", "activityLifecycleTracker", "Lcom/taptap/sdk/base/utils/lifecycle/TapActivityLifecycleTracker;", "eventContext", "Lcom/taptap/sdk/db/EventContext;", "(Lcom/taptap/sdk/db/biz/gameplay/storage/GameDurationStorage;Lcom/taptap/sdk/db/biz/gameplay/reporter/GameDurationReporter;Lcom/taptap/sdk/base/utils/lifecycle/TapActivityLifecycleTracker;Lcom/taptap/sdk/db/EventContext;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "deviceStartTimeAtInMillis", "", "lifecycleCallbacks", "com/taptap/sdk/db/biz/gameplay/tracker/DefaultGameDurationTracker$lifecycleCallbacks$1", "Lcom/taptap/sdk/db/biz/gameplay/tracker/DefaultGameDurationTracker$lifecycleCallbacks$1;", "timerJob", "Lkotlinx/coroutines/Job;", "userStartTimeAtInMillis", "getUserStartTimeAtInMillis", "()J", "setUserStartTimeAtInMillis", "(J)V", "enableAutoSubmitGameDurationEvent", "", "initialize", "startTimer", "startTracking", "stopTimer", "stopTracking", "submitGameDurationEvent", "userId", "", "onlyUserDuration", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitOldUserGameDurationEvent", "oldUserId", "newUserId", "updateLocalGameDuration", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultGameDurationTracker implements GameDurationTracker {
    private static final String TAG = "GameDurationTracker";
    private static final long TIMER_INTERVAL = 3000;
    private final TapActivityLifecycleTracker activityLifecycleTracker;
    private final CoroutineScope coroutineScope;
    private long deviceStartTimeAtInMillis;
    private final EventContext eventContext;
    private final DefaultGameDurationTracker$lifecycleCallbacks$1 lifecycleCallbacks;
    private final GameDurationReporter reporter;
    private final GameDurationStorage storage;
    private Job timerJob;
    private long userStartTimeAtInMillis;

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$submitGameDurationEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker", f = "DefaultGameDurationTracker.kt", i = {0, 0, 0, 1}, l = {Opcodes.ARETURN, Opcodes.INVOKEVIRTUAL}, m = "submitGameDurationEvent", n = {"this", "onlyUserDuration", "deviceDuration", "this"}, s = {"L$0", "Z$0", "J$0", "L$0"})
    static final class C00921 extends ContinuationImpl {
        long J$0;
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00921(Continuation<? super C00921> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultGameDurationTracker.this.submitGameDurationEvent(null, false, this);
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$lifecycleCallbacks$1] */
    public DefaultGameDurationTracker(GameDurationStorage storage, GameDurationReporter reporter, TapActivityLifecycleTracker activityLifecycleTracker, EventContext eventContext) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(reporter, "reporter");
        Intrinsics.checkNotNullParameter(activityLifecycleTracker, "activityLifecycleTracker");
        Intrinsics.checkNotNullParameter(eventContext, "eventContext");
        this.storage = storage;
        this.reporter = reporter;
        this.activityLifecycleTracker = activityLifecycleTracker;
        this.eventContext = eventContext;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        this.deviceStartTimeAtInMillis = -1L;
        this.userStartTimeAtInMillis = -1L;
        this.lifecycleCallbacks = new TapActivityLifecycleCallbacks() { // from class: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$lifecycleCallbacks$1
            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivityCreated(this, activity, bundle);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivityDestroyed(this, activity);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivityPaused(this, activity);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivityResumed(this, activity);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivitySaveInstanceState(this, activity, bundle);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivityStarted(this, activity);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                TapActivityLifecycleCallbacks.DefaultImpls.onActivityStopped(this, activity);
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks
            public void onForeground(Context context) {
                Intrinsics.checkNotNullParameter(context, "context");
                this.this$0.startTracking();
            }

            @Override // com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleCallbacks
            public void onBackground(Context context) {
                Intrinsics.checkNotNullParameter(context, "context");
                this.this$0.stopTracking();
            }
        };
    }

    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    public long getUserStartTimeAtInMillis() {
        return this.userStartTimeAtInMillis;
    }

    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    public void setUserStartTimeAtInMillis(long j) {
        this.userStartTimeAtInMillis = j;
    }

    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    public void initialize() {
        this.activityLifecycleTracker.registerActivityLifecycleCallbacks(this.lifecycleCallbacks);
        if (this.activityLifecycleTracker.isAppForeground()) {
            startTracking();
        }
        enableAutoSubmitGameDurationEvent();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$startTracking$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$startTracking$1", f = "DefaultGameDurationTracker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00901(Continuation<? super C00901> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultGameDurationTracker.this.new C00901(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (DefaultGameDurationTracker.this.deviceStartTimeAtInMillis < 0) {
                DefaultGameDurationTracker.this.deviceStartTimeAtInMillis = jCurrentTimeMillis;
                Log.d(DefaultGameDurationTracker.TAG, "Device tracking started");
            }
            String currentUserId = DefaultGameDurationTracker.this.eventContext.getCurrentUserId();
            if (!(currentUserId == null || currentUserId.length() == 0) && DefaultGameDurationTracker.this.getUserStartTimeAtInMillis() < 0) {
                DefaultGameDurationTracker.this.setUserStartTimeAtInMillis(jCurrentTimeMillis);
                Log.d(DefaultGameDurationTracker.TAG, "User tracking started for user: " + DefaultGameDurationTracker.this.eventContext.getCurrentUserId());
            }
            DefaultGameDurationTracker.this.startTimer();
            return Unit.INSTANCE;
        }
    }

    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    public void startTracking() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C00901(null), 3, null);
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$stopTracking$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$stopTracking$1", f = "DefaultGameDurationTracker.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
    static final class C00911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00911(Continuation<? super C00911> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultGameDurationTracker.this.new C00911(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DefaultGameDurationTracker defaultGameDurationTracker = DefaultGameDurationTracker.this;
                defaultGameDurationTracker.updateLocalGameDuration(defaultGameDurationTracker.eventContext.getCurrentUserId());
                DefaultGameDurationTracker defaultGameDurationTracker2 = DefaultGameDurationTracker.this;
                this.label = 1;
                if (defaultGameDurationTracker2.submitGameDurationEvent(defaultGameDurationTracker2.eventContext.getCurrentUserId(), false, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            DefaultGameDurationTracker.this.deviceStartTimeAtInMillis = -1L;
            DefaultGameDurationTracker.this.setUserStartTimeAtInMillis(-1L);
            DefaultGameDurationTracker.this.stopTimer();
            Log.d(DefaultGameDurationTracker.TAG, "tracking stopped");
            return Unit.INSTANCE;
        }
    }

    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    public void stopTracking() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C00911(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimer() {
        stopTimer();
        this.timerJob = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C00891(null), 3, null);
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$startTimer$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$startTimer$1", f = "DefaultGameDurationTracker.kt", i = {0}, l = {133}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C00891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00891(Continuation<? super C00891> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00891 c00891 = DefaultGameDurationTracker.this.new C00891(continuation);
            c00891.L$0 = obj;
            return c00891;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                DefaultGameDurationTracker defaultGameDurationTracker = DefaultGameDurationTracker.this;
                defaultGameDurationTracker.updateLocalGameDuration(defaultGameDurationTracker.eventContext.getCurrentUserId());
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(DefaultGameDurationTracker.TIMER_INTERVAL, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopTimer() {
        Job job = this.timerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.timerJob = null;
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$submitOldUserGameDurationEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$submitOldUserGameDurationEvent$1", f = "DefaultGameDurationTracker.kt", i = {}, l = {Opcodes.IFNE}, m = "invokeSuspend", n = {}, s = {})
    static final class C00931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $newUserId;
        final /* synthetic */ String $oldUserId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00931(String str, String str2, Continuation<? super C00931> continuation) {
            super(2, continuation);
            this.$oldUserId = str;
            this.$newUserId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultGameDurationTracker.this.new C00931(this.$oldUserId, this.$newUserId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            boolean z = true;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DefaultGameDurationTracker.this.updateLocalGameDuration(this.$oldUserId);
                this.label = 1;
                if (DefaultGameDurationTracker.this.submitGameDurationEvent(this.$oldUserId, true, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            String str = this.$newUserId;
            if (str != null && str.length() != 0) {
                z = false;
            }
            if (!z && DefaultGameDurationTracker.this.deviceStartTimeAtInMillis > 0) {
                DefaultGameDurationTracker.this.setUserStartTimeAtInMillis(System.currentTimeMillis());
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    public void submitOldUserGameDurationEvent(String oldUserId, String newUserId) {
        Intrinsics.checkNotNullParameter(oldUserId, "oldUserId");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C00931(oldUserId, newUserId, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object submitGameDurationEvent(java.lang.String r8, boolean r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker.C00921
            if (r0 == 0) goto L14
            r0 = r10
            com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$submitGameDurationEvent$1 r0 = (com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker.C00921) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$submitGameDurationEvent$1 r0 = new com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$submitGameDurationEvent$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L45
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r8 = r0.L$0
            com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker r8 = (com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L96
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            long r8 = r0.J$0
            boolean r2 = r0.Z$0
            java.lang.Object r4 = r0.L$0
            com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker r4 = (com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L77
        L45:
            kotlin.ResultKt.throwOnFailure(r10)
            r7.updateLocalGameDuration(r8)
            com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage r8 = r7.storage
            com.taptap.sdk.db.data.model.GameDuration r8 = r8.getGameDuration()
            long r5 = r8.getDeviceDuration()
            java.util.List r8 = r8.component2()
            r10 = r8
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r4
            if (r10 == 0) goto L80
            com.taptap.sdk.db.biz.gameplay.reporter.GameDurationReporter r10 = r7.reporter
            r0.L$0 = r7
            r0.Z$0 = r9
            r0.J$0 = r5
            r0.label = r4
            java.lang.Object r8 = r10.submitUserDurations(r8, r0)
            if (r8 != r1) goto L74
            return r1
        L74:
            r4 = r7
            r2 = r9
            r8 = r5
        L77:
            com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage r10 = r4.storage
            r10.resetUserDuration()
            r5 = r8
            r9 = r2
            r8 = r4
            goto L81
        L80:
            r8 = r7
        L81:
            if (r9 != 0) goto L9b
            r9 = 0
            int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r2 <= 0) goto L9b
            com.taptap.sdk.db.biz.gameplay.reporter.GameDurationReporter r9 = r8.reporter
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r9 = r9.submitDeviceDuration(r5, r0)
            if (r9 != r1) goto L96
            return r1
        L96:
            com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage r8 = r8.storage
            r8.resetDeviceDuration()
        L9b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker.submitGameDurationEvent(java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$enableAutoSubmitGameDurationEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultGameDurationTracker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker$enableAutoSubmitGameDurationEvent$1", f = "DefaultGameDurationTracker.kt", i = {}, l = {Opcodes.CHECKCAST}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultGameDurationTracker.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DefaultGameDurationTracker defaultGameDurationTracker = DefaultGameDurationTracker.this;
                this.label = 1;
                if (defaultGameDurationTracker.submitGameDurationEvent(defaultGameDurationTracker.eventContext.getCurrentUserId(), false, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            DefaultGameDurationTracker.this.startTimer();
            return Unit.INSTANCE;
        }
    }

    private final void enableAutoSubmitGameDurationEvent() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLocalGameDuration(String userId) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.deviceStartTimeAtInMillis;
        if (j > 0) {
            long j2 = jCurrentTimeMillis - j;
            if (j2 > 0) {
                this.deviceStartTimeAtInMillis = jCurrentTimeMillis;
                this.storage.incrementDeviceDuration(j2);
                Log.v(TAG, "累积设备游戏时长: +" + j2 + " ms");
            }
        }
        String str = userId;
        if (!(str == null || str.length() == 0) && getUserStartTimeAtInMillis() > 0) {
            long userStartTimeAtInMillis = jCurrentTimeMillis - getUserStartTimeAtInMillis();
            if (userStartTimeAtInMillis > 0) {
                setUserStartTimeAtInMillis(jCurrentTimeMillis);
                this.storage.incrementUserDuration(userId, userStartTimeAtInMillis);
                Log.v(TAG, "累积用户(" + userId + ")游戏时长: +" + userStartTimeAtInMillis + " ms");
            }
        }
        GameDurationStorage.DefaultImpls.sync$default(this.storage, false, 1, null);
    }
}
