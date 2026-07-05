package com.taptap.sdk.startup;

import android.content.Context;
import android.util.Log;
import com.huya.mtp.http.monitor.Stat;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.startup.grpah.Graph;
import com.taptap.sdk.startup.task.InitializeTask;
import com.taptap.sdk.startup.task.Task;
import com.taptap.sdk.startup.task.TaskNode;
import com.taptap.sdk.startup.utils.ListUtils;
import com.taptap.sdk.startup.utils.TaskFactoryCollector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.TimeSource;
import kotlin.time.TimedValue;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: StartUp.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001!B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B#\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0006\u0010\u0015\u001a\u00020\u000eJ\u001c\u0010\u0016\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u0011J&\u0010\u001a\u001a\u0004\u0018\u00010\u000b*\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\u001d\u001a\u00020\u0011*\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\u001e\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\f\u0010\u001f\u001a\u00020\u000e*\u00020\u001bH\u0002J&\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0017*\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/taptap/sdk/startup/StartUp;", "", "builder", "Lcom/taptap/sdk/startup/StartUp$Builder;", "(Lcom/taptap/sdk/startup/StartUp$Builder;)V", "context", "Landroid/content/Context;", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "tasks", "", "Lcom/taptap/sdk/startup/task/Task;", "(Landroid/content/Context;Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;Ljava/util/List;)V", "__inject", "", "buildGraph", "Lcom/taptap/sdk/startup/grpah/Graph;", "Lcom/taptap/sdk/startup/task/TaskNode;", "findTask", "clazz", "", "initialize", "validate", "Lkotlin/Function1;", "", "taskNode", "createTask", "Lkotlinx/coroutines/CoroutineScope;", "task", "createTaskNode", Stat.EXECUTE_KEY, "start", "updateTaskWhenSort", "Builder", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class StartUp {
    private final Context context;
    private TapTapSdkOptions options;
    private List<Task> tasks;

    public /* synthetic */ StartUp(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public StartUp(Context context, TapTapSdkOptions options, List<Task> tasks) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(tasks, "tasks");
        this.context = context;
        this.options = options;
        this.tasks = tasks;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private StartUp(Builder builder) {
        Context context = builder.getContext();
        TapTapSdkOptions options = builder.getOptions();
        if (options != null) {
            this(context, options, builder.getTasks$tap_initializer_release());
            __inject();
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final void __inject() {
        List<Task> list = this.tasks;
        List<Task> listCollect = new TaskFactoryCollector().collect();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listCollect, 10));
        Iterator<T> it = listCollect.iterator();
        while (it.hasNext()) {
            arrayList.add(((Task) it.next()).reduce(new Function1<Task, Task>() { // from class: com.taptap.sdk.startup.StartUp$__inject$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Task invoke(final Task reduce) {
                    Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                    return Task.copy$default(reduce, null, null, false, 0, null, new Function2<Context, TapTapSdkOptions, Unit>() { // from class: com.taptap.sdk.startup.StartUp$__inject$1$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Context context, TapTapSdkOptions tapTapSdkOptions) {
                            invoke2(context, tapTapSdkOptions);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Context context, TapTapSdkOptions options) {
                            Intrinsics.checkNotNullParameter(context, "context");
                            Intrinsics.checkNotNullParameter(options, "options");
                            reduce.getClazz().newInstance().execute(context, options);
                        }
                    }, null, 95, null);
                }
            }));
        }
        this.tasks = CollectionsKt.plus((Collection) list, (Iterable) arrayList);
    }

    /* JADX INFO: renamed from: com.taptap.sdk.startup.StartUp$initialize$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StartUp.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.startup.StartUp$initialize$1", f = "StartUp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C01041(Continuation<? super C01041> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01041 c01041 = StartUp.this.new C01041(continuation);
            c01041.L$0 = obj;
            return c01041;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                StartUp.this.start((CoroutineScope) this.L$0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void initialize() {
        BuildersKt__BuildersKt.runBlocking$default(null, new C01041(null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void start(CoroutineScope coroutineScope) {
        buildGraph(this.tasks).sort(updateTaskWhenSort(coroutineScope, this.context, this.options));
    }

    public final Function1<TaskNode, TaskNode> updateTaskWhenSort(final CoroutineScope coroutineScope, final Context context, final TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        return new Function1<TaskNode, TaskNode>() { // from class: com.taptap.sdk.startup.StartUp.updateTaskWhenSort.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final TaskNode invoke(TaskNode taskNode) {
                Intrinsics.checkNotNullParameter(taskNode, "taskNode");
                TaskNode taskNodeCreateTaskNode = StartUp.this.createTaskNode(coroutineScope, taskNode, context, options);
                StartUp.this.tasks = CollectionsKt.filterNotNull(ListUtils.INSTANCE.updateOrInsert(StartUp.this.tasks, taskNodeCreateTaskNode.getTask(), StartUp.this.validate(taskNodeCreateTaskNode)));
                return taskNodeCreateTaskNode;
            }
        };
    }

    public final TaskNode createTaskNode(final CoroutineScope coroutineScope, TaskNode taskNode, final Context context, final TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(taskNode, "taskNode");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        if (taskNode instanceof TaskNode.Real) {
            return ((TaskNode.Real) taskNode).reduce(new Function1<TaskNode.Real, TaskNode.Real>() { // from class: com.taptap.sdk.startup.StartUp.createTaskNode.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final TaskNode.Real invoke(TaskNode.Real taskNode2) {
                    Intrinsics.checkNotNullParameter(taskNode2, "$this$taskNode");
                    return taskNode2.copy(StartUp.this.createTask(coroutineScope, taskNode2.getTask(), context, options));
                }
            });
        }
        if (taskNode instanceof TaskNode.Virtual) {
            return taskNode;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Task createTask(final CoroutineScope coroutineScope, final Task task, final Context context, final TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        if (task != null) {
            return task.reduce(new Function1<Task, Task>() { // from class: com.taptap.sdk.startup.StartUp.createTask.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX INFO: renamed from: com.taptap.sdk.startup.StartUp$createTask$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: StartUp.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "com.taptap.sdk.startup.StartUp$createTask$1$1", f = "StartUp.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
                static final class C00771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Context $context;
                    final /* synthetic */ TapTapSdkOptions $options;
                    final /* synthetic */ Task $task;
                    Object L$0;
                    Object L$1;
                    int label;
                    final /* synthetic */ StartUp this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00771(Task task, StartUp startUp, Context context, TapTapSdkOptions tapTapSdkOptions, Continuation<? super C00771> continuation) {
                        super(2, continuation);
                        this.$task = task;
                        this.this$0 = startUp;
                        this.$context = context;
                        this.$options = tapTapSdkOptions;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00771(this.$task, this.this$0, this.$context, this.$options, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) throws Exception {
                        StartUp startUp;
                        Iterator it;
                        Job job;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            Set<String> dependsOn = this.$task.getDependsOn();
                            startUp = this.this$0;
                            it = dependsOn.iterator();
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            it = (Iterator) this.L$1;
                            startUp = (StartUp) this.L$0;
                            ResultKt.throwOnFailure(obj);
                        }
                        while (it.hasNext()) {
                            Task taskFindTask = startUp.findTask((String) it.next());
                            if (taskFindTask != null && (job = taskFindTask.getJob()) != null) {
                                this.L$0 = startUp;
                                this.L$1 = it;
                                this.label = 1;
                                if (job.join(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                        this.this$0.execute(this.$task, this.$context, this.$options);
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.jvm.functions.Function1
                public final Task invoke(Task reduce) {
                    Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                    return Task.copy$default(reduce, null, null, false, 0, null, null, BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00771(task, this, context, options, null), 3, null), 63, null);
                }
            });
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Task findTask(String clazz) {
        Object next;
        Iterator<T> it = this.tasks.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Task) next).getClazz().getName(), clazz)) {
                break;
            }
        }
        Task task = (Task) next;
        if (task == null) {
            TapLogger.logi("TapStartUp", "can not found task: " + clazz);
        }
        return task;
    }

    public final Function1<Task, Boolean> validate(final TaskNode taskNode) {
        Intrinsics.checkNotNullParameter(taskNode, "taskNode");
        return new Function1<Task, Boolean>() { // from class: com.taptap.sdk.startup.StartUp.validate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Task task) {
                Class<? extends InitializeTask> clazz = task != null ? task.getClazz() : null;
                Task task2 = taskNode.getTask();
                return Boolean.valueOf(Intrinsics.areEqual(clazz, task2 != null ? task2.getClazz() : null));
            }
        };
    }

    private final Graph<TaskNode> buildGraph(List<Task> tasks) {
        Graph.Builder builder = new Graph.Builder();
        for (Task task : tasks) {
            if (!(!task.getDependsOn().isEmpty())) {
                builder.putEdge(new TaskNode.Real(task), new TaskNode.Virtual(null, 1, null));
            } else {
                Iterator<T> it = task.getDependsOn().iterator();
                while (it.hasNext()) {
                    builder.putEdge(new TaskNode.Real(task), new TaskNode.Real(findTask((String) it.next())));
                }
            }
        }
        return builder.build();
    }

    /* JADX INFO: compiled from: StartUp.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/taptap/sdk/startup/StartUp$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext$tap_initializer_release", "()Landroid/content/Context;", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "getOptions$tap_initializer_release", "()Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "setOptions$tap_initializer_release", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "tasks", "", "Lcom/taptap/sdk/startup/task/Task;", "getTasks$tap_initializer_release", "()Ljava/util/List;", "setTasks$tap_initializer_release", "(Ljava/util/List;)V", "build", "Lcom/taptap/sdk/startup/StartUp;", "setOptions", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private final Context context;
        private TapTapSdkOptions options;
        private List<Task> tasks;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.tasks = CollectionsKt.emptyList();
        }

        /* JADX INFO: renamed from: getContext$tap_initializer_release, reason: from getter */
        public final Context getContext() {
            return this.context;
        }

        public final List<Task> getTasks$tap_initializer_release() {
            return this.tasks;
        }

        public final void setTasks$tap_initializer_release(List<Task> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.tasks = list;
        }

        /* JADX INFO: renamed from: getOptions$tap_initializer_release, reason: from getter */
        public final TapTapSdkOptions getOptions() {
            return this.options;
        }

        public final void setOptions$tap_initializer_release(TapTapSdkOptions tapTapSdkOptions) {
            this.options = tapTapSdkOptions;
        }

        public final Builder setOptions(TapTapSdkOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            Builder builder = this;
            builder.options = options;
            return builder;
        }

        public final StartUp build() {
            return new StartUp(this, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void execute(Task task, Context context, TapTapSdkOptions tapTapSdkOptions) throws Exception {
        long jM1511markNowz9LOYto = TimeSource.Monotonic.INSTANCE.m1511markNowz9LOYto();
        task.getAction().invoke(context, tapTapSdkOptions);
        TimedValue timedValue = new TimedValue(Unit.INSTANCE, TimeSource.Monotonic.ValueTimeMark.m1514elapsedNowUwyO8pc(jM1511markNowz9LOYto), null);
        timedValue.component1();
        Unit unit = Unit.INSTANCE;
        Log.d("TapStartUp", "task: " + task.getName() + ", thread: " + Thread.currentThread().getName() + ", cost: " + ((Object) Duration.m1423toStringimpl(timedValue.getDuration())));
    }
}
