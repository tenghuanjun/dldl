package com.cy.yyjia.zhe28.ui.activity;

import android.util.Log;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.domain.BaseResult;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: compiled from: NetUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;", "com/cy/yyjia/zhe28/util/NetUtil$get$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$getRole$$inlined$get$1", f = "ItemTradeDetailActivity.kt", i = {}, l = {382}, m = "invokeSuspend", n = {}, s = {})
public final class ItemTradeDetailActivity$getRole$$inlined$get$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map $param;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ ItemTradeDetailActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ItemTradeDetailActivity$getRole$$inlined$get$1(String str, Map map, Continuation continuation, ItemTradeDetailActivity itemTradeDetailActivity, ItemTradeDetailActivity itemTradeDetailActivity2) {
        super(2, continuation);
        this.$url = str;
        this.$param = map;
        this.this$0 = itemTradeDetailActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        String str = this.$url;
        Map map = this.$param;
        ItemTradeDetailActivity itemTradeDetailActivity = this.this$0;
        return new ItemTradeDetailActivity$getRole$$inlined$get$1(str, map, continuation, itemTradeDetailActivity, itemTradeDetailActivity);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ItemTradeDetailActivity$getRole$$inlined$get$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.$url, this.$param, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NetUtil.RequestResult requestResult = (NetUtil.RequestResult) obj;
        if (!(requestResult instanceof NetUtil.RequestResult.Success)) {
            if (requestResult instanceof NetUtil.RequestResult.Error) {
                this.this$0.netFail(((NetUtil.RequestResult.Error) requestResult).getE());
            }
        } else {
            List list = (List) ((NetUtil.RequestResult.Success) requestResult).getData();
            if (list.size() != 0) {
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_deal_role, list);
                final BaseDialog baseDialogShow = new QuickDialog(this.this$0, R.layout.dialog_gm_roles).setAdapter(R.id.rv, baseAdapter).setOnClickListener(R.id.iv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$getRole$1$dialog$1
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                    public final void onClick(BaseDialog baseDialog, View view) {
                        baseDialog.dismiss();
                    }
                }).show();
                final ItemTradeDetailActivity itemTradeDetailActivity = this.this$0;
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$getRole$1$1
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, int i2) {
                        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
                        itemTradeDetailActivity.getMBinding().setRole(baseAdapter.getItem(i2).getRoleName());
                        itemTradeDetailActivity.getMBinding().setRoleId(baseAdapter.getItem(i2).getRoleId());
                        itemTradeDetailActivity.setAccountId(baseAdapter.getItem(i2).getAccountId());
                        itemTradeDetailActivity.setServiceId(baseAdapter.getItem(i2).getServiceId());
                        baseDialogShow.dismiss();
                    }
                });
            } else {
                this.this$0.tip("未获取到该游戏区服下的角色信息");
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$getRole$$inlined$get$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;", "com/cy/yyjia/zhe28/util/NetUtil$get$1$result$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$get$1$result$1", f = "NetUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetUtil.RequestResult<? extends List<GMRoleBean>>>, Object> {
        final /* synthetic */ Map $param;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Map map, Continuation continuation) {
            super(2, continuation);
            this.$url = str;
            this.$param = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$url, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NetUtil.RequestResult<? extends List<GMRoleBean>>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            NetUtil netUtil = NetUtil.INSTANCE;
            String str = this.$url;
            Map<String, String> map = this.$param;
            if (!StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
                str = NetUtil.BASE_URL1 + str;
            }
            String str2 = "";
            for (String str3 : map.keySet()) {
                str2 = str2 + "&" + str3 + "=" + ((Object) map.get(str3));
            }
            try {
                String token = Constant.INSTANCE.getToken();
                Response responseExecute = ((GetRequest) ((GetRequest) OkGo.get(str).headers("Authorization", "bearer " + token)).params(map, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                Log.e("request: ", "请求接口" + responseExecute.request().url());
                Log.e("request: ", "请求结果" + strString);
                if (strString != null) {
                    if (responseExecute.isSuccessful()) {
                        JSONObject jSONObject = new JSONObject(strString);
                        int i = jSONObject.getInt("code");
                        String string = jSONObject.getString("msg");
                        if (i == 200) {
                            Object objFromJson = new Gson().fromJson(strString, new TypeToken<BaseResult<List<GMRoleBean>>>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeDetailActivity$getRole$.inlined.get.1.1.1
                            }.getType());
                            Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                            return new NetUtil.RequestResult.Success(((BaseResult) objFromJson).getData());
                        }
                        if (i == 403) {
                            netUtil.toLogin();
                            return new NetUtil.RequestResult.Error(new Exception(string));
                        }
                        if (i == 4004) {
                            String string2 = jSONObject.getString("data");
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            netUtil.toUrl(string2);
                            return new NetUtil.RequestResult.Error(new Exception(string));
                        }
                        Intrinsics.checkNotNull(string);
                        netUtil.toOther(string);
                        return new NetUtil.RequestResult.Error(new Exception(string));
                    }
                    return new NetUtil.RequestResult.Error(new Exception("fail" + responseExecute.code()));
                }
                return new NetUtil.RequestResult.Error(new Exception("返回为空"));
            } catch (Exception e) {
                return new NetUtil.RequestResult.Error(e);
            }
        }
    }
}
