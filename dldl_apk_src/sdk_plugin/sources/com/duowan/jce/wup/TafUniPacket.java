package com.duowan.jce.wup;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TafUniPacket extends UniPacket {
    private static final long serialVersionUID = 1;

    public TafUniPacket() {
        this._package.iVersion = (short) 2;
        this._package.cPacketType = (byte) 0;
        this._package.iMessageType = 0;
        this._package.iTimeout = 0;
        this._package.sBuffer = new byte[0];
        this._package.context = new HashMap();
        this._package.status = new HashMap();
    }

    public void setTafVersion(short s) {
        this._package.iVersion = s;
        if (s == 3) {
            useVersion3();
        }
    }

    public void setTafPacketType(byte b) {
        this._package.cPacketType = b;
    }

    public void setTafMessageType(int i) {
        this._package.iMessageType = i;
    }

    public void setTafTimeout(int i) {
        this._package.iTimeout = i;
    }

    public void setTafBuffer(byte[] bArr) {
        this._package.sBuffer = bArr;
    }

    public void setTafContext(Map<String, String> map) {
        this._package.context = map;
    }

    public void setTafStatus(Map<String, String> map) {
        this._package.status = map;
    }

    public short getTafVersion() {
        return this._package.iVersion;
    }

    public byte getTafPacketType() {
        return this._package.cPacketType;
    }

    public int getTafMessageType() {
        return this._package.iMessageType;
    }

    public int getTafTimeout() {
        return this._package.iTimeout;
    }

    public byte[] getTafBuffer() {
        return this._package.sBuffer;
    }

    public Map<String, String> getTafContext() {
        return this._package.context;
    }

    public Map<String, String> getTafStatus() {
        return this._package.status;
    }

    public int getTafResultCode() {
        String str = this._package.status.get("STATUS_RESULT_CODE");
        if (str != null) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    public String getTafResultDesc() {
        String str = this._package.status.get("STATUS_RESULT_DESC");
        return str != null ? str : "";
    }
}
