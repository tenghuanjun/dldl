package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hysignal.listener.TimeAdjustListener;
import com.huya.hysignal.wrapper.business.TimeSyncBiz;
import com.huya.mtp.hyns.api.NSTimeSyncApi;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyTimeSyncImpl implements NSTimeSyncApi {
    private TimeSyncBiz mTimeSyncBiz = Hal.getTimeSyncBiz();

    @Override // com.huya.mtp.hyns.api.NSTimeSyncApi
    public long getEpochTime() {
        return this.mTimeSyncBiz.getEpochTime();
    }

    @Override // com.huya.mtp.hyns.api.NSTimeSyncApi
    public String getFormattedTime(Locale locale) {
        return this.mTimeSyncBiz.getFormattedTime(locale);
    }

    @Override // com.huya.mtp.hyns.api.NSTimeSyncApi
    public boolean addTimeAdjustListener(final NSTimeSyncApi.TimeAdjustListener timeAdjustListener) {
        return this.mTimeSyncBiz.addTimeAdjustListener(new TimeAdjustListener() { // from class: com.huya.mtp.hyns.hysignal.HyTimeSyncImpl.1
            @Override // com.huya.hysignal.listener.TimeAdjustListener
            public void onTime(long j) {
                timeAdjustListener.onTime(j);
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSTimeSyncApi
    public boolean removeTimeAdjustListener(final NSTimeSyncApi.TimeAdjustListener timeAdjustListener) {
        return this.mTimeSyncBiz.removeTimeAdjustListener(new TimeAdjustListener() { // from class: com.huya.mtp.hyns.hysignal.HyTimeSyncImpl.2
            @Override // com.huya.hysignal.listener.TimeAdjustListener
            public void onTime(long j) {
                timeAdjustListener.onTime(j);
            }
        });
    }

    @Override // com.huya.mtp.hyns.api.NSTimeSyncApi
    public long getCurrentMaxOffset() {
        return this.mTimeSyncBiz.getCurrentMaxOffset();
    }
}
