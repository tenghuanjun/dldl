package com.sqwan.msdk.views;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.afinal.download.DownloadListener;
import com.sqwan.afinal.download.DownloadTask;
import com.sqwan.common.dialog.FullScreenDialog;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.SQUpdateManager;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.utils.Util;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQUpdateDialog extends FullScreenDialog {
    private Context context;
    private DownloadTask downloadTask;
    private Button hide;
    private boolean isForceUpdate;
    private ImageView ivClose;
    private String notice;
    private View progress_view;
    private Button start;
    private boolean switch_on;
    private TextView update_notice;
    private ProgressBar update_progress;
    private TextView update_size;
    private String url;
    private String version;

    public SQUpdateDialog(Context context) {
        super(context);
        this.switch_on = false;
    }

    public SQUpdateDialog(Context context, boolean z, String str, String str2, String str3) {
        super(context);
        this.switch_on = false;
        this.context = context;
        this.isForceUpdate = z;
        this.notice = str;
        this.url = str2;
        this.version = str3;
    }

    public SQUpdateDialog(Context context, int i, boolean z, String str, String str2, String str3) {
        super(context, i);
        this.switch_on = false;
        this.context = context;
        this.isForceUpdate = z;
        this.notice = str;
        this.url = str2;
        this.version = str3;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().setTheme(Util.getIdByName("Mdialog", "style", this.context.getPackageName(), this.context));
        setContentView(Util.getIdByName("sy37_update_layout", "layout", this.context.getPackageName(), this.context));
        this.update_notice = (TextView) findViewById(Util.getIdByName("update_notice", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.update_size = (TextView) findViewById(Util.getIdByName("update_size", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.start = (Button) findViewById(Util.getIdByName("stop_start_btn", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.hide = (Button) findViewById(Util.getIdByName("hide_btn", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.progress_view = findViewById(Util.getIdByName("progress_view", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.update_progress = (ProgressBar) findViewById(Util.getIdByName("progressbar", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.ivClose = (ImageView) findViewById(Util.getIdByName("iv_close", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.start.setText(this.isForceUpdate ? "开始下载" : "开始");
        this.update_notice.setText(this.notice.replace("\\n", ShellAdbUtils.COMMAND_LINE_END).replace("\\space", " "));
        this.start.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQUpdateDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Util.isNetworkConnected(SQUpdateDialog.this.getContext())) {
                    SQUpdateManager.showTips(SQUpdateDialog.this.context, "没有网络连接，请检查网络设置后重试");
                    return;
                }
                if (SQUpdateDialog.this.switch_on) {
                    SQUpdateDialog.this.switch_on = false;
                    SQUpdateDialog.this.start.setText(SQUpdateDialog.this.isForceUpdate ? "继续下载" : "继续");
                    SQUpdateDialog.this.stopDownload();
                    return;
                }
                if (SQUpdateDialog.this.isForceUpdate) {
                    SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.updateForceStart, SqTrackBtn.SqTrackBtnExt.updateForceStart);
                } else {
                    SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.updateStart, SqTrackBtn.SqTrackBtnExt.updateStart);
                }
                SQUpdateDialog.this.switch_on = true;
                SQUpdateDialog.this.start.setText(SQUpdateDialog.this.isForceUpdate ? "暂停下载" : "暂停");
                SQUpdateDialog sQUpdateDialog = SQUpdateDialog.this;
                sQUpdateDialog.startDownload(sQUpdateDialog.url);
                if (SQUpdateDialog.this.progress_view.getVisibility() == 4 || SQUpdateDialog.this.progress_view.getVisibility() == 8) {
                    SQUpdateDialog.this.progress_view.setVisibility(0);
                }
            }
        });
        this.hide.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQUpdateDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.updateCancel, SqTrackBtn.SqTrackBtnExt.updateCancel);
                SQUpdateDialog.this.dismiss();
            }
        });
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQUpdateDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.updateClose, SqTrackBtn.SqTrackBtnExt.updateClose);
                SQUpdateDialog.this.dismiss();
            }
        });
        setCanceledOnTouchOutside(false);
        if (this.isForceUpdate) {
            setCancelable(false);
            this.ivClose.setVisibility(8);
            this.hide.setVisibility(8);
        }
        if (this.isForceUpdate) {
            PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.updateForce, SqTrackPage.SqTrackViewName.updateForce);
            HashMap map = new HashMap();
            map.put(SqTrackKey.update_uurl, this.url);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.forced_update, map);
            return;
        }
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.update, SqTrackPage.SqTrackViewName.update);
        HashMap map2 = new HashMap();
        map2.put(SqTrackKey.update_uurl, this.url);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.general_update, map2);
    }

    public void startDownload(String str) {
        String sDPath = SQUpdateManager.getSDPath(this.context);
        if (sDPath != null && !"".endsWith(sDPath)) {
            LogUtil.i("开始下载:" + str);
            final String fileNameOfUrl = SQUpdateManager.getFileNameOfUrl(this.context, str, this.version);
            final String str2 = sDPath + fileNameOfUrl;
            LogUtil.i("下载目录:" + str2);
            DownloadTask downloadTask = new DownloadTask(str, fileNameOfUrl, sDPath, new DownloadListener() { // from class: com.sqwan.msdk.views.SQUpdateDialog.4
                private long lastCountMb;
                private int lastProgress;

                @Override // com.sqwan.afinal.download.DownloadListener
                public void onUpdate(long j, long j2) {
                    if (j != 0) {
                        int i = (int) (j2 / (j / 100));
                        long j3 = (j2 / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                        if (j2 <= j && (j3 > this.lastCountMb || i > this.lastProgress)) {
                            LogUtil.i("下载进度：" + j2 + "/" + j + ", " + j3 + "MB, " + i + "%");
                            TextView textView = SQUpdateDialog.this.update_size;
                            StringBuilder sb = new StringBuilder();
                            sb.append(SQUpdateDialog.this.convert2MB(j2));
                            sb.append("/");
                            sb.append(SQUpdateDialog.this.convert2MB(j));
                            textView.setText(sb.toString());
                            SQUpdateDialog.this.update_progress.setProgress(i);
                        }
                        this.lastCountMb = j3;
                        this.lastProgress = i;
                    }
                }

                @Override // com.sqwan.afinal.download.DownloadListener
                public void onSuccess(File file) {
                    if (file == null) {
                        SQUpdateManager.showTips(SQUpdateDialog.this.context, "下载失败:建议在WIFI下重新启动游戏~");
                        return;
                    }
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.update_uurl, SQUpdateDialog.this.url);
                    if (SQUpdateDialog.this.isForceUpdate) {
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.forced_update_success, map);
                    } else {
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.general_update_success, map);
                    }
                    SQUpdateManager.showTips(SQUpdateDialog.this.context, "下载完成：" + file.getAbsoluteFile().toString());
                    LogUtil.i("apk file path is " + file.getAbsoluteFile().toString());
                    SQUpdateManager.saveFileLength(SQUpdateDialog.this.context, fileNameOfUrl, file.length());
                    SQUpdateManager.checkAndInstall(SQUpdateDialog.this.isForceUpdate, SQUpdateDialog.this.context, file);
                    SQUpdateDialog.this.dismiss();
                }

                @Override // com.sqwan.afinal.download.DownloadListener
                public void onFailure(Throwable th, int i, String str3) {
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.update_uurl, SQUpdateDialog.this.url);
                    map.put(SqTrackKey.fail_code, i + "");
                    map.put(SqTrackKey.reason_fail, str3 + "");
                    if (SQUpdateDialog.this.isForceUpdate) {
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.forced_update_fail, map);
                    } else {
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.general_update_fail, map);
                    }
                    if (th != null) {
                        th.printStackTrace();
                    }
                    LogUtil.d("errorNo: " + i + ", strMsg: " + str3);
                    SQUpdateDialog.this.switch_on = false;
                    SQUpdateDialog.this.start.setText("开始下载");
                    SQUpdateDialog.this.stopDownload();
                    if (i == 1) {
                        SQUpdateManager.showTips(SQUpdateDialog.this.context, "下载失败:无效的下载链接~");
                        return;
                    }
                    if (i == 2) {
                        LogUtil.i("下载结束， apk文件没找到");
                        return;
                    }
                    if (i == 4) {
                        LogUtil.i("下载未完成");
                        return;
                    }
                    if (i == 3) {
                        LogUtil.i("下载出错");
                        return;
                    }
                    if (i != 416) {
                        SQUpdateManager.showTips(SQUpdateDialog.this.context, "下载失败:无效的下载链接~");
                        return;
                    }
                    LogUtil.i("返回 416 --> " + str2);
                    File file = new File(str2);
                    if (file.exists()) {
                        SQUpdateManager.checkAndInstall(SQUpdateDialog.this.isForceUpdate, SQUpdateDialog.this.context, file);
                        SQUpdateDialog.this.dismiss();
                    } else {
                        LogUtil.i("apk file is not exists");
                    }
                }
            });
            this.downloadTask = downloadTask;
            downloadTask.execute(new String[0]);
            return;
        }
        SQUpdateManager.showTips(this.context, "下载失败:请您检查设备存储盘情况。");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopDownload() {
        System.out.println("请求暂停");
        DownloadTask downloadTask = this.downloadTask;
        if (downloadTask == null || downloadTask.getStatus() != AsyncTask.Status.RUNNING) {
            return;
        }
        this.downloadTask.cancel(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String convert2MB(long j) {
        return ((int) ((j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) + "MB";
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        stopDownload();
        super.dismiss();
    }
}
