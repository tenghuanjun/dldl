package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding;
import com.cy.yyjia.zhe28.databinding.ItemGmTitleBinding;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMRuleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.ui.dialog.GMGameDialog;
import com.cy.yyjia.zhe28.ui.dialog.GMRoleDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.mobile.auth.gatewayauth.Constant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMTransActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\"\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GMTransActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGmTransBinding;", "Landroid/view/View$OnClickListener;", "()V", "roles", "", "Lcom/cy/yyjia/zhe28/domain/GMRoleBean;", "getRoles", "()Ljava/util/List;", "titleAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GMTitleBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGmTitleBinding;", "getTitleAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "getTitles", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMTransActivity extends BaseActivity<ActivityGmTransBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final List<GMRoleBean> roles;
    private final BaseAdapter<GMTitleBean, ItemGmTitleBinding> titleAdapter;

    public GMTransActivity() {
        super(R.layout.activity_gm_trans, 0, 2, null);
        this.titleAdapter = new BaseAdapter<>(R.layout.item_gm_title, null, 2, null);
        this.roles = new ArrayList();
    }

    public static final /* synthetic */ ActivityGmTransBinding access$getMBinding(GMTransActivity gMTransActivity) {
        return gMTransActivity.getMBinding();
    }

    public final BaseAdapter<GMTitleBean, ItemGmTitleBinding> getTitleAdapter() {
        return this.titleAdapter;
    }

    public final List<GMRoleBean> getRoles() {
        return this.roles;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GMTransActivity.init$lambda$0(this.f$0, view);
            }
        });
        ActivityGmTransBinding mBinding = getMBinding();
        Serializable serializableExtra = getIntent().getSerializableExtra("game");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.GMGameBean");
        mBinding.setOldGame((GMGameBean) serializableExtra);
        ActivityGmTransBinding mBinding2 = getMBinding();
        Serializable serializableExtra2 = getIntent().getSerializableExtra("role");
        Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.GMRoleBean");
        mBinding2.setOldRole((GMRoleBean) serializableExtra2);
        ActivityGmTransBinding mBinding3 = getMBinding();
        Serializable serializableExtra3 = getIntent().getSerializableExtra("title");
        Intrinsics.checkNotNull(serializableExtra3, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.GMTitleBean");
        mBinding3.setOldTitle((GMTitleBean) serializableExtra3);
        getMBinding().rv.setAdapter(this.titleAdapter);
        this.titleAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GMTransActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().llGame.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final GMTransActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.getGMRule(new Function1<GMRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity$init$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GMRuleBean gMRuleBean) {
                invoke2(gMRuleBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GMRuleBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Util.openWeb(this.this$0, "转游规则", it.getTrans_rule());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity$init$1$2
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GMTransActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        int size = this$0.titleAdapter.getData().size();
        int i2 = 0;
        while (i2 < size) {
            this$0.titleAdapter.getItem(i2).setSelected(i2 == i);
            i2++;
        }
        this$0.getMBinding().setTitle(this$0.titleAdapter.getItem(i));
    }

    /* JADX INFO: renamed from: getRoles, reason: collision with other method in class */
    public final void m6495getRoles() {
        Repository repository = Repository.INSTANCE;
        GMGameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        repository.getGMRoleList(game.getId(), new Function1<List<GMRoleBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity.getRoles.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<GMRoleBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<GMRoleBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Iterator<GMRoleBean> it2 = it.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    GMRoleBean next = it2.next();
                    String roleId = next.getRoleId();
                    GMRoleBean oldRole = GMTransActivity.access$getMBinding(GMTransActivity.this).getOldRole();
                    Intrinsics.checkNotNull(oldRole);
                    if (Intrinsics.areEqual(roleId, oldRole.getRoleId())) {
                        it.remove(next);
                        break;
                    }
                }
                GMTransActivity.this.getRoles().addAll(it);
                GMTransActivity.access$getMBinding(GMTransActivity.this).llRoles.performClick();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity.getRoles.2
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
                GMTransActivity.this.netFail(it);
            }
        });
    }

    public final void getTitles() {
        Repository repository = Repository.INSTANCE;
        GMGameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        int id = game.getId();
        GMRoleBean role = getMBinding().getRole();
        String accountId = role != null ? role.getAccountId() : null;
        GMRoleBean role2 = getMBinding().getRole();
        repository.getGMTitleList(id, accountId, role2 != null ? role2.getRoleId() : null, new Function1<List<GMTitleBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity.getTitles.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<GMTitleBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<GMTitleBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.get(0).setSelected(true);
                GMTransActivity.access$getMBinding(GMTransActivity.this).setTitle(it.get(0));
                GMTransActivity.this.getTitleAdapter().setNewInstance(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity.getTitles.2
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
                GMTransActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id != R.id.btn) {
            if (id == R.id.ll_game) {
                new GMGameDialog(this, new Function1<GMGameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity.onClick.1
                    {
                        super(1);
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GMGameBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        GMTransActivity.access$getMBinding(GMTransActivity.this).setGame(it);
                        GMTransActivity.access$getMBinding(GMTransActivity.this).setRole(null);
                        GMTransActivity.access$getMBinding(GMTransActivity.this).setTitle(null);
                        GMTransActivity.this.getRoles().clear();
                        GMTransActivity.this.m6495getRoles();
                        GMTransActivity.this.getTitles();
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GMGameBean gMGameBean) {
                        invoke2(gMGameBean);
                        return Unit.INSTANCE;
                    }
                }).show();
                return;
            } else {
                if (id != R.id.ll_roles) {
                    return;
                }
                new GMRoleDialog(this, new Function1<GMRoleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMTransActivity.onClick.2
                    {
                        super(1);
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GMRoleBean it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        GMTransActivity.access$getMBinding(GMTransActivity.this).setRole(it);
                        GMTransActivity.this.getTitles();
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GMRoleBean gMRoleBean) {
                        invoke2(gMRoleBean);
                        return Unit.INSTANCE;
                    }
                }).setData(this.roles).show();
                return;
            }
        }
        Iterator<GMTitleBean> it = this.titleAdapter.getData().iterator();
        while (it.hasNext()) {
            if (it.next().getHas_access() == 1) {
                toast("已有权限不支持转游");
                return;
            }
        }
        Intent intent = new Intent(this, (Class<?>) GMTransPayActivity.class);
        intent.putExtra("oldGame", getMBinding().getOldGame());
        intent.putExtra("game", getMBinding().getGame());
        intent.putExtra("oldRole", getMBinding().getOldRole());
        intent.putExtra("role", getMBinding().getRole());
        intent.putExtra("oldTitle", getMBinding().getOldTitle());
        intent.putExtra("title", getMBinding().getTitle());
        startActivityForResult(intent, 8987);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8987 && resultCode == 8987) {
            finish();
        }
    }
}
