package com.aliyun.aliyunface.network;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OssClientHelper {
    private static OssClientHelper s_Instance = new OssClientHelper();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ArrayList<FileItem> ossUploadItems = new ArrayList<>();
    private final Object ossLock = new Object();

    public interface OssClientUploadListener {
        void onFinish(int i, int i2);

        boolean onUploadError(int i, String str, String str2, String str3);

        boolean onUploadSuccess(int i, String str, String str2);
    }

    public static OssClientHelper getInstance() {
        return s_Instance;
    }

    private static class FileItem {
        public String bucketName;
        public byte[] fileContent;
        public int fileIdx;
        public String fileName;

        FileItem(int i, String str, String str2, byte[] bArr) {
            this.fileIdx = i;
            this.bucketName = str;
            this.fileName = str2;
            this.fileContent = bArr;
        }
    }

    public void init() {
        synchronized (this.ossLock) {
            this.ossUploadItems = new ArrayList<>();
        }
    }

    public void release() {
        init();
    }

    public void addUploadFile(int i, String str, String str2, byte[] bArr) {
        synchronized (this.ossLock) {
            this.ossUploadItems.add(new FileItem(i, str, str2, bArr));
        }
    }

    public String getUploadFileName(int i) {
        synchronized (this.ossLock) {
            for (FileItem fileItem : this.ossUploadItems) {
                if (fileItem.fileIdx == i) {
                    return fileItem.fileName;
                }
            }
            return "";
        }
    }

    private static class OssInteralCB {
        public String errMsg;
        public boolean success;

        private OssInteralCB() {
            this.success = false;
            this.errMsg = "";
        }
    }

    public void startUploadFiles(final Context context, final String str, final String str2, final String str3, final String str4, final OssClientUploadListener ossClientUploadListener) {
        synchronized (this.ossLock) {
            this.executorService.execute(new Runnable() { // from class: com.aliyun.aliyunface.network.OssClientHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    int i = 0;
                    for (FileItem fileItem : OssClientHelper.this.ossUploadItems) {
                        OssInteralCB ossInteralCBOssUploadFile = OssClientHelper.this.ossUploadFile(context, str, str2, str3, str4, fileItem.bucketName, fileItem.fileName, fileItem.fileContent);
                        if (TextUtils.isEmpty(ossInteralCBOssUploadFile.errMsg) && ossInteralCBOssUploadFile.success) {
                            i++;
                            OssClientUploadListener ossClientUploadListener2 = ossClientUploadListener;
                            if (ossClientUploadListener2 != null) {
                                ossClientUploadListener2.onUploadSuccess(fileItem.fileIdx, fileItem.bucketName, fileItem.fileName);
                            }
                        } else {
                            OssClientUploadListener ossClientUploadListener3 = ossClientUploadListener;
                            if (ossClientUploadListener3 != null && !ossClientUploadListener3.onUploadError(fileItem.fileIdx, fileItem.bucketName, fileItem.fileName, ossInteralCBOssUploadFile.errMsg)) {
                                break;
                            }
                        }
                    }
                    OssClientUploadListener ossClientUploadListener4 = ossClientUploadListener;
                    if (ossClientUploadListener4 != null) {
                        ossClientUploadListener4.onFinish(OssClientHelper.this.ossUploadItems.size(), i);
                    }
                }
            });
        }
    }

    public OssInteralCB ossUploadFile(Context context, String str, String str2, String str3, String str4, final String str5, final String str6, byte[] bArr) {
        final OssInteralCB ossInteralCB = new OssInteralCB();
        try {
        } catch (Exception e) {
            RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "ossUploadServerException", "msg", e.getMessage());
            ossInteralCB.success = false;
            ossInteralCB.errMsg = e.getMessage();
        }
        if (bArr == null) {
            ossInteralCB.success = false;
            ossInteralCB.errMsg = "OSSFile Empty";
            return ossInteralCB;
        }
        OSSStsTokenCredentialProvider oSSStsTokenCredentialProvider = new OSSStsTokenCredentialProvider(str2, str3, str4);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(5);
        clientConfiguration.setMaxErrorRetry(3);
        OSSClient oSSClient = new OSSClient(context, str, oSSStsTokenCredentialProvider, clientConfiguration);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentMD5(BinaryUtil.toBase64String(BinaryUtil.calculateMd5(bArr)));
        PutObjectRequest putObjectRequest = new PutObjectRequest(str5, str6, bArr, objectMetadata);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        oSSClient.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.aliyun.aliyunface.network.OssClientHelper.2
            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onSuccess(PutObjectRequest putObjectRequest2, PutObjectResult putObjectResult) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ossUploadCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                RecordService recordService = RecordService.getInstance();
                RecordLevel recordLevel = RecordLevel.LOG_INFO;
                String[] strArr = new String[6];
                strArr[0] = "bucketName";
                strArr[1] = str5;
                strArr[2] = TTDownloadField.TT_FILE_NAME;
                strArr[3] = str6;
                strArr[4] = "RequestId";
                strArr[5] = putObjectResult != null ? putObjectResult.getRequestId() : "";
                recordService.recordEvent(recordLevel, "ossUploadSuccess", strArr);
                ossInteralCB.success = true;
                ossInteralCB.errMsg = "";
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onFailure(PutObjectRequest putObjectRequest2, ClientException clientException, ServiceException serviceException) {
                ossInteralCB.success = false;
                ossInteralCB.errMsg = "unknownError";
                if (clientException != null) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "ossUploadClientError", "bucketName", str5, TTDownloadField.TT_FILE_NAME, str6, "error", clientException.getMessage());
                    ossInteralCB.errMsg = clientException.getMessage();
                }
                if (serviceException != null) {
                    RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "ossUploadServerError", "bucketName", str5, TTDownloadField.TT_FILE_NAME, str6, "error", serviceException.getMessage(), "RequestId", serviceException.getRequestId(), "StatusCode", "" + serviceException.getStatusCode());
                    ossInteralCB.errMsg = serviceException.getErrorCode();
                }
            }
        }).waitUntilFinished();
        return ossInteralCB;
    }
}
