package notchtools.geek.com.notchtools.core;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NotchProperty {
    private boolean mIsNotch;
    private int mMarginTop;
    private int mNotchHeight;
    private int mStatusBarHeight;

    public int getStatusBarHeight() {
        return this.mStatusBarHeight;
    }

    public void setStatusBarHeight(int i) {
        this.mStatusBarHeight = i;
    }

    public int geNotchHeight() {
        return this.mNotchHeight;
    }

    public void setNotchHeight(int i) {
        this.mNotchHeight = i;
    }

    public boolean isNotch() {
        return this.mIsNotch;
    }

    public void setNotch(boolean z) {
        this.mIsNotch = z;
    }

    public int getMarginTop() {
        return this.mMarginTop;
    }

    public void setMarginTop(int i) {
        this.mMarginTop = i;
    }

    public String toString() {
        return "NotchProperty{mNotchHeight=" + this.mNotchHeight + ", mIsNotch=" + this.mIsNotch + ", mMarginTop=" + this.mMarginTop + ", mStatusBarHeight=" + this.mStatusBarHeight + AbstractJsonLexerKt.END_OBJ;
    }
}
