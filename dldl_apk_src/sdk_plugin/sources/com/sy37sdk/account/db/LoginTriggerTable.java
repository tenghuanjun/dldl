package com.sy37sdk.account.db;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface LoginTriggerTable {
    public static final String ID = "ID";
    public static final String LOGIN_TYPE = "login_type";
    public static final String SQL = "create table login_trigger (ID integer primary key autoincrement, uid text, uname text, token text, trigger_time integer, login_type text)";
    public static final String TABLE_NAME = "login_trigger";
    public static final String TOKEN = "token";
    public static final String TRIGGER_TIME = "trigger_time";
    public static final String UID = "uid";
    public static final String UNAME = "uname";
}
