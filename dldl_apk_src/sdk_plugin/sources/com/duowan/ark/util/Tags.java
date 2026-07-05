package com.duowan.ark.util;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Tags {
    public final Object[] mTags;

    public Tags(Object... objArr) {
        this.mTags = objArr;
    }

    public String toString() {
        Object[] objArr = this.mTags;
        if (objArr == null || objArr.length == 0) {
            return "EmptyTags";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : this.mTags) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(obj);
            sb.append(AbstractJsonLexerKt.END_LIST);
        }
        return sb.toString();
    }
}
