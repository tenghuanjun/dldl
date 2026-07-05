package com.sq.sywebsocket.client;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DnsResolver {
    InetAddress resolve(URI uri) throws UnknownHostException;
}
