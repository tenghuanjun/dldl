package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityLotteryBinding;
import com.cy.yyjia.zhe28.domain.GameToolBean;
import com.cy.yyjia.zhe28.domain.LotteryGiftBean;
import com.cy.yyjia.zhe28.domain.LotteryIdResult;
import com.cy.yyjia.zhe28.domain.LotteryInfoBean;
import com.cy.yyjia.zhe28.domain.LotteryResultBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.adapter.LotteryMessageAdapter;
import com.cy.yyjia.zhe28.ui.dialog.LotteryResultDialog;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LotteryActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\u0006\u0010\u001a\u001a\u00020\u0012J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0012H\u0014J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/LotteryActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityLotteryBinding;", "Landroid/view/View$OnClickListener;", "()V", "id", "Lcom/cy/yyjia/zhe28/domain/LotteryIdResult;", "getId", "()Lcom/cy/yyjia/zhe28/domain/LotteryIdResult;", "setId", "(Lcom/cy/yyjia/zhe28/domain/LotteryIdResult;)V", "typeId", "", "getTypeId", "()I", "setTypeId", "(I)V", "getBoxType", "", "getGift", "getInfo", "getMessage", "go", "type", "", "init", "initBottom", "onClick", "v", "Landroid/view/View;", "onResume", "setType", "i", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LotteryActivity extends BaseActivity<ActivityLotteryBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private LotteryIdResult id;
    private int typeId;

    public LotteryActivity() {
        super(R.layout.activity_lottery, 2);
    }

    public static final /* synthetic */ ActivityLotteryBinding access$getMBinding(LotteryActivity lotteryActivity) {
        return lotteryActivity.getMBinding();
    }

    public final LotteryIdResult getId() {
        return this.id;
    }

    public final void setId(LotteryIdResult lotteryIdResult) {
        this.id = lotteryIdResult;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        setLoginSuccessListener(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.init.1
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
                LotteryActivity.this.getBoxType();
            }
        });
        getMBinding().setType(1);
        initBottom();
        getBoxType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initBottom() {
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_lottery_fun, null, 2, 0 == true ? 1 : 0);
        getMBinding().rvBottom.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                LotteryActivity.initBottom$lambda$1(baseAdapter, this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void initBottom$lambda$1(BaseAdapter adapter1, LotteryActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter1, "$adapter1");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        String title = ((GameToolBean) adapter1.getItem(i)).getTitle();
        String str = title;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "积分", false, 2, (Object) null)) {
            this$0.startActivity(LotteryActivity.class);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "签到", false, 2, (Object) null)) {
            this$0.startActivity(QiandaoActivity.class);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "任务", false, 2, (Object) null)) {
            this$0.startActivity(new Intent(this$0.getMContext(), (Class<?>) QiandaoActivity.class).putExtra("task", true));
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "邀请", false, 2, (Object) null)) {
            this$0.startActivity(InviteActivity.class);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "三宝", false, 2, (Object) null)) {
            this$0.startActivity(SanbaoActivity.class);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "回收", false, 2, (Object) null)) {
            this$0.startActivity(RecycleActivity.class);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "新人福利", false, 2, (Object) null)) {
            this$0.startActivity(NoviceWelfareActivity.class);
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "试玩", false, 2, (Object) null)) {
            Util.openWebWithLogin(this$0.getMContext(), title, NetUtil.BASE_URL3 + "dist/award-trial-play");
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "客服", false, 2, (Object) null)) {
            Util.openWebWithLogin(this$0.getMContext(), title, NetUtil.BASE_URL3 + "dist/customer-service");
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "转游", false, 2, (Object) null)) {
            Util.openWebWithLogin(this$0.getMContext(), title, NetUtil.BASE_URL3 + "dist/transfer-game");
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "评论", false, 2, (Object) null)) {
            Util.openWebWithLogin(this$0.getMContext(), title, NetUtil.BASE_URL3 + "dist/game-notice-detail?id=99518");
            return;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "美女", false, 2, (Object) null)) {
            Util.openWebWithLogin(this$0.getMContext(), title, NetUtil.BASE_URL3 + "dist/video");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.id != null) {
            setType(getMBinding().getType());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn1 /* 2131361940 */:
                if (Constant.INSTANCE.getLogged()) {
                    go("one");
                } else {
                    toLogin();
                }
                break;
            case R.id.btn10 /* 2131361941 */:
                if (Constant.INSTANCE.getLogged()) {
                    go("ten");
                } else {
                    toLogin();
                }
                break;
            case R.id.tv1 /* 2131362645 */:
                setType(1);
                break;
            case R.id.tv2 /* 2131362646 */:
                setType(2);
                break;
            case R.id.tv_prize /* 2131362752 */:
                Repository repository = Repository.INSTANCE;
                LotteryInfoBean data = getMBinding().getData();
                Intrinsics.checkNotNull(data);
                repository.getLotteryWelfare(data.getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity$onClick$1$1
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
                        this.this$0.toast(it.getMsg());
                        LotteryActivity lotteryActivity = this.this$0;
                        lotteryActivity.setType(LotteryActivity.access$getMBinding(lotteryActivity).getType());
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity$onClick$1$2
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
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity$onClick$1$3
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
                        this.this$0.startActivity(LotteryRecordActivity.class);
                    }
                });
                break;
            case R.id.tv_rule /* 2131362764 */:
                RuleDialog ruleDialog = new RuleDialog(getMContext());
                LotteryInfoBean data2 = getMBinding().getData();
                Intrinsics.checkNotNull(data2);
                ruleDialog.setText(data2.getRule()).show();
                break;
            case R.id.tv_shop /* 2131362772 */:
                toast("敬请期待");
                break;
        }
    }

    public final void getBoxType() {
        Repository.INSTANCE.getLotteryTypeId(new Function1<LotteryIdResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getBoxType.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LotteryIdResult lotteryIdResult) {
                invoke2(lotteryIdResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LotteryIdResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LotteryActivity.this.setId(it);
                LotteryActivity.this.setType(1);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getBoxType.2
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
                LotteryActivity.this.netFail(it);
            }
        });
    }

    public final void go(String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Repository.INSTANCE.goLottery(this.typeId, type, new Function1<LotteryResultBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.go.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LotteryResultBean lotteryResultBean) {
                invoke2(lotteryResultBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LotteryResultBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                new LotteryResultDialog(LotteryActivity.this.getMContext()).setData(it.getList()).show();
                LotteryActivity lotteryActivity = LotteryActivity.this;
                lotteryActivity.setType(LotteryActivity.access$getMBinding(lotteryActivity).getType());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.go.2
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
                LotteryActivity.this.netFail(it);
            }
        });
    }

    public final void setType(int i) {
        int common;
        if (i == 1) {
            LotteryIdResult lotteryIdResult = this.id;
            Intrinsics.checkNotNull(lotteryIdResult);
            common = lotteryIdResult.getZhe();
        } else {
            LotteryIdResult lotteryIdResult2 = this.id;
            Intrinsics.checkNotNull(lotteryIdResult2);
            common = lotteryIdResult2.getCommon();
        }
        this.typeId = common;
        getMBinding().setType(i);
        getMessage();
        getGift();
        getInfo();
    }

    public final void getInfo() {
        Repository.INSTANCE.getLotteryInfo(this.typeId, new Function1<LotteryInfoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getInfo.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LotteryInfoBean lotteryInfoBean) {
                invoke2(lotteryInfoBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LotteryInfoBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LotteryActivity.access$getMBinding(LotteryActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getInfo.2
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
                LotteryActivity.this.netFail(it);
            }
        });
    }

    public final void getMessage() {
        Repository.INSTANCE.getLotteryMessage(this.typeId, new Function1<List<String>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getMessage.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<String> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LotteryActivity.access$getMBinding(LotteryActivity.this).vf.setAdapter(new LotteryMessageAdapter(LotteryActivity.this, it));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getMessage.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }
        });
    }

    public final void getGift() {
        Repository.INSTANCE.getLotteryGift(this.typeId, new Function1<List<LotteryGiftBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getGift.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<LotteryGiftBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<LotteryGiftBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LotteryActivity.access$getMBinding(LotteryActivity.this).rv.setAdapter(new BaseAdapter(R.layout.item_lottery_gift, it));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LotteryActivity.getGift.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }
        });
    }
}
