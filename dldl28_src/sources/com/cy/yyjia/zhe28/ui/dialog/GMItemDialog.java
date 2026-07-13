package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogGmItemsBinding;
import com.cy.yyjia.zhe28.databinding.ItemGmItemBinding;
import com.cy.yyjia.zhe28.domain.GMItemBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: GMItemDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0006\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B0\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fJ\u0006\u0010\u0018\u001a\u00020\u000bJ\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/GMItemDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogGmItemsBinding;", "fa", "Landroidx/fragment/app/FragmentActivity;", "click", "Lkotlin/Function1;", "Lcom/cy/yyjia/zhe28/domain/GMItemBean;", "Lkotlin/ParameterName;", "name", "role", "", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function1;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/databinding/ItemGmItemBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "data", "", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "search", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMItemDialog extends BaseDataBindingDialog<DialogGmItemsBinding, GMItemDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<GMItemBean, ItemGmItemBinding> adapter;
    public List<GMItemBean> data;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GMItemDialog(FragmentActivity fa, final Function1<? super GMItemBean, Unit> click) {
        super(fa, R.layout.dialog_gm_items);
        Intrinsics.checkNotNullParameter(fa, "fa");
        Intrinsics.checkNotNullParameter(click, "click");
        BaseAdapter<GMItemBean, ItemGmItemBinding> baseAdapter = new BaseAdapter<>(R.layout.item_gm_item, null, 2, null);
        this.adapter = baseAdapter;
        ((DialogGmItemsBinding) this.mBinding).setText("");
        ((DialogGmItemsBinding) this.mBinding).addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMItemDialog.1
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable sender, int propertyId) {
                GMItemDialog.this.search();
            }
        });
        ((DialogGmItemsBinding) this.mBinding).rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMItemDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GMItemDialog._init_$lambda$0(this.f$0, click, baseQuickAdapter, view, i);
            }
        });
        ((DialogGmItemsBinding) this.mBinding).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMItemDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GMItemDialog._init_$lambda$1(this.f$0, view);
            }
        });
    }

    public final BaseAdapter<GMItemBean, ItemGmItemBinding> getAdapter() {
        return this.adapter;
    }

    public final List<GMItemBean> getData() {
        List<GMItemBean> list = this.data;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    /* JADX INFO: renamed from: setData, reason: collision with other method in class */
    public final void m6586setData(List<GMItemBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(GMItemDialog this$0, Function1 click, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(click, "$click");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        int size = this$0.getData().size();
        for (int i2 = 0; i2 < size; i2++) {
            this$0.getData().get(i2).setSelected(this$0.getData().get(i2).getId() == this$0.adapter.getItem(i).getId());
        }
        click.invoke(this$0.adapter.getItem(i));
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(GMItemDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final GMItemDialog setData(List<GMItemBean> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        m6586setData(data);
        this.adapter.setNewInstance(data);
        return this;
    }

    public final void search() {
        String text = ((DialogGmItemsBinding) this.mBinding).getText();
        Intrinsics.checkNotNull(text);
        if (text.length() == 0) {
            this.adapter.setNewInstance(getData());
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (GMItemBean gMItemBean : getData()) {
            String item_name = gMItemBean.getItem_name();
            String text2 = ((DialogGmItemsBinding) this.mBinding).getText();
            Intrinsics.checkNotNull(text2);
            if (StringsKt.contains$default((CharSequence) item_name, (CharSequence) text2, false, 2, (Object) null)) {
                arrayList.add(gMItemBean);
            }
        }
        this.adapter.setNewInstance(arrayList);
    }
}
