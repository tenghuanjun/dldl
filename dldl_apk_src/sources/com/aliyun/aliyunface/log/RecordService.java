package com.aliyun.aliyunface.log;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.api.ZIMCrashCallback;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.network.NetworkEnv;
import com.aliyun.aliyunface.network.NetworkPresenter;
import com.aliyun.aliyunface.network.ZimUploadLogCallback;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.aliyun.aliyunface.utils.MobileUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class RecordService {
    private static final int MAX_RECORD_COUNT = 10;
    private static RecordService s_instance = new RecordService();
    private Context ctx;
    private String netType;
    private String osVersion;
    private String phoneLanguage;
    private String phoneType;
    private List<RecordBase> records = new ArrayList();
    private String screenMetrix;
    private String sessionId;
    private String zimId;

    public static RecordService getInstance() {
        return s_instance;
    }

    public void init(Context context, String str) {
        this.ctx = context;
        this.zimId = str;
        this.sessionId = UUID.randomUUID().toString().replace("-", "");
        this.phoneType = Build.FINGERPRINT;
        this.osVersion = String.valueOf(Build.VERSION.SDK_INT);
        this.netType = MobileUtil.NETWORK_4G;
        this.phoneLanguage = MobileUtil.getMobileLan();
        this.screenMetrix = MobileUtil.getDisplayMetrix(context);
    }

    public void recordEvent(RecordLevel recordLevel, String str, String... strArr) {
        synchronized (RecordService.class) {
            recordEventEx(recordLevel, str, strArr);
        }
    }

    private void recordEventEx(RecordLevel recordLevel, String str, String... strArr) {
        EventRecord eventRecord = new EventRecord();
        eventRecord.setLogLevel(String.valueOf(recordLevel));
        eventRecord.setActionName(str);
        eventRecord.setSessionId(this.sessionId);
        eventRecord.setPhoneType(this.phoneType);
        eventRecord.setOsVersion(this.osVersion);
        eventRecord.setNetType(this.netType);
        eventRecord.setLanguage(this.phoneLanguage);
        eventRecord.setScreenMetrix(this.screenMetrix);
        eventRecord.setExtParam1(this.zimId);
        eventRecord.setExtParma2("0");
        eventRecord.setExtParam3("0");
        HashMap map = new HashMap();
        if (strArr != null && strArr.length % 2 == 0) {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < strArr.length - 1; i += 2) {
                jSONObject.put(strArr[i], (Object) strArr[i + 1]);
            }
            map.put("extParam", MiscUtil.base64Encode(jSONObject.toJSONString()));
        }
        map.put("logType", "BI_C_V1");
        map.put("publicParam", "JTdCJTdE");
        map.put("zimId", this.zimId);
        map.put("uiVersion", "992");
        map.put("uploadLog", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE);
        map.put("productName", "faceverfy");
        map.put("logPlanId", "ALIYUN_FACE_LOGPLAN_V1");
        map.put("logModelVersion", "V1.0");
        map.put("zid", "NONE");
        map.put("bistoken", "1234");
        map.put("bioType", "facedetect");
        map.put("keepUploadPage", "1");
        map.put("sceneId", "AliyunID+Aliyun+certify+face");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            sb.append(str2);
            sb.append("=");
            sb.append(str3);
            sb.append("^");
        }
        String string = sb.toString();
        if (string.endsWith("^")) {
            string = string.substring(0, string.length() - 1);
        }
        eventRecord.setExtParam4(string);
        this.records.add(eventRecord);
        tryUpload(false, null);
    }

    private void tryUpload(boolean z, final ZIMCrashCallback zIMCrashCallback) {
        NetworkEnv networkEnv;
        if (this.records.size() >= 10 || z) {
            ArrayList arrayList = new ArrayList();
            Iterator<RecordBase> it = this.records.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString());
            }
            this.records.clear();
            arrayList.addAll(readCacheLogs());
            if (arrayList.size() > 0 && (networkEnv = ToygerPresenter.getInstance().getNetworkEnv()) != null) {
                NetworkPresenter.zimUploadLog(networkEnv, arrayList, new ZimUploadLogCallback() { // from class: com.aliyun.aliyunface.log.RecordService.1
                    @Override // com.aliyun.aliyunface.network.ZimUploadLogCallback
                    public void onSuccess() {
                        ZIMCrashCallback zIMCrashCallback2 = zIMCrashCallback;
                        if (zIMCrashCallback2 != null) {
                            zIMCrashCallback2.onSuccess();
                        }
                    }

                    @Override // com.aliyun.aliyunface.network.ZimUploadLogCallback
                    public void onFail(List<String> list) {
                        RecordService.this.cacheLogs(list);
                        ZIMCrashCallback zIMCrashCallback2 = zIMCrashCallback;
                        if (zIMCrashCallback2 != null) {
                            zIMCrashCallback2.onError();
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheLogs(List<String> list) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        synchronized (RecordService.class) {
            if (this.ctx == null) {
                return;
            }
            String str = this.ctx.getFilesDir().getAbsolutePath() + RecordConst.LOG_FILE_NAME;
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.exists() && file.length() > 1048576) {
                    file.delete();
                }
            }
            BufferedWriter bufferedWriter2 = null;
            try {
                fileWriter = new FileWriter(str, true);
                try {
                    try {
                        bufferedWriter = new BufferedWriter(fileWriter);
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
                fileWriter = null;
            } catch (Throwable th2) {
                th = th2;
                fileWriter = null;
            }
            try {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    bufferedWriter.write(it.next());
                }
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                }
            } catch (Exception e4) {
                e = e4;
                bufferedWriter2 = bufferedWriter;
                e.printStackTrace();
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (Exception e5) {
                        e = e5;
                        e.printStackTrace();
                    }
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter2 = bufferedWriter;
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                        throw th;
                    }
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0075 A[Catch: Exception -> 0x0078, TRY_LEAVE, TryCatch #3 {Exception -> 0x0078, blocks: (B:37:0x0070, B:39:0x0075), top: B:46:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<java.lang.String> readCacheLogs() throws java.lang.Throwable {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.content.Context r1 = r6.ctx
            if (r1 != 0) goto La
            return r0
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            android.content.Context r2 = r6.ctx
            java.io.File r2 = r2.getFilesDir()
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r2 = "/aliyun_log"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r3 = r2.exists()
            if (r3 != 0) goto L31
            return r0
        L31:
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
        L3c:
            java.lang.String r3 = r1.readLine()     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L6c
            if (r3 == 0) goto L46
            r0.add(r3)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L6c
            goto L3c
        L46:
            r4.close()     // Catch: java.lang.Exception -> L68
            goto L65
        L4a:
            r3 = move-exception
            goto L5b
        L4c:
            r0 = move-exception
            r1 = r3
            goto L6d
        L4f:
            r1 = move-exception
            r5 = r3
            r3 = r1
            r1 = r5
            goto L5b
        L54:
            r0 = move-exception
            r1 = r3
            goto L6e
        L57:
            r1 = move-exception
            r4 = r3
            r3 = r1
            r1 = r4
        L5b:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L6c
            if (r4 == 0) goto L63
            r4.close()     // Catch: java.lang.Exception -> L68
        L63:
            if (r1 == 0) goto L68
        L65:
            r1.close()     // Catch: java.lang.Exception -> L68
        L68:
            r2.delete()
            return r0
        L6c:
            r0 = move-exception
        L6d:
            r3 = r4
        L6e:
            if (r3 == 0) goto L73
            r3.close()     // Catch: java.lang.Exception -> L78
        L73:
            if (r1 == 0) goto L78
            r1.close()     // Catch: java.lang.Exception -> L78
        L78:
            r2.delete()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aliyun.aliyunface.log.RecordService.readCacheLogs():java.util.List");
    }

    public void flush() {
        synchronized (RecordService.class) {
            tryUpload(true, null);
        }
        this.ctx = null;
    }

    public void reportCrash(ZIMCrashCallback zIMCrashCallback) {
        synchronized (RecordService.class) {
            tryUpload(true, zIMCrashCallback);
        }
    }
}
