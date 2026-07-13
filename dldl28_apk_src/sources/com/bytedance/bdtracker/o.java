package com.bytedance.bdtracker;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0007H&¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u000e¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/bytedance/applog/alink/model/BaseData;", "", "<init>", "()V", "", "toString", "()Ljava/lang/String;", "Lorg/json/JSONObject;", "json", "", "initWithJson", "(Lorg/json/JSONObject;)V", "toJson", "()Lorg/json/JSONObject;", "", "toMap", "()Ljava/util/Map;", "Companion", "agent_liteChinaRelease"}, k = 1, mv = {1, 4, 0})
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f299a = new a(null);

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final <T extends o> T a(JSONObject jSONObject, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
            Intrinsics.checkParameterIsNotNull(clazz, "clazz");
            if (jSONObject == null) {
                return null;
            }
            T tNewInstance = clazz.getConstructor(null).newInstance(null);
            Intrinsics.checkExpressionValueIsNotNull(tNewInstance, "clazz.getConstructor().newInstance()");
            T t = tNewInstance;
            t.a(jSONObject);
            return t;
        }
    }

    public abstract JSONObject a();

    public abstract void a(JSONObject jSONObject);

    public final Map<String, String> b() {
        HashMap map = new HashMap();
        JSONObject jSONObjectA = a();
        Iterator<String> itKeys = jSONObjectA.keys();
        Intrinsics.checkExpressionValueIsNotNull(itKeys, "keys()");
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            Intrinsics.checkExpressionValueIsNotNull(key, "key");
            map.put(key, jSONObjectA.optString(key, null));
        }
        return map;
    }

    public String toString() {
        String string = a().toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "toJson().toString()");
        return string;
    }
}
