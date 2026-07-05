package com.bytedance.sdk.openadsdk.c.a.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bytedance.sdk.openadsdk.CSJSplashAd;
import com.bytedance.sdk.openadsdk.TTAdInteractionListener;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b implements CSJSplashAd {
    private final Bridge a;

    public b(Bridge bridge) {
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

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public View getSplashView() {
        return (View) this.a.values().objectValue(110001, View.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public View getSplashClickEyeView() {
        return (View) this.a.values().objectValue(110002, View.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public View getSplashCardView() {
        return (View) this.a.values().objectValue(110003, View.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public int getInteractionType() {
        return this.a.values().intValue(110004);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public Map<String, Object> getMediaExtraInfo() {
        return (Map) this.a.values().objectValue(110005, Map.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public int[] getSplashClickEyeSizeToDp() {
        return (int[]) this.a.values().objectValue(110006, int[].class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void hideSkipButton() {
        this.a.call(110101, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void setDownloadListener(TTAppDownloadListener tTAppDownloadListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.c.a.b.b(tTAppDownloadListener));
        this.a.call(110102, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void setSplashAdListener(CSJSplashAd.SplashAdListener splashAdListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.b.a.a.a.a(splashAdListener));
        this.a.call(110103, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void startClickEye() {
        this.a.call(110104, com.bykv.a.a.a.a.b.a(0).b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void setSplashClickEyeListener(CSJSplashAd.SplashClickEyeListener splashClickEyeListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.b.a.a.a.c(splashClickEyeListener));
        this.a.call(110105, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void setSplashCardListener(CSJSplashAd.SplashCardListener splashCardListener) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, new com.bytedance.sdk.openadsdk.b.a.a.a.b(splashCardListener));
        this.a.call(110106, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void showSplashView(ViewGroup viewGroup) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, viewGroup);
        this.a.call(110108, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void showSplashClickEyeView(ViewGroup viewGroup) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(1);
        bVarA.a(0, viewGroup);
        this.a.call(110107, bVarA.b(), Void.class);
    }

    @Override // com.bytedance.sdk.openadsdk.CSJSplashAd
    public void showSplashCardView(ViewGroup viewGroup, Activity activity) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a(2);
        bVarA.a(0, viewGroup);
        bVarA.a(1, activity);
        this.a.call(110109, bVarA.b(), Void.class);
    }
}
