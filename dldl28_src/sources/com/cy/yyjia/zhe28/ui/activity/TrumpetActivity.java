package com.cy.yyjia.zhe28.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.ActivityTrumpetBinding;
import com.cy.yyjia.zhe28.databinding.ItemTrumpet1Binding;
import com.cy.yyjia.zhe28.databinding.ItemTrumpet2Binding;
import com.cy.yyjia.zhe28.domain.AccountListBean;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.activity.TrumpetActivity;
import com.cy.yyjia.zhe28.ui.dialog.PinDialog;
import com.cy.yyjia.zhe28.ui.dialog.TipDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TrumpetActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0013J\b\u0010\u0017\u001a\u00020\u0013H\u0016R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/TrumpetActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityTrumpetBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/AccountListBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemTrumpet1Binding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "setAdapter", "(Lcom/cy/yyjia/zhe28/base/BaseAdapter;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "batchDelete", "", "v", "Landroid/view/View;", "getData", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TrumpetActivity extends BaseActivity<ActivityTrumpetBinding> {
    public static final int $stable = 8;
    public BaseAdapter<AccountListBean, ItemTrumpet1Binding> adapter;
    private int page;

    public TrumpetActivity() {
        super(R.layout.activity_trumpet, 0, 2, null);
        this.page = 1;
    }

    public static final /* synthetic */ ActivityTrumpetBinding access$getMBinding(TrumpetActivity trumpetActivity) {
        return trumpetActivity.getMBinding();
    }

    public final BaseAdapter<AccountListBean, ItemTrumpet1Binding> getAdapter() {
        BaseAdapter<AccountListBean, ItemTrumpet1Binding> baseAdapter = this.adapter;
        if (baseAdapter != null) {
            return baseAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public final void setAdapter(BaseAdapter<AccountListBean, ItemTrumpet1Binding> baseAdapter) {
        Intrinsics.checkNotNullParameter(baseAdapter, "<set-?>");
        this.adapter = baseAdapter;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrumpetActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().setManager(false);
        setAdapter(new BaseAdapter<>(R.layout.item_trumpet1, new C10852()));
        getMBinding().setGame("");
        getMBinding().et.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return TrumpetActivity.init$lambda$1(this.f$0, textView, i, keyEvent);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                TrumpetActivity.init$lambda$2(this.f$0);
            }
        });
        getAdapter().setMyEmptyView("trumpet");
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(TrumpetActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().setManager(!this$0.getMBinding().getManager());
        this$0.getAdapter().notifyDataSetChanged();
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$init$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TrumpetActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemTrumpet1Binding;", ImageSelector.POSITION, "", "item", "Lcom/cy/yyjia/zhe28/domain/AccountListBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C10852 extends Lambda implements Function3<BaseDataBindingHolder<ItemTrumpet1Binding>, Integer, AccountListBean, Unit> {
        C10852() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemTrumpet1Binding> baseDataBindingHolder, Integer num, AccountListBean accountListBean) {
            invoke(baseDataBindingHolder, num.intValue(), accountListBean);
            return Unit.INSTANCE;
        }

        public final void invoke(BaseDataBindingHolder<ItemTrumpet1Binding> holder, int i, AccountListBean accountListBean) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            ItemTrumpet1Binding itemTrumpet1Binding = (ItemTrumpet1Binding) holder.getDataBinding();
            if (itemTrumpet1Binding != null) {
                final TrumpetActivity trumpetActivity = TrumpetActivity.this;
                Intrinsics.checkNotNull(accountListBean);
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_trumpet2, accountListBean.getAccount_list(), new Function3<BaseDataBindingHolder<ItemTrumpet2Binding>, Integer, DealBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$init$2$1$childAdapter$1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemTrumpet2Binding> baseDataBindingHolder, Integer num, DealBean dealBean) {
                        invoke(baseDataBindingHolder, num.intValue(), dealBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemTrumpet2Binding> h, int i2, DealBean dealBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemTrumpet2Binding itemTrumpet2Binding = (ItemTrumpet2Binding) h.getDataBinding();
                        if (itemTrumpet2Binding != null) {
                            itemTrumpet2Binding.setManager(TrumpetActivity.access$getMBinding(trumpetActivity).getManager());
                        }
                        if (TrumpetActivity.access$getMBinding(trumpetActivity).getManager()) {
                            return;
                        }
                        Intrinsics.checkNotNull(dealBean);
                        dealBean.setSelect(false);
                    }
                });
                itemTrumpet1Binding.rv.setAdapter(baseAdapter);
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$init$2$$ExternalSyntheticLambda0
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                        TrumpetActivity.C10852.invoke$lambda$1$lambda$0(trumpetActivity, baseAdapter, baseQuickAdapter, view, i2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$1$lambda$0(TrumpetActivity this$0, BaseAdapter childAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(childAdapter, "$childAdapter");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            if (TrumpetActivity.access$getMBinding(this$0).getManager()) {
                ((DealBean) childAdapter.getItem(i)).setSelect(!((DealBean) childAdapter.getItem(i)).getSelect());
                childAdapter.notifyDataSetChanged();
                this$0.log("select" + ((DealBean) childAdapter.getItem(i)).getSelect());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean init$lambda$1(TrumpetActivity this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 3) {
            return false;
        }
        textView.clearFocus();
        this$0.hideSoftKeyboard();
        this$0.page = 1;
        this$0.getData();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(TrumpetActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        Repository repository = Repository.INSTANCE;
        String game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        repository.getDealTrumpetList(game, this.page, new Function1<PageBean<AccountListBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<AccountListBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<AccountListBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (TrumpetActivity.this.getPage() == 1) {
                    TrumpetActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    TrumpetActivity.this.getAdapter().addData(it.getList());
                }
                TrumpetActivity trumpetActivity = TrumpetActivity.this;
                trumpetActivity.setPage(trumpetActivity.getPage() + 1);
                trumpetActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(TrumpetActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    TrumpetActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity.getData.2
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
                TrumpetActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                TrumpetActivity.this.netFail(it);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void batchDelete(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        Iterator<AccountListBean> it = getAdapter().getData().iterator();
        while (it.hasNext()) {
            for (DealBean dealBean : it.next().getAccount_list()) {
                if (dealBean.getSelect()) {
                    log("选中的小号id=" + dealBean.getId() + "," + dealBean.getName());
                    objectRef.element = Intrinsics.areEqual(objectRef.element, "") ? String.valueOf(dealBean.getId()) : objectRef.element + "," + dealBean.getId();
                }
            }
        }
        if (Intrinsics.areEqual(objectRef.element, "")) {
            toast("请选择你要删除的小号");
            return;
        }
        log("ids=" + objectRef.element);
        new PinDialog(this).setTitle("删除小号").setTip("删除小号需要输入二级密码，小号删除后不可恢复，请务必谨慎操作！").onSubmit(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity.batchDelete.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$batchDelete$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: TrumpetActivity.kt */
            @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/Result;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            static final class C02261 extends Lambda implements Function1<Result, Unit> {
                final /* synthetic */ TrumpetActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02261(TrumpetActivity trumpetActivity) {
                    super(1);
                    this.this$0 = trumpetActivity;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (it.getCode() == 200) {
                        TipDialog tip = new TipDialog(this.this$0).setTitle("删除成功").setTip("该小号已删除完成！");
                        final TrumpetActivity trumpetActivity = this.this$0;
                        tip.addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity$batchDelete$1$1$$ExternalSyntheticLambda0
                            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
                            public final void onDismiss(BaseDialog baseDialog) {
                                TrumpetActivity.AnonymousClass1.C02261.invoke$lambda$0(trumpetActivity, baseDialog);
                            }
                        }).show();
                        return;
                    }
                    this.this$0.toast(it.getMsg());
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$0(TrumpetActivity this$0, BaseDialog baseDialog) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    this$0.setPage(1);
                    this$0.getData();
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String code) {
                Intrinsics.checkNotNullParameter(code, "code");
                Repository repository = Repository.INSTANCE;
                String str = objectRef.element;
                C02261 c02261 = new C02261(this);
                final TrumpetActivity trumpetActivity = this;
                repository.batchDeleteTrumpet(code, str, c02261, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.TrumpetActivity.batchDelete.1.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                        invoke2(exc);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Exception it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        trumpetActivity.netFail(it2);
                    }
                });
            }
        }).show();
    }
}
