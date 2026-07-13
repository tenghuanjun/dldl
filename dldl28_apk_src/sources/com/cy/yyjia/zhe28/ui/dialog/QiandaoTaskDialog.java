package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.DialogQiandaoTaskBinding;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoTaskBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.domain.TaskResult;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QiandaoTaskDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u0015\u0010\n\u001a\u00060\u000bR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u00060\u000bR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/QiandaoTaskDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogQiandaoTaskBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "data", "Lcom/cy/yyjia/zhe28/domain/TaskResult;", "type", "", "(Landroidx/fragment/app/FragmentActivity;Lcom/cy/yyjia/zhe28/domain/TaskResult;I)V", "adapter1", "Lcom/cy/yyjia/zhe28/ui/dialog/QiandaoTaskDialog$ListAdapter;", "getAdapter1", "()Lcom/cy/yyjia/zhe28/ui/dialog/QiandaoTaskDialog$ListAdapter;", "adapter2", "getAdapter2", "getData", "", "receive", ImageSelector.POSITION, "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "ListAdapter", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class QiandaoTaskDialog extends BaseDataBindingDialog<DialogQiandaoTaskBinding, QiandaoTaskDialog> {
    public static final int $stable = 0;
    private final ListAdapter adapter1;
    private final ListAdapter adapter2;

    public /* synthetic */ QiandaoTaskDialog(FragmentActivity fragmentActivity, TaskResult taskResult, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, taskResult, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QiandaoTaskDialog(FragmentActivity activity, TaskResult taskResult, int i) {
        super(activity, R.layout.dialog_qiandao_task);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ListAdapter listAdapter = new ListAdapter();
        this.adapter1 = listAdapter;
        this.adapter2 = new ListAdapter();
        ((DialogQiandaoTaskBinding) this.mBinding).setType(i);
        ((DialogQiandaoTaskBinding) this.mBinding).rv1.setAdapter(listAdapter);
        if (taskResult == null) {
            getData();
        } else {
            ((DialogQiandaoTaskBinding) this.mBinding).setData(taskResult);
        }
    }

    public final ListAdapter getAdapter1() {
        return this.adapter1;
    }

    public final ListAdapter getAdapter2() {
        return this.adapter2;
    }

    public final void getData() {
        Repository.INSTANCE.getTaskData(new Function1<TaskResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog.getData.1
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
                ((DialogQiandaoTaskBinding) QiandaoTaskDialog.this.mBinding).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog.getData.2
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
                QiandaoTaskDialog.this.netFail(it);
            }
        });
    }

    public final void receive(TaskBean position) {
        Intrinsics.checkNotNullParameter(position, "position");
        Repository.INSTANCE.getTaskReward(position.getLog_id(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog.receive.1
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
                QiandaoTaskDialog.this.toast(it.getMsg());
                QiandaoTaskDialog.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog.receive.2
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
                QiandaoTaskDialog.this.netFail(it);
            }
        }, position.getGiveType());
        if (Constant.INSTANCE.getUsername().length() == 0) {
            getContext().startActivity(new Intent(getContext(), (Class<?>) LoginActivity.class));
        }
    }

    /* JADX INFO: compiled from: QiandaoTaskDialog.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/QiandaoTaskDialog$ListAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemQiandaoTaskBinding;", "(Lcom/cy/yyjia/zhe28/ui/dialog/QiandaoTaskDialog;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ListAdapter extends BaseAdapter<TaskBean, ItemQiandaoTaskBinding> {
        public ListAdapter() {
            super(R.layout.item_qiandao_task, null, 2, null);
            addChildClickViewIds(R.id.btn);
            setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog$ListAdapter$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    QiandaoTaskDialog.ListAdapter._init_$lambda$1(this.f$0, qiandaoTaskDialog, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$1(ListAdapter this$0, final QiandaoTaskDialog this$1, BaseQuickAdapter a2, View v, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(a2, "a");
            Intrinsics.checkNotNullParameter(v, "v");
            if (Intrinsics.areEqual(this$0.getItem(i).getName(), "关注公众号")) {
                new FastDialog(this$0.getContext()).setContentView(R.layout.dialog_task_wx).setOnClickListener(R.id.sure, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog$ListAdapter$$ExternalSyntheticLambda0
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        QiandaoTaskDialog.ListAdapter.lambda$1$lambda$0(this$1, baseDialog, view);
                    }
                }).show();
            } else if (Intrinsics.areEqual(this$0.getItem(i).getBtnText(), "领取")) {
                this$1.receive(this$0.getItem(i));
            } else {
                new TaskDialog(this$0.getContext()).setTitle("游戏任务").setName(this$0.getItem(i).getName()).setDetail(this$0.getItem(i).getShortDesc()).show();
            }
        }

        static final void lambda$1$lambda$0(final QiandaoTaskDialog this$0, final BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            EditText editText = (EditText) baseDialog.findViewById(R.id.et);
            Repository.INSTANCE.getWXTaskReward(String.valueOf(editText != null ? editText.getText() : null), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog$ListAdapter$1$1$1
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
                    this$0.toast(it.getMsg());
                    if (it.getCode() == 200) {
                        baseDialog.dismiss();
                        this$0.getData();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog$ListAdapter$1$1$2
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
                    this$0.netFail(it);
                }
            });
        }
    }
}
