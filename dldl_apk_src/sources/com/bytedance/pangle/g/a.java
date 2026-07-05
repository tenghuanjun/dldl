package com.bytedance.pangle.g;

import android.content.pm.Signature;
import com.plugin.core.manifest.AndroidManifestConstants;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private static final AtomicReference<byte[]> a = new AtomicReference<>();

    public static o a(String str) throws Throwable {
        JarFile jarFile;
        JarFile jarFile2 = null;
        try {
            try {
                jarFile = new JarFile(str);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (RuntimeException e2) {
            e = e2;
        } catch (GeneralSecurityException e3) {
            e = e3;
        }
        try {
            ArrayList<JarEntry> arrayList = new ArrayList();
            JarEntry jarEntry = jarFile.getJarEntry(AndroidManifestConstants.ANDROID_MANIFEST_FILE_NAME);
            if (jarEntry == null) {
                throw new q(1, "Package " + str + " has no manifest");
            }
            Certificate[][] certificateArrA = a(jarFile, jarEntry);
            if (com.bytedance.pangle.util.d.a(certificateArrA)) {
                throw new q(4, "Package " + str + " has no certificates at entry AndroidManifest.xml");
            }
            Signature[] signatureArrA = d.a(certificateArrA);
            Enumeration<JarEntry> enumerationEntries = jarFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                if (!jarEntryNextElement.isDirectory()) {
                    String name = jarEntryNextElement.getName();
                    if (!name.startsWith("META-INF/") && !name.equals(AndroidManifestConstants.ANDROID_MANIFEST_FILE_NAME)) {
                        arrayList.add(jarEntryNextElement);
                    }
                }
            }
            for (JarEntry jarEntry2 : arrayList) {
                Certificate[][] certificateArrA2 = a(jarFile, jarEntry2);
                if (com.bytedance.pangle.util.d.a(certificateArrA2)) {
                    throw new q(4, "Package " + str + " has no certificates at entry " + jarEntry2.getName());
                }
                if (!o.a(signatureArrA, d.a(certificateArrA2))) {
                    throw new q(3, "Package " + str + " has mismatched certificates at entry " + jarEntry2.getName());
                }
            }
            o oVar = new o(signatureArrA, 1, null, null, null);
            try {
                jarFile.close();
            } catch (Exception unused) {
            }
            return oVar;
        } catch (IOException e4) {
            e = e4;
            throw new q(4, "Failed to collect certificates from ".concat(String.valueOf(str)), e);
        } catch (RuntimeException e5) {
            e = e5;
            throw new q(4, "Failed to collect certificates from ".concat(String.valueOf(str)), e);
        } catch (GeneralSecurityException e6) {
            e = e6;
            throw new q(2, "Failed to collect certificates from ".concat(String.valueOf(str)), e);
        } catch (Throwable th2) {
            th = th2;
            jarFile2 = jarFile;
            if (jarFile2 != null) {
                try {
                    jarFile2.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0034: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:17:0x0034 */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.security.cert.Certificate[][] a(java.util.jar.JarFile r7, java.util.jar.JarEntry r8) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.InputStream r1 = r7.getInputStream(r8)     // Catch: java.lang.Throwable -> L3a java.lang.RuntimeException -> L3c java.io.IOException -> L3e
            java.util.concurrent.atomic.AtomicReference<byte[]> r2 = com.bytedance.pangle.g.a.a     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            java.lang.Object r0 = r2.getAndSet(r0)     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            byte[] r0 = (byte[]) r0     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            if (r0 != 0) goto L13
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
        L13:
            int r2 = r0.length     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            r3 = 0
            int r2 = r1.read(r0, r3, r2)     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            r4 = -1
            if (r2 != r4) goto L13
            java.util.concurrent.atomic.AtomicReference<byte[]> r2 = com.bytedance.pangle.g.a.a     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            r2.set(r0)     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            r0 = 1
            java.security.cert.Certificate[][] r0 = new java.security.cert.Certificate[r0][]     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            java.security.cert.Certificate[] r2 = r8.getCertificates()     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            r0[r3] = r2     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36 java.io.IOException -> L38
            if (r1 == 0) goto L32
            r1.close()     // Catch: java.lang.RuntimeException -> L30 java.lang.Exception -> L32
            goto L32
        L30:
            r7 = move-exception
            throw r7
        L32:
            return r0
        L33:
            r7 = move-exception
            r0 = r1
            goto L63
        L36:
            r0 = move-exception
            goto L42
        L38:
            r0 = move-exception
            goto L42
        L3a:
            r7 = move-exception
            goto L63
        L3c:
            r1 = move-exception
            goto L3f
        L3e:
            r1 = move-exception
        L3f:
            r6 = r1
            r1 = r0
            r0 = r6
        L42:
            com.bytedance.pangle.g.q r2 = new com.bytedance.pangle.g.q     // Catch: java.lang.Throwable -> L33
            r3 = 5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "Failed reading "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r8 = r8.getName()     // Catch: java.lang.Throwable -> L33
            r4.append(r8)     // Catch: java.lang.Throwable -> L33
            java.lang.String r8 = " in "
            r4.append(r8)     // Catch: java.lang.Throwable -> L33
            r4.append(r7)     // Catch: java.lang.Throwable -> L33
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L33
            r2.<init>(r3, r7, r0)     // Catch: java.lang.Throwable -> L33
            throw r2     // Catch: java.lang.Throwable -> L33
        L63:
            if (r0 == 0) goto L6b
            r0.close()     // Catch: java.lang.RuntimeException -> L69 java.lang.Exception -> L6b
            goto L6b
        L69:
            r7 = move-exception
            throw r7
        L6b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.g.a.a(java.util.jar.JarFile, java.util.jar.JarEntry):java.security.cert.Certificate[][]");
    }
}
