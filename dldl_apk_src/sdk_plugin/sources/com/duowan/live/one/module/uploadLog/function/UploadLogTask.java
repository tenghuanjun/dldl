package com.duowan.live.one.module.uploadLog.function;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.duowan.live.one.module.uploadLog.Response.LogUploadRangeRsp;
import com.duowan.live.one.module.uploadLog.Response.LogUploadRsp;
import com.duowan.live.one.util.AsyncHttpClient;
import com.duowan.live.one.util.LogUtils;
import com.duowan.live.one.util.Md5Util;
import com.duowan.live.one.util.ThreadPoolFactory;
import com.google.gson.Gson;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UploadLogTask {
    private static final int DEFAULT_CHUNK_SIZE = 131072;
    private static final int MAX_TRY_TIME = 3;
    private static final String NEED = "1";
    private static final String TAG = "UploadLogTask";
    private String mFbId;
    private boolean mIsReload;
    private long mLogBeginTime;
    private long mLogEndTime;
    private long mMaxFileSize;

    public UploadLogTask(String str, long j, long j2, long j3, boolean z) {
        this.mFbId = str;
        this.mLogBeginTime = j;
        this.mLogEndTime = j2;
        this.mMaxFileSize = j3;
        this.mIsReload = z;
    }

    public void execute() {
        getRemoteFileRange(this.mFbId, this.mLogBeginTime, this.mLogEndTime, this.mMaxFileSize);
    }

    public void getRemoteFileRange(final String str, final long j, final long j2, final long j3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.putBody(FeedBackConstants.KEY_LOG_FBID, str);
        requestParams.putBody(FeedBackConstants.KEY_LOG_IS_NEED_META_DATA, "1");
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        requestParams.putBody("token", defaultToken.getToken());
        requestParams.putBody(FeedBackConstants.KEY_FB_TICKETTYPE, String.valueOf(defaultToken.getTokenType()));
        HttpClient.post(FeedBackConstants.LOG_GET_FILE_RANGE_URL, requestParams, new HttpClient.HttpHandler() { // from class: com.duowan.live.one.module.uploadLog.function.UploadLogTask.1
            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                try {
                    LogUploadRangeRsp logUploadRangeRsp = (LogUploadRangeRsp) new Gson().fromJson(new String(bArr), LogUploadRangeRsp.class);
                    if (logUploadRangeRsp == null || "1".equals(Integer.valueOf(logUploadRangeRsp.getStatus()))) {
                        return;
                    }
                    String md5 = logUploadRangeRsp.getMetadata() != null ? logUploadRangeRsp.getMetadata().getMd5() : null;
                    List<String> range = logUploadRangeRsp.getRange();
                    if (range == null) {
                        range = new ArrayList<>();
                    }
                    UploadLogTask.this.upload(str, j, j2, j3, md5, range);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                L.error(UploadLogTask.TAG, "getRemoteFileRange onFailure...");
            }
        });
    }

    public void upload(final String str, final long j, final long j2, final long j3, final String str2, final List<String> list) {
        L.debug(TAG, " upload %s", str);
        ThreadPoolFactory.run(new Runnable() { // from class: com.duowan.live.one.module.uploadLog.function.UploadLogTask.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                boolean z = list.size() > 0;
                File logByTime = LogUtils.getLogByTime(str, j, j2, z);
                if (logByTime == null) {
                    L.error(UploadLogTask.TAG, "file is null, so drop this upload");
                    return;
                }
                if (logByTime.length() > j3) {
                    L.error(UploadLogTask.TAG, "file's size is over mMaxFileSize or null, so drop this upload");
                    return;
                }
                String strEncryptFileMD5 = Md5Util.encryptFileMD5(logByTime);
                if (TextUtils.isEmpty(str2) || strEncryptFileMD5.equals(str2)) {
                    UploadLogTask.this.uploadLogFile(str, list, logByTime, z, strEncryptFileMD5);
                } else {
                    L.error(UploadLogTask.TAG, "local file md5 is not same...");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b6 A[Catch: IOException -> 0x00a3, TRY_ENTER, TRY_LEAVE, TryCatch #5 {IOException -> 0x00a3, blocks: (B:36:0x008a, B:49:0x009f, B:57:0x00b6), top: B:73:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void uploadLogFile(java.lang.String r18, java.util.List<java.lang.String> r19, java.io.File r20, boolean r21, java.lang.String r22) throws java.lang.Throwable {
        /*
            r17 = this;
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r18
            java.lang.String r12 = "UploadLogTask"
            java.lang.String r3 = " 上传指定文件uploadLogFile-> %s"
            com.duowan.auk.util.L.debug(r12, r3, r1)
            r1 = 0
            long r3 = r20.length()     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L97 java.io.FileNotFoundException -> La9
            r13 = 131072(0x20000, double:6.4758E-319)
            long r3 = r3 / r13
            int r4 = (int) r3     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L97 java.io.FileNotFoundException -> La9
            long r5 = r20.length()     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L97 java.io.FileNotFoundException -> La9
            long r5 = r5 % r13
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 <= 0) goto L23
            goto L24
        L23:
            r0 = 0
        L24:
            int r0 = r0 + r4
            r15 = r17
            r3 = r19
            boolean[] r16 = r15.getNoNeedUploadChunks(r3, r0)     // Catch: java.io.IOException -> L8e java.io.FileNotFoundException -> L90 java.lang.Throwable -> Lba
            java.io.RandomAccessFile r11 = new java.io.RandomAccessFile     // Catch: java.io.IOException -> L8e java.io.FileNotFoundException -> L90 java.lang.Throwable -> Lba
            java.lang.String r3 = "rw"
            r10 = r20
            r11.<init>(r10, r3)     // Catch: java.io.IOException -> L8e java.io.FileNotFoundException -> L90 java.lang.Throwable -> Lba
        L36:
            if (r2 >= r0) goto L89
            boolean r1 = r16[r2]     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            if (r1 == 0) goto L3e
            r1 = r11
            goto L7a
        L3e:
            long r3 = (long) r2     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            long r8 = r3 * r13
            long r3 = r20.length()     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            long r3 = r3 - r8
            long r3 = java.lang.Math.min(r3, r13)     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            int r1 = (int) r3
            if (r1 > 0) goto L58
            java.lang.String r0 = "bufferSize <= 0"
            com.duowan.auk.util.L.error(r12, r0)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            goto L89
        L53:
            r0 = move-exception
            r2 = r0
            r1 = r11
            goto Lbc
        L58:
            r11.seek(r8)     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            byte[] r5 = new byte[r1]     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            int r1 = r11.read(r5)     // Catch: java.lang.Throwable -> L80 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            r3 = -1
            if (r1 != r3) goto L6a
            java.lang.String r0 = "read chunk failed"
            com.duowan.auk.util.L.error(r12, r0)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L83 java.io.FileNotFoundException -> L86
            goto L89
        L6a:
            r1 = 0
            r3 = r17
            r4 = r18
            r6 = r22
            r7 = r20
            r10 = r1
            r1 = r11
            r11 = r21
            r3.requestUpload(r4, r5, r6, r7, r8, r10, r11)     // Catch: java.io.IOException -> L8e java.io.FileNotFoundException -> L90 java.lang.Throwable -> Lba
        L7a:
            int r2 = r2 + 1
            r10 = r20
            r11 = r1
            goto L36
        L80:
            r0 = move-exception
            r1 = r11
            goto L95
        L83:
            r0 = move-exception
            r1 = r11
            goto L9a
        L86:
            r0 = move-exception
            r1 = r11
            goto Lac
        L89:
            r1 = r11
            r1.close()     // Catch: java.io.IOException -> La3
            goto Lb9
        L8e:
            r0 = move-exception
            goto L9a
        L90:
            r0 = move-exception
            goto Lac
        L92:
            r0 = move-exception
            r15 = r17
        L95:
            r2 = r0
            goto Lbc
        L97:
            r0 = move-exception
            r15 = r17
        L9a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lba
            if (r1 == 0) goto Lb9
            r1.close()     // Catch: java.io.IOException -> La3
            goto Lb9
        La3:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
            goto Lb9
        La9:
            r0 = move-exception
            r15 = r17
        Lac:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r0 = "log file not found error"
            com.duowan.auk.util.L.error(r12, r0)     // Catch: java.lang.Throwable -> Lba
            if (r1 == 0) goto Lb9
            r1.close()     // Catch: java.io.IOException -> La3
        Lb9:
            return
        Lba:
            r0 = move-exception
            goto L95
        Lbc:
            if (r1 == 0) goto Lc7
            r1.close()     // Catch: java.io.IOException -> Lc2
            goto Lc7
        Lc2:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        Lc7:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.one.module.uploadLog.function.UploadLogTask.uploadLogFile(java.lang.String, java.util.List, java.io.File, boolean, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestUpload(final String str, final byte[] bArr, final String str2, final File file, final long j, final int i, final boolean z) {
        AsyncHttpClient.RequestParams requestParams = new AsyncHttpClient.RequestParams();
        requestParams.put("file", new ByteArrayInputStream(bArr), file.getName(), URLConnection.guessContentTypeFromName(file.getName()));
        new UploadTask(FeedBackConstants.LOG_UPLOAD_URL + "?" + FeedBackConstants.KEY_LOG_FBID + SimpleComparison.EQUAL_TO_OPERATION + str + "&" + FeedBackConstants.KEY_LOG_ISRELOAD + SimpleComparison.EQUAL_TO_OPERATION + "0&" + FeedBackConstants.KEY_LOG_MD5 + SimpleComparison.EQUAL_TO_OPERATION + str2 + "&" + FeedBackConstants.KEY_LOG_FILESIZE + SimpleComparison.EQUAL_TO_OPERATION + file.length() + "&" + FeedBackConstants.KEY_LOG_BEGIN_POSITION + SimpleComparison.EQUAL_TO_OPERATION + String.valueOf(j)) { // from class: com.duowan.live.one.module.uploadLog.function.UploadLogTask.3
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.duowan.live.one.module.uploadLog.function.UploadTask
            protected void onResponse(boolean z2, AsyncHttpClient.RequestParams requestParams2, String str3) {
                if (z2) {
                    try {
                        LogUploadRsp logUploadRsp = (LogUploadRsp) new Gson().fromJson(str3, LogUploadRsp.class);
                        String result = logUploadRsp.getResult();
                        byte b = -1;
                        switch (result.hashCode()) {
                            case 48:
                                if (result.equals("0")) {
                                    b = 0;
                                }
                                break;
                            case 49:
                                if (result.equals("1")) {
                                    b = 1;
                                }
                                break;
                            case 50:
                                if (result.equals("2")) {
                                    b = 2;
                                }
                                break;
                        }
                        if (b == 0) {
                            L.info(UploadLogTask.TAG, "---file %s is uploaded failed, %s uploadTime %d---", file.getName(), logUploadRsp.getDescription(), Long.valueOf(System.currentTimeMillis()));
                            return;
                        }
                        if (b != 1) {
                            if (b != 2) {
                                return;
                            }
                            L.debug(UploadLogTask.TAG, "---- start %d file %s is partial uploaded---", Long.valueOf(j), file.getName());
                            return;
                        } else {
                            L.debug(UploadLogTask.TAG, "---file %s is upload succeed to %s, now is to delete it---", file.getName(), logUploadRsp.getUrl());
                            boolean zDelete = file.delete();
                            Object[] objArr = new Object[2];
                            objArr[0] = file.getName();
                            objArr[1] = zDelete ? "succeed" : "failed";
                            L.info(UploadLogTask.TAG, "---file %s is deleted %s---", objArr);
                            return;
                        }
                    } catch (Exception e) {
                        L.error(UploadLogTask.TAG, (Throwable) e);
                        return;
                    }
                }
                L.info(UploadLogTask.TAG, "%s uploadLogTask is failed, %s uploadTime %d", file.getName(), str3, Integer.valueOf(i));
                int i2 = i;
                if (i2 < 3) {
                    UploadLogTask.this.requestUpload(str, bArr, str2, file, j, i2 + 1, z);
                }
            }
        }.runPost(requestParams, true);
    }

    private boolean[] getNoNeedUploadChunks(List<String> list, int i) {
        if (list == null) {
            list = new ArrayList<>();
        }
        boolean[] zArr = new boolean[i];
        if (i == 1) {
            zArr[0] = true;
            return zArr;
        }
        for (int i2 = 0; i2 < i; i2++) {
            zArr[i2] = false;
        }
        if (list.size() > 0) {
            for (String str : list) {
                long jLongValue = Long.valueOf(str.substring(0, str.indexOf("-"))).longValue();
                long jLongValue2 = Long.valueOf(str.substring(str.indexOf("-") + 1)).longValue();
                if (jLongValue2 > 0) {
                    int i3 = (int) (jLongValue2 / PlaybackStateCompat.ACTION_PREPARE_FROM_URI);
                    for (int i4 = ((int) jLongValue) / 131072; i4 <= i3 && i4 < i; i4++) {
                        L.debug(TAG, "fbId %s 's chunk %s has been uploaded before", this.mFbId, Integer.valueOf(i4));
                        zArr[i4] = true;
                    }
                }
            }
        }
        return zArr;
    }
}
