package com.taptap.sdk.common.oaid.strategy;

import android.content.Context;
import com.taptap.sdk.common.oaid.version.Versions;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: OAIDStrategy.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\b\u0010\r\u001a\u00020\u000eH&J%\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/common/oaid/strategy/OAIDStrategy;", "", "supportedVersion", "", "Lcom/taptap/sdk/common/oaid/version/Versions;", "getSupportedVersion", "()Ljava/util/Set;", "initPemCert", "", "context", "Landroid/content/Context;", "oaidCert", "", "loadClassesByReflect", "", "tryGetOAIDByReflect", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface OAIDStrategy {
    Set<Versions> getSupportedVersion();

    void initPemCert(Context context, String oaidCert);

    boolean loadClassesByReflect();

    Object tryGetOAIDByReflect(Context context, String str, Continuation<? super String> continuation);
}
