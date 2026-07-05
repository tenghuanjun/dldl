package com.bytedance.pangle.util.a;

import android.text.TextUtils;
import com.bytedance.pangle.util.f;
import com.igexin.push.core.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public static String[] a(File file) throws Throwable {
        String strA;
        String str;
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        try {
            zipFile = new ZipFile(file);
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                if (zipEntryNextElement.getName().startsWith("META-INF/")) {
                    if (zipEntryNextElement.getName().endsWith("MANIFEST.MF")) {
                        z3 = true;
                    } else if (zipEntryNextElement.getName().endsWith(".SF")) {
                        z = true;
                    } else if (zipEntryNextElement.getName().endsWith(".RSA")) {
                        z2 = true;
                    }
                    arrayList.add(Long.valueOf(zipEntryNextElement.getCrc()));
                }
            }
            Collections.sort(arrayList, Collections.reverseOrder());
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append((Long) it.next());
            }
            if (z3 && z && z2) {
                strA = f.a(sb.toString());
                str = "";
            } else {
                str = "without v1 signature.";
                strA = "";
            }
            c.a(zipFile);
        } catch (Exception unused2) {
            zipFile2 = zipFile;
            c.a(zipFile2);
            strA = "";
            str = strA;
        } catch (Throwable th2) {
            th = th2;
            zipFile2 = zipFile;
            c.a(zipFile2);
            throw th;
        }
        String[] strArr = new String[3];
        strArr[0] = strA;
        strArr[1] = TextUtils.isEmpty(strA) ? "" : g.e;
        strArr[2] = str;
        return strArr;
    }
}
