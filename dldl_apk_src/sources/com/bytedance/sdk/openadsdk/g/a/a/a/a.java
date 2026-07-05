package com.bytedance.sdk.openadsdk.g.a.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.c.a.a.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTAdNative.DrawFeedAdListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public a(TTAdNative.DrawFeedAdListener drawFeedAdListener) {
        this.b = drawFeedAdListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        if (this.b == null) {
            return null;
        }
        switch (i) {
            case 172101:
                this.b.onError(valueSet.intValue(0), (String) valueSet.objectValue(1, String.class));
                break;
            case 172102:
                List arrayList = (List) valueSet.objectValue(0, List.class);
                if (arrayList == null) {
                    arrayList = new ArrayList(0);
                }
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new j((Bridge) it.next()));
                }
                this.b.onDrawFeedAdLoad(arrayList2);
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
