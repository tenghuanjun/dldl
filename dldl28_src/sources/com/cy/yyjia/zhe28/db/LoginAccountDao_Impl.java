package com.cy.yyjia.zhe28.db;

import android.database.Cursor;
import androidx.autofill.HintConstants;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cy.yyjia.zhe28.domain.LoginAccount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class LoginAccountDao_Impl implements LoginAccountDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<LoginAccount> __deletionAdapterOfLoginAccount;
    private final EntityInsertionAdapter<LoginAccount> __insertionAdapterOfLoginAccount;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final EntityDeletionOrUpdateAdapter<LoginAccount> __updateAdapterOfLoginAccount;

    public LoginAccountDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfLoginAccount = new EntityInsertionAdapter<LoginAccount>(__db) { // from class: com.cy.yyjia.zhe28.db.LoginAccountDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `LoginAccount` (`username`,`password`,`time`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, LoginAccount value) {
                if (value.getUsername() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getUsername());
                }
                if (value.getPassword() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPassword());
                }
                stmt.bindLong(3, value.getTime());
            }
        };
        this.__deletionAdapterOfLoginAccount = new EntityDeletionOrUpdateAdapter<LoginAccount>(__db) { // from class: com.cy.yyjia.zhe28.db.LoginAccountDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `LoginAccount` WHERE `username` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, LoginAccount value) {
                if (value.getUsername() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getUsername());
                }
            }
        };
        this.__updateAdapterOfLoginAccount = new EntityDeletionOrUpdateAdapter<LoginAccount>(__db) { // from class: com.cy.yyjia.zhe28.db.LoginAccountDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `LoginAccount` SET `username` = ?,`password` = ?,`time` = ? WHERE `username` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, LoginAccount value) {
                if (value.getUsername() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getUsername());
                }
                if (value.getPassword() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPassword());
                }
                stmt.bindLong(3, value.getTime());
                if (value.getUsername() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getUsername());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.cy.yyjia.zhe28.db.LoginAccountDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LoginAccount";
            }
        };
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
    public void add(final LoginAccount... data) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLoginAccount.insert(data);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
    public void delete(final LoginAccount account) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfLoginAccount.handle(account);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
    public void update(final LoginAccount... data) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfLoginAccount.handleMultiple(data);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
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

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
    public LoginAccount getRecent() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM LoginAccount order by time desc limit 1", 0);
        this.__db.assertNotSuspendingTransaction();
        LoginAccount loginAccount = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, HintConstants.AUTOFILL_HINT_USERNAME);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, HintConstants.AUTOFILL_HINT_PASSWORD);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                if (!cursorQuery.isNull(columnIndexOrThrow2)) {
                    string = cursorQuery.getString(columnIndexOrThrow2);
                }
                loginAccount = new LoginAccount(string2, string, cursorQuery.getLong(columnIndexOrThrow3));
            }
            return loginAccount;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
    public List<LoginAccount> getAll() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM LoginAccount order by time desc", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, HintConstants.AUTOFILL_HINT_USERNAME);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, HintConstants.AUTOFILL_HINT_PASSWORD);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new LoginAccount(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDao
    public LoginAccount check(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from LoginAccount where username =  ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        LoginAccount loginAccount = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, HintConstants.AUTOFILL_HINT_USERNAME);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, HintConstants.AUTOFILL_HINT_PASSWORD);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                if (!cursorQuery.isNull(columnIndexOrThrow2)) {
                    string = cursorQuery.getString(columnIndexOrThrow2);
                }
                loginAccount = new LoginAccount(string2, string, cursorQuery.getLong(columnIndexOrThrow3));
            }
            return loginAccount;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
