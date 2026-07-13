package com.lzy.okserver;

import com.lzy.okgo.db.UploadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.OkLogger;
import com.lzy.okserver.task.XExecutor;
import com.lzy.okserver.upload.UploadTask;
import com.lzy.okserver.upload.UploadThreadPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class OkUpload {
    private Map<String, UploadTask<?>> taskMap;
    private UploadThreadPool threadPool;

    public static OkUpload getInstance() {
        return OkUploadHolder.instance;
    }

    private static class OkUploadHolder {
        private static final OkUpload instance = new OkUpload();

        private OkUploadHolder() {
        }
    }

    private OkUpload() {
        this.threadPool = new UploadThreadPool();
        this.taskMap = new LinkedHashMap();
        List<Progress> uploading = UploadManager.getInstance().getUploading();
        for (Progress progress : uploading) {
            if (progress.status == 1 || progress.status == 2 || progress.status == 3) {
                progress.status = 0;
            }
        }
        UploadManager.getInstance().replace((List) uploading);
    }

    public static <T> UploadTask<T> request(String str, Request<T, ? extends Request> request) {
        Map<String, UploadTask<?>> taskMap = getInstance().getTaskMap();
        UploadTask<T> uploadTask = (UploadTask) taskMap.get(str);
        if (uploadTask != null) {
            return uploadTask;
        }
        UploadTask<T> uploadTask2 = new UploadTask<>(str, request);
        taskMap.put(str, uploadTask2);
        return uploadTask2;
    }

    public static <T> UploadTask<T> restore(Progress progress) {
        Map<String, UploadTask<?>> taskMap = getInstance().getTaskMap();
        UploadTask<T> uploadTask = (UploadTask) taskMap.get(progress.tag);
        if (uploadTask != null) {
            return uploadTask;
        }
        UploadTask<T> uploadTask2 = new UploadTask<>(progress);
        taskMap.put(progress.tag, uploadTask2);
        return uploadTask2;
    }

    public static List<UploadTask<?>> restore(List<Progress> list) {
        Map<String, UploadTask<?>> taskMap = getInstance().getTaskMap();
        ArrayList arrayList = new ArrayList();
        for (Progress progress : list) {
            UploadTask<?> uploadTask = taskMap.get(progress.tag);
            if (uploadTask == null) {
                uploadTask = new UploadTask<>(progress);
                taskMap.put(progress.tag, uploadTask);
            }
            arrayList.add(uploadTask);
        }
        return arrayList;
    }

    public void startAll() {
        for (Map.Entry<String, UploadTask<?>> entry : this.taskMap.entrySet()) {
            UploadTask<?> value = entry.getValue();
            if (value == null) {
                OkLogger.w("can't find task with tag = " + entry.getKey());
            } else {
                value.start();
            }
        }
    }

    public void pauseAll() {
        for (Map.Entry<String, UploadTask<?>> entry : this.taskMap.entrySet()) {
            UploadTask<?> value = entry.getValue();
            if (value == null) {
                OkLogger.w("can't find task with tag = " + entry.getKey());
            } else if (value.progress.status != 2) {
                value.pause();
            }
        }
        for (Map.Entry<String, UploadTask<?>> entry2 : this.taskMap.entrySet()) {
            UploadTask<?> value2 = entry2.getValue();
            if (value2 == null) {
                OkLogger.w("can't find task with tag = " + entry2.getKey());
            } else if (value2.progress.status == 2) {
                value2.pause();
            }
        }
    }

    public void removeAll() {
        HashMap map = new HashMap(this.taskMap);
        for (Map.Entry entry : map.entrySet()) {
            UploadTask uploadTask = (UploadTask) entry.getValue();
            if (uploadTask == null) {
                OkLogger.w("can't find task with tag = " + ((String) entry.getKey()));
            } else if (uploadTask.progress.status != 2) {
                uploadTask.remove();
            }
        }
        for (Map.Entry entry2 : map.entrySet()) {
            UploadTask uploadTask2 = (UploadTask) entry2.getValue();
            if (uploadTask2 == null) {
                OkLogger.w("can't find task with tag = " + ((String) entry2.getKey()));
            } else if (uploadTask2.progress.status == 2) {
                uploadTask2.remove();
            }
        }
    }

    public UploadThreadPool getThreadPool() {
        return this.threadPool;
    }

    public Map<String, UploadTask<?>> getTaskMap() {
        return this.taskMap;
    }

    public UploadTask<?> getTask(String str) {
        return this.taskMap.get(str);
    }

    public boolean hasTask(String str) {
        return this.taskMap.containsKey(str);
    }

    public UploadTask<?> removeTask(String str) {
        return this.taskMap.remove(str);
    }

    public void addOnAllTaskEndListener(XExecutor.OnAllTaskEndListener onAllTaskEndListener) {
        this.threadPool.getExecutor().addOnAllTaskEndListener(onAllTaskEndListener);
    }

    public void removeOnAllTaskEndListener(XExecutor.OnAllTaskEndListener onAllTaskEndListener) {
        this.threadPool.getExecutor().removeOnAllTaskEndListener(onAllTaskEndListener);
    }
}
