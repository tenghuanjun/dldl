package com.cy.yyjia.zhe28.util;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadBroadcast extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(intent.getLongExtra("extra_download_id", 0L));
            Cursor cursorQuery = downloadManager.query(query);
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("local_uri"));
                if (!TextUtils.isEmpty(string)) {
                    Util.installApk(context, Uri.parse(string));
                }
            }
            cursorQuery.close();
            Toast.makeText(context, "下载完成", 0).show();
        }
    }
}
