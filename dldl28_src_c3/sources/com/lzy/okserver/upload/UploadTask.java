package com.lzy.okserver.upload;

import com.lzy.okgo.db.UploadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.ProgressRequestBody;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.HttpUtils;
import com.lzy.okgo.utils.OkLogger;
import com.lzy.okserver.OkUpload;
import com.lzy.okserver.task.PriorityRunnable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import okhttp3.Call;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class UploadTask<T> implements Runnable {
    private ThreadPoolExecutor executor;
    public Map<Object, UploadListener<T>> listeners;
    private PriorityRunnable priorityRunnable;
    public Progress progress;

    /* JADX WARN: Multi-variable type inference failed */
    public UploadTask(String str, Request<T, ? extends Request> request) {
        HttpUtils.checkNotNull(str, "tag == null");
        Progress progress = new Progress();
        this.progress = progress;
        progress.tag = str;
        this.progress.url = request.getBaseUrl();
        this.progress.status = 0;
        this.progress.totalSize = -1L;
        this.progress.request = request;
        this.executor = OkUpload.getInstance().getThreadPool().getExecutor();
        this.listeners = new HashMap();
    }

    public UploadTask(Progress progress) {
        HttpUtils.checkNotNull(progress, "progress == null");
        this.progress = progress;
        this.executor = OkUpload.getInstance().getThreadPool().getExecutor();
        this.listeners = new HashMap();
    }

    public UploadTask<T> priority(int i) {
        this.progress.priority = i;
        return this;
    }

    public UploadTask<T> extra1(Serializable serializable) {
        this.progress.extra1 = serializable;
        return this;
    }

    public UploadTask<T> extra2(Serializable serializable) {
        this.progress.extra2 = serializable;
        return this;
    }

    public UploadTask<T> extra3(Serializable serializable) {
        this.progress.extra3 = serializable;
        return this;
    }

    public UploadTask<T> save() {
        UploadManager.getInstance().replace(this.progress);
        return this;
    }

    public UploadTask<T> register(UploadListener<T> uploadListener) {
        if (uploadListener != null) {
            this.listeners.put(uploadListener.tag, uploadListener);
        }
        return this;
    }

    public void unRegister(UploadListener<T> uploadListener) {
        HttpUtils.checkNotNull(uploadListener, "listener == null");
        this.listeners.remove(uploadListener.tag);
    }

    public void unRegister(String str) {
        HttpUtils.checkNotNull(str, "tag == null");
        this.listeners.remove(str);
    }

    public UploadTask<T> start() {
        if (OkUpload.getInstance().getTask(this.progress.tag) == null || UploadManager.getInstance().get(this.progress.tag) == null) {
            throw new IllegalStateException("you must call UploadTask#save() before UploadTask#start()！");
        }
        if (this.progress.status != 1 && this.progress.status != 2) {
            postOnStart(this.progress);
            postWaiting(this.progress);
            PriorityRunnable priorityRunnable = new PriorityRunnable(this.progress.priority, this);
            this.priorityRunnable = priorityRunnable;
            this.executor.execute(priorityRunnable);
        } else {
            OkLogger.w("the task with tag " + this.progress.tag + " is already in the upload queue, current task status is " + this.progress.status);
        }
        return this;
    }

    public void restart() {
        pause();
        this.progress.status = 0;
        this.progress.currentSize = 0L;
        this.progress.fraction = 0.0f;
        this.progress.speed = 0L;
        UploadManager.getInstance().replace(this.progress);
        start();
    }

    public void pause() {
        this.executor.remove(this.priorityRunnable);
        if (this.progress.status == 1) {
            postPause(this.progress);
            return;
        }
        if (this.progress.status == 2) {
            this.progress.speed = 0L;
            this.progress.status = 3;
        } else {
            OkLogger.w("only the task with status WAITING(1) or LOADING(2) can pause, current status is " + this.progress.status);
        }
    }

    public UploadTask<T> remove() {
        pause();
        UploadManager.getInstance().delete(this.progress.tag);
        UploadTask<T> uploadTask = (UploadTask<T>) OkUpload.getInstance().removeTask(this.progress.tag);
        postOnRemove(this.progress);
        return uploadTask;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        this.progress.status = 2;
        postLoading(this.progress);
        try {
            Request<?, ? extends Request> request = this.progress.request;
            final Call rawCall = request.getRawCall();
            request.uploadInterceptor(new ProgressRequestBody.UploadInterceptor() { // from class: com.lzy.okserver.upload.UploadTask.1
                @Override // com.lzy.okgo.request.base.ProgressRequestBody.UploadInterceptor
                public void uploadProgress(Progress progress) {
                    if (rawCall.isCanceled()) {
                        return;
                    }
                    if (UploadTask.this.progress.status != 2) {
                        rawCall.cancel();
                        return;
                    }
                    UploadTask.this.progress.from(progress);
                    UploadTask uploadTask = UploadTask.this;
                    uploadTask.postLoading(uploadTask.progress);
                }
            });
            Response<?> responseExecute = request.adapt().execute();
            if (responseExecute.isSuccessful()) {
                postOnFinish(this.progress, responseExecute.body());
            } else {
                postOnError(this.progress, responseExecute.getException());
            }
        } catch (Exception e) {
            postOnError(this.progress, e);
        }
    }

    private void postOnStart(final Progress progress) {
        progress.speed = 0L;
        progress.status = 0;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<UploadListener<T>> it = UploadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onStart(progress);
                }
            }
        });
    }

    private void postWaiting(final Progress progress) {
        progress.speed = 0L;
        progress.status = 1;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<UploadListener<T>> it = UploadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(progress);
                }
            }
        });
    }

    private void postPause(final Progress progress) {
        progress.speed = 0L;
        progress.status = 3;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<UploadListener<T>> it = UploadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(progress);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLoading(final Progress progress) {
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<UploadListener<T>> it = UploadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(progress);
                }
            }
        });
    }

    private void postOnError(final Progress progress, Throwable th) {
        progress.speed = 0L;
        progress.status = 4;
        progress.exception = th;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.6
            @Override // java.lang.Runnable
            public void run() {
                for (UploadListener<T> uploadListener : UploadTask.this.listeners.values()) {
                    uploadListener.onProgress(progress);
                    uploadListener.onError(progress);
                }
            }
        });
    }

    private void postOnFinish(final Progress progress, final T t) {
        progress.speed = 0L;
        progress.fraction = 1.0f;
        progress.status = 5;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.7
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                for (UploadListener<T> uploadListener : UploadTask.this.listeners.values()) {
                    uploadListener.onProgress(progress);
                    uploadListener.onFinish(t, progress);
                }
            }
        });
    }

    private void postOnRemove(final Progress progress) {
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.upload.UploadTask.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<UploadListener<T>> it = UploadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onRemove(progress);
                }
                UploadTask.this.listeners.clear();
            }
        });
    }

    private void updateDatabase(Progress progress) {
        UploadManager.getInstance().update(Progress.buildUpdateContentValues(progress), progress.tag);
    }
}
