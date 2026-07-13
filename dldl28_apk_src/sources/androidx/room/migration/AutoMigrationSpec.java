package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
public interface AutoMigrationSpec {

    /* JADX INFO: renamed from: androidx.room.migration.AutoMigrationSpec$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onPostMigrate(AutoMigrationSpec _this, SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
