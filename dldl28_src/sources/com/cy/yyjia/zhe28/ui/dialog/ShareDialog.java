package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogShareBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.ShareInfo;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.ShareTool;
import com.cy.yyjia.zhe28.util.Util;
import com.volcengine.common.contant.CommonConstants;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J$\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0015J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0018J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0018R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/ShareDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogShareBinding;", "Landroid/view/View$OnClickListener;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", CommonConstants.VALUE_LEVEL_INFO, "Lcom/cy/yyjia/zhe28/domain/ShareInfo;", "getInfo", "()Lcom/cy/yyjia/zhe28/domain/ShareInfo;", "onClick", "", "v", "Landroid/view/View;", "setDeal", "id", "", "isCollect", "", "success", "Lkotlin/Function0;", "setDesc", "describe", "", "setGame", "setImgUrl", "imgUrl", "setPic", "pic", "Ljava/io/File;", "setTitle", "title", "setUrl", "url", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ShareDialog extends BaseDataBindingDialog<DialogShareBinding, ShareDialog> implements View.OnClickListener {
    public static final int $stable = 8;
    private final ShareInfo info;
    private final FragmentActivity mActivity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShareDialog(FragmentActivity mActivity) {
        super(mActivity, R.layout.dialog_share);
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        this.mActivity = mActivity;
        this.info = new ShareInfo(null, null, null, null, null, 31, null);
        ((DialogShareBinding) this.mBinding).setOnClick(this);
    }

    public final ShareInfo getInfo() {
        return this.info;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                ShareTool.INSTANCE.shareToWechat(this.mActivity, this.info, 0);
                break;
            case R.id.tv2 /* 2131362646 */:
                ShareTool.INSTANCE.shareToWechat(this.mActivity, this.info, 1);
                break;
            case R.id.tv3 /* 2131362647 */:
                ShareTool.INSTANCE.shareToWechat(this.mActivity, this.info, 2);
                break;
            case R.id.tv4 /* 2131362648 */:
                Util.copy(this.mActivity, this.info.getDescribe());
                break;
            case R.id.tv5 /* 2131362649 */:
                Util.copy(this.mActivity, this.info.getUrl());
                break;
        }
        dismiss();
    }

    public final ShareDialog setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.info.setTitle(title);
        return this;
    }

    public final ShareDialog setDesc(String describe) {
        Intrinsics.checkNotNullParameter(describe, "describe");
        this.info.setDescribe(describe);
        return this;
    }

    public final ShareDialog setImgUrl(String imgUrl) {
        Intrinsics.checkNotNullParameter(imgUrl, "imgUrl");
        this.info.setImgUrl(imgUrl);
        return this;
    }

    public final ShareDialog setUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.info.setUrl(url);
        return this;
    }

    public final ShareDialog setPic(File pic) {
        Intrinsics.checkNotNullParameter(pic, "pic");
        this.info.setPic(pic);
        return this;
    }

    public final ShareDialog setDeal(final int id, boolean isCollect, final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        ((DialogShareBinding) this.mBinding).tvGame.setVisibility(0);
        if (isCollect) {
            ((DialogShareBinding) this.mBinding).tvGame.setText("取消收藏");
        }
        ((DialogShareBinding) this.mBinding).tvGame.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ShareDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShareDialog.setDeal$lambda$0(id, this, success, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDeal$lambda$0(int i, final ShareDialog this$0, final Function0 success, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(success, "$success");
        Repository.INSTANCE.collectDeal(i, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ShareDialog$setDeal$1$1
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
                this.this$0.toast(it.getMsg());
                success.invoke();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ShareDialog$setDeal$1$2
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
        this$0.dismiss();
    }

    public final ShareDialog setGame(final int id) {
        ((DialogShareBinding) this.mBinding).tvGame.setVisibility(0);
        ((DialogShareBinding) this.mBinding).tvGame.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ShareDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShareDialog.setGame$lambda$1(id, this, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setGame$lambda$1(int i, final ShareDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.collectGame(i, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ShareDialog$setGame$1$1
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
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ShareDialog$setGame$1$2
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
        this$0.dismiss();
    }
}
