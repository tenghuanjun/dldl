package com.volcengine.h;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: classes3.dex */
public class c extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Context f1141a;
    protected final ContentResolver b;

    protected c(Context context, Handler.Callback callback) {
        super(callback);
        this.f1141a = context;
        this.b = context.getContentResolver();
    }

    protected Uri a(Context context) {
        return new Uri.Builder().authority(context.getPackageName()).path("multiprocess").scheme(DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT).build();
    }

    public boolean a() {
        boolean z = false;
        try {
            Cursor cursorQuery = this.b.query(a(this.f1141a), null, null, null, null);
            if (cursorQuery != null) {
                Bundle extras = cursorQuery.getExtras();
                if (extras != null && extras.containsKey("multi-process-state")) {
                    z = extras.getBoolean("multi-process-state", false);
                }
                cursorQuery.close();
            }
        } catch (Exception e) {
            AcLog.e("multi-process", e.getMessage());
        }
        return z;
    }

    public void b() {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("multi-process-state", Boolean.TRUE);
            this.b.insert(a(this.f1141a), contentValues);
        } catch (Exception e) {
            AcLog.e("multi-process", e.getMessage());
        }
    }
}
