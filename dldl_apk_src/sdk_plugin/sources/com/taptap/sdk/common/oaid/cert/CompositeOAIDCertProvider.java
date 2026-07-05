package com.taptap.sdk.common.oaid.cert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompositeOAIDCertProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/oaid/cert/CompositeOAIDCertProvider;", "Lcom/taptap/sdk/common/oaid/cert/OAIDCertProvider;", "providers", "", "(Ljava/util/List;)V", "provideOAIDCert", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class CompositeOAIDCertProvider implements OAIDCertProvider {
    private final List<OAIDCertProvider> providers;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeOAIDCertProvider(List<? extends OAIDCertProvider> providers) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        this.providers = providers;
    }

    @Override // com.taptap.sdk.common.oaid.cert.OAIDCertProvider
    public String provideOAIDCert() {
        List<OAIDCertProvider> list = this.providers;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((OAIDCertProvider) it.next()).provideOAIDCert());
        }
        return (String) CollectionsKt.firstOrNull((List) arrayList);
    }
}
