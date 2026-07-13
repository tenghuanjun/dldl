package com.volcengine.b;

import android.content.Context;
import android.os.Process;
import com.volcengine.b.a;
import com.volcengine.j.m;
import java.net.InetAddress;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class b extends a {
    public b(Context context, Map<String, String> map, Map<String, String> map2, a.b bVar) {
        super(context, map, map2, bVar, 2);
    }

    public static int a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    private String a(String str) {
        InetAddress[] inetAddressArr = (InetAddress[]) m.a(str).get("remoteInet");
        String str2 = "";
        if (inetAddressArr == null) {
            return "";
        }
        for (InetAddress inetAddress : inetAddressArr) {
            str2 = str2 + inetAddress.getHostAddress() + ",";
        }
        return str2.substring(0, str2.length() - 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ef  */
    @Override // com.volcengine.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(java.util.Map<java.lang.String, java.lang.String> r5) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.b.b.a(java.util.Map):void");
    }

    @Override // com.volcengine.b.a
    protected String c() {
        return "GetNetworkInfo";
    }
}
