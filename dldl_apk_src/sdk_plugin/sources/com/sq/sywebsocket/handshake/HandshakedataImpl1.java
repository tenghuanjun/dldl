package com.sq.sywebsocket.handshake;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HandshakedataImpl1 implements HandshakeBuilder {
    private byte[] content;
    private TreeMap<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    @Override // com.sq.sywebsocket.handshake.Handshakedata
    public Iterator<String> iterateHttpFields() {
        return Collections.unmodifiableSet(this.map.keySet()).iterator();
    }

    @Override // com.sq.sywebsocket.handshake.Handshakedata
    public String getFieldValue(String str) {
        String str2 = this.map.get(str);
        return str2 == null ? "" : str2;
    }

    @Override // com.sq.sywebsocket.handshake.Handshakedata
    public byte[] getContent() {
        return this.content;
    }

    @Override // com.sq.sywebsocket.handshake.HandshakeBuilder
    public void setContent(byte[] bArr) {
        this.content = bArr;
    }

    @Override // com.sq.sywebsocket.handshake.HandshakeBuilder
    public void put(String str, String str2) {
        this.map.put(str, str2);
    }

    @Override // com.sq.sywebsocket.handshake.Handshakedata
    public boolean hasFieldValue(String str) {
        return this.map.containsKey(str);
    }
}
