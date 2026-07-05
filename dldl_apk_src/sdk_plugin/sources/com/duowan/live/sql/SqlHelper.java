package com.duowan.live.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import com.duowan.auk.util.L;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SqlHelper extends OrmLiteSqliteOpenHelper {
    static final String DATABASE_NAME = "ark";
    static final int DATABASE_VERSION = 1;
    private static SqlHelper msInstance;

    public interface OnQueryListener<T> {
        void onQueryResult(List<T> list);
    }

    public static <T> void asyncCreateOrUpdate(Context context, T... tArr) {
        if (tArr.length == 0) {
            return;
        }
        new AsyncTask<T, Void, Void>() { // from class: com.duowan.live.sql.SqlHelper.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(T... tArr2) {
                SqlHelper.createOrUpdate(SqlHelper.this, tArr2);
                return null;
            }
        }.execute(tArr);
    }

    public static <T> void asyncDelete(Context context, T... tArr) {
        if (tArr.length == 0) {
            return;
        }
        new AsyncTask<T, Void, Void>() { // from class: com.duowan.live.sql.SqlHelper.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(T... tArr2) {
                SqlHelper.delete(SqlHelper.this, tArr2);
                return null;
            }
        }.execute(tArr);
    }

    public static <T, ID> void asyncQueryForIds(Context context, final Class<T> cls, final OnQueryListener<T> onQueryListener, ID... idArr) {
        new AsyncTask<ID, Void, List<T>>() { // from class: com.duowan.live.sql.SqlHelper.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<T> doInBackground(ID... idArr2) {
                return SqlHelper.queryForIds(SqlHelper.this, cls, idArr2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<T> list) {
                onQueryListener.onQueryResult(list);
            }
        }.execute(idArr);
    }

    public static <T> void asyncQueryForAll(Context context, final Class<T> cls, final OnQueryListener<T> onQueryListener) {
        new AsyncTask<Void, Void, List<T>>() { // from class: com.duowan.live.sql.SqlHelper.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<T> doInBackground(Void... voidArr) {
                return SqlHelper.queryForAll(SqlHelper.this, cls);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<T> list) {
                onQueryListener.onQueryResult(list);
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.duowan.live.sql.SqlHelper$5] */
    public static <T> void asyncDeleteAll(Context context, final Class<T> cls) {
        new AsyncTask<Void, Void, Void>() { // from class: com.duowan.live.sql.SqlHelper.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                SqlHelper.deleteAll(SqlHelper.this, cls);
                return null;
            }
        }.execute(new Void[0]);
    }

    public static <T> void syncCreateOrUpdate(Context context, T... tArr) {
        if (tArr.length == 0) {
            return;
        }
        createOrUpdate(getHelper(context), tArr);
    }

    public static <T, ID> T syncQueryForId(Context context, Class<T> cls, ID id) {
        return (T) queryForId(getHelper(context), cls, id);
    }

    public static <T, ID> List<T> syncQueryForIds(Context context, Class<T> cls, ID... idArr) {
        return queryForIds(getHelper(context), cls, idArr);
    }

    public static <T> List<T> syncQueryForAll(Context context, Class<T> cls) {
        return queryForAll(getHelper(context), cls);
    }

    public static <T> void syncDelete(Context context, T... tArr) {
        if (tArr.length == 0) {
            return;
        }
        delete(getHelper(context), tArr);
    }

    public static <T> void syncDeleteAll(Context context, Class<T> cls) {
        deleteAll(getHelper(context), cls);
    }

    public static <T> Dao<T, ?> dao(Context context, Class<T> cls) throws SQLException {
        SqlHelper helper = getHelper(context);
        Dao<T, ?> dao = helper.getDao(cls);
        TableUtils.createTableIfNotExists(helper.getConnectionSource(), cls);
        return dao;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        L.info(this, "onCreate");
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        L.info(this, "onUpgrade");
    }

    private static synchronized SqlHelper getHelper(Context context) {
        if (msInstance == null) {
            msInstance = new SqlHelper(context.getApplicationContext());
        }
        return msInstance;
    }

    private SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void createOrUpdate(SqlHelper sqlHelper, T... tArr) {
        try {
            Class<?> cls = tArr[0].getClass();
            Dao dao = sqlHelper.getDao(cls);
            TableUtils.createTableIfNotExists(sqlHelper.getConnectionSource(), cls);
            for (T t : tArr) {
                dao.createOrUpdate(t);
            }
        } catch (Throwable th) {
            L.error(sqlHelper, "Database create or update exception : %s", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void delete(SqlHelper sqlHelper, T... tArr) {
        try {
            Class<?> cls = tArr[0].getClass();
            Dao dao = sqlHelper.getDao(cls);
            TableUtils.createTableIfNotExists(sqlHelper.getConnectionSource(), cls);
            for (T t : tArr) {
                dao.delete(t);
            }
        } catch (Throwable th) {
            L.error(sqlHelper, "Database delete exception : %s", th);
        }
    }

    private static <T, ID> T queryForId(SqlHelper sqlHelper, Class<T> cls, ID id) {
        try {
            Dao dao = sqlHelper.getDao(cls);
            TableUtils.createTableIfNotExists(sqlHelper.getConnectionSource(), cls);
            return (T) dao.queryForId(id);
        } catch (Throwable th) {
            L.error(sqlHelper, "Database query exception : %s", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T, ID> List<T> queryForIds(SqlHelper sqlHelper, Class<T> cls, ID... idArr) {
        try {
            Dao dao = sqlHelper.getDao(cls);
            TableUtils.createTableIfNotExists(sqlHelper.getConnectionSource(), cls);
            ArrayList arrayList = new ArrayList();
            for (ID id : idArr) {
                arrayList.add(dao.queryForId(id));
            }
            return arrayList;
        } catch (Throwable th) {
            L.error(sqlHelper, "Database query exception : %s", th);
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> queryForAll(SqlHelper sqlHelper, Class<T> cls) {
        try {
            Dao dao = sqlHelper.getDao(cls);
            TableUtils.createTableIfNotExists(sqlHelper.getConnectionSource(), cls);
            return dao.queryForAll();
        } catch (Throwable th) {
            L.error(sqlHelper, "Database query exception : %s", th);
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void deleteAll(SqlHelper sqlHelper, Class<T> cls) {
        Iterator it = queryForAll(sqlHelper, cls).iterator();
        while (it.hasNext()) {
            delete(sqlHelper, it.next());
        }
    }
}
