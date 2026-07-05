package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.wrapper.business.LiveLaunchBiz;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.api.NSLaunchApi;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyLaunchBiz implements NSLaunchApi {
    public static final String TAG = "-HyLaunchBiz";
    private LiveLaunchBiz mLiveLaunchBiz = Hal.getLiveLaunchBiz();
    private ConcurrentHashMap<NSLaunchApi.NSGuidListener, HySignalGuidListener> mGuidListenerMap = new ConcurrentHashMap<>();

    @Override // com.huya.mtp.hyns.api.NSLaunchApi
    public String getGuid() {
        return this.mLiveLaunchBiz.getGuid();
    }

    @Override // com.huya.mtp.hyns.api.NSLaunchApi
    public String getClientIp() {
        return this.mLiveLaunchBiz.getClientIp();
    }

    @Override // com.huya.mtp.hyns.api.NSLaunchApi
    public void addGuidListener(final NSLaunchApi.NSGuidListener nSGuidListener) {
        HySignalGuidListener hySignalGuidListener = new HySignalGuidListener() { // from class: com.huya.mtp.hyns.hysignal.HyLaunchBiz.1
            @Override // com.huya.hysignal.listener.HySignalGuidListener
            public void onGuid(String str) {
                nSGuidListener.onGuid(str);
                MTPApi.LOGGER.debug("NetService-HyLaunchBiz", "sdk guid :%s", str);
            }
        };
        this.mGuidListenerMap.put(nSGuidListener, hySignalGuidListener);
        this.mLiveLaunchBiz.addGuidListener(hySignalGuidListener);
        nSGuidListener.onGuid(getGuid());
    }

    @Override // com.huya.mtp.hyns.api.NSLaunchApi
    public void removeGuidListener(NSLaunchApi.NSGuidListener nSGuidListener) {
        HySignalGuidListener hySignalGuidListenerRemove = this.mGuidListenerMap.remove(nSGuidListener);
        if (hySignalGuidListenerRemove != null) {
            this.mLiveLaunchBiz.removeGuidListener(hySignalGuidListenerRemove);
        }
    }
}
