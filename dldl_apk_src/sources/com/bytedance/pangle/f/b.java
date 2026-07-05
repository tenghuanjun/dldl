package com.bytedance.pangle.f;

import com.bytedance.pangle.ZeusPluginInstallListener;
import com.bytedance.pangle.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends d.a {
    private final ZeusPluginInstallListener a;

    public b(ZeusPluginInstallListener zeusPluginInstallListener) {
        this.a = zeusPluginInstallListener;
    }

    @Override // com.bytedance.pangle.d
    public final void a(String str, int i, String str2) {
        ZeusPluginInstallListener zeusPluginInstallListener = this.a;
        if (zeusPluginInstallListener != null) {
            zeusPluginInstallListener.onPluginInstall(str, i, str2);
        }
    }
}
