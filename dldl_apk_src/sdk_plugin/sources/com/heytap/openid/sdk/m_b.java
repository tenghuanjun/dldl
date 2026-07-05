package com.heytap.openid.sdk;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.heytap.openid.sdk.m_c;
import com.heytap.openid.sdk.m_i;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class m_b extends com.heytap.openid.base.m_b {

    public class m_a implements Runnable {
        public final /* synthetic */ Context m_a;

        public m_a(Context context) {
            this.m_a = context;
        }

        @Override // java.lang.Runnable
        public native void run();
    }

    /* JADX INFO: renamed from: com.heytap.openid.sdk.m_b$m_b, reason: collision with other inner class name */
    public static class C0016m_b {
        public static final m_b m_a = new m_b();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.heytap.openid.base.m_b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.HashMap<java.lang.String, java.lang.String> m_a(android.content.Context r9, java.util.List<java.lang.String> r10) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.sdk.m_b.m_a(android.content.Context, java.util.List):java.util.HashMap");
    }

    public final void m_a(Context context, List<String> list, HashMap<String, String> map) {
        String string = Settings.Secure.getString(context.getContentResolver(), "oplus_omes_stdid_ouid");
        if (TextUtils.isEmpty(string)) {
            m_h.m_a("2045");
            return;
        }
        map.put("OUID", string);
        list.remove("OUID");
        com.heytap.openid.sdk.m_a.m_a.execute(new m_a(context));
    }

    @Override // com.heytap.openid.base.m_b
    public void m_a(Context context, List<String> list, boolean z) {
        (this.m_b.equals("OP_APP") ? m_c.m_b.m_a : m_i.m_b.m_a).m_a(context, list, z);
    }
}
