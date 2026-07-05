package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MappedUpdate<T, ID> extends BaseMappedStatement<T, ID> {
    private final FieldType versionFieldType;
    private final int versionFieldTypeIndex;

    private MappedUpdate(TableInfo<T, ID> tableInfo, String str, FieldType[] fieldTypeArr, FieldType fieldType, int i) {
        super(tableInfo, str, fieldTypeArr);
        this.versionFieldType = fieldType;
        this.versionFieldTypeIndex = i;
    }

    public static <T, ID> MappedUpdate<T, ID> build(DatabaseType databaseType, TableInfo<T, ID> tableInfo) throws SQLException {
        FieldType idField = tableInfo.getIdField();
        if (idField == null) {
            throw new SQLException("Cannot update " + tableInfo.getDataClass() + " because it doesn't have an id field");
        }
        StringBuilder sb = new StringBuilder(64);
        appendTableName(databaseType, sb, "UPDATE ", tableInfo.getTableName());
        FieldType fieldType = null;
        int i = 0;
        int i2 = -1;
        for (FieldType fieldType2 : tableInfo.getFieldTypes()) {
            if (isFieldUpdatable(fieldType2, idField)) {
                if (fieldType2.isVersion()) {
                    i2 = i;
                    fieldType = fieldType2;
                }
                i++;
            }
        }
        boolean z = true;
        int i3 = i + 1;
        if (fieldType != null) {
            i3++;
        }
        FieldType[] fieldTypeArr = new FieldType[i3];
        int i4 = 0;
        for (FieldType fieldType3 : tableInfo.getFieldTypes()) {
            if (isFieldUpdatable(fieldType3, idField)) {
                if (z) {
                    sb.append("SET ");
                    z = false;
                } else {
                    sb.append(", ");
                }
                appendFieldColumnName(databaseType, sb, fieldType3, null);
                fieldTypeArr[i4] = fieldType3;
                sb.append("= ?");
                i4++;
            }
        }
        sb.append(' ');
        appendWhereFieldEq(databaseType, idField, sb, null);
        int i5 = i4 + 1;
        fieldTypeArr[i4] = idField;
        if (fieldType != null) {
            sb.append(" AND ");
            appendFieldColumnName(databaseType, sb, fieldType, null);
            sb.append("= ?");
            fieldTypeArr[i5] = fieldType;
        }
        return new MappedUpdate<>(tableInfo, sb.toString(), fieldTypeArr, fieldType, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int update(DatabaseConnection databaseConnection, T t, ObjectCache objectCache) throws SQLException {
        Object objMoveToNextValue;
        try {
            if (this.argFieldTypes.length <= 1) {
                return 0;
            }
            Object[] fieldObjects = getFieldObjects(t);
            if (this.versionFieldType != null) {
                objMoveToNextValue = this.versionFieldType.moveToNextValue(this.versionFieldType.extractJavaFieldValue(t));
                fieldObjects[this.versionFieldTypeIndex] = this.versionFieldType.convertJavaFieldToSqlArgValue(objMoveToNextValue);
            } else {
                objMoveToNextValue = null;
            }
            int iUpdate = databaseConnection.update(this.statement, fieldObjects, this.argFieldTypes);
            if (iUpdate > 0) {
                if (objMoveToNextValue != null) {
                    this.versionFieldType.assignField(t, objMoveToNextValue, false, null);
                }
                if (objectCache != 0) {
                    Object obj = objectCache.get(this.clazz, this.idField.extractJavaFieldValue(t));
                    if (obj != null && obj != t) {
                        for (FieldType fieldType : this.tableInfo.getFieldTypes()) {
                            if (fieldType != this.idField) {
                                fieldType.assignField(obj, fieldType.extractJavaFieldValue(t), false, objectCache);
                            }
                        }
                    }
                }
            }
            logger.debug("update data with statement '{}' and {} args, changed {} rows", this.statement, Integer.valueOf(fieldObjects.length), Integer.valueOf(iUpdate));
            if (fieldObjects.length > 0) {
                logger.trace("update arguments: {}", (Object) fieldObjects);
            }
            return iUpdate;
        } catch (SQLException e) {
            throw SqlExceptionUtil.create("Unable to run update stmt on object " + t + ": " + this.statement, e);
        }
    }

    private static boolean isFieldUpdatable(FieldType fieldType, FieldType fieldType2) {
        return (fieldType == fieldType2 || fieldType.isForeignCollection()) ? false : true;
    }
}
