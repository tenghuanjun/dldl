package com.bytedance.sdk.openadsdk.c.a;

import android.util.Pair;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.CSJAdError;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.g.a.a.a.c;
import com.bytedance.sdk.openadsdk.g.a.a.a.d;
import com.bytedance.sdk.openadsdk.g.a.a.a.e;
import com.bytedance.sdk.openadsdk.g.a.a.a.f;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class a {
    public abstract Pair<Integer, String> a(Exception exc);

    public abstract void a(ValueSet valueSet, Bridge bridge);

    public abstract void a(ValueSet valueSet, Bridge bridge, int i);

    public abstract void b(ValueSet valueSet, Bridge bridge);

    public abstract void c(ValueSet valueSet, Bridge bridge);

    public abstract void d(ValueSet valueSet, Bridge bridge);

    public abstract void e(ValueSet valueSet, Bridge bridge);

    public abstract void f(ValueSet valueSet, Bridge bridge);

    public abstract void g(ValueSet valueSet, Bridge bridge);

    public abstract void h(ValueSet valueSet, Bridge bridge);

    public abstract void i(ValueSet valueSet, Bridge bridge);

    public TTAdNative a() {
        return new C0027a(this);
    }

    /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.c.a.a$a, reason: collision with other inner class name */
    public static class C0027a implements TTAdNative {
        private final a a;

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadInteractionExpressAd(AdSlot adSlot, TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
        }

        protected C0027a(a aVar) {
            this.a = aVar;
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadFeedAd(AdSlot adSlot, TTAdNative.FeedAdListener feedAdListener) {
            try {
                this.a.a(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new com.bytedance.sdk.openadsdk.g.a.a.a.b(feedAdListener));
            } catch (Exception e) {
                if (feedAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    feedAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadStream(AdSlot adSlot, TTAdNative.FeedAdListener feedAdListener) {
            try {
                this.a.b(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new com.bytedance.sdk.openadsdk.g.a.a.a.b(feedAdListener));
            } catch (Exception e) {
                if (feedAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    feedAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadDrawFeedAd(AdSlot adSlot, TTAdNative.DrawFeedAdListener drawFeedAdListener) {
            try {
                this.a.c(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new com.bytedance.sdk.openadsdk.g.a.a.a.a(drawFeedAdListener));
            } catch (Exception e) {
                if (drawFeedAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    drawFeedAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadNativeAd(AdSlot adSlot, TTAdNative.NativeAdListener nativeAdListener) {
            try {
                this.a.d(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new d(nativeAdListener));
            } catch (Exception e) {
                if (nativeAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    nativeAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadSplashAd(AdSlot adSlot, TTAdNative.CSJSplashAdListener cSJSplashAdListener, int i) {
            try {
                this.a.a(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new b(cSJSplashAdListener), i);
            } catch (Exception e) {
                if (cSJSplashAdListener != null) {
                    final Pair<Integer, String> pairA = this.a.a(e);
                    cSJSplashAdListener.onSplashLoadFail(new CSJAdError() { // from class: com.bytedance.sdk.openadsdk.c.a.a.a.1
                        @Override // com.bytedance.sdk.openadsdk.CSJAdError
                        public int getCode() {
                            return ((Integer) pairA.first).intValue();
                        }

                        @Override // com.bytedance.sdk.openadsdk.CSJAdError
                        public String getMsg() {
                            return (String) pairA.second;
                        }
                    });
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadRewardVideoAd(AdSlot adSlot, TTAdNative.RewardVideoAdListener rewardVideoAdListener) {
            try {
                this.a.e(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new f(rewardVideoAdListener));
            } catch (Exception e) {
                if (rewardVideoAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    rewardVideoAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadFullScreenVideoAd(AdSlot adSlot, TTAdNative.FullScreenVideoAdListener fullScreenVideoAdListener) {
            try {
                this.a.f(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new c(fullScreenVideoAdListener));
            } catch (Exception e) {
                if (fullScreenVideoAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    fullScreenVideoAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadNativeExpressAd(AdSlot adSlot, TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            try {
                this.a.g(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new e(nativeExpressAdListener));
            } catch (Exception e) {
                if (nativeExpressAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    nativeExpressAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadExpressDrawFeedAd(AdSlot adSlot, TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            try {
                this.a.h(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new e(nativeExpressAdListener));
            } catch (Exception e) {
                if (nativeExpressAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    nativeExpressAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadBannerExpressAd(AdSlot adSlot, TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            try {
                this.a.i(com.bytedance.sdk.openadsdk.c.a.c.b.a(adSlot), new e(nativeExpressAdListener));
            } catch (Exception e) {
                if (nativeExpressAdListener != null) {
                    Pair<Integer, String> pairA = this.a.a(e);
                    nativeExpressAdListener.onError(((Integer) pairA.first).intValue(), (String) pairA.second);
                }
            }
        }
    }
}
