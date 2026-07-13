package com.bytedance.applog.network;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkClient {
    public static final byte METHOD_GET = 0;
    public static final byte METHOD_POST = 1;
    public static final byte RESPONSE_TYPE_STREAM = 1;
    public static final byte RESPONSE_TYPE_STRING = 0;

    byte[] execute(byte b, String str, JSONObject jSONObject, Map<String, String> map, byte b2, boolean z, int i);
}
