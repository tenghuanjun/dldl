package com.taptap.sdk.kit.internal.identifier.content;

import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: compiled from: TapOpenIdUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u0017\u0010\u0007\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\tR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/kit/internal/identifier/content/TapOpenIdUtil;", "", "()V", "openId", "", "openIdLock", "getOpenId", "setOpenId", "", "setOpenId$tap_common_release", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapOpenIdUtil {
    private static volatile String openId;
    public static final TapOpenIdUtil INSTANCE = new TapOpenIdUtil();
    private static final Object openIdLock = new Object();

    private TapOpenIdUtil() {
    }

    public final String getOpenId() {
        String str;
        synchronized (openIdLock) {
            str = openId;
        }
        return str;
    }

    public final void setOpenId$tap_common_release(String openId2) {
        synchronized (openIdLock) {
            openId = openId2;
            Unit unit = Unit.INSTANCE;
        }
    }
}
