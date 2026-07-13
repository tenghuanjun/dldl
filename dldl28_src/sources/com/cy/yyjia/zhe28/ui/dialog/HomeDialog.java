package com.cy.yyjia.zhe28.ui.dialog;

import android.content.SharedPreferences;
import android.view.View;
import androidx.recyclerview.widget.PagerSnapHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.activity.MainActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.DialogHomeBinding;
import com.cy.yyjia.zhe28.domain.SlideBean;
import com.cy.yyjia.zhe28.ui.activity.CardActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: HomeDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/HomeDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogHomeBinding;", "mainActivity", "Lcom/cy/yyjia/zhe28/activity/MainActivity;", "data", "", "Lcom/cy/yyjia/zhe28/domain/SlideBean;", "(Lcom/cy/yyjia/zhe28/activity/MainActivity;Ljava/util/List;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeDialog extends BaseDataBindingDialog<DialogHomeBinding, HomeDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeDialog(final MainActivity mainActivity, List<SlideBean> data) {
        super(mainActivity, R.layout.dialog_home);
        Intrinsics.checkNotNullParameter(mainActivity, "mainActivity");
        Intrinsics.checkNotNullParameter(data, "data");
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_dialog_home, data);
        ((DialogHomeBinding) this.mBinding).rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.HomeDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeDialog._init_$lambda$1(baseAdapter, this, mainActivity, baseQuickAdapter, view, i);
            }
        });
        if (data.size() > 1 || data.get(0).getOpenTimes() == 1) {
            ((DialogHomeBinding) this.mBinding).cb.setVisibility(0);
        } else {
            ((DialogHomeBinding) this.mBinding).cb.setVisibility(8);
        }
        ((DialogHomeBinding) this.mBinding).indicator.setIndicatorLength(data.size());
        ((DialogHomeBinding) this.mBinding).indicator.attachToRecyclerView(((DialogHomeBinding) this.mBinding).rv);
        new PagerSnapHelper().attachToRecyclerView(((DialogHomeBinding) this.mBinding).rv);
        setOnClickListener(R.id.iv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.HomeDialog$$ExternalSyntheticLambda1
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                HomeDialog._init_$lambda$2(this.f$0, baseDialog, view);
            }
        });
        addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.HomeDialog$$ExternalSyntheticLambda2
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
            public final void onDismiss(BaseDialog baseDialog) {
                HomeDialog._init_$lambda$3(this.f$0, mainActivity, baseDialog);
            }
        });
        if (data.get(0).getCountdown() != 0) {
            final Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new HomeDialog$job$1(data, this, null), 3, null);
            addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.HomeDialog$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
                public final void onDismiss(BaseDialog baseDialog) {
                    HomeDialog._init_$lambda$4(jobLaunch$default, baseDialog);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void _init_$lambda$1(BaseAdapter adapter, HomeDialog this$0, MainActivity mainActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mainActivity, "$mainActivity");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        SlideBean slideBean = (SlideBean) adapter.getItem(i);
        if (Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.readHomeDialog(slideBean.getId());
        }
        switch (slideBean.getActionid()) {
            case 1:
            case 3:
            case 4:
            case 5:
                mainActivity.select(slideBean.getActionid() - 1);
                break;
            case 2:
            default:
                Util.openWebWithLogin(this$0.getContext(), "", slideBean.getUrl());
                break;
            case 6:
                Util.gotoGame(mainActivity, slideBean.getGid());
                break;
            case 7:
                Util.skipWithLogin(mainActivity, CardActivity.class);
                break;
            case 8:
                Util.skipWithLogin(mainActivity, MonthCardActivity.class);
                break;
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(HomeDialog this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$3(HomeDialog this$0, MainActivity mainActivity, BaseDialog baseDialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mainActivity, "$mainActivity");
        if (((DialogHomeBinding) this$0.mBinding).cb.isChecked()) {
            SharedPreferences.Editor editorEdit = mainActivity.getSharedPreferences("lastAdTime", 0).edit();
            Intrinsics.checkNotNullExpressionValue(editorEdit, "edit(...)");
            editorEdit.putLong("time", System.currentTimeMillis());
            editorEdit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$4(Job job, BaseDialog baseDialog) {
        Intrinsics.checkNotNullParameter(job, "$job");
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }
}
