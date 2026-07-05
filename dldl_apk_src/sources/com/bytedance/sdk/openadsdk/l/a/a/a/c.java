package com.bytedance.sdk.openadsdk.l.a.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTNativeExpressAd.ExpressVideoAdListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public c(TTNativeExpressAd.ExpressVideoAdListener expressVideoAdListener) {
        this.b = expressVideoAdListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        TTNativeExpressAd.ExpressVideoAdListener expressVideoAdListener = this.b;
        if (expressVideoAdListener == null) {
            return null;
        }
        switch (i) {
            case 152101:
                expressVideoAdListener.onVideoLoad();
                break;
            case 152102:
                this.b.onVideoError(valueSet.intValue(0), valueSet.intValue(1));
                break;
            case 152103:
                expressVideoAdListener.onVideoAdStartPlay();
                break;
            case 152104:
                expressVideoAdListener.onVideoAdPaused();
                break;
            case 152105:
                expressVideoAdListener.onVideoAdContinuePlay();
                break;
            case 152106:
                this.b.onProgressUpdate(valueSet.longValue(0), valueSet.longValue(1));
                break;
            case 152107:
                expressVideoAdListener.onVideoAdComplete();
                break;
            case 152108:
                expressVideoAdListener.onClickRetry();
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
