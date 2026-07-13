package com.volcengine.common.plugin;

import android.text.TextUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.j.i;
import com.volcengine.j.k;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
class PluginConfig {
    public boolean builtin = true;
    public String check_plugin_dex_ready_class;
    public String check_plugin_so_ready_method;
    public String config_version;
    public String cpu_abi;
    public List<String> dex_list;
    public String download_url;
    public String md5;
    public String plugin_name;
    public String plugin_version;
    public List<String> so_list;

    PluginConfig() {
    }

    public boolean checkBuiltin() {
        return this.builtin;
    }

    public boolean checkHasInstalled(ClassLoader classLoader, List<Throwable> list) {
        boolean z;
        try {
            k.a(classLoader, this.check_plugin_dex_ready_class);
            z = true;
        } catch (Throwable th) {
            if (list != null) {
                list.add(th);
            }
            z = false;
        }
        AcLog.v(PluginService.TAG_PLUGIN, "checkHasInstalled: " + this.plugin_name + " ret=" + z);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean checkPluginFilesExist(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            java.lang.String r0 = r5.md5
            boolean r0 = android.text.TextUtils.equals(r0, r8)
            r1 = 0
            if (r0 != 0) goto L23
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "md5 diff: "
            r6.<init>(r7)
            java.lang.String r7 = r5.md5
            r6.append(r7)
            java.lang.String r7 = "-->"
            r6.append(r7)
            r6.append(r8)
        L1d:
            java.lang.String r6 = r6.toString()
            goto L9d
        L23:
            java.util.List<java.lang.String> r8 = r5.dex_list
            java.lang.String r0 = " not exists"
            if (r8 == 0) goto L61
            java.util.Iterator r8 = r8.iterator()
        L2d:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L61
            java.lang.Object r2 = r8.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            boolean r3 = com.volcengine.j.i.d(r3)
            if (r3 != 0) goto L2d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "dex file "
            r6.<init>(r8)
            r6.append(r2)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r8 = 0
            goto L65
        L61:
            r6 = 1
            r8 = 0
            r6 = r8
            r8 = 1
        L65:
            java.util.List<java.lang.String> r2 = r5.so_list
            if (r2 == 0) goto L9c
            java.util.Iterator r2 = r2.iterator()
        L6d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L9c
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r7)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            boolean r4 = com.volcengine.j.i.d(r4)
            if (r4 != 0) goto L6d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "so file "
            r6.<init>(r7)
            r6.append(r3)
            r6.append(r0)
            goto L1d
        L9c:
            r1 = r8
        L9d:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "checkPluginFilesExist: "
            r7.<init>(r8)
            java.lang.String r8 = r5.plugin_name
            r7.append(r8)
            java.lang.String r8 = ", ret="
            r7.append(r8)
            r7.append(r1)
            java.lang.String r8 = ", msg="
            r7.append(r8)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "VE_PLUGIN"
            com.volcengine.androidcloud.common.log.AcLog.v(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.common.plugin.PluginConfig.checkPluginFilesExist(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public boolean checkValidity() {
        boolean z = (TextUtils.isEmpty(this.plugin_name) || TextUtils.isEmpty(this.download_url) || TextUtils.isEmpty(this.plugin_version) || TextUtils.isEmpty(this.md5)) ? false : true;
        AcLog.v(PluginService.TAG_PLUGIN, "checkValidity: " + this.plugin_name + " ret=" + z);
        return z;
    }

    public void deletePluginFiles(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.dex_list;
        if (list != null) {
            for (String str3 : list) {
                if (i.b(str + str3)) {
                    sb.append(str3);
                    sb.append(",");
                }
            }
        }
        List<String> list2 = this.so_list;
        if (list2 != null) {
            for (String str4 : list2) {
                if (i.b(str2 + str4)) {
                    sb.append(str4);
                    sb.append(",");
                }
            }
        }
        AcLog.v(PluginService.TAG_PLUGIN, "deletePluginFiles: " + this.plugin_name + StringUtils.SPACE + ((Object) sb));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PluginConfig pluginConfig = (PluginConfig) obj;
        return Objects.equals(this.config_version, pluginConfig.config_version) && Objects.equals(this.plugin_name, pluginConfig.plugin_name) && Objects.equals(this.plugin_version, pluginConfig.plugin_version) && Objects.equals(this.cpu_abi, pluginConfig.cpu_abi) && Objects.equals(this.download_url, pluginConfig.download_url) && Objects.equals(this.md5, pluginConfig.md5);
    }

    public int hashCode() {
        return Objects.hash(this.config_version, this.plugin_name, this.plugin_version, this.cpu_abi, this.download_url, this.md5);
    }

    public String toString() {
        return "PluginConfig{config_version='" + this.config_version + "', plugin_name='" + this.plugin_name + "', plugin_version='" + this.plugin_version + "'}";
    }
}
