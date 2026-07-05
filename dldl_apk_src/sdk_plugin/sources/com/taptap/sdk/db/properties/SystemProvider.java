package com.taptap.sdk.db.properties;

import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import com.taptap.sdk.kit.internal.identifier.TapIdentifierUtil;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/db/properties/SystemProvider;", "", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "provide", "", "", "event", "Lcom/taptap/sdk/db/data/model/Event;", "userId", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SystemProvider {
    private final TapTapSdkOptions options;

    public SystemProvider(TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
    }

    public final Map<String, Object> provide(Event event, String userId) {
        Intrinsics.checkNotNullParameter(event, "event");
        HashMap map = new HashMap();
        Pair pair = TuplesKt.to("type", event.getType());
        map.put(pair.getFirst(), pair.getSecond());
        Pair pair2 = TuplesKt.to("name", event.getName());
        map.put(pair2.getFirst(), pair2.getSecond());
        Pair pair3 = TuplesKt.to("client_id", this.options.getClientId());
        map.put(pair3.getFirst(), pair3.getSecond());
        Pair pair4 = TuplesKt.to(Common.System.DEVICE_ID, TapIdentifierUtil.INSTANCE.getDeviceId(this.options.getContext()));
        map.put(pair4.getFirst(), pair4.getSecond());
        Pair pair5 = TuplesKt.to(Common.System.USER_ID, userId);
        map.put(pair5.getFirst(), pair5.getSecond());
        return map;
    }
}
