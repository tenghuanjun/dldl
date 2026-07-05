package com.sy37sdk.plugin.data;

import android.content.Context;
import android.text.TextUtils;
import com.sq.sdk.tool.download.DownloadListener;
import com.sq.sdk.tool.download.DownloadTask;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.plugin.net.PluginRequestManager;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PluginDownloadManager {
    private static final int FILE_MD5_CALCULATION_FAILURE = 1024;
    private static final int FILE_MD5_DIFFERENT = 1025;
    private Context mContext;
    private SpPluginConfig mPluginConfig;
    private String mPluginPath;
    private PluginRequestManager mRequestManager = new PluginRequestManager();

    public PluginDownloadManager(Context context) {
        this.mContext = context;
        this.mPluginConfig = new SpPluginConfig(context);
    }

    public void handlePlugin() {
        if (this.mPluginConfig.getPluginVersion() != 0) {
            this.mPluginPath = generatePluginPath();
            if (this.mPluginConfig.getPluginVersion() > this.mPluginConfig.getPluginCurrentVersion()) {
                HashMap map = new HashMap();
                map.put(SqTrackKey.plug_id, this.mPluginConfig.getPluginConfId() + "");
                map.put(SqTrackKey.plug_type, this.mPluginConfig.getPluginType() + "");
                map.put(SqTrackKey.plug_version, this.mPluginConfig.getPluginVersion() + "");
                map.put(SqTrackKey.plug_current_version, this.mPluginConfig.getPluginCurrentVersion() + "");
                map.put(SqTrackKey.plug_link, this.mPluginConfig.getPluginUrl());
                map.put(SqTrackKey.plug_hash, this.mPluginConfig.getPluginHash() + "");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.hotter_update, map);
            }
            downloadPlugin();
            return;
        }
        if (this.mPluginConfig.getPluginLatestVersion() != 0 && this.mPluginConfig.getPluginLatestVersion() != -1) {
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.hotter_update_rollback);
            this.mContext.getSharedPreferences("sq_plugin_config", 0).edit().putString("hotter_rollback_effect", this.mPluginConfig.getPluginLatestVersion() + "").apply();
        }
        this.mPluginConfig.setPluginLatestVersion(0);
        LogUtil.i("没有插件配置，或插件已全部回滚，走默认插件");
    }

    private String generatePluginPath() {
        File dir = this.mContext.getDir("plugin", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String absolutePath = dir.getAbsolutePath();
        LogUtil.i("插件地址: " + absolutePath);
        return absolutePath;
    }

    private boolean isPluginExist(String str, String str2) {
        return new File(str, str2).exists();
    }

    private void downloadPlugin() {
        new DownloadTask(this.mPluginConfig.getPluginUrl(), "plugin_" + this.mPluginConfig.getPluginVersion() + ".apk", this.mPluginPath, new DownloadListener() { // from class: com.sy37sdk.plugin.data.PluginDownloadManager.1
            @Override // com.sq.sdk.tool.download.DownloadListener
            public void onUpdate(long j, long j2) {
            }

            @Override // com.sq.sdk.tool.download.DownloadListener
            public void onSuccess(File file) {
                LogUtil.i("文件下载成功: " + file.getAbsolutePath());
                if (PluginDownloadManager.this.mPluginConfig.getPluginLatestVersion() == PluginDownloadManager.this.mPluginConfig.getPluginVersion()) {
                    return;
                }
                try {
                    String fileMd5 = PluginDownloadManager.getFileMd5(file);
                    if (TextUtils.isEmpty(PluginDownloadManager.this.mPluginConfig.getPluginHash()) || fileMd5.equalsIgnoreCase(PluginDownloadManager.this.mPluginConfig.getPluginHash())) {
                        PluginDownloadManager.this.mPluginConfig.setPluginLatestVersion(PluginDownloadManager.this.mPluginConfig.getPluginVersion());
                        PluginDownloadManager.this.reportHotterUpdateSuccess();
                    } else {
                        PluginDownloadManager.this.reportHotterUpdateFail(1025, "热更插件本地 apk md5 值和后台下发的不一致");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PluginDownloadManager.this.reportHotterUpdateFail(1024, e.getMessage());
                }
            }

            @Override // com.sq.sdk.tool.download.DownloadListener
            public void onFailure(Throwable th, int i, String str) {
                LogUtil.e("插件下载失败: " + str);
                PluginDownloadManager.this.reportHotterUpdateFail(i, str);
            }
        }).execute(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportHotterUpdateSuccess() {
        uploadDownloadState(true, "");
        HashMap map = new HashMap();
        map.put(SqTrackKey.plug_id, this.mPluginConfig.getPluginConfId() + "");
        map.put(SqTrackKey.plug_type, this.mPluginConfig.getPluginType() + "");
        map.put(SqTrackKey.plug_version, this.mPluginConfig.getPluginVersion() + "");
        map.put(SqTrackKey.plug_link, this.mPluginConfig.getPluginUrl());
        map.put(SqTrackKey.plug_hash, this.mPluginConfig.getPluginHash());
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.hotter_update_success, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportHotterUpdateFail(int i, String str) {
        uploadDownloadState(false, str);
        HashMap map = new HashMap();
        map.put(SqTrackKey.plug_id, this.mPluginConfig.getPluginConfId() + "");
        map.put(SqTrackKey.plug_type, this.mPluginConfig.getPluginType() + "");
        map.put(SqTrackKey.plug_version, this.mPluginConfig.getPluginVersion() + "");
        map.put(SqTrackKey.plug_link, this.mPluginConfig.getPluginUrl());
        map.put(SqTrackKey.plug_hash, this.mPluginConfig.getPluginHash());
        map.put(SqTrackKey.fail_code, i + "");
        map.put(SqTrackKey.reason_fail, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.hotter_update_fail, map);
    }

    public static String getFileMd5(File file) throws Exception {
        DigestInputStream digestInputStream;
        Throwable th;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
            try {
                while (digestInputStream.read(new byte[262144]) > 0) {
                }
                byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    sb.append(String.format("%02X", Byte.valueOf(b)));
                }
                String lowerCase = sb.toString().toLowerCase();
                closeStream(fileInputStream);
                closeStream(digestInputStream);
                return lowerCase;
            } catch (Throwable th2) {
                th = th2;
                closeStream(fileInputStream);
                closeStream(digestInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            digestInputStream = null;
            th = th3;
        }
    }

    public static void closeStream(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadDownloadState(boolean z, String str) {
        if (z) {
            this.mRequestManager.pluginDownloadReport(this.mPluginConfig.getPluginVersion(), 1, this.mPluginConfig.getPluginType(), this.mPluginConfig.getPluginConfId(), "", null);
        } else {
            this.mRequestManager.pluginDownloadReport(this.mPluginConfig.getPluginVersion(), 0, this.mPluginConfig.getPluginType(), this.mPluginConfig.getPluginConfId(), str, null);
        }
    }
}
