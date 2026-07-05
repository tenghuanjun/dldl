package com.sqwan.common.util.task;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TaskSubThread {
    private static final String TAG = "sq.TaskSubThread";
    private static AtomicInteger sTaskId = new AtomicInteger(0);
    private int taskId;
    private Runnable runnable = null;
    private boolean isExecuting = false;
    private boolean stopWhenExecuting = false;

    public enum Result {
        Next,
        Stop
    }

    public interface TaskFunc {
        Result exec();
    }

    private TaskSubThread() {
        this.taskId = 0;
        this.taskId = sTaskId.getAndIncrement();
    }

    public static TaskSubThread create() {
        return new TaskSubThread();
    }

    public void oneShot(long j, final Runnable runnable) {
        oneShot(j, new TaskFunc() { // from class: com.sqwan.common.util.task.TaskSubThread.1
            @Override // com.sqwan.common.util.task.TaskSubThread.TaskFunc
            public Result exec() {
                runnable.run();
                return null;
            }
        });
    }

    public void oneShot(final Runnable runnable) {
        oneShot(new TaskFunc() { // from class: com.sqwan.common.util.task.TaskSubThread.2
            @Override // com.sqwan.common.util.task.TaskSubThread.TaskFunc
            public Result exec() {
                runnable.run();
                return null;
            }
        });
    }

    public void oneShot(TaskFunc taskFunc) {
        repeat(0L, 0L, taskFunc);
    }

    public void oneShot(long j, TaskFunc taskFunc) {
        repeat(j, 0L, taskFunc);
    }

    public void repeat(long j, TaskFunc taskFunc) {
        repeat(0L, j, taskFunc);
    }

    public void repeat(long j, final long j2, final TaskFunc taskFunc) {
        stop();
        if (this.isExecuting) {
            this.stopWhenExecuting = true;
        }
        this.runnable = new Runnable() { // from class: com.sqwan.common.util.task.TaskSubThread.3
            @Override // java.lang.Runnable
            public void run() {
                Result result = Result.Next;
                TaskSubThread.this.isExecuting = true;
                Result resultExec = taskFunc.exec();
                TaskSubThread.this.isExecuting = false;
                if (TaskSubThread.this.stopWhenExecuting) {
                    TaskSubThread.this.stopWhenExecuting = false;
                    return;
                }
                if (resultExec == null) {
                    resultExec = Result.Next;
                }
                if (resultExec == Result.Stop || j2 <= 0) {
                    HandlerSubThreadManager.getInstance().remove(TaskSubThread.this.runnable);
                    TaskSubThread.this.runnable = null;
                } else {
                    HandlerSubThreadManager.getInstance().postDelay(j2, TaskSubThread.this.runnable);
                }
            }
        };
        HandlerSubThreadManager.getInstance().postDelay(j, this.runnable);
    }

    public boolean isRunning() {
        return this.runnable != null;
    }

    public void stop() {
        if (this.runnable != null) {
            HandlerSubThreadManager.getInstance().stop(this.runnable);
            this.runnable = null;
        }
    }

    public static void post(final Runnable runnable) {
        if (runnable != null) {
            create().oneShot(new TaskFunc() { // from class: com.sqwan.common.util.task.TaskSubThread.4
                @Override // com.sqwan.common.util.task.TaskSubThread.TaskFunc
                public Result exec() {
                    runnable.run();
                    return null;
                }
            });
        }
    }
}
