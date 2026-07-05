package com.bytedance.sdk.openadsdk.api.a;

import com.bykv.vk.openvk.api.proto.EventListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ValueSetConstants;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class g extends com.bytedance.sdk.openadsdk.api.b implements OnItemClickListener {
    public g(EventListener eventListener) {
        this.a = eventListener;
    }

    @Override // com.ss.android.download.api.config.OnItemClickListener
    public void onItemClick(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        a(ValueSetConstants.VALUE_ON_ITEM_CLICK, a() ? null : com.bykv.a.a.a.a.a.a().a(com.bykv.a.a.a.a.b.a().a(ValueSetConstants.VALUE_DOWNLOAD_MODEL, new c(downloadModel)).a(ValueSetConstants.VALUE_DOWNLOAD_EVENT_CONFIG, new b(downloadEventConfig)).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER, new a(downloadController)).b()).b());
    }
}
