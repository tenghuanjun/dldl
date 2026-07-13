package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMOrderBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.domain.PayInfo;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.WxPayInfo;
import com.cy.yyjia.zhe28.ui.dialog.PayDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMTransPayActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000bH\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GMTransPayActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGmTransPayBinding;", "()V", "toPay", "", "getToPay", "()Z", "setToPay", "(Z)V", "init", "", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMTransPayActivity extends BaseActivity<ActivityGmTransPayBinding> {
    public static final int $stable = 8;
    private boolean toPay;

    public GMTransPayActivity() {
        super(R.layout.activity_gm_trans_pay, 0, 2, null);
    }

    public static final /* synthetic */ ActivityGmTransPayBinding access$getMBinding(GMTransPayActivity gMTransPayActivity) {
        return gMTransPayActivity.getMBinding();
    }

    public final boolean getToPay() {
        return this.toPay;
    }

    public final void setToPay(boolean z) {
        this.toPay = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setUsername(Constant.INSTANCE.getUsername());
        ActivityGmTransPayBinding mBinding = getMBinding();
        Serializable serializableExtra = getIntent().getSerializableExtra("oldGame");
        Intrinsics.checkNotNull(serializableExtra);
        mBinding.setOldGame((GMGameBean) serializableExtra);
        ActivityGmTransPayBinding mBinding2 = getMBinding();
        Serializable serializableExtra2 = getIntent().getSerializableExtra("game");
        Intrinsics.checkNotNull(serializableExtra2);
        mBinding2.setGame((GMGameBean) serializableExtra2);
        ActivityGmTransPayBinding mBinding3 = getMBinding();
        Serializable serializableExtra3 = getIntent().getSerializableExtra("oldRole");
        Intrinsics.checkNotNull(serializableExtra3);
        mBinding3.setOldRole((GMRoleBean) serializableExtra3);
        ActivityGmTransPayBinding mBinding4 = getMBinding();
        Serializable serializableExtra4 = getIntent().getSerializableExtra("role");
        Intrinsics.checkNotNull(serializableExtra4);
        mBinding4.setRole((GMRoleBean) serializableExtra4);
        ActivityGmTransPayBinding mBinding5 = getMBinding();
        Serializable serializableExtra5 = getIntent().getSerializableExtra("title");
        Intrinsics.checkNotNull(serializableExtra5);
        mBinding5.setTitle((GMTitleBean) serializableExtra5);
        ActivityGmTransPayBinding mBinding6 = getMBinding();
        Serializable serializableExtra6 = getIntent().getSerializableExtra("oldTitle");
        Intrinsics.checkNotNull(serializableExtra6);
        mBinding6.setOldTitle((GMTitleBean) serializableExtra6);
    }

    public final void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Repository repository = Repository.INSTANCE;
        GMGameBean oldGame = getMBinding().getOldGame();
        Intrinsics.checkNotNull(oldGame);
        int id = oldGame.getId();
        GMGameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        int id2 = game.getId();
        GMTitleBean title = getMBinding().getTitle();
        Intrinsics.checkNotNull(title);
        String id3 = title.getId();
        GMRoleBean oldRole = getMBinding().getOldRole();
        Intrinsics.checkNotNull(oldRole);
        GMRoleBean role = getMBinding().getRole();
        Intrinsics.checkNotNull(role);
        repository.createGMTransOrder(id, id2, id3, oldRole, role, new Function1<GMOrderBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GMOrderBean gMOrderBean) {
                invoke2(gMOrderBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final GMOrderBean order) {
                Intrinsics.checkNotNullParameter(order, "order");
                PayDialog price = new PayDialog(GMTransPayActivity.this).setPrice(GMTransPayActivity.access$getMBinding(GMTransPayActivity.this).tvPrice.getText().toString());
                final GMTransPayActivity gMTransPayActivity = GMTransPayActivity.this;
                price.setPayWithPtbListener(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        Repository repository2 = Repository.INSTANCE;
                        GMOrderBean gMOrderBean = order;
                        final GMTransPayActivity gMTransPayActivity2 = gMTransPayActivity;
                        Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.1.1.1
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
                                gMTransPayActivity2.toast(it.getMsg());
                                gMTransPayActivity2.setResult(8987);
                                gMTransPayActivity2.finish();
                            }
                        };
                        final GMTransPayActivity gMTransPayActivity3 = gMTransPayActivity;
                        Function1<PayInfo, Unit> function12 = new Function1<PayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.1.1.2
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
                                Intent intent = new Intent(gMTransPayActivity3, (Class<?>) WebPayActivity.class);
                                intent.putExtra("url", it.getPaydata());
                                gMTransPayActivity3.startActivity(intent);
                            }
                        };
                        final GMTransPayActivity gMTransPayActivity4 = gMTransPayActivity;
                        Function1<WxPayInfo, Unit> function13 = new Function1<WxPayInfo, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.1.1.3
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
                                    Intent intent = new Intent(gMTransPayActivity4, (Class<?>) WebPayActivity.class);
                                    intent.putExtra("url", it.getPaydata().getMweb_url());
                                    gMTransPayActivity4.startActivity(intent);
                                    gMTransPayActivity4.setToPay(true);
                                } catch (Exception e) {
                                    gMTransPayActivity4.log(e.toString());
                                }
                            }
                        };
                        final GMTransPayActivity gMTransPayActivity5 = gMTransPayActivity;
                        repository2.payGMOrder(i, gMOrderBean, function1, function12, function13, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.1.1.4
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
                                gMTransPayActivity5.netFail(it);
                            }
                        });
                    }
                }).show();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransPayActivity.onClick.2
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
                GMTransPayActivity.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.toPay) {
            setResult(8987);
            finish();
        }
    }
}
