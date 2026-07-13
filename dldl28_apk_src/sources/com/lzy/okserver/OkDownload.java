package com.lzy.okserver;

import android.os.Environment;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.IOUtils;
import com.lzy.okgo.utils.OkLogger;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.download.DownloadThreadPool;
import com.lzy.okserver.task.XExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class OkDownload {
    private String folder;
    private ConcurrentHashMap<String, DownloadTask> taskMap;
    private DownloadThreadPool threadPool;

    public static OkDownload getInstance() {
        return OkDownloadHolder.instance;
    }

    private static class OkDownloadHolder {
        private static final OkDownload instance = new OkDownload();

        private OkDownloadHolder() {
        }
    }

    private OkDownload() {
        String str = Environment.getExternalStorageDirectory() + File.separator + "download" + File.separator;
        this.folder = str;
        IOUtils.createFolder(str);
        this.threadPool = new DownloadThreadPool();
        this.taskMap = new ConcurrentHashMap<>();
        List<Progress> downloading = DownloadManager.getInstance().getDownloading();
        for (Progress progress : downloading) {
            if (progress.status == 1 || progress.status == 2 || progress.status == 3) {
                progress.status = 0;
            }
        }
        DownloadManager.getInstance().replace((List) downloading);
    }

    public static DownloadTask request(String str, Request<File, ? extends Request> request) {
        Map<String, DownloadTask> taskMap = getInstance().getTaskMap();
        DownloadTask downloadTask = taskMap.get(str);
        if (downloadTask != null) {
            return downloadTask;
        }
        DownloadTask downloadTask2 = new DownloadTask(str, request);
        taskMap.put(str, downloadTask2);
        return downloadTask2;
    }

    public static DownloadTask restore(Progress progress) {
        Map<String, DownloadTask> taskMap = getInstance().getTaskMap();
        DownloadTask downloadTask = taskMap.get(progress.tag);
        if (downloadTask != null) {
            return downloadTask;
        }
        DownloadTask downloadTask2 = new DownloadTask(progress);
        taskMap.put(progress.tag, downloadTask2);
        return downloadTask2;
    }

    public static List<DownloadTask> restore(List<Progress> list) {
        Map<String, DownloadTask> taskMap = getInstance().getTaskMap();
        ArrayList arrayList = new ArrayList();
        for (Progress progress : list) {
            DownloadTask downloadTask = taskMap.get(progress.tag);
            if (downloadTask == null) {
                downloadTask = new DownloadTask(progress);
                taskMap.put(progress.tag, downloadTask);
            }
            arrayList.add(downloadTask);
        }
        return arrayList;
    }

    public void startAll() {
        for (Map.Entry<String, DownloadTask> entry : this.taskMap.entrySet()) {
            DownloadTask value = entry.getValue();
            if (value == null) {
                OkLogger.w("can't find task with tag = " + entry.getKey());
            } else {
                value.start();
            }
        }
    }

    public void pauseAll() {
        for (Map.Entry<String, DownloadTask> entry : this.taskMap.entrySet()) {
            DownloadTask value = entry.getValue();
            if (value == null) {
                OkLogger.w("can't find task with tag = " + entry.getKey());
            } else if (value.progress.status != 2) {
                value.pause();
            }
        }
        for (Map.Entry<String, DownloadTask> entry2 : this.taskMap.entrySet()) {
            DownloadTask value2 = entry2.getValue();
            if (value2 == null) {
                OkLogger.w("can't find task with tag = " + entry2.getKey());
            } else if (value2.progress.status == 2) {
                value2.pause();
            }
        }
    }

    public void removeAll() {
        removeAll(false);
    }

    public void removeAll(boolean z) {
        HashMap map = new HashMap(this.taskMap);
        for (Map.Entry entry : map.entrySet()) {
            DownloadTask downloadTask = (DownloadTask) entry.getValue();
            if (downloadTask == null) {
                OkLogger.w("can't find task with tag = " + ((String) entry.getKey()));
            } else if (downloadTask.progress.status != 2) {
                downloadTask.remove(z);
            }
        }
        for (Map.Entry entry2 : map.entrySet()) {
            DownloadTask downloadTask2 = (DownloadTask) entry2.getValue();
            if (downloadTask2 == null) {
                OkLogger.w("can't find task with tag = " + ((String) entry2.getKey()));
            } else if (downloadTask2.progress.status == 2) {
                downloadTask2.remove(z);
            }
        }
    }

    public String getFolder() {
        return this.folder;
    }

    public OkDownload setFolder(String str) {
        this.folder = str;
        return this;
    }

    public DownloadThreadPool getThreadPool() {
        return this.threadPool;
    }

    public Map<String, DownloadTask> getTaskMap() {
        return this.taskMap;
    }

    public DownloadTask getTask(String str) {
        return this.taskMap.get(str);
    }

    public boolean hasTask(String str) {
        return this.taskMap.containsKey(str);
    }

    public DownloadTask removeTask(String str) {
        return this.taskMap.remove(str);
    }

    public void addOnAllTaskEndListener(XExecutor.OnAllTaskEndListener onAllTaskEndListener) {
        this.threadPool.getExecutor().addOnAllTaskEndListener(onAllTaskEndListener);
    }

    public void removeOnAllTaskEndListener(XExecutor.OnAllTaskEndListener onAllTaskEndListener) {
        this.threadPool.getExecutor().removeOnAllTaskEndListener(onAllTaskEndListener);
    }
}
