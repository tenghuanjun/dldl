package androidx.compose.ui.input.pointer;

import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.JvmInline;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerId;", "", DBHelper.COL_VALUE, "", "constructor-impl", "(J)J", "getValue", "()J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
public final class PointerId {
    private final long value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PointerId m4635boximpl(long j) {
        return new PointerId(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m4636constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4637equalsimpl(long j, Object obj) {
        return (obj instanceof PointerId) && j == ((PointerId) obj).m4641unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4638equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4639hashCodeimpl(long j) {
        return UByte$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4640toStringimpl(long j) {
        return "PointerId(value=" + j + ')';
    }

    public boolean equals(Object obj) {
        return m4637equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m4639hashCodeimpl(this.value);
    }

    public String toString() {
        return m4640toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m4641unboximpl() {
        return this.value;
    }

    private /* synthetic */ PointerId(long j) {
        this.value = j;
    }

    public final long getValue() {
        return this.value;
    }
}
