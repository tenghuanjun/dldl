package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.DialogBbsSignBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsSignBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsSignWelfareBinding;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.cy.yyjia.zhe28.domain.BbsSignBean;
import com.cy.yyjia.zhe28.ui.activity.BbsEditActivity;
import com.cy.yyjia.zhe28.util.NetUtil;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsSignDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0015R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/BbsSignDialog;", "Lcom/cy/yyjia/zhe28/base/QuickDialog;", "", "Lcom/cy/yyjia/zhe28/databinding/DialogBbsSignBinding;", "activity", "Landroid/content/Context;", "data", "Lcom/cy/yyjia/zhe28/domain/BbsDetailBean;", "(Landroid/content/Context;Lcom/cy/yyjia/zhe28/domain/BbsDetailBean;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsSignBean$Day;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsSignBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter2", "Lcom/cy/yyjia/zhe28/domain/BbsSignBean$Welfare;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsSignWelfareBinding;", "getAdapter2", "getData", "()Lcom/cy/yyjia/zhe28/domain/BbsDetailBean;", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsSignDialog extends QuickDialog<String, DialogBbsSignBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<BbsSignBean.Day, ItemBbsSignBinding> adapter;
    private final BaseAdapter<BbsSignBean.Welfare, ItemBbsSignWelfareBinding> adapter2;
    private final BbsDetailBean data;

    public final BbsDetailBean getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BbsSignDialog(final Context activity, BbsDetailBean data) {
        super(activity, R.layout.dialog_bbs_sign);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        BaseAdapter<BbsSignBean.Day, ItemBbsSignBinding> baseAdapter = new BaseAdapter<>(R.layout.item_bbs_sign, null, 2, null);
        this.adapter = baseAdapter;
        BaseAdapter<BbsSignBean.Welfare, ItemBbsSignWelfareBinding> baseAdapter2 = new BaseAdapter<>(R.layout.item_bbs_sign_welfare, null, 2, null);
        this.adapter2 = baseAdapter2;
        getMBinding().tvSign.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.BbsSignDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsSignDialog._init_$lambda$0(activity, this, view);
            }
        });
        getMBinding().rv.setAdapter(baseAdapter);
        getMBinding().rv2.setAdapter(baseAdapter2);
        m6576getData();
    }

    public final BaseAdapter<BbsSignBean.Day, ItemBbsSignBinding> getAdapter() {
        return this.adapter;
    }

    public final BaseAdapter<BbsSignBean.Welfare, ItemBbsSignWelfareBinding> getAdapter2() {
        return this.adapter2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(Context activity, BbsSignDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(activity, (Class<?>) BbsEditActivity.class);
        intent.putExtra("data", this$0.data);
        activity.startActivity(intent);
    }

    /* JADX INFO: renamed from: getData, reason: collision with other method in class */
    public final void m6576getData() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsSignDialog$getData$$inlined$get$1("bbs/clockInList", MapsKt.emptyMap(), null, this, this), 3, null);
    }
}
