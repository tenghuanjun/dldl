package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.ActivityDealDetailBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.DealDetailResult;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.PtbHomeBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.ui.adapter.PicAdapter;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.DickerDialog;
import com.cy.yyjia.zhe28.ui.dialog.PayDialog;
import com.cy.yyjia.zhe28.ui.dialog.ShareDialog;
import com.cy.yyjia.zhe28.util.Constant;
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

/* JADX INFO: compiled from: DealDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0010\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DealDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDealDetailBinding;", "Landroid/view/View$OnClickListener;", "()V", "dickerMoney", "", "getDickerMoney", "()Ljava/lang/String;", "dickerMoney$delegate", "Lkotlin/Lazy;", "id", "", "getId", "()I", "id$delegate", "isCustomer", "", "()Z", "isCustomer$delegate", "rule", "Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;", "getRule", "()Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;", "setRule", "(Lcom/cy/yyjia/zhe28/domain/DealDetailResult$TradeInfo;)V", "alipay", "", "type", "getData", "init", "onClick", "v", "Landroid/view/View;", "wxpay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealDetailActivity extends BaseActivity<ActivityDealDetailBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: dickerMoney$delegate, reason: from kotlin metadata */
    private final Lazy dickerMoney;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;

    /* JADX INFO: renamed from: isCustomer$delegate, reason: from kotlin metadata */
    private final Lazy isCustomer;
    public DealDetailResult.TradeInfo rule;

    public DealDetailActivity() {
        super(R.layout.activity_deal_detail, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
        this.dickerMoney = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$dickerMoney$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("dickerMoney");
            }
        });
        this.isCustomer = LazyKt.lazy(new Function0<Boolean>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.isCustomer.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(DealDetailActivity.this.getIntent().getBooleanExtra("customer", false));
            }
        });
    }

    public static final /* synthetic */ ActivityDealDetailBinding access$getMBinding(DealDetailActivity dealDetailActivity) {
        return dealDetailActivity.getMBinding();
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final String getDickerMoney() {
        return (String) this.dickerMoney.getValue();
    }

    public final boolean isCustomer() {
        return ((Boolean) this.isCustomer.getValue()).booleanValue();
    }

    public final DealDetailResult.TradeInfo getRule() {
        DealDetailResult.TradeInfo tradeInfo = this.rule;
        if (tradeInfo != null) {
            return tradeInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rule");
        return null;
    }

    public final void setRule(DealDetailResult.TradeInfo tradeInfo) {
        Intrinsics.checkNotNullParameter(tradeInfo, "<set-?>");
        this.rule = tradeInfo;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().reTag.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DealDetailActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().setShowDickerPrice(getIntent().getBooleanExtra("dickerPrice", false));
        boolean showDickerPrice = getMBinding().getShowDickerPrice();
        StringBuilder sb = new StringBuilder();
        sb.append(showDickerPrice);
        log(sb.toString());
        if (!TextUtils.isEmpty(getDickerMoney())) {
            if (!isCustomer()) {
                getMBinding().llDicker.setVisibility(0);
            }
            getMBinding().tvDickerPrice.setText("议价金额：￥" + getDickerMoney());
            getMBinding().tvDickerPrice1.setText("议价金额：￥" + getDickerMoney());
        }
        getMBinding().rv.setAdapter(new PicAdapter());
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(DealDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final DealBean data = this$0.getMBinding().getData();
        if (data != null) {
            new ShareDialog(this$0.getMContext()).setTitle(data.getGame().getName()).setDesc(data.getDescription()).setImgUrl(data.getGameIcon()).setDeal(data.getId(), data.getIscollect(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$init$1$1$1
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
                    data.setIscollect(!r0.getIscollect());
                }
            }).setUrl(NetUtil.BASE_URL3 + "dist/trumpet-detail?id=" + data.getId()).show();
        }
    }

    public final void getData() {
        Repository.INSTANCE.getDealDetail(getId(), new Function1<DealDetailResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DealDetailResult dealDetailResult) {
                invoke2(dealDetailResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DealDetailResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DealDetailActivity.access$getMBinding(DealDetailActivity.this).setData(it.getDetail());
                DealDetailActivity.this.setRule(it.getTradeInfo());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.getData.2
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
                DealDetailActivity dealDetailActivity = DealDetailActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                dealDetailActivity.log(localizedMessage);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (!Constant.INSTANCE.getLogged()) {
            startActivity(LoginActivity.class);
            return;
        }
        if (getMBinding().getData() != null) {
            int id = v.getId();
            if (id != R.id.tv_buy) {
                switch (id) {
                    case R.id.tv_dicker /* 2131362683 */:
                        new DickerDialog(this).setListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                invoke2(str);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String price) {
                                Intrinsics.checkNotNullParameter(price, "price");
                                Repository repository = Repository.INSTANCE;
                                int id2 = DealDetailActivity.this.getId();
                                final DealDetailActivity dealDetailActivity = DealDetailActivity.this;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.1.1
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
                                        dealDetailActivity.toast(it.getMsg());
                                    }
                                };
                                final DealDetailActivity dealDetailActivity2 = DealDetailActivity.this;
                                repository.dicker(id2, price, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.1.2
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
                                        dealDetailActivity2.netFail(it);
                                    }
                                });
                            }
                        }).show();
                        break;
                    case R.id.tv_dicker1 /* 2131362684 */:
                        new ConfirmDialog(this).setTip("确定要同意本次议价申请吗？").setBtnText("同意").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.5
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
                                int id2 = DealDetailActivity.this.getId();
                                final DealDetailActivity dealDetailActivity = DealDetailActivity.this;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.5.1
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
                                        dealDetailActivity.toast(it.getMsg());
                                        if (it.getCode() == 200) {
                                            dealDetailActivity.finish();
                                        }
                                    }
                                };
                                final DealDetailActivity dealDetailActivity2 = DealDetailActivity.this;
                                repository.agreeDicker(id2, 1, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.5.2
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
                                        dealDetailActivity2.netFail(it);
                                    }
                                });
                            }
                        }).show();
                        break;
                    case R.id.tv_dicker2 /* 2131362685 */:
                        new ConfirmDialog(this).setTip("确定要拒绝本次议价申请吗？").setBtnText("拒绝").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.6
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
                                int id2 = DealDetailActivity.this.getId();
                                final DealDetailActivity dealDetailActivity = DealDetailActivity.this;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.6.1
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
                                        dealDetailActivity.toast(it.getMsg());
                                        if (it.getCode() == 200) {
                                            dealDetailActivity.finish();
                                        }
                                    }
                                };
                                final DealDetailActivity dealDetailActivity2 = DealDetailActivity.this;
                                repository.agreeDicker(id2, 0, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.onClick.6.2
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
                                        dealDetailActivity2.netFail(it);
                                    }
                                });
                            }
                        }).show();
                        break;
                }
                return;
            }
            if (Constant.INSTANCE.getLogged()) {
                new FastDialog(this).setContentView(R.layout.dialog_deal_buy_notice).setText(R.id.title, getRule().getTitle()).setText(R.id.tv_tag1, getRule().getContext()).setText(R.id.f438tv, getRule().getDesc()).setOnClickListener(R.id.f438tv, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$$ExternalSyntheticLambda0
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        DealDetailActivity.onClick$lambda$2(baseDialog, view);
                    }
                }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$$ExternalSyntheticLambda1
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        baseDialog.dismiss();
                    }
                }).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$$ExternalSyntheticLambda2
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        DealDetailActivity.onClick$lambda$4(this.f$0, baseDialog, view);
                    }
                }).show();
            } else {
                toLogin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$2(BaseDialog baseDialog, View view) {
        view.setSelected(!view.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$4(final DealDetailActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View viewFindViewById = baseDialog.findViewById(R.id.f438tv);
        Intrinsics.checkNotNull(viewFindViewById);
        if (!((TextView) viewFindViewById).isSelected()) {
            this$0.toast("请阅读并同意买家须知");
        } else {
            baseDialog.dismiss();
            Repository.INSTANCE.getDealPayInfo(this$0.getId(), new Function1<PtbHomeBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$onClick$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PtbHomeBean ptbHomeBean) {
                    invoke2(ptbHomeBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final PtbHomeBean result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    PayDialog payDialog = new PayDialog(this.this$0);
                    DealBean data = DealDetailActivity.access$getMBinding(this.this$0).getData();
                    Intrinsics.checkNotNull(data);
                    PayDialog price = payDialog.setPrice(data.getSellMoney());
                    final DealDetailActivity dealDetailActivity = this.this$0;
                    price.setPayListener(new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$onClick$4$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                dealDetailActivity.alipay(result.getPayment().getAlipay_bank().getPayType());
                            } else {
                                dealDetailActivity.wxpay(result.getPayment().getWxpay_bank().getPayType());
                            }
                        }
                    }).show();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity$onClick$4$2
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void alipay(String type) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put("treadId", String.valueOf(getId()));
        linkedHashMap2.put("paytype", type);
        linkedHashMap2.put("driver", "APP");
        linkedHashMap2.put("os", "APP");
        Repository.INSTANCE.alipay(1, linkedHashMap, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.alipay.1
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
                if (Intrinsics.areEqual(payInfo.getPaytype(), "")) {
                    DealDetailActivity.this.toast(payInfo.getMsg());
                    return;
                }
                Intent intent = new Intent(DealDetailActivity.this, (Class<?>) WebPayActivity.class);
                intent.putExtra("url", payInfo.getPaydata());
                DealDetailActivity.this.startActivity(intent);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.alipay.2
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
                DealDetailActivity.this.netFail(exception);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void wxpay(String type) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put("treadId", String.valueOf(getId()));
        linkedHashMap2.put("paytype", type);
        linkedHashMap2.put("driver", "mobile");
        linkedHashMap2.put("os", "APP");
        Repository.INSTANCE.wxpay(1, linkedHashMap, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.wxpay.1
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
                if (Intrinsics.areEqual(payInfo.getPaytype(), "")) {
                    DealDetailActivity.this.toast(payInfo.getMsg());
                    return;
                }
                try {
                    Intent intent = new Intent(DealDetailActivity.this, (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", payInfo.getPaydata().getMweb_url());
                    DealDetailActivity.this.startActivity(intent);
                } catch (Exception e) {
                    DealDetailActivity.this.log(e.toString());
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealDetailActivity.wxpay.2
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
                DealDetailActivity.this.netFail(exception);
            }
        });
    }
}
