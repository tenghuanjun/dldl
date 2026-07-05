package com.duowan.ark.util;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogFileComparator implements Comparator<File> {
    public static final int ASC_SORT_BY_LAST_MODIFIED_TIME = 0;
    public static final int DESC_SORT_BY_LAST_MODIFIED_TIME = 1;
    private int sortType;

    LogFileComparator(int i) {
        this.sortType = i;
    }

    @Override // java.util.Comparator
    public int compare(File file, File file2) {
        if (this.sortType != 0) {
            if (file.lastModified() > file2.lastModified()) {
                return -1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : 1;
        }
        if (file.lastModified() < file2.lastModified()) {
            return -1;
        }
        return file.lastModified() == file2.lastModified() ? 0 : 1;
    }
}
