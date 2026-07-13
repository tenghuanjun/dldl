package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogNoviceWelfareBinding;
import com.cy.yyjia.zhe28.databinding.ItemNoviceGameCouponBinding;
import com.cy.yyjia.zhe28.databinding.ItemNoviceGameGiftBinding;
import com.cy.yyjia.zhe28.domain.NoviceGameBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoviceWelfareDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eJ\u0014\u0010\u001f\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/NoviceWelfareDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogNoviceWelfareBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "couponAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/NoviceGameBean$CouponBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemNoviceGameCouponBinding;", "getCouponAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "giftAdapter", "Lcom/cy/yyjia/zhe28/domain/NoviceGameBean$GiftBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemNoviceGameGiftBinding;", "getGiftAdapter", "listener", "Lkotlin/Function0;", "", "getListener", "()Lkotlin/jvm/functions/Function0;", "setListener", "(Lkotlin/jvm/functions/Function0;)V", "receive", "type", "", "id", ImageSelector.POSITION, "setData", "data", "Lcom/cy/yyjia/zhe28/domain/NoviceGameBean;", "setReceiveListener", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class NoviceWelfareDialog extends BaseDataBindingDialog<DialogNoviceWelfareBinding, NoviceWelfareDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<NoviceGameBean.CouponBean, ItemNoviceGameCouponBinding> couponAdapter;
    private final BaseAdapter<NoviceGameBean.GiftBean, ItemNoviceGameGiftBinding> giftAdapter;
    private Function0<Unit> listener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoviceWelfareDialog(final FragmentActivity activity) {
        super(activity, R.layout.dialog_novice_welfare);
        Intrinsics.checkNotNullParameter(activity, "activity");
        BaseAdapter<NoviceGameBean.CouponBean, ItemNoviceGameCouponBinding> baseAdapter = new BaseAdapter<>(R.layout.item_novice_game_coupon, null, 2, null);
        this.couponAdapter = baseAdapter;
        BaseAdapter<NoviceGameBean.GiftBean, ItemNoviceGameGiftBinding> baseAdapter2 = new BaseAdapter<>(R.layout.item_novice_game_gift, null, 2, null);
        this.giftAdapter = baseAdapter2;
        ((DialogNoviceWelfareBinding) this.mBinding).tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.NoviceWelfareDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoviceWelfareDialog._init_$lambda$0(this.f$0, view);
            }
        });
        ((DialogNoviceWelfareBinding) this.mBinding).rvCoupon.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.NoviceWelfareDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NoviceWelfareDialog._init_$lambda$1(this.f$0, activity, baseQuickAdapter, view, i);
            }
        });
        ((DialogNoviceWelfareBinding) this.mBinding).rvGift.setAdapter(baseAdapter2);
        baseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.NoviceWelfareDialog$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NoviceWelfareDialog._init_$lambda$2(this.f$0, activity, baseQuickAdapter, view, i);
            }
        });
    }

    public final BaseAdapter<NoviceGameBean.CouponBean, ItemNoviceGameCouponBinding> getCouponAdapter() {
        return this.couponAdapter;
    }

    public final BaseAdapter<NoviceGameBean.GiftBean, ItemNoviceGameGiftBinding> getGiftAdapter() {
        return this.giftAdapter;
    }

    public final Function0<Unit> getListener() {
        return this.listener;
    }

    public final void setListener(Function0<Unit> function0) {
        this.listener = function0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(NoviceWelfareDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(NoviceWelfareDialog this$0, FragmentActivity activity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Function0<Unit> function0 = this$0.listener;
        if (function0 != null) {
            function0.invoke();
        }
        if (this$0.couponAdapter.getItem(i).getIsbig() == 1) {
            this$0.receive(2, this$0.couponAdapter.getItem(i).getId(), i);
        } else {
            new ReceiveStatusDialog(activity).set(2, this$0.couponAdapter.getItem(i).getId(), this$0.couponAdapter.getItem(i).getGameId()).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(NoviceWelfareDialog this$0, FragmentActivity activity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Function0<Unit> function0 = this$0.listener;
        if (function0 != null) {
            function0.invoke();
        }
        new ReceiveStatusDialog(activity).set(1, this$0.giftAdapter.getItem(i).getId(), this$0.giftAdapter.getItem(i).getGameId()).show();
    }

    public final NoviceWelfareDialog setData(NoviceGameBean data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.giftAdapter.setNewInstance(data.getGift());
        this.couponAdapter.setNewInstance(data.getYhq());
        if (data.getGift().isEmpty()) {
            ((DialogNoviceWelfareBinding) this.mBinding).rvGift.setVisibility(8);
        }
        if (data.getYhq().isEmpty()) {
            ((DialogNoviceWelfareBinding) this.mBinding).rvCoupon.setVisibility(8);
        }
        return this;
    }

    public final NoviceWelfareDialog setReceiveListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        return this;
    }

    public final void receive(final int type, int id, final int position) {
        Repository.INSTANCE.receive(type, id, "0", new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.NoviceWelfareDialog.receive.1
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
                NoviceWelfareDialog.this.toast(it.getMsg());
                if (type == 1) {
                    NoviceWelfareDialog.this.getGiftAdapter().getItem(position).setGet(1);
                    NoviceWelfareDialog.this.getGiftAdapter().notifyItemChanged(position);
                } else {
                    NoviceWelfareDialog.this.getCouponAdapter().getItem(position).setGet(1);
                    NoviceWelfareDialog.this.getCouponAdapter().notifyItemChanged(position);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.NoviceWelfareDialog.receive.2
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
                NoviceWelfareDialog.this.netFail(it);
            }
        });
    }
}
