package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.lzy.okgo.cache.CacheEntity;
import kotlin.Metadata;

/* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002J)\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\b0\t2\u0006\u0010\u000f\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Landroidx/compose/ui/node/DelegatableNode;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "current", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/modifier/ModifierLocal;", "getCurrent", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "provide", "", CacheEntity.KEY, DBHelper.COL_VALUE, "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ModifierLocalModifierNode extends ModifierLocalReadScope, DelegatableNode {
    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    <T> T getCurrent(ModifierLocal<T> modifierLocal);

    ModifierLocalMap getProvidedValues();

    <T> void provide(ModifierLocal<T> key, T value);

    /* JADX INFO: renamed from: androidx.compose.ui.modifier.ModifierLocalModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
    public final /* synthetic */ class CC {
        public static void $default$provide(ModifierLocalModifierNode _this, ModifierLocal modifierLocal, Object obj) {
            if (_this.getProvidedValues() == EmptyMap.INSTANCE) {
                throw new IllegalArgumentException("In order to provide locals you must override providedValues: ModifierLocalMap".toString());
            }
            if (!_this.getProvidedValues().contains$ui_release(modifierLocal)) {
                throw new IllegalArgumentException(("Any provided key must be initially provided in the overridden providedValues: ModifierLocalMap property. Key " + modifierLocal + " was not found.").toString());
            }
            _this.getProvidedValues().mo4873set$ui_release(modifierLocal, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v12 */
        public static Object $default$getCurrent(ModifierLocalModifierNode modifierLocalModifierNode, ModifierLocal modifierLocal) {
            NodeChain nodes;
            if (!modifierLocalModifierNode.getNode().getIsAttached()) {
                throw new IllegalArgumentException("ModifierLocal accessed from an unattached node".toString());
            }
            ModifierLocalModifierNode modifierLocalModifierNode2 = modifierLocalModifierNode;
            int iM4993constructorimpl = NodeKind.m4993constructorimpl(32);
            if (!modifierLocalModifierNode2.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node parent = modifierLocalModifierNode2.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(modifierLocalModifierNode2);
            while (layoutNodeRequireLayoutNode != null) {
                if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4993constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM4993constructorimpl) != 0) {
                            Modifier.Node nodePop = parent;
                            MutableVector mutableVector = null;
                            while (nodePop != 0) {
                                if (nodePop instanceof ModifierLocalModifierNode) {
                                    ModifierLocalModifierNode modifierLocalModifierNode3 = (ModifierLocalModifierNode) nodePop;
                                    if (modifierLocalModifierNode3.getProvidedValues().contains$ui_release(modifierLocal)) {
                                        return modifierLocalModifierNode3.getProvidedValues().get$ui_release(modifierLocal);
                                    }
                                } else if ((nodePop.getKindSet() & iM4993constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                    int i = 0;
                                    nodePop = nodePop;
                                    while (delegate$ui_release != null) {
                                        if ((delegate$ui_release.getKindSet() & iM4993constructorimpl) != 0) {
                                            i++;
                                            if (i == 1) {
                                                nodePop = delegate$ui_release;
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
                                                    mutableVector.add(delegate$ui_release);
                                                }
                                            }
                                        }
                                        delegate$ui_release = delegate$ui_release.getChild();
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
            return modifierLocal.getDefaultFactory$ui_release().invoke();
        }
    }
}
