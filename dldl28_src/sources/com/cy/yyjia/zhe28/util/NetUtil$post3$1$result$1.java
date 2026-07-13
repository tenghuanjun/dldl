package com.cy.yyjia.zhe28.util;

import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NetUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lcom/cy/yyjia/zhe28/util/NetUtil$RequestResult;", "Lcom/cy/yyjia/zhe28/domain/Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.cy.yyjia.zhe28.util.NetUtil$post3$1$result$1", f = "NetUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class NetUtil$post3$1$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetUtil.RequestResult<? extends Result>>, Object> {
    final /* synthetic */ File $file;
    final /* synthetic */ String $fileName;
    final /* synthetic */ Map<String, String> $param;
    final /* synthetic */ String $url;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NetUtil$post3$1$result$1(String str, Map<String, String> map, File file, String str2, Continuation<? super NetUtil$post3$1$result$1> continuation) {
        super(2, continuation);
        this.$url = str;
        this.$param = map;
        this.$file = file;
        this.$fileName = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetUtil$post3$1$result$1(this.$url, this.$param, this.$file, this.$fileName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super NetUtil.RequestResult<? extends Result>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super NetUtil.RequestResult<Result>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super NetUtil.RequestResult<Result>> continuation) {
        return ((NetUtil$post3$1$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return NetUtil.INSTANCE.requestResult2(this.$url, this.$param, this.$file, this.$fileName);
    }
}
