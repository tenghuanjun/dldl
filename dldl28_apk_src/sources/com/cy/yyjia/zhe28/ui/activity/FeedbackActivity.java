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
import com.cy.yyjia.zhe28.databinding.ActivityFeedbackBinding;
import com.cy.yyjia.zhe28.databinding.ItemFeedbackPicBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
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

/* JADX INFO: compiled from: FeedbackActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\"\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R'\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/FeedbackActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityFeedbackBinding;", "Landroid/view/View$OnClickListener;", "()V", "REQUEST_CODE", "", "maxCount", "picAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemFeedbackPicBinding;", "getPicAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "picAdapter$delegate", "Lkotlin/Lazy;", "checkPermission", "", "success", "Lkotlin/Function0;", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "submit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FeedbackActivity extends BaseActivity<ActivityFeedbackBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final int REQUEST_CODE;
    private final int maxCount;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;

    public FeedbackActivity() {
        super(R.layout.activity_feedback, 0, 2, null);
        this.REQUEST_CODE = 11;
        this.maxCount = 3;
        this.picAdapter = LazyKt.lazy(new Function0<BaseAdapter<String, ItemFeedbackPicBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$picAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<String, ItemFeedbackPicBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_feedback_pic, CollectionsKt.arrayListOf(""), new Function3<BaseDataBindingHolder<ItemFeedbackPicBinding>, Integer, String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$picAdapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemFeedbackPicBinding> baseDataBindingHolder, Integer num, String str) {
                        invoke(baseDataBindingHolder, num.intValue(), str);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemFeedbackPicBinding> holder, int i, String str) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        holder.setVisible(R.id.iv_delete, !Intrinsics.areEqual(str, ""));
                    }
                });
            }
        });
    }

    public final BaseAdapter<String, ItemFeedbackPicBinding> getPicAdapter() {
        return (BaseAdapter) this.picAdapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(FeedbackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(FeedbackRecordActivity.class);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().setType(1);
        getMBinding().setDesc("");
        getMBinding().rvPic.setAdapter(getPicAdapter());
        getPicAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FeedbackActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getPicAdapter().addChildClickViewIds(R.id.iv_delete);
        getPicAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FeedbackActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(final FeedbackActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.checkPermission(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$init$2$1
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
    public static final void init$lambda$2(FeedbackActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
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
        FeedbackActivity feedbackActivity = this;
        if (XXPermissions.isGranted(feedbackActivity, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(feedbackActivity).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity$$ExternalSyntheticLambda0
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    FeedbackActivity.checkPermission$lambda$3(success, list, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPermission$lambda$3(Function0 success, List permissions, boolean z) {
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
        String desc = getMBinding().getDesc();
        Intrinsics.checkNotNull(desc);
        repository.submitFeedback(desc, getMBinding().getType(), arrayList, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity.submit.1
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
                FeedbackActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    FeedbackActivity.this.finish();
                    FeedbackActivity.this.startActivity(FeedbackRecordActivity.class);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FeedbackActivity.submit.2
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
                FeedbackActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                getMBinding().setType(1);
                break;
            case R.id.tv2 /* 2131362646 */:
                getMBinding().setType(2);
                break;
            case R.id.tv3 /* 2131362647 */:
                getMBinding().setType(3);
                break;
            case R.id.tv4 /* 2131362648 */:
                getMBinding().setType(4);
                break;
            case R.id.tv5 /* 2131362649 */:
                getMBinding().setType(5);
                break;
        }
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
