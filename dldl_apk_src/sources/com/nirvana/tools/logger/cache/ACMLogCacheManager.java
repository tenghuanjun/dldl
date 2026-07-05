package com.nirvana.tools.logger.cache;

import android.content.Context;
import com.nirvana.tools.logger.cache.db.ACMLogDatabase;
import com.nirvana.tools.logger.executor.ReentrantSingleThreadExecutor;
import com.nirvana.tools.logger.model.ACMLoggerRecord;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ACMLogCacheManager extends ACMCacheManager<ACMLoggerRecord, ACMLogDatabase> {
    public ACMLogCacheManager(Context context, ReentrantSingleThreadExecutor reentrantSingleThreadExecutor, String str, String str2) {
        super(new ACMLogDatabase(context, str, str2), reentrantSingleThreadExecutor);
    }

    public List<ACMLoggerRecord> getLoggerRecords(long j, long j2, int i, int i2, int i3) {
        return ((ACMLogDatabase) this.mDatabase).queryLog(j, j2, i, i2, i3);
    }
}
