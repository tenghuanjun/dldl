package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityDailyTaskBinding;
import com.cy.yyjia.zhe28.databinding.ItemTaskDailyBinding;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DailyTaskActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\b\u0010\u0010\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DailyTaskActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDailyTaskBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemTaskDailyBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "batchReceive", "", "getData", "init", "onClick", "v", "Landroid/view/View;", "receive", "p", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DailyTaskActivity extends BaseActivity<ActivityDailyTaskBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    public static final /* synthetic */ ActivityDailyTaskBinding access$getMBinding(DailyTaskActivity dailyTaskActivity) {
        return dailyTaskActivity.getMBinding();
    }

    public DailyTaskActivity() {
        super(R.layout.activity_daily_task, 1);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<TaskBean, ItemTaskDailyBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TaskBean, ItemTaskDailyBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_task_daily, null, 2, null);
            }
        });
    }

    public final BaseAdapter<TaskBean, ItemTaskDailyBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DailyTaskActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().tvAll.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DailyTaskActivity.init$lambda$1(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(DailyTaskActivity this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.btn && Intrinsics.areEqual(this$0.getAdapter().getItem(i).getDone(), "not_receive")) {
            this$0.receive(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(DailyTaskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.batchReceive();
    }

    public final void getData() {
        Repository.INSTANCE.getSign(new Function1<QiandaoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(QiandaoBean qiandaoBean) {
                invoke2(qiandaoBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(QiandaoBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DailyTaskActivity.access$getMBinding(DailyTaskActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity.getData.2
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
                DailyTaskActivity.this.netFail(it);
            }
        });
    }

    public final void receive(int p) {
        Repository.INSTANCE.getTaskReward(getAdapter().getItem(p).getLog_id(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity.receive.1
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
                DailyTaskActivity.this.toast(it.getMsg());
                DailyTaskActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity.receive.2
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
                DailyTaskActivity.this.netFail(it);
            }
        }, getAdapter().getItem(p).getGiveType());
    }

    public final void batchReceive() {
        NetUtil.post2$default(NetUtil.INSTANCE, "task/receiveAllTaskWelfare", new LinkedHashMap(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity.batchReceive.1
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
                DailyTaskActivity.this.toast(it.getMsg());
                DailyTaskActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity.batchReceive.2
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
                DailyTaskActivity.this.netFail(it);
            }
        }, null, 16, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.iv_rule) {
            RuleDialog ruleDialog = new RuleDialog(this);
            QiandaoBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            ruleDialog.setTextStr(data.getConfig().getRule_task()).show();
            return;
        }
        getMBinding().setPosition(Integer.parseInt(v.getTag().toString()));
    }
}
