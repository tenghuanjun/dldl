package com.j256.ormlite.table;

import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.field.DatabaseFieldConfigLoader;
import com.j256.ormlite.misc.SqlExceptionUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DatabaseTableConfigLoader {
    private static final String CONFIG_FILE_END_MARKER = "# --table-end--";
    private static final String CONFIG_FILE_FIELDS_END = "# --table-fields-end--";
    private static final String CONFIG_FILE_FIELDS_START = "# --table-fields-start--";
    private static final String CONFIG_FILE_START_MARKER = "# --table-start--";
    private static final String FIELD_NAME_DATA_CLASS = "dataClass";
    private static final String FIELD_NAME_TABLE_NAME = "tableName";

    public static List<DatabaseTableConfig<?>> loadDatabaseConfigFromReader(BufferedReader bufferedReader) throws SQLException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            DatabaseTableConfig databaseTableConfigFromReader = fromReader(bufferedReader);
            if (databaseTableConfigFromReader == null) {
                return arrayList;
            }
            arrayList.add(databaseTableConfigFromReader);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r3 == false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.j256.ormlite.table.DatabaseTableConfig<T> fromReader(java.io.BufferedReader r7) throws java.sql.SQLException {
        /*
            com.j256.ormlite.table.DatabaseTableConfig r0 = new com.j256.ormlite.table.DatabaseTableConfig
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
        L8:
            java.lang.String r4 = r7.readLine()     // Catch: java.io.IOException -> L6a
            if (r4 != 0) goto Lf
            goto L17
        Lf:
            java.lang.String r5 = "# --table-end--"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L1c
        L17:
            if (r3 == 0) goto L1a
            return r0
        L1a:
            r7 = 0
            return r7
        L1c:
            java.lang.String r5 = "# --table-fields-start--"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L28
            readFields(r7, r0)
            goto L8
        L28:
            int r5 = r4.length()
            if (r5 == 0) goto L8
            java.lang.String r5 = "#"
            boolean r5 = r4.startsWith(r5)
            if (r5 != 0) goto L8
            java.lang.String r5 = "# --table-start--"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L3f
            goto L8
        L3f:
            r3 = -2
            java.lang.String r5 = "="
            java.lang.String[] r3 = r4.split(r5, r3)
            int r5 = r3.length
            r6 = 2
            if (r5 != r6) goto L53
            r4 = r3[r2]
            r3 = r3[r1]
            readTableField(r0, r4, r3)
            r3 = 1
            goto L8
        L53:
            java.sql.SQLException r7 = new java.sql.SQLException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "DatabaseTableConfig reading from stream cannot parse line: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L6a:
            r7 = move-exception
            java.lang.String r0 = "Could not read DatabaseTableConfig from stream"
            java.sql.SQLException r7 = com.j256.ormlite.misc.SqlExceptionUtil.create(r0, r7)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.j256.ormlite.table.DatabaseTableConfigLoader.fromReader(java.io.BufferedReader):com.j256.ormlite.table.DatabaseTableConfig");
    }

    public static <T> void write(BufferedWriter bufferedWriter, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        try {
            writeConfig(bufferedWriter, databaseTableConfig);
        } catch (IOException e) {
            throw SqlExceptionUtil.create("Could not write config to writer", e);
        }
    }

    private static <T> void writeConfig(BufferedWriter bufferedWriter, DatabaseTableConfig<T> databaseTableConfig) throws SQLException, IOException {
        bufferedWriter.append(CONFIG_FILE_START_MARKER);
        bufferedWriter.newLine();
        if (databaseTableConfig.getDataClass() != null) {
            bufferedWriter.append(FIELD_NAME_DATA_CLASS).append('=').append((CharSequence) databaseTableConfig.getDataClass().getName());
            bufferedWriter.newLine();
        }
        if (databaseTableConfig.getTableName() != null) {
            bufferedWriter.append(FIELD_NAME_TABLE_NAME).append('=').append((CharSequence) databaseTableConfig.getTableName());
            bufferedWriter.newLine();
        }
        bufferedWriter.append(CONFIG_FILE_FIELDS_START);
        bufferedWriter.newLine();
        if (databaseTableConfig.getFieldConfigs() != null) {
            Iterator<DatabaseFieldConfig> it = databaseTableConfig.getFieldConfigs().iterator();
            while (it.hasNext()) {
                DatabaseFieldConfigLoader.write(bufferedWriter, it.next(), databaseTableConfig.getTableName());
            }
        }
        bufferedWriter.append(CONFIG_FILE_FIELDS_END);
        bufferedWriter.newLine();
        bufferedWriter.append(CONFIG_FILE_END_MARKER);
        bufferedWriter.newLine();
    }

    private static <T> void readTableField(DatabaseTableConfig<T> databaseTableConfig, String str, String str2) {
        if (str.equals(FIELD_NAME_DATA_CLASS)) {
            try {
                databaseTableConfig.setDataClass(Class.forName(str2));
                return;
            } catch (ClassNotFoundException unused) {
                throw new IllegalArgumentException("Unknown class specified for dataClass: " + str2);
            }
        }
        if (str.equals(FIELD_NAME_TABLE_NAME)) {
            databaseTableConfig.setTableName(str2);
        }
    }

    private static <T> void readFields(BufferedReader bufferedReader, DatabaseTableConfig<T> databaseTableConfig) throws SQLException {
        DatabaseFieldConfig databaseFieldConfigFromReader;
        ArrayList arrayList = new ArrayList();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null || line.equals(CONFIG_FILE_FIELDS_END) || (databaseFieldConfigFromReader = DatabaseFieldConfigLoader.fromReader(bufferedReader)) == null) {
                    break;
                } else {
                    arrayList.add(databaseFieldConfigFromReader);
                }
            } catch (IOException e) {
                throw SqlExceptionUtil.create("Could not read next field from config file", e);
            }
        }
        databaseTableConfig.setFieldConfigs(arrayList);
    }
}
