package com.sdk.sq.net;

import android.os.Handler;
import android.os.Looper;
import com.sqnetwork.voly.ExecutorDelivery;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.VolleyError;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class ExecutorDeliveryWithReporter extends ExecutorDelivery {
    private ExceptionReporter mReporter;

    public ExecutorDeliveryWithReporter() {
        super(new Handler(Looper.getMainLooper()));
    }

    public ExecutorDeliveryWithReporter(Handler handler) {
        super(handler);
    }

    public ExecutorDeliveryWithReporter(Executor executor) {
        super(executor);
    }

    @Override // com.sqnetwork.voly.ExecutorDelivery, com.sqnetwork.voly.ResponseDelivery
    public void postError(Request<?> request, VolleyError error) {
        ExceptionReporter exceptionReporter = this.mReporter;
        if (exceptionReporter != null) {
            exceptionReporter.report(request, error);
        }
        super.postError(request, error);
    }

    public void setExceptionReporter(ExceptionReporter reporter) {
        this.mReporter = reporter;
    }
}
