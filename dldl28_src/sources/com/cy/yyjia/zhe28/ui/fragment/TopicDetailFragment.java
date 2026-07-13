package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentTopicDetailBinding;
import com.cy.yyjia.zhe28.databinding.ItemTopicBannerBinding;
import com.cy.yyjia.zhe28.databinding.ItemTopicLotteryBinding;
import com.cy.yyjia.zhe28.databinding.ItemTopicTaskBinding;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TopicBroadcastBean;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;
import com.cy.yyjia.zhe28.ui.activity.ImageActivity;
import com.cy.yyjia.zhe28.ui.adapter.LotteryMessageAdapter;
import com.cy.yyjia.zhe28.ui.dialog.TopicRecordDialog;
import com.cy.yyjia.zhe28.ui.dialog.TopicRulerDialog;
import com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.GalleryLayoutManager;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TopicDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002()B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020!J\b\u0010#\u001a\u00020!H\u0016J\u0010\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020!H\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\f\u001a\u0004\b\u0013\u0010\u0014R'\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0019\u0010\nR\u001b\u0010\u001b\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\f\u001a\u0004\b\u001d\u0010\u001e¨\u0006*"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/TopicDetailFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentTopicDetailBinding;", "Landroid/view/View$OnClickListener;", "()V", "lotteryAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$Prize;", "Lcom/cy/yyjia/zhe28/databinding/ItemTopicLotteryBinding;", "getLotteryAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "lotteryAdapter$delegate", "Lkotlin/Lazy;", "manager", "Lcom/cy/yyjia/zhe28/view/GalleryLayoutManager;", "getManager", "()Lcom/cy/yyjia/zhe28/view/GalleryLayoutManager;", "picAdapter", "Lcom/cy/yyjia/zhe28/ui/fragment/TopicDetailFragment$PicAdapter;", "getPicAdapter", "()Lcom/cy/yyjia/zhe28/ui/fragment/TopicDetailFragment$PicAdapter;", "picAdapter$delegate", "taskAdapter", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean$TaskList;", "Lcom/cy/yyjia/zhe28/databinding/ItemTopicTaskBinding;", "getTaskAdapter", "taskAdapter$delegate", "tid", "", "getTid", "()I", "tid$delegate", "draw", "", "getData", "init", "onClick", "v", "Landroid/view/View;", "onPause", "Companion", "PicAdapter", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TopicDetailFragment extends BaseFragment<FragmentTopicDetailBinding> implements View.OnClickListener {

    /* JADX INFO: renamed from: lotteryAdapter$delegate, reason: from kotlin metadata */
    private final Lazy lotteryAdapter;
    private final GalleryLayoutManager manager;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;

    /* JADX INFO: renamed from: taskAdapter$delegate, reason: from kotlin metadata */
    private final Lazy taskAdapter;

    /* JADX INFO: renamed from: tid$delegate, reason: from kotlin metadata */
    private final Lazy tid;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static final /* synthetic */ FragmentTopicDetailBinding access$getMBinding(TopicDetailFragment topicDetailFragment) {
        return topicDetailFragment.getMBinding();
    }

    public TopicDetailFragment() {
        super(R.layout.fragment_topic_detail);
        this.tid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$tid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.requireArguments().getInt("tid", 0));
            }
        });
        this.lotteryAdapter = LazyKt.lazy(new Function0<BaseAdapter<TopicDetailBean.Prize, ItemTopicLotteryBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$lotteryAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TopicDetailBean.Prize, ItemTopicLotteryBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_topic_lottery, null, 2, null);
            }
        });
        this.taskAdapter = LazyKt.lazy(new Function0<BaseAdapter<TopicDetailBean.TaskList, ItemTopicTaskBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$taskAdapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TopicDetailBean.TaskList, ItemTopicTaskBinding> invoke() {
                final TopicDetailFragment topicDetailFragment = this.this$0;
                return new BaseAdapter<>(R.layout.item_topic_task, new Function3<BaseDataBindingHolder<ItemTopicTaskBinding>, Integer, TopicDetailBean.TaskList, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$taskAdapter$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemTopicTaskBinding> baseDataBindingHolder, Integer num, TopicDetailBean.TaskList taskList) {
                        invoke(baseDataBindingHolder, num.intValue(), taskList);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemTopicTaskBinding> h, int i, TopicDetailBean.TaskList taskList) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemTopicTaskBinding itemTopicTaskBinding = (ItemTopicTaskBinding) h.getDataBinding();
                        if (itemTopicTaskBinding == null) {
                            return;
                        }
                        TopicDetailBean data = TopicDetailFragment.access$getMBinding(topicDetailFragment).getData();
                        itemTopicTaskBinding.setConfig(data != null ? data.getTask() : null);
                    }
                });
            }
        });
        this.picAdapter = LazyKt.lazy(new Function0<PicAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$picAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TopicDetailFragment.PicAdapter invoke() {
                return new TopicDetailFragment.PicAdapter();
            }
        });
        this.manager = new GalleryLayoutManager(0);
    }

    public final int getTid() {
        return ((Number) this.tid.getValue()).intValue();
    }

    public final BaseAdapter<TopicDetailBean.Prize, ItemTopicLotteryBinding> getLotteryAdapter() {
        return (BaseAdapter) this.lotteryAdapter.getValue();
    }

    public final BaseAdapter<TopicDetailBean.TaskList, ItemTopicTaskBinding> getTaskAdapter() {
        return (BaseAdapter) this.taskAdapter.getValue();
    }

    public final PicAdapter getPicAdapter() {
        return (PicAdapter) this.picAdapter.getValue();
    }

    public final GalleryLayoutManager getManager() {
        return this.manager;
    }

    /* JADX INFO: compiled from: TopicDetailFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/TopicDetailFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/TopicDetailFragment;", "id", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TopicDetailFragment newInstance(int id) {
            Bundle bundle = new Bundle();
            bundle.putInt("tid", id);
            TopicDetailFragment topicDetailFragment = new TopicDetailFragment();
            topicDetailFragment.setArguments(bundle);
            return topicDetailFragment;
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setOnClick(this);
        getMBinding().rvLottery.setAdapter(getLotteryAdapter());
        getLotteryAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TopicDetailFragment.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rvTask.setAdapter(getTaskAdapter());
        getTaskAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TopicDetailFragment.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.manager.attach(getMBinding().rvPic, getPicAdapter().getItemCount() / 2);
        getMBinding().rvPic.setAdapter(getPicAdapter());
        this.manager.setItemTransformer(new GalleryLayoutManager.ItemTransformer() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$$ExternalSyntheticLambda2
            @Override // com.cy.yyjia.zhe28.view.GalleryLayoutManager.ItemTransformer
            public final void transformItem(GalleryLayoutManager galleryLayoutManager, View view, float f) {
                TopicDetailFragment.init$lambda$2(galleryLayoutManager, view, f);
            }
        });
        this.manager.scrollToPosition(getPicAdapter().getItemCount() / 2);
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(TopicDetailFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.checkClick() || i != 4) {
            return;
        }
        this$0.draw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(TopicDetailFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (TextUtils.isEmpty(this$0.getTaskAdapter().getItem(i).getUrl())) {
            return;
        }
        Util.openWebWithLogin(this$0.getMContext(), "", this$0.getTaskAdapter().getItem(i).getUrl());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(GalleryLayoutManager galleryLayoutManager, View view, float f) {
        view.setPivotX(view.getWidth() / 2.0f);
        view.setPivotY(view.getHeight() / 2.0f);
        float fAbs = 1 - (Math.abs(f) * 0.2f);
        view.setScaleX(fAbs);
        view.setScaleY(fAbs);
    }

    public final void getData() {
        Repository.INSTANCE.getTopicDetail(getTid(), new Function1<TopicDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TopicDetailBean topicDetailBean) {
                invoke2(topicDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TopicDetailBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                TopicDetailFragment.access$getMBinding(TopicDetailFragment.this).setData(it);
                TopicDetailFragment.this.getPicAdapter().setList(it.getFeature().getUrls());
                TopicDetailFragment.this.getPicAdapter().notifyDataSetChanged();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.getData.2
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
                TopicDetailFragment.this.netFail(it);
            }
        });
        Repository.INSTANCE.getTopicBroadcast(getTid(), new Function1<PageBean<TopicBroadcastBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.getData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<TopicBroadcastBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<TopicBroadcastBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ArrayList arrayList = new ArrayList();
                Iterator<TopicBroadcastBean> it2 = it.getList().iterator();
                while (it2.hasNext()) {
                    arrayList.add(it2.next().getMsg());
                }
                TopicDetailFragment.access$getMBinding(TopicDetailFragment.this).vf.setAdapter(new LotteryMessageAdapter(TopicDetailFragment.this.getMContext(), arrayList));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.getData.4
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
                TopicDetailFragment.this.netFail(it);
            }
        });
    }

    public final void draw() {
        Repository repository = Repository.INSTANCE;
        TopicDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        repository.topicPrizeDraw(data.getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.draw.1
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
                TopicDetailFragment.this.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.draw.2
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
                TopicDetailFragment.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (getMBinding().getData() == null) {
        }
        switch (v.getId()) {
            case R.id.btn_record /* 2131361972 */:
                TopicRecordDialog topicRecordDialog = new TopicRecordDialog(getMContext());
                TopicDetailBean data = getMBinding().getData();
                Intrinsics.checkNotNull(data);
                topicRecordDialog.setData(data).show();
                break;
            case R.id.btn_rule /* 2131361975 */:
                TopicRulerDialog topicRulerDialog = new TopicRulerDialog(getMContext());
                TopicDetailBean data2 = getMBinding().getData();
                Intrinsics.checkNotNull(data2);
                topicRulerDialog.setData(data2).show();
                break;
            case R.id.iv /* 2131362158 */:
                Intent intent = new Intent(getMContext(), (Class<?>) ImageActivity.class);
                TopicDetailBean data3 = getMBinding().getData();
                Intrinsics.checkNotNull(data3);
                List<String> urls = data3.getFeature().getUrls();
                Intrinsics.checkNotNull(urls, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
                intent.putStringArrayListExtra("images", (ArrayList) urls);
                intent.putExtra(ImageSelector.POSITION, 0);
                startActivity(intent);
                break;
            case R.id.iv_coupon /* 2131362168 */:
                TopicDetailBean data4 = getMBinding().getData();
                Intrinsics.checkNotNull(data4);
                if (data4.getCoupon().is_received() == 0) {
                    Repository repository = Repository.INSTANCE;
                    TopicDetailBean data5 = getMBinding().getData();
                    Intrinsics.checkNotNull(data5);
                    repository.getSanbaoVoucher(data5.getGame_id(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.onClick.1
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
                            TopicDetailFragment.this.toast(it.getMsg());
                            TopicDetailFragment.this.getData();
                        }
                    }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment.onClick.2
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
                            TopicDetailFragment.this.netFail(it);
                        }
                    });
                }
                break;
            case R.id.iv_download /* 2131362171 */:
            case R.id.iv_download2 /* 2131362172 */:
                BaseActivity<?> mContext = getMContext();
                TopicDetailBean data6 = getMBinding().getData();
                Intrinsics.checkNotNull(data6);
                Util.gotoGame(mContext, data6.getGame_id());
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        GSYVideoManager.releaseAllVideos();
    }

    /* JADX INFO: compiled from: TopicDetailFragment.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\u001e\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/TopicDetailFragment$PicAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemTopicBannerBinding;", "()V", "list", "", "", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", ImageSelector.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class PicAdapter extends RecyclerView.Adapter<BaseDataBindingHolder<ItemTopicBannerBinding>> {
        public static final int $stable = 8;
        private List<String> list = new ArrayList();

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public BaseDataBindingHolder<ItemTopicBannerBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_banner, parent, false);
            Intrinsics.checkNotNull(viewInflate);
            return new BaseDataBindingHolder<>(viewInflate);
        }

        public final List<String> getList() {
            return this.list;
        }

        public final void setList(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.list = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(BaseDataBindingHolder<ItemTopicBannerBinding> holder, final int position) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            ItemTopicBannerBinding itemTopicBannerBinding = (ItemTopicBannerBinding) holder.getDataBinding();
            if (itemTopicBannerBinding == null || this.list.size() == 0) {
                return;
            }
            List<String> list = this.list;
            itemTopicBannerBinding.setData(list.get(position % list.size()));
            itemTopicBannerBinding.iv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment$PicAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TopicDetailFragment.PicAdapter.onBindViewHolder$lambda$1$lambda$0(this.f$0, position, view);
                }
            });
            itemTopicBannerBinding.executePendingBindings();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onBindViewHolder$lambda$1$lambda$0(PicAdapter this$0, int i, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intent intent = new Intent(view.getContext(), (Class<?>) ImageActivity.class);
            List<String> list = this$0.list;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
            intent.putStringArrayListExtra("images", (ArrayList) list);
            intent.putExtra(ImageSelector.POSITION, i % this$0.list.size());
            view.getContext().startActivity(intent);
        }
    }
}
