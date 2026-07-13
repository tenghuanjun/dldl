package androidx.compose.ui.layout;

import androidx.compose.ui.node.MeasureScopeWithLayoutNodeKt;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MultiContentMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0004"}, d2 = {"createMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "measurePolicy", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultiContentMeasurePolicyKt {
    public static final MeasurePolicy createMeasurePolicy(final MultiContentMeasurePolicy multiContentMeasurePolicy) {
        return new MeasurePolicy() { // from class: androidx.compose.ui.layout.MultiContentMeasurePolicyKt$createMeasurePolicy$1$1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo69measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                return multiContentMeasurePolicy.mo1690measure3p2s80s(measureScope, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren(measureScope), j);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
                return multiContentMeasurePolicy.minIntrinsicWidth(intrinsicMeasureScope, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren(intrinsicMeasureScope), i);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
                return multiContentMeasurePolicy.minIntrinsicHeight(intrinsicMeasureScope, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren(intrinsicMeasureScope), i);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
                return multiContentMeasurePolicy.maxIntrinsicWidth(intrinsicMeasureScope, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren(intrinsicMeasureScope), i);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
                return multiContentMeasurePolicy.maxIntrinsicHeight(intrinsicMeasureScope, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren(intrinsicMeasureScope), i);
            }
        };
    }
}
