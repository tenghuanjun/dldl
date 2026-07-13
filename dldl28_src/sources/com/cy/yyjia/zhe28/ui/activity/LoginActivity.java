package com.cy.yyjia.zhe28.ui.activity;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import androidx.activity.compose.ComponentActivityKt;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.ClickableTextKt;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.core.view.ViewCompat;
import androidx.room.Room;
import com.cy.yyjia.zhe28.BuildConfig;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseComponentActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.db.LoginAccountDao;
import com.cy.yyjia.zhe28.db.LoginAccountDatabase;
import com.cy.yyjia.zhe28.domain.LoginAccount;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.domain.LoginResult;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.WxLoginBean;
import com.cy.yyjia.zhe28.domain.WxLoginResult;
import com.cy.yyjia.zhe28.domain.WxUserBean;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.theme.ComponentKt;
import com.cy.yyjia.zhe28.ui.theme.ThemeKt;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.google.gson.Gson;
import com.lzy.okgo.cookie.SerializableCookie;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.AuthUIControlClickListener;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: LoginActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010X\u001a\u00020\u00122\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\b\u0010Y\u001a\u00020\u0012H\u0016J\u0006\u0010Z\u001a\u00020\u0012J\u0006\u0010[\u001a\u00020\u0012J\b\u0010\\\u001a\u00020\u0012H\u0016J\u0006\u0010]\u001a\u00020\u0012J\u000e\u0010^\u001a\u00020\u00122\u0006\u0010_\u001a\u00020`J\b\u0010a\u001a\u00020\u0012H\u0016J\u0010\u0010b\u001a\u00020\u00122\u0006\u0010c\u001a\u00020dH\u0016J\b\u0010e\u001a\u00020\u0012H\u0014J\u000e\u0010f\u001a\u00020\u00122\u0006\u0010g\u001a\u00020`J\u000e\u0010h\u001a\u00020\u00122\u0006\u0010i\u001a\u000201J\u000e\u0010j\u001a\u00020\u00122\u0006\u0010k\u001a\u00020lR\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\r\u0010\u000eR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R#\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\t\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R+\u0010,\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010+\u001a\u0004\b-\u0010'\"\u0004\b.\u0010)R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R \u00106\u001a\b\u0012\u0004\u0012\u00020\f07X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R+\u0010<\u001a\u0002012\u0006\u0010#\u001a\u0002018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010+\u001a\u0004\b=\u00103\"\u0004\b>\u00105R+\u0010@\u001a\u0002012\u0006\u0010#\u001a\u0002018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010+\u001a\u0004\bA\u00103\"\u0004\bB\u00105R+\u0010D\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010+\u001a\u0004\bE\u0010'\"\u0004\bF\u0010)R+\u0010H\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010+\u001a\u0004\bI\u0010'\"\u0004\bJ\u0010)R+\u0010L\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010+\u001a\u0004\bM\u0010'\"\u0004\bN\u0010)R+\u0010P\u001a\u0002012\u0006\u0010#\u001a\u0002018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010+\u001a\u0004\bQ\u00103\"\u0004\bR\u00105R+\u0010T\u001a\u0002012\u0006\u0010#\u001a\u0002018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010+\u001a\u0004\bU\u00103\"\u0004\bV\u00105¨\u0006m²\u0006\n\u0010n\u001a\u00020$X\u008a\u008e\u0002"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/LoginActivity;", "Lcom/cy/yyjia/zhe28/base/BaseComponentActivity;", "Landroid/view/View$OnClickListener;", "()V", "accountDao", "Lcom/cy/yyjia/zhe28/db/LoginAccountDao;", "getAccountDao", "()Lcom/cy/yyjia/zhe28/db/LoginAccountDao;", "accountDao$delegate", "Lkotlin/Lazy;", "accounts", "", "Lcom/cy/yyjia/zhe28/domain/LoginAccount;", "getAccounts", "()Ljava/util/List;", "accounts$delegate", "action", "Lkotlin/Function0;", "", "getAction", "()Lkotlin/jvm/functions/Function0;", "setAction", "(Lkotlin/jvm/functions/Function0;)V", "api", "Lcom/tencent/mm/opensdk/openapi/IWXAPI;", "kotlin.jvm.PlatformType", "getApi", "()Lcom/tencent/mm/opensdk/openapi/IWXAPI;", "api$delegate", "authHelper", "Lcom/mobile/auth/gatewayauth/PhoneNumberAuthHelper;", "getAuthHelper", "()Lcom/mobile/auth/gatewayauth/PhoneNumberAuthHelper;", "setAuthHelper", "(Lcom/mobile/auth/gatewayauth/PhoneNumberAuthHelper;)V", "<set-?>", "", "check", "getCheck", "()Z", "setCheck", "(Z)V", "check$delegate", "Landroidx/compose/runtime/MutableState;", "countDown", "getCountDown", "setCountDown", "countDown$delegate", "loginType", "", "getLoginType", "()Ljava/lang/String;", "setLoginType", "(Ljava/lang/String;)V", "matchedAccounts", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "getMatchedAccounts", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "setMatchedAccounts", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;)V", HintConstants.AUTOFILL_HINT_PASSWORD, "getPassword", "setPassword", "password$delegate", HintConstants.AUTOFILL_HINT_PHONE, "getPhone", "setPhone", "phone$delegate", "phoneLogin", "getPhoneLogin", "setPhoneLogin", "phoneLogin$delegate", "showAccounts", "getShowAccounts", "setShowAccounts", "showAccounts$delegate", "showCheck", "getShowCheck", "setShowCheck", "showCheck$delegate", HintConstants.AUTOFILL_HINT_USERNAME, "getUsername", "setUsername", "username$delegate", "yzm", "getYzm", "setYzm", "yzm$delegate", "checkPolicy", "init", "initFastLogin", "initLoginUI", "initView", "login", "loginSuccess", "it", "Lcom/cy/yyjia/zhe28/domain/LoginResult;", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onResume", "setCookie", "result", "tokenLogin", "token", "wxLogin", "wxLoginBean", "Lcom/cy/yyjia/zhe28/domain/WxLoginBean;", "app_zhe28Release", "restart"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LoginActivity extends BaseComponentActivity implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: accountDao$delegate, reason: from kotlin metadata */
    private final Lazy accountDao;

    /* JADX INFO: renamed from: accounts$delegate, reason: from kotlin metadata */
    private final Lazy accounts;
    public Function0<Unit> action;

    /* JADX INFO: renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api;
    public PhoneNumberAuthHelper authHelper;

    /* JADX INFO: renamed from: check$delegate, reason: from kotlin metadata */
    private final MutableState check;

    /* JADX INFO: renamed from: countDown$delegate, reason: from kotlin metadata */
    private final MutableState countDown;
    private String loginType;
    private SnapshotStateList<LoginAccount> matchedAccounts;

    /* JADX INFO: renamed from: password$delegate, reason: from kotlin metadata */
    private final MutableState password;

    /* JADX INFO: renamed from: phone$delegate, reason: from kotlin metadata */
    private final MutableState phone;

    /* JADX INFO: renamed from: phoneLogin$delegate, reason: from kotlin metadata */
    private final MutableState phoneLogin;

    /* JADX INFO: renamed from: showAccounts$delegate, reason: from kotlin metadata */
    private final MutableState showAccounts;

    /* JADX INFO: renamed from: showCheck$delegate, reason: from kotlin metadata */
    private final MutableState showCheck;

    /* JADX INFO: renamed from: username$delegate, reason: from kotlin metadata */
    private final MutableState username;

    /* JADX INFO: renamed from: yzm$delegate, reason: from kotlin metadata */
    private final MutableState yzm;

    public LoginActivity() {
        super(2);
        this.loginType = "acccount";
        this.phone = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
        this.username = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
        this.password = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
        this.yzm = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
        this.countDown = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.phoneLogin = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.check = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.showCheck = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.accountDao = LazyKt.lazy(new Function0<LoginAccountDao>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$accountDao$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LoginAccountDao invoke() {
                return ((LoginAccountDatabase) Room.databaseBuilder(this.this$0, LoginAccountDatabase.class, "LoginAccount").allowMainThreadQueries().build()).LoginAccountDao();
            }
        });
        this.accounts = LazyKt.lazy(new Function0<List<LoginAccount>>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$accounts$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<LoginAccount> invoke() {
                return this.this$0.getAccountDao().getAll();
            }
        });
        this.matchedAccounts = SnapshotStateKt.mutableStateListOf();
        this.showAccounts = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.api = LazyKt.lazy(new Function0<IWXAPI>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$api$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final IWXAPI invoke() {
                return WXAPIFactory.createWXAPI(this.this$0, Constant.INSTANCE.getWxAppId(), true);
            }
        });
    }

    public final String getLoginType() {
        return this.loginType;
    }

    public final void setLoginType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.loginType = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String getPhone() {
        return (String) this.phone.getValue();
    }

    public final void setPhone(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phone.setValue(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String getUsername() {
        return (String) this.username.getValue();
    }

    public final void setUsername(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.username.setValue(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String getPassword() {
        return (String) this.password.getValue();
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password.setValue(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String getYzm() {
        return (String) this.yzm.getValue();
    }

    public final void setYzm(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.yzm.setValue(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getCountDown() {
        return ((Boolean) this.countDown.getValue()).booleanValue();
    }

    public final void setCountDown(boolean z) {
        this.countDown.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getPhoneLogin() {
        return ((Boolean) this.phoneLogin.getValue()).booleanValue();
    }

    public final void setPhoneLogin(boolean z) {
        this.phoneLogin.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getCheck() {
        return ((Boolean) this.check.getValue()).booleanValue();
    }

    public final void setCheck(boolean z) {
        this.check.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getShowCheck() {
        return ((Boolean) this.showCheck.getValue()).booleanValue();
    }

    public final void setShowCheck(boolean z) {
        this.showCheck.setValue(Boolean.valueOf(z));
    }

    public final LoginAccountDao getAccountDao() {
        return (LoginAccountDao) this.accountDao.getValue();
    }

    public final List<LoginAccount> getAccounts() {
        return (List) this.accounts.getValue();
    }

    public final SnapshotStateList<LoginAccount> getMatchedAccounts() {
        return this.matchedAccounts;
    }

    public final void setMatchedAccounts(SnapshotStateList<LoginAccount> snapshotStateList) {
        Intrinsics.checkNotNullParameter(snapshotStateList, "<set-?>");
        this.matchedAccounts = snapshotStateList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getShowAccounts() {
        return ((Boolean) this.showAccounts.getValue()).booleanValue();
    }

    public final void setShowAccounts(boolean z) {
        this.showAccounts.setValue(Boolean.valueOf(z));
    }

    public final Function0<Unit> getAction() {
        Function0<Unit> function0 = this.action;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("action");
        return null;
    }

    public final void setAction(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.action = function0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IWXAPI getApi() {
        return (IWXAPI) this.api.getValue();
    }

    public final PhoneNumberAuthHelper getAuthHelper() {
        PhoneNumberAuthHelper phoneNumberAuthHelper = this.authHelper;
        if (phoneNumberAuthHelper != null) {
            return phoneNumberAuthHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("authHelper");
        return null;
    }

    public final void setAuthHelper(PhoneNumberAuthHelper phoneNumberAuthHelper) {
        Intrinsics.checkNotNullParameter(phoneNumberAuthHelper, "<set-?>");
        this.authHelper = phoneNumberAuthHelper;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseComponentActivity
    public void init() {
        Constant.INSTANCE.logout(this);
        LoginAccount recent = getAccountDao().getRecent();
        if (recent != null) {
            setUsername(recent.getUsername());
            setPassword(recent.getPassword());
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
            String string = sharedPreferences.getString(HintConstants.AUTOFILL_HINT_USERNAME, "");
            Intrinsics.checkNotNull(string);
            setUsername(string);
            String string2 = sharedPreferences.getString(HintConstants.AUTOFILL_HINT_PASSWORD, "");
            Intrinsics.checkNotNull(string2);
            setPassword(string2);
        }
        initFastLogin();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseComponentActivity
    public void initView() {
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-1170494181, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                if ((i & 11) != 2 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1170494181, i, -1, "com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.<anonymous> (LoginActivity.kt:139)");
                    }
                    final LoginActivity loginActivity = LoginActivity.this;
                    ThemeKt.TransparentTheme(ComposableLambdaKt.composableLambda(composer, -866770369, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i2) {
                            if ((i2 & 11) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-866770369, i2, -1, "com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.<anonymous>.<anonymous> (LoginActivity.kt:140)");
                                }
                                ScaffoldKt.m1824ScaffoldTvnljyQ(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda(composer2, 1631157582, true, new C02091(loginActivity)), composer2, 805306374, 510);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }

                        /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1, reason: invalid class name and collision with other inner class name */
                        /* JADX INFO: compiled from: LoginActivity.kt */
                        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "innerPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "invoke", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
                        static final class C02091 extends Lambda implements Function3<PaddingValues, Composer, Integer, Unit> {
                            final /* synthetic */ LoginActivity this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C02091(LoginActivity loginActivity) {
                                super(3);
                                this.this$0 = loginActivity;
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
                                invoke(paddingValues, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(PaddingValues innerPadding, Composer composer, int i) {
                                int i2;
                                final LoginActivity loginActivity;
                                int i3;
                                String str;
                                final LoginActivity loginActivity2;
                                LoginActivity loginActivity3;
                                String str2;
                                Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
                                if ((i & 14) == 0) {
                                    i2 = (composer.changed(innerPadding) ? 4 : 2) | i;
                                } else {
                                    i2 = i;
                                }
                                if ((i2 & 91) != 18 || !composer.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1631157582, i, -1, "com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.<anonymous>.<anonymous>.<anonymous> (LoginActivity.kt:143)");
                                    }
                                    Modifier modifierPadding = PaddingKt.padding(BackgroundKt.m242backgroundbw27NRU$default(Modifier.INSTANCE, ColorKt.Color(1711276032), null, 2, null), innerPadding);
                                    final LoginActivity loginActivity4 = this.this$0;
                                    composer.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation(composer, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
                                    composer.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierPadding);
                                    if (!(composer.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer.startReusableNode();
                                    if (composer.getInserting()) {
                                        composer.createNode(constructor);
                                    } else {
                                        composer.useNode();
                                    }
                                    Composer composerM2989constructorimpl = Updater.m2989constructorimpl(composer);
                                    Updater.m2996setimpl(composerM2989constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                    composer.startReplaceableGroup(2058660585);
                                    ComposerKt.sourceInformationMarkerStart(composer, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                    SpacerKt.Spacer(ColumnScope.CC.weight$default(ColumnScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), composer, 0);
                                    if (loginActivity4.getShowCheck()) {
                                        composer.startReplaceableGroup(-1963996694);
                                        float f = 16;
                                        Modifier modifierM241backgroundbw27NRU = BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3531getWhite0d7_KjU(), RoundedCornerShapeKt.m878RoundedCornerShapea9UjIt4$default(Dp.m5882constructorimpl(f), Dp.m5882constructorimpl(f), 0.0f, 0.0f, 12, null));
                                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                        composer.startReplaceableGroup(-483455358);
                                        ComposerKt.sourceInformation(composer, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                        MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composer, 48);
                                        composer.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM241backgroundbw27NRU);
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            composer.createNode(constructor2);
                                        } else {
                                            composer.useNode();
                                        }
                                        Composer composerM2989constructorimpl2 = Updater.m2989constructorimpl(composer);
                                        Updater.m2996setimpl(composerM2989constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM2989constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM2989constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                        composer.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(40)), composer, 6);
                                        TextKt.m2169Text4IGK_g("登录需知", (Modifier) null, com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 199686, 0, 131026);
                                        float f2 = 32;
                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f2)), composer, 6);
                                        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
                                        builder.append("请您确认已阅读并同意");
                                        builder.pushStyle(new SpanStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimary(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null));
                                        int iPushStringAnnotation = builder.pushStringAnnotation("UserAgreement", "");
                                        try {
                                            builder.append("《28手游用户协议》");
                                            Unit unit = Unit.INSTANCE;
                                            builder.pop(iPushStringAnnotation);
                                            builder.pop();
                                            builder.append("和");
                                            builder.pushStyle(new SpanStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimary(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65534, (DefaultConstructorMarker) null));
                                            iPushStringAnnotation = builder.pushStringAnnotation("PrivacyPolicy", "");
                                            try {
                                                builder.append("《28手游隐私政策》");
                                                Unit unit2 = Unit.INSTANCE;
                                                builder.pop(iPushStringAnnotation);
                                                builder.pop();
                                                final AnnotatedString annotatedString = builder.toAnnotatedString();
                                                ClickableTextKt.m889ClickableText4YKlhWE(annotatedString, PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(23), 0.0f, 2, null), new TextStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777208, (DefaultConstructorMarker) null), false, 0, 0, null, new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(1);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                                        invoke(num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(int i4) {
                                                        if (((AnnotatedString.Range) CollectionsKt.firstOrNull((List) annotatedString.getStringAnnotations("UserAgreement", i4, i4))) != null) {
                                                            Util.openProtocol(loginActivity4.getMContext(), "用户协议", "userAgreement");
                                                        }
                                                        if (((AnnotatedString.Range) CollectionsKt.firstOrNull((List) annotatedString.getStringAnnotations("PrivacyPolicy", i4, i4))) != null) {
                                                            Util.openProtocol(loginActivity4.getMContext(), "隐私政策", "privacyPolicy");
                                                        }
                                                    }
                                                }, composer, 48, 120);
                                                SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f2)), composer, 6);
                                                TextKt.m2169Text4IGK_g("同意并登录", PaddingKt.m600paddingVpY3zN4$default(BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(PaddingKt.m600paddingVpY3zN4$default(ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$1$2
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
                                                        loginActivity4.setCheck(true);
                                                        loginActivity4.setShowCheck(false);
                                                        loginActivity4.getAction().invoke();
                                                    }
                                                }, composer, 6), Dp.m5882constructorimpl(38), 0.0f, 2, null), 0.0f, 1, null), com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimary(), RoundedCornerShapeKt.getCircleShape()), 0.0f, Dp.m5882constructorimpl(15), 1, null), Color.INSTANCE.m3531getWhite0d7_KjU(), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5757boximpl(TextAlign.INSTANCE.m5764getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 130512);
                                                float f3 = 24;
                                                SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f3)), composer, 6);
                                                TextKt.m2169Text4IGK_g("取消", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$1$3
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
                                                        loginActivity4.setShowCheck(false);
                                                    }
                                                }, composer, 6), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText2(), TextUnitKt.getSp(16), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5757boximpl(TextAlign.INSTANCE.m5764getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3078, 0, 130544);
                                                SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f3)), composer, 6);
                                                ComposerKt.sourceInformationMarkerEnd(composer);
                                                composer.endReplaceableGroup();
                                                composer.endNode();
                                                composer.endReplaceableGroup();
                                                composer.endReplaceableGroup();
                                                composer.endReplaceableGroup();
                                            } finally {
                                            }
                                        } finally {
                                        }
                                    } else if (loginActivity4.getCountDown()) {
                                        composer.startReplaceableGroup(-1963991726);
                                        float f4 = 16;
                                        Modifier modifierM241backgroundbw27NRU2 = BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3531getWhite0d7_KjU(), RoundedCornerShapeKt.m878RoundedCornerShapea9UjIt4$default(Dp.m5882constructorimpl(f4), Dp.m5882constructorimpl(f4), 0.0f, 0.0f, 12, null));
                                        Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
                                        composer.startReplaceableGroup(-483455358);
                                        ComposerKt.sourceInformation(composer, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                        MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally2, composer, 48);
                                        composer.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM241backgroundbw27NRU2);
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            composer.createNode(constructor3);
                                        } else {
                                            composer.useNode();
                                        }
                                        Composer composerM2989constructorimpl3 = Updater.m2989constructorimpl(composer);
                                        Updater.m2996setimpl(composerM2989constructorimpl3, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                                            composerM2989constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                                            composerM2989constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                                        }
                                        function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                        composer.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                        float f5 = 40;
                                        Modifier modifierM602paddingqDBjuR0$default = PaddingKt.m602paddingqDBjuR0$default(PaddingKt.m600paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5882constructorimpl(f4), 0.0f, 2, null), 0.0f, Dp.m5882constructorimpl(f5), 0.0f, 0.0f, 13, null);
                                        composer.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation(composer, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer, 0);
                                        composer.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                        CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM602paddingqDBjuR0$default);
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            composer.createNode(constructor4);
                                        } else {
                                            composer.useNode();
                                        }
                                        Composer composerM2989constructorimpl4 = Updater.m2989constructorimpl(composer);
                                        Updater.m2996setimpl(composerM2989constructorimpl4, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                                            composerM2989constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                                            composerM2989constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                                        }
                                        function3ModifierMaterializerOf4.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                        composer.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        IconKt.m1641Iconww6aTOc(PainterResources_androidKt.painterResource(R.mipmap.ic_back24, composer, 6), "", ComponentKt.onClick(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$2$1$1
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
                                                loginActivity4.onBackPressed();
                                            }
                                        }, composer, 0), Color.INSTANCE.m3530getUnspecified0d7_KjU(), composer, 3128, 0);
                                        TextKt.m2169Text4IGK_g("输入验证码", boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 199686, 0, 131024);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer.endReplaceableGroup();
                                        composer.endNode();
                                        composer.endReplaceableGroup();
                                        composer.endReplaceableGroup();
                                        String strSubstring = loginActivity4.getPhone().substring(0, 3);
                                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                                        String strSubstring2 = loginActivity4.getPhone().substring(loginActivity4.getPhone().length() - 4);
                                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                                        TextKt.m2169Text4IGK_g("验证码已发送至" + strSubstring + "****" + strSubstring2, columnScopeInstance2.align(PaddingKt.m599paddingVpY3zN4(Modifier.INSTANCE, Dp.m5882constructorimpl(24), Dp.m5882constructorimpl(f4)), Alignment.INSTANCE.getStart()), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText3(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 131056);
                                        ComponentKt.Input(loginActivity4.getYzm(), new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$2$2
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                                                invoke2(str3);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(String it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                loginActivity4.setYzm(it);
                                                if (loginActivity4.getYzm().length() == 6) {
                                                    loginActivity4.login();
                                                }
                                            }
                                        }, null, columnScopeInstance2.align(SizeKt.fillMaxWidth$default(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5882constructorimpl(f4), 1, null), 0.0f, 1, null), Alignment.INSTANCE.getCenterHorizontally()), false, null, new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5598getNumberPjHm6EE(), 0, null, 27, null), new TextStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(22), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, TextAlign.INSTANCE.m5764getCentere0LSkKk(), 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16744444, (DefaultConstructorMarker) null), composer, 1572864, 52);
                                        BoxKt.Box(BackgroundKt.m242backgroundbw27NRU$default(SizeKt.m633height3ABfNKs(SizeKt.fillMaxWidth$default(PaddingKt.m602paddingqDBjuR0$default(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl((float) 50), 0.0f, 2, null), 0.0f, 0.0f, 0.0f, Dp.m5882constructorimpl(f5), 7, null), 0.0f, 1, null), Dp.m5882constructorimpl((float) 1)), ColorKt.Color(4292401368L), null, 2, null), composer, 6);
                                        composer.startReplaceableGroup(-492369756);
                                        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                                        Object objRememberedValue = composer.rememberedValue();
                                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                                            composer.updateRememberedValue(objRememberedValue);
                                        }
                                        composer.endReplaceableGroup();
                                        final MutableState mutableState = (MutableState) objRememberedValue;
                                        AndroidView_androidKt.AndroidView(new LoginActivity$initView$1$1$1$1$2$3(loginActivity4), PaddingKt.m600paddingVpY3zN4$default(BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(PaddingKt.m602paddingqDBjuR0$default(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(38), 0.0f, 2, null), 0.0f, 0.0f, 0.0f, Dp.m5882constructorimpl(88), 7, null), 0.0f, 1, null), invoke$lambda$16$lambda$8$lambda$6(mutableState) ? com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimary() : com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimaryDisable(), RoundedCornerShapeKt.getCircleShape()), 0.0f, Dp.m5882constructorimpl(15), 1, null), new Function1<CountdownView, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$2$4
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(CountdownView countdownView) {
                                                invoke2(countdownView);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(CountdownView it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                LoginActivity.AnonymousClass1.C02081.C02091.invoke$lambda$16$lambda$8$lambda$7(mutableState, it.isEnabled());
                                                loginActivity4.log("restart " + LoginActivity.AnonymousClass1.C02081.C02091.invoke$lambda$16$lambda$8$lambda$6(mutableState));
                                            }
                                        }, composer, 0, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer.endReplaceableGroup();
                                        composer.endNode();
                                        composer.endReplaceableGroup();
                                        composer.endReplaceableGroup();
                                        composer.endReplaceableGroup();
                                    } else {
                                        composer.startReplaceableGroup(-1963985970);
                                        float f6 = 16;
                                        Modifier modifierM241backgroundbw27NRU3 = BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3531getWhite0d7_KjU(), RoundedCornerShapeKt.m878RoundedCornerShapea9UjIt4$default(Dp.m5882constructorimpl(f6), Dp.m5882constructorimpl(f6), 0.0f, 0.0f, 12, null));
                                        Alignment.Horizontal centerHorizontally3 = Alignment.INSTANCE.getCenterHorizontally();
                                        composer.startReplaceableGroup(-483455358);
                                        ComposerKt.sourceInformation(composer, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                        MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally3, composer, 48);
                                        composer.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                        CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM241backgroundbw27NRU3);
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            composer.createNode(constructor5);
                                        } else {
                                            composer.useNode();
                                        }
                                        Composer composerM2989constructorimpl5 = Updater.m2989constructorimpl(composer);
                                        Updater.m2996setimpl(composerM2989constructorimpl5, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                                            composerM2989constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                                            composerM2989constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                                        }
                                        function3ModifierMaterializerOf5.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                        composer.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                        ImageKt.Image(PainterResources_androidKt.painterResource(R.mipmap.ic_close24, composer, 6), "", ComponentKt.onClick(PaddingKt.m602paddingqDBjuR0$default(ColumnScopeInstance.INSTANCE.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd()), 0.0f, Dp.m5882constructorimpl(f6), Dp.m5882constructorimpl(f6), 0.0f, 9, null), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$1
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
                                                loginActivity4.finish();
                                            }
                                        }, composer, 0), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 56, 120);
                                        if (loginActivity4.getPhoneLogin()) {
                                            composer.startReplaceableGroup(-1342941244);
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                            Alignment.Horizontal centerHorizontally4 = Alignment.INSTANCE.getCenterHorizontally();
                                            composer.startReplaceableGroup(-483455358);
                                            ComposerKt.sourceInformation(composer, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                            MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally4, composer, 48);
                                            composer.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                            CompositionLocalMap currentCompositionLocalMap6 = composer.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
                                            if (!(composer.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer.startReusableNode();
                                            if (composer.getInserting()) {
                                                composer.createNode(constructor6);
                                            } else {
                                                composer.useNode();
                                            }
                                            Composer composerM2989constructorimpl6 = Updater.m2989constructorimpl(composer);
                                            Updater.m2996setimpl(composerM2989constructorimpl6, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2996setimpl(composerM2989constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM2989constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                                                composerM2989constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                                                composerM2989constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                                            }
                                            function3ModifierMaterializerOf6.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                            composer.startReplaceableGroup(2058660585);
                                            ComposerKt.sourceInformationMarkerStart(composer, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                                            TextKt.m2169Text4IGK_g("验证码登录", (Modifier) null, com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 199686, 0, 131026);
                                            float f7 = 32;
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f7)), composer, 6);
                                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                            float f8 = 38;
                                            Modifier modifierM633height3ABfNKs = SizeKt.m633height3ABfNKs(SizeKt.fillMaxWidth$default(BackgroundKt.m241backgroundbw27NRU(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(f8), 0.0f, 2, null), ColorKt.Color(4294506744L), RoundedCornerShapeKt.getCircleShape()), 0.0f, 1, null), Dp.m5882constructorimpl(52));
                                            composer.startReplaceableGroup(693286680);
                                            ComposerKt.sourceInformation(composer, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, 48);
                                            composer.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                            CompositionLocalMap currentCompositionLocalMap7 = composer.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierM633height3ABfNKs);
                                            if (!(composer.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer.startReusableNode();
                                            if (composer.getInserting()) {
                                                composer.createNode(constructor7);
                                            } else {
                                                composer.useNode();
                                            }
                                            Composer composerM2989constructorimpl7 = Updater.m2989constructorimpl(composer);
                                            Updater.m2996setimpl(composerM2989constructorimpl7, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2996setimpl(composerM2989constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM2989constructorimpl7.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                                                composerM2989constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                                                composerM2989constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
                                            }
                                            function3ModifierMaterializerOf7.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                            composer.startReplaceableGroup(2058660585);
                                            ComposerKt.sourceInformationMarkerStart(composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                            ComponentKt.Input(loginActivity4.getPhone(), new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$2$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                                                    invoke2(str3);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(String it) {
                                                    Intrinsics.checkNotNullParameter(it, "it");
                                                    loginActivity4.setPhone(it);
                                                }
                                            }, "请输入手机号", PaddingKt.m600paddingVpY3zN4$default(RowScope.CC.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5882constructorimpl(f6), 0.0f, 2, null), true, null, new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5601getPhonePjHm6EE(), ImeAction.INSTANCE.m5548getDoneeUduSuo(), null, 19, null), new TextStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777208, (DefaultConstructorMarker) null), composer, 1597824, 32);
                                            ImageKt.Image(PainterResources_androidKt.painterResource(R.mipmap.ic_input_clear, composer, 6), "", PaddingKt.m602paddingqDBjuR0$default(ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$2$1$2
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
                                                    loginActivity4.setPhone("");
                                                }
                                            }, composer, 6), 0.0f, 0.0f, Dp.m5882constructorimpl(f6), 0.0f, 11, null), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 56, 120);
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            composer.endReplaceableGroup();
                                            composer.endNode();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f7)), composer, 6);
                                            ComponentKt.PolicyAgreeView(loginActivity4.getCheck(), new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$2$2
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                                    invoke(bool.booleanValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(boolean z) {
                                                    loginActivity4.setCheck(z);
                                                }
                                            }, composer, 0, 0);
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f6)), composer, 6);
                                            TextKt.m2169Text4IGK_g("获取验证码", PaddingKt.m600paddingVpY3zN4$default(BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(PaddingKt.m600paddingVpY3zN4$default(ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$2$3
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
                                                    LoginActivity loginActivity5 = loginActivity4;
                                                    final LoginActivity loginActivity6 = loginActivity4;
                                                    loginActivity5.checkPolicy(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$2$3.1
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
                                                            if (loginActivity6.getPhone().length() > 5 && !loginActivity6.checkClick()) {
                                                                loginActivity6.setCountDown(true);
                                                            } else {
                                                                loginActivity6.toast("请输入正确的手机号");
                                                            }
                                                        }
                                                    });
                                                }
                                            }, composer, 6), Dp.m5882constructorimpl(f8), 0.0f, 2, null), 0.0f, 1, null), com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimary(), RoundedCornerShapeKt.getCircleShape()), 0.0f, Dp.m5882constructorimpl(15), 1, null), Color.INSTANCE.m3531getWhite0d7_KjU(), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5757boximpl(TextAlign.INSTANCE.m5764getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 130512);
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            composer.endReplaceableGroup();
                                            composer.endNode();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                            loginActivity3 = loginActivity4;
                                            str2 = "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh";
                                            str = "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo";
                                        } else {
                                            composer.startReplaceableGroup(-1342936516);
                                            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                            Alignment.Horizontal centerHorizontally5 = Alignment.INSTANCE.getCenterHorizontally();
                                            composer.startReplaceableGroup(-483455358);
                                            ComposerKt.sourceInformation(composer, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                            MeasurePolicy measurePolicyColumnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally5, composer, 48);
                                            composer.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                            CompositionLocalMap currentCompositionLocalMap8 = composer.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default2);
                                            if (!(composer.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer.startReusableNode();
                                            if (composer.getInserting()) {
                                                composer.createNode(constructor8);
                                            } else {
                                                composer.useNode();
                                            }
                                            Composer composerM2989constructorimpl8 = Updater.m2989constructorimpl(composer);
                                            Updater.m2996setimpl(composerM2989constructorimpl8, measurePolicyColumnMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2996setimpl(composerM2989constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM2989constructorimpl8.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                                                composerM2989constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                                                composerM2989constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                                            }
                                            function3ModifierMaterializerOf8.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                            composer.startReplaceableGroup(2058660585);
                                            ComposerKt.sourceInformationMarkerStart(composer, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                                            TextKt.m2169Text4IGK_g("密码登录", (Modifier) null, com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 199686, 0, 131026);
                                            float f9 = 32;
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f9)), composer, 6);
                                            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                                            float f10 = 38;
                                            float f11 = 52;
                                            Modifier modifierM602paddingqDBjuR0$default2 = PaddingKt.m602paddingqDBjuR0$default(SizeKt.m633height3ABfNKs(SizeKt.fillMaxWidth$default(BackgroundKt.m241backgroundbw27NRU(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(f10), 0.0f, 2, null), ColorKt.Color(4294506744L), RoundedCornerShapeKt.getCircleShape()), 0.0f, 1, null), Dp.m5882constructorimpl(f11)), 0.0f, 0.0f, Dp.m5882constructorimpl(f6), 0.0f, 11, null);
                                            composer.startReplaceableGroup(693286680);
                                            ComposerKt.sourceInformation(composer, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composer, 48);
                                            composer.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                            char c = 0;
                                            int currentCompositeKeyHash9 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                            CompositionLocalMap currentCompositionLocalMap9 = composer.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf9 = LayoutKt.modifierMaterializerOf(modifierM602paddingqDBjuR0$default2);
                                            if (!(composer.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer.startReusableNode();
                                            if (composer.getInserting()) {
                                                composer.createNode(constructor9);
                                            } else {
                                                composer.useNode();
                                            }
                                            Composer composerM2989constructorimpl9 = Updater.m2989constructorimpl(composer);
                                            Updater.m2996setimpl(composerM2989constructorimpl9, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2996setimpl(composerM2989constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM2989constructorimpl9.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl9.rememberedValue(), Integer.valueOf(currentCompositeKeyHash9))) {
                                                composerM2989constructorimpl9.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash9));
                                                composerM2989constructorimpl9.apply(Integer.valueOf(currentCompositeKeyHash9), setCompositeKeyHash9);
                                            }
                                            function3ModifierMaterializerOf9.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                            composer.startReplaceableGroup(2058660585);
                                            ComposerKt.sourceInformationMarkerStart(composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                            ComponentKt.Input(loginActivity4.getUsername(), new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                                                    invoke2(str3);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(String text) {
                                                    Intrinsics.checkNotNullParameter(text, "text");
                                                    loginActivity4.setUsername(text);
                                                    loginActivity4.getMatchedAccounts().clear();
                                                    SnapshotStateList<LoginAccount> matchedAccounts = loginActivity4.getMatchedAccounts();
                                                    List<LoginAccount> accounts = loginActivity4.getAccounts();
                                                    ArrayList arrayList = new ArrayList();
                                                    for (Object obj : accounts) {
                                                        if (StringsKt.contains$default((CharSequence) ((LoginAccount) obj).getUsername(), (CharSequence) text, false, 2, (Object) null)) {
                                                            arrayList.add(obj);
                                                        }
                                                    }
                                                    matchedAccounts.addAll(arrayList);
                                                    loginActivity4.setShowAccounts(!r10.getMatchedAccounts().isEmpty());
                                                }
                                            }, "请输入账号/手机号码", PaddingKt.m600paddingVpY3zN4$default(RowScope.CC.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5882constructorimpl(f6), 0.0f, 2, null), true, null, new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5597getEmailPjHm6EE(), ImeAction.INSTANCE.m5550getNexteUduSuo(), null, 19, null), new TextStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777208, (DefaultConstructorMarker) null), composer, 1597824, 32);
                                            composer.startReplaceableGroup(1347202416);
                                            if (TextUtils.isEmpty(loginActivity4.getUsername())) {
                                                loginActivity = loginActivity4;
                                            } else {
                                                loginActivity = loginActivity4;
                                                ImageKt.Image(PainterResources_androidKt.painterResource(R.mipmap.ic_input_clear, composer, 6), "", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$1$2
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
                                                        loginActivity.setUsername("");
                                                    }
                                                }, composer, 6), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 56, 120);
                                            }
                                            composer.endReplaceableGroup();
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            composer.endReplaceableGroup();
                                            composer.endNode();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                            composer.startReplaceableGroup(1347202979);
                                            if (loginActivity.getShowAccounts()) {
                                                i3 = 1;
                                                AndroidPopup_androidKt.m6135PopupK5zGePQ(null, IntOffsetKt.IntOffset(Util.dpToPx(loginActivity.getMContext(), 38.0f), 0), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$2
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
                                                        loginActivity.setShowAccounts(false);
                                                    }
                                                }, null, ComposableLambdaKt.composableLambda(composer, -1088737524, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$3
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                                        invoke(composer2, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer2, int i4) {
                                                        if ((i4 & 11) != 2 || !composer2.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-1088737524, i4, -1, "com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LoginActivity.kt:497)");
                                                            }
                                                            Modifier modifierM600paddingVpY3zN4$default = PaddingKt.m600paddingVpY3zN4$default(BackgroundKt.m241backgroundbw27NRU(SizeKt.wrapContentWidth$default(Modifier.INSTANCE, null, false, 3, null), ColorKt.Color(4294506744L), RoundedCornerShapeKt.m876RoundedCornerShape0680j_4(Dp.m5882constructorimpl(8))), Dp.m5882constructorimpl(16), 0.0f, 2, null);
                                                            final LoginActivity loginActivity5 = loginActivity;
                                                            Modifier modifierOnClick = ComponentKt.onClick(modifierM600paddingVpY3zN4$default, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$3.1
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
                                                                    loginActivity5.setShowAccounts(false);
                                                                }
                                                            }, composer2, 0);
                                                            final LoginActivity loginActivity6 = loginActivity;
                                                            LazyDslKt.LazyColumn(modifierOnClick, null, null, false, null, null, null, false, new Function1<LazyListScope, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$3.2
                                                                {
                                                                    super(1);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function1
                                                                public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                                                                    invoke2(lazyListScope);
                                                                    return Unit.INSTANCE;
                                                                }

                                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                public final void invoke2(LazyListScope LazyColumn) {
                                                                    Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
                                                                    int size = loginActivity6.getMatchedAccounts().size();
                                                                    final LoginActivity loginActivity7 = loginActivity6;
                                                                    LazyListScope.CC.items$default(LazyColumn, size, null, null, ComposableLambdaKt.composableLambdaInstance(-2037831063, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.1.1.1.1.3.3.3.2.1
                                                                        {
                                                                            super(4);
                                                                        }

                                                                        @Override // kotlin.jvm.functions.Function4
                                                                        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer3, Integer num2) {
                                                                            invoke(lazyItemScope, num.intValue(), composer3, num2.intValue());
                                                                            return Unit.INSTANCE;
                                                                        }

                                                                        public final void invoke(LazyItemScope items, final int i5, Composer composer3, int i6) {
                                                                            int i7;
                                                                            Intrinsics.checkNotNullParameter(items, "$this$items");
                                                                            if ((i6 & 112) == 0) {
                                                                                i7 = (composer3.changed(i5) ? 32 : 16) | i6;
                                                                            } else {
                                                                                i7 = i6;
                                                                            }
                                                                            if ((i7 & 721) != 144 || !composer3.getSkipping()) {
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventStart(-2037831063, i6, -1, "com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LoginActivity.kt:510)");
                                                                                }
                                                                                float f12 = 200;
                                                                                Modifier modifierM600paddingVpY3zN4$default2 = PaddingKt.m600paddingVpY3zN4$default(SizeKt.m652width3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f12)), 0.0f, Dp.m5882constructorimpl(8), 1, null);
                                                                                final LoginActivity loginActivity8 = loginActivity7;
                                                                                Modifier modifierOnClick2 = ComponentKt.onClick(modifierM600paddingVpY3zN4$default2, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.initView.1.1.1.1.3.3.3.2.1.1
                                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                                                                        LoginActivity loginActivity9 = loginActivity8;
                                                                                        loginActivity9.setUsername(loginActivity9.getMatchedAccounts().get(i5).getUsername());
                                                                                        LoginActivity loginActivity10 = loginActivity8;
                                                                                        loginActivity10.setPassword(loginActivity10.getMatchedAccounts().get(i5).getPassword());
                                                                                        loginActivity8.setShowAccounts(false);
                                                                                        loginActivity8.hideSoftKeyboard();
                                                                                    }
                                                                                }, composer3, 6);
                                                                                final LoginActivity loginActivity9 = loginActivity7;
                                                                                composer3.startReplaceableGroup(693286680);
                                                                                ComposerKt.sourceInformation(composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                                                                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                                                                composer3.startReplaceableGroup(-1323940314);
                                                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                                                int currentCompositeKeyHash10 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                                                CompositionLocalMap currentCompositionLocalMap10 = composer3.getCurrentCompositionLocalMap();
                                                                                Function0<ComposeUiNode> constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                                                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf10 = LayoutKt.modifierMaterializerOf(modifierOnClick2);
                                                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                                                    ComposablesKt.invalidApplier();
                                                                                }
                                                                                composer3.startReusableNode();
                                                                                if (composer3.getInserting()) {
                                                                                    composer3.createNode(constructor10);
                                                                                } else {
                                                                                    composer3.useNode();
                                                                                }
                                                                                Composer composerM2989constructorimpl10 = Updater.m2989constructorimpl(composer3);
                                                                                Updater.m2996setimpl(composerM2989constructorimpl10, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                Updater.m2996setimpl(composerM2989constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                if (composerM2989constructorimpl10.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl10.rememberedValue(), Integer.valueOf(currentCompositeKeyHash10))) {
                                                                                    composerM2989constructorimpl10.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash10));
                                                                                    composerM2989constructorimpl10.apply(Integer.valueOf(currentCompositeKeyHash10), setCompositeKeyHash10);
                                                                                }
                                                                                function3ModifierMaterializerOf10.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                                                composer3.startReplaceableGroup(2058660585);
                                                                                ComposerKt.sourceInformationMarkerStart(composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                                                                TextKt.m2169Text4IGK_g(loginActivity9.getMatchedAccounts().get(i5).getUsername(), RowScope.CC.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3072, 0, 131056);
                                                                                ImageKt.Image(PainterResources_androidKt.painterResource(R.mipmap.ic_close24, composer3, 6), "", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$3$2$1$2$1
                                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                                                                        loginActivity9.getAccounts().remove(loginActivity9.getMatchedAccounts().get(i5));
                                                                                        loginActivity9.getAccountDao().delete(loginActivity9.getMatchedAccounts().get(i5));
                                                                                        loginActivity9.getMatchedAccounts().remove(i5);
                                                                                    }
                                                                                }, composer3, 6), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer3, 56, 120);
                                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                                composer3.endReplaceableGroup();
                                                                                composer3.endNode();
                                                                                composer3.endReplaceableGroup();
                                                                                composer3.endReplaceableGroup();
                                                                                if (i5 < loginActivity7.getMatchedAccounts().size() - 1) {
                                                                                    BoxKt.Box(BackgroundKt.m242backgroundbw27NRU$default(SizeKt.m649sizeVpY3zN4(Modifier.INSTANCE, Dp.m5882constructorimpl(f12), Dp.m5882constructorimpl((float) 0.5d)), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText3(), null, 2, null), composer3, 0);
                                                                                }
                                                                                if (ComposerKt.isTraceInProgress()) {
                                                                                    ComposerKt.traceEventEnd();
                                                                                    return;
                                                                                }
                                                                                return;
                                                                            }
                                                                            composer3.skipToGroupEnd();
                                                                        }
                                                                    }), 6, null);
                                                                }
                                                            }, composer2, 0, KeyBoardKey.KeyboardKeyOemClear);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer2.skipToGroupEnd();
                                                    }
                                                }), composer, 24576, 9);
                                            } else {
                                                i3 = 1;
                                            }
                                            composer.endReplaceableGroup();
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f6)), composer, 6);
                                            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                                            DefaultConstructorMarker defaultConstructorMarker = null;
                                            Modifier modifierM633height3ABfNKs2 = SizeKt.m633height3ABfNKs(SizeKt.fillMaxWidth$default(BackgroundKt.m241backgroundbw27NRU(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(f10), 0.0f, 2, null), ColorKt.Color(4294506744L), RoundedCornerShapeKt.getCircleShape()), 0.0f, i3, null), Dp.m5882constructorimpl(f11));
                                            composer.startReplaceableGroup(693286680);
                                            ComposerKt.sourceInformation(composer, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer, 48);
                                            composer.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash10 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                            CompositionLocalMap currentCompositionLocalMap10 = composer.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf10 = LayoutKt.modifierMaterializerOf(modifierM633height3ABfNKs2);
                                            if (!(composer.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer.startReusableNode();
                                            if (composer.getInserting()) {
                                                composer.createNode(constructor10);
                                            } else {
                                                composer.useNode();
                                            }
                                            Composer composerM2989constructorimpl10 = Updater.m2989constructorimpl(composer);
                                            Updater.m2996setimpl(composerM2989constructorimpl10, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2996setimpl(composerM2989constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM2989constructorimpl10.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl10.rememberedValue(), Integer.valueOf(currentCompositeKeyHash10))) {
                                                composerM2989constructorimpl10.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash10));
                                                composerM2989constructorimpl10.apply(Integer.valueOf(currentCompositeKeyHash10), setCompositeKeyHash10);
                                            }
                                            function3ModifierMaterializerOf10.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                            composer.startReplaceableGroup(2058660585);
                                            ComposerKt.sourceInformationMarkerStart(composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            str = "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo";
                                            LoginActivity loginActivity5 = loginActivity;
                                            ComponentKt.Input(loginActivity.getPassword(), new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$4$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                                                    invoke2(str3);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(String it) {
                                                    Intrinsics.checkNotNullParameter(it, "it");
                                                    loginActivity.setPassword(it);
                                                }
                                            }, "请输入登录密码", PaddingKt.m600paddingVpY3zN4$default(RowScope.CC.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m5882constructorimpl(f6), 0.0f, 2, null), true, new PasswordVisualTransformation(c, i3, defaultConstructorMarker), new KeyboardOptions(0, false, KeyboardType.INSTANCE.m5600getPasswordPjHm6EE(), ImeAction.INSTANCE.m5548getDoneeUduSuo(), null, 19, null), new TextStyle(com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777208, (DefaultConstructorMarker) null), composer, 1597824, 0);
                                            composer.startReplaceableGroup(1347209628);
                                            if (TextUtils.isEmpty(loginActivity5.getPassword())) {
                                                loginActivity2 = loginActivity5;
                                            } else {
                                                loginActivity2 = loginActivity5;
                                                ImageKt.Image(PainterResources_androidKt.painterResource(R.mipmap.ic_input_clear, composer, 6), "", PaddingKt.m602paddingqDBjuR0$default(ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$4$2
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
                                                        loginActivity2.setPassword("");
                                                    }
                                                }, composer, 6), 0.0f, 0.0f, Dp.m5882constructorimpl(f6), 0.0f, 11, null), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 56, 120);
                                            }
                                            composer.endReplaceableGroup();
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            composer.endReplaceableGroup();
                                            composer.endNode();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f9)), composer, 6);
                                            ComponentKt.PolicyAgreeView(loginActivity2.getCheck(), new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$5
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                                    invoke(bool.booleanValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(boolean z) {
                                                    loginActivity2.setCheck(z);
                                                }
                                            }, composer, 0, 0);
                                            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f6)), composer, 6);
                                            loginActivity3 = loginActivity2;
                                            str2 = "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh";
                                            TextKt.m2169Text4IGK_g("立即登录", PaddingKt.m600paddingVpY3zN4$default(BackgroundKt.m241backgroundbw27NRU(SizeKt.fillMaxWidth$default(PaddingKt.m600paddingVpY3zN4$default(ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$6
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
                                                    LoginActivity loginActivity6 = loginActivity2;
                                                    final LoginActivity loginActivity7 = loginActivity2;
                                                    loginActivity6.checkPolicy(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$3$6.1
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
                                                            if (loginActivity7.checkClick()) {
                                                                return;
                                                            }
                                                            loginActivity7.login();
                                                        }
                                                    });
                                                }
                                            }, composer, 6), Dp.m5882constructorimpl(f10), 0.0f, 2, null), 0.0f, 1, null), com.cy.yyjia.zhe28.ui.theme.ColorKt.getPrimary(), RoundedCornerShapeKt.getCircleShape()), 0.0f, Dp.m5882constructorimpl(15), 1, null), Color.INSTANCE.m3531getWhite0d7_KjU(), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5757boximpl(TextAlign.INSTANCE.m5764getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 130512);
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            composer.endReplaceableGroup();
                                            composer.endNode();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                            composer.endReplaceableGroup();
                                        }
                                        float f12 = 32;
                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f12)), composer, 6);
                                        Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                                        composer.startReplaceableGroup(693286680);
                                        ComposerKt.sourceInformation(composer, str);
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer, 48);
                                        composer.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer, str2);
                                        int currentCompositeKeyHash11 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                        CompositionLocalMap currentCompositionLocalMap11 = composer.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf11 = LayoutKt.modifierMaterializerOf(companion);
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            composer.createNode(constructor11);
                                        } else {
                                            composer.useNode();
                                        }
                                        Composer composerM2989constructorimpl11 = Updater.m2989constructorimpl(composer);
                                        Updater.m2996setimpl(composerM2989constructorimpl11, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl11, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl11.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl11.rememberedValue(), Integer.valueOf(currentCompositeKeyHash11))) {
                                            composerM2989constructorimpl11.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash11));
                                            composerM2989constructorimpl11.apply(Integer.valueOf(currentCompositeKeyHash11), setCompositeKeyHash11);
                                        }
                                        function3ModifierMaterializerOf11.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                                        composer.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        final LoginActivity loginActivity6 = loginActivity3;
                                        TextKt.m2169Text4IGK_g("一键登录", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$4$1
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
                                                loginActivity6.getAuthHelper().getLoginToken(loginActivity6.getMContext(), 5000);
                                            }
                                        }, composer, 6), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText2(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131064);
                                        float f13 = 12;
                                        float f14 = 1;
                                        BoxKt.Box(SizeKt.m649sizeVpY3zN4(BackgroundKt.m242backgroundbw27NRU$default(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(f13), 0.0f, 2, null), ColorKt.Color(4292401368L), null, 2, null), Dp.m5882constructorimpl(f14), Dp.m5882constructorimpl(f13)), composer, 6);
                                        TextKt.m2169Text4IGK_g(loginActivity6.getPhoneLogin() ? "密码登录" : "验证码登录", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$4$2
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
                                                loginActivity6.setPhoneLogin(!r0.getPhoneLogin());
                                                loginActivity6.setShowAccounts(false);
                                            }
                                        }, composer, 6), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText2(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131064);
                                        BoxKt.Box(SizeKt.m649sizeVpY3zN4(BackgroundKt.m242backgroundbw27NRU$default(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(f13), 0.0f, 2, null), ColorKt.Color(4292401368L), null, 2, null), Dp.m5882constructorimpl(f14), Dp.m5882constructorimpl(f13)), composer, 6);
                                        TextKt.m2169Text4IGK_g("微信登录", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$4$3
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
                                                LoginActivity loginActivity7 = loginActivity6;
                                                final LoginActivity loginActivity8 = loginActivity6;
                                                loginActivity7.checkPolicy(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$4$3.1
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
                                                        if (loginActivity8.getApi().isWXAppInstalled()) {
                                                            SendAuth.Req req = new SendAuth.Req();
                                                            req.scope = "snsapi_userinfo";
                                                            req.state = "28zhe";
                                                            loginActivity8.getApi().sendReq(req);
                                                            return;
                                                        }
                                                        loginActivity8.toast("请先安装微信");
                                                    }
                                                });
                                            }
                                        }, composer, 6), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText2(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131064);
                                        composer.startReplaceableGroup(-1342921193);
                                        if (!loginActivity6.getPhoneLogin()) {
                                            BoxKt.Box(SizeKt.m649sizeVpY3zN4(BackgroundKt.m242backgroundbw27NRU$default(PaddingKt.m600paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5882constructorimpl(f13), 0.0f, 2, null), ColorKt.Color(4292401368L), null, 2, null), Dp.m5882constructorimpl(f14), Dp.m5882constructorimpl(f13)), composer, 6);
                                            TextKt.m2169Text4IGK_g("忘记密码", ComponentKt.onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$3$4$4
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
                                                    loginActivity6.startActivity(new Intent(loginActivity6.getMContext(), (Class<?>) ChangePasswordActivity.class).putExtra("forget", true));
                                                }
                                            }, composer, 6), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText2(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131064);
                                        }
                                        composer.endReplaceableGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer.endReplaceableGroup();
                                        composer.endNode();
                                        composer.endReplaceableGroup();
                                        composer.endReplaceableGroup();
                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f12)), composer, 6);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer.endReplaceableGroup();
                                        composer.endNode();
                                        composer.endReplaceableGroup();
                                        composer.endReplaceableGroup();
                                        composer.endReplaceableGroup();
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    composer.endReplaceableGroup();
                                    composer.endNode();
                                    composer.endReplaceableGroup();
                                    composer.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer.skipToGroupEnd();
                            }

                            /* JADX INFO: Access modifiers changed from: private */
                            public static final boolean invoke$lambda$16$lambda$8$lambda$6(MutableState<Boolean> mutableState) {
                                return mutableState.getValue().booleanValue();
                            }

                            /* JADX INFO: Access modifiers changed from: private */
                            public static final void invoke$lambda$16$lambda$8$lambda$7(MutableState<Boolean> mutableState, boolean z) {
                                mutableState.setValue(Boolean.valueOf(z));
                            }
                        }
                    }), composer, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }), 1, null);
    }

    public final void initFastLogin() {
        String string = getString(R.string.ali_app_secret);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        LoginActivity loginActivity = this;
        PhoneNumberAuthHelper phoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(loginActivity, new TokenResultListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initFastLogin$resultListener$1
            @Override // com.mobile.auth.gatewayauth.TokenResultListener
            public void onTokenSuccess(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                try {
                    TokenRet tokenRetFromJson = TokenRet.fromJson(s);
                    if (Intrinsics.areEqual(ResultCode.CODE_START_AUTHPAGE_SUCCESS, tokenRetFromJson.getCode())) {
                        this.this$0.log("唤起授权页成功：" + s);
                    }
                    if (Intrinsics.areEqual("600000", tokenRetFromJson.getCode())) {
                        LoginActivity loginActivity2 = this.this$0;
                        String token = tokenRetFromJson.getToken();
                        Intrinsics.checkNotNullExpressionValue(token, "getToken(...)");
                        loginActivity2.tokenLogin(token);
                        this.this$0.getAuthHelper().setAuthListener(null);
                        this.this$0.getAuthHelper().quitLoginPage();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LoginActivity loginActivity3 = this.this$0;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    loginActivity3.toast(localizedMessage);
                }
            }

            @Override // com.mobile.auth.gatewayauth.TokenResultListener
            public void onTokenFailed(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                this.this$0.log(s);
                this.this$0.getAuthHelper().quitLoginPage();
            }
        });
        Intrinsics.checkNotNullExpressionValue(phoneNumberAuthHelper, "getInstance(...)");
        setAuthHelper(phoneNumberAuthHelper);
        getAuthHelper().getReporter().setLoggerEnable(true);
        getAuthHelper().setAuthSDKInfo(string);
        getAuthHelper().checkEnvAvailable(2);
        initLoginUI();
        getAuthHelper().getLoginToken(loginActivity, 5000);
    }

    public final void initLoginUI() {
        getAuthHelper().setAuthPageUseDayLight(false);
        getAuthHelper().setUIClickListener(new AuthUIControlClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$$ExternalSyntheticLambda0
            @Override // com.mobile.auth.gatewayauth.AuthUIControlClickListener
            public final void onClick(String str, Context context, String str2) {
                LoginActivity.initLoginUI$lambda$0(this.f$0, str, context, str2);
            }
        });
        getAuthHelper().setAuthUIConfig(new AuthUIConfig.Builder().setAppPrivacyOne("用户协议", "https://www.28zhe.com/help/agreement.htm").setAppPrivacyTwo("隐私政策", "https://www.28zhe.com/help/privacyPolicy.htm").setAppPrivacyColor(-7829368, android.graphics.Color.parseColor("#F86938")).setPrivacyState(false).setSwitchAccHidden(true).setLogBtnToastHidden(false).setStatusBarColor(0).setStatusBarUIFlag(1).setLightColor(false).setProtocolAction("com.cy.yyjia.zhw28.web").setNavColor(-1).setNavTextColor(ViewCompat.MEASURED_STATE_MASK).setWebNavTextSizeDp(20).setLogBtnBackgroundPath("bg_button").setSwitchAccHidden(false).create());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final void initLoginUI$lambda$0(LoginActivity this$0, String str, Context context, String str2) {
        JSONObject jSONObject;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            jSONObject = !TextUtils.isEmpty(str2) ? new JSONObject(str2) : null;
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            switch (str.hashCode()) {
                case 1620409945:
                    if (str.equals(ResultCode.CODE_ERROR_USER_CANCEL)) {
                        this$0.getAuthHelper().quitLoginPage();
                        break;
                    }
                    break;
                case 1620409946:
                    if (str.equals(ResultCode.CODE_ERROR_USER_SWITCH)) {
                        this$0.getAuthHelper().quitLoginPage();
                        break;
                    }
                    break;
                case 1620409947:
                    if (str.equals(ResultCode.CODE_ERROR_USER_LOGIN_BTN)) {
                        Intrinsics.checkNotNull(jSONObject);
                        if (!jSONObject.optBoolean("isChecked")) {
                            this$0.toast("同意服务条款才可以登录");
                        }
                        break;
                    }
                    break;
                case 1620409948:
                    if (str.equals(ResultCode.CODE_ERROR_USER_CHECKBOX)) {
                        Intrinsics.checkNotNull(jSONObject);
                        this$0.log("checkbox状态变为" + jSONObject.optBoolean("isChecked"));
                        break;
                    }
                    break;
                case 1620409949:
                    if (str.equals(ResultCode.CODE_ERROR_USER_PROTOCOL_CONTROL)) {
                        Intrinsics.checkNotNull(jSONObject);
                        this$0.log("点击协议，name: " + jSONObject.optString("name") + ", url: " + jSONObject.optString("url"));
                        break;
                    }
                    break;
            }
        }
    }

    public final void login() {
        if (getPhoneLogin()) {
            Repository.INSTANCE.codeLogin(getPhone(), getYzm(), new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.login.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                    invoke2(loginResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoginResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    LoginActivity.this.loginSuccess(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.login.2
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
                    LoginActivity.this.netFail(it);
                }
            });
        } else {
            Repository.INSTANCE.login(getUsername(), getPassword(), new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.login.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                    invoke2(loginResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoginResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    LoginActivity.this.loginSuccess(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.login.4
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
                    LoginActivity.this.netFail(it);
                }
            });
        }
    }

    public final void tokenLogin(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        Repository.INSTANCE.fastLogin(token, new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.tokenLogin.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                invoke2(loginResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginActivity.this.setLoginType("token");
                LoginActivity.this.loginSuccess(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.tokenLogin.2
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
                LoginActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.ll_confirm /* 2131362251 */:
                setCheck(!getCheck());
                break;
            case R.id.tv_fast /* 2131362698 */:
                getAuthHelper().getLoginToken(this, 5000);
                break;
            case R.id.tv_forget /* 2131362706 */:
                startActivity(new Intent(this, (Class<?>) ChangePasswordActivity.class).putExtra("forget", true));
                break;
            case R.id.tv_login /* 2131362721 */:
                if (getCheck()) {
                    login();
                } else {
                    toast("请先阅读并同意用户协议隐私政策");
                }
                break;
            case R.id.tv_privacy /* 2131362751 */:
                Util.openProtocol(this, "隐私政策", "privacyPolicy");
                break;
            case R.id.tv_user /* 2131362804 */:
                Util.openProtocol(this, "用户协议", "userAgreement");
                break;
            case R.id.tv_wx /* 2131362809 */:
                if (getCheck()) {
                    if (getApi().isWXAppInstalled()) {
                        SendAuth.Req req = new SendAuth.Req();
                        req.scope = "snsapi_userinfo";
                        req.state = "28zhe";
                        getApi().sendReq(req);
                    } else {
                        toast("请先安装微信");
                    }
                } else {
                    toast("请先阅读并同意用户协议隐私政策");
                }
                break;
        }
    }

    public final void checkPolicy(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        setAction(action);
        if (getCheck()) {
            action.invoke();
        } else {
            setShowCheck(true);
        }
    }

    public final void wxLogin(WxLoginBean wxLoginBean) {
        Intrinsics.checkNotNullParameter(wxLoginBean, "wxLoginBean");
        Repository.INSTANCE.getWxAccessToken(wxLoginBean.getCode(), new Function1<WxLoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WxLoginResult wxLoginResult) {
                invoke2(wxLoginResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WxLoginResult result1) {
                Intrinsics.checkNotNullParameter(result1, "result1");
                if (result1.getRefresh_token() != null) {
                    Repository repository = Repository.INSTANCE;
                    String refresh_token = result1.getRefresh_token();
                    final LoginActivity loginActivity = LoginActivity.this;
                    Function1<WxLoginResult, Unit> function1 = new Function1<WxLoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WxLoginResult wxLoginResult) {
                            invoke2(wxLoginResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WxLoginResult result2) {
                            Intrinsics.checkNotNullParameter(result2, "result2");
                            if (result2.getAccess_token() != null) {
                                Repository repository2 = Repository.INSTANCE;
                                String access_token = result2.getAccess_token();
                                String openid = result2.getOpenid();
                                Intrinsics.checkNotNull(openid);
                                final LoginActivity loginActivity2 = loginActivity;
                                Function1<WxUserBean, Unit> function12 = new Function1<WxUserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1.1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(WxUserBean wxUserBean) {
                                        invoke2(wxUserBean);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(WxUserBean wxUser) {
                                        Intrinsics.checkNotNullParameter(wxUser, "wxUser");
                                        Repository repository3 = Repository.INSTANCE;
                                        String openid2 = wxUser.getOpenid();
                                        Intrinsics.checkNotNull(openid2);
                                        String nickname = wxUser.getNickname();
                                        Intrinsics.checkNotNull(nickname);
                                        String headimgurl = wxUser.getHeadimgurl();
                                        Intrinsics.checkNotNull(headimgurl);
                                        String unionid = wxUser.getUnionid();
                                        Intrinsics.checkNotNull(unionid);
                                        final LoginActivity loginActivity3 = loginActivity2;
                                        Function1<LoginResult, Unit> function13 = new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1.1.1.1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                                                invoke2(loginResult);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(LoginResult it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                loginActivity3.setLoginType("wx");
                                                loginActivity3.loginSuccess(it);
                                            }
                                        };
                                        final LoginActivity loginActivity4 = loginActivity2;
                                        repository3.wxLogin(openid2, nickname, headimgurl, unionid, function13, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1.1.1.2
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
                                                loginActivity4.netFail(it);
                                            }
                                        });
                                    }
                                };
                                final LoginActivity loginActivity3 = loginActivity;
                                repository2.getWxUser(access_token, openid, function12, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1.1.2
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
                                        loginActivity3.netFail(it);
                                    }
                                });
                                return;
                            }
                            LoginActivity loginActivity4 = loginActivity;
                            String errmsg = result2.getErrmsg();
                            Intrinsics.checkNotNull(errmsg);
                            loginActivity4.toast(errmsg);
                        }
                    };
                    final LoginActivity loginActivity2 = LoginActivity.this;
                    repository.refreshWxAccessToken(refresh_token, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.1.2
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
                            loginActivity2.netFail(it);
                        }
                    });
                    return;
                }
                LoginActivity loginActivity3 = LoginActivity.this;
                String errmsg = result1.getErrmsg();
                Intrinsics.checkNotNull(errmsg);
                loginActivity3.toast(errmsg);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity.wxLogin.2
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
                LoginActivity.this.netFail(it);
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(Constant.INSTANCE.getWxLoginCode())) {
            return;
        }
        wxLogin(new WxLoginBean(Constant.INSTANCE.getWxLoginCode()));
    }

    public final void loginSuccess(LoginResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ContentValues contentValues = new ContentValues();
        contentValues.put("str", it.getToken());
        getContentResolver().insert(Uri.parse("content://com.cy.yyjia.zhe28.loginprovider/login"), contentValues);
        LoginAccount loginAccount = new LoginAccount(getUsername(), getPassword(), System.currentTimeMillis());
        if (getAccountDao().check(getUsername()) != null) {
            getAccountDao().update(loginAccount);
        } else {
            getAccountDao().add(loginAccount);
        }
        final Constant constant = Constant.INSTANCE;
        constant.setWxLoginCode("");
        setCookie(it);
        constant.setLogged(true);
        constant.setShowMainAd(true);
        constant.setToken(it.getToken());
        constant.setUsername(it.getUser().getUserName());
        constant.setId(it.getUser().getUid());
        constant.setCookieString(it.getCookie().toString());
        Integer unset_status = it.getUser().getUnset_status();
        if (unset_status != null && unset_status.intValue() == 0) {
            EventBus.getDefault().post(new LoginChangeBean());
            SharedPreferences.Editor editorEdit = getSharedPreferences("user", 0).edit();
            editorEdit.putBoolean("logged", true);
            editorEdit.putInt("id", constant.getId());
            editorEdit.putString(HintConstants.AUTOFILL_HINT_USERNAME, constant.getUsername());
            editorEdit.putString(HintConstants.AUTOFILL_HINT_PASSWORD, getPassword());
            editorEdit.putString("token", constant.getToken());
            editorEdit.putString(SerializableCookie.COOKIE, constant.getCookieString());
            editorEdit.putString("loginType", this.loginType);
            editorEdit.putString(HintConstants.AUTOFILL_HINT_PHONE, it.getUser().getTelphone());
            editorEdit.putString("idCard", it.getUser().getIdCard());
            editorEdit.putString("realName", it.getUser().getRealName());
            editorEdit.putString("age", it.getUser().getAge());
            editorEdit.commit();
            if (getIntent().getStringExtra("url") != null) {
                Util.openWebWithLogin(getMContext(), getIntent().getStringExtra("title"), getIntent().getStringExtra("url"));
            }
            String stringExtra = getIntent().getStringExtra("next");
            if (stringExtra != null) {
                startActivity(new Intent().setComponent(new ComponentName(BuildConfig.APPLICATION_ID, stringExtra)));
            }
            setResult(1001, new Intent().putExtra("token", constant.getToken()));
            finish();
            return;
        }
        Integer unset_status2 = it.getUser().getUnset_status();
        if (unset_status2 != null && unset_status2.intValue() == 1) {
            new FastDialog(getMContext()).setContentView(R.layout.dialog_cancellation_tips).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$$ExternalSyntheticLambda1
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    LoginActivity.loginSuccess$lambda$5$lambda$2(this.f$0, baseDialog, view);
                }
            }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$$ExternalSyntheticLambda2
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    baseDialog.dismiss();
                }
            }).addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
                public final void onDismiss(BaseDialog baseDialog) {
                    LoginActivity.loginSuccess$lambda$5$lambda$4(constant, this, baseDialog);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSuccess$lambda$5$lambda$2(final LoginActivity this$0, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.cancelUnsetAccount(new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$loginSuccess$1$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result result) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.this$0.toast(result.getMsg());
                if (result.getCode() == 200) {
                    baseDialog.dismiss();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$loginSuccess$1$2$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                this.this$0.netFail(e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSuccess$lambda$5$lambda$4(Constant this_run, LoginActivity this$0, BaseDialog baseDialog) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_run.logout(this$0.getMContext());
    }

    public final void setCookie(LoginResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        for (LoginResult.CookieBean cookieBean : result.getCookie()) {
            final String str = StringsKt.trim((CharSequence) cookieBean.getName()).toString() + "=" + StringsKt.trim((CharSequence) cookieBean.getValue()).toString();
            cookieManager.setCookie(NetUtil.BASE_URL3, str, new ValueCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$$ExternalSyntheticLambda4
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    LoginActivity.setCookie$lambda$6(this.f$0, str, (Boolean) obj);
                }
            });
        }
        cookieManager.setCookie(NetUtil.BASE_URL3, "payversion=2");
        cookieManager.setCookie(NetUtil.BASE_URL3, "wancms=" + new Gson().toJson(result));
        cookieManager.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setCookie$lambda$6(LoginActivity this$0, String cookie, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cookie, "$cookie");
        this$0.log("setCookie:" + cookie + "  " + bool);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        setResult(7892);
    }
}
