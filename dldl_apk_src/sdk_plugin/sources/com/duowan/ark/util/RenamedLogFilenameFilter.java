package com.duowan.ark.util;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class RenamedLogFilenameFilter implements FilenameFilter {
    private String startLable = null;
    private String endLable = null;
    private int length = 0;

    public RenamedLogFilenameFilter setStartLable(String str) {
        this.startLable = str;
        return this;
    }

    public RenamedLogFilenameFilter setEndLable(String str) {
        this.endLable = str;
        return this;
    }

    public RenamedLogFilenameFilter setLength(int i) {
        this.length = i;
        return this;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        String str2 = this.startLable;
        boolean zStartsWith = (str2 == null || str2.length() <= 0) ? true : str.startsWith(this.startLable) & true;
        if (this.endLable != null && this.startLable.length() > 0) {
            zStartsWith &= str.endsWith(this.endLable);
        }
        if (this.length > 0) {
            return zStartsWith & (str.length() == this.length);
        }
        return zStartsWith;
    }
}
