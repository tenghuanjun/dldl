package com.cy.yyjia.zhe28.db;

import androidx.autofill.HintConstants;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class LoginAccountDatabase_Impl extends LoginAccountDatabase {
    private volatile LoginAccountDao _loginAccountDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: com.cy.yyjia.zhe28.db.LoginAccountDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `LoginAccount` (`username` TEXT NOT NULL, `password` TEXT NOT NULL, `time` INTEGER NOT NULL, PRIMARY KEY(`username`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4681eeabe39d25cd039406c5b035c56f')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `LoginAccount`");
                if (LoginAccountDatabase_Impl.this.mCallbacks != null) {
                    int size = LoginAccountDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) LoginAccountDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase _db) {
                if (LoginAccountDatabase_Impl.this.mCallbacks != null) {
                    int size = LoginAccountDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) LoginAccountDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                LoginAccountDatabase_Impl.this.mDatabase = _db;
                LoginAccountDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (LoginAccountDatabase_Impl.this.mCallbacks != null) {
                    int size = LoginAccountDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) LoginAccountDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(3);
                map.put(HintConstants.AUTOFILL_HINT_USERNAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_USERNAME, "TEXT", true, 1, null, 1));
                map.put(HintConstants.AUTOFILL_HINT_PASSWORD, new TableInfo.Column(HintConstants.AUTOFILL_HINT_PASSWORD, "TEXT", true, 0, null, 1));
                map.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("LoginAccount", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "LoginAccount");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "LoginAccount(com.cy.yyjia.zhe28.domain.LoginAccount).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "4681eeabe39d25cd039406c5b035c56f", "e95163de156835dd430b85becad3bb6a")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "LoginAccount");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `LoginAccount`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(LoginAccountDao.class, LoginAccountDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.cy.yyjia.zhe28.db.LoginAccountDatabase
    public LoginAccountDao LoginAccountDao() {
        LoginAccountDao loginAccountDao;
        if (this._loginAccountDao != null) {
            return this._loginAccountDao;
        }
        synchronized (this) {
            if (this._loginAccountDao == null) {
                this._loginAccountDao = new LoginAccountDao_Impl(this);
            }
            loginAccountDao = this._loginAccountDao;
        }
        return loginAccountDao;
    }
}
