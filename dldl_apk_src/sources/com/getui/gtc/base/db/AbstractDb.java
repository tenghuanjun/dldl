package com.getui.gtc.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class AbstractDb {
    private SQLiteOpenHelper helper;
    private Map<Class<? extends AbstractTable>, AbstractTable> tables = new HashMap();
    private AtomicBoolean inited = new AtomicBoolean(false);

    private void initCache() {
        Iterator<Class<? extends AbstractTable>> it = this.tables.keySet().iterator();
        while (it.hasNext()) {
            this.tables.get(it.next()).initCache();
        }
    }

    public final void addTable(Class<? extends AbstractTable> cls) throws IllegalAccessException, InstantiationException {
        AbstractTable abstractTableNewInstance = this.tables.get(cls);
        if (abstractTableNewInstance == null) {
            abstractTableNewInstance = cls.newInstance();
            this.tables.put(cls, abstractTableNewInstance);
        }
        abstractTableNewInstance.setDb(this);
    }

    public final void addTables(List<Class<? extends AbstractTable>> list) throws IllegalAccessException, InstantiationException {
        Iterator<Class<? extends AbstractTable>> it = list.iterator();
        while (it.hasNext()) {
            addTable(it.next());
        }
    }

    public abstract String getDbName();

    public final SQLiteOpenHelper getHelper() {
        SQLiteOpenHelper sQLiteOpenHelper = this.helper;
        if (sQLiteOpenHelper != null) {
            return sQLiteOpenHelper;
        }
        throw new RuntimeException("db " + getDbName() + " has not been initialized");
    }

    public final <T extends AbstractTable> T getTable(Class<T> cls) {
        T t = (T) this.tables.get(cls);
        if (t != null) {
            return t;
        }
        throw new RuntimeException("table " + cls.getSimpleName() + " has not been added to db " + getDbName());
    }

    public final Collection<AbstractTable> getTables() {
        Collection<AbstractTable> collectionValues = this.tables.values();
        if (collectionValues.size() > 0) {
            return collectionValues;
        }
        throw new RuntimeException("no table has been added to db " + getDbName());
    }

    public abstract int getVersion();

    public void init(Context context) {
        if (this.inited.getAndSet(true)) {
            return;
        }
        this.helper = new SQLiteOpenHelper(context, getDbName(), null, getVersion()) { // from class: com.getui.gtc.base.db.AbstractDb.1
            @Override // android.database.sqlite.SQLiteOpenHelper
            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                sQLiteDatabase.beginTransaction();
                try {
                    Iterator it = AbstractDb.this.tables.keySet().iterator();
                    while (it.hasNext()) {
                        sQLiteDatabase.execSQL(((AbstractTable) AbstractDb.this.tables.get((Class) it.next())).createSql());
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            }

            @Override // android.database.sqlite.SQLiteOpenHelper
            public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                sQLiteDatabase.beginTransaction();
                try {
                    Iterator it = AbstractDb.this.tables.keySet().iterator();
                    while (it.hasNext()) {
                        ((AbstractTable) AbstractDb.this.tables.get((Class) it.next())).onDowngradle(sQLiteDatabase, i, i2);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            }

            @Override // android.database.sqlite.SQLiteOpenHelper
            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                sQLiteDatabase.beginTransaction();
                try {
                    Iterator it = AbstractDb.this.tables.keySet().iterator();
                    while (it.hasNext()) {
                        ((AbstractTable) AbstractDb.this.tables.get((Class) it.next())).onUpgrade(sQLiteDatabase, i, i2);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            }
        };
        initCache();
    }
}
