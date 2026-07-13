package com.volcengine.j;

import android.content.Context;
import android.os.Build;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.InternalConstants;
import com.volcengine.common.innerapi.PluginService;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f1148a;
    private static Map<String, Integer> b = new HashMap();

    static {
        if (d()) {
            String str = Build.SUPPORTED_ABIS[0];
        } else {
            String str2 = Build.CPU_ABI;
        }
        b.put(InternalConstants.ABI_arm64_v8a_str, 64);
        b.put(InternalConstants.ABI_armeabi_v7a_str, 32);
        b.put("armeabi", 32);
        b.put(InternalConstants.ABI_x86_64_str, 64);
        b.put(InternalConstants.ABI_x86_str, 32);
        b.put("mips64", 64);
        b.put("mips", 32);
        f1148a = b();
    }

    public static String a() {
        String str = f1148a;
        if (str != null) {
            return str;
        }
        String strB = b();
        f1148a = strB;
        return strB;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008b A[Catch: Exception -> 0x00b9, TryCatch #0 {Exception -> 0x00b9, blocks: (B:4:0x0016, B:6:0x0040, B:18:0x0078, B:20:0x008b, B:22:0x0099, B:24:0x00a9, B:17:0x0075, B:15:0x0066, B:9:0x0047, B:13:0x0056), top: B:30:0x0016, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a9 A[Catch: Exception -> 0x00b9, TRY_LEAVE, TryCatch #0 {Exception -> 0x00b9, blocks: (B:4:0x0016, B:6:0x0040, B:18:0x0078, B:20:0x008b, B:22:0x0099, B:24:0x00a9, B:17:0x0075, B:15:0x0066, B:9:0x0047, B:13:0x0056), top: B:30:0x0016, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(org.json.JSONObject r11) {
        /*
            java.lang.String r0 = "primaryCpuAbi"
            java.lang.String r1 = "VE_PLUGIN"
            java.lang.String r2 = "NativeLibHelper inferHostAbiAuto2, sHostAbi="
            java.lang.String r3 = "NativeLibHelper inferHostAbiAuto1, sHostAbi="
            java.lang.String r4 = ""
            java.lang.String r5 = "NativeLibHelper inferHostAbiAuto, processMode="
            java.lang.String r6 = "NativeLibHelper inferHostAbiAuto, processMode exception default="
            java.lang.String r7 = "NativeLibHelper inferHostAbiAuto, primaryCpuAbi="
            boolean r8 = d()
            if (r8 == 0) goto Lc6
            android.content.Context r8 = com.volcengine.common.SDKContext.getContext()     // Catch: java.lang.Exception -> Lb9
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo()     // Catch: java.lang.Exception -> Lb9
            java.lang.reflect.Field r9 = com.volcengine.j.k.a(r8, r0)     // Catch: java.lang.Exception -> Lb9
            r10 = 1
            r9.setAccessible(r10)     // Catch: java.lang.Exception -> Lb9
            java.lang.Object r8 = r9.get(r8)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r9.<init>(r7)     // Catch: java.lang.Exception -> Lb9
            r9.append(r8)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r7 = r9.toString()     // Catch: java.lang.Exception -> Lb9
            com.volcengine.androidcloud.common.log.AcLog.w(r1, r7)     // Catch: java.lang.Exception -> Lb9
            a(r11, r0, r8)     // Catch: java.lang.Exception -> Lb9
            if (r8 == 0) goto Lc6
            boolean r0 = e()     // Catch: java.lang.Exception -> Lb9
            r7 = 0
            if (r0 == 0) goto L73
            boolean r0 = com.volcengine.j.l$$ExternalSyntheticApiModelOutline0.m6727m()     // Catch: java.lang.Exception -> L66
            if (r0 == 0) goto L52
            r0 = 64
            r7 = 64
            goto L56
        L52:
            r0 = 32
            r7 = 32
        L56:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L66
            r0.<init>(r5)     // Catch: java.lang.Exception -> L66
            r0.append(r7)     // Catch: java.lang.Exception -> L66
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L66
            com.volcengine.androidcloud.common.log.AcLog.w(r1, r0)     // Catch: java.lang.Exception -> L66
            goto L78
        L66:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r0.<init>(r6)     // Catch: java.lang.Exception -> Lb9
            r0.append(r7)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lb9
            goto L75
        L73:
            java.lang.String r0 = "NativeLibHelper inferHostAbiAuto, processMode default=0"
        L75:
            com.volcengine.androidcloud.common.log.AcLog.w(r1, r0)     // Catch: java.lang.Exception -> Lb9
        L78:
            java.lang.String r0 = "processMode"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r5.<init>(r4)     // Catch: java.lang.Exception -> Lb9
            r5.append(r7)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Exception -> Lb9
            a(r11, r0, r4)     // Catch: java.lang.Exception -> Lb9
            if (r7 == 0) goto La9
            java.util.Map<java.lang.String, java.lang.Integer> r0 = com.volcengine.j.l.b     // Catch: java.lang.Exception -> Lb9
            java.lang.Object r0 = r0.get(r8)     // Catch: java.lang.Exception -> Lb9
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Exception -> Lb9
            int r0 = r0.intValue()     // Catch: java.lang.Exception -> Lb9
            if (r0 != r7) goto Lc6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r0.<init>(r2)     // Catch: java.lang.Exception -> Lb9
            r0.append(r8)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lb9
            com.volcengine.androidcloud.common.log.AcLog.w(r1, r0)     // Catch: java.lang.Exception -> Lb9
            return r8
        La9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r0.<init>(r3)     // Catch: java.lang.Exception -> Lb9
            r0.append(r8)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lb9
            com.volcengine.androidcloud.common.log.AcLog.w(r1, r0)     // Catch: java.lang.Exception -> Lb9
            return r8
        Lb9:
            r0 = move-exception
            java.lang.String r2 = "NativeLibHelper inferHostAbiAuto failed!"
            com.volcengine.androidcloud.common.log.AcLog.e(r1, r2, r0)
            java.lang.String r0 = "autoError"
            java.lang.String r1 = "1"
            a(r11, r0, r1)
        Lc6:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.j.l.a(org.json.JSONObject):java.lang.String");
    }

    private static Map<String, List<ZipEntry>> a(ZipFile zipFile) {
        String[] strArrSplit;
        HashMap map = new HashMap();
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        Pattern patternCompile = Pattern.compile("^lib/[^/]+/lib[^/]+.so$");
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (!zipEntryNextElement.isDirectory() && patternCompile.matcher(zipEntryNextElement.getName()).matches() && (strArrSplit = zipEntryNextElement.getName().split(File.separator)) != null && strArrSplit.length >= 2) {
                String str = strArrSplit[strArrSplit.length - 2];
                if (b.containsKey(str)) {
                    if (map.get(str) == null) {
                        map.put(str, new LinkedList());
                    }
                    ((List) map.get(str)).add(zipEntryNextElement);
                }
            }
        }
        AcLog.i(PluginService.TAG_PLUGIN, "NativeLibHelper getAllSoZipEntries, zipFile=" + zipFile.getName() + ", soEntries=" + map.toString());
        return map;
    }

    private static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String b() {
        JSONObject jSONObjectC = c();
        String strA = a(jSONObjectC);
        return strA == null ? b(jSONObjectC) : strA;
    }

    private static String b(JSONObject jSONObject) {
        HashSet hashSet;
        String[] strArr;
        try {
            Context context = SDKContext.getContext();
            ZipFile zipFile = new ZipFile(new File(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.sourceDir));
            hashSet = new HashSet(a(zipFile).keySet());
            try {
                zipFile.close();
            } catch (IOException unused) {
                AcLog.w(PluginService.TAG_PLUGIN, "NativeLibHelper inferHostAbiManual, close sourceApkZipFile error!");
            }
            strArr = d() ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        } catch (Throwable th) {
            AcLog.e(PluginService.TAG_PLUGIN, "NativeLibHelper inferHostAbiManual failed!", th);
            a(jSONObject, "manualError", "1");
        }
        if (hashSet.isEmpty()) {
            AcLog.w(PluginService.TAG_PLUGIN, "NativeLibHelper inferHostAbiManual, host source apk .so is empty, use supportedABIs[0]=" + strArr[0]);
            a(jSONObject, "supportedABI0", strArr[0]);
            return strArr[0];
        }
        for (String str : strArr) {
            if (hashSet.contains(str)) {
                AcLog.w(PluginService.TAG_PLUGIN, "NativeLibHelper inferHostAbiManual, match cpuAbi=" + str);
                a(jSONObject, "matchCpuAbi", str);
                return str;
            }
        }
        if (d()) {
            String[] strArr2 = Build.SUPPORTED_ABIS;
            a(jSONObject, "defaultABI0", strArr2[0]);
            return strArr2[0];
        }
        String str2 = Build.CPU_ABI;
        a(jSONObject, "defaultABI", str2);
        return str2;
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("primaryCpuAbi", "0");
            jSONObject.put("processMode", "0");
            jSONObject.put("supportedABI0", "0");
            jSONObject.put("matchCpuAbi", "0");
            jSONObject.put("defaultABI0", "0");
            jSONObject.put("defaultABI", "0");
            jSONObject.put("autoError", "0");
            jSONObject.put("manualError", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static boolean d() {
        return true;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
