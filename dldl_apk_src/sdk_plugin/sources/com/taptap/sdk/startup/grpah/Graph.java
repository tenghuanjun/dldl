package com.taptap.sdk.startup.grpah;

import com.huya.statistics.core.StatisticsContent;
import com.taptap.sdk.startup.grpah.Node;
import io.reactivex.internal.operators.observable.ObservableTakeLast;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: Graph.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003:\u0001#B!\b\u0002\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006¢\u0006\u0002\u0010\bJ$\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0016`\u0017H\u0002J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00160\u0006H\u0002J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u001c\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u001dJ\u0015\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u001fH\u0096\u0002J \u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\"R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012¨\u0006$"}, d2 = {"Lcom/taptap/sdk/startup/grpah/Graph;", "T", "Lcom/taptap/sdk/startup/grpah/Node;", "", "Lcom/taptap/sdk/startup/grpah/Edge;", "edges", "", "", "(Ljava/util/Map;)V", "entries", "", "getEntries", "()Ljava/util/List;", "entries$delegate", "Lkotlin/Lazy;", "nodes", "", "getNodes", "()Ljava/util/Collection;", "nodes$delegate", "calculateInDegreeMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "calculateZeroInDegreeQueue", "Ljava/util/ArrayDeque;", "inDegreeMap", "get", StatisticsContent.FROM, "(Lcom/taptap/sdk/startup/grpah/Node;)Ljava/util/Set;", "iterator", "", "sort", "action", "Lkotlin/Function1;", "Builder", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Graph<T extends Node> implements Iterable<Edge<T>>, KMappedMarker {
    private final Map<T, Set<T>> edges;

    /* JADX INFO: renamed from: entries$delegate, reason: from kotlin metadata */
    private final Lazy entries;

    /* JADX INFO: renamed from: nodes$delegate, reason: from kotlin metadata */
    private final Lazy nodes;

    public /* synthetic */ Graph(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Graph(Map<T, ? extends Set<? extends T>> map) {
        this.edges = map;
        this.entries = LazyKt.lazy(new Function0<List<Edge<T>>>(this) { // from class: com.taptap.sdk.startup.grpah.Graph$entries$2
            final /* synthetic */ Graph<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<Edge<T>> invoke() {
                Set<Map.Entry> setEntrySet = ((Graph) this.this$0).edges.entrySet();
                ArrayList arrayList = new ArrayList();
                for (Map.Entry entry : setEntrySet) {
                    Iterator<T> it = ((Iterable) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        arrayList.add(new Edge((Node) entry.getKey(), (Node) it.next()));
                    }
                }
                return Collections.unmodifiableList(arrayList);
            }
        });
        this.nodes = LazyKt.lazy(new Function0<Set<T>>(this) { // from class: com.taptap.sdk.startup.grpah.Graph$nodes$2
            final /* synthetic */ Graph<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<T> invoke() {
                Graph<T> graph = this.this$0;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (T t : graph) {
                    linkedHashSet.add(t.getFrom());
                    linkedHashSet.add(t.getTo());
                }
                return Collections.unmodifiableSet(linkedHashSet);
            }
        });
    }

    private final List<Edge<T>> getEntries() {
        Object value = this.entries.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-entries>(...)");
        return (List) value;
    }

    private final Collection<T> getNodes() {
        Object value = this.nodes.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-nodes>(...)");
        return (Collection) value;
    }

    @Override // java.lang.Iterable
    public Iterator<Edge<T>> iterator() {
        return getEntries().iterator();
    }

    public final Set<T> get(T from) {
        Intrinsics.checkNotNullParameter(from, "from");
        Set<T> set = this.edges.get(from);
        Set<T> setUnmodifiableSet = set != null ? Collections.unmodifiableSet(set) : null;
        return setUnmodifiableSet == null ? SetsKt.emptySet() : setUnmodifiableSet;
    }

    public final Set<T> sort(Function1<? super T, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        HashMap<T, Integer> mapCalculateInDegreeMap = calculateInDegreeMap();
        ArrayDeque arrayDequeCalculateZeroInDegreeQueue = calculateZeroInDegreeQueue(mapCalculateInDegreeMap);
        while (!arrayDequeCalculateZeroInDegreeQueue.isEmpty()) {
            Object objPoll = arrayDequeCalculateZeroInDegreeQueue.poll();
            if (objPoll == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Node node = (Node) objPoll;
            linkedHashSet.add(action.invoke(node));
            List<Edge<T>> entries = getEntries();
            ArrayList arrayList = new ArrayList();
            for (Object obj : entries) {
                if (Intrinsics.areEqual(((Edge) obj).getTo(), node)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList<Node> arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(((Edge) it.next()).getFrom());
            }
            for (Node node2 : arrayList3) {
                Object obj2 = mapCalculateInDegreeMap.get(node2);
                if (obj2 == null) {
                    obj2 = 0;
                }
                int iIntValue = ((Number) obj2).intValue() - 1;
                if (iIntValue == 0) {
                    arrayDequeCalculateZeroInDegreeQueue.offer(node2);
                }
                mapCalculateInDegreeMap.put(node2, Integer.valueOf(iIntValue));
            }
        }
        return linkedHashSet;
    }

    private final HashMap<T, Integer> calculateInDegreeMap() {
        Collection<T> nodes = getNodes();
        HashMap<T, Integer> map = new HashMap<>();
        for (T t : nodes) {
            Pair pair = TuplesKt.to(t, Integer.valueOf(get(t).size()));
            map.put(pair.getFirst(), pair.getSecond());
        }
        return map;
    }

    private final ArrayDeque<T> calculateZeroInDegreeQueue(Map<T, Integer> inDegreeMap) {
        List<Pair> list = MapsKt.toList(inDegreeMap);
        ObservableTakeLast.TakeLastObserver takeLastObserver = (ArrayDeque<T>) new ArrayDeque();
        for (Pair pair : list) {
            Node node = (Node) pair.component1();
            if (((Number) pair.component2()).intValue() == 0) {
                takeLastObserver.push(node);
            }
        }
        return takeLastObserver;
    }

    /* JADX INFO: compiled from: Graph.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00018\u00018\u00010\tJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00028\u0001¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/startup/grpah/Graph$Builder;", "T", "Lcom/taptap/sdk/startup/grpah/Node;", "", "()V", "edges", "Ljava/util/concurrent/ConcurrentHashMap;", "", "build", "Lcom/taptap/sdk/startup/grpah/Graph;", "kotlin.jvm.PlatformType", "putEdge", StatisticsContent.FROM, "to", "(Lcom/taptap/sdk/startup/grpah/Node;Lcom/taptap/sdk/startup/grpah/Node;)Lcom/taptap/sdk/startup/grpah/Graph$Builder;", "edge", "Lcom/taptap/sdk/startup/grpah/Edge;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder<T extends Node> {
        private final ConcurrentHashMap<T, Set<T>> edges = new ConcurrentHashMap<>();

        public final Builder<T> putEdge(Edge<T> edge) {
            Intrinsics.checkNotNullParameter(edge, "edge");
            return putEdge(edge.getFrom(), edge.getTo());
        }

        public final Builder<T> putEdge(T from, T to) {
            Set<T> setPutIfAbsent;
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            Builder<T> builder = this;
            ConcurrentHashMap<T, Set<T>> concurrentHashMap = builder.edges;
            Set<T> copyOnWriteArraySet = concurrentHashMap.get(from);
            if (copyOnWriteArraySet == null && (setPutIfAbsent = concurrentHashMap.putIfAbsent(from, (copyOnWriteArraySet = new CopyOnWriteArraySet<>()))) != null) {
                copyOnWriteArraySet = setPutIfAbsent;
            }
            Intrinsics.checkNotNullExpressionValue(copyOnWriteArraySet, "edges.getOrPut(from, ::CopyOnWriteArraySet)");
            copyOnWriteArraySet.add(to);
            return builder;
        }

        public final Graph<T> build() {
            Map mapUnmodifiableMap = Collections.unmodifiableMap(this.edges);
            Intrinsics.checkNotNullExpressionValue(mapUnmodifiableMap, "unmodifiableMap(this.edges)");
            return new Graph<>(mapUnmodifiableMap, null);
        }
    }
}
