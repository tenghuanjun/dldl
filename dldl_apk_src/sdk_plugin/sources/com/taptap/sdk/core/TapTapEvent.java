package com.taptap.sdk.core;

import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.EventType;
import com.taptap.sdk.db.data.model.WrapEvent;
import com.taptap.sdk.db.event.DeviceAddEvent;
import com.taptap.sdk.db.event.DeviceInitializeEvent;
import com.taptap.sdk.db.event.DeviceLoginEvent;
import com.taptap.sdk.db.event.DeviceUpdateEvent;
import com.taptap.sdk.db.event.PurchasedEvent;
import com.taptap.sdk.db.event.UserAddEvent;
import com.taptap.sdk.db.event.UserInitializeEvent;
import com.taptap.sdk.db.event.UserLoginEvent;
import com.taptap.sdk.db.event.UserUpdateEvent;
import com.taptap.sdk.db.utils.JSONObjectUtils;
import com.taptap.sdk.db.utils.UserIdVerifyTools;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.identifier.TapIdentifierUtil;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapTapEvent.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001$B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0007J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\b\u0010\n\u001a\u00020\u0004H\u0007J!\u0010\u000b\u001a\u00020\u00042\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\r\"\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0004H\u0007J\u0012\u0010\u0010\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010\u0011\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010\u0012\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0014\u001a\u00020\u0004H\u0007J\u001c\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0012\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\u0012\u0010\u001d\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010!\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010\"\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010#\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¨\u0006%"}, d2 = {"Lcom/taptap/sdk/core/TapTapEvent;", "", "()V", "addCommon", "", "key", "", "value", Common.Predefined.PROPERTIES, "Lorg/json/JSONObject;", "clearAllCommonProperties", "clearCommonProperties", "keys", "", "([Ljava/lang/String;)V", "clearUser", "deviceAdd", "deviceInitialize", "deviceUpdate", "getDeviceId", "logDeviceLoginEvent", "logEvent", "name", "logPurchasedEvent", "event", "Lcom/taptap/sdk/core/TapTapPurchasedEvent;", "registerDynamicProperties", "dynamicProperties", "Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;", "setOAID", "oaid", "setUserId", "userId", "userAdd", "userInitialize", "userUpdate", "TapEventDynamicProperties", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapTapEvent {
    public static final TapTapEvent INSTANCE = new TapTapEvent();

    /* JADX INFO: compiled from: TapTapEvent.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;", "", "getDynamicProperties", "Lorg/json/JSONObject;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface TapEventDynamicProperties {
        JSONObject getDynamicProperties();
    }

    private TapTapEvent() {
    }

    public static /* synthetic */ void logEvent$default(String str, JSONObject jSONObject, int i, Object obj) {
        if ((i & 2) != 0) {
            jSONObject = null;
        }
        logEvent(str, jSONObject);
    }

    @JvmStatic
    public static final void logEvent(String name, JSONObject properties) {
        Intrinsics.checkNotNullParameter(name, "name");
        TapDB companion = TapDB.INSTANCE.getInstance();
        WrapEvent.Builder builder = new WrapEvent.Builder();
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        String value = EventType.TRACK.getValue();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (properties == null) {
            properties = new JSONObject();
        }
        companion.submitEvent(builder.setEvent(new Event(string, name, value, jCurrentTimeMillis, properties)).setEventType(EventType.TRACK).isAutomatically(false).isCustomEvent(true).build());
    }

    @JvmStatic
    public static final void logPurchasedEvent(TapTapPurchasedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        new PurchasedEvent.Builder(event.getProperties()).isAutomatically(false).setOrderId(event.getOrderId()).setProductName(event.getProductName()).setAmount(event.getAmount()).setCurrencyType(event.getCurrencyType()).setPaymentMethod(event.getPaymentMethod()).build().submit();
    }

    public static /* synthetic */ void setUserId$default(String str, JSONObject jSONObject, int i, Object obj) {
        if ((i & 2) != 0) {
            jSONObject = null;
        }
        setUserId(str, jSONObject);
    }

    @JvmStatic
    public static final void setUserId(String userId, JSONObject properties) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        try {
            UserIdVerifyTools.INSTANCE.verify(userId);
            TapDB.INSTANCE.getInstance().setUserId(userId);
            new UserLoginEvent.Builder(properties).isAutomatically(true).build().submit();
            new DeviceUpdateEvent.Builder(MapsKt.hashMapOf(TuplesKt.to("has_user", true))).isAutomatically(false).build().submit();
            TapIdentifierUtil.INSTANCE.setGameUserId(userId);
        } catch (Exception e) {
            TapLogger.loge$default(null, null, e, 3, null);
        }
    }

    @JvmStatic
    public static final void clearUser() {
        TapDB.INSTANCE.getInstance().setUserId(null);
        TapIdentifierUtil.INSTANCE.setGameUserId(null);
    }

    public final String getDeviceId() {
        return TapIdentifierUtil.INSTANCE.getDeviceId(TapDB.INSTANCE.getOptions$tap_db_release().getContext());
    }

    @JvmStatic
    public static final void deviceInitialize(JSONObject properties) {
        new DeviceInitializeEvent.Builder(properties).isAutomatically(false).build().submit();
    }

    @JvmStatic
    public static final void deviceUpdate(JSONObject properties) {
        Map<String, Object> mapEmptyMap;
        if (properties == null || (mapEmptyMap = JSONObjectUtils.INSTANCE.toMap(properties)) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        new DeviceUpdateEvent.Builder(mapEmptyMap).isAutomatically(false).build().submit();
    }

    @JvmStatic
    public static final void deviceAdd(JSONObject properties) {
        new DeviceAddEvent.Builder(properties).isAutomatically(false).build().submit();
    }

    @JvmStatic
    public static final void userInitialize(JSONObject properties) {
        new UserInitializeEvent.Builder(properties).isAutomatically(false).build().submit();
    }

    @JvmStatic
    public static final void userUpdate(JSONObject properties) {
        new UserUpdateEvent.Builder(properties).isAutomatically(false).build().submit();
    }

    @JvmStatic
    public static final void userAdd(JSONObject properties) {
        new UserAddEvent.Builder(properties).isAutomatically(false).build().submit();
    }

    @JvmStatic
    public static final void addCommon(JSONObject properties) {
        Map<String, Object> mapEmptyMap;
        TapDB companion = TapDB.INSTANCE.getInstance();
        if (properties == null || (mapEmptyMap = JSONObjectUtils.INSTANCE.toMap(properties)) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        companion.addCommon(mapEmptyMap);
    }

    @JvmStatic
    public static final void addCommon(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        TapDB.INSTANCE.getInstance().addCommon(MapsKt.mapOf(TuplesKt.to(key, value)));
    }

    @JvmStatic
    public static final void clearCommonProperties(String... keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        TapDB.INSTANCE.getInstance().clearCommonProperties(ArraysKt.toSet(keys));
    }

    @JvmStatic
    public static final void clearAllCommonProperties() {
        TapDB.INSTANCE.getInstance().clearAllCommonProperties();
    }

    @JvmStatic
    public static final void registerDynamicProperties(TapEventDynamicProperties dynamicProperties) {
        TapDB.INSTANCE.getInstance().registerDynamicPropertiesGetter(dynamicProperties);
    }

    @JvmStatic
    public static final void setOAID(String oaid) {
        TapDB.INSTANCE.getInstance().setOAID(oaid);
    }

    @JvmStatic
    public static final void logDeviceLoginEvent() {
        new DeviceLoginEvent.Builder(TapDB.INSTANCE.getOptions$tap_db_release().getProperties()).isAutomatically(true).build().submitNow();
    }
}
