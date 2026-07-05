package com.duowan.taf.jce.dynamic;

import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArrayListPoolFactory extends AbsPoolFactory<ArrayList> {
    public ArrayListPoolFactory() {
    }

    public ArrayListPoolFactory(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.taf.jce.dynamic.AbsPoolFactory
    public ArrayList createObject() {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.duowan.taf.jce.dynamic.AbsPoolFactory
    public void resetObject(ArrayList arrayList) {
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
