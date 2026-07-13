package com.cy.yyjia.zhe28.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.autofill.HintConstants;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityQuickLoginBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.SPUtil;
import com.lzy.okgo.cookie.SerializableCookie;
import com.mobile.auth.gatewayauth.Constant;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QuickLoginActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\fJ\u0006\u0010 \u001a\u00020\u0014R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/QuickLoginActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityQuickLoginBinding;", "Landroid/view/View$OnClickListener;", "()V", "loginType", "", "getLoginType", "()I", "loginType$delegate", "Lkotlin/Lazy;", "pkgName", "", "getPkgName", "()Ljava/lang/String;", "pkgName$delegate", "getIcon", "Landroid/graphics/drawable/Drawable;", "pakgename", "init", "", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onClick", "v", "Landroid/view/View;", "toSDKLogin", "token", "toSDKLoginOld", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class QuickLoginActivity extends BaseActivity<ActivityQuickLoginBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: loginType$delegate, reason: from kotlin metadata */
    private final Lazy loginType;

    /* JADX INFO: renamed from: pkgName$delegate, reason: from kotlin metadata */
    private final Lazy pkgName;

    public QuickLoginActivity() {
        super(R.layout.activity_quick_login, 0, 2, null);
        this.loginType = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.QuickLoginActivity$loginType$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("loginType", 0));
            }
        });
        this.pkgName = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.QuickLoginActivity$pkgName$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra = this.this$0.getIntent().getStringExtra("packageName");
                Intrinsics.checkNotNull(stringExtra);
                return stringExtra;
            }
        });
    }

    public final int getLoginType() {
        return ((Number) this.loginType.getValue()).intValue();
    }

    public final String getPkgName() {
        return (String) this.pkgName.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        ImageView imageView = getMBinding().ivGame;
        String pkgName = getPkgName();
        Intrinsics.checkNotNullExpressionValue(pkgName, "<get-pkgName>(...)");
        imageView.setImageDrawable(getIcon(pkgName));
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.QuickLoginActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QuickLoginActivity.init$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(QuickLoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toast("取消登录");
        this$0.setResult(7892);
        this$0.finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id != R.id.tv_login) {
            if (id != R.id.tv_logout) {
                return;
            }
            startActivityForResult(new Intent(this, (Class<?>) LoginActivity.class), 1001);
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            String pkgName = getPkgName();
            Intrinsics.checkNotNullExpressionValue(pkgName, "<get-pkgName>(...)");
            linkedHashMap.put("packageName", pkgName);
            NetUtil.INSTANCE.get2("checkPackage", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QuickLoginActivity.onClick.1
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
                    if (QuickLoginActivity.this.getLoginType() == 1) {
                        QuickLoginActivity.this.toSDKLogin(com.cy.yyjia.zhe28.util.Constant.INSTANCE.getToken());
                    } else {
                        QuickLoginActivity.this.toSDKLoginOld();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.QuickLoginActivity.onClick.2
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
                    QuickLoginActivity.this.netFail(it);
                }
            });
        }
    }

    public final void toSDKLogin(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        setResult(1001, new Intent().putExtra("token", token));
        finish();
    }

    public final void toSDKLoginOld() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPkgName(), "com.cy.yyjia.sdk.activity.AppLoginActivity"));
        intent.setFlags(268435456);
        Bundle bundle = new Bundle();
        com.cy.yyjia.zhe28.util.Constant constant = com.cy.yyjia.zhe28.util.Constant.INSTANCE;
        bundle.putString("userName", constant.getUsername());
        bundle.putString("userId", String.valueOf(constant.getId()));
        bundle.putString(SerializableCookie.COOKIE, constant.getCookieString());
        bundle.putString(HintConstants.AUTOFILL_HINT_PASSWORD, SPUtil.INSTANCE.getSPData(getMContext(), HintConstants.AUTOFILL_HINT_PASSWORD));
        bundle.putString(HintConstants.AUTOFILL_HINT_PHONE, SPUtil.INSTANCE.getSPData(getMContext(), HintConstants.AUTOFILL_HINT_PHONE));
        bundle.putString("idCard", SPUtil.INSTANCE.getSPData(getMContext(), "idCard"));
        bundle.putString("realName", SPUtil.INSTANCE.getSPData(getMContext(), "realName"));
        bundle.putString("age", SPUtil.INSTANCE.getSPData(getMContext(), "age"));
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == 1001 && data != null) {
            getMBinding().tvLogin.performClick();
        }
    }

    private final Drawable getIcon(String pakgename) {
        PackageManager packageManager = getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(pakgename, 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            return packageManager.getApplicationIcon(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        toast("取消登录");
        setResult(7892);
        finish();
    }
}
