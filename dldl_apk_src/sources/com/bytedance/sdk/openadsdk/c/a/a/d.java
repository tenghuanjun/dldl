package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.DislikeInfo;
import com.bytedance.sdk.openadsdk.FilterWord;
import com.bytedance.sdk.openadsdk.PersonalizationPrompt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d implements DislikeInfo {
    private final Bridge a;

    public d(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.DislikeInfo
    public List<FilterWord> getFilterWords() {
        List arrayList = (List) this.a.values().objectValue(243001, List.class);
        if (arrayList == null) {
            arrayList = new ArrayList(0);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new f((Bridge) it.next()));
        }
        return arrayList2;
    }

    @Override // com.bytedance.sdk.openadsdk.DislikeInfo
    public PersonalizationPrompt getPersonalizationPrompt() {
        return new g((Bridge) this.a.values().objectValue(243002, Bridge.class));
    }
}
