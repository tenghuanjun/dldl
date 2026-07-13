package com.cy.yyjia.zhe28.domain;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R \u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0007\"\u0004\b$\u0010\u0011¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "action", "Landroidx/lifecycle/MutableLiveData;", "", "getAction", "()Landroidx/lifecycle/MutableLiveData;", "category", "getCategory", "dealParam", "Lcom/cy/yyjia/zhe28/domain/DealParamBean;", "getDealParam", "home", "Lcom/cy/yyjia/zhe28/domain/HomeBean;", "getHome", "setHome", "(Landroidx/lifecycle/MutableLiveData;)V", "homeFun", "", "getHomeFun", "setHomeFun", "homeHeight", "getHomeHeight", "homeNav", "", "Lcom/cy/yyjia/zhe28/domain/MainTabBean;", "getHomeNav", "mainTabPosition", "getMainTabPosition", "showKeyboard", "", "getShowKeyboard", "user", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "getUser", "setUser", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MainViewModel extends ViewModel {
    public static final int $stable = 8;
    private MutableLiveData<UserBean> user = new MutableLiveData<>();
    private MutableLiveData<HomeBean> home = new MutableLiveData<>();
    private MutableLiveData<String> homeFun = new MutableLiveData<>();
    private final MutableLiveData<Integer> category = new MutableLiveData<>();
    private final MutableLiveData<Integer> action = new MutableLiveData<>();
    private final MutableLiveData<List<MainTabBean>> homeNav = new MutableLiveData<>();
    private final MutableLiveData<Integer> mainTabPosition = new MutableLiveData<>();
    private final MutableLiveData<Integer> homeHeight = new MutableLiveData<>();
    private final MutableLiveData<DealParamBean> dealParam = new MutableLiveData<>();
    private final MutableLiveData<Boolean> showKeyboard = new MutableLiveData<>();

    public final MutableLiveData<UserBean> getUser() {
        return this.user;
    }

    public final void setUser(MutableLiveData<UserBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.user = mutableLiveData;
    }

    public final MutableLiveData<HomeBean> getHome() {
        return this.home;
    }

    public final void setHome(MutableLiveData<HomeBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.home = mutableLiveData;
    }

    public final MutableLiveData<String> getHomeFun() {
        return this.homeFun;
    }

    public final void setHomeFun(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.homeFun = mutableLiveData;
    }

    public final MutableLiveData<Integer> getCategory() {
        return this.category;
    }

    public final MutableLiveData<Integer> getAction() {
        return this.action;
    }

    public final MutableLiveData<List<MainTabBean>> getHomeNav() {
        return this.homeNav;
    }

    public final MutableLiveData<Integer> getMainTabPosition() {
        return this.mainTabPosition;
    }

    public final MutableLiveData<Integer> getHomeHeight() {
        return this.homeHeight;
    }

    public final MutableLiveData<DealParamBean> getDealParam() {
        return this.dealParam;
    }

    public final MutableLiveData<Boolean> getShowKeyboard() {
        return this.showKeyboard;
    }
}
