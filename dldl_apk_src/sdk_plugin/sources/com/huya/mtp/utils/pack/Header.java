package com.huya.mtp.utils.pack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Header {
    public static final Uint16 RES_SUCCESS = new Uint16(200);
    protected Uint32 uri = Uint32.toUInt(0);
    protected Uint32 length = Uint32.toUInt(0);
    protected Uint16 resCode = Uint16.toUInt(200);

    public Uint32 getUri() {
        return this.uri;
    }

    public void setUri(Uint32 uint32) {
        this.uri = uint32;
    }

    public Uint32 getLength() {
        return this.length;
    }

    public void setLength(Uint32 uint32) {
        this.length = uint32;
    }

    public Uint16 getResCode() {
        return this.resCode;
    }

    public void setResCode(Uint16 uint16) {
        this.resCode = uint16;
    }

    public boolean isSuccess() {
        return this.resCode.toLong() == 200;
    }

    public int getUriPrefix() {
        return this.uri.toInt() >> 8;
    }

    public int getUriSuffix() {
        return this.uri.toInt() - ((this.uri.toInt() >> 8) << 8);
    }
}
