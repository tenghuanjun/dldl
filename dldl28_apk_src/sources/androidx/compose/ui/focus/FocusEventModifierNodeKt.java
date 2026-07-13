package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusEventModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0004*\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"getFocusState", "Landroidx/compose/ui/focus/FocusState;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "invalidateFocusEvent", "", "refreshFocusEventNodes", "Landroidx/compose/ui/focus/FocusTargetNode;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusEventModifierNodeKt {

    /* JADX INFO: compiled from: FocusEventModifierNode.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void invalidateFocusEvent(FocusEventModifierNode focusEventModifierNode) {
        DelegatableNodeKt.requireOwner(focusEventModifierNode).getFocusOwner().scheduleInvalidation(focusEventModifierNode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:0x009d, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusState getFocusState(androidx.compose.ui.focus.FocusEventModifierNode r12) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusEventModifierNodeKt.getFocusState(androidx.compose.ui.focus.FocusEventModifierNode):androidx.compose.ui.focus.FocusState");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v14 */
    public static final void refreshFocusEventNodes(FocusTargetNode focusTargetNode) {
        NodeChain nodes;
        FocusTargetNode focusTargetNode2 = focusTargetNode;
        int iM4993constructorimpl = NodeKind.m4993constructorimpl(4096);
        int iM4993constructorimpl2 = NodeKind.m4993constructorimpl(1024);
        Modifier.Node node = focusTargetNode2.getNode();
        int i = iM4993constructorimpl | iM4993constructorimpl2;
        if (!focusTargetNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node2 = focusTargetNode2.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode2);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i) != 0) {
                        if (node2 != node && (node2.getKindSet() & iM4993constructorimpl2) != 0) {
                            return;
                        }
                        if ((node2.getKindSet() & iM4993constructorimpl) != 0) {
                            Modifier.Node nodePop = node2;
                            MutableVector mutableVector = null;
                            while (nodePop != 0) {
                                if (!(nodePop instanceof FocusEventModifierNode)) {
                                    if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                        Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                        int i2 = 0;
                                        nodePop = nodePop;
                                        while (delegate != null) {
                                            if ((delegate.getKindSet() & iM4993constructorimpl) != 0) {
                                                i2++;
                                                if (i2 == 1) {
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
                                        if (i2 == 1) {
                                        }
                                    }
                                } else {
                                    FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) nodePop;
                                    focusEventModifierNode.onFocusEvent(getFocusState(focusEventModifierNode));
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                    }
                    node2 = node2.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }
}
