package com.igexin.push.core.d;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class f {
    com.getui.gtc.dyc.b.c b = new com.getui.gtc.dyc.b.c() { // from class: com.igexin.push.core.d.f.1
        @Override // com.getui.gtc.dyc.b.c
        public final void a(Map map, Map map2) {
            f.this.a((Map<String, String>) map2);
        }

        @Override // com.getui.gtc.dyc.b.c
        public final void b(String str) {
            f.this.a(str);
        }
    };

    private com.getui.gtc.dyc.b.c a() {
        return this.b;
    }

    public abstract void a(String str);

    public abstract void a(Map<String, String> map);
}
