package com.bytedance.pangle.plugin;

import android.os.SystemClock;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.c.b;
import com.bytedance.pangle.f.a.e;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.g;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class a implements Runnable {
    private File a;
    private final String b;

    a(String str, File file) {
        this.a = file;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a();
    }

    private void b() {
        int i = 3;
        while (i > 0) {
            i--;
            try {
                File file = new File(this.a.getAbsolutePath() + "_unzip");
                if (file.exists()) {
                    file.delete();
                    file.mkdirs();
                }
                g.b(this.a.getAbsolutePath(), file.getAbsolutePath());
                File[] fileArrListFiles = file.listFiles();
                File file2 = this.a;
                if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                    file2 = fileArrListFiles[0];
                }
                File fileA = a(file2);
                if (fileA != null && fileA.exists() && fileA.isFile()) {
                    this.a = fileA;
                    ZeusLogger.d("Plugin install : unZip count : " + (3 - i));
                    return;
                }
            } catch (Exception e) {
                ZeusLogger.errReport(ZeusLogger.TAG_INIT, "Plugin install : unZip file failed !!!", e);
                e.printStackTrace();
            }
        }
    }

    private static File a(File file) {
        if (file.exists() || file.getParent() == null) {
            return file;
        }
        File[] fileArrListFiles = new File(file.getParent()).listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return null;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.exists() && file2.getName().endsWith(".apk")) {
                return file2;
            }
        }
        return file;
    }

    private static void a(String str, int i, String str2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt(MonitorConstants.STATUS_CODE, com.bytedance.pangle.log.b.a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", com.bytedance.pangle.log.b.a(str2));
            jSONObject.putOpt("version_code", com.bytedance.pangle.log.b.a((Object) 0));
            jSONObject3.putOpt(MediationConstant.EXTRA_DURATION, Integer.valueOf(com.bytedance.pangle.log.b.b(Long.valueOf(j))));
            jSONObject2.putOpt(com.igexin.push.core.b.Z, com.bytedance.pangle.log.b.a(str3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.bytedance.pangle.c.b.a().a(str, jSONObject, jSONObject3, jSONObject2);
    }

    final boolean a() {
        if (g.b(this.a)) {
            ZeusLogger.d("Plugin install : start unZip file ~~~~");
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            b();
            a(com.bytedance.pangle.c.b.h, b.a.G, this.b, SystemClock.elapsedRealtime() - jElapsedRealtime, "");
            ZeusLogger.d("Plugin install : start install from unZip ~~~~");
        } else {
            ZeusLogger.d("Plugin install : start install without unZip ~~~~");
        }
        e eVarA = com.bytedance.pangle.f.a.d.a(this.a);
        if (eVarA == null) {
            ZeusPluginStateListener.postStateChange(this.b, 7, " read local file package info failed !!! pluginPkg = " + this.b + " mApkFile.exists = " + this.a.exists());
            StringBuilder sb = new StringBuilder("PluginInstallRunnable read local file package info failed !!! pluginPkg = ");
            sb.append(this.b);
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, sb.toString());
            return false;
        }
        Plugin plugin = PluginManager.getInstance().getPlugin(eVarA.a);
        if (plugin == null) {
            ZeusPluginStateListener.postStateChange(this.b, 7, " plugin == null !!! pluginPkg = " + this.b);
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginInstallRunnable cannot query valid plugin !!! packageName = " + eVarA.a);
            return false;
        }
        boolean zInstall = plugin.install(this.a, eVarA);
        if (zInstall) {
            ZeusPluginStateListener.postStateChange(eVarA.a, 6, new Object[0]);
        } else {
            ZeusPluginStateListener.postStateChange(eVarA.a, 7, "Internal error.");
        }
        return zInstall;
    }
}
