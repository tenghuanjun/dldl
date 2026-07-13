package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.FragmentUserBinding;
import com.cy.yyjia.zhe28.databinding.ItemMyGameBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UnreadBean;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.cy.yyjia.zhe28.ui.activity.CardActivity;
import com.cy.yyjia.zhe28.ui.activity.DownloadManagerActivity;
import com.cy.yyjia.zhe28.ui.activity.InfoActivity;
import com.cy.yyjia.zhe28.ui.activity.InviteActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.activity.MessageActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.ui.activity.MyCouponActivity;
import com.cy.yyjia.zhe28.ui.activity.MyGameActivity;
import com.cy.yyjia.zhe28.ui.activity.MyMoneyActivity;
import com.cy.yyjia.zhe28.ui.activity.MyVoucherActivity;
import com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity;
import com.cy.yyjia.zhe28.ui.activity.PtbRecordActivity;
import com.cy.yyjia.zhe28.ui.activity.QiandaoActivity;
import com.cy.yyjia.zhe28.ui.activity.SettingActivity2;
import com.cy.yyjia.zhe28.ui.activity.SubscribeActivity;
import com.cy.yyjia.zhe28.ui.activity.VipActivity;
import com.cy.yyjia.zhe28.ui.activity.YunBuyActivity;
import com.cy.yyjia.zhe28.ui.activity.YunPlayActivity;
import com.cy.yyjia.zhe28.ui.activity.YunTipsActivity;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.RvPopup;
import com.cy.yyjia.zhe28.ui.dialog.YunGameDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: UserFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001cJ\u0006\u0010 \u001a\u00020\u001cJ\u0006\u0010!\u001a\u00020\u001cJ\b\u0010\"\u001a\u00020\u001cH\u0016J\u0006\u0010#\u001a\u00020\u001cJ\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u001cH\u0016J\u0006\u0010(\u001a\u00020\u001cR'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012¨\u0006)"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/UserFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentUserBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMyGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "toYun", "", "getToYun", "()Z", "setToYun", "(Z)V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "yunActivity", "getYunActivity", "setYunActivity", "delete", "", ImageSelector.POSITION, "", "editYunName", "getGame", "getYunData", "init", "initGameRV", "onClick", "v", "Landroid/view/View;", "onResume", "selectYunGame", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UserFragment extends BaseFragment<FragmentUserBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private boolean toYun;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;
    private boolean yunActivity;

    public UserFragment() {
        super(R.layout.fragment_user);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemMyGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemMyGameBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_my_game, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ FragmentUserBinding access$getMBinding(UserFragment userFragment) {
        return userFragment.getMBinding();
    }

    private final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final BaseAdapter<GameBean, ItemMyGameBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final boolean getToYun() {
        return this.toYun;
    }

    public final void setToYun(boolean z) {
        this.toYun = z;
    }

    public final boolean getYunActivity() {
        return this.yunActivity;
    }

    public final void setYunActivity(boolean z) {
        this.yunActivity = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        ConstraintLayout cl = getMBinding().cl;
        Intrinsics.checkNotNullExpressionValue(cl, "cl");
        setViewFitsSystemWindows(cl);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.yunActivity = arguments.getBoolean("yun", false);
        }
        getMBinding().setOnClick(this);
        getMBinding().setPosition(1);
        initGameRV();
        getMBinding().rvYunBlock.setAdapter(new BaseAdapter(R.layout.item_yun_block, null, 2, null));
        getVm().getUser().observe(this, new UserFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                invoke2(userBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserBean userBean) {
                UserFragment.access$getMBinding(UserFragment.this).setData(userBean);
                if (userBean.getUid() != 0) {
                    if (UserFragment.this.getYunActivity()) {
                        UserFragment.access$getMBinding(UserFragment.this).tvGame4.performClick();
                        return;
                    }
                    if (UserFragment.access$getMBinding(UserFragment.this).getPosition() != 4) {
                        UserFragment.this.getGame();
                        return;
                    } else if (!UserFragment.this.getToYun()) {
                        UserFragment.this.getYunData();
                        return;
                    } else {
                        UserFragment.this.setToYun(false);
                        return;
                    }
                }
                UserFragment.access$getMBinding(UserFragment.this).setYun(null);
            }
        }));
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.init.3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getResultCode() == 9852) {
                    Intent data = it.getData();
                    Intrinsics.checkNotNull(data);
                    int intExtra = data.getIntExtra("did", 0);
                    if (intExtra != 0) {
                        YunIndexBean yun = UserFragment.access$getMBinding(UserFragment.this).getYun();
                        Intrinsics.checkNotNull(yun);
                        for (YunIndexBean.Device device : yun.getDeviceList()) {
                            device.setSelected(device.getId() == intExtra);
                        }
                        UserFragment.access$getMBinding(UserFragment.this).setYun(UserFragment.access$getMBinding(UserFragment.this).getYun());
                    }
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        setResultLauncher(activityResultLauncherRegisterForActivityResult);
    }

    public final void delete(final int position) {
        Repository.INSTANCE.deleteMyGame(getMBinding().getPosition() - 2, getAdapter().getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.delete.1
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
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserFragment.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    UserFragment.this.getAdapter().removeAt(position);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.delete.2
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
                UserFragment.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn_coupon /* 2131361959 */:
                Util.skipWithLogin(getMContext(), MyCouponActivity.class);
                break;
            case R.id.btn_download /* 2131361961 */:
                startActivity(DownloadManagerActivity.class);
                break;
            case R.id.btn_gift /* 2131361964 */:
            case R.id.ll_point /* 2131362271 */:
                Util.skipWithLogin(getMContext(), MyWelfareActivity.class);
                break;
            case R.id.btn_service /* 2131361978 */:
                Util.toService(getMContext());
                break;
            case R.id.btn_setting /* 2131361979 */:
                startActivity(SettingActivity2.class);
                getMContext().overridePendingTransition(R.anim.dialog_right_in, 0);
                break;
            case R.id.cl_month /* 2131362012 */:
                startActivity(MonthCardActivity.class);
                break;
            case R.id.cl_sqk /* 2131362013 */:
                startActivity(CardActivity.class);
                break;
            case R.id.iv_invite /* 2131362180 */:
                startActivity(InviteActivity.class);
                break;
            case R.id.iv_message /* 2131362184 */:
                startActivity(MessageActivity.class);
                break;
            case R.id.iv_qiandao /* 2131362187 */:
                startActivity(QiandaoActivity.class);
                break;
            case R.id.iv_subscribe /* 2131362196 */:
                startActivity(SubscribeActivity.class);
                break;
            case R.id.iv_yun_go /* 2131362211 */:
                Intent intent = new Intent(getMContext(), (Class<?>) YunPlayActivity.class);
                YunIndexBean yun = getMBinding().getYun();
                Intrinsics.checkNotNull(yun);
                YunIndexBean.Device selectedDevice = yun.getSelectedDevice();
                Intrinsics.checkNotNull(selectedDevice);
                intent.putExtra("gid", selectedDevice.getGameId());
                YunIndexBean yun2 = getMBinding().getYun();
                Intrinsics.checkNotNull(yun2);
                YunIndexBean.Device selectedDevice2 = yun2.getSelectedDevice();
                Intrinsics.checkNotNull(selectedDevice2);
                intent.putExtra("myDeviceId", selectedDevice2.getId());
                YunIndexBean yun3 = getMBinding().getYun();
                Intrinsics.checkNotNull(yun3);
                YunIndexBean.Device selectedDevice3 = yun3.getSelectedDevice();
                Intrinsics.checkNotNull(selectedDevice3);
                intent.putExtra("landscape", Intrinsics.areEqual(selectedDevice3.getScreen(), "horizontal"));
                this.toYun = true;
                getResultLauncher().launch(intent);
                break;
            case R.id.iv_yun_question /* 2131362212 */:
                startActivity(YunTipsActivity.class);
                break;
            case R.id.ll_company /* 2131362250 */:
                getMBinding().setCompany(!getMBinding().getCompany());
                break;
            case R.id.ll_flb /* 2131362257 */:
                Util.skipWithLogin(getMContext(), PtbRecordActivity.class);
                break;
            case R.id.ll_ptb /* 2131362272 */:
                Util.skipWithLogin(getMContext(), MyMoneyActivity.class);
                break;
            case R.id.ll_vip /* 2131362284 */:
                Util.skipWithLogin(getMContext(), VipActivity.class);
                break;
            case R.id.ll_voucher /* 2131362285 */:
                Util.skipWithLogin(getMContext(), MyVoucherActivity.class);
                break;
            case R.id.tv_device_name /* 2131362682 */:
                editYunName();
                break;
            case R.id.tv_game1 /* 2131362709 */:
                getMBinding().setPosition(1);
                getGame();
                break;
            case R.id.tv_game2 /* 2131362710 */:
                getMBinding().setPosition(2);
                getGame();
                break;
            case R.id.tv_game3 /* 2131362711 */:
                getMBinding().setPosition(3);
                getGame();
                break;
            case R.id.tv_game4 /* 2131362712 */:
                getMBinding().setPosition(4);
                getMBinding().tvMore.setVisibility(8);
                getYunData();
                break;
            case R.id.tv_more /* 2131362730 */:
                int position = getMBinding().getPosition();
                startActivity(new Intent(getMContext(), (Class<?>) MyGameActivity.class).putExtra(ImageSelector.POSITION, position != 2 ? position != 3 ? 2 : 1 : 0));
                break;
            case R.id.tv_nickname /* 2131362732 */:
            case R.id.user_icon /* 2131362830 */:
                Util.skipWithLogin(getMContext(), InfoActivity.class);
                break;
            case R.id.tv_yun_add /* 2131362814 */:
            case R.id.tv_yun_buy /* 2131362815 */:
                startActivity(YunBuyActivity.class);
                break;
            case R.id.tv_yun_device /* 2131362816 */:
                YunIndexBean yun4 = getMBinding().getYun();
                Intrinsics.checkNotNull(yun4);
                BaseAdapter<?, ?> baseAdapter = new BaseAdapter<>(R.layout.item_yun_pop_device, yun4.getDeviceList());
                final RvPopup width = new RvPopup(getMContext()).setAdapter(baseAdapter).setWidth(Util.dpToPx(getMContext(), 64.0f));
                width.showAsDropDown(v);
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$$ExternalSyntheticLambda0
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        UserFragment.onClick$lambda$1(width, this, baseQuickAdapter, view, i);
                    }
                });
                break;
            case R.id.tv_yun_exit /* 2131362817 */:
                Repository repository = Repository.INSTANCE;
                YunIndexBean yun5 = getMBinding().getYun();
                Intrinsics.checkNotNull(yun5);
                YunIndexBean.Device selectedDevice4 = yun5.getSelectedDevice();
                Intrinsics.checkNotNull(selectedDevice4);
                repository.stopYunGame(selectedDevice4.getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onClick.2
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
                        UserFragment.this.toast(it.getMsg());
                        UserFragment.this.getYunData();
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onClick.3
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
                        UserFragment.this.netFail(it);
                    }
                });
                break;
            case R.id.tv_yun_game /* 2131362818 */:
                selectYunGame();
                break;
            case R.id.tv_yun_games /* 2131362819 */:
                new YunGameDialog(getMContext(), true).show();
                break;
            case R.id.tv_yun_lesson /* 2131362820 */:
                startActivity(YunTipsActivity.class);
                break;
            case R.id.tv_yun_refresh /* 2131362821 */:
                Repository repository2 = Repository.INSTANCE;
                YunIndexBean yun6 = getMBinding().getYun();
                Intrinsics.checkNotNull(yun6);
                YunIndexBean.Device selectedDevice5 = yun6.getSelectedDevice();
                Intrinsics.checkNotNull(selectedDevice5);
                repository2.refreshYunGame(selectedDevice5.getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onClick.4
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
                        UserFragment.this.toast(it.getMsg());
                        UserFragment.this.getYunData();
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onClick.5
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
                        UserFragment.this.netFail(it);
                    }
                });
                break;
            case R.id.tv_yun_renew /* 2131362822 */:
                Intent intent2 = new Intent(getMContext(), (Class<?>) YunBuyActivity.class);
                YunIndexBean yun7 = getMBinding().getYun();
                Intrinsics.checkNotNull(yun7);
                YunIndexBean.Device selectedDevice6 = yun7.getSelectedDevice();
                Intrinsics.checkNotNull(selectedDevice6);
                intent2.putExtra("deviceId", selectedDevice6.getId());
                startActivity(intent2);
                break;
            default:
                if (!Constant.INSTANCE.getLogged()) {
                    startActivity(LoginActivity.class);
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$1(RvPopup rvPopup, UserFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        rvPopup.dismiss();
        YunIndexBean yun = this$0.getMBinding().getYun();
        Intrinsics.checkNotNull(yun);
        Iterator<YunIndexBean.Device> it = yun.getDeviceList().iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        YunIndexBean yun2 = this$0.getMBinding().getYun();
        Intrinsics.checkNotNull(yun2);
        yun2.getDeviceList().get(i).setSelected(true);
        this$0.getMBinding().setYun(this$0.getMBinding().getYun());
    }

    public final void initGameRV() {
        getMBinding().rvGame.setAdapter(getAdapter());
        getAdapter().setMyEmptyView("");
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UserFragment.initGameRV$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().addChildClickViewIds(R.id.iv_delete);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UserFragment.initGameRV$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGameRV$lambda$2(UserFragment this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.getAdapter().getItem(i).gotoGame(v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGameRV$lambda$3(final UserFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.iv_delete) {
            int position = this$0.getMBinding().getPosition() - 2;
            if (position == 0) {
                str = "取消预约";
            } else if (position == 1) {
                str = "取消收藏";
            } else {
                str = "删除历史";
            }
            new ConfirmDialog(this$0.getMContext()).setTip("您确定要" + str + "吗").setBtnText(str).setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$initGameRV$2$1
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
                    this.this$0.delete(i);
                }
            }).show();
        }
    }

    public final void getGame() {
        Repository.INSTANCE.getMyGame(1, getMBinding().getPosition() - 2, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.getGame.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserFragment.this.getAdapter().setNewInstance(it.getList());
                UserFragment.access$getMBinding(UserFragment.this).tvMore.setVisibility(it.getLast_page() > 1 ? 0 : 8);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.getGame.2
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
                UserFragment.this.netFail(it);
            }
        });
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.UserFragment$getYunData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UserFragment.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/YunIndexBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C11741 extends Lambda implements Function1<YunIndexBean, Unit> {
        C11741() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(YunIndexBean yunIndexBean) {
            invoke2(yunIndexBean);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0069  */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke2(com.cy.yyjia.zhe28.domain.YunIndexBean r7) {
            /*
                r6 = this;
                java.lang.String r0 = "it"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.util.List r0 = r7.getDeviceList()
                int r0 = r0.size()
                r1 = 0
                if (r0 <= 0) goto L76
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                com.cy.yyjia.zhe28.databinding.FragmentUserBinding r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.access$getMBinding(r0)
                com.cy.yyjia.zhe28.domain.YunIndexBean r0 = r0.getYun()
                r2 = 1
                if (r0 == 0) goto L69
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                com.cy.yyjia.zhe28.databinding.FragmentUserBinding r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.access$getMBinding(r0)
                com.cy.yyjia.zhe28.domain.YunIndexBean r0 = r0.getYun()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                com.cy.yyjia.zhe28.domain.YunIndexBean$Device r0 = r0.getSelectedDevice()
                if (r0 == 0) goto L69
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                com.cy.yyjia.zhe28.databinding.FragmentUserBinding r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.access$getMBinding(r0)
                com.cy.yyjia.zhe28.domain.YunIndexBean r0 = r0.getYun()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                com.cy.yyjia.zhe28.domain.YunIndexBean$Device r0 = r0.getSelectedDevice()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                int r0 = r0.getId()
                java.util.List r3 = r7.getDeviceList()
                java.util.Iterator r3 = r3.iterator()
            L50:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L76
                java.lang.Object r4 = r3.next()
                com.cy.yyjia.zhe28.domain.YunIndexBean$Device r4 = (com.cy.yyjia.zhe28.domain.YunIndexBean.Device) r4
                int r5 = r4.getId()
                if (r5 != r0) goto L64
                r5 = 1
                goto L65
            L64:
                r5 = 0
            L65:
                r4.setSelected(r5)
                goto L50
            L69:
                java.util.List r0 = r7.getDeviceList()
                java.lang.Object r0 = r0.get(r1)
                com.cy.yyjia.zhe28.domain.YunIndexBean$Device r0 = (com.cy.yyjia.zhe28.domain.YunIndexBean.Device) r0
                r0.setSelected(r2)
            L76:
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                com.cy.yyjia.zhe28.databinding.FragmentUserBinding r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.access$getMBinding(r0)
                r0.setYun(r7)
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r7 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                boolean r7 = r7.getYunActivity()
                if (r7 == 0) goto La0
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r7 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                r7.setYunActivity(r1)
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r7 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                com.cy.yyjia.zhe28.databinding.FragmentUserBinding r7 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.access$getMBinding(r7)
                androidx.core.widget.NestedScrollView r7 = r7.nsv
                com.cy.yyjia.zhe28.ui.fragment.UserFragment r0 = com.cy.yyjia.zhe28.ui.fragment.UserFragment.this
                com.cy.yyjia.zhe28.ui.fragment.UserFragment$getYunData$1$$ExternalSyntheticLambda0 r1 = new com.cy.yyjia.zhe28.ui.fragment.UserFragment$getYunData$1$$ExternalSyntheticLambda0
                r1.<init>()
                r2 = 200(0xc8, double:9.9E-322)
                r7.postDelayed(r1, r2)
            La0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.fragment.UserFragment.C11741.invoke2(com.cy.yyjia.zhe28.domain.YunIndexBean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(UserFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            int height = UserFragment.access$getMBinding(this$0).nsv.getChildAt(0).getHeight();
            UserFragment.access$getMBinding(this$0).nsv.fullScroll(KeyBoardKey.KeyboardKeyF19);
            UserFragment.access$getMBinding(this$0).nsv.scrollTo(0, height);
        }
    }

    public final void getYunData() {
        Repository.INSTANCE.getYunIndex(new C11741(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.getYunData.2
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
                UserFragment.this.netFail(it);
            }
        });
    }

    public final void editYunName() {
        FastDialog contentView = new FastDialog(getMContext()).setContentView(R.layout.dialog_yun_device_name);
        YunIndexBean yun = getMBinding().getYun();
        Intrinsics.checkNotNull(yun);
        YunIndexBean.Device selectedDevice = yun.getSelectedDevice();
        Intrinsics.checkNotNull(selectedDevice);
        contentView.setText(R.id.et, selectedDevice.getName()).setOnClickListener(R.id.tv_confirm, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$$ExternalSyntheticLambda1
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                UserFragment.editYunName$lambda$4(this.f$0, baseDialog, view);
            }
        }).setOnClickListener(R.id.iv_clear, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$$ExternalSyntheticLambda2
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                UserFragment.editYunName$lambda$5(baseDialog, view);
            }
        }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$$ExternalSyntheticLambda3
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                baseDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void editYunName$lambda$4(final UserFragment this$0, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View viewFindViewById = baseDialog.findViewById(R.id.et);
        Intrinsics.checkNotNull(viewFindViewById);
        final String string = ((EditText) viewFindViewById).getText().toString();
        if (string.length() > 0) {
            Repository repository = Repository.INSTANCE;
            YunIndexBean yun = this$0.getMBinding().getYun();
            Intrinsics.checkNotNull(yun);
            YunIndexBean.Device selectedDevice = yun.getSelectedDevice();
            Intrinsics.checkNotNull(selectedDevice);
            repository.editYunName(selectedDevice.getId(), string, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$editYunName$1$1
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
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this.this$0.toast(it.getMsg());
                    YunIndexBean yun2 = UserFragment.access$getMBinding(this.this$0).getYun();
                    Intrinsics.checkNotNull(yun2);
                    YunIndexBean.Device selectedDevice2 = yun2.getSelectedDevice();
                    Intrinsics.checkNotNull(selectedDevice2);
                    selectedDevice2.setName(string);
                    UserFragment.access$getMBinding(this.this$0).setYun(UserFragment.access$getMBinding(this.this$0).getYun());
                    baseDialog.dismiss();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment$editYunName$1$2
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
                    this.this$0.netFail(it);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void editYunName$lambda$5(BaseDialog baseDialog, View view) {
        View viewFindViewById = baseDialog.findViewById(R.id.et);
        Intrinsics.checkNotNull(viewFindViewById);
        ((EditText) viewFindViewById).setText("");
    }

    public final void selectYunGame() {
        YunGameDialog yunGameDialog = new YunGameDialog(getMContext(), false, 2, null);
        YunIndexBean yun = getMBinding().getYun();
        Intrinsics.checkNotNull(yun);
        YunIndexBean.Device selectedDevice = yun.getSelectedDevice();
        if (selectedDevice != null) {
            yunGameDialog.setDeviceId(selectedDevice.getId());
        }
        yunGameDialog.show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Repository.INSTANCE.getYunShow(new Function1<UpdateBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onResume.1
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
                UserFragment.access$getMBinding(UserFragment.this).tvGame4.setVisibility(it.getHideTrade() == 1 ? 8 : 0);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onResume.2
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
        Repository.INSTANCE.getUnreadNumber(new Function1<UnreadBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onResume.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UnreadBean unreadBean) {
                invoke2(unreadBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UnreadBean it) {
                String strValueOf;
                Intrinsics.checkNotNullParameter(it, "it");
                FragmentUserBinding fragmentUserBindingAccess$getMBinding = UserFragment.access$getMBinding(UserFragment.this);
                if (it.getMsgNum() > 99) {
                    strValueOf = "99+";
                } else {
                    strValueOf = String.valueOf(it.getMsgNum());
                }
                fragmentUserBindingAccess$getMBinding.setMessageNum(strValueOf);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.UserFragment.onResume.4
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
                UserFragment.this.netFail(it);
            }
        });
    }
}
