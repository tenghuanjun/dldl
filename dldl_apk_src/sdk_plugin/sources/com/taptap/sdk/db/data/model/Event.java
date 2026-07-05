package com.taptap.sdk.db.data.model;

import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import com.taptap.sdk.db.constant.Common;
import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import java.io.ObjectStreamException;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: Event.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 '2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0002'(B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003J=\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0015HÖ\u0001J\u001f\u0010!\u001a\u00020\u00002\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000#¢\u0006\u0002\b$J\t\u0010%\u001a\u00020\u0004HÖ\u0001J\u0006\u0010&\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006)"}, d2 = {"Lcom/taptap/sdk/db/data/model/Event;", "Lcom/taptap/sdk/db/data/model/SerializableEvent;", "", SqTrackCommonKey.id, "", "name", "type", Common.Predefined.SUB_TIMESTAMP, "", Common.Predefined.PROPERTIES, "Lorg/json/JSONObject;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLorg/json/JSONObject;)V", "getId", "()Ljava/lang/String;", BillingClientConstants.METHOD_GET_PRODUCT_NAME, "getProperties", "()Lorg/json/JSONObject;", "getTimestamp", "()J", "getType", "compareTo", "", "other", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "", "hashCode", "reduce", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "writeReplace", "Companion", "SerializationProxy", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class Event implements SerializableEvent, Comparable<Event> {
    private static final long serialVersionUID = 20230323002L;
    private final String id;
    private final String name;
    private final JSONObject properties;
    private final long timestamp;
    private final String type;

    public static /* synthetic */ Event copy$default(Event event, String str, String str2, String str3, long j, JSONObject jSONObject, int i, Object obj) {
        if ((i & 1) != 0) {
            str = event.id;
        }
        if ((i & 2) != 0) {
            str2 = event.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = event.type;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            j = event.timestamp;
        }
        long j2 = j;
        if ((i & 16) != 0) {
            jSONObject = event.properties;
        }
        return event.copy(str, str4, str5, j2, jSONObject);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final JSONObject getProperties() {
        return this.properties;
    }

    public final Event copy(String id, String name, String type, long timestamp, JSONObject properties) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(properties, "properties");
        return new Event(id, name, type, timestamp, properties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Event)) {
            return false;
        }
        Event event = (Event) other;
        return Intrinsics.areEqual(this.id, event.id) && Intrinsics.areEqual(this.name, event.name) && Intrinsics.areEqual(this.type, event.type) && this.timestamp == event.timestamp && Intrinsics.areEqual(this.properties, event.properties);
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.name;
        return ((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.type.hashCode()) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.timestamp)) * 31) + this.properties.hashCode();
    }

    public String toString() {
        return "Event(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ", timestamp=" + this.timestamp + ", properties=" + this.properties + ')';
    }

    public Event(String id, String str, String type, long j, JSONObject properties) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.id = id;
        this.name = str;
        this.type = type;
        this.timestamp = j;
        this.properties = properties;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final JSONObject getProperties() {
        return this.properties;
    }

    @Override // java.lang.Comparable
    public int compareTo(Event other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return Intrinsics.compare(this.timestamp, other.timestamp);
    }

    public final Object writeReplace() throws ObjectStreamException {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.type;
        long j = this.timestamp;
        String string = this.properties.toString();
        Intrinsics.checkNotNullExpressionValue(string, "properties.toString()");
        return new SerializationProxy(str, str2, str3, j, string);
    }

    public final Event reduce(Function1<? super Event, Event> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(copy$default(this, null, null, null, 0L, null, 31, null));
    }

    /* JADX INFO: compiled from: Event.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/db/data/model/Event$SerializationProxy;", "Ljava/io/Serializable;", SqTrackCommonKey.id, "", "name", "type", Common.Predefined.SUB_TIMESTAMP, "", "propertiesJsonStr", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "readResolve", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SerializationProxy implements Serializable {
        private final String id;
        private final String name;
        private final String propertiesJsonStr;
        private final long timestamp;
        private final String type;

        public SerializationProxy(String id, String str, String type, long j, String propertiesJsonStr) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(propertiesJsonStr, "propertiesJsonStr");
            this.id = id;
            this.name = str;
            this.type = type;
            this.timestamp = j;
            this.propertiesJsonStr = propertiesJsonStr;
        }

        public final Object readResolve() throws ObjectStreamException {
            return new Event(this.id, this.name, this.type, this.timestamp, new JSONObject(this.propertiesJsonStr));
        }
    }
}
