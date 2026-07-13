package coil.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSizeKt;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.request.NullRequestDataException;
import coil.size.Scale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aX\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006H\u0001\u001a\u0017\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0001¢\u0006\u0002\u0010\u0013\u001a2\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0016H\u0001\u001a!\u0010\u0019\u001a\u00020\u001a*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001aH\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a!\u0010\u001e\u001a\u00020\u001a*\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u001aH\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u001d\u001a\u001b\u0010!\u001a\u00020\u001a*\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0#H\u0080\b\u001a\u0019\u0010$\u001a\u00020%*\u00020&H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\f\u0010)\u001a\u00020**\u00020+H\u0001\"\u0019\u0010\u0000\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "onStateOf", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "requestOf", "Lcoil/request/ImageRequest;", "model", "", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Lcoil/request/ImageRequest;", "transformOf", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "constrainHeight", "", "height", "constrainHeight-K40F9xA", "(JF)F", "constrainWidth", "width", "constrainWidth-K40F9xA", "takeOrElse", "block", "Lkotlin/Function0;", "toIntSize", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/geometry/Size;", "toIntSize-uvyYCjk", "(J)J", "toScale", "Lcoil/size/Scale;", "Landroidx/compose/ui/layout/ContentScale;", "coil-compose-base_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    private static final long ZeroConstraints = Constraints.INSTANCE.m5836fixedJhjzzOo(0, 0);

    public static final ImageRequest requestOf(Object obj, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1151830858, i, -1, "coil.compose.requestOf (Utils.kt:21)");
        }
        if (obj instanceof ImageRequest) {
            return (ImageRequest) obj;
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composer);
        return new ImageRequest.Builder((Context) objConsume).data(obj).build();
    }

    public static final Function1<AsyncImagePainter.State, AsyncImagePainter.State> transformOf(final Painter painter, final Painter painter2, final Painter painter3) {
        if (painter != null || painter2 != null || painter3 != null) {
            return new Function1<AsyncImagePainter.State, AsyncImagePainter.State>() { // from class: coil.compose.UtilsKt.transformOf.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final AsyncImagePainter.State invoke(AsyncImagePainter.State state) {
                    if (state instanceof AsyncImagePainter.State.Loading) {
                        Painter painter4 = painter;
                        AsyncImagePainter.State.Loading loadingCopy = (AsyncImagePainter.State.Loading) state;
                        if (painter4 != null) {
                            loadingCopy = loadingCopy.copy(painter4);
                        }
                        return loadingCopy;
                    }
                    if (!(state instanceof AsyncImagePainter.State.Error)) {
                        return state;
                    }
                    AsyncImagePainter.State.Error errorCopy$default = (AsyncImagePainter.State.Error) state;
                    if (errorCopy$default.getResult().getThrowable() instanceof NullRequestDataException) {
                        Painter painter5 = painter3;
                        if (painter5 != null) {
                            errorCopy$default = AsyncImagePainter.State.Error.copy$default(errorCopy$default, painter5, null, 2, null);
                        }
                    } else {
                        Painter painter6 = painter2;
                        if (painter6 != null) {
                            errorCopy$default = AsyncImagePainter.State.Error.copy$default(errorCopy$default, painter6, null, 2, null);
                        }
                    }
                    return errorCopy$default;
                }
            };
        }
        return AsyncImagePainter.INSTANCE.getDefaultTransform();
    }

    public static final Function1<AsyncImagePainter.State, Unit> onStateOf(final Function1<? super AsyncImagePainter.State.Loading, Unit> function1, final Function1<? super AsyncImagePainter.State.Success, Unit> function12, final Function1<? super AsyncImagePainter.State.Error, Unit> function13) {
        if (function1 == null && function12 == null && function13 == null) {
            return null;
        }
        return new Function1<AsyncImagePainter.State, Unit>() { // from class: coil.compose.UtilsKt.onStateOf.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AsyncImagePainter.State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AsyncImagePainter.State state) {
                if (state instanceof AsyncImagePainter.State.Loading) {
                    Function1<AsyncImagePainter.State.Loading, Unit> function14 = function1;
                    if (function14 != null) {
                        function14.invoke(state);
                        return;
                    }
                    return;
                }
                if (state instanceof AsyncImagePainter.State.Success) {
                    Function1<AsyncImagePainter.State.Success, Unit> function15 = function12;
                    if (function15 != null) {
                        function15.invoke(state);
                        return;
                    }
                    return;
                }
                if (!(state instanceof AsyncImagePainter.State.Error)) {
                    boolean z = state instanceof AsyncImagePainter.State.Empty;
                    return;
                }
                Function1<AsyncImagePainter.State.Error, Unit> function16 = function13;
                if (function16 != null) {
                    function16.invoke(state);
                }
            }
        };
    }

    public static final Scale toScale(ContentScale contentScale) {
        return Intrinsics.areEqual(contentScale, ContentScale.INSTANCE.getFit()) ? true : Intrinsics.areEqual(contentScale, ContentScale.INSTANCE.getInside()) ? Scale.FIT : Scale.FILL;
    }

    /* JADX INFO: renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final float m6332constrainWidthK40F9xA(long j, float f) {
        return RangesKt.coerceIn(f, Constraints.m5830getMinWidthimpl(j), Constraints.m5828getMaxWidthimpl(j));
    }

    /* JADX INFO: renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final float m6331constrainHeightK40F9xA(long j, float f) {
        return RangesKt.coerceIn(f, Constraints.m5829getMinHeightimpl(j), Constraints.m5827getMaxHeightimpl(j));
    }

    public static final float takeOrElse(float f, Function0<Float> function0) {
        return (Float.isInfinite(f) || Float.isNaN(f)) ? function0.invoke().floatValue() : f;
    }

    /* JADX INFO: renamed from: toIntSize-uvyYCjk, reason: not valid java name */
    public static final long m6333toIntSizeuvyYCjk(long j) {
        return IntSizeKt.IntSize(MathKt.roundToInt(Size.m3288getWidthimpl(j)), MathKt.roundToInt(Size.m3285getHeightimpl(j)));
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }
}
