package com.sq.websocket_engine;

import com.sq.websocket_engine.RequestQueueHandler;
import com.sq.websocket_engine.parse.ResponseDataParse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqWrapperHandler {

    public interface FailedListener<T> {
        void on(T t);
    }

    public interface FinishListener<T> {
        void on(T t);
    }

    public interface SuccessListener<T> {
        void on(T t);
    }

    public abstract class ReqCallback<T> {
        private T rsp = null;

        public void failed(ResponseDataParse responseDataParse) {
        }

        abstract void on(T t);

        public ReqCallback() {
        }

        public void setRsp(T t) {
            this.rsp = t;
        }

        public T getRsp() {
            return this.rsp;
        }

        public void onSuccess() {
            on(this.rsp);
        }
    }

    public class FailedHolder<T> {
        boolean banFailedTips = false;
        FailedListener failedListener;

        public FailedHolder() {
        }

        public FailedHolder<T> onFailed(FailedListener<T> failedListener) {
            this.failedListener = failedListener;
            return this;
        }

        public FailedHolder<T> banTips() {
            return banTips(true);
        }

        public FailedHolder<T> banTips(Boolean bool) {
            this.banFailedTips = bool.booleanValue();
            return this;
        }
    }

    private <T> void req(MsgBaseReq msgBaseReq, final ReqCallback<T> reqCallback) {
        RequestQueueHandler.getInstance().sendMessage(msgBaseReq, new RequestQueueHandler.IRequestCallback<ResponseDataParse>() { // from class: com.sq.websocket_engine.ReqWrapperHandler.1
            @Override // com.sq.websocket_engine.RequestQueueHandler.IRequestCallback
            public void onSuccess(ResponseDataParse responseDataParse) {
                reqCallback.setRsp(responseDataParse);
                reqCallback.onSuccess();
            }

            @Override // com.sq.websocket_engine.RequestQueueHandler.IRequestCallback
            public void onFalid(ResponseDataParse responseDataParse) {
                reqCallback.failed(responseDataParse);
            }
        });
    }

    public <Req extends MsgBaseReq, Rsp extends ResponseDataParse> FailedHolder<Rsp> reqImpl(Req req, final SuccessListener<Rsp> successListener) {
        final FailedHolder<Rsp> failedHolder = new FailedHolder<>();
        req(req, new ReqCallback<Rsp>() { // from class: com.sq.websocket_engine.ReqWrapperHandler.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Incorrect types in method signature: (TRsp;)V */
            @Override // com.sq.websocket_engine.ReqWrapperHandler.ReqCallback
            public void on(ResponseDataParse responseDataParse) {
                successListener.on(responseDataParse);
            }

            @Override // com.sq.websocket_engine.ReqWrapperHandler.ReqCallback
            public void failed(ResponseDataParse responseDataParse) {
                if (failedHolder.failedListener == null && !failedHolder.banFailedTips) {
                    super.failed(responseDataParse);
                    return;
                }
                failedHolder.failedListener.on(getRsp());
                if (failedHolder.banFailedTips) {
                    return;
                }
                super.failed(responseDataParse);
            }
        });
        return failedHolder;
    }
}
