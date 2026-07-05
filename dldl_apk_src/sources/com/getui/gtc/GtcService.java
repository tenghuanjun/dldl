package com.getui.gtc;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Base64;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.api.GtcManager;
import com.getui.gtc.api.SdkInfo;
import com.getui.gtc.b.c;
import com.getui.gtc.i.c.a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
@Deprecated
public class GtcService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        a.a("GtcService onBind");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        a.a("GtcService onCreated");
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        a.a("GtcService onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            Context applicationContext = getApplicationContext();
            if (intent != null && intent.hasExtra("10010")) {
                String str = new String(Base64.decode(intent.getByteArrayExtra("10010"), 0));
                int iB = c.b(str);
                com.getui.gtc.api.GtcManager.getInstance().init(applicationContext, new GtcIdCallback.Stub() { // from class: com.getui.gtc.b.c.1
                    final /* synthetic */ Context a;
                    final /* synthetic */ String b;
                    final /* synthetic */ int c;
                    final /* synthetic */ String d;

                    public AnonymousClass1(Context applicationContext2, String str2, int iB2, String str3) {
                        context = applicationContext2;
                        str = str2;
                        i = iB2;
                        str = str3;
                    }

                    @Override // com.getui.gtc.api.GtcIdCallback
                    public final void onFailure(String str2) {
                    }

                    @Override // com.getui.gtc.api.GtcIdCallback
                    public final void onSuccess(String str2) {
                        Context context = context;
                        try {
                            if (TextUtils.isEmpty(str2)) {
                                com.getui.gtc.i.c.a.a("send cid broadcast fail,cid is null");
                            } else {
                                Intent intent2 = new Intent();
                                intent2.setAction(context.getPackageName());
                                intent2.putExtra("gicid", str2);
                                context.sendBroadcast(intent2);
                            }
                        } catch (Exception e) {
                            com.getui.gtc.i.c.a.c(e);
                        }
                        Context context2 = context;
                        String str3 = str;
                        try {
                            if (TextUtils.isEmpty(str2)) {
                                com.getui.gtc.i.c.a.a("send gicid broadcast fail,cid is null");
                            } else {
                                Intent intent3 = new Intent();
                                intent3.setPackage(context2.getPackageName());
                                intent3.setAction(str3);
                                intent3.putExtra("gicid", str2);
                                context2.sendBroadcast(intent3);
                            }
                        } catch (Exception e2) {
                            com.getui.gtc.i.c.a.c(e2);
                        }
                        GtcManager.getInstance().loadSdk(new SdkInfo.Builder().moduleName("SDKID:" + i).appid(str).version(str).cid(str2).build());
                    }
                });
                return 2;
            }
            return 2;
        } catch (Throwable th) {
            a.a(th);
            return 2;
        }
    }
}
