package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityGmBinding;
import com.cy.yyjia.zhe28.databinding.ItemGmLogBinding;
import com.cy.yyjia.zhe28.databinding.ItemGmTitleBinding;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMItemBean;
import com.cy.yyjia.zhe28.domain.GMLogBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMRuleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.GMItemDialog;
import com.cy.yyjia.zhe28.ui.dialog.GMRoleDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.mobile.auth.BuildConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: GMActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\rJ\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u000e\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020*J\u0006\u0010\u0014\u001a\u00020!J\u0006\u0010+\u001a\u00020!J\b\u0010,\u001a\u00020!H\u0016J\u0010\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020!H\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\nR\u001a\u0010\u0019\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\b\"\u0004\b\u001b\u0010\nR\u001d\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010¨\u00061"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GMActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGmBinding;", "Landroid/view/View$OnClickListener;", "()V", "has", "", "getHas", "()Z", "setHas", "(Z)V", "logAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GMLogBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGmLogBinding;", "getLogAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "roles", "", "Lcom/cy/yyjia/zhe28/domain/GMRoleBean;", "getRoles", "()Ljava/util/List;", "show", "getShow", "setShow", "stop", "getStop", "setStop", "titleAdapter", "Lcom/cy/yyjia/zhe28/domain/GMTitleBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGmTitleBinding;", "getTitleAdapter", "addLog", "", BuildConfig.FLAVOR_type, "dealMenu", "hide", "getItem", "data", "Lcom/cy/yyjia/zhe28/domain/GMItemBean;", "getItems", ImageSelector.POSITION, "", "getTitles", "init", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMActivity extends BaseActivity<ActivityGmBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private boolean has;
    private final BaseAdapter<GMLogBean, ItemGmLogBinding> logAdapter;
    private final List<GMRoleBean> roles;
    private boolean show;
    private boolean stop;
    private final BaseAdapter<GMTitleBean, ItemGmTitleBinding> titleAdapter;

    public GMActivity() {
        super(R.layout.activity_gm, 0, 2, null);
        this.roles = new ArrayList();
        this.titleAdapter = new BaseAdapter<>(R.layout.item_gm_title, null, 2, null);
        this.logAdapter = new BaseAdapter<>(R.layout.item_gm_log, null, 2, null);
        this.show = true;
    }

    public static final /* synthetic */ ActivityGmBinding access$getMBinding(GMActivity gMActivity) {
        return gMActivity.getMBinding();
    }

    public final List<GMRoleBean> getRoles() {
        return this.roles;
    }

    public final BaseAdapter<GMTitleBean, ItemGmTitleBinding> getTitleAdapter() {
        return this.titleAdapter;
    }

    public final BaseAdapter<GMLogBean, ItemGmLogBinding> getLogAdapter() {
        return this.logAdapter;
    }

    public final boolean getStop() {
        return this.stop;
    }

    public final void setStop(boolean z) {
        this.stop = z;
    }

    public final boolean getShow() {
        return this.show;
    }

    public final void setShow(boolean z) {
        this.show = z;
    }

    public final boolean getHas() {
        return this.has;
    }

    public final void setHas(boolean z) {
        this.has = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GMActivity.init$lambda$0(this.f$0, view);
            }
        });
        ActivityGmBinding mBinding = getMBinding();
        Serializable serializableExtra = getIntent().getSerializableExtra("game");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.GMGameBean");
        mBinding.setGame((GMGameBean) serializableExtra);
        getMBinding().rv.setAdapter(this.titleAdapter);
        this.titleAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GMActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.titleAdapter.addChildClickViewIds(R.id.btn);
        this.titleAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GMActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        m6494getRoles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final GMActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.getGMRule(new Function1<GMRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity$init$1$1
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
                Util.openWeb(this.this$0, "GM规则", it.getRule());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity$init$1$2
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
    public static final void init$lambda$1(GMActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
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
        if (this$0.getMBinding().iv.isSelected()) {
            this$0.getMBinding().iv.performClick();
        }
        this$0.getItems(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(GMActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getMBinding().getRole() == null) {
            this$0.toast("选择角色后才可解锁权限");
            return;
        }
        GMTitleBean gMTitleBean = null;
        for (GMTitleBean gMTitleBean2 : this$0.titleAdapter.getData()) {
            if (gMTitleBean2.getHas_access() == 1) {
                gMTitleBean = gMTitleBean2;
            }
        }
        Intent intent = new Intent(this$0, (Class<?>) GMPayActivity.class);
        intent.putExtra("game", this$0.getMBinding().getGame());
        intent.putExtra("role", this$0.getMBinding().getRole());
        intent.putExtra("title", this$0.titleAdapter.getItem(i));
        intent.putExtra("oldTitle", gMTitleBean);
        this$0.startActivityForResult(intent, 8987);
    }

    /* JADX INFO: renamed from: getRoles, reason: collision with other method in class */
    public final void m6494getRoles() {
        Repository repository = Repository.INSTANCE;
        GMGameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        repository.getGMRoleList(game.getId(), new Function1<List<GMRoleBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getRoles.1
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
                GMActivity.this.getRoles().addAll(it);
                if (it.size() != 0) {
                    it.get(0).setSelected(true);
                    GMActivity.access$getMBinding(GMActivity.this).setRole(it.get(0));
                }
                GMActivity.this.getTitles();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getRoles.2
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
                GMActivity.this.netFail(it);
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
        repository.getGMTitleList(id, accountId, role2 != null ? role2.getRoleId() : null, new Function1<List<GMTitleBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getTitles.1
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
                GMActivity.this.setHas(false);
                int size = it.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        i = 0;
                        break;
                    } else {
                        if (it.get(i).getHas_access() == 1) {
                            GMActivity.this.setHas(true);
                            it.get(i).setSelected(true);
                            GMActivity.access$getMBinding(GMActivity.this).setTitle(it.get(i));
                            break;
                        }
                        i++;
                    }
                }
                if (!GMActivity.this.getHas()) {
                    it.get(0).setSelected(true);
                    GMActivity.access$getMBinding(GMActivity.this).setTitle(it.get(0));
                }
                GMActivity.this.getTitleAdapter().setNewInstance(it);
                GMActivity.this.getItems(i);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getTitles.2
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
                GMActivity.this.netFail(it);
            }
        });
    }

    public final void getItems(int position) {
        if (this.titleAdapter.getItem(position).getItems() != null) {
            List<GMItemBean> items = this.titleAdapter.getItem(position).getItems();
            Intrinsics.checkNotNull(items);
            if (items.size() != 0) {
                List<GMItemBean> items2 = this.titleAdapter.getItem(position).getItems();
                Intrinsics.checkNotNull(items2);
                for (GMItemBean gMItemBean : items2) {
                    if (gMItemBean.getSelected()) {
                        getMBinding().setItem(gMItemBean);
                        return;
                    }
                }
                return;
            }
        }
        Repository repository = Repository.INSTANCE;
        GMGameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        int id = game.getId();
        GMTitleBean title = getMBinding().getTitle();
        Intrinsics.checkNotNull(title);
        repository.getGMItemList(id, title.getId(), new Function1<List<GMItemBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getItems.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<GMItemBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<GMItemBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.get(0).setSelected(true);
                GMActivity.access$getMBinding(GMActivity.this).setItem(it.get(0));
                GMTitleBean title2 = GMActivity.access$getMBinding(GMActivity.this).getTitle();
                Intrinsics.checkNotNull(title2);
                title2.setItems(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getItems.2
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
                GMActivity.this.netFail(it);
            }
        });
    }

    public final void getItem(final GMItemBean data) {
        final int i;
        Intrinsics.checkNotNullParameter(data, "data");
        if (TextUtils.isEmpty(getMBinding().getCount())) {
            i = 1;
        } else {
            String count = getMBinding().getCount();
            Intrinsics.checkNotNull(count);
            i = Integer.parseInt(count);
        }
        addLog(new GMLogBean(11, "任务开始执行", "发放" + data.getItem_name(), Integer.valueOf(i)));
        Repository repository = Repository.INSTANCE;
        GMGameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        int id = game.getId();
        GMTitleBean title = getMBinding().getTitle();
        Intrinsics.checkNotNull(title);
        String id2 = title.getId();
        GMRoleBean role = getMBinding().getRole();
        Intrinsics.checkNotNull(role);
        repository.getGMItem(i, id, id2, role, data, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getItem.1
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
                this.addLog(new GMLogBean(it.getCode(), "任务执行" + it.getMsg(), "发放" + data.getItem_name(), Integer.valueOf(i)));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.getItem.2
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
                this.addLog(new GMLogBean(-1, "任务执行出错" + it.getLocalizedMessage(), "发放" + data.getItem_name(), Integer.valueOf(i)));
            }
        });
    }

    public final void addLog(GMLogBean log) {
        Intrinsics.checkNotNullParameter(log, "log");
        this.logAdapter.addData(0, log);
        try {
            this.logAdapter.getRecyclerView().scrollToPosition(0);
        } catch (Exception unused) {
        }
    }

    private final void dealMenu(boolean hide) {
        RotateAnimation rotateAnimation;
        TranslateAnimation translateAnimation;
        if (hide) {
            rotateAnimation = new RotateAnimation(135.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        } else {
            rotateAnimation = new RotateAnimation(0.0f, 135.0f, 1, 0.5f, 1, 0.5f);
        }
        rotateAnimation.setDuration(400L);
        rotateAnimation.setFillAfter(true);
        getMBinding().iv.startAnimation(rotateAnimation);
        getMBinding().ll.setVisibility(0);
        if (hide) {
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, getMBinding().ll.getHeight());
        } else {
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, getMBinding().ll.getHeight(), 0.0f);
        }
        translateAnimation.setDuration(400L);
        translateAnimation.setFillAfter(true);
        getMBinding().ll.startAnimation(translateAnimation);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        switch (id) {
            case R.id.btn /* 2131361939 */:
                getMBinding().btn6.performClick();
                GMItemBean item = getMBinding().getItem();
                Intrinsics.checkNotNull(item);
                getItem(item);
                break;
            case R.id.btn1 /* 2131361940 */:
                GMTitleBean title = getMBinding().getTitle();
                Intrinsics.checkNotNull(title);
                if (title.getHas_access() != 1) {
                    toast("暂无权限");
                } else {
                    new ConfirmDialog(this).setTitle("温馨提示").setTip("当前操作会发放已选择权限内所有道具，是否发放？").setButton("立即发放", new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.3
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
                            GMActivity.this.setStop(false);
                            GMActivity.access$getMBinding(GMActivity.this).btn6.performClick();
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(GMActivity.this, null), 3, null);
                        }

                        /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$3$1, reason: invalid class name */
                        /* JADX INFO: compiled from: GMActivity.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                        @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$3$1", f = "GMActivity.kt", i = {}, l = {272}, m = "invokeSuspend", n = {}, s = {})
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            int label;
                            final /* synthetic */ GMActivity this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(GMActivity gMActivity, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.this$0 = gMActivity;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.this$0, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.this$0.addLog(new GMLogBean(0, "发放已选择权限内所有道具", null, null, 12, null));
                                    this.label = 1;
                                    if (BuildersKt.withContext(Dispatchers.getMain(), new C01801(this.this$0, null), this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$3$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: GMActivity.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                            @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$3$1$1", f = "GMActivity.kt", i = {}, l = {278}, m = "invokeSuspend", n = {}, s = {})
                            static final class C01801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                Object L$0;
                                int label;
                                final /* synthetic */ GMActivity this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C01801(GMActivity gMActivity, Continuation<? super C01801> continuation) {
                                    super(2, continuation);
                                    this.this$0 = gMActivity;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C01801(this.this$0, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C01801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    Iterator<GMItemBean> it;
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    int i = this.label;
                                    if (i == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        GMTitleBean title = GMActivity.access$getMBinding(this.this$0).getTitle();
                                        Intrinsics.checkNotNull(title);
                                        List<GMItemBean> items = title.getItems();
                                        Intrinsics.checkNotNull(items);
                                        it = items.iterator();
                                    } else {
                                        if (i != 1) {
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        it = (Iterator) this.L$0;
                                        ResultKt.throwOnFailure(obj);
                                    }
                                    while (it.hasNext()) {
                                        GMItemBean next = it.next();
                                        if (this.this$0.getStop()) {
                                            break;
                                        }
                                        this.this$0.getItem(next);
                                        this.L$0 = it;
                                        this.label = 1;
                                        if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    this.this$0.addLog(new GMLogBean(0, "队列终止", null, null, 12, null));
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }).show();
                }
                break;
            default:
                switch (id) {
                    case R.id.btn2 /* 2131361942 */:
                        GMTitleBean title2 = getMBinding().getTitle();
                        Intrinsics.checkNotNull(title2);
                        if (title2.getHas_access() != 1) {
                            toast("暂无权限");
                        } else {
                            new ConfirmDialog(this).setTitle("温馨提示").setTip("是否终止所有自动发放道具任务？").setButton("终止", new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.4
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
                                    GMActivity.this.setStop(true);
                                    GMActivity.access$getMBinding(GMActivity.this).btn6.performClick();
                                }
                            }).show();
                        }
                        break;
                    case R.id.btn3 /* 2131361943 */:
                        GMTitleBean title3 = getMBinding().getTitle();
                        Intrinsics.checkNotNull(title3);
                        if (title3.getHas_access() != 1) {
                            toast("暂无权限");
                        } else {
                            new ConfirmDialog(this).setTitle("温馨提示").setTip("当前操作会把选中道具循环发放10次，是否发放？").setButton("立即发放", new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.5
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
                                    GMActivity.this.setStop(false);
                                    GMActivity.access$getMBinding(GMActivity.this).btn6.performClick();
                                    GMActivity.this.addLog(new GMLogBean(0, "循环发放选中道具10次", null, null, 12, null));
                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(GMActivity.this, null), 3, null);
                                }

                                /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$5$1, reason: invalid class name */
                                /* JADX INFO: compiled from: GMActivity.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$5$1", f = "GMActivity.kt", i = {}, l = {318}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    int label;
                                    final /* synthetic */ GMActivity this$0;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(GMActivity gMActivity, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.this$0 = gMActivity;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.this$0, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$5$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: GMActivity.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$5$1$1", f = "GMActivity.kt", i = {0}, l = {324}, m = "invokeSuspend", n = {"i"}, s = {"I$0"})
                                    static final class C01811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        int I$0;
                                        int label;
                                        final /* synthetic */ GMActivity this$0;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C01811(GMActivity gMActivity, Continuation<? super C01811> continuation) {
                                            super(2, continuation);
                                            this.this$0 = gMActivity;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C01811(this.this$0, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C01811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
                                        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0048 -> B:17:0x004b). Please report as a decompilation issue!!! */
                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        /*
                                            Code decompiled incorrectly, please refer to instructions dump.
                                            To view partially-correct add '--show-bad-code' argument
                                        */
                                        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                                            /*
                                                r10 = this;
                                                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                                int r1 = r10.label
                                                r2 = 1
                                                if (r1 == 0) goto L19
                                                if (r1 != r2) goto L11
                                                int r1 = r10.I$0
                                                kotlin.ResultKt.throwOnFailure(r11)
                                                goto L4b
                                            L11:
                                                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                                                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                                r11.<init>(r0)
                                                throw r11
                                            L19:
                                                kotlin.ResultKt.throwOnFailure(r11)
                                                r11 = 0
                                                r1 = 0
                                            L1e:
                                                r11 = 10
                                                if (r1 >= r11) goto L4d
                                                com.cy.yyjia.zhe28.ui.activity.GMActivity r11 = r10.this$0
                                                boolean r11 = r11.getStop()
                                                if (r11 == 0) goto L2b
                                                goto L4d
                                            L2b:
                                                com.cy.yyjia.zhe28.ui.activity.GMActivity r11 = r10.this$0
                                                com.cy.yyjia.zhe28.databinding.ActivityGmBinding r3 = com.cy.yyjia.zhe28.ui.activity.GMActivity.access$getMBinding(r11)
                                                com.cy.yyjia.zhe28.domain.GMItemBean r3 = r3.getItem()
                                                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                                                r11.getItem(r3)
                                                r11 = r10
                                                kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
                                                r10.I$0 = r1
                                                r10.label = r2
                                                r3 = 1000(0x3e8, double:4.94E-321)
                                                java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r3, r11)
                                                if (r11 != r0) goto L4b
                                                return r0
                                            L4b:
                                                int r1 = r1 + r2
                                                goto L1e
                                            L4d:
                                                com.cy.yyjia.zhe28.domain.GMLogBean r11 = new com.cy.yyjia.zhe28.domain.GMLogBean
                                                r8 = 12
                                                r9 = 0
                                                r4 = 0
                                                java.lang.String r5 = "队列终止"
                                                r6 = 0
                                                r7 = 0
                                                r3 = r11
                                                r3.<init>(r4, r5, r6, r7, r8, r9)
                                                com.cy.yyjia.zhe28.ui.activity.GMActivity r0 = r10.this$0
                                                r0.addLog(r11)
                                                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                                                return r11
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.GMActivity.AnonymousClass5.AnonymousClass1.C01811.invokeSuspend(java.lang.Object):java.lang.Object");
                                        }
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (BuildersKt.withContext(Dispatchers.getMain(), new C01811(this.this$0, null), this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }).show();
                        }
                        break;
                    case R.id.btn4 /* 2131361944 */:
                        GMTitleBean title4 = getMBinding().getTitle();
                        Intrinsics.checkNotNull(title4);
                        if (title4.getHas_access() != 1) {
                            toast("暂无权限");
                        } else {
                            new ConfirmDialog(this).setTitle("温馨提示").setTip("当前操作会把选中道具->以及之后的所有道具全部发放，是否发放？").setButton("立即发放", new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.6
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
                                    GMActivity.this.setStop(false);
                                    GMActivity.access$getMBinding(GMActivity.this).btn6.performClick();
                                    GMActivity.this.addLog(new GMLogBean(0, "发放选中道具之后的所有道具", null, null, 12, null));
                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(GMActivity.this, null), 3, null);
                                }

                                /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$6$1, reason: invalid class name */
                                /* JADX INFO: compiled from: GMActivity.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$6$1", f = "GMActivity.kt", i = {}, l = {349}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    int label;
                                    final /* synthetic */ GMActivity this$0;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(GMActivity gMActivity, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.this$0 = gMActivity;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.this$0, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$6$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: GMActivity.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$6$1$1", f = "GMActivity.kt", i = {0}, l = {360}, m = "invokeSuspend", n = {"start"}, s = {"I$0"})
                                    static final class C01821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        int I$0;
                                        Object L$0;
                                        int label;
                                        final /* synthetic */ GMActivity this$0;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C01821(GMActivity gMActivity, Continuation<? super C01821> continuation) {
                                            super(2, continuation);
                                            this.this$0 = gMActivity;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C01821(this.this$0, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C01821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object obj) {
                                            int i;
                                            Iterator<GMItemBean> it;
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            int i2 = this.label;
                                            if (i2 == 0) {
                                                ResultKt.throwOnFailure(obj);
                                                GMTitleBean title = GMActivity.access$getMBinding(this.this$0).getTitle();
                                                Intrinsics.checkNotNull(title);
                                                List<GMItemBean> items = title.getItems();
                                                Intrinsics.checkNotNull(items);
                                                i = 0;
                                                it = items.iterator();
                                            } else {
                                                if (i2 != 1) {
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                i = this.I$0;
                                                it = (Iterator) this.L$0;
                                                ResultKt.throwOnFailure(obj);
                                            }
                                            while (it.hasNext()) {
                                                GMItemBean next = it.next();
                                                if (next.getSelected()) {
                                                    i = 1;
                                                }
                                                if (this.this$0.getStop()) {
                                                    break;
                                                }
                                                if (i != 0) {
                                                    this.this$0.getItem(next);
                                                    this.L$0 = it;
                                                    this.I$0 = i;
                                                    this.label = 1;
                                                    if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                }
                                            }
                                            this.this$0.addLog(new GMLogBean(0, "队列终止", null, null, 12, null));
                                            return Unit.INSTANCE;
                                        }
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (BuildersKt.withContext(Dispatchers.getMain(), new C01821(this.this$0, null), this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }).show();
                        }
                        break;
                    case R.id.btn5 /* 2131361945 */:
                        GMTitleBean title5 = getMBinding().getTitle();
                        Intrinsics.checkNotNull(title5);
                        if (title5.getHas_access() != 1) {
                            toast("暂无权限");
                        } else {
                            new ConfirmDialog(this).setTitle("温馨提示").setTip("当前操作会把选中道具->以及之后的20个道具全部发放，是否发放？").setButton("立即发放", new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.7
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
                                    GMActivity.this.setStop(false);
                                    GMActivity.access$getMBinding(GMActivity.this).btn6.performClick();
                                    GMActivity.this.addLog(new GMLogBean(0, "发放选中道具之后的20个道具", null, null, 12, null));
                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(GMActivity.this, null), 3, null);
                                }

                                /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$7$1, reason: invalid class name */
                                /* JADX INFO: compiled from: GMActivity.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$7$1", f = "GMActivity.kt", i = {}, l = {386}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    int label;
                                    final /* synthetic */ GMActivity this$0;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(GMActivity gMActivity, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.this$0 = gMActivity;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.this$0, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$7$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: GMActivity.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                                    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.GMActivity$onClick$7$1$1", f = "GMActivity.kt", i = {0, 0}, l = {399}, m = "invokeSuspend", n = {"start", MetricsSQLiteCacheKt.METRICS_COUNT}, s = {"I$0", "I$1"})
                                    static final class C01831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        int I$0;
                                        int I$1;
                                        Object L$0;
                                        int label;
                                        final /* synthetic */ GMActivity this$0;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C01831(GMActivity gMActivity, Continuation<? super C01831> continuation) {
                                            super(2, continuation);
                                            this.this$0 = gMActivity;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C01831(this.this$0, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C01831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object obj) {
                                            int i;
                                            Iterator<GMItemBean> it;
                                            int i2;
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            int i3 = this.label;
                                            if (i3 == 0) {
                                                ResultKt.throwOnFailure(obj);
                                                GMTitleBean title = GMActivity.access$getMBinding(this.this$0).getTitle();
                                                Intrinsics.checkNotNull(title);
                                                List<GMItemBean> items = title.getItems();
                                                Intrinsics.checkNotNull(items);
                                                i = 0;
                                                it = items.iterator();
                                                i2 = 0;
                                            } else {
                                                if (i3 != 1) {
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                int i4 = this.I$1;
                                                int i5 = this.I$0;
                                                it = (Iterator) this.L$0;
                                                ResultKt.throwOnFailure(obj);
                                                i2 = i4;
                                                i = i5;
                                            }
                                            while (it.hasNext()) {
                                                GMItemBean next = it.next();
                                                if (next.getSelected()) {
                                                    i = 1;
                                                }
                                                if (this.this$0.getStop()) {
                                                    break;
                                                }
                                                if (i != 0 && i2 < 20) {
                                                    i2++;
                                                    this.this$0.getItem(next);
                                                    this.L$0 = it;
                                                    this.I$0 = i;
                                                    this.I$1 = i2;
                                                    this.label = 1;
                                                    if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                }
                                            }
                                            this.this$0.addLog(new GMLogBean(0, "队列终止", null, null, 12, null));
                                            return Unit.INSTANCE;
                                        }
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (BuildersKt.withContext(Dispatchers.getMain(), new C01831(this.this$0, null), this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }).show();
                        }
                        break;
                    case R.id.btn6 /* 2131361946 */:
                        GMTitleBean title6 = getMBinding().getTitle();
                        Intrinsics.checkNotNull(title6);
                        if (title6.getHas_access() != 1) {
                            toast("暂无权限");
                        } else {
                            new QuickDialog(this, R.layout.dialog_gm_log).setAdapter(R.id.rv, this.logAdapter).show();
                        }
                        break;
                    case R.id.btn7 /* 2131361947 */:
                        GMTitleBean title7 = getMBinding().getTitle();
                        Intrinsics.checkNotNull(title7);
                        if (title7.getHas_access() != 1) {
                            toast("暂无权限");
                        } else {
                            new ConfirmDialog(this).setTitle("温馨提示").setTip("是否要清空日志").setButton("清空日志", new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.8
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
                                    GMActivity.this.getLogAdapter().setNewInstance(null);
                                }
                            }).show();
                        }
                        break;
                    default:
                        switch (id) {
                            case R.id.iv /* 2131362158 */:
                                dealMenu(v.isSelected());
                                v.setSelected(!v.isSelected());
                                break;
                            case R.id.ll_item /* 2131362264 */:
                                GMItemDialog gMItemDialog = new GMItemDialog(this, new Function1<GMItemBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.2
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GMItemBean it) {
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        GMActivity.access$getMBinding(GMActivity.this).setItem(it);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(GMItemBean gMItemBean) {
                                        invoke2(gMItemBean);
                                        return Unit.INSTANCE;
                                    }
                                });
                                GMTitleBean title8 = getMBinding().getTitle();
                                Intrinsics.checkNotNull(title8);
                                List<GMItemBean> items = title8.getItems();
                                Intrinsics.checkNotNull(items);
                                gMItemDialog.setData(items).show();
                                break;
                            case R.id.ll_roles /* 2131362277 */:
                                new GMRoleDialog(this, new Function1<GMRoleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GMActivity.onClick.1
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GMRoleBean it) {
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        GMActivity.access$getMBinding(GMActivity.this).setRole(it);
                                        GMActivity.this.getTitles();
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(GMRoleBean gMRoleBean) {
                                        invoke2(gMRoleBean);
                                        return Unit.INSTANCE;
                                    }
                                }).setData(this.roles).show();
                                break;
                            case R.id.tv_trans /* 2131362797 */:
                                if (getMBinding().getRole() == null) {
                                    toast("角色不能为空，请先去游戏中创建角色");
                                } else if (!this.has) {
                                    toast("暂无可转移的权限");
                                } else {
                                    GMTitleBean gMTitleBean = null;
                                    for (GMTitleBean gMTitleBean2 : this.titleAdapter.getData()) {
                                        if (gMTitleBean2.getHas_access() == 1) {
                                            gMTitleBean = gMTitleBean2;
                                        }
                                    }
                                    Intent intent = new Intent(this, (Class<?>) GMTransActivity.class);
                                    intent.putExtra("game", getMBinding().getGame());
                                    intent.putExtra("role", getMBinding().getRole());
                                    intent.putExtra("title", gMTitleBean);
                                    startActivity(intent);
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (getMBinding().getRole() != null) {
            getTitles();
        }
    }
}
