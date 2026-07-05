package com.nirvana.tools.logger.cache.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.nirvana.tools.logger.model.ACMLoggerRecord;
import com.nirvana.tools.logger.utils.ConsoleLogUtils;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ACMLogDatabase extends AbstractDatabase<ACMLoggerRecord> {
    private static final String LOG_DATABASE_NAME = "logger.db";
    private static final String TAG = "ALICOM_LoggerDao";

    /* JADX WARN: Illegal instructions before constructor call */
    public ACMLogDatabase(Context context, String str, String str2) {
        String str3;
        if (str2 == null) {
            str3 = LOG_DATABASE_NAME;
        } else {
            str3 = str2 + "_logger.db";
        }
        super(str, new DBHelper(context, str3, null, 3, DBHelpTool.getCreateLogTableSql(str), DBHelpTool.getDropTableSql(str), DBHelpTool.getCreateLogIndexSql(str)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nirvana.tools.logger.cache.db.AbstractDatabase
    public ContentValues getContentValuesByRecord(ACMLoggerRecord aCMLoggerRecord) {
        if (aCMLoggerRecord == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelpTool.RecordEntry.COLUMN_NAME_STRATEGY, Integer.valueOf(aCMLoggerRecord.getStrategy()));
        contentValues.put("timestamp", Long.valueOf(aCMLoggerRecord.getTimestamp()));
        contentValues.put("content", aCMLoggerRecord.getContent());
        contentValues.put(DBHelpTool.RecordEntry.COLUMN_NAME_LEVEL, Integer.valueOf(aCMLoggerRecord.getLevel()));
        contentValues.put(DBHelpTool.RecordEntry.COLUMN_UPLOAD_FLAG, Integer.valueOf(aCMLoggerRecord.getUploadFlag()));
        contentValues.put(DBHelpTool.RecordEntry.COLUMN_UPLOAD_COUNT, Integer.valueOf(aCMLoggerRecord.getUploadCount()));
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nirvana.tools.logger.cache.db.AbstractDatabase
    public ACMLoggerRecord parseDataFromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ACMLoggerRecord aCMLoggerRecord = new ACMLoggerRecord();
        aCMLoggerRecord.setId(cursor.getLong(cursor.getColumnIndex(DBDefinition.ID)));
        aCMLoggerRecord.setLevel(cursor.getInt(cursor.getColumnIndex(DBHelpTool.RecordEntry.COLUMN_NAME_LEVEL)));
        aCMLoggerRecord.setStrategy(cursor.getInt(cursor.getColumnIndex(DBHelpTool.RecordEntry.COLUMN_NAME_STRATEGY)));
        aCMLoggerRecord.setContent(cursor.getString(cursor.getColumnIndex("content")));
        aCMLoggerRecord.setUploadFlag(cursor.getInt(cursor.getColumnIndex(DBHelpTool.RecordEntry.COLUMN_UPLOAD_FLAG)));
        aCMLoggerRecord.setUploadCount(cursor.getInt(cursor.getColumnIndex(DBHelpTool.RecordEntry.COLUMN_UPLOAD_COUNT)));
        aCMLoggerRecord.setTimestamp(cursor.getLong(cursor.getColumnIndex("timestamp")));
        return aCMLoggerRecord;
    }

    public synchronized List<ACMLoggerRecord> queryLog(long j, long j2, int i, int i2, int i3) {
        ArrayList arrayList;
        try {
            ArrayList arrayList2 = new ArrayList();
            StringBuilder sb = new StringBuilder();
            arrayList2.add(String.valueOf(i3));
            sb.append("level=?");
            if (j > 0) {
                arrayList2.add(String.valueOf(j));
                sb.append(" and timestamp");
                sb.append(">=?");
            }
            if (j2 > 0) {
                arrayList2.add(String.valueOf(j2));
                sb.append(" and timestamp<=?");
            }
            if (i2 >= 0) {
                arrayList2.add(String.valueOf(i2));
                sb.append(" and upload_flag=?");
            }
            String strValueOf = i > 0 ? String.valueOf(i) : "";
            String[] strArr = new String[arrayList2.size()];
            arrayList2.toArray(strArr);
            ConsoleLogUtils.logcatV(TAG, "query: selection=" + ((Object) sb));
            arrayList = new ArrayList();
            Cursor cursorQuery = getReadDatabase().query(this.mTableName, null, sb.toString(), strArr, null, null, null, strValueOf);
            while (cursorQuery.moveToNext()) {
                ACMLoggerRecord dataFromCursor = parseDataFromCursor(cursorQuery);
                if (dataFromCursor != null) {
                    arrayList.add(dataFromCursor);
                }
            }
            cursorQuery.close();
            ConsoleLogUtils.logcatV(TAG, "query: result=" + arrayList + ", size=" + arrayList.size());
            close();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                close();
                return null;
            } catch (Throwable th2) {
                close();
                throw th2;
            }
        }
        return arrayList;
    }
}
