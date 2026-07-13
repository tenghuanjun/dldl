package com.cy.yyjia.zhe28.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.os.BundleKt;
import androidx.lifecycle.ViewModelProvider;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityUserBinding;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.fragment.UserFragment;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0014R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/UserActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityUserBinding;", "()V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "Lkotlin/Lazy;", "init", "", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UserActivity extends BaseActivity<ActivityUserBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public UserActivity() {
        super(R.layout.activity_user, 1);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.activity.UserActivity$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0).get(MainViewModel.class);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        UserFragment userFragment = new UserFragment();
        Bundle bundleBundleOf = BundleKt.bundleOf();
        bundleBundleOf.putBoolean("yun", true);
        userFragment.setArguments(bundleBundleOf);
        getSupportFragmentManager().beginTransaction().replace(R.id.body, userFragment).commit();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.UserActivity.onResume.1
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
                UserActivity.this.getVm().getUser().postValue(it);
                Constant.INSTANCE.setNickname(it.getNickName());
                Constant.INSTANCE.setAvatar(it.getUser_avatar());
                Constant constant = Constant.INSTANCE;
                String strSubstring = it.getVieLevel().substring(it.getVieLevel().length() - 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                constant.setVip(strSubstring);
                Constant.INSTANCE.setNoPin(TextUtils.isEmpty(it.getTrade_pin()));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.UserActivity.onResume.2
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
                UserActivity.this.netFail(it);
            }
        });
    }
}
