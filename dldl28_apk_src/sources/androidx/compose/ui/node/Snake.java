package androidx.compose.ui.node;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0083@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\u001a\u0010\"\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b'\u0010\u000bJ\u000f\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000b\u0088\u0001\u0002¨\u0006,"}, d2 = {"Landroidx/compose/ui/node/Snake;", "", "data", "", "constructor-impl", "([I)[I", "getData", "()[I", "diagonalSize", "", "getDiagonalSize-impl", "([I)I", "endX", "getEndX-impl", "endY", "getEndY-impl", "hasAdditionOrRemoval", "", "getHasAdditionOrRemoval-impl", "([I)Z", "isAddition", "isAddition-impl", "reverse", "getReverse-impl", "startX", "getStartX-impl", "startY", "getStartY-impl", "addDiagonalToStack", "", "diagonals", "Landroidx/compose/ui/node/IntStack;", "addDiagonalToStack-impl", "([ILandroidx/compose/ui/node/IntStack;)V", "equals", "other", "equals-impl", "([ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "([I)Ljava/lang/String;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
final class Snake {
    private final int[] data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Snake m5052boximpl(int[] iArr) {
        return new Snake(iArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m5053constructorimpl(int[] iArr) {
        return iArr;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5054equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof Snake) && Intrinsics.areEqual(iArr, ((Snake) obj).m5066unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5055equalsimpl0(int[] iArr, int[] iArr2) {
        return Intrinsics.areEqual(iArr, iArr2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5063hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    public boolean equals(Object obj) {
        return m5054equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m5063hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int[] m5066unboximpl() {
        return this.data;
    }

    private /* synthetic */ Snake(int[] iArr) {
        this.data = iArr;
    }

    public final int[] getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: getStartX-impl, reason: not valid java name */
    public static final int m5061getStartXimpl(int[] iArr) {
        return iArr[0];
    }

    /* JADX INFO: renamed from: getStartY-impl, reason: not valid java name */
    public static final int m5062getStartYimpl(int[] iArr) {
        return iArr[1];
    }

    /* JADX INFO: renamed from: getEndX-impl, reason: not valid java name */
    public static final int m5057getEndXimpl(int[] iArr) {
        return iArr[2];
    }

    /* JADX INFO: renamed from: getEndY-impl, reason: not valid java name */
    public static final int m5058getEndYimpl(int[] iArr) {
        return iArr[3];
    }

    /* JADX INFO: renamed from: getReverse-impl, reason: not valid java name */
    public static final boolean m5060getReverseimpl(int[] iArr) {
        return iArr[4] != 0;
    }

    /* JADX INFO: renamed from: getDiagonalSize-impl, reason: not valid java name */
    public static final int m5056getDiagonalSizeimpl(int[] iArr) {
        return Math.min(m5057getEndXimpl(iArr) - m5061getStartXimpl(iArr), m5058getEndYimpl(iArr) - m5062getStartYimpl(iArr));
    }

    /* JADX INFO: renamed from: getHasAdditionOrRemoval-impl, reason: not valid java name */
    private static final boolean m5059getHasAdditionOrRemovalimpl(int[] iArr) {
        return m5058getEndYimpl(iArr) - m5062getStartYimpl(iArr) != m5057getEndXimpl(iArr) - m5061getStartXimpl(iArr);
    }

    /* JADX INFO: renamed from: isAddition-impl, reason: not valid java name */
    private static final boolean m5064isAdditionimpl(int[] iArr) {
        return m5058getEndYimpl(iArr) - m5062getStartYimpl(iArr) > m5057getEndXimpl(iArr) - m5061getStartXimpl(iArr);
    }

    /* JADX INFO: renamed from: addDiagonalToStack-impl, reason: not valid java name */
    public static final void m5051addDiagonalToStackimpl(int[] iArr, IntStack intStack) {
        if (m5059getHasAdditionOrRemovalimpl(iArr)) {
            if (m5060getReverseimpl(iArr)) {
                intStack.pushDiagonal(m5061getStartXimpl(iArr), m5062getStartYimpl(iArr), m5056getDiagonalSizeimpl(iArr));
                return;
            } else if (m5064isAdditionimpl(iArr)) {
                intStack.pushDiagonal(m5061getStartXimpl(iArr), m5062getStartYimpl(iArr) + 1, m5056getDiagonalSizeimpl(iArr));
                return;
            } else {
                intStack.pushDiagonal(m5061getStartXimpl(iArr) + 1, m5062getStartYimpl(iArr), m5056getDiagonalSizeimpl(iArr));
                return;
            }
        }
        intStack.pushDiagonal(m5061getStartXimpl(iArr), m5062getStartYimpl(iArr), m5057getEndXimpl(iArr) - m5061getStartXimpl(iArr));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5065toStringimpl(int[] iArr) {
        return "Snake(" + m5061getStartXimpl(iArr) + ',' + m5062getStartYimpl(iArr) + ',' + m5057getEndXimpl(iArr) + ',' + m5058getEndYimpl(iArr) + ',' + m5060getReverseimpl(iArr) + ')';
    }

    public String toString() {
        return m5065toStringimpl(this.data);
    }
}
