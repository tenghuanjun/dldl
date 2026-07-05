package com.sq.oaid.sq_oaid;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sq.oaid.sq_oaid.CertManager;
import com.sqwan.common.route.FunctionRouter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CertManager {
    private static final String CERT_URL = "https://app-liefer.37.com.cn/app/channel/latest_cert/get";
    private static final Executor sExecutor = Executors.newSingleThreadExecutor();

    public interface CertCallBack {
        void onCertFail(String failReason, int code, Exception e);

        void onCertGet(String certContent);
    }

    private static String loadCertFromAsset(Context context) throws IOException {
        InputStream inputStreamOpen = context.getAssets().open(getCertFileName(context));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
                sb.append('\n');
            } else {
                String string = sb.toString();
                inputStreamOpen.close();
                bufferedReader.close();
                return string;
            }
        }
    }

    public static void getCert(final Context context, CertCallBack certCallBack) {
        File certFile = getCertFile(context);
        if (certFile.exists()) {
            if (checkCertAvailable(certFile)) {
                certCallBack.onCertGet(loadCert(context));
                return;
            } else {
                updateCert(context, certCallBack);
                return;
            }
        }
        try {
            final String strLoadCertFromAsset = loadCertFromAsset(context);
            sExecutor.execute(new Runnable() { // from class: com.sq.oaid.sq_oaid.-$$Lambda$CertManager$42n2sdonKBh8t4J3oEmKZlpqQwc
                @Override // java.lang.Runnable
                public final void run() {
                    Context context2 = context;
                    FileUtils.save(context2, CertManager.getCertFileName(context2), strLoadCertFromAsset);
                }
            });
            if (checkCertAvailable(getCertFile(context))) {
                certCallBack.onCertGet(strLoadCertFromAsset);
            } else {
                updateCert(context, certCallBack);
            }
        } catch (IOException unused) {
            updateCert(context, certCallBack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getCertFileName(Context context) {
        return context.getPackageName() + ".cert.pem";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File getCertFile(Context context) {
        return new File(context.getFilesDir() + File.separator + getCertFileName(context));
    }

    private static void updateCert(final Context context, final CertCallBack certCallBack) {
        Handler handler = new Handler(Looper.getMainLooper());
        new OkHttpClient().newCall(new Request.Builder().get().url("https://app-liefer.37.com.cn/app/channel/latest_cert/get?apk_name=" + context.getPackageName()).build()).enqueue(new AnonymousClass1(handler, certCallBack, context));
    }

    /* JADX INFO: renamed from: com.sq.oaid.sq_oaid.CertManager$1, reason: invalid class name */
    static class AnonymousClass1 implements Callback {
        final /* synthetic */ CertCallBack val$certCallBack;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Handler val$handler;

        AnonymousClass1(final Handler val$context, final CertCallBack val$certCallBack, final Context val$handler) {
            this.val$handler = val$context;
            this.val$certCallBack = val$certCallBack;
            this.val$context = val$handler;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, final IOException e) {
            Handler handler = this.val$handler;
            final CertCallBack certCallBack = this.val$certCallBack;
            handler.post(new Runnable() { // from class: com.sq.oaid.sq_oaid.-$$Lambda$CertManager$1$r6mX7PIlO7YhZo-YuqFKqo54Vho
                @Override // java.lang.Runnable
                public final void run() {
                    CertManager.AnonymousClass1.lambda$onFailure$0(certCallBack);
                }
            });
        }

        static /* synthetic */ void lambda$onFailure$0(final CertCallBack certCallBack) {
            if (certCallBack != null) {
                certCallBack.onCertGet("");
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, final Response response) {
            try {
                ResponseBody responseBodyBody = response.body();
                if (responseBodyBody != null) {
                    String strString = responseBodyBody.string();
                    JSONObject jSONObjectOptJSONObject = new JSONObject(strString).optJSONObject(FunctionRouter.KEY_DATA);
                    if (jSONObjectOptJSONObject != null) {
                        final String strOptString = jSONObjectOptJSONObject.optString("cert");
                        Handler handler = this.val$handler;
                        final CertCallBack certCallBack = this.val$certCallBack;
                        handler.post(new Runnable() { // from class: com.sq.oaid.sq_oaid.-$$Lambda$CertManager$1$EKMwQZT7m-cuhrADy8BI_bMQG5s
                            @Override // java.lang.Runnable
                            public final void run() {
                                CertManager.AnonymousClass1.lambda$onResponse$1(certCallBack, strOptString);
                            }
                        });
                        FileUtils.save(this.val$context, CertManager.getCertFileName(this.val$context), strOptString);
                        CertManager.checkCertAvailable(CertManager.getCertFile(this.val$context));
                    } else if (this.val$certCallBack != null) {
                        this.val$certCallBack.onCertFail("request cert fail", 203, new Exception("response data is null , " + strString));
                    }
                } else if (this.val$certCallBack != null) {
                    this.val$certCallBack.onCertFail("request cert fail", 203, new Exception("response is null"));
                }
            } catch (Exception e) {
                CertCallBack certCallBack2 = this.val$certCallBack;
                if (certCallBack2 != null) {
                    certCallBack2.onCertFail("update cert fail", 203, e);
                }
            }
        }

        static /* synthetic */ void lambda$onResponse$1(final CertCallBack certCallBack, final String cert) {
            if (certCallBack != null) {
                certCallBack.onCertGet(cert);
            }
        }
    }

    public static String loadCert(Context context) {
        return FileUtils.read(context, getCertFileName(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkCertAvailable(File certFile) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            FileInputStream fileInputStream = new FileInputStream(certFile);
            X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            fileInputStream.close();
            x509Certificate.checkValidity(new Date());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
