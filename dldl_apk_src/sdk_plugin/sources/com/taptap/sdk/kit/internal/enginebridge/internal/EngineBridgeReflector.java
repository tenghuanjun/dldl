package com.taptap.sdk.kit.internal.enginebridge.internal;

import android.app.Activity;
import android.text.TextUtils;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.enginebridge.EngineBridge;
import com.taptap.sdk.kit.internal.enginebridge.EngineBridgeCallback;
import com.taptap.sdk.kit.internal.enginebridge.EngineBridgeKt;
import com.taptap.sdk.kit.internal.enginebridge.IEngineBridgeService;
import com.taptap.sdk.kit.internal.enginebridge.annotation.EngineBridgeParams;
import com.taptap.sdk.kit.internal.enginebridge.annotation.EngineBridgeService;
import com.taptap.sdk.kit.internal.enginebridge.command.Command;
import com.taptap.sdk.kit.internal.enginebridge.exception.BridgeExceptionMessage;
import com.taptap.sdk.kit.internal.enginebridge.exception.EngineBridgeException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EngineBridgeReflector.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ-\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\r2\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u001c\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001b\u0018\u00010\r2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\r¨\u0006\u001e"}, d2 = {"Lcom/taptap/sdk/kit/internal/enginebridge/internal/EngineBridgeReflector;", "", "()V", "checkCommand", "", "command", "Lcom/taptap/sdk/kit/internal/enginebridge/command/Command;", "checkParamsMatchMethod", "", "jsonObject", "Lorg/json/JSONObject;", "checkServiceValid", "serviceClz", "Ljava/lang/Class;", "constructorCommandArgs", "", "method", "Ljava/lang/reflect/Method;", "callback", "Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridgeCallback;", "(Ljava/lang/reflect/Method;Lcom/taptap/sdk/kit/internal/enginebridge/command/Command;Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridgeCallback;)[Ljava/lang/Object;", "filterArray", "jsonArray", "Lorg/json/JSONArray;", "(Lorg/json/JSONArray;)[Ljava/lang/Object;", "findParams", "getRegisteredService", "Lcom/taptap/sdk/kit/internal/enginebridge/IEngineBridgeService;", "getValidService", "clz", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EngineBridgeReflector {
    public static final EngineBridgeReflector INSTANCE = new EngineBridgeReflector();

    private EngineBridgeReflector() {
    }

    public final boolean checkServiceValid(Class<?> serviceClz) {
        Intrinsics.checkNotNullParameter(serviceClz, "serviceClz");
        if (serviceClz.getAnnotation(EngineBridgeService.class) == null) {
            return false;
        }
        return IEngineBridgeService.class.isAssignableFrom(serviceClz);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Class<? extends IEngineBridgeService> getValidService(Class<?> clz) {
        Intrinsics.checkNotNullParameter(clz, "clz");
        Class<?>[] interfaceClz = clz.getInterfaces();
        Intrinsics.checkNotNullExpressionValue(interfaceClz, "interfaceClz");
        for (Class<? extends IEngineBridgeService> superClz : interfaceClz) {
            Intrinsics.checkNotNullExpressionValue(superClz, "superClz");
            if (checkServiceValid(superClz)) {
                return superClz;
            }
        }
        throw new EngineBridgeException(BridgeExceptionMessage.COMMAND_SERVICE_ERROR.getMessage());
    }

    public final void checkCommand(Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        if (!(command.getService().length() == 0)) {
            if (!(command.getMethod().length() == 0)) {
                return;
            }
        }
        throw new EngineBridgeException(BridgeExceptionMessage.COMMAND_PARSE_ERROR.getMessage());
    }

    public final Class<? extends IEngineBridgeService> getRegisteredService(Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        Class<? extends IEngineBridgeService> key = BridgeHolder.INSTANCE.getBridgeService(command.getService()).getKey();
        EngineBridgeService engineBridgeService = (EngineBridgeService) key.getAnnotation(EngineBridgeService.class);
        if (engineBridgeService != null && Intrinsics.areEqual(engineBridgeService.value(), command.getService())) {
            return key;
        }
        throw new EngineBridgeException(BridgeExceptionMessage.COMMAND_SERVICE_ERROR.getMessage());
    }

    public final Object[] constructorCommandArgs(Method method, Command command, EngineBridgeCallback callback) {
        JSONObject jSONObject;
        int length;
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(command, "command");
        Class<?>[] parameterTypes = method.getParameterTypes();
        int length2 = parameterTypes.length;
        Object[] objArr = new Object[length2];
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "construct command: command=" + command + ", args=" + command.getArgs());
        try {
            if (TextUtils.isEmpty(command.getArgs())) {
                jSONObject = new JSONObject();
                length = 0;
            } else {
                jSONObject = new JSONObject(command.getArgs());
                length = jSONObject.length();
            }
            int length3 = parameterTypes.length;
            for (int i = 0; i < length3; i++) {
                Class<?> cls = parameterTypes[i];
                if (Intrinsics.areEqual(cls, Activity.class)) {
                    objArr[i] = EngineBridge.INSTANCE.getCurrentActivity$tap_common_release();
                } else if (Intrinsics.areEqual(cls, EngineBridgeCallback.class)) {
                    if (callback == null) {
                        throw new EngineBridgeException(BridgeExceptionMessage.COMMAND_ARGS_ERROR.getExtraMessage("callback is null"));
                    }
                    objArr[i] = callback;
                } else {
                    objArr[i] = findParams(jSONObject, method);
                }
                length++;
            }
            TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "construct command over: command=" + command + ", args=" + command.getArgs());
            if (!checkParamsMatchMethod(jSONObject) || length != length2) {
                return new Object[0];
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return objArr;
    }

    private final boolean checkParamsMatchMethod(JSONObject jsonObject) {
        return jsonObject.length() == 0;
    }

    public final Object findParams(JSONObject jsonObject, Method method) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(method, "method");
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "findParams: jsonObject=" + jsonObject + ", method=" + method);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "parameterAnnotations");
        int length = parameterAnnotations.length;
        int i = 0;
        while (true) {
            Object obj = null;
            if (i >= length) {
                return null;
            }
            Annotation[] annotations = parameterAnnotations[i];
            Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
            for (Annotation annotation : annotations) {
                if (annotation instanceof EngineBridgeParams) {
                    EngineBridgeParams engineBridgeParams = (EngineBridgeParams) annotation;
                    if (jsonObject.has(engineBridgeParams.value())) {
                        Object obj2 = jsonObject.get(engineBridgeParams.value());
                        jsonObject.remove(engineBridgeParams.value());
                        if (obj2 == JSONObject.NULL) {
                            TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, " method " + method.getDeclaringClass().getName() + '.' + method.getName() + " param " + engineBridgeParams.value() + " convert jsonNull to null");
                        } else {
                            obj = obj2;
                        }
                        return obj instanceof JSONArray ? filterArray((JSONArray) obj) : obj;
                    }
                }
            }
            i++;
        }
    }

    private final Object[] filterArray(JSONArray jsonArray) throws JSONException {
        int length = jsonArray.length();
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "filterArray: jsonArray=" + jsonArray);
        Class<?> cls = jsonArray.get(0).getClass();
        if (Intrinsics.areEqual(cls, Boolean.TYPE)) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < length; i++) {
                arrayList.add(Boolean.valueOf(jsonArray.getBoolean(i)));
            }
            return arrayList.toArray(new Boolean[0]);
        }
        if (Intrinsics.areEqual(cls, Integer.TYPE)) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < length; i2++) {
                arrayList2.add(Integer.valueOf(jsonArray.getInt(i2)));
            }
            return arrayList2.toArray(new Integer[0]);
        }
        if (Intrinsics.areEqual(cls, Long.TYPE)) {
            ArrayList arrayList3 = new ArrayList();
            for (int i3 = 0; i3 < length; i3++) {
                arrayList3.add(Long.valueOf(jsonArray.getLong(i3)));
            }
            return arrayList3.toArray(new Long[0]);
        }
        if (Intrinsics.areEqual(cls, Float.TYPE)) {
            ArrayList arrayList4 = new ArrayList();
            for (int i4 = 0; i4 < length; i4++) {
                arrayList4.add(Float.valueOf((float) jsonArray.getDouble(i4)));
            }
            return arrayList4.toArray(new Float[0]);
        }
        if (Intrinsics.areEqual(cls, Double.TYPE)) {
            ArrayList arrayList5 = new ArrayList();
            for (int i5 = 0; i5 < length; i5++) {
                arrayList5.add(Double.valueOf(jsonArray.getDouble(i5)));
            }
            return arrayList5.toArray(new Double[0]);
        }
        if (Intrinsics.areEqual(cls, String.class)) {
            ArrayList arrayList6 = new ArrayList();
            for (int i6 = 0; i6 < length; i6++) {
                String string = jsonArray.getString(i6);
                Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
                arrayList6.add(string);
            }
            return arrayList6.toArray(new String[0]);
        }
        throw new EngineBridgeException(BridgeExceptionMessage.COMMAND_ARGS_ERROR.getExtraMessage("数组类型的参数必须为基础数据类型!"));
    }
}
