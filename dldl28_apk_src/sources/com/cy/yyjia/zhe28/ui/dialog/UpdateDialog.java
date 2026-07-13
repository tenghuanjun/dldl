package com.cy.yyjia.zhe28.ui.dialog;

import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.DialogUpdateBinding;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.cy.yyjia.zhe28.util.DownloadBroadcast;
import com.cy.yyjia.zhe28.util.Util;
import com.lzy.okgo.model.Progress;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: UpdateDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020'J\b\u0010)\u001a\u00020'H\u0002J\b\u0010*\u001a\u00020'H\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0015\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006+"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/UpdateDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "mActivity", "Landroid/content/Context;", "data", "Lcom/cy/yyjia/zhe28/domain/UpdateBean;", "(Landroid/content/Context;Lcom/cy/yyjia/zhe28/domain/UpdateBean;)V", "apkFile", "Ljava/io/File;", "getApkFile", "()Ljava/io/File;", "setApkFile", "(Ljava/io/File;)V", "getData", "()Lcom/cy/yyjia/zhe28/domain/UpdateBean;", "fileDir", "", "getFileDir", "()Ljava/lang/String;", "fileDir$delegate", "Lkotlin/Lazy;", Progress.FILE_NAME, "getFileName", "fileName$delegate", "mBinding", "Lcom/cy/yyjia/zhe28/databinding/DialogUpdateBinding;", "mDownloadBroadcast", "Lcom/cy/yyjia/zhe28/util/DownloadBroadcast;", "getMDownloadBroadcast", "()Lcom/cy/yyjia/zhe28/util/DownloadBroadcast;", "setMDownloadBroadcast", "(Lcom/cy/yyjia/zhe28/util/DownloadBroadcast;)V", "mDownloadId", "", "getMDownloadId", "()J", "setMDownloadId", "(J)V", "download", "", "install", "queryState", "startQuery", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UpdateDialog extends BaseDialog.Builder<UpdateDialog> {
    public static final int $stable = 8;
    public File apkFile;
    private final UpdateBean data;

    /* JADX INFO: renamed from: fileDir$delegate, reason: from kotlin metadata */
    private final Lazy fileDir;

    /* JADX INFO: renamed from: fileName$delegate, reason: from kotlin metadata */
    private final Lazy fileName;
    private final Context mActivity;
    private DialogUpdateBinding mBinding;
    public DownloadBroadcast mDownloadBroadcast;
    private long mDownloadId;

    public final UpdateBean getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateDialog(Context mActivity, UpdateBean data) {
        super(mActivity);
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(data, "data");
        this.mActivity = mActivity;
        this.data = data;
        this.fileDir = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.dialog.UpdateDialog$fileDir$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.mActivity.getCacheDir().getPath() + "/apk/";
            }
        });
        this.fileName = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.dialog.UpdateDialog$fileName$2
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "助手" + System.currentTimeMillis() + ".apk";
            }
        });
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_update, new FrameLayout(getContext()), false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogUpdateBinding dialogUpdateBinding = (DialogUpdateBinding) viewDataBindingInflate;
        this.mBinding = dialogUpdateBinding;
        setContentView(dialogUpdateBinding.getRoot());
        this.mBinding.setCurrent(100);
        this.mBinding.setMax(100);
        this.mBinding.setNumber("立即更新");
        this.mBinding.setData(data);
        this.mBinding.tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.mBinding.btnDownload.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.UpdateDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpdateDialog._init_$lambda$0(this.f$0, view);
            }
        });
        setCancelable(Intrinsics.areEqual(data.getUpdateForced(), BooleanUtils.NO));
    }

    public final String getFileDir() {
        return (String) this.fileDir.getValue();
    }

    public final String getFileName() {
        return (String) this.fileName.getValue();
    }

    public final File getApkFile() {
        File file = this.apkFile;
        if (file != null) {
            return file;
        }
        Intrinsics.throwUninitializedPropertyAccessException("apkFile");
        return null;
    }

    public final void setApkFile(File file) {
        Intrinsics.checkNotNullParameter(file, "<set-?>");
        this.apkFile = file;
    }

    public final long getMDownloadId() {
        return this.mDownloadId;
    }

    public final void setMDownloadId(long j) {
        this.mDownloadId = j;
    }

    public final DownloadBroadcast getMDownloadBroadcast() {
        DownloadBroadcast downloadBroadcast = this.mDownloadBroadcast;
        if (downloadBroadcast != null) {
            return downloadBroadcast;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mDownloadBroadcast");
        return null;
    }

    public final void setMDownloadBroadcast(DownloadBroadcast downloadBroadcast) {
        Intrinsics.checkNotNullParameter(downloadBroadcast, "<set-?>");
        this.mDownloadBroadcast = downloadBroadcast;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(UpdateDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(this$0.mBinding.getNumber(), "立即更新")) {
            this$0.download();
        } else if (Intrinsics.areEqual(this$0.mBinding.getNumber(), "安装")) {
            this$0.install();
        }
    }

    public final void download() {
        Object systemService = this.mActivity.getSystemService("download");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.data.getUpdateUrl()));
        request.setNotificationVisibility(1);
        request.setMimeType("application/vnd.android.package-archive");
        setApkFile(new File(getFileDir(), getFileName()));
        this.mDownloadId = ((DownloadManager) systemService).enqueue(request);
        startQuery();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
        intentFilter.addAction("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
        intentFilter.addAction("android.intent.action.VIEW_DOWNLOADS");
        setMDownloadBroadcast(new DownloadBroadcast());
        if (Build.VERSION.SDK_INT >= 26) {
            this.mActivity.registerReceiver(getMDownloadBroadcast(), intentFilter, 4);
        } else {
            this.mActivity.registerReceiver(getMDownloadBroadcast(), intentFilter);
        }
    }

    private final void startQuery() {
        this.mBinding.getRoot().post(new Runnable() { // from class: com.cy.yyjia.zhe28.ui.dialog.UpdateDialog$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDialog.startQuery$lambda$2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startQuery$lambda$2(final UpdateDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.queryState();
        this$0.mBinding.getRoot().postDelayed(new Runnable() { // from class: com.cy.yyjia.zhe28.ui.dialog.UpdateDialog$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                UpdateDialog.startQuery$lambda$2$lambda$1(this.f$0);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startQuery$lambda$2$lambda$1(UpdateDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startQuery();
    }

    private final void queryState() {
        String str;
        Object systemService = this.mActivity.getSystemService("download");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
        Cursor cursorQuery = ((DownloadManager) systemService).query(new DownloadManager.Query().setFilterById(this.mDownloadId));
        if (cursorQuery == null) {
            Toast.makeText(this.mActivity, "下载失败", 0).show();
            return;
        }
        if (!cursorQuery.moveToFirst()) {
            Toast.makeText(this.mActivity, "下载失败", 0).show();
            if (cursorQuery.isClosed()) {
                return;
            }
            cursorQuery.close();
            return;
        }
        int i = cursorQuery.getInt(cursorQuery.getColumnIndex("bytes_so_far"));
        int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("total_size"));
        if (i2 > 0) {
            this.mBinding.setCurrent(i);
            this.mBinding.setMax(i2);
            BigDecimal bigDecimalDivide = new BigDecimal(i).multiply(new BigDecimal("100.0")).divide(new BigDecimal(i2), 2, RoundingMode.HALF_UP);
            DialogUpdateBinding dialogUpdateBinding = this.mBinding;
            if (i2 == i) {
                str = "安装";
            } else {
                str = bigDecimalDivide + "%";
            }
            dialogUpdateBinding.setNumber(str);
        }
        if (cursorQuery.isClosed()) {
            return;
        }
        cursorQuery.close();
    }

    public final void install() {
        Object systemService = getContext().getSystemService("download");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(this.mDownloadId);
        Cursor cursorQuery = ((DownloadManager) systemService).query(query);
        if (cursorQuery.moveToFirst()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("local_uri"));
            if (!TextUtils.isEmpty(string)) {
                Util.installApk(getContext(), Uri.parse(string));
            }
        }
        cursorQuery.close();
    }
}
