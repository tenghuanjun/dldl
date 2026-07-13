package com.cy.yyjia.zhe28.ui.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ItemRecycleAccountBinding;
import com.cy.yyjia.zhe28.databinding.ItemRecycleBinding;
import com.cy.yyjia.zhe28.domain.RecycleListBean;
import com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecycleAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/RecycleAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/RecycleListBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemRecycleBinding;", "mode", "", "(I)V", "AccountAdapter", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RecycleAdapter extends BaseAdapter<RecycleListBean, ItemRecycleBinding> {
    public static final int $stable = 8;
    private int mode;

    public RecycleAdapter(final int i) {
        super(R.layout.item_recycle, new Function3<BaseDataBindingHolder<ItemRecycleBinding>, Integer, RecycleListBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemRecycleBinding> baseDataBindingHolder, Integer num, RecycleListBean recycleListBean) {
                invoke(baseDataBindingHolder, num.intValue(), recycleListBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemRecycleBinding> h, int i2, final RecycleListBean recycleListBean) {
                Intrinsics.checkNotNullParameter(h, "h");
                ItemRecycleBinding itemRecycleBinding = (ItemRecycleBinding) h.getDataBinding();
                if (itemRecycleBinding != null) {
                    itemRecycleBinding.setMode(i);
                }
                AccountAdapter accountAdapter = new AccountAdapter(i, new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter$1$adapter$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i3) {
                        RecycleListBean recycleListBean2 = recycleListBean;
                        if (recycleListBean2 == null) {
                            return;
                        }
                        recycleListBean2.setSelectType(i3);
                    }
                });
                ItemRecycleBinding itemRecycleBinding2 = (ItemRecycleBinding) h.getDataBinding();
                RecyclerView recyclerView = itemRecycleBinding2 != null ? itemRecycleBinding2.rv : null;
                Intrinsics.checkNotNull(recyclerView);
                recyclerView.setAdapter(accountAdapter);
            }
        });
        this.mode = i;
        setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                RecycleAdapter._init_$lambda$0(this.f$0, baseQuickAdapter, view, i2);
            }
        });
        addChildClickViewIds(R.id.ll_box);
        setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                RecycleAdapter._init_$lambda$1(this.f$0, baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(RecycleAdapter this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getItem(i).setSelected(!this$0.getItem(i).getSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(RecycleAdapter this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.ll_box) {
            int selectType = this$0.getItem(i).getSelectType();
            boolean z = true;
            if (selectType != 0 && selectType == 2) {
                z = false;
            }
            this$0.getItem(i).setSelectType(z ? 2 : 0);
            Iterator<RecycleListBean.Account> it = this$0.getItem(i).getAccounts().iterator();
            while (it.hasNext()) {
                it.next().setSelected(z);
            }
        }
    }

    /* JADX INFO: compiled from: RecycleAdapter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B0\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/RecycleAdapter$AccountAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/RecycleListBean$Account;", "Lcom/cy/yyjia/zhe28/databinding/ItemRecycleAccountBinding;", "mode", "", "select", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "selectType", "", "(ILkotlin/jvm/functions/Function1;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AccountAdapter extends BaseAdapter<RecycleListBean.Account, ItemRecycleAccountBinding> {
        public static final int $stable = 8;
        private int mode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AccountAdapter(final int i, final Function1<? super Integer, Unit> select) {
            super(R.layout.item_recycle_account, new Function3<BaseDataBindingHolder<ItemRecycleAccountBinding>, Integer, RecycleListBean.Account, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter.AccountAdapter.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemRecycleAccountBinding> baseDataBindingHolder, Integer num, RecycleListBean.Account account) {
                    invoke(baseDataBindingHolder, num.intValue(), account);
                    return Unit.INSTANCE;
                }

                public final void invoke(BaseDataBindingHolder<ItemRecycleAccountBinding> h, int i2, RecycleListBean.Account account) {
                    Intrinsics.checkNotNullParameter(h, "h");
                    ItemRecycleAccountBinding itemRecycleAccountBinding = (ItemRecycleAccountBinding) h.getDataBinding();
                    if (itemRecycleAccountBinding == null) {
                        return;
                    }
                    itemRecycleAccountBinding.setMode(i);
                }
            });
            Intrinsics.checkNotNullParameter(select, "select");
            this.mode = i;
            setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter$AccountAdapter$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                    RecycleAdapter.AccountAdapter._init_$lambda$0(this.f$0, select, baseQuickAdapter, view, i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$0(AccountAdapter this$0, Function1 select, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(select, "$select");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            if (this$0.mode == 0) {
                RecycleListBean.Account item = this$0.getItem(i);
                boolean selected = this$0.getItem(i).getSelected();
                int i2 = 1;
                item.setSelected(!selected);
                Iterator<RecycleListBean.Account> it = this$0.getData().iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    if (it.next().getSelected()) {
                        i3++;
                    }
                }
                if (i3 == 0) {
                    i2 = 0;
                } else if (i3 == this$0.getData().size()) {
                    i2 = 2;
                }
                select.invoke(Integer.valueOf(i2));
            }
        }
    }
}
