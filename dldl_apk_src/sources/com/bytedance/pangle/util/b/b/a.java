package com.bytedance.pangle.util.b.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public List<c> a = new ArrayList();

    public final void a(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        for (c cVar : this.a) {
            if (!set.contains(cVar.h)) {
                arrayList.add(cVar);
            }
        }
        this.a = arrayList;
    }
}
