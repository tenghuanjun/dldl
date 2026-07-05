package com.bytedance.sdk.openadsdk.downloadnew;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTDownloadEventLogger;
import com.bytedance.sdk.openadsdk.downloadnew.b;
import com.bytedance.sdk.openadsdk.downloadnew.core.DialogBuilder;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadAdapter;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadEventModel;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.g;
import com.ss.android.download.api.config.h;
import com.ss.android.download.api.config.l;
import com.ss.android.download.api.config.q;
import com.ss.android.download.api.config.r;
import com.ss.android.download.api.config.t;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.a;
import com.ss.android.downloadlib.addownload.a.a;
import com.ss.android.downloadlib.addownload.b.f;
import com.ss.android.downloadlib.b.j;
import com.ss.android.downloadlib.i;
import com.ss.android.socialbase.downloader.depend.IDownloadSettings;
import com.ss.android.socialbase.downloader.depend.IInstallAppHandler;
import com.ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c {
    public static volatile String a;
    public static ITTDownloadVisitor c;
    private static Context e;
    private static Map<Integer, ITTDownloadAdapter.OnEventLogHandler> f;
    private static final com.ss.android.download.api.download.a.a g;
    private static final AtomicBoolean d = new AtomicBoolean(false);
    public static boolean b = true;

    private static boolean g() {
        return false;
    }

    static {
        try {
            a = i().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
        } catch (Throwable unused) {
        }
        g = new com.ss.android.download.api.download.a.a() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.6
            @Override // com.ss.android.download.api.download.a.a
            public void a(DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig) {
                com.bytedance.sdk.openadsdk.api.c.b("TTDownloadVisitor", "completeListener: onDownloadStart");
            }

            @Override // com.ss.android.download.api.download.a.a
            public void a(DownloadInfo downloadInfo, String str) {
                com.bytedance.sdk.openadsdk.api.c.b("TTDownloadVisitor", "completeListener: onDownloadFinished");
            }

            @Override // com.ss.android.download.api.download.a.a
            public void b(DownloadInfo downloadInfo, String str) {
                com.bytedance.sdk.openadsdk.api.c.b("TTDownloadVisitor", "completeListener: onInstalled");
                c.c(str);
            }

            @Override // com.ss.android.download.api.download.a.a
            public void a(DownloadInfo downloadInfo, BaseException baseException, String str) {
                com.bytedance.sdk.openadsdk.api.c.b("TTDownloadVisitor", "completeListener: onDownloadFailed");
            }

            @Override // com.ss.android.download.api.download.a.a
            public void a(DownloadInfo downloadInfo) {
                com.bytedance.sdk.openadsdk.api.c.b("TTDownloadVisitor", "completeListener: onCanceled");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ITTDownloadVisitor f() {
        ITTDownloadVisitor iTTDownloadVisitor = c;
        if (iTTDownloadVisitor != null) {
            return iTTDownloadVisitor;
        }
        TTAdManager adManager = TTAdSdk.getAdManager();
        if (adManager == null) {
            return null;
        }
        return (ITTDownloadVisitor) adManager.getExtra(ITTDownloadVisitor.class, com.bytedance.sdk.openadsdk.downloadnew.a.a(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str) {
        com.ss.android.downloadad.api.a.b bVarA;
        JSONObject jSONObjectG;
        if (TextUtils.isEmpty(str) || (bVarA = f.a().a(str)) == null || (jSONObjectG = bVarA.g()) == null || f() == null) {
            return;
        }
        f().checkAutoControl(jSONObjectG, str);
    }

    public static void a(Context context) {
        if (context == null) {
            context = TTAppContextHolder.getContext();
        }
        if (context == null || d.get()) {
            return;
        }
        com.ss.android.socialbase.appdownloader.d.j().a(true);
        synchronized (c.class) {
            if (!d.get()) {
                e = context.getApplicationContext();
                if (f() != null) {
                    String strInitPath = f().initPath(b);
                    if (!TextUtils.isEmpty(strInitPath)) {
                        a = strInitPath;
                    }
                }
                d.set(b(e));
            }
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a = str;
    }

    public static i a() {
        a(i());
        return i.a(i());
    }

    public static boolean a(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener) {
        return a().e().a(context, uri, downloadModel, downloadEventConfig, downloadController, iDownloadButtonClickListener);
    }

    public static boolean a(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        return a().e().a(context, uri, downloadModel, downloadEventConfig, downloadController);
    }

    public static boolean a(Uri uri) {
        return j.a(uri);
    }

    public static void b() {
        a().g();
        if (f() != null) {
            f().clearAllData(a);
        }
    }

    public static void a(int i) {
        Map<Integer, ITTDownloadAdapter.OnEventLogHandler> map = f;
        if (map != null) {
            map.remove(Integer.valueOf(i));
        }
    }

    public static void a(int i, ITTDownloadAdapter.OnEventLogHandler onEventLogHandler) {
        if (onEventLogHandler != null) {
            if (f == null) {
                f = Collections.synchronizedMap(new WeakHashMap());
            }
            f.put(Integer.valueOf(i), onEventLogHandler);
        }
    }

    public static Map<Integer, ITTDownloadAdapter.OnEventLogHandler> c() {
        return f;
    }

    public static boolean a(String str, String str2, JSONObject jSONObject, Object obj) {
        Map<Integer, ITTDownloadAdapter.OnEventLogHandler> mapC;
        boolean z = false;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && jSONObject != null && (mapC = c()) != null) {
            for (Map.Entry<Integer, ITTDownloadAdapter.OnEventLogHandler> entry : mapC.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                ITTDownloadAdapter.OnEventLogHandler value = entry.getValue();
                if (value != null) {
                    boolean zOnEventLog = value.onEventLog(iIntValue, jSONObject.toString(), str, str2, obj);
                    if (!z && !zOnEventLog) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean b(Context context) {
        com.ss.android.download.api.a aVarA;
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            packageName = "";
        }
        if (g()) {
            try {
                aVarA = i.a(applicationContext).a("pangolin");
            } catch (Throwable unused) {
                aVarA = i.a(applicationContext).a();
            }
        } else {
            aVarA = i.a(applicationContext).a();
        }
        if (aVarA == null) {
            return false;
        }
        aVarA.a(new C0028c()).a(new a()).a(new d(applicationContext)).a(new b()).a(new com.ss.android.download.api.config.j() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.3
            @Override // com.ss.android.download.api.config.j
            public JSONObject a() {
                return c.h();
            }
        }).a(new com.ss.android.download.api.config.b() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.2
            @Override // com.ss.android.download.api.config.b
            public boolean a() {
                if (c.f() != null) {
                    return c.f().getAppIsBackground();
                }
                return false;
            }
        }).a(new a.C0086a().b("143").a("open_news").c("6.3.1.0").d(String.valueOf(6310)).a()).a(new q() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.1
            @Override // com.ss.android.download.api.config.q
            public byte[] a(byte[] bArr, int i) {
                return new byte[0];
            }
        }).a(packageName + ".TTFileProvider").a(a(applicationContext, h())).a();
        com.ss.android.downloadlib.g.a.a();
        i.a(applicationContext).d().a(1);
        i.a(applicationContext).a(g);
        com.ss.android.socialbase.appdownloader.d.j().a(new IInstallAppHandler() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.4
            @Override // com.ss.android.socialbase.downloader.depend.IInstallAppHandler
            public boolean installApp(Intent intent) {
                return false;
            }
        });
        TTDownloadEventLogger tTDownloadEventLogger = f() != null ? f().getTTDownloadEventLogger() : null;
        if (tTDownloadEventLogger != null) {
            tTDownloadEventLogger.onDownloadConfigReady();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject h() {
        try {
            ITTDownloadVisitor iTTDownloadVisitorF = f();
            if (iTTDownloadVisitorF != null) {
                JSONObject downloadSettings = iTTDownloadVisitorF.getDownloadSettings();
                if (downloadSettings.optInt("enable_app_install_receiver", 1) == 0) {
                    downloadSettings.put("enable_app_install_receiver", 0);
                }
                return downloadSettings;
            }
        } catch (Exception unused) {
        }
        return new JSONObject();
    }

    private static DownloaderBuilder a(Context context, JSONObject jSONObject) {
        return new DownloaderBuilder(context).downloadSetting(new IDownloadSettings() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.5
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadSettings
            public JSONObject get() {
                return c.h();
            }
        }).downloadExpSwitch(jSONObject.optInt("download_exp_switch_temp", 1040187391)).httpService(new e());
    }

    public static boolean a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            List<DownloadInfo> listB = com.ss.android.socialbase.appdownloader.d.j().b(context);
            if (!listB.isEmpty()) {
                for (DownloadInfo downloadInfo : listB) {
                    if (downloadInfo != null && str.equals(downloadInfo.getUrl())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Context i() {
        Context context = e;
        return context == null ? TTAppContextHolder.getContext() : context;
    }

    /* JADX INFO: renamed from: com.bytedance.sdk.openadsdk.downloadnew.c$c, reason: collision with other inner class name */
    private static class C0028c implements com.ss.android.download.api.config.i {
        @Override // com.ss.android.download.api.config.i
        public void a(Activity activity, int i, String[] strArr, int[] iArr) {
        }

        @Override // com.ss.android.download.api.config.i
        public void a(Activity activity, String[] strArr, final t tVar) {
            if (c.f() != null) {
                c.f().requestPermission(activity, strArr, new ITTPermissionCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.c.1
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback
                    public void onGranted() {
                        t tVar2 = tVar;
                        if (tVar2 != null) {
                            tVar2.a();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback
                    public void onDenied(String str) {
                        t tVar2 = tVar;
                        if (tVar2 != null) {
                            tVar2.a(str);
                        }
                    }
                });
            }
        }

        @Override // com.ss.android.download.api.config.i
        public boolean a(Context context, String str) {
            if (c.f() != null) {
                return c.f().hasPermission(context, str);
            }
            return false;
        }
    }

    private static class b implements h {
        private b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
        @Override // com.ss.android.download.api.config.h
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(java.lang.String r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.Object> r7, final com.ss.android.download.api.config.r r8) {
            /*
                r4 = this;
                int r0 = r5.hashCode()
                r1 = 70454(0x11336, float:9.8727E-41)
                r2 = 1
                r3 = 0
                if (r0 == r1) goto L1b
                r1 = 2461856(0x2590a0, float:3.449795E-39)
                if (r0 == r1) goto L11
                goto L25
            L11:
                java.lang.String r0 = "POST"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L25
                r5 = 1
                goto L26
            L1b:
                java.lang.String r0 = "GET"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L25
                r5 = 0
                goto L26
            L25:
                r5 = -1
            L26:
                if (r5 == 0) goto L2a
                if (r5 == r2) goto L2b
            L2a:
                r2 = 0
            L2b:
                com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor r5 = com.bytedance.sdk.openadsdk.downloadnew.c.d()
                if (r5 == 0) goto L3d
                com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor r5 = com.bytedance.sdk.openadsdk.downloadnew.c.d()
                com.bytedance.sdk.openadsdk.downloadnew.c$b$1 r0 = new com.bytedance.sdk.openadsdk.downloadnew.c$b$1
                r0.<init>()
                r5.execute(r2, r6, r7, r0)
            L3d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.openadsdk.downloadnew.c.b.a(java.lang.String, java.lang.String, java.util.Map, com.ss.android.download.api.config.r):void");
        }

        @Override // com.ss.android.download.api.config.h
        public void a(String str, byte[] bArr, String str2, int i, final r rVar) {
            if (c.f() != null) {
                c.f().postBody(str, bArr, str2, new ITTHttpCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.b.2
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onResponse(String str3) {
                        r rVar2 = rVar;
                        if (rVar2 != null) {
                            rVar2.a(str3);
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onError(Throwable th) {
                        r rVar2 = rVar;
                        if (rVar2 != null) {
                            rVar2.a(th);
                        }
                    }
                });
            }
        }
    }

    public static class e implements IDownloadHttpService {
        @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpService
        public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
            final b.a aVarA = com.bytedance.sdk.openadsdk.downloadnew.b.a(str, list);
            if (aVarA != null) {
                return new IDownloadHttpConnection() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.e.1
                    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                    public void cancel() {
                    }

                    @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
                    public InputStream getInputStream() {
                        return aVarA.a;
                    }

                    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                    public String getResponseHeaderField(String str2) {
                        if (aVarA.b != null) {
                            return aVarA.b.get(str2);
                        }
                        return null;
                    }

                    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                    public int getResponseCode() {
                        return aVarA.c;
                    }

                    @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
                    public void end() {
                        try {
                            aVarA.d.disconnect();
                        } catch (Exception unused) {
                        }
                    }
                };
            }
            return null;
        }
    }

    public static class d implements l {
        private final WeakReference<Context> a;

        public d(Context context) {
            this.a = new WeakReference<>(context);
        }

        @Override // com.ss.android.download.api.config.l
        public void a(int i, Context context, DownloadModel downloadModel, String str, Drawable drawable, int i2) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Toast.makeText(context, str, 0).show();
            } catch (Exception e) {
                Logger.e("LibUIFactory", "showToastWithDuration e " + e.getMessage());
            }
        }

        @Override // com.ss.android.download.api.config.l
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AlertDialog b(com.ss.android.download.api.model.b bVar) {
            if (bVar != null && c.f() != null) {
                if (bVar.a != null && (bVar.a instanceof Activity)) {
                    return c.f().showDialogBySelf((Activity) bVar.a, bVar.j == 1, c(bVar));
                }
                c.f().showDialogByDelegate(this.a, bVar.j == 1, c(bVar));
            }
            return null;
        }

        private DialogBuilder c(final com.ss.android.download.api.model.b bVar) {
            return DialogBuilder.builder().setTitle(bVar.b).setMessage(bVar.c).setNegativeBtnText(bVar.e).setPositiveBtnText(bVar.d).setIcon(bVar.g).setDialogStatusChangedListener(new IDialogStatusChangedListener() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.d.1
                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onPositiveBtnClick(DialogInterface dialogInterface) {
                    if (bVar.h != null) {
                        bVar.h.a(dialogInterface);
                    }
                }

                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onNegativeBtnClick(DialogInterface dialogInterface) {
                    if (bVar.h != null) {
                        try {
                            bVar.h.b(dialogInterface);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onCancel(DialogInterface dialogInterface) {
                    if (bVar.h != null) {
                        bVar.h.c(dialogInterface);
                    }
                }
            });
        }
    }

    public static class a implements g {
        @Override // com.ss.android.download.api.config.g
        public void a(com.ss.android.download.api.model.c cVar) {
            com.bytedance.sdk.openadsdk.api.c.b("LibEventLogger", "onV3Event");
            a(cVar, true);
        }

        @Override // com.ss.android.download.api.config.g
        public void b(com.ss.android.download.api.model.c cVar) {
            com.bytedance.sdk.openadsdk.api.c.b("LibEventLogger", "onEvent called");
            a(cVar, false);
            c(cVar);
        }

        private void c(com.ss.android.download.api.model.c cVar) {
            if (cVar == null) {
                return;
            }
            Object objL = cVar.l();
            TTDownloadEventModel label = TTDownloadEventModel.builder().setTag(cVar.b()).setExtJson(cVar.h()).setMaterialMeta(objL instanceof JSONObject ? (JSONObject) objL : null).setLabel(cVar.c());
            boolean z = "download_notification".equals(cVar.b()) || "landing_h5_download_ad_button".equals(cVar.b());
            if (c.f() != null) {
                c.f().executeLogUpload(label, z);
            }
        }

        private void a(com.ss.android.download.api.model.c cVar, boolean z) {
            TTDownloadEventLogger tTDownloadEventLogger;
            if (c.f() == null || (tTDownloadEventLogger = c.f().getTTDownloadEventLogger()) == null || cVar == null) {
                return;
            }
            if (tTDownloadEventLogger.shouldFilterOpenSdkLog() && c.f().isOpenSdkEvent(cVar.toString())) {
                return;
            }
            if (z) {
                tTDownloadEventLogger.onV3Event(c.b(cVar));
            } else {
                tTDownloadEventLogger.onEvent(c.b(cVar));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject b(com.ss.android.download.api.model.c cVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", cVar.a());
            jSONObject.put(TTDownloadField.TT_TAG, cVar.b());
            jSONObject.put(TTDownloadField.TT_LABEL, cVar.c());
            jSONObject.put(TTDownloadField.TT_IS_AD, cVar.d());
            jSONObject.put("adId", cVar.e());
            jSONObject.put(TTDownloadField.TT_LOG_EXTRA, cVar.f());
            jSONObject.put("extValue", cVar.g());
            jSONObject.put("extJson", cVar.h());
            jSONObject.put(TTDownloadField.TT_PARAMS_JSON, cVar.i());
            jSONObject.put("eventSource", cVar.k());
            jSONObject.put(TTDownloadField.TT_EXTRA_OBJECT, cVar.l());
            jSONObject.put(TTDownloadField.TT_CLICK_TRACK_URL, cVar.j());
            jSONObject.put("isV3", cVar.m());
            jSONObject.put("V3EventName", cVar.n());
            jSONObject.put("V3EventParams", cVar.o());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static boolean a(Activity activity, final ExitInstallListener exitInstallListener) {
        return com.ss.android.downloadlib.addownload.a.a.a().a(activity, false, new a.InterfaceC0092a() { // from class: com.bytedance.sdk.openadsdk.downloadnew.c.7
            @Override // com.ss.android.downloadlib.addownload.a.a.InterfaceC0092a
            public void a() {
                ExitInstallListener exitInstallListener2 = exitInstallListener;
                if (exitInstallListener2 != null) {
                    exitInstallListener2.onExitInstall();
                }
            }
        });
    }
}
