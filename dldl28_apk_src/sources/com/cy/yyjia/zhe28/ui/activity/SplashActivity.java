package com.cy.yyjia.zhe28.ui.activity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.activity.MainActivity;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.ActivitySplashBinding;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.cy.yyjia.zhe28.ui.dialog.PrivacyDialog;
import com.cy.yyjia.zhe28.ui.dialog.UpdateDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.IsPhoneTool;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.hjq.shape.view.ShapeTextView;
import com.volcengine.cloudgame.VeGameEngine;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: SplashActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0013J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0006\u0010\u001e\u001a\u00020\u0013J\u0006\u0010\u001f\u001a\u00020\u0013R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SplashActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivitySplashBinding;", "Landroid/view/View$OnClickListener;", "()V", "actionId", "", "getActionId", "()I", "actionId$delegate", "Lkotlin/Lazy;", "gid", "getGid", "gid$delegate", "oaidCode", "getOaidCode", "setOaidCode", "(I)V", DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "", "time", "", "go", "init", "initOaid", "cert", "", "onClick", "v", "Landroid/view/View;", "picGo", "update", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SplashActivity extends BaseActivity<ActivitySplashBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: actionId$delegate, reason: from kotlin metadata */
    private final Lazy actionId;

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final Lazy gid;
    private int oaidCode;

    public SplashActivity() {
        super(R.layout.activity_splash, 2);
        this.actionId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity$actionId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("action", 0));
            }
        });
        this.gid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity$gid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
    }

    public static final /* synthetic */ ActivitySplashBinding access$getMBinding(SplashActivity splashActivity) {
        return splashActivity.getMBinding();
    }

    private final int getActionId() {
        return ((Number) this.actionId.getValue()).intValue();
    }

    public final int getGid() {
        return ((Number) this.gid.getValue()).intValue();
    }

    public final int getOaidCode() {
        return this.oaidCode;
    }

    public final void setOaidCode(int i) {
        this.oaidCode = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        ShapeTextView tvSkip = getMBinding().tvSkip;
        Intrinsics.checkNotNullExpressionValue(tvSkip, "tvSkip");
        BaseActivity.setViewFitsSystemWindows$default(this, tvSkip, false, 2, null);
        getMBinding().tvSkip.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SplashActivity.init$lambda$0(this.f$0, view);
            }
        });
        final SharedPreferences sharedPreferences = getSharedPreferences("first", 0);
        if (!sharedPreferences.getBoolean("read", false)) {
            ((PrivacyDialog) ((PrivacyDialog) new PrivacyDialog(this).setCancelable(false)).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity$$ExternalSyntheticLambda1
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    SplashActivity.init$lambda$1(sharedPreferences, this, baseDialog, view);
                }
            })).show();
        } else {
            update();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(SplashActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.go();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(SharedPreferences sharedPreferences, SplashActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("read", true);
        editorEdit.commit();
        baseDialog.dismiss();
        this$0.update();
    }

    public final void update() {
        VeGameEngine.getInstance().init(getApplication());
        Constant.INSTANCE.getDeviceImei(this);
        Repository.INSTANCE.update(new Function1<UpdateBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity.update.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UpdateBean updateBean) {
                invoke2(updateBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UpdateBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Constant.INSTANCE.setAppTheme(it.getAppTheme());
                Constant.INSTANCE.setHideTrade(it.getHideTrade() == 1);
                if (Intrinsics.areEqual(it.getIsUpdate(), BooleanUtils.YES) && it.getVersionCode() > 429) {
                    new UpdateDialog(SplashActivity.this, it).show();
                    return;
                }
                if (TextUtils.isEmpty(Constant.INSTANCE.getOaid())) {
                    SplashActivity.this.initOaid(it.getOaidsdkcert());
                }
                SplashActivity.access$getMBinding(SplashActivity.this).setInfo(it.getBaseInfo());
                UpdateBean.BaseInfoBean info = SplashActivity.access$getMBinding(SplashActivity.this).getInfo();
                Intrinsics.checkNotNull(info);
                if (!TextUtils.isEmpty(info.getPic())) {
                    SplashActivity.this.auto(3000L);
                } else {
                    SplashActivity.this.auto(1000L);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity.update.2
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
                SplashActivity.this.netFail(it);
                SplashActivity.this.auto(1000L);
            }
        });
    }

    public final void auto(long time) {
        if (time == 3000) {
            getMBinding().tvSkip.setVisibility(0);
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(time, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.SplashActivity$auto$1, reason: invalid class name */
    /* JADX INFO: compiled from: SplashActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.SplashActivity$auto$1", f = "SplashActivity.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $time;
        int label;
        final /* synthetic */ SplashActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, SplashActivity splashActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$time = j;
            this.this$0 = splashActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$time, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(this.$time, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0.go();
            return Unit.INSTANCE;
        }
    }

    public final void go() {
        getMBinding().tvSkip.setEnabled(false);
        startActivity(MainActivity.class);
        if (getActionId() == 1) {
            Util.gotoGame(this, getGid());
        }
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.iv) {
            picGo();
        } else {
            if (id != R.id.tv_skip) {
                return;
            }
            go();
        }
    }

    public final void picGo() {
        UpdateBean.BaseInfoBean info = getMBinding().getInfo();
        if (info != null) {
            startActivity(MainActivity.class);
            try {
                BtnBean dumpUrl = info.getDumpUrl();
                ImageView iv = getMBinding().iv;
                Intrinsics.checkNotNullExpressionValue(iv, "iv");
                dumpUrl.onClick(iv);
            } catch (Exception unused) {
            }
            finish();
        }
    }

    public final void initOaid(String cert) {
        Intrinsics.checkNotNullParameter(cert, "cert");
        if (IsPhoneTool.isEmulator()) {
            Constant.INSTANCE.setOaid(Constant.INSTANCE.getMyId());
            Constant.INSTANCE.setImei(Constant.INSTANCE.getMyId());
            Repository.INSTANCE.reportInit();
        } else {
            System.loadLibrary("msaoaidsec");
            SplashActivity splashActivity = this;
            MdidSdkHelper.InitCert(splashActivity, cert);
            this.oaidCode = MdidSdkHelper.InitSdk(splashActivity, true, new IIdentifierListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SplashActivity$$ExternalSyntheticLambda2
                @Override // com.bun.miitmdid.interfaces.IIdentifierListener
                public final void onSupport(IdSupplier idSupplier) {
                    SplashActivity.initOaid$lambda$3(this.f$0, idSupplier);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOaid$lambda$3(SplashActivity this$0, IdSupplier idSupplier) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (TextUtils.isEmpty(idSupplier.getOAID())) {
            Constant.INSTANCE.setOaid(Constant.INSTANCE.getMyId());
            Constant.INSTANCE.setImei(Constant.INSTANCE.getMyId());
        } else {
            Constant constant = Constant.INSTANCE;
            String oaid = idSupplier.getOAID();
            Intrinsics.checkNotNullExpressionValue(oaid, "getOAID(...)");
            constant.setOaid(oaid);
            Constant constant2 = Constant.INSTANCE;
            String oaid2 = idSupplier.getOAID();
            Intrinsics.checkNotNullExpressionValue(oaid2, "getOAID(...)");
            constant2.setImei(oaid2);
        }
        this$0.log("oaid: " + idSupplier.getOAID());
        Repository.INSTANCE.reportInit();
    }
}
