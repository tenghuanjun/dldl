package com.nirvana.tools.logger.cache;

import com.nirvana.tools.logger.cache.db.AbstractDatabase;
import com.nirvana.tools.logger.cache.db.DbException;
import com.nirvana.tools.logger.executor.AbstractSafeRunnable;
import com.nirvana.tools.logger.executor.ReentrantSingleThreadExecutor;
import com.nirvana.tools.logger.model.ACMRecord;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ACMCacheManager<T extends ACMRecord, G extends AbstractDatabase<T>> {
    protected G mDatabase;
    protected ReentrantSingleThreadExecutor mExecutor;

    /* JADX INFO: renamed from: com.nirvana.tools.logger.cache.ACMCacheManager$1, reason: invalid class name */
    class AnonymousClass1 extends AbstractSafeRunnable {
        final /* synthetic */ ACMRecord val$record;

        AnonymousClass1(ACMRecord aCMRecord) {
            this.val$record = aCMRecord;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.nirvana.tools.logger.executor.AbstractSafeRunnable
        public void safeRun() {
            ACMCacheManager.this.mDatabase.insert(this.val$record);
        }
    }

    /* JADX INFO: renamed from: com.nirvana.tools.logger.cache.ACMCacheManager$2, reason: invalid class name */
    class AnonymousClass2 extends AbstractSafeRunnable {
        final /* synthetic */ List val$records;
        final /* synthetic */ long val$time;

        AnonymousClass2(List list, long j) {
            this.val$records = list;
            this.val$time = j;
        }

        @Override // com.nirvana.tools.logger.executor.AbstractSafeRunnable
        public void safeRun() throws DbException {
            ACMCacheManager.this.mDatabase.updateUploadCount(this.val$records, this.val$time, 1);
        }
    }

    public ACMCacheManager(G g, ReentrantSingleThreadExecutor reentrantSingleThreadExecutor) {
        this.mDatabase = g;
        this.mExecutor = reentrantSingleThreadExecutor;
    }

    public void addUploadCount(List<T> list) throws DbException {
        this.mDatabase.updateUploadCount(list, System.currentTimeMillis(), 1);
    }

    public boolean cacheRecord(T t) throws DbException {
        return this.mDatabase.insert(t);
    }

    public boolean cacheRecords(List<T> list) throws DbException {
        return this.mDatabase.insertList(list);
    }

    public boolean deleteRecords(List<T> list) throws DbException {
        return this.mDatabase.deleteRecords(list);
    }

    public List<T> getAllFailedRecords() {
        return this.mDatabase.query(-1, 1, null);
    }

    public List<T> getAllRecords() {
        return this.mDatabase.query(-1, -1, null);
    }

    public long getFailedMaxID() {
        return this.mDatabase.queryFailedMaxId();
    }

    public List<T> getFailedRecords(long j, long j2, int i) {
        return this.mDatabase.queryFailed(j, j2, i);
    }

    public List<T> getUnUploadRecords() {
        return this.mDatabase.query(-1, 0, null);
    }

    public boolean hasFailedRecords() {
        List<T> listQuery = this.mDatabase.query(1, 1, null);
        return listQuery != null && listQuery.size() > 0;
    }

    public boolean hasNormalRecords() {
        List<T> listQuery = this.mDatabase.query(1, 0, null);
        return listQuery != null && listQuery.size() > 0;
    }
}
