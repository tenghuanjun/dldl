package com.sy37sdk.account.PlatformAnnouncement;

import android.os.Bundle;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.UrlConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PlatformAnnouncementManager {
    private static final PlatformAnnouncementManager platformFaultManager = new PlatformAnnouncementManager();

    private PlatformAnnouncementManager() {
    }

    public static PlatformAnnouncementManager getInstance() {
        return platformFaultManager;
    }

    public void getPlatformAnnouncementRequest(final SQResultListener sQResultListener) {
        SqRequest.of(UrlConstant.PLATFORM_FAULT_URL).signV3().addParamsTransformer(new CommonParamsV3()).get(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.PlatformAnnouncement.PlatformAnnouncementManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                Bundle bundle = new Bundle();
                bundle.putString("title", jSONObject.optString("title"));
                bundle.putBoolean("is_show", jSONObject.optBoolean("is_show"));
                bundle.putString("content", jSONObject.optString("content"));
                sQResultListener.onSuccess(bundle);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                sQResultListener.onFailture(i, str);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                sQResultListener.onFailture(i2, str);
            }
        });
    }
}
