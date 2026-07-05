package com.nirvana.tools.logger.upload;

import android.content.Context;
import com.nirvana.tools.logger.cache.ACMLogCacheManager;
import com.nirvana.tools.logger.cache.db.DbException;
import com.nirvana.tools.logger.executor.AbstractSafeRunnable;
import com.nirvana.tools.logger.executor.ReentrantSingleThreadExecutor;
import com.nirvana.tools.logger.model.ACMLoggerRecord;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ACMLogUploadManager extends AbstractACMUploadManager<ACMLoggerRecord> {
    private ACMLogCacheManager mCacheManager;

    public ACMLogUploadManager(Context context, ACMLogCacheManager aCMLogCacheManager, ACMUpload<ACMLoggerRecord> aCMUpload, ReentrantSingleThreadExecutor reentrantSingleThreadExecutor) {
        super(context, aCMUpload, aCMLogCacheManager, reentrantSingleThreadExecutor);
        this.mCacheManager = aCMLogCacheManager;
    }

    @Override // com.nirvana.tools.logger.upload.AbstractACMUploadManager
    protected void processUploadingFailed(List<ACMLoggerRecord> list) throws DbException {
        this.mCacheManager.addUploadCount(list);
    }

    public void uploadLogger(final long j, final long j2, final int i) {
        this.mExecutor.execute(new AbstractSafeRunnable() { // from class: com.nirvana.tools.logger.upload.ACMLogUploadManager.1
            @Override // com.nirvana.tools.logger.executor.AbstractSafeRunnable
            public void safeRun() {
                List<ACMLoggerRecord> loggerRecords;
                for (int i2 = 0; ACMLogUploadManager.this.isAllowUploading() && i2 < 100 && (loggerRecords = ACMLogUploadManager.this.mCacheManager.getLoggerRecords(j, j2, 20, 0, i)) != null && loggerRecords.size() > 0; i2++) {
                    try {
                        ACMLogUploadManager.this.doUploadRecords(loggerRecords);
                    } catch (DbException unused) {
                        return;
                    }
                }
            }
        });
    }
}
