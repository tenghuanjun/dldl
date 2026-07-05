package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.j256.ormlite.field.FieldType;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.k;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class b {
    private static int a;
    private Context b;
    private u c;
    private p d;
    private com.tencent.bugly.crashreport.common.strategy.a e;
    private o f;
    private BuglyStrategy.a g;

    public b(int i, Context context, u uVar, p pVar, com.tencent.bugly.crashreport.common.strategy.a aVar, BuglyStrategy.a aVar2, o oVar) {
        a = i;
        this.b = context;
        this.c = uVar;
        this.d = pVar;
        this.e = aVar;
        this.g = aVar2;
        this.f = oVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b <= jCurrentTimeMillis - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> listB;
        String[] strArrSplit;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0 && (listB = b(arrayList)) != null && listB.size() > 0) {
            Collections.sort(listB);
            for (int i = 0; i < listB.size(); i++) {
                CrashDetailBean crashDetailBean3 = listB.get(i);
                if (i == 0) {
                    crashDetailBean2 = crashDetailBean3;
                } else if (crashDetailBean3.s != null && (strArrSplit = crashDetailBean3.s.split(ShellAdbUtils.COMMAND_LINE_END)) != null) {
                    for (String str : strArrSplit) {
                        if (!crashDetailBean2.s.contains(str)) {
                            crashDetailBean2.t++;
                            crashDetailBean2.s += str + ShellAdbUtils.COMMAND_LINE_END;
                        }
                    }
                }
            }
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean2 = crashDetailBean;
        }
        for (a aVar2 : list) {
            if (!aVar2.e && !aVar2.d) {
                String str2 = crashDetailBean2.s;
                StringBuilder sb = new StringBuilder();
                sb.append(aVar2.b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean2.t++;
                    crashDetailBean2.s += aVar2.b + ShellAdbUtils.COMMAND_LINE_END;
                }
            }
        }
        if (crashDetailBean2.r != crashDetailBean.r) {
            String str3 = crashDetailBean2.s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean2.t++;
                crashDetailBean2.s += crashDetailBean.r + ShellAdbUtils.COMMAND_LINE_END;
            }
        }
        return crashDetailBean2;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return a(crashDetailBean, -123456789);
    }

    public final boolean a(CrashDetailBean crashDetailBean, int i) {
        if (crashDetailBean == null) {
            return true;
        }
        if (c.n != null && !c.n.isEmpty()) {
            x.c("Crash filter for crash stack is: %s", c.n);
            if (crashDetailBean.q.contains(c.n)) {
                x.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (c.o != null && !c.o.isEmpty()) {
            x.c("Crash regular filter for crash stack is: %s", c.o);
            if (Pattern.compile(c.o).matcher(crashDetailBean.q).find()) {
                x.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        int i2 = crashDetailBean.b;
        String str = crashDetailBean.n;
        String str2 = crashDetailBean.o;
        String str3 = crashDetailBean.p;
        String str4 = crashDetailBean.q;
        long j = crashDetailBean.r;
        String str5 = crashDetailBean.m;
        String str6 = crashDetailBean.e;
        String str7 = crashDetailBean.c;
        String str8 = crashDetailBean.A;
        String str9 = crashDetailBean.B;
        if (this.f != null) {
            x.c("Calling 'onCrashSaving' of RQD crash listener.", new Object[0]);
            if (!this.f.c()) {
                x.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.b != 2) {
            r rVar = new r();
            rVar.b = 1;
            rVar.c = crashDetailBean.A;
            rVar.d = crashDetailBean.B;
            rVar.e = crashDetailBean.r;
            this.d.b(1);
            this.d.a(rVar);
            x.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            x.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> listB = b();
        ArrayList arrayList = null;
        if (listB != null && listB.size() > 0) {
            arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            arrayList.addAll(a(listB));
            listB.removeAll(arrayList);
            if (!com.tencent.bugly.b.c && c.d) {
                boolean z = false;
                for (a aVar : listB) {
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            z = true;
                        }
                        arrayList2.add(aVar);
                    }
                }
                if (z || arrayList2.size() >= c.c) {
                    x.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean crashDetailBeanA = a(arrayList2, crashDetailBean);
                    for (a aVar2 : arrayList2) {
                        if (aVar2.a != crashDetailBeanA.a) {
                            arrayList.add(aVar2);
                        }
                    }
                    d(crashDetailBeanA);
                    c(arrayList);
                    x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
        }
        d(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            c(arrayList);
        }
        x.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final List<CrashDetailBean> a() {
        StrategyBean strategyBeanC = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (strategyBeanC == null) {
            x.d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!strategyBeanC.g) {
            x.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            x.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jB = z.b();
        List<a> listB = b();
        x.c("Size of crash list loaded from DB: %s", Integer.valueOf(listB.size()));
        if (listB == null || listB.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a(listB));
        listB.removeAll(arrayList);
        Iterator<a> it = listB.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.b < jB - c.g) {
                it.remove();
                arrayList.add(next);
            } else if (next.d) {
                if (next.b >= jCurrentTimeMillis - 86400000) {
                    it.remove();
                } else if (!next.e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f >= 3 && next.b < jCurrentTimeMillis - 86400000) {
                it.remove();
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            c(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        List<CrashDetailBean> listB2 = b(listB);
        if (listB2 != null && listB2.size() > 0) {
            String str = com.tencent.bugly.crashreport.common.info.a.b().j;
            Iterator<CrashDetailBean> it2 = listB2.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f)) {
                    it2.remove();
                    arrayList2.add(next2);
                }
            }
        }
        if (arrayList2.size() > 0) {
            d(arrayList2);
        }
        return listB2;
    }

    public final void b(CrashDetailBean crashDetailBean) {
        if (this.f != null) {
            x.c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            int i = crashDetailBean.b;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (c.l) {
            x.a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            a(arrayList, 3000L, z, crashDetailBean.b == 7, z);
        }
    }

    public final void a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        u uVar;
        ao aoVar;
        if (com.tencent.bugly.crashreport.common.info.a.a(this.b).e && (uVar = this.c) != null) {
            if (z3 || uVar.b(c.a)) {
                StrategyBean strategyBeanC = this.e.c();
                if (!strategyBeanC.g) {
                    x.d("remote report is disable!", new Object[0]);
                    x.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
                    return;
                }
                if (list == null || list.size() == 0) {
                    return;
                }
                try {
                    String str = this.c.a ? strategyBeanC.s : strategyBeanC.t;
                    String str2 = this.c.a ? StrategyBean.c : StrategyBean.a;
                    int i = this.c.a ? 830 : 630;
                    Context context = this.b;
                    com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
                    if (context == null || list == null || list.size() == 0 || aVarB == null) {
                        x.d("enEXPPkg args == null!", new Object[0]);
                        aoVar = null;
                    } else {
                        aoVar = new ao();
                        aoVar.a = new ArrayList<>();
                        Iterator<CrashDetailBean> it = list.iterator();
                        while (it.hasNext()) {
                            aoVar.a.add(a(context, it.next(), aVarB));
                        }
                    }
                    if (aoVar == null) {
                        x.d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] bArrA = com.tencent.bugly.proguard.a.a((k) aoVar);
                    if (bArrA == null) {
                        x.d("send encode fail!", new Object[0]);
                        return;
                    }
                    ap apVarA = com.tencent.bugly.proguard.a.a(this.b, i, bArrA);
                    if (apVarA == null) {
                        x.d("request package is null.", new Object[0]);
                        return;
                    }
                    t tVar = new t() { // from class: com.tencent.bugly.crashreport.crash.b.1
                        @Override // com.tencent.bugly.proguard.t
                        public final void a(boolean z4) {
                            b.a(z4, (List<CrashDetailBean>) list);
                        }
                    };
                    if (z) {
                        this.c.a(a, apVarA, str, str2, tVar, j, z2);
                    } else {
                        this.c.a(a, apVarA, str, str2, tVar, false);
                    }
                } catch (Throwable th) {
                    x.e("req cr error %s", th.toString());
                    if (x.b(th)) {
                        return;
                    }
                    th.printStackTrace();
                }
            }
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            x.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                x.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.d = z;
                x.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                c.a().a(it.next());
            }
            x.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        x.b("[crash] upload fail.", new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(com.tencent.bugly.crashreport.crash.CrashDetailBean r13) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.c(com.tencent.bugly.crashreport.crash.CrashDetailBean):void");
    }

    private static ContentValues e(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put(FieldType.FOREIGN_ID_FIELD_SUFFIX, Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            int i = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.d ? 1 : 0));
            if (!crashDetailBean.j) {
                i = 0;
            }
            contentValues.put("_me", Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", z.a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            CrashDetailBean crashDetailBean = (CrashDetailBean) z.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void d(CrashDetailBean crashDetailBean) {
        ContentValues contentValuesE;
        if (crashDetailBean == null || (contentValuesE = e(crashDetailBean)) == null) {
            return;
        }
        long jA = p.a().a("t_cr", contentValuesE, (o) null, true);
        if (jA >= 0) {
            x.c("insert %s success!", "t_cr");
            crashDetailBean.a = jA;
        }
    }

    private List<CrashDetailBean> b(List<a> list) {
        Cursor cursorA;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().a);
            sb.append(",");
        }
        if (sb.toString().contains(",")) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        }
        sb.append(")");
        String string = sb.toString();
        sb.setLength(0);
        try {
            cursorA = p.a().a("t_cr", null, string, null, null, true);
            if (cursorA == null) {
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                sb.append("_id in ");
                sb.append("(");
                int i = 0;
                while (cursorA.moveToNext()) {
                    CrashDetailBean crashDetailBeanA = a(cursorA);
                    if (crashDetailBeanA != null) {
                        arrayList.add(crashDetailBeanA);
                    } else {
                        try {
                            sb.append(cursorA.getLong(cursorA.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX)));
                            sb.append(",");
                            i++;
                        } catch (Throwable unused) {
                            x.d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String string2 = sb.toString();
                if (i > 0) {
                    x.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", string2, (String[]) null, (o) null, true)));
                }
                if (cursorA != null) {
                    cursorA.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    return null;
                } finally {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorA = null;
        }
    }

    private static a b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            aVar.e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private List<a> b() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor cursorA = p.a().a("t_cr", new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX, "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (cursorA == null) {
                if (cursorA != null) {
                    cursorA.close();
                }
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                int i = 0;
                while (cursorA.moveToNext()) {
                    a aVarB = b(cursorA);
                    if (aVarB != null) {
                        arrayList.add(aVarB);
                    } else {
                        try {
                            sb.append(cursorA.getLong(cursorA.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX)));
                            sb.append(",");
                            i++;
                        } catch (Throwable unused) {
                            x.d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String string = sb.toString();
                sb.setLength(0);
                if (i > 0) {
                    x.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", string, (String[]) null, (o) null, true)));
                }
                if (cursorA != null) {
                    cursorA.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursor = cursorA;
                try {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    return arrayList;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void c(List<a> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().a);
            sb.append(",");
        }
        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        sb2.append(")");
        String string = sb2.toString();
        sb2.setLength(0);
        try {
            x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", string, (String[]) null, (o) null, true)));
        } catch (Throwable th) {
            if (x.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private static void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (CrashDetailBean crashDetailBean : list) {
                    sb.append(" or _id");
                    sb.append(" = ");
                    sb.append(crashDetailBean.a);
                }
                String string = sb.toString();
                if (string.length() > 0) {
                    string = string.substring(4);
                }
                sb.setLength(0);
                x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", string, (String[]) null, (o) null, true)));
            } catch (Throwable th) {
                if (x.a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    private static an a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        am amVarA;
        am amVarA2;
        am amVar;
        if (context == null || crashDetailBean == null || aVar == null) {
            x.d("enExp args == null", new Object[0]);
            return null;
        }
        an anVar = new an();
        switch (crashDetailBean.b) {
            case 0:
                anVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                anVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case 2:
                anVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case 3:
                anVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                anVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                anVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case 6:
                anVar.a = crashDetailBean.j ? "206" : "106";
                break;
            case 7:
                anVar.a = crashDetailBean.j ? "208" : "108";
                break;
            default:
                x.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        anVar.b = crashDetailBean.r;
        anVar.c = crashDetailBean.n;
        anVar.d = crashDetailBean.o;
        anVar.e = crashDetailBean.p;
        anVar.g = crashDetailBean.q;
        anVar.h = crashDetailBean.z;
        anVar.i = crashDetailBean.c;
        anVar.j = null;
        anVar.l = crashDetailBean.m;
        anVar.m = crashDetailBean.e;
        anVar.f = crashDetailBean.B;
        anVar.t = com.tencent.bugly.crashreport.common.info.a.b().i();
        anVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            anVar.o = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.i.entrySet()) {
                ak akVar = new ak();
                akVar.a = entry.getValue().a;
                akVar.c = entry.getValue().c;
                akVar.d = entry.getValue().b;
                akVar.b = aVar.r();
                anVar.o.add(akVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            anVar.p = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry2 : crashDetailBean.h.entrySet()) {
                ak akVar2 = new ak();
                akVar2.a = entry2.getValue().a;
                akVar2.c = entry2.getValue().c;
                akVar2.d = entry2.getValue().b;
                anVar.p.add(akVar2);
            }
        }
        if (crashDetailBean.j) {
            anVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (anVar.q == null) {
                    anVar.q = new ArrayList<>();
                }
                try {
                    anVar.q.add(new am((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    anVar.q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(anVar.k);
            objArr[1] = Integer.valueOf(anVar.q != null ? anVar.q.size() : 0);
            x.c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.w != null) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            try {
                anVar.q.add(new am((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                anVar.q = null;
            }
        }
        if (crashDetailBean.x != null) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            try {
                anVar.q.add(new am((byte) 1, "jniLog.txt", crashDetailBean.x.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                anVar.q = null;
            }
        }
        if (!z.a(crashDetailBean.U)) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            try {
                amVar = new am((byte) 1, "crashInfos.txt", crashDetailBean.U.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
                amVar = null;
            }
            if (amVar != null) {
                x.c("attach crash infos", new Object[0]);
                anVar.q.add(amVar);
            }
        }
        if (crashDetailBean.V != null) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            am amVarA3 = a("backupRecord.zip", context, crashDetailBean.V);
            if (amVarA3 != null) {
                x.c("attach backup record", new Object[0]);
                anVar.q.add(amVarA3);
            }
        }
        if (crashDetailBean.y != null && crashDetailBean.y.length > 0) {
            am amVar2 = new am((byte) 2, "buglylog.zip", crashDetailBean.y);
            x.c("attach user log", new Object[0]);
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            anVar.q.add(amVar2);
        }
        if (crashDetailBean.b == 3) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            x.c("crashBean.userDatas:%s", crashDetailBean.O);
            if (crashDetailBean.O != null && crashDetailBean.O.containsKey("BUGLY_CR_01")) {
                try {
                    if (!TextUtils.isEmpty(crashDetailBean.O.get("BUGLY_CR_01"))) {
                        anVar.q.add(new am((byte) 1, "anrMessage.txt", crashDetailBean.O.get("BUGLY_CR_01").getBytes("utf-8")));
                        x.c("attach anr message", new Object[0]);
                    }
                } catch (UnsupportedEncodingException e5) {
                    e5.printStackTrace();
                    anVar.q = null;
                }
                crashDetailBean.O.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null && (amVarA2 = a("trace.zip", context, crashDetailBean.v)) != null) {
                x.c("attach traces", new Object[0]);
                anVar.q.add(amVarA2);
            }
        }
        if (crashDetailBean.b == 1) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            if (crashDetailBean.v != null && (amVarA = a("tomb.zip", context, crashDetailBean.v)) != null) {
                x.c("attach tombs", new Object[0]);
                anVar.q.add(amVarA);
            }
        }
        if (aVar.C != null && !aVar.C.isEmpty()) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = aVar.C.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            try {
                anVar.q.add(new am((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                x.c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e6) {
                e6.printStackTrace();
            }
        }
        if (crashDetailBean.T != null && crashDetailBean.T.length > 0) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            anVar.q.add(new am((byte) 1, "userExtraByteData", crashDetailBean.T));
            x.c("attach extraData", new Object[0]);
        }
        anVar.r = new HashMap();
        Map<String, String> map = anVar.r;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.C);
        map.put("A9", sb2.toString());
        Map<String, String> map2 = anVar.r;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.D);
        map2.put("A11", sb3.toString());
        Map<String, String> map3 = anVar.r;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(crashDetailBean.E);
        map3.put("A10", sb4.toString());
        anVar.r.put("A23", crashDetailBean.f);
        anVar.r.put("A7", aVar.f);
        anVar.r.put("A6", aVar.s());
        anVar.r.put("A5", aVar.r());
        anVar.r.put("A22", aVar.h());
        Map<String, String> map4 = anVar.r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.G);
        map4.put("A2", sb5.toString());
        Map<String, String> map5 = anVar.r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.F);
        map5.put("A1", sb6.toString());
        anVar.r.put("A24", aVar.h);
        Map<String, String> map6 = anVar.r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.H);
        map6.put("A17", sb7.toString());
        anVar.r.put("A3", aVar.k());
        anVar.r.put("A16", aVar.m());
        anVar.r.put("A25", aVar.n());
        anVar.r.put("A14", aVar.l());
        anVar.r.put("A15", aVar.w());
        Map<String, String> map7 = anVar.r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(aVar.x());
        map7.put("A13", sb8.toString());
        anVar.r.put("A34", crashDetailBean.A);
        if (aVar.x != null) {
            anVar.r.put("productIdentify", aVar.x);
        }
        try {
            anVar.r.put("A26", URLEncoder.encode(crashDetailBean.I, "utf-8"));
        } catch (UnsupportedEncodingException e7) {
            e7.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            anVar.r.put("A27", crashDetailBean.K);
            anVar.r.put("A28", crashDetailBean.J);
            Map<String, String> map8 = anVar.r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.k);
            map8.put("A29", sb9.toString());
        }
        anVar.r.put("A30", crashDetailBean.L);
        Map<String, String> map9 = anVar.r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.M);
        map9.put("A18", sb10.toString());
        Map<String, String> map10 = anVar.r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.N);
        map10.put("A36", sb11.toString());
        Map<String, String> map11 = anVar.r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(aVar.q);
        map11.put("F02", sb12.toString());
        Map<String, String> map12 = anVar.r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(aVar.r);
        map12.put("F03", sb13.toString());
        anVar.r.put("F04", aVar.e());
        Map<String, String> map13 = anVar.r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(aVar.s);
        map13.put("F05", sb14.toString());
        anVar.r.put("F06", aVar.p);
        anVar.r.put("F08", aVar.v);
        anVar.r.put("F09", aVar.w);
        Map<String, String> map14 = anVar.r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(aVar.t);
        map14.put("F10", sb15.toString());
        if (crashDetailBean.P >= 0) {
            Map<String, String> map15 = anVar.r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.P);
            map15.put("C01", sb16.toString());
        }
        if (crashDetailBean.Q >= 0) {
            Map<String, String> map16 = anVar.r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.Q);
            map16.put("C02", sb17.toString());
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Map.Entry<String, String> entry3 : crashDetailBean.R.entrySet()) {
                anVar.r.put("C03_" + entry3.getKey(), entry3.getValue());
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            for (Map.Entry<String, String> entry4 : crashDetailBean.S.entrySet()) {
                anVar.r.put("C04_" + entry4.getKey(), entry4.getValue());
            }
        }
        anVar.s = null;
        if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
            anVar.s = crashDetailBean.O;
            x.a("setted message size %d", Integer.valueOf(anVar.s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.e();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        objArr2[7] = Boolean.valueOf(crashDetailBean.b == 1);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(anVar.r.size());
        x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return anVar;
    }

    private static am a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        if (str2 == null || context == null) {
            x.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        x.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!z.a(file, file2, 5000)) {
            x.d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                    byteArrayOutputStream.flush();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                x.c("read bytes :%d", Integer.valueOf(byteArray.length));
                am amVar = new am((byte) 2, file2.getName(), byteArray);
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    if (!x.a(e)) {
                        e.printStackTrace();
                    }
                }
                if (file2.exists()) {
                    x.c("del tmp", new Object[0]);
                    file2.delete();
                }
                return amVar;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    if (file2.exists()) {
                        x.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    return null;
                } catch (Throwable th2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            if (!x.a(e3)) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (file2.exists()) {
                        x.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
        if (aVarB == null) {
            return;
        }
        x.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        x.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        x.e("# PKG NAME: %s", aVarB.c);
        x.e("# APP VER: %s", aVarB.j);
        x.e("# LAUNCH TIME: %s", z.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a)));
        x.e("# CRASH TYPE: %s", str);
        x.e("# CRASH TIME: %s", str2);
        x.e("# CRASH PROCESS: %s", str3);
        x.e("# CRASH THREAD: %s", str4);
        if (crashDetailBean != null) {
            x.e("# REPORT ID: %s", crashDetailBean.c);
            Object[] objArr = new Object[2];
            objArr[0] = aVarB.g;
            objArr[1] = aVarB.x().booleanValue() ? "ROOTED" : "UNROOT";
            x.e("# CRASH DEVICE: %s %s", objArr);
            x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D), Long.valueOf(crashDetailBean.E));
            x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G), Long.valueOf(crashDetailBean.H));
            if (!z.a(crashDetailBean.K)) {
                x.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.K, crashDetailBean.J);
            } else if (crashDetailBean.b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.O == null) {
                    str6 = AbstractJsonLexerKt.NULL;
                } else {
                    str6 = crashDetailBean.O.get("BUGLY_CR_01");
                }
                objArr2[0] = str6;
                x.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!z.a(str5)) {
            x.e("# CRASH STACK: ", new Object[0]);
            x.e(str5, new Object[0]);
        }
        x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }
}
