package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityRecycleBinding;
import com.cy.yyjia.zhe28.domain.CommentConfig;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.RecycleListBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.adapter.RecycleAdapter;
import com.cy.yyjia.zhe28.ui.dialog.CommentDialog;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.PinDialog;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: RecycleActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u000f\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u001aR\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/RecycleActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRecycleBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/RecycleAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/RecycleAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "adapter2", "getAdapter2", "adapter2$delegate", "config", "Lcom/cy/yyjia/zhe28/domain/CommentConfig;", "getConfig", "()Lcom/cy/yyjia/zhe28/domain/CommentConfig;", "setConfig", "(Lcom/cy/yyjia/zhe28/domain/CommentConfig;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "checkAccount", "", "getData", "init", "onBackPressed", "onClick", "v", "Landroid/view/View;", "recycle", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RecycleActivity extends BaseActivity<ActivityRecycleBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: adapter2$delegate, reason: from kotlin metadata */
    private final Lazy adapter2;
    public CommentConfig config;
    private int page;

    public RecycleActivity() {
        super(R.layout.activity_recycle, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<RecycleAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RecycleAdapter invoke() {
                return new RecycleAdapter(0);
            }
        });
        this.adapter2 = LazyKt.lazy(new Function0<RecycleAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity$adapter2$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RecycleAdapter invoke() {
                return new RecycleAdapter(1);
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRecycleBinding access$getMBinding(RecycleActivity recycleActivity) {
        return recycleActivity.getMBinding();
    }

    public final RecycleAdapter getAdapter() {
        return (RecycleAdapter) this.adapter.getValue();
    }

    public final RecycleAdapter getAdapter2() {
        return (RecycleAdapter) this.adapter2.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final CommentConfig getConfig() {
        CommentConfig commentConfig = this.config;
        if (commentConfig != null) {
            return commentConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("config");
        return null;
    }

    public final void setConfig(CommentConfig commentConfig) {
        Intrinsics.checkNotNullParameter(commentConfig, "<set-?>");
        this.config = commentConfig;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setName("");
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecycleActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                RecycleActivity.init$lambda$2(this.f$0);
            }
        });
        getData();
        m6544getConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(RecycleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(RecycleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new RuleDialog(this$0.getMContext()).setText(this$0.getConfig().getRule()).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(RecycleActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.iv_record) {
            startActivity(RecycleRecordActivity.class);
            return;
        }
        if (id == R.id.tv_recycle) {
            if (getMBinding().getStep() == 0) {
                checkAccount();
                return;
            } else {
                recycle();
                return;
            }
        }
        if (id != R.id.tv_search) {
            return;
        }
        CommentDialog btnText = new CommentDialog(this).setBtnText("搜索");
        String name = getMBinding().getName();
        Intrinsics.checkNotNull(name);
        btnText.setText(name).setHint("请输入需要搜索的游戏名").setSubmitListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.onClick.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RecycleActivity.access$getMBinding(RecycleActivity.this).setName(it);
                RecycleActivity.this.getAdapter().setNewInstance(null);
                RecycleActivity.this.setPage(1);
                RecycleActivity.this.getData();
            }
        }).show();
    }

    public final void getData() {
        Repository repository = Repository.INSTANCE;
        String name = getMBinding().getName();
        Intrinsics.checkNotNull(name);
        repository.getRecycleList(name, new Function1<PageBean<RecycleListBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<RecycleListBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<RecycleListBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (RecycleActivity.this.getPage() == 1) {
                    RecycleActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    RecycleActivity.this.getAdapter().addData((Collection) it.getList());
                }
                RecycleActivity recycleActivity = RecycleActivity.this;
                recycleActivity.setPage(recycleActivity.getPage() + 1);
                recycleActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(RecycleActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    RecycleActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.getData.2
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
                RecycleActivity.this.netFail(it);
            }
        });
    }

    public final void checkAccount() {
        ArrayList arrayList = new ArrayList();
        for (RecycleListBean recycleListBean : getAdapter().getData()) {
            if (recycleListBean.getSelectType() != 0) {
                RecycleListBean recycleListBeanCopy = recycleListBean.copy((131070 & 1) != 0 ? recycleListBean.accounts : new ArrayList(), (131070 & 2) != 0 ? recycleListBean.createTime : 0, (131070 & 4) != 0 ? recycleListBean.gameId : 0, (131070 & 8) != 0 ? recycleListBean.gamePayType : null, (131070 & 16) != 0 ? recycleListBean.id : 0, (131070 & 32) != 0 ? recycleListBean.isHot : 0, (131070 & 64) != 0 ? recycleListBean.maxMoney : null, (131070 & 128) != 0 ? recycleListBean.minMoney : null, (131070 & 256) != 0 ? recycleListBean.planId : 0, (131070 & 512) != 0 ? recycleListBean.rate : null, (131070 & 1024) != 0 ? recycleListBean.rate_text : null, (131070 & 2048) != 0 ? recycleListBean.money_text : null, (131070 & 4096) != 0 ? recycleListBean.max_money_text : null, (131070 & 8192) != 0 ? recycleListBean.returnType : null, (131070 & 16384) != 0 ? recycleListBean.sort : 0, (131070 & 32768) != 0 ? recycleListBean.status : 0, (131070 & 65536) != 0 ? recycleListBean.game : null);
                recycleListBeanCopy.setSelected(true);
                for (RecycleListBean.Account account : recycleListBean.getAccounts()) {
                    if (account.getSelected()) {
                        recycleListBeanCopy.getAccounts().add(account);
                    }
                }
                arrayList.add(recycleListBeanCopy);
            }
        }
        if (arrayList.size() == 0) {
            toast("请先选择要回收的小号");
            return;
        }
        getMBinding().rv.setAdapter(getAdapter2());
        getAdapter2().setNewInstance(arrayList);
        getMBinding().setStep(getMBinding().getStep() + 1);
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.String] */
    public final void recycle() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        Iterator<RecycleListBean> it = getAdapter2().getData().iterator();
        while (it.hasNext()) {
            for (RecycleListBean.Account account : it.next().getAccounts()) {
                objectRef.element = objectRef.element + "," + account.getId();
            }
        }
        if (((CharSequence) objectRef.element).length() == 0) {
            toast("请先选择要回收的小号");
        } else if (Constant.INSTANCE.getNoPin()) {
            new ConfirmDialog(this).setTitle("温馨提示").setTip("您需要设置二级密码，然后进行验证。验证成功后，您可以回收账号").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.recycle.1
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
                    RecycleActivity.this.startActivity(new Intent(RecycleActivity.this, (Class<?>) PhoneActivity.class).putExtra("type", 2));
                }
            }).show();
        } else {
            new PinDialog(this).setTitle("回收小号").setTip(Html.fromHtml(getConfig().getDesc()).toString()).onSubmit(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.recycle.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String code) {
                    Intrinsics.checkNotNullParameter(code, "code");
                    Repository repository = Repository.INSTANCE;
                    String strSubstring = objectRef.element.substring(1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    final RecycleActivity recycleActivity = this;
                    Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.recycle.2.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                            invoke2(result);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Result it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                            recycleActivity.toast(it2.getMsg());
                            recycleActivity.startActivity(RecycleRecordActivity.class);
                            recycleActivity.finish();
                        }
                    };
                    final RecycleActivity recycleActivity2 = this;
                    repository.recycleSubmit(strSubstring, code, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.recycle.2.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                            invoke2(exc);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Exception it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                            recycleActivity2.netFail(it2);
                        }
                    });
                }
            }).show();
        }
    }

    /* JADX INFO: renamed from: getConfig, reason: collision with other method in class */
    public final void m6544getConfig() {
        Repository.INSTANCE.getRecycleConfig(new Function1<CommentConfig, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.getConfig.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CommentConfig commentConfig) {
                invoke2(commentConfig);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CommentConfig it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RecycleActivity.this.setConfig(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleActivity.getConfig.2
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
                RecycleActivity.this.netFail(it);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getMBinding().getStep() == 0) {
            super.onBackPressed();
        } else {
            getMBinding().setStep(getMBinding().getStep() - 1);
            getMBinding().rv.setAdapter(getAdapter());
        }
    }
}
