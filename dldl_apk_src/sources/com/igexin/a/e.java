package com.igexin.a;

import android.os.Build;
import android.text.TextUtils;
import com.igexin.a.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class e implements c.b {
    e() {
    }

    @Override // com.igexin.a.c.b
    public final void a(String str) {
        System.loadLibrary(str);
    }

    @Override // com.igexin.a.c.b
    public final String[] a() {
        return (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) ? !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI} : Build.SUPPORTED_ABIS;
    }

    @Override // com.igexin.a.c.b
    public final void b(String str) {
        System.load(str);
    }

    @Override // com.igexin.a.c.b
    public final String c(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // com.igexin.a.c.b
    public final String d(String str) {
        return str.substring(3, str.length() - 3);
    }
}
