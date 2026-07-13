package com.bytedance.bdtracker;

import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class u0 extends s0 {
    public u0(HashSet<String> hashSet, HashMap<String, HashSet<String>> map) {
        super(hashSet, map);
    }

    @Override // com.bytedance.bdtracker.s0
    public boolean a(String str) {
        return this.f321a.contains(str);
    }

    @Override // com.bytedance.bdtracker.s0
    public boolean a(HashSet<String> hashSet, String str) {
        return hashSet.contains(str);
    }
}
