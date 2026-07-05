package com.huya.mtp.hyns.miniprogram.utils;

import com.huya.mtp.hyns.miniprogram.data.ProxySignalSocketData;
import com.huya.mtp.hyns.miniprogram.jce.WsProxyPacket;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalSocketHelper {
    public static ProxySignalSocketData getCommonPacket(byte[] bArr, int i) {
        WsProxyPacket wsProxyPacket = new WsProxyPacket();
        wsProxyPacket.iCommand = i;
        wsProxyPacket.vData = bArr;
        ProxySignalSocketData proxySignalSocketData = new ProxySignalSocketData();
        proxySignalSocketData.data = wsProxyPacket.toByteArray();
        return proxySignalSocketData;
    }

    @Deprecated
    public static byte[] getContentByte(byte[] bArr) {
        byte[] bArr2 = new byte[r1.getInt() - 4];
        ByteBuffer.wrap(bArr).get(bArr2);
        return bArr2;
    }

    public static int addSizeLength(byte[] bArr) {
        return (bArr != null ? bArr.length : 0) + 4;
    }
}
