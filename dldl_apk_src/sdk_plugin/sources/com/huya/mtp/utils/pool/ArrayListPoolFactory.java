package com.huya.mtp.utils.pool;

import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ArrayListPoolFactory extends AbsPoolFactory<ArrayList> {
    public ArrayListPoolFactory() {
    }

    public ArrayListPoolFactory(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huya.mtp.utils.pool.AbsPoolFactory
    public ArrayList createObject() {
        Log.d("pool", "createObject new ArrayList()");
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huya.mtp.utils.pool.AbsPoolFactory
    public void resetObject(ArrayList arrayList) {
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
