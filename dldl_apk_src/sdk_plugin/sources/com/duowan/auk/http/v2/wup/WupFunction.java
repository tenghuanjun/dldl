package com.duowan.auk.http.v2.wup;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.HttpFunction;
import com.duowan.auk.http.v2.exception.NullResponseException;
import com.duowan.auk.util.L;
import com.duowan.jce.wup.UniPacket;
import com.duowan.taf.jce.JceStruct;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class WupFunction<Req extends JceStruct, Rsp extends JceStruct> extends HttpFunction<Rsp> implements WupRequestDelegate, WupResponseDelegate<Rsp> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/multipart-formdata", PROTOCOL_CHARSET);
    private static final String TAG = "WupDataRequest";
    private Req mReq;

    public abstract String getCodeKey();

    public abstract String getFuncName();

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public int getMethod() {
        return 1;
    }

    public Map<String, Object> getOtherParams() {
        return null;
    }

    public abstract String getRequestKey();

    public abstract String getResponseKey();

    public abstract Rsp getRspProxy();

    public abstract String getServantName();

    public WupFunction(Req req) {
        this.mReq = req;
    }

    public Req getRequest() {
        return this.mReq;
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getCacheKey() {
        return getFuncName();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public Rsp onReadResponse(NetworkResponse networkResponse) throws VolleyError {
        UniPacket uniPacket = new UniPacket();
        try {
            uniPacket.decode(networkResponse.data);
            return (Rsp) readResponseFromUniPacket(uniPacket);
        } catch (Exception e) {
            throw new VolleyError(e);
        }
    }

    private Rsp readResponseFromUniPacket(UniPacket uniPacket) throws VolleyError {
        if (uniPacket == null) {
            throw new WupError("response is null from server when executing function" + getFuncName());
        }
        int code = WupUtil.getCode(uniPacket, getCodeKey());
        Rsp rsp = (Rsp) decodePacket(uniPacket);
        if (code >= 0) {
            return rsp;
        }
        throw new WupError("server return code:" + code + " when executing function:" + getFuncName(), null, code, getFuncName(), rsp);
    }

    private Rsp decodePacket(UniPacket uniPacket) {
        Rsp rsp;
        String responseKey = getResponseKey();
        Rsp rsp2 = (Rsp) getRspProxy();
        if (responseKey != null) {
            rsp = (Rsp) WupUtil.getObject(uniPacket, responseKey, rsp2);
            if (rsp != null) {
                L.debug(TAG, "rsp=%s", rsp);
                return rsp;
            }
        } else {
            rsp = null;
        }
        if (L.isLogLevelEnabled(3)) {
            L.debug(TAG, "[decodePacket] rspKey = %s, rsp = %s, return default proxy object!", responseKey, rsp);
        }
        return rsp2;
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public byte[] getBody() {
        return WupUtil.create(getServantName(), getFuncName(), getRequestKey(), getRequest(), getOtherParams()).encode();
    }

    @Override // com.duowan.auk.http.v2.wup.WupRequestDelegate
    public UniPacket getUniPacketBody() {
        return WupUtil.create(getServantName(), getFuncName(), getRequestKey(), getRequest(), getOtherParams());
    }

    @Override // com.duowan.auk.http.v2.wup.WupResponseDelegate
    public Rsp parseUniPacketResponse(UniPacket uniPacket, byte[] bArr) throws VolleyError {
        Rsp rsp = (Rsp) readResponseFromUniPacket(uniPacket);
        validateResponse((JceStruct) rsp);
        if (shouldUseCustomCache()) {
            saveCacheToStorage(new NetworkResponse(uniPacket.encode()));
        }
        return rsp;
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public Request.Priority getPriority() {
        return Request.Priority.NORMAL;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url = ");
        sb.append(getUrl());
        sb.append(", servantName = ");
        sb.append(getServantName());
        sb.append(", funcName = ");
        sb.append(getFuncName());
        JceStruct request = getRequest();
        if (request != null) {
            sb.append("\nrequest --- ");
            sb.append(request);
        }
        sb.append("\n[addr:");
        sb.append(super.toString());
        sb.append("]");
        return sb.toString();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public final Map<String, String> getParams() {
        return super.getParams();
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public void validateResponse(Rsp rsp) throws VolleyError {
        if (rsp == null && getRspProxy() != null) {
            throw new NullResponseException();
        }
    }
}
