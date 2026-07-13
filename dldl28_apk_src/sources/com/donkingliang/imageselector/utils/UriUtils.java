package com.donkingliang.imageselector.utils;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class UriUtils {
    public static String getPathForUri(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                    if (VersionUtils.isAndroidQ()) {
                        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + strArrSplit[1];
                    }
                    return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = strArrSplit2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
                }
            }
        } else {
            if (DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c A[PHI: r8
  0x003c: PHI (r8v3 android.database.Cursor) = (r8v2 android.database.Cursor), (r8v4 android.database.Cursor) binds: [B:21:0x003a, B:14:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L31 java.lang.IllegalArgumentException -> L38
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L31 java.lang.IllegalArgumentException -> L38
            if (r8 == 0) goto L2e
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            if (r9 == 0) goto L2e
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> L29 java.lang.IllegalArgumentException -> L2c
            if (r8 == 0) goto L28
            r8.close()
        L28:
            return r9
        L29:
            r9 = move-exception
            r7 = r8
            goto L32
        L2c:
            goto L3a
        L2e:
            if (r8 == 0) goto L3f
            goto L3c
        L31:
            r9 = move-exception
        L32:
            if (r7 == 0) goto L37
            r7.close()
        L37:
            throw r9
        L38:
            r8 = r7
        L3a:
            if (r8 == 0) goto L3f
        L3c:
            r8.close()
        L3f:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.imageselector.utils.UriUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isGooglePlayPhotosUri(Uri uri) {
        return "com.google.android.apps.photos.contentprovider".equals(uri.getAuthority());
    }

    public static Uri getImageContentUri(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{DBHelper.COL_ID}, "_data=? ", new String[]{str}, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex(DBHelper.COL_ID));
            return Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + i);
        }
        if (!new File(str).exists()) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", str);
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }
}
