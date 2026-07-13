package com.cy.yyjia.zhe28.ui.activity;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemInviteWithdrewRecordBinding;
import com.cy.yyjia.zhe28.domain.InviteBillBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteBillActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/InviteBillActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "page", "", "getPage", "()I", "setPage", "(I)V", "withdrewAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/InviteBillBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemInviteWithdrewRecordBinding;", "getWithdrewAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "withdrewAdapter$delegate", "Lkotlin/Lazy;", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class InviteBillActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;
    private int page;

    /* JADX INFO: renamed from: withdrewAdapter$delegate, reason: from kotlin metadata */
    private final Lazy withdrewAdapter;

    public InviteBillActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.withdrewAdapter = LazyKt.lazy(new Function0<BaseAdapter<InviteBillBean, ItemInviteWithdrewRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteBillActivity$withdrewAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<InviteBillBean, ItemInviteWithdrewRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_invite_withdrew_record, null, 2, null);
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(InviteBillActivity inviteBillActivity) {
        return inviteBillActivity.getMBinding();
    }

    public final BaseAdapter<InviteBillBean, ItemInviteWithdrewRecordBinding> getWithdrewAdapter() {
        return (BaseAdapter) this.withdrewAdapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("账单");
        getMBinding().rv.setAdapter(getWithdrewAdapter());
        getWithdrewAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteBillActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                InviteBillActivity.init$lambda$0(this.f$0);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteBillActivity$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                InviteBillActivity.init$lambda$1(this.f$0, refreshLayout);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(InviteBillActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(InviteBillActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getData();
    }

    public final void getData() {
        Repository.INSTANCE.getInviteWithdrewRecord(this.page, new Function1<PageBean<InviteBillBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteBillActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<InviteBillBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<InviteBillBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                InviteBillActivity.access$getMBinding(InviteBillActivity.this).srl.finishRefresh();
                if (InviteBillActivity.this.getPage() == 1) {
                    InviteBillActivity.this.getWithdrewAdapter().setNewInstance(it.getList());
                } else {
                    InviteBillActivity.this.getWithdrewAdapter().addData(it.getList());
                }
                InviteBillActivity inviteBillActivity = InviteBillActivity.this;
                inviteBillActivity.setPage(inviteBillActivity.getPage() + 1);
                inviteBillActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(InviteBillActivity.this.getWithdrewAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    InviteBillActivity.this.getWithdrewAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteBillActivity.getData.2
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
                InviteBillActivity.this.netFail(it);
                InviteBillActivity.access$getMBinding(InviteBillActivity.this).srl.finishRefresh(false);
                InviteBillActivity.this.getWithdrewAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }
}
