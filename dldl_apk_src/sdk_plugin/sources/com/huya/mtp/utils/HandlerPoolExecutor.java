package com.huya.mtp.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HandlerPoolExecutor implements Executor {
    private ExecutorService mExecutor = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.huya.mtp.utils.HandlerPoolExecutor.1
        private int num = 0;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("HandlerPoolExecutor-");
            int i = this.num;
            this.num = i + 1;
            sb.append(i);
            return new Thread(runnable, sb.toString());
        }
    });

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mExecutor.execute(runnable);
    }
}
