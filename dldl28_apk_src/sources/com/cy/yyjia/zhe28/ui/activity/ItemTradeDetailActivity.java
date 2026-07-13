package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityItemTradeDetailBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.volcengine.common.contant.CommonConstants;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: ItemTradeDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J,\u0010\u0019\u001a\u00020\u001a2\"\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001cj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u001dH\u0002J\u0006\u0010\u001e\u001a\u00020\u001aJ\u0006\u0010\u001f\u001a\u00020\u001aJ\u0006\u0010 \u001a\u00020\u001aJ\b\u0010!\u001a\u00020\u001aH\u0016J\b\u0010\"\u001a\u00020\u001aH\u0014J\u0006\u0010#\u001a\u00020\u001aJ,\u0010$\u001a\u00020\u001a2\"\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001cj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u001dH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ItemTradeDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityItemTradeDetailBinding;", "()V", CommonConstants.key_accountId, "", "getAccountId", "()Ljava/lang/String;", "setAccountId", "(Ljava/lang/String;)V", "id", "", "getId", "()I", "id$delegate", "Lkotlin/Lazy;", "serviceId", "getServiceId", "setServiceId", "toPay", "", "getToPay", "()Z", "setToPay", "(Z)V", "alipay", "", "param", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "calculate", "getData", "getRole", "init", "onResume", "pay", "wxpay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ItemTradeDetailActivity extends BaseActivity<ActivityItemTradeDetailBinding> {
    public static final int $stable = 8;
    private String accountId;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;
    private String serviceId;
    private boolean toPay;

    public ItemTradeDetailActivity() {
        super(R.layout.activity_item_trade_detail, 2);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
        this.accountId = "";
        this.serviceId = "";
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final void setAccountId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.accountId = str;
    }

    public final String getServiceId() {
        return this.serviceId;
    }

    public final void setServiceId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serviceId = str;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        EditText et = getMBinding().et;
        Intrinsics.checkNotNullExpressionValue(et, "et");
        et.addTextChangedListener(new TextWatcher() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$init$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                this.this$0.calculate();
            }
        });
        getMBinding().tvRole.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeDetailActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeDetailActivity.init$lambda$2(this.f$0, view);
            }
        });
        getMBinding().ivUp.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeDetailActivity.init$lambda$3(this.f$0, view);
            }
        });
        getMBinding().ivDown.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeDetailActivity.init$lambda$4(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(ItemTradeDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getRole();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(ItemTradeDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(ItemTradeDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getMBinding().et.getText().toString();
        if (TextUtils.isEmpty(string)) {
            this$0.getMBinding().et.setText("1");
        } else {
            this$0.getMBinding().et.setText(String.valueOf(Integer.parseInt(string) + 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(ItemTradeDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getMBinding().et.getText().toString();
        if (TextUtils.isEmpty(string)) {
            this$0.getMBinding().et.setText("1");
        } else {
            int i = Integer.parseInt(string) - 1;
            this$0.getMBinding().et.setText(i > 1 ? String.valueOf(i) : "1");
        }
    }

    public final void calculate() {
        String string = getMBinding().et.getText().toString();
        if (TextUtils.isEmpty(string)) {
            getMBinding().tvPrice.setText("0");
            return;
        }
        int i = Integer.parseInt(string);
        log("计算价格一次");
        ItemTradeBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        BigDecimal bigDecimal = new BigDecimal(data.getPrice());
        ItemTradeBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        if (i > data2.getNum()) {
            EditText editText = getMBinding().et;
            ItemTradeBean data3 = getMBinding().getData();
            Intrinsics.checkNotNull(data3);
            editText.setText(String.valueOf(data3.getNum()));
            return;
        }
        getMBinding().tvPrice.setText(bigDecimal.multiply(new BigDecimal(i)).toString());
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("id", String.valueOf(getId()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeDetailActivity$getData$$inlined$get$1("game/asset/detail", linkedHashMap, null, this, this), 3, null);
    }

    public final void getRole() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ItemTradeBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        GameBean game = data.getGame();
        Intrinsics.checkNotNull(game);
        linkedHashMap.put(CommonConstants.key_gameId, String.valueOf(game.getId()));
        ItemTradeBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        linkedHashMap.put("serviceCode", data2.getServiceCode());
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeDetailActivity$getRole$$inlined$get$1("game/asset/roles", linkedHashMap, null, this, this), 3, null);
    }

    public final void pay() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeDetailActivity$pay$$inlined$get$1("game/asset/payTypeList", new LinkedHashMap(), null, this, this), 3, null);
    }

    public final boolean getToPay() {
        return this.toPay;
    }

    public final void setToPay(boolean z) {
        this.toPay = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void alipay(LinkedHashMap<String, String> param) {
        Repository.INSTANCE.alipay(5, param, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity.alipay.1
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
                    ItemTradeDetailActivity.this.toast(payInfo.getMsg());
                    return;
                }
                Intent intent = new Intent(ItemTradeDetailActivity.this, (Class<?>) WebPayActivity.class);
                intent.putExtra("url", payInfo.getPaydata());
                ItemTradeDetailActivity.this.startActivity(intent);
                ItemTradeDetailActivity.this.setToPay(true);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity.alipay.2
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
                ItemTradeDetailActivity.this.netFail(exception);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void wxpay(LinkedHashMap<String, String> param) {
        Repository.INSTANCE.wxpay(5, param, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity.wxpay.1
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
                    ItemTradeDetailActivity.this.toast(payInfo.getMsg());
                    return;
                }
                try {
                    Intent intent = new Intent(ItemTradeDetailActivity.this, (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", payInfo.getPaydata().getMweb_url());
                    ItemTradeDetailActivity.this.startActivity(intent);
                    ItemTradeDetailActivity.this.setToPay(true);
                } catch (Exception e) {
                    ItemTradeDetailActivity.this.log(e.toString());
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity.wxpay.2
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
                ItemTradeDetailActivity.this.netFail(exception);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.toPay) {
            finish();
            startActivity(ItemTradeRecordActivity.class);
        }
    }
}
