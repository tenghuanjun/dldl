package com.sy37sdk.account.download;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NotifyReceiver extends BroadcastReceiver {
    public static String ACTION_DOWN_QUERY = "android.sq.down.query";
    private static final String TAG = "NotifyReceiver";
    private SQLiteDatabase database;
    private DownloaddataBase downloaddataBase;
    private NotificationManager notifyManager;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.i(TAG, "onReceive");
        String action = intent.getAction();
        if (action.equals(ACTION_DOWN_QUERY)) {
            if (Build.VERSION.SDK_INT >= 9) {
                DownloaddataBase downloaddataBase = DownloaddataBase.getDownloaddataBase(context, DownloaddataBase.DATABASE);
                this.downloaddataBase = downloaddataBase;
                this.database = downloaddataBase.getWritableDatabase();
                new NineDown().updateDownInfo(context);
                return;
            }
            return;
        }
        if (action.equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            DownloaddataBase downloaddataBase2 = DownloaddataBase.getDownloaddataBase(context, DownloaddataBase.DATABASE);
            this.downloaddataBase = downloaddataBase2;
            this.database = downloaddataBase2.getWritableDatabase();
            long longExtra = intent.getLongExtra("extra_download_id", 0L);
            Cursor cursorQuery = this.database.query(DownloaddataBase.TABLE, new String[]{"filename"}, "id=?", new String[]{longExtra + ""}, null, null, null);
            String string = cursorQuery.moveToFirst() ? cursorQuery.getString(0) : "";
            cursorQuery.close();
            if (string == null || "".equals(string) || Build.VERSION.SDK_INT < 9) {
                return;
            }
            new NineDown().TaskFind(context, longExtra, string);
        }
    }

    class NineDown {
        NineDown() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateDownInfo(Context context) {
            DownloaderUtil downloaderUtil = new DownloaderUtil(context);
            Cursor cursorQuery = NotifyReceiver.this.database.query(DownloaddataBase.TABLE, new String[]{SqTrackCommonKey.id, "url"}, "downloading=?", new String[]{"1"}, null, null, null);
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(Long.parseLong(string));
                try {
                    Cursor cursorQuery2 = downloadManager.query(query);
                    if (!cursorQuery2.moveToFirst()) {
                        NotifyReceiver.this.database.delete(DownloaddataBase.TABLE, "url = ?", new String[]{string2});
                    } else if (cursorQuery2.getInt(cursorQuery2.getColumnIndex("status")) == 16) {
                        NotifyReceiver.this.database.delete(DownloaddataBase.TABLE, "url = ?", new String[]{string2});
                        downloaderUtil.download(string2, "apk");
                    }
                    cursorQuery2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("--> sq down query error! ");
                }
            }
            cursorQuery.close();
        }

        private void checkGoOnDown(Context context) {
            DownloaderUtil downloaderUtil = new DownloaderUtil(context);
            Cursor cursorQuery = NotifyReceiver.this.database.query(DownloaddataBase.TABLE, new String[]{SqTrackCommonKey.id, "url"}, "downloading=?", new String[]{"1"}, null, null, null);
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(Long.parseLong(string));
                Cursor cursorQuery2 = downloadManager.query(query);
                if (cursorQuery2 == null) {
                    return;
                }
                if (cursorQuery2.moveToFirst() && cursorQuery2.getInt(cursorQuery2.getColumnIndex("status")) == 16) {
                    NotifyReceiver.this.database.delete(DownloaddataBase.TABLE, "url = ?", new String[]{string2});
                    downloaderUtil.download(string2, "apk");
                }
                cursorQuery2.close();
            }
            cursorQuery.close();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void TaskFind(Context context, long j, String str) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(j);
            Cursor cursorQuery = downloadManager.query(query);
            if (cursorQuery.moveToFirst()) {
                int i = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                if (i == 8) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("downloading", (Integer) 2);
                    NotifyReceiver.this.database.update(DownloaddataBase.TABLE, contentValues, "id=?", new String[]{j + ""});
                    if (DownloaderUtil.getSDPath(context) != null) {
                        File file = new File(DownloaderUtil.getSDPath(context) + "/" + str);
                        Intent intent = new Intent();
                        intent.addFlags(268435456);
                        intent.setAction("android.intent.action.VIEW");
                        if (Build.VERSION.SDK_INT >= 24) {
                            intent.setDataAndType(FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file), "application/vnd.android.package-archive");
                            intent.addFlags(1);
                        } else {
                            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        }
                        context.startActivity(intent);
                    }
                } else if (i == 16) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("downloading", (Integer) 0);
                    NotifyReceiver.this.database.update(DownloaddataBase.TABLE, contentValues2, "id=?", new String[]{j + ""});
                    ToastUtil.showToast(context, "下载失败");
                }
            }
            cursorQuery.close();
        }
    }
}
