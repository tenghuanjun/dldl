package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"androidx/compose/material3/ExitAlwaysScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", SocialConstants.PARAM_SOURCE, "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExitAlwaysScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitAlwaysScrollBehavior this$0;

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public /* synthetic */ Object mo433onPreFlingQWom1Mo(long j, Continuation continuation) {
        return NestedScrollConnection.CC.m4532onPreFlingQWom1Mo$suspendImpl(this, j, continuation);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public /* synthetic */ long mo434onPreScrollOzD1aCk(long j, int i) {
        return NestedScrollConnection.CC.m4526$default$onPreScrollOzD1aCk(this, j, i);
    }

    ExitAlwaysScrollBehavior$nestedScrollConnection$1(ExitAlwaysScrollBehavior exitAlwaysScrollBehavior) {
        this.this$0 = exitAlwaysScrollBehavior;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo432onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m3235getZeroF1C5BW0();
        }
        BottomAppBarState state = this.this$0.getState();
        state.setContentOffset(state.getContentOffset() + Offset.m3220getYimpl(consumed));
        if ((this.this$0.getState().getHeightOffset() == 0.0f || this.this$0.getState().getHeightOffset() == this.this$0.getState().getHeightOffsetLimit()) && Offset.m3220getYimpl(consumed) == 0.0f && Offset.m3220getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m3220getYimpl(consumed));
        return Offset.INSTANCE.m3235getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo431onPostFlingRZ2iAVY(long r10, long r12, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r14) {
        /*
            r9 = this;
            boolean r0 = r14 instanceof androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1
            if (r0 == 0) goto L14
            r0 = r14
            androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = (androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = new androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1
            r0.<init>(r9, r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 2
            r2 = 1
            if (r1 == 0) goto L41
            if (r1 == r2) goto L37
            if (r1 != r8) goto L2f
            long r10 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L81
        L2f:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L37:
            long r12 = r0.J$0
            java.lang.Object r10 = r0.L$0
            androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1 r10 = (androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto L56
        L41:
            kotlin.ResultKt.throwOnFailure(r14)
            r0.L$0 = r9
            r0.J$0 = r12
            r0.label = r2
            r1 = r9
            r2 = r10
            r4 = r12
            r6 = r0
            java.lang.Object r14 = androidx.compose.ui.input.nestedscroll.NestedScrollConnection.CC.m4523$default$onPostFlingRZ2iAVY(r1, r2, r4, r6)
            if (r14 != r7) goto L55
            return r7
        L55:
            r10 = r9
        L56:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r1 = r14.getPackedValue()
            androidx.compose.material3.ExitAlwaysScrollBehavior r11 = r10.this$0
            androidx.compose.material3.BottomAppBarState r11 = r11.getState()
            float r12 = androidx.compose.ui.unit.Velocity.m6122getYimpl(r12)
            androidx.compose.material3.ExitAlwaysScrollBehavior r13 = r10.this$0
            androidx.compose.animation.core.DecayAnimationSpec r13 = r13.getFlingAnimationSpec()
            androidx.compose.material3.ExitAlwaysScrollBehavior r10 = r10.this$0
            androidx.compose.animation.core.AnimationSpec r10 = r10.getSnapAnimationSpec()
            r14 = 0
            r0.L$0 = r14
            r0.J$0 = r1
            r0.label = r8
            java.lang.Object r14 = androidx.compose.material3.AppBarKt.access$settleAppBarBottom(r11, r12, r13, r10, r0)
            if (r14 != r7) goto L80
            return r7
        L80:
            r10 = r1
        L81:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r12 = r14.getPackedValue()
            long r10 = androidx.compose.ui.unit.Velocity.m6125plusAH228Gc(r10, r12)
            androidx.compose.ui.unit.Velocity r10 = androidx.compose.ui.unit.Velocity.m6112boximpl(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExitAlwaysScrollBehavior$nestedScrollConnection$1.mo431onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
