package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogDealFilterBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealFilterTypeBinding;
import com.cy.yyjia.zhe28.domain.DealParamBean;
import com.cy.yyjia.zhe28.domain.FilterBean;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealFilterDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0003BK\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012<\u0010\u0006\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0007¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0016J\u001c\u0010\"\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000e\u001a\u00020\rR\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015RP\u0010\u0006\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/DealFilterDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogDealFilterBinding;", "Landroid/view/View$OnClickListener;", "fa", "Landroidx/fragment/app/FragmentActivity;", "click", "Lkotlin/Function2;", "", "Lcom/cy/yyjia/zhe28/domain/FilterBean;", "Lkotlin/ParameterName;", "name", "list", "Lcom/cy/yyjia/zhe28/domain/DealParamBean;", "data", "", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function2;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealFilterTypeBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "getClick", "()Lkotlin/jvm/functions/Function2;", "setClick", "(Lkotlin/jvm/functions/Function2;)V", "selectSort", "getSelectSort", "()Lcom/cy/yyjia/zhe28/domain/FilterBean;", "setSelectSort", "(Lcom/cy/yyjia/zhe28/domain/FilterBean;)V", "onClick", "v", "Landroid/view/View;", "setData", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealFilterDialog extends BaseDataBindingDialog<DialogDealFilterBinding, DealFilterDialog> implements View.OnClickListener {
    public static final int $stable = 8;
    private final BaseAdapter<FilterBean, ItemDealFilterTypeBinding> adapter;
    private Function2<? super List<FilterBean>, ? super DealParamBean, Unit> click;
    private FilterBean selectSort;

    public final Function2<List<FilterBean>, DealParamBean, Unit> getClick() {
        return this.click;
    }

    public final void setClick(Function2<? super List<FilterBean>, ? super DealParamBean, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.click = function2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DealFilterDialog(FragmentActivity fa, Function2<? super List<FilterBean>, ? super DealParamBean, Unit> click) {
        super(fa, R.layout.dialog_deal_filter);
        Intrinsics.checkNotNullParameter(fa, "fa");
        Intrinsics.checkNotNullParameter(click, "click");
        this.click = click;
        BaseAdapter<FilterBean, ItemDealFilterTypeBinding> baseAdapter = new BaseAdapter<>(R.layout.item_deal_filter_type, null, 2, null);
        this.adapter = baseAdapter;
        ((DialogDealFilterBinding) this.mBinding).rvType.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.DealFilterDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealFilterDialog._init_$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        ((DialogDealFilterBinding) this.mBinding).setOnClick(this);
    }

    public final BaseAdapter<FilterBean, ItemDealFilterTypeBinding> getAdapter() {
        return this.adapter;
    }

    public final FilterBean getSelectSort() {
        return this.selectSort;
    }

    public final void setSelectSort(FilterBean filterBean) {
        this.selectSort = filterBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(DealFilterDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        int size = this$0.adapter.getData().size();
        int i2 = 0;
        while (i2 < size) {
            this$0.adapter.getItem(i2).setSelected(i2 == i);
            i2++;
        }
        this$0.selectSort = this$0.adapter.getItem(i);
    }

    public final DealFilterDialog setData(List<FilterBean> list, DealParamBean data) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(data, "data");
        this.adapter.setNewInstance(list);
        Iterator<FilterBean> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FilterBean next = it.next();
            if (next.getSelected()) {
                this.selectSort = next;
                break;
            }
        }
        ((DialogDealFilterBinding) this.mBinding).setData(data);
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.btn1) {
            ((DialogDealFilterBinding) this.mBinding).setData(new DealParamBean(null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 4095, null));
            int size = this.adapter.getData().size();
            int i = 0;
            while (i < size) {
                this.adapter.getItem(i).setSelected(i == 0);
                i++;
            }
            return;
        }
        if (id != R.id.btn2) {
            if (id != R.id.iv_close) {
                return;
            }
            dismiss();
            return;
        }
        DealParamBean data = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data);
        FilterBean filterBean = this.selectSort;
        Intrinsics.checkNotNull(filterBean);
        data.setSort(filterBean.getSort());
        DealParamBean data2 = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data2);
        FilterBean filterBean2 = this.selectSort;
        Intrinsics.checkNotNull(filterBean2);
        data2.setOrder(filterBean2.getData());
        DealParamBean data3 = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data3);
        FilterBean filterBean3 = this.selectSort;
        Intrinsics.checkNotNull(filterBean3);
        data3.setIsopen(Intrinsics.areEqual(filterBean3.getName(), "开局号") ? 1 : 0);
        DealParamBean data4 = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data4);
        FilterBean filterBean4 = this.selectSort;
        Intrinsics.checkNotNull(filterBean4);
        data4.setIsregress(Intrinsics.areEqual(filterBean4.getName(), "回归号") ? 1 : 0);
        DealParamBean data5 = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data5);
        FilterBean filterBean5 = this.selectSort;
        Intrinsics.checkNotNull(filterBean5);
        data5.setSiteaccount(Intrinsics.areEqual(filterBean5.getName(), "官方捡漏") ? 1 : 0);
        DealParamBean data6 = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data6);
        FilterBean filterBean6 = this.selectSort;
        Intrinsics.checkNotNull(filterBean6);
        data6.setIsdolo(Intrinsics.areEqual(filterBean6.getName(), "官方斗罗账号") ? 1 : 0);
        Function2<? super List<FilterBean>, ? super DealParamBean, Unit> function2 = this.click;
        List<FilterBean> data7 = this.adapter.getData();
        DealParamBean data8 = ((DialogDealFilterBinding) this.mBinding).getData();
        Intrinsics.checkNotNull(data8);
        function2.invoke(data7, data8);
        dismiss();
    }
}
