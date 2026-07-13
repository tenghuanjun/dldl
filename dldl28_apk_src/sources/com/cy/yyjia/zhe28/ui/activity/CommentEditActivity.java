package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.ActivityCommentEditBinding;
import com.cy.yyjia.zhe28.databinding.ItemCommentContentBinding;
import com.cy.yyjia.zhe28.databinding.ItemCommentTagBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealPicBinding;
import com.cy.yyjia.zhe28.domain.CommentConfig;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mobile.auth.gatewayauth.Constant;
import java.io.File;
import java.util.ArrayList;
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

/* JADX INFO: compiled from: CommentEditActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020%0'J\u0006\u0010(\u001a\u00020%J\u0006\u0010)\u001a\u00020%J\b\u0010*\u001a\u00020%H\u0016J\"\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020%H\u0016J\u000e\u00101\u001a\u00020%2\u0006\u00102\u001a\u000203R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\r\u001a\u0004\b\u0019\u0010\u0015R'\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001c0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\r\u001a\u0004\b\u001d\u0010\u000bR'\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\r\u001a\u0004\b\"\u0010\u000b¨\u00064"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CommentEditActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityCommentEditBinding;", "()V", "REQUEST_CODE", "", "contentAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CommentConfig$Content;", "Lcom/cy/yyjia/zhe28/databinding/ItemCommentContentBinding;", "getContentAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "contentAdapter$delegate", "Lkotlin/Lazy;", "gid", "getGid", "()I", "gid$delegate", "icon", "", "getIcon", "()Ljava/lang/String;", "icon$delegate", "maxCount", "name", "getName", "name$delegate", "picAdapter", "Lcom/cy/yyjia/zhe28/databinding/ItemDealPicBinding;", "getPicAdapter", "picAdapter$delegate", "tagAdapter", "Lcom/cy/yyjia/zhe28/domain/CommentConfig$Tag;", "Lcom/cy/yyjia/zhe28/databinding/ItemCommentTagBinding;", "getTagAdapter", "tagAdapter$delegate", "checkPermission", "", "success", "Lkotlin/Function0;", "draft", "getData", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "submit", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CommentEditActivity extends BaseActivity<ActivityCommentEditBinding> {
    public static final int $stable = 8;
    private final int REQUEST_CODE;

    /* JADX INFO: renamed from: contentAdapter$delegate, reason: from kotlin metadata */
    private final Lazy contentAdapter;

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final Lazy gid;

    /* JADX INFO: renamed from: icon$delegate, reason: from kotlin metadata */
    private final Lazy icon;
    private final int maxCount;

    /* JADX INFO: renamed from: name$delegate, reason: from kotlin metadata */
    private final Lazy name;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;

    /* JADX INFO: renamed from: tagAdapter$delegate, reason: from kotlin metadata */
    private final Lazy tagAdapter;

    public static final /* synthetic */ ActivityCommentEditBinding access$getMBinding(CommentEditActivity commentEditActivity) {
        return commentEditActivity.getMBinding();
    }

    public CommentEditActivity() {
        super(R.layout.activity_comment_edit, 0, 2, null);
        this.gid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$gid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.icon = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$icon$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("icon");
            }
        });
        this.name = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$name$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("name");
            }
        });
        this.REQUEST_CODE = 11;
        this.maxCount = 3;
        this.picAdapter = LazyKt.lazy(new Function0<BaseAdapter<String, ItemDealPicBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$picAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<String, ItemDealPicBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_deal_pic, CollectionsKt.arrayListOf(""), new Function3<BaseDataBindingHolder<ItemDealPicBinding>, Integer, String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$picAdapter$2.1
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
        this.tagAdapter = LazyKt.lazy(new Function0<BaseAdapter<CommentConfig.Tag, ItemCommentTagBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$tagAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CommentConfig.Tag, ItemCommentTagBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_comment_tag, null, 2, null);
            }
        });
        this.contentAdapter = LazyKt.lazy(new Function0<BaseAdapter<CommentConfig.Content, ItemCommentContentBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$contentAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CommentConfig.Content, ItemCommentContentBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_comment_content, null, 2, null);
            }
        });
    }

    public final int getGid() {
        return ((Number) this.gid.getValue()).intValue();
    }

    public final String getIcon() {
        return (String) this.icon.getValue();
    }

    public final String getName() {
        return (String) this.name.getValue();
    }

    public final BaseAdapter<String, ItemDealPicBinding> getPicAdapter() {
        return (BaseAdapter) this.picAdapter.getValue();
    }

    public final BaseAdapter<CommentConfig.Tag, ItemCommentTagBinding> getTagAdapter() {
        return (BaseAdapter) this.tagAdapter.getValue();
    }

    public final BaseAdapter<CommentConfig.Content, ItemCommentContentBinding> getContentAdapter() {
        return (BaseAdapter) this.contentAdapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(CommentEditActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommentEditActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommentEditActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().setName(getName());
        getMBinding().setIcon(getIcon());
        getMBinding().setTitle("");
        getMBinding().setContent("");
        getMBinding().rv.setAdapter(getPicAdapter());
        getPicAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentEditActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getPicAdapter().addChildClickViewIds(R.id.iv_delete);
        getPicAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentEditActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().rvContent.setLayoutManager(new FlexboxLayoutManager(this));
        getMBinding().rvContent.setAdapter(getContentAdapter());
        getMBinding().rvTag.setAdapter(getTagAdapter());
        getTagAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda7
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentEditActivity.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getContentAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda8
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommentEditActivity.init$lambda$5(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(CommentEditActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(DraftActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(final CommentEditActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.checkPermission(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$init$3$1
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
    public static final void init$lambda$3(CommentEditActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (!Intrinsics.areEqual(this$0.getPicAdapter().getItem(this$0.getPicAdapter().getItemCount() - 1), "")) {
            this$0.getPicAdapter().addData("");
        }
        this$0.getPicAdapter().removeAt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(CommentEditActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getMBinding().setTag(this$0.getTagAdapter().getItem(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(CommentEditActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getMBinding().setContent(this$0.getContentAdapter().getItem(i).getContent());
    }

    public final void getData() {
        Repository.INSTANCE.getCommentConfig(new Function1<CommentConfig, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CommentConfig commentConfig) {
                invoke2(commentConfig);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CommentConfig result) {
                Intrinsics.checkNotNullParameter(result, "result");
                CommentEditActivity.access$getMBinding(CommentEditActivity.this).setData(result);
                if (result.getTags().size() > 0) {
                    CommentEditActivity.this.getContentAdapter().setNewInstance(result.getTags().get(0).getContent());
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity.getData.2
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
                CommentEditActivity.this.netFail(it);
            }
        });
    }

    public final void checkPermission(final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        List<String> listListOf = CollectionsKt.listOf(Permission.READ_MEDIA_IMAGES);
        CommentEditActivity commentEditActivity = this;
        if (XXPermissions.isGranted(commentEditActivity, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(commentEditActivity).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda2
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    CommentEditActivity.checkPermission$lambda$6(success, list, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPermission$lambda$6(Function0 success, List permissions, boolean z) {
        Intrinsics.checkNotNullParameter(success, "$success");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (z) {
            success.invoke();
        }
    }

    public final void submit(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        ArrayList<File> arrayList = new ArrayList<>();
        for (String str : getPicAdapter().getData()) {
            if (str.length() > 0) {
                arrayList.add(new File(str));
            }
        }
        Repository repository = Repository.INSTANCE;
        int gid = getGid();
        int rating = (int) getMBinding().ratingbar.getRating();
        CommentConfig.Tag tag = getMBinding().getTag();
        Integer numValueOf = tag != null ? Integer.valueOf(tag.getId()) : null;
        String content = getMBinding().getContent();
        Intrinsics.checkNotNull(content);
        repository.submitComment(gid, rating, numValueOf, content, 0, 0, arrayList, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity.submit.1
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
                CommentEditActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    CommentEditActivity.this.finish();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity.submit.2
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
                CommentEditActivity.this.netFail(it);
            }
        }, 0, getMBinding().getTitle(), Integer.valueOf(!Intrinsics.areEqual(v, getMBinding().tvGo) ? 1 : 0));
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

    public final void draft() {
        new FastDialog(this).setContentView(R.layout.dialog_draft).setOnClickListener(R.id.btn_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda0
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                CommentEditActivity.draft$lambda$7(this.f$0, baseDialog, view);
            }
        }).setOnClickListener(R.id.btn_save, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CommentEditActivity$$ExternalSyntheticLambda1
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                CommentEditActivity.draft$lambda$8(this.f$0, baseDialog, view);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void draft$lambda$7(CommentEditActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        baseDialog.dismiss();
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void draft$lambda$8(CommentEditActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        baseDialog.dismiss();
        this$0.toast("正在保存");
        Intrinsics.checkNotNull(view);
        this$0.submit(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
}
