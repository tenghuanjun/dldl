package com.cy.yyjia.zhe28.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginContentProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000e\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016JO\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u000b\u001a\u00020\f2\u0010\u0010\u001a\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000e\u0018\u00010\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000e\u0018\u00010\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010\u001cJ;\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000e\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u001eR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/db/LoginContentProvider;", "Landroid/content/ContentProvider;", "()V", "db", "Landroid/database/sqlite/SQLiteDatabase;", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "setDb", "(Landroid/database/sqlite/SQLiteDatabase;)V", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LoginContentProvider extends ContentProvider {
    public static final int $stable = 8;
    public SQLiteDatabase db;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    public final SQLiteDatabase getDb() {
        SQLiteDatabase sQLiteDatabase = this.db;
        if (sQLiteDatabase != null) {
            return sQLiteDatabase;
        }
        Intrinsics.throwUninitializedPropertyAccessException("db");
        return null;
    }

    public final void setDb(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "<set-?>");
        this.db = sQLiteDatabase;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        SQLiteDatabase readableDatabase = new LoginHelper(context).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "getReadableDatabase(...)");
        setDb(readableDatabase);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return getDb().query("login", projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return ContentUris.withAppendedId(Uri.parse("content://com.cy.yyjia.zhe28.loginprovider/login"), getDb().insert("login", null, values));
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return getDb().delete("login", selection, selectionArgs);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return getDb().update("login", values, selection, selectionArgs);
    }
}
