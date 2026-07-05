package com.unionpay.tsmservice.mi.mini;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.unionpay.tsmservice.mi.mini.ITsmCallback;
import com.unionpay.tsmservice.mi.mini.ITsmServiceMini;
import com.unionpay.tsmservice.mi.mini.data.Constant;
import com.unionpay.tsmservice.mi.mini.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.mini.request.wrapper.BaseRequestParamsWrapper;
import com.unionpay.tsmservice.mi.mini.request.wrapper.QueryVendorPayStatusRequestParamsWrapper;
import com.unionpay.tsmservice.mi.mini.result.wrapper.BaseResultCallbackWrapper;
import com.unionpay.tsmservice.mi.mini.result.wrapper.QueryVendorPayStatusResultCallbackWrapper;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class UPTsmAddonMini {
    private static UPTsmAddonMini a;
    private static CopyOnWriteArrayList b;
    private Context f;
    private final Handler.Callback c = new Handler.Callback() { // from class: com.unionpay.tsmservice.mi.mini.UPTsmAddonMini.1
        @Override // android.os.Handler.Callback
        public final synchronized boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                UPTsmAddonMini.this.a();
                return true;
            }
            if (i != 1) {
                return false;
            }
            UPTsmAddonMini.this.b();
            return true;
        }
    };
    private ITsmServiceMini d = null;
    private final Handler e = new Handler(Looper.getMainLooper(), this.c);
    private ServiceConnection g = null;
    private boolean h = false;

    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    final class a {
        private final String c;
        private final BaseRequestParamsWrapper d;
        private final BaseResultCallbackWrapper e;
        private final int b = 4001;
        private final ITsmProgressCallback f = null;

        public a(String str, BaseRequestParamsWrapper baseRequestParamsWrapper, BaseResultCallbackWrapper baseResultCallbackWrapper) {
            this.c = str;
            this.d = baseRequestParamsWrapper;
            this.e = baseResultCallbackWrapper;
        }

        public final int a() {
            if (!UPTsmAddonMini.this.a(this.c)) {
                return -8;
            }
            if (this.d.isParamsValid() && this.e != null) {
                try {
                    JSONObject reserveJSONObject = this.d.getReserveJSONObject();
                    JSONObject requestJSONObject = this.d.getRequestJSONObject();
                    requestJSONObject.put("interfaceId", this.b);
                    return UPTsmAddonMini.this.commonInterface(requestJSONObject, reserveJSONObject, this.e, this.f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return -3;
        }
    }

    final class b extends ITsmCallback.Stub {
        private final ITsmCallback b;
        private final int c;

        private b(ITsmCallback iTsmCallback, int i) {
            this.b = iTsmCallback;
            this.c = i;
        }

        /* synthetic */ b(UPTsmAddonMini uPTsmAddonMini, ITsmCallback iTsmCallback, int i, byte b) {
            this(iTsmCallback, i);
        }

        @Override // com.unionpay.tsmservice.mi.mini.ITsmCallback
        public final void onError(String str, String str2) {
            ITsmCallback iTsmCallback = this.b;
            if (iTsmCallback != null) {
                iTsmCallback.onError(str, str2);
            }
        }

        @Override // com.unionpay.tsmservice.mi.mini.ITsmCallback
        public final void onResult(Bundle bundle) {
            if (this.b != null) {
                bundle.putInt("interfaceId", this.c);
                this.b.onResult(bundle);
            }
        }
    }

    private UPTsmAddonMini(Context context) {
        this.f = null;
        this.f = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        if (b != null && b.size() > 0) {
            for (UPTsmConnectionListener uPTsmConnectionListener : b) {
                if (uPTsmConnectionListener != null) {
                    uPTsmConnectionListener.onTsmConnected();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        String strB = b("com.unionpay.tsmservice.mi");
        return strB != null && strB.compareTo(str) >= 0;
    }

    private String b(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.f.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b() {
        if (b != null && b.size() > 0) {
            for (UPTsmConnectionListener uPTsmConnectionListener : b) {
                if (uPTsmConnectionListener != null) {
                    uPTsmConnectionListener.onTsmDisconnected();
                }
            }
        }
    }

    public static synchronized UPTsmAddonMini getInstance(Context context) {
        if (context == null) {
            return null;
        }
        if (a == null) {
            a = new UPTsmAddonMini(context.getApplicationContext());
        }
        if (b == null) {
            b = new CopyOnWriteArrayList();
        }
        return a;
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.add(uPTsmConnectionListener);
        }
    }

    public boolean bind() {
        try {
            if (this.g == null) {
                this.g = new ServiceConnection() { // from class: com.unionpay.tsmservice.mi.mini.UPTsmAddonMini.2
                    @Override // android.content.ServiceConnection
                    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        UPTsmAddonMini.this.h = true;
                        UPTsmAddonMini.this.d = ITsmServiceMini.Stub.asInterface(iBinder);
                        UPTsmAddonMini.this.e.sendEmptyMessage(0);
                    }

                    @Override // android.content.ServiceConnection
                    public final synchronized void onServiceDisconnected(ComponentName componentName) {
                        UPTsmAddonMini.this.h = false;
                        UPTsmAddonMini.this.d = null;
                        UPTsmAddonMini.this.e.sendEmptyMessage(1);
                    }
                };
            }
            if (this.h) {
                return true;
            }
            Intent intent = new Intent("com.unionpay.tsmservice.mi.UPServiceTsmMini");
            intent.setPackage("com.unionpay.tsmservice.mi");
            return this.f.bindService(intent, this.g, 1);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0059 A[Catch: all -> 0x006e, TRY_LEAVE, TryCatch #2 {, blocks: (B:7:0x0007, B:11:0x0012, B:15:0x0018, B:20:0x0023, B:22:0x002c, B:28:0x003b, B:33:0x0049, B:36:0x0056, B:31:0x0044, B:37:0x0059), top: B:49:0x0007, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0049 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized int commonInterface(org.json.JSONObject r5, org.json.JSONObject r6, com.unionpay.tsmservice.mi.mini.ITsmCallback r7, com.unionpay.tsmservice.mi.mini.ITsmProgressCallback r8) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = -3
            if (r5 == 0) goto L71
            if (r7 != 0) goto L7
            goto L71
        L7:
            java.lang.String r1 = "interfaceId"
            r2 = -1
            int r1 = r5.optInt(r1, r2)     // Catch: java.lang.Throwable -> L6e
            if (r1 != r2) goto L12
            monitor-exit(r4)
            return r0
        L12:
            com.unionpay.tsmservice.mi.mini.ITsmServiceMini r0 = r4.d     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L18
            monitor-exit(r4)
            return r2
        L18:
            java.lang.String r0 = "01.00.35"
            boolean r0 = r4.a(r0)     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L23
            r5 = -8
            monitor-exit(r4)
            return r5
        L23:
            java.lang.String r0 = "com.unionpay.tsmservice.mi"
            java.lang.String r0 = r4.b(r0)     // Catch: java.lang.Throwable -> L6e
            r2 = 0
            if (r0 == 0) goto L36
            java.lang.String r3 = "01.00.02"
            int r0 = r0.compareTo(r3)     // Catch: java.lang.Throwable -> L6e
            if (r0 < 0) goto L36
            r0 = 1
            goto L37
        L36:
            r0 = 0
        L37:
            if (r0 == 0) goto L59
            if (r6 == 0) goto L47
            java.lang.String r0 = "jarVersionCode"
            r3 = 36
            r6.put(r0, r3)     // Catch: org.json.JSONException -> L43 java.lang.Throwable -> L6e
            goto L47
        L43:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L6e
        L47:
            if (r6 == 0) goto L59
            java.lang.String r0 = "packageName"
            android.content.Context r3 = r4.f     // Catch: org.json.JSONException -> L55 java.lang.Throwable -> L6e
            java.lang.String r3 = r3.getPackageName()     // Catch: org.json.JSONException -> L55 java.lang.Throwable -> L6e
            r6.put(r0, r3)     // Catch: org.json.JSONException -> L55 java.lang.Throwable -> L6e
            goto L59
        L55:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L6e
        L59:
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L6e
            com.unionpay.tsmservice.mi.mini.ITsmServiceMini r0 = r4.d     // Catch: java.lang.Throwable -> L6e
            com.unionpay.tsmservice.mi.mini.UPTsmAddonMini$b r3 = new com.unionpay.tsmservice.mi.mini.UPTsmAddonMini$b     // Catch: java.lang.Throwable -> L6e
            r3.<init>(r4, r7, r1, r2)     // Catch: java.lang.Throwable -> L6e
            int r5 = r0.commonInterface(r5, r6, r3, r8)     // Catch: java.lang.Throwable -> L6e
            monitor-exit(r4)
            return r5
        L6e:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L71:
            monitor-exit(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.mini.UPTsmAddonMini.commonInterface(org.json.JSONObject, org.json.JSONObject, com.unionpay.tsmservice.mi.mini.ITsmCallback, com.unionpay.tsmservice.mi.mini.ITsmProgressCallback):int");
    }

    public Context getContext() {
        return this.f;
    }

    public synchronized int getListenerCount() {
        if (b == null) {
            return 0;
        }
        return b.size();
    }

    public boolean isConnected() {
        return this.h;
    }

    public synchronized int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
        return new a(Constant.APK_VERSION_010035, new QueryVendorPayStatusRequestParamsWrapper(queryVendorPayStatusRequestParams), new QueryVendorPayStatusResultCallbackWrapper(4001, iTsmCallback)).a();
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.remove(uPTsmConnectionListener);
        }
    }

    public void unbind() {
        ServiceConnection serviceConnection = this.g;
        if (serviceConnection == null || !this.h) {
            return;
        }
        this.f.unbindService(serviceConnection);
        this.h = false;
    }
}
