package com.igexin.push.core.a.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class i implements PushMessageInterface {
    private static final String a = "com.igexin.push.core.a.c.i";

    private static void a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(packageInfo.packageName);
            ResolveInfo next = context.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
            if (next != null) {
                String str2 = next.activityInfo.packageName;
                String str3 = next.activityInfo.name;
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setFlags(270532608);
                intent2.setComponent(new ComponentName(str2, str3));
                context.startActivity(intent2);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0044 A[Catch: Exception -> 0x00d2, TRY_ENTER, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005f, B:18:0x0068, B:20:0x006e, B:21:0x0079, B:22:0x007d, B:23:0x0081, B:25:0x009a, B:27:0x00a2, B:30:0x00ae, B:32:0x00b4, B:33:0x00c0, B:35:0x00c4), top: B:39:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0081 A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005f, B:18:0x0068, B:20:0x006e, B:21:0x0079, B:22:0x007d, B:23:0x0081, B:25:0x009a, B:27:0x00a2, B:30:0x00ae, B:32:0x00b4, B:33:0x00c0, B:35:0x00c4), top: B:39:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ae A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005f, B:18:0x0068, B:20:0x006e, B:21:0x0079, B:22:0x007d, B:23:0x0081, B:25:0x009a, B:27:0x00a2, B:30:0x00ae, B:32:0x00b4, B:33:0x00c0, B:35:0x00c4), top: B:39:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c0 A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005f, B:18:0x0068, B:20:0x006e, B:21:0x0079, B:22:0x007d, B:23:0x0081, B:25:0x009a, B:27:0x00a2, B:30:0x00ae, B:32:0x00b4, B:33:0x00c0, B:35:0x00c4), top: B:39:0x0042 }] */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r9, com.igexin.push.extension.mod.BaseActionBean r10) {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.i.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            com.igexin.push.core.b.k kVar = new com.igexin.push.core.b.k();
            kVar.setType(com.igexin.push.core.b.o);
            kVar.setActionId(jSONObject.getString("actionid"));
            kVar.setDoActionId(jSONObject.getString("do"));
            if (jSONObject.has("appstartupid")) {
                kVar.a = jSONObject.getJSONObject("appstartupid").getString("android");
            }
            if (jSONObject.has("is_autostart")) {
                kVar.d = jSONObject.getString("is_autostart");
            }
            if (jSONObject.has("appid")) {
                kVar.b = jSONObject.getString("appid");
            }
            if (jSONObject.has("noinstall_action")) {
                kVar.c = jSONObject.getString("noinstall_action");
            }
            return kVar;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
