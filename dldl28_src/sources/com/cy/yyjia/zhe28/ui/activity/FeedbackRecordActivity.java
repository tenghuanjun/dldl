package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemPrombleBinding;
import com.cy.yyjia.zhe28.domain.FeedbackRecordBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedbackRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/FeedbackRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/FeedbackRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemPrombleBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FeedbackRecordActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    public FeedbackRecordActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<FeedbackRecordBean, ItemPrombleBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<FeedbackRecordBean, ItemPrombleBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_promble, new Function3<BaseDataBindingHolder<ItemPrombleBinding>, Integer, FeedbackRecordBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$adapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemPrombleBinding> baseDataBindingHolder, Integer num, FeedbackRecordBean feedbackRecordBean) {
                        invoke(baseDataBindingHolder, num.intValue(), feedbackRecordBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemPrombleBinding> h, int i, FeedbackRecordBean feedbackRecordBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemPrombleBinding itemPrombleBinding = (ItemPrombleBinding) h.getDataBinding();
                        RecyclerView recyclerView = itemPrombleBinding != null ? itemPrombleBinding.rv : null;
                        Intrinsics.checkNotNull(recyclerView);
                        Intrinsics.checkNotNull(feedbackRecordBean);
                        recyclerView.setAdapter(new BaseAdapter(R.layout.item_feedback_pic, feedbackRecordBean.getFiles()));
                    }
                });
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(FeedbackRecordActivity feedbackRecordActivity) {
        return feedbackRecordActivity.getMBinding();
    }

    public final BaseAdapter<FeedbackRecordBean, ItemPrombleBinding> getAdapter() {
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
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                FeedbackRecordActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().navigation.setTitle("我的反馈");
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                FeedbackRecordActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.tv_delete);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FeedbackRecordActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(FeedbackRecordActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(FeedbackRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(final FeedbackRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        new ConfirmDialog(this$0).setTitle("删除提醒").setTip("确定要删除该反馈吗").setBtnText("删除").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$init$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                Repository repository = Repository.INSTANCE;
                int id = this.this$0.getAdapter().getItem(i).getId();
                final FeedbackRecordActivity feedbackRecordActivity = this.this$0;
                final int i2 = i;
                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$init$3$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                        invoke2(result);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Result it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        feedbackRecordActivity.toast(it.getMsg());
                        if (it.getCode() == 200) {
                            feedbackRecordActivity.getAdapter().removeAt(i2);
                        }
                    }
                };
                final FeedbackRecordActivity feedbackRecordActivity2 = this.this$0;
                repository.deleteFeedback(id, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity$init$3$1.2
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
                        feedbackRecordActivity2.netFail(it);
                    }
                });
            }
        }).show();
    }

    public final void getData() {
        Repository.INSTANCE.getFeedbackRecord(this.page, new Function1<PageBean<FeedbackRecordBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<FeedbackRecordBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<FeedbackRecordBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FeedbackRecordActivity.access$getMBinding(FeedbackRecordActivity.this).srl.finishRefresh();
                if (FeedbackRecordActivity.this.getPage() == 1) {
                    FeedbackRecordActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    FeedbackRecordActivity.this.getAdapter().addData(it.getList());
                }
                FeedbackRecordActivity feedbackRecordActivity = FeedbackRecordActivity.this;
                feedbackRecordActivity.setPage(feedbackRecordActivity.getPage() + 1);
                feedbackRecordActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(FeedbackRecordActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    FeedbackRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackRecordActivity.getData.2
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
                FeedbackRecordActivity.access$getMBinding(FeedbackRecordActivity.this).srl.finishRefresh(false);
                FeedbackRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                FeedbackRecordActivity.this.netFail(it);
            }
        });
    }
}
