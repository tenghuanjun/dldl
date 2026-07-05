package com.taptap.sdk.common.oaid.cert;

import android.content.Context;
import com.taptap.sdk.common.utils.AssetUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssetOAIDCertProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/common/oaid/cert/AssetOAIDCertProvider;", "Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "provideOAIDCert", "", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class AssetOAIDCertProvider implements OAIDCertProvider {
    private static final String CERT_EXTENSION = ".cert.pem";
    private final Context context;

    public AssetOAIDCertProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.taptap.sdk.common.oaid.cert.OAIDCertProvider
    public String provideOAIDCert() {
        try {
            return AssetUtils.INSTANCE.readContentFromAssets(this.context, this.context.getPackageName() + CERT_EXTENSION);
        } catch (Exception unused) {
            return null;
        }
    }
}
