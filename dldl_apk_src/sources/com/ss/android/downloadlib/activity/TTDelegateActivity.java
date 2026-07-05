package com.ss.android.downloadlib.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.ss.android.download.api.config.l;
import com.ss.android.download.api.config.t;
import com.ss.android.download.api.model.b;
import com.ss.android.downloadad.api.a.b;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadlib.addownload.a.e;
import com.ss.android.downloadlib.addownload.b.f;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.addownload.n;
import com.ss.android.downloadlib.g.h;
import com.ss.android.downloadlib.g.j;
import com.ss.android.downloadlib.g.m;
import com.ss.android.downloadlib.guide.install.a;
import com.ss.android.downloadlib.i;
import com.ss.android.socialbase.appdownloader.c;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class TTDelegateActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static a d;
    protected Intent a = null;
    private boolean b;
    private b c;

    public static void a(String str, String[] strArr) {
        Intent intent = new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 1);
        intent.putExtra("permission_id_key", str);
        intent.putExtra("permission_content_key", strArr);
        if (k.a() != null) {
            k.a().startActivity(intent);
        }
    }

    public static void a(String str, com.ss.android.downloadad.api.a.a aVar) {
        Intent intentC = c(aVar);
        intentC.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intentC.putExtra("type", 2);
        intentC.putExtra(AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL, str);
        if (k.a() != null) {
            k.a().startActivity(intentC);
        }
    }

    public static void b(String str, com.ss.android.downloadad.api.a.a aVar) {
        Intent intentC = c(aVar);
        intentC.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intentC.putExtra("type", 11);
        intentC.putExtra("package_name", str);
        if (k.a() != null) {
            k.a().startActivity(intentC);
        }
    }

    public static void a(com.ss.android.downloadad.api.a.a aVar) {
        Intent intentC = c(aVar);
        intentC.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intentC.putExtra("type", 4);
        intentC.putExtra("model_id", aVar.b());
        if (k.a() != null) {
            k.a().startActivity(intentC);
        }
    }

    public static void a(com.ss.android.downloadad.api.a.a aVar, a aVar2) {
        Intent intentC = c(aVar);
        intentC.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intentC.putExtra("type", 9);
        d = aVar2;
        if (k.a() != null) {
            k.a().startActivity(intentC);
        }
    }

    public static void a(long j) {
        Intent intent = new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 10);
        intent.putExtra("app_info_id", j);
        if (k.a() != null) {
            k.a().startActivity(intent);
        }
    }

    public static void a(String str, long j, String str2, JSONObject jSONObject) {
        Intent intent = new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 12);
        intent.putExtra("package_name", str);
        intent.putExtra("model_id", j);
        intent.putExtra("param", str2);
        intent.putExtra("ext_json", jSONObject.toString());
        if (k.a() != null) {
            k.a().startActivity(intent);
        }
    }

    public static void a(String str, long j, String str2) {
        Intent intent = new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 13);
        intent.putExtra("package_name", str);
        intent.putExtra("model_id", j);
        intent.putExtra("need_comment", str2);
        if (k.a() != null) {
            k.a().startActivity(intent);
        }
    }

    public static void b(String str, long j, String str2) {
        Intent intent = new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 14);
        intent.putExtra("package_name", str);
        intent.putExtra("model_id", j);
        intent.putExtra("market_app_id", str2);
        if (k.a() != null) {
            k.a().startActivity(intent);
        }
    }

    public static void a(String str, long j) {
        Intent intent = new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 15);
        intent.putExtra("package_name", str);
        intent.putExtra("model_id", j);
        if (k.a() != null) {
            k.a().startActivity(intent);
        }
    }

    private static Intent c(com.ss.android.downloadad.api.a.a aVar) {
        return new Intent(k.a(), (Class<?>) TTDelegateActivity.class);
    }

    public static void b(com.ss.android.downloadad.api.a.a aVar) {
        a(aVar, 5, "", "", "", "");
    }

    public static void a(com.ss.android.downloadad.api.a.a aVar, String str) {
        a(aVar, 19, "", "", "", str);
    }

    public static void a(com.ss.android.downloadad.api.a.a aVar, String str, String str2, String str3) {
        a(aVar, 8, str, str2, str3, "");
    }

    public static void a(com.ss.android.downloadad.api.a.a aVar, String str, String str2, String str3, String str4) {
        a(aVar, 21, str, str2, str3, str4);
    }

    public static void b(com.ss.android.downloadad.api.a.a aVar, String str, String str2, String str3) {
        a(aVar, 7, str, str2, str3, "");
    }

    public static void b(com.ss.android.downloadad.api.a.a aVar, String str, String str2, String str3, String str4) {
        a(aVar, 20, str, str2, str3, str4);
    }

    private static void a(com.ss.android.downloadad.api.a.a aVar, int i, String str, String str2, String str3, String str4) {
        Intent intentC = c(aVar);
        intentC.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intentC.putExtra("type", i);
        if (!TextUtils.isEmpty(str2)) {
            intentC.putExtra("positive_button_text", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            intentC.putExtra("negative_button_text", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            intentC.putExtra("delete_button_text", str4);
        }
        if (!TextUtils.isEmpty(str)) {
            intentC.putExtra("message_text", str);
        }
        intentC.putExtra("model_id", aVar.b());
        if (k.a() != null) {
            k.a().startActivity(intentC);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        this.a = getIntent();
        k.b(this);
        a();
    }

    private void b() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.a = intent;
        k.b(this);
        a();
    }

    @Override // android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        k.f().a(this, i, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onStop() {
        b bVar;
        DownloadInfo downloadInfoB;
        super.onStop();
        if (!this.b || (bVar = this.c) == null) {
            return;
        }
        if (!TextUtils.isEmpty(bVar.af())) {
            downloadInfoB = i.a(k.a()).a(this.c.af(), null, true);
        } else {
            downloadInfoB = i.a(k.a()).b(this.c.a());
        }
        if (downloadInfoB == null || downloadInfoB.getCurBytes() < downloadInfoB.getTotalBytes() || isFinishing()) {
            return;
        }
        finish();
    }

    protected void a() {
        Intent intent = this.a;
        if (intent == null) {
            return;
        }
        switch (intent.getIntExtra("type", 0)) {
            case 1:
                b(this.a.getStringExtra("permission_id_key"), this.a.getStringArrayExtra("permission_content_key"));
                break;
            case 2:
                a(this.a.getStringExtra(AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL));
                break;
            case 3:
            case 6:
            case 16:
            case 17:
            case 18:
            default:
                c.a((Activity) this);
                break;
            case 4:
                b(this.a.getLongExtra("model_id", 0L));
                break;
            case 5:
                a(this.a.getLongExtra("model_id", 0L), "");
                break;
            case 7:
            case 8:
            case 20:
            case 21:
                c();
                break;
            case 9:
                a aVar = d;
                if (aVar != null) {
                    aVar.a();
                }
                c.a((Activity) this);
                break;
            case 10:
                c(this.a.getLongExtra("app_info_id", 0L));
                break;
            case 11:
                b(this.a.getStringExtra("package_name"));
                break;
            case 12:
                h.a(this, this.a.getStringExtra("package_name"), this.a.getLongExtra("model_id", 0L), this.a.getStringExtra("param"), this.a.getStringExtra("ext_json"));
                c.a((Activity) this);
                break;
            case 13:
                h.a(this, this.a.getStringExtra("package_name"), this.a.getLongExtra("model_id", 0L), this.a.getStringExtra("need_comment"));
                c.a((Activity) this);
                break;
            case 14:
                h.b(this, this.a.getStringExtra("package_name"), this.a.getLongExtra("model_id", 0L), this.a.getStringExtra("market_app_id"));
                c.a((Activity) this);
                break;
            case 15:
                h.a(this, this.a.getStringExtra("package_name"), this.a.getLongExtra("model_id", 0L));
                c.a((Activity) this);
                break;
            case 19:
                a(this.a.getLongExtra("model_id", 0L), this.a.getStringExtra("delete_button_text"));
                break;
        }
        this.a = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c() {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.activity.TTDelegateActivity.c():void");
    }

    private void a(long j, String str) {
        if (n.a() == null) {
            return;
        }
        b bVarD = f.a().d(j);
        if (bVarD != null) {
            DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(bVarD.s());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("time_after_click", Long.valueOf(System.currentTimeMillis() - bVarD.T()));
                jSONObject.putOpt("click_download_size", Long.valueOf(bVarD.U()));
                if (downloadInfo != null) {
                    jSONObject.putOpt("download_length", Long.valueOf(downloadInfo.getCurBytes()));
                    jSONObject.putOpt("download_percent", Long.valueOf(downloadInfo.getCurBytes() / downloadInfo.getTotalBytes()));
                    jSONObject.putOpt("download_apk_size", Long.valueOf(downloadInfo.getTotalBytes()));
                    jSONObject.putOpt("download_current_bytes", Integer.valueOf((int) (downloadInfo.getCurBytes() / 1048576)));
                    jSONObject.putOpt("download_total_bytes", Integer.valueOf((int) (downloadInfo.getTotalBytes() / 1048576)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(str)) {
                com.ss.android.downloadlib.d.a.a().a("cancel_pause_reserve_wifi_dialog_show", jSONObject, bVarD);
            } else {
                com.ss.android.downloadlib.d.a.a().b("pause_reserve_wifi_dialog_show", jSONObject, bVarD);
            }
        }
        e.a aVarA = new e.a(this).a(false).a(n.a());
        if (!TextUtils.isEmpty(str)) {
            aVarA.d(str).a(n.b());
        }
        aVarA.a().show();
        this.b = true;
        this.c = bVarD;
    }

    private void b(final String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length <= 0) {
            c.a((Activity) this);
            return;
        }
        t tVar = new t() { // from class: com.ss.android.downloadlib.activity.TTDelegateActivity.1
            private WeakReference<Activity> c;

            {
                this.c = new WeakReference<>(TTDelegateActivity.this);
            }

            @Override // com.ss.android.download.api.config.t
            public void a() {
                j.a(str);
                c.a(this.c.get());
            }

            @Override // com.ss.android.download.api.config.t
            public void a(String str2) {
                j.a(str, str2);
                c.a(this.c.get());
            }
        };
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                k.f().a(this, strArr, tVar);
                return;
            } catch (Exception e) {
                k.u().a(e, "requestPermission");
                tVar.a();
                return;
            }
        }
        tVar.a();
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            try {
                Uri uri = Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                intent.putExtra(AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL, str);
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                if (DownloadSetting.obtainGlobal().optBugFix("fix_app_link_flag")) {
                    intent.addFlags(67108864);
                }
                intent.putExtra("start_only_for_android", true);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            c.a((Activity) this);
        }
    }

    private void b(String str) {
        Intent intentG = m.g(this, str);
        if (intentG == null) {
            return;
        }
        try {
            try {
                intentG.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                intentG.putExtra("start_only_for_android", true);
                startActivity(intentG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            c.a((Activity) this);
        }
    }

    private void b(long j) {
        final b bVarD = f.a().d(j);
        if (bVarD == null) {
            com.ss.android.downloadlib.e.c.a().a("showOpenAppDialogInner nativeModel null");
            c.a((Activity) this);
            return;
        }
        l lVarD = k.d();
        b.a aVarA = new b.a(this).a("已安装完成");
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(bVarD.N()) ? "刚刚下载的应用" : bVarD.N();
        lVarD.b(aVarA.b(String.format("%1$s已安装完成，是否立即打开？", objArr)).c("打开").d("取消").a(false).a(m.d(this, bVarD.e())).a(new b.InterfaceC0087b() { // from class: com.ss.android.downloadlib.activity.TTDelegateActivity.2
            @Override // com.ss.android.download.api.model.b.InterfaceC0087b
            public void a(DialogInterface dialogInterface) {
                com.ss.android.downloadlib.b.a.b(bVarD);
                TTDelegateActivity tTDelegateActivity = TTDelegateActivity.this;
                if (tTDelegateActivity != null && !tTDelegateActivity.isFinishing()) {
                    dialogInterface.dismiss();
                }
                c.a((Activity) TTDelegateActivity.this);
            }

            @Override // com.ss.android.download.api.model.b.InterfaceC0087b
            public void b(DialogInterface dialogInterface) {
                com.ss.android.downloadlib.d.a.a().b("market_openapp_cancel", bVarD);
                TTDelegateActivity tTDelegateActivity = TTDelegateActivity.this;
                if (tTDelegateActivity != null && !tTDelegateActivity.isFinishing()) {
                    dialogInterface.dismiss();
                }
                c.a((Activity) TTDelegateActivity.this);
            }

            @Override // com.ss.android.download.api.model.b.InterfaceC0087b
            public void c(DialogInterface dialogInterface) {
                c.a((Activity) TTDelegateActivity.this);
            }
        }).a(2).a());
        com.ss.android.downloadlib.d.a.a().b("market_openapp_window_show", bVarD);
    }

    private void c(long j) {
        new com.ss.android.downloadlib.addownload.compliance.a(this, j).show();
    }
}
