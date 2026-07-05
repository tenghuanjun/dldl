package com.taptap.sdk.db;

import android.content.Context;
import com.huya.mtp.http.monitor.Stat;
import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.interceptor.DeviceEventInterceptor;
import com.taptap.sdk.db.interceptor.EventVerifyInterceptor;
import com.taptap.sdk.db.interceptor.TrackEventInterceptor;
import com.taptap.sdk.db.interceptor.UserEventInterceptor;
import com.taptap.sdk.db.processor.EventProcessor;
import com.taptap.sdk.db.properties.PredefinedProvider;
import com.taptap.sdk.db.properties.SystemProvider;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import com.taptap.sdk.startup.task.InitializeTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: DBInitializeTask.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/db/DBInitializeTask;", "Lcom/taptap/sdk/startup/task/InitializeTask;", "()V", Stat.EXECUTE_KEY, "", "context", "Landroid/content/Context;", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DBInitializeTask implements InitializeTask {
    @Override // com.taptap.sdk.startup.task.InitializeTask
    public void execute(Context context, TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        new TapDB.Builder(options).configuration(new EventProcessor.Config(options.getContext(), "TapEvent_persistedData", 10485760L, 1000, 200, 50, 15000L, CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))))).addInterceptor(new EventVerifyInterceptor()).addInterceptor(new TrackEventInterceptor(options)).addInterceptor(new DeviceEventInterceptor(options)).addInterceptor(new UserEventInterceptor(options)).setSystemProvider(new SystemProvider(options)).setPredefinedProvider(new PredefinedProvider(options)).build().initialize();
    }
}
