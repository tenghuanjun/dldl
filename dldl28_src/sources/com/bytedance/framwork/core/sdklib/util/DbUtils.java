package com.bytedance.framwork.core.sdklib.util;

import android.database.Cursor;

/* JADX INFO: loaded from: classes2.dex */
public class DbUtils {
    public static void safeCloseCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }
}
