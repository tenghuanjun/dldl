package com.cy.yyjia.zhe28.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cy.yyjia.zhe28.domain.SearchHistory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class SearchHistoryDao_Impl implements SearchHistoryDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SearchHistory> __insertionAdapterOfSearchHistory;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final EntityDeletionOrUpdateAdapter<SearchHistory> __updateAdapterOfSearchHistory;

    public SearchHistoryDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSearchHistory = new EntityInsertionAdapter<SearchHistory>(__db) { // from class: com.cy.yyjia.zhe28.db.SearchHistoryDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `SearchHistory` (`text`,`time`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SearchHistory value) {
                if (value.getText() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getText());
                }
                stmt.bindLong(2, value.getTime());
            }
        };
        this.__updateAdapterOfSearchHistory = new EntityDeletionOrUpdateAdapter<SearchHistory>(__db) { // from class: com.cy.yyjia.zhe28.db.SearchHistoryDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `SearchHistory` SET `text` = ?,`time` = ? WHERE `text` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SearchHistory value) {
                if (value.getText() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getText());
                }
                stmt.bindLong(2, value.getTime());
                if (value.getText() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getText());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.cy.yyjia.zhe28.db.SearchHistoryDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM SearchHistory";
            }
        };
    }

    @Override // com.cy.yyjia.zhe28.db.SearchHistoryDao
    public void add(final SearchHistory... data) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSearchHistory.insert(data);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.SearchHistoryDao
    public void updateData(final SearchHistory... data) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSearchHistory.handleMultiple(data);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.SearchHistoryDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.cy.yyjia.zhe28.db.SearchHistoryDao
    public List<SearchHistory> getAll() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SearchHistory order by time desc limit 10", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "text");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchHistory(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.SearchHistoryDao
    public SearchHistory check(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from SearchHistory where text =  ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SearchHistory searchHistory = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "text");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            if (cursorQuery.moveToFirst()) {
                if (!cursorQuery.isNull(columnIndexOrThrow)) {
                    string = cursorQuery.getString(columnIndexOrThrow);
                }
                searchHistory = new SearchHistory(string, cursorQuery.getLong(columnIndexOrThrow2));
            }
            return searchHistory;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
