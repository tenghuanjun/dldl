package androidx.compose.material3.carousel;

import androidx.compose.ui.util.MathHelpersKt;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.lzy.okgo.model.Progress;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: Strategy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a0\u0010\u0000\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002¨\u0006\u000e"}, d2 = {"lerp", "Landroidx/compose/material3/carousel/Keyline;", "start", "end", Progress.FRACTION, "", "Landroidx/compose/material3/carousel/KeylineList;", Constants.FROM, "to", "outputMin", "outputMax", "inputMin", "inputMax", DBHelper.COL_VALUE, "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrategyKt {
    public static final Keyline lerp(Keyline keyline, Keyline keyline2, float f) {
        return new Keyline(MathHelpersKt.lerp(keyline.getSize(), keyline2.getSize(), f), MathHelpersKt.lerp(keyline.getOffset(), keyline2.getOffset(), f), MathHelpersKt.lerp(keyline.getUnadjustedOffset(), keyline2.getUnadjustedOffset(), f), f < 0.5f ? keyline.isFocal() : keyline2.isFocal(), f < 0.5f ? keyline.isAnchor() : keyline2.isAnchor(), f < 0.5f ? keyline.isPivot() : keyline2.isPivot(), MathHelpersKt.lerp(keyline.getCutoff(), keyline2.getCutoff(), f));
    }

    public static final KeylineList lerp(KeylineList keylineList, KeylineList keylineList2, float f) {
        KeylineList keylineList3 = keylineList;
        ArrayList arrayList = new ArrayList(keylineList3.size());
        int size = keylineList3.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(lerp(keylineList3.get(i), keylineList2.get(i), f));
        }
        return new KeylineList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float lerp(float f, float f2, float f3, float f4, float f5) {
        return f5 <= f3 ? f : f5 >= f4 ? f2 : MathHelpersKt.lerp(f, f2, (f5 - f3) / (f4 - f3));
    }
}
