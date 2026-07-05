package com.ss.android.downloadlib;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.model.b;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.e.b;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b implements com.ss.android.downloadad.api.b {
    private static String a = b.class.getSimpleName();
    private static volatile b b;
    private i c = i.a(k.a());

    private b() {
    }

    public static b a() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    @Override // com.ss.android.downloadad.api.b
    public Dialog a(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        return a(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, false);
    }

    @Override // com.ss.android.downloadad.api.b
    public Dialog a(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, IDownloadButtonClickListener iDownloadButtonClickListener) {
        return a(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, false, iDownloadButtonClickListener);
    }

    public Dialog a(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, boolean z2) {
        return a(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, z2, null);
    }

    public Dialog a(final Context context, final String str, final boolean z, final DownloadModel downloadModel, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final DownloadStatusChangeListener downloadStatusChangeListener, final int i, final boolean z2, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        return (Dialog) com.ss.android.downloadlib.e.b.a(new b.a<Dialog>() { // from class: com.ss.android.downloadlib.b.1
            @Override // com.ss.android.downloadlib.e.b.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Dialog b() {
                return b.this.b(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, z2, iDownloadButtonClickListener);
            }
        });
    }

    public Dialog b(Context context, String str, boolean z, final DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, boolean z2, IDownloadButtonClickListener iDownloadButtonClickListener) {
        if (a(downloadModel.getId())) {
            if (z2) {
                a(downloadModel.getId(), downloadEventConfig, downloadController);
            } else {
                b(downloadModel.getId());
            }
            return null;
        }
        if (context == null || TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            return null;
        }
        this.c.a(context, i, downloadStatusChangeListener, downloadModel);
        final DownloadEventConfig downloadEventConfig2 = (DownloadEventConfig) m.a(downloadEventConfig, c());
        final DownloadController downloadController2 = (DownloadController) m.a(downloadController, b());
        downloadEventConfig2.setDownloadScene(1);
        if ((downloadController2.enableShowComplianceDialog() && com.ss.android.downloadlib.addownload.compliance.b.a().a(downloadModel)) ? true : (k.j().optInt("disable_lp_dialog", 0) == 1) | z) {
            this.c.a(downloadModel.getDownloadUrl(), downloadModel.getId(), 2, downloadEventConfig2, downloadController2, iDownloadButtonClickListener);
            return null;
        }
        com.ss.android.downloadlib.g.k.a(a, "tryStartDownload show dialog appName:" + downloadModel.getDownloadUrl(), null);
        Dialog dialogB = k.d().b(new b.a(context).a(downloadModel.getName()).b("确认要下载此应用吗？").c("确认").d("取消").a(new b.InterfaceC0087b() { // from class: com.ss.android.downloadlib.b.2
            @Override // com.ss.android.download.api.model.b.InterfaceC0087b
            public void a(DialogInterface dialogInterface) {
                b.this.c.a(downloadModel.getDownloadUrl(), downloadModel.getId(), 2, downloadEventConfig2, downloadController2);
                com.ss.android.downloadlib.d.a.a().a("landing_download_dialog_confirm", downloadModel, downloadEventConfig2, downloadController2);
                dialogInterface.dismiss();
            }

            @Override // com.ss.android.download.api.model.b.InterfaceC0087b
            public void b(DialogInterface dialogInterface) {
                com.ss.android.downloadlib.d.a.a().a("landing_download_dialog_cancel", downloadModel, downloadEventConfig2, downloadController2);
                dialogInterface.dismiss();
            }

            @Override // com.ss.android.download.api.model.b.InterfaceC0087b
            public void c(DialogInterface dialogInterface) {
                com.ss.android.downloadlib.d.a.a().a("landing_download_dialog_cancel", downloadModel, downloadEventConfig2, downloadController2);
            }
        }).a(0).a());
        com.ss.android.downloadlib.d.a.a().a("landing_download_dialog_show", downloadModel, downloadEventConfig2, downloadController2);
        return dialogB;
    }

    @Override // com.ss.android.downloadad.api.b
    public boolean a(Context context, long j, String str, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(j);
        if (bVarD != null) {
            this.c.a(context, i, downloadStatusChangeListener, bVarD.ai());
            return true;
        }
        DownloadModel downloadModelA = com.ss.android.downloadlib.addownload.b.f.a().a(j);
        if (downloadModelA == null) {
            return false;
        }
        this.c.a(context, i, downloadStatusChangeListener, downloadModelA);
        return true;
    }

    @Override // com.ss.android.downloadad.api.b
    public boolean a(long j, int i) {
        DownloadModel downloadModelA = com.ss.android.downloadlib.addownload.b.f.a().a(j);
        if (downloadModelA == null) {
            return false;
        }
        this.c.a(downloadModelA.getDownloadUrl(), i);
        return true;
    }

    public void a(long j, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        DownloadModel downloadModelA = com.ss.android.downloadlib.addownload.b.f.a().a(j);
        com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(j);
        if (downloadModelA == null && bVarD != null) {
            downloadModelA = bVarD.ai();
        }
        if (downloadModelA == null) {
            return;
        }
        if (downloadEventConfig == null || downloadController == null || (downloadEventConfig instanceof com.ss.android.download.api.download.c) || (downloadController instanceof com.ss.android.download.api.download.b)) {
            b(j);
        } else {
            downloadEventConfig.setDownloadScene(1);
            this.c.a(downloadModelA.getDownloadUrl(), j, 2, downloadEventConfig, downloadController);
        }
    }

    public void b(long j) {
        DownloadModel downloadModelA = com.ss.android.downloadlib.addownload.b.f.a().a(j);
        com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(j);
        if (downloadModelA == null && bVarD != null) {
            downloadModelA = bVarD.ai();
        }
        if (downloadModelA == null) {
            return;
        }
        DownloadEventConfig downloadEventConfigB = com.ss.android.downloadlib.addownload.b.f.a().b(j);
        DownloadController downloadControllerC = com.ss.android.downloadlib.addownload.b.f.a().c(j);
        if (downloadEventConfigB instanceof com.ss.android.download.api.download.c) {
            downloadEventConfigB = null;
        }
        if (downloadControllerC instanceof com.ss.android.download.api.download.b) {
            downloadControllerC = null;
        }
        if (bVarD == null) {
            if (downloadEventConfigB == null) {
                downloadEventConfigB = c();
            }
            if (downloadControllerC == null) {
                downloadControllerC = b();
            }
        } else {
            if (downloadEventConfigB == null) {
                downloadEventConfigB = new AdDownloadEventConfig.Builder().setClickButtonTag(bVarD.j()).setRefer(bVarD.i()).setIsEnableV3Event(bVarD.m()).setIsEnableClickEvent(false).setClickStartLabel("click_start_detail").setClickPauseLabel("click_pause_detail").setClickContinueLabel("click_continue_detail").setClickInstallLabel("click_install_detail").setStorageDenyLabel("storage_deny_detail").build();
            }
            if (downloadControllerC == null) {
                downloadControllerC = bVarD.ak();
            }
        }
        DownloadEventConfig downloadEventConfig = downloadEventConfigB;
        downloadEventConfig.setDownloadScene(1);
        this.c.a(downloadModelA.getDownloadUrl(), j, 2, downloadEventConfig, downloadControllerC);
    }

    @Override // com.ss.android.downloadad.api.b
    public boolean a(long j) {
        return (com.ss.android.downloadlib.addownload.b.f.a().a(j) == null && com.ss.android.downloadlib.addownload.b.f.a().d(j) == null) ? false : true;
    }

    @Override // com.ss.android.downloadad.api.b
    public boolean a(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        return a(context, uri, downloadModel, downloadEventConfig, downloadController, null);
    }

    @Override // com.ss.android.downloadad.api.b
    public boolean a(final Context context, final Uri uri, final DownloadModel downloadModel, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        return ((Boolean) com.ss.android.downloadlib.e.b.a(new b.a<Boolean>() { // from class: com.ss.android.downloadlib.b.3
            @Override // com.ss.android.downloadlib.e.b.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean b() {
                return Boolean.valueOf(b.this.b(context, uri, downloadModel, downloadEventConfig, downloadController, iDownloadButtonClickListener));
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener) {
        DownloadController downloadControllerB = downloadController;
        if (!com.ss.android.download.api.c.a.a(uri) || k.j().optInt("disable_market") == 1) {
            return false;
        }
        Context contextA = context == null ? k.a() : context;
        String strB = com.ss.android.download.api.c.a.b(uri);
        if (downloadModel == null) {
            return com.ss.android.downloadlib.g.i.a(contextA, strB).a() == 5;
        }
        if (!TextUtils.isEmpty(strB) && (downloadModel instanceof AdDownloadModel)) {
            ((AdDownloadModel) downloadModel).setPackageName(strB);
        }
        if (downloadControllerB != null) {
            downloadControllerB.setDownloadMode(2);
        } else if ((downloadModel instanceof AdDownloadModel) && TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            ((AdDownloadModel) downloadModel).setDownloadUrl(uri.toString());
            downloadControllerB = a(true);
        } else if (downloadModel.getDownloadUrl().startsWith("market")) {
            downloadControllerB = a(true);
        } else {
            downloadControllerB = b();
        }
        com.ss.android.downloadlib.addownload.b.e eVar = new com.ss.android.downloadlib.addownload.b.e(downloadModel.getId(), downloadModel, (DownloadEventConfig) m.a(downloadEventConfig, c()), downloadControllerB);
        com.ss.android.downloadlib.addownload.b.f.a().a(eVar.b);
        com.ss.android.downloadlib.addownload.b.f.a().a(eVar.a, eVar.c);
        com.ss.android.downloadlib.addownload.b.f.a().a(eVar.a, eVar.d);
        if (m.a(downloadModel) && DownloadSetting.obtainGlobal().optInt("app_link_opt") == 1 && com.ss.android.downloadlib.b.a.a(eVar)) {
            return true;
        }
        JSONObject jSONObject = new JSONObject();
        m.a(jSONObject, "market_url", uri.toString());
        m.a(jSONObject, "download_scene", (Object) 1);
        com.ss.android.downloadlib.d.a.a().b("market_click_open", jSONObject, eVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = com.ss.android.downloadlib.g.i.a(contextA, eVar, strB);
        String strA = m.a(gVarA.c(), "open_market");
        if (gVarA.a() == 5) {
            com.ss.android.downloadlib.b.a.a(strA, jSONObject, eVar, true);
            return true;
        }
        if (gVarA.a() != 6) {
            return true;
        }
        m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, Integer.valueOf(gVarA.b()));
        com.ss.android.downloadlib.d.a.a().b("market_open_failed", jSONObject, eVar);
        if (com.ss.android.downloadlib.addownload.i.a(downloadModel, iDownloadButtonClickListener)) {
            iDownloadButtonClickListener.handleMarketFailedComplianceDialog();
        }
        return false;
    }

    public static DownloadController b() {
        return a(false);
    }

    public static DownloadController a(boolean z) {
        AdDownloadController.Builder shouldUseNewWebView = new AdDownloadController.Builder().setLinkMode(0).setIsEnableBackDialog(true).setIsEnableMultipleDownload(false).setShouldUseNewWebView(false);
        if (z) {
            shouldUseNewWebView.setDownloadMode(2);
        } else {
            shouldUseNewWebView.setDownloadMode(0);
        }
        return shouldUseNewWebView.build();
    }

    public static DownloadEventConfig c() {
        return new AdDownloadEventConfig.Builder().setClickButtonTag("landing_h5_download_ad_button").setClickItemTag("landing_h5_download_ad_button").setClickStartLabel("click_start_detail").setClickPauseLabel("click_pause_detail").setClickContinueLabel("click_continue_detail").setClickInstallLabel("click_install_detail").setClickOpenLabel("click_open_detail").setStorageDenyLabel("storage_deny_detail").setDownloadScene(1).setIsEnableClickEvent(false).setIsEnableNoChargeClickEvent(true).setIsEnableV3Event(false).build();
    }
}
