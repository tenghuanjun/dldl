package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityBbsEditBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsEditContentBinding;
import com.cy.yyjia.zhe28.domain.BaseResult;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;
import com.cy.yyjia.zhe28.domain.BbsEditFastBean;
import com.cy.yyjia.zhe28.domain.BbsPublishBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.ui.activity.BbsEditActivity;
import com.cy.yyjia.zhe28.ui.adapter.PicSelectAdapter;
import com.cy.yyjia.zhe28.ui.dialog.BbsGameDialog;
import com.cy.yyjia.zhe28.ui.dialog.BottomTipDialog;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.mobile.auth.gatewayauth.Constant;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BbsEditActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0006\u0010\u001e\u001a\u00020\u0014R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BbsEditActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityBbsEditBinding;", "Landroid/view/View$OnClickListener;", "()V", "picAdapter", "Lcom/cy/yyjia/zhe28/ui/adapter/PicSelectAdapter;", "getPicAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/PicSelectAdapter;", "picAdapter$delegate", "Lkotlin/Lazy;", "replayAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsEditFastBean$Content;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsEditContentBinding;", "getReplayAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "getImprovedHtmlFilter", "Landroid/text/InputFilter;", "init", "", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "submit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsEditActivity extends BaseActivity<ActivityBbsEditBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;
    private final BaseAdapter<BbsEditFastBean.Content, ItemBbsEditContentBinding> replayAdapter;

    public final InputFilter getImprovedHtmlFilter() {
        return new InputFilter() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity$$ExternalSyntheticLambda0
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return BbsEditActivity.getImprovedHtmlFilter$lambda$4(charSequence, i, i2, spanned, i3, i4);
            }
        };
    }

    public BbsEditActivity() {
        super(R.layout.activity_bbs_edit, 0, 2, null);
        this.replayAdapter = new BaseAdapter<>(R.layout.item_bbs_edit_content, null, 2, null);
        this.picAdapter = LazyKt.lazy(new Function0<PicSelectAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity$picAdapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PicSelectAdapter invoke() {
                return new PicSelectAdapter(this.this$0, 8);
            }
        });
    }

    public static final /* synthetic */ ActivityBbsEditBinding access$getMBinding(BbsEditActivity bbsEditActivity) {
        return bbsEditActivity.getMBinding();
    }

    public final BaseAdapter<BbsEditFastBean.Content, ItemBbsEditContentBinding> getReplayAdapter() {
        return this.replayAdapter;
    }

    public final PicSelectAdapter getPicAdapter() {
        return (PicSelectAdapter) this.picAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsEditActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().setTitle("");
        ActivityBbsEditBinding mBinding = getMBinding();
        Serializable serializableExtra = getIntent().getSerializableExtra("data");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.BbsDetailBean");
        mBinding.setData((BbsDetailBean) serializableExtra);
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BbsEditActivity$init$$inlined$get$1("bbs/tagCategory", MapsKt.emptyMap(), null, this, this), 3, null);
        getMBinding().rvReply.setLayoutManager(new FlexboxLayoutManager(this));
        getMBinding().rvReply.setAdapter(this.replayAdapter);
        getMBinding().rvPic.setAdapter(getPicAdapter());
        this.replayAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsEditActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().et.setFilters(new InputFilter[]{getImprovedHtmlFilter()});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BbsEditActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.checkClick()) {
            return;
        }
        this$0.submit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(BbsEditActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getMBinding().setText(this$0.replayAdapter.getItem(i).getContent());
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPicAdapter().getPicAuto(requestCode, resultCode, data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void submit() {
        int id;
        if (TextUtils.isEmpty(getMBinding().getText())) {
            toast("请输入内容");
            return;
        }
        ArrayList<File> arrayList = new ArrayList<>();
        for (String str : getPicAdapter().getData()) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new File(str));
            }
        }
        int intExtra = getIntent().getIntExtra("cate", 1);
        BbsDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (data.getGame() != null) {
            BbsDetailBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            GameBean game = data2.getGame();
            Intrinsics.checkNotNull(game);
            id = game.getId();
        } else {
            id = 0;
        }
        final WaitDialog waitDialog = (WaitDialog) new WaitDialog(this).setCancelable(false);
        waitDialog.show();
        Repository repository = Repository.INSTANCE;
        String title = getMBinding().getTitle();
        Intrinsics.checkNotNull(title);
        String text = getMBinding().getText();
        Intrinsics.checkNotNull(text);
        BbsDetailBean data3 = getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        int id2 = data3.getInfo().getId();
        if (intExtra == 0) {
            intExtra = 1;
        }
        repository.editBbs(title, text, id2, intExtra, id, arrayList, new C08891(waitDialog, this), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity.submit.2
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
                BottomTipDialog bottomTipDialog = new BottomTipDialog(this);
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                bottomTipDialog.setText(localizedMessage).show();
                this.netFail(it);
            }
        });
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity$submit$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BbsEditActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/BaseResult;", "Lcom/cy/yyjia/zhe28/domain/BbsPublishBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C08891 extends Lambda implements Function1<BaseResult<BbsPublishBean>, Unit> {
        final /* synthetic */ WaitDialog $dialog;
        final /* synthetic */ BbsEditActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08891(WaitDialog waitDialog, BbsEditActivity bbsEditActivity) {
            super(1);
            this.$dialog = waitDialog;
            this.this$0 = bbsEditActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseResult<BbsPublishBean> baseResult) {
            invoke2(baseResult);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseResult<BbsPublishBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            this.$dialog.hide();
            this.this$0.toast(it.getMsg());
            if (it.getData() != null && it.getData().isTodayFirst()) {
                QuickDialog data = new QuickDialog(this.this$0, R.layout.dialog_pic).setData(it.getData().getAwardBg());
                final BbsEditActivity bbsEditActivity = this.this$0;
                data.addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity$submit$1$$ExternalSyntheticLambda0
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
                    public final void onDismiss(BaseDialog baseDialog) {
                        BbsEditActivity.C08891.invoke$lambda$0(bbsEditActivity, baseDialog);
                    }
                }).show();
            } else {
                this.this$0.setResult(2915);
                this.this$0.finish();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BbsEditActivity this$0, BaseDialog baseDialog) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.setResult(2915);
            this$0.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getImprovedHtmlFilter$lambda$4(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String string = charSequence.subSequence(i, i2).toString();
        String strReplace = new Regex("<[^<>]+>").replace(string, "");
        if (Intrinsics.areEqual(strReplace, string)) {
            return null;
        }
        return strReplace;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id != R.id.iv_game) {
            if (id != R.id.ll_game) {
                return;
            }
            Repository.INSTANCE.getBbsGames(new Function1<List<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity.onClick.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<GameBean> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<GameBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    BbsEditActivity bbsEditActivity = BbsEditActivity.this;
                    final BbsEditActivity bbsEditActivity2 = BbsEditActivity.this;
                    new BbsGameDialog(bbsEditActivity, new Function1<GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity.onClick.1.1
                        {
                            super(1);
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(GameBean game) {
                            Intrinsics.checkNotNullParameter(game, "game");
                            BbsDetailBean data = BbsEditActivity.access$getMBinding(bbsEditActivity2).getData();
                            Intrinsics.checkNotNull(data);
                            data.setGame(game);
                            ActivityBbsEditBinding activityBbsEditBindingAccess$getMBinding = BbsEditActivity.access$getMBinding(bbsEditActivity2);
                            BbsDetailBean data2 = BbsEditActivity.access$getMBinding(bbsEditActivity2).getData();
                            Intrinsics.checkNotNull(data2);
                            activityBbsEditBindingAccess$getMBinding.setData(data2);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GameBean gameBean) {
                            invoke2(gameBean);
                            return Unit.INSTANCE;
                        }
                    }).setData(it).show();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsEditActivity.onClick.2
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
                    BbsEditActivity.this.netFail(it);
                }
            });
            return;
        }
        BbsDetailBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        data.setGame(null);
        ActivityBbsEditBinding mBinding = getMBinding();
        BbsDetailBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        mBinding.setData(data2);
    }
}
