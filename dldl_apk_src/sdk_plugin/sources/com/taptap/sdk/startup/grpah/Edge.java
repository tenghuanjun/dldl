package com.taptap.sdk.startup.grpah;

import com.huya.statistics.core.StatisticsContent;
import com.taptap.sdk.startup.grpah.Node;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Edge.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ(\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/taptap/sdk/startup/grpah/Edge;", "T", "Lcom/taptap/sdk/startup/grpah/Node;", "", StatisticsContent.FROM, "to", "(Lcom/taptap/sdk/startup/grpah/Node;Lcom/taptap/sdk/startup/grpah/Node;)V", "getFrom", "()Lcom/taptap/sdk/startup/grpah/Node;", "Lcom/taptap/sdk/startup/grpah/Node;", "getTo", "component1", "component2", "copy", "(Lcom/taptap/sdk/startup/grpah/Node;Lcom/taptap/sdk/startup/grpah/Node;)Lcom/taptap/sdk/startup/grpah/Edge;", "equals", "", "other", "hashCode", "", "toString", "", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class Edge<T extends Node> {
    private final T from;
    private final T to;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Edge copy$default(Edge edge, Node node, Node node2, int i, Object obj) {
        if ((i & 1) != 0) {
            node = edge.from;
        }
        if ((i & 2) != 0) {
            node2 = edge.to;
        }
        return edge.copy(node, node2);
    }

    public final T component1() {
        return this.from;
    }

    public final T component2() {
        return this.to;
    }

    public final Edge<T> copy(T from, T to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        return new Edge<>(from, to);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) other;
        return Intrinsics.areEqual(this.from, edge.from) && Intrinsics.areEqual(this.to, edge.to);
    }

    public int hashCode() {
        return (this.from.hashCode() * 31) + this.to.hashCode();
    }

    public String toString() {
        return "Edge(from=" + this.from + ", to=" + this.to + ')';
    }

    public Edge(T from, T to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        this.from = from;
        this.to = to;
    }

    public final T getFrom() {
        return this.from;
    }

    public final T getTo() {
        return this.to;
    }
}
