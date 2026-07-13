package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityMonthlyTaskBinding;
import com.cy.yyjia.zhe28.databinding.ItemTaskMonthlyBinding;
import com.cy.yyjia.zhe28.domain.MonthlyTaskNavBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: MonthlyTaskActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u000eJ\u0006\u0010\u0011\u001a\u00020\u000eJ\b\u0010\u0012\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/MonthlyTaskActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityMonthlyTaskBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemTaskMonthlyBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "batchReceive", "", "getData", "getNav", "getRule", "init", "onClick", "v", "Landroid/view/View;", "receive", "p", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MonthlyTaskActivity extends BaseActivity<ActivityMonthlyTaskBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    public MonthlyTaskActivity() {
        super(R.layout.activity_monthly_task, 2);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<TaskBean, ItemTaskMonthlyBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TaskBean, ItemTaskMonthlyBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_task_monthly, null, 2, null);
            }
        });
    }

    public final BaseAdapter<TaskBean, ItemTaskMonthlyBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MonthlyTaskActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().tvAll.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MonthlyTaskActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().ivRule.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MonthlyTaskActivity.init$lambda$2(this.f$0, view);
            }
        });
        getNav();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MonthlyTaskActivity this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.btn && Intrinsics.areEqual(this$0.getAdapter().getItem(i).getDone(), "not_receive")) {
            this$0.receive(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(MonthlyTaskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.batchReceive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(MonthlyTaskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getRule();
    }

    public final void getNav() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("t", String.valueOf(System.currentTimeMillis()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new MonthlyTaskActivity$getNav$$inlined$get$1("monthTask/category", linkedHashMap, null, this, this), 3, null);
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("t", String.valueOf(System.currentTimeMillis()));
        MonthlyTaskNavBean nav = getMBinding().getNav();
        Intrinsics.checkNotNull(nav);
        linkedHashMap.put("type", String.valueOf(nav.getList().get(getMBinding().getPosition()).getType()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new MonthlyTaskActivity$getData$$inlined$get$1("monthTask/index", linkedHashMap, null, this, this), 3, null);
    }

    public final void receive(int p) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("log_id", String.valueOf(getAdapter().getItem(p).getLog_id()));
        NetUtil.post2$default(NetUtil.INSTANCE, "monthTask/receive", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity.receive.1
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
                MonthlyTaskActivity.this.toast(it.getMsg());
                MonthlyTaskActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity.receive.2
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
                MonthlyTaskActivity.this.netFail(it);
            }
        }, null, 16, null);
    }

    public final void batchReceive() {
        NetUtil.post2$default(NetUtil.INSTANCE, "monthTask/receiveAll", new LinkedHashMap(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity.batchReceive.1
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
                MonthlyTaskActivity.this.toast(it.getMsg());
                MonthlyTaskActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity.batchReceive.2
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
                MonthlyTaskActivity.this.netFail(it);
            }
        }, null, 16, null);
    }

    public final void getRule() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new MonthlyTaskActivity$getRule$$inlined$get$1("monthTask/rule", linkedHashMap, null, this, this), 3, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        getMBinding().setPosition(Integer.parseInt(v.getTag().toString()));
        getData();
    }
}
