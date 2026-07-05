package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.OSSSharedPreferences;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SequenceUploadTask extends BaseMultipartUploadTask<ResumableUploadRequest, ResumableUploadResult> implements Callable<ResumableUploadResult> {
    private List<Integer> mAlreadyUploadIndex;
    private File mCRC64RecordFile;
    private long mFirstPartSize;
    private File mRecordFile;
    private OSSSharedPreferences mSp;

    public SequenceUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext, InternalRequestOperation internalRequestOperation) {
        super(internalRequestOperation, resumableUploadRequest, oSSCompletedCallback, executionContext);
        this.mAlreadyUploadIndex = new ArrayList();
        this.mSp = OSSSharedPreferences.instance(this.mContext.getApplicationContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x013e  */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void initMultipartUploadId() throws com.alibaba.sdk.android.oss.ServiceException, com.alibaba.sdk.android.oss.ClientException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 655
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.initMultipartUploadId():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public ResumableUploadResult doMultipartUpload() throws Throwable {
        long j = this.mUploadedLength;
        checkCancel();
        int i = this.mPartAttr[0];
        int i2 = this.mPartAttr[1];
        if (this.mPartETags.size() > 0 && this.mAlreadyUploadIndex.size() > 0) {
            if (this.mUploadedLength > this.mFileLength) {
                throw new ClientException("The uploading file is inconsistent with before");
            }
            if (this.mFirstPartSize != i) {
                throw new ClientException("The part size setting is inconsistent with before");
            }
            long jLongValue = this.mUploadedLength;
            if (!TextUtils.isEmpty(this.mSp.getStringValue(this.mUploadId))) {
                jLongValue = Long.valueOf(this.mSp.getStringValue(this.mUploadId)).longValue();
            }
            long j2 = jLongValue;
            if (this.mProgressCallback != null) {
                this.mProgressCallback.onProgress(this.mRequest, j2, this.mFileLength);
            }
            this.mSp.removeKey(this.mUploadId);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mAlreadyUploadIndex.size() == 0 || !this.mAlreadyUploadIndex.contains(Integer.valueOf(i3 + 1))) {
                if (i3 == i2 - 1) {
                    i = (int) (this.mFileLength - j);
                }
                OSSLog.logDebug("upload part readByte : " + i);
                j += (long) i;
                uploadPart(i3, i, i2);
                if (this.mUploadException != null) {
                    break;
                }
            }
        }
        checkException();
        CompleteMultipartUploadResult completeMultipartUploadResult = completeMultipartUploadResult();
        ResumableUploadResult resumableUploadResult = completeMultipartUploadResult != null ? new ResumableUploadResult(completeMultipartUploadResult) : null;
        File file = this.mRecordFile;
        if (file != null) {
            file.delete();
        }
        File file2 = this.mCRC64RecordFile;
        if (file2 != null) {
            file2.delete();
        }
        return resumableUploadResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c3 A[Catch: ServiceException -> 0x0117, Exception -> 0x011b, all -> 0x01ba, TryCatch #2 {ServiceException -> 0x0117, blocks: (B:31:0x0093, B:33:0x00c3, B:34:0x00ce, B:36:0x00e7, B:44:0x0102, B:45:0x0116), top: B:104:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e7 A[Catch: ServiceException -> 0x0117, Exception -> 0x011b, all -> 0x01ba, TRY_LEAVE, TryCatch #2 {ServiceException -> 0x0117, blocks: (B:31:0x0093, B:33:0x00c3, B:34:0x00ce, B:36:0x00e7, B:44:0x0102, B:45:0x0116), top: B:104:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0102 A[Catch: ServiceException -> 0x0117, Exception -> 0x011b, all -> 0x01ba, TRY_ENTER, TryCatch #2 {ServiceException -> 0x0117, blocks: (B:31:0x0093, B:33:0x00c3, B:34:0x00ce, B:36:0x00e7, B:44:0x0102, B:45:0x0116), top: B:104:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x013f A[Catch: IOException -> 0x0143, TRY_ENTER, TryCatch #14 {IOException -> 0x0143, blocks: (B:38:0x00f3, B:40:0x00f8, B:42:0x00fd, B:64:0x013f, B:68:0x0147, B:70:0x014c, B:85:0x01ac, B:87:0x01b1, B:89:0x01b6), top: B:111:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0147 A[Catch: IOException -> 0x0143, TryCatch #14 {IOException -> 0x0143, blocks: (B:38:0x00f3, B:40:0x00f8, B:42:0x00fd, B:64:0x013f, B:68:0x0147, B:70:0x014c, B:85:0x01ac, B:87:0x01b1, B:89:0x01b6), top: B:111:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014c A[Catch: IOException -> 0x0143, TRY_LEAVE, TryCatch #14 {IOException -> 0x0143, blocks: (B:38:0x00f3, B:40:0x00f8, B:42:0x00fd, B:64:0x013f, B:68:0x0147, B:70:0x014c, B:85:0x01ac, B:87:0x01b1, B:89:0x01b6), top: B:111:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0163 A[Catch: all -> 0x01ba, TryCatch #17 {all -> 0x01ba, blocks: (B:30:0x007b, B:31:0x0093, B:33:0x00c3, B:34:0x00ce, B:36:0x00e7, B:44:0x0102, B:45:0x0116, B:77:0x015b, B:79:0x0163, B:80:0x0167, B:82:0x0181, B:83:0x019f), top: B:105:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0167 A[Catch: all -> 0x01ba, TryCatch #17 {all -> 0x01ba, blocks: (B:30:0x007b, B:31:0x0093, B:33:0x00c3, B:34:0x00ce, B:36:0x00e7, B:44:0x0102, B:45:0x0116, B:77:0x015b, B:79:0x0163, B:80:0x0167, B:82:0x0181, B:83:0x019f), top: B:105:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01ac A[Catch: IOException -> 0x0143, TRY_ENTER, TryCatch #14 {IOException -> 0x0143, blocks: (B:38:0x00f3, B:40:0x00f8, B:42:0x00fd, B:64:0x013f, B:68:0x0147, B:70:0x014c, B:85:0x01ac, B:87:0x01b1, B:89:0x01b6), top: B:111:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b1 A[Catch: IOException -> 0x0143, TryCatch #14 {IOException -> 0x0143, blocks: (B:38:0x00f3, B:40:0x00f8, B:42:0x00fd, B:64:0x013f, B:68:0x0147, B:70:0x014c, B:85:0x01ac, B:87:0x01b1, B:89:0x01b6), top: B:111:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01b6 A[Catch: IOException -> 0x0143, TRY_LEAVE, TryCatch #14 {IOException -> 0x0143, blocks: (B:38:0x00f3, B:40:0x00f8, B:42:0x00fd, B:64:0x013f, B:68:0x0147, B:70:0x014c, B:85:0x01ac, B:87:0x01b1, B:89:0x01b6), top: B:111:0x0006 }] */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void uploadPart(int r16, int r17, int r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.uploadPart(int, int, int):void");
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void checkException() throws Throwable {
        ObjectOutputStream objectOutputStream;
        if (this.mContext.getCancellationHandler().isCancelled()) {
            if (((ResumableUploadRequest) this.mRequest).deleteUploadOnCancelling().booleanValue()) {
                abortThisUpload();
                File file = this.mRecordFile;
                if (file != null) {
                    file.delete();
                }
            } else if (this.mPartETags != null && this.mPartETags.size() > 0 && this.mCheckCRC64 && ((ResumableUploadRequest) this.mRequest).getRecordDirectory() != null) {
                HashMap map = new HashMap();
                for (PartETag partETag : this.mPartETags) {
                    map.put(Integer.valueOf(partETag.getPartNumber()), Long.valueOf(partETag.getCRC64()));
                }
                ObjectOutputStream objectOutputStream2 = null;
                try {
                    try {
                        File file2 = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                        this.mCRC64RecordFile = file2;
                        if (!file2.exists()) {
                            this.mCRC64RecordFile.createNewFile();
                        }
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.mCRC64RecordFile));
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    objectOutputStream.writeObject(map);
                    objectOutputStream.close();
                } catch (IOException e2) {
                    e = e2;
                    objectOutputStream2 = objectOutputStream;
                    OSSLog.logThrowable2Local(e);
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream;
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                    throw th;
                }
            }
        }
        super.checkException();
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void abortThisUpload() {
        if (this.mUploadId != null) {
            this.mApiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId), null).waitUntilFinished();
        }
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void processException(Exception exc) {
        if (this.mUploadException == null || !exc.getMessage().equals(this.mUploadException.getMessage())) {
            this.mUploadException = exc;
        }
        OSSLog.logThrowable2Local(exc);
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mIsCancel) {
            return;
        }
        this.mIsCancel = true;
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void uploadPartFinish(PartETag partETag) throws Exception {
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mSp.contains(this.mUploadId)) {
            return;
        }
        this.mSp.setStringValue(this.mUploadId, String.valueOf(this.mUploadedLength));
        onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
    }
}
