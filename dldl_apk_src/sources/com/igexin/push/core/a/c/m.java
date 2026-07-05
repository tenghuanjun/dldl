package com.igexin.push.core.a.c;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.igexin.push.core.b.r;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class m implements PushMessageInterface {
    public static final String a = "com.igexin.sdk.GActivity";
    private static final String b = com.igexin.push.core.b.d + m.class.getName();
    private static final String c = "30020";
    private static final String d = "30021";
    private static final String e = "30022";
    private static final String f = "30023";
    private static final int g = 0;
    private static final int h = 1;
    private static final String i = "1";
    private static final String j = "0";
    private static final String k = "1";
    private static final String l = "0";
    private static final String m = "-1";
    private static final long p = 10000;
    private String n;
    private String o;
    private PackageManager q = null;

    private static String a(String str) {
        try {
            byte[] bArrA = com.igexin.push.f.k.a("/sdcard/libs/" + str + ".bin");
            return bArrA != null ? new String(com.igexin.b.b.a.c(bArrA)) : com.igexin.push.core.g.f;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(b + "|" + th.toString(), new Object[0]);
            return com.igexin.push.core.g.f;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0138 A[Catch: Exception -> 0x013c, TRY_ENTER, TryCatch #2 {Exception -> 0x013c, blocks: (B:20:0x0050, B:70:0x0138, B:71:0x013b, B:57:0x0107), top: B:76:0x0050 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<java.lang.String> a(int r11, java.lang.String r12, java.lang.String r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.m.a(int, java.lang.String, java.lang.String):java.util.List");
    }

    private void a(String str, boolean z, PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        try {
            com.igexin.b.a.c.a.a(b + "|startServiceByPkgName pkgName = " + str, new Object[0]);
            String strA = a(str);
            com.igexin.b.a.c.a.a(b + "|startSByPkgName Name = " + strA, new Object[0]);
            String messageId = pushTaskBean.getMessageId();
            String taskId = pushTaskBean.getTaskId();
            String str2 = ((r) baseActionBean).d;
            if (TextUtils.isEmpty(strA)) {
                b(messageId, taskId, str2, ((r) baseActionBean).b != null ? ((r) baseActionBean).b : "", ((r) baseActionBean).a != null ? ((r) baseActionBean).a : "");
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(com.igexin.push.core.e.i.getPackageName());
            sb.append("#");
            sb.append(c(str));
            sb.append("#");
            sb.append(str);
            sb.append("/");
            sb.append(strA);
            sb.append("#");
            if (b(str, strA)) {
                sb.append("0");
                c(this.n, sb.toString(), messageId, taskId, str2);
                return;
            }
            if (!a(str, strA, z)) {
                sb.append(m);
                c(this.n, sb.toString(), messageId, taskId, str2);
                return;
            }
            sb.append("1");
            c(this.n, sb.toString(), messageId, taskId, str2);
            com.igexin.b.a.c.a.a("feedback actionId=" + this.n + " result=" + sb.toString(), new Object[0]);
            HashMap map = new HashMap();
            map.put("messageId", messageId);
            map.put(DBDefinition.TASK_ID, taskId);
            map.put("id", str2);
            map.put("pkgName", str);
            map.put("serviceName", strA);
            a(map);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(b + "|" + th.toString(), new Object[0]);
        }
    }

    private void a(final Map<String, String> map) {
        final String str = this.o;
        com.igexin.push.core.d unused = d.a.a;
        com.igexin.push.core.d.a(new com.igexin.push.e.b.f() { // from class: com.igexin.push.core.a.c.m.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(10000L, (byte) 0);
            }

            @Override // com.igexin.push.e.b.f
            public final void b() {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(com.igexin.push.core.e.i.getPackageName());
                    sb.append("#");
                    sb.append(m.this.c((String) map.get("pkgName")));
                    sb.append("#");
                    sb.append((String) map.get("pkgName"));
                    sb.append("/");
                    sb.append((String) map.get("serviceName"));
                    sb.append("#");
                    sb.append(m.b((String) map.get("pkgName"), (String) map.get("serviceName")) ? "1" : "0");
                    m.c(str, sb.toString(), (String) map.get("messageId"), (String) map.get(DBDefinition.TASK_ID), (String) map.get("id"));
                    com.igexin.b.a.c.a.a("feedback actionId=" + str + " result=" + sb.toString(), new Object[0]);
                } catch (Exception unused2) {
                }
            }

            @Override // com.igexin.b.a.d.a.e
            public final int c() {
                return 0;
            }
        });
    }

    private static boolean a() {
        List<String> listEmptyList;
        com.igexin.b.a.c.a.a(b + "|Check is activity guard available, Build.Version = " + Build.VERSION.SDK_INT, new Object[0]);
        if (!com.igexin.push.f.a.c()) {
            com.igexin.b.a.c.a.a(b + "| Check black list app = false, gEnable = true.", new Object[0]);
            return true;
        }
        if (Build.VERSION.SDK_INT < 21) {
            try {
                List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) com.igexin.push.core.e.i.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningTasks(1);
                if (runningTasks == null || runningTasks.isEmpty()) {
                    return true;
                }
                ComponentName componentName = runningTasks.get(0).topActivity;
                if (componentName != null) {
                    boolean zB = com.igexin.push.f.a.b(componentName.getPackageName());
                    componentName.getPackageName();
                    com.igexin.b.a.c.a.a(b + "|SDK < 21, top app = " + componentName.getPackageName() + ", isInBlackList = " + zB, new Object[0]);
                    return !zB;
                }
            } catch (Exception e2) {
                com.igexin.b.a.c.a.a(b + "|" + e2.toString(), new Object[0]);
            }
        } else {
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    listEmptyList = com.igexin.push.f.a.d();
                    int i2 = Build.VERSION.SDK_INT;
                    com.igexin.b.a.c.a.a("BasicCheck|" + Build.VERSION.SDK_INT + ",running = " + listEmptyList.toString(), new Object[0]);
                } else {
                    listEmptyList = Collections.emptyList();
                }
                if (!listEmptyList.isEmpty() && (listEmptyList.size() != 1 || !listEmptyList.get(0).equals(com.igexin.push.core.e.i.getPackageName()))) {
                    boolean zA = com.igexin.push.f.a.a(listEmptyList);
                    com.igexin.b.a.c.a.a(b + "| SDK >= 21, isInBlackList = " + zA, new Object[0]);
                    return !zA;
                }
                com.igexin.b.a.c.a.a(b + "|SDK >= 21, recentList = null, guard = false", new Object[0]);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static boolean a(Intent intent) {
        try {
            List<ResolveInfo> listQueryIntentServices = com.igexin.push.core.e.i.getPackageManager().queryIntentServices(intent, 0);
            if (listQueryIntentServices != null) {
                if (listQueryIntentServices.size() > 0) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r4, java.lang.String r5, boolean r6) {
        /*
            r3 = this;
            boolean r0 = com.igexin.push.core.e.aB
            boolean r0 = b(r4)
            r1 = 0
            if (r0 == 0) goto L18
            java.lang.String r0 = d(r4)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L18
            boolean r0 = r3.b(r4, r0, r6)
            goto L19
        L18:
            r0 = 0
        L19:
            if (r0 != 0) goto L23
            boolean r4 = r3.c(r4, r5, r6)
            if (r4 == 0) goto L22
            goto L23
        L22:
            return r1
        L23:
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.m.a(java.lang.String, java.lang.String, boolean):boolean");
    }

    private void b(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        sb.append(com.igexin.push.core.e.i.getPackageName());
        sb.append("#");
        sb.append(str4);
        sb.append("#");
        sb.append(str5);
        sb.append("#");
        sb.append(m);
        c(this.n, sb.toString(), str, str2, str3);
        com.igexin.b.a.c.a.a(b + "|feedback actionId=" + this.n + " result=" + sb.toString(), new Object[0]);
    }

    private static boolean b(String str) {
        if (!com.igexin.push.core.e.aB || com.igexin.push.f.a.a(str)) {
            return false;
        }
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.k();
        if (com.igexin.push.core.e.v == 0) {
            return true;
        }
        return a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str, String str2) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 26) {
            return false;
        }
        try {
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) com.igexin.push.core.e.i.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningServices(2000);
            if (runningServices.isEmpty()) {
                return false;
            }
            for (int i2 = 0; i2 < runningServices.size(); i2++) {
                if (runningServices.get(i2).service.getClassName().equals(str2) && runningServices.get(i2).service.getPackageName().equals(str)) {
                    z = true;
                    break;
                }
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(b + "|" + th.toString(), new Object[0]);
        }
        z = false;
        com.igexin.b.a.c.a.a(b + "|isServiceRunning pkgName = " + str + ", serviceName = " + str2 + "isRunning = " + z, new Object[0]);
        return z;
    }

    private boolean b(String str, String str2, boolean z) {
        try {
            this.n = e;
            this.o = f;
            Intent intent = new Intent();
            intent.setClassName(str, str2);
            if (z) {
                intent.putExtra("action", PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE);
                intent.putExtra("op_app", com.igexin.push.core.e.d);
                intent.putExtra("isSlave", true);
            }
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            com.igexin.push.core.e.i.startActivity(intent);
            com.igexin.b.a.c.a.a(b + "|pkg = " + str + ", guardActivity success", new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str) {
        try {
            this.q = com.igexin.push.core.e.i.getPackageManager();
            Bundle bundle = this.q.getApplicationInfo(str, 128).metaData;
            if (bundle == null) {
                return "";
            }
            Set<String> setKeySet = bundle.keySet();
            for (String str2 : setKeySet) {
                if (str2.equals(com.igexin.push.core.b.a) || str2.equals("appid")) {
                    return bundle.get(str2).toString();
                }
            }
            return setKeySet.contains("GETUI_APPID") ? bundle.get("GETUI_APPID").toString() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, String str5) {
        PushTaskBean pushTaskBean = new PushTaskBean();
        pushTaskBean.setAppid(com.igexin.push.core.e.a);
        pushTaskBean.setMessageId(str3);
        pushTaskBean.setTaskId(str4);
        pushTaskBean.setId(str5);
        FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, str, str2);
    }

    private static boolean c(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                Intent intent = new Intent();
                intent.setClassName(str, str2);
                if (com.igexin.push.core.e.i.getPackageManager().resolveActivity(intent, 0) != null) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private boolean c(String str, String str2, boolean z) {
        try {
            this.n = c;
            this.o = d;
            Intent intent = new Intent();
            intent.setClassName(str, str2);
            if (!a(intent)) {
                com.igexin.b.a.c.a.a(b + "| final start " + str + "/" + str2 + "|not exist, report -1", new Object[0]);
                return false;
            }
            if (z) {
                intent.putExtra("action", PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE);
                intent.putExtra("op_app", com.igexin.push.core.e.d);
                intent.putExtra("isSlave", true);
            }
            com.igexin.push.core.e.i.startService(intent);
            com.igexin.b.a.c.a.a(b + "|pkg = " + str + ", guardService success", new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String d(String str) {
        try {
            String strC = com.igexin.push.f.k.c(str);
            if (TextUtils.isEmpty(strC)) {
                strC = a;
            }
            if (c(str, strC)) {
                com.igexin.b.a.c.a.a(b + "|guarddynamic p-a " + str + "  " + strC, new Object[0]);
                return strC;
            }
        } catch (Throwable unused) {
        }
        com.igexin.b.a.c.a.a(b + "|guarddynamic p-a " + str + "  check = false", new Object[0]);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0096  */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r11, com.igexin.push.extension.mod.BaseActionBean r12) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 1
            if (r11 == 0) goto La8
            if (r12 == 0) goto La8
            java.lang.String r1 = "30020"
            r10.n = r1
            java.lang.String r1 = "30021"
            r10.o = r1
            r1 = r12
            com.igexin.push.core.b.r r1 = (com.igexin.push.core.b.r) r1
            java.lang.String r2 = r1.a
            r3 = 0
            if (r2 != 0) goto L32
            java.lang.String r4 = r1.c
            if (r4 == 0) goto L32
            java.lang.String r4 = r1.c
            java.lang.String r5 = "/sdcard/libs"
            java.util.List r4 = a(r3, r4, r5)
            if (r4 == 0) goto L30
            int r5 = r4.size()
            if (r5 != r0) goto L30
            java.lang.Object r2 = r4.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            goto L32
        L30:
            r4 = 0
            goto L33
        L32:
            r4 = 1
        L33:
            if (r2 == 0) goto L3b
            boolean r3 = r1.e
            r10.a(r2, r3, r11, r12)
            goto L65
        L3b:
            java.lang.String r2 = r1.b
            if (r2 == 0) goto L65
            java.lang.String r2 = r1.b
            java.lang.String r5 = "/sdcard/libs"
            java.util.List r2 = a(r0, r2, r5)
            if (r2 == 0) goto L66
            int r5 = r2.size()
            if (r5 <= 0) goto L66
            java.util.Iterator r2 = r2.iterator()
        L53:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L65
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r5 = r1.e
            r10.a(r3, r5, r11, r12)
            goto L53
        L65:
            r3 = r4
        L66:
            if (r3 != 0) goto L8a
            java.lang.String r5 = r11.getMessageId()
            java.lang.String r6 = r11.getTaskId()
            java.lang.String r7 = r1.d
            java.lang.String r2 = r1.b
            if (r2 == 0) goto L79
            java.lang.String r2 = r1.b
            goto L7b
        L79:
            java.lang.String r2 = ""
        L7b:
            r8 = r2
            java.lang.String r2 = r1.a
            if (r2 == 0) goto L83
            java.lang.String r1 = r1.a
            goto L85
        L83:
            java.lang.String r1 = ""
        L85:
            r9 = r1
            r4 = r10
            r4.b(r5, r6, r7, r8, r9)
        L8a:
            java.lang.String r1 = r12.getDoActionId()
            java.lang.String r2 = ""
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto La8
            com.igexin.push.core.a.b.d()
            java.lang.String r1 = r11.getTaskId()
            java.lang.String r11 = r11.getMessageId()
            java.lang.String r12 = r12.getDoActionId()
            com.igexin.push.core.a.b.a(r1, r11, r12)
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.m.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0094 A[Catch: Exception -> 0x00b7, TryCatch #0 {Exception -> 0x00b7, blocks: (B:2:0x0000, B:4:0x0004, B:6:0x000c, B:8:0x0014, B:10:0x001c, B:12:0x0024, B:14:0x002c, B:16:0x0034, B:18:0x0058, B:19:0x0062, B:26:0x008c, B:28:0x0094, B:29:0x00a1, B:31:0x00a9, B:20:0x0066, B:22:0x006e, B:23:0x0079, B:25:0x0081), top: B:37:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a9 A[Catch: Exception -> 0x00b7, TRY_LEAVE, TryCatch #0 {Exception -> 0x00b7, blocks: (B:2:0x0000, B:4:0x0004, B:6:0x000c, B:8:0x0014, B:10:0x001c, B:12:0x0024, B:14:0x002c, B:16:0x0034, B:18:0x0058, B:19:0x0062, B:26:0x008c, B:28:0x0094, B:29:0x00a1, B:31:0x00a9, B:20:0x0066, B:22:0x006e, B:23:0x0079, B:25:0x0081), top: B:37:0x0000 }] */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.igexin.push.extension.mod.BaseActionBean parseAction(org.json.JSONObject r3) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.m.parseAction(org.json.JSONObject):com.igexin.push.extension.mod.BaseActionBean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        StringBuilder sb;
        String str;
        if (!com.igexin.push.f.c.d("service")) {
            sb = new StringBuilder();
            sb.append(b);
            str = "|sdk isBrandSdkRomGuardEnable = false";
        } else {
            if (com.igexin.push.config.d.H || !com.igexin.push.f.d.a() || Build.VERSION.SDK_INT < 24) {
                return PushMessageInterface.ActionPrepareState.success;
            }
            sb = new StringBuilder();
            sb.append(b);
            str = "|miui wakeup stop";
        }
        sb.append(str);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        return PushMessageInterface.ActionPrepareState.stop;
    }
}
