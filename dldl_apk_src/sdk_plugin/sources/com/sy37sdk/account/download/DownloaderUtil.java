package com.sy37sdk.account.download;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.ToastUtil;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DownloaderUtil {
    private String URL;
    private Context context;
    private SQLiteDatabase database;
    private int downState = 0;
    private DownloaddataBase downloaddataBase;
    private String fileName;
    private long id;

    public DownloaderUtil(Context context) {
        this.context = context;
        DownloaddataBase downloaddataBase = DownloaddataBase.getDownloaddataBase(context, DownloaddataBase.DATABASE);
        this.downloaddataBase = downloaddataBase;
        this.database = downloaddataBase.getWritableDatabase();
    }

    public void download(String str, String str2) {
        if (str == null || "".equals(str)) {
            return;
        }
        this.URL = str;
        if (str.endsWith(FileUtil.FILE_EXTENSION_SEPARATOR + str2)) {
            this.fileName = str.substring(str.lastIndexOf("/") + 1);
        } else {
            this.fileName = System.currentTimeMillis() + FileUtil.FILE_EXTENSION_SEPARATOR + str2;
        }
        if (getSDPath(this.context) != null) {
            Download();
        } else {
            ToastUtil.showToast(this.context, "请插入SD卡");
        }
    }

    public static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonSubDirPath(context, "download");
    }

    private String Download() {
        if (Build.VERSION.SDK_INT < 9) {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.URL)));
            return null;
        }
        try {
            new LevelNineDown().down();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.URL)));
            return null;
        }
    }

    class LevelNineDown {
        DownloadManager downloadManager;

        LevelNineDown() {
            this.downloadManager = (DownloadManager) DownloaderUtil.this.context.getSystemService("download");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void down() throws Exception {
            Thread thread = new Thread(new Runnable() { // from class: com.sy37sdk.account.download.DownloaderUtil.LevelNineDown.1
                @Override // java.lang.Runnable
                public void run() {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloaderUtil.this.URL));
                    request.setAllowedNetworkTypes(3);
                    request.setAllowedOverRoaming(false);
                    request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(DownloaderUtil.this.URL)));
                    request.setShowRunningNotification(true);
                    request.setVisibleInDownloadsUi(true);
                    request.setNotificationVisibility(1);
                    request.setVisibleInDownloadsUi(true);
                    request.setDestinationInExternalPublicDir("/download/", DownloaderUtil.this.fileName);
                    request.setTitle(DownloaderUtil.this.fileName);
                    try {
                        try {
                            DownloaderUtil.this.id = LevelNineDown.this.downloadManager.enqueue(request);
                            DownloaderUtil.this.downState = 1;
                            try {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("url", DownloaderUtil.this.URL);
                                contentValues.put("downloading", DownloaderUtil.this.downState + "");
                                contentValues.put("filename", DownloaderUtil.this.fileName);
                                contentValues.put(SqTrackCommonKey.id, DownloaderUtil.this.id + "");
                                DownloaderUtil.this.database.insert(DownloaddataBase.TABLE, null, contentValues);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            System.out.println("-----enqueue Exception----");
                            DownloaderUtil.this.downState = -1;
                            DownloaderUtil.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(DownloaderUtil.this.URL)));
                            try {
                                ContentValues contentValues2 = new ContentValues();
                                contentValues2.put("url", DownloaderUtil.this.URL);
                                contentValues2.put("downloading", DownloaderUtil.this.downState + "");
                                contentValues2.put("filename", DownloaderUtil.this.fileName);
                                contentValues2.put(SqTrackCommonKey.id, DownloaderUtil.this.id + "");
                                DownloaderUtil.this.database.insert(DownloaddataBase.TABLE, null, contentValues2);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            ContentValues contentValues3 = new ContentValues();
                            contentValues3.put("url", DownloaderUtil.this.URL);
                            contentValues3.put("downloading", DownloaderUtil.this.downState + "");
                            contentValues3.put("filename", DownloaderUtil.this.fileName);
                            contentValues3.put(SqTrackCommonKey.id, DownloaderUtil.this.id + "");
                            DownloaderUtil.this.database.insert(DownloaddataBase.TABLE, null, contentValues3);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            });
            Intent intent = new Intent();
            intent.setAction(NotifyReceiver.ACTION_DOWN_QUERY);
            DownloaderUtil.this.context.sendBroadcast(intent);
            Cursor cursorQuery = DownloaderUtil.this.database.query(DownloaddataBase.TABLE, new String[]{"url,id,downloading"}, "url=?", new String[]{DownloaderUtil.this.URL}, null, null, null);
            String string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndex("downloading")) : "0";
            if (string.equals("-1")) {
                ToastUtil.showToast(DownloaderUtil.this.context, "使用浏览器进行文件下载");
                DownloaderUtil.this.downState = 2;
                DownloaderUtil.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(DownloaderUtil.this.URL)));
                return;
            }
            if (string.equals("0")) {
                ToastUtil.showToast(DownloaderUtil.this.context, "开始下载");
                thread.start();
                return;
            }
            if (string.equals("1")) {
                ToastUtil.showToast(DownloaderUtil.this.context, "当前任务正在下载中");
                return;
            }
            if (string.equals("2")) {
                DownloaderUtil.this.database.delete(DownloaddataBase.TABLE, "url=?", new String[]{DownloaderUtil.this.URL});
                File file = new File(DownloaderUtil.getSDPath(DownloaderUtil.this.context) + "/" + DownloaderUtil.this.fileName);
                if (file.exists()) {
                    file.delete();
                }
                ToastUtil.showToast(DownloaderUtil.this.context, "开始下载");
                thread.start();
            }
        }
    }
}
