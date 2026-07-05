package com.taptap.sdk.initialize;

import com.taptap.sdk.db.DBInitializeTask;
import com.taptap.sdk.startup.task.Task;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: Sdk_tap_db_TaskFactory.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initialize/Sdk_tap_db_TaskFactory;", "", "()V", "create", "", "Lcom/taptap/sdk/startup/task/Task;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Sdk_tap_db_TaskFactory {
    public final List<Task> create() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Task("DBInitializeTask", DBInitializeTask.class, false, 0, SetsKt.setOf("com.taptap.sdk.gid.GidInitializeTask"), null, null, 96, null));
        return arrayList;
    }
}
