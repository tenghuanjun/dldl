package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogBbsGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsGameBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BbsGameDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B0\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fJ\u0014\u0010\u0017\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0013j\b\u0012\u0004\u0012\u00020\u0007`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/BbsGameDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogBbsGameBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "onSelect", "Lkotlin/Function1;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lkotlin/ParameterName;", "name", "game", "", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function1;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "data", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsGameDialog extends BaseDataBindingDialog<DialogBbsGameBinding, BbsGameDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<GameBean, ItemBbsGameBinding> adapter;
    private final ArrayList<GameBean> data;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BbsGameDialog(FragmentActivity activity, final Function1<? super GameBean, Unit> onSelect) {
        super(activity, R.layout.dialog_bbs_game);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        BaseAdapter<GameBean, ItemBbsGameBinding> baseAdapter = new BaseAdapter<>(R.layout.item_bbs_game, null, 2, null);
        this.adapter = baseAdapter;
        this.data = new ArrayList<>();
        ((DialogBbsGameBinding) this.mBinding).tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.BbsGameDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsGameDialog._init_$lambda$0(this.f$0, view);
            }
        });
        ((DialogBbsGameBinding) this.mBinding).rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.BbsGameDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsGameDialog._init_$lambda$1(onSelect, this, baseQuickAdapter, view, i);
            }
        });
        ((DialogBbsGameBinding) this.mBinding).addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.cy.yyjia.zhe28.ui.dialog.BbsGameDialog.3
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable sender, int propertyId) {
                ArrayList arrayList = new ArrayList();
                for (GameBean gameBean : BbsGameDialog.this.getData()) {
                    String name = gameBean.getName();
                    String game = ((DialogBbsGameBinding) BbsGameDialog.this.mBinding).getGame();
                    Intrinsics.checkNotNull(game);
                    if (StringsKt.contains$default((CharSequence) name, (CharSequence) game, false, 2, (Object) null)) {
                        arrayList.add(gameBean);
                    }
                }
                BbsGameDialog.this.getAdapter().setNewInstance(arrayList);
            }
        });
    }

    public final BaseAdapter<GameBean, ItemBbsGameBinding> getAdapter() {
        return this.adapter;
    }

    public final ArrayList<GameBean> getData() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(BbsGameDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(Function1 onSelect, BbsGameDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(onSelect, "$onSelect");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "view");
        onSelect.invoke(this$0.adapter.getItem(i));
        this$0.dismiss();
    }

    public final BbsGameDialog setData(List<GameBean> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data.addAll(data);
        this.adapter.setNewInstance(data);
        return this;
    }
}
