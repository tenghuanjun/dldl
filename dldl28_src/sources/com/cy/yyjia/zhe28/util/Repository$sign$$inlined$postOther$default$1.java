package com.cy.yyjia.zhe28.util;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.cy.yyjia.zhe28.domain.BaseResult;
import com.cy.yyjia.zhe28.domain.SignResultBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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

/* JADX INFO: compiled from: NetUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;", "com/cy/yyjia/zhe28/util/NetUtil$postOther$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.cy.yyjia.zhe28.util.Repository$sign$$inlined$postOther$default$1", f = "Repository.kt", i = {}, l = {448}, m = "invokeSuspend", n = {}, s = {})
public final class Repository$sign$$inlined$postOther$default$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $fail;
    final /* synthetic */ ArrayList $fileList;
    final /* synthetic */ Map $param;
    final /* synthetic */ Function1 $success;
    final /* synthetic */ String $url;
    final /* synthetic */ boolean $useToken;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Repository$sign$$inlined$postOther$default$1(Function1 function1, Function1 function12, String str, Map map, boolean z, ArrayList arrayList, Continuation continuation) {
        super(2, continuation);
        this.$success = function1;
        this.$fail = function12;
        this.$url = str;
        this.$param = map;
        this.$useToken = z;
        this.$fileList = arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Repository$sign$$inlined$postOther$default$1(this.$success, this.$fail, this.$url, this.$param, this.$useToken, this.$fileList, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Repository$sign$$inlined$postOther$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.util.Repository$sign$$inlined$postOther$default$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: NetUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/CoroutineScope;", "com/cy/yyjia/zhe28/util/NetUtil$postOther$1$result$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$postOther$1$result$1", f = "NetUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetUtil.RequestResult<? extends BaseResult<SignResultBean>>>, Object> {
        final /* synthetic */ ArrayList $fileList;
        final /* synthetic */ Map $param;
        final /* synthetic */ String $url;
        final /* synthetic */ boolean $useToken;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Map map, boolean z, ArrayList arrayList, Continuation continuation) {
            super(2, continuation);
            this.$url = str;
            this.$param = map;
            this.$useToken = z;
            this.$fileList = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$url, this.$param, this.$useToken, this.$fileList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NetUtil.RequestResult<? extends BaseResult<SignResultBean>>> continuation) {
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
            boolean z = this.$useToken;
            ArrayList arrayList = this.$fileList;
            if (!StringsKt.startsWith$default(str, "http", false, 2, (Object) null)) {
                str = NetUtil.BASE_URL1 + str;
            }
            String str2 = "";
            for (String str3 : map.keySet()) {
                str2 = str2 + "&" + str3 + "=" + ((Object) map.get(str3));
            }
            try {
                String token = Constant.INSTANCE.getToken();
                PostRequest postRequestPost = OkGo.post(str);
                if (z) {
                    postRequestPost.headers("Authorization", "bearer " + token);
                }
                Integer numBoxInt = arrayList != null ? Boxing.boxInt(arrayList.size()) : null;
                Intrinsics.checkNotNull(numBoxInt);
                if (numBoxInt.intValue() > 0) {
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        int i2 = i + 1;
                        postRequestPost.params("file" + i2, (File) arrayList.get(i));
                        i = i2;
                    }
                }
                Response responseExecute = ((PostRequest) postRequestPost.params(map, new boolean[0])).execute();
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
                        Object objFromJson = new Gson().fromJson(strString, new TypeToken<BaseResult<SignResultBean>>() { // from class: com.cy.yyjia.zhe28.util.Repository$sign$.inlined.postOther.default.1.1.1
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.$url, this.$param, this.$useToken, this.$fileList, null), this);
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
