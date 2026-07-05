package com.igexin.push.core;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.main.FeedbackImpl;
import com.mobile.auth.gatewayauth.Constant;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class o {
    private static final String b = "PushMessageExecutor";
    private static Set<String> d;
    private static volatile o g;
    private final Map<String, PushMessageInterface> c;
    protected HashMap<String, String> a = new HashMap<>();
    private int e = -1;
    private final Map<String, String> f = new ConcurrentHashMap();

    private o() {
        d = new HashSet();
        this.c = new HashMap();
        d.add(b.p);
        d.add(b.l);
        d.add(b.m);
        d.add(b.n);
        d.add(b.o);
        d.add("null");
        d.add(b.s);
        d.add(b.q);
        d.add(b.r);
        d.add(b.t);
        d.add(b.u);
        d.add(b.v);
    }

    public static o a() {
        if (g == null) {
            synchronized (o.class) {
                if (g == null) {
                    g = new o();
                }
            }
        }
        return g;
    }

    private PushMessageInterface a(String str) {
        PushMessageInterface pushMessageInterface;
        Map<String, PushMessageInterface> map;
        String str2;
        PushMessageInterface fVar;
        ClassLoader classLoaderB;
        Class<?> clsLoadClass;
        if (this.c.containsKey(str)) {
            return this.c.get(str);
        }
        PushMessageInterface pushMessageInterface2 = null;
        if (!this.a.containsKey(str) || (classLoaderB = e.b(str)) == null || (clsLoadClass = classLoaderB.loadClass(this.a.get(str))) == null) {
            pushMessageInterface = null;
        } else {
            pushMessageInterface = (PushMessageInterface) clsLoadClass.newInstance();
            try {
                this.c.put(str, pushMessageInterface);
            } catch (Throwable unused) {
            }
        }
        if (pushMessageInterface != null) {
            return pushMessageInterface;
        }
        if (!TextUtils.isEmpty(str) && d.contains(str) && (pushMessageInterface2 = this.c.get(str)) == null) {
            switch (str) {
                case "goto":
                    map = this.c;
                    str2 = b.p;
                    fVar = new com.igexin.push.core.a.c.f();
                    break;
                case "notification":
                    map = this.c;
                    str2 = b.l;
                    fVar = new com.igexin.push.core.a.c.g();
                    break;
                case "terminatetask":
                    map = this.c;
                    str2 = b.m;
                    fVar = new com.igexin.push.core.a.c.l();
                    break;
                case "startmyactivity":
                    map = this.c;
                    str2 = b.n;
                    fVar = new com.igexin.push.core.a.c.j();
                    break;
                case "startapp":
                    map = this.c;
                    str2 = b.o;
                    fVar = new com.igexin.push.core.a.c.i();
                    break;
                case "null":
                    map = this.c;
                    str2 = "null";
                    fVar = new com.igexin.push.core.a.c.e();
                    break;
                case "wakeupsdk":
                    map = this.c;
                    str2 = b.s;
                    fVar = new com.igexin.push.core.a.c.m();
                    break;
                case "startweb":
                    map = this.c;
                    str2 = b.q;
                    fVar = new com.igexin.push.core.a.c.k();
                    break;
                case "checkapp":
                    map = this.c;
                    str2 = b.r;
                    fVar = new com.igexin.push.core.a.c.a();
                    break;
                case "enablelog":
                    map = this.c;
                    str2 = b.u;
                    fVar = new com.igexin.push.core.a.c.d();
                    break;
                case "disablelog":
                    map = this.c;
                    str2 = b.v;
                    fVar = new com.igexin.push.core.a.c.c();
                    break;
                case "cleanext":
                    map = this.c;
                    str2 = b.t;
                    fVar = new com.igexin.push.core.a.c.b();
                    break;
                default:
                    pushMessageInterface2 = this.c.get(str);
                    break;
            }
            map.put(str2, fVar);
            pushMessageInterface2 = this.c.get(str);
        }
        return pushMessageInterface2;
    }

    private static void a(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Integer.valueOf(i));
        d.a.a.j.a(b.Z, contentValues, new String[]{"taskid"}, new String[]{str});
    }

    private void a(ContentValues contentValues) {
        try {
            if (this.e == -1) {
                this.e = com.igexin.push.f.f.a();
            }
            if (this.e < 1000) {
                if (d.a.a.j.a(b.Z, contentValues)) {
                    this.e++;
                    return;
                }
                return;
            }
            int iA = d.a.a.j.a(b.Z, "id IN (SELECT id from message where status IS NULL or status=1 or status=2 order by id asc limit 250)");
            this.e -= iA;
            if (iA < 250) {
                this.e -= d.a.a.j.a(b.Z, "id IN (SELECT id from message where status=0 order by id asc limit " + (250 - iA) + ")");
            }
            if (d.a.a.j.a(b.Z, contentValues)) {
                this.e++;
            }
        } catch (Throwable unused) {
        }
    }

    private PushMessageInterface b(String str) {
        Map<String, PushMessageInterface> map;
        String str2;
        PushMessageInterface fVar;
        if (TextUtils.isEmpty(str) || !d.contains(str)) {
            return null;
        }
        PushMessageInterface pushMessageInterface = this.c.get(str);
        if (pushMessageInterface != null) {
            return pushMessageInterface;
        }
        switch (str) {
            case "goto":
                map = this.c;
                str2 = b.p;
                fVar = new com.igexin.push.core.a.c.f();
                break;
            case "notification":
                map = this.c;
                str2 = b.l;
                fVar = new com.igexin.push.core.a.c.g();
                break;
            case "terminatetask":
                map = this.c;
                str2 = b.m;
                fVar = new com.igexin.push.core.a.c.l();
                break;
            case "startmyactivity":
                map = this.c;
                str2 = b.n;
                fVar = new com.igexin.push.core.a.c.j();
                break;
            case "startapp":
                map = this.c;
                str2 = b.o;
                fVar = new com.igexin.push.core.a.c.i();
                break;
            case "null":
                map = this.c;
                str2 = "null";
                fVar = new com.igexin.push.core.a.c.e();
                break;
            case "wakeupsdk":
                map = this.c;
                str2 = b.s;
                fVar = new com.igexin.push.core.a.c.m();
                break;
            case "startweb":
                map = this.c;
                str2 = b.q;
                fVar = new com.igexin.push.core.a.c.k();
                break;
            case "checkapp":
                map = this.c;
                str2 = b.r;
                fVar = new com.igexin.push.core.a.c.a();
                break;
            case "enablelog":
                map = this.c;
                str2 = b.u;
                fVar = new com.igexin.push.core.a.c.d();
                break;
            case "disablelog":
                map = this.c;
                str2 = b.v;
                fVar = new com.igexin.push.core.a.c.c();
                break;
            case "cleanext":
                map = this.c;
                str2 = b.t;
                fVar = new com.igexin.push.core.a.c.b();
                break;
            default:
                return this.c.get(str);
        }
        map.put(str2, fVar);
        return this.c.get(str);
    }

    private static void b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        Message messageObtain = Message.obtain();
        messageObtain.what = b.U;
        messageObtain.obj = bundle;
        d.a.a.a(messageObtain);
    }

    private static void b(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("condition");
            HashMap map = new HashMap();
            if (jSONObject2.has("wifi")) {
                map.put("wifi", jSONObject2.getString("wifi"));
            }
            if (jSONObject2.has("screenOn")) {
                map.put("screenOn", jSONObject2.getString("screenOn"));
            }
            if (jSONObject2.has("ssid")) {
                map.put("ssid", jSONObject2.getString("ssid"));
                if (jSONObject2.has("bssid")) {
                    map.put("bssid", jSONObject2.getString("bssid"));
                }
            }
            if (jSONObject2.has(MediationConstant.EXTRA_DURATION)) {
                String string = jSONObject2.getString(MediationConstant.EXTRA_DURATION);
                if (string.contains("-")) {
                    int iIndexOf = string.indexOf("-");
                    String strSubstring = string.substring(0, iIndexOf);
                    String strSubstring2 = string.substring(iIndexOf + 1, string.length());
                    map.put(Constant.START_TIME, strSubstring);
                    map.put("endTime", strSubstring2);
                }
            }
            if (jSONObject2.has("netConnected")) {
                map.put("netConnected", jSONObject2.getString("netConnected"));
            }
            if (jSONObject2.has("expiredTime")) {
                String string2 = jSONObject2.getString("expiredTime");
                if (!TextUtils.isEmpty(string2) && TextUtils.isDigitsOnly(string2)) {
                    map.put("expiredTime", string2);
                }
            }
            pushTaskBean.setConditionMap(map);
        } catch (Exception unused) {
        }
    }

    public static boolean b() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (e.P <= 0) {
            e.P = jCurrentTimeMillis - 60000;
            return true;
        }
        if (jCurrentTimeMillis - e.P <= 60000) {
            return false;
        }
        e.P = jCurrentTimeMillis;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("condition");
            if (jSONObject2.has("wifi") || jSONObject2.has("screenOn") || jSONObject2.has("ssid") || jSONObject2.has(MediationConstant.EXTRA_DURATION)) {
                return false;
            }
            return !jSONObject2.has("netConnected");
        } catch (Exception unused) {
            return true;
        }
    }

    private void c(String str, String str2) {
        com.igexin.b.a.c.a.a("PushMessageExecutor do processActionExecute", new Object[0]);
        if (str2 == null || str == null) {
            return;
        }
        try {
            if (a(str, str2) == PushMessageInterface.ActionPrepareState.success) {
                a(str, str2, "1");
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushMessageExecutor|" + th.toString(), new Object[0]);
        }
    }

    public static void d() {
        try {
            if (!TextUtils.isEmpty(com.igexin.push.config.d.G) && !com.igexin.push.a.i.equals(com.igexin.push.config.d.G)) {
                List<String> listAsList = Arrays.asList(com.igexin.push.config.d.G.split(b.aj));
                if (listAsList.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<Map.Entry<String, PushTaskBean>> it = e.aj.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, PushTaskBean> next = it.next();
                    String key = next.getKey();
                    PushTaskBean value = next.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        for (String str : listAsList) {
                            if (!TextUtils.isEmpty(str) && key.startsWith(str)) {
                                arrayList.add(value.getTaskId());
                                it.remove();
                            }
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    strArr[i] = (String) arrayList.get(i);
                }
                d.a.a.j.a(b.Z, new String[]{"taskid"}, strArr);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean g() {
        if (e.aj.isEmpty() && e.t) {
            Cursor cursorA = null;
            try {
                try {
                    cursorA = d.a.a.j.a(b.Z, new String[]{"status"}, new String[]{"0"}, (String) null);
                    if (cursorA != null) {
                        while (cursorA.moveToNext()) {
                            byte[] blob = cursorA.getBlob(cursorA.getColumnIndex("msgextra"));
                            try {
                                JSONObject jSONObject = new JSONObject(new String(com.igexin.b.b.a.c(cursorA.getBlob(cursorA.getColumnIndex(DBDefinition.SEGMENT_INFO)))));
                                String string = jSONObject.getString("id");
                                String string2 = jSONObject.getString("appid");
                                String string3 = jSONObject.getString("messageid");
                                String string4 = jSONObject.getString("taskid");
                                String string5 = jSONObject.getString("appkey");
                                JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
                                com.igexin.push.core.a.b.d();
                                String strA = com.igexin.push.core.a.b.a(string4, string3);
                                PushTaskBean pushTaskBean = new PushTaskBean();
                                pushTaskBean.setAppid(string2);
                                pushTaskBean.setMessageId(string3);
                                pushTaskBean.setTaskId(string4);
                                pushTaskBean.setId(string);
                                pushTaskBean.setAppKey(string5);
                                pushTaskBean.setCurrentActionid(1);
                                pushTaskBean.setStatus(cursorA.getInt(cursorA.getColumnIndex("status")));
                                if (blob != null) {
                                    pushTaskBean.setMsgExtra(blob);
                                }
                                if (jSONObject.has("condition")) {
                                    b(jSONObject, pushTaskBean);
                                }
                                if (jSONArray.length() > 0 && !a(jSONObject, pushTaskBean)) {
                                    com.igexin.b.a.c.a.a("PushMessageExecutor|load task from db parseActionChains error, " + jSONObject.toString(), new Object[0]);
                                }
                                e.aj.put(strA, pushTaskBean);
                            } catch (JSONException unused) {
                            }
                        }
                    }
                    e.t = false;
                } catch (Throwable th) {
                    com.igexin.b.a.c.a.a("PushMessageExecutor|checkPushMessageMapValue error:" + th.toString(), new Object[0]);
                    if (cursorA != null) {
                    }
                }
                if (cursorA != null) {
                    cursorA.close();
                }
            } catch (Throwable th2) {
                if (cursorA != null) {
                    cursorA.close();
                }
                throw th2;
            }
        }
        return e.aj.isEmpty();
    }

    private void h() {
        try {
            List<ScanResult> listI = com.igexin.push.f.n.i();
            this.f.clear();
            if (listI == null || listI.isEmpty()) {
                return;
            }
            for (int i = 0; i < listI.size(); i++) {
                this.f.put(listI.get(i).BSSID, listI.get(i).SSID);
                String str = listI.get(i).BSSID;
                String str2 = listI.get(i).SSID;
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushMessageExecutor|" + th.toString(), new Object[0]);
        }
    }

    final PushMessageInterface.ActionPrepareState a(String str, String str2) {
        PushMessageInterface.ActionPrepareState actionPrepareState = PushMessageInterface.ActionPrepareState.success;
        com.igexin.push.core.a.b.d();
        PushTaskBean pushTaskBean = e.aj.get(com.igexin.push.core.a.b.a(str, str2));
        if (pushTaskBean == null) {
            return PushMessageInterface.ActionPrepareState.stop;
        }
        int i = 0;
        for (BaseActionBean baseActionBean : pushTaskBean.getActionChains()) {
            PushMessageInterface.ActionPrepareState actionPrepareStatePrepareExecuteAction = PushMessageInterface.ActionPrepareState.stop;
            if (baseActionBean == null) {
                return actionPrepareStatePrepareExecuteAction;
            }
            PushMessageInterface pushMessageInterfaceA = a(baseActionBean.getType());
            if (pushMessageInterfaceA != null) {
                actionPrepareStatePrepareExecuteAction = pushMessageInterfaceA.prepareExecuteAction(pushTaskBean, baseActionBean);
            } else {
                baseActionBean.getType();
            }
            if (actionPrepareState == PushMessageInterface.ActionPrepareState.success) {
                actionPrepareState = actionPrepareStatePrepareExecuteAction;
            }
            if (actionPrepareStatePrepareExecuteAction == PushMessageInterface.ActionPrepareState.wait) {
                i++;
            }
        }
        return (i == 0 || e.a(str, Integer.valueOf(i))) ? actionPrepareState : PushMessageInterface.ActionPrepareState.success;
    }

    public final void a(Intent intent) {
        String stringExtra = intent.getStringExtra("taskid");
        String stringExtra2 = intent.getStringExtra("messageid");
        String stringExtra3 = intent.getStringExtra("actionid");
        String stringExtra4 = intent.getStringExtra("accesstoken");
        String stringExtra5 = intent.hasExtra("title") ? intent.getStringExtra("title") : "";
        String stringExtra6 = intent.hasExtra("content") ? intent.getStringExtra("content") : "";
        int intExtra = intent.getIntExtra("notifID", 0);
        NotificationManager notificationManager = (NotificationManager) e.i.getSystemService(b.l);
        if (intExtra != 0) {
            notificationManager.cancel(intExtra);
        } else if (e.ak.containsKey(stringExtra)) {
            intExtra = e.ak.get(stringExtra).intValue();
            notificationManager.cancel(intExtra);
        }
        e.ak.remove(stringExtra);
        if (stringExtra4.equals(e.an)) {
            m.a().c(stringExtra, stringExtra2, stringExtra5, stringExtra6);
            b(stringExtra, stringExtra2, stringExtra3);
        }
    }

    public final boolean a(String str, String str2, String str3) {
        if (Thread.currentThread().getId() == d.a.a.b()) {
            b(str, str2, str3);
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        bundle.putString("actionid", str3);
        Message messageObtain = Message.obtain();
        messageObtain.what = b.R;
        messageObtain.obj = bundle;
        return d.a.a.a(messageObtain);
    }

    public final boolean a(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        com.igexin.b.a.c.a.a("PushMessageExecutor------parse pushmessage actionchain json start-------", new Object[0]);
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = ((JSONObject) jSONArray.get(i)).getString("type");
                if (!this.a.containsKey(string) && !d.contains(string)) {
                    com.igexin.b.a.c.a.a("PushMessageExecutor|" + string + " not support~", new Object[0]);
                    return false;
                }
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                String string2 = jSONObject2.getString("type");
                com.igexin.b.a.c.a.a("PushMessageExecutor|start parse type = ".concat(String.valueOf(string2)), new Object[0]);
                PushMessageInterface pushMessageInterfaceA = a(string2);
                if (pushMessageInterfaceA != null) {
                    arrayList.add(pushMessageInterfaceA.parseAction(jSONObject2));
                }
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("PushMessageExecutor|" + th.toString(), new Object[0]);
        }
        pushTaskBean.setActionChains(arrayList);
        com.igexin.b.a.c.a.a("PushMessageExecutor------parse pushmessage actionchain json end-------", new Object[0]);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0232 A[Catch: all -> 0x023f, Exception -> 0x0246, TryCatch #4 {Exception -> 0x0246, all -> 0x023f, blocks: (B:39:0x0115, B:41:0x012d, B:46:0x0139, B:48:0x017c, B:49:0x0184, B:51:0x018c, B:52:0x019c, B:55:0x01ae, B:57:0x01b3, B:58:0x01b9, B:60:0x01bf, B:62:0x01cd, B:63:0x01cf, B:64:0x01d3, B:66:0x01ea, B:67:0x020f, B:69:0x021d, B:71:0x0220, B:73:0x0232, B:74:0x0236, B:53:0x01a0), top: B:96:0x0115, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0236 A[Catch: all -> 0x023f, Exception -> 0x0246, TRY_LEAVE, TryCatch #4 {Exception -> 0x0246, all -> 0x023f, blocks: (B:39:0x0115, B:41:0x012d, B:46:0x0139, B:48:0x017c, B:49:0x0184, B:51:0x018c, B:52:0x019c, B:55:0x01ae, B:57:0x01b3, B:58:0x01b9, B:60:0x01bf, B:62:0x01cd, B:63:0x01cf, B:64:0x01d3, B:66:0x01ea, B:67:0x020f, B:69:0x021d, B:71:0x0220, B:73:0x0232, B:74:0x0236, B:53:0x01a0), top: B:96:0x0115, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(org.json.JSONObject r12, byte[] r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.o.a(org.json.JSONObject, byte[], boolean):boolean");
    }

    public final boolean b(String str, String str2, final String str3) {
        PushMessageInterface pushMessageInterfaceA;
        com.igexin.push.core.a.b.d();
        String strA = com.igexin.push.core.a.b.a(str, str2);
        final PushTaskBean pushTaskBean = e.aj.get(strA);
        if (pushTaskBean == null) {
            Cursor cursorA = null;
            try {
                try {
                    cursorA = d.a.a.j.a(b.Z, new String[]{"taskid", "messageid"}, new String[]{str, str2}, (String) null);
                } catch (Throwable th) {
                    com.igexin.b.a.c.a.a("PushMessageExecutor|" + th.toString(), new Object[0]);
                    if (cursorA != null) {
                    }
                }
                if (cursorA != null && cursorA.getCount() > 0) {
                    while (cursorA.moveToNext()) {
                        a().a(new JSONObject(new String(com.igexin.b.b.a.c(cursorA.getBlob(cursorA.getColumnIndexOrThrow(DBDefinition.SEGMENT_INFO))))), cursorA.getBlob(cursorA.getColumnIndexOrThrow("msgextra")), false);
                        PushTaskBean pushTaskBean2 = e.aj.get(str + ":" + str2);
                        if (pushTaskBean2 == null) {
                            return false;
                        }
                        pushTaskBean = pushTaskBean2;
                    }
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
                if (cursorA != null) {
                    cursorA.close();
                }
                return false;
            } finally {
                if (cursorA != null) {
                    cursorA.close();
                }
            }
        }
        int executeTimes = pushTaskBean.getExecuteTimes();
        if (executeTimes >= 50) {
            try {
                e.aj.remove(strA);
            } catch (Exception e) {
                com.igexin.b.a.c.a.a("PushMessageExecutor|" + e.toString(), new Object[0]);
            }
            return true;
        }
        pushTaskBean.setExecuteTimes(executeTimes + 1);
        FeedbackImpl.getInstance().asyncFeedback(new Runnable() { // from class: com.igexin.push.core.o.2
            @Override // java.lang.Runnable
            public final void run() {
                FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, str3);
            }
        });
        try {
            BaseActionBean baseAction = pushTaskBean.getBaseAction(str3);
            if (baseAction != null && (pushMessageInterfaceA = a(baseAction.getType())) != null) {
                return pushMessageInterfaceA.executeAction(pushTaskBean, baseAction);
            }
        } catch (Throwable th2) {
            com.igexin.b.a.c.a.a("PushMessageExecutor|" + th2.toString(), new Object[0]);
        }
        return false;
    }

    public final void c() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.o.3
            @Override // com.igexin.push.a.d
            public final void a_() throws Exception {
                d.a.a.j.a(b.Z, "createtime <= ".concat(String.valueOf(System.currentTimeMillis() - com.igexin.push.e.b.d.b)));
            }
        }, false, true);
    }

    public final void e() {
        int i;
        String str;
        try {
            if (g()) {
                return;
            }
            for (Map.Entry<String, PushTaskBean> entry : e.aj.entrySet()) {
                try {
                    entry.getKey();
                    PushTaskBean value = entry.getValue();
                    String str2 = "";
                    if (value != null && value.getStatus() == b.ae) {
                        String taskId = value.getTaskId();
                        Map<String, String> conditionMap = value.getConditionMap();
                        if (conditionMap == null) {
                            return;
                        }
                        if (com.igexin.push.f.c.b(taskId)) {
                            a(b.ag, taskId);
                            i = b.af;
                        } else {
                            if (conditionMap.size() > 0) {
                                if (conditionMap.containsKey("expiredTime") && Long.parseLong(conditionMap.get("expiredTime")) < System.currentTimeMillis()) {
                                    a(b.ag, taskId);
                                    i = b.af;
                                } else if (!conditionMap.containsKey("endTime") || Long.parseLong(conditionMap.get("endTime")) >= System.currentTimeMillis()) {
                                    if (conditionMap.containsKey("wifi")) {
                                        int i2 = Integer.parseInt(conditionMap.get("wifi"));
                                        com.igexin.push.f.c.d();
                                        if (i2 != e.u) {
                                        }
                                    }
                                    if (conditionMap.containsKey("screenOn")) {
                                        int i3 = Integer.parseInt(conditionMap.get("screenOn"));
                                        com.igexin.push.f.c.e();
                                        if (i3 != e.v) {
                                        }
                                    }
                                    if (conditionMap.containsKey("ssid")) {
                                        str2 = conditionMap.get("ssid");
                                        h();
                                        if (!this.f.containsValue(str2)) {
                                        }
                                    }
                                    if (conditionMap.containsKey("bssid")) {
                                        String str3 = conditionMap.get("bssid");
                                        if (this.f.containsKey(str3) && ((str = this.f.get(str3)) == null || str.equals(str2))) {
                                        }
                                    }
                                    if (!conditionMap.containsKey(Constant.START_TIME) || Long.parseLong(conditionMap.get(Constant.START_TIME)) <= System.currentTimeMillis()) {
                                        if (conditionMap.containsKey("netConnected")) {
                                            try {
                                                if (Integer.parseInt(conditionMap.get("netConnected")) != com.igexin.push.f.c.f()) {
                                                }
                                            } catch (Exception unused) {
                                            }
                                        }
                                    }
                                } else {
                                    a(b.ag, taskId);
                                    i = b.af;
                                }
                            }
                            b(taskId, value.getMessageId());
                            a(b.af, taskId);
                            i = b.af;
                        }
                        value.setStatus(i);
                    }
                } catch (Exception e) {
                    com.igexin.b.a.c.a.a("PushMessageExecutor|" + e.toString(), new Object[0]);
                }
            }
        } catch (Exception e2) {
            com.igexin.b.a.c.a.a("PushMessageExecutor|" + e2.toString(), new Object[0]);
        }
    }

    public final void f() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.o.4
            @Override // com.igexin.push.a.d
            public final void a_() throws Exception {
                Cursor cursorA = null;
                try {
                    try {
                        com.igexin.push.a.b bVar = d.a.a.j;
                        cursorA = bVar.a(b.Z, new String[]{"status"}, new String[]{"0"}, (String) null);
                        if (cursorA != null) {
                            while (cursorA.moveToNext()) {
                                byte[] blob = cursorA.getBlob(cursorA.getColumnIndex(DBDefinition.SEGMENT_INFO));
                                long j = cursorA.getLong(cursorA.getColumnIndex("createtime"));
                                try {
                                    JSONObject jSONObject = new JSONObject(new String(com.igexin.b.b.a.c(blob)));
                                    String string = jSONObject.getString("taskid");
                                    if (jSONObject.has("condition") && !o.b(jSONObject) && System.currentTimeMillis() - j > 259200000) {
                                        com.igexin.b.a.c.a.a("PushMessageExecutor|del condition taskid = ".concat(String.valueOf(string)), new Object[0]);
                                        bVar.a(b.Z, new String[]{"taskid"}, new String[]{string});
                                    }
                                } catch (Throwable th) {
                                    com.igexin.b.a.c.a.a("PushMessageExecutor|del condition" + th.toString(), new Object[0]);
                                }
                            }
                        }
                        if (cursorA != null) {
                            cursorA.close();
                        }
                    } catch (Throwable th2) {
                        if (cursorA != null) {
                            cursorA.close();
                        }
                        throw th2;
                    }
                } catch (Throwable th3) {
                    com.igexin.b.a.c.a.a("PushMessageExecutor|del condition" + th3.toString(), new Object[0]);
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
        }, false, true);
    }
}
