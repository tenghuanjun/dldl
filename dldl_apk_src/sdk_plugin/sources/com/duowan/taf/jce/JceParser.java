package com.duowan.taf.jce;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class JceParser {
    public static <T extends JceStruct> T parseJce(byte[] bArr, T t) {
        if (bArr == null || bArr.length == 0 || t == null) {
            return null;
        }
        try {
            t.readFrom(new JceInputStream(bArr));
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
