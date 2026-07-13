package com.cy.yyjia.zhe28.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.ActivityNoviceTaskBinding;
import com.cy.yyjia.zhe28.databinding.ItemTaskBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.domain.TaskResult;
import com.cy.yyjia.zhe28.ui.dialog.TaskDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: NoviceTaskActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0016J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/NoviceTaskActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityNoviceTaskBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemTaskBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "getData", "", "init", "receive", "p", "", "wxCode", "code", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class NoviceTaskActivity extends BaseActivity<ActivityNoviceTaskBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    public static final /* synthetic */ ActivityNoviceTaskBinding access$getMBinding(NoviceTaskActivity noviceTaskActivity) {
        return noviceTaskActivity.getMBinding();
    }

    public NoviceTaskActivity() {
        super(R.layout.activity_novice_task, 2);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<TaskBean, ItemTaskBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TaskBean, ItemTaskBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_task, new Function3<BaseDataBindingHolder<ItemTaskBinding>, Integer, TaskBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$adapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemTaskBinding> baseDataBindingHolder, Integer num, TaskBean taskBean) {
                        invoke(baseDataBindingHolder, num.intValue(), taskBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemTaskBinding> h, int i, TaskBean taskBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemTaskBinding itemTaskBinding = (ItemTaskBinding) h.getDataBinding();
                        if (itemTaskBinding != null) {
                            String done = taskBean != null ? taskBean.getDone() : null;
                            if (done != null) {
                                int iHashCode = done.hashCode();
                                if (iHashCode == -500088457) {
                                    if (done.equals("not_receive")) {
                                        itemTaskBinding.btnNext.setText("领取");
                                        itemTaskBinding.btnNext.getShapeDrawableBuilder().setStrokeGradientColors(Color.parseColor("#FC5932"), Color.parseColor("#F28140")).buildBackgroundDrawable();
                                        itemTaskBinding.btnNext.setSelected(true);
                                        itemTaskBinding.btnNext.setTextColor(Color.parseColor("#eeeeee"));
                                        return;
                                    }
                                    return;
                                }
                                if (iHashCode != 3521) {
                                    if (iHashCode != 104418) {
                                        if (iHashCode == 119527 && done.equals(BooleanUtils.YES)) {
                                            itemTaskBinding.btnNext.setText("已领取");
                                            itemTaskBinding.btnNext.getShapeDrawableBuilder().setStrokeGradientColors(Color.parseColor("#cccccc"), Color.parseColor("#cccccc")).buildBackgroundDrawable();
                                            itemTaskBinding.btnNext.setSelected(false);
                                            itemTaskBinding.btnNext.setTextColor(Color.parseColor("#eeeeee"));
                                            return;
                                        }
                                        return;
                                    }
                                    if (!done.equals("ing")) {
                                        return;
                                    }
                                } else if (!done.equals(BooleanUtils.NO)) {
                                    return;
                                }
                                itemTaskBinding.btnNext.setText("进行中");
                                itemTaskBinding.btnNext.getShapeDrawableBuilder().setStrokeGradientColors(Color.parseColor("#FC5932"), Color.parseColor("#F28140")).buildBackgroundDrawable();
                                itemTaskBinding.btnNext.setTextColor(Color.parseColor("#ffffff"));
                            }
                        }
                    }
                });
            }
        });
    }

    public final BaseAdapter<TaskBean, ItemTaskBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().list.setAdapter(getAdapter());
        getData();
        getAdapter().addChildClickViewIds(R.id.btn_next);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NoviceTaskActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NoviceTaskActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoviceTaskActivity.init$lambda$3(this.f$0, view);
            }
        });
        getMBinding().linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoviceTaskActivity.init$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(NoviceTaskActivity this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.btn_next && Intrinsics.areEqual(this$0.getAdapter().getItem(i).getDone(), "not_receive")) {
            this$0.receive(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(final NoviceTaskActivity this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(this$0.getAdapter().getItem(i).getName(), "关注公众号")) {
            new FastDialog(this$0).setContentView(R.layout.dialog_task_wx).setOnClickListener(R.id.sure, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$$ExternalSyntheticLambda4
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    NoviceTaskActivity.init$lambda$2$lambda$1(this.f$0, baseDialog, view);
                }
            }).show();
        } else {
            new TaskDialog(this$0).setTitle("游戏任务").setName(this$0.getAdapter().getItem(i).getName()).setDetail(this$0.getAdapter().getItem(i).getDescription()).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2$lambda$1(final NoviceTaskActivity this$0, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = (EditText) baseDialog.findViewById(R.id.et);
        Repository.INSTANCE.getWXTaskReward(String.valueOf(editText != null ? editText.getText() : null), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$init$2$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                this.this$0.toast(it.getMsg());
                if (it.getCode() == 200) {
                    baseDialog.dismiss();
                    this.this$0.getData();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity$init$2$1$2
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
                this.this$0.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(NoviceTaskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(LotteryActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(NoviceTaskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.openWebWithLogin(this$0, "积分商城", NetUtil.BASE_URL3 + "dist/shop");
    }

    public final void getData() {
        Repository.INSTANCE.getTaskData(new Function1<TaskResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TaskResult taskResult) {
                invoke2(taskResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TaskResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NoviceTaskActivity.access$getMBinding(NoviceTaskActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity.getData.2
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
                NoviceTaskActivity.this.netFail(it);
            }
        });
    }

    public final void receive(int p) {
        Repository.INSTANCE.getTaskReward(getAdapter().getItem(p).getLog_id(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity.receive.1
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
                NoviceTaskActivity.this.toast(it.getMsg());
                NoviceTaskActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity.receive.2
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
                NoviceTaskActivity.this.netFail(it);
            }
        }, getAdapter().getItem(p).getGiveType());
    }

    public final void wxCode(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        Repository.INSTANCE.getWXTaskReward(code, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity.wxCode.1
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
                NoviceTaskActivity.this.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceTaskActivity.wxCode.2
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
                NoviceTaskActivity.this.netFail(it);
            }
        });
    }
}
