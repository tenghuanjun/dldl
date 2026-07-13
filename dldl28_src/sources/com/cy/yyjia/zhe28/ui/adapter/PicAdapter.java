package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ItemDealPicBinding;
import com.cy.yyjia.zhe28.ui.activity.ImageActivity;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PicAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/PicAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemDealPicBinding;", "()V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PicAdapter extends BaseAdapter<String, ItemDealPicBinding> {
    public static final int $stable = 0;

    public PicAdapter() {
        super(R.layout.item_deal_pic, null, 2, null);
        setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.PicAdapter$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PicAdapter._init_$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(PicAdapter this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        Intent intent = new Intent(this$0.getContext(), (Class<?>) ImageActivity.class);
        List<String> data = this$0.getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
        intent.putStringArrayListExtra("images", (ArrayList) data);
        intent.putExtra(ImageSelector.POSITION, i);
        this$0.getContext().startActivity(intent);
    }
}
