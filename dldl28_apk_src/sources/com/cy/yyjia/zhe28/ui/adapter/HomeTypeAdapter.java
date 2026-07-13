package com.cy.yyjia.zhe28.ui.adapter;

import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseProvider;
import com.cy.yyjia.zhe28.databinding.ItemHomeC1Binding;
import com.cy.yyjia.zhe28.databinding.ItemHomeC2Binding;
import com.cy.yyjia.zhe28.databinding.ItemHomeC3Binding;
import com.cy.yyjia.zhe28.databinding.ItemHomeC4Binding;
import com.cy.yyjia.zhe28.databinding.ItemHomeGroupBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomePicBinding;
import com.cy.yyjia.zhe28.domain.CollectionBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.GroupBuyBean;
import com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: HomeTypeAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0006\r\u000e\u000f\u0010\u0011\u0012B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;", "Lcom/chad/library/adapter/base/BaseProviderMultiAdapter;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "()V", "getItemType", "", "data", "", ImageSelector.POSITION, "onAttachedToRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "LargeProvider", "LargeProvider2", "MiniProvider", "PicProvider", "VerticalProvider", "VideoGameProvider", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeTypeAdapter extends BaseProviderMultiAdapter<CollectionBean> {
    public static final int $stable = 0;

    public HomeTypeAdapter() {
        super(null, 1, null);
        int i = 0;
        addItemProvider(new MiniProvider(this, i, 0, 3, null));
        int i2 = 3;
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i3 = 0;
        addItemProvider(new VerticalProvider(this, i3, i, i2, defaultConstructorMarker));
        addItemProvider(new LargeProvider2(this, i3, i, i2, defaultConstructorMarker));
        addItemProvider(new VideoGameProvider(this, i3, i, i2, defaultConstructorMarker));
        addItemProvider(new PicProvider(this, i3, i, i2, defaultConstructorMarker));
        addItemProvider(new BaseProvider(5, R.layout.item_home_video1));
        addItemProvider(new BaseProvider(6, R.layout.item_home_video2));
        addItemProvider(new BaseProvider(7, R.layout.item_home_group, AnonymousClass1.INSTANCE));
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "h", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "b", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeGroupBinding;", "p", "", "i", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function4<BaseViewHolder, ItemHomeGroupBinding, Integer, CollectionBean, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(4);
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ItemHomeGroupBinding itemHomeGroupBinding, Integer num, CollectionBean collectionBean) {
            invoke(baseViewHolder, itemHomeGroupBinding, num.intValue(), collectionBean);
            return Unit.INSTANCE;
        }

        public final void invoke(BaseViewHolder h, ItemHomeGroupBinding b, int i, final CollectionBean collectionBean) {
            Intrinsics.checkNotNullParameter(h, "h");
            Intrinsics.checkNotNullParameter(b, "b");
            BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_group_game, null, 2, null);
            b.rvGroupGame.setAdapter(baseAdapter);
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                    HomeTypeAdapter.AnonymousClass1.invoke$lambda$0(collectionBean, baseQuickAdapter, view, i2);
                }
            });
            Intrinsics.checkNotNull(collectionBean);
            GroupBuyBean groupbuy = collectionBean.getGroupbuy();
            Intrinsics.checkNotNull(groupbuy);
            BaseAdapter baseAdapter2 = new BaseAdapter(R.layout.item_group_user, groupbuy.getUsers());
            b.rvGroupUser.setAdapter(baseAdapter2);
            baseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter$1$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                    HomeTypeAdapter.AnonymousClass1.invoke$lambda$1(collectionBean, baseQuickAdapter, view, i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(CollectionBean collectionBean, BaseQuickAdapter baseQuickAdapter, View v, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNull(collectionBean);
            GroupBuyBean groupbuy = collectionBean.getGroupbuy();
            Intrinsics.checkNotNull(groupbuy);
            groupbuy.getLink().onClick(v);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(CollectionBean collectionBean, BaseQuickAdapter baseQuickAdapter, View v, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNull(collectionBean);
            GroupBuyBean groupbuy = collectionBean.getGroupbuy();
            Intrinsics.checkNotNull(groupbuy);
            groupbuy.getLink().onClick(v);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082 A[ORIG_RETURN, RETURN] */
    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected int getItemType(java.util.List<? extends com.cy.yyjia.zhe28.domain.CollectionBean> r3, int r4) {
        /*
            r2 = this;
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.Object r0 = r3.get(r4)
            com.cy.yyjia.zhe28.domain.CollectionBean r0 = (com.cy.yyjia.zhe28.domain.CollectionBean) r0
            java.lang.String r0 = r0.getType()
            java.lang.String r1 = "ad_pic"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L1a
            r3 = 4
            goto L83
        L1a:
            java.lang.Object r0 = r3.get(r4)
            com.cy.yyjia.zhe28.domain.CollectionBean r0 = (com.cy.yyjia.zhe28.domain.CollectionBean) r0
            java.lang.String r0 = r0.getType()
            java.lang.String r1 = "ad_video"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L3c
            java.lang.Object r3 = r3.get(r4)
            com.cy.yyjia.zhe28.domain.CollectionBean r3 = (com.cy.yyjia.zhe28.domain.CollectionBean) r3
            int r3 = r3.getScreen()
            if (r3 != 0) goto L3a
            r3 = 5
            goto L83
        L3a:
            r3 = 6
            goto L83
        L3c:
            java.lang.Object r3 = r3.get(r4)
            com.cy.yyjia.zhe28.domain.CollectionBean r3 = (com.cy.yyjia.zhe28.domain.CollectionBean) r3
            java.lang.String r3 = r3.getShow_type()
            int r4 = r3.hashCode()
            r0 = 0
            switch(r4) {
                case -1984141450: goto L77;
                case 102742843: goto L6c;
                case 112202875: goto L61;
                case 506364839: goto L56;
                case 813284183: goto L4f;
                default: goto L4e;
            }
        L4e:
            goto L82
        L4f:
            java.lang.String r4 = "minigraph"
            boolean r3 = r3.equals(r4)
            goto L82
        L56:
            java.lang.String r4 = "groupbuy"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L5f
            goto L82
        L5f:
            r3 = 7
            goto L83
        L61:
            java.lang.String r4 = "video"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L6a
            goto L82
        L6a:
            r3 = 3
            goto L83
        L6c:
            java.lang.String r4 = "large"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L75
            goto L82
        L75:
            r3 = 2
            goto L83
        L77:
            java.lang.String r4 = "vertical"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L80
            goto L82
        L80:
            r3 = 1
            goto L83
        L82:
            r3 = 0
        L83:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter.getItemType(java.util.List, int):int");
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter.onAttachedToRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                GSYVideoManager.releaseAllVideos();
            }
        });
    }

    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter$MiniProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "itemViewType", "", "layoutId", "(Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class MiniProvider extends BaseItemProvider<CollectionBean> {
        private final int itemViewType;
        private final int layoutId;

        public MiniProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        public /* synthetic */ MiniProvider(HomeTypeAdapter homeTypeAdapter, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? R.layout.item_home_c1 : i2);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, CollectionBean item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ItemHomeC1Binding itemHomeC1Binding = (ItemHomeC1Binding) DataBindingUtil.getBinding(helper.itemView);
            if (itemHomeC1Binding != null) {
                itemHomeC1Binding.rv.setAdapter(new BaseAdapter(R.layout.item_home_mini, null, 2, null));
                itemHomeC1Binding.setData(item);
                itemHomeC1Binding.executePendingBindings();
            }
        }
    }

    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter$VerticalProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "itemViewType", "", "layoutId", "(Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class VerticalProvider extends BaseItemProvider<CollectionBean> {
        private final int itemViewType;
        private final int layoutId;

        public VerticalProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        public /* synthetic */ VerticalProvider(HomeTypeAdapter homeTypeAdapter, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 1 : i, (i3 & 2) != 0 ? R.layout.item_home_c1 : i2);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, CollectionBean item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ItemHomeC1Binding itemHomeC1Binding = (ItemHomeC1Binding) DataBindingUtil.getBinding(helper.itemView);
            if (itemHomeC1Binding != null) {
                itemHomeC1Binding.rv.setAdapter(new BaseAdapter(R.layout.item_home_vertical, null, 2, null));
                itemHomeC1Binding.setData(item);
                itemHomeC1Binding.executePendingBindings();
            }
        }
    }

    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter$LargeProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "itemViewType", "", "layoutId", "(Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class LargeProvider extends BaseItemProvider<CollectionBean> {
        private final int itemViewType;
        private final int layoutId;

        public LargeProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        public /* synthetic */ LargeProvider(HomeTypeAdapter homeTypeAdapter, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 2 : i, (i3 & 2) != 0 ? R.layout.item_home_c3 : i2);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, CollectionBean item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            int iCeil = (int) Math.ceil(((double) item.getGame_list().size()) / 3.0d);
            ArrayList arrayList = new ArrayList();
            if (iCeil == 1) {
                arrayList.add(item.getGame_list());
            } else {
                for (int i = 0; i < iCeil; i++) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < 3; i2++) {
                        int i3 = (i * 3) + i2;
                        if (i3 < item.getGame_list().size()) {
                            arrayList2.add(item.getGame_list().get(i3));
                        }
                    }
                    arrayList.add(arrayList2);
                }
            }
            ItemHomeC3Binding itemHomeC3Binding = (ItemHomeC3Binding) DataBindingUtil.getBinding(helper.itemView);
            if (itemHomeC3Binding != null) {
                itemHomeC3Binding.rv.setAdapter(new BaseAdapter(R.layout.item_home_c2, arrayList, new Function3<BaseDataBindingHolder<ItemHomeC2Binding>, Integer, List<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter$LargeProvider$convert$1$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemHomeC2Binding> baseDataBindingHolder, Integer num, List<GameBean> list) {
                        invoke(baseDataBindingHolder, num.intValue(), list);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemHomeC2Binding> h, int i4, List<GameBean> list) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemHomeC2Binding itemHomeC2Binding = (ItemHomeC2Binding) h.getDataBinding();
                        if (itemHomeC2Binding != null) {
                            itemHomeC2Binding.rv.setAdapter(new BaseAdapter(R.layout.item_home_large, list));
                            itemHomeC2Binding.executePendingBindings();
                        }
                    }
                }));
                itemHomeC3Binding.setData(item);
                itemHomeC3Binding.executePendingBindings();
            }
        }
    }

    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter$LargeProvider2;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "itemViewType", "", "layoutId", "(Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class LargeProvider2 extends BaseItemProvider<CollectionBean> {
        private final int itemViewType;
        private final int layoutId;

        public LargeProvider2(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        public /* synthetic */ LargeProvider2(HomeTypeAdapter homeTypeAdapter, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 2 : i, (i3 & 2) != 0 ? R.layout.item_home_c3 : i2);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, CollectionBean item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ItemHomeC3Binding itemHomeC3Binding = (ItemHomeC3Binding) DataBindingUtil.getBinding(helper.itemView);
            if (itemHomeC3Binding != null) {
                itemHomeC3Binding.rv.setAdapter(new BaseAdapter(R.layout.item_home_large, null, 2, null));
                itemHomeC3Binding.setData(item);
                itemHomeC3Binding.executePendingBindings();
            }
        }
    }

    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter$VideoGameProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "itemViewType", "", "layoutId", "(Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class VideoGameProvider extends BaseItemProvider<CollectionBean> {
        private final int itemViewType;
        private final int layoutId;

        public VideoGameProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        public /* synthetic */ VideoGameProvider(HomeTypeAdapter homeTypeAdapter, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 3 : i, (i3 & 2) != 0 ? R.layout.item_home_c4 : i2);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, CollectionBean item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ItemHomeC4Binding itemHomeC4Binding = (ItemHomeC4Binding) DataBindingUtil.getBinding(helper.itemView);
            if (itemHomeC4Binding != null) {
                itemHomeC4Binding.rv.setAdapter(new BaseAdapter(R.layout.item_home_video_game, null, 2, null));
                itemHomeC4Binding.setData(item);
                itemHomeC4Binding.executePendingBindings();
            }
        }
    }

    /* JADX INFO: compiled from: HomeTypeAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter$PicProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CollectionBean;", "itemViewType", "", "layoutId", "(Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;II)V", "getItemViewType", "()I", "getLayoutId", "convert", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class PicProvider extends BaseItemProvider<CollectionBean> {
        private final int itemViewType;
        private final int layoutId;

        public PicProvider(int i, int i2) {
            this.itemViewType = i;
            this.layoutId = i2;
        }

        public /* synthetic */ PicProvider(HomeTypeAdapter homeTypeAdapter, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 4 : i, (i3 & 2) != 0 ? R.layout.item_home_pic : i2);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return this.itemViewType;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return this.layoutId;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(BaseViewHolder helper, CollectionBean item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ItemHomePicBinding itemHomePicBinding = (ItemHomePicBinding) DataBindingUtil.getBinding(helper.itemView);
            if (itemHomePicBinding != null) {
                itemHomePicBinding.setData(item);
                itemHomePicBinding.executePendingBindings();
            }
        }
    }
}
