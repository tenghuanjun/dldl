package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogUnableGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemUnableGameBinding;
import com.cy.yyjia.zhe28.domain.UnableGameBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UnableGameDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0012R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\b0\rj\b\u0012\u0004\u0012\u00020\b`\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/UnableGameDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogUnableGameBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/UnableGameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemUnableGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UnableGameDialog extends BaseDataBindingDialog<DialogUnableGameBinding, UnableGameDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<UnableGameBean, ItemUnableGameBinding> adapter;
    private final ArrayList<UnableGameBean> data;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnableGameDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_unable_game);
        Intrinsics.checkNotNullParameter(activity, "activity");
        BaseAdapter<UnableGameBean, ItemUnableGameBinding> baseAdapter = new BaseAdapter<>(R.layout.item_unable_game, null, 2, null);
        this.adapter = baseAdapter;
        this.data = new ArrayList<>();
        ((DialogUnableGameBinding) this.mBinding).tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.UnableGameDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UnableGameDialog._init_$lambda$0(this.f$0, view);
            }
        });
        ((DialogUnableGameBinding) this.mBinding).rv.setAdapter(baseAdapter);
        ((DialogUnableGameBinding) this.mBinding).addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.cy.yyjia.zhe28.ui.dialog.UnableGameDialog.2
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable sender, int propertyId) {
                ArrayList arrayList = new ArrayList();
                for (UnableGameBean unableGameBean : UnableGameDialog.this.getData()) {
                    String game_name = unableGameBean.getGame_name();
                    String game = ((DialogUnableGameBinding) UnableGameDialog.this.mBinding).getGame();
                    Intrinsics.checkNotNull(game);
                    if (StringsKt.contains$default((CharSequence) game_name, (CharSequence) game, false, 2, (Object) null)) {
                        arrayList.add(unableGameBean);
                    }
                }
                UnableGameDialog.this.getAdapter().setNewInstance(arrayList);
            }
        });
    }

    public final BaseAdapter<UnableGameBean, ItemUnableGameBinding> getAdapter() {
        return this.adapter;
    }

    public final ArrayList<UnableGameBean> getData() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(UnableGameDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final UnableGameDialog setData(List<UnableGameBean> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data.addAll(data);
        this.adapter.setNewInstance(data);
        return this;
    }
}
