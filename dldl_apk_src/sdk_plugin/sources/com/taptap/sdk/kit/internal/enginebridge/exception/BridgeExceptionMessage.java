package com.taptap.sdk.kit.internal.enginebridge.exception;

import kotlin.Metadata;

/* JADX INFO: compiled from: EngineBridgeException.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/taptap/sdk/kit/internal/enginebridge/exception/BridgeExceptionMessage;", "", "message", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getExtraMessage", "extraMessage", "COMMAND_PARSE_ERROR", "COMMAND_SERVICE_ERROR", "COMMAND_METHOD_ERROR", "COMMAND_ARGS_ERROR", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum BridgeExceptionMessage {
    COMMAND_PARSE_ERROR("json解析错误，检查Command是否符合规则"),
    COMMAND_SERVICE_ERROR("Service异常，检查Service是否注册或者符合规则"),
    COMMAND_METHOD_ERROR("Method异常，检查Method命名是否正确"),
    COMMAND_ARGS_ERROR("参数匹配错误或者执行方法错误，检查参数以及桥接方法执行是否正确");

    private final String message;

    BridgeExceptionMessage(String str) {
        this.message = str;
    }

    public final String getMessage() {
        return this.message;
    }

    public static /* synthetic */ String getExtraMessage$default(BridgeExceptionMessage bridgeExceptionMessage, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getExtraMessage");
        }
        if ((i & 1) != 0) {
            str = "";
        }
        return bridgeExceptionMessage.getExtraMessage(str);
    }

    public final String getExtraMessage(String extraMessage) {
        return this.message + ", extraMsg=" + extraMessage;
    }
}
