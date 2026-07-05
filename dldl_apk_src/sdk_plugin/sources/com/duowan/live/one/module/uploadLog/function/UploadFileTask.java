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
import com.huya.mtp.utils.FP;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UploadFileTask {
    private static final int DEFAULT_CHUNK_SIZE = 131072;
    private static final int MAX_TRY_TIME = 3;
    private static final String NEED = "1";
    private static final String TAG = UploadFileTask.class.getSimpleName();
    private String mFbId;
    private List<String> mFilePaths;
    private long mMaxFileSize;

    public UploadFileTask(String str, long j, List<String> list) {
        if (FP.empty(list)) {
            L.info(TAG, "file path is null");
            return;
        }
        this.mFbId = str;
        this.mMaxFileSize = j;
        this.mFilePaths = list;
    }

    public void execute() {
        getRemoteFileRange();
    }

    public void getRemoteFileRange() {
        if (TextUtils.isEmpty(this.mFbId)) {
            return;
        }
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.putBody(FeedBackConstants.KEY_LOG_FBID, this.mFbId);
        requestParams.putBody(FeedBackConstants.KEY_LOG_IS_NEED_META_DATA, "1");
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        requestParams.putBody("token", defaultToken.getToken());
        requestParams.putBody(FeedBackConstants.KEY_FB_TICKETTYPE, String.valueOf(defaultToken.getTokenType()));
        HttpClient.post(FeedBackConstants.LOG_GET_FILE_RANGE_URL, requestParams, new HttpClient.HttpHandler() { // from class: com.duowan.live.one.module.uploadLog.function.UploadFileTask.1
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
                    UploadFileTask.this.upload(md5, range);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                L.error(UploadFileTask.TAG, "getRemoteFileRange onFailure...");
            }
        });
    }

    public void upload(final String str, final List<String> list) {
        L.debug(TAG, " upload %s", this.mFbId);
        ThreadPoolFactory.run(new Runnable() { // from class: com.duowan.live.one.module.uploadLog.function.UploadFileTask.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                boolean z = list.size() > 0;
                File uploadFile = UploadFileTask.this.getUploadFile();
                if (uploadFile == null) {
                    L.error(UploadFileTask.TAG, "file is null, so drop this upload");
                    return;
                }
                if (uploadFile.length() > UploadFileTask.this.mMaxFileSize) {
                    L.error(UploadFileTask.TAG, "file's size is over mMaxFileSize or null, so drop this upload");
                    return;
                }
                String strEncryptFileMD5 = Md5Util.encryptFileMD5(uploadFile);
                if (!TextUtils.isEmpty(str) && !strEncryptFileMD5.equals(str)) {
                    L.error(UploadFileTask.TAG, "local file md5 is not same...");
                } else {
                    UploadFileTask uploadFileTask = UploadFileTask.this;
                    uploadFileTask.uploadLogFile(uploadFileTask.mFbId, list, uploadFile, z, strEncryptFileMD5);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getUploadFile() throws Throwable {
        File file = new File(LogUtils.getCompressFileName(this.mFbId));
        if (file.exists()) {
            return file;
        }
        ArrayList<File> arrayList = new ArrayList();
        Iterator<String> it = this.mFilePaths.iterator();
        while (it.hasNext()) {
            File file2 = new File(it.next());
            if (file2.exists() && file2.length() > 0) {
                arrayList.add(file2);
            }
        }
        if (FP.empty(arrayList)) {
            L.info(TAG, "file list is empty");
            return null;
        }
        File fileCompressFile = LogUtils.compressFile(arrayList, this.mFbId);
        for (File file3 : arrayList) {
            if (file3 != null) {
                file3.delete();
            }
        }
        return fileCompressFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadLogFile(String str, List<String> list, File file, boolean z, String str2) {
        L.debug(TAG, " 上传指定文件uploadLogFile-> %s", str);
        try {
            int length = ((int) (file.length() / PlaybackStateCompat.ACTION_PREPARE_FROM_URI)) + (file.length() % PlaybackStateCompat.ACTION_PREPARE_FROM_URI <= 0 ? 0 : 1);
            try {
                boolean[] noNeedUploadChunks = getNoNeedUploadChunks(list, length);
                for (int i = 0; i < length; i++) {
                    if (!noNeedUploadChunks[i]) {
                        long j = ((long) i) * PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                        int iMin = (int) Math.min(file.length() - j, PlaybackStateCompat.ACTION_PREPARE_FROM_URI);
                        if (iMin <= 0) {
                            L.error(TAG, "bufferSize <= 0");
                            return;
                        }
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(j);
                        byte[] bArr = new byte[iMin];
                        if (randomAccessFile.read(bArr) == -1) {
                            L.error(TAG, "read chunk failed");
                            return;
                        }
                        requestUpload(str, bArr, str2, file, j, 0, z);
                    }
                }
            } catch (FileNotFoundException e) {
                e = e;
                e.printStackTrace();
                L.error(TAG, "log file not found error");
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (FileNotFoundException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestUpload(final String str, final byte[] bArr, final String str2, final File file, final long j, final int i, final boolean z) {
        AsyncHttpClient.RequestParams requestParams = new AsyncHttpClient.RequestParams();
        requestParams.put("file", new ByteArrayInputStream(bArr), file.getName(), URLConnection.guessContentTypeFromName(file.getName()));
        new UploadTask(FeedBackConstants.LOG_UPLOAD_URL + "?" + FeedBackConstants.KEY_LOG_FBID + SimpleComparison.EQUAL_TO_OPERATION + str + "&" + FeedBackConstants.KEY_LOG_ISRELOAD + SimpleComparison.EQUAL_TO_OPERATION + "0&" + FeedBackConstants.KEY_LOG_MD5 + SimpleComparison.EQUAL_TO_OPERATION + str2 + "&" + FeedBackConstants.KEY_LOG_FILESIZE + SimpleComparison.EQUAL_TO_OPERATION + file.length() + "&" + FeedBackConstants.KEY_LOG_BEGIN_POSITION + SimpleComparison.EQUAL_TO_OPERATION + String.valueOf(j)) { // from class: com.duowan.live.one.module.uploadLog.function.UploadFileTask.3
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.duowan.live.one.module.uploadLog.function.UploadTask
            protected void onResponse(boolean z2, AsyncHttpClient.RequestParams requestParams2, String str3) {
                if (!z2) {
                    L.info(UploadFileTask.TAG, "%s uploadLogTask is failed, %s uploadTime %d", file.getName(), str3, Integer.valueOf(i));
                    int i2 = i;
                    if (i2 < 3) {
                        UploadFileTask.this.requestUpload(str, bArr, str2, file, j, i2 + 1, z);
                        return;
                    }
                    return;
                }
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
                        L.info(UploadFileTask.TAG, "---file %s is uploaded failed, %s uploadTime %d---", file.getName(), logUploadRsp.getDescription(), Long.valueOf(System.currentTimeMillis()));
                        return;
                    }
                    if (b != 1) {
                        if (b != 2) {
                            return;
                        }
                        L.debug(UploadFileTask.TAG, "---- start %d file %s is partial uploaded---", Long.valueOf(j), file.getName());
                        return;
                    }
                    L.debug(UploadFileTask.TAG, "---file %s is upload succeed to %s, now is to delete it---", file.getName(), logUploadRsp.getUrl());
                    boolean zDelete = file.delete();
                    String str4 = UploadFileTask.TAG;
                    Object[] objArr = new Object[2];
                    objArr[0] = file.getName();
                    objArr[1] = zDelete ? "succeed" : "failed";
                    L.info(str4, "---file %s is deleted %s---", objArr);
                } catch (Exception e) {
                    L.error(UploadFileTask.TAG, (Throwable) e);
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
