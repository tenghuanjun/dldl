package coil.compose;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.BitmapPainterKt;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.exifinterface.media.ExifInterface;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.SuccessResult;
import coil.size.Precision;
import coil.size.SizeResolver;
import coil.target.Target;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.google.accompanist.drawablepainter.DrawablePainter;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: AsyncImagePainter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 l2\u00020\u00012\u00020\u0002:\u0002lmB\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010Y\u001a\u0002092\u0006\u0010\u0012\u001a\u00020\u0011H\u0014J\u0012\u0010Z\u001a\u0002092\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010[\u001a\u00020@H\u0002J\u001a\u0010\\\u001a\u0004\u0018\u00010]2\u0006\u0010^\u001a\u00020\f2\u0006\u0010_\u001a\u00020\fH\u0002J\b\u0010`\u001a\u00020@H\u0016J\b\u0010a\u001a\u00020@H\u0016J\b\u0010b\u001a\u00020@H\u0016J\u0010\u0010c\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010d\u001a\u00020@2\u0006\u0010e\u001a\u00020\fH\u0002J\f\u0010f\u001a\u00020@*\u00020gH\u0014J\f\u0010h\u001a\u00020\u0001*\u00020iH\u0002J\f\u0010j\u001a\u00020\f*\u00020kH\u0002R\"\u0010\t\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0001@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R/\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0010\u001a\u0004\u0018\u00010\u00198B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0018\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010 \u001a\u00020!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R%\u0010)\u001a\u00020*X\u0080\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00068F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\u0018\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001d\u00105\u001a\u00020(8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u000209X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R(\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020@\u0018\u00010?X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR/\u0010E\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bI\u0010\u0018\u001a\u0004\bF\u0010G\"\u0004\bH\u0010\u000bR\u0010\u0010J\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00048F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010\u0018\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR+\u0010Q\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bU\u0010\u0018\u001a\u0004\bR\u0010S\"\u0004\bT\u0010\u000fR&\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0?X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010B\"\u0004\bX\u0010D\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006n"}, d2 = {"Lcoil/compose/AsyncImagePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/runtime/RememberObserver;", "request", "Lcoil/request/ImageRequest;", "imageLoader", "Lcoil/ImageLoader;", "(Lcoil/request/ImageRequest;Lcoil/ImageLoader;)V", DBHelper.COL_VALUE, "_painter", "set_painter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "Lcoil/compose/AsyncImagePainter$State;", "_state", "set_state", "(Lcoil/compose/AsyncImagePainter$State;)V", "<set-?>", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "alpha$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "colorFilter$delegate", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "getContentScale$coil_compose_base_release", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale$coil_compose_base_release", "(Landroidx/compose/ui/layout/ContentScale;)V", "drawSize", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/ui/geometry/Size;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getFilterQuality-f-v9h1I$coil_compose_base_release", "()I", "setFilterQuality-vDHp3xo$coil_compose_base_release", "(I)V", "I", "getImageLoader", "()Lcoil/ImageLoader;", "setImageLoader$coil_compose_base_release", "(Lcoil/ImageLoader;)V", "imageLoader$delegate", "intrinsicSize", "getIntrinsicSize-NH-jbRc", "()J", "isPreview", "", "isPreview$coil_compose_base_release", "()Z", "setPreview$coil_compose_base_release", "(Z)V", "onState", "Lkotlin/Function1;", "", "getOnState$coil_compose_base_release", "()Lkotlin/jvm/functions/Function1;", "setOnState$coil_compose_base_release", "(Lkotlin/jvm/functions/Function1;)V", "painter", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "painter$delegate", "rememberScope", "Lkotlinx/coroutines/CoroutineScope;", "getRequest", "()Lcoil/request/ImageRequest;", "setRequest$coil_compose_base_release", "(Lcoil/request/ImageRequest;)V", "request$delegate", "state", "getState", "()Lcoil/compose/AsyncImagePainter$State;", "setState", "state$delegate", "transform", "getTransform$coil_compose_base_release", "setTransform$coil_compose_base_release", "applyAlpha", "applyColorFilter", "clear", "maybeNewCrossfadePainter", "Lcoil/compose/CrossfadePainter;", "previous", "current", "onAbandoned", "onForgotten", "onRemembered", "updateRequest", "updateState", "input", "onDraw", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "toPainter", "Landroid/graphics/drawable/Drawable;", "toState", "Lcoil/request/ImageResult;", "Companion", "State", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AsyncImagePainter extends Painter implements RememberObserver {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Function1<State, State> DefaultTransform = new Function1<State, State>() { // from class: coil.compose.AsyncImagePainter$Companion$DefaultTransform$1
        @Override // kotlin.jvm.functions.Function1
        public final AsyncImagePainter.State invoke(AsyncImagePainter.State state) {
            return state;
        }
    };
    private Painter _painter;

    /* JADX INFO: renamed from: imageLoader$delegate, reason: from kotlin metadata */
    private final MutableState imageLoader;
    private boolean isPreview;
    private Function1<? super State, Unit> onState;
    private CoroutineScope rememberScope;

    /* JADX INFO: renamed from: request$delegate, reason: from kotlin metadata */
    private final MutableState request;
    private final MutableStateFlow<Size> drawSize = StateFlowKt.MutableStateFlow(Size.m3276boximpl(Size.INSTANCE.m3297getZeroNHjbRc()));

    /* JADX INFO: renamed from: painter$delegate, reason: from kotlin metadata */
    private final MutableState painter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: alpha$delegate, reason: from kotlin metadata */
    private final MutableState alpha = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(1.0f), null, 2, null);

    /* JADX INFO: renamed from: colorFilter$delegate, reason: from kotlin metadata */
    private final MutableState colorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private State _state = State.Empty.INSTANCE;
    private Function1<? super State, ? extends State> transform = DefaultTransform;
    private ContentScale contentScale = ContentScale.INSTANCE.getFit();
    private int filterQuality = DrawScope.INSTANCE.m4057getDefaultFilterQualityfv9h1I();

    /* JADX INFO: renamed from: state$delegate, reason: from kotlin metadata */
    private final MutableState state = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(State.Empty.INSTANCE, null, 2, null);

    public AsyncImagePainter(ImageRequest imageRequest, ImageLoader imageLoader) {
        this.request = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(imageRequest, null, 2, null);
        this.imageLoader = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(imageLoader, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Painter getPainter() {
        return (Painter) this.painter.getValue();
    }

    private final void setPainter(Painter painter) {
        this.painter.setValue(painter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final float getAlpha() {
        return ((Number) this.alpha.getValue()).floatValue();
    }

    private final void setAlpha(float f) {
        this.alpha.setValue(Float.valueOf(f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ColorFilter getColorFilter() {
        return (ColorFilter) this.colorFilter.getValue();
    }

    private final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter.setValue(colorFilter);
    }

    private final void set_state(State state) {
        this._state = state;
        setState(state);
    }

    private final void set_painter(Painter painter) {
        this._painter = painter;
        setPainter(painter);
    }

    public final Function1<State, State> getTransform$coil_compose_base_release() {
        return this.transform;
    }

    public final void setTransform$coil_compose_base_release(Function1<? super State, ? extends State> function1) {
        this.transform = function1;
    }

    public final Function1<State, Unit> getOnState$coil_compose_base_release() {
        return this.onState;
    }

    public final void setOnState$coil_compose_base_release(Function1<? super State, Unit> function1) {
        this.onState = function1;
    }

    /* JADX INFO: renamed from: getContentScale$coil_compose_base_release, reason: from getter */
    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final void setContentScale$coil_compose_base_release(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    /* JADX INFO: renamed from: getFilterQuality-f-v9h1I$coil_compose_base_release, reason: not valid java name and from getter */
    public final int getFilterQuality() {
        return this.filterQuality;
    }

    /* JADX INFO: renamed from: setFilterQuality-vDHp3xo$coil_compose_base_release, reason: not valid java name */
    public final void m6301setFilterQualityvDHp3xo$coil_compose_base_release(int i) {
        this.filterQuality = i;
    }

    /* JADX INFO: renamed from: isPreview$coil_compose_base_release, reason: from getter */
    public final boolean getIsPreview() {
        return this.isPreview;
    }

    public final void setPreview$coil_compose_base_release(boolean z) {
        this.isPreview = z;
    }

    private final void setState(State state) {
        this.state.setValue(state);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final State getState() {
        return (State) this.state.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ImageRequest getRequest() {
        return (ImageRequest) this.request.getValue();
    }

    public final void setRequest$coil_compose_base_release(ImageRequest imageRequest) {
        this.request.setValue(imageRequest);
    }

    public final ImageLoader getImageLoader() {
        return (ImageLoader) this.imageLoader.getValue();
    }

    public final void setImageLoader$coil_compose_base_release(ImageLoader imageLoader) {
        this.imageLoader.setValue(imageLoader);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        Painter painter = getPainter();
        return painter != null ? painter.getIntrinsicSize() : Size.INSTANCE.m3296getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope drawScope) {
        this.drawSize.setValue(Size.m3276boximpl(drawScope.mo3973getSizeNHjbRc()));
        Painter painter = getPainter();
        if (painter != null) {
            painter.m4132drawx_KDEd0(drawScope, drawScope.mo3973getSizeNHjbRc(), getAlpha(), getColorFilter());
        }
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float alpha) {
        setAlpha(alpha);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        setColorFilter(colorFilter);
        return true;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        if (this.rememberScope != null) {
            return;
        }
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));
        this.rememberScope = CoroutineScope;
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onRemembered();
        }
        if (!this.isPreview) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new AnonymousClass1(null), 3, null);
        } else {
            Drawable placeholder = ImageRequest.newBuilder$default(getRequest(), null, 1, null).defaults(getImageLoader().getDefaults()).build().getPlaceholder();
            updateState(new State.Loading(placeholder != null ? toPainter(placeholder) : null));
        }
    }

    /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1, reason: invalid class name */
    /* JADX INFO: compiled from: AsyncImagePainter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "coil.compose.AsyncImagePainter$onRemembered$1", f = "AsyncImagePainter.kt", i = {}, l = {KeyBoardKey.KeyboardKeyOemAuto}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AsyncImagePainter.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final AsyncImagePainter asyncImagePainter = AsyncImagePainter.this;
                this.label = 1;
                if (FlowKt.mapLatest(SnapshotStateKt.snapshotFlow(new Function0<ImageRequest>() { // from class: coil.compose.AsyncImagePainter.onRemembered.1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final ImageRequest invoke() {
                        return asyncImagePainter.getRequest();
                    }
                }), new AnonymousClass2(AsyncImagePainter.this, null)).collect(new AnonymousClass3(AsyncImagePainter.this), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lcoil/compose/AsyncImagePainter$State;", "it", "Lcoil/request/ImageRequest;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "coil.compose.AsyncImagePainter$onRemembered$1$2", f = "AsyncImagePainter.kt", i = {}, l = {KeyBoardKey.KeyboardKeyOemCopy}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<ImageRequest, Continuation<? super State>, Object> {
            Object L$0;
            int label;
            final /* synthetic */ AsyncImagePainter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(AsyncImagePainter asyncImagePainter, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = asyncImagePainter;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ImageRequest imageRequest, Continuation<? super State> continuation) {
                return ((AnonymousClass2) create(imageRequest, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                AsyncImagePainter asyncImagePainter;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AsyncImagePainter asyncImagePainter2 = this.this$0;
                    ImageLoader imageLoader = asyncImagePainter2.getImageLoader();
                    AsyncImagePainter asyncImagePainter3 = this.this$0;
                    this.L$0 = asyncImagePainter2;
                    this.label = 1;
                    Object objExecute = imageLoader.execute(asyncImagePainter3.updateRequest(asyncImagePainter3.getRequest()), this);
                    if (objExecute == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    asyncImagePainter = asyncImagePainter2;
                    obj = objExecute;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    asyncImagePainter = (AsyncImagePainter) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return asyncImagePainter.toState((ImageResult) obj);
            }
        }

        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: AsyncImagePainter.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* synthetic */ class AnonymousClass3 implements FlowCollector, FunctionAdapter {
            final /* synthetic */ AsyncImagePainter $tmp0;

            AnonymousClass3(AsyncImagePainter asyncImagePainter) {
                this.$tmp0 = asyncImagePainter;
            }

            public final boolean equals(Object obj) {
                if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                    return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                }
                return false;
            }

            @Override // kotlin.jvm.internal.FunctionAdapter
            public final Function<?> getFunctionDelegate() {
                return new AdaptedFunctionReference(2, this.$tmp0, AsyncImagePainter.class, "updateState", "updateState(Lcoil/compose/AsyncImagePainter$State;)V", 4);
            }

            public final int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            public final Object emit(State state, Continuation<? super Unit> continuation) {
                Object objInvokeSuspend$updateState = AnonymousClass1.invokeSuspend$updateState(this.$tmp0, state, continuation);
                return objInvokeSuspend$updateState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$updateState : Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((State) obj, (Continuation<? super Unit>) continuation);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ Object invokeSuspend$updateState(AsyncImagePainter asyncImagePainter, State state, Continuation continuation) {
            asyncImagePainter.updateState(state);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        clear();
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onForgotten();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        clear();
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onAbandoned();
        }
    }

    private final void clear() {
        CoroutineScope coroutineScope = this.rememberScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.rememberScope = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageRequest updateRequest(ImageRequest request) {
        ImageRequest.Builder builderTarget = ImageRequest.newBuilder$default(request, null, 1, null).target(new Target() { // from class: coil.compose.AsyncImagePainter$updateRequest$$inlined$target$default$1
            @Override // coil.target.Target
            public void onError(Drawable error) {
            }

            @Override // coil.target.Target
            public void onSuccess(Drawable result) {
            }

            @Override // coil.target.Target
            public void onStart(Drawable placeholder) {
                this.this$0.updateState(new AsyncImagePainter.State.Loading(placeholder != null ? this.this$0.toPainter(placeholder) : null));
            }
        });
        if (request.getDefined().getSizeResolver() == null) {
            builderTarget.size(new SizeResolver() { // from class: coil.compose.AsyncImagePainter$updateRequest$2$1
                @Override // coil.size.SizeResolver
                public final Object size(Continuation<? super coil.size.Size> continuation) {
                    final MutableStateFlow mutableStateFlow = this.this$0.drawSize;
                    return FlowKt.first(new Flow<coil.size.Size>() { // from class: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1

                        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: Emitters.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", DBHelper.COL_VALUE, "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        public static final class AnonymousClass2<T> implements FlowCollector {
                            final /* synthetic */ FlowCollector $this_unsafeFlow;

                            /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1, reason: invalid class name */
                            /* JADX INFO: compiled from: Emitters.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2", f = "AsyncImagePainter.kt", i = {}, l = {KeyBoardKey.KeyboardKeyOemAx}, m = "emit", n = {}, s = {})
                            public static final class AnonymousClass1 extends ContinuationImpl {
                                Object L$0;
                                int label;
                                /* synthetic */ Object result;

                                public AnonymousClass1(Continuation continuation) {
                                    super(continuation);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    this.result = obj;
                                    this.label |= Integer.MIN_VALUE;
                                    return AnonymousClass2.this.emit(null, this);
                                }
                            }

                            public AnonymousClass2(FlowCollector flowCollector) {
                                this.$this_unsafeFlow = flowCollector;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                                /*
                                    r6 = this;
                                    boolean r0 = r8 instanceof coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                    if (r0 == 0) goto L14
                                    r0 = r8
                                    coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1 r0 = (coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                    int r1 = r0.label
                                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                    r1 = r1 & r2
                                    if (r1 == 0) goto L14
                                    int r8 = r0.label
                                    int r8 = r8 - r2
                                    r0.label = r8
                                    goto L19
                                L14:
                                    coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1 r0 = new coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1
                                    r0.<init>(r8)
                                L19:
                                    java.lang.Object r8 = r0.result
                                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                    int r2 = r0.label
                                    r3 = 1
                                    if (r2 == 0) goto L32
                                    if (r2 != r3) goto L2a
                                    kotlin.ResultKt.throwOnFailure(r8)
                                    goto L4f
                                L2a:
                                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                                    r7.<init>(r8)
                                    throw r7
                                L32:
                                    kotlin.ResultKt.throwOnFailure(r8)
                                    kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                                    r2 = r0
                                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                                    androidx.compose.ui.geometry.Size r7 = (androidx.compose.ui.geometry.Size) r7
                                    long r4 = r7.getPackedValue()
                                    coil.size.Size r7 = coil.compose.AsyncImagePainterKt.m6302access$toSizeOrNulluvyYCjk(r4)
                                    if (r7 == 0) goto L4f
                                    r0.label = r3
                                    java.lang.Object r7 = r8.emit(r7, r0)
                                    if (r7 != r1) goto L4f
                                    return r1
                                L4f:
                                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                                    return r7
                                */
                                throw new UnsupportedOperationException("Method not decompiled: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                            }
                        }

                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super coil.size.Size> flowCollector, Continuation continuation2) {
                            Object objCollect = mutableStateFlow.collect(new AnonymousClass2(flowCollector), continuation2);
                            return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                        }
                    }, continuation);
                }
            });
        }
        if (request.getDefined().getScale() == null) {
            builderTarget.scale(UtilsKt.toScale(this.contentScale));
        }
        if (request.getDefined().getPrecision() != Precision.EXACT) {
            builderTarget.precision(Precision.INEXACT);
        }
        return builderTarget.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState(State input) {
        State state = this._state;
        State stateInvoke = this.transform.invoke(input);
        set_state(stateInvoke);
        CrossfadePainter crossfadePainterMaybeNewCrossfadePainter = maybeNewCrossfadePainter(state, stateInvoke);
        set_painter(crossfadePainterMaybeNewCrossfadePainter != null ? crossfadePainterMaybeNewCrossfadePainter : stateInvoke.getPainter());
        if (this.rememberScope != null && state.getPainter() != stateInvoke.getPainter()) {
            Object painter = state.getPainter();
            RememberObserver rememberObserver = painter instanceof RememberObserver ? (RememberObserver) painter : null;
            if (rememberObserver != null) {
                rememberObserver.onForgotten();
            }
            Object painter2 = stateInvoke.getPainter();
            RememberObserver rememberObserver2 = painter2 instanceof RememberObserver ? (RememberObserver) painter2 : null;
            if (rememberObserver2 != null) {
                rememberObserver2.onRemembered();
            }
        }
        Function1<? super State, Unit> function1 = this.onState;
        if (function1 != null) {
            function1.invoke(stateInvoke);
        }
    }

    private final CrossfadePainter maybeNewCrossfadePainter(State previous, State current) {
        ErrorResult result;
        if (!(current instanceof State.Success)) {
            if (current instanceof State.Error) {
                result = ((State.Error) current).getResult();
            }
            return null;
        }
        result = ((State.Success) current).getResult();
        Transition transitionCreate = result.getRequest().getTransitionFactory().create(AsyncImagePainterKt.FakeTransitionTarget, result);
        if (transitionCreate instanceof CrossfadeTransition) {
            CrossfadeTransition crossfadeTransition = (CrossfadeTransition) transitionCreate;
            return new CrossfadePainter(previous instanceof State.Loading ? previous.getPainter() : null, current.getPainter(), this.contentScale, crossfadeTransition.getDurationMillis(), ((result instanceof SuccessResult) && ((SuccessResult) result).getIsPlaceholderCached()) ? false : true, crossfadeTransition.getPreferExactIntrinsicSize());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final State toState(ImageResult imageResult) {
        if (imageResult instanceof SuccessResult) {
            SuccessResult successResult = (SuccessResult) imageResult;
            return new State.Success(toPainter(successResult.getDrawable()), successResult);
        }
        if (!(imageResult instanceof ErrorResult)) {
            throw new NoWhenBranchMatchedException();
        }
        Drawable drawable = imageResult.getDrawable();
        return new State.Error(drawable != null ? toPainter(drawable) : null, (ErrorResult) imageResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Painter toPainter(Drawable drawable) {
        return drawable instanceof BitmapDrawable ? BitmapPainterKt.m4129BitmapPainterQZhYCtY$default(AndroidImageBitmap_androidKt.asImageBitmap(((BitmapDrawable) drawable).getBitmap()), 0L, 0L, this.filterQuality, 6, null) : new DrawablePainter(drawable.mutate());
    }

    /* JADX INFO: compiled from: AsyncImagePainter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcoil/compose/AsyncImagePainter$State;", "", "()V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "Empty", "Error", "Loading", "Success", "Lcoil/compose/AsyncImagePainter$State$Empty;", "Lcoil/compose/AsyncImagePainter$State$Error;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lcoil/compose/AsyncImagePainter$State$Success;", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class State {
        public static final int $stable = 0;

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract Painter getPainter();

        private State() {
        }

        /* JADX INFO: compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Empty;", "Lcoil/compose/AsyncImagePainter$State;", "()V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Empty extends State {
            public static final int $stable = 0;
            public static final Empty INSTANCE = new Empty();

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return null;
            }

            private Empty() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Loading;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final /* data */ class Loading extends State {
            public static final int $stable = 8;
            private final Painter painter;

            public static /* synthetic */ Loading copy$default(Loading loading, Painter painter, int i, Object obj) {
                if ((i & 1) != 0) {
                    painter = loading.painter;
                }
                return loading.copy(painter);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            public final Loading copy(Painter painter) {
                return new Loading(painter);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Loading) && Intrinsics.areEqual(this.painter, ((Loading) other).painter);
            }

            public int hashCode() {
                Painter painter = this.painter;
                if (painter == null) {
                    return 0;
                }
                return painter.hashCode();
            }

            public String toString() {
                return "Loading(painter=" + this.painter + ')';
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public Loading(Painter painter) {
                super(null);
                this.painter = painter;
            }
        }

        /* JADX INFO: compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Success;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "result", "Lcoil/request/SuccessResult;", "(Landroidx/compose/ui/graphics/painter/Painter;Lcoil/request/SuccessResult;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "getResult", "()Lcoil/request/SuccessResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final /* data */ class Success extends State {
            public static final int $stable = 8;
            private final Painter painter;
            private final SuccessResult result;

            public static /* synthetic */ Success copy$default(Success success, Painter painter, SuccessResult successResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    painter = success.painter;
                }
                if ((i & 2) != 0) {
                    successResult = success.result;
                }
                return success.copy(painter, successResult);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final SuccessResult getResult() {
                return this.result;
            }

            public final Success copy(Painter painter, SuccessResult result) {
                return new Success(painter, result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Success)) {
                    return false;
                }
                Success success = (Success) other;
                return Intrinsics.areEqual(this.painter, success.painter) && Intrinsics.areEqual(this.result, success.result);
            }

            public int hashCode() {
                return (this.painter.hashCode() * 31) + this.result.hashCode();
            }

            public String toString() {
                return "Success(painter=" + this.painter + ", result=" + this.result + ')';
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public final SuccessResult getResult() {
                return this.result;
            }

            public Success(Painter painter, SuccessResult successResult) {
                super(null);
                this.painter = painter;
                this.result = successResult;
            }
        }

        /* JADX INFO: compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Error;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "result", "Lcoil/request/ErrorResult;", "(Landroidx/compose/ui/graphics/painter/Painter;Lcoil/request/ErrorResult;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "getResult", "()Lcoil/request/ErrorResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final /* data */ class Error extends State {
            public static final int $stable = 8;
            private final Painter painter;
            private final ErrorResult result;

            public static /* synthetic */ Error copy$default(Error error, Painter painter, ErrorResult errorResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    painter = error.painter;
                }
                if ((i & 2) != 0) {
                    errorResult = error.result;
                }
                return error.copy(painter, errorResult);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final ErrorResult getResult() {
                return this.result;
            }

            public final Error copy(Painter painter, ErrorResult result) {
                return new Error(painter, result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Error)) {
                    return false;
                }
                Error error = (Error) other;
                return Intrinsics.areEqual(this.painter, error.painter) && Intrinsics.areEqual(this.result, error.result);
            }

            public int hashCode() {
                Painter painter = this.painter;
                return ((painter == null ? 0 : painter.hashCode()) * 31) + this.result.hashCode();
            }

            public String toString() {
                return "Error(painter=" + this.painter + ", result=" + this.result + ')';
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public final ErrorResult getResult() {
                return this.result;
            }

            public Error(Painter painter, ErrorResult errorResult) {
                super(null);
                this.painter = painter;
                this.result = errorResult;
            }
        }
    }

    /* JADX INFO: compiled from: AsyncImagePainter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcoil/compose/AsyncImagePainter$Companion;", "", "()V", "DefaultTransform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "getDefaultTransform", "()Lkotlin/jvm/functions/Function1;", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Function1<State, State> getDefaultTransform() {
            return AsyncImagePainter.DefaultTransform;
        }
    }
}
