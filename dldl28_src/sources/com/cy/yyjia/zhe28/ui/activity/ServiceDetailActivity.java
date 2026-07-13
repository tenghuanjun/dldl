package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityServiceDetailBinding;
import com.cy.yyjia.zhe28.databinding.ItemServiceProblemBinding;
import com.cy.yyjia.zhe28.domain.ProblemBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\f\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ServiceDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityServiceDetailBinding;", "()V", "id", "", "getId", "()I", "id$delegate", "Lkotlin/Lazy;", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ServiceDetailActivity extends BaseActivity<ActivityServiceDetailBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;

    public static final /* synthetic */ ActivityServiceDetailBinding access$getMBinding(ServiceDetailActivity serviceDetailActivity) {
        return serviceDetailActivity.getMBinding();
    }

    public ServiceDetailActivity() {
        super(R.layout.activity_service_detail, 1);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_service_problem, new Function3<BaseDataBindingHolder<ItemServiceProblemBinding>, Integer, ProblemBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceDetailActivity$init$adapter$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemServiceProblemBinding> baseDataBindingHolder, Integer num, ProblemBean problemBean) {
                invoke(baseDataBindingHolder, num.intValue(), problemBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemServiceProblemBinding> h, int i, ProblemBean problemBean) {
                TextView textView;
                Intrinsics.checkNotNullParameter(h, "h");
                ItemServiceProblemBinding itemServiceProblemBinding = (ItemServiceProblemBinding) h.getDataBinding();
                if (itemServiceProblemBinding == null || (textView = itemServiceProblemBinding.f470tv) == null) {
                    return;
                }
                textView.setTextColor(this.this$0.getColor(R.color.colorPrimary));
            }
        });
        getMBinding().rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceDetailActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ServiceDetailActivity.init$lambda$0(this.f$0, baseAdapter, baseQuickAdapter, view, i);
            }
        });
        getData(getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void init$lambda$0(ServiceDetailActivity this$0, BaseAdapter adapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getData(((ProblemBean) adapter.getItem(i)).getId());
    }

    public final void getData(int id) {
        Repository.INSTANCE.getServiceProblemDetail(id, new Function1<ProblemBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceDetailActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ProblemBean problemBean) {
                invoke2(problemBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ProblemBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ServiceDetailActivity.access$getMBinding(ServiceDetailActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceDetailActivity.getData.2
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
                ServiceDetailActivity.this.netFail(it);
            }
        });
    }
}
