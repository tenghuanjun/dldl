package com.taptap.sdk.startup.task;

import com.taptap.sdk.startup.grpah.Node;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TaskNode.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0006\u0007R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/startup/task/TaskNode;", "Lcom/taptap/sdk/startup/grpah/Node;", "task", "Lcom/taptap/sdk/startup/task/Task;", "getTask", "()Lcom/taptap/sdk/startup/task/Task;", "Real", "Virtual", "Lcom/taptap/sdk/startup/task/TaskNode$Real;", "Lcom/taptap/sdk/startup/task/TaskNode$Virtual;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface TaskNode extends Node {
    Task getTask();

    /* JADX INFO: compiled from: TaskNode.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/startup/task/TaskNode$Virtual;", "Lcom/taptap/sdk/startup/task/TaskNode;", "task", "Lcom/taptap/sdk/startup/task/Task;", "(Lcom/taptap/sdk/startup/task/Task;)V", "getTask", "()Lcom/taptap/sdk/startup/task/Task;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Virtual implements TaskNode {
        private final Task task;

        /* JADX WARN: Multi-variable type inference failed */
        public Virtual() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Virtual copy$default(Virtual virtual, Task task, int i, Object obj) {
            if ((i & 1) != 0) {
                task = virtual.getTask();
            }
            return virtual.copy(task);
        }

        public final Task component1() {
            return getTask();
        }

        public final Virtual copy(Task task) {
            return new Virtual(task);
        }

        @Override // com.taptap.sdk.startup.grpah.Node
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Virtual) && Intrinsics.areEqual(getTask(), ((Virtual) other).getTask());
        }

        @Override // com.taptap.sdk.startup.grpah.Node
        public int hashCode() {
            if (getTask() == null) {
                return 0;
            }
            return getTask().hashCode();
        }

        public String toString() {
            return "Virtual(task=" + getTask() + ')';
        }

        public Virtual(Task task) {
            this.task = task;
        }

        public /* synthetic */ Virtual(Task task, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : task);
        }

        @Override // com.taptap.sdk.startup.task.TaskNode
        public Task getTask() {
            return this.task;
        }
    }

    /* JADX INFO: compiled from: TaskNode.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u001f\u0010\u000f\u001a\u00020\u00002\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0011¢\u0006\u0002\b\u0012J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/startup/task/TaskNode$Real;", "Lcom/taptap/sdk/startup/task/TaskNode;", "task", "Lcom/taptap/sdk/startup/task/Task;", "(Lcom/taptap/sdk/startup/task/Task;)V", "getTask", "()Lcom/taptap/sdk/startup/task/Task;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "reduce", "reducer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Real implements TaskNode {
        private final Task task;

        public static /* synthetic */ Real copy$default(Real real, Task task, int i, Object obj) {
            if ((i & 1) != 0) {
                task = real.getTask();
            }
            return real.copy(task);
        }

        public final Task component1() {
            return getTask();
        }

        public final Real copy(Task task) {
            return new Real(task);
        }

        @Override // com.taptap.sdk.startup.grpah.Node
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Real) && Intrinsics.areEqual(getTask(), ((Real) other).getTask());
        }

        @Override // com.taptap.sdk.startup.grpah.Node
        public int hashCode() {
            if (getTask() == null) {
                return 0;
            }
            return getTask().hashCode();
        }

        public String toString() {
            return "Real(task=" + getTask() + ')';
        }

        public Real(Task task) {
            this.task = task;
        }

        @Override // com.taptap.sdk.startup.task.TaskNode
        public Task getTask() {
            return this.task;
        }

        public final Real reduce(Function1<? super Real, Real> reducer) {
            Intrinsics.checkNotNullParameter(reducer, "reducer");
            return reducer.invoke(this);
        }
    }
}
