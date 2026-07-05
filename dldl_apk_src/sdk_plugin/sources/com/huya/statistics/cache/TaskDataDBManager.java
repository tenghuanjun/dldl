package com.huya.statistics.cache;

import android.content.Context;
import android.database.Cursor;
import com.huya.statistics.log.SLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class TaskDataDBManager implements IDiskCacheManager {
    private static final int DATA_RESERVE_TIME_OUT = 259200000;
    private static final int DATA_TIME_OUT = 1296000000;
    private static final String DB_NAME = "huya_statistics";
    private static final String TAG = "TaskDataDBManager";
    private static boolean writeDbEnable = true;
    private TaskDataSqLiteDBHelper helper;

    public TaskDataDBManager(Context context) {
        this.helper = new TaskDataSqLiteDBHelper(context, DB_NAME);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.statistics.cache.IDiskCacheManager
    public void saveTask(TaskData taskData) {
        String str = "saveTask CostTime:";
        if (writeDbEnable) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    this.helper.getWritableDatabase().execSQL("INSERT INTO task_data( uuid,content , createTime, lastSendTime , sendCount , isSuccess) VALUES(?,?,?,?,?,?)", new Object[]{taskData.getUuid(), taskData.getContent(), Long.valueOf(taskData.getCreateTime()), 0, 0, 0});
                    Object[] objArr = new Object[0];
                    SLog.debug(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), objArr);
                    str = objArr;
                } catch (Exception e) {
                    SLog.error(TAG, e.getMessage(), new Object[0]);
                    e.printStackTrace();
                    Object[] objArr2 = new Object[0];
                    SLog.debug(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), objArr2);
                    str = objArr2;
                }
            } catch (Throwable th) {
                SLog.debug(TAG, str + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00bf A[Catch: Exception -> 0x00db, TryCatch #1 {Exception -> 0x00db, blocks: (B:34:0x00ba, B:36:0x00bf, B:37:0x00c2), top: B:41:0x00ba }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.huya.statistics.cache.IDiskCacheManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void saveTaskList(java.util.List<com.huya.statistics.cache.TaskData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.statistics.cache.TaskDataDBManager.saveTaskList(java.util.List):void");
    }

    @Override // com.huya.statistics.cache.IDiskCacheManager
    public List<TaskData> getTaskList(int i, long j) {
        ArrayList arrayList = new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = this.helper.getWritableDatabase().rawQuery("SELECT id,uuid,content , createTime, lastSendTime , sendCount , isSuccess FROM task_data  WHERE  isSuccess=0  and createTime <=? ORDER BY id  limit ?", new String[]{String.valueOf(j), String.valueOf(i)});
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        TaskData taskData = new TaskData();
                        taskData.setId(cursorRawQuery.getLong(0));
                        taskData.setUuid(cursorRawQuery.getBlob(1));
                        taskData.setContent(cursorRawQuery.getBlob(2));
                        taskData.setCreateTime(cursorRawQuery.getLong(3));
                        taskData.setLastSendTime(cursorRawQuery.getLong(4));
                        taskData.setId(cursorRawQuery.getInt(5));
                        taskData.setIsSuccess(cursorRawQuery.getInt(6));
                        arrayList.add(taskData);
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                SLog.debug(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            } catch (Exception e) {
                SLog.error(TAG, e.getMessage(), new Object[0]);
                e.printStackTrace();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                SLog.debug(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            SLog.debug(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            throw th;
        }
    }

    @Override // com.huya.statistics.cache.IDiskCacheManager
    public void setWriteDbEnable(boolean z) {
        writeDbEnable = z;
    }

    @Override // com.huya.statistics.cache.IDiskCacheManager
    public int getLastTaskId() {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = this.helper.getWritableDatabase().rawQuery("SELECT id FROM task_data  WHERE  isSuccess=0 ORDER BY createTime desc limit 1", null);
            } catch (Exception e) {
                SLog.error(TAG, e.getMessage(), new Object[0]);
                e.printStackTrace();
                if (cursorRawQuery == null) {
                    return -1;
                }
            }
            if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
                if (cursorRawQuery == null) {
                    return -1;
                }
                cursorRawQuery.close();
                return -1;
            }
            int i = cursorRawQuery.getInt(0);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return i;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    @Override // com.huya.statistics.cache.IDiskCacheManager
    public boolean updateTask(Object[] objArr, boolean z) {
        if (!writeDbEnable) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("UPDATE  task_data  SET isSuccess=");
        sb.append(z ? "1" : "0");
        sb.append(",lastSendTime= ");
        sb.append(String.valueOf(System.currentTimeMillis()));
        sb.append(",sendCount=sendCount+1");
        sb.append(" WHERE  uuid in(");
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            sb.append("?");
            if (i < length - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        try {
            try {
                this.helper.getWritableDatabase().execSQL(sb.toString(), objArr);
                SLog.info(TAG, "updateTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
                return true;
            } catch (Exception e) {
                SLog.error(TAG, e.getMessage(), new Object[0]);
                e.printStackTrace();
                SLog.info(TAG, "updateTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
                return false;
            }
        } catch (Throwable th) {
            SLog.info(TAG, "updateTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            throw th;
        }
    }

    @Override // com.huya.statistics.cache.IDiskCacheManager
    public void cleanOldData() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                this.helper.getWritableDatabase().execSQL("DELETE FROM task_data WHERE createTime<? OR (isSuccess=1 AND createTime<?)", new Object[]{Long.valueOf(jCurrentTimeMillis - 1296000000), Long.valueOf(jCurrentTimeMillis - 259200000)});
                SLog.info(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            } catch (Exception e) {
                SLog.error(TAG, e.getMessage(), new Object[0]);
                e.printStackTrace();
                SLog.info(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            }
        } catch (Throwable th) {
            SLog.info(TAG, "saveTask CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
            throw th;
        }
    }
}
