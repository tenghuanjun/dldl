package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityYunBuyBinding;
import com.cy.yyjia.zhe28.databinding.ItemYunPriceBinding;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.cy.yyjia.zhe28.domain.YunPrice;
import com.cy.yyjia.zhe28.ui.dialog.RvPopup;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YunBuyActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J*\u0010\u0012\u001a\u00020\u00132\"\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016`\u0017J\u0006\u0010\u0018\u001a\u00020\u0013J\b\u0010\u0019\u001a\u0004\u0018\u00010\rJ\u0006\u0010\u001a\u001a\u00020\u0013J\b\u0010\u001b\u001a\u00020\u0013H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0013J*\u0010 \u001a\u00020\u00132\"\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016`\u0017R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/YunBuyActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityYunBuyBinding;", "Landroid/view/View$OnClickListener;", "()V", "did", "", "getDid", "()I", "did$delegate", "Lkotlin/Lazy;", "priceAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/YunPrice;", "Lcom/cy/yyjia/zhe28/databinding/ItemYunPriceBinding;", "getPriceAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "priceAdapter$delegate", "alipay", "", "param", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "getData", "getSelectPrice", "getYunData", "init", "onClick", "v", "Landroid/view/View;", "pay", "wxpay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class YunBuyActivity extends BaseActivity<ActivityYunBuyBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: did$delegate, reason: from kotlin metadata */
    private final Lazy did;

    /* JADX INFO: renamed from: priceAdapter$delegate, reason: from kotlin metadata */
    private final Lazy priceAdapter;

    public YunBuyActivity() {
        super(R.layout.activity_yun_buy, 0, 2, null);
        this.priceAdapter = LazyKt.lazy(new Function0<BaseAdapter<YunPrice, ItemYunPriceBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity$priceAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<YunPrice, ItemYunPriceBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_yun_price, null, 2, null);
            }
        });
        this.did = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity$did$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("deviceId", 0));
            }
        });
    }

    public static final /* synthetic */ ActivityYunBuyBinding access$getMBinding(YunBuyActivity yunBuyActivity) {
        return yunBuyActivity.getMBinding();
    }

    public final BaseAdapter<YunPrice, ItemYunPriceBinding> getPriceAdapter() {
        return (BaseAdapter) this.priceAdapter.getValue();
    }

    public final int getDid() {
        return ((Number) this.did.getValue()).intValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setRenew(getDid() != 0);
        getMBinding().setAlipay(true);
        getMBinding().setNum(1);
        getMBinding().rv.setAdapter(getPriceAdapter());
        getPriceAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                YunBuyActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
        if (getMBinding().getRenew()) {
            getMBinding().navigation.setTitle("续费云手机");
            getYunData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(YunBuyActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        YunPrice selectPrice = this$0.getSelectPrice();
        Intrinsics.checkNotNull(selectPrice);
        selectPrice.setSelected(false);
        this$0.getPriceAdapter().getItem(i).setSelected(true);
        this$0.getMBinding().setPrice(this$0.getPriceAdapter().getItem(i));
    }

    public final void getData() {
        Repository.INSTANCE.getYunBuyIndex(new Function1<List<YunPrice>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<YunPrice> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<YunPrice> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.get(0).setSelected(true);
                YunBuyActivity.access$getMBinding(YunBuyActivity.this).setPrice(it.get(0));
                YunBuyActivity.this.getPriceAdapter().setNewInstance(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.getData.2
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
                YunBuyActivity.this.netFail(it);
            }
        });
    }

    public final void getYunData() {
        Repository.INSTANCE.getYunIndex(new Function1<YunIndexBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.getYunData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(YunIndexBean yunIndexBean) {
                invoke2(yunIndexBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(YunIndexBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getDeviceList().size() > 0) {
                    Iterator<YunIndexBean.Device> it2 = it.getDeviceList().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        YunIndexBean.Device next = it2.next();
                        if (next.getId() == YunBuyActivity.this.getDid()) {
                            next.setSelected(true);
                            break;
                        }
                    }
                }
                YunBuyActivity.access$getMBinding(YunBuyActivity.this).setYun(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.getYunData.2
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
                YunBuyActivity.this.netFail(it);
            }
        });
    }

    public final YunPrice getSelectPrice() {
        for (YunPrice yunPrice : getPriceAdapter().getData()) {
            if (yunPrice.getSelected()) {
                return yunPrice;
            }
        }
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn_buy /* 2131361953 */:
                pay();
                break;
            case R.id.btn_wx /* 2131361987 */:
                getMBinding().setAlipay(false);
                break;
            case R.id.btn_zfb /* 2131361988 */:
                getMBinding().setAlipay(true);
                break;
            case R.id.iv_add /* 2131362160 */:
                ActivityYunBuyBinding mBinding = getMBinding();
                mBinding.setNum(mBinding.getNum() + 1);
                break;
            case R.id.iv_subtract /* 2131362197 */:
                getMBinding().setNum(r5.getNum() - 1);
                break;
            case R.id.tv_yun_device /* 2131362816 */:
                YunIndexBean yun = getMBinding().getYun();
                Intrinsics.checkNotNull(yun);
                BaseAdapter<?, ?> baseAdapter = new BaseAdapter<>(R.layout.item_yun_pop_device, yun.getDeviceList());
                final RvPopup width = new RvPopup(getMContext()).setAdapter(baseAdapter).setWidth(Util.dpToPx(getMContext(), 64.0f));
                width.showAsDropDown(v);
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity$$ExternalSyntheticLambda0
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        YunBuyActivity.onClick$lambda$1(width, this, baseQuickAdapter, view, i);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$1(RvPopup rvPopup, YunBuyActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        rvPopup.dismiss();
        YunIndexBean yun = this$0.getMBinding().getYun();
        Intrinsics.checkNotNull(yun);
        Iterator<YunIndexBean.Device> it = yun.getDeviceList().iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        YunIndexBean yun2 = this$0.getMBinding().getYun();
        Intrinsics.checkNotNull(yun2);
        yun2.getDeviceList().get(i).setSelected(true);
        this$0.getMBinding().setYun(this$0.getMBinding().getYun());
    }

    public final void pay() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
        YunPrice price = getMBinding().getPrice();
        Intrinsics.checkNotNull(price);
        linkedHashMap2.put("goodsId", String.valueOf(price.getId()));
        linkedHashMap2.put("deviceId", String.valueOf(getDid()));
        linkedHashMap2.put("paytype", getMBinding().getAlipay() ? "alipay_bank" : "wxpay_bank");
        linkedHashMap2.put("os", "APP");
        linkedHashMap2.put("num", String.valueOf(getMBinding().getNum()));
        if (getMBinding().getAlipay()) {
            alipay(linkedHashMap);
        } else {
            wxpay(linkedHashMap);
        }
    }

    public final void alipay(LinkedHashMap<String, String> param) {
        Intrinsics.checkNotNullParameter(param, "param");
        Repository.INSTANCE.alipay(4, param, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.alipay.1
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
                Intent intent = new Intent(YunBuyActivity.this, (Class<?>) WebPayActivity.class);
                intent.putExtra("url", payInfo.getPaydata());
                YunBuyActivity.this.startActivity(intent);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.alipay.2
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
                YunBuyActivity.this.netFail(exception);
            }
        });
    }

    public final void wxpay(LinkedHashMap<String, String> param) {
        Intrinsics.checkNotNullParameter(param, "param");
        param.put("driver", "mobile");
        Repository.INSTANCE.wxpay(4, param, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.wxpay.1
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
                    Intent intent = new Intent(YunBuyActivity.this, (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", payInfo.getPaydata().getMweb_url());
                    YunBuyActivity.this.startActivity(intent);
                } catch (Exception e) {
                    YunBuyActivity.this.log(e.toString());
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.YunBuyActivity.wxpay.2
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
                YunBuyActivity.this.netFail(exception);
            }
        });
    }
}
