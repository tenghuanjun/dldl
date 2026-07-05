package com.sq.tools.report.media;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IMediaReporter {
    String getName();

    String getRefer(Context context);

    String getUniqueId(Context context);

    void init(Context context);

    void report(String event, Map<String, String> params);

    void setCustomId(Context context, String id);

    void setUserConsent(Map<String, Boolean> consent);
}
