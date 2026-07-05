package com.taptap.sdk.initializer.repository.local;

import com.taptap.sdk.initializer.data.response.GateKeeper;
import kotlin.Metadata;

/* JADX INFO: compiled from: GatekeeperStorage.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/initializer/repository/local/GatekeeperStorage;", "", "clearGateKeeper", "", "getGateKeeper", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "saveGateKeeper", "value", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GatekeeperStorage {
    void clearGateKeeper();

    GateKeeper getGateKeeper();

    void saveGateKeeper(GateKeeper value);
}
