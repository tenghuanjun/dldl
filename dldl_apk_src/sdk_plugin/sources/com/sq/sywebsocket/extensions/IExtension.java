package com.sq.sywebsocket.extensions;

import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.framing.Framedata;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IExtension {
    boolean acceptProvidedExtensionAsClient(String str);

    boolean acceptProvidedExtensionAsServer(String str);

    IExtension copyInstance();

    void decodeFrame(Framedata framedata) throws InvalidDataException;

    void encodeFrame(Framedata framedata);

    String getProvidedExtensionAsClient();

    String getProvidedExtensionAsServer();

    void isFrameValid(Framedata framedata) throws InvalidDataException;

    void reset();

    String toString();
}
