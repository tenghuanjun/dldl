package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.ActivitySubscribeBinding;
import com.cy.yyjia.zhe28.databinding.FragmentSubscribe2Binding;
import com.cy.yyjia.zhe28.databinding.FragmentSubscribeBinding;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UnableGameBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.ui.activity.SubscribeActivity;
import com.cy.yyjia.zhe28.ui.dialog.PayDialog;
import com.cy.yyjia.zhe28.ui.dialog.UnableGameDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscribeActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SubscribeActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivitySubscribeBinding;", "()V", "init", "", "initTab", "ChildFragment", "ChildFragment2", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SubscribeActivity extends BaseActivity<ActivitySubscribeBinding> {
    public static final int $stable = 0;

    public SubscribeActivity() {
        super(R.layout.activity_subscribe, 1);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubscribeActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().vp2.setAdapter(new FragmentStateAdapter(this) { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity.init.2
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return 2;
            }

            {
                super(this);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                if (position == 0) {
                    return new ChildFragment();
                }
                return new ChildFragment2();
            }
        });
        initTab();
        getMBinding().vp2.setCurrentItem(getIntent().getIntExtra("type", 0), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final SubscribeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$init$1$1
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
                Intent intent = new Intent(this.this$0.getMContext(), (Class<?>) CardRecordActivity.class);
                if (this.this$0.getMBinding().vp2.getCurrentItem() == 0) {
                    intent.putExtra("saving", true);
                }
                this.this$0.startActivity(intent);
            }
        });
    }

    public final void initTab() {
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, 16.0f, 16.0f, true);
        final List listMutableListOf = CollectionsKt.mutableListOf("省钱卡", "月卡");
        new TabLayoutMediator(getMBinding().tab, getMBinding().vp2, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab2, int i) {
                SubscribeActivity.initTab$lambda$1(listMutableListOf, tab2, i);
            }
        }).attach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initTab$lambda$1(List title, TabLayout.Tab tab, int i) {
        Intrinsics.checkNotNullParameter(title, "$title");
        Intrinsics.checkNotNullParameter(tab, "tab");
        tab.setText((CharSequence) title.get(i));
    }

    /* JADX INFO: compiled from: SubscribeActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0002¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SubscribeActivity$ChildFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentSubscribeBinding;", "Landroid/view/View$OnClickListener;", "()V", "alipay", "", "buy", "getData", "init", "onClick", "v", "Landroid/view/View;", "onResume", "wxpay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ChildFragment extends BaseFragment<FragmentSubscribeBinding> implements View.OnClickListener {
        public static final int $stable = 0;

        public ChildFragment() {
            super(R.layout.fragment_subscribe);
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().setOnClick(this);
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_card_title, null, 2, null);
            getMBinding().rvTitle.setAdapter(baseAdapter);
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    SubscribeActivity.ChildFragment.init$lambda$0(baseAdapter, this, baseQuickAdapter, view, i);
                }
            });
            final BaseAdapter baseAdapter2 = new BaseAdapter(R.layout.item_card_price, null, 2, null);
            getMBinding().rv.setAdapter(baseAdapter2);
            baseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    SubscribeActivity.ChildFragment.init$lambda$1(baseAdapter2, this, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void init$lambda$0(BaseAdapter titleAdapter, ChildFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(titleAdapter, "$titleAdapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            int size = titleAdapter.getData().size();
            int i2 = 0;
            while (i2 < size) {
                ((CardInfoBean) titleAdapter.getItem(i2)).setSelected(i2 == i);
                i2++;
            }
            this$0.getMBinding().setData(this$0.getMBinding().getData());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(BaseAdapter adapter, ChildFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "$adapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            Iterator it = adapter.getData().iterator();
            while (it.hasNext()) {
                ((CardInfoBean.Price) it.next()).setSelected(false);
            }
            ((CardInfoBean.Price) adapter.getData().get(i)).setSelected(true);
            CardModuleBean data = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            selectedModule.setSelectPrice((CardInfoBean.Price) adapter.getData().get(i));
            CardModuleBean data2 = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            CardInfoBean selectedModule2 = data2.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule2);
            selectedModule2.setSelectPricePosition(i);
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            getData();
        }

        public final void getData() {
            Repository.INSTANCE.getCardInfo(new Function1<CardModuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$getData$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CardModuleBean cardModuleBean) {
                    invoke2(cardModuleBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CardModuleBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.getModules().get(0).setSelected(true);
                    for (CardInfoBean cardInfoBean : it.getModules()) {
                        cardInfoBean.getList().get(0).setSelected(true);
                        cardInfoBean.setSelectPrice(cardInfoBean.getList().get(0));
                    }
                    this.this$0.getMBinding().setData(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$getData$2
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
            if (Constant.INSTANCE.getLogged()) {
                Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$getData$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                        invoke2(userBean);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(UserBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        this.this$0.getMBinding().setUser(it);
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$getData$4
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
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intrinsics.checkNotNullParameter(v, "v");
            if (getMBinding().getData() != null) {
                switch (v.getId()) {
                    case R.id.tv_buy /* 2131362665 */:
                        doWithLogin(new SubscribeActivity$ChildFragment$onClick$3(this));
                        break;
                    case R.id.tv_gain /* 2131362707 */:
                        doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$onClick$2
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
                                Repository repository = Repository.INSTANCE;
                                final SubscribeActivity.ChildFragment childFragment = this.this$0;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$onClick$2.1
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
                                        childFragment.toast(it.getMsg());
                                        childFragment.getData();
                                    }
                                };
                                final SubscribeActivity.ChildFragment childFragment2 = this.this$0;
                                Repository.getCardCoupon$default(repository, true, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$onClick$2.2
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
                                        childFragment2.netFail(it);
                                    }
                                }, null, 8, null);
                            }
                        });
                        break;
                    case R.id.tv_game /* 2131362708 */:
                    case R.id.tv_game2 /* 2131362710 */:
                        Repository repository = Repository.INSTANCE;
                        CardModuleBean data = getMBinding().getData();
                        Intrinsics.checkNotNull(data);
                        CardInfoBean selectedModule = data.getSelectedModule();
                        Intrinsics.checkNotNull(selectedModule);
                        repository.getUnableGames(true, selectedModule.getId(), new Function1<List<UnableGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$onClick$4
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(List<UnableGameBean> list) {
                                invoke2(list);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(List<UnableGameBean> it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                new UnableGameDialog(this.this$0.getMContext()).setData(it).show();
                            }
                        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$onClick$5
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
                        break;
                    case R.id.tv_record /* 2131362757 */:
                        doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$onClick$1
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
                                Intent intent = new Intent(this.this$0.getMContext(), (Class<?>) CardRecordActivity.class);
                                intent.putExtra("saving", true);
                                this.this$0.startActivity(intent);
                            }
                        });
                        break;
                }
            }
        }

        public final void buy() {
            PayDialog payDialog = new PayDialog(getMContext());
            CardModuleBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            payDialog.setPrice(selectedModule.getSelectPrice().getPrice()).setPayListener(new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$buy$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        this.this$0.alipay();
                    } else {
                        this.this$0.wxpay();
                    }
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void alipay() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
            CardModuleBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            linkedHashMap2.put("type", selectedModule.getSelectPrice().getType());
            linkedHashMap2.put("paytype", "alipay_bank");
            linkedHashMap2.put("driver", "APP");
            linkedHashMap2.put("os", "APP");
            CardModuleBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            CardInfoBean selectedModule2 = data2.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule2);
            linkedHashMap2.put("module", String.valueOf(selectedModule2.getId()));
            Repository.INSTANCE.alipay(3, linkedHashMap, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$alipay$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PayInfo payInfo) {
                    invoke2(payInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PayInfo payInfo) {
                    Intrinsics.checkNotNullParameter(payInfo, "payInfo");
                    Intent intent = new Intent(this.this$0.getMContext(), (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", payInfo.getPaydata());
                    this.this$0.startActivity(intent);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$alipay$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception exception) {
                    Intrinsics.checkNotNullParameter(exception, "exception");
                    this.this$0.netFail(exception);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void wxpay() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
            CardModuleBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            linkedHashMap2.put("type", selectedModule.getSelectPrice().getType());
            linkedHashMap2.put("paytype", "wxpay_bank");
            linkedHashMap2.put("driver", "mobile");
            linkedHashMap2.put("os", "APP");
            CardModuleBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            CardInfoBean selectedModule2 = data2.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule2);
            linkedHashMap2.put("module", String.valueOf(selectedModule2.getId()));
            Repository.INSTANCE.wxpay(3, linkedHashMap, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$wxpay$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(WxPayInfo wxPayInfo) {
                    invoke2(wxPayInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(WxPayInfo payInfo) {
                    Intrinsics.checkNotNullParameter(payInfo, "payInfo");
                    try {
                        Intent intent = new Intent(this.this$0.getMContext(), (Class<?>) WebPayActivity.class);
                        intent.putExtra("url", payInfo.getPaydata().getMweb_url());
                        this.this$0.startActivity(intent);
                    } catch (Exception e) {
                        this.this$0.log(e.toString());
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment$wxpay$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception exception) {
                    Intrinsics.checkNotNullParameter(exception, "exception");
                    this.this$0.netFail(exception);
                }
            });
        }
    }

    /* JADX INFO: compiled from: SubscribeActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0002¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SubscribeActivity$ChildFragment2;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentSubscribe2Binding;", "Landroid/view/View$OnClickListener;", "()V", "alipay", "", "buy", "getData", "init", "onClick", "v", "Landroid/view/View;", "onResume", "wxpay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ChildFragment2 extends BaseFragment<FragmentSubscribe2Binding> implements View.OnClickListener {
        public static final int $stable = 0;

        public ChildFragment2() {
            super(R.layout.fragment_subscribe2);
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().setOnClick(this);
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_card_title, null, 2, null);
            getMBinding().rvTitle.setAdapter(baseAdapter);
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    SubscribeActivity.ChildFragment2.init$lambda$0(baseAdapter, this, baseQuickAdapter, view, i);
                }
            });
            final BaseAdapter baseAdapter2 = new BaseAdapter(R.layout.item_card_price, null, 2, null);
            getMBinding().rv.setAdapter(baseAdapter2);
            baseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    SubscribeActivity.ChildFragment2.init$lambda$1(baseAdapter2, this, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void init$lambda$0(BaseAdapter titleAdapter, ChildFragment2 this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(titleAdapter, "$titleAdapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            int size = titleAdapter.getData().size();
            int i2 = 0;
            while (i2 < size) {
                ((CardInfoBean) titleAdapter.getItem(i2)).setSelected(i2 == i);
                i2++;
            }
            this$0.getMBinding().setData(this$0.getMBinding().getData());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(BaseAdapter adapter, ChildFragment2 this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "$adapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            Iterator it = adapter.getData().iterator();
            while (it.hasNext()) {
                ((CardInfoBean.Price) it.next()).setSelected(false);
            }
            ((CardInfoBean.Price) adapter.getData().get(i)).setSelected(true);
            CardModuleBean data = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            selectedModule.setSelectPrice((CardInfoBean.Price) adapter.getData().get(i));
            CardModuleBean data2 = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            CardInfoBean selectedModule2 = data2.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule2);
            selectedModule2.setSelectPricePosition(i);
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            getData();
        }

        public final void getData() {
            Repository.INSTANCE.getMonthCardInfo(new Function1<CardModuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$getData$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CardModuleBean cardModuleBean) {
                    invoke2(cardModuleBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CardModuleBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.getModules().get(0).setSelected(true);
                    for (CardInfoBean cardInfoBean : it.getModules()) {
                        cardInfoBean.getList().get(0).setSelected(true);
                        cardInfoBean.setSelectPrice(cardInfoBean.getList().get(0));
                    }
                    this.this$0.getMBinding().setData(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$getData$2
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
            if (Constant.INSTANCE.getLogged()) {
                Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$getData$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                        invoke2(userBean);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(UserBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        this.this$0.getMBinding().setUser(it);
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$getData$4
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
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intrinsics.checkNotNullParameter(v, "v");
            if (getMBinding().getData() != null) {
                switch (v.getId()) {
                    case R.id.tv_buy /* 2131362665 */:
                        doWithLogin(new SubscribeActivity$ChildFragment2$onClick$3(this));
                        break;
                    case R.id.tv_gain /* 2131362707 */:
                        doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$2
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
                                Repository repository = Repository.INSTANCE;
                                final SubscribeActivity.ChildFragment2 childFragment2 = this.this$0;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$2.1
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
                                        childFragment2.toast(it.getMsg());
                                        childFragment2.getData();
                                    }
                                };
                                final SubscribeActivity.ChildFragment2 childFragment22 = this.this$0;
                                Function1<Exception, Unit> function12 = new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$2.2
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
                                        childFragment22.netFail(it);
                                    }
                                };
                                CardModuleBean data = this.this$0.getMBinding().getData();
                                Intrinsics.checkNotNull(data);
                                CardInfoBean selectedModule = data.getSelectedModule();
                                Intrinsics.checkNotNull(selectedModule);
                                repository.getCardCoupon(false, function1, function12, Integer.valueOf(selectedModule.getId()));
                            }
                        });
                        break;
                    case R.id.tv_game /* 2131362708 */:
                    case R.id.tv_game2 /* 2131362710 */:
                        Repository repository = Repository.INSTANCE;
                        CardModuleBean data = getMBinding().getData();
                        Intrinsics.checkNotNull(data);
                        CardInfoBean selectedModule = data.getSelectedModule();
                        Intrinsics.checkNotNull(selectedModule);
                        repository.getUnableGames(false, selectedModule.getId(), new Function1<List<UnableGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$4
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(List<UnableGameBean> list) {
                                invoke2(list);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(List<UnableGameBean> it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                new UnableGameDialog(this.this$0.getMContext()).setData(it).show();
                            }
                        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$5
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
                        break;
                    case R.id.tv_record /* 2131362757 */:
                        doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$1
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
                                this.this$0.startActivity(CardRecordActivity.class);
                            }
                        });
                        break;
                }
            }
        }

        public final void buy() {
            PayDialog payDialog = new PayDialog(getMContext());
            CardModuleBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            payDialog.setPrice(selectedModule.getSelectPrice().getPrice()).setPayListener(new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$buy$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        this.this$0.alipay();
                    } else {
                        this.this$0.wxpay();
                    }
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void alipay() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
            CardModuleBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            linkedHashMap2.put("type", selectedModule.getSelectPrice().getType());
            linkedHashMap2.put("paytype", "alipay_bank");
            linkedHashMap2.put("driver", "APP");
            linkedHashMap2.put("os", "APP");
            CardModuleBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            CardInfoBean selectedModule2 = data2.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule2);
            linkedHashMap2.put("module", String.valueOf(selectedModule2.getId()));
            Repository.INSTANCE.alipay(2, linkedHashMap, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$alipay$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PayInfo payInfo) {
                    invoke2(payInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PayInfo payInfo) {
                    Intrinsics.checkNotNullParameter(payInfo, "payInfo");
                    Intent intent = new Intent(this.this$0.getMContext(), (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", payInfo.getPaydata());
                    this.this$0.startActivity(intent);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$alipay$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception exception) {
                    Intrinsics.checkNotNullParameter(exception, "exception");
                    this.this$0.netFail(exception);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void wxpay() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
            CardModuleBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            linkedHashMap2.put("type", selectedModule.getSelectPrice().getType());
            linkedHashMap2.put("paytype", "wxpay_bank");
            linkedHashMap2.put("driver", "mobile");
            linkedHashMap2.put("os", "APP");
            CardModuleBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            CardInfoBean selectedModule2 = data2.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule2);
            linkedHashMap2.put("module", String.valueOf(selectedModule2.getId()));
            Repository.INSTANCE.wxpay(2, linkedHashMap, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$wxpay$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(WxPayInfo wxPayInfo) {
                    invoke2(wxPayInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(WxPayInfo payInfo) {
                    Intrinsics.checkNotNullParameter(payInfo, "payInfo");
                    try {
                        Intent intent = new Intent(this.this$0.getMContext(), (Class<?>) WebPayActivity.class);
                        intent.putExtra("url", payInfo.getPaydata().getMweb_url());
                        this.this$0.startActivity(intent);
                    } catch (Exception e) {
                        this.this$0.log(e.toString());
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$wxpay$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception exception) {
                    Intrinsics.checkNotNullParameter(exception, "exception");
                    this.this$0.netFail(exception);
                }
            });
        }
    }
}
