package com.sdk.sq.net;

import android.content.Context;
import com.sqnetwork.voly.IpController;
import com.sqnetwork.voly.toolbox.NetworkStatus;
import com.sqnetwork.voly.toolbox.Util;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OkHttpEventListener extends EventListener {
    private final Context mContext;

    public OkHttpEventListener(Context context) {
        this.mContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
    }

    @Override // okhttp3.EventListener
    public void callStart(Call call) {
        super.callStart(call);
        logToVolleyMarker(call, "okhttp call start");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.url = call.request().url();
            if (hasUserConsent(call) && allowObtainIp(call)) {
                netStatusFromCall.activeIp = Util.getActiveIp(this.mContext);
            } else {
                netStatusFromCall.activeIp = "0.0.0.0";
            }
            netStatusFromCall.callStart();
        }
    }

    @Override // okhttp3.EventListener
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
        logToVolleyMarker(call, "okhttp dns start[" + domainName + "]");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.dnsStart();
        }
    }

    @Override // okhttp3.EventListener
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        logToVolleyMarker(call, "okhttp dns end: " + inetAddressList);
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.dnsEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        logToVolleyMarker(call, "okhttp connect start[" + call.request().url() + "]");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectAddress = inetSocketAddress.getAddress();
            netStatusFromCall.connectStart();
        }
    }

    @Override // okhttp3.EventListener
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
        logToVolleyMarker(call, "okhttp secure connect start");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.secureConnectStart();
        }
    }

    @Override // okhttp3.EventListener
    public void secureConnectEnd(Call call, Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        logToVolleyMarker(call, "okhttp secure connect end[tlsVersion=" + handshake.tlsVersion() + "]");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.secureConnectEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        logToVolleyMarker(call, "okhttp connect end[" + protocol + "://" + inetSocketAddress + "]");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectAddress = inetSocketAddress.getAddress();
            netStatusFromCall.connectEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
        logToVolleyMarker(call, "okhttp connect failed[" + protocol + "://" + inetSocketAddress + "]");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.connectAddress = inetSocketAddress.getAddress();
            netStatusFromCall.connectEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
        logToVolleyMarker(call, "okhttp connect acquired[" + connection.protocol() + "://" + connection.route().socketAddress() + "]");
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
        logToVolleyMarker(call, "okhttp request body end[" + byteCount + "]");
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
        logToVolleyMarker(call, "okhttp response body end[" + byteCount + "]");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.responseBodyEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void callEnd(Call call) {
        super.callEnd(call);
        logToVolleyMarker(call, "okhttp call end");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.callEnd();
        }
    }

    @Override // okhttp3.EventListener
    public void callFailed(Call call, IOException ioe) {
        super.callFailed(call, ioe);
        logToVolleyMarker(call, "okhttp call failed");
        NetworkStatus netStatusFromCall = getNetStatusFromCall(call);
        if (netStatusFromCall != null) {
            netStatusFromCall.callEnd();
        }
    }

    private void logToVolleyMarker(Call call, String message) {
        com.sqnetwork.voly.Request request;
        if (call == null || (request = (com.sqnetwork.voly.Request) call.request().tag(com.sqnetwork.voly.Request.class)) == null) {
            return;
        }
        request.addMarker(message);
    }

    private NetworkStatus getNetStatusFromCall(Call call) {
        com.sqnetwork.voly.Request request;
        if (call == null || (request = (com.sqnetwork.voly.Request) call.request().tag(com.sqnetwork.voly.Request.class)) == null) {
            return null;
        }
        return request.getRequestStatus().currentCallStatus();
    }

    private boolean hasUserConsent(Call call) {
        com.sqnetwork.voly.Request request;
        Map<String, Boolean> userConsent;
        return (call == null || (request = (com.sqnetwork.voly.Request) call.request().tag(com.sqnetwork.voly.Request.class)) == null || (userConsent = request.getUserConsent()) == null || userConsent.isEmpty()) ? false : true;
    }

    private boolean allowObtainIp(Call call) {
        com.sqnetwork.voly.Request request;
        if (call == null || (request = (com.sqnetwork.voly.Request) call.request().tag(com.sqnetwork.voly.Request.class)) == null) {
            return false;
        }
        IpController ipController = request.getIpController();
        if (ipController != null) {
            return ipController.isAllowIpObtain();
        }
        return true;
    }
}
