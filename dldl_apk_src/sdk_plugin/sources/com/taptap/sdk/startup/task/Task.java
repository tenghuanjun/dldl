package com.taptap.sdk.startup.task;

import android.content.Context;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Task.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u001a\b\u0002\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u0011\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0003J\u001b\u0010(\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000eHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0013HÆ\u0003Jq\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u001a\b\u0002\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÆ\u0001J\u0013\u0010+\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\nHÖ\u0001J\u001f\u0010.\u001a\u00020\u00002\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000000¢\u0006\u0002\b1J\t\u00102\u001a\u00020\u0003HÖ\u0001R#\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00063"}, d2 = {"Lcom/taptap/sdk/startup/task/Task;", "", "name", "", "clazz", "Ljava/lang/Class;", "Lcom/taptap/sdk/startup/task/InitializeTask;", "background", "", "priority", "", "dependsOn", "", "action", "Lkotlin/Function2;", "Landroid/content/Context;", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "", "job", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Ljava/lang/Class;ZILjava/util/Set;Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/Job;)V", "getAction", "()Lkotlin/jvm/functions/Function2;", "getBackground", "()Z", "getClazz", "()Ljava/lang/Class;", "getDependsOn", "()Ljava/util/Set;", "getJob", "()Lkotlinx/coroutines/Job;", BillingClientConstants.METHOD_GET_PRODUCT_NAME, "()Ljava/lang/String;", "getPriority", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "reduce", "reducer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class Task {
    private final Function2<Context, TapTapSdkOptions, Unit> action;
    private final boolean background;
    private final Class<? extends InitializeTask> clazz;
    private final Set<String> dependsOn;
    private final Job job;
    private final String name;
    private final int priority;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Task copy$default(Task task, String str, Class cls, boolean z, int i, Set set, Function2 function2, Job job, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = task.name;
        }
        if ((i2 & 2) != 0) {
            cls = task.clazz;
        }
        Class cls2 = cls;
        if ((i2 & 4) != 0) {
            z = task.background;
        }
        boolean z2 = z;
        if ((i2 & 8) != 0) {
            i = task.priority;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            set = task.dependsOn;
        }
        Set set2 = set;
        if ((i2 & 32) != 0) {
            function2 = task.action;
        }
        Function2 function22 = function2;
        if ((i2 & 64) != 0) {
            job = task.job;
        }
        return task.copy(str, cls2, z2, i3, set2, function22, job);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final Class<? extends InitializeTask> component2() {
        return this.clazz;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getBackground() {
        return this.background;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    public final Set<String> component5() {
        return this.dependsOn;
    }

    public final Function2<Context, TapTapSdkOptions, Unit> component6() {
        return this.action;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Job getJob() {
        return this.job;
    }

    public final Task copy(String name, Class<? extends InitializeTask> clazz, boolean background, int priority, Set<String> dependsOn, Function2<? super Context, ? super TapTapSdkOptions, Unit> action, Job job) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(dependsOn, "dependsOn");
        Intrinsics.checkNotNullParameter(action, "action");
        return new Task(name, clazz, background, priority, dependsOn, action, job);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task task = (Task) other;
        return Intrinsics.areEqual(this.name, task.name) && Intrinsics.areEqual(this.clazz, task.clazz) && this.background == task.background && this.priority == task.priority && Intrinsics.areEqual(this.dependsOn, task.dependsOn) && Intrinsics.areEqual(this.action, task.action) && Intrinsics.areEqual(this.job, task.job);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.clazz.hashCode()) * 31;
        boolean z = this.background;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int iHashCode2 = (((((((iHashCode + r1) * 31) + this.priority) * 31) + this.dependsOn.hashCode()) * 31) + this.action.hashCode()) * 31;
        Job job = this.job;
        return iHashCode2 + (job == null ? 0 : job.hashCode());
    }

    public String toString() {
        return "Task(name=" + this.name + ", clazz=" + this.clazz + ", background=" + this.background + ", priority=" + this.priority + ", dependsOn=" + this.dependsOn + ", action=" + this.action + ", job=" + this.job + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Task(String name, Class<? extends InitializeTask> clazz, boolean z, int i, Set<String> dependsOn, Function2<? super Context, ? super TapTapSdkOptions, Unit> action, Job job) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(dependsOn, "dependsOn");
        Intrinsics.checkNotNullParameter(action, "action");
        this.name = name;
        this.clazz = clazz;
        this.background = z;
        this.priority = i;
        this.dependsOn = dependsOn;
        this.action = action;
        this.job = job;
    }

    public final String getName() {
        return this.name;
    }

    public final Class<? extends InitializeTask> getClazz() {
        return this.clazz;
    }

    public final boolean getBackground() {
        return this.background;
    }

    public final int getPriority() {
        return this.priority;
    }

    public /* synthetic */ Task(String str, Class cls, boolean z, int i, Set set, Function2 function2, Job job, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, cls, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? SetsKt.emptySet() : set, (i2 & 32) != 0 ? new Function2<Context, TapTapSdkOptions, Unit>() { // from class: com.taptap.sdk.startup.task.Task.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Context context, TapTapSdkOptions options) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(options, "options");
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Context context, TapTapSdkOptions tapTapSdkOptions) {
                invoke2(context, tapTapSdkOptions);
                return Unit.INSTANCE;
            }
        } : function2, (i2 & 64) != 0 ? null : job);
    }

    public final Set<String> getDependsOn() {
        return this.dependsOn;
    }

    public final Function2<Context, TapTapSdkOptions, Unit> getAction() {
        return this.action;
    }

    public final Job getJob() {
        return this.job;
    }

    public final Task reduce(Function1<? super Task, Task> reducer) {
        Intrinsics.checkNotNullParameter(reducer, "reducer");
        return reducer.invoke(this);
    }
}
