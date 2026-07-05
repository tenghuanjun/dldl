package android.support.v4.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SQLiteCursorCompat {
    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor, boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            sQLiteCursor.setFillWindowForwardOnly(z);
        }
    }
}
