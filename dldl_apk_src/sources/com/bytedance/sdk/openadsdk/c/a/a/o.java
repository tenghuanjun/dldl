package com.bytedance.sdk.openadsdk.c.a.a;

import android.app.Activity;
import android.view.View;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.DislikeInfo;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdInteractionListener;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTDislikeDialogAbstract;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class o implements TTNativeExpressAd {
    private final Bridge a;

    public o(Bridge bridge) {
        this.a = bridge == null ? com.bykv.a.a.a.a.b.b : bridge;
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void win(Double d) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, d);
        this.a.call(210101, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void loss(Double d, String str, String str2) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(3);
        bVarA.a(0, d);
        bVarA.a(1, str);
        bVarA.a(2, str2);
        this.a.call(210102, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void setPrice(Double d) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, d);
        this.a.call(210103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTClientBidding
    public void setAdInteractionListener(TTAdInteractionListener tTAdInteractionListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.c.a.b.a(tTAdInteractionListener));
        this.a.call(210104, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public View getExpressAdView() {
        return (View) this.a.values().objectValue(150001, View.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public int getImageMode() {
        return this.a.values().intValue(150002);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public DislikeInfo getDislikeInfo() {
        return new d((Bridge) this.a.values().objectValue(150003, Bridge.class));
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setExpressInteractionListener(TTNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.l.a.a.a.b(expressAdInteractionListener));
        this.a.call(150101, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setExpressInteractionListener(TTNativeExpressAd.AdInteractionListener adInteractionListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.l.a.a.a.a(adInteractionListener));
        this.a.call(150102, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setDownloadListener(TTAppDownloadListener tTAppDownloadListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.c.a.b.b(tTAppDownloadListener));
        this.a.call(150103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public int getInteractionType() {
        return this.a.values().intValue(150004);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void render() {
        this.a.call(150104, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void destroy() {
        this.a.call(150105, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setDislikeCallback(Activity activity, TTAdDislike.DislikeInteractionCallback dislikeInteractionCallback) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(2);
        bVarA.a(0, activity);
        bVarA.a(1, new com.bytedance.sdk.openadsdk.f.a.a.a.a(dislikeInteractionCallback));
        this.a.call(150106, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setDislikeDialog(TTDislikeDialogAbstract tTDislikeDialogAbstract) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, tTDislikeDialogAbstract);
        this.a.call(150107, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void uploadDislikeEvent(String str) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, str);
        this.a.call(150114, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public TTAdDislike getDislikeDialog(Activity activity) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, activity);
        return new h((Bridge) this.a.call(150108, bVarA.b(), Bridge.class));
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void showInteractionExpressAd(Activity activity) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, activity);
        this.a.call(150109, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setSlideIntervalTime(int i) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, i);
        this.a.call(150110, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setVideoAdListener(TTNativeExpressAd.ExpressVideoAdListener expressVideoAdListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.l.a.a.a.c(expressVideoAdListener));
        this.a.call(150111, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public void setCanInterruptVideoPlay(boolean z) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, z);
        this.a.call(150112, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd
    public Map<String, Object> getMediaExtraInfo() {
        return (Map) this.a.values().objectValue(150005, Map.class);
    }
}
