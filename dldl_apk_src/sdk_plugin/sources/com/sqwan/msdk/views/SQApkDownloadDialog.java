package com.sqwan.msdk.views;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sqwan.afinal.download.SQDownloadManager;
import com.sqwan.afinal.download.bean.DownloadBean;
import com.sqwan.common.dialog.FullScreenDialog;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.SQUpdateManager;
import com.sqwan.msdk.utils.ImageCacheManager;
import com.sy37sdk.utils.Util;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQApkDownloadDialog extends FullScreenDialog implements SQDownloadManager.DialogStateUpdater {
    private static final long CLICK_DEBOUNCE_TIME = 1500;
    private ImageView backgroundImageView;
    private CircularProgressView circularProgressView;
    private ImageView closeButton;
    private int closeButtonRightDp;
    private int closeButtonTopDp;
    private String currentTaskId;
    private int downloadButtonBottomDp;
    private ImageView downloadButtonImageView;
    private LinearLayout downloadLayout;
    private TextView downloadTextView;
    private TextView fileSizeTextView;
    private boolean isDownloading;
    private long lastClickTime;
    private long lastCountMb;
    private int lastProgress;
    private Context mContext;
    private String mCurrentType;
    private DownloadBean mDownLoadBean;
    private SQDownloadManager.DownloadStateListener progressListener;
    private RelativeLayout rootLayout;

    public SQApkDownloadDialog(Context context) {
        super(context);
        this.mCurrentType = SQDownloadManager.DOWNLOAD;
        this.closeButtonTopDp = 40;
        this.closeButtonRightDp = 10;
        this.downloadButtonBottomDp = 30;
        this.lastCountMb = 0L;
        this.lastProgress = 0;
        this.isDownloading = false;
        this.lastClickTime = 0L;
        this.mContext = context;
    }

    public SQApkDownloadDialog(Context context, int i) {
        super(context, i);
        this.mCurrentType = SQDownloadManager.DOWNLOAD;
        this.closeButtonTopDp = 40;
        this.closeButtonRightDp = 10;
        this.downloadButtonBottomDp = 30;
        this.lastCountMb = 0L;
        this.lastProgress = 0;
        this.isDownloading = false;
        this.lastClickTime = 0L;
    }

    public SQApkDownloadDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mCurrentType = SQDownloadManager.DOWNLOAD;
        this.closeButtonTopDp = 40;
        this.closeButtonRightDp = 10;
        this.downloadButtonBottomDp = 30;
        this.lastCountMb = 0L;
        this.lastProgress = 0;
        this.isDownloading = false;
        this.lastClickTime = 0L;
    }

    public SQApkDownloadDialog setDownloadBean(DownloadBean downloadBean) {
        this.mDownLoadBean = downloadBean;
        return this;
    }

    public SQApkDownloadDialog setCloseButtonPosition(int i, int i2) {
        this.closeButtonTopDp = i;
        this.closeButtonRightDp = i2;
        return this;
    }

    public SQApkDownloadDialog setDownloadButtonBottomMargin(int i) {
        this.downloadButtonBottomDp = i;
        return this;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initData();
        initView();
        initProgressListener();
        checkExistingDownload();
        SQDownloadManager.getInstance().setDialogUpdater(this);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DLYZAPP_DOWNLOAD_PUSH_SHOW);
    }

    private void initProgressListener() {
        this.progressListener = new SQDownloadManager.DownloadStateListener() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.1
            @Override // com.sqwan.afinal.download.SQDownloadManager.DownloadStateListener
            public void onStateChanged(String str, SQDownloadManager.DownloadState downloadState) {
            }

            @Override // com.sqwan.afinal.download.SQDownloadManager.DownloadStateListener
            public void onProgressChanged(String str, final int i, long j, long j2) {
                if (str.equals(SQApkDownloadDialog.this.currentTaskId)) {
                    long j3 = (j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    if (j <= j2) {
                        if (j3 > SQApkDownloadDialog.this.lastCountMb || i > SQApkDownloadDialog.this.lastProgress) {
                            if ((SQApkDownloadDialog.this.mContext instanceof Activity) && SQApkDownloadDialog.this.circularProgressView != null && SQApkDownloadDialog.this.fileSizeTextView != null) {
                                ((Activity) SQApkDownloadDialog.this.mContext).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (SQApkDownloadDialog.this.circularProgressView == null || SQApkDownloadDialog.this.fileSizeTextView == null) {
                                            return;
                                        }
                                        SQApkDownloadDialog.this.circularProgressView.setProgress(i);
                                        SQApkDownloadDialog.this.fileSizeTextView.setText("(" + i + "%)");
                                    }
                                });
                            }
                            SQApkDownloadDialog.this.lastCountMb = j3;
                            SQApkDownloadDialog.this.lastProgress = i;
                        }
                    }
                }
            }
        };
        SQDownloadManager.getInstance().registerListener(this.progressListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDownloadTaskActiveInSystem(long j) {
        try {
            DownloadManager downloadManager = (DownloadManager) this.mContext.getSystemService("download");
            if (downloadManager == null) {
                return false;
            }
            DownloadManager.Query query = new DownloadManager.Query();
            boolean z = true;
            query.setFilterById(j);
            Cursor cursorQuery = null;
            try {
                cursorQuery = downloadManager.query(query);
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return false;
                }
                int i = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                if (i != 2 && i != 4 && i != 1) {
                    z = false;
                }
                return z;
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Exception e) {
            LogUtil.e("检查系统下载任务状态异常: " + e.getMessage());
            return false;
        }
    }

    private void checkExistingDownload() {
        if (this.mDownLoadBean == null) {
            LogUtil.e("checkExistingDownload: mDownLoadBean为空");
        } else {
            new Thread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final String str = SQApkDownloadDialog.this.mDownLoadBean.getPackage_name() + "_" + SQApkDownloadDialog.this.mDownLoadBean.getId();
                        final SQDownloadManager.DownloadTaskInfo downloadTaskInfo = SQDownloadManager.getInstance().getDownloadTaskInfo(str);
                        if ((SQApkDownloadDialog.this.mContext instanceof Activity) && downloadTaskInfo != null && downloadTaskInfo.state == SQDownloadManager.DownloadState.DOWNLOADING && downloadTaskInfo.downloadId != 0) {
                            if (SQApkDownloadDialog.this.isDownloadTaskActiveInSystem(downloadTaskInfo.downloadId)) {
                                ((Activity) SQApkDownloadDialog.this.mContext).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        if (SQApkDownloadDialog.this.mDownLoadBean == null || SQApkDownloadDialog.this.currentTaskId != null) {
                                            return;
                                        }
                                        SQApkDownloadDialog.this.connectToExistingDownloadTask(str, downloadTaskInfo);
                                    }
                                });
                            } else {
                                LogUtil.d("检测到无效的下载任务，清理任务信息: " + str);
                            }
                        }
                    } catch (Exception e) {
                        LogUtil.e("checkExistingDownload异步检查失败: " + e.getMessage());
                    }
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToExistingDownloadTask(String str, SQDownloadManager.DownloadTaskInfo downloadTaskInfo) {
        this.currentTaskId = str;
        this.isDownloading = true;
        this.mCurrentType = SQDownloadManager.DOWNLOADING;
        updateDownloadLayoutForType();
        CircularProgressView circularProgressView = this.circularProgressView;
        if (circularProgressView != null) {
            circularProgressView.setProgress(downloadTaskInfo.progress);
        }
        TextView textView = this.fileSizeTextView;
        if (textView != null) {
            textView.setText("(" + downloadTaskInfo.progress + "%)");
        }
        LogUtil.d("连接到已存在的下载任务，taskId: " + str + ", progress: " + downloadTaskInfo.progress + "%");
    }

    private void initData() {
        DownloadBean downloadBean = this.mDownLoadBean;
        if (downloadBean != null) {
            if (downloadBean.getClose_button_top_margin() != DownloadBean.defualt_margin) {
                this.closeButtonTopDp = this.mDownLoadBean.getClose_button_top_margin();
            }
            if (this.mDownLoadBean.getClose_button_right_margin() != DownloadBean.defualt_margin) {
                this.closeButtonRightDp = this.mDownLoadBean.getClose_button_right_margin();
            }
            if (this.mDownLoadBean.getDownload_button_bottom_margin() != DownloadBean.defualt_margin) {
                this.downloadButtonBottomDp = this.mDownLoadBean.getDownload_button_bottom_margin();
            }
        }
    }

    private void initView() {
        if (this.mContext == null) {
            LogUtil.e("initView: mContext为空");
            return;
        }
        RelativeLayout relativeLayout = new RelativeLayout(this.mContext);
        this.rootLayout = relativeLayout;
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        ImageView imageView = new ImageView(this.mContext);
        this.backgroundImageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.backgroundImageView.setId(View.generateViewId());
        int iDpToPx = dpToPx(this.mContext, 535);
        int iDpToPx2 = dpToPx(this.mContext, 375);
        if (MultiSDKUtils.isScreenOriatationPortrait(this.mContext)) {
            iDpToPx = dpToPx(this.mContext, 375);
            iDpToPx2 = dpToPx(this.mContext, 535);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iDpToPx, iDpToPx2);
        layoutParams.addRule(13);
        this.backgroundImageView.setLayoutParams(layoutParams);
        DownloadBean downloadBean = this.mDownLoadBean;
        if (downloadBean != null && downloadBean.getBackground_img_url() != null) {
            String background_img_url = this.mDownLoadBean.getBackground_img_url();
            if (!TextUtils.isEmpty(background_img_url)) {
                ImageCacheManager.getInstance(this.mContext).loadImage(background_img_url, this.backgroundImageView, new ImageCacheManager.ImageLoadCallback() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.3
                    @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageLoadCallback
                    public void onError(String str) {
                    }

                    @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageLoadCallback
                    public void onSuccess(Bitmap bitmap) {
                        if (SQApkDownloadDialog.this.backgroundImageView == null || bitmap == null) {
                            return;
                        }
                        SQApkDownloadDialog.this.backgroundImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }
        ImageView imageView2 = new ImageView(this.mContext);
        this.downloadButtonImageView = imageView2;
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        this.downloadButtonImageView.setId(View.generateViewId());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dpToPx(this.mContext, 170), dpToPx(this.mContext, 45));
        layoutParams2.addRule(14, this.backgroundImageView.getId());
        layoutParams2.addRule(8, this.backgroundImageView.getId());
        layoutParams2.bottomMargin = dpToPx(this.mContext, this.downloadButtonBottomDp);
        this.downloadButtonImageView.setLayoutParams(layoutParams2);
        this.downloadButtonImageView.setClickable(true);
        this.downloadButtonImageView.setFocusable(true);
        DownloadBean downloadBean2 = this.mDownLoadBean;
        if (downloadBean2 != null && downloadBean2.getDownload_button_url() != null) {
            String download_button_url = this.mDownLoadBean.getDownload_button_url();
            if (!TextUtils.isEmpty(download_button_url)) {
                ImageCacheManager.getInstance(this.mContext).loadImage(download_button_url, this.downloadButtonImageView, new ImageCacheManager.ImageLoadCallback() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.4
                    @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageLoadCallback
                    public void onSuccess(Bitmap bitmap) {
                        if (SQApkDownloadDialog.this.downloadButtonImageView == null || bitmap == null) {
                            return;
                        }
                        SQApkDownloadDialog.this.downloadButtonImageView.setImageBitmap(bitmap);
                    }

                    @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageLoadCallback
                    public void onError(String str) {
                        int drawableId = SqResUtils.getDrawableId(SQApkDownloadDialog.this.mContext, "default_download_btn");
                        if (drawableId != 0) {
                            SQApkDownloadDialog.this.downloadButtonImageView.setImageResource(drawableId);
                        }
                    }
                });
            }
        }
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        this.downloadLayout = linearLayout;
        linearLayout.setOrientation(0);
        this.downloadLayout.setGravity(17);
        this.downloadLayout.setId(View.generateViewId());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(15);
        layoutParams3.addRule(5, this.downloadButtonImageView.getId());
        layoutParams3.addRule(7, this.downloadButtonImageView.getId());
        layoutParams3.addRule(6, this.downloadButtonImageView.getId());
        layoutParams3.addRule(8, this.downloadButtonImageView.getId());
        this.downloadLayout.setLayoutParams(layoutParams3);
        this.downloadLayout.setClickable(true);
        this.downloadLayout.setFocusable(true);
        TextView textView = new TextView(this.mContext);
        this.downloadTextView = textView;
        textView.setText("下载");
        this.downloadTextView.setTextSize(1, 16.0f);
        this.downloadTextView.setTextColor(-1);
        TextView textView2 = this.downloadTextView;
        textView2.setTypeface(textView2.getTypeface(), 1);
        this.downloadTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        CircularProgressView circularProgressView = new CircularProgressView(this.mContext);
        this.circularProgressView = circularProgressView;
        circularProgressView.setProgress(0);
        int iDpToPx3 = dpToPx(this.mContext, 18);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(iDpToPx3, iDpToPx3);
        layoutParams4.rightMargin = dpToPx(this.mContext, 8);
        this.circularProgressView.setLayoutParams(layoutParams4);
        this.fileSizeTextView = new TextView(this.mContext);
        DownloadBean downloadBean3 = this.mDownLoadBean;
        this.fileSizeTextView.setText((downloadBean3 == null || TextUtils.isEmpty(downloadBean3.getDownload_apk_size())) ? "未知大小" : this.mDownLoadBean.getDownload_apk_size());
        this.fileSizeTextView.setTextSize(1, 14.0f);
        this.fileSizeTextView.setTextColor(-1);
        TextView textView3 = this.fileSizeTextView;
        textView3.setTypeface(textView3.getTypeface(), 1);
        this.fileSizeTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.downloadLayout.addView(this.downloadTextView);
        this.closeButton = new ImageView(this.mContext);
        int iDpToPx4 = dpToPx(this.mContext, 20);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(iDpToPx4, iDpToPx4);
        layoutParams5.addRule(6, this.backgroundImageView.getId());
        layoutParams5.addRule(7, this.backgroundImageView.getId());
        layoutParams5.topMargin = dpToPx(this.mContext, this.closeButtonTopDp);
        layoutParams5.rightMargin = dpToPx(this.mContext, this.closeButtonRightDp);
        this.closeButton.setLayoutParams(layoutParams5);
        this.closeButton.setScaleType(ImageView.ScaleType.FIT_XY);
        this.closeButton.setClickable(true);
        this.closeButton.setFocusable(true);
        DownloadBean downloadBean4 = this.mDownLoadBean;
        if (downloadBean4 != null && downloadBean4.getClose_button_url() != null) {
            String close_button_url = this.mDownLoadBean.getClose_button_url();
            if (!TextUtils.isEmpty(close_button_url)) {
                ImageCacheManager.getInstance(this.mContext).loadImage(close_button_url, this.closeButton, new ImageCacheManager.ImageLoadCallback() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.5
                    @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageLoadCallback
                    public void onSuccess(Bitmap bitmap) {
                        if (SQApkDownloadDialog.this.closeButton == null || bitmap == null) {
                            return;
                        }
                        SQApkDownloadDialog.this.closeButton.setImageBitmap(bitmap);
                    }

                    @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageLoadCallback
                    public void onError(String str) {
                        int idByName = Util.getIdByName("sy37_icon_default", "drawable", SQApkDownloadDialog.this.mContext.getPackageName(), SQApkDownloadDialog.this.mContext);
                        if (idByName != 0) {
                            SQApkDownloadDialog.this.closeButton.setImageResource(idByName);
                        }
                    }
                });
            } else {
                int idByName = Util.getIdByName("sy37_icon_default", "drawable", this.mContext.getPackageName(), this.mContext);
                if (idByName != 0) {
                    this.closeButton.setImageResource(idByName);
                }
            }
        } else {
            int idByName2 = Util.getIdByName("sy37_icon_default", "drawable", this.mContext.getPackageName(), this.mContext);
            if (idByName2 != 0) {
                this.closeButton.setImageResource(idByName2);
            }
        }
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DLYZAPP_DOWNLOAD_PUSH_CLOSE);
                SQApkDownloadDialog.this.dismiss();
            }
        });
        this.rootLayout.addView(this.backgroundImageView);
        this.rootLayout.addView(this.downloadButtonImageView);
        this.rootLayout.addView(this.downloadLayout);
        this.rootLayout.addView(this.closeButton);
        this.downloadLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SQApkDownloadDialog.this.handleDownloadButtonClick();
            }
        });
        this.downloadButtonImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SQApkDownloadDialog.this.handleDownloadButtonClick();
            }
        });
        setContentView(this.rootLayout);
        updateDownloadLayoutForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadLayoutForType() {
        LinearLayout linearLayout = this.downloadLayout;
        if (linearLayout == null || this.downloadTextView == null) {
            LogUtil.e("downloadLayout or downloadTextView is null");
            return;
        }
        linearLayout.removeAllViews();
        if (this.mCurrentType.equals(SQDownloadManager.DOWNLOAD)) {
            DownloadBean downloadBean = this.mDownLoadBean;
            String download_apk_size = (downloadBean == null || TextUtils.isEmpty(downloadBean.getDownload_apk_size())) ? "未知大小" : this.mDownLoadBean.getDownload_apk_size();
            this.downloadTextView.setText("立即下载（" + download_apk_size + "）");
            this.downloadLayout.addView(this.downloadTextView);
            this.downloadLayout.setVisibility(0);
            return;
        }
        if (this.mCurrentType.equals(SQDownloadManager.DOWNLOADING)) {
            this.downloadTextView.setText("下载中");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = dpToPx(this.mContext, 8);
            this.downloadTextView.setLayoutParams(layoutParams);
            this.downloadLayout.addView(this.downloadTextView);
            CircularProgressView circularProgressView = this.circularProgressView;
            if (circularProgressView != null) {
                this.downloadLayout.addView(circularProgressView);
            }
            TextView textView = this.fileSizeTextView;
            if (textView != null) {
                this.downloadLayout.addView(textView);
            }
            this.downloadLayout.setVisibility(0);
            return;
        }
        if (this.mCurrentType.equals(SQDownloadManager.INSTALL)) {
            this.downloadTextView.setText("安装");
            this.downloadLayout.addView(this.downloadTextView);
            this.downloadLayout.setVisibility(0);
        } else if (this.mCurrentType.equals(SQDownloadManager.OPEN_APP)) {
            this.downloadTextView.setText("打开APP");
            this.downloadLayout.addView(this.downloadTextView);
            this.downloadLayout.setVisibility(0);
        } else {
            this.downloadLayout.setVisibility(8);
            this.downloadButtonImageView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadButtonClick() {
        if (this.mContext == null) {
            LogUtil.e("context为null，请检查");
            return;
        }
        if (this.mDownLoadBean == null) {
            LogUtil.e("下载参数为null,请检查");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime < CLICK_DEBOUNCE_TIME) {
            LogUtil.d("handleDownloadButtonClick: 点击过于频繁，忽略点击");
            return;
        }
        this.lastClickTime = jCurrentTimeMillis;
        if (this.isDownloading) {
            LogUtil.d("handleDownloadButtonClick: 下载中，忽略点击");
            ToastUtil.showToast(this.mContext, "正在下载中，请稍候...");
            return;
        }
        if (this.mCurrentType.equals(SQDownloadManager.OPEN_APP)) {
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DLYZAPP_DOWNLOAD_PUSH_OPEN);
            openApk();
        } else {
            if (this.mCurrentType.equals(SQDownloadManager.INSTALL)) {
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DLYZAPP_DOWNLOAD_PUSH_INSTALL);
                SQUpdateManager.installApk(this.mContext, new File(SQDownloadManager.getInstance().targetPath + this.mDownLoadBean.getPackage_name() + ".apk"));
                return;
            }
            onDownloadClick();
        }
    }

    private int dpToPx(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    private void openApk() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PackageManager packageManager = SQApkDownloadDialog.this.mContext.getPackageManager();
                        LogUtil.i("tgid:" + SQApkDownloadDialog.this.mDownLoadBean.getTgid() + " token:" + MultiSDKUtils.getToken(SQApkDownloadDialog.this.mContext));
                        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(SQApkDownloadDialog.this.mDownLoadBean.getPackage_name());
                        if (launchIntentForPackage == null) {
                            ToastUtil.showToast(SQApkDownloadDialog.this.mContext, "无法打开应用，请检查应用是否已安装");
                        } else {
                            launchIntentForPackage.addFlags(268435456);
                            launchIntentForPackage.putExtra("tgid", SQApkDownloadDialog.this.mDownLoadBean.getTgid());
                            launchIntentForPackage.putExtra("token", MultiSDKUtils.getToken(SQApkDownloadDialog.this.mContext));
                            launchIntentForPackage.putExtra("sdk_pid", MultiSDKUtils.getPID(SQApkDownloadDialog.this.mContext));
                            launchIntentForPackage.putExtra("sdk_gid", MultiSDKUtils.getGID(SQApkDownloadDialog.this.mContext));
                            launchIntentForPackage.putExtra("sdk_refer", MultiSDKUtils.getRefer(SQApkDownloadDialog.this.mContext));
                            launchIntentForPackage.putExtra("sdk_sversion", VersionUtil.sdkVersion);
                            SQApkDownloadDialog.this.mContext.startActivity(launchIntentForPackage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.e("打开应用失败: " + e.getMessage());
                        ToastUtil.showToast(SQApkDownloadDialog.this.mContext, "打开应用失败");
                    }
                }
            });
        } else {
            LogUtil.e("mContext is not activity");
        }
    }

    private void onDownloadClick() {
        LogUtil.d("onDownloadClick: 开始下载流程");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DLYZAPP_DOWNLOAD_PUSH_START);
        resetProgressState();
        this.isDownloading = true;
        SQDownloadManager.getInstance().clearOtherApk(this.mDownLoadBean.getPackage_name());
        String strStartDownload = SQDownloadManager.getInstance().startDownload(this.mDownLoadBean);
        this.currentTaskId = strStartDownload;
        if (strStartDownload == null) {
            LogUtil.e("启动下载失败");
            this.isDownloading = false;
            ToastUtil.showToast(this.mContext, "启动下载失败，请重试");
        } else {
            LogUtil.d("使用SQDownloadManager启动下载，taskId: " + this.currentTaskId);
            this.mCurrentType = SQDownloadManager.DOWNLOADING;
            updateDownloadLayoutForType();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetProgressState() {
        this.lastCountMb = 0L;
        this.lastProgress = 0;
        CircularProgressView circularProgressView = this.circularProgressView;
        if (circularProgressView != null) {
            circularProgressView.setProgress(0);
        }
        TextView textView = this.fileSizeTextView;
        if (textView != null) {
            textView.setText("(0%)");
        }
        LogUtil.d("进度状态已重置");
    }

    @Override // com.sqwan.afinal.download.SQDownloadManager.DialogStateUpdater
    public void updateDialogState(final String str) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.10
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = SQApkDownloadDialog.this.mCurrentType;
                    SQApkDownloadDialog.this.mCurrentType = str;
                    if (SQDownloadManager.INSTALL.equals(str)) {
                        SQApkDownloadDialog.this.isDownloading = false;
                        LogUtil.d("下载完成，准备安装");
                    } else if (SQDownloadManager.DOWNLOAD.equals(str) && SQDownloadManager.DOWNLOADING.equals(str2)) {
                        SQApkDownloadDialog.this.isDownloading = false;
                        SQApkDownloadDialog.this.resetProgressState();
                        ToastUtil.showToast(SQApkDownloadDialog.this.mContext, "下载已取消");
                        LogUtil.d("下载状态重置");
                    }
                    SQApkDownloadDialog.this.updateDownloadLayoutForType();
                }
            });
        }
    }

    @Override // com.sqwan.afinal.download.SQDownloadManager.DialogStateUpdater
    public void dismissDialog() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.11
                @Override // java.lang.Runnable
                public void run() {
                    SQApkDownloadDialog.this.dismiss();
                }
            });
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.currentTaskId = null;
        SQDownloadManager.getInstance().setDialogUpdater(null);
        if (this.progressListener != null) {
            SQDownloadManager.getInstance().unregisterListener(this.progressListener);
            this.progressListener = null;
        }
        this.circularProgressView = null;
        this.fileSizeTextView = null;
        this.downloadLayout = null;
        this.downloadTextView = null;
        super.dismiss();
    }

    public void setType(final String str) {
        if (str.equals(SQDownloadManager.DOWNLOAD)) {
            if (SQDownloadManager.getInstance().checkApkExists(this.mDownLoadBean.getDownload_apk_md5(), this.mDownLoadBean.getPackage_name())) {
                str = SQDownloadManager.INSTALL;
            }
        }
        checkSystemInstallDlyzApp();
        if (SQDownloadManager.INSTALL.equals(str) || SQDownloadManager.OPEN_APP.equals(str)) {
            this.isDownloading = false;
        }
        if (SQDownloadManager.DOWNLOAD.equals(str) && this.isDownloading) {
            this.isDownloading = false;
            this.currentTaskId = null;
        }
        if (this.isDownloading && !SQDownloadManager.DOWNLOAD.equals(str)) {
            LogUtil.i("下载中，不变化UI");
            return;
        }
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.views.SQApkDownloadDialog.12
                @Override // java.lang.Runnable
                public void run() {
                    SQApkDownloadDialog.this.mCurrentType = str;
                    SQApkDownloadDialog.this.updateDownloadLayoutForType();
                }
            });
        }
    }

    private void checkSystemInstallDlyzApp() {
        Context context = this.mContext;
        if (context == null) {
            LogUtil.e("context为null，请检查");
            return;
        }
        if (this.mDownLoadBean == null) {
            LogUtil.e("下载参数为null,请检查");
            return;
        }
        try {
            boolean z = context.getPackageManager().getLaunchIntentForPackage(this.mDownLoadBean.getPackage_name()) != null;
            HashMap map = new HashMap();
            map.put("install_result", z ? "1" : "2");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.IS_SYSTEM_INSTALL_DLYZAPP, map);
        } catch (Exception e) {
            LogUtil.e("检查斗罗app安装状态失败: " + e.getMessage());
            HashMap map2 = new HashMap();
            map2.put("install_result", "2");
            map2.put("error", e.getMessage());
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.IS_SYSTEM_INSTALL_DLYZAPP, map2);
        }
    }
}
