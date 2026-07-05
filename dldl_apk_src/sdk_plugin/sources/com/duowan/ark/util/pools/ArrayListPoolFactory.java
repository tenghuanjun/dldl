package com.duowan.ark.util.pools;

import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArrayListPoolFactory extends AbsPoolFactory<ArrayList> {
    public ArrayListPoolFactory() {
    }

    public ArrayListPoolFactory(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.util.pools.AbsPoolFactory
    public ArrayList createObject() {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.ark.util.pools.AbsPoolFactory
    public void resetObject(ArrayList arrayList) {
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
