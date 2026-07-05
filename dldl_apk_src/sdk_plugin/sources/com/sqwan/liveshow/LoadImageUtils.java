package com.sqwan.liveshow;

import android.content.Context;
import com.sq.sdk.tool.download.DownloadListener;
import com.sq.sdk.tool.download.DownloadTask;
import com.sqwan.common.util.EnvironmentUtils;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoadImageUtils {

    public interface LoadImageListener {
        void loadFailure(int i, String str);

        void loadSuccess(File file);
    }

    private static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonSubDirPath(context, "avatar");
    }

    public static void loadBitmap(String str, String str2, Context context, final LoadImageListener loadImageListener) {
        new DownloadTask(str, str2, getSDPath(context), new DownloadListener() { // from class: com.sqwan.liveshow.LoadImageUtils.1
            @Override // com.sq.sdk.tool.download.DownloadListener
            public void onUpdate(long j, long j2) {
            }

            @Override // com.sq.sdk.tool.download.DownloadListener
            public void onSuccess(File file) {
                loadImageListener.loadSuccess(file);
            }

            @Override // com.sq.sdk.tool.download.DownloadListener
            public void onFailure(Throwable th, int i, String str3) {
                loadImageListener.loadFailure(i, str3);
            }
        });
    }
}
