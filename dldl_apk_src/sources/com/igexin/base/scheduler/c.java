package com.igexin.base.scheduler;

import com.igexin.base.scheduler.BaseTask;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface c<T extends BaseTask> {
    void execute(T t);

    void submit(T t);
}
