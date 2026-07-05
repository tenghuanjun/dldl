package com.sqwan.msdk.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.common.util.EnvironmentUtils;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Downloader {
    private String URL;
    private Context context;
    private int downState = 0;
    private String fileName;
    private long id;

    public Downloader(Context context) {
        this.context = context;
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
            File file = new File(getSDPath(this.context) + "/" + this.fileName);
            if (file.exists()) {
                file.delete();
            }
            Download();
            return;
        }
        ViewUtils.showToast(this.context, "请插入SD卡");
    }

    public static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonSubDirPath(context, "download");
    }

    private String Download() {
        if (Build.VERSION.SDK_INT < 9) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.URL));
            intent.addFlags(268435456);
            this.context.startActivity(intent);
            return null;
        }
        try {
            new LevelNineDown().down();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(this.URL));
            intent2.addFlags(268435456);
            this.context.startActivity(intent2);
            return null;
        }
    }

    class LevelNineDown {
        DownloadManager downloadManager;

        LevelNineDown() {
            this.downloadManager = (DownloadManager) Downloader.this.context.getSystemService("download");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void down() throws Exception {
            new Thread(new Runnable() { // from class: com.sqwan.msdk.utils.Downloader.LevelNineDown.1
                @Override // java.lang.Runnable
                public void run() {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Downloader.this.URL));
                    request.setAllowedNetworkTypes(3);
                    request.setAllowedOverRoaming(false);
                    request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(Downloader.this.URL)));
                    request.setShowRunningNotification(true);
                    request.setVisibleInDownloadsUi(true);
                    request.setDestinationInExternalPublicDir("/download/", Downloader.this.fileName);
                    request.setTitle(Downloader.this.fileName);
                    try {
                        Downloader.this.id = LevelNineDown.this.downloadManager.enqueue(request);
                        Downloader.this.downState = 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("-----enqueue Exception----");
                        Downloader.this.downState = -1;
                        Downloader.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Downloader.this.URL)));
                    }
                }
            }).start();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
            Downloader.this.context.registerReceiver(new BroadcastReceiver() { // from class: com.sqwan.msdk.utils.Downloader.LevelNineDown.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (Downloader.getSDPath(context) != null) {
                        String str = Downloader.getSDPath(context) + "/" + Downloader.this.fileName;
                        Uri uriFromFile = Uri.fromFile(new File(str));
                        System.out.println("URL:" + str);
                        Intent intent2 = new Intent("android.intent.action.VIEW");
                        intent2.setDataAndType(uriFromFile, "application/vnd.android.package-archive");
                        context.startActivity(intent2);
                        context.unregisterReceiver(this);
                    }
                }
            }, intentFilter);
        }
    }
}
