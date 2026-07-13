package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivitySanbaoBinding;
import com.cy.yyjia.zhe28.databinding.ItemSanbaoGame2Binding;
import com.cy.yyjia.zhe28.databinding.ItemSanbaoGameBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.SanbaoBean;
import com.cy.yyjia.zhe28.domain.SanbaoRuleBean;
import com.cy.yyjia.zhe28.ui.dialog.CommentDialog;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SanbaoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020$J\b\u0010'\u001a\u00020$H\u0016J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0016J\u000e\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0019J\u000e\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020\u0019R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0010\u0010\nR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\f\u001a\u0004\b \u0010!¨\u0006/"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SanbaoActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivitySanbaoBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/SanbaoBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSanbaoGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "adapter2", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSanbaoGame2Binding;", "getAdapter2", "adapter2$delegate", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getBanner", "", "getData", "getData2", "init", "onClick", "v", "Landroid/view/View;", "receive", ImageSelector.POSITION, "setPosition", "p", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SanbaoActivity extends BaseActivity<ActivitySanbaoBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: adapter2$delegate, reason: from kotlin metadata */
    private final Lazy adapter2;
    private String name;
    private int page;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public SanbaoActivity() {
        super(R.layout.activity_sanbao, 1);
        this.page = 1;
        this.name = "";
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0).get(MainViewModel.class);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<SanbaoBean, ItemSanbaoGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<SanbaoBean, ItemSanbaoGameBinding> invoke() {
                final SanbaoActivity sanbaoActivity = this.this$0;
                return new BaseAdapter<>(R.layout.item_sanbao_game, new Function3<BaseDataBindingHolder<ItemSanbaoGameBinding>, Integer, SanbaoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$adapter$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemSanbaoGameBinding> baseDataBindingHolder, Integer num, SanbaoBean sanbaoBean) {
                        invoke(baseDataBindingHolder, num.intValue(), sanbaoBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemSanbaoGameBinding> h, int i, SanbaoBean sanbaoBean) {
                        String str;
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemSanbaoGameBinding itemSanbaoGameBinding = (ItemSanbaoGameBinding) h.getDataBinding();
                        if (itemSanbaoGameBinding == null) {
                            return;
                        }
                        int position = SanbaoActivity.access$getMBinding(sanbaoActivity).getPosition();
                        if (position == 1) {
                            Intrinsics.checkNotNull(sanbaoBean);
                            str = sanbaoBean.getIs_received() == 1 ? "已领取" : "领取";
                        } else if (position == 2) {
                            str = "马上玩";
                        } else {
                            str = "进入";
                        }
                        itemSanbaoGameBinding.setBtn(str);
                    }
                });
            }
        });
        this.adapter2 = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemSanbaoGame2Binding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$adapter2$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemSanbaoGame2Binding> invoke() {
                return new BaseAdapter<>(R.layout.item_sanbao_game2, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ ActivitySanbaoBinding access$getMBinding(SanbaoActivity sanbaoActivity) {
        return sanbaoActivity.getMBinding();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final BaseAdapter<SanbaoBean, ItemSanbaoGameBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<GameBean, ItemSanbaoGame2Binding> getAdapter2() {
        return (BaseAdapter) this.adapter2.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.body, new Sanbao648Fragment()).commit();
        getMBinding().setPosition(1);
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                SanbaoActivity.init$lambda$0(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.tv_btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SanbaoActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SanbaoActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        setPosition(getIntent().getIntExtra(ImageSelector.POSITION, 1));
        getAdapter2().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                SanbaoActivity.init$lambda$3(this.f$0);
            }
        });
        getBanner();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(SanbaoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(SanbaoActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getMBinding().getPosition() == 1) {
            this$0.receive(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(SanbaoActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getMBinding().getPosition() == 3) {
            this$0.startActivity(new Intent(this$0.getMContext(), (Class<?>) TopicDetailActivity.class).putExtra("id", this$0.getAdapter().getItem(i).getId()));
        } else {
            Util.gotoGame(this$0, this$0.getAdapter().getItem(i).getGame().getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(SanbaoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData2();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.iv_search) {
            new CommentDialog(this).setHint("请输入游戏名").setBtnText("搜索").setSubmitListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.onClick.1
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
                    if (SanbaoActivity.access$getMBinding(SanbaoActivity.this).getPosition() == 1) {
                        SanbaoActivity.this.getVm().getHomeFun().setValue(it);
                        return;
                    }
                    SanbaoActivity.this.setName(it);
                    SanbaoActivity.this.setPage(1);
                    SanbaoActivity.this.getData();
                }
            }).show();
            return;
        }
        if (id != R.id.tv_rule) {
            switch (id) {
                case R.id.tv1 /* 2131362645 */:
                    setPosition(1);
                    break;
                case R.id.tv2 /* 2131362646 */:
                    setPosition(2);
                    break;
                case R.id.tv3 /* 2131362647 */:
                    setPosition(3);
                    break;
            }
            return;
        }
        Repository.INSTANCE.getSanbaoRule(getMBinding().getPosition(), new Function1<SanbaoRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.onClick.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SanbaoRuleBean sanbaoRuleBean) {
                invoke2(sanbaoRuleBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SanbaoRuleBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RuleDialog ruleDialog = new RuleDialog(SanbaoActivity.this);
                int position = SanbaoActivity.access$getMBinding(SanbaoActivity.this).getPosition();
                if (position == 1) {
                    ruleDialog.setTextStr(it.getDesc());
                } else if (position == 2) {
                    ruleDialog.setText(it.getHour().getRule());
                } else if (position == 3) {
                    ruleDialog.setText(it.getTopic().getRule());
                }
                ruleDialog.show();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.onClick.3
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
                SanbaoActivity.this.netFail(it);
            }
        });
    }

    public final void setPosition(int p) {
        this.name = "";
        getMBinding().setPosition(p);
        this.page = 1;
        if (p == 2) {
            getAdapter2().setNewInstance(null);
            getMBinding().rv.setAdapter(getAdapter2());
            getData2();
        } else {
            getAdapter().setNewInstance(null);
            getMBinding().rv.setAdapter(getAdapter());
            getData();
        }
    }

    public final void getData() {
        if (getMBinding().getPosition() == 1) {
            getMBinding().body.setVisibility(0);
        } else {
            getMBinding().body.setVisibility(8);
            Repository.INSTANCE.getSanbaoGame(getMBinding().getPosition(), this.page, this.name, new Function1<PageBean<SanbaoBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.getData.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<SanbaoBean> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<SanbaoBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (SanbaoActivity.this.getPage() == 1) {
                        SanbaoActivity.this.getAdapter().setNewInstance(it.getList());
                    } else {
                        SanbaoActivity.this.getAdapter().addData(it.getList());
                    }
                    SanbaoActivity sanbaoActivity = SanbaoActivity.this;
                    sanbaoActivity.setPage(sanbaoActivity.getPage() + 1);
                    sanbaoActivity.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(SanbaoActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        SanbaoActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.getData.2
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
                    SanbaoActivity.this.netFail(it);
                    SanbaoActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                }
            });
        }
    }

    public final void getData2() {
        Repository.INSTANCE.getHourGame(this.page, this.name, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.getData2.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (SanbaoActivity.this.getPage() == 1) {
                    SanbaoActivity.this.getAdapter2().setNewInstance(it.getList());
                } else {
                    SanbaoActivity.this.getAdapter2().addData(it.getList());
                }
                SanbaoActivity sanbaoActivity = SanbaoActivity.this;
                sanbaoActivity.setPage(sanbaoActivity.getPage() + 1);
                sanbaoActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(SanbaoActivity.this.getAdapter2().getLoadMoreModule(), false, 1, null);
                } else {
                    SanbaoActivity.this.getAdapter2().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.getData2.2
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
                SanbaoActivity.this.netFail(it);
                SanbaoActivity.this.getAdapter2().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void receive(final int position) {
        if (getAdapter().getItem(position).getIs_received() == 1) {
            return;
        }
        Repository.INSTANCE.getSanbaoVoucher(getAdapter().getItem(position).getGame().getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.receive.1
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
                SanbaoActivity.this.toast(it.getMsg());
                SanbaoActivity.this.getAdapter().getItem(position).set_received(1);
                SanbaoActivity.this.getAdapter().notifyItemChanged(position);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.receive.2
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
                SanbaoActivity.this.netFail(it);
            }
        });
    }

    public final void getBanner() {
        Repository.INSTANCE.getSanbaoRule(3, new Function1<SanbaoRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.getBanner.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SanbaoRuleBean sanbaoRuleBean) {
                invoke2(sanbaoRuleBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SanbaoRuleBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SanbaoActivity.access$getMBinding(SanbaoActivity.this).setTopic(it.getSpecialTopic());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SanbaoActivity.getBanner.2
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
                SanbaoActivity sanbaoActivity = SanbaoActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                sanbaoActivity.log(localizedMessage);
            }
        });
    }
}
