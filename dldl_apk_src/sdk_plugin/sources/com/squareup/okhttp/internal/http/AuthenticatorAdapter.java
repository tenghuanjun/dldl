package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Challenge;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class AuthenticatorAdapter implements Authenticator {
    public static final Authenticator INSTANCE = new AuthenticatorAdapter();

    @Override // com.squareup.okhttp.Authenticator
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        List<Challenge> listChallenges = response.challenges();
        Request request = response.request();
        URL url = request.url();
        int size = listChallenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = listChallenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme()) && (passwordAuthenticationRequestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(url.getHost(), getConnectToInetAddress(proxy, url), url.getPort(), url.getProtocol(), challenge.getRealm(), challenge.getScheme(), url, Authenticator.RequestorType.SERVER)) != null) {
                return request.newBuilder().header("Authorization", Credentials.basic(passwordAuthenticationRequestPasswordAuthentication.getUserName(), new String(passwordAuthenticationRequestPasswordAuthentication.getPassword()))).build();
            }
        }
        return null;
    }

    @Override // com.squareup.okhttp.Authenticator
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        List<Challenge> listChallenges = response.challenges();
        Request request = response.request();
        URL url = request.url();
        int size = listChallenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = listChallenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme())) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication = java.net.Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), getConnectToInetAddress(proxy, url), inetSocketAddress.getPort(), url.getProtocol(), challenge.getRealm(), challenge.getScheme(), url, Authenticator.RequestorType.PROXY);
                if (passwordAuthenticationRequestPasswordAuthentication != null) {
                    return request.newBuilder().header("Proxy-Authorization", Credentials.basic(passwordAuthenticationRequestPasswordAuthentication.getUserName(), new String(passwordAuthenticationRequestPasswordAuthentication.getPassword()))).build();
                }
            }
        }
        return null;
    }

    private InetAddress getConnectToInetAddress(Proxy proxy, URL url) throws IOException {
        return (proxy == null || proxy.type() == Proxy.Type.DIRECT) ? InetAddress.getByName(url.getHost()) : ((InetSocketAddress) proxy.address()).getAddress();
    }
}
