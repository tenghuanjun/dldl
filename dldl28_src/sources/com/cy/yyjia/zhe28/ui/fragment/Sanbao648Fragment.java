package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentSanbao648Binding;
import com.cy.yyjia.zhe28.databinding.ItemSanbao648Binding;
import com.cy.yyjia.zhe28.databinding.ItemSanbao648CouponBinding;
import com.cy.yyjia.zhe28.databinding.ItemSanbao648GiftBinding;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.domain.DownloadResult;
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.NoviceGameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.SanbaoRuleBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.service.DownloadService;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.activity.SanbaoActivity;
import com.cy.yyjia.zhe28.ui.adapter.SanbaoMessageAdapter;
import com.cy.yyjia.zhe28.ui.dialog.NoviceWelfareDialog;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.task.XExecutor;
import java.io.File;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: Sanbao648Fragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001eJ\u0006\u0010.\u001a\u00020,J\u0006\u0010/\u001a\u00020,J\u0006\u00100\u001a\u00020,J\b\u00101\u001a\u00020,H\u0016J\u0010\u00102\u001a\u00020,2\u0006\u00103\u001a\u000204H\u0016J\u0006\u00105\u001a\u00020,R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0010\u0010\nR'\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0015\u0010\nR\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\f\u001a\u0004\b(\u0010)¨\u00066"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/Sanbao648Fragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentSanbao648Binding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/NoviceGameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSanbao648Binding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "couponAdapter", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSanbao648CouponBinding;", "getCouponAdapter", "couponAdapter$delegate", "giftAdapter", "Lcom/cy/yyjia/zhe28/domain/GiftBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSanbao648GiftBinding;", "getGiftAdapter", "giftAdapter$delegate", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "typeId", "getTypeId", "setTypeId", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "download", "", ImageSelector.POSITION, "getCoupon", "getGame", "getGift", "init", "onClick", "v", "Landroid/view/View;", "toMy", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Sanbao648Fragment extends BaseFragment<FragmentSanbao648Binding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: couponAdapter$delegate, reason: from kotlin metadata */
    private final Lazy couponAdapter;

    /* JADX INFO: renamed from: giftAdapter$delegate, reason: from kotlin metadata */
    private final Lazy giftAdapter;
    private String name;
    private int page;
    private int typeId;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public Sanbao648Fragment() {
        super(R.layout.fragment_sanbao648);
        this.page = 1;
        this.name = "";
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<NoviceGameBean, ItemSanbao648Binding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<NoviceGameBean, ItemSanbao648Binding> invoke() {
                return new BaseAdapter<>(R.layout.item_sanbao_648, null, 2, null);
            }
        });
        this.giftAdapter = LazyKt.lazy(new Function0<BaseAdapter<GiftBean, ItemSanbao648GiftBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$giftAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GiftBean, ItemSanbao648GiftBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_sanbao_648_gift, null, 2, null);
            }
        });
        this.couponAdapter = LazyKt.lazy(new Function0<BaseAdapter<CouponBean, ItemSanbao648CouponBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$couponAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CouponBean, ItemSanbao648CouponBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_sanbao_648_coupon, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ FragmentSanbao648Binding access$getMBinding(Sanbao648Fragment sanbao648Fragment) {
        return sanbao648Fragment.getMBinding();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final BaseAdapter<NoviceGameBean, ItemSanbao648Binding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<GiftBean, ItemSanbao648GiftBinding> getGiftAdapter() {
        return (BaseAdapter) this.giftAdapter.getValue();
    }

    public final BaseAdapter<CouponBean, ItemSanbao648CouponBinding> getCouponAdapter() {
        return (BaseAdapter) this.couponAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.init.1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getResultCode() == 1001) {
                    Sanbao648Fragment.this.getGame();
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        setResultLauncher(activityResultLauncherRegisterForActivityResult);
        getMBinding().setOnClick(this);
        getVm().getHomeFun().observe(this, new Sanbao648Fragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.init.2
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
                Sanbao648Fragment sanbao648Fragment = Sanbao648Fragment.this;
                Intrinsics.checkNotNull(str);
                sanbao648Fragment.setName(str);
                Sanbao648Fragment.this.setPage(1);
                Sanbao648Fragment.this.getAdapter().setNewInstance(null);
                Sanbao648Fragment.this.getGame();
            }
        }));
        getMBinding().rvGame.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                Sanbao648Fragment.init$lambda$0(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Sanbao648Fragment.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getGiftAdapter().addChildClickViewIds(R.id.btn);
        getGiftAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Sanbao648Fragment.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getGiftAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Sanbao648Fragment.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getGiftAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                Sanbao648Fragment.init$lambda$4(this.f$0);
            }
        });
        getCouponAdapter().addChildClickViewIds(R.id.btn);
        getCouponAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Sanbao648Fragment.init$lambda$5(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getCouponAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Sanbao648Fragment.init$lambda$6(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getCouponAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$$ExternalSyntheticLambda7
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                Sanbao648Fragment.init$lambda$7(this.f$0);
            }
        });
        Repository.INSTANCE.getSanbao648Type(new AnonymousClass11(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.init.12
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
                Sanbao648Fragment.this.netFail(it);
            }
        });
        getGame();
        Repository.INSTANCE.getSanbaoRule(2, new Function1<SanbaoRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.init.13
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SanbaoRuleBean sanbaoRuleBean) {
                invoke2(sanbaoRuleBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SanbaoRuleBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Sanbao648Fragment.access$getMBinding(Sanbao648Fragment.this).vf.setAdapter(new SanbaoMessageAdapter(Sanbao648Fragment.this.getMContext(), it.getWelfareUsers()));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.init.14
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
                Sanbao648Fragment.this.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(Sanbao648Fragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getGame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(final Sanbao648Fragment this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (Constant.INSTANCE.getLogged()) {
            new NoviceWelfareDialog(this$0.getMContext()).setData(this$0.getAdapter().getItem(i)).setReceiveListener(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$init$4$1
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
                    BaseActivity mContext = this.this$0.getMContext();
                    AppInfo appInfo = this.this$0.getAdapter().getItem(i).getAppInfo();
                    if (Util.isAPPInstalled(mContext, appInfo != null ? appInfo.getPackage() : null)) {
                        return;
                    }
                    this.this$0.toast("正在下载游戏");
                    this.this$0.download(i);
                }
            }).show();
        } else {
            this$0.getResultLauncher().launch(new Intent(this$0.getMContext(), (Class<?>) LoginActivity.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(Sanbao648Fragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Util.copy(this$0.getMContext(), this$0.getGiftAdapter().getItem(i).getGift_code());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(Sanbao648Fragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getGiftAdapter().getItem(i).setSelected(!this$0.getGiftAdapter().getItem(i).getSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(Sanbao648Fragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getGift();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(Sanbao648Fragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        BaseActivity<?> mContext = this$0.getMContext();
        Integer gameId = this$0.getCouponAdapter().getItem(i).getGameId();
        Intrinsics.checkNotNull(gameId);
        Util.gotoGame(mContext, gameId.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$6(Sanbao648Fragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getCouponAdapter().getItem(i).setSelected(!this$0.getCouponAdapter().getItem(i).getSelected());
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$init$11, reason: invalid class name */
    /* JADX INFO: compiled from: Sanbao648Fragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass11 extends Lambda implements Function1<List<TypeBean>, Unit> {
        AnonymousClass11() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<TypeBean> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<TypeBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.get(0).setSelected(true);
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_type, it);
            Sanbao648Fragment.access$getMBinding(Sanbao648Fragment.this).rvType.setAdapter(baseAdapter);
            final Sanbao648Fragment sanbao648Fragment = Sanbao648Fragment.this;
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$init$11$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    Sanbao648Fragment.AnonymousClass11.invoke$lambda$0(baseAdapter, sanbao648Fragment, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$0(BaseAdapter typeAdapter, Sanbao648Fragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(typeAdapter, "$typeAdapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            for (T t : typeAdapter.getData()) {
                t.setSelected(Intrinsics.areEqual(t, typeAdapter.getItem(i)));
            }
            this$0.setTypeId(((TypeBean) typeAdapter.getItem(i)).getId());
            this$0.getVm().getHomeFun().setValue("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$7(Sanbao648Fragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getCoupon();
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$download$1, reason: invalid class name */
    /* JADX INFO: compiled from: Sanbao648Fragment.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/DownloadResult;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<DownloadResult, Unit> {
        final /* synthetic */ int $position;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i) {
            super(1);
            this.$position = i;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DownloadResult downloadResult) {
            invoke2(downloadResult);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DownloadResult it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (Intrinsics.areEqual(it.getType(), "url")) {
                GetRequest getRequest = OkGo.get(it.getContent());
                AppInfo appInfo = Sanbao648Fragment.this.getAdapter().getItem(this.$position).getAppInfo();
                NoviceGameBean item = Sanbao648Fragment.this.getAdapter().getItem(this.$position);
                if (appInfo != null) {
                    appInfo.setIcon(item.getIcon());
                }
                if (appInfo != null) {
                    appInfo.setName(item.getName());
                }
                final DownloadTask downloadTaskSave = OkDownload.request(String.valueOf(Sanbao648Fragment.this.getAdapter().getItem(this.$position).getId()), getRequest).extra1(appInfo).save();
                String strValueOf = String.valueOf(Sanbao648Fragment.this.getAdapter().getItem(this.$position).getId());
                final Sanbao648Fragment sanbao648Fragment = Sanbao648Fragment.this;
                downloadTaskSave.register(new DownloadListener(strValueOf) { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.download.1.2
                    @Override // com.lzy.okserver.ProgressListener
                    public void onProgress(Progress p) {
                        Intrinsics.checkNotNullParameter(p, "p");
                    }

                    @Override // com.lzy.okserver.ProgressListener
                    public void onRemove(Progress p) {
                        Intrinsics.checkNotNullParameter(p, "p");
                    }

                    @Override // com.lzy.okserver.ProgressListener
                    public void onStart(Progress p) {
                        Intrinsics.checkNotNullParameter(p, "p");
                    }

                    @Override // com.lzy.okserver.ProgressListener
                    public void onError(Progress p) {
                        Intrinsics.checkNotNullParameter(p, "p");
                        Sanbao648Fragment sanbao648Fragment2 = sanbao648Fragment;
                        String localizedMessage = p.exception.getLocalizedMessage();
                        Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                        sanbao648Fragment2.log(localizedMessage);
                    }

                    @Override // com.lzy.okserver.ProgressListener
                    public void onFinish(File apk, Progress p) {
                        Intrinsics.checkNotNullParameter(apk, "apk");
                        Intrinsics.checkNotNullParameter(p, "p");
                        Util.installApk(sanbao648Fragment.getMContext(), apk, downloadTaskSave);
                    }
                });
                Sanbao648Fragment.this.getMContext().startService(new Intent(Sanbao648Fragment.this.getMContext(), (Class<?>) DownloadService.class));
                Intrinsics.checkNotNull(downloadTaskSave);
                downloadTaskSave.start();
                OkDownload okDownload = OkDownload.getInstance();
                final Sanbao648Fragment sanbao648Fragment2 = Sanbao648Fragment.this;
                okDownload.addOnAllTaskEndListener(new XExecutor.OnAllTaskEndListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment$download$1$$ExternalSyntheticLambda0
                    @Override // com.lzy.okserver.task.XExecutor.OnAllTaskEndListener
                    public final void onAllTaskEnd() {
                        Sanbao648Fragment.AnonymousClass1.invoke$lambda$1(sanbao648Fragment2);
                    }
                });
                return;
            }
            Sanbao648Fragment.this.toast("正在分包中，请稍后再试");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(Sanbao648Fragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getMContext().stopService(new Intent(this$0.getMContext(), (Class<?>) DownloadService.class));
        }
    }

    public final void download(int position) {
        Repository.INSTANCE.requestGameDownloadUrl(getAdapter().getItem(position).getId(), Intrinsics.areEqual(getAdapter().getItem(position).getType(), "gameweb"), new AnonymousClass1(position), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.download.2
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
                Sanbao648Fragment.this.netFail(it);
            }
        });
    }

    public final void getGame() {
        Repository.INSTANCE.getSanbao648(this.page, this.typeId, this.name, new Function1<PageBean<NoviceGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.getGame.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<NoviceGameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<NoviceGameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (Sanbao648Fragment.this.getPage() == 1) {
                    Sanbao648Fragment.this.getAdapter().setNewInstance(it.getList());
                } else {
                    Sanbao648Fragment.this.getAdapter().addData(it.getList());
                }
                Sanbao648Fragment sanbao648Fragment = Sanbao648Fragment.this;
                sanbao648Fragment.setPage(sanbao648Fragment.getPage() + 1);
                sanbao648Fragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(Sanbao648Fragment.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    Sanbao648Fragment.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.getGame.2
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
                Sanbao648Fragment.this.netFail(it);
                Sanbao648Fragment.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void getCoupon() {
        Repository.INSTANCE.getMyCoupon(this.page, 0, new Function1<PageBean<CouponBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.getCoupon.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<CouponBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<CouponBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (Sanbao648Fragment.this.getPage() == 1) {
                    Sanbao648Fragment.this.getCouponAdapter().setNewInstance(it.getList());
                } else {
                    Sanbao648Fragment.this.getCouponAdapter().addData(it.getList());
                }
                Sanbao648Fragment sanbao648Fragment = Sanbao648Fragment.this;
                sanbao648Fragment.setPage(sanbao648Fragment.getPage() + 1);
                sanbao648Fragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(Sanbao648Fragment.this.getCouponAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    Sanbao648Fragment.this.getCouponAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.getCoupon.2
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
                Sanbao648Fragment.this.getCouponAdapter().getLoadMoreModule().loadMoreFail();
                Sanbao648Fragment.this.netFail(it);
            }
        }, 1);
    }

    public final void getGift() {
        Repository.INSTANCE.getMyGift(this.page, new Function1<PageBean<GiftBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.getGift.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GiftBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GiftBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (Sanbao648Fragment.this.getPage() == 1) {
                    Sanbao648Fragment.this.getGiftAdapter().setNewInstance(it.getList());
                } else {
                    Sanbao648Fragment.this.getGiftAdapter().addData(it.getList());
                }
                Sanbao648Fragment sanbao648Fragment = Sanbao648Fragment.this;
                sanbao648Fragment.setPage(sanbao648Fragment.getPage() + 1);
                sanbao648Fragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(Sanbao648Fragment.this.getGiftAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    Sanbao648Fragment.this.getGiftAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.getGift.2
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
                Sanbao648Fragment.this.getGiftAdapter().getLoadMoreModule().loadMoreFail();
                Sanbao648Fragment.this.netFail(it);
            }
        }, 1);
    }

    public final void toMy() {
        this.page = 1;
        getMBinding().setPosition(1);
        getMBinding().ll.setBackgroundResource(R.mipmap.bg_sanbao_648_title2);
        if (getMBinding().getGift()) {
            getMBinding().tvGift.performClick();
        } else {
            getMBinding().tvCoupon.performClick();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                this.page = 1;
                getMBinding().setPosition(0);
                getMBinding().ll.setBackgroundResource(R.mipmap.bg_sanbao_648_title1);
                getGame();
                break;
            case R.id.tv2 /* 2131362646 */:
                if (Constant.INSTANCE.getLogged()) {
                    toMy();
                } else {
                    BaseActivity<?> mContext = getMContext();
                    Intrinsics.checkNotNull(mContext, "null cannot be cast to non-null type com.cy.yyjia.zhe28.ui.activity.SanbaoActivity");
                    ((SanbaoActivity) mContext).toLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.onClick.1
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
                            Sanbao648Fragment.this.toMy();
                        }
                    });
                }
                break;
            case R.id.tv_coupon /* 2131362677 */:
                this.page = 1;
                getMBinding().setGift(false);
                getMBinding().rvMy.setAdapter(getCouponAdapter());
                getCoupon();
                break;
            case R.id.tv_gift /* 2131362713 */:
                this.page = 1;
                getMBinding().setGift(true);
                getMBinding().rvMy.setAdapter(getGiftAdapter());
                getGift();
                break;
            case R.id.tv_rule /* 2131362764 */:
                Repository.INSTANCE.getSanbaoRule(getMBinding().getPosition(), new Function1<SanbaoRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.onClick.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SanbaoRuleBean sanbaoRuleBean) {
                        invoke2(sanbaoRuleBean);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SanbaoRuleBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        new RuleDialog(Sanbao648Fragment.this.getMContext()).setText(it.getWelfare648().getRule()).show();
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.Sanbao648Fragment.onClick.3
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
                        Sanbao648Fragment.this.netFail(it);
                    }
                });
                break;
        }
    }
}
