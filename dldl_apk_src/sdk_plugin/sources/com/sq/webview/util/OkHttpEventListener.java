package com.sq.webview.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OkHttpEventListener extends EventListener {
    @Override // okhttp3.EventListener
    public void callStart(Call call) {
        super.callStart(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.url = call.request().url();
            netStatusFromCall.callStart();
        }
    }

    @Override // okhttp3.EventListener
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.dnsStart();
        }
    }

    @Override // okhttp3.EventListener
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.dnsEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectStart();
        }
    }

    @Override // okhttp3.EventListener
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.secureConnectStart();
        }
    }

    @Override // okhttp3.EventListener
    public void secureConnectEnd(Call call, Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.secureConnectEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectAddress = connection.route().socketAddress().getAddress();
        }
    }

    @Override // okhttp3.EventListener
    public void connectionReleased(Call call, Connection connection) {
        super.connectionReleased(call, connection);
    }

    @Override // okhttp3.EventListener
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.requestHeadersStart();
        }
    }

    @Override // okhttp3.EventListener
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.requestHeadersEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.requestBodyStart();
        }
    }

    @Override // okhttp3.EventListener
    public void requestBodyEnd(Call call, long byteCount) {
        super.requestBodyEnd(call, byteCount);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.requestBodyEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void responseHeadersStart(Call call) {
        super.responseHeadersStart(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.responseHeadersStart();
        }
    }

    @Override // okhttp3.EventListener
    public void responseHeadersEnd(Call call, Response response) {
        super.responseHeadersEnd(call, response);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.responseHeadersEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void responseBodyStart(Call call) {
        super.responseBodyStart(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.responseBodyStart();
        }
    }

    @Override // okhttp3.EventListener
    public void responseBodyEnd(Call call, long byteCount) {
        super.responseBodyEnd(call, byteCount);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.responseBodyEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void callEnd(Call call) {
        super.callEnd(call);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.callEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void callFailed(Call call, IOException ioe) {
        super.callFailed(call, ioe);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.callEnd();
        }
    }

    private NetworkStatus getNetStatusFromCall(Call call) {
        if (call == null) {
            return null;
        }
        return (NetworkStatus) call.request().tag(NetworkStatus.class);
    }
}
