package com.sq.libwebsocket.dispatcher;

import android.os.Process;
import com.sq.libwebsocket.dispatcher.ResponseProcessEngine;
import com.sq.libwebsocket.util.LogUtil;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EngineThread extends Thread {
    private ExecutorService executorService;
    private boolean stop;
    private String TAG = "EngineThread";
    private ArrayBlockingQueue<ResponseProcessEngine.EngineEntity> jobQueue = new ArrayBlockingQueue<>(3000);

    @Override // java.lang.Thread
    public synchronized void start() {
        this.stop = false;
        super.start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        Process.setThreadPriority(10);
        while (!this.stop) {
            try {
                ResponseProcessEngine.EngineEntity engineEntityTake = this.jobQueue.take();
                if (engineEntityTake.isError) {
                    engineEntityTake.dispatcher.onSendDataError(engineEntityTake.errorResponse, engineEntityTake.delivery);
                    LogUtil.i(this.TAG, "onSendDataError");
                } else {
                    engineEntityTake.response.onResponse(engineEntityTake.dispatcher, engineEntityTake.delivery);
                    LogUtil.i(this.TAG, "onResponse");
                }
                ResponseProcessEngine.EngineEntity.release(engineEntityTake);
            } catch (InterruptedException unused) {
                if (this.stop) {
                    return;
                }
            } catch (Exception e) {
                LogUtil.e(this.TAG, "run()->Exception", e);
            }
        }
    }

    void add(ResponseProcessEngine.EngineEntity engineEntity) {
        if (this.jobQueue.offer(engineEntity)) {
            return;
        }
        LogUtil.e(this.TAG, "Offer response to Engine failed!start an thread to put.");
    }

    void quit() {
        this.stop = true;
        this.jobQueue.clear();
        interrupt();
    }
}
