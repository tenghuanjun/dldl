package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ItemGameCommentBinding;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity;
import com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: GameCommentAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B>\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005\u0012%\b\u0002\u0010\u0006\u001a\u001f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0007¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter;", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CommentBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameCommentBinding;", "data", "", "reply", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "comment", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "praise", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameCommentAdapter extends BaseAdapter<CommentBean, ItemGameCommentBinding> {
    public static final int $stable = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public GameCommentAdapter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ GameCommentAdapter(List list, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : function1);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: GameCommentAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameCommentBinding;", ImageSelector.POSITION, "", "item", "Lcom/cy/yyjia/zhe28/domain/CommentBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function3<BaseDataBindingHolder<ItemGameCommentBinding>, Integer, CommentBean, Unit> {
        final /* synthetic */ Function1<CommentBean, Unit> $reply;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super CommentBean, Unit> function1) {
            super(3);
            this.$reply = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemGameCommentBinding> baseDataBindingHolder, Integer num, CommentBean commentBean) {
            invoke(baseDataBindingHolder, num.intValue(), commentBean);
            return Unit.INSTANCE;
        }

        public final void invoke(final BaseDataBindingHolder<ItemGameCommentBinding> holder, int i, CommentBean commentBean) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            if (commentBean != null) {
                commentBean.setShowText(commentBean.getShowTextShort());
            }
            ItemGameCommentBinding itemGameCommentBinding = (ItemGameCommentBinding) holder.getDataBinding();
            if (itemGameCommentBinding != null) {
                final Function1<CommentBean, Unit> function1 = this.$reply;
                itemGameCommentBinding.rvPic.setLayoutManager(new GridLayoutManager(itemGameCommentBinding.rvPic.getContext(), 3));
                itemGameCommentBinding.rvPic.setAdapter(new PicAdapter());
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_comment_sub, null, 2, null);
                itemGameCommentBinding.rv.setAdapter(baseAdapter);
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter$1$$ExternalSyntheticLambda0
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                        GameCommentAdapter.AnonymousClass1.invoke$lambda$3$lambda$1(function1, baseAdapter, baseQuickAdapter, view, i2);
                    }
                });
                if (function1 == null) {
                    itemGameCommentBinding.rv.setOnTouchListener(new View.OnTouchListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter$1$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            return GameCommentAdapter.AnonymousClass1.invoke$lambda$3$lambda$2(holder, view, motionEvent);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$3$lambda$1(Function1 function1, BaseAdapter replyAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(replyAdapter, "$replyAdapter");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            if (function1 != null) {
                function1.invoke(replyAdapter.getItem(i));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invoke$lambda$3$lambda$2(BaseDataBindingHolder holder, View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(holder, "$holder");
            if (motionEvent.getAction() != 1) {
                return false;
            }
            holder.itemView.performClick();
            return false;
        }
    }

    public GameCommentAdapter(List<CommentBean> list, Function1<? super CommentBean, Unit> function1) {
        super(R.layout.item_game_comment, list, new AnonymousClass1(function1));
        addChildClickViewIds(R.id.tv_praise, R.id.tv_folder);
        setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameCommentAdapter._init_$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameCommentAdapter._init_$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(GameCommentAdapter this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        String showTextShort;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id != R.id.tv_folder) {
            if (id != R.id.tv_praise) {
                return;
            }
            this$0.praise(i);
        } else {
            v.setSelected(!v.isSelected());
            CommentBean item = this$0.getItem(i);
            if (v.isSelected()) {
                showTextShort = this$0.getItem(i).getMessage();
            } else {
                showTextShort = this$0.getItem(i).getShowTextShort();
            }
            item.setShowText(showTextShort);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(GameCommentAdapter this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0.getContext(), (Class<?>) CommentDetailActivity.class);
        intent.putExtra("data", this$0.getItem(i));
        this$0.getContext().startActivity(intent);
    }

    public final void praise(final int position) {
        Repository.INSTANCE.praiseComments(getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter.praise.1
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
                Toast.makeText(GameCommentAdapter.this.getContext(), it.getMsg(), 0).show();
                if (it.getCode() == 200) {
                    GameCommentAdapter.this.getItem(position).setIslikeNum(GameCommentAdapter.this.getItem(position).getIslikeNum() != 1 ? 1 : 0);
                    CommentBean item = GameCommentAdapter.this.getItem(position);
                    Object data = it.getData();
                    Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.Boolean");
                    if (((Boolean) data).booleanValue()) {
                        like_num = GameCommentAdapter.this.getItem(position).getLike_num() + 1;
                    } else {
                        like_num = GameCommentAdapter.this.getItem(position).getLike_num() - 1;
                    }
                    item.setLike_num(like_num);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter.praise.2
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
                Toast.makeText(GameCommentAdapter.this.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }
}
