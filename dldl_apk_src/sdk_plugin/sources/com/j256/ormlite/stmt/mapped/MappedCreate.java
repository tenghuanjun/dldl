package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MappedCreate<T, ID> extends BaseMappedStatement<T, ID> {
    private String dataClassName;
    private final String queryNextSequenceStmt;
    private int versionFieldTypeIndex;

    private MappedCreate(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, String str2, int i) {
        super(tableInfo, str, fieldTypeArr);
        this.dataClassName = tableInfo.getDataClass().getSimpleName();
        this.queryNextSequenceStmt = str2;
        this.versionFieldTypeIndex = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int insert(DatabaseType databaseType, DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        KeyHolder keyHolder;
        Object objMoveToNextValue;
        Object objExtractRawJavaFieldValue;
        if (this.idField == null) {
            keyHolder = null;
        } else {
            boolean z = !this.idField.isAllowGeneratedIdInsert() || this.idField.isObjectsFieldValueDefault(t);
            if (this.idField.isSelfGeneratedId() && this.idField.isGeneratedId()) {
                if (z) {
                    this.idField.assignField(t, this.idField.generateId(), false, objectCache);
                }
            } else if (this.idField.isGeneratedIdSequence() && databaseType.isSelectSequenceBeforeInsert()) {
                if (z) {
                    assignSequenceId(databaseConnection, t, objectCache);
                }
            } else if (this.idField.isGeneratedId() && z) {
                keyHolder = new KeyHolder();
            }
            keyHolder = null;
        }
        try {
            if (this.tableInfo.isForeignAutoCreate()) {
                for (FieldType fieldType : this.tableInfo.getFieldTypes()) {
                    if (fieldType.isForeignAutoCreate() && (objExtractRawJavaFieldValue = fieldType.extractRawJavaFieldValue(t)) != null && fieldType.getForeignIdField().isObjectsFieldValueDefault(objExtractRawJavaFieldValue)) {
                        fieldType.createWithForeignDao(objExtractRawJavaFieldValue);
                    }
                }
            }
            Object[] fieldObjects = getFieldObjects(t);
            if (this.versionFieldTypeIndex < 0 || fieldObjects[this.versionFieldTypeIndex] != null) {
                objMoveToNextValue = null;
            } else {
                FieldType fieldType2 = this.argFieldTypes[this.versionFieldTypeIndex];
                objMoveToNextValue = fieldType2.moveToNextValue(null);
                fieldObjects[this.versionFieldTypeIndex] = fieldType2.convertJavaFieldToSqlArgValue(objMoveToNextValue);
            }
            int iInsert = databaseConnection.insert(this.statement, fieldObjects, this.argFieldTypes, keyHolder);
            logger.debug("insert data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(iInsert));
            if (fieldObjects.length > 0) {
                logger.trace("insert arguments: {}", (Object) fieldObjects);
            }
            if (iInsert > 0) {
                if (objMoveToNextValue != null) {
                    this.argFieldTypes[this.versionFieldTypeIndex].assignField(t, objMoveToNextValue, false, null);
                }
                if (keyHolder != null) {
                    Number key = keyHolder.getKey();
                    if (key == null) {
                        throw new SQLException("generated-id key was not set by the update call");
                    }
                    if (key.longValue() == 0) {
                        throw new SQLException("generated-id key must not be 0 value");
                    }
                    assignIdValue(t, key, "keyholder", objectCache);
                }
                if (objectCache != 0 && foreignCollectionsAreAssigned(this.tableInfo.getForeignCollections(), t)) {
                    objectCache.put(this.clazz, this.idField.extractJavaFieldValue(t), t);
                }
            }
            return iInsert;
        } catch (SQLException e) {
            throw SqlExceptionUtil.create("Unable to run insert stmt on object " + t + ": " + this.statement, e);
        }
    }

    public static <T, ID> MappedCreate<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo) {
        StringBuilder sb = new StringBuilder(128);
        appendTableName(databaseType, sb, "INSERT INTO ", tableInfo.getTableName());
        sb.append('(');
        int i = 0;
        int i2 = -1;
        for (FieldType fieldType : tableInfo.getFieldTypes()) {
            if (isFieldCreatable(databaseType, fieldType)) {
                if (fieldType.isVersion()) {
                    i2 = i;
                }
                i++;
            }
        }
        FieldType[] fieldTypeArr = new FieldType[i];
        boolean z = true;
        boolean z2 = true;
        int i3 = 0;
        for (FieldType fieldType2 : tableInfo.getFieldTypes()) {
            if (isFieldCreatable(databaseType, fieldType2)) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(",");
                }
                appendFieldColumnName(databaseType, sb, fieldType2, null);
                fieldTypeArr[i3] = fieldType2;
                i3++;
            }
        }
        sb.append(") VALUES (");
        for (FieldType fieldType3 : tableInfo.getFieldTypes()) {
            if (isFieldCreatable(databaseType, fieldType3)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append("?");
            }
        }
        sb.append(")");
        return new MappedCreate<>(tableInfo, sb.toString(), fieldTypeArr, buildQueryNextSequence(databaseType, tableInfo.getIdField()), i2);
    }

    private boolean foreignCollectionsAreAssigned(FieldType[] fieldTypeArr, Object obj) throws SQLException {
        for (FieldType fieldType : fieldTypeArr) {
            if (fieldType.extractJavaFieldValue(obj) == null) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFieldCreatable(DatabaseType databaseType, FieldType fieldType) {
        if (fieldType.isForeignCollection()) {
            return false;
        }
        return (databaseType.isIdSequenceNeeded() && databaseType.isSelectSequenceBeforeInsert()) || !fieldType.isGeneratedId() || fieldType.isSelfGeneratedId() || fieldType.isAllowGeneratedIdInsert();
    }

    private static String buildQueryNextSequence(DatabaseType databaseType, FieldType fieldType) {
        String generatedIdSequence;
        if (fieldType == null || (generatedIdSequence = fieldType.getGeneratedIdSequence()) == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(64);
        databaseType.appendSelectNextValFromSequence(sb, generatedIdSequence);
        return sb.toString();
    }

    private void assignSequenceId(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        long jQueryForLong = databaseConnection.queryForLong(this.queryNextSequenceStmt);
        logger.debug("queried for sequence {} using stmt: {}", Long.valueOf(jQueryForLong), this.queryNextSequenceStmt);
        if (jQueryForLong == 0) {
            throw new SQLException("Should not have returned 0 for stmt: " + this.queryNextSequenceStmt);
        }
        assignIdValue(t, Long.valueOf(jQueryForLong), "sequence", objectCache);
    }

    private void assignIdValue(T t, Number number, String str, ObjectCache objectCache) throws SQLException {
        this.idField.assignIdValue(t, number, objectCache);
        if (logger.isLevelEnabled(Log.Level.DEBUG)) {
            logger.debug("assigned id '{}' from {} to '{}' in {} object", new Object[]{number, str, this.idField.getFieldName(), this.dataClassName});
        }
    }

    private static class KeyHolder implements GeneratedKeyHolder {
        Number key;

        private KeyHolder() {
        }

        public Number getKey() {
            return this.key;
        }

        @Override // com.j256.ormlite.support.GeneratedKeyHolder
        public void addKey(Number number) throws SQLException {
            if (this.key == null) {
                this.key = number;
                return;
            }
            throw new SQLException("generated key has already been set to " + this.key + ", now set to " + number);
        }
    }
}
