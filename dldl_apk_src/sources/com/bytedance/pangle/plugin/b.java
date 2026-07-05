package com.bytedance.pangle.plugin;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusPluginEventCallback;
import com.bytedance.pangle.a.a;
import com.bytedance.pangle.c.b;
import com.bytedance.pangle.e.f;
import com.bytedance.pangle.e.g;
import com.bytedance.pangle.i;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.e;
import com.bytedance.pangle.util.g;
import com.bytedance.pangle.util.h;
import com.bytedance.pangle.util.l;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class b {
    private static final i a = i.a();

    static boolean a(final File file, final String str, final int i) {
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("useOpt;");
        final boolean[] zArr = {false};
        try {
            a.a(1000, 0, str, i, null);
            com.bytedance.pangle.log.a aVarA = com.bytedance.pangle.log.a.a(ZeusLogger.TAG_INSTALL, "PluginInstaller", "install:".concat(String.valueOf(str)));
            a(com.bytedance.pangle.c.b.e, b.a.r, str, i, -1L, null);
            int iB = l.a().b(str, i, "install");
            int iB2 = l.a().b(str, i, "load");
            int removeApkEntryFlag = GlobalParam.getInstance().getRemoveApkEntryFlag(str);
            if (iB > 3 || iB2 > 3) {
                removeApkEntryFlag = 0;
            }
            SharedPreferences.Editor editorEdit = l.a().a.edit();
            editorEdit.putInt("remove_entry_flag_" + str + "_" + i, removeApkEntryFlag);
            editorEdit.apply();
            l.a().c(str, i, false);
            g.a(com.bytedance.pangle.d.c.a(str, i));
            com.bytedance.pangle.a.a.a(new a.InterfaceC0014a() { // from class: com.bytedance.pangle.plugin.b.1
                @Override // com.bytedance.pangle.a.a.InterfaceC0014a
                public final void a() {
                    b.a(file, str, i, stringBuffer);
                }
            }, new a.InterfaceC0014a() { // from class: com.bytedance.pangle.plugin.b.2
                @Override // com.bytedance.pangle.a.a.InterfaceC0014a
                public final void a() throws Throwable {
                    final Map mapF = b.f(file, str, i, stringBuffer);
                    b.c(file, str, i, stringBuffer);
                    b.a(b.g(file, str, i, stringBuffer), str, i, stringBuffer);
                    if (com.bytedance.pangle.util.i.f() || com.bytedance.pangle.util.i.b()) {
                        final boolean[] zArr2 = {false};
                        com.bytedance.pangle.a.a.a(new a.InterfaceC0014a() { // from class: com.bytedance.pangle.plugin.b.2.1
                            @Override // com.bytedance.pangle.a.a.InterfaceC0014a
                            public final void a() {
                                b.a(str, i, mapF, stringBuffer);
                            }
                        }, new a.InterfaceC0014a() { // from class: com.bytedance.pangle.plugin.b.2.2
                            @Override // com.bytedance.pangle.a.a.InterfaceC0014a
                            public final void a() {
                                zArr2[0] = b.h(file, str, i, stringBuffer);
                            }
                        });
                        zArr[0] = b.a(str, i, zArr2[0], stringBuffer);
                    } else {
                        b.a(str, i, mapF, stringBuffer);
                        zArr[0] = b.a(str, i, false, stringBuffer);
                        b.a(str, i);
                    }
                }
            });
            g.a(file);
            a(com.bytedance.pangle.c.b.f, b.a.s, str, i, aVarA.a(), stringBuffer.toString());
            aVarA.a("success");
            a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, 0, str, i, null);
            return true;
        } catch (Throwable th) {
            if (th instanceof a) {
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginInstaller " + str + " install failed.", th);
            } else {
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginInstaller " + str + " install failed unknown error.", th);
                a(com.bytedance.pangle.c.b.f, b.a.t, str, i, -1L, stringBuffer.toString());
                a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -1, str, i, th);
            }
            if (zArr[0]) {
                l.a().a(str, i, "install");
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, List<ZipEntry>> f(File file, String str, int i, StringBuffer stringBuffer) {
        String str2 = "插件包包含so不符合宿主ABI类型";
        if (!GlobalParam.getInstance().checkMatchHostAbi()) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte b = 0;
        try {
            try {
                e<Boolean, Map<String, List<ZipEntry>>> eVarA = com.bytedance.pangle.d.b.a(file);
                boolean zBooleanValue = eVarA.a.booleanValue();
                Map<String, List<ZipEntry>> map = eVarA.b;
                if (zBooleanValue) {
                    return map;
                }
                throw new a(str2, b);
            } catch (Exception e) {
                a(com.bytedance.pangle.c.b.f, b.a.A, str, i, -1L, null);
                a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -5, str, i, e);
                throw new a(str2, e, b);
            }
        } finally {
            stringBuffer.append("checkMatchHostAbi cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(com.alipay.sdk.util.i.b);
        }
        stringBuffer.append("checkMatchHostAbi cost:");
        stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
        stringBuffer.append(com.alipay.sdk.util.i.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String g(File file, String str, int i, StringBuffer stringBuffer) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strB = com.bytedance.pangle.d.c.b(str, i);
        try {
            try {
                h.a(file.getAbsolutePath(), strB);
                return strB;
            } catch (Exception e) {
                a(com.bytedance.pangle.c.b.f, b.a.w, str, i, -1L, null);
                a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -6, str, i, e);
                throw new a("安装包拷贝失败", e, (byte) 0);
            }
        } finally {
            stringBuffer.append("copyApk cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(com.alipay.sdk.util.i.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Not initialized variable reg: 17, insn: 0x0240: MOVE (r9 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY]), block:B:72:0x0240 */
    /* JADX WARN: Not initialized variable reg: 17, insn: 0x0244: MOVE (r9 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY]), block:B:74:0x0244 */
    public static boolean h(File file, String str, int i, StringBuffer stringBuffer) throws Throwable {
        String str2;
        String str3;
        String str4;
        String str5;
        File file2;
        String str6 = ".dex";
        String str7 = "classes";
        String str8 = com.alipay.sdk.util.i.b;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!com.bytedance.pangle.util.i.b()) {
                str4 = com.alipay.sdk.util.i.b;
                if (com.bytedance.pangle.util.i.f()) {
                    String strC = com.bytedance.pangle.d.c.c(str, i);
                    String strB = com.bytedance.pangle.d.c.b(str, i);
                    String str9 = strC + File.separator + com.bytedance.pangle.e.b.a(strB);
                    if (com.bytedance.pangle.e.b.a(strB, strC + File.separator + com.bytedance.pangle.e.b.a(strB)) && com.bytedance.pangle.e.b.a(str9)) {
                        stringBuffer.append("dexOpt1 cost:");
                        stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
                        stringBuffer.append(str4);
                        return true;
                    }
                }
            } else {
                if (!file.exists() || str == null) {
                    throw new IOException("Could not check apk info " + file.getAbsolutePath());
                }
                ZipFile zipFile = null;
                try {
                    ZipFile zipFile2 = new ZipFile(file);
                    try {
                        ArrayList arrayList = new ArrayList();
                        File file3 = new File(com.bytedance.pangle.d.c.i(str, i));
                        com.bytedance.pangle.e.g.a(file3);
                        int i2 = 1;
                        while (true) {
                            StringBuilder sb = new StringBuilder(str7);
                            Object objValueOf = "";
                            sb.append(i2 == 1 ? "" : Integer.valueOf(i2));
                            sb.append(str6);
                            ZipEntry entry = zipFile2.getEntry(sb.toString());
                            if (entry != null) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(str7);
                                String str10 = str8;
                                if (i2 != 1) {
                                    try {
                                        objValueOf = Integer.valueOf(i2);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        zipFile = zipFile2;
                                        g.a(zipFile);
                                        throw th;
                                    }
                                }
                                sb2.append(objValueOf);
                                sb2.append(str6);
                                g.a aVar = new g.a(file3, sb2.toString());
                                String str11 = str6;
                                int i3 = 0;
                                boolean z = false;
                                while (i3 < 3 && !z) {
                                    try {
                                        com.bytedance.pangle.e.g.a(zipFile2, entry, aVar, str7);
                                        str5 = str7;
                                        file2 = file3;
                                        z = true;
                                    } catch (IOException e2) {
                                        str5 = str7;
                                        file2 = file3;
                                        ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin-MultiDex Failed to extract entry from " + aVar.getAbsolutePath(), e2);
                                    }
                                    int i4 = i3 + 1;
                                    StringBuilder sb3 = new StringBuilder("Plugin-MultiDex Extraction ");
                                    sb3.append(z ? "succeeded" : com.alipay.sdk.util.e.a);
                                    sb3.append(" '");
                                    sb3.append(aVar.getAbsolutePath());
                                    sb3.append("': length ");
                                    sb3.append(aVar.length());
                                    ZeusLogger.i(ZeusLogger.TAG_INSTALL, sb3.toString());
                                    if (!z) {
                                        aVar.delete();
                                        if (aVar.exists()) {
                                            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin-MultiDex Failed to delete corrupted secondary dex '" + aVar.getPath() + "'");
                                        }
                                    }
                                    i3 = i4;
                                    str7 = str5;
                                    file3 = file2;
                                }
                                String str12 = str7;
                                File file4 = file3;
                                if (!z) {
                                    throw new IOException("Could not create zip file " + aVar.getAbsolutePath() + " for secondary dex (" + i2 + ")");
                                }
                                arrayList.add(aVar);
                                i2++;
                                str6 = str11;
                                str8 = str10;
                                str7 = str12;
                                file3 = file4;
                            } else {
                                str4 = str8;
                                file.getName();
                                SharedPreferences.Editor editorEdit = com.bytedance.pangle.e.g.a().edit();
                                editorEdit.putInt((str + "-" + i) + ".dex.number", arrayList.size());
                                editorEdit.commit();
                                com.bytedance.pangle.util.g.a(zipFile2);
                                com.bytedance.pangle.e.b.a(Zeus.getAppApplication()).edit().putInt(str, i).apply();
                                f.a();
                                break;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            stringBuffer.append("dexOpt1 cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(str4);
            return false;
        } catch (Exception e3) {
            e = e3;
            str8 = str3;
            a(com.bytedance.pangle.c.b.f, b.a.z, str, i, -1L, null);
            throw new a("dexOpt1失败", e, (byte) 0);
        } catch (Throwable th5) {
            th = th5;
            str8 = str2;
            stringBuffer.append("dexOpt1 cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(str8);
            throw th;
        }
    }

    private static void a(String str, int i, String str2, int i2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt(MonitorConstants.STATUS_CODE, com.bytedance.pangle.log.b.a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", com.bytedance.pangle.log.b.a(str2));
            jSONObject.putOpt("version_code", com.bytedance.pangle.log.b.a(Integer.valueOf(i2)));
            jSONObject3.putOpt(MediationConstant.EXTRA_DURATION, Integer.valueOf(com.bytedance.pangle.log.b.b(Long.valueOf(j))));
            jSONObject2.putOpt(com.igexin.push.core.b.Z, com.bytedance.pangle.log.b.a(str3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.bytedance.pangle.c.b.a().a(str, jSONObject, jSONObject3, jSONObject2);
    }

    public static class a extends IOException {
        /* synthetic */ a(String str, byte b) {
            this(str);
        }

        /* synthetic */ a(String str, Throwable th, byte b) {
            this(str, th);
        }

        private a(String str) {
            super(str);
        }

        private a(String str, Throwable th) {
            super(str, th);
        }
    }

    static /* synthetic */ void a(File file, String str, int i, StringBuffer stringBuffer) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                if (com.bytedance.pangle.g.e.a(file.getAbsolutePath(), str)) {
                    return;
                } else {
                    throw new RuntimeException("安装包签名校验失败[1]");
                }
            } catch (Exception e) {
                a(com.bytedance.pangle.c.b.f, b.a.u, str, i, -1L, null);
                a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -3, str, i, e);
                throw new a(e.getMessage(), e, (byte) 0);
            }
        } finally {
            stringBuffer.append("checkSignature cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(com.alipay.sdk.util.i.b);
        }
        stringBuffer.append("checkSignature cost:");
        stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
        stringBuffer.append(com.alipay.sdk.util.i.b);
    }

    static /* synthetic */ void c(File file, String str, int i, StringBuffer stringBuffer) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte b = 0;
        try {
            try {
                PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 4096);
                PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 4096);
                List listAsList = Arrays.asList(packageInfo.requestedPermissions);
                if (packageArchiveInfo.requestedPermissions != null && packageArchiveInfo.requestedPermissions.length > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (String str2 : packageArchiveInfo.requestedPermissions) {
                        if (!listAsList.contains(str2)) {
                            arrayList.add(str2);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        ZeusLogger.w("PluginInstaller", "The following permissions are declared in the plugin but not in the host: ".concat(String.valueOf(arrayList)));
                        if (GlobalParam.getInstance().checkPermission()) {
                            throw new a("The following permissions are declared in the plugin but not in the host: ".concat(String.valueOf(arrayList)), b);
                        }
                    }
                }
            } catch (Exception e) {
                a(com.bytedance.pangle.c.b.f, b.a.v, str, i, -1L, null);
                a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -4, str, i, e);
                throw new a("安装包权限校验失败", e, b);
            }
        } finally {
            stringBuffer.append("checkPermissions cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(com.alipay.sdk.util.i.b);
        }
    }

    static /* synthetic */ void a(String str, String str2, int i, StringBuffer stringBuffer) throws a {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        byte b = 0;
        int iA = new com.bytedance.pangle.res.a.c().a(new File(str), false, sb);
        stringBuffer.append(iA == 100 ? "modifyRes" : "noModifyRes");
        stringBuffer.append(" cost:");
        stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
        stringBuffer.append(com.alipay.sdk.util.i.b);
        if (iA == 100 || iA == 200) {
            return;
        }
        String string = sb.toString();
        a(com.bytedance.pangle.c.b.f, b.a.B, str2, i, -1L, string);
        a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -2, str2, i, null);
        throw new a("modifyRes failed. result = " + iA + ", errorLog = " + string, b);
    }

    static /* synthetic */ void a(String str, int i, Map map, StringBuffer stringBuffer) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                com.bytedance.pangle.d.b.a(new File(com.bytedance.pangle.d.c.b(str, i)), new File(com.bytedance.pangle.d.c.d(str, i)), str, (Map<String, List<ZipEntry>>) map);
            } catch (Exception e) {
                a(com.bytedance.pangle.c.b.f, b.a.x, str, i, -1L, com.bytedance.pangle.log.b.a((Object) e));
                a.a(ZeusPluginEventCallback.EVENT_FINISH_INSTALLATION, -7, str, i, e);
                throw new a("安装包动态库拷贝失败", e, (byte) 0);
            }
        } finally {
            stringBuffer.append("copySo cost:");
            stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
            stringBuffer.append(com.alipay.sdk.util.i.b);
        }
    }

    static /* synthetic */ boolean a(String str, int i, boolean z, StringBuffer stringBuffer) {
        int iB = l.a().b(str, i);
        boolean z2 = (iB & 1) != 0;
        boolean z3 = (iB & 2) != 0;
        if (!z2 && !z3) {
            stringBuffer.append("removeEntry skip;");
            return false;
        }
        boolean z4 = z && z2;
        String strB = com.bytedance.pangle.d.c.b(str, i);
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zA = com.bytedance.pangle.util.b.b.a(strB, z4, z3, str, i, 1);
        stringBuffer.append("removeEntry cost:");
        stringBuffer.append(System.currentTimeMillis() - jCurrentTimeMillis);
        stringBuffer.append(com.alipay.sdk.util.i.b);
        return zA;
    }

    static /* synthetic */ void a(String str, int i) throws a {
        byte b = 0;
        try {
            if (com.bytedance.pangle.util.i.e() || com.bytedance.pangle.util.i.g()) {
                l.a().b(str, i, false);
                com.bytedance.pangle.e.b.a(Zeus.getAppApplication()).edit().putInt(str, i).apply();
                f.a();
            }
        } catch (Exception e) {
            a(com.bytedance.pangle.c.b.f, b.a.z, str, i, -1L, null);
            throw new a("dexOpt2失败", e, b);
        }
    }
}
