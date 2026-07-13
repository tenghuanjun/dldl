package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.DialogSelectTrumpetBinding;
import com.cy.yyjia.zhe28.databinding.ItemSelectTrumpetBinding;
import com.cy.yyjia.zhe28.domain.GiftDetailBean;
import com.cy.yyjia.zhe28.domain.ReceiveStatusBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.volcengine.common.contant.CommonConstants;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: ReceiveStatusDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\u001a\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fJ\u001e\u0010!\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ)\u0010\"\u001a\u00020\u00002!\u0010#\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u001d0$R'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006("}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/ReceiveStatusDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogSelectTrumpetBinding;", "activity", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/ReceiveStatusBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemSelectTrumpetBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "gid", "", "getGid", "()I", "setGid", "(I)V", "id", "getId", "setId", "type", "getType", "setType", "data", "", "getData", "", "receive", CommonConstants.key_accountId, "receiveAll", "set", "setConfirm", "onConfirm", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "account", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ReceiveStatusDialog extends BaseDataBindingDialog<DialogSelectTrumpetBinding, ReceiveStatusDialog> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int gid;
    private int id;
    private int type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReceiveStatusDialog(Context activity) {
        super(activity, R.layout.dialog_select_trumpet);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<ReceiveStatusBean, ItemSelectTrumpetBinding>>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<ReceiveStatusBean, ItemSelectTrumpetBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_select_trumpet, null, 2, null);
            }
        });
        ((DialogSelectTrumpetBinding) this.mBinding).rv.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReceiveStatusDialog._init_$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        ((DialogSelectTrumpetBinding) this.mBinding).tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReceiveStatusDialog._init_$lambda$1(this.f$0, view);
            }
        });
        ((DialogSelectTrumpetBinding) this.mBinding).tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReceiveStatusDialog._init_$lambda$2(this.f$0, view);
            }
        });
    }

    public final BaseAdapter<ReceiveStatusBean, ItemSelectTrumpetBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final int getGid() {
        return this.gid;
    }

    public final void setGid(int i) {
        this.gid = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(ReceiveStatusDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getAdapter().getItem(i).isCollected()) {
            return;
        }
        Iterator<ReceiveStatusBean> it = this$0.getAdapter().getData().iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        this$0.getAdapter().getItem(i).setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(ReceiveStatusDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getAdapter().getData().size() == 0) {
            this$0.dismiss();
            return;
        }
        for (ReceiveStatusBean receiveStatusBean : this$0.getAdapter().getData()) {
            if (receiveStatusBean.getSelected()) {
                this$0.receive(receiveStatusBean.getId());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(ReceiveStatusDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final ReceiveStatusDialog set(int type, int id, int gid) {
        this.type = type;
        this.id = id;
        this.gid = gid;
        if (type == 1) {
            ((DialogSelectTrumpetBinding) this.mBinding).tvTip.setText("您游戏中还未创建小号\n暂时无法领取此礼包");
        }
        getData();
        return this;
    }

    public final ReceiveStatusDialog data(List<ReceiveStatusBean> data, int gid) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.gid = gid;
        ((DialogSelectTrumpetBinding) this.mBinding).setData(data);
        return this;
    }

    public final ReceiveStatusDialog setConfirm(final Function1<? super ReceiveStatusBean, Unit> onConfirm) {
        Intrinsics.checkNotNullParameter(onConfirm, "onConfirm");
        ((DialogSelectTrumpetBinding) this.mBinding).tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReceiveStatusDialog.setConfirm$lambda$3(this.f$0, onConfirm, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setConfirm$lambda$3(ReceiveStatusDialog this$0, Function1 onConfirm, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onConfirm, "$onConfirm");
        Iterator<ReceiveStatusBean> it = this$0.getAdapter().getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ReceiveStatusBean next = it.next();
            if (next.getSelected()) {
                onConfirm.invoke(next);
                break;
            }
        }
        this$0.dismiss();
    }

    public final void getData() {
        Repository.INSTANCE.getReceiveStatus(this.type, this.id, this.gid, new Function1<List<ReceiveStatusBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<ReceiveStatusBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<ReceiveStatusBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Iterator<ReceiveStatusBean> it2 = it.iterator();
                while (it2.hasNext()) {
                    it2.next().setSelected(false);
                }
                Iterator<ReceiveStatusBean> it3 = it.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    ReceiveStatusBean next = it3.next();
                    if (!next.isCollected()) {
                        next.setSelected(true);
                        break;
                    }
                }
                ((DialogSelectTrumpetBinding) ReceiveStatusDialog.this.mBinding).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.getData.2
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
                ReceiveStatusDialog.this.dismiss();
                Log.e("ReceiveStatusDialog: ", it.getLocalizedMessage());
                Toast.makeText(ReceiveStatusDialog.this.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }

    public final void receive(int accountId) {
        Repository.INSTANCE.receive(this.type, this.id, String.valueOf(accountId), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.receive.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$receive$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: ReceiveStatusDialog.kt */
            @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "gift", "Lcom/cy/yyjia/zhe28/domain/GiftDetailBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            static final class C02301 extends Lambda implements Function1<GiftDetailBean, Unit> {
                final /* synthetic */ Result $it;
                final /* synthetic */ ReceiveStatusDialog this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02301(ReceiveStatusDialog receiveStatusDialog, Result result) {
                    super(1);
                    this.this$0 = receiveStatusDialog;
                    this.$it = result;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(GiftDetailBean giftDetailBean) {
                    invoke2(giftDetailBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GiftDetailBean gift) {
                    Intrinsics.checkNotNullParameter(gift, "gift");
                    Context context = this.this$0.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "access$getContext(...)");
                    QuickDialog quickDialog = new QuickDialog(context, R.layout.dialog_gift_code);
                    Object data = this.$it.getData();
                    Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.String");
                    QuickDialog variable = quickDialog.setData((String) data).setVariable(45, gift.getHowToUser());
                    final ReceiveStatusDialog receiveStatusDialog = this.this$0;
                    final Result result = this.$it;
                    variable.setOnClickListener(R.id.tv_copy, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog$receive$1$1$$ExternalSyntheticLambda0
                        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                        public final void onClick(BaseDialog baseDialog, View view) {
                            ReceiveStatusDialog.C11221.C02301.invoke$lambda$0(receiveStatusDialog, result, baseDialog, view);
                        }
                    }).show();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$0(ReceiveStatusDialog this$0, Result it, BaseDialog baseDialog, View view) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(it, "$it");
                    Util.copy(this$0.getContext(), (String) it.getData());
                }
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getCode() != 200 || ReceiveStatusDialog.this.getType() != 1) {
                    Toast.makeText(ReceiveStatusDialog.this.getContext(), it.getMsg(), 0).show();
                } else {
                    Repository.INSTANCE.getGameGiftDetail(ReceiveStatusDialog.this.getId(), new C02301(ReceiveStatusDialog.this, it), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.receive.1.2
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Exception it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                            invoke2(exc);
                            return Unit.INSTANCE;
                        }
                    });
                }
                ReceiveStatusDialog.this.dismiss();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.receive.2
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
                ReceiveStatusDialog.this.dismiss();
                Log.e("ReceiveStatusDialog: ", it.getLocalizedMessage());
                Toast.makeText(ReceiveStatusDialog.this.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }

    public final void receiveAll(int accountId) {
        Repository.INSTANCE.receiveAll(this.gid, String.valueOf(accountId), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.receiveAll.1
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
                Toast.makeText(ReceiveStatusDialog.this.getContext(), it.getMsg(), 0).show();
                ReceiveStatusDialog.this.dismiss();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog.receiveAll.2
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
                ReceiveStatusDialog.this.dismiss();
                Log.e("ReceiveStatusDialog: ", it.getLocalizedMessage());
                Toast.makeText(ReceiveStatusDialog.this.getContext(), it.getLocalizedMessage(), 0).show();
            }
        });
    }
}
