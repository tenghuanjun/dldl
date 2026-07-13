package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.base.BasePopupWindow;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.FragmentGameIntroBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameDetailChatBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameVipBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.GameDetailChatBean;
import com.cy.yyjia.zhe28.domain.GameDetailExtraBean;
import com.cy.yyjia.zhe28.domain.GameViewModel;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.activity.DealListActivity;
import com.cy.yyjia.zhe28.ui.activity.GameBbsActivity;
import com.cy.yyjia.zhe28.ui.activity.GameCouponActivity;
import com.cy.yyjia.zhe28.ui.activity.GameEventActivity;
import com.cy.yyjia.zhe28.ui.activity.GameGiftActivity;
import com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.cy.yyjia.zhe28.ui.activity.TopicDetailActivity;
import com.cy.yyjia.zhe28.ui.adapter.GameDetailNewsAdapter;
import com.cy.yyjia.zhe28.ui.dialog.BottomTipDialog;
import com.cy.yyjia.zhe28.ui.dialog.GameServiceDialog;
import com.cy.yyjia.zhe28.util.CalendarUtil;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: GameIntroFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020&J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001b0)J\u0006\u0010*\u001a\u00020&J\b\u0010+\u001a\u00020&H\u0016J\u0006\u0010,\u001a\u00020&J\u0006\u0010-\u001a\u00020&J\u0010\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020&H\u0016J\b\u00102\u001a\u00020&H\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\f\u001a\u0004\b\"\u0010#¨\u00063"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameIntroFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentGameIntroBinding;", "Landroid/view/View$OnClickListener;", "()V", "chatAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameDetailChatBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameDetailChatBinding;", "getChatAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "chatAdapter$delegate", "Lkotlin/Lazy;", "newsAdapter", "Lcom/cy/yyjia/zhe28/ui/adapter/GameDetailNewsAdapter;", "getNewsAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/GameDetailNewsAdapter;", "newsAdapter$delegate", ImageSelector.POSITION, "", "getPosition", "()I", "setPosition", "(I)V", "similarGames", "", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "getSimilarGames", "()Ljava/util/List;", "setSimilarGames", "(Ljava/util/List;)V", "vm", "Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "vm$delegate", "getComments", "", "getExtra", "getNewSimilarGame", "", "getTotal", "init", "initChatRv", "initSimilarGame", "onClick", "v", "Landroid/view/View;", "onDestroy", "onPause", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameIntroFragment extends BaseFragment<FragmentGameIntroBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: chatAdapter$delegate, reason: from kotlin metadata */
    private final Lazy chatAdapter;

    /* JADX INFO: renamed from: newsAdapter$delegate, reason: from kotlin metadata */
    private final Lazy newsAdapter;
    private int position;
    public List<GameBean[]> similarGames;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public final void getComments() {
    }

    public final void getTotal() {
    }

    public GameIntroFragment() {
        super(R.layout.fragment_game_intro);
        this.vm = LazyKt.lazy(new Function0<GameViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameViewModel invoke() {
                return (GameViewModel) new ViewModelProvider(this.this$0.getMContext()).get(GameViewModel.class);
            }
        });
        this.newsAdapter = LazyKt.lazy(new Function0<GameDetailNewsAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$newsAdapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameDetailNewsAdapter invoke() {
                return new GameDetailNewsAdapter(this.this$0.getMContext());
            }
        });
        this.chatAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameDetailChatBean, ItemGameDetailChatBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$chatAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameDetailChatBean, ItemGameDetailChatBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_detail_chat, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ FragmentGameIntroBinding access$getMBinding(GameIntroFragment gameIntroFragment) {
        return gameIntroFragment.getMBinding();
    }

    public final GameViewModel getVm() {
        return (GameViewModel) this.vm.getValue();
    }

    public final List<GameBean[]> getSimilarGames() {
        List<GameBean[]> list = this.similarGames;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("similarGames");
        return null;
    }

    public final void setSimilarGames(List<GameBean[]> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.similarGames = list;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    public final GameDetailNewsAdapter getNewsAdapter() {
        return (GameDetailNewsAdapter) this.newsAdapter.getValue();
    }

    public final BaseAdapter<GameDetailChatBean, ItemGameDetailChatBinding> getChatAdapter() {
        return (BaseAdapter) this.chatAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        GameIntroFragment gameIntroFragment = this;
        getMBinding().setOnClick(gameIntroFragment);
        getMBinding().discount.discount.setOnClickListener(gameIntroFragment);
        getMBinding().setVip(false);
        GameIntroFragment gameIntroFragment2 = this;
        getVm().getData().observe(gameIntroFragment2, new GameIntroFragment$sam$androidx_lifecycle_Observer$0(new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment.init.1
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
                GameIntroFragment.access$getMBinding(GameIntroFragment.this).setData(gameDetailBean);
                GameIntroFragment.this.getNewsAdapter().setData(gameDetailBean.getNews());
                GameIntroFragment.this.getNewsAdapter().notifyDataSetChanged();
                GameIntroFragment.this.initChatRv();
                GameIntroFragment.this.getTotal();
            }
        }));
        getVm().getCurrentPosition().observe(gameIntroFragment2, new GameIntroFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment.init.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                GSYVideoManager.releaseAllVideos();
            }
        }));
        getMBinding().vf.setAdapter(getNewsAdapter());
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_update, null, 2, null);
        getMBinding().rvUpdate.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameIntroFragment.init$lambda$1(this.f$0, baseAdapter, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rvTag.setAdapter(new BaseAdapter(R.layout.item_game_detail_tag, null, 2, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameIntroFragment this$0, BaseAdapter updateAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(updateAdapter, "$updateAdapter");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        new QuickDialog(this$0.getMContext(), R.layout.dialog_game_update_detail).setData(updateAdapter.getItem(i)).setOnClickListener(R.id.tv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$$ExternalSyntheticLambda3
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view2) {
                baseDialog.dismiss();
            }
        }).show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        String str;
        Intrinsics.checkNotNullParameter(v, "v");
        final GameDetailBean data = getMBinding().getData();
        if (data != null) {
            switch (v.getId()) {
                case R.id.btn_648 /* 2131361950 */:
                    Repository.INSTANCE.get648Gift(data.getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$3
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
                        }
                    }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$4
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
                case R.id.btn_coupon /* 2131361959 */:
                    startActivity(new Intent(getMContext(), (Class<?>) GameCouponActivity.class).putExtra("gid", data.getId()));
                    break;
                case R.id.btn_event /* 2131361962 */:
                case R.id.tv_event /* 2131362696 */:
                    Intent intentPutExtra = new Intent(getMContext(), (Class<?>) GameEventActivity.class).putExtra("gid", data.getId());
                    GameDetailBean data2 = getMBinding().getData();
                    Intrinsics.checkNotNull(data2);
                    startActivity(intentPutExtra.putExtra("gamename", data2.getName()));
                    break;
                case R.id.btn_gift /* 2131361964 */:
                    startActivity(new Intent(getMContext(), (Class<?>) GameGiftActivity.class).putExtra("gid", data.getId()));
                    break;
                case R.id.btn_intro /* 2131361965 */:
                    new QuickDialog(getMContext(), R.layout.dialog_bottom_tip).setData(data).setAdapter(R.id.rv_vip, new BaseAdapter(R.layout.item_game_vip, new Function3<BaseDataBindingHolder<ItemGameVipBinding>, Integer, GameDetailBean.VipBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$vipAdapter$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemGameVipBinding> baseDataBindingHolder, Integer num, GameDetailBean.VipBean vipBean) {
                            invoke(baseDataBindingHolder, num.intValue(), vipBean);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(BaseDataBindingHolder<ItemGameVipBinding> h, int i, GameDetailBean.VipBean vipBean) {
                            Intrinsics.checkNotNullParameter(h, "h");
                            h.setBackgroundColor(R.id.bg, i % 2 == 1 ? Color.parseColor("#FAFAFA") : -1);
                        }
                    })).setOnClickListener(R.id.tv_privacy, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$$ExternalSyntheticLambda1
                        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                        public final void onClick(BaseDialog baseDialog, View view) {
                            GameIntroFragment.onClick$lambda$6$lambda$4(this.f$0, data, baseDialog, view);
                        }
                    }).setOnClickListener(R.id.tv_permission, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$$ExternalSyntheticLambda2
                        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                        public final void onClick(BaseDialog baseDialog, View view) {
                            GameIntroFragment.onClick$lambda$6$lambda$5(this.f$0, data, baseDialog, view);
                        }
                    }).show();
                    break;
                case R.id.btn_reserve /* 2131361974 */:
                    CalendarUtil.INSTANCE.checkCalendarPermission(getMContext(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$7
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
                            Repository repository = Repository.INSTANCE;
                            int id = data.getId();
                            final GameIntroFragment gameIntroFragment = this;
                            final GameDetailBean gameDetailBean = data;
                            Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$7.1
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
                                    gameIntroFragment.toast(it.getMsg());
                                    gameDetailBean.setIsreservation(1);
                                    gameDetailBean.notifyChange();
                                    GameDetailBean data3 = GameIntroFragment.access$getMBinding(gameIntroFragment).getData();
                                    Intrinsics.checkNotNull(data3);
                                    long startTime = data3.getStartTime();
                                    GameDetailBean data4 = GameIntroFragment.access$getMBinding(gameIntroFragment).getData();
                                    Intrinsics.checkNotNull(data4);
                                    String str2 = data4.getName() + "即将上线";
                                    String string = gameIntroFragment.getString(R.string.app_name);
                                    GameDetailBean data5 = GameIntroFragment.access$getMBinding(gameIntroFragment).getData();
                                    Intrinsics.checkNotNull(data5);
                                    String str3 = string + "提醒：您预约的游戏 " + data5.getName() + " 十分钟后上线";
                                    GameDetailBean data6 = GameIntroFragment.access$getMBinding(gameIntroFragment).getData();
                                    Intrinsics.checkNotNull(data6);
                                    if (data6.getServiceList() != null) {
                                        GameDetailBean data7 = GameIntroFragment.access$getMBinding(gameIntroFragment).getData();
                                        Intrinsics.checkNotNull(data7);
                                        List<GameDetailBean.ServiceBean> serviceList = data7.getServiceList();
                                        Intrinsics.checkNotNull(serviceList);
                                        if (!serviceList.isEmpty()) {
                                            GameDetailBean data8 = GameIntroFragment.access$getMBinding(gameIntroFragment).getData();
                                            Intrinsics.checkNotNull(data8);
                                            List<GameDetailBean.ServiceBean> serviceList2 = data8.getServiceList();
                                            if (serviceList2 != null) {
                                                GameIntroFragment gameIntroFragment2 = gameIntroFragment;
                                                if (!serviceList2.isEmpty()) {
                                                    startTime = (serviceList2.get(0).getStartTime() * ((long) 1000)) - ((long) 600000);
                                                    GameDetailBean data9 = GameIntroFragment.access$getMBinding(gameIntroFragment2).getData();
                                                    Intrinsics.checkNotNull(data9);
                                                    str2 = data9.getName() + "即将开服";
                                                    String string2 = gameIntroFragment2.getString(R.string.app_name);
                                                    GameDetailBean data10 = GameIntroFragment.access$getMBinding(gameIntroFragment2).getData();
                                                    Intrinsics.checkNotNull(data10);
                                                    str3 = string2 + "提醒：您预约的游戏 " + data10.getName() + " 十分钟后开服";
                                                }
                                            }
                                        }
                                    }
                                    boolean zAddCalendarEvent = CalendarUtil.INSTANCE.addCalendarEvent(gameIntroFragment.getMContext(), startTime, str2, str3);
                                    gameIntroFragment.log("写入日历" + zAddCalendarEvent);
                                }
                            };
                            final GameIntroFragment gameIntroFragment2 = this;
                            repository.orderGame(id, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$7.2
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
                                    gameIntroFragment2.netFail(it);
                                }
                            });
                        }
                    }, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$onClick$1$8
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
                            this.this$0.tip("预约需要授予日历权限");
                        }
                    });
                    break;
                case R.id.discount /* 2131362063 */:
                    if (!TextUtils.isEmpty(data.getFreeTips()) || !TextUtils.isEmpty(data.getDiscountTips())) {
                        String str2 = TextUtils.isEmpty(data.getFreeTips()) ? "什么是游戏折扣？" : "什么是免费版？";
                        if (!TextUtils.isEmpty(data.getFreeTips()) && !TextUtils.isEmpty(data.getDiscountTips())) {
                            str = "下一步";
                        } else {
                            str = "我知道了";
                        }
                        new BasePopupWindow.Builder(getMContext()).setBackgroundDimAmount(0.5f).setContentView(R.layout.dialog_free_tip).setText(R.id.tv_content, TextUtils.isEmpty(data.getFreeTips()) ? data.getDiscountTips() : data.getFreeTips()).setText(R.id.tv_title, str2).setText(R.id.btn, str).setOnClickListener(R.id.btn, new BasePopupWindow.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$$ExternalSyntheticLambda0
                            @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnClickListener
                            public final void onClick(BasePopupWindow basePopupWindow, View view) {
                                GameIntroFragment.onClick$lambda$6$lambda$2(data, basePopupWindow, view);
                            }
                        }).showAsDropDown(v);
                    }
                    break;
                case R.id.iv_bbs /* 2131362162 */:
                    Intent intent = new Intent(getMContext(), (Class<?>) GameBbsActivity.class);
                    GameDetailBean value = getVm().getData().getValue();
                    Intrinsics.checkNotNull(value);
                    intent.putExtra("bbsId", value.getBbsId());
                    startActivity(intent);
                    break;
                case R.id.iv_topic /* 2131362205 */:
                case R.id.tv_lottery /* 2131362724 */:
                    startActivity(new Intent(getMContext(), (Class<?>) TopicDetailActivity.class).putExtra("id", data.getSpecialTopic()));
                    break;
                case R.id.tv_kaiju /* 2131362718 */:
                    Intent intent2 = new Intent(getMContext(), (Class<?>) DealListActivity.class);
                    intent2.putExtra("order", 2);
                    GameDetailBean value2 = getVm().getData().getValue();
                    Intrinsics.checkNotNull(value2);
                    intent2.putExtra("game", value2.getName());
                    startActivity(intent2);
                    break;
                case R.id.tv_service /* 2131362771 */:
                    List<GameDetailBean.ServiceBean> serviceList = data.getServiceList();
                    if (serviceList != null && serviceList.size() > 0) {
                        new GameServiceDialog(getMContext(), serviceList).show();
                        break;
                    }
                    break;
                case R.id.tv_task /* 2131362788 */:
                    Util.openWebWithLogin(getMContext(), "", NetUtil.BASE_URL3 + "dist/task-detail?taskId=" + data.getTryPlay());
                    break;
                case R.id.tv_type1 /* 2131362799 */:
                case R.id.tv_type2 /* 2131362800 */:
                case R.id.tv_type3 /* 2131362801 */:
                    startActivity(new Intent(getMContext(), (Class<?>) SearchActivity.class).putExtra("str", ((TextView) v).getText().toString()));
                    break;
                case R.id.tv_update /* 2131362803 */:
                    Intent intent3 = new Intent(getMContext(), (Class<?>) GameUpdateHistoryActivity.class);
                    GameDetailBean value3 = getVm().getData().getValue();
                    Intrinsics.checkNotNull(value3);
                    intent3.putExtra("gid", value3.getId());
                    startActivity(intent3);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$6$lambda$2(GameDetailBean this_run, BasePopupWindow basePopupWindow, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) view;
        if (Intrinsics.areEqual(textView.getText().toString(), "下一步")) {
            ((TextView) basePopupWindow.findViewById(R.id.tv_title)).setText("什么是游戏折扣？");
            ((TextView) basePopupWindow.findViewById(R.id.tv_content)).setText(this_run.getDiscountTips());
            textView.setText("我知道了");
            return;
        }
        basePopupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$6$lambda$4(GameIntroFragment this$0, GameDetailBean this_run, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        BottomTipDialog title = new BottomTipDialog(this$0.getMContext()).setTitle("隐私政策");
        GameDetailExtraBean detailExtraBean = this_run.getDetailExtraBean();
        Intrinsics.checkNotNull(detailExtraBean);
        title.setText(detailExtraBean.getPolicy()).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$6$lambda$5(GameIntroFragment this$0, GameDetailBean this_run, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        BottomTipDialog title = new BottomTipDialog(this$0.getMContext()).setTitle("权限信息");
        GameDetailExtraBean detailExtraBean = this_run.getDetailExtraBean();
        Intrinsics.checkNotNull(detailExtraBean);
        title.setText(detailExtraBean.getAuthority()).show();
    }

    public final void initSimilarGame() {
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        List listChunked = CollectionsKt.chunked(data.getSimilarGames(), 6);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listChunked, 10));
        Iterator it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add((GameBean[]) ((List) it.next()).toArray(new GameBean[0]));
        }
        setSimilarGames(arrayList);
        if (getSimilarGames().isEmpty()) {
            return;
        }
        CollectionsKt.addAll(new ArrayList(), getSimilarGames().get(this.position));
    }

    public final List<GameBean> getNewSimilarGame() {
        if (this.position + 1 < getSimilarGames().size()) {
            this.position++;
        } else {
            this.position = 0;
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, getSimilarGames().get(this.position));
        return arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        GSYVideoManager.releaseAllVideos();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    public final void getExtra() {
        Repository repository = Repository.INSTANCE;
        GameDetailBean value = getVm().getData().getValue();
        Intrinsics.checkNotNull(value);
        repository.getGameDetailExtra(value.getId(), new Function1<GameDetailExtraBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment.getExtra.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailExtraBean gameDetailExtraBean) {
                invoke2(gameDetailExtraBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailExtraBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameDetailBean value2 = GameIntroFragment.this.getVm().getData().getValue();
                Intrinsics.checkNotNull(value2);
                value2.setDetailExtraBean(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment.getExtra.2
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
                GameIntroFragment.this.log(it.toString());
            }
        });
    }

    public final void initChatRv() {
        GameDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        List<GameDetailChatBean> im_logs = data.getIm_logs();
        if (im_logs.size() == 0) {
            return;
        }
        Iterator<GameDetailChatBean> it = im_logs.iterator();
        boolean z = true;
        while (it.hasNext()) {
            it.next().setLeft(z);
            z = !z;
        }
        getChatAdapter().setNewInstance(im_logs);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C11471(im_logs, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$initChatRv$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GameIntroFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.fragment.GameIntroFragment$initChatRv$1", f = "GameIntroFragment.kt", i = {}, l = {349}, m = "invokeSuspend", n = {}, s = {})
    static final class C11471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<GameDetailChatBean> $list;
        int label;
        final /* synthetic */ GameIntroFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C11471(List<GameDetailChatBean> list, GameIntroFragment gameIntroFragment, Continuation<? super C11471> continuation) {
            super(2, continuation);
            this.$list = list;
            this.this$0 = gameIntroFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C11471(this.$list, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0 && i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            do {
                if (!this.$list.isEmpty()) {
                    GameDetailChatBean item = this.this$0.getChatAdapter().getItem(0);
                    this.this$0.getChatAdapter().remove(item);
                    this.this$0.getChatAdapter().addData(item);
                }
                this.label = 1;
            } while (DelayKt.delay(2000L, this) != coroutine_suspended);
            return coroutine_suspended;
        }
    }
}
