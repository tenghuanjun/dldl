package com.bytedance.sdk.openadsdk.c.a.c;

import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.AdSlot;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    public static final ValueSet a(final AdSlot adSlot) {
        com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a();
        if (adSlot == null) {
            return null;
        }
        bVarA.a(260001, adSlot.getAdId());
        bVarA.a(260002, adSlot.getCreativeId());
        bVarA.a(260003, adSlot.getExt());
        bVarA.a(260004, adSlot.getCodeId());
        bVarA.a(260005, adSlot.isAutoPlay());
        bVarA.a(260006, adSlot.getImgAcceptedWidth());
        bVarA.a(260007, adSlot.getImgAcceptedHeight());
        bVarA.a(260008, adSlot.getExpressViewAcceptedWidth());
        bVarA.a(260009, adSlot.getExpressViewAcceptedHeight());
        bVarA.a(260010, adSlot.isSupportDeepLink());
        bVarA.a(260011, adSlot.isSupportRenderConrol());
        bVarA.a(2600012, adSlot.getAdCount());
        bVarA.a(260013, adSlot.getMediaExtra());
        bVarA.a(260014, adSlot.getUserID());
        bVarA.a(260015, adSlot.getOrientation());
        bVarA.a(260016, adSlot.getNativeAdType());
        bVarA.a(260017, adSlot.getExternalABVid());
        bVarA.a(260018, adSlot.getAdloadSeq());
        bVarA.a(260019, adSlot.getPrimeRit());
        bVarA.a(260020, adSlot.getAdType());
        bVarA.a(260021, adSlot.getBidAdm());
        bVarA.a(260022, adSlot.getUserData());
        bVarA.a(260023, adSlot.getAdLoadType());
        bVarA.a(260024, new ValueSet.ValueGetter<String>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.b.1
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public String get() {
                return adSlot.getRewardName();
            }
        });
        bVarA.a(260025, new ValueSet.ValueGetter<Integer>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.b.2
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer get() {
                return Integer.valueOf(adSlot.getRewardAmount());
            }
        });
        bVarA.a(260026, new ValueSet.ValueGetter<Boolean>() { // from class: com.bytedance.sdk.openadsdk.c.a.c.b.3
            @Override // com.bykv.vk.openvk.api.proto.ValueSet.ValueGetter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean get() {
                return Boolean.valueOf(adSlot.isSupportIconStyle());
            }
        });
        return bVarA.b();
    }
}
