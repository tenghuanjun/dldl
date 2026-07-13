package com.lzy.okserver.download;

import android.text.TextUtils;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.OkGoException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.HttpUtils;
import com.lzy.okgo.utils.IOUtils;
import com.lzy.okgo.utils.OkLogger;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.task.PriorityRunnable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class DownloadTask implements Runnable {
    private static final int BUFFER_SIZE = 8192;
    private ThreadPoolExecutor executor;
    public Map<Object, DownloadListener> listeners;
    private PriorityRunnable priorityRunnable;
    public Progress progress;

    public DownloadTask(String str, Request<File, ? extends Request> request) {
        HttpUtils.checkNotNull(str, "tag == null");
        Progress progress = new Progress();
        this.progress = progress;
        progress.tag = str;
        this.progress.folder = OkDownload.getInstance().getFolder();
        this.progress.url = request.getBaseUrl();
        this.progress.status = 0;
        this.progress.totalSize = -1L;
        this.progress.request = request;
        this.executor = OkDownload.getInstance().getThreadPool().getExecutor();
        this.listeners = new HashMap();
    }

    public DownloadTask(Progress progress) {
        HttpUtils.checkNotNull(progress, "progress == null");
        this.progress = progress;
        this.executor = OkDownload.getInstance().getThreadPool().getExecutor();
        this.listeners = new HashMap();
    }

    public DownloadTask folder(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.progress.folder = str;
        } else {
            OkLogger.w("folder is null, ignored!");
        }
        return this;
    }

    public DownloadTask fileName(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.progress.fileName = str;
        } else {
            OkLogger.w("fileName is null, ignored!");
        }
        return this;
    }

    public DownloadTask priority(int i) {
        this.progress.priority = i;
        return this;
    }

    public DownloadTask extra1(Serializable serializable) {
        this.progress.extra1 = serializable;
        return this;
    }

    public DownloadTask extra2(Serializable serializable) {
        this.progress.extra2 = serializable;
        return this;
    }

    public DownloadTask extra3(Serializable serializable) {
        this.progress.extra3 = serializable;
        return this;
    }

    public DownloadTask save() {
        if (!TextUtils.isEmpty(this.progress.folder) && !TextUtils.isEmpty(this.progress.fileName)) {
            this.progress.filePath = new File(this.progress.folder, this.progress.fileName).getAbsolutePath();
        }
        DownloadManager.getInstance().replace(this.progress);
        return this;
    }

    public DownloadTask register(DownloadListener downloadListener) {
        if (downloadListener != null) {
            this.listeners.put(downloadListener.tag, downloadListener);
        }
        return this;
    }

    public void unRegister(DownloadListener downloadListener) {
        HttpUtils.checkNotNull(downloadListener, "listener == null");
        this.listeners.remove(downloadListener.tag);
    }

    public void unRegister(String str) {
        HttpUtils.checkNotNull(str, "tag == null");
        this.listeners.remove(str);
    }

    public void start() {
        if (OkDownload.getInstance().getTask(this.progress.tag) == null || DownloadManager.getInstance().get(this.progress.tag) == null) {
            throw new IllegalStateException("you must call DownloadTask#save() before DownloadTask#start()！");
        }
        if (this.progress.status == 0 || this.progress.status == 3 || this.progress.status == 4) {
            postOnStart(this.progress);
            postWaiting(this.progress);
            PriorityRunnable priorityRunnable = new PriorityRunnable(this.progress.priority, this);
            this.priorityRunnable = priorityRunnable;
            this.executor.execute(priorityRunnable);
            return;
        }
        if (this.progress.status == 5) {
            if (this.progress.filePath == null) {
                postOnError(this.progress, new StorageException("the file of the task with tag:" + this.progress.tag + " may be invalid or damaged, please call the method restart() to download again！"));
                return;
            }
            File file = new File(this.progress.filePath);
            if (file.exists() && file.length() == this.progress.totalSize) {
                postOnFinish(this.progress, new File(this.progress.filePath));
                return;
            }
            postOnError(this.progress, new StorageException("the file " + this.progress.filePath + " may be invalid or damaged, please call the method restart() to download again！"));
            return;
        }
        OkLogger.w("the task with tag " + this.progress.tag + " is already in the download queue, current task status is " + this.progress.status);
    }

    public void restart() {
        pause();
        IOUtils.delFileOrFolder(this.progress.filePath);
        this.progress.status = 0;
        this.progress.currentSize = 0L;
        this.progress.fraction = 0.0f;
        this.progress.speed = 0L;
        DownloadManager.getInstance().replace(this.progress);
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

    public void remove() {
        remove(false);
    }

    public DownloadTask remove(boolean z) {
        pause();
        if (z) {
            IOUtils.delFileOrFolder(this.progress.filePath);
        }
        DownloadManager.getInstance().delete(this.progress.tag);
        DownloadTask downloadTaskRemoveTask = OkDownload.getInstance().removeTask(this.progress.tag);
        postOnRemove(this.progress);
        return downloadTaskRemoveTask;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        File file;
        long j = this.progress.currentSize;
        if (j < 0) {
            postOnError(this.progress, OkGoException.BREAKPOINT_EXPIRED());
            return;
        }
        if (j > 0 && !TextUtils.isEmpty(this.progress.filePath) && !new File(this.progress.filePath).exists()) {
            postOnError(this.progress, OkGoException.BREAKPOINT_NOT_EXIST());
            return;
        }
        try {
            Request<?, ? extends Request> request = this.progress.request;
            request.headers(HttpHeaders.HEAD_KEY_RANGE, "bytes=" + j + "-");
            Response responseExecute = request.execute();
            int iCode = responseExecute.code();
            if (iCode == 404 || iCode >= 500) {
                postOnError(this.progress, HttpException.NET_ERROR());
                return;
            }
            ResponseBody responseBodyBody = responseExecute.body();
            if (responseBodyBody == null) {
                postOnError(this.progress, new HttpException("response body is null"));
                return;
            }
            if (this.progress.totalSize == -1) {
                this.progress.totalSize = responseBodyBody.contentLength();
            }
            String netFileName = this.progress.fileName;
            if (TextUtils.isEmpty(netFileName)) {
                netFileName = HttpUtils.getNetFileName(responseExecute, this.progress.url);
                this.progress.fileName = netFileName;
            }
            if (!IOUtils.createFolder(this.progress.folder)) {
                postOnError(this.progress, StorageException.NOT_AVAILABLE());
                return;
            }
            if (TextUtils.isEmpty(this.progress.filePath)) {
                file = new File(this.progress.folder, netFileName);
                this.progress.filePath = file.getAbsolutePath();
            } else {
                file = new File(this.progress.filePath);
            }
            if (j > 0 && !file.exists()) {
                postOnError(this.progress, OkGoException.BREAKPOINT_EXPIRED());
                return;
            }
            if (j > this.progress.totalSize) {
                postOnError(this.progress, OkGoException.BREAKPOINT_EXPIRED());
                return;
            }
            if (j == 0 && file.exists()) {
                IOUtils.delFileOrFolder(file);
            }
            if (j == this.progress.totalSize && j > 0) {
                if (file.exists() && j == file.length()) {
                    postOnFinish(this.progress, file);
                    return;
                } else {
                    postOnError(this.progress, OkGoException.BREAKPOINT_EXPIRED());
                    return;
                }
            }
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(j);
                this.progress.currentSize = j;
                try {
                    DownloadManager.getInstance().replace(this.progress);
                    download(responseBodyBody.byteStream(), randomAccessFile, this.progress);
                    if (this.progress.status == 3) {
                        postPause(this.progress);
                        return;
                    }
                    if (this.progress.status != 2) {
                        postOnError(this.progress, OkGoException.UNKNOWN());
                    } else if (file.length() == this.progress.totalSize) {
                        postOnFinish(this.progress, file);
                    } else {
                        postOnError(this.progress, OkGoException.BREAKPOINT_EXPIRED());
                    }
                } catch (IOException e) {
                    postOnError(this.progress, e);
                }
            } catch (Exception e2) {
                postOnError(this.progress, e2);
            }
        } catch (IOException e3) {
            postOnError(this.progress, e3);
        }
    }

    private void download(InputStream inputStream, RandomAccessFile randomAccessFile, Progress progress) throws Throwable {
        if (inputStream == null || randomAccessFile == null) {
            return;
        }
        progress.status = 2;
        byte[] bArr = new byte[BUFFER_SIZE];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
        while (true) {
            try {
                int i = bufferedInputStream.read(bArr, 0, BUFFER_SIZE);
                if (i == -1 || progress.status != 2) {
                    break;
                }
                randomAccessFile.write(bArr, 0, i);
                try {
                    Progress.changeProgress(progress, i, progress.totalSize, new Progress.Action() { // from class: com.lzy.okserver.download.DownloadTask.1
                        @Override // com.lzy.okgo.model.Progress.Action
                        public void call(Progress progress2) {
                            DownloadTask.this.postLoading(progress2);
                        }
                    });
                } catch (Throwable th) {
                    th = th;
                    IOUtils.closeQuietly(randomAccessFile);
                    IOUtils.closeQuietly(bufferedInputStream);
                    IOUtils.closeQuietly(inputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        IOUtils.closeQuietly(randomAccessFile);
        IOUtils.closeQuietly(bufferedInputStream);
        IOUtils.closeQuietly(inputStream);
    }

    private void postOnStart(final Progress progress) {
        progress.speed = 0L;
        progress.status = 0;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadListener> it = DownloadTask.this.listeners.values().iterator();
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
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadListener> it = DownloadTask.this.listeners.values().iterator();
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
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadListener> it = DownloadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(progress);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLoading(final Progress progress) {
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadListener> it = DownloadTask.this.listeners.values().iterator();
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
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.6
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadListener downloadListener : DownloadTask.this.listeners.values()) {
                    downloadListener.onProgress(progress);
                    downloadListener.onError(progress);
                }
            }
        });
    }

    private void postOnFinish(final Progress progress, final File file) {
        progress.speed = 0L;
        progress.fraction = 1.0f;
        progress.status = 5;
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.7
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadListener downloadListener : DownloadTask.this.listeners.values()) {
                    downloadListener.onProgress(progress);
                    downloadListener.onFinish(file, progress);
                }
            }
        });
    }

    private void postOnRemove(final Progress progress) {
        updateDatabase(progress);
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okserver.download.DownloadTask.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadListener> it = DownloadTask.this.listeners.values().iterator();
                while (it.hasNext()) {
                    it.next().onRemove(progress);
                }
                DownloadTask.this.listeners.clear();
            }
        });
    }

    private void updateDatabase(Progress progress) {
        DownloadManager.getInstance().update(Progress.buildUpdateContentValues(progress), progress.tag);
    }
}
