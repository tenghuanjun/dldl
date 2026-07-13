package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogWelfare3RoleBinding;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3RoleBinding;
import com.cy.yyjia.zhe28.domain.RoleBean;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoleDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ)\u0010\u000f\u001a\u00020\u00002!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00140\u0011R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/RoleDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogWelfare3RoleBinding;", "Lcom/cy/yyjia/zhe28/ui/dialog/RuleDialog;", "activity", "Landroidx/fragment/app/FragmentActivity;", "data", "", "Lcom/cy/yyjia/zhe28/domain/RoleBean;", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/List;)V", "listAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/databinding/ItemWelfare3RoleBinding;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "setOnSubmitListener", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RoleDialog extends BaseDataBindingDialog<DialogWelfare3RoleBinding, RuleDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<RoleBean, ItemWelfare3RoleBinding> listAdapter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoleDialog(FragmentActivity activity, final List<RoleBean> data) {
        super(activity, R.layout.dialog_welfare3_role);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(data, "data");
        BaseAdapter<RoleBean, ItemWelfare3RoleBinding> baseAdapter = new BaseAdapter<>(R.layout.item_welfare3_role, data);
        this.listAdapter = baseAdapter;
        ((DialogWelfare3RoleBinding) this.mBinding).rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.RoleDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                RoleDialog._init_$lambda$0(data, this, baseQuickAdapter, view, i);
            }
        });
        ((DialogWelfare3RoleBinding) this.mBinding).tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.RoleDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RoleDialog._init_$lambda$1(this.f$0, view);
            }
        });
    }

    public final BaseAdapter<RoleBean, ItemWelfare3RoleBinding> getListAdapter() {
        return this.listAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(List data, RoleDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Iterator it = data.iterator();
        while (it.hasNext()) {
            RoleBean roleBean = (RoleBean) it.next();
            roleBean.setSelected(roleBean.getId() == this$0.listAdapter.getItem(i).getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(RoleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final RoleDialog setOnSubmitListener(final Function1<? super RoleBean, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((DialogWelfare3RoleBinding) this.mBinding).tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.RoleDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RoleDialog.setOnSubmitListener$lambda$2(this.f$0, listener, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnSubmitListener$lambda$2(RoleDialog this$0, Function1 listener, View view) {
        RoleBean next;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Iterator<RoleBean> it = this$0.listAdapter.getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.getSelected()) {
                    break;
                }
            }
        }
        if (next == null) {
            this$0.toast("请先选择角色");
        } else {
            listener.invoke(next);
            this$0.dismiss();
        }
    }
}
