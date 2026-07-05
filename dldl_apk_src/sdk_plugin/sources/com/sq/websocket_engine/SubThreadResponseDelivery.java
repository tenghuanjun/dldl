package com.sq.websocket_engine;

import android.os.Handler;
import android.os.HandlerThread;
import com.sq.libwebsocket.dispatcher.MainThreadResponseDelivery;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SubThreadResponseDelivery extends MainThreadResponseDelivery {
    private Handler handler;

    public SubThreadResponseDelivery() {
        HandlerThread handlerThread = new HandlerThread("SubThreadResponseDelivery");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());
    }

    @Override // com.sq.libwebsocket.dispatcher.MainThreadResponseDelivery
    protected void runThread(Runnable runnable) {
        this.handler.post(runnable);
    }

    @Override // com.sq.libwebsocket.dispatcher.MainThreadResponseDelivery, com.sq.libwebsocket.dispatcher.ResponseDelivery
    public void destroy() {
        if (RUNNABLE_POOL != null) {
            Iterator<MainThreadResponseDelivery.CallbackRunnable> it = RUNNABLE_POOL.iterator();
            while (it.hasNext()) {
                this.handler.removeCallbacks(it.next());
            }
        }
    }
}
