package com.sy37sdk.account.db;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginTriggerDBManager extends DBManager {
    private static LoginTriggerDBManager dbManager;

    private LoginTriggerDBManager() {
    }

    public static LoginTriggerDBManager getInstance() {
        if (dbManager == null) {
            synchronized (LoginTriggerDBManager.class) {
                if (dbManager == null) {
                    dbManager = new LoginTriggerDBManager();
                }
            }
        }
        return dbManager;
    }

    public void insertLoginTrigger(LoginTrigger loginTrigger) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", loginTrigger.getUid());
        contentValues.put("uname", loginTrigger.getUname());
        contentValues.put("token", loginTrigger.getToken());
        contentValues.put(LoginTriggerTable.TRIGGER_TIME, Long.valueOf(loginTrigger.getTriggerTime()));
        contentValues.put("login_type", loginTrigger.getLoginType());
        getDb().insert(LoginTriggerTable.TABLE_NAME, null, contentValues);
    }

    public List<LoginTrigger> query() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = getDb().rawQuery("select * from login_trigger", null);
        if (cursorRawQuery != null) {
            while (cursorRawQuery.moveToNext()) {
                LoginTrigger loginTrigger = new LoginTrigger();
                loginTrigger.setId(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(LoginTriggerTable.ID)));
                loginTrigger.setUid(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("uid")));
                loginTrigger.setUname(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("uname")));
                loginTrigger.setToken(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("token")));
                loginTrigger.setTriggerTime(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(LoginTriggerTable.TRIGGER_TIME)));
                loginTrigger.setLoginType(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("login_type")));
                arrayList.add(loginTrigger);
            }
            cursorRawQuery.close();
        }
        return arrayList;
    }

    public void delete(String str) {
        getDb().delete(LoginTriggerTable.TABLE_NAME, "ID = ?", new String[]{str + ""});
    }

    public void delete(List<LoginTrigger> list) {
        for (LoginTrigger loginTrigger : list) {
            getDb().delete(LoginTriggerTable.TABLE_NAME, "ID = ?", new String[]{loginTrigger.getId() + ""});
        }
    }
}
