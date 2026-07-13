package com.cy.yyjia.zhe28.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityMainBinding;
import com.cy.yyjia.zhe28.domain.LoginResult;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.SdkJumpBean;
import com.cy.yyjia.zhe28.domain.SlideBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WxLoginBean;
import com.cy.yyjia.zhe28.domain.WxLoginResult;
import com.cy.yyjia.zhe28.domain.WxUserBean;
import com.cy.yyjia.zhe28.ui.activity.CardActivity;
import com.cy.yyjia.zhe28.ui.activity.DailyTaskActivity;
import com.cy.yyjia.zhe28.ui.activity.DealDetailActivity;
import com.cy.yyjia.zhe28.ui.activity.DealSellActivity;
import com.cy.yyjia.zhe28.ui.activity.EventDetailActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthlyTaskActivity;
import com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity;
import com.cy.yyjia.zhe28.ui.activity.QuickLoginActivity;
import com.cy.yyjia.zhe28.ui.activity.RecycleActivity;
import com.cy.yyjia.zhe28.ui.dialog.HomeDialog;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.cy.yyjia.zhe28.ui.fragment.BbsIndexFragment;
import com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment;
import com.cy.yyjia.zhe28.ui.fragment.MainFragment;
import com.cy.yyjia.zhe28.ui.fragment.UserFragment;
import com.cy.yyjia.zhe28.ui.fragment.WelfareFragment3;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.SPUtil;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.gson.Gson;
import com.lzy.okgo.cookie.SerializableCookie;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.Calendar;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0016J\u0006\u00102\u001a\u00020-J\u0006\u00103\u001a\u00020-J\u0006\u00104\u001a\u00020-J\b\u00105\u001a\u00020-H\u0016J\u0006\u00106\u001a\u00020-J\"\u00107\u001a\u00020-2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002092\b\u0010;\u001a\u0004\u0018\u00010<H\u0014J\u0010\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020?H\u0016J\u0018\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u0002092\u0006\u0010B\u001a\u00020CH\u0016J\b\u0010D\u001a\u00020-H\u0014J\u000e\u0010E\u001a\u00020-2\u0006\u0010F\u001a\u000209J\u0006\u0010G\u001a\u00020-J\u000e\u0010H\u001a\u00020-2\u0006\u0010I\u001a\u00020\fJ\u000e\u0010J\u001a\u00020-2\u0006\u0010K\u001a\u00020LR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001a\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001b\u0010\u0017R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0019\u001a\u0004\b\"\u0010#R\u001b\u0010%\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0019\u001a\u0004\b&\u0010\u0017R\u001a\u0010(\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0017\"\u0004\b*\u0010+¨\u0006M"}, d2 = {"Lcom/cy/yyjia/zhe28/activity/MainActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityMainBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "getAdapter", "()Landroidx/viewpager2/adapter/FragmentStateAdapter;", "exitTime", "", "pkgName", "", "getPkgName", "()Ljava/lang/String;", "setPkgName", "(Ljava/lang/String;)V", "sdkVersion", "getSdkVersion", "setSdkVersion", "servicePosition1", "", "getServicePosition1", "()F", "servicePosition1$delegate", "Lkotlin/Lazy;", "servicePosition2", "getServicePosition2", "servicePosition2$delegate", "type", "getType", "setType", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "width", "getWidth", "width$delegate", "y", "getY", "setY", "(F)V", "dealSDK", "", "dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "getHomeDialog", "getUserInfo", "hideService", "init", "initTheme", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onResume", "select", ImageSelector.POSITION, "showService", "unreadNumber", "num", "wxLogin", "wxLoginBean", "Lcom/cy/yyjia/zhe28/domain/WxLoginBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final FragmentStateAdapter adapter;
    private long exitTime;
    private String pkgName;
    private String sdkVersion;

    /* JADX INFO: renamed from: servicePosition1$delegate, reason: from kotlin metadata */
    private final Lazy servicePosition1;

    /* JADX INFO: renamed from: servicePosition2$delegate, reason: from kotlin metadata */
    private final Lazy servicePosition2;
    private String type;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    /* JADX INFO: renamed from: width$delegate, reason: from kotlin metadata */
    private final Lazy width;
    private float y;

    public MainActivity() {
        super(R.layout.activity_main, 1);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0).get(MainViewModel.class);
            }
        });
        this.type = "";
        this.sdkVersion = "";
        this.pkgName = "";
        this.adapter = new FragmentStateAdapter(this) { // from class: com.cy.yyjia.zhe28.activity.MainActivity$adapter$1
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return 5;
            }

            {
                super(this);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                if (position == 1) {
                    return new BbsIndexFragment();
                }
                if (position == 2) {
                    return new WelfareFragment3();
                }
                if (position == 3) {
                    return new DealIndexFragment();
                }
                if (position == 4) {
                    return new UserFragment();
                }
                return new MainFragment();
            }
        };
        this.width = LazyKt.lazy(new Function0<Float>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity$width$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(Util.getWidth(this.this$0));
            }
        });
        this.servicePosition1 = LazyKt.lazy(new Function0<Float>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity$servicePosition1$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getWidth() - Util.dpToPx(this.this$0, 71.0f));
            }
        });
        this.servicePosition2 = LazyKt.lazy(new Function0<Float>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity$servicePosition2$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getWidth() - Util.dpToPx(this.this$0, 32.0f));
            }
        });
    }

    public static final /* synthetic */ ActivityMainBinding access$getMBinding(MainActivity mainActivity) {
        return mainActivity.getMBinding();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public final void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public final String getPkgName() {
        return this.pkgName;
    }

    public final void setPkgName(String str) {
        this.pkgName = str;
    }

    public final FragmentStateAdapter getAdapter() {
        return this.adapter;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        initTheme();
        log("开始初始化");
        log("username:" + com.cy.yyjia.zhe28.util.Constant.INSTANCE.getUsername() + "\ttoken:" + com.cy.yyjia.zhe28.util.Constant.INSTANCE.getToken());
        getMBinding().setPosition(0);
        getMBinding().vp.setUserInputEnabled(false);
        getMBinding().vp.setAdapter(this.adapter);
        getHomeDialog();
        getVm().getMainTabPosition().setValue(0);
        MainActivity mainActivity = this;
        getVm().getMainTabPosition().observe(mainActivity, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.select(MainActivity.access$getMBinding(mainActivity2).getPosition());
            }
        }));
        getVm().getHomeFun().observe(mainActivity, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                if (Intrinsics.areEqual(str, "福利中心")) {
                    MainActivity.this.select(2);
                }
            }
        }));
        log("看看cookie：" + CookieManager.getInstance().getCookie(NetUtil.BASE_URL3));
        log("看看cookieString：" + com.cy.yyjia.zhe28.util.Constant.INSTANCE.getCookieString());
        getMBinding().ivService.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.activity.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.init$lambda$0(this.f$0, view);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("first", 0);
        if (sharedPreferences.getBoolean("firstGame", true) && com.cy.yyjia.zhe28.util.Constant.INSTANCE.getFirstGameId() != 0) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean("firstGame", false);
            editorEdit.commit();
            Util.gotoGame(this, com.cy.yyjia.zhe28.util.Constant.INSTANCE.getFirstGameId());
        }
        select(getIntent().getIntExtra(ImageSelector.POSITION, 0));
        log("初始化结束");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showService();
        Util.toService(this$0.getMContext());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                if (getMBinding().getPosition() != 0) {
                    select(0);
                } else {
                    getVm().getAction().setValue(-1);
                }
                break;
            case R.id.tv2 /* 2131362646 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.onClick.1
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
                        MainActivity.this.select(1);
                    }
                });
                break;
            case R.id.tv3 /* 2131362647 */:
                select(2);
                break;
            case R.id.tv4 /* 2131362648 */:
                select(3);
                break;
            case R.id.tv5 /* 2131362649 */:
                select(4);
                break;
        }
    }

    public final void select(int position) {
        log("select position = " + position);
        if (position == 0) {
            Integer value = getVm().getMainTabPosition().getValue();
            Intrinsics.checkNotNull(value);
            if (value.intValue() == 3) {
                immersionBar(R.color.transparent, false);
            }
        } else {
            immersionBar();
        }
        getMBinding().setPosition(position);
        LinearLayout linearLayout = getMBinding().llHome;
        Integer value2 = getVm().getMainTabPosition().getValue();
        Intrinsics.checkNotNull(value2);
        linearLayout.setVisibility(value2.intValue() + position == 0 ? 0 : 8);
        getMBinding().vp.setCurrentItem(position, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        log("onResume");
        if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getShowMainAd()) {
            com.cy.yyjia.zhe28.util.Constant.INSTANCE.setShowMainAd(false);
            Repository.INSTANCE.getHomeDialog(new Function1<List<SlideBean>, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.onResume.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<SlideBean> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<SlideBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (it.size() > 0) {
                        new HomeDialog(MainActivity.this, it).show();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.onResume.2
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
                    MainActivity mainActivity = MainActivity.this;
                    String localizedMessage = it.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    mainActivity.log(localizedMessage);
                }
            });
        }
        com.cy.yyjia.zhe28.util.Constant.INSTANCE.setCancellation(false);
        getUserInfo();
        if (!TextUtils.isEmpty(com.cy.yyjia.zhe28.util.Constant.INSTANCE.getWxLoginCode())) {
            wxLogin(new WxLoginBean(com.cy.yyjia.zhe28.util.Constant.INSTANCE.getWxLoginCode()));
        } else {
            dealSDK();
        }
    }

    public final void getHomeDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        if (calendar.getTimeInMillis() - ((long) 259200000) > getSharedPreferences("lastAdTime", 0).getLong("time", 0L)) {
            Repository.INSTANCE.getHomeDialog(new Function1<List<SlideBean>, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.getHomeDialog.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<SlideBean> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<SlideBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (it.size() > 0) {
                        new HomeDialog(MainActivity.this, it).show();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.getHomeDialog.2
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
                    MainActivity mainActivity = MainActivity.this;
                    String localizedMessage = it.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    mainActivity.log(localizedMessage);
                }
            });
        } else {
            log("ad time：看过了");
        }
    }

    public final void getUserInfo() {
        if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.getUserInfo.1
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
                    MainActivity.this.getVm().getUser().postValue(it);
                    com.cy.yyjia.zhe28.util.Constant.INSTANCE.setNickname(it.getNickName());
                    try {
                        com.cy.yyjia.zhe28.util.Constant.INSTANCE.setVip((String) StringsKt.split$default((CharSequence) it.getVieLevel(), new String[]{"VIP"}, false, 0, 6, (Object) null).get(1));
                    } catch (Exception unused) {
                        com.cy.yyjia.zhe28.util.Constant.INSTANCE.setVip("0");
                    }
                    com.cy.yyjia.zhe28.util.Constant.INSTANCE.setAvatar(it.getUser_avatar());
                    com.cy.yyjia.zhe28.util.Constant.INSTANCE.setNoPin(TextUtils.isEmpty(it.getTrade_pin()));
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.getUserInfo.2
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
                    MainActivity.this.netFail(it);
                }
            });
        } else {
            getVm().getUser().postValue(new UserBean());
            getMBinding().tvUnread.setVisibility(8);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (keyCode != 4 || event.getAction() != 0) {
            return super.onKeyDown(keyCode, event);
        }
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", 0).show();
            this.exitTime = System.currentTimeMillis();
            return true;
        }
        finish();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    public final void dealSDK() {
        this.type = getIntent().getStringExtra("type");
        this.sdkVersion = getIntent().getStringExtra("sdkVersion");
        String stringExtra = getIntent().getStringExtra("packageName");
        this.pkgName = stringExtra;
        log("deal sdk " + this.type + StringUtils.SPACE + this.sdkVersion + StringUtils.SPACE + stringExtra);
        if (this.pkgName != null) {
            if (Intrinsics.areEqual(this.type, "wx")) {
                IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this, com.cy.yyjia.zhe28.util.Constant.INSTANCE.getWxAppId(), true);
                if (iwxapiCreateWXAPI.isWXAppInstalled()) {
                    new WaitDialog(this).setText("正在前往微信").show();
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "28zhe";
                    iwxapiCreateWXAPI.sendReq(req);
                } else {
                    toast("请先安装微信");
                }
            } else if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged()) {
                if (!TextUtils.isEmpty(this.sdkVersion)) {
                    Intent intent = new Intent(this, (Class<?>) QuickLoginActivity.class);
                    intent.putExtra("packageName", this.pkgName);
                    intent.putExtra("loginType", 1);
                    startActivityForResult(intent, 1001);
                } else {
                    Intent intent2 = new Intent(this, (Class<?>) QuickLoginActivity.class);
                    intent2.putExtra("packageName", this.pkgName);
                    intent2.putExtra("loginType", intent2.getIntExtra("loginType", 0));
                    startActivityForResult(intent2, 1001);
                }
            } else {
                startActivityForResult(new Intent(this, (Class<?>) LoginActivity.class), 1001);
            }
        }
        String stringExtra2 = getIntent().getStringExtra("url");
        log("url: " + stringExtra2);
        if (stringExtra2 != null) {
            SdkJumpBean sdkJumpBean = (SdkJumpBean) new Gson().fromJson(stringExtra2, SdkJumpBean.class);
            if (sdkJumpBean.getActionid() == 3) {
                Intent intent3 = new Intent(this, (Class<?>) MainActivity.class);
                intent3.putExtra(ImageSelector.POSITION, 2);
                startActivity(intent3);
                finish();
            } else if (sdkJumpBean.getActionid() == 4) {
                if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged()) {
                    select(3);
                } else {
                    startActivity(LoginActivity.class);
                }
            } else if (sdkJumpBean.getActionid() == 5) {
                select(4);
            } else {
                int actionid = sdkJumpBean.getActionid();
                if (actionid == 0) {
                    Util.openWeb(getMContext(), "", sdkJumpBean.getUrl());
                } else if (actionid == 3) {
                    select(2);
                } else if (actionid == 49) {
                    Util.skip(getMContext(), MonthlyTaskActivity.class);
                } else if (actionid == 15) {
                    Intent intent4 = new Intent(this, (Class<?>) EventDetailActivity.class);
                    intent4.putExtra("newsId", sdkJumpBean.getUrl());
                    startActivity(intent4);
                } else if (actionid != 16) {
                    switch (actionid) {
                        case 6:
                            Util.gotoGame(getMContext(), sdkJumpBean.getGid());
                            break;
                        case 7:
                            Util.skipWithLogin(getMContext(), CardActivity.class);
                            break;
                        case 8:
                            Util.skipWithLogin(getMContext(), MonthCardActivity.class);
                            break;
                        case 9:
                            Util.toService(getMContext());
                            break;
                        case 10:
                            Util.skipWithLogin(getMContext(), RecycleActivity.class);
                            break;
                        case 11:
                            Util.skipWithLogin(getMContext(), DealSellActivity.class);
                            break;
                        case 12:
                            Util.skip(getMContext(), DailyTaskActivity.class);
                            break;
                    }
                } else {
                    Util.skipWithLogin(getMContext(), NoviceWelfareActivity.class);
                }
                finish();
            }
        }
        String stringExtra3 = getIntent().getStringExtra("role_id");
        if (stringExtra3 != null) {
            Intent intent5 = new Intent(this, (Class<?>) DealDetailActivity.class);
            intent5.putExtra("id", Integer.parseInt(stringExtra3));
            startActivity(intent5);
            setIntent(new Intent());
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 7892) {
            finish();
        } else if (requestCode == 1001) {
            if (TextUtils.isEmpty(this.sdkVersion)) {
                Intent intent = new Intent();
                String str = this.pkgName;
                Intrinsics.checkNotNull(str);
                intent.setComponent(new ComponentName(str, "com.cy.yyjia.sdk.activity.AppLoginActivity"));
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
            } else if (data != null) {
                String stringExtra = data.getStringExtra("token");
                Intent intent2 = new Intent();
                intent2.putExtra("token", stringExtra);
                setResult(-1, intent2);
                finish();
            }
        }
        if (resultCode == 7789) {
            MutableLiveData<Integer> category = getVm().getCategory();
            Intrinsics.checkNotNull(data);
            category.setValue(Integer.valueOf(data.getIntExtra("id", 0)));
        }
        this.pkgName = "";
    }

    public final void wxLogin(WxLoginBean wxLoginBean) {
        Intrinsics.checkNotNullParameter(wxLoginBean, "wxLoginBean");
        Repository.INSTANCE.getWxAccessToken(wxLoginBean.getCode(), new Function1<WxLoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1
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
                    final MainActivity mainActivity = MainActivity.this;
                    Function1<WxLoginResult, Unit> function1 = new Function1<WxLoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1.1
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
                                final MainActivity mainActivity2 = mainActivity;
                                Function1<WxUserBean, Unit> function12 = new Function1<WxUserBean, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1.1.1
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
                                        final MainActivity mainActivity3 = mainActivity2;
                                        Function1<LoginResult, Unit> function13 = new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1.1.1.1
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
                                                com.cy.yyjia.zhe28.util.Constant.INSTANCE.setWxLoginCode("");
                                                new Intent().putExtra("token", it.getToken());
                                                mainActivity3.setResult(-1, new Intent().putExtra("token", it.getToken()));
                                                mainActivity3.finish();
                                            }
                                        };
                                        final MainActivity mainActivity4 = mainActivity2;
                                        repository3.wxLogin(openid2, nickname, headimgurl, unionid, function13, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1.1.1.2
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
                                                mainActivity4.netFail(it);
                                            }
                                        });
                                    }
                                };
                                final MainActivity mainActivity3 = mainActivity;
                                repository2.getWxUser(access_token, openid, function12, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1.1.2
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
                                        mainActivity3.netFail(it);
                                    }
                                });
                                return;
                            }
                            MainActivity mainActivity4 = mainActivity;
                            String errmsg = result2.getErrmsg();
                            Intrinsics.checkNotNull(errmsg);
                            mainActivity4.toast(errmsg);
                        }
                    };
                    final MainActivity mainActivity2 = MainActivity.this;
                    repository.refreshWxAccessToken(refresh_token, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.1.2
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
                            mainActivity2.netFail(it);
                        }
                    });
                    return;
                }
                MainActivity mainActivity3 = MainActivity.this;
                String errmsg = result1.getErrmsg();
                Intrinsics.checkNotNull(errmsg);
                mainActivity3.toast(errmsg);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.activity.MainActivity.wxLogin.2
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
                MainActivity.this.netFail(it);
            }
        });
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (ev.getAction() == 2) {
            hideService();
        }
        return super.dispatchTouchEvent(ev);
    }

    public final float getWidth() {
        return ((Number) this.width.getValue()).floatValue();
    }

    public final float getServicePosition1() {
        return ((Number) this.servicePosition1.getValue()).floatValue();
    }

    public final float getServicePosition2() {
        return ((Number) this.servicePosition2.getValue()).floatValue();
    }

    public final void hideService() {
        getMBinding().ivService.setAlpha(0.5f);
        getMBinding().ivService.setX(getServicePosition2());
    }

    public final void showService() {
        getMBinding().ivService.setAlpha(1.0f);
        getMBinding().ivService.setX(getServicePosition1());
    }

    public final void initTheme() {
        TypedArray typedArrayObtainTypedArray;
        if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getAppTheme() == 1) {
            typedArrayObtainTypedArray = getResources().obtainTypedArray(R.array.main_icon_cj);
        } else {
            typedArrayObtainTypedArray = getResources().obtainTypedArray(R.array.main_icon_normal);
        }
        Intrinsics.checkNotNull(typedArrayObtainTypedArray);
        getMBinding().tv1.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, AppCompatResources.getDrawable(this, typedArrayObtainTypedArray.getResourceId(0, 0)), (Drawable) null, (Drawable) null);
        getMBinding().tv2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(typedArrayObtainTypedArray.getResourceId(1, 0)), (Drawable) null, (Drawable) null);
        getMBinding().tv3.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(typedArrayObtainTypedArray.getResourceId(2, 0)), (Drawable) null, (Drawable) null);
        getMBinding().tv4.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(typedArrayObtainTypedArray.getResourceId(3, 0)), (Drawable) null, (Drawable) null);
        getMBinding().tv5.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(typedArrayObtainTypedArray.getResourceId(4, 0)), (Drawable) null, (Drawable) null);
    }

    public final void unreadNumber(String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        String str = num;
        if (TextUtils.isEmpty(str) || Intrinsics.areEqual(num, "0")) {
            getMBinding().tvUnread.setVisibility(8);
        } else {
            getMBinding().tvUnread.setVisibility(0);
            getMBinding().tvUnread.setText(str);
        }
    }
}
