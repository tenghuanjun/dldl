package coil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import coil.EventListener;
import coil.ImageLoader;
import coil.decode.BitmapFactoryDecoder;
import coil.disk.DiskCache;
import coil.fetch.AssetUriFetcher;
import coil.fetch.BitmapFetcher;
import coil.fetch.ByteBufferFetcher;
import coil.fetch.ContentUriFetcher;
import coil.fetch.DrawableFetcher;
import coil.fetch.FileFetcher;
import coil.fetch.HttpUriFetcher;
import coil.fetch.ResourceUriFetcher;
import coil.intercept.EngineInterceptor;
import coil.intercept.Interceptor;
import coil.key.FileKeyer;
import coil.key.UriKeyer;
import coil.map.ByteArrayMapper;
import coil.map.FileUriMapper;
import coil.map.HttpUrlMapper;
import coil.map.ResourceIntMapper;
import coil.map.ResourceUriMapper;
import coil.map.StringMapper;
import coil.memory.MemoryCache;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.OneShotDisposable;
import coil.request.RequestService;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.transition.NoneTransition;
import coil.transition.Transition;
import coil.transition.TransitionTarget;
import coil.util.ImageLoaderOptions;
import coil.util.Logger;
import coil.util.SystemCallbacks;
import coil.util.Utils;
import com.tencent.connect.common.Constants;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.Call;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RealImageLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 [2\u00020\u0001:\u0001[Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u0010\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0016J\u0019\u0010@\u001a\u00020A2\u0006\u0010>\u001a\u00020?H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010BJ!\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020?2\u0006\u0010E\u001a\u00020FH\u0083@ø\u0001\u0000¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u00020IH\u0016J\u0018\u0010J\u001a\u00020K2\u0006\u0010>\u001a\u00020?2\u0006\u0010L\u001a\u00020MH\u0002J\"\u0010N\u001a\u00020K2\u0006\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010L\u001a\u00020MH\u0002J\"\u0010S\u001a\u00020K2\u0006\u0010O\u001a\u00020T2\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010L\u001a\u00020MH\u0002J\u0015\u0010U\u001a\u00020K2\u0006\u0010V\u001a\u00020FH\u0000¢\u0006\u0002\bWJ\b\u00108\u001a\u00020KH\u0016J1\u0010X\u001a\u00020K2\u0006\u0010O\u001a\u00020A2\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010L\u001a\u00020M2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020K0ZH\u0082\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\u0004\u0018\u00010\n8VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\b#\u0010$*\u0004\b!\u0010\"R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u001d\u0010-\u001a\u0004\u0018\u00010\b8VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\b/\u00100*\u0004\b.\u0010\"R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\\"}, d2 = {"Lcoil/RealImageLoader;", "Lcoil/ImageLoader;", "context", "Landroid/content/Context;", "defaults", "Lcoil/request/DefaultRequestOptions;", "memoryCacheLazy", "Lkotlin/Lazy;", "Lcoil/memory/MemoryCache;", "diskCacheLazy", "Lcoil/disk/DiskCache;", "callFactoryLazy", "Lokhttp3/Call$Factory;", "eventListenerFactory", "Lcoil/EventListener$Factory;", "componentRegistry", "Lcoil/ComponentRegistry;", "options", "Lcoil/util/ImageLoaderOptions;", "logger", "Lcoil/util/Logger;", "(Landroid/content/Context;Lcoil/request/DefaultRequestOptions;Lkotlin/Lazy;Lkotlin/Lazy;Lkotlin/Lazy;Lcoil/EventListener$Factory;Lcoil/ComponentRegistry;Lcoil/util/ImageLoaderOptions;Lcoil/util/Logger;)V", "getCallFactoryLazy", "()Lkotlin/Lazy;", "getComponentRegistry", "()Lcoil/ComponentRegistry;", "components", "getComponents", "getContext", "()Landroid/content/Context;", "getDefaults", "()Lcoil/request/DefaultRequestOptions;", "diskCache", "getDiskCache$delegate", "(Lcoil/RealImageLoader;)Ljava/lang/Object;", "getDiskCache", "()Lcoil/disk/DiskCache;", "getDiskCacheLazy", "getEventListenerFactory", "()Lcoil/EventListener$Factory;", "interceptors", "", "Lcoil/intercept/Interceptor;", "getLogger", "()Lcoil/util/Logger;", "memoryCache", "getMemoryCache$delegate", "getMemoryCache", "()Lcoil/memory/MemoryCache;", "getMemoryCacheLazy", "getOptions", "()Lcoil/util/ImageLoaderOptions;", "requestService", "Lcoil/request/RequestService;", Constants.PARAM_SCOPE, "Lkotlinx/coroutines/CoroutineScope;", "shutdown", "Ljava/util/concurrent/atomic/AtomicBoolean;", "systemCallbacks", "Lcoil/util/SystemCallbacks;", "enqueue", "Lcoil/request/Disposable;", "request", "Lcoil/request/ImageRequest;", "execute", "Lcoil/request/ImageResult;", "(Lcoil/request/ImageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeMain", "initialRequest", "type", "", "(Lcoil/request/ImageRequest;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newBuilder", "Lcoil/ImageLoader$Builder;", "onCancel", "", "eventListener", "Lcoil/EventListener;", "onError", "result", "Lcoil/request/ErrorResult;", "target", "Lcoil/target/Target;", "onSuccess", "Lcoil/request/SuccessResult;", "onTrimMemory", "level", "onTrimMemory$coil_base_release", "transition", "setDrawable", "Lkotlin/Function0;", "Companion", "coil-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RealImageLoader implements ImageLoader {
    private static final int REQUEST_TYPE_ENQUEUE = 0;
    private static final int REQUEST_TYPE_EXECUTE = 1;
    private static final String TAG = "RealImageLoader";
    private final Lazy<Call.Factory> callFactoryLazy;
    private final ComponentRegistry componentRegistry;
    private final ComponentRegistry components;
    private final Context context;
    private final DefaultRequestOptions defaults;
    private final Lazy<DiskCache> diskCacheLazy;
    private final EventListener.Factory eventListenerFactory;
    private final List<Interceptor> interceptors;
    private final Logger logger;
    private final Lazy<MemoryCache> memoryCacheLazy;
    private final ImageLoaderOptions options;
    private final RequestService requestService;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()).plus(new RealImageLoader$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this)));
    private final AtomicBoolean shutdown;
    private final SystemCallbacks systemCallbacks;

    /* JADX INFO: renamed from: coil.RealImageLoader$executeMain$1, reason: invalid class name */
    /* JADX INFO: compiled from: RealImageLoader.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "coil.RealImageLoader", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, l = {KeyBoardKey.KeyboardKeyBrowserBack, KeyBoardKey.KeyboardKeyMediaStop, KeyBoardKey.KeyboardKeyLaunchApp1}, m = "executeMain", n = {"this", "requestDelegate", "request", "eventListener", "this", "requestDelegate", "request", "eventListener", "placeholderBitmap", "this", "requestDelegate", "request", "eventListener"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RealImageLoader.this.executeMain(null, 0, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RealImageLoader(Context context, DefaultRequestOptions defaultRequestOptions, Lazy<? extends MemoryCache> lazy, Lazy<? extends DiskCache> lazy2, Lazy<? extends Call.Factory> lazy3, EventListener.Factory factory, ComponentRegistry componentRegistry, ImageLoaderOptions imageLoaderOptions, Logger logger) {
        this.context = context;
        this.defaults = defaultRequestOptions;
        this.memoryCacheLazy = lazy;
        this.diskCacheLazy = lazy2;
        this.callFactoryLazy = lazy3;
        this.eventListenerFactory = factory;
        this.componentRegistry = componentRegistry;
        this.options = imageLoaderOptions;
        this.logger = logger;
        SystemCallbacks systemCallbacks = new SystemCallbacks(this, context, imageLoaderOptions.getNetworkObserverEnabled());
        this.systemCallbacks = systemCallbacks;
        RealImageLoader realImageLoader = this;
        RequestService requestService = new RequestService(realImageLoader, systemCallbacks, logger);
        this.requestService = requestService;
        this.components = componentRegistry.newBuilder().add(new HttpUrlMapper(), HttpUrl.class).add(new StringMapper(), String.class).add(new FileUriMapper(), Uri.class).add(new ResourceUriMapper(), Uri.class).add(new ResourceIntMapper(), Integer.class).add(new ByteArrayMapper(), byte[].class).add(new UriKeyer(), Uri.class).add(new FileKeyer(imageLoaderOptions.getAddLastModifiedToFileCacheKey()), File.class).add(new HttpUriFetcher.Factory(lazy3, lazy2, imageLoaderOptions.getRespectCacheHeaders()), Uri.class).add(new FileFetcher.Factory(), File.class).add(new AssetUriFetcher.Factory(), Uri.class).add(new ContentUriFetcher.Factory(), Uri.class).add(new ResourceUriFetcher.Factory(), Uri.class).add(new DrawableFetcher.Factory(), Drawable.class).add(new BitmapFetcher.Factory(), Bitmap.class).add(new ByteBufferFetcher.Factory(), ByteBuffer.class).add(new BitmapFactoryDecoder.Factory(imageLoaderOptions.getBitmapFactoryMaxParallelism(), imageLoaderOptions.getBitmapFactoryExifOrientationPolicy())).build();
        this.interceptors = CollectionsKt.plus((Collection<? extends EngineInterceptor>) getComponents().getInterceptors(), new EngineInterceptor(realImageLoader, requestService, logger));
        this.shutdown = new AtomicBoolean(false);
        systemCallbacks.register();
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // coil.ImageLoader
    public DefaultRequestOptions getDefaults() {
        return this.defaults;
    }

    public final Lazy<MemoryCache> getMemoryCacheLazy() {
        return this.memoryCacheLazy;
    }

    public final Lazy<DiskCache> getDiskCacheLazy() {
        return this.diskCacheLazy;
    }

    public final Lazy<Call.Factory> getCallFactoryLazy() {
        return this.callFactoryLazy;
    }

    public final EventListener.Factory getEventListenerFactory() {
        return this.eventListenerFactory;
    }

    public final ComponentRegistry getComponentRegistry() {
        return this.componentRegistry;
    }

    public final ImageLoaderOptions getOptions() {
        return this.options;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    @Override // coil.ImageLoader
    public MemoryCache getMemoryCache() {
        return this.memoryCacheLazy.getValue();
    }

    @Override // coil.ImageLoader
    public DiskCache getDiskCache() {
        return this.diskCacheLazy.getValue();
    }

    @Override // coil.ImageLoader
    public ComponentRegistry getComponents() {
        return this.components;
    }

    @Override // coil.ImageLoader
    public Disposable enqueue(ImageRequest request) {
        Deferred<? extends ImageResult> deferredAsync$default = BuildersKt__Builders_commonKt.async$default(this.scope, null, null, new RealImageLoader$enqueue$job$1(this, request, null), 3, null);
        if (request.getTarget() instanceof ViewTarget) {
            return Utils.getRequestManager(((ViewTarget) request.getTarget()).getView()).getDisposable(deferredAsync$default);
        }
        return new OneShotDisposable(deferredAsync$default);
    }

    /* JADX INFO: renamed from: coil.RealImageLoader$execute$2, reason: invalid class name */
    /* JADX INFO: compiled from: RealImageLoader.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcoil/request/ImageResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "coil.RealImageLoader$execute$2", f = "RealImageLoader.kt", i = {}, l = {KeyBoardKey.KeyboardKeyNavigationLeft}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ImageResult>, Object> {
        final /* synthetic */ ImageRequest $request;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ RealImageLoader this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ImageRequest imageRequest, RealImageLoader realImageLoader, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$request = imageRequest;
            this.this$0 = realImageLoader;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$request, this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ImageResult> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Deferred<? extends ImageResult> deferredAsync$default = BuildersKt__Builders_commonKt.async$default((CoroutineScope) this.L$0, Dispatchers.getMain().getImmediate(), null, new RealImageLoader$execute$2$job$1(this.this$0, this.$request, null), 2, null);
                if (this.$request.getTarget() instanceof ViewTarget) {
                    Utils.getRequestManager(((ViewTarget) this.$request.getTarget()).getView()).getDisposable(deferredAsync$default);
                }
                this.label = 1;
                obj = deferredAsync$default.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @Override // coil.ImageLoader
    public Object execute(ImageRequest imageRequest, Continuation<? super ImageResult> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(imageRequest, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0109 A[Catch: all -> 0x01a9, TryCatch #4 {all -> 0x01a9, blocks: (B:42:0x00ed, B:44:0x00f3, B:46:0x00f9, B:49:0x0101, B:52:0x0109, B:54:0x011d, B:56:0x0123, B:57:0x0126, B:59:0x012f, B:60:0x0132, B:53:0x0119), top: B:98:0x00ed }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0119 A[Catch: all -> 0x01a9, TryCatch #4 {all -> 0x01a9, blocks: (B:42:0x00ed, B:44:0x00f3, B:46:0x00f9, B:49:0x0101, B:52:0x0109, B:54:0x011d, B:56:0x0123, B:57:0x0126, B:59:0x012f, B:60:0x0132, B:53:0x0119), top: B:98:0x00ed }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0123 A[Catch: all -> 0x01a9, TryCatch #4 {all -> 0x01a9, blocks: (B:42:0x00ed, B:44:0x00f3, B:46:0x00f9, B:49:0x0101, B:52:0x0109, B:54:0x011d, B:56:0x0123, B:57:0x0126, B:59:0x012f, B:60:0x0132, B:53:0x0119), top: B:98:0x00ed }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012f A[Catch: all -> 0x01a9, TryCatch #4 {all -> 0x01a9, blocks: (B:42:0x00ed, B:44:0x00f3, B:46:0x00f9, B:49:0x0101, B:52:0x0109, B:54:0x011d, B:56:0x0123, B:57:0x0126, B:59:0x012f, B:60:0x0132, B:53:0x0119), top: B:98:0x00ed }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0181 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x018c A[Catch: all -> 0x004c, TryCatch #5 {all -> 0x004c, blocks: (B:14:0x0047, B:68:0x0186, B:70:0x018c, B:71:0x0197, B:73:0x019b), top: B:100:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0197 A[Catch: all -> 0x004c, TryCatch #5 {all -> 0x004c, blocks: (B:14:0x0047, B:68:0x0186, B:70:0x018c, B:71:0x0197, B:73:0x019b), top: B:100:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01bd A[Catch: all -> 0x01d2, TRY_LEAVE, TryCatch #2 {all -> 0x01d2, blocks: (B:82:0x01b9, B:84:0x01bd, B:87:0x01ce, B:88:0x01d1), top: B:96:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01ce A[Catch: all -> 0x01d2, TRY_ENTER, TryCatch #2 {all -> 0x01d2, blocks: (B:82:0x01b9, B:84:0x01bd, B:87:0x01ce, B:88:0x01d1), top: B:96:0x01b9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeMain(coil.request.ImageRequest r21, int r22, kotlin.coroutines.Continuation<? super coil.request.ImageResult> r23) {
        /*
            Method dump skipped, instruction units count: 471
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.executeMain(coil.request.ImageRequest, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onTrimMemory$coil_base_release(int level) {
        MemoryCache value;
        Lazy<MemoryCache> lazy = this.memoryCacheLazy;
        if (lazy == null || (value = lazy.getValue()) == null) {
            return;
        }
        value.trimMemory(level);
    }

    @Override // coil.ImageLoader
    public void shutdown() {
        if (this.shutdown.getAndSet(true)) {
            return;
        }
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        this.systemCallbacks.shutdown();
        MemoryCache memoryCache = getMemoryCache();
        if (memoryCache != null) {
            memoryCache.clear();
        }
    }

    @Override // coil.ImageLoader
    public ImageLoader.Builder newBuilder() {
        return new ImageLoader.Builder(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onSuccess(coil.request.SuccessResult r7, coil.target.Target r8, coil.EventListener r9) {
        /*
            r6 = this;
            coil.request.ImageRequest r0 = r7.getRequest()
            coil.decode.DataSource r1 = r7.getDataSource()
            coil.util.Logger r2 = r6.logger
            if (r2 == 0) goto L41
            int r3 = r2.getLevel()
            r4 = 4
            if (r3 > r4) goto L41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = coil.util.Utils.getEmoji(r1)
            r3.append(r5)
            java.lang.String r5 = " Successful ("
            r3.append(r5)
            java.lang.String r1 = r1.name()
            r3.append(r1)
            java.lang.String r1 = ") - "
            r3.append(r1)
            java.lang.Object r1 = r0.getData()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r3 = 0
            java.lang.String r5 = "RealImageLoader"
            r2.log(r5, r4, r1, r3)
        L41:
            boolean r1 = r8 instanceof coil.transition.TransitionTarget
            if (r1 != 0) goto L48
            if (r8 == 0) goto L77
            goto L5e
        L48:
            r1 = r7
            coil.request.ImageResult r1 = (coil.request.ImageResult) r1
            coil.request.ImageRequest r2 = r1.getRequest()
            coil.transition.Transition$Factory r2 = r2.getTransitionFactory()
            r3 = r8
            coil.transition.TransitionTarget r3 = (coil.transition.TransitionTarget) r3
            coil.transition.Transition r2 = r2.create(r3, r1)
            boolean r3 = r2 instanceof coil.transition.NoneTransition
            if (r3 == 0) goto L66
        L5e:
            android.graphics.drawable.Drawable r1 = r7.getDrawable()
            r8.onSuccess(r1)
            goto L77
        L66:
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionStart(r8, r2)
            r2.transition()
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionEnd(r8, r2)
        L77:
            r9.onSuccess(r0, r7)
            coil.request.ImageRequest$Listener r8 = r0.getListener()
            if (r8 == 0) goto L83
            r8.onSuccess(r0, r7)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onSuccess(coil.request.SuccessResult, coil.target.Target, coil.EventListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onError(coil.request.ErrorResult r7, coil.target.Target r8, coil.EventListener r9) {
        /*
            r6 = this;
            coil.request.ImageRequest r0 = r7.getRequest()
            coil.util.Logger r1 = r6.logger
            if (r1 == 0) goto L33
            int r2 = r1.getLevel()
            r3 = 4
            if (r2 > r3) goto L33
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "🚨 Failed - "
            r2.<init>(r4)
            java.lang.Object r4 = r0.getData()
            r2.append(r4)
            java.lang.String r4 = " - "
            r2.append(r4)
            java.lang.Throwable r4 = r7.getThrowable()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r4 = 0
            java.lang.String r5 = "RealImageLoader"
            r1.log(r5, r3, r2, r4)
        L33:
            boolean r1 = r8 instanceof coil.transition.TransitionTarget
            if (r1 != 0) goto L3a
            if (r8 == 0) goto L69
            goto L50
        L3a:
            r1 = r7
            coil.request.ImageResult r1 = (coil.request.ImageResult) r1
            coil.request.ImageRequest r2 = r1.getRequest()
            coil.transition.Transition$Factory r2 = r2.getTransitionFactory()
            r3 = r8
            coil.transition.TransitionTarget r3 = (coil.transition.TransitionTarget) r3
            coil.transition.Transition r2 = r2.create(r3, r1)
            boolean r3 = r2 instanceof coil.transition.NoneTransition
            if (r3 == 0) goto L58
        L50:
            android.graphics.drawable.Drawable r1 = r7.getDrawable()
            r8.onError(r1)
            goto L69
        L58:
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionStart(r8, r2)
            r2.transition()
            coil.request.ImageRequest r8 = r1.getRequest()
            r9.transitionEnd(r8, r2)
        L69:
            r9.onError(r0, r7)
            coil.request.ImageRequest$Listener r8 = r0.getListener()
            if (r8 == 0) goto L75
            r8.onError(r0, r7)
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onError(coil.request.ErrorResult, coil.target.Target, coil.EventListener):void");
    }

    private final void onCancel(ImageRequest request, EventListener eventListener) {
        Logger logger = this.logger;
        if (logger != null && logger.getLevel() <= 4) {
            logger.log(TAG, 4, "🏗  Cancelled - " + request.getData(), null);
        }
        eventListener.onCancel(request);
        ImageRequest.Listener listener = request.getListener();
        if (listener != null) {
            listener.onCancel(request);
        }
    }

    private final void transition(ImageResult result, Target target, EventListener eventListener, Function0<Unit> setDrawable) {
        if (!(target instanceof TransitionTarget)) {
            setDrawable.invoke();
            return;
        }
        Transition transitionCreate = result.getRequest().getTransitionFactory().create((TransitionTarget) target, result);
        if (transitionCreate instanceof NoneTransition) {
            setDrawable.invoke();
            return;
        }
        eventListener.transitionStart(result.getRequest(), transitionCreate);
        transitionCreate.transition();
        eventListener.transitionEnd(result.getRequest(), transitionCreate);
    }
}
