package com.bytedance.sdk.openadsdk.l.a.a.a;

import android.view.View;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements Bridge {
    private ValueSet a = com.bykv.a.a.a.a.b.a;
    private final TTNativeExpressAd.AdInteractionListener b;

    protected void a(int i, ValueSet valueSet, Class cls) {
    }

    public a(TTNativeExpressAd.AdInteractionListener adInteractionListener) {
        this.b = adInteractionListener;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return this.a;
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        TTNativeExpressAd.AdInteractionListener adInteractionListener = this.b;
        if (adInteractionListener == null) {
            return null;
        }
        switch (i) {
            case 151101:
                this.b.onAdClicked((View) valueSet.objectValue(0, View.class), valueSet.intValue(1));
                break;
            case 151102:
                this.b.onAdShow((View) valueSet.objectValue(0, View.class), valueSet.intValue(1));
                break;
            case 151103:
                this.b.onRenderFail((View) valueSet.objectValue(0, View.class), (String) valueSet.objectValue(1, String.class), valueSet.intValue(2));
                break;
            case 151104:
                this.b.onRenderSuccess((View) valueSet.objectValue(0, View.class), valueSet.floatValue(1), valueSet.floatValue(2));
                break;
            case 151105:
                adInteractionListener.onAdDismiss();
                break;
        }
        a(i, valueSet, cls);
        return null;
    }
}
