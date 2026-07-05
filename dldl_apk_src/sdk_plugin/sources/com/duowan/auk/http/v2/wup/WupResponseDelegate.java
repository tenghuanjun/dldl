package com.duowan.auk.http.v2.wup;

import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.HttpResponseDelegate;
import com.duowan.jce.wup.UniPacket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface WupResponseDelegate<Rsp> extends HttpResponseDelegate<Rsp> {
    Rsp parseUniPacketResponse(UniPacket uniPacket, byte[] bArr) throws VolleyError;
}
