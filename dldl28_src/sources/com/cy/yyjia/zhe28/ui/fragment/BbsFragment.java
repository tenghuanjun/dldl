package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentBbsBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsTopBinding;
import com.cy.yyjia.zhe28.domain.BbsBannerBean;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.BbsBlockBean;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity;
import com.cy.yyjia.zhe28.ui.activity.BbsEditActivity;
import com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity;
import com.cy.yyjia.zhe28.ui.dialog.BbsSignDialog;
import com.cy.yyjia.zhe28.ui.fragment.BbsFragment;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.view.BbsBannerHolder;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010%\u001a\u00020\u0016J\u0006\u0010&\u001a\u00020\u0016J\u0006\u0010'\u001a\u00020\u0016J\u0006\u0010(\u001a\u00020\u0016J\u0006\u0010)\u001a\u00020\u0016J\u001c\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\b\u0010,\u001a\u00020\u0016H\u0016J\u0006\u0010-\u001a\u00020\u0016J\u0010\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u000200H\u0016R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0010R\u001c\u0010\u001e\u001a\u0010\u0012\f\u0012\n !*\u0004\u0018\u00010 0 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020#0\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\n¨\u00061"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/BbsFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentBbsBinding;", "Landroid/view/View$OnClickListener;", "()V", "bbsAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsBinding;", "getBbsAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "bbsId", "", "getBbsId", "()I", "setBbsId", "(I)V", "cateId", "getCateId", "setCateId", "delCallback", "Lkotlin/Function0;", "", "getDelCallback", "()Lkotlin/jvm/functions/Function0;", "setDelCallback", "(Lkotlin/jvm/functions/Function0;)V", "page", "getPage", "setPage", "startForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "topAdapter", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsTopBinding;", "getTopAdapter", "getBanner", "getBbs", "getBlockDetail", "getIndex", "getNewBbs", "go", "id", "init", "initRv", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsFragment extends BaseFragment<FragmentBbsBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final BaseAdapter<BbsBean, ItemBbsBinding> bbsAdapter;
    private int bbsId;
    private int cateId;
    private Function0<Unit> delCallback;
    private int page;
    private final ActivityResultLauncher<Intent> startForResult;
    private final BaseAdapter<BbsBean, ItemBbsTopBinding> topAdapter;

    public BbsFragment() {
        super(R.layout.fragment_bbs);
        this.bbsAdapter = new BaseAdapter<>(R.layout.item_bbs, new Function3<BaseDataBindingHolder<ItemBbsBinding>, Integer, BbsBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$bbsAdapter$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemBbsBinding> baseDataBindingHolder, Integer num, BbsBean bbsBean) {
                invoke(baseDataBindingHolder, num.intValue(), bbsBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemBbsBinding> baseDataBindingHolder, int i, BbsBean bbsBean) {
                Intrinsics.checkNotNullParameter(baseDataBindingHolder, "<anonymous parameter 0>");
                if (bbsBean != null) {
                    bbsBean.initContent();
                }
            }
        });
        this.topAdapter = new BaseAdapter<>(R.layout.item_bbs_top, null, 2, null);
        this.page = 1;
        this.delCallback = new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$delCallback$1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        };
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$startForResult$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                int resultCode = result.getResultCode();
                if (resultCode == 2914) {
                    this.this$0.getDelCallback().invoke();
                } else {
                    if (resultCode != 2915) {
                        return;
                    }
                    this.this$0.getNewBbs();
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.startForResult = activityResultLauncherRegisterForActivityResult;
    }

    public static final /* synthetic */ FragmentBbsBinding access$getMBinding(BbsFragment bbsFragment) {
        return bbsFragment.getMBinding();
    }

    public final BaseAdapter<BbsBean, ItemBbsBinding> getBbsAdapter() {
        return this.bbsAdapter;
    }

    public final BaseAdapter<BbsBean, ItemBbsTopBinding> getTopAdapter() {
        return this.topAdapter;
    }

    public final int getCateId() {
        return this.cateId;
    }

    public final void setCateId(int i) {
        this.cateId = i;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getBbsId() {
        return this.bbsId;
    }

    public final void setBbsId(int i) {
        this.bbsId = i;
    }

    public final Function0<Unit> getDelCallback() {
        return this.delCallback;
    }

    public final void setDelCallback(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.delCallback = function0;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setOnClick(this);
        getMBinding().abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                BbsFragment.init$lambda$0(this.f$0, appBarLayout, i);
            }
        });
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment.init.2
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                BbsFragment.this.setCateId(Integer.parseInt(String.valueOf(tab.getTag())));
                BbsFragment.this.getNewBbs();
            }
        });
        initRv();
        getIndex();
        getBanner();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BbsFragment this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().setFold(Math.abs(i) / appBarLayout.getTotalScrollRange());
        Math.abs(i);
        appBarLayout.getTotalScrollRange();
    }

    public final void initRv() {
        getMBinding().rvTop.setAdapter(this.topAdapter);
        this.topAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsFragment.initRv$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rvBbs.setAdapter(this.bbsAdapter);
        BaseAdapter.setMyEmptyView$default(this.bbsAdapter, null, 1, null);
        this.bbsAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsFragment.initRv$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.bbsAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BbsFragment.initRv$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$1(final BbsFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (this$0.getMBinding().getFold() == 1.0f) {
            return;
        }
        this$0.go(this$0.topAdapter.getItem(i).getId(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$initRv$1$1
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
                this.this$0.getTopAdapter().removeAt(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$2(final BbsFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.go(this$0.bbsAdapter.getItem(i).getId(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$initRv$2$1
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
                this.this$0.getBbsAdapter().removeAt(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$3(BbsFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBbs();
    }

    public final void go(int id, Function0<Unit> delCallback) {
        Intrinsics.checkNotNullParameter(delCallback, "delCallback");
        this.delCallback = delCallback;
        Intent intent = new Intent(getMContext(), (Class<?>) BbsDetailActivity.class);
        intent.putExtra("id", id);
        this.startForResult.launch(intent);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$getIndex$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BbsFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/BbsBlockBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C11261 extends Lambda implements Function1<List<BbsBlockBean>, Unit> {
        C11261() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<BbsBlockBean> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<BbsBlockBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.get(2).getList().get(0).setSelected(true);
            BbsFragment.this.setBbsId(it.get(2).getList().get(0).getId());
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_bbs_cate, it.get(2).getList());
            BbsFragment.access$getMBinding(BbsFragment.this).rv.setAdapter(baseAdapter);
            final BbsFragment bbsFragment = BbsFragment.this;
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$getIndex$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    BbsFragment.C11261.invoke$lambda$0(baseAdapter, bbsFragment, baseQuickAdapter, view, i);
                }
            });
            BbsFragment.this.getBlockDetail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$0(BaseAdapter adapter, BbsFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "$adapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            int size = adapter.getData().size();
            int i2 = 0;
            while (i2 < size) {
                ((BbsBlockBean.ListBean) adapter.getItem(i2)).setSelected(i2 == i);
                i2++;
            }
            this$0.setBbsId(((BbsBlockBean.ListBean) adapter.getItem(i)).getId());
            adapter.notifyDataSetChanged();
            this$0.getBlockDetail();
        }
    }

    public final void getIndex() {
        Repository.INSTANCE.getBbsIndex(new C11261(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment.getIndex.2
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
                BbsFragment.this.netFail(it);
            }
        });
    }

    public final void getBlockDetail() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("id", String.valueOf(this.bbsId));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsFragment$getBlockDetail$$inlined$get$1("bbs/detailById", linkedHashMap, null, this, this), 3, null);
    }

    public final void getNewBbs() {
        this.page = 1;
        this.bbsAdapter.setNewInstance(null);
        getBbs();
    }

    public final void getBbs() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        BbsDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        linkedHashMap.put("id", String.valueOf(data.getInfo().getId()));
        linkedHashMap.put("cate", String.valueOf(this.cateId));
        linkedHashMap.put("page", String.valueOf(this.page));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsFragment$getBbs$$inlined$get$1("bbs/listById", linkedHashMap, null, this, this), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$getBanner$1, reason: invalid class name */
    /* JADX INFO: compiled from: BbsFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/BbsBannerBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<List<BbsBannerBean>, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<BbsBannerBean> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<BbsBannerBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            BbsFragment.access$getMBinding(BbsFragment.this).banner.setCanLoop(it.size() > 1);
            BbsFragment.access$getMBinding(BbsFragment.this).banner.setPointViewVisible(it.size() > 1);
            BbsFragment.access$getMBinding(BbsFragment.this).banner.setPages(new CBViewHolderCreator() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment$getBanner$1$$ExternalSyntheticLambda0
                @Override // com.bigkoo.convenientbanner.holder.CBViewHolderCreator
                public final Object createHolder() {
                    return BbsFragment.AnonymousClass1.invoke$lambda$0();
                }
            }, it).setPageIndicator(new int[]{R.mipmap.ic_indicator, R.mipmap.ic_indicator_true}).startTurning(2000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BbsBannerHolder invoke$lambda$0() {
            return new BbsBannerHolder();
        }
    }

    public final void getBanner() {
        Repository.INSTANCE.getBbsBanner(new AnonymousClass1(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment.getBanner.2
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
                BbsFragment.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.iv_edit /* 2131362173 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment.onClick.1
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
                        Intent intent = new Intent(BbsFragment.this.getMContext(), (Class<?>) BbsEditActivity.class);
                        BbsDetailBean data = BbsFragment.access$getMBinding(BbsFragment.this).getData();
                        Intrinsics.checkNotNull(data);
                        intent.putExtra("data", data);
                        intent.putExtra("cate", BbsFragment.this.getCateId());
                        BbsFragment.this.startForResult.launch(intent);
                    }
                });
                break;
            case R.id.iv_refresh /* 2131362189 */:
                getNewBbs();
                break;
            case R.id.tv_search /* 2131362768 */:
                startActivity(BbsSearchActivity.class);
                break;
            case R.id.tv_sign /* 2131362774 */:
                BaseActivity<?> mContext = getMContext();
                BbsDetailBean data = getMBinding().getData();
                Intrinsics.checkNotNull(data);
                new BbsSignDialog(mContext, data).show();
                break;
        }
    }
}
