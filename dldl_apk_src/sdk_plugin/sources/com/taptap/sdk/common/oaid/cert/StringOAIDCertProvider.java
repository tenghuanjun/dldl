package com.taptap.sdk.common.oaid.cert;

import kotlin.Metadata;

/* JADX INFO: compiled from: StringOAIDCertProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/common/oaid/cert/StringOAIDCertProvider;", "Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;", "certString", "", "(Ljava/lang/String;)V", "provideOAIDCert", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class StringOAIDCertProvider implements OAIDCertProvider {
    private final String certString;

    public StringOAIDCertProvider(String str) {
        this.certString = str;
    }

    @Override // com.taptap.sdk.common.oaid.cert.OAIDCertProvider
    public String provideOAIDCert() {
        String str = this.certString;
        if (str != null) {
            if (str.length() > 0) {
                return str;
            }
        }
        return null;
    }
}
