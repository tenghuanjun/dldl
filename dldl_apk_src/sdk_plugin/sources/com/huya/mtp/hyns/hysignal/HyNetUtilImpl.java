package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.wrapper.business.NetUtilBiz;
import com.huya.mtp.hyns.api.NSNetUtilApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyNetUtilImpl implements NSNetUtilApi {
    private NetUtilBiz mNetUtilBiz = Hal.getNetUtilBiz();

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public boolean isLongLinkConnected(int i) {
        return this.mNetUtilBiz.isLongLinkConnected(i);
    }

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public boolean isLongLinkConnected(String str) {
        return this.mNetUtilBiz.isLongLinkConnected(str);
    }

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public int getLinkStatus(int i) {
        return this.mNetUtilBiz.getLinkStatus(i);
    }

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public int getLinkStatus(String str) {
        return this.mNetUtilBiz.getLinkStatus(str);
    }

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public boolean addLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        return this.mNetUtilBiz.addLinkStatusListener(str, linkStatusListener);
    }

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public boolean removeLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        return this.mNetUtilBiz.removeLinkStatusListener(str, linkStatusListener);
    }

    @Override // com.huya.mtp.hyns.api.NSNetUtilApi
    public NSNetUtilApi.HySignalIPStack getLocalIPStack() {
        return this.mNetUtilBiz.getLocalIPStack();
    }
}
