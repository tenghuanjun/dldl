package com.huya.berry.module.props.prop;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PropItem implements Comparable<PropItem> {
    public static final int INVALID = -1;
    protected int mId = -1;
    protected String mName = "";
    String mResUrl = "";

    protected PropItem() {
    }

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getResUrl() {
        return this.mResUrl;
    }

    protected boolean isValid() {
        return -1 != this.mId;
    }

    @Override // java.lang.Comparable
    public int compareTo(PropItem propItem) {
        return this.mId;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PropItem)) {
            return false;
        }
        PropItem propItem = (PropItem) obj;
        return getId() == propItem.getId() && getName().equals(propItem.getName());
    }

    public int hashCode() {
        return (this.mId * 31) + this.mName.hashCode();
    }

    public String toString() {
        return "PropItem{mId=" + this.mId + ", mName='" + this.mName + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
