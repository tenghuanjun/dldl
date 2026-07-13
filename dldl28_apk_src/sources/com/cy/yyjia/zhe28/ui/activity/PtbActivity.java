package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityPtbBinding;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.PtbHomeBean;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.tencent.connect.common.Constants;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PtbActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\b\u0010\u000e\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\fH\u0014J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/PtbActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityPtbBinding;", "Landroid/view/View$OnClickListener;", "()V", "ptbInfo", "Lcom/cy/yyjia/zhe28/domain/PtbHomeBean;", "getPtbInfo", "()Lcom/cy/yyjia/zhe28/domain/PtbHomeBean;", "setPtbInfo", "(Lcom/cy/yyjia/zhe28/domain/PtbHomeBean;)V", "buy", "", "getUser", "init", "onClick", "v", "Landroid/view/View;", "onResume", "select", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PtbActivity extends BaseActivity<ActivityPtbBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    public PtbHomeBean ptbInfo;

    public PtbActivity() {
        super(R.layout.activity_ptb, 2);
    }

    public static final /* synthetic */ ActivityPtbBinding access$getMBinding(PtbActivity ptbActivity) {
        return ptbActivity.getMBinding();
    }

    public final PtbHomeBean getPtbInfo() {
        PtbHomeBean ptbHomeBean = this.ptbInfo;
        if (ptbHomeBean != null) {
            return ptbHomeBean;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ptbInfo");
        return null;
    }

    public final void setPtbInfo(PtbHomeBean ptbHomeBean) {
        Intrinsics.checkNotNullParameter(ptbHomeBean, "<set-?>");
        this.ptbInfo = ptbHomeBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(PtbActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(PtbRecordActivity.class);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PtbActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().setPosition(1);
        getMBinding().setAlipay(true);
        getMBinding().et.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                PtbActivity.init$lambda$1(this.f$0, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(PtbActivity this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.getMBinding().setPosition(6);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.tv_alipay) {
            getMBinding().setAlipay(false);
            return;
        }
        if (id == R.id.tv_go) {
            buy();
            return;
        }
        if (id != R.id.tv_wx) {
            switch (id) {
                case R.id.ll1 /* 2131362235 */:
                    select(1);
                    break;
                case R.id.ll2 /* 2131362236 */:
                    select(2);
                    break;
                case R.id.ll3 /* 2131362237 */:
                    select(3);
                    break;
                case R.id.ll4 /* 2131362238 */:
                    select(4);
                    break;
                case R.id.ll5 /* 2131362239 */:
                    select(5);
                    break;
            }
            return;
        }
        getMBinding().setAlipay(true);
    }

    public final void select(int position) {
        getMBinding().setPosition(position);
        getMBinding().et.clearFocus();
        getMBinding().et.setText("");
    }

    public final void getUser() {
        getMBinding().tvUsername.setText(Constant.INSTANCE.getUsername());
        Repository.INSTANCE.getPtbInfo(new Function1<PtbHomeBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity.getUser.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PtbHomeBean ptbHomeBean) {
                invoke2(ptbHomeBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PtbHomeBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PtbActivity.access$getMBinding(PtbActivity.this).tvPtb.setText(it.getMoney());
                PtbActivity.this.setPtbInfo(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity.getUser.2
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
                PtbActivity.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getUser();
    }

    public final void buy() {
        String string;
        int position = getMBinding().getPosition();
        if (position == 1) {
            string = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        } else if (position == 2) {
            string = "30";
        } else if (position == 3) {
            string = "50";
        } else if (position == 4) {
            string = "100";
        } else if (position == 5) {
            string = "200";
        } else {
            string = getMBinding().et.getText().toString();
        }
        if (getMBinding().getAlipay()) {
            getPtbInfo().getPayment().getAlipay_bank().getPayType();
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
            linkedHashMap2.put("money", string);
            linkedHashMap2.put("paytype", getPtbInfo().getPayment().getAlipay_bank().getPayType());
            linkedHashMap2.put("driver", "APP");
            linkedHashMap2.put("os", "APP");
            Repository.INSTANCE.alipay(0, linkedHashMap, new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity.buy.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PayInfo payInfo) {
                    invoke2(payInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PayInfo it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Intent intent = new Intent(PtbActivity.this, (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", it.getPaydata());
                    PtbActivity.this.startActivity(intent);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity.buy.2
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
                    PtbActivity.this.netFail(it);
                }
            });
            return;
        }
        LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>();
        LinkedHashMap<String, String> linkedHashMap4 = linkedHashMap3;
        linkedHashMap4.put("money", string);
        linkedHashMap4.put("paytype", getPtbInfo().getPayment().getWxpay_bank().getPayType());
        linkedHashMap4.put("driver", "mobile");
        linkedHashMap4.put("os", "APP");
        Repository.INSTANCE.wxpay(0, linkedHashMap3, new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity.buy.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WxPayInfo wxPayInfo) {
                invoke2(wxPayInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WxPayInfo it) {
                Intrinsics.checkNotNullParameter(it, "it");
                try {
                    Intent intent = new Intent(PtbActivity.this, (Class<?>) WebPayActivity.class);
                    intent.putExtra("url", it.getPaydata().getMweb_url());
                    PtbActivity.this.startActivity(intent);
                } catch (Exception e) {
                    PtbActivity.this.log(e.toString());
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PtbActivity.buy.4
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
                PtbActivity.this.netFail(it);
            }
        });
    }
}
