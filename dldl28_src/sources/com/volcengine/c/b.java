package com.volcengine.c;

import com.bytedance.downloader.core.DownloadRequest;
import com.bytedance.downloader.core.DownloadResponse;
import com.volcengine.common.innerapi.DownloadService;
import com.volcengine.common.util.CompatConsumer;
import com.volcengine.j.d;
import com.volcengine.j.e;

/* JADX INFO: loaded from: classes3.dex */
public class b implements DownloadService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f1112a = new a();

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(DownloadService.Callback callback, DownloadResponse downloadResponse) {
        callback.onSuccess(new DownloadService.Response(downloadResponse.getUrl(), downloadResponse.getFileName(), downloadResponse.getSavePath(), downloadResponse.getMd5()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(DownloadService.Callback callback, DownloadResponse downloadResponse, Integer num) {
        callback.onProgress(new DownloadService.Response(downloadResponse.getUrl(), downloadResponse.getFileName(), downloadResponse.getSavePath(), downloadResponse.getMd5()), num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(DownloadService.Callback callback, DownloadResponse downloadResponse, Integer num, String str) {
        callback.onFailure(new DownloadService.Response(downloadResponse.getUrl(), downloadResponse.getFileName(), downloadResponse.getSavePath(), downloadResponse.getMd5()), num.intValue(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(DownloadService.Callback callback, DownloadResponse downloadResponse, String str) {
        callback.onTick(new DownloadService.Response(downloadResponse.getUrl(), downloadResponse.getFileName(), downloadResponse.getSavePath(), downloadResponse.getMd5()), str);
    }

    @Override // com.volcengine.common.innerapi.DownloadService
    public void cancel(String str, String str2, String str3) {
        this.f1112a.a(new DownloadRequest.Builder().url(str).fileName(str2).savePath(str3).build());
    }

    @Override // com.volcengine.common.innerapi.DownloadService
    public void cancelAll() {
        this.f1112a.a();
    }

    @Override // com.volcengine.common.innerapi.DownloadService
    public void downloadFile(String str, String str2, String str3, String str4, final DownloadService.Callback callback) {
        this.f1112a.b(new DownloadRequest.Builder().url(str).md5(str4).fileName(str2).savePath(str3).hostIpList(this.f1112a.b(str)).build());
        this.f1112a.a(new CompatConsumer() { // from class: com.volcengine.c.b$$ExternalSyntheticLambda0
            @Override // com.volcengine.common.util.CompatConsumer
            public final void accept(Object obj) {
                b.a(callback, (DownloadResponse) obj);
            }
        });
        this.f1112a.a(new e() { // from class: com.volcengine.c.b$$ExternalSyntheticLambda1
            @Override // com.volcengine.j.e
            public final void a(Object obj, Object obj2, Object obj3) {
                b.a(callback, (DownloadResponse) obj, (Integer) obj2, (String) obj3);
            }
        });
        this.f1112a.a(new d() { // from class: com.volcengine.c.b$$ExternalSyntheticLambda2
            @Override // com.volcengine.j.d
            public final void a(Object obj, Object obj2) {
                b.a(callback, (DownloadResponse) obj, (Integer) obj2);
            }
        });
        this.f1112a.b(new d() { // from class: com.volcengine.c.b$$ExternalSyntheticLambda3
            @Override // com.volcengine.j.d
            public final void a(Object obj, Object obj2) {
                b.a(callback, (DownloadResponse) obj, (String) obj2);
            }
        });
    }
}
