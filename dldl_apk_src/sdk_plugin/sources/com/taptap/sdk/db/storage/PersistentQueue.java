package com.taptap.sdk.db.storage;

import android.util.Log;
import com.sqwan.liveshow.huya.SqR;
import com.taptap.sdk.db.processor.EventProcessor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PersistentQueue.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u0000 %*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001%B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001b\u0010\u0011\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0012\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00172\u0006\u0010\u001a\u001a\u00020\u0015J\u001f\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020! \"*\n\u0012\u0004\u0012\u00020!\u0018\u00010 0 H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\f\u0010#\u001a\u00020$*\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lcom/taptap/sdk/db/storage/PersistentQueue;", "T", "", "config", "Lcom/taptap/sdk/db/processor/EventProcessor$Config;", "(Lcom/taptap/sdk/db/processor/EventProcessor$Config;)V", "mappedBuffer", "Ljava/nio/MappedByteBuffer;", "getMappedBuffer", "()Ljava/nio/MappedByteBuffer;", "mappedBuffer$delegate", "Lkotlin/Lazy;", "queue", "Ljava/util/concurrent/ConcurrentLinkedDeque;", "createMappedBufferFromFile", "file", "Ljava/io/File;", "enqueue", "item", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQueueSize", "", "load", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peek", "n", "removeAll", "", "items", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", SqR.string.save, "", "", "kotlin.jvm.PlatformType", "readContentFromMappedBuffer", "", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PersistentQueue<T> {
    private static final String TAG = "PersistentQueue";
    private final EventProcessor.Config config;

    /* JADX INFO: renamed from: mappedBuffer$delegate, reason: from kotlin metadata */
    private final Lazy mappedBuffer;
    private final ConcurrentLinkedDeque<T> queue;

    /* JADX INFO: renamed from: com.taptap.sdk.db.storage.PersistentQueue$enqueue$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PersistentQueue.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.storage.PersistentQueue", f = "PersistentQueue.kt", i = {0}, l = {48}, m = "enqueue", n = {"removed"}, s = {"L$0"})
    static final class C00951 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PersistentQueue<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00951(PersistentQueue<T> persistentQueue, Continuation<? super C00951> continuation) {
            super(continuation);
            this.this$0 = persistentQueue;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.enqueue(null, this);
        }
    }

    public PersistentQueue(EventProcessor.Config config) throws InterruptedException {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.queue = new ConcurrentLinkedDeque<>();
        this.mappedBuffer = LazyKt.lazy(new Function0<MappedByteBuffer>(this) { // from class: com.taptap.sdk.db.storage.PersistentQueue$mappedBuffer$2
            final /* synthetic */ PersistentQueue<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final MappedByteBuffer invoke() {
                return this.this$0.createMappedBufferFromFile(new File(((PersistentQueue) this.this$0).config.getContext().getFilesDir(), ((PersistentQueue) this.this$0).config.getFileName()));
            }
        });
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MappedByteBuffer getMappedBuffer() {
        return (MappedByteBuffer) this.mappedBuffer.getValue();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.storage.PersistentQueue$1, reason: invalid class name */
    /* JADX INFO: compiled from: PersistentQueue.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.storage.PersistentQueue$1", f = "PersistentQueue.kt", i = {}, l = {33}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;
        final /* synthetic */ PersistentQueue<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PersistentQueue<T> persistentQueue, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = persistentQueue;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = this.this$0.load(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List list = (List) obj;
            CollectionsKt.addAll(((PersistentQueue) this.this$0).queue, list);
            return Boxing.boxInt(Log.d(PersistentQueue.TAG, "load pending events: " + list));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enqueue(T r6, kotlin.coroutines.Continuation<? super T> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.taptap.sdk.db.storage.PersistentQueue.C00951
            if (r0 == 0) goto L14
            r0 = r7
            com.taptap.sdk.db.storage.PersistentQueue$enqueue$1 r0 = (com.taptap.sdk.db.storage.PersistentQueue.C00951) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.taptap.sdk.db.storage.PersistentQueue$enqueue$1 r0 = new com.taptap.sdk.db.storage.PersistentQueue$enqueue$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            java.lang.Object r6 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5b
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = 0
            int r2 = r5.getQueueSize()
            com.taptap.sdk.db.processor.EventProcessor$Config r4 = r5.config
            int r4 = r4.getEventQueueCapacity()
            if (r2 < r4) goto L4a
            java.util.concurrent.ConcurrentLinkedDeque<T> r7 = r5.queue
            java.lang.Object r7 = r7.poll()
        L4a:
            java.util.concurrent.ConcurrentLinkedDeque<T> r2 = r5.queue
            r2.add(r6)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r6 = r5.save(r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            r6 = r7
        L5b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.storage.PersistentQueue.enqueue(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<T> peek(int n) {
        if (!(n >= 0)) {
            throw new IllegalArgumentException("n must be > 0".toString());
        }
        if (n == 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.queue.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "queue.iterator()");
        for (int i = 0; it.hasNext() && i < n; i++) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final int getQueueSize() {
        return this.queue.size();
    }

    public final Object removeAll(List<? extends T> list, Continuation<? super Unit> continuation) {
        this.queue.removeAll(list);
        Object objSave = save(continuation);
        return objSave == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSave : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.storage.PersistentQueue$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: PersistentQueue.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.storage.PersistentQueue$load$2", f = "PersistentQueue.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends T>>, Object> {
        int label;
        final /* synthetic */ PersistentQueue<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PersistentQueue<T> persistentQueue, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = persistentQueue;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends T>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(this.this$0.readContentFromMappedBuffer(this.this$0.getMappedBuffer())));
                try {
                    Object object = objectInputStream.readObject();
                    List listEmptyList = object instanceof List ? (List) object : null;
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    CloseableKt.closeFinally(objectInputStream, null);
                    return listEmptyList;
                } finally {
                }
            } catch (Exception e) {
                Log.d(PersistentQueue.TAG, "load error: " + e.getMessage(), e);
                return CollectionsKt.emptyList();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object load(Continuation<? super List<? extends T>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(this, null), continuation);
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.storage.PersistentQueue$save$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PersistentQueue.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "", "kotlin.jvm.PlatformType", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.storage.PersistentQueue$save$2", f = "PersistentQueue.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00962 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Comparable<?>>, Object> {
        int label;
        final /* synthetic */ PersistentQueue<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00962(PersistentQueue<T> persistentQueue, Continuation<? super C00962> continuation) {
            super(2, continuation);
            this.this$0 = persistentQueue;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00962(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Comparable<?>> continuation) {
            return ((C00962) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Integer numBoxInt;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                ObjectOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PersistentQueue<T> persistentQueue = this.this$0;
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                    byteArrayOutputStream = new ObjectOutputStream(byteArrayOutputStream2);
                    try {
                        byteArrayOutputStream.writeObject(CollectionsKt.toList(((PersistentQueue) persistentQueue).queue));
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        if (byteArray.length < persistentQueue.getMappedBuffer().capacity()) {
                            persistentQueue.getMappedBuffer().clear();
                            numBoxInt = persistentQueue.getMappedBuffer().put(byteArray);
                        } else {
                            numBoxInt = Boxing.boxInt(Log.i(PersistentQueue.TAG, "not enough space for save, capacity = " + persistentQueue.getMappedBuffer().capacity() + ", byteArray = " + byteArray.length));
                        }
                        CloseableKt.closeFinally(byteArrayOutputStream, null);
                        CloseableKt.closeFinally(byteArrayOutputStream, null);
                        return numBoxInt;
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e) {
                return Boxing.boxInt(Log.e(PersistentQueue.TAG, "保存数据失败", e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object save(Continuation<? super Comparable<?>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C00962(this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MappedByteBuffer createMappedBufferFromFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0L, this.config.getMaxSize());
            CloseableKt.closeFinally(randomAccessFile, null);
            Intrinsics.checkNotNullExpressionValue(map, "RandomAccessFile(file, \"…          )\n            }");
            return map;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] readContentFromMappedBuffer(MappedByteBuffer mappedByteBuffer) {
        byte[] bArr = new byte[mappedByteBuffer.remaining()];
        mappedByteBuffer.get(bArr);
        return bArr;
    }
}
