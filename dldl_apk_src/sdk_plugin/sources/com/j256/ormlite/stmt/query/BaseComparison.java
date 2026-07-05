package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.sql.SQLException;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
abstract class BaseComparison implements Comparison {
    private static final String NUMBER_CHARACTERS = "0123456789.-+";
    protected final String columnName;
    protected final FieldType fieldType;
    private final Object value;

    @Override // com.j256.ormlite.stmt.query.Comparison
    public abstract void appendOperation(StringBuilder sb);

    protected BaseComparison(String str, FieldType fieldType, Object obj, boolean z) throws SQLException {
        if (z && fieldType != null && !fieldType.isComparable()) {
            throw new SQLException("Field '" + str + "' is of data type " + fieldType.getDataPersister() + " which can not be compared");
        }
        this.columnName = str;
        this.fieldType = fieldType;
        this.value = obj;
    }

    @Override // com.j256.ormlite.stmt.query.Clause
    public void appendSql(DatabaseType databaseType, String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        if (str != null) {
            databaseType.appendEscapedEntityName(sb, str);
            sb.append('.');
        }
        databaseType.appendEscapedEntityName(sb, this.columnName);
        sb.append(' ');
        appendOperation(sb);
        appendValue(databaseType, sb, list);
    }

    @Override // com.j256.ormlite.stmt.query.Comparison
    public String getColumnName() {
        return this.columnName;
    }

    @Override // com.j256.ormlite.stmt.query.Comparison
    public void appendValue(DatabaseType databaseType, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        appendArgOrValue(databaseType, this.fieldType, sb, list, this.value);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void appendArgOrValue(com.j256.ormlite.db.DatabaseType r9, com.j256.ormlite.field.FieldType r10, java.lang.StringBuilder r11, java.util.List<com.j256.ormlite.stmt.ArgumentHolder> r12, java.lang.Object r13) throws java.sql.SQLException {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.stmt.query.BaseComparison.appendArgOrValue(com.j256.ormlite.db.DatabaseType, com.j256.ormlite.field.FieldType, java.lang.StringBuilder, java.util.List, java.lang.Object):void");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.columnName);
        sb.append(' ');
        appendOperation(sb);
        sb.append(' ');
        sb.append(this.value);
        return sb.toString();
    }
}
