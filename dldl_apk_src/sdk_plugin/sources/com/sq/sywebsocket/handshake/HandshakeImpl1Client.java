package com.sq.sywebsocket.handshake;

import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
    private String resourceDescriptor = Marker.ANY_MARKER;

    @Override // com.sq.sywebsocket.handshake.ClientHandshakeBuilder
    public void setResourceDescriptor(String str) {
        if (str == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.resourceDescriptor = str;
    }

    @Override // com.sq.sywebsocket.handshake.ClientHandshake
    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }
}
