package com.cy.yyjia.zhe28.base;

import android.R;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.mobile.auth.BuildConfig;
import com.mobile.auth.gatewayauth.Constant;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\"\u001a\u00020#J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0005H\u0016J\u0014\u0010%\u001a\u00020\u00122\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u000e\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020)J\b\u0010*\u001a\u00020\u0005H\u0016J\b\u0010+\u001a\u00020\u0012H\u0016J\b\u0010,\u001a\u00020\u0012H\u0016J\u0018\u0010,\u001a\u00020\u00122\b\b\u0001\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020#J\u0016\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020#J\b\u0010/\u001a\u00020\u0012H&J(\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020#H\u0016J6\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\f\u00107\u001a\b\u0012\u0004\u0012\u00020\t082\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020#H\u0016J\u000e\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\tJ\u0012\u0010;\u001a\u00020\u00122\n\u0010<\u001a\u00060=j\u0002`>J\"\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020\u00052\u0006\u0010A\u001a\u00020\u00052\b\u0010B\u001a\u0004\u0018\u00010CH\u0014J\u0012\u0010D\u001a\u00020\u00122\b\u0010E\u001a\u0004\u0018\u00010FH\u0014J\u001a\u0010G\u001a\u00020\u00122\u0006\u0010H\u001a\u00020)2\b\b\u0002\u0010I\u001a\u00020#H\u0016J\u0012\u0010J\u001a\u00020\u00122\n\u0010K\u001a\u0006\u0012\u0002\b\u00030LJ\u000e\u0010M\u001a\u00020\u00122\u0006\u0010:\u001a\u00020NJ\u0016\u0010M\u001a\u00020\u00122\u0006\u0010:\u001a\u00020N2\u0006\u0010M\u001a\u00020\tJ9\u0010M\u001a\u00020\u00122\u0006\u0010:\u001a\u00020N2\u0006\u0010M\u001a\u00020\t2!\u0010O\u001a\u001d\u0012\u0013\u0012\u00110)¢\u0006\f\bQ\u0012\b\bR\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00120PJ\u0006\u0010S\u001a\u00020\u0012J\u0014\u0010S\u001a\u00020\u00122\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u000e\u0010U\u001a\u00020\u00122\u0006\u0010:\u001a\u00020NJ\u000e\u0010U\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\tR\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u00028\u0000X\u0094.¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u0003X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006V"}, d2 = {"Lcom/cy/yyjia/zhe28/base/BaseActivity;", "DB", "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/FragmentActivity;", "layoutId", "", "imm", "(II)V", "TAG", "", "clickTime", "", "getClickTime", "()J", "setClickTime", "(J)V", "loginSuccessListener", "Lkotlin/Function0;", "", "getLoginSuccessListener", "()Lkotlin/jvm/functions/Function0;", "setLoginSuccessListener", "(Lkotlin/jvm/functions/Function0;)V", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "mContext", "getMContext", "()Landroidx/fragment/app/FragmentActivity;", "setMContext", "(Landroidx/fragment/app/FragmentActivity;)V", "checkClick", "", "sec", "doWithLogin", "action", "finish", "v", "Landroid/view/View;", "getStatusBarHeight", "hideSoftKeyboard", "immersionBar", "color", "dark", "init", "initTab", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "textSize", "", "textSelectSize", "bold", "titles", "", BuildConfig.FLAVOR_type, "text", "netFail", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setViewFitsSystemWindows", "view", "padding", "startActivity", "cls", "Ljava/lang/Class;", "tip", "", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "toLogin", "success", "toast", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class BaseActivity<DB extends ViewDataBinding> extends FragmentActivity {
    public static final int $stable = 8;
    private String TAG;
    private long clickTime;
    private final int imm;
    private final int layoutId;
    private Function0<Unit> loginSuccessListener;
    protected DB mBinding;
    protected FragmentActivity mContext;

    public abstract void init();

    public /* synthetic */ BaseActivity(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? 0 : i2);
    }

    public BaseActivity(int i, int i2) {
        this.layoutId = i;
        this.imm = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final FragmentActivity getMContext() {
        FragmentActivity fragmentActivity = this.mContext;
        if (fragmentActivity != null) {
            return fragmentActivity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    protected final void setMContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.mContext = fragmentActivity;
    }

    protected DB getMBinding() {
        DB db = this.mBinding;
        if (db != null) {
            return db;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        return null;
    }

    protected void setMBinding(DB db) {
        Intrinsics.checkNotNullParameter(db, "<set-?>");
        this.mBinding = db;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String localClassName = getLocalClassName();
        Intrinsics.checkNotNullExpressionValue(localClassName, "getLocalClassName(...)");
        this.TAG = localClassName;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (displayMetrics.density < 2.0f) {
            double d = 2.75d / ((double) displayMetrics.density);
            displayMetrics.density = (float) (((double) displayMetrics.density) * d);
            displayMetrics.scaledDensity = (float) (((double) displayMetrics.scaledDensity) * d);
            displayMetrics.densityDpi = (int) (((double) displayMetrics.densityDpi) * d);
            Configuration configuration = new Configuration();
            configuration.densityDpi = displayMetrics.densityDpi;
            getResources().updateConfiguration(configuration, displayMetrics);
        }
        setMContext(this);
        ViewDataBinding contentView = DataBindingUtil.setContentView(getMContext(), this.layoutId);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setMBinding(contentView);
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.base.BaseActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
        int i = this.imm;
        if (i == 1) {
            immersionBar();
        } else if (i == 2) {
            immersionBar(com.cy.yyjia.zhe28.R.color.transparent, false);
        }
        init();
        if (NetUtil.INSTANCE.isConnected(this)) {
            return;
        }
        setContentView(com.cy.yyjia.zhe28.R.layout.layout_net_error);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(BaseActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideSoftKeyboard();
    }

    public final void log(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String str = this.TAG;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("TAG");
            str = null;
        }
        Log.e(str, text);
    }

    public final void toast(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Toast.makeText(getMContext(), text, 0).show();
    }

    public final void toast(CharSequence text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Toast.makeText(getMContext(), text, 0).show();
    }

    public final void tip(CharSequence text) {
        Intrinsics.checkNotNullParameter(text, "text");
        tip(text, "我知道了");
    }

    public final void tip(CharSequence text, String tip) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(tip, "tip");
        tip(text, tip, new Function1<View, Unit>() { // from class: com.cy.yyjia.zhe28.base.BaseActivity.tip.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }
        });
    }

    public final void tip(CharSequence text, String tip, final Function1<? super View, Unit> listener) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(tip, "tip");
        Intrinsics.checkNotNullParameter(listener, "listener");
        hideSoftKeyboard();
        Snackbar.make(getMBinding().getRoot(), text, -2).setAction(tip, new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.base.BaseActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseActivity.tip$lambda$1(listener, view);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tip$lambda$1(Function1 listener, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNull(view);
        listener.invoke(view);
    }

    public final long getClickTime() {
        return this.clickTime;
    }

    public final void setClickTime(long j) {
        this.clickTime = j;
    }

    public boolean checkClick(int sec) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.clickTime < sec * 1000) {
            log(sec + "秒内频繁点击");
            return true;
        }
        this.clickTime = jCurrentTimeMillis;
        return false;
    }

    public final boolean checkClick() {
        return checkClick(2);
    }

    public final void startActivity(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "cls");
        startActivity(new Intent(getMContext(), cls));
    }

    public void immersionBar() {
        ImmersionBar.with(getMContext()).statusBarColor(com.cy.yyjia.zhe28.R.color.transparent).statusBarDarkFont(true).init();
    }

    public final void immersionBar(int color, boolean dark) {
        ImmersionBar.with(getMContext()).statusBarColor(color).statusBarDarkFont(dark).init();
    }

    public final void immersionBar(String color, boolean dark) {
        Intrinsics.checkNotNullParameter(color, "color");
        ImmersionBar.with(getMContext()).statusBarColor(color).statusBarDarkFont(dark).init();
    }

    public final void netFail(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        String localizedMessage = e.getLocalizedMessage();
        Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
        toast(localizedMessage);
        String localizedMessage2 = e.getLocalizedMessage();
        Intrinsics.checkNotNullExpressionValue(localizedMessage2, "getLocalizedMessage(...)");
        log(localizedMessage2);
    }

    public void hideSoftKeyboard() {
        View root = getMBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(root.getWindowToken(), 0);
        }
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", PodInfo.GAME_TYPE_ANDROID);
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static /* synthetic */ void setViewFitsSystemWindows$default(BaseActivity baseActivity, View view, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setViewFitsSystemWindows");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        baseActivity.setViewFitsSystemWindows(view, z);
    }

    public void setViewFitsSystemWindows(View view, boolean padding) {
        FrameLayout.LayoutParams layoutParams;
        Intrinsics.checkNotNullParameter(view, "view");
        ViewParent parent = view.getParent();
        if (parent instanceof LinearLayout) {
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            layoutParams = (LinearLayout.LayoutParams) layoutParams2;
        } else if (parent instanceof RelativeLayout) {
            ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            layoutParams = (RelativeLayout.LayoutParams) layoutParams3;
        } else if (parent instanceof ConstraintLayout) {
            ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams4, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            layoutParams = (ConstraintLayout.LayoutParams) layoutParams4;
        } else {
            ViewGroup.LayoutParams layoutParams5 = view.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams5, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            layoutParams = (FrameLayout.LayoutParams) layoutParams5;
        }
        if (padding) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(), view.getPaddingBottom(), view.getPaddingRight());
        } else {
            layoutParams.topMargin += getStatusBarHeight();
        }
        view.setLayoutParams(layoutParams);
    }

    public final Function0<Unit> getLoginSuccessListener() {
        return this.loginSuccessListener;
    }

    public final void setLoginSuccessListener(Function0<Unit> function0) {
        this.loginSuccessListener = function0;
    }

    public final void toLogin(Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        this.loginSuccessListener = success;
        startActivityForResult(new Intent(this, (Class<?>) LoginActivity.class), com.cy.yyjia.zhe28.util.Constant.LOGIN_REQUEST_CODE);
    }

    public final void toLogin() {
        if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged()) {
            return;
        }
        startActivityForResult(new Intent(this, (Class<?>) LoginActivity.class), com.cy.yyjia.zhe28.util.Constant.LOGIN_REQUEST_CODE);
    }

    public void initTab(TabLayout tabLayout, float textSize, float textSelectSize, boolean bold) {
        Intrinsics.checkNotNullParameter(tabLayout, "tabLayout");
        ArrayList arrayList = new ArrayList();
        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
            Intrinsics.checkNotNull(tabAt);
            arrayList.add(String.valueOf(tabAt.getText()));
        }
        initTab(tabLayout, arrayList, textSize, textSelectSize, bold);
    }

    public void initTab(TabLayout tabLayout, List<String> titles, final float textSize, final float textSelectSize, final boolean bold) {
        Intrinsics.checkNotNullParameter(tabLayout, "tabLayout");
        Intrinsics.checkNotNullParameter(titles, "titles");
        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(com.cy.yyjia.zhe28.R.layout.layout_tab_text);
                View customView = tabAt.getCustomView();
                if (customView != null) {
                    TextView textView = (TextView) customView.findViewById(com.cy.yyjia.zhe28.R.id.f438tv);
                    textView.setText(titles.get(i));
                    textView.setTextColor(tabLayout.getTabTextColors());
                    if (bold && i == 0) {
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setTextSize(1, textSelectSize);
                    } else {
                        textView.setTypeface(Typeface.DEFAULT);
                        textView.setTextSize(1, textSize);
                    }
                }
            }
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.base.BaseActivity.initTab.2
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView2 = tab.getCustomView();
                if (customView2 != null) {
                    float f = textSelectSize;
                    boolean z = bold;
                    TextView textView2 = (TextView) customView2.findViewById(com.cy.yyjia.zhe28.R.id.f438tv);
                    textView2.setTextSize(1, f);
                    if (z) {
                        textView2.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView2 = tab.getCustomView();
                if (customView2 != null) {
                    float f = textSize;
                    boolean z = bold;
                    TextView textView2 = (TextView) customView2.findViewById(com.cy.yyjia.zhe28.R.id.f438tv);
                    textView2.setTextSize(1, f);
                    if (z) {
                        textView2.setTypeface(Typeface.DEFAULT);
                    }
                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView2 = tab.getCustomView();
                if (customView2 != null) {
                    float f = textSelectSize;
                    boolean z = bold;
                    TextView textView2 = (TextView) customView2.findViewById(com.cy.yyjia.zhe28.R.id.f438tv);
                    textView2.setTextSize(1, f);
                    if (z) {
                        textView2.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Function0<Unit> function0;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 1001 || (function0 = this.loginSuccessListener) == null) {
            return;
        }
        function0.invoke();
    }

    public final void doWithLogin(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged()) {
            action.invoke();
        } else {
            startActivity(LoginActivity.class);
        }
    }

    public final void finish(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        finish();
    }
}
