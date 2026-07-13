package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityQiandaoBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeLargeBinding;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoBinding;
import com.cy.yyjia.zhe28.domain.BaseResult;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.SignResultBean;
import com.cy.yyjia.zhe28.ui.adapter.QiandaoUserAdapter;
import com.cy.yyjia.zhe28.ui.dialog.QiandaoTaskDialog;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QiandaoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\b\u0010 \u001a\u00020\u001eH\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u001eH\u0014R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0010\u0010\nR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\f\u001a\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/QiandaoActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityQiandaoBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemQiandaoBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "gameAdapter", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeLargeBinding;", "getGameAdapter", "gameAdapter$delegate", "showTask", "", "getShowTask", "()Z", "setShowTask", "(Z)V", "vfAdapter", "Lcom/cy/yyjia/zhe28/ui/adapter/QiandaoUserAdapter;", "getVfAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/QiandaoUserAdapter;", "vfAdapter$delegate", "getCoupon", "", "getData", "init", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class QiandaoActivity extends BaseActivity<ActivityQiandaoBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: gameAdapter$delegate, reason: from kotlin metadata */
    private final Lazy gameAdapter;
    private boolean showTask;

    /* JADX INFO: renamed from: vfAdapter$delegate, reason: from kotlin metadata */
    private final Lazy vfAdapter;

    public QiandaoActivity() {
        super(R.layout.activity_qiandao, 1);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<String, ItemQiandaoBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<String, ItemQiandaoBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_qiandao, null, 2, null);
            }
        });
        this.gameAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHomeLargeBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity$gameAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHomeLargeBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_home_large, null, 2, null);
            }
        });
        this.vfAdapter = LazyKt.lazy(new Function0<QiandaoUserAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity$vfAdapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final QiandaoUserAdapter invoke() {
                return new QiandaoUserAdapter(this.this$0);
            }
        });
    }

    public static final /* synthetic */ ActivityQiandaoBinding access$getMBinding(QiandaoActivity qiandaoActivity) {
        return qiandaoActivity.getMBinding();
    }

    public final boolean getShowTask() {
        return this.showTask;
    }

    public final void setShowTask(boolean z) {
        this.showTask = z;
    }

    public final BaseAdapter<String, ItemQiandaoBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<GameBean, ItemHomeLargeBinding> getGameAdapter() {
        return (BaseAdapter) this.gameAdapter.getValue();
    }

    public final QiandaoUserAdapter getVfAdapter() {
        return (QiandaoUserAdapter) this.vfAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        this.showTask = getIntent().getBooleanExtra("task", false);
        getMBinding().rv.setAdapter(getGameAdapter());
        getMBinding().list.setAdapter(getAdapter());
        getMBinding().sign.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QiandaoActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().vf.setAdapter(getVfAdapter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final QiandaoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.sign(new Function1<BaseResult<SignResultBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity$init$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BaseResult<SignResultBean> baseResult) {
                    invoke2(baseResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BaseResult<SignResultBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this.this$0.toast(it.getMsg());
                    if (it.getCode() == 200) {
                        new QuickDialog(this.this$0, R.layout.dialog_to_thunt).setData(it.getData()).show();
                        this.this$0.getData();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity$init$1$2
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
        } else {
            this$0.toLogin();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
    }

    public final void getData() {
        Repository.INSTANCE.getSign(new Function1<QiandaoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity.getData.1
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
                QiandaoActivity.access$getMBinding(QiandaoActivity.this).setData(it);
                QiandaoActivity.this.getVfAdapter().setData(it.getList().getUserList());
                QiandaoActivity.this.getVfAdapter().notifyDataSetChanged();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity.getData.2
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
                QiandaoActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.fl_daily /* 2131362105 */:
                new QiandaoTaskDialog(this, getMBinding().getTask(), 1).show();
                break;
            case R.id.fl_growup /* 2131362108 */:
                new QiandaoTaskDialog(this, getMBinding().getTask(), 2).show();
                break;
            case R.id.ll_task /* 2131362282 */:
                new QiandaoTaskDialog(this, getMBinding().getTask(), 0, 4, null).show();
                break;
            case R.id.tv_lottery /* 2131362724 */:
                startActivity(LotteryActivity.class);
                break;
            case R.id.tv_record /* 2131362757 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity.onClick.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        QiandaoActivity.this.startActivity(QiandaoRecordActivity.class);
                    }
                });
                break;
            case R.id.tv_rule /* 2131362764 */:
                QiandaoBean data = getMBinding().getData();
                if (data != null) {
                    new RuleDialog(getMContext()).setTextStr(data.getConfig().getRule()).show();
                }
                break;
            case R.id.tv_yhq /* 2131362811 */:
                getCoupon();
                break;
        }
    }

    public final void getCoupon() {
        Repository.INSTANCE.getQiandaoCoupon(new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity.getCoupon.1
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
                QiandaoActivity.this.toast(it.getMsg());
                QiandaoActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QiandaoActivity.getCoupon.2
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
                QiandaoActivity.this.netFail(it);
            }
        });
    }
}
