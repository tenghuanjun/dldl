package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityItemSellBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.open.SocialConstants;
import com.volcengine.common.contant.CommonConstants;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: ItemSellActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0005J\b\u0010\n\u001a\u00020\u0005H\u0016J\"\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ItemSellActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityItemSellBinding;", "()V", "checkPermission", "", "success", "Lkotlin/Function0;", "getPic", "getServer", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "", "resultCode", "data", "Landroid/content/Intent;", "submit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ItemSellActivity extends BaseActivity<ActivityItemSellBinding> {
    public static final int $stable = 0;

    public ItemSellActivity() {
        super(R.layout.activity_item_sell, 2);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setDesc("");
        getMBinding().setData(new ItemTradeBean());
        getMBinding().llGame.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemSellActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemSellActivity.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().llServer.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemSellActivity.init$lambda$2(this.f$0, view);
            }
        });
        getMBinding().iv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemSellActivity.init$lambda$3(this.f$0, view);
            }
        });
        getMBinding().et.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ItemSellActivity.init$lambda$4(this.f$0, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(ItemSellActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0, (Class<?>) ItemSellGameActivity.class), 7787);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(ItemSellActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(ItemSellActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getServer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(ItemSellActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(ItemSellActivity this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ItemTradeBean data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (StringsKt.contains$default((CharSequence) data.getPrice(), (CharSequence) ".", false, 2, (Object) null)) {
            ItemTradeBean data2 = this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            List listSplit$default = StringsKt.split$default((CharSequence) data2.getPrice(), new String[]{"."}, false, 0, 6, (Object) null);
            String str = (String) listSplit$default.get(0);
            String strSubstring = (String) listSplit$default.get(1);
            if (((String) listSplit$default.get(0)).length() == 0) {
                str = "0";
            }
            if (((String) listSplit$default.get(1)).length() > 2) {
                strSubstring = ((String) listSplit$default.get(1)).substring(0, 2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            }
            this$0.getMBinding().et.setText(str + "." + strSubstring);
        }
    }

    public final void getServer() {
        ItemTradeBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (data.getGame() == null) {
            getMBinding().llGame.performClick();
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ItemTradeBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        GameBean game = data2.getGame();
        Intrinsics.checkNotNull(game);
        linkedHashMap.put(CommonConstants.key_gameId, String.valueOf(game.getId()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemSellActivity$getServer$$inlined$get$1("game/asset/getServiceByGame", linkedHashMap, null, this, this), 3, null);
    }

    public final void submit() {
        getMBinding().et.clearFocus();
        ItemTradeBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        log(data.toString());
        ItemTradeBean data2 = getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        if (data2.getGame() == null) {
            toast("请选择游戏");
            return;
        }
        ItemTradeBean data3 = getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        if (TextUtils.isEmpty(data3.getServiceCode())) {
            toast("请填写区服");
            return;
        }
        ItemTradeBean data4 = getMBinding().getData();
        Intrinsics.checkNotNull(data4);
        if (TextUtils.isEmpty(data4.getName())) {
            toast("请填写材料名称");
            return;
        }
        ItemTradeBean data5 = getMBinding().getData();
        Intrinsics.checkNotNull(data5);
        if (TextUtils.isEmpty(data5.getPic())) {
            toast("请上传图片");
            return;
        }
        ItemTradeBean data6 = getMBinding().getData();
        Intrinsics.checkNotNull(data6);
        if (!TextUtils.isEmpty(data6.getInputNum())) {
            ItemTradeBean data7 = getMBinding().getData();
            Intrinsics.checkNotNull(data7);
            ItemTradeBean data8 = getMBinding().getData();
            Intrinsics.checkNotNull(data8);
            data7.setNum(Integer.parseInt(data8.getInputNum()));
            ItemTradeBean data9 = getMBinding().getData();
            Intrinsics.checkNotNull(data9);
            if (data9.getNum() < 1) {
                toast("请输入大于0的数量");
            }
        }
        ItemTradeBean data10 = getMBinding().getData();
        Intrinsics.checkNotNull(data10);
        if (!TextUtils.isEmpty(data10.getPrice())) {
            ItemTradeBean data11 = getMBinding().getData();
            Intrinsics.checkNotNull(data11);
            if (Double.parseDouble(data11.getPrice()) <= 0.0d) {
                toast("请输入大于0的单价");
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ItemTradeBean data12 = getMBinding().getData();
        Intrinsics.checkNotNull(data12);
        GameBean game = data12.getGame();
        Intrinsics.checkNotNull(game);
        linkedHashMap.put(CommonConstants.key_gameId, String.valueOf(game.getId()));
        ItemTradeBean data13 = getMBinding().getData();
        Intrinsics.checkNotNull(data13);
        linkedHashMap.put("serviceCode", data13.getServiceCode());
        ItemTradeBean data14 = getMBinding().getData();
        Intrinsics.checkNotNull(data14);
        linkedHashMap.put("name", data14.getName());
        String desc = getMBinding().getDesc();
        Intrinsics.checkNotNull(desc);
        linkedHashMap.put(SocialConstants.PARAM_COMMENT, desc);
        ItemTradeBean data15 = getMBinding().getData();
        Intrinsics.checkNotNull(data15);
        linkedHashMap.put("num", String.valueOf(data15.getNum()));
        ItemTradeBean data16 = getMBinding().getData();
        Intrinsics.checkNotNull(data16);
        linkedHashMap.put("price", data16.getPrice());
        NetUtil netUtil = NetUtil.INSTANCE;
        ItemTradeBean data17 = getMBinding().getData();
        Intrinsics.checkNotNull(data17);
        netUtil.post3("game/asset/sellAsset", new File(data17.getPic()), "pic", new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity.submit.1
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
                ItemSellActivity.this.toast(it.getMsg());
                ItemSellActivity.this.finish();
                ItemSellActivity.this.setResult(777);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity.submit.2
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
                ItemSellActivity.this.netFail(it);
            }
        }, linkedHashMap);
    }

    public final void getPic() {
        checkPermission(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity.getPic.1
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
                ImageSelector.builder().useCamera(false).setSingle(true).setMaxSelectCount(1).canPreview(false).start(ItemSellActivity.this.getMContext(), 9998);
            }
        });
    }

    public final void checkPermission(final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        List<String> listListOf = CollectionsKt.listOf(Permission.READ_MEDIA_IMAGES);
        ItemSellActivity itemSellActivity = this;
        if (XXPermissions.isGranted(itemSellActivity, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(itemSellActivity).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellActivity$$ExternalSyntheticLambda0
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    ItemSellActivity.checkPermission$lambda$7(success, list, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPermission$lambda$7(Function0 success, List permissions, boolean z) {
        Intrinsics.checkNotNullParameter(success, "$success");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (z) {
            success.invoke();
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 7787) {
            ItemTradeBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            Intrinsics.checkNotNull(data);
            Serializable serializableExtra = data.getSerializableExtra("game");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.cy.yyjia.zhe28.domain.GameBean");
            data2.setGame((GameBean) serializableExtra);
            getMBinding().setData(getMBinding().getData());
        }
        if (requestCode != 9998 || data == null) {
            return;
        }
        ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
        Intrinsics.checkNotNull(stringArrayListExtra, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.String>");
        List listAsMutableList = TypeIntrinsics.asMutableList(stringArrayListExtra);
        ItemTradeBean data3 = getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        data3.setPic((String) listAsMutableList.get(0));
        Glide.with((FragmentActivity) this).load((String) listAsMutableList.get(0)).into(getMBinding().iv);
        getMBinding().setData(getMBinding().getData());
    }
}
