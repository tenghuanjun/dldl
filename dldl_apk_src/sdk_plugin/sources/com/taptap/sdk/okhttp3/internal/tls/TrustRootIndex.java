package com.taptap.sdk.okhttp3.internal.tls;

import java.security.cert.X509Certificate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface TrustRootIndex {
    X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);
}
