package androidx.compose.runtime.collection;

import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Add missing generic type declarations: [Key] */
/* JADX INFO: compiled from: IdentityArrayMap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.collection.IdentityArrayMap$asMap$1$keys$1$iterator$1", f = "IdentityArrayMap.kt", i = {0, 0}, l = {KeyBoardKey.KeyboardKeyZoom}, m = "invokeSuspend", n = {"$this$sequence", "index"}, s = {"L$0", "I$0"})
final class IdentityArrayMap$asMap$1$keys$1$iterator$1<Key> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Key>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IdentityArrayMap<Key, Value> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IdentityArrayMap$asMap$1$keys$1$iterator$1(IdentityArrayMap<Key, Value> identityArrayMap, Continuation<? super IdentityArrayMap$asMap$1$keys$1$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = identityArrayMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IdentityArrayMap$asMap$1$keys$1$iterator$1 identityArrayMap$asMap$1$keys$1$iterator$1 = new IdentityArrayMap$asMap$1$keys$1$iterator$1(this.this$0, continuation);
        identityArrayMap$asMap$1$keys$1$iterator$1.L$0 = obj;
        return identityArrayMap$asMap$1$keys$1$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Key> sequenceScope, Continuation<? super Unit> continuation) {
        return ((IdentityArrayMap$asMap$1$keys$1$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x004c -> B:13:0x004f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L1f
            if (r1 != r2) goto L17
            int r1 = r6.I$1
            int r3 = r6.I$0
            java.lang.Object r4 = r6.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4f
        L17:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1f:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            androidx.compose.runtime.collection.IdentityArrayMap<Key, Value> r1 = r6.this$0
            int r1 = r1.getSize()
            r3 = 0
            r4 = r7
        L2e:
            if (r3 >= r1) goto L51
            androidx.compose.runtime.collection.IdentityArrayMap<Key, Value> r7 = r6.this$0
            java.lang.Object[] r7 = r7.getKeys()
            r7 = r7[r3]
            java.lang.String r5 = "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r5)
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.L$0 = r4
            r6.I$0 = r3
            r6.I$1 = r1
            r6.label = r2
            java.lang.Object r7 = r4.yield(r7, r5)
            if (r7 != r0) goto L4f
            return r0
        L4f:
            int r3 = r3 + r2
            goto L2e
        L51:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.IdentityArrayMap$asMap$1$keys$1$iterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
