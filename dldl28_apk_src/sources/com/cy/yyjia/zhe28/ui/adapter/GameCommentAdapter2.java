package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseProvider;
import com.cy.yyjia.zhe28.databinding.ItemGameCommentBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameCommentTopBinding;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity;
import com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameCommentAdapter2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u000bB\u0017\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\t2\u0006\u0010\n\u001a\u00020\bH\u0014¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter2;", "Lcom/chad/library/adapter/base/BaseProviderMultiAdapter;", "Lcom/cy/yyjia/zhe28/domain/CommentBean;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "data", "", "(Ljava/util/List;)V", "getItemType", "", "", ImageSelector.POSITION, "CommentProvider", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameCommentAdapter2 extends BaseProviderMultiAdapter<CommentBean> implements LoadMoreModule {
    public static final int $stable = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public GameCommentAdapter2() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.chad.library.adapter.base.module.LoadMoreModule
    public /* synthetic */ BaseLoadMoreModule addLoadMoreModule(BaseQuickAdapter baseQuickAdapter) {
        return LoadMoreModule.CC.$default$addLoadMoreModule(this, baseQuickAdapter);
    }

    public /* synthetic */ GameCommentAdapter2(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public GameCommentAdapter2(List<CommentBean> list) {
        super(list);
        addItemProvider(new BaseProvider(1, R.layout.item_game_comment_top, new Function4<BaseViewHolder, ItemGameCommentTopBinding, Integer, CommentBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2.1
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ItemGameCommentTopBinding itemGameCommentTopBinding, Integer num, CommentBean commentBean) {
                invoke(baseViewHolder, itemGameCommentTopBinding, num.intValue(), commentBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseViewHolder h, ItemGameCommentTopBinding b, int i, CommentBean commentBean) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNullParameter(b, "b");
                Intrinsics.checkNotNull(commentBean);
                commentBean.setShowText(commentBean.is_top() == 1 ? commentBean.getMessage() : commentBean.getShowTextShort());
            }
        }));
        addItemProvider(new CommentProvider());
        setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameCommentAdapter2._init_$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends CommentBean> data, int position) {
        Intrinsics.checkNotNullParameter(data, "data");
        return data.get(position).is_top();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(GameCommentAdapter2 this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0.getContext(), (Class<?>) CommentDetailActivity.class);
        intent.putExtra("data", this$0.getItem(i));
        this$0.getContext().startActivity(intent);
    }

    /* JADX INFO: compiled from: GameCommentAdapter2.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0005R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter2$CommentProvider;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "Lcom/cy/yyjia/zhe28/domain/CommentBean;", "(Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter2;)V", "itemViewType", "", "getItemViewType", "()I", "layoutId", "getLayoutId", "convert", "", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onViewHolderCreated", "viewHolder", "viewType", "praise", ImageSelector.POSITION, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class CommentProvider extends BaseItemProvider<CommentBean> {
        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getItemViewType() {
            return 0;
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public int getLayoutId() {
            return R.layout.item_game_comment;
        }

        public CommentProvider() {
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            DataBindingUtil.bind(viewHolder.itemView);
        }

        @Override // com.chad.library.adapter.base.provider.BaseItemProvider
        public void convert(final BaseViewHolder holder, CommentBean item) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(item, "item");
            ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
            Intrinsics.checkNotNull(binding);
            ItemGameCommentBinding itemGameCommentBinding = (ItemGameCommentBinding) binding;
            item.setShowText(item.is_top() == 1 ? item.getMessage() : item.getShowTextShort());
            itemGameCommentBinding.rvPic.setLayoutManager(new GridLayoutManager(itemGameCommentBinding.rvPic.getContext(), 3));
            itemGameCommentBinding.rvPic.setAdapter(new PicAdapter());
            itemGameCommentBinding.rv.setAdapter(new BaseAdapter(R.layout.item_game_comment_sub, null, 2, null));
            itemGameCommentBinding.rv.setOnTouchListener(new View.OnTouchListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2$CommentProvider$$ExternalSyntheticLambda0
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return GameCommentAdapter2.CommentProvider.convert$lambda$2$lambda$1(holder, view, motionEvent);
                }
            });
            itemGameCommentBinding.setVariable(23, item);
            itemGameCommentBinding.executePendingBindings();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean convert$lambda$2$lambda$1(BaseViewHolder holder, View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(holder, "$holder");
            if (motionEvent.getAction() != 1) {
                return false;
            }
            holder.itemView.performClick();
            return false;
        }

        public final void praise(final int position) {
            Repository repository = Repository.INSTANCE;
            int id = GameCommentAdapter2.this.getItem(position).getId();
            final GameCommentAdapter2 gameCommentAdapter2 = GameCommentAdapter2.this;
            repository.praiseComments(id, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2$CommentProvider$praise$1
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
                    int like_num;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Toast.makeText(this.this$0.getContext(), it.getMsg(), 0).show();
                    if (it.getCode() == 200) {
                        gameCommentAdapter2.getItem(position).setIslikeNum(gameCommentAdapter2.getItem(position).getIslikeNum() != 1 ? 1 : 0);
                        CommentBean item = gameCommentAdapter2.getItem(position);
                        Object data = it.getData();
                        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.Boolean");
                        if (((Boolean) data).booleanValue()) {
                            like_num = gameCommentAdapter2.getItem(position).getLike_num() + 1;
                        } else {
                            like_num = gameCommentAdapter2.getItem(position).getLike_num() - 1;
                        }
                        item.setLike_num(like_num);
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter2$CommentProvider$praise$2
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
                    Toast.makeText(this.this$0.getContext(), it.getLocalizedMessage(), 0).show();
                }
            });
        }
    }
}
