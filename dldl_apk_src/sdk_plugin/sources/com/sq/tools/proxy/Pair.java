package com.sq.tools.proxy;

import android.text.TextUtils;
import java.util.UUID;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Pair {
    public String name;
    public String value;

    public Pair(String str, String str2) {
        this.name = TextUtils.isEmpty(str) ? UUID.randomUUID().toString().substring(0, 8) : str;
        this.value = TextUtils.isEmpty(str2) ? "" : str2;
    }

    public String toString() {
        return "Pair{name='" + this.name + "', value='" + this.value + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
