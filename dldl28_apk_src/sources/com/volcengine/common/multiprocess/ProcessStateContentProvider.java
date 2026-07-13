package com.volcengine.common.multiprocess;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.AbstractCursor;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: classes3.dex */
public class ProcessStateContentProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bundle f1120a = new Bundle();
    private final AbstractCursor b = new a();

    class a extends AbstractWindowedCursor {
        a() {
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public String[] getColumnNames() {
            return new String[0];
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public int getCount() {
            return ProcessStateContentProvider.this.f1120a.size();
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public Bundle getExtras() {
            return ProcessStateContentProvider.this.f1120a;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        try {
            if (!getContext().getPackageName().equals(uri.getAuthority())) {
                return 0;
            }
            this.f1120a.remove("multi-process-state");
            return 0;
        } catch (Exception e) {
            AcLog.e("multi-process", e.getMessage());
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            if (getContext().getPackageName().equals(uri.getAuthority()) && contentValues != null && contentValues.containsKey("multi-process-state")) {
                this.f1120a.putBoolean("multi-process-state", contentValues.getAsBoolean("multi-process-state").booleanValue());
                getContext().getContentResolver().notifyChange(uri, null);
            }
        } catch (Exception e) {
            AcLog.e("multi-process", e.getMessage());
        }
        return uri;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return this.b;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            if (!getContext().getPackageName().equals(uri.getAuthority()) || contentValues == null || !contentValues.containsKey("multi-process-state")) {
                return 0;
            }
            this.f1120a.putBoolean("multi-process-state", contentValues.getAsBoolean("multi-process-state").booleanValue());
            getContext().getContentResolver().notifyChange(uri, null);
            return 0;
        } catch (Exception e) {
            AcLog.e("multi-process", e.getMessage());
            return 0;
        }
    }
}
