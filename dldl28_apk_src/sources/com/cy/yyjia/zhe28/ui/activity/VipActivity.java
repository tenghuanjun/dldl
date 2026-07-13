package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityVipBinding;
import com.cy.yyjia.zhe28.databinding.FragmentVipRightBinding;
import com.cy.yyjia.zhe28.databinding.ItemVipRightBinding;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.GameToolBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.VipGiftBean;
import com.cy.yyjia.zhe28.domain.VipListBean;
import com.cy.yyjia.zhe28.ui.activity.VipActivity;
import com.cy.yyjia.zhe28.ui.dialog.VipWebDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: VipActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\fJ\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/VipActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityVipBinding;", "Landroid/view/View$OnClickListener;", "()V", "listAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/VipListBean$ListBean;", "Lcom/cy/yyjia/zhe28/databinding/FragmentVipRightBinding;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "init", "", "initFun", "initGift", "initVp", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipActivity extends BaseActivity<ActivityVipBinding> implements View.OnClickListener {
    public static final int $stable = BaseAdapter.$stable;
    private final BaseAdapter<VipListBean.ListBean, FragmentVipRightBinding> listAdapter;

    public VipActivity() {
        super(R.layout.activity_vip, 2);
        this.listAdapter = new BaseAdapter<>(R.layout.fragment_vip_right, new Function3<BaseDataBindingHolder<FragmentVipRightBinding>, Integer, VipListBean.ListBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity$listAdapter$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<FragmentVipRightBinding> baseDataBindingHolder, Integer num, VipListBean.ListBean listBean) {
                invoke(baseDataBindingHolder, num.intValue(), listBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<FragmentVipRightBinding> h, int i, VipListBean.ListBean listBean) {
                UserBean user;
                UserBean user2;
                Intrinsics.checkNotNullParameter(h, "h");
                FragmentVipRightBinding fragmentVipRightBinding = (FragmentVipRightBinding) h.getDataBinding();
                if (fragmentVipRightBinding != null) {
                    fragmentVipRightBinding.setPosition(i);
                }
                FragmentVipRightBinding fragmentVipRightBinding2 = (FragmentVipRightBinding) h.getDataBinding();
                String vieLevel = null;
                if (fragmentVipRightBinding2 != null) {
                    VipListBean data = VipActivity.access$getMBinding(this.this$0).getData();
                    Integer numValueOf = (data == null || (user2 = data.getUser()) == null) ? null : Integer.valueOf(user2.getAllExperience());
                    Intrinsics.checkNotNull(numValueOf);
                    fragmentVipRightBinding2.setProgress(numValueOf.intValue());
                }
                String name = listBean != null ? listBean.getName() : null;
                VipListBean data2 = VipActivity.access$getMBinding(this.this$0).getData();
                if (data2 != null && (user = data2.getUser()) != null) {
                    vieLevel = user.getVieLevel();
                }
                h.setGone(R.id.tv_current, !Intrinsics.areEqual(name, vieLevel));
            }
        });
    }

    public static final /* synthetic */ ActivityVipBinding access$getMBinding(VipActivity vipActivity) {
        return vipActivity.getMBinding();
    }

    public final BaseAdapter<VipListBean.ListBean, FragmentVipRightBinding> getListAdapter() {
        return this.listAdapter;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        initVp();
        getMBinding().setRight(true);
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_vip_right, new Function3<BaseDataBindingHolder<ItemVipRightBinding>, Integer, VipListBean.RightBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity$init$rightAdapter$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemVipRightBinding> baseDataBindingHolder, Integer num, VipListBean.RightBean rightBean) {
                invoke(baseDataBindingHolder, num.intValue(), rightBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemVipRightBinding> h, int i, VipListBean.RightBean rightBean) {
                Intrinsics.checkNotNullParameter(h, "h");
                ViewDataBinding dataBinding = h.getDataBinding();
                Intrinsics.checkNotNull(dataBinding);
                ImageView imageView = ((ItemVipRightBinding) dataBinding).iv;
                Intrinsics.checkNotNull(rightBean);
                Util.setGray(imageView, rightBean.getUnlock() == 0);
            }
        });
        getMBinding().rvRight.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipActivity.init$lambda$1(baseAdapter, this, baseQuickAdapter, view, i);
            }
        });
        initGift();
        initFun();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void init$lambda$1(final BaseAdapter rightAdapter, final VipActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(rightAdapter, "$rightAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (((VipListBean.RightBean) rightAdapter.getItem(i)).getUnlock() == 0 || ((VipListBean.RightBean) rightAdapter.getItem(i)).getUser_unlock() == 0) {
            this$0.toast("您还未达到等级");
        } else {
            new QuickDialog(this$0, R.layout.dialog_vip_right2).setData(rightAdapter.getItem(i)).setOnClickListener(R.id.btn, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity$$ExternalSyntheticLambda1
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    VipActivity.init$lambda$1$lambda$0(rightAdapter, i, this$0, baseDialog, view2);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void init$lambda$1$lambda$0(BaseAdapter rightAdapter, int i, VipActivity this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(rightAdapter, "$rightAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((VipListBean.RightBean) rightAdapter.getItem(i)).getId() != 1) {
            BtnBean link = ((VipListBean.RightBean) rightAdapter.getItem(i)).getLink();
            Intrinsics.checkNotNull(view);
            link.onClick(view);
        } else {
            new VipWebDialog(this$0).setUrl(((VipListBean.RightBean) rightAdapter.getItem(i)).getLink().getUrl()).show();
        }
        baseDialog.dismiss();
    }

    public final void initVp() {
        getMBinding().vp.setOffscreenPageLimit(3);
        getMBinding().vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity.initVp.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                VipActivity.access$getMBinding(VipActivity.this).setPosition(position);
            }
        });
        Repository.INSTANCE.getVipList(new C10892(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity.initVp.3
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
                VipActivity.this.netFail(it);
            }
        });
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.VipActivity$initVp$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VipActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/VipListBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C10892 extends Lambda implements Function1<VipListBean, Unit> {
        C10892() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(VipListBean vipListBean) {
            invoke2(vipListBean);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final VipListBean it) {
            Intrinsics.checkNotNullParameter(it, "it");
            VipActivity.access$getMBinding(VipActivity.this).setData(it);
            VipActivity.this.getListAdapter().setNewInstance(it.getList());
            VipActivity.access$getMBinding(VipActivity.this).vp.setAdapter(VipActivity.this.getListAdapter());
            BaseAdapter<VipListBean.ListBean, FragmentVipRightBinding> listAdapter = VipActivity.this.getListAdapter();
            final VipActivity vipActivity = VipActivity.this;
            listAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity$initVp$2$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    VipActivity.C10892.invoke$lambda$0(vipActivity, it, baseQuickAdapter, view, i);
                }
            });
            int size = it.getList().size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(it.getList().get(i).getName(), it.getUser().getVieLevel())) {
                    VipActivity.access$getMBinding(VipActivity.this).vp.setCurrentItem(i, false);
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(VipActivity this$0, VipListBean it, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "$it");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            new VipWebDialog(this$0).setUrl(it.getConfigUrl()).show();
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.VipActivity$initGift$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VipActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/PageBean;", "Lcom/cy/yyjia/zhe28/domain/VipGiftBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C10861 extends Lambda implements Function1<PageBean<VipGiftBean>, Unit> {
        C10861() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PageBean<VipGiftBean> pageBean) {
            invoke2(pageBean);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PageBean<VipGiftBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_vip_gift_small, it.getList());
            VipActivity.access$getMBinding(VipActivity.this).rvGift.setAdapter(baseAdapter);
            final VipActivity vipActivity = VipActivity.this;
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity$initGift$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    VipActivity.C10861.invoke$lambda$0(vipActivity, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(VipActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            this$0.startActivity(VipGiftListActivity.class);
        }
    }

    public final void initGift() {
        Repository.INSTANCE.getVipGiftList(1, new C10861(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity.initGift.2
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
                VipActivity.this.netFail(it);
            }
        });
    }

    public final void initFun() {
        Repository.INSTANCE.getNavFun("", new Function1<List<GameToolBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity.initFun.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<GameToolBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<GameToolBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VipActivity.access$getMBinding(VipActivity.this).rvFun.setAdapter(new BaseAdapter(R.layout.item_vip_fun, it));
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipActivity.initFun.2
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
                VipActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.btn1) {
            Intent intent = new Intent(this, (Class<?>) VipCouponListActivity.class);
            intent.putExtra("type", 0);
            intent.putExtra("title", "周周领券");
            startActivity(intent);
        }
        if (id == R.id.ll_right) {
            getMBinding().setRight(!getMBinding().getRight());
            return;
        }
        if (id == R.id.tv_gift) {
            startActivity(VipGiftListActivity.class);
            return;
        }
        switch (id) {
            case R.id.btn2 /* 2131361942 */:
                Intent intent2 = new Intent(this, (Class<?>) VipFlbListActivity.class);
                intent2.putExtra("type", 1);
                intent2.putExtra("title", "VIP升级奖励");
                startActivity(intent2);
                break;
            case R.id.btn3 /* 2131361943 */:
                Intent intent3 = new Intent(this, (Class<?>) VipFlbListActivity.class);
                intent3.putExtra("type", 2);
                intent3.putExtra("title", "生日福利币");
                startActivity(intent3);
                break;
            case R.id.btn4 /* 2131361944 */:
                Intent intent4 = new Intent(this, (Class<?>) VipCouponListActivity.class);
                intent4.putExtra("type", 3);
                intent4.putExtra("title", "节日优惠券");
                startActivity(intent4);
                break;
            case R.id.btn5 /* 2131361945 */:
                Intent intent5 = new Intent(this, (Class<?>) VipCouponListActivity.class);
                intent5.putExtra("type", 4);
                intent5.putExtra("title", "新游代金券");
                startActivity(intent5);
                break;
            case R.id.btn6 /* 2131361946 */:
                Intent intent6 = new Intent(this, (Class<?>) VipFlbListActivity.class);
                intent6.putExtra("type", 4);
                intent6.putExtra("title", "活动特权");
                startActivity(intent6);
                break;
        }
    }
}
