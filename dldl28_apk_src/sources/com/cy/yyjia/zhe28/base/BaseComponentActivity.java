package com.cy.yyjia.zhe28.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseComponentActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\b'\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0016J\u0014\u0010\u001d\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\b\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020\u000fH\u0016J\b\u0010!\u001a\u00020\u000fH\u0016J\u0018\u0010!\u001a\u00020\u000f2\b\b\u0001\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u001bJ\u0016\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u001bJ\b\u0010$\u001a\u00020\u000fH&J(\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u001bH\u0016J6\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020'2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060-2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u001bH\u0016J\b\u0010.\u001a\u00020\u000fH&J\u000e\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u0006J\u0012\u00101\u001a\u00020\u000f2\n\u00102\u001a\u000603j\u0002`4J\"\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\b\u00108\u001a\u0004\u0018\u000109H\u0014J\u0012\u0010:\u001a\u00020\u000f2\b\u0010;\u001a\u0004\u0018\u00010<H\u0014J\u0010\u0010=\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020?H\u0016J\u0012\u0010@\u001a\u00020\u000f2\n\u0010A\u001a\u0006\u0012\u0002\b\u00030BJ\u0006\u0010C\u001a\u00020\u000fJ\u0014\u0010C\u001a\u00020\u000f2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u000e\u0010E\u001a\u00020\u000f2\u0006\u00100\u001a\u00020FJ\u000e\u0010E\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006G"}, d2 = {"Lcom/cy/yyjia/zhe28/base/BaseComponentActivity;", "Landroidx/activity/ComponentActivity;", "imm", "", "(I)V", "TAG", "", "clickTime", "", "getClickTime", "()J", "setClickTime", "(J)V", "loginSuccessListener", "Lkotlin/Function0;", "", "getLoginSuccessListener", "()Lkotlin/jvm/functions/Function0;", "setLoginSuccessListener", "(Lkotlin/jvm/functions/Function0;)V", "mContext", "Landroid/app/Activity;", "getMContext", "()Landroid/app/Activity;", "setMContext", "(Landroid/app/Activity;)V", "checkClick", "", "sec", "doWithLogin", "action", "getStatusBarHeight", "hideSoftKeyboard", "immersionBar", "color", "dark", "init", "initTab", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "textSize", "", "textSelectSize", "bold", "titles", "", "initView", BuildConfig.FLAVOR_type, "text", "netFail", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setViewFitsSystemWindows", "view", "Landroid/view/View;", "startActivity", "cls", "Ljava/lang/Class;", "toLogin", "success", "toast", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class BaseComponentActivity extends ComponentActivity {
    public static final int $stable = 8;
    private String TAG;
    private long clickTime;
    private final int imm;
    private Function0<Unit> loginSuccessListener;
    protected Activity mContext;

    public BaseComponentActivity() {
        this(0, 1, null);
    }

    public abstract void init();

    public abstract void initView();

    public /* synthetic */ BaseComponentActivity(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public BaseComponentActivity(int i) {
        this.imm = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Activity getMContext() {
        Activity activity = this.mContext;
        if (activity != null) {
            return activity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    protected final void setMContext(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.mContext = activity;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMContext(this);
        String localClassName = getLocalClassName();
        Intrinsics.checkNotNullExpressionValue(localClassName, "getLocalClassName(...)");
        this.TAG = localClassName;
        int i = this.imm;
        if (i == 1) {
            EdgeToEdge.enable$default(this, null, null, 3, null);
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(true);
        } else if (i == 2) {
            EdgeToEdge.enable$default(this, null, null, 3, null);
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);
        } else if (i == -1) {
            getWindow().setFlags(1024, 1024);
            EdgeToEdge.enable$default(this, null, null, 3, null);
        }
        init();
        initView();
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
        ImmersionBar.with(getMContext()).statusBarColor(R.color.transparent).statusBarDarkFont(true).init();
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
        InputMethodManager inputMethodManager;
        View currentFocus = getCurrentFocus();
        if (currentFocus == null || (inputMethodManager = (InputMethodManager) getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", PodInfo.GAME_TYPE_ANDROID);
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public void setViewFitsSystemWindows(View view) {
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
        layoutParams.topMargin += getStatusBarHeight();
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
                tabAt.setCustomView(R.layout.layout_tab_text);
                View customView = tabAt.getCustomView();
                if (customView != null) {
                    TextView textView = (TextView) customView.findViewById(R.id.f438tv);
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
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.base.BaseComponentActivity.initTab.2
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView2 = tab.getCustomView();
                if (customView2 != null) {
                    float f = textSelectSize;
                    boolean z = bold;
                    TextView textView2 = (TextView) customView2.findViewById(R.id.f438tv);
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
                    TextView textView2 = (TextView) customView2.findViewById(R.id.f438tv);
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
                    TextView textView2 = (TextView) customView2.findViewById(R.id.f438tv);
                    textView2.setTextSize(1, f);
                    if (z) {
                        textView2.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
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
}
