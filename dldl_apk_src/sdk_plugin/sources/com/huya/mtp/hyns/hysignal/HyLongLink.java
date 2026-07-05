package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.core.HySignalError;
import com.huya.hysignal.wrapper.business.BaseBiz;
import com.huya.hysignal.wrapper.business.PushBiz;
import com.huya.mtp.hyns.api.Call;
import com.huya.mtp.hyns.api.Callback;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.Request;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyLongLink implements NSLongLinkApi {
    private BaseBiz mBaseBiz = Hal.getBaseBiz();
    private PushBiz mPushBiz = Hal.getPushBiz();

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public Call newCall(Request request) {
        return new CallDelegate(this.mBaseBiz.newCall(request), request);
    }

    private static class CallDelegate implements Call {
        private com.huya.hysignal.core.Call mRealCall;
        private Request mRequest;

        public CallDelegate(com.huya.hysignal.core.Call call, Request request) {
            this.mRealCall = call;
            this.mRequest = request;
        }

        @Override // com.huya.mtp.hyns.api.Call
        public Request request() {
            return this.mRequest;
        }

        @Override // com.huya.mtp.hyns.api.Call
        public void enqueue(final Callback callback) {
            this.mRealCall.enqueue(new com.huya.hysignal.core.Callback() { // from class: com.huya.mtp.hyns.hysignal.HyLongLink.CallDelegate.1
                @Override // com.huya.hysignal.core.Callback
                public void onResponse(byte[] bArr, HySignalError hySignalError) {
                    callback.onResponse(bArr, hySignalError.getErrType(), hySignalError.getErrType());
                }
            });
        }

        @Override // com.huya.mtp.hyns.api.Call
        public void cancel() {
            this.mRealCall.cancel();
        }
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public void addPushListener(NSLongLinkApi.PushListener pushListener) {
        this.mPushBiz.addPushListener(pushListener);
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public void removePushListener(NSLongLinkApi.PushListener pushListener) {
        if (pushListener != null) {
            this.mPushBiz.removePushListener(pushListener);
        }
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public int getLinkStatus() {
        return this.mPushBiz.getLinkStatus();
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public void updateExperimentConfig(Map<String, String> map) {
        this.mBaseBiz.updateExperimentConfig(map);
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public boolean addPushStatInfoListener(NSLongLinkApi.PushStatListener pushStatListener) {
        return this.mPushBiz.addPushStatInfoListener(pushStatListener);
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi
    public boolean removePushStatInfoListener(NSLongLinkApi.PushStatListener pushStatListener) {
        return this.mPushBiz.removePushStatInfoListener(pushStatListener);
    }
}
