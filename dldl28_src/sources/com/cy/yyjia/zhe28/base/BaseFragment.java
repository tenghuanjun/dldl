package com.cy.yyjia.zhe28.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.databinding.FragmentBaseBinding;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.util.Constant;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.mobile.auth.BuildConfig;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010,\u001a\u00020-J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0005H\u0016J\u0014\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020002J\b\u00103\u001a\u00020\u0005H\u0016J\b\u00104\u001a\u000200H\u0016J\b\u00105\u001a\u000200H&J(\u00106\u001a\u0002002\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020-H\u0016J6\u00106\u001a\u0002002\u0006\u00107\u001a\u0002082\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0>2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020-H\u0016J\u000e\u0010?\u001a\u0002002\u0006\u0010@\u001a\u00020\bJ\u0012\u0010A\u001a\u0002002\n\u0010B\u001a\u00060Cj\u0002`DJ\u0010\u0010E\u001a\u0002002\u0006\u0010F\u001a\u00020GH\u0016J\u0012\u0010H\u001a\u0002002\b\u0010I\u001a\u0004\u0018\u00010JH\u0016J&\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010P2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016J\u001a\u0010Q\u001a\u0002002\u0006\u0010R\u001a\u00020L2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016J\u0010\u0010S\u001a\u0002002\u0006\u0010R\u001a\u00020LH\u0016J\u0012\u0010T\u001a\u0002002\n\u0010U\u001a\u0006\u0012\u0002\b\u00030VJ\u000e\u0010W\u001a\u0002002\u0006\u0010@\u001a\u00020XJ\u0016\u0010W\u001a\u0002002\u0006\u0010@\u001a\u00020X2\u0006\u0010W\u001a\u00020\bJ9\u0010W\u001a\u0002002\u0006\u0010@\u001a\u00020X2\u0006\u0010W\u001a\u00020\b2!\u0010Y\u001a\u001d\u0012\u0013\u0012\u00110L¢\u0006\f\b[\u0012\b\b\\\u0012\u0004\b\b(]\u0012\u0004\u0012\u0002000ZJ\u000e\u0010^\u001a\u0002002\u0006\u0010@\u001a\u00020XJ\u000e\u0010^\u001a\u0002002\u0006\u0010@\u001a\u00020\bR\u001a\u0010\u0007\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u00028\u0000X\u0084.¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R \u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006_"}, d2 = {"Lcom/cy/yyjia/zhe28/base/BaseFragment;", "DB", "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/Fragment;", "layoutId", "", "(I)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "bBinding", "Lcom/cy/yyjia/zhe28/databinding/FragmentBaseBinding;", "getBBinding", "()Lcom/cy/yyjia/zhe28/databinding/FragmentBaseBinding;", "setBBinding", "(Lcom/cy/yyjia/zhe28/databinding/FragmentBaseBinding;)V", "clickTime", "", "getClickTime", "()J", "setClickTime", "(J)V", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "mContext", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "getMContext", "()Lcom/cy/yyjia/zhe28/base/BaseActivity;", "setMContext", "(Lcom/cy/yyjia/zhe28/base/BaseActivity;)V", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getResultLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setResultLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "checkClick", "", "sec", "doWithLogin", "", "action", "Lkotlin/Function0;", "getStatusBarHeight", "hideSoftKeyboard", "init", "initTab", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "textSize", "", "textSelectSize", "bold", "titles", "", BuildConfig.FLAVOR_type, "text", "netFail", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setViewFitsSystemWindows", "startActivity", "cls", "Ljava/lang/Class;", "tip", "", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "v", "toast", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {
    public static final int $stable = 8;
    private String TAG;
    protected FragmentBaseBinding bBinding;
    private long clickTime;
    private final int layoutId;
    protected DB mBinding;
    protected BaseActivity<?> mContext;
    public ActivityResultLauncher<Intent> resultLauncher;

    public abstract void init();

    public BaseFragment(int i) {
        super(i);
        this.layoutId = i;
        this.TAG = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BaseActivity<?> getMContext() {
        BaseActivity<?> baseActivity = this.mContext;
        if (baseActivity != null) {
            return baseActivity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    protected final void setMContext(BaseActivity<?> baseActivity) {
        Intrinsics.checkNotNullParameter(baseActivity, "<set-?>");
        this.mContext = baseActivity;
    }

    protected final String getTAG() {
        return this.TAG;
    }

    protected final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DB getMBinding() {
        DB db = this.mBinding;
        if (db != null) {
            return db;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        return null;
    }

    protected final void setMBinding(DB db) {
        Intrinsics.checkNotNullParameter(db, "<set-?>");
        this.mBinding = db;
    }

    protected final FragmentBaseBinding getBBinding() {
        FragmentBaseBinding fragmentBaseBinding = this.bBinding;
        if (fragmentBaseBinding != null) {
            return fragmentBaseBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bBinding");
        return null;
    }

    protected final void setBBinding(FragmentBaseBinding fragmentBaseBinding) {
        Intrinsics.checkNotNullParameter(fragmentBaseBinding, "<set-?>");
        this.bBinding = fragmentBaseBinding;
    }

    public final ActivityResultLauncher<Intent> getResultLauncher() {
        ActivityResultLauncher<Intent> activityResultLauncher = this.resultLauncher;
        if (activityResultLauncher != null) {
            return activityResultLauncher;
        }
        Intrinsics.throwUninitializedPropertyAccessException("resultLauncher");
        return null;
    }

    public final void setResultLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.resultLauncher = activityResultLauncher;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        this.TAG = name;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        setBBinding((FragmentBaseBinding) viewDataBindingInflate);
        return getBBinding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ViewStub viewStub = getBBinding().vs.getViewStub();
        Intrinsics.checkNotNull(viewStub);
        viewStub.setLayoutResource(this.layoutId);
        ViewStub viewStub2 = getBBinding().vs.getViewStub();
        Intrinsics.checkNotNull(viewStub2);
        ViewDataBinding viewDataBindingBind = DataBindingUtil.bind(viewStub2.inflate());
        Intrinsics.checkNotNull(viewDataBindingBind);
        setMBinding(viewDataBindingBind);
        init();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        log("attach");
        setMContext((BaseActivity) context);
    }

    public final void log(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Log.e(this.TAG, text);
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
        tip(text, tip, new Function1<View, Unit>() { // from class: com.cy.yyjia.zhe28.base.BaseFragment.tip.1
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
        Snackbar.make(getMBinding().getRoot(), text, -2).setAction(tip, new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.base.BaseFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseFragment.tip$lambda$0(listener, view);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tip$lambda$0(Function1 listener, View view) {
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
        InputMethodManager inputMethodManager = (InputMethodManager) getMContext().getSystemService("input_method");
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

    public final void doWithLogin(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (Constant.INSTANCE.getLogged()) {
            action.invoke();
        } else {
            startActivity(LoginActivity.class);
        }
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
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.base.BaseFragment.initTab.2
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
}
