package com.cy.yyjia.zhe28.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityGameDetailBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro1Binding;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro2Binding;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro3Binding;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro4Binding;
import com.cy.yyjia.zhe28.databinding.LayoutGameDetailTabBinding;
import com.cy.yyjia.zhe28.domain.AppInfo;
import com.cy.yyjia.zhe28.domain.DownloadResult;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GameBannerBean;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.GameScoreBean;
import com.cy.yyjia.zhe28.domain.GameViewModel;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.YunApplyResult;
import com.cy.yyjia.zhe28.service.DownloadService;
import com.cy.yyjia.zhe28.ui.activity.GameDetailActivity;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.ShareDialog;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.cy.yyjia.zhe28.ui.fragment.GameCommentFragment;
import com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment;
import com.cy.yyjia.zhe28.ui.fragment.GameServerFragment;
import com.cy.yyjia.zhe28.ui.fragment.GameToolFragment;
import com.cy.yyjia.zhe28.util.CalendarUtil;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.AutoHeightPagerAdapter;
import com.cy.yyjia.zhe28.view.AutoHeightViewPager;
import com.cy.yyjia.zhe28.view.Navigation;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.hjq.shape.view.ShapeTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.task.XExecutor;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import eightbitlab.com.blurview.RenderScriptBlur;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/* JADX INFO: compiled from: GameDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001bJ\u0006\u0010 \u001a\u00020\u001bJ\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\u001bH\u0016J\u0006\u0010$\u001a\u00020\u001bJ\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020\u001bJ\u000e\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020&H\u0016J\b\u0010-\u001a\u00020\u001bH\u0014J\b\u0010.\u001a\u00020\u001bH\u0014J\u0006\u0010/\u001a\u00020\u001bJ\u0006\u00100\u001a\u00020\u001bJ\u000e\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\fJ\u0006\u00103\u001a\u00020\u001bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018¨\u00064"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGameDetailBinding;", "Landroid/view/View$OnClickListener;", "()V", "downloadTask", "Lcom/lzy/okserver/download/DownloadTask;", "getDownloadTask", "()Lcom/lzy/okserver/download/DownloadTask;", "setDownloadTask", "(Lcom/lzy/okserver/download/DownloadTask;)V", "id", "", "getId", "()I", "id$delegate", "Lkotlin/Lazy;", ImageSelector.POSITION, "", "getPosition", "()Ljava/util/List;", "vm", "Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "vm$delegate", "checkInstall", "", "download", "downloadUrl", "", "downloadEvent", "getData", "getDownloadListener", "Lcom/lzy/okserver/download/DownloadListener;", "init", "initBanner", "initPicView", "Landroid/view/View;", "initTab", "install", "apk", "Ljava/io/File;", "onClick", "v", "onDestroy", "onResume", "requestDownloadUrl", "reserve", "select", "p", "share", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameDetailActivity extends BaseActivity<ActivityGameDetailBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private DownloadTask downloadTask;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;
    private final List<Integer> position;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public static final /* synthetic */ ActivityGameDetailBinding access$getMBinding(GameDetailActivity gameDetailActivity) {
        return gameDetailActivity.getMBinding();
    }

    public GameDetailActivity() {
        super(R.layout.activity_game_detail, 1);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.vm = LazyKt.lazy(new Function0<GameViewModel>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameViewModel invoke() {
                return (GameViewModel) new ViewModelProvider(this.this$0).get(GameViewModel.class);
            }
        });
        this.position = CollectionsKt.mutableListOf(0, 1, 2, 3);
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final GameViewModel getVm() {
        return (GameViewModel) this.vm.getValue();
    }

    public final DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    public final void setDownloadTask(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    public final List<Integer> getPosition() {
        return this.position;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        GameDetailActivity gameDetailActivity = this;
        Toolbar toolbar = getMBinding().toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "toolbar");
        BaseActivity.setViewFitsSystemWindows$default(gameDetailActivity, toolbar, false, 2, null);
        AutoHeightViewPager vp2 = getMBinding().vp2;
        Intrinsics.checkNotNullExpressionValue(vp2, "vp2");
        BaseActivity.setViewFitsSystemWindows$default(gameDetailActivity, vp2, false, 2, null);
        Navigation navigation = getMBinding().navigation;
        Intrinsics.checkNotNullExpressionValue(navigation, "navigation");
        setViewFitsSystemWindows(navigation, true);
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        getMBinding().bv.setupWith(getMBinding().cl, new RenderScriptBlur(this)).setFrameClearDrawable(decorView.getBackground()).setBlurRadius(5.0f);
        getMBinding().setPosition(0);
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameDetailActivity.init$lambda$0(this.f$0, view);
            }
        });
        GameDetailActivity gameDetailActivity2 = this;
        getVm().getData().observe(gameDetailActivity2, new GameDetailActivity$sam$androidx_lifecycle_Observer$0(new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailBean gameDetailBean) {
                invoke2(gameDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailBean gameDetailBean) {
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setData(gameDetailBean);
                GameDetailActivity.this.initBanner();
                GameDetailActivity.this.checkInstall();
            }
        }));
        getVm().getCurrentPosition().setValue(0);
        getVm().getCurrentPosition().observe(gameDetailActivity2, new GameDetailActivity$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.init.3
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
                ViewPager viewPager = GameDetailActivity.access$getMBinding(GameDetailActivity.this).vp;
                Intrinsics.checkNotNull(num);
                viewPager.setCurrentItem(num.intValue(), false);
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setPosition(GameDetailActivity.this.getPosition().get(num.intValue()).intValue());
            }
        }));
        getMBinding().vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.init.4
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                GameDetailActivity.this.getVm().getCurrentPosition().postValue(Integer.valueOf(position));
            }
        });
        getMBinding().appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$$ExternalSyntheticLambda2
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                GameDetailActivity.init$lambda$1(this.f$0, appBarLayout, i);
            }
        });
        getMBinding().btnComment.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameDetailActivity.init$lambda$2(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameDetailActivity this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().setFold(Math.abs(i) == appBarLayout.getTotalScrollRange());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(GameDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Constant.INSTANCE.getLogged()) {
            Intent intent = new Intent(this$0.getMContext(), (Class<?>) CommentEditActivity.class);
            GameDetailBean value = this$0.getVm().getData().getValue();
            intent.putExtra("gid", value != null ? Integer.valueOf(value.getId()) : null);
            this$0.startActivity(intent);
            return;
        }
        this$0.startActivity(LoginActivity.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        DownloadTask task = OkDownload.getInstance().getTask(String.valueOf(getId()));
        this.downloadTask = task;
        if (task != null) {
            task.register(getDownloadListener());
            getMBinding().setProgress(task.progress);
        }
        checkInstall();
    }

    public final void getData() {
        Repository.INSTANCE.getGameDetail(getId(), new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailBean gameDetailBean) {
                invoke2(gameDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                final List listMutableListOf = CollectionsKt.mutableListOf(new GameIntroFragment(), new GameCommentFragment(), new GameToolFragment(), new GameServerFragment());
                if (TextUtils.isEmpty(it.getServer_img())) {
                    listMutableListOf.remove(3);
                }
                if (it.getIsTool() == 0) {
                    listMutableListOf.remove(2);
                }
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).vp.setAdapter(new FragmentStatePagerAdapter(GameDetailActivity.this.getSupportFragmentManager()) { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.getData.1.1
                    @Override // androidx.viewpager.widget.PagerAdapter
                    public int getCount() {
                        return listMutableListOf.size();
                    }

                    @Override // androidx.fragment.app.FragmentStatePagerAdapter
                    public Fragment getItem(int position) {
                        return listMutableListOf.get(position);
                    }
                });
                GameDetailActivity.this.getVm().getData().setValue(it);
                if (it.getIsTool() == 0) {
                    GameDetailActivity.this.getPosition().remove((Object) 2);
                }
                if (TextUtils.isEmpty(it.getServer_img())) {
                    GameDetailActivity.this.getPosition().remove((Object) 3);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.getData.2
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
                GameDetailActivity.this.netFail(it);
            }
        });
        Repository.INSTANCE.getGameScore(getId(), new Function1<GameScoreBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.getData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameScoreBean gameScoreBean) {
                invoke2(gameScoreBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameScoreBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameDetailActivity.this.getVm().getScore().setValue(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.getData.4
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
                GameDetailActivity.this.netFail(it);
            }
        });
    }

    public final void select(int p) {
        int size = this.position.size();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            if (this.position.get(i2).intValue() == p) {
                i = i2;
                break;
            }
            i2++;
        }
        getVm().getCurrentPosition().postValue(Integer.valueOf(i));
        GSYVideoManager.releaseAllVideos();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        final GameDetailBean data = getMBinding().getData();
        if (data != null) {
            switch (v.getId()) {
                case R.id.rl_download /* 2131362444 */:
                    if (!checkClick()) {
                        downloadEvent();
                    }
                    break;
                case R.id.tv0 /* 2131362644 */:
                    select(0);
                    break;
                case R.id.tv1 /* 2131362645 */:
                    select(1);
                    break;
                case R.id.tv2 /* 2131362646 */:
                    select(2);
                    break;
                case R.id.tv3 /* 2131362647 */:
                    select(3);
                    break;
                case R.id.tv_gm /* 2131362714 */:
                    Intent intent = new Intent(getMContext(), (Class<?>) GMActivity.class);
                    intent.putExtra("game", new GMGameBean(data));
                    startActivity(intent);
                    break;
                case R.id.tv_open /* 2131362737 */:
                    GameDetailActivity gameDetailActivity = this;
                    AppInfo appInfo = data.getAppInfo();
                    Util.openOtherApp(gameDetailActivity, appInfo != null ? appInfo.getPackage() : null);
                    break;
                case R.id.tv_order /* 2131362738 */:
                    if (!checkClick()) {
                        reserve();
                    }
                    break;
                case R.id.tv_play /* 2131362747 */:
                    if (Constant.INSTANCE.getLogged()) {
                        Util.openWebGame(getMContext(), NetUtil.BASE_URL3 + "play/" + data.getId() + ".htm");
                    } else {
                        startActivity(LoginActivity.class);
                    }
                    break;
                case R.id.tv_yun /* 2131362813 */:
                    Repository.INSTANCE.applyYunPlay(data.getId(), new Function1<YunApplyResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$onClick$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(YunApplyResult yunApplyResult) {
                            invoke2(yunApplyResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(YunApplyResult it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            Intent intent2 = new Intent(this.this$0.getMContext(), (Class<?>) YunPlayActivity.class);
                            intent2.putExtra("duration", it.getDuration());
                            intent2.putExtra("gid", data.getId());
                            intent2.putExtra("landscape", Intrinsics.areEqual(data.getScreen(), "horizontal"));
                            this.this$0.startActivity(intent2);
                        }
                    }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$onClick$1$2
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
                    break;
            }
        }
    }

    public final void downloadEvent() {
        DownloadTask downloadTask = this.downloadTask;
        if (downloadTask != null) {
            Intrinsics.checkNotNull(downloadTask);
            int i = downloadTask.progress.status;
            if (i != 0) {
                if (i == 1 || i == 2) {
                    downloadTask.pause();
                    return;
                }
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                        Util.installApk(getMContext(), new File(downloadTask.progress.folder, downloadTask.progress.fileName), this.downloadTask);
                        return;
                    }
                    try {
                        if (Intrinsics.areEqual("Software caused connection abort", downloadTask.progress.exception.getLocalizedMessage())) {
                            downloadTask.start();
                        } else {
                            toast("下载出错，请重新下载");
                            downloadTask.remove(true);
                            requestDownloadUrl();
                        }
                        return;
                    } catch (Exception unused) {
                        downloadTask.remove(true);
                        requestDownloadUrl();
                        return;
                    }
                }
            }
            downloadTask.start();
            return;
        }
        requestDownloadUrl();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void requestDownloadUrl() {
        final WaitDialog waitDialog = (WaitDialog) new WaitDialog(this).setText("正在获取文件，请耐心等待").setCancelable(false);
        waitDialog.show();
        Repository repository = Repository.INSTANCE;
        int id = getId();
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        repository.requestGameDownloadUrl(id, Intrinsics.areEqual(data.getType(), "gameweb"), new Function1<DownloadResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.requestDownloadUrl.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DownloadResult downloadResult) {
                invoke2(downloadResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                waitDialog.hide();
                if (Intrinsics.areEqual(it.getType(), "url")) {
                    this.download(it.getContent());
                } else {
                    this.toast("正在分包中，请稍后再试");
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.requestDownloadUrl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                waitDialog.hide();
                this.netFail(it);
            }
        });
    }

    public final void download(String downloadUrl) {
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        GetRequest getRequest = OkGo.get(downloadUrl);
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        AppInfo appInfo = data.getAppInfo();
        GameDetailBean data2 = getMBinding().getData();
        if (data2 != null) {
            if (appInfo != null) {
                appInfo.setIcon(data2.getIcon());
            }
            if (appInfo != null) {
                appInfo.setName(data2.getName());
            }
        }
        this.downloadTask = OkDownload.request(String.valueOf(getId()), getRequest).extra1(appInfo).save().register(getDownloadListener());
        startService(new Intent(this, (Class<?>) DownloadService.class));
        DownloadTask downloadTask = this.downloadTask;
        Intrinsics.checkNotNull(downloadTask);
        downloadTask.start();
        OkDownload.getInstance().addOnAllTaskEndListener(new XExecutor.OnAllTaskEndListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$$ExternalSyntheticLambda0
            @Override // com.lzy.okserver.task.XExecutor.OnAllTaskEndListener
            public final void onAllTaskEnd() {
                GameDetailActivity.download$lambda$7(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void download$lambda$7(GameDetailActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stopService(new Intent(this$0, (Class<?>) DownloadService.class));
    }

    public final DownloadListener getDownloadListener() {
        return new DownloadListener(String.valueOf(getId())) { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.getDownloadListener.1
            @Override // com.lzy.okserver.ProgressListener
            public void onStart(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setProgress(p);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onProgress(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setProgress(p);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onError(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setProgress(p);
                GameDetailActivity gameDetailActivity = GameDetailActivity.this;
                String localizedMessage = p.exception.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                gameDetailActivity.log(localizedMessage);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onFinish(File apk, Progress p) {
                Intrinsics.checkNotNullParameter(apk, "apk");
                Intrinsics.checkNotNullParameter(p, "p");
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setProgress(p);
                GameDetailActivity.this.log("file name is " + apk.getName());
                GameDetailActivity.this.install(apk);
            }

            @Override // com.lzy.okserver.ProgressListener
            public void onRemove(Progress p) {
                Intrinsics.checkNotNullParameter(p, "p");
                GameDetailActivity.access$getMBinding(GameDetailActivity.this).setProgress(p);
            }
        };
    }

    public final void checkInstall() {
        GameDetailBean data = getMBinding().getData();
        if (data != null) {
            ShapeTextView shapeTextView = getMBinding().tvOpen;
            FragmentActivity mContext = getMContext();
            AppInfo appInfo = data.getAppInfo();
            shapeTextView.setVisibility(Util.isAPPInstalled(mContext, appInfo != null ? appInfo.getPackage() : null) ? 0 : 8);
        }
    }

    public final void reserve() {
        CalendarUtil.INSTANCE.checkCalendarPermission(this, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.reserve.1
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
                Repository repository = Repository.INSTANCE;
                int id = GameDetailActivity.this.getId();
                final GameDetailActivity gameDetailActivity = GameDetailActivity.this;
                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.reserve.1.1
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
                        gameDetailActivity.toast(it.getMsg());
                        GameDetailBean data = GameDetailActivity.access$getMBinding(gameDetailActivity).getData();
                        Intrinsics.checkNotNull(data);
                        data.setIsreservation(1);
                        GameDetailBean data2 = GameDetailActivity.access$getMBinding(gameDetailActivity).getData();
                        Intrinsics.checkNotNull(data2);
                        List<GameDetailBean.ServiceBean> serviceList = data2.getServiceList();
                        if (serviceList != null) {
                            GameDetailActivity gameDetailActivity2 = gameDetailActivity;
                            if (serviceList.isEmpty()) {
                                return;
                            }
                            long startTime = (serviceList.get(0).getStartTime() * ((long) 1000)) - ((long) 600000);
                            GameDetailBean data3 = GameDetailActivity.access$getMBinding(gameDetailActivity2).getData();
                            Intrinsics.checkNotNull(data3);
                            String str = data3.getName() + "即将开服";
                            GameDetailBean data4 = GameDetailActivity.access$getMBinding(gameDetailActivity2).getData();
                            Intrinsics.checkNotNull(data4);
                            boolean zAddCalendarEvent = CalendarUtil.INSTANCE.addCalendarEvent(gameDetailActivity2, startTime, str, "您预约的游戏 " + data4.getName() + " 十分钟后开服");
                            StringBuilder sb = new StringBuilder("写入日历");
                            sb.append(zAddCalendarEvent);
                            gameDetailActivity2.log(sb.toString());
                        }
                    }
                };
                final GameDetailActivity gameDetailActivity2 = GameDetailActivity.this;
                repository.orderGame(id, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.reserve.1.2
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
                        gameDetailActivity2.netFail(it);
                    }
                });
            }
        }, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.reserve.2
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
                GameDetailActivity.this.tip("预约需要授予日历权限");
            }
        });
    }

    public final void share() {
        GameDetailBean data = getMBinding().getData();
        if (data != null) {
            new ShareDialog(getMContext()).setTitle(data.getName()).setDesc(data.getDescription()).setImgUrl(data.getIcon()).setGame(data.getId()).setUrl(NetUtil.BASE_URL3 + "dist/game-detail?id=" + data.getId()).show();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    public final void install(File apk) {
        Intrinsics.checkNotNullParameter(apk, "apk");
        String path = apk.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        if (!StringsKt.endsWith$default(path, ".apk", false, 2, (Object) null) || apk.length() < 1048576) {
            new ConfirmDialog(this).setTitle("下载失败").setTip("检测到下载apk失败，请重新下载").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.install.1
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
                    DownloadTask downloadTask = GameDetailActivity.this.getDownloadTask();
                    if (downloadTask != null) {
                        downloadTask.remove(true);
                    }
                    GameDetailActivity.this.requestDownloadUrl();
                }
            }).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        Log.e("installApk: ", apk.getPath());
        Uri uriForFile = FileProvider.getUriForFile(this, "com.cy.yyjia.zhe28.fileProvider", apk);
        Log.e("installApk: ", uriForFile.toString());
        String path2 = uriForFile.getPath();
        Intrinsics.checkNotNull(path2);
        Log.e("installApk: ", path2);
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        intent.addFlags(1);
        startActivity(intent);
    }

    public final void initBanner() {
        List listMutableListOf = CollectionsKt.mutableListOf("实机演示", "宣传片", "介绍图");
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (TextUtils.isEmpty(data.getVideo())) {
            listMutableListOf.remove(1);
        }
        GameDetailBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        if (TextUtils.isEmpty(data2.getVideo_real())) {
            listMutableListOf.remove(0);
        }
        AutoHeightPagerAdapter autoHeightPagerAdapter = new AutoHeightPagerAdapter();
        getMBinding().vp2.setAdapter(autoHeightPagerAdapter);
        ArrayList arrayList = new ArrayList();
        GameDetailBean data3 = getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        for (GameBannerBean gameBannerBean : data3.getVideoPicList()) {
            if (!TextUtils.isEmpty(gameBannerBean.getVideo())) {
                if (!gameBannerBean.getHorizontal()) {
                    ItemGameIntro3Binding itemGameIntro3Binding = (ItemGameIntro3Binding) DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_game_intro3, null, false);
                    itemGameIntro3Binding.setData(gameBannerBean);
                    View root = itemGameIntro3Binding.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                    arrayList.add(root);
                } else {
                    ItemGameIntro1Binding itemGameIntro1Binding = (ItemGameIntro1Binding) DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_game_intro1, null, false);
                    itemGameIntro1Binding.setData(gameBannerBean);
                    View root2 = itemGameIntro1Binding.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
                    arrayList.add(root2);
                }
            }
        }
        arrayList.add(initPicView());
        autoHeightPagerAdapter.setViews(arrayList);
        CommonNavigator commonNavigator = new CommonNavigator(getMContext());
        commonNavigator.setAdapter(new C09551(listMutableListOf, this));
        getMBinding().indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(getMBinding().indicator, getMBinding().vp2);
        getMBinding().vp2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.initBanner.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                GSYVideoManager.releaseAllVideos();
            }
        });
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$initBanner$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GameDetailActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, d2 = {"com/cy/yyjia/zhe28/ui/activity/GameDetailActivity$initBanner$1", "Lnet/lucode/hackware/magicindicator/buildins/commonnavigator/abs/CommonNavigatorAdapter;", "getCount", "", "getIndicator", "Lnet/lucode/hackware/magicindicator/buildins/commonnavigator/abs/IPagerIndicator;", "context", "Landroid/content/Context;", "getTitleView", "Lnet/lucode/hackware/magicindicator/buildins/commonnavigator/abs/IPagerTitleView;", "index", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class C09551 extends CommonNavigatorAdapter {
        final /* synthetic */ List<String> $title;
        final /* synthetic */ GameDetailActivity this$0;

        C09551(List<String> list, GameDetailActivity gameDetailActivity) {
            this.$title = list;
            this.this$0 = gameDetailActivity;
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public int getCount() {
            return this.$title.size();
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerTitleView getTitleView(Context context, final int index) {
            Intrinsics.checkNotNullParameter(context, "context");
            ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
            colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#CCCCCC"));
            colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#333333"));
            colorTransitionPagerTitleView.setText(this.$title.get(index));
            colorTransitionPagerTitleView.setTextSize(10.0f);
            final GameDetailActivity gameDetailActivity = this.this$0;
            colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$initBanner$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GameDetailActivity.C09551.getTitleView$lambda$0(gameDetailActivity, index, view);
                }
            });
            return colorTransitionPagerTitleView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void getTitleView$lambda$0(GameDetailActivity this$0, int i, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            GameDetailActivity.access$getMBinding(this$0).vp2.setCurrentItem(i);
        }

        @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
        public IPagerIndicator getIndicator(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WrapPagerIndicator wrapPagerIndicator = new WrapPagerIndicator(context);
            wrapPagerIndicator.setFillColor(-1);
            wrapPagerIndicator.setVerticalPadding(Util.dpToPx(context, 2.0f));
            wrapPagerIndicator.setHorizontalPadding(Util.dpToPx(context, 8.0f));
            return wrapPagerIndicator;
        }
    }

    public final View initPicView() {
        View root;
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        Integer gamePicScreen = data.getGamePicScreen();
        if (gamePicScreen != null && gamePicScreen.intValue() == 0) {
            root = ((ItemGameIntro4Binding) DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_game_intro4, null, false)).getRoot();
        } else {
            root = ((ItemGameIntro2Binding) DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_game_intro2, null, false)).getRoot();
        }
        Intrinsics.checkNotNull(root);
        GameDetailBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        List<GameBannerBean> videoPicList = data2.getVideoPicList();
        int size = videoPicList.size();
        while (true) {
            size--;
            if (-1 < size) {
                String video = videoPicList.get(size).getVideo();
                Intrinsics.checkNotNull(video);
                if (video.length() > 0) {
                    videoPicList.remove(size);
                }
            } else {
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_intro_pic, videoPicList);
                RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv);
                recyclerView.setAdapter(baseAdapter);
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity$$ExternalSyntheticLambda4
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        GameDetailActivity.initPicView$lambda$10(baseAdapter, this, baseQuickAdapter, view, i);
                    }
                });
                new LinearSnapHelper().attachToRecyclerView(recyclerView);
                return root;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPicView$lambda$10(BaseAdapter adapter, GameDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator it = adapter.getData().iterator();
        while (it.hasNext()) {
            arrayList.add(((GameBannerBean) it.next()).getPic());
        }
        Intent intent = new Intent(this$0, (Class<?>) ImageActivity.class);
        intent.putStringArrayListExtra("images", arrayList);
        intent.putExtra(ImageSelector.POSITION, i);
        this$0.startActivity(intent);
    }

    public final void initTab() {
        getMBinding().tab.setupWithViewPager(getMBinding().vp);
        List listMutableListOf = CollectionsKt.mutableListOf("详情", "社区", "工具箱", "区服介绍");
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (TextUtils.isEmpty(data.getServer_img())) {
            listMutableListOf.remove(3);
        }
        GameDetailBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        if (data2.getIsTool() == 0) {
            listMutableListOf.remove(2);
        }
        int size = listMutableListOf.size();
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tabAt = getMBinding().tab.getTabAt(i);
            LayoutGameDetailTabBinding layoutGameDetailTabBinding = (LayoutGameDetailTabBinding) DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_game_detail_tab, getMBinding().tab, false);
            layoutGameDetailTabBinding.setText((String) listMutableListOf.get(i));
            Intrinsics.checkNotNull(tabAt);
            layoutGameDetailTabBinding.setSelected(tabAt.isSelected());
            tabAt.setCustomView(layoutGameDetailTabBinding.getRoot());
        }
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameDetailActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                LayoutGameDetailTabBinding layoutGameDetailTabBinding2 = (LayoutGameDetailTabBinding) DataBindingUtil.getBinding(customView);
                Intrinsics.checkNotNull(layoutGameDetailTabBinding2);
                layoutGameDetailTabBinding2.setSelected(true);
                if (tab.getPosition() == 1) {
                    GameDetailActivity.access$getMBinding(GameDetailActivity.this).appBar.setExpanded(false, false);
                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                LayoutGameDetailTabBinding layoutGameDetailTabBinding2 = (LayoutGameDetailTabBinding) DataBindingUtil.getBinding(customView);
                Intrinsics.checkNotNull(layoutGameDetailTabBinding2);
                layoutGameDetailTabBinding2.setSelected(false);
            }
        });
    }
}
