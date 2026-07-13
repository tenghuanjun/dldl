package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J=\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0019¢\u0006\u0002\b\u001dH¦@¢\u0006\u0002\u0010\u001eR\u001a\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R*\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\u00020\u0010X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/unit/Density;", "extendedTouchPadding", "Landroidx/compose/ui/geometry/Size;", "getExtendedTouchPadding-NH-jbRc", "()J", "<anonymous parameter 0>", "", "interceptOutOfBoundsChildEvents", "getInterceptOutOfBoundsChildEvents$annotations", "()V", "getInterceptOutOfBoundsChildEvents", "()Z", "setInterceptOutOfBoundsChildEvents", "(Z)V", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "awaitPointerEventScope", "R", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PointerInputScope extends Density {
    <R> Object awaitPointerEventScope(Function2<? super AwaitPointerEventScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation);

    /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc */
    long mo336getExtendedTouchPaddingNHjbRc();

    boolean getInterceptOutOfBoundsChildEvents();

    /* JADX INFO: renamed from: getSize-YbymL2g */
    long mo337getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    void setInterceptOutOfBoundsChildEvents(boolean z);

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInputScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    public final /* synthetic */ class CC {
        public static boolean $default$getInterceptOutOfBoundsChildEvents(PointerInputScope _this) {
            return false;
        }

        public static void $default$setInterceptOutOfBoundsChildEvents(PointerInputScope _this, boolean z) {
        }

        /* JADX INFO: renamed from: $default$getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m4681$default$getExtendedTouchPaddingNHjbRc(PointerInputScope _this) {
            return Size.INSTANCE.m3297getZeroNHjbRc();
        }
    }

    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getInterceptOutOfBoundsChildEvents$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4696roundToPxR2X_6o(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m5846$default$roundToPxR2X_6o(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4697roundToPx0680j_4(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m5847$default$roundToPx0680j_4(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4698toDpGaN1DYA(PointerInputScope pointerInputScope, long j) {
            return FontScaling.CC.m5991$default$toDpGaN1DYA(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4699toDpu2uoSUM(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m5848$default$toDpu2uoSUM(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4700toDpu2uoSUM(PointerInputScope pointerInputScope, int i) {
            return Density.CC.m5849$default$toDpu2uoSUM((Density) pointerInputScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4701toDpSizekrfVVM(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m5850$default$toDpSizekrfVVM(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4702toPxR2X_6o(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m5851$default$toPxR2X_6o(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4703toPx0680j_4(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m5852$default$toPx0680j_4(pointerInputScope, f);
        }

        @Deprecated
        public static Rect toRect(PointerInputScope pointerInputScope, DpRect dpRect) {
            return Density.CC.$default$toRect(pointerInputScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4704toSizeXkaWNTQ(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m5853$default$toSizeXkaWNTQ(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4705toSp0xMU5do(PointerInputScope pointerInputScope, float f) {
            return FontScaling.CC.m5992$default$toSp0xMU5do(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4706toSpkPz2Gy4(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m5854$default$toSpkPz2Gy4(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4707toSpkPz2Gy4(PointerInputScope pointerInputScope, int i) {
            return Density.CC.m5855$default$toSpkPz2Gy4((Density) pointerInputScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m4695getExtendedTouchPaddingNHjbRc(PointerInputScope pointerInputScope) {
            return CC.m4681$default$getExtendedTouchPaddingNHjbRc(pointerInputScope);
        }

        @Deprecated
        public static boolean getInterceptOutOfBoundsChildEvents(PointerInputScope pointerInputScope) {
            return CC.$default$getInterceptOutOfBoundsChildEvents(pointerInputScope);
        }

        @Deprecated
        public static void setInterceptOutOfBoundsChildEvents(PointerInputScope pointerInputScope, boolean z) {
            CC.$default$setInterceptOutOfBoundsChildEvents(pointerInputScope, z);
        }
    }
}
