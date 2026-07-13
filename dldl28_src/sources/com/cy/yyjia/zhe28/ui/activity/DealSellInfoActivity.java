package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealPicBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity;
import com.cy.yyjia.zhe28.ui.dialog.PinDialog;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.math.BigDecimal;
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
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: DealSellInfoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017J\u0006\u0010\u0018\u001a\u00020\u0015J\u0006\u0010\u0019\u001a\u00020\u0015J\u0006\u0010\u001a\u001a\u00020\u0015J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\"\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#H\u0016J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DealSellInfoActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDealSellInfoBinding;", "Landroid/view/View$OnClickListener;", "()V", "REQUEST_CODE", "", "id", "getId", "()I", "id$delegate", "Lkotlin/Lazy;", "maxCount", "picAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemDealPicBinding;", "getPicAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "picAdapter$delegate", "checkPermission", "", "success", "Lkotlin/Function0;", "checkPrice", "getData", "getRole", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "submit", "pin", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealSellInfoActivity extends BaseActivity<ActivityDealSellInfoBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final int REQUEST_CODE;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;
    private final int maxCount;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;

    public static final /* synthetic */ ActivityDealSellInfoBinding access$getMBinding(DealSellInfoActivity dealSellInfoActivity) {
        return dealSellInfoActivity.getMBinding();
    }

    public DealSellInfoActivity() {
        super(R.layout.activity_deal_sell_info, 2);
        this.REQUEST_CODE = 11;
        this.maxCount = 8;
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
        this.picAdapter = LazyKt.lazy(new Function0<BaseAdapter<String, ItemDealPicBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$picAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<String, ItemDealPicBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_deal_pic, new ArrayList(), new Function3<BaseDataBindingHolder<ItemDealPicBinding>, Integer, String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$picAdapter$2.1
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
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final BaseAdapter<String, ItemDealPicBinding> getPicAdapter() {
        return (BaseAdapter) this.picAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(getPicAdapter());
        getPicAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealSellInfoActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getPicAdapter().addChildClickViewIds(R.id.iv_delete);
        getPicAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealSellInfoActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        EditText etDesc = getMBinding().etDesc;
        Intrinsics.checkNotNullExpressionValue(etDesc, "etDesc");
        etDesc.addTextChangedListener(new TextWatcher() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$init$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                DealSellInfoActivity.access$getMBinding(this.this$0).tvLimit.setText((s != null ? Integer.valueOf(s.length()) : null) + "/100");
            }
        });
        getMBinding().tvDicker.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DealSellInfoActivity.init$lambda$4(this.f$0, view);
            }
        });
        EditText etPrice = getMBinding().etPrice;
        Intrinsics.checkNotNullExpressionValue(etPrice, "etPrice");
        etPrice.addTextChangedListener(new TextWatcher() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$init$$inlined$addTextChangedListener$default$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                this.this$0.checkPrice();
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final DealSellInfoActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.checkPermission(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$init$1$1
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
    public static final void init$lambda$1(DealSellInfoActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (!Intrinsics.areEqual(this$0.getPicAdapter().getItem(this$0.getPicAdapter().getItemCount() - 1), "")) {
            this$0.getPicAdapter().addData("");
        }
        this$0.getPicAdapter().removeAt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static final void init$lambda$4(DealSellInfoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DealBean data = this$0.getMBinding().getData();
        if (data != 0) {
            ?? r0 = data.isDicker() == 0 ? 1 : 0;
            data.setDicker(r0);
            view.setSelected(r0);
        }
    }

    public final void getData() {
        Repository.INSTANCE.getDealSellDetail(getId(), new Function1<DealBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DealBean dealBean) {
                invoke2(dealBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DealBean it) {
                List<String> pic;
                Intrinsics.checkNotNullParameter(it, "it");
                if (Intrinsics.areEqual(it.getSellMoney(), "") || Double.parseDouble(it.getSellMoney()) == 0.0d) {
                    it.setSellMoney("");
                }
                List<String> pic2 = it.getPic();
                if ((pic2 == null || pic2.isEmpty()) && (pic = it.getPic()) != null) {
                    pic.add("");
                }
                DealSellInfoActivity.access$getMBinding(DealSellInfoActivity.this).setData(it);
                DealSellInfoActivity.this.getRole();
                DealSellInfoActivity.this.checkPrice();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity.getData.2
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
                DealSellInfoActivity dealSellInfoActivity = DealSellInfoActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                dealSellInfoActivity.toast(localizedMessage);
                DealSellInfoActivity.this.finish();
            }
        });
    }

    public final void checkPermission(final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{Permission.READ_MEDIA_IMAGES, Permission.WRITE_EXTERNAL_STORAGE});
        DealSellInfoActivity dealSellInfoActivity = this;
        if (XXPermissions.isGranted(dealSellInfoActivity, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(dealSellInfoActivity).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$$ExternalSyntheticLambda0
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    DealSellInfoActivity.checkPermission$lambda$6(success, list, z);
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

    public final void submit(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (getMBinding().getData() == null) {
            return;
        }
        if (getPicAdapter().getItemCount() <= 4) {
            toast("最少上传四张图片");
            return;
        }
        DealBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (data.getSellMoney().length() == 0) {
            toast("请输入出售价格");
            return;
        }
        DealBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        if (data2.getService().length() == 0) {
            toast("请输入区服");
            return;
        }
        DealBean data3 = getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        if (data3.getRoleName().length() == 0) {
            toast("请输入角色名");
        } else {
            new PinDialog(this).setTitle("卖家须知").setTip("1.请据实描述账号详细情况，填写出售申请，因描述不实造成的任何损失将由卖家自己承担。\n\n2.交易完成后，不支持找回，出售前请先仔细了解《交易说明》").onSubmit(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity.submit.1
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
                    DealSellInfoActivity.this.submit(it);
                }
            }).show();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void submit(String pin) {
        Intrinsics.checkNotNullParameter(pin, "pin");
        WaitDialog text = new WaitDialog(this).setText("正在上传");
        text.show();
        DealBean data = getMBinding().getData();
        if (data != null) {
            String token = com.cy.yyjia.zhe28.util.Constant.INSTANCE.getToken();
            int i = 0;
            PostRequest postRequest = (PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) OkGo.post(NetUtil.BASE_URL1 + "sell/create").headers("Authorization", "bearer " + token)).params("id", data.getId(), new boolean[0])).params("pin", pin, new boolean[0])).params(SocialConstants.PARAM_COMMENT, data.getDescription(), new boolean[0])).params("service", data.getService(), new boolean[0])).params("productName", data.getGame().getName(), new boolean[0])).params("roleName", data.getRoleName(), new boolean[0])).params("sellMoney", data.getSellMoney(), new boolean[0])).params("isDicker", data.isDicker(), new boolean[0])).params("v", 1, new boolean[0]);
            ArrayList arrayList = new ArrayList();
            for (String str : getPicAdapter().getData()) {
                if (str.length() > 0) {
                    arrayList.add(new File(str));
                }
            }
            int size = arrayList.size();
            while (i < size) {
                int i2 = i + 1;
                postRequest.params("file" + i2, (File) arrayList.get(i));
                i = i2;
            }
            String string = postRequest.getParams().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            log(string);
            postRequest.execute(new DealSellInfoActivity$submit$2$1(this, text));
        }
    }

    public final void checkPrice() {
        DealBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (data.getSellMoney().length() > 0) {
            DealBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            double d = Double.parseDouble(data2.getSellMoney());
            double dCoerceAtLeast = RangesKt.coerceAtLeast(d - RangesKt.coerceAtLeast(new BigDecimal(d).divide(new BigDecimal(10)).doubleValue(), 5.0d), 0.0d);
            if (d < 10.0d) {
                getMBinding().tvTag5.setText("不低于10元出售，到手可得" + dCoerceAtLeast + "余额。手续费10%最低5余额！");
                return;
            }
            getMBinding().tvTag5.setText("到手可得" + dCoerceAtLeast + "余额。手续费10%最低5余额！");
        }
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$getRole$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DealSellInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/GMRoleBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C09261 extends Lambda implements Function1<List<GMRoleBean>, Unit> {
        C09261() {
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
            if (it.size() != 0) {
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_deal_role, it);
                final BaseDialog baseDialogShow = new QuickDialog(DealSellInfoActivity.this, R.layout.dialog_gm_roles).setAdapter(R.id.rv, baseAdapter).setOnClickListener(R.id.iv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$getRole$1$$ExternalSyntheticLambda0
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        baseDialog.dismiss();
                    }
                }).show();
                final DealSellInfoActivity dealSellInfoActivity = DealSellInfoActivity.this;
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$getRole$1$$ExternalSyntheticLambda1
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        DealSellInfoActivity.C09261.invoke$lambda$1(dealSellInfoActivity, baseAdapter, baseDialogShow, baseQuickAdapter, view, i);
                    }
                });
                return;
            }
            DealSellInfoActivity.this.toast("无法识别，请手动添加信息");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$1(DealSellInfoActivity this$0, BaseAdapter roleAdapter, BaseDialog baseDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(roleAdapter, "$roleAdapter");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            DealBean data = DealSellInfoActivity.access$getMBinding(this$0).getData();
            Intrinsics.checkNotNull(data);
            data.setRoleName(((GMRoleBean) roleAdapter.getItem(i)).getRoleName());
            DealBean data2 = DealSellInfoActivity.access$getMBinding(this$0).getData();
            Intrinsics.checkNotNull(data2);
            data2.setService(((GMRoleBean) roleAdapter.getItem(i)).getServiceCode());
            DealSellInfoActivity.access$getMBinding(this$0).setData(DealSellInfoActivity.access$getMBinding(this$0).getData());
            baseDialog.dismiss();
        }
    }

    public final void getRole() {
        Repository repository = Repository.INSTANCE;
        DealBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        repository.getAccoutRole(data.getGameId(), getId(), new C09261(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity.getRole.2
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
                DealSellInfoActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        getRole();
    }
}
