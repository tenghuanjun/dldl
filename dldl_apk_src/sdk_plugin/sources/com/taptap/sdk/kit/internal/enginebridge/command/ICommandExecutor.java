package com.taptap.sdk.kit.internal.enginebridge.command;

import com.huya.mtp.http.monitor.Stat;
import com.taptap.sdk.kit.internal.enginebridge.EngineBridgeCallback;
import kotlin.Metadata;

/* JADX INFO: compiled from: ICommandExecutor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/kit/internal/enginebridge/command/ICommandExecutor;", "", Stat.EXECUTE_KEY, "", "command", "Lcom/taptap/sdk/kit/internal/enginebridge/command/Command;", "callback", "Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridgeCallback;", "executeAsync", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ICommandExecutor {
    void execute(Command command, EngineBridgeCallback callback);

    Object executeAsync(Command command);
}
