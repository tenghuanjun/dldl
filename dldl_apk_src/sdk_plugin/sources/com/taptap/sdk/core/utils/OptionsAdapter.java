package com.taptap.sdk.core.utils;

import com.taptap.sdk.initializer.TapTapSdkInitializer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: OptionsAdapter.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u0004*\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/core/utils/OptionsAdapter;", "", "()V", "adapt", "Lcom/taptap/sdk/initializer/TapTapSdkInitializer$Builder;", "options", "", "", "tap-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OptionsAdapter {
    public static final OptionsAdapter INSTANCE = new OptionsAdapter();

    private OptionsAdapter() {
    }

    public final TapTapSdkInitializer.Builder adapt(TapTapSdkInitializer.Builder builder, List<String> options) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        List listFilterNotNull = CollectionsKt.filterNotNull(options);
        HashMap map = new HashMap();
        Iterator it = listFilterNotNull.iterator();
        while (it.hasNext()) {
            map.putAll(JSONUtils.INSTANCE.toMap(new JSONObject((String) it.next())));
        }
        Object obj = map.get("enableToast");
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        builder.setEnableToast(bool != null ? bool.booleanValue() : true);
        Object obj2 = map.get("showSwitchAccount");
        Boolean bool2 = obj2 instanceof Boolean ? (Boolean) obj2 : null;
        builder.setShowSwitchAccount(bool2 != null ? bool2.booleanValue() : false);
        Object obj3 = map.get("useAgeRange");
        Boolean bool3 = obj3 instanceof Boolean ? (Boolean) obj3 : null;
        builder.setUseAgeRange(bool3 != null ? bool3.booleanValue() : true);
        return builder;
    }
}
