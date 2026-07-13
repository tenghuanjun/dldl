package com.cy.yyjia.zhe28.ui.activity;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityQiandaoRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoRecordBinding;
import com.cy.yyjia.zhe28.domain.QiandaoRecordBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QiandaoRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/QiandaoRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityQiandaoRecordBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/QiandaoRecordBean$Data;", "Lcom/cy/yyjia/zhe28/databinding/ItemQiandaoRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class QiandaoRecordActivity extends BaseActivity<ActivityQiandaoRecordBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    public static final /* synthetic */ ActivityQiandaoRecordBinding access$getMBinding(QiandaoRecordActivity qiandaoRecordActivity) {
        return qiandaoRecordActivity.getMBinding();
    }

    public QiandaoRecordActivity() {
        super(R.layout.activity_qiandao_record, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<QiandaoRecordBean.Data, ItemQiandaoRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<QiandaoRecordBean.Data, ItemQiandaoRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_qiandao_record, null, 2, null);
            }
        });
        this.page = 1;
    }

    public final BaseAdapter<QiandaoRecordBean.Data, ItemQiandaoRecordBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoRecordActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                QiandaoRecordActivity.init$lambda$0(this.f$0);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(QiandaoRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        Repository.INSTANCE.getQiandaoRecord(this.page, new Function1<QiandaoRecordBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoRecordActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(QiandaoRecordBean qiandaoRecordBean) {
                invoke2(qiandaoRecordBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(QiandaoRecordBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (QiandaoRecordActivity.this.getPage() == 1) {
                    QiandaoRecordActivity.access$getMBinding(QiandaoRecordActivity.this).setData(it);
                    QiandaoRecordActivity.this.getAdapter().setNewInstance(it.getList().getList());
                } else {
                    QiandaoRecordActivity.this.getAdapter().addData(it.getList().getList());
                }
                QiandaoRecordActivity qiandaoRecordActivity = QiandaoRecordActivity.this;
                qiandaoRecordActivity.setPage(qiandaoRecordActivity.getPage() + 1);
                qiandaoRecordActivity.getPage();
                if (it.getList().getCurrent_page() >= it.getList().getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(QiandaoRecordActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    QiandaoRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoRecordActivity.getData.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                QiandaoRecordActivity.this.netFail(it);
                QiandaoRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }
}
