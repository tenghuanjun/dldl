package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ShortType extends ShortObjectType {
    private static final ShortType singleTon = new ShortType();

    @Override // com.j256.ormlite.field.types.BaseDataType, com.j256.ormlite.field.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    public static ShortType getSingleton() {
        return singleTon;
    }

    private ShortType() {
        super(SqlType.SHORT, new Class[]{Short.TYPE});
    }

    protected ShortType(SqlType sqlType, Class<?>[] clsArr) {
        super(sqlType, clsArr);
    }
}
