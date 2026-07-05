package com.taptap.sdk.startup.task;

import android.content.Context;
import com.huya.mtp.http.monitor.Stat;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import kotlin.Metadata;

/* JADX INFO: compiled from: InitializeTask.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/startup/task/InitializeTask;", "", Stat.EXECUTE_KEY, "", "context", "Landroid/content/Context;", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface InitializeTask {
    void execute(Context context, TapTapSdkOptions options);
}
