package com.sq.diagnostic.assistant.log.strategy;

import android.text.TextUtils;
import com.sq.diagnostic.assistant.http.HttpData;
import com.sq.diagnostic.assistant.http.HttpManager;
import com.sq.diagnostic.assistant.http.OnHttpListener;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.ILogCallBack;
import com.sq.diagnostic.assistant.log.impl.ErrorStats;
import com.sq.diagnostic.assistant.log.utils.ExtraUtil;
import com.sq.diagnostic.assistant.zip.ICompressionCallBack;
import com.sq.diagnostic.assistant.zip.ZipLogManager;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PackageUploadLogStrategy extends HandlerStrategy {
    private String cachePath;
    private List<String> extraLogPaths;
    private String logPath;

    public PackageUploadLogStrategy(String str, String str2, String str3, String str4, String str5, List<String> list) {
        super(str, str2, str3);
        this.logPath = str4;
        this.cachePath = str5;
        this.extraLogPaths = list;
    }

    @Override // com.sq.diagnostic.assistant.log.strategy.HandlerStrategy
    public boolean process(final UploadLogRequest uploadLogRequest, final ILogCallBack<?> iLogCallBack) {
        LinkedList<String> linkedListGenAllLogPaths = genAllLogPaths(this.logDateStart, this.logDateEnd);
        if (linkedListGenAllLogPaths != null && !linkedListGenAllLogPaths.isEmpty()) {
            ZipLogManager.getInstance().zip(linkedListGenAllLogPaths, this.zipLogDirPath, ExtraUtil.genZipFileName(this.logDateStart, this.logDateEnd), new ICompressionCallBack() { // from class: com.sq.diagnostic.assistant.log.strategy.PackageUploadLogStrategy.1
                @Override // com.sq.diagnostic.assistant.zip.ICompressionCallBack
                public void onSuccess(String str) {
                    HttpManager.uploadLog(uploadLogRequest, str, new OnHttpListener<JSONObject>() { // from class: com.sq.diagnostic.assistant.log.strategy.PackageUploadLogStrategy.1.1
                        @Override // com.sq.diagnostic.assistant.http.OnHttpListener
                        public void onSuccess(HttpData<JSONObject> httpData) {
                            if (iLogCallBack == null) {
                                return;
                            }
                            iLogCallBack.onSuccess(null);
                        }

                        @Override // com.sq.diagnostic.assistant.http.OnHttpListener
                        public void onFailed(int i, String str2) {
                            if (iLogCallBack == null) {
                                return;
                            }
                            iLogCallBack.onFailed(i, str2);
                        }
                    });
                }

                @Override // com.sq.diagnostic.assistant.zip.ICompressionCallBack
                public void onFailed(int i, String str) {
                    ILogCallBack iLogCallBack2 = iLogCallBack;
                    if (iLogCallBack2 == null) {
                        return;
                    }
                    iLogCallBack2.onFailed(i, str);
                }
            });
            return true;
        }
        if (iLogCallBack != null) {
            iLogCallBack.onFailed(10004, ErrorStats.ERROR_MSG_LOG_EMPTY);
        }
        return true;
    }

    private LinkedList<String> genAllLogPaths(String str, String str2) {
        LinkedList<String> linkedList = new LinkedList<>();
        addLogPaths(linkedList, this.logPath, str, str2);
        addLogPaths(linkedList, this.cachePath, str, str2);
        addExtraLogPaths(linkedList, str, str2);
        return linkedList;
    }

    private void addLogPaths(LinkedList<String> linkedList, String str, String str2, String str3) {
        LinkedList<String> linkedListGenLogPaths = genLogPaths(str, str2, str3);
        if (linkedListGenLogPaths == null || linkedListGenLogPaths.isEmpty()) {
            return;
        }
        linkedList.addAll(linkedListGenLogPaths);
    }

    private void addExtraLogPaths(LinkedList<String> linkedList, String str, String str2) {
        List<String> list = this.extraLogPaths;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.extraLogPaths.size(); i++) {
            File file = new File(this.extraLogPaths.get(i));
            if (file.exists()) {
                if (file.isDirectory()) {
                    addLogPaths(linkedList, file.getPath(), str, str2);
                } else {
                    linkedList.add(file.getPath());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.LinkedList<java.lang.String> genLogPaths(java.lang.String r15, java.lang.String r16, java.lang.String r17) {
        /*
            r14 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r15)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.io.File r0 = new java.io.File
            r2 = r15
            r0.<init>(r15)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L78
            boolean r2 = r0.isDirectory()
            if (r2 == 0) goto L78
            java.io.File[] r2 = r0.listFiles()
            if (r2 == 0) goto L78
            java.io.File[] r2 = r0.listFiles()
            int r2 = r2.length
            if (r2 > 0) goto L28
            goto L78
        L28:
            java.util.LinkedList r1 = new java.util.LinkedList
            r1.<init>()
            java.io.File[] r0 = r0.listFiles()
            int r2 = r0.length
            r3 = 0
        L33:
            if (r3 >= r2) goto L76
            r4 = r0[r3]
            if (r4 == 0) goto L72
            boolean r5 = r4.isDirectory()
            if (r5 == 0) goto L40
            goto L72
        L40:
            long r5 = r4.lastModified()
            long r7 = com.sq.diagnostic.assistant.log.utils.ExtraUtil.getMillis(r16)
            long r9 = com.sq.diagnostic.assistant.log.utils.ExtraUtil.getMillis(r17)
            r11 = 86399999(0x5265bff, double:4.26872713E-316)
            long r9 = r9 + r11
            r11 = 0
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 <= 0) goto L72
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 > 0) goto L5b
            goto L72
        L5b:
            int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r11 < 0) goto L72
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 > 0) goto L72
            r5 = r14
            boolean r6 = r14.shouldIgnoredFile(r4)
            if (r6 != 0) goto L73
            java.lang.String r4 = r4.getPath()
            r1.add(r4)
            goto L73
        L72:
            r5 = r14
        L73:
            int r3 = r3 + 1
            goto L33
        L76:
            r5 = r14
            return r1
        L78:
            r5 = r14
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.diagnostic.assistant.log.strategy.PackageUploadLogStrategy.genLogPaths(java.lang.String, java.lang.String, java.lang.String):java.util.LinkedList");
    }

    private boolean shouldIgnoredFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        String name = file.getName();
        if (TextUtils.isEmpty(name)) {
            return true;
        }
        return name.contains(".mmap");
    }
}
