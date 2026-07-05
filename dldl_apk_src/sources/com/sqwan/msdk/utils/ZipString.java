package com.sqwan.msdk.utils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
@Deprecated
public class ZipString {
    public static final String json2ZipString(String str) {
        return Atwbase.encode(compress(str));
    }

    public static final String zipString2Json(String str) {
        return decompress(Atwbase.decode(str));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:(4:49|5|47|6)|(4:51|7|37|8)|41|9|33) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final byte[] compress(java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L45
            r1.<init>()     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L45
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            java.util.zip.ZipEntry r3 = new java.util.zip.ZipEntry     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            java.lang.String r4 = "0"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            r2.putNextEntry(r3)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            byte[] r5 = r5.getBytes()     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            r2.write(r5)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            r2.closeEntry()     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            r2.close()     // Catch: java.io.IOException -> L29
        L29:
            r1.close()     // Catch: java.io.IOException -> L51
            goto L51
        L2d:
            r5 = move-exception
            r0 = r2
            goto L38
        L30:
            goto L47
        L32:
            r5 = move-exception
            goto L38
        L34:
            r2 = r0
            goto L47
        L36:
            r5 = move-exception
            r1 = r0
        L38:
            if (r0 == 0) goto L3f
            r0.close()     // Catch: java.io.IOException -> L3e
            goto L3f
        L3e:
        L3f:
            if (r1 == 0) goto L44
            r1.close()     // Catch: java.io.IOException -> L44
        L44:
            throw r5
        L45:
            r1 = r0
            r2 = r1
        L47:
            if (r2 == 0) goto L4e
            r2.close()     // Catch: java.io.IOException -> L4d
            goto L4e
        L4d:
        L4e:
            if (r1 == 0) goto L51
            goto L29
        L51:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.utils.ZipString.compress(java.lang.String):byte[]");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:(2:74|5)|(6:78|6|76|7|72|8)|(5:9|(1:11)(1:80)|58|15|51)|12|64|13|62|14|58|15|51) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final java.lang.String decompress(byte[] r7) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L5e
            r1.<init>()     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L5e
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            java.util.zip.ZipInputStream r7 = new java.util.zip.ZipInputStream     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
            r7.getNextEntry()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
        L1a:
            int r4 = r7.read(r3)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r5 = -1
            if (r4 == r5) goto L26
            r5 = 0
            r1.write(r3, r5, r4)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            goto L1a
        L26:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r7.close()     // Catch: java.io.IOException -> L2d
        L2d:
            r2.close()     // Catch: java.io.IOException -> L30
        L30:
            r1.close()     // Catch: java.io.IOException -> L72
            goto L72
        L34:
            r0 = move-exception
            goto L4a
        L36:
            goto L61
        L38:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L4a
        L3d:
            r7 = r0
            goto L61
        L3f:
            r7 = move-exception
            r2 = r0
            goto L48
        L42:
            r7 = r0
            r2 = r7
            goto L61
        L45:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L48:
            r0 = r7
            r7 = r2
        L4a:
            if (r7 == 0) goto L51
            r7.close()     // Catch: java.io.IOException -> L50
            goto L51
        L50:
        L51:
            if (r2 == 0) goto L58
            r2.close()     // Catch: java.io.IOException -> L57
            goto L58
        L57:
        L58:
            if (r1 == 0) goto L5d
            r1.close()     // Catch: java.io.IOException -> L5d
        L5d:
            throw r0
        L5e:
            r7 = r0
            r1 = r7
            r2 = r1
        L61:
            if (r7 == 0) goto L68
            r7.close()     // Catch: java.io.IOException -> L67
            goto L68
        L67:
        L68:
            if (r2 == 0) goto L6f
            r2.close()     // Catch: java.io.IOException -> L6e
            goto L6f
        L6e:
        L6f:
            if (r1 == 0) goto L72
            goto L30
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.utils.ZipString.decompress(byte[]):java.lang.String");
    }
}
