package com.cy.yyjia.zhe28.ui.adapter;

import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ItemDealBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: DealAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nJ\u001c\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DealBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealBinding;", "()V", "confirm", "", ImageSelector.POSITION, "", "success", "Lkotlin/Function0;", IjkMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealAdapter extends BaseAdapter<DealBean, ItemDealBinding> {
    public static final int $stable = 0;

    public DealAdapter() {
        super(R.layout.item_deal, new Function3<BaseDataBindingHolder<ItemDealBinding>, Integer, DealBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.DealAdapter.1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemDealBinding> baseDataBindingHolder, Integer num, DealBean dealBean) {
                invoke(baseDataBindingHolder, num.intValue(), dealBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemDealBinding> holder, int i, DealBean dealBean) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                ItemDealBinding itemDealBinding = (ItemDealBinding) holder.getDataBinding();
                if (itemDealBinding != null) {
                    itemDealBinding.rv.setLayoutManager(new GridLayoutManager(itemDealBinding.rv.getContext(), 3));
                    itemDealBinding.rv.setAdapter(new PicAdapter());
                }
            }
        });
    }

    public final void offset(int position, final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        Repository.INSTANCE.offsetDeal(getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.DealAdapter.offset.1
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
                Toast.makeText(DealAdapter.this.getContext(), it.getMsg(), 0).show();
                if (it.getCode() == 200) {
                    success.invoke();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.DealAdapter.offset.2
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
                Toast.makeText(DealAdapter.this.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }

    public final void confirm(int position, final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        Repository.INSTANCE.confirmDeal(getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.DealAdapter.confirm.1
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
                Toast.makeText(DealAdapter.this.getContext(), it.getMsg(), 0).show();
                if (it.getCode() == 200) {
                    success.invoke();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.DealAdapter.confirm.2
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
                Toast.makeText(DealAdapter.this.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }
}
