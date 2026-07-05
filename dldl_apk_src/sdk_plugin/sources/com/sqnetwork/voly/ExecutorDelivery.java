package com.sqnetwork.voly;

import android.os.Handler;
import com.sqwan.liveshow.huya.SqR;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ExecutorDelivery implements ResponseDelivery {
    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler) {
        this.mResponsePoster = new Executor() { // from class: com.sqnetwork.voly.ExecutorDelivery.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    public ExecutorDelivery(Executor executor) {
        this.mResponsePoster = executor;
    }

    @Override // com.sqnetwork.voly.ResponseDelivery
    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, null);
    }

    @Override // com.sqnetwork.voly.ResponseDelivery
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        request.getRequestStatus().response = response;
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
    }

    @Override // com.sqnetwork.voly.ResponseDelivery
    public void postError(Request<?> request, VolleyError error) {
        request.addMarker("post-error");
        Response<?> responseError = Response.error(error);
        request.getRequestStatus().response = responseError;
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, responseError, null));
    }

    private static class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public ResponseDeliveryRunnable(Request request, Response response, Runnable runnable) {
            this.mRequest = request;
            this.mResponse = response;
            this.mRunnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("canceled-at-delivery");
                return;
            }
            if (this.mResponse.isSuccess()) {
                Request request = this.mRequest;
                Response response = this.mResponse;
                request.deliverResponse(response, response.result);
            } else {
                this.mRequest.deliverError(this.mResponse.error);
            }
            if (this.mResponse.intermediate) {
                this.mRequest.addMarker("intermediate-response");
            } else {
                this.mRequest.finish(SqR.string.done);
            }
            Runnable runnable = this.mRunnable;
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
