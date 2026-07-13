package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityCommentDetailBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealPicBinding;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.adapter.GameCommentAdapter;
import com.cy.yyjia.zhe28.ui.adapter.PicAdapter;
import com.cy.yyjia.zhe28.ui.dialog.CommentDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mobile.auth.gatewayauth.Constant;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: CommentDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\"J\u0006\u0010\u000f\u001a\u00020 J\b\u0010#\u001a\u00020 H\u0016J\u0006\u0010$\u001a\u00020 J\"\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010(H\u0014J\u0010\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020+H\u0016J\u0006\u0010,\u001a\u00020 J\u001e\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R'\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\f\u001a\u0004\b\u001c\u0010\u001d¨\u00061"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CommentDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityCommentDetailBinding;", "Landroid/view/View$OnClickListener;", "()V", "REQUEST_CODE", "", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/GameCommentAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "data", "Lcom/cy/yyjia/zhe28/domain/CommentBean;", "getData", "()Lcom/cy/yyjia/zhe28/domain/CommentBean;", "data$delegate", "maxCount", "page", "getPage", "()I", "setPage", "(I)V", "picAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemDealPicBinding;", "getPicAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "picAdapter$delegate", "checkPermission", "", "success", "Lkotlin/Function0;", "init", "initEdit", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "praise", "sendComment", "id", "uid", "text", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CommentDetailActivity extends BaseActivity<ActivityCommentDetailBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final int REQUEST_CODE;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: data$delegate, reason: from kotlin metadata */
    private final Lazy data;
    private final int maxCount;
    private int page;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;

    public static final /* synthetic */ ActivityCommentDetailBinding access$getMBinding(CommentDetailActivity commentDetailActivity) {
        return commentDetailActivity.getMBinding();
    }

    public CommentDetailActivity() {
        super(R.layout.activity_comment_detail, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<GameCommentAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameCommentAdapter invoke() {
                final CommentDetailActivity commentDetailActivity = this.this$0;
                return new GameCommentAdapter(null, new Function1<CommentBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$adapter$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(CommentBean commentBean) {
                        invoke2(commentBean);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final CommentBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        CommentDialog hint = new CommentDialog(commentDetailActivity.getMContext()).setHint("回复@" + it.getUser().getNickName());
                        final CommentDetailActivity commentDetailActivity2 = commentDetailActivity;
                        hint.setSubmitListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.adapter.2.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                invoke2(str);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String text) {
                                Intrinsics.checkNotNullParameter(text, "text");
                                commentDetailActivity2.sendComment(it.getId(), it.getUid(), text);
                            }
                        }).show();
                    }
                });
            }
        });
        this.REQUEST_CODE = 11;
        this.maxCount = 3;
        this.picAdapter = LazyKt.lazy(new Function0<BaseAdapter<String, ItemDealPicBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$picAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<String, ItemDealPicBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_deal_pic, CollectionsKt.arrayListOf(""), new Function3<BaseDataBindingHolder<ItemDealPicBinding>, Integer, String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$picAdapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemDealPicBinding> baseDataBindingHolder, Integer num, String str) {
                        invoke(baseDataBindingHolder, num.intValue(), str);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemDealPicBinding> holder, int i, String str) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        holder.setVisible(R.id.iv_delete, !Intrinsics.areEqual(str, ""));
                    }
                });
            }
        });
        this.data = LazyKt.lazy(new Function0<CommentBean>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$data$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CommentBean invoke() {
                Serializable serializableExtra = this.this$0.getIntent().getSerializableExtra("data");
                Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.CommentBean");
                return (CommentBean) serializableExtra;
            }
        });
        this.page = 1;
    }

    public final GameCommentAdapter getAdapter() {
        return (GameCommentAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<String, ItemDealPicBinding> getPicAdapter() {
        return (BaseAdapter) this.picAdapter.getValue();
    }

    public final CommentBean getData() {
        return (CommentBean) this.data.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setText("");
        getMBinding().setData(getData());
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().rvPic.setAdapter(new PicAdapter());
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentDetailActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                CommentDetailActivity.init$lambda$2(this.f$0);
            }
        });
        initEdit();
        m6463getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(final CommentDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        final CommentBean item = this$0.getAdapter().getItem(i);
        new CommentDialog(this$0.getMContext()).setSubmitListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$init$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.sendComment(item.getId(), item.getUid(), it);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(CommentDetailActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.m6463getData();
    }

    public final void initEdit() {
        getMBinding().rvEdit.setAdapter(getPicAdapter());
        getPicAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentDetailActivity.initEdit$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getPicAdapter().addChildClickViewIds(R.id.iv_delete);
        getPicAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentDetailActivity.initEdit$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initEdit$lambda$3(final CommentDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.checkPermission(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$initEdit$1$1
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
                ImageSelector.ImageSelectorBuilder maxSelectCount = ImageSelector.builder().useCamera(false).setSingle(false).setMaxSelectCount(this.this$0.maxCount);
                List<String> data = this.this$0.getPicAdapter().getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
                maxSelectCount.setSelected((ArrayList) data).canPreview(false).start(this.this$0.getMContext(), this.this$0.REQUEST_CODE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initEdit$lambda$4(CommentDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (!Intrinsics.areEqual(this$0.getPicAdapter().getItem(this$0.getPicAdapter().getItemCount() - 1), "")) {
            this$0.getPicAdapter().addData("");
        }
        this$0.getPicAdapter().removeAt(i);
    }

    public final void checkPermission(final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        List<String> listListOf = CollectionsKt.listOf(Permission.READ_MEDIA_IMAGES);
        CommentDetailActivity commentDetailActivity = this;
        if (XXPermissions.isGranted(commentDetailActivity, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(commentDetailActivity).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity$$ExternalSyntheticLambda0
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    CommentDetailActivity.checkPermission$lambda$5(success, list, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPermission$lambda$5(Function0 success, List permissions, boolean z) {
        Intrinsics.checkNotNullParameter(success, "$success");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (z) {
            success.invoke();
        }
    }

    /* JADX INFO: renamed from: getData, reason: collision with other method in class */
    public final void m6463getData() {
        Repository.INSTANCE.getCommentDetail(this.page, getData().getGameId(), getData().getId(), new Function1<PageBean<CommentBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<CommentBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<CommentBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (CommentDetailActivity.this.getPage() == 1) {
                    CommentDetailActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    CommentDetailActivity.this.getAdapter().addData((Collection) it.getList());
                }
                CommentDetailActivity commentDetailActivity = CommentDetailActivity.this;
                commentDetailActivity.setPage(commentDetailActivity.getPage() + 1);
                commentDetailActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(CommentDetailActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    CommentDetailActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.getData.2
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
                CommentDetailActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void praise() {
        Repository.INSTANCE.praiseComments(getData().getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.praise.1
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
                CommentDetailActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    CommentBean data = CommentDetailActivity.this.getData();
                    Object data2 = it.getData();
                    Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type kotlin.Boolean");
                    data.setIslikeNum(((Boolean) data2).booleanValue() ? 1 : 0);
                    CommentBean data3 = CommentDetailActivity.this.getData();
                    if (((Boolean) it.getData()).booleanValue()) {
                        like_num = CommentDetailActivity.this.getData().getLike_num() + 1;
                    } else {
                        like_num = CommentDetailActivity.this.getData().getLike_num() - 1;
                    }
                    data3.setLike_num(like_num);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.praise.2
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
                CommentDetailActivity commentDetailActivity = CommentDetailActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                commentDetailActivity.toast(localizedMessage);
            }
        });
    }

    public final void sendComment(int id, int uid, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        hideSoftKeyboard();
        ArrayList<File> arrayList = new ArrayList<>();
        for (String str : getPicAdapter().getData()) {
            if (str.length() > 0) {
                arrayList.add(new File(str));
            }
        }
        Repository.INSTANCE.replyComment(getData().getGameId(), text, id, uid, arrayList, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.sendComment.1
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
                CommentDetailActivity.this.toast(it.getMsg());
                CommentDetailActivity.this.getPicAdapter().setNewInstance(null);
                CommentDetailActivity.access$getMBinding(CommentDetailActivity.this).setText("");
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentDetailActivity.sendComment.2
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
                CommentDetailActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        String showTextShort;
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.btn) {
            CommentBean data = getData();
            int id2 = data.getId();
            int uid = data.getUid();
            String text = getMBinding().getText();
            Intrinsics.checkNotNull(text);
            sendComment(id2, uid, text);
            return;
        }
        if (id != R.id.tv_folder) {
            if (id != R.id.tv_praise) {
                return;
            }
            praise();
            return;
        }
        CommentBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        if (Intrinsics.areEqual("展开", getMBinding().tvFolder.getText())) {
            CommentBean data3 = getMBinding().getData();
            Intrinsics.checkNotNull(data3);
            showTextShort = data3.getMessage();
        } else {
            CommentBean data4 = getMBinding().getData();
            Intrinsics.checkNotNull(data4);
            showTextShort = data4.getShowTextShort();
        }
        data2.setShowText(showTextShort);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != this.REQUEST_CODE || data == null) {
            return;
        }
        ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
        Intrinsics.checkNotNull(stringArrayListExtra, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.String>");
        List<String> listAsMutableList = TypeIntrinsics.asMutableList(stringArrayListExtra);
        if (listAsMutableList.size() < this.maxCount) {
            listAsMutableList.add("");
        }
        getPicAdapter().setNewInstance(listAsMutableList);
    }
}
