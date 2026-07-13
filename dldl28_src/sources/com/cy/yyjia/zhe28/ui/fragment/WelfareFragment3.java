package com.cy.yyjia.zhe28.ui.fragment;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.cy.yyjia.zhe28.MyApplication;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3GameBinding;
import com.cy.yyjia.zhe28.databinding.ItemWelfareTask2Binding;
import com.cy.yyjia.zhe28.databinding.ItemWelfareTask3Binding;
import com.cy.yyjia.zhe28.databinding.ItemWelfareTaskBinding;
import com.cy.yyjia.zhe28.domain.BbsBannerBean;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.MonthlyTaskNavBean;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WelfareBean3;
import com.cy.yyjia.zhe28.ui.activity.BaofuActivity;
import com.cy.yyjia.zhe28.ui.activity.BossServerActivity;
import com.cy.yyjia.zhe28.ui.activity.CardActivity;
import com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity;
import com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity;
import com.cy.yyjia.zhe28.ui.activity.GameCouponActivity;
import com.cy.yyjia.zhe28.ui.activity.InviteActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity;
import com.cy.yyjia.zhe28.ui.activity.SanbaoActivity;
import com.cy.yyjia.zhe28.ui.activity.VipActivity;
import com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.BbsBannerHolder;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: WelfareFragment3.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u001bJ\u0006\u0010\u001f\u001a\u00020\u001bJ\u0006\u0010 \u001a\u00020\u001bJ\b\u0010!\u001a\u00020\u001bH\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u001bH\u0016J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/WelfareFragment3;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentWelfare3Binding;", "Landroid/view/View$OnClickListener;", "()V", "adapter1", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemWelfareTaskBinding;", "adapter2", "Lcom/cy/yyjia/zhe28/databinding/ItemWelfareTask2Binding;", "adapter3", "Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Tier;", "Lcom/cy/yyjia/zhe28/databinding/ItemWelfareTask3Binding;", "adapter4", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemWelfare3GameBinding;", "adapter5", "gid", "", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "Lkotlin/Lazy;", "getBanner", "", "getData", "getData3", "getData5", "getMonth", "getNav", "init", "onClick", "v", "Landroid/view/View;", "onResume", "receiveAll", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WelfareFragment3 extends BaseFragment<FragmentWelfare3Binding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final BaseAdapter<TaskBean, ItemWelfareTaskBinding> adapter1;
    private final BaseAdapter<TaskBean, ItemWelfareTask2Binding> adapter2;
    private final BaseAdapter<DailyCouponBean.Tier, ItemWelfareTask3Binding> adapter3;
    private final BaseAdapter<GameBean, ItemWelfare3GameBinding> adapter4;
    private final BaseAdapter<GameBean, ItemWelfare3GameBinding> adapter5;
    private int gid;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public WelfareFragment3() {
        super(R.layout.fragment_welfare3);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.adapter1 = new BaseAdapter<>(R.layout.item_welfare_task, null, 2, null);
        this.adapter2 = new BaseAdapter<>(R.layout.item_welfare_task2, null, 2, null);
        this.adapter3 = new BaseAdapter<>(R.layout.item_welfare_task3, null, 2, null);
        this.adapter4 = new BaseAdapter<>(R.layout.item_welfare3_game, null, 2, null);
        this.adapter5 = new BaseAdapter<>(R.layout.item_welfare3_game, null, 2, null);
    }

    public static final /* synthetic */ FragmentWelfare3Binding access$getMBinding(WelfareFragment3 welfareFragment3) {
        return welfareFragment3.getMBinding();
    }

    private final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setOnClick(this);
        getVm().getUser().observe(this, new WelfareFragment3$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                invoke2(userBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserBean userBean) {
                WelfareFragment3.access$getMBinding(WelfareFragment3.this).setUser(userBean);
            }
        }));
        getMBinding().rv1.setAdapter(this.adapter1);
        getMBinding().rv2.setAdapter(this.adapter2);
        getMBinding().rv3.setAdapter(this.adapter3);
        getMBinding().rv4.setAdapter(this.adapter4);
        getMBinding().rv5.setAdapter(this.adapter5);
        getBanner();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (getMBinding().getData() == null) {
        }
        switch (v.getId()) {
            case R.id.btn_receive1 /* 2131361969 */:
            case R.id.btn_receive2 /* 2131361970 */:
            case R.id.btn_receive3 /* 2131361971 */:
                receiveAll(v);
                break;
            case R.id.btn_task1 /* 2131361981 */:
            case R.id.btn_task12 /* 2131361982 */:
            case R.id.btn_task13 /* 2131361983 */:
                getMBinding().setTask1(Integer.parseInt(v.getTag().toString()));
                break;
            case R.id.btn_task2 /* 2131361984 */:
            case R.id.btn_task22 /* 2131361985 */:
                getMBinding().setTask2(Integer.parseInt(v.getTag().toString()));
                getMonth();
                break;
            case R.id.cl_month /* 2131362012 */:
                startActivity(MonthCardActivity.class);
                break;
            case R.id.cl_sqk /* 2131362013 */:
                startActivity(CardActivity.class);
                break;
            case R.id.cl_user /* 2131362015 */:
                if (Constant.INSTANCE.getLogged()) {
                    startActivity(VipActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
                break;
            case R.id.iv_bbs /* 2131362162 */:
                Application application = getMContext().getApplication();
                Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.cy.yyjia.zhe28.MyApplication");
                ((MyApplication) application).toMain(1);
                break;
            case R.id.iv_invite /* 2131362180 */:
                startActivity(InviteActivity.class);
                break;
            case R.id.ll_game /* 2131362260 */:
                Util.gotoGame(getMContext(), this.gid);
                break;
            case R.id.tv_baofu /* 2131362660 */:
                startActivity(BaofuActivity.class);
                break;
            case R.id.tv_boss /* 2131362663 */:
                startActivity(BossServerActivity.class);
                break;
            case R.id.tv_coupon /* 2131362677 */:
                startActivity(new Intent(getMContext(), (Class<?>) GameCouponActivity.class).putExtra("gid", this.gid));
                break;
            case R.id.tv_daily_coupon /* 2131362679 */:
                startActivity(DailyCouponActivity.class);
                break;
            case R.id.tv_daily_task /* 2131362680 */:
                startActivity(DailyTaskActivity.class);
                break;
            case R.id.tv_monthly_task /* 2131362729 */:
                startActivity(MonthlyTaskActivity.class);
                break;
            case R.id.tv_more /* 2131362730 */:
                startActivity(SanbaoActivity.class);
                break;
        }
    }

    public final void getData() {
        Repository.INSTANCE.getWelfareData3(new Function1<WelfareBean3, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WelfareBean3 welfareBean3) {
                invoke2(welfareBean3);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WelfareBean3 it) {
                Intrinsics.checkNotNullParameter(it, "it");
                WelfareFragment3.access$getMBinding(WelfareFragment3.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.getData.2
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
                WelfareFragment3.this.netFail(it);
            }
        });
        Repository.INSTANCE.getSign(new Function1<QiandaoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.getData.3
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
                WelfareFragment3.access$getMBinding(WelfareFragment3.this).setQiandao(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.getData.4
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
                WelfareFragment3.this.netFail(it);
            }
        });
    }

    public final void getNav() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("t", String.valueOf(System.currentTimeMillis()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new WelfareFragment3$getNav$$inlined$get$1("monthTask/category", linkedHashMap, null, this, this), 3, null);
    }

    public final void getMonth() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("t", String.valueOf(System.currentTimeMillis()));
        MonthlyTaskNavBean nav = getMBinding().getNav();
        Intrinsics.checkNotNull(nav);
        linkedHashMap.put("type", String.valueOf(nav.getList().get(getMBinding().getTask2()).getType()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new WelfareFragment3$getMonth$$inlined$get$1("monthTask/index", linkedHashMap, null, this, this), 3, null);
    }

    public final void getData3() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new WelfareFragment3$getData3$$inlined$get$1("dailyRechargeCoupon/index", MapsKt.emptyMap(), null, this, this), 3, null);
    }

    public final void receiveAll(final View v) {
        String str;
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getMBinding().btnReceive1)) {
            str = "task/receiveAllTaskWelfare";
        } else {
            str = Intrinsics.areEqual(v, getMBinding().btnReceive3) ? "dailyRechargeCoupon/receiveAll" : "monthTask/receiveAll";
        }
        NetUtil.post2$default(NetUtil.INSTANCE, str, new LinkedHashMap(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.receiveAll.1
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
                WelfareFragment3.this.toast(it.getMsg());
                View view = v;
                if (Intrinsics.areEqual(view, WelfareFragment3.access$getMBinding(WelfareFragment3.this).btnReceive1)) {
                    WelfareFragment3.this.getData();
                } else if (Intrinsics.areEqual(view, WelfareFragment3.access$getMBinding(WelfareFragment3.this).btnReceive2)) {
                    WelfareFragment3.this.getMonth();
                } else if (Intrinsics.areEqual(view, WelfareFragment3.access$getMBinding(WelfareFragment3.this).btnReceive3)) {
                    WelfareFragment3.this.getData3();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.receiveAll.2
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
                WelfareFragment3.this.netFail(it);
            }
        }, null, 16, null);
    }

    public final void getData5() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new WelfareFragment3$getData5$$inlined$get$1("topic/bossTopic", MapsKt.emptyMap(), null, this, this), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3$getBanner$1, reason: invalid class name */
    /* JADX INFO: compiled from: WelfareFragment3.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/BbsBannerBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<List<BbsBannerBean>, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<BbsBannerBean> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<BbsBannerBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            WelfareFragment3.access$getMBinding(WelfareFragment3.this).banner.setCanLoop(it.size() > 1);
            WelfareFragment3.access$getMBinding(WelfareFragment3.this).banner.setPointViewVisible(it.size() > 1);
            WelfareFragment3.access$getMBinding(WelfareFragment3.this).banner.setPages(new CBViewHolderCreator() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3$getBanner$1$$ExternalSyntheticLambda0
                @Override // com.bigkoo.convenientbanner.holder.CBViewHolderCreator
                public final Object createHolder() {
                    return WelfareFragment3.AnonymousClass1.invoke$lambda$0();
                }
            }, it).setPageIndicator(new int[]{R.mipmap.ic_indicator, R.drawable.ic_indicator_true_long}).startTurning(2000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BbsBannerHolder invoke$lambda$0() {
            return new BbsBannerHolder();
        }
    }

    public final void getBanner() {
        Repository.INSTANCE.getBbsBanner(new AnonymousClass1(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3.getBanner.2
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
                WelfareFragment3.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getData();
        getNav();
        getData3();
        getData5();
    }
}
