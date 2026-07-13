package com.cy.yyjia.zhe28.util;

import android.util.Log;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.ArrayList;
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
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: NetUtil.kt */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = KeyBoardKey.KeyboardKeyMediaNextTrack)
@DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$getOther$1$result$1", f = "NetUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class NetUtil$getOther$1$result$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetUtil.RequestResult<? extends T>>, Object> {
    final /* synthetic */ Map<String, String> $param;
    final /* synthetic */ String $url;
    final /* synthetic */ boolean $useToken;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetUtil$getOther$1$result$1(String str, Map<String, String> map, boolean z, Continuation<? super NetUtil$getOther$1$result$1> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$param = map;
        this.$useToken = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.needClassReification();
        return new NetUtil$getOther$1$result$1<>(this.$url, this.$param, this.$useToken, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NetUtil.RequestResult<? extends T>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        NetUtil netUtil = NetUtil.INSTANCE;
        String str = this.$url;
        Map<String, String> map = this.$param;
        boolean z = this.$useToken;
        new ArrayList();
        if (!StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
            str = NetUtil.BASE_URL1 + str;
        }
        String str2 = "";
        for (String str3 : map.keySet()) {
            str2 = str2 + "&" + str3 + "=" + ((Object) map.get(str3));
        }
        try {
            String token = Constant.INSTANCE.getToken();
            GetRequest getRequest = OkGo.get(str);
            if (z) {
                getRequest.headers("Authorization", "bearer " + token);
            }
            Response responseExecute = ((GetRequest) getRequest.params(map, new boolean[0])).execute();
            Intrinsics.checkNotNull(responseExecute);
            ResponseBody responseBodyBody = responseExecute.body();
            String strString = responseBodyBody != null ? responseBodyBody.string() : null;
            Log.e("request: ", "请求接口" + responseExecute.request().url());
            Log.e("request: ", "请求结果" + strString);
            if (strString != null) {
                if (responseExecute.isSuccessful()) {
                    Intrinsics.needClassReification();
                    Object objFromJson = new Gson().fromJson(strString, new TypeToken<T>() { // from class: com.cy.yyjia.zhe28.util.NetUtil$getOther$1$result$1$invokeSuspend$$inlined$requestOther$default$1
                    }.getType());
                    Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                    return new NetUtil.RequestResult.Success(objFromJson);
                }
                return new NetUtil.RequestResult.Error(new Exception("fail" + responseExecute.code()));
            }
            return new NetUtil.RequestResult.Error(new Exception("返回为空"));
        } catch (Exception e) {
            return new NetUtil.RequestResult.Error(e);
        }
    }
}
