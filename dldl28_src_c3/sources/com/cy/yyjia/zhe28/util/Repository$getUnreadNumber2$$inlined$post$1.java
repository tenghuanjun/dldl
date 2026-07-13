package com.cy.yyjia.zhe28.util;

import android.util.Log;
import com.cy.yyjia.zhe28.domain.BaseResult;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.request.PostRequest;
import com.tencent.open.SocialConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
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
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "com/cy/yyjia/zhe28/util/NetUtil$post$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.cy.yyjia.zhe28.util.Repository$getUnreadNumber2$$inlined$post$1", f = "Repository.kt", i = {}, l = {431}, m = "invokeSuspend", n = {}, s = {})
public final class Repository$getUnreadNumber2$$inlined$post$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $fail;
    final /* synthetic */ Map $param;
    final /* synthetic */ Function1 $success;
    final /* synthetic */ String $url;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Repository$getUnreadNumber2$$inlined$post$1(Function1 function1, Function1 function12, String str, Map map, Continuation continuation) {
        super(2, continuation);
        this.$success = function1;
        this.$fail = function12;
        this.$url = str;
        this.$param = map;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Repository$getUnreadNumber2$$inlined$post$1(this.$success, this.$fail, this.$url, this.$param, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.Repository$getUnreadNumber2$$inlined$post$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "com/cy/yyjia/zhe28/util/NetUtil$post$1$result$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$post$1$result$1", f = "NetUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetUtil.RequestResult<? extends Integer>>, Object> {
        final /* synthetic */ Map $param;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Map map, Continuation continuation) {
            super(2, continuation);
            this.$url = str;
            this.$param = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$url, this.$param, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NetUtil.RequestResult<? extends Integer>> continuation) {
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
            if (!StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
                str = NetUtil.BASE_URL1 + str;
            }
            String str2 = "";
            for (String str3 : map.keySet()) {
                str2 = str2 + "&" + str3 + "=" + ((Object) map.get(str3));
            }
            try {
                String token = Constant.INSTANCE.getToken();
                Response responseExecute = ((PostRequest) ((PostRequest) OkGo.post(str).headers("Authorization", "bearer " + token)).params(map, new boolean[0])).execute();
                Intrinsics.checkNotNull(responseExecute);
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                Log.e("request: ", "请求接口" + responseExecute.request().url() + "\n请求参数" + netUtil.getRequestParam(responseExecute.request()));
                StringBuilder sb = new StringBuilder();
                sb.append("请求结果");
                sb.append(strString);
                Log.e("request: ", sb.toString());
                if (strString != null) {
                    if (responseExecute.isSuccessful()) {
                        JSONObject jSONObject = new JSONObject(strString);
                        int i = jSONObject.getInt("code");
                        String string = jSONObject.getString(SocialConstants.PARAM_SEND_MSG);
                        if (i == 200) {
                            Object objFromJson = new Gson().fromJson(strString, new TypeToken<BaseResult<Integer>>() { // from class: com.cy.yyjia.zhe28.util.Repository$getUnreadNumber2$.inlined.post.1.1.1
                            }.getType());
                            Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(...)");
                            return new NetUtil.RequestResult.Success(((BaseResult) objFromJson).getData());
                        }
                        if (i == 403) {
                            netUtil.toLogin();
                            return new NetUtil.RequestResult.Error(new Exception(string));
                        }
                        if (i == 4004) {
                            String string2 = jSONObject.getString(CacheEntity.DATA);
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

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.$url, this.$param, null), (Continuation) this);
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
        if (requestResult instanceof NetUtil.RequestResult.Success) {
            this.$success.invoke(((NetUtil.RequestResult.Success) requestResult).getData());
        } else if (requestResult instanceof NetUtil.RequestResult.Error) {
            this.$fail.invoke(((NetUtil.RequestResult.Error) requestResult).getE());
        }
        return Unit.INSTANCE;
    }
}
