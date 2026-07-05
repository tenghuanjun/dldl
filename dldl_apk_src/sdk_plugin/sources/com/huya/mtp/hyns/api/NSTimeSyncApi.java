package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSTimeSyncProtocol;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSTimeSyncProtocol.class)
public interface NSTimeSyncApi {

    public interface TimeAdjustListener {
        void onTime(long j);
    }

    boolean addTimeAdjustListener(TimeAdjustListener timeAdjustListener);

    long getCurrentMaxOffset();

    long getEpochTime();

    String getFormattedTime(Locale locale);

    boolean removeTimeAdjustListener(TimeAdjustListener timeAdjustListener);
}
