package androidx.compose.ui.semantics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.http2.Http2Connection;

/* JADX INFO: compiled from: SemanticsNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\"\u0010\n\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000b\u001a\u00020\u0002H\u0000\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0007H\u0002\u001a\"\u0010\u0011\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\u0013H\u0000\u001a\f\u0010\u0014\u001a\u00020\u0010*\u00020\u0007H\u0002\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"outerMergingSemantics", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/LayoutNode;", "getOuterMergingSemantics", "(Landroidx/compose/ui/node/LayoutNode;)Landroidx/compose/ui/node/SemanticsModifierNode;", "role", "Landroidx/compose/ui/semantics/Role;", "Landroidx/compose/ui/semantics/SemanticsNode;", "getRole", "(Landroidx/compose/ui/semantics/SemanticsNode;)Landroidx/compose/ui/semantics/Role;", "SemanticsNode", "layoutNode", "mergingEnabled", "", "outerSemanticsNode", "contentDescriptionFakeNodeId", "", "findClosestParentNode", "selector", "Lkotlin/Function1;", "roleFakeNodeId", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SemanticsNodeKt {
    /* JADX WARN: Removed duplicated region for block: B:39:0x0079 A[LOOP:0: B:5:0x0016->B:39:0x0079, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007e A[EDGE_INSN: B:44:0x007e->B:40:0x007e BREAK  A[LOOP:0: B:5:0x0016->B:39:0x0079], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.semantics.SemanticsNode SemanticsNode(androidx.compose.ui.node.LayoutNode r10, boolean r11) {
        /*
            androidx.compose.ui.node.NodeChain r0 = r10.getNodes()
            r1 = 8
            int r1 = androidx.compose.ui.node.NodeKind.m4993constructorimpl(r1)
            int r2 = androidx.compose.ui.node.NodeChain.access$getAggregateChildKindSet(r0)
            r2 = r2 & r1
            r3 = 0
            if (r2 == 0) goto L7e
            androidx.compose.ui.Modifier$Node r0 = r0.getHead()
        L16:
            if (r0 == 0) goto L7e
            int r2 = r0.getKindSet()
            r2 = r2 & r1
            if (r2 == 0) goto L72
            r2 = r0
            r4 = r3
        L21:
            if (r2 == 0) goto L72
            boolean r5 = r2 instanceof androidx.compose.ui.node.SemanticsModifierNode
            if (r5 == 0) goto L29
            r3 = r2
            goto L7e
        L29:
            int r5 = r2.getKindSet()
            r5 = r5 & r1
            if (r5 == 0) goto L6d
            boolean r5 = r2 instanceof androidx.compose.ui.node.DelegatingNode
            if (r5 == 0) goto L6d
            r5 = r2
            androidx.compose.ui.node.DelegatingNode r5 = (androidx.compose.ui.node.DelegatingNode) r5
            androidx.compose.ui.Modifier$Node r5 = r5.getDelegate()
            r6 = 0
            r7 = 0
        L3d:
            r8 = 1
            if (r5 == 0) goto L6a
            int r9 = r5.getKindSet()
            r9 = r9 & r1
            if (r9 == 0) goto L65
            int r7 = r7 + 1
            if (r7 != r8) goto L4d
            r2 = r5
            goto L65
        L4d:
            if (r4 != 0) goto L58
            androidx.compose.runtime.collection.MutableVector r4 = new androidx.compose.runtime.collection.MutableVector
            r8 = 16
            androidx.compose.ui.Modifier$Node[] r8 = new androidx.compose.ui.Modifier.Node[r8]
            r4.<init>(r8, r6)
        L58:
            if (r2 == 0) goto L60
            if (r4 == 0) goto L5f
            r4.add(r2)
        L5f:
            r2 = r3
        L60:
            if (r4 == 0) goto L65
            r4.add(r5)
        L65:
            androidx.compose.ui.Modifier$Node r5 = r5.getChild()
            goto L3d
        L6a:
            if (r7 != r8) goto L6d
            goto L21
        L6d:
            androidx.compose.ui.Modifier$Node r2 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r4)
            goto L21
        L72:
            int r2 = r0.getAggregateChildKindSet()
            r2 = r2 & r1
            if (r2 == 0) goto L7e
            androidx.compose.ui.Modifier$Node r0 = r0.getChild()
            goto L16
        L7e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            androidx.compose.ui.node.SemanticsModifierNode r3 = (androidx.compose.ui.node.SemanticsModifierNode) r3
            androidx.compose.ui.Modifier$Node r0 = r3.getNode()
            androidx.compose.ui.semantics.SemanticsConfiguration r1 = r10.getCollapsedSemantics$ui_release()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            androidx.compose.ui.semantics.SemanticsNode r2 = new androidx.compose.ui.semantics.SemanticsNode
            r2.<init>(r0, r11, r10, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.semantics.SemanticsNodeKt.SemanticsNode(androidx.compose.ui.node.LayoutNode, boolean):androidx.compose.ui.semantics.SemanticsNode");
    }

    public static /* synthetic */ SemanticsNode SemanticsNode$default(SemanticsModifierNode semanticsModifierNode, boolean z, LayoutNode layoutNode, int i, Object obj) {
        if ((i & 4) != 0) {
            layoutNode = DelegatableNodeKt.requireLayoutNode(semanticsModifierNode);
        }
        return SemanticsNode(semanticsModifierNode, z, layoutNode);
    }

    public static final SemanticsNode SemanticsNode(SemanticsModifierNode semanticsModifierNode, boolean z, LayoutNode layoutNode) {
        Modifier.Node node = semanticsModifierNode.getNode();
        SemanticsConfiguration collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release();
        if (collapsedSemantics$ui_release == null) {
            collapsedSemantics$ui_release = new SemanticsConfiguration();
        }
        return new SemanticsNode(node, z, layoutNode, collapsedSemantics$ui_release);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0082 A[LOOP:0: B:5:0x0016->B:41:0x0082, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0087 A[EDGE_INSN: B:46:0x0087->B:42:0x0087 BREAK  A[LOOP:0: B:5:0x0016->B:41:0x0082], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.node.SemanticsModifierNode getOuterMergingSemantics(androidx.compose.ui.node.LayoutNode r9) {
        /*
            androidx.compose.ui.node.NodeChain r9 = r9.getNodes()
            r0 = 8
            int r0 = androidx.compose.ui.node.NodeKind.m4993constructorimpl(r0)
            int r1 = androidx.compose.ui.node.NodeChain.access$getAggregateChildKindSet(r9)
            r1 = r1 & r0
            r2 = 0
            if (r1 == 0) goto L87
            androidx.compose.ui.Modifier$Node r9 = r9.getHead()
        L16:
            if (r9 == 0) goto L87
            int r1 = r9.getKindSet()
            r1 = r1 & r0
            if (r1 == 0) goto L7b
            r1 = r9
            r3 = r2
        L21:
            if (r1 == 0) goto L7b
            boolean r4 = r1 instanceof androidx.compose.ui.node.SemanticsModifierNode
            if (r4 == 0) goto L32
            r4 = r1
            androidx.compose.ui.node.SemanticsModifierNode r4 = (androidx.compose.ui.node.SemanticsModifierNode) r4
            boolean r4 = r4.getShouldMergeDescendantSemantics()
            if (r4 == 0) goto L76
            r2 = r1
            goto L87
        L32:
            int r4 = r1.getKindSet()
            r4 = r4 & r0
            if (r4 == 0) goto L76
            boolean r4 = r1 instanceof androidx.compose.ui.node.DelegatingNode
            if (r4 == 0) goto L76
            r4 = r1
            androidx.compose.ui.node.DelegatingNode r4 = (androidx.compose.ui.node.DelegatingNode) r4
            androidx.compose.ui.Modifier$Node r4 = r4.getDelegate()
            r5 = 0
            r6 = 0
        L46:
            r7 = 1
            if (r4 == 0) goto L73
            int r8 = r4.getKindSet()
            r8 = r8 & r0
            if (r8 == 0) goto L6e
            int r6 = r6 + 1
            if (r6 != r7) goto L56
            r1 = r4
            goto L6e
        L56:
            if (r3 != 0) goto L61
            androidx.compose.runtime.collection.MutableVector r3 = new androidx.compose.runtime.collection.MutableVector
            r7 = 16
            androidx.compose.ui.Modifier$Node[] r7 = new androidx.compose.ui.Modifier.Node[r7]
            r3.<init>(r7, r5)
        L61:
            if (r1 == 0) goto L69
            if (r3 == 0) goto L68
            r3.add(r1)
        L68:
            r1 = r2
        L69:
            if (r3 == 0) goto L6e
            r3.add(r4)
        L6e:
            androidx.compose.ui.Modifier$Node r4 = r4.getChild()
            goto L46
        L73:
            if (r6 != r7) goto L76
            goto L21
        L76:
            androidx.compose.ui.Modifier$Node r1 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r3)
            goto L21
        L7b:
            int r1 = r9.getAggregateChildKindSet()
            r1 = r1 & r0
            if (r1 == 0) goto L87
            androidx.compose.ui.Modifier$Node r9 = r9.getChild()
            goto L16
        L87:
            androidx.compose.ui.node.SemanticsModifierNode r2 = (androidx.compose.ui.node.SemanticsModifierNode) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.semantics.SemanticsNodeKt.getOuterMergingSemantics(androidx.compose.ui.node.LayoutNode):androidx.compose.ui.node.SemanticsModifierNode");
    }

    public static final LayoutNode findClosestParentNode(LayoutNode layoutNode, Function1<? super LayoutNode, Boolean> function1) {
        for (LayoutNode parent$ui_release = layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (function1.invoke(parent$ui_release).booleanValue()) {
                return parent$ui_release;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Role getRole(SemanticsNode semanticsNode) {
        return (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int contentDescriptionFakeNodeId(SemanticsNode semanticsNode) {
        return semanticsNode.getId() + 2000000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int roleFakeNodeId(SemanticsNode semanticsNode) {
        return semanticsNode.getId() + Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
    }
}
