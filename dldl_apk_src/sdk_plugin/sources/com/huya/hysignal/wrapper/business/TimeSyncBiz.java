package com.huya.hysignal.wrapper.business;

import com.huya.hysignal.listener.TimeAdjustListener;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface TimeSyncBiz {
    boolean addTimeAdjustListener(TimeAdjustListener timeAdjustListener);

    long getCurrentMaxOffset();

    long getEpochTime();

    String getFormattedTime(Locale locale);

    boolean removeTimeAdjustListener(TimeAdjustListener timeAdjustListener);
}
