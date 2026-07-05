package com.taptap.sdk.kit.internal.enginebridge;

import android.app.Activity;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.enginebridge.command.Command;
import com.taptap.sdk.kit.internal.enginebridge.command.CommandExecutor;
import com.taptap.sdk.kit.internal.enginebridge.exception.EngineBridgeException;
import com.taptap.sdk.kit.internal.enginebridge.internal.BridgeHolder;
import com.taptap.sdk.kit.internal.enginebridge.internal.EngineBridgeReflector;
import com.taptap.sdk.kit.internal.extensions.GameEngineExtKt;
import com.taptap.sdk.kit.internal.json.TapJson;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: EngineBridge.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b\u0011J \u0010\u0012\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0011\u0010\u0017\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086 J\u001a\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\tH\u0002J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0002J \u0010\u001d\u001a\u00020\u00072\u000e\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020 H\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridge;", "Lcom/taptap/sdk/kit/internal/enginebridge/IEngineBridge;", "()V", "activityRef", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "execCommand", "", "command", "", "callback", "Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridgeCallback;", "execCommandAsync", "execCommandAsyncInternal", "Lcom/taptap/sdk/kit/internal/enginebridge/command/Command;", "execCommandInternal", "getCurrentActivity", "getCurrentActivity$tap_common_release", "handleResult", "result", "Lcom/taptap/sdk/kit/internal/enginebridge/EngineBridgeResult;", "init", "activity", "nativeOnResult", "processUnityEngineCommand", "cmdStr", "processUnityEngineCommandAsync", "processUnrealEngineCommand", "processUnrealEngineCommandAsync", "registerService", "serviceClz", "Ljava/lang/Class;", "Lcom/taptap/sdk/kit/internal/enginebridge/IEngineBridgeService;", "service", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EngineBridge implements IEngineBridge {
    public static final EngineBridge INSTANCE = new EngineBridge();
    private static WeakReference<Activity> activityRef;

    public final native synchronized void nativeOnResult(String command) throws EngineBridgeException;

    private EngineBridge() {
    }

    public final Activity getCurrentActivity$tap_common_release() {
        WeakReference<Activity> weakReference = activityRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.taptap.sdk.kit.internal.enginebridge.IEngineBridge
    public void init(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activityRef = new WeakReference<>(activity);
    }

    @Override // com.taptap.sdk.kit.internal.enginebridge.IEngineBridge
    public void execCommand(String command, EngineBridgeCallback callback) {
        Intrinsics.checkNotNullParameter(command, "command");
        if (GameEngineExtKt.isUnrealEngine()) {
            processUnrealEngineCommand(command);
        } else {
            if (!GameEngineExtKt.isUnityEngine()) {
                throw new EngineBridgeException("unknown engine");
            }
            processUnityEngineCommand(command, callback);
        }
    }

    @Override // com.taptap.sdk.kit.internal.enginebridge.IEngineBridge
    public String execCommandAsync(String command) {
        Intrinsics.checkNotNullParameter(command, "command");
        if (GameEngineExtKt.isUnrealEngine()) {
            return processUnrealEngineCommandAsync(command);
        }
        if (GameEngineExtKt.isUnityEngine()) {
            return processUnityEngineCommandAsync(command);
        }
        throw new EngineBridgeException("unknown engine");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060 A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #0 {all -> 0x007d, blocks: (B:3:0x0017, B:5:0x0023, B:16:0x005b, B:19:0x0060, B:12:0x0031, B:15:0x0052), top: B:27:0x0017, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void processUnityEngineCommand(java.lang.String r11, com.taptap.sdk.kit.internal.enginebridge.EngineBridgeCallback r12) {
        /*
            r10 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "processUnityEngineCommand: "
            r0.append(r1)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TAP_ENGINE_BRIDGE"
            com.taptap.sdk.kit.internal.TapLogger.logi(r1, r0)
            r0 = 0
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L7d
            r2 = r10
            com.taptap.sdk.kit.internal.enginebridge.EngineBridge r2 = (com.taptap.sdk.kit.internal.enginebridge.EngineBridge) r2     // Catch: java.lang.Throwable -> L7d
            com.taptap.sdk.kit.internal.json.TapJson r3 = com.taptap.sdk.kit.internal.json.TapJson.INSTANCE     // Catch: java.lang.Throwable -> L7d
            r4 = r11
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch: java.lang.Throwable -> L7d
            if (r4 == 0) goto L2c
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L7d
            if (r4 != 0) goto L2a
            goto L2c
        L2a:
            r4 = 0
            goto L2d
        L2c:
            r4 = 1
        L2d:
            if (r4 == 0) goto L31
        L2f:
            r3 = r0
            goto L5b
        L31:
            kotlinx.serialization.json.Json r3 = r3.getJson()     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            kotlinx.serialization.StringFormat r3 = (kotlinx.serialization.StringFormat) r3     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            kotlinx.serialization.modules.SerializersModule r4 = r3.getSerializersModule()     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            java.lang.Class<com.taptap.sdk.kit.internal.enginebridge.command.Command> r5 = com.taptap.sdk.kit.internal.enginebridge.command.Command.class
            kotlin.reflect.KType r5 = kotlin.jvm.internal.Reflection.typeOf(r5)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            kotlinx.serialization.KSerializer r4 = kotlinx.serialization.SerializersKt.serializer(r4, r5)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            java.lang.String r5 = "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            java.lang.Object r3 = r3.decodeFromString(r4, r11)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L7d
            goto L5b
        L51:
            r3 = move-exception
            java.lang.String r4 = "TapJson"
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch: java.lang.Throwable -> L7d
            r5 = 2
            com.taptap.sdk.kit.internal.TapLogger.loge$default(r4, r0, r3, r5, r0)     // Catch: java.lang.Throwable -> L7d
            goto L2f
        L5b:
            com.taptap.sdk.kit.internal.enginebridge.command.Command r3 = (com.taptap.sdk.kit.internal.enginebridge.command.Command) r3     // Catch: java.lang.Throwable -> L7d
            if (r3 != 0) goto L60
            return
        L60:
            kotlinx.coroutines.CoroutineScope r4 = kotlinx.coroutines.CoroutineScopeKt.MainScope()     // Catch: java.lang.Throwable -> L7d
            kotlinx.coroutines.MainCoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Throwable -> L7d
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5     // Catch: java.lang.Throwable -> L7d
            r6 = 0
            com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnityEngineCommand$1$1 r7 = new com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnityEngineCommand$1$1     // Catch: java.lang.Throwable -> L7d
            r7.<init>(r2, r3, r12, r0)     // Catch: java.lang.Throwable -> L7d
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch: java.lang.Throwable -> L7d
            r8 = 2
            r9 = 0
            kotlinx.coroutines.Job r12 = kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r12 = kotlin.Result.m52constructorimpl(r12)     // Catch: java.lang.Throwable -> L7d
            goto L88
        L7d:
            r12 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r12 = kotlin.ResultKt.createFailure(r12)
            java.lang.Object r12 = kotlin.Result.m52constructorimpl(r12)
        L88:
            java.lang.Throwable r12 = kotlin.Result.m55exceptionOrNullimpl(r12)
            if (r12 == 0) goto Laf
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "execUnityCommand failed: cmd="
            r2.append(r3)
            r2.append(r11)
            java.lang.String r11 = ", cause="
            r2.append(r11)
            java.lang.String r11 = r12.getMessage()
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r12 = 4
            com.taptap.sdk.kit.internal.TapLogger.loge$default(r1, r11, r0, r12, r0)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.enginebridge.EngineBridge.processUnityEngineCommand(java.lang.String, com.taptap.sdk.kit.internal.enginebridge.EngineBridgeCallback):void");
    }

    private final String processUnityEngineCommandAsync(String cmdStr) {
        Object objDecodeFromString;
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "processUnityEngineCommandAsync: " + cmdStr);
        try {
            Result.Companion companion = Result.INSTANCE;
            EngineBridge engineBridge = this;
            TapJson tapJson = TapJson.INSTANCE;
            String str = cmdStr;
            if (str == null || str.length() == 0) {
                objDecodeFromString = null;
            } else {
                try {
                    Json json = tapJson.getJson();
                    KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Command.class));
                    Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                    objDecodeFromString = json.decodeFromString(kSerializerSerializer, cmdStr);
                } catch (Exception e) {
                    TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
                    objDecodeFromString = null;
                }
            }
            Command command = (Command) objDecodeFromString;
            return command == null ? "" : engineBridge.execCommandAsyncInternal(command);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Throwable thM55exceptionOrNullimpl = Result.m55exceptionOrNullimpl(Result.m52constructorimpl(ResultKt.createFailure(th)));
            if (thM55exceptionOrNullimpl != null) {
                TapLogger.loge$default(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execUnityCommand failed: cmd=" + cmdStr + ", cause=" + thM55exceptionOrNullimpl.getMessage(), null, 4, null);
            }
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0065 A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #1 {all -> 0x007d, blocks: (B:3:0x0017, B:5:0x0028, B:16:0x0060, B:19:0x0065, B:12:0x0036, B:15:0x0057), top: B:29:0x0017, inners: #0 }] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnrealEngineCommand$1$callback$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void processUnrealEngineCommand(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "processUnrealEngineCommand: "
            r0.append(r1)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TAP_ENGINE_BRIDGE"
            com.taptap.sdk.kit.internal.TapLogger.logi(r1, r0)
            r0 = 0
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L7d
            r2 = r11
            com.taptap.sdk.kit.internal.enginebridge.EngineBridge r2 = (com.taptap.sdk.kit.internal.enginebridge.EngineBridge) r2     // Catch: java.lang.Throwable -> L7d
            com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnrealEngineCommand$1$callback$1 r3 = new com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnrealEngineCommand$1$callback$1     // Catch: java.lang.Throwable -> L7d
            r3.<init>()     // Catch: java.lang.Throwable -> L7d
            com.taptap.sdk.kit.internal.json.TapJson r4 = com.taptap.sdk.kit.internal.json.TapJson.INSTANCE     // Catch: java.lang.Throwable -> L7d
            r5 = r12
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch: java.lang.Throwable -> L7d
            if (r5 == 0) goto L31
            int r5 = r5.length()     // Catch: java.lang.Throwable -> L7d
            if (r5 != 0) goto L2f
            goto L31
        L2f:
            r5 = 0
            goto L32
        L31:
            r5 = 1
        L32:
            if (r5 == 0) goto L36
        L34:
            r4 = r0
            goto L60
        L36:
            kotlinx.serialization.json.Json r4 = r4.getJson()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            kotlinx.serialization.StringFormat r4 = (kotlinx.serialization.StringFormat) r4     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            kotlinx.serialization.modules.SerializersModule r5 = r4.getSerializersModule()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            java.lang.Class<com.taptap.sdk.kit.internal.enginebridge.command.Command> r6 = com.taptap.sdk.kit.internal.enginebridge.command.Command.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf(r6)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            kotlinx.serialization.KSerializer r5 = kotlinx.serialization.SerializersKt.serializer(r5, r6)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            java.lang.String r6 = "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r6)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            java.lang.Object r4 = r4.decodeFromString(r5, r12)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L7d
            goto L60
        L56:
            r4 = move-exception
            java.lang.String r5 = "TapJson"
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch: java.lang.Throwable -> L7d
            r6 = 2
            com.taptap.sdk.kit.internal.TapLogger.loge$default(r5, r0, r4, r6, r0)     // Catch: java.lang.Throwable -> L7d
            goto L34
        L60:
            com.taptap.sdk.kit.internal.enginebridge.command.Command r4 = (com.taptap.sdk.kit.internal.enginebridge.command.Command) r4     // Catch: java.lang.Throwable -> L7d
            if (r4 != 0) goto L65
            return
        L65:
            kotlinx.coroutines.CoroutineScope r5 = kotlinx.coroutines.CoroutineScopeKt.MainScope()     // Catch: java.lang.Throwable -> L7d
            r6 = 0
            r7 = 0
            com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnrealEngineCommand$1$1 r8 = new com.taptap.sdk.kit.internal.enginebridge.EngineBridge$processUnrealEngineCommand$1$1     // Catch: java.lang.Throwable -> L7d
            r8.<init>(r2, r4, r3, r0)     // Catch: java.lang.Throwable -> L7d
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch: java.lang.Throwable -> L7d
            r9 = 3
            r10 = 0
            kotlinx.coroutines.Job r2 = kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r2 = kotlin.Result.m52constructorimpl(r2)     // Catch: java.lang.Throwable -> L7d
            goto L88
        L7d:
            r2 = move-exception
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r2 = kotlin.Result.m52constructorimpl(r2)
        L88:
            java.lang.Throwable r2 = kotlin.Result.m55exceptionOrNullimpl(r2)
            if (r2 == 0) goto Laf
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "execUnrealCommand failed: cmd="
            r3.append(r4)
            r3.append(r12)
            java.lang.String r12 = ", cause="
            r3.append(r12)
            java.lang.String r12 = r2.getMessage()
            r3.append(r12)
            java.lang.String r12 = r3.toString()
            r2 = 4
            com.taptap.sdk.kit.internal.TapLogger.loge$default(r1, r12, r0, r2, r0)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.enginebridge.EngineBridge.processUnrealEngineCommand(java.lang.String):void");
    }

    private final String processUnrealEngineCommandAsync(String cmdStr) {
        Object objDecodeFromString;
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "processUnrealEngineCommandAsync: " + cmdStr);
        try {
            Result.Companion companion = Result.INSTANCE;
            EngineBridge engineBridge = this;
            TapJson tapJson = TapJson.INSTANCE;
            String str = cmdStr;
            if (str == null || str.length() == 0) {
                objDecodeFromString = null;
            } else {
                try {
                    Json json = tapJson.getJson();
                    KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Command.class));
                    Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                    objDecodeFromString = json.decodeFromString(kSerializerSerializer, cmdStr);
                } catch (Exception e) {
                    TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
                    objDecodeFromString = null;
                }
            }
            Command command = (Command) objDecodeFromString;
            return command == null ? "" : engineBridge.execCommandAsyncInternal(command);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Throwable thM55exceptionOrNullimpl = Result.m55exceptionOrNullimpl(Result.m52constructorimpl(ResultKt.createFailure(th)));
            if (thM55exceptionOrNullimpl != null) {
                TapLogger.loge$default(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execUnrealCommand failed: cmd=" + cmdStr + ", cause=" + thM55exceptionOrNullimpl.getMessage(), null, 4, null);
            }
            return "";
        }
    }

    /* JADX INFO: renamed from: com.taptap.sdk.kit.internal.enginebridge.EngineBridge$handleResult$1, reason: invalid class name */
    /* JADX INFO: compiled from: EngineBridge.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.kit.internal.enginebridge.EngineBridge$handleResult$1", f = "EngineBridge.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ EngineBridgeCallback $callback;
        final /* synthetic */ EngineBridgeResult<String> $result;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(EngineBridgeResult<String> engineBridgeResult, EngineBridgeCallback engineBridgeCallback, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$result = engineBridgeResult;
            this.$callback = engineBridgeCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$result, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "handle bridge message: content=" + this.$result);
            EngineBridgeCallback engineBridgeCallback = this.$callback;
            if (engineBridgeCallback != null) {
                TapJson tapJson = TapJson.INSTANCE;
                EngineBridgeResult<String> engineBridgeResult = this.$result;
                String strEncodeToString = null;
                try {
                    Json json = tapJson.getJson();
                    KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(EngineBridgeResult.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class))));
                    Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                    strEncodeToString = json.encodeToString(kSerializerSerializer, engineBridgeResult);
                } catch (Exception e) {
                    TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
                }
                if (strEncodeToString == null) {
                    strEncodeToString = "";
                }
                engineBridgeCallback.onResult(strEncodeToString);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleResult(EngineBridgeResult<String> result, EngineBridgeCallback callback) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), Dispatchers.getIO(), null, new AnonymousClass1(result, callback, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void execCommandInternal(final Command command, final EngineBridgeCallback callback) {
        Object objM52constructorimpl;
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execCommandInternal: " + command);
        try {
            Result.Companion companion = Result.INSTANCE;
            final EngineBridge engineBridge = this;
            new CommandExecutor().execute(command, new EngineBridgeCallback() { // from class: com.taptap.sdk.kit.internal.enginebridge.EngineBridge$execCommandInternal$1$1
                @Override // com.taptap.sdk.kit.internal.enginebridge.EngineBridgeCallback
                public void onResult(String jsonString) {
                    Intrinsics.checkNotNullParameter(jsonString, "jsonString");
                    TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execCommandInternal success: cmd=" + command + ", result=" + jsonString);
                    engineBridge.handleResult(new EngineBridgeResult(0, "Success", jsonString), callback);
                }
            });
            objM52constructorimpl = Result.m52constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM52constructorimpl = Result.m52constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM55exceptionOrNullimpl = Result.m55exceptionOrNullimpl(objM52constructorimpl);
        if (thM55exceptionOrNullimpl != null) {
            TapLogger.loge(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execCommandInternal failed: cmd=" + command + ", cause=" + thM55exceptionOrNullimpl.getMessage(), thM55exceptionOrNullimpl);
            INSTANCE.handleResult(new EngineBridgeResult<>(-1, "Failed", (Object) null, 4, (DefaultConstructorMarker) null), callback);
        }
    }

    private final String execCommandAsyncInternal(Command command) {
        Object objM52constructorimpl;
        TapLogger.logi(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execCommandAsyncInternal: " + command);
        try {
            Result.Companion companion = Result.INSTANCE;
            EngineBridge engineBridge = this;
            Object objExecuteAsync = new CommandExecutor().executeAsync(command);
            if (objExecuteAsync instanceof String) {
                return (String) objExecuteAsync;
            }
            if (Intrinsics.areEqual(objExecuteAsync, Boolean.valueOf(objExecuteAsync == null))) {
                return "";
            }
            String strEncodeToString = null;
            try {
                Json json = TapJson.INSTANCE.getJson();
                KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(Object.class));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                strEncodeToString = json.encodeToString(kSerializerSerializer, objExecuteAsync);
            } catch (Exception e) {
                TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
            }
            objM52constructorimpl = Result.m52constructorimpl(strEncodeToString);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM52constructorimpl = Result.m52constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM55exceptionOrNullimpl = Result.m55exceptionOrNullimpl(objM52constructorimpl);
        if (thM55exceptionOrNullimpl != null) {
            TapLogger.loge(EngineBridgeKt.ENGINE_BRIDGE_LOGGER_TAG, "execCommandAsyncInternal failed: cmd=" + command + ", cause=" + thM55exceptionOrNullimpl.getMessage(), thM55exceptionOrNullimpl);
        }
        return "";
    }

    @Override // com.taptap.sdk.kit.internal.enginebridge.IEngineBridge
    public void registerService(Class<? extends IEngineBridgeService> serviceClz, IEngineBridgeService service) {
        Intrinsics.checkNotNullParameter(serviceClz, "serviceClz");
        Intrinsics.checkNotNullParameter(service, "service");
        if (EngineBridgeReflector.INSTANCE.checkServiceValid(serviceClz) && serviceClz.isInterface()) {
            BridgeHolder.INSTANCE.registerService(serviceClz, service);
        } else {
            if (EngineBridgeReflector.INSTANCE.getValidService(serviceClz) != null) {
                BridgeHolder.INSTANCE.registerService(serviceClz, service);
                return;
            }
            throw new EngineBridgeException("注册IBridgeService出现错误");
        }
    }
}
