package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.ActivityMonthCardBinding;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UnableGameBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.ui.dialog.CardRulerDialog;
import com.cy.yyjia.zhe28.ui.dialog.PayDialog;
import com.cy.yyjia.zhe28.ui.dialog.UnableGameDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: MonthCardActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0014J\b\u0010\u000e\u001a\u00020\u0006H\u0002¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/MonthCardActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityMonthCardBinding;", "Landroid/view/View$OnClickListener;", "()V", "alipay", "", "buy", "getData", "init", "onClick", "v", "Landroid/view/View;", "onResume", "wxpay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MonthCardActivity extends BaseActivity<ActivityMonthCardBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public MonthCardActivity() {
        super(R.layout.activity_month_card, 1);
    }

    public static final /* synthetic */ ActivityMonthCardBinding access$getMBinding(MonthCardActivity monthCardActivity) {
        return monthCardActivity.getMBinding();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        startActivity(new Intent(this, (Class<?>) SubscribeActivity.class).putExtra("type", 1));
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void init$lambda$0(BaseAdapter titleAdapter, MonthCardActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
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

    private static final void init$lambda$1(BaseAdapter adapter, MonthCardActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
    }

    public final void getData() {
        Repository.INSTANCE.getMonthCardInfo(new Function1<CardModuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.getData.1
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
                MonthCardActivity.access$getMBinding(MonthCardActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.getData.2
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
                MonthCardActivity.this.netFail(it);
            }
        });
        if (Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.getData.3
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
                    MonthCardActivity.access$getMBinding(MonthCardActivity.this).setUser(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.getData.4
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
                    MonthCardActivity.this.netFail(it);
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getMBinding().ivBack)) {
            finish();
        }
        if (getMBinding().getData() != null) {
            switch (v.getId()) {
                case R.id.tv_buy /* 2131362665 */:
                    doWithLogin(new C10183());
                    break;
                case R.id.tv_gain /* 2131362707 */:
                    doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.onClick.2
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
                            final MonthCardActivity monthCardActivity = MonthCardActivity.this;
                            Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.onClick.2.1
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
                                    monthCardActivity.toast(it.getMsg());
                                    monthCardActivity.getData();
                                }
                            };
                            final MonthCardActivity monthCardActivity2 = MonthCardActivity.this;
                            Function1<Exception, Unit> function12 = new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.onClick.2.2
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
                                    monthCardActivity2.netFail(it);
                                }
                            };
                            CardModuleBean data = MonthCardActivity.access$getMBinding(MonthCardActivity.this).getData();
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
                    repository.getUnableGames(false, selectedModule.getId(), new Function1<List<UnableGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.onClick.4
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
                            new UnableGameDialog(MonthCardActivity.this).setData(it).show();
                        }
                    }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.onClick.5
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
                            MonthCardActivity.this.netFail(it);
                        }
                    });
                    break;
                case R.id.tv_record /* 2131362757 */:
                    doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.onClick.1
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
                            MonthCardActivity.this.startActivity(CardRecordActivity.class);
                        }
                    });
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity$onClick$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MonthCardActivity.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C10183 extends Lambda implements Function0<Unit> {
        C10183() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            final SharedPreferences sharedPreferences = MonthCardActivity.this.getSharedPreferences("yktip", 0);
            if (sharedPreferences.getBoolean("show", true)) {
                CardRulerDialog title = new CardRulerDialog(MonthCardActivity.this.getMContext()).setTitle("月卡规则");
                CardModuleBean data = MonthCardActivity.access$getMBinding(MonthCardActivity.this).getData();
                Intrinsics.checkNotNull(data);
                CardInfoBean selectedModule = data.getSelectedModule();
                Intrinsics.checkNotNull(selectedModule);
                CardRulerDialog text = title.setText(selectedModule.getConfig().getDesc());
                final MonthCardActivity monthCardActivity = MonthCardActivity.this;
                CardRulerDialog cardRulerDialog = (CardRulerDialog) text.setOnClickListener(R.id.tv_buy, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity$onClick$3$$ExternalSyntheticLambda0
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        MonthCardActivity.C10183.invoke$lambda$0(monthCardActivity, baseDialog, view);
                    }
                });
                final MonthCardActivity monthCardActivity2 = MonthCardActivity.this;
                ((CardRulerDialog) cardRulerDialog.setOnClickListener(R.id.tv_tip, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity$onClick$3$$ExternalSyntheticLambda1
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        MonthCardActivity.C10183.invoke$lambda$1(sharedPreferences, monthCardActivity2, baseDialog, view);
                    }
                })).show();
                return;
            }
            MonthCardActivity.this.buy();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(MonthCardActivity this$0, BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            baseDialog.dismiss();
            this$0.buy();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(SharedPreferences sharedPreferences, MonthCardActivity this$0, BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean("show", false);
            editorEdit.apply();
            baseDialog.dismiss();
            this$0.buy();
        }
    }

    public final void buy() {
        PayDialog payDialog = new PayDialog(this);
        CardModuleBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        CardInfoBean selectedModule = data.getSelectedModule();
        Intrinsics.checkNotNull(selectedModule);
        payDialog.setPrice(selectedModule.getSelectPrice().getPrice()).setPayListener(new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.buy.1
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
                    MonthCardActivity.this.alipay();
                } else {
                    MonthCardActivity.this.wxpay();
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
        Repository.INSTANCE.alipay(2, linkedHashMap, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.alipay.1
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
                Intent intent = new Intent(MonthCardActivity.this, (Class<?>) WebPayActivity.class);
                intent.putExtra("url", payInfo.getPaydata());
                MonthCardActivity.this.startActivity(intent);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.alipay.2
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
                MonthCardActivity.this.netFail(exception);
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
        Repository.INSTANCE.wxpay(2, linkedHashMap, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.wxpay.1
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
                    Intent intent = new Intent(MonthCardActivity.this, (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", payInfo.getPaydata().getMweb_url());
                    MonthCardActivity.this.startActivity(intent);
                } catch (Exception e) {
                    MonthCardActivity.this.log(e.toString());
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MonthCardActivity.wxpay.2
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
                MonthCardActivity.this.netFail(exception);
            }
        });
    }
}
