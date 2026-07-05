package com.taptap.sdk.db.repository;

import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.request.TrackEventRequest;
import com.taptap.sdk.db.storage.PersistentQueue;
import com.taptap.sdk.db.utils.JSONObjectUtils;
import com.taptap.sdk.db.utils.MapUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventRepository.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\u0002J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\u000fJ\u0019\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/taptap/sdk/db/repository/EventRepository;", "", "eventQueue", "Lcom/taptap/sdk/db/storage/PersistentQueue;", "Lcom/taptap/sdk/db/data/model/Event;", "eventApi", "Lcom/taptap/sdk/db/repository/EventApi;", "(Lcom/taptap/sdk/db/storage/PersistentQueue;Lcom/taptap/sdk/db/repository/EventApi;)V", "buildTrackEventRequest", "Lcom/taptap/sdk/db/data/request/TrackEventRequest;", "events", "", "flushEvents", "", "maxSendEventCount", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEventQueueSize", "submitEvent", "event", "(Lcom/taptap/sdk/db/data/model/Event;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EventRepository {
    private static final String TAG = "EventRepository";
    private final EventApi eventApi;
    private final PersistentQueue<Event> eventQueue;

    /* JADX INFO: renamed from: com.taptap.sdk.db.repository.EventRepository$flushEvents$1, reason: invalid class name */
    /* JADX INFO: compiled from: EventRepository.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.repository.EventRepository", f = "EventRepository.kt", i = {0, 0, 1, 1, 2}, l = {45, 47, 53}, m = "flushEvents", n = {"this", "pendingEvents", "this", "pendingEvents", "error"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EventRepository.this.flushEvents(0, this);
        }
    }

    public EventRepository(PersistentQueue<Event> eventQueue, EventApi eventApi) {
        Intrinsics.checkNotNullParameter(eventQueue, "eventQueue");
        Intrinsics.checkNotNullParameter(eventApi, "eventApi");
        this.eventQueue = eventQueue;
        this.eventApi = eventApi;
    }

    public final Object submitEvent(Event event, Continuation<? super Unit> continuation) {
        Object objEnqueue = this.eventQueue.enqueue(event, continuation);
        return objEnqueue == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEnqueue : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r10v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.taptap.sdk.db.repository.EventRepository] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.taptap.sdk.db.repository.EventRepository, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.taptap.sdk.db.repository.EventRepository] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.taptap.sdk.db.repository.EventRepository, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object flushEvents(int r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.repository.EventRepository.flushEvents(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final int getEventQueueSize() {
        return this.eventQueue.getQueueSize();
    }

    private final TrackEventRequest buildTrackEventRequest(List<Event> events) {
        List<Event> list = events;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(MapUtils.INSTANCE.toJsonElement((Map<String, ? extends Object>) JSONObjectUtils.INSTANCE.toMap(((Event) it.next()).getProperties())));
        }
        return new TrackEventRequest(CollectionsKt.toList(arrayList));
    }
}
