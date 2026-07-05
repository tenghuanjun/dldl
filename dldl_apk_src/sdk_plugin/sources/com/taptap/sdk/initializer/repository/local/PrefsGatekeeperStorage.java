package com.taptap.sdk.initializer.repository.local;

import android.content.SharedPreferences;
import com.taptap.sdk.base.utils.prefs.SharedPreferenceDelegate;
import com.taptap.sdk.initializer.data.response.GateKeeper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: PrefsGatekeeperStorage.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/taptap/sdk/initializer/repository/local/PrefsGatekeeperStorage;", "Lcom/taptap/sdk/initializer/repository/local/GatekeeperStorage;", "prefs", "Landroid/content/SharedPreferences;", "json", "Lkotlinx/serialization/json/Json;", "(Landroid/content/SharedPreferences;Lkotlinx/serialization/json/Json;)V", "<set-?>", "", "jsonStr", "getJsonStr", "()Ljava/lang/String;", "setJsonStr", "(Ljava/lang/String;)V", "jsonStr$delegate", "Lcom/taptap/sdk/base/utils/prefs/SharedPreferenceDelegate;", "clearGateKeeper", "", "getGateKeeper", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "saveGateKeeper", "value", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PrefsGatekeeperStorage implements GatekeeperStorage {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PrefsGatekeeperStorage.class, "jsonStr", "getJsonStr()Ljava/lang/String;", 0))};
    private static final String KEY_GATEKEEPER = "key_gatekeeper";
    public static final String PREFS_NAME = "";
    private final Json json;

    /* JADX INFO: renamed from: jsonStr$delegate, reason: from kotlin metadata */
    private final SharedPreferenceDelegate jsonStr;

    public PrefsGatekeeperStorage(SharedPreferences prefs, Json json) {
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.jsonStr = new SharedPreferenceDelegate(prefs, KEY_GATEKEEPER, "");
    }

    private final String getJsonStr() {
        return (String) this.jsonStr.getValue(this, $$delegatedProperties[0]);
    }

    private final void setJsonStr(String str) {
        this.jsonStr.setValue(this, $$delegatedProperties[0], str);
    }

    @Override // com.taptap.sdk.initializer.repository.local.GatekeeperStorage
    public GateKeeper getGateKeeper() {
        try {
            if (!(getJsonStr().length() > 0)) {
                return null;
            }
            Json json = this.json;
            String jsonStr = getJsonStr();
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(GateKeeper.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return (GateKeeper) json.decodeFromString(kSerializerSerializer, jsonStr);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.taptap.sdk.initializer.repository.local.GatekeeperStorage
    public void saveGateKeeper(GateKeeper value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            Json json = this.json;
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(GateKeeper.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            setJsonStr(json.encodeToString(kSerializerSerializer, value));
        } catch (Exception unused) {
        }
    }

    @Override // com.taptap.sdk.initializer.repository.local.GatekeeperStorage
    public void clearGateKeeper() {
        setJsonStr("");
    }
}
