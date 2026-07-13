package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentScheduleBinding;
import com.cy.yyjia.zhe28.domain.NewGameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.ui.adapter.ScheduleAdapter;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScheduleFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\fJ\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/ScheduleFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentScheduleBinding;", "Landroid/view/View$OnClickListener;", "()V", "listAdapter", "Lcom/cy/yyjia/zhe28/ui/adapter/ScheduleAdapter;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/ScheduleAdapter;", "listAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "getNewData", ImageSelector.POSITION, "init", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ScheduleFragment extends BaseFragment<FragmentScheduleBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: listAdapter$delegate, reason: from kotlin metadata */
    private final Lazy listAdapter;
    private int page;

    public ScheduleFragment() {
        super(R.layout.fragment_schedule);
        this.page = 1;
        this.listAdapter = LazyKt.lazy(new Function0<ScheduleAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.ScheduleFragment$listAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ScheduleAdapter invoke() {
                return new ScheduleAdapter();
            }
        });
    }

    public static final /* synthetic */ FragmentScheduleBinding access$getMBinding(ScheduleFragment scheduleFragment) {
        return scheduleFragment.getMBinding();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ScheduleAdapter getListAdapter() {
        return (ScheduleAdapter) this.listAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setOnClick(this);
        getMBinding().setPosition(2);
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.ScheduleFragment$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                ScheduleFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().rv.setAdapter(getListAdapter());
        getListAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.ScheduleFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                ScheduleFragment.init$lambda$1(this.f$0);
            }
        });
        getMBinding().rv.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.ScheduleFragment.init.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                if (ScheduleFragment.this.getListAdapter().getData().size() == 0) {
                    return;
                }
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ScheduleFragment.access$getMBinding(ScheduleFragment.this).rv.getLayoutManager();
                Intrinsics.checkNotNull(linearLayoutManager);
                int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (ScheduleFragment.this.getListAdapter().getItem(iFindFirstVisibleItemPosition).getGame() != null) {
                    ScheduleFragment.access$getMBinding(ScheduleFragment.this).setTime("开服时间 " + ScheduleFragment.this.getListAdapter().getItem(iFindFirstVisibleItemPosition).getNew_service_date());
                }
            }
        });
        getListAdapter().setEmptyView(R.layout.layout_empty);
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(ScheduleFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getListAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(ScheduleFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                getNewData(1);
                break;
            case R.id.tv2 /* 2131362646 */:
                getNewData(2);
                break;
            case R.id.tv3 /* 2131362647 */:
                getNewData(3);
                break;
        }
    }

    public final void getNewData(int position) {
        getMBinding().setPosition(position);
        getMBinding().srl.autoRefresh();
    }

    private final void getData() {
        Repository.getSchedule$default(Repository.INSTANCE, this.page, getMBinding().getPosition(), new Function1<PageBean<NewGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.ScheduleFragment.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<NewGameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<NewGameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ScheduleFragment.access$getMBinding(ScheduleFragment.this).srl.finishRefresh();
                List<NewGameBean> listGroupData = ScheduleFragment.this.getListAdapter().groupData(it.getList());
                if (ScheduleFragment.this.getPage() == 1) {
                    ScheduleFragment.this.getListAdapter().setNewInstance(listGroupData);
                } else {
                    ScheduleFragment.this.getListAdapter().addData((Collection) listGroupData);
                }
                ScheduleFragment scheduleFragment = ScheduleFragment.this;
                scheduleFragment.setPage(scheduleFragment.getPage() + 1);
                scheduleFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(ScheduleFragment.this.getListAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    ScheduleFragment.this.getListAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.ScheduleFragment.getData.2
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
                ScheduleFragment.this.netFail(it);
                ScheduleFragment.access$getMBinding(ScheduleFragment.this).srl.finishRefresh(false);
                ScheduleFragment.this.getListAdapter().getLoadMoreModule().loadMoreFail();
            }
        }, 0, null, 48, null);
    }
}
