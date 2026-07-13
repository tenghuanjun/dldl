package com.cy.yyjia.zhe28.ui.activity;

import android.text.format.Formatter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityDownloadBinding;
import com.cy.yyjia.zhe28.databinding.ItemDownloadBinding;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.util.Util;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: DownloadManagerActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0015H\u0014R\u001e\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DownloadManagerActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDownloadBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/activity/DownloadManagerActivity$ListAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/activity/DownloadManagerActivity$ListAdapter;", "setAdapter", "(Lcom/cy/yyjia/zhe28/ui/activity/DownloadManagerActivity$ListAdapter;)V", "all", "", "getAll", "()Z", "setAll", "(Z)V", "manager", "getManager", "setManager", "checkAll", "", "downloadEvent", "downloadTask", "Lcom/lzy/okserver/download/DownloadTask;", "init", "onClick", "v", "Landroid/view/View;", "onResume", "ListAdapter", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DownloadManagerActivity extends BaseActivity<ActivityDownloadBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    public ListAdapter adapter;
    private boolean all;
    private boolean manager;

    public DownloadManagerActivity() {
        super(R.layout.activity_download, 0, 2, null);
    }

    public static final /* synthetic */ ActivityDownloadBinding access$getMBinding(DownloadManagerActivity downloadManagerActivity) {
        return downloadManagerActivity.getMBinding();
    }

    public final boolean getManager() {
        return this.manager;
    }

    public final void setManager(boolean z) {
        this.manager = z;
    }

    public final ListAdapter getAdapter() {
        ListAdapter listAdapter = this.adapter;
        if (listAdapter != null) {
            return listAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public final void setAdapter(ListAdapter listAdapter) {
        Intrinsics.checkNotNullParameter(listAdapter, "<set-?>");
        this.adapter = listAdapter;
    }

    public final boolean getAll() {
        return this.all;
    }

    public final void setAll(boolean z) {
        this.all = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DownloadManagerActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadManagerActivity.init$lambda$0(this.f$0, view);
            }
        });
        List<DownloadTask> listRestore = OkDownload.restore(DownloadManager.getInstance().getAll());
        Intrinsics.checkNotNullExpressionValue(listRestore, "restore(...)");
        setAdapter(new ListAdapter(this, listRestore));
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DownloadManagerActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DownloadManagerActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().addChildClickViewIds(R.id.f438tv);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DownloadManagerActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DownloadManagerActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setEmptyView(R.layout.layout_empty);
        FrameLayout emptyLayout = getAdapter().getEmptyLayout();
        if (emptyLayout != null) {
            ((TextView) emptyLayout.findViewById(R.id.f438tv)).setText("暂时没有下载任务");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(DownloadManagerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = this$0.manager;
        this$0.manager = !z;
        if (!z) {
            this$0.checkAll();
            this$0.getMBinding().llManager.setVisibility(0);
            this$0.getMBinding().navigation.setMoreText("取消");
        } else {
            this$0.getMBinding().llManager.setVisibility(8);
            this$0.getMBinding().navigation.setMoreText("编辑");
        }
        this$0.getAdapter().notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(DownloadManagerActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.manager) {
            Serializable serializable = this$0.getAdapter().getItem(i).progress.extra1;
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.AppInfo");
            ((AppInfo) serializable).setSelected(!r2.getSelected());
            this$0.checkAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(DownloadManagerActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.downloadEvent(this$0.getAdapter().getItem(i));
    }

    public final void downloadEvent(DownloadTask downloadTask) {
        Intrinsics.checkNotNullParameter(downloadTask, "downloadTask");
        int i = downloadTask.progress.status;
        if (i != 0) {
            if (i == 1 || i == 2) {
                downloadTask.pause();
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    Util.installApk(getMContext(), new File(downloadTask.progress.folder, downloadTask.progress.fileName), downloadTask);
                    return;
                } else {
                    toast("下载出错，请重新下载");
                    FragmentActivity mContext = getMContext();
                    String tag = downloadTask.progress.tag;
                    Intrinsics.checkNotNullExpressionValue(tag, "tag");
                    Util.gotoGame(mContext, Integer.parseInt(tag));
                    downloadTask.remove(true);
                    return;
                }
            }
        }
        downloadTask.start();
    }

    public final void checkAll() {
        this.all = true;
        Iterator<DownloadTask> it = getAdapter().getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Serializable serializable = it.next().progress.extra1;
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.AppInfo");
            if (!((AppInfo) serializable).getSelected()) {
                this.all = false;
                break;
            }
        }
        getMBinding().tvAll.setText(this.all ? "取消全选" : "全选");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getAdapter().setNewInstance(OkDownload.restore(DownloadManager.getInstance().getAll()));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.tv_all) {
            Iterator<DownloadTask> it = getAdapter().getData().iterator();
            while (it.hasNext()) {
                Serializable serializable = it.next().progress.extra1;
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.AppInfo");
                ((AppInfo) serializable).setSelected(!this.all);
            }
            getAdapter().notifyDataSetChanged();
            checkAll();
            return;
        }
        if (v.getId() == R.id.tv_delete) {
            new ConfirmDialog(this).setTip("确定要删除选中的下载任务吗？").setBtnText("删除").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DownloadManagerActivity.onClick.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    for (DownloadTask downloadTask : DownloadManagerActivity.this.getAdapter().getData()) {
                        Serializable serializable2 = downloadTask.progress.extra1;
                        Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.AppInfo");
                        if (((AppInfo) serializable2).getSelected()) {
                            downloadTask.remove(true);
                        }
                    }
                    DownloadManagerActivity.this.getAdapter().setNewInstance(OkDownload.restore(DownloadManager.getInstance().getAll()));
                    DownloadManagerActivity.access$getMBinding(DownloadManagerActivity.this).llManager.setVisibility(8);
                    DownloadManagerActivity.access$getMBinding(DownloadManagerActivity.this).navigation.setMoreText("编辑");
                }
            }).show();
        }
    }

    /* JADX INFO: compiled from: DownloadManagerActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0014J\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DownloadManagerActivity$ListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/lzy/okserver/download/DownloadTask;", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemDownloadBinding;", "data", "", "(Lcom/cy/yyjia/zhe28/ui/activity/DownloadManagerActivity;Ljava/util/List;)V", "convert", "", "holder", "item", IjkMediaMeta.IJKM_KEY_FORMAT, "db", "p0", "Lcom/lzy/okgo/model/Progress;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ListAdapter extends BaseQuickAdapter<DownloadTask, BaseDataBindingHolder<ItemDownloadBinding>> {
        final /* synthetic */ DownloadManagerActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ListAdapter(DownloadManagerActivity downloadManagerActivity, List<DownloadTask> data) {
            super(R.layout.item_download, data);
            Intrinsics.checkNotNullParameter(data, "data");
            this.this$0 = downloadManagerActivity;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(BaseDataBindingHolder<ItemDownloadBinding> holder, final DownloadTask item) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(item, "item");
            try {
                final ItemDownloadBinding itemDownloadBinding = (ItemDownloadBinding) holder.getDataBinding();
                if (itemDownloadBinding != null) {
                    final DownloadManagerActivity downloadManagerActivity = this.this$0;
                    itemDownloadBinding.setManager(downloadManagerActivity.getManager());
                    Serializable serializable = item.progress.extra1;
                    Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.AppInfo");
                    itemDownloadBinding.setData((AppInfo) serializable);
                    itemDownloadBinding.setProgress(item.progress);
                    Progress progress = itemDownloadBinding.getProgress();
                    Intrinsics.checkNotNull(progress);
                    format(itemDownloadBinding, progress);
                    final String str = item.progress.tag;
                    item.register(new DownloadListener(str) { // from class: com.cy.yyjia.zhe28.ui.activity.DownloadManagerActivity$ListAdapter$convert$1$1
                        @Override // com.lzy.okserver.ProgressListener
                        public void onStart(Progress p0) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            this.this$0.format(itemDownloadBinding, p0);
                        }

                        @Override // com.lzy.okserver.ProgressListener
                        public void onProgress(Progress p0) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            this.this$0.format(itemDownloadBinding, p0);
                        }

                        @Override // com.lzy.okserver.ProgressListener
                        public void onError(Progress p0) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            this.this$0.format(itemDownloadBinding, p0);
                        }

                        @Override // com.lzy.okserver.ProgressListener
                        public void onFinish(File p0, Progress p1) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            Intrinsics.checkNotNullParameter(p1, "p1");
                            this.this$0.format(itemDownloadBinding, p1);
                            Util.installApk(downloadManagerActivity.getMContext(), p0, item);
                        }

                        @Override // com.lzy.okserver.ProgressListener
                        public void onRemove(Progress p0) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            this.this$0.format(itemDownloadBinding, p0);
                        }
                    });
                }
            } catch (Exception unused) {
                this.this$0.toast("下载出错，请前往设置使用下载修复功能");
            }
        }

        public final void format(ItemDownloadBinding db, Progress p0) {
            Intrinsics.checkNotNullParameter(db, "db");
            Intrinsics.checkNotNullParameter(p0, "p0");
            db.setProgress(p0);
            AppInfo data = db.getData();
            Intrinsics.checkNotNull(data);
            String fileSize = Formatter.formatFileSize(this.this$0, p0.currentSize);
            Intrinsics.checkNotNullExpressionValue(fileSize, "formatFileSize(...)");
            data.setCurrentSize(fileSize);
            AppInfo data2 = db.getData();
            Intrinsics.checkNotNull(data2);
            String fileSize2 = Formatter.formatFileSize(this.this$0, p0.totalSize);
            Intrinsics.checkNotNullExpressionValue(fileSize2, "formatFileSize(...)");
            data2.setTotalSize(fileSize2);
            AppInfo data3 = db.getData();
            Intrinsics.checkNotNull(data3);
            data3.setProgress(MathKt.roundToInt(p0.fraction * 100));
        }
    }
}
