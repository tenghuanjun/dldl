package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogInviteRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemInviteWithdrawRecordBinding;
import com.cy.yyjia.zhe28.domain.InviteBillBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteWithdrewRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0014\u001a\u00020\u0015R'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/InviteWithdrewRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogInviteRecordBinding;", "fa", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/InviteBillBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemInviteWithdrawRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class InviteWithdrewRecordActivity extends BaseDataBindingDialog<DialogInviteRecordBinding, InviteWithdrewRecordActivity> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InviteWithdrewRecordActivity(FragmentActivity fa) {
        super(fa, R.layout.dialog_invite_record);
        Intrinsics.checkNotNullParameter(fa, "fa");
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<InviteBillBean, ItemInviteWithdrawRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<InviteBillBean, ItemInviteWithdrawRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_invite_withdraw_record, null, 2, null);
            }
        });
        this.page = 1;
        ((DialogInviteRecordBinding) this.mBinding).rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewRecordActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                InviteWithdrewRecordActivity._init_$lambda$0(this.f$0);
            }
        });
        BaseAdapter.setMyEmptyView$default(getAdapter(), null, 1, null);
        ((DialogInviteRecordBinding) this.mBinding).tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewRecordActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteWithdrewRecordActivity._init_$lambda$1(this.f$0, view);
            }
        });
        getData();
    }

    public final BaseAdapter<InviteBillBean, ItemInviteWithdrawRecordBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(InviteWithdrewRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(InviteWithdrewRecordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final void getData() {
        Repository.INSTANCE.getInviteWithdrewRecord(this.page, new Function1<PageBean<InviteBillBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewRecordActivity.getData.1
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
                if (InviteWithdrewRecordActivity.this.getPage() == 1) {
                    InviteWithdrewRecordActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    InviteWithdrewRecordActivity.this.getAdapter().addData(it.getList());
                }
                InviteWithdrewRecordActivity inviteWithdrewRecordActivity = InviteWithdrewRecordActivity.this;
                inviteWithdrewRecordActivity.setPage(inviteWithdrewRecordActivity.getPage() + 1);
                inviteWithdrewRecordActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(InviteWithdrewRecordActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    InviteWithdrewRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewRecordActivity.getData.2
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
                InviteWithdrewRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                InviteWithdrewRecordActivity.this.netFail(it);
            }
        });
    }
}
