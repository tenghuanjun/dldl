package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentBbs2Binding;
import com.cy.yyjia.zhe28.databinding.ItemBbsBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsTopBinding;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity;
import com.cy.yyjia.zhe28.ui.activity.BbsEditActivity;
import com.cy.yyjia.zhe28.ui.activity.BbsSearchActivity;
import com.cy.yyjia.zhe28.ui.dialog.BbsSignDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsFragment2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010$\u001a\u00020\u0015J\u0006\u0010%\u001a\u00020\u0015J\u0006\u0010&\u001a\u00020\u0015J\u001c\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\b\u0010)\u001a\u00020\u0015H\u0016J\u0006\u0010*\u001a\u00020\u0015J\u0010\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020-H\u0016R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0006R\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0006R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0006R\u001c\u0010\u001d\u001a\u0010\u0012\f\u0012\n  *\u0004\u0018\u00010\u001f0\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\"0\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\f¨\u0006."}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/BbsFragment2;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentBbs2Binding;", "Landroid/view/View$OnClickListener;", "bbsId", "", "(I)V", "bbsAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsBinding;", "getBbsAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "getBbsId", "()I", "setBbsId", "cateId", "getCateId", "setCateId", "delCallback", "Lkotlin/Function0;", "", "getDelCallback", "()Lkotlin/jvm/functions/Function0;", "setDelCallback", "(Lkotlin/jvm/functions/Function0;)V", "page", "getPage", "setPage", "startForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "topAdapter", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsTopBinding;", "getTopAdapter", "getBbs", "getBlockDetail", "getNewBbs", "go", "id", "init", "initRv", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsFragment2 extends BaseFragment<FragmentBbs2Binding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final BaseAdapter<BbsBean, ItemBbsBinding> bbsAdapter;
    private int bbsId;
    private int cateId;
    private Function0<Unit> delCallback;
    private int page;
    private final ActivityResultLauncher<Intent> startForResult;
    private final BaseAdapter<BbsBean, ItemBbsTopBinding> topAdapter;

    public BbsFragment2(int i) {
        super(R.layout.fragment_bbs2);
        this.bbsId = i;
        this.bbsAdapter = new BaseAdapter<>(R.layout.item_bbs, null, 2, null);
        this.topAdapter = new BaseAdapter<>(R.layout.item_bbs_top, null, 2, null);
        this.page = 1;
        this.delCallback = new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$delCallback$1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        };
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$startForResult$1
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

    public static final /* synthetic */ FragmentBbs2Binding access$getMBinding(BbsFragment2 bbsFragment2) {
        return bbsFragment2.getMBinding();
    }

    public final int getBbsId() {
        return this.bbsId;
    }

    public final void setBbsId(int i) {
        this.bbsId = i;
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
        getMBinding().abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$$ExternalSyntheticLambda3
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                BbsFragment2.init$lambda$0(this.f$0, appBarLayout, i);
            }
        });
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2.init.2
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                BbsFragment2.this.setCateId(Integer.parseInt(String.valueOf(tab.getTag())));
                BbsFragment2.this.getNewBbs();
            }
        });
        initRv();
        getBlockDetail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BbsFragment2 this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().setFold(Math.abs(i) / appBarLayout.getTotalScrollRange());
        Math.abs(i);
        appBarLayout.getTotalScrollRange();
    }

    public final void initRv() {
        getMBinding().rvTop.setAdapter(this.topAdapter);
        this.topAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsFragment2.initRv$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rvBbs.setAdapter(this.bbsAdapter);
        BaseAdapter.setMyEmptyView$default(this.bbsAdapter, null, 1, null);
        this.bbsAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsFragment2.initRv$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.bbsAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BbsFragment2.initRv$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$1(final BbsFragment2 this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (this$0.getMBinding().getFold() == 0.0f) {
            this$0.go(this$0.topAdapter.getItem(i).getId(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$initRv$1$1
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$2(final BbsFragment2 this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.go(this$0.bbsAdapter.getItem(i).getId(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2$initRv$2$1
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
    public static final void initRv$lambda$3(BbsFragment2 this$0) {
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

    public final void getBlockDetail() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("id", String.valueOf(this.bbsId));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsFragment2$getBlockDetail$$inlined$get$1("bbs/detailById", linkedHashMap, null, this, this), 3, null);
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
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsFragment2$getBbs$$inlined$get$1("bbs/listById", linkedHashMap, null, this, this), 3, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.iv_edit /* 2131362173 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsFragment2.onClick.1
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
                        Intent intent = new Intent(BbsFragment2.this.getMContext(), (Class<?>) BbsEditActivity.class);
                        BbsDetailBean data = BbsFragment2.access$getMBinding(BbsFragment2.this).getData();
                        Intrinsics.checkNotNull(data);
                        intent.putExtra("data", data);
                        intent.putExtra("cate", BbsFragment2.this.getCateId());
                        BbsFragment2.this.startForResult.launch(intent);
                    }
                });
                break;
            case R.id.iv_refresh /* 2131362189 */:
                getNewBbs();
                break;
            case R.id.tv_search /* 2131362768 */:
                Intent intent = new Intent(getMContext(), (Class<?>) BbsSearchActivity.class);
                intent.putExtra("bbsId", this.bbsId);
                startActivity(intent);
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
