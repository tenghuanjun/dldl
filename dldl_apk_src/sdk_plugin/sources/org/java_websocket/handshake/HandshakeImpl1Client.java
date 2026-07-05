package org.java_websocket.handshake;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
    private String resourcedescriptor;

    @Override // org.java_websocket.handshake.ClientHandshakeBuilder
    public void setResourceDescriptor(String str) throws IllegalArgumentException {
        this.resourcedescriptor = str;
    }

    @Override // org.java_websocket.handshake.ClientHandshake
    public String getResourceDescriptor() {
        return this.resourcedescriptor;
    }
}
