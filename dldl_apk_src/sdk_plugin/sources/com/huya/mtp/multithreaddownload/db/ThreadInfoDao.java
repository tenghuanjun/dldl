package com.huya.mtp.multithreaddownload.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.duowan.kiwi.base.smile.SmileConst;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ThreadInfoDao extends AbstractDao<ThreadInfo> {
    private static final String TABLE_NAME = "ThreadInfoDao";

    public ThreadInfoDao(Context context) {
        super(context);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table ThreadInfoDao(_id integer primary key autoincrement, id integer, tag text, uri text, start long, end long, finished long)");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("drop table if exists ThreadInfoDao");
    }

    public void insert(ThreadInfo threadInfo) {
        getWritableDatabase().execSQL("insert into ThreadInfoDao(id, tag, uri, start, end, finished) values(?, ?, ?, ?, ?, ?)", new Object[]{Integer.valueOf(threadInfo.getId()), threadInfo.getTag(), threadInfo.getUri(), Long.valueOf(threadInfo.getStart()), Long.valueOf(threadInfo.getEnd()), Long.valueOf(threadInfo.getFinished())});
    }

    public void delete(String str) {
        getWritableDatabase().execSQL("delete from ThreadInfoDao where tag = ?", new Object[]{str});
    }

    public void update(String str, int i, long j) {
        getWritableDatabase().execSQL("update ThreadInfoDao set finished = ? where tag = ? and id = ? ", new Object[]{Long.valueOf(j), str, Integer.valueOf(i)});
    }

    public List<ThreadInfo> getThreadInfos(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("select * from ThreadInfoDao where tag = ?", new String[]{str});
        while (cursorRawQuery.moveToNext()) {
            ThreadInfo threadInfo = new ThreadInfo();
            threadInfo.setId(cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(SqTrackCommonKey.id)));
            threadInfo.setTag(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(SmileConst.KEY_ATTNAME)));
            threadInfo.setUri(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("uri")));
            threadInfo.setEnd(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("end")));
            threadInfo.setStart(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("start")));
            threadInfo.setFinished(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("finished")));
            arrayList.add(threadInfo);
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public boolean existsThread(String str, int i) {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("select * from ThreadInfoDao where tag = ? and id = ?", new String[]{str, i + ""});
        boolean zMoveToNext = cursorRawQuery.moveToNext();
        cursorRawQuery.close();
        return zMoveToNext;
    }

    public boolean existsTask(String str) {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("select * from ThreadInfoDao where tag = ?", new String[]{str});
        boolean z = cursorRawQuery != null && cursorRawQuery.getCount() > 0;
        cursorRawQuery.close();
        return z;
    }
}
