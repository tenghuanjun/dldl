package com.sq.sywebsocket.extensions;

import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.exceptions.InvalidFrameException;
import com.sq.sywebsocket.framing.Framedata;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultExtension implements IExtension {
    @Override // com.sq.sywebsocket.extensions.IExtension
    public boolean acceptProvidedExtensionAsClient(String str) {
        return true;
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public boolean acceptProvidedExtensionAsServer(String str) {
        return true;
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public void decodeFrame(Framedata framedata) throws InvalidDataException {
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public void encodeFrame(Framedata framedata) {
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public String getProvidedExtensionAsClient() {
        return "";
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public String getProvidedExtensionAsServer() {
        return "";
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public void reset() {
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public void isFrameValid(Framedata framedata) throws InvalidDataException {
        if (framedata.isRSV1() || framedata.isRSV2() || framedata.isRSV3()) {
            throw new InvalidFrameException("bad rsv RSV1: " + framedata.isRSV1() + " RSV2: " + framedata.isRSV2() + " RSV3: " + framedata.isRSV3());
        }
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public IExtension copyInstance() {
        return new DefaultExtension();
    }

    @Override // com.sq.sywebsocket.extensions.IExtension
    public String toString() {
        return getClass().getSimpleName();
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass());
    }
}
