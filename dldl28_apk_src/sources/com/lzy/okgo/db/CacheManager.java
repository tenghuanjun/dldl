package com.lzy.okgo.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.cache.CacheEntity;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CacheManager extends BaseDao<CacheEntity<?>> {
    @Override // com.lzy.okgo.db.BaseDao
    public void unInit() {
    }

    public static CacheManager getInstance() {
        return CacheManagerHolder.instance;
    }

    private static class CacheManagerHolder {
        private static final CacheManager instance = new CacheManager();

        private CacheManagerHolder() {
        }
    }

    private CacheManager() {
        super(new DBHelper());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lzy.okgo.db.BaseDao
    public CacheEntity<?> parseCursorToBean(Cursor cursor) {
        return CacheEntity.parseCursorToBean(cursor);
    }

    @Override // com.lzy.okgo.db.BaseDao
    public ContentValues getContentValues(CacheEntity<?> cacheEntity) {
        return CacheEntity.getContentValues(cacheEntity);
    }

    @Override // com.lzy.okgo.db.BaseDao
    public String getTableName() {
        return "cache";
    }

    public CacheEntity<?> get(String str) {
        if (str == null) {
            return null;
        }
        List<CacheEntity<?>> listQuery = query("key=?", new String[]{str});
        if (listQuery.size() > 0) {
            return listQuery.get(0);
        }
        return null;
    }

    public boolean remove(String str) {
        if (str == null) {
            return false;
        }
        return delete("key=?", new String[]{str});
    }

    public <T> CacheEntity<T> get(String str, Class<T> cls) {
        return (CacheEntity<T>) get(str);
    }

    public List<CacheEntity<?>> getAll() {
        return queryAll();
    }

    public <T> CacheEntity<T> replace(String str, CacheEntity<T> cacheEntity) {
        cacheEntity.setKey(str);
        replace(cacheEntity);
        return cacheEntity;
    }

    public boolean clear() {
        return deleteAll();
    }
}
