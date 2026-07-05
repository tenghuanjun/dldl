package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseMappedQuery<T, ID> extends BaseMappedStatement<T, ID> implements GenericRowMapper<T> {
    private Map<String, Integer> columnPositions;
    private Object parent;
    private Object parentId;
    protected final FieldType[] resultsFieldTypes;

    protected BaseMappedQuery(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, FieldType[] fieldTypeArr2) {
        super(tableInfo, str, fieldTypeArr);
        this.columnPositions = null;
        this.parent = null;
        this.parentId = null;
        this.resultsFieldTypes = fieldTypeArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.j256.ormlite.stmt.GenericRowMapper
    public T mapRow(DatabaseResults databaseResults) throws SQLException {
        Object objBuildForeignCollection;
        Map<String, Integer> map = this.columnPositions;
        if (map == null) {
            map = new HashMap<>();
        }
        ObjectCache objectCache = databaseResults.getObjectCache();
        if (objectCache != 0) {
            T t = (T) objectCache.get(this.clazz, this.idField.resultToJava(databaseResults, map));
            if (t != null) {
                return t;
            }
        }
        T tCreateObject = this.tableInfo.createObject();
        Object obj = null;
        boolean z = false;
        for (FieldType fieldType : this.resultsFieldTypes) {
            if (fieldType.isForeignCollection()) {
                z = true;
            } else {
                Object objResultToJava = fieldType.resultToJava(databaseResults, map);
                if (objResultToJava != null && this.parent != null && fieldType.getField().getType() == this.parent.getClass() && objResultToJava.equals(this.parentId)) {
                    fieldType.assignField(tCreateObject, this.parent, true, objectCache);
                } else {
                    fieldType.assignField(tCreateObject, objResultToJava, false, objectCache);
                }
                if (fieldType == this.idField) {
                    obj = objResultToJava;
                }
            }
        }
        if (z) {
            for (FieldType fieldType2 : this.resultsFieldTypes) {
                if (fieldType2.isForeignCollection() && (objBuildForeignCollection = fieldType2.buildForeignCollection(tCreateObject, obj)) != null) {
                    fieldType2.assignField(tCreateObject, objBuildForeignCollection, false, objectCache);
                }
            }
        }
        if (objectCache != 0 && obj != null) {
            objectCache.put(this.clazz, obj, tCreateObject);
        }
        if (this.columnPositions == null) {
            this.columnPositions = map;
        }
        return tCreateObject;
    }

    public void setParentInformation(Object obj, Object obj2) {
        this.parent = obj;
        this.parentId = obj2;
    }
}
