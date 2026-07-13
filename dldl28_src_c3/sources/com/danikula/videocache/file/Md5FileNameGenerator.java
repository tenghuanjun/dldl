package com.danikula.videocache.file;

import android.text.TextUtils;
import com.danikula.videocache.ProxyCacheUtils;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Md5FileNameGenerator implements FileNameGenerator {
    private static final int MAX_EXTENSION_LENGTH = 4;

    @Override // com.danikula.videocache.file.FileNameGenerator
    public String generate(String str) {
        String extension = getExtension(str);
        String strComputeMD5 = ProxyCacheUtils.computeMD5(str);
        if (TextUtils.isEmpty(extension)) {
            return strComputeMD5;
        }
        return strComputeMD5 + "." + extension;
    }

    private String getExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || iLastIndexOf <= str.lastIndexOf(47) || iLastIndexOf + 6 <= str.length()) ? "" : str.substring(iLastIndexOf + 1);
    }
}
