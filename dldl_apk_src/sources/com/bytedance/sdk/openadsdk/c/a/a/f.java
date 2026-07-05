package com.bytedance.sdk.openadsdk.c.a.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.FilterWord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f implements Bridge, FilterWord {
    private final Bridge a;
    private FilterWord b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public f(FilterWord filterWord) {
        this.b = filterWord;
        this.a = com.bykv.a.a.a.a.b.b;
    }

    public f(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public String getId() {
        return (String) this.a.call(241103, com.bykv.a.a.a.a.b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public String getName() {
        return (String) this.a.call(241104, com.bykv.a.a.a.a.b.a(0).b(), String.class);
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public boolean getIsSelected() {
        return ((Boolean) this.a.call(241105, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public List<FilterWord> getOptions() {
        List arrayList = (List) this.a.call(241108, com.bykv.a.a.a.a.b.a(0).b(), List.class);
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

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public boolean isValid() {
        return ((Boolean) this.a.call(241107, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public boolean hasSecondOptions() {
        return ((Boolean) this.a.call(241106, com.bykv.a.a.a.a.b.a(0).b(), Boolean.TYPE)).booleanValue();
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        FilterWord filterWord = this.b;
        if (filterWord == null) {
            return null;
        }
        switch (i) {
            case 241101:
                this.b.addOption(new f((Bridge) valueSet.objectValue(0, Bridge.class)));
                break;
            case 241102:
                this.b.setIsSelected(valueSet.booleanValue(0));
                break;
            case 241103:
                return (T) filterWord.getId();
            case 241104:
                return (T) filterWord.getName();
            case 241105:
                return (T) Boolean.class.cast(Boolean.valueOf(filterWord.getIsSelected()));
            case 241106:
                return (T) Boolean.class.cast(Boolean.valueOf(filterWord.hasSecondOptions()));
            case 241107:
                return (T) Boolean.class.cast(Boolean.valueOf(filterWord.isValid()));
            case 241108:
                return (T) filterWord.getOptions();
        }
        a(i, valueSet, cls);
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public void addOption(FilterWord filterWord) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new f(filterWord));
        this.a.call(241101, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.FilterWord
    public void setIsSelected(boolean z) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, z);
        this.a.call(241102, bVarA.b(), Void.class);
    }
}
