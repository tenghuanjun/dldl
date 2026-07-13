package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityGameCouponBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameCouponBinding;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.domain.GameCouponBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.ReceiveStatusBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.volcengine.common.contant.CommonConstants;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameCouponActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020$J\b\u0010'\u001a\u00020$H\u0016J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0016J\u000e\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u001bJ\u0006\u0010-\u001a\u00020$R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R'\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001a\u001a\u00020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001f\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\"¨\u0006."}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameCouponActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGameCouponBinding;", "Landroid/view/View$OnClickListener;", "()V", CommonConstants.key_accountId, "", "getAccountId", "()Ljava/lang/String;", "setAccountId", "(Ljava/lang/String;)V", "accountList", "", "Lcom/cy/yyjia/zhe28/domain/ReceiveStatusBean;", "getAccountList", "()Ljava/util/List;", "setAccountList", "(Ljava/util/List;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameCouponBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "gid", "", "getGid", "()I", "gid$delegate", "page", "getPage", "setPage", "(I)V", "getAccount", "", "getData", "getNewData", "init", "onClick", "v", "Landroid/view/View;", "receive", "couponId", "receiveAll", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameCouponActivity extends BaseActivity<ActivityGameCouponBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private String accountId;
    public List<ReceiveStatusBean> accountList;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final Lazy gid;
    private int page;

    public GameCouponActivity() {
        super(R.layout.activity_game_coupon, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<CouponBean, ItemGameCouponBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CouponBean, ItemGameCouponBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_coupon, null, 2, null);
            }
        });
        this.gid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity$gid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.accountId = "0";
        this.page = 1;
    }

    public static final /* synthetic */ ActivityGameCouponBinding access$getMBinding(GameCouponActivity gameCouponActivity) {
        return gameCouponActivity.getMBinding();
    }

    public final BaseAdapter<CouponBean, ItemGameCouponBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getGid() {
        return ((Number) this.gid.getValue()).intValue();
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final void setAccountId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.accountId = str;
    }

    public final List<ReceiveStatusBean> getAccountList() {
        List<ReceiveStatusBean> list = this.accountList;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("accountList");
        return null;
    }

    public final void setAccountList(List<ReceiveStatusBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.accountList = list;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameCouponActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameCouponActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameCouponActivity.init$lambda$2(this.f$0);
            }
        });
        if (Constant.INSTANCE.getLogged()) {
            getAccount();
        }
        getNewData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final GameCouponActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (Constant.INSTANCE.getLogged()) {
            if (TextUtils.isEmpty(this$0.accountId)) {
                this$0.getMBinding().tvAccount.performClick();
                return;
            } else {
                this$0.receive(this$0.getAdapter().getItem(i).getId());
                return;
            }
        }
        this$0.toLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity$init$1$1
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
                this.this$0.getAccount();
                this.this$0.getNewData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameCouponActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getAdapter().getItem(i).setSelected(!this$0.getAdapter().getItem(i).getSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(GameCouponActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getAccount() {
        Repository.INSTANCE.getAccountList(1, getGid(), new Function1<PageBean<ReceiveStatusBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.getAccount.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<ReceiveStatusBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<ReceiveStatusBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getTotal_num() == 0) {
                    GameCouponActivity.access$getMBinding(GameCouponActivity.this).tvAccount.setText("领取前请先创建小号再领取抵扣券");
                } else {
                    GameCouponActivity.access$getMBinding(GameCouponActivity.this).tvAccount.setText("请选择小号");
                }
                GameCouponActivity.this.setAccountList(it.getList());
                Iterator<ReceiveStatusBean> it2 = GameCouponActivity.this.getAccountList().iterator();
                while (it2.hasNext()) {
                    it2.next().setSelected(false);
                }
                if (GameCouponActivity.this.getAccountList().size() > 0) {
                    GameCouponActivity.this.getAccountList().get(0).setSelected(true);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.getAccount.2
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
                GameCouponActivity.this.netFail(it);
            }
        });
    }

    public final void getData() {
        Repository.INSTANCE.getGameCouponList(this.accountId, getGid(), this.page, new Function1<GameCouponBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameCouponBean gameCouponBean) {
                invoke2(gameCouponBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameCouponBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (GameCouponActivity.this.getPage() == 1) {
                    GameCouponActivity.access$getMBinding(GameCouponActivity.this).setData(it);
                    GameCouponActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    GameCouponActivity.this.getAdapter().addData(it.getList());
                }
                GameCouponActivity gameCouponActivity = GameCouponActivity.this;
                gameCouponActivity.setPage(gameCouponActivity.getPage() + 1);
                gameCouponActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GameCouponActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GameCouponActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.getData.2
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
                GameCouponActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn_all /* 2131361951 */:
                receiveAll();
                break;
            case R.id.btn_change /* 2131361955 */:
            case R.id.tv_account /* 2131362654 */:
                new ReceiveStatusDialog(this).data(getAccountList(), getGid()).setConfirm(new Function1<ReceiveStatusBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.onClick.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ReceiveStatusBean receiveStatusBean) {
                        invoke2(receiveStatusBean);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ReceiveStatusBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        GameCouponActivity.this.setAccountId(String.valueOf(it.getId()));
                        GameCouponActivity.access$getMBinding(GameCouponActivity.this).tvAccount.setText(it.getName());
                        Iterator<ReceiveStatusBean> it2 = GameCouponActivity.this.getAccountList().iterator();
                        while (true) {
                            boolean z = true;
                            if (it2.hasNext()) {
                                ReceiveStatusBean next = it2.next();
                                if (next.getId() != it.getId()) {
                                    z = false;
                                }
                                next.setSelected(z);
                            } else {
                                GameCouponActivity.access$getMBinding(GameCouponActivity.this).btnChange.setVisibility(0);
                                GameCouponActivity.this.setPage(1);
                                GameCouponActivity.this.getNewData();
                                GameCouponActivity.this.log("小号名：" + it.getName() + " 小号id：" + it.getId());
                                return;
                            }
                        }
                    }
                }).show();
                break;
            case R.id.btn_month /* 2131361966 */:
                startActivity(MonthCardActivity.class);
                break;
            case R.id.btn_sqk /* 2131361980 */:
                startActivity(CardActivity.class);
                break;
        }
    }

    public final void receiveAll() {
        Repository.INSTANCE.receiveAll(getGid(), this.accountId, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.receiveAll.1
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
                GameCouponActivity.this.toast(it.getMsg());
                GameCouponActivity.this.getNewData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.receiveAll.2
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
                GameCouponActivity.this.netFail(it);
            }
        });
    }

    public final void receive(int couponId) {
        Repository.INSTANCE.receive(2, couponId, this.accountId, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.receive.1
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
                GameCouponActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    GameCouponActivity.this.getNewData();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameCouponActivity.receive.2
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
                GameCouponActivity.this.netFail(it);
            }
        });
    }

    public final void getNewData() {
        this.page = 1;
        getData();
    }
}
