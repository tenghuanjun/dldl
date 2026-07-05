package cn.thinkingdata.android;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import cn.thinkingdata.android.TDConfig;
import cn.thinkingdata.android.c;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import com.sqwan.common.route.FunctionRouter;
import com.taptap.sdk.db.constant.Common;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class b {
    private static final Map<Context, b> g = new HashMap();
    private final C0004b a;
    private final a b;
    private final k c;
    private final c d;
    private final Context e;
    private final Map<String, Boolean> f = new ConcurrentHashMap();

    private class a {
        private final Handler a;

        /* JADX INFO: renamed from: cn.thinkingdata.android.b$a$a, reason: collision with other inner class name */
        private class HandlerC0003a extends Handler {
            private final List<String> a;

            HandlerC0003a(Looper looper) {
                super(looper);
                this.a = new ArrayList();
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int iA;
                int i = message.what;
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            b.this.a.c((String) message.obj);
                            return;
                        } else {
                            if (i == 3) {
                                this.a.remove((String) message.obj);
                                return;
                            }
                            return;
                        }
                    }
                    String str = (String) message.obj;
                    if (str == null) {
                        return;
                    }
                    b.this.a.a(str);
                    synchronized (a.this.a) {
                        a.this.a.removeMessages(2, str);
                        this.a.add(str);
                    }
                    synchronized (b.this.d) {
                        b.this.d.a(c.EnumC0005c.EVENTS, (String) message.obj);
                    }
                    return;
                }
                try {
                    cn.thinkingdata.android.a aVar = (cn.thinkingdata.android.a) message.obj;
                    if (aVar == null) {
                        return;
                    }
                    String str2 = aVar.i;
                    if (this.a.contains(str2)) {
                        return;
                    }
                    JSONObject jSONObjectA = aVar.a();
                    try {
                        jSONObjectA.put("#uuid", UUID.randomUUID().toString());
                    } catch (JSONException unused) {
                    }
                    synchronized (b.this.d) {
                        iA = b.this.d.a(jSONObjectA, c.EnumC0005c.EVENTS, str2);
                    }
                    if (iA < 0) {
                        TDLog.w("ThinkingAnalytics.DataHandle", "Saving data to database failed.");
                    } else {
                        TDLog.i("ThinkingAnalytics.DataHandle", "Data enqueued(" + r.a(str2, 4) + "):\n" + jSONObjectA.toString(4));
                    }
                    a.this.a(str2, iA);
                } catch (Exception e) {
                    TDLog.w("ThinkingAnalytics.DataHandle", "Exception occurred while saving data to database: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        a() {
            HandlerThread handlerThread = new HandlerThread("thinkingData.sdk.saveMessageWorker", 1);
            handlerThread.start();
            this.a = new HandlerC0003a(handlerThread.getLooper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, int i) {
            if (i >= b.this.e(str)) {
                b.this.a.c(str);
            } else {
                b.this.a.a(str, b.this.f(str));
            }
        }

        void a(cn.thinkingdata.android.a aVar) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 0;
            messageObtain.obj = aVar;
            Handler handler = this.a;
            if (handler != null) {
                handler.sendMessage(messageObtain);
            }
        }

        void a(String str) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 1;
            messageObtain.obj = str;
            Handler handler = this.a;
            if (handler != null) {
                handler.sendMessageAtFrontOfQueue(messageObtain);
            }
            Message messageObtain2 = Message.obtain();
            messageObtain2.what = 3;
            messageObtain2.obj = str;
            Handler handler2 = this.a;
            if (handler2 != null) {
                handler2.sendMessage(messageObtain2);
            }
        }

        void b(String str) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            messageObtain.obj = str;
            this.a.sendMessage(messageObtain);
        }
    }

    /* JADX INFO: renamed from: cn.thinkingdata.android.b$b, reason: collision with other inner class name */
    private class C0004b {
        private final Handler b;
        private final cn.thinkingdata.android.utils.i c;
        private final Object a = new Object();
        private final Map<String, Boolean> d = new HashMap();

        /* JADX INFO: renamed from: cn.thinkingdata.android.b$b$a */
        private class a extends Handler {
            a(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) throws Throwable {
                C0004b c0004b;
                int i = message.what;
                if (i == 0) {
                    String str = (String) message.obj;
                    TDConfig tDConfigD = b.this.d(str);
                    if (tDConfigD != null) {
                        synchronized (C0004b.this.a) {
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1;
                            messageObtain.obj = str;
                            C0004b.this.b.sendMessage(messageObtain);
                            removeMessages(0, str);
                        }
                        try {
                            C0004b.this.a(tDConfigD);
                        } catch (RuntimeException e) {
                            TDLog.w("ThinkingAnalytics.DataHandle", "Sending data to server failed due to unexpected exception: " + e.getMessage());
                            e.printStackTrace();
                        }
                        synchronized (C0004b.this.a) {
                            removeMessages(1, str);
                            C0004b.this.a(str, b.this.f(str));
                        }
                        return;
                    }
                } else {
                    if (i != 2) {
                        if (i == 3) {
                            if (((String) message.obj) == null) {
                                return;
                            }
                            synchronized (C0004b.this.a) {
                                removeMessages(0, message.obj);
                            }
                            return;
                        }
                        if (i == 4) {
                            try {
                                cn.thinkingdata.android.a aVar = (cn.thinkingdata.android.a) message.obj;
                                if (aVar == null) {
                                    return;
                                }
                                C0004b.this.a(b.this.d(aVar.i), aVar.a());
                                return;
                            } catch (Exception e2) {
                                TDLog.e("ThinkingAnalytics.DataHandle", "Exception occurred while sending message to Server: " + e2.getMessage());
                                return;
                            }
                        }
                        if (i != 5) {
                            if (i != 6) {
                                return;
                            }
                            m mVarA = m.a(b.this.e);
                            synchronized (b.this.d) {
                                b.this.d.a(System.currentTimeMillis() - mVarA.a(), c.EnumC0005c.EVENTS);
                            }
                            return;
                        }
                        try {
                            cn.thinkingdata.android.a aVar2 = (cn.thinkingdata.android.a) message.obj;
                            if (aVar2 == null) {
                                return;
                            }
                            TDConfig tDConfigD2 = b.this.d(aVar2.i);
                            if (tDConfigD2.isNormal()) {
                                c0004b = C0004b.this;
                            } else {
                                try {
                                    C0004b.this.b(tDConfigD2, aVar2.a());
                                    return;
                                } catch (Exception e3) {
                                    TDLog.e("ThinkingAnalytics.DataHandle", "Exception occurred while sending message to Server: " + e3.getMessage());
                                    if (tDConfigD2.shouldThrowException()) {
                                        throw new n(e3);
                                    }
                                    if (tDConfigD2.isDebugOnly()) {
                                        return;
                                    } else {
                                        c0004b = C0004b.this;
                                    }
                                }
                            }
                            b.this.c(aVar2);
                            return;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            return;
                        }
                    }
                    TDConfig tDConfigD3 = b.this.d((String) message.obj);
                    if (tDConfigD3 != null) {
                        try {
                            C0004b.this.a("", tDConfigD3);
                            return;
                        } catch (RuntimeException e5) {
                            TDLog.w("ThinkingAnalytics.DataHandle", "Sending old data failed due to unexpected exception: " + e5.getMessage());
                            e5.printStackTrace();
                            return;
                        }
                    }
                }
                TDLog.w("ThinkingAnalytics.DataHandle", "Could found config object for token. Canceling...");
            }
        }

        C0004b() {
            HandlerThread handlerThread = new HandlerThread("thinkingData.sdk.sendMessageWorker", 1);
            handlerThread.start();
            this.b = new a(handlerThread.getLooper());
            this.c = b.this.a();
        }

        private Map<String, String> a(JSONArray jSONArray) {
            HashMap map = new HashMap();
            map.put("TA-Integration-Type", k.i());
            map.put("TA-Integration-Version", k.j());
            map.put("TA-Integration-Count", String.valueOf(jSONArray.length()));
            map.put("TA-Integration-Extra", "Android");
            map.put("TA-Datas-Type", cn.thinkingdata.android.encrypt.c.a(jSONArray) ? "1" : "0");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(TDConfig tDConfig) throws Throwable {
            a(tDConfig.getName(), tDConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(TDConfig tDConfig, JSONObject jSONObject) throws JSONException {
            if (TextUtils.isEmpty(tDConfig.mToken)) {
                return;
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(FunctionRouter.KEY_DATA, jSONArray);
            jSONObject2.put("#app_id", tDConfig.mToken);
            jSONObject2.put("#flush_time", System.currentTimeMillis());
            TDLog.i("ThinkingAnalytics.DataHandle", "ret code: " + new JSONObject(this.c.a(tDConfig.getServerUrl(), jSONObject2.toString(), false, tDConfig.getSSLSocketFactory(), d("1"))).getString("code") + ", upload message:\n" + jSONObject2.toString(4));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:102:0x022d A[PHI: r0
  0x022d: PHI (r0v15 java.lang.String) = (r0v14 java.lang.String), (r0v18 java.lang.String) binds: [B:81:0x01ae, B:101:0x022b] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:107:0x023b  */
        /* JADX WARN: Removed duplicated region for block: B:109:0x0242  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(java.lang.String r19, cn.thinkingdata.android.TDConfig r20) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 627
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.b.C0004b.a(java.lang.String, cn.thinkingdata.android.TDConfig):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(TDConfig tDConfig, JSONObject jSONObject) throws JSONException {
            StringBuilder sb = new StringBuilder();
            sb.append("appid=");
            sb.append(tDConfig.mToken);
            if (jSONObject.optJSONObject(Common.Predefined.PROPERTIES) != null) {
                TDPresetProperties presetProperties = ThinkingAnalyticsSDK.sharedInstance(tDConfig).getPresetProperties();
                String strA = (presetProperties == null || TDPresetProperties.disableList.contains("#device_id")) ? "" : presetProperties.deviceId;
                if (TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#device_id")) {
                    strA = k.e(tDConfig.mContext).a(tDConfig.mContext);
                }
                if (!TextUtils.isEmpty(strA)) {
                    sb.append("&deviceId=");
                    sb.append(strA);
                }
            }
            sb.append("&source=client&data=");
            sb.append(URLEncoder.encode(jSONObject.toString()));
            if (tDConfig.isDebugOnly()) {
                sb.append("&dryRun=1");
            }
            String strA2 = r.a(tDConfig.getName(), 4);
            TDLog.d("ThinkingAnalytics.DataHandle", "uploading message(" + strA2 + "):\n" + jSONObject.toString(4));
            JSONObject jSONObject2 = new JSONObject(this.c.a(tDConfig.getDebugUrl(), sb.toString(), true, tDConfig.getSSLSocketFactory(), d("1")));
            int i = jSONObject2.getInt("errorLevel");
            if (i == -1) {
                if (tDConfig.isDebugOnly()) {
                    TDLog.w("ThinkingAnalytics.DataHandle", "The data will be discarded due to this device is not allowed to debug for: " + strA2);
                    return;
                }
                tDConfig.setMode(TDConfig.ModeEnum.NORMAL);
                throw new n("Fallback to normal mode due to the device is not allowed to debug for: " + strA2);
            }
            Boolean bool = this.d.get(tDConfig.getName());
            if (bool == null || !bool.booleanValue()) {
                Toast.makeText(b.this.e, "Debug Mode enabled for: " + strA2, 1).show();
                this.d.put(tDConfig.getName(), true);
                tDConfig.setAllowDebug();
            }
            if (i == 0) {
                TDLog.d("ThinkingAnalytics.DataHandle", "Upload debug data successfully for " + strA2);
                return;
            }
            if (jSONObject2.has("errorProperties")) {
                TDLog.d("ThinkingAnalytics.DataHandle", " Error Properties: \n" + jSONObject2.getJSONArray("errorProperties").toString(4));
            }
            if (jSONObject2.has("errorReasons")) {
                TDLog.d("ThinkingAnalytics.DataHandle", "Error Reasons: \n" + jSONObject2.getJSONArray("errorReasons").toString(4));
            }
            if (tDConfig.shouldThrowException()) {
                if (1 == i) {
                    throw new n("Invalid properties. Please refer to the logcat log for detail info.");
                }
                if (2 == i) {
                    throw new n("Invalid data format. Please refer to the logcat log for detail info.");
                }
                throw new n("Unknown error level: " + i);
            }
        }

        private Map<String, String> d(String str) {
            HashMap map = new HashMap();
            map.put("TA-Integration-Type", k.i());
            map.put("TA-Integration-Version", k.j());
            map.put("TA-Integration-Count", str);
            map.put("TA-Integration-Extra", "Android");
            return map;
        }

        void a() {
            Message messageObtain = Message.obtain();
            messageObtain.what = 6;
            this.b.sendMessage(messageObtain);
        }

        void a(cn.thinkingdata.android.a aVar) {
            if (aVar == null) {
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 5;
            messageObtain.obj = aVar;
            this.b.sendMessage(messageObtain);
        }

        void a(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.obj = str;
            this.b.sendMessageAtFrontOfQueue(messageObtain);
        }

        void a(String str, long j) {
            synchronized (this.a) {
                if (this.b != null && !this.b.hasMessages(0, str) && !this.b.hasMessages(1, str)) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 0;
                    messageObtain.obj = str;
                    try {
                        this.b.sendMessageDelayed(messageObtain, j);
                    } catch (IllegalStateException e) {
                        TDLog.w("ThinkingAnalytics.DataHandle", "The app might be quiting: " + e.getMessage());
                    }
                }
            }
        }

        void b(cn.thinkingdata.android.a aVar) {
            if (aVar == null) {
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 4;
            messageObtain.obj = aVar;
            this.b.sendMessage(messageObtain);
        }

        void b(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            messageObtain.obj = str;
            this.b.sendMessage(messageObtain);
        }

        void c(String str) {
            synchronized (this.a) {
                if (this.b != null && !this.b.hasMessages(1, str)) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 0;
                    messageObtain.obj = str;
                    this.b.sendMessage(messageObtain);
                }
            }
        }
    }

    b(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.e = applicationContext;
        this.c = k.e(applicationContext);
        this.d = a(this.e);
        this.a = new C0004b();
        this.b = new a();
        this.a.a();
    }

    static b b(Context context) {
        b bVar;
        synchronized (g) {
            Context applicationContext = context.getApplicationContext();
            if (g.containsKey(applicationContext)) {
                bVar = g.get(applicationContext);
            } else {
                bVar = new b(applicationContext);
                g.put(applicationContext, bVar);
            }
        }
        return bVar;
    }

    protected c a(Context context) {
        return c.a(context);
    }

    protected cn.thinkingdata.android.utils.i a() {
        return new cn.thinkingdata.android.utils.d();
    }

    void a(cn.thinkingdata.android.a aVar) {
        this.a.b(aVar);
    }

    void a(String str) {
        this.b.a(str);
    }

    public void a(String str, boolean z) {
        if (z) {
            this.f.put(str, true);
        } else {
            this.f.remove(str);
        }
    }

    void b(cn.thinkingdata.android.a aVar) {
        this.a.a(aVar);
    }

    void b(String str) {
        this.b.b(str);
    }

    void c(cn.thinkingdata.android.a aVar) {
        this.b.a(aVar);
    }

    void c(String str) {
        this.a.b(str);
    }

    protected TDConfig d(String str) {
        return TDConfig.getInstance(this.e, str);
    }

    protected int e(String str) {
        TDConfig tDConfigD = d(str);
        if (tDConfigD == null) {
            return 20;
        }
        return tDConfigD.getFlushBulkSize();
    }

    protected int f(String str) {
        TDConfig tDConfigD = d(str);
        if (tDConfigD == null) {
            return 15000;
        }
        return tDConfigD.getFlushInterval();
    }
}
