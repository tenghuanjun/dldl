package com.cy.yyjia.zhe28.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityInvite3Binding;
import com.cy.yyjia.zhe28.databinding.ItemInviteRankBinding;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.cy.yyjia.zhe28.domain.InviteRankBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.ShareInfo;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.adapter.InviteRankAdapter;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.ShareTool;
import com.cy.yyjia.zhe28.util.Util;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: InviteActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0014J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0014J\u0016\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0006R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/InviteActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityInvite3Binding;", "Landroid/view/View$OnClickListener;", "()V", "account", "", "getAccount", "()Ljava/lang/String;", "setAccount", "(Ljava/lang/String;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/InviteRankBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemInviteRankBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "onResume", "share", "id", "", "showWithdrawDialog", "withdraw", "type", "num", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class InviteActivity extends BaseActivity<ActivityInvite3Binding> implements View.OnClickListener {
    public static final int $stable = 8;
    private String account;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    public InviteActivity() {
        super(R.layout.activity_invite3, 1);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<InviteRankBean, ItemInviteRankBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<InviteRankBean, ItemInviteRankBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_invite_rank, null, 2, null);
            }
        });
        this.account = "";
    }

    public static final /* synthetic */ ActivityInvite3Binding access$getMBinding(InviteActivity inviteActivity) {
        return inviteActivity.getMBinding();
    }

    public final BaseAdapter<InviteRankBean, ItemInviteRankBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final String getAccount() {
        return this.account;
    }

    public final void setAccount(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.account = str;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        Repository.INSTANCE.getInviteRank(1, new Function1<List<InviteRankBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<InviteRankBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<InviteRankBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                InviteActivity.access$getMBinding(InviteActivity.this).vf.setAdapter(new InviteRankAdapter(InviteActivity.this, it));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.init.2
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
                InviteActivity inviteActivity = InviteActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                inviteActivity.log(localizedMessage);
            }
        });
    }

    public final void getData() {
        Repository.INSTANCE.getInviteInfo(new Function1<InviteInfoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InviteInfoBean inviteInfoBean) {
                invoke2(inviteInfoBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InviteInfoBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                InviteActivity.access$getMBinding(InviteActivity.this).setData(it);
                if (InviteActivity.this.getIntent().getBooleanExtra("r", false)) {
                    InviteActivity.access$getMBinding(InviteActivity.this).tvRule.performClick();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.getData.2
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
                InviteActivity.this.netFail(it);
            }
        });
        Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.getData.3
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
                InviteActivity.this.setAccount(it.getAliAccount());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.getData.4
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (getMBinding().getData() != null) {
            switch (v.getId()) {
                case R.id.fl1 /* 2131362103 */:
                case R.id.fl2 /* 2131362104 */:
                    String string = v.getTag().toString();
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("type", string);
                    NetUtil netUtil = NetUtil.INSTANCE;
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new InviteActivity$onClick$$inlined$get$1("invite/game", linkedHashMap, null, this, this), 3, null);
                    break;
                case R.id.iv_share /* 2131362194 */:
                case R.id.iv_share2 /* 2131362195 */:
                    BaseDialog.OnClickListener onClickListener = new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity$$ExternalSyntheticLambda2
                        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                        public final void onClick(BaseDialog baseDialog, View view) {
                            InviteActivity.onClick$lambda$0(this.f$0, baseDialog, (TextView) view);
                        }
                    };
                    QuickDialog quickDialog = new QuickDialog(this, R.layout.dialog_invite);
                    InviteInfoBean data = getMBinding().getData();
                    Intrinsics.checkNotNull(data);
                    quickDialog.setData(data.getInvite_qrcode()).setAnimStyle(0).setOnClickListener(R.id.tv1, onClickListener).setOnClickListener(R.id.tv2, onClickListener).setOnClickListener(R.id.tv3, onClickListener).setOnClickListener(R.id.tv4, onClickListener).setOnClickListener(R.id.tv_close, onClickListener).show();
                    break;
                case R.id.tv_record /* 2131362757 */:
                    doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.onClick.2
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
                            new InviteWithdrewRecordActivity(InviteActivity.this).show();
                        }
                    });
                    break;
                case R.id.tv_rule /* 2131362764 */:
                    RuleDialog ruleDialog = new RuleDialog(getMContext());
                    InviteInfoBean data2 = getMBinding().getData();
                    Intrinsics.checkNotNull(data2);
                    ruleDialog.setText(data2.getReward_info().getActivityRules()).show();
                    break;
                case R.id.tv_withdraw /* 2131362807 */:
                    doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.onClick.1
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
                            InviteActivity.this.showWithdrawDialog();
                        }
                    });
                    break;
                default:
                    getMBinding().setPosition(Integer.parseInt(v.getTag().toString()));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$0(InviteActivity this$0, BaseDialog baseDialog, TextView textView) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (textView.getId() != R.id.tv_close) {
            this$0.share(textView.getId());
        }
        baseDialog.dismiss();
    }

    public final void share(int id) {
        ShareInfo shareInfo = new ShareInfo(null, null, null, null, null, 31, null);
        InviteInfoBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        shareInfo.setUrl(data.getInvite_url());
        InviteInfoBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        shareInfo.setImgUrl(data2.getShare_icon());
        InviteInfoBean data3 = getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        shareInfo.setTitle(data3.getShare_title());
        InviteInfoBean data4 = getMBinding().getData();
        Intrinsics.checkNotNull(data4);
        shareInfo.setDescribe(data4.getShare_desc());
        switch (id) {
            case R.id.tv1 /* 2131362645 */:
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new C09711(null), 3, null);
                break;
            case R.id.tv2 /* 2131362646 */:
                InviteInfoBean data5 = getMBinding().getData();
                Intrinsics.checkNotNull(data5);
                Util.copy(this, data5.getShare_ads() + StringUtils.LF + shareInfo.getUrl());
                break;
            case R.id.tv3 /* 2131362647 */:
                ShareTool.INSTANCE.shareToWechat(this, shareInfo, 0);
                break;
            case R.id.tv4 /* 2131362648 */:
                ShareTool.INSTANCE.shareToWechat(this, shareInfo, 1);
                break;
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.InviteActivity$share$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InviteActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.InviteActivity$share$1", f = "InviteActivity.kt", i = {}, l = {118, 126}, m = "invokeSuspend", n = {}, s = {})
    static final class C09711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C09711(Continuation<? super C09711> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InviteActivity.this.new C09711(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.InviteActivity$share$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: InviteActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.InviteActivity$share$1$1", f = "InviteActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            int label;
            final /* synthetic */ InviteActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01921(InviteActivity inviteActivity, Continuation<? super C01921> continuation) {
                super(2, continuation);
                this.this$0 = inviteActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01921(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
                return ((C01921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    RequestBuilder<Bitmap> requestBuilderAsBitmap = Glide.with(this.this$0.getMContext()).asBitmap();
                    InviteInfoBean data = InviteActivity.access$getMBinding(this.this$0).getData();
                    Intrinsics.checkNotNull(data);
                    Bitmap bitmap = requestBuilderAsBitmap.load(data.getInvite_qrcode()).submit().get();
                    return Util.savePicToDCIM(this.this$0.getMContext(), bitmap, "邀请图片" + System.currentTimeMillis(), 50);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C01921(InviteActivity.this, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(InviteActivity.this, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.InviteActivity$share$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: InviteActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.InviteActivity$share$1$2", f = "InviteActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ InviteActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(InviteActivity inviteActivity, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = inviteActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.toast("保存成功");
                return Unit.INSTANCE;
            }
        }
    }

    public final void showWithdrawDialog() {
        final BaseDialog.OnClickListener onClickListener = new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity$$ExternalSyntheticLambda0
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                InviteActivity.showWithdrawDialog$lambda$3(this.f$0, baseDialog, view);
            }
        };
        BaseDialog.OnClickListener onClickListener2 = new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity$$ExternalSyntheticLambda1
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                InviteActivity.showWithdrawDialog$lambda$4(this.f$0, onClickListener, baseDialog, view);
            }
        };
        QuickDialog quickDialog = new QuickDialog(this, R.layout.dialog_withdraw);
        InviteInfoBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        quickDialog.setData(data).setOnClickListener(R.id.iv_close, onClickListener2).setOnClickListener(R.id.ll_flb, onClickListener2).setOnClickListener(R.id.ll_ptb, onClickListener2).setOnClickListener(R.id.ll_zfb, onClickListener2).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWithdrawDialog$lambda$3(InviteActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        switch (view.getId()) {
            case R.id.btn /* 2131361939 */:
                View viewFindViewById = baseDialog.findViewById(R.id.et);
                Intrinsics.checkNotNull(viewFindViewById);
                String string = ((EditText) viewFindViewById).getText().toString();
                int i = Integer.parseInt(view.getTag().toString());
                if (string.length() <= 0) {
                    this$0.toast("请先输入提现金额");
                } else {
                    this$0.withdraw(i, string);
                }
                break;
            case R.id.iv_close /* 2131362167 */:
                baseDialog.dismiss();
                break;
            case R.id.tv_all /* 2131362657 */:
                View viewFindViewById2 = baseDialog.findViewById(R.id.et);
                Intrinsics.checkNotNull(viewFindViewById2);
                InviteInfoBean data = this$0.getMBinding().getData();
                Intrinsics.checkNotNull(data);
                ((EditText) viewFindViewById2).setText(data.getInvite_info().getReward_money());
                break;
            case R.id.tv_bind /* 2131362662 */:
                this$0.startActivity(AlipayBindActivity.class);
                baseDialog.dismiss();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWithdrawDialog$lambda$4(InviteActivity this$0, BaseDialog.OnClickListener childListener, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(childListener, "$childListener");
        QuickDialog quickDialog = new QuickDialog(this$0, R.layout.dialog_withdraw2);
        InviteInfoBean data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        QuickDialog onClickListener = quickDialog.setData(data).setOnClickListener(R.id.iv_close, childListener).setOnClickListener(R.id.tv_all, childListener).setOnClickListener(R.id.tv_bind, childListener).setOnClickListener(R.id.btn, childListener);
        int id = view.getId();
        if (id == R.id.ll_flb) {
            InviteInfoBean data2 = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            InviteInfoBean.Platform welfare = data2.getPayList().getWelfare();
            Intrinsics.checkNotNull(welfare);
            onClickListener.setVariable(118, Integer.valueOf(welfare.getType())).show();
        } else if (id == R.id.ll_ptb) {
            InviteInfoBean data3 = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data3);
            InviteInfoBean.Platform platform = data3.getPayList().getPlatform();
            Intrinsics.checkNotNull(platform);
            onClickListener.setVariable(118, Integer.valueOf(platform.getType())).show();
        } else if (id == R.id.ll_zfb) {
            QuickDialog variable = onClickListener.setVariable(7, this$0.account);
            InviteInfoBean data4 = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data4);
            InviteInfoBean.Platform alipay = data4.getPayList().getAlipay();
            Intrinsics.checkNotNull(alipay);
            variable.setVariable(118, Integer.valueOf(alipay.getType())).show();
        }
        baseDialog.dismiss();
    }

    public final void withdraw(int type, String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        Repository.INSTANCE.inviteWithdrew(num, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.withdraw.1
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
                InviteActivity.this.toast(it.getMsg());
                InviteActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteActivity.withdraw.2
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
                InviteActivity.this.netFail(it);
            }
        }, type);
    }
}
