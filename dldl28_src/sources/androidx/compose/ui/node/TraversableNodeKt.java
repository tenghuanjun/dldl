package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.TraversableNode;
import androidx.exifinterface.media.ExifInterface;
import com.lzy.okgo.cache.CacheEntity;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TraversableNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u0001¢\u0006\u0002\u0010\u0003\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0002*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a-\u0010\u0007\u001a\u00020\b\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\u0007\u001a\u00020\b*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\r\u001a\u00020\b\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\r\u001a\u00020\b*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\u000e\u001a\u00020\b\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0002\u0010\f\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\n¨\u0006\u0010"}, d2 = {"findNearestAncestor", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/node/TraversableNode;", "(Landroidx/compose/ui/node/TraversableNode;)Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/DelegatableNode;", CacheEntity.KEY, "", "traverseAncestors", "", "block", "Lkotlin/Function1;", "", "(Landroidx/compose/ui/node/TraversableNode;Lkotlin/jvm/functions/Function1;)V", "traverseChildren", "traverseDescendants", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TraversableNodeKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    public static final <T extends TraversableNode> T findNearestAncestor(T t) {
        NodeChain nodes;
        T t2 = t;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(262144);
        if (!t2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = t2.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(t2);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4993constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                T t3 = (T) nodePop;
                                if (Intrinsics.areEqual(t.getTraverseKey(), t3.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, t3)) {
                                    return t3;
                                }
                            } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v14 */
    public static final <T extends TraversableNode> void traverseAncestors(T t, Function1<? super T, Boolean> function1) {
        NodeChain nodes;
        T t2 = t;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(262144);
        if (!t2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = t2.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(t2);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4993constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            boolean zBooleanValue = true;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (Intrinsics.areEqual(t.getTraverseKey(), traversableNode.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, traversableNode)) {
                                    zBooleanValue = function1.invoke(traversableNode).booleanValue();
                                }
                                if (!zBooleanValue) {
                                    return;
                                }
                            } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0032, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T extends androidx.compose.ui.node.TraversableNode> void traverseChildren(T r11, kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> r12) {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseChildren(androidx.compose.ui.node.TraversableNode, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v10 */
    public static final <T extends TraversableNode> void traverseDescendants(T t, Function1<? super T, ? extends TraversableNode.Companion.TraverseDescendantsAction> function1) {
        TraversableNode.Companion.TraverseDescendantsAction traverseDescendantsActionInvoke;
        T t2 = t;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(262144);
        if (!t2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node".toString());
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = t2.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, t2.getNode());
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                for (Modifier.Node child2 = node; child2 != null; child2 = child2.getChild()) {
                    if ((child2.getKindSet() & iM4993constructorimpl) != 0) {
                        Modifier.Node nodePop = child2;
                        MutableVector mutableVector2 = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (Intrinsics.areEqual(t.getTraverseKey(), traversableNode.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, traversableNode)) {
                                    traverseDescendantsActionInvoke = function1.invoke(traversableNode);
                                } else {
                                    traverseDescendantsActionInvoke = TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                                }
                                if (traverseDescendantsActionInvoke == TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal) {
                                    return;
                                }
                                if (traverseDescendantsActionInvoke == TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal) {
                                    break;
                                }
                            } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(delegate);
                                            }
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12 */
    public static final TraversableNode findNearestAncestor(DelegatableNode delegatableNode, Object obj) {
        NodeChain nodes;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(262144);
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4993constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (Intrinsics.areEqual(obj, traversableNode.getTraverseKey())) {
                                    return traversableNode;
                                }
                            } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v14 */
    public static final void traverseAncestors(DelegatableNode delegatableNode, Object obj, Function1<? super TraversableNode, Boolean> function1) {
        NodeChain nodes;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(262144);
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4993constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (!(Intrinsics.areEqual(obj, traversableNode.getTraverseKey()) ? function1.invoke(traversableNode).booleanValue() : true)) {
                                    return;
                                }
                            } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate);
                                            }
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x002f, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void traverseChildren(androidx.compose.ui.node.DelegatableNode r10, java.lang.Object r11, kotlin.jvm.functions.Function1<? super androidx.compose.ui.node.TraversableNode, java.lang.Boolean> r12) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseChildren(androidx.compose.ui.node.DelegatableNode, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    public static final void traverseDescendants(DelegatableNode delegatableNode, Object obj, Function1<? super TraversableNode, ? extends TraversableNode.Companion.TraverseDescendantsAction> function1) {
        TraversableNode.Companion.TraverseDescendantsAction traverseDescendantsActionInvoke;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(262144);
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node".toString());
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, delegatableNode.getNode());
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                for (Modifier.Node child2 = node; child2 != null; child2 = child2.getChild()) {
                    if ((child2.getKindSet() & iM4993constructorimpl) != 0) {
                        Modifier.Node nodePop = child2;
                        MutableVector mutableVector2 = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (Intrinsics.areEqual(obj, traversableNode.getTraverseKey())) {
                                    traverseDescendantsActionInvoke = function1.invoke(traversableNode);
                                } else {
                                    traverseDescendantsActionInvoke = TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                                }
                                if (traverseDescendantsActionInvoke == TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal) {
                                    return;
                                }
                                if (traverseDescendantsActionInvoke == TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal) {
                                    break;
                                }
                            } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(delegate);
                                            }
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node);
        }
    }
}
