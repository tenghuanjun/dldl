package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SegmentedButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ%\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J%\u0010$\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010#J%\u0010&\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010'\u001a\u00020 H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010#J\u0088\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u0013\u0010,\u001a\u00020 2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010.\u001a\u00020/H\u0016R\u0019\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u0011R\u0019\u0010\u000b\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0015\u0010\u0011R\u0019\u0010\t\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0016\u0010\u0011R\u0019\u0010\n\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0017\u0010\u0011R\u0019\u0010\u000e\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0018\u0010\u0011R\u0019\u0010\f\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0019\u0010\u0011R\u0019\u0010\r\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001a\u0010\u0011R\u0019\u0010\b\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001b\u0010\u0011R\u0019\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001c\u0010\u0011R\u0019\u0010\u0007\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001d\u0010\u0011\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00060"}, d2 = {"Landroidx/compose/material3/SegmentedButtonColors;", "", "activeContainerColor", "Landroidx/compose/ui/graphics/Color;", "activeContentColor", "activeBorderColor", "inactiveContainerColor", "inactiveContentColor", "inactiveBorderColor", "disabledActiveContainerColor", "disabledActiveContentColor", "disabledActiveBorderColor", "disabledInactiveContainerColor", "disabledInactiveContentColor", "disabledInactiveBorderColor", "(JJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getActiveBorderColor-0d7_KjU", "()J", "J", "getActiveContainerColor-0d7_KjU", "getActiveContentColor-0d7_KjU", "getDisabledActiveBorderColor-0d7_KjU", "getDisabledActiveContainerColor-0d7_KjU", "getDisabledActiveContentColor-0d7_KjU", "getDisabledInactiveBorderColor-0d7_KjU", "getDisabledInactiveContainerColor-0d7_KjU", "getDisabledInactiveContentColor-0d7_KjU", "getInactiveBorderColor-0d7_KjU", "getInactiveContainerColor-0d7_KjU", "getInactiveContentColor-0d7_KjU", "borderColor", "enabled", "", "active", "borderColor-WaAFU9c$material3_release", "(ZZ)J", "containerColor", "containerColor-WaAFU9c$material3_release", "contentColor", "checked", "contentColor-WaAFU9c$material3_release", "copy", "copy-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material3/SegmentedButtonColors;", "equals", "other", "hashCode", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SegmentedButtonColors {
    public static final int $stable = 0;
    private final long activeBorderColor;
    private final long activeContainerColor;
    private final long activeContentColor;
    private final long disabledActiveBorderColor;
    private final long disabledActiveContainerColor;
    private final long disabledActiveContentColor;
    private final long disabledInactiveBorderColor;
    private final long disabledInactiveContainerColor;
    private final long disabledInactiveContentColor;
    private final long inactiveBorderColor;
    private final long inactiveContainerColor;
    private final long inactiveContentColor;

    public /* synthetic */ SegmentedButtonColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12);
    }

    private SegmentedButtonColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12) {
        this.activeContainerColor = j;
        this.activeContentColor = j2;
        this.activeBorderColor = j3;
        this.inactiveContainerColor = j4;
        this.inactiveContentColor = j5;
        this.inactiveBorderColor = j6;
        this.disabledActiveContainerColor = j7;
        this.disabledActiveContentColor = j8;
        this.disabledActiveBorderColor = j9;
        this.disabledInactiveContainerColor = j10;
        this.disabledInactiveContentColor = j11;
        this.disabledInactiveBorderColor = j12;
    }

    /* JADX INFO: renamed from: getActiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContainerColor() {
        return this.activeContainerColor;
    }

    /* JADX INFO: renamed from: getActiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContentColor() {
        return this.activeContentColor;
    }

    /* JADX INFO: renamed from: getActiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveBorderColor() {
        return this.activeBorderColor;
    }

    /* JADX INFO: renamed from: getInactiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContainerColor() {
        return this.inactiveContainerColor;
    }

    /* JADX INFO: renamed from: getInactiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContentColor() {
        return this.inactiveContentColor;
    }

    /* JADX INFO: renamed from: getInactiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveBorderColor() {
        return this.inactiveBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveContainerColor() {
        return this.disabledActiveContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveContentColor() {
        return this.disabledActiveContentColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveBorderColor() {
        return this.disabledActiveBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveContainerColor() {
        return this.disabledInactiveContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveContentColor() {
        return this.disabledInactiveContentColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveBorderColor() {
        return this.disabledInactiveBorderColor;
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1848borderColorWaAFU9c$material3_release(boolean enabled, boolean active) {
        if (enabled && active) {
            return this.activeBorderColor;
        }
        if (enabled && !active) {
            return this.inactiveBorderColor;
        }
        if (!enabled && active) {
            return this.disabledActiveBorderColor;
        }
        return this.disabledInactiveBorderColor;
    }

    /* JADX INFO: renamed from: contentColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1850contentColorWaAFU9c$material3_release(boolean enabled, boolean checked) {
        if (enabled && checked) {
            return this.activeContentColor;
        }
        if (enabled && !checked) {
            return this.inactiveContentColor;
        }
        if (!enabled && checked) {
            return this.disabledActiveContentColor;
        }
        return this.disabledInactiveContentColor;
    }

    /* JADX INFO: renamed from: containerColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m1849containerColorWaAFU9c$material3_release(boolean enabled, boolean active) {
        if (enabled && active) {
            return this.activeContainerColor;
        }
        if (enabled && !active) {
            return this.inactiveContainerColor;
        }
        if (!enabled && active) {
            return this.disabledActiveContainerColor;
        }
        return this.disabledInactiveContainerColor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        SegmentedButtonColors segmentedButtonColors = (SegmentedButtonColors) other;
        return Color.m3495equalsimpl0(this.activeBorderColor, segmentedButtonColors.activeBorderColor) && Color.m3495equalsimpl0(this.activeContentColor, segmentedButtonColors.activeContentColor) && Color.m3495equalsimpl0(this.activeContainerColor, segmentedButtonColors.activeContainerColor) && Color.m3495equalsimpl0(this.inactiveBorderColor, segmentedButtonColors.inactiveBorderColor) && Color.m3495equalsimpl0(this.inactiveContentColor, segmentedButtonColors.inactiveContentColor) && Color.m3495equalsimpl0(this.inactiveContainerColor, segmentedButtonColors.inactiveContainerColor) && Color.m3495equalsimpl0(this.disabledActiveBorderColor, segmentedButtonColors.disabledActiveBorderColor) && Color.m3495equalsimpl0(this.disabledActiveContentColor, segmentedButtonColors.disabledActiveContentColor) && Color.m3495equalsimpl0(this.disabledActiveContainerColor, segmentedButtonColors.disabledActiveContainerColor) && Color.m3495equalsimpl0(this.disabledInactiveBorderColor, segmentedButtonColors.disabledInactiveBorderColor) && Color.m3495equalsimpl0(this.disabledInactiveContentColor, segmentedButtonColors.disabledInactiveContentColor) && Color.m3495equalsimpl0(this.disabledInactiveContainerColor, segmentedButtonColors.disabledInactiveContainerColor);
    }

    public int hashCode() {
        return (((((((((((((((((((((Color.m3501hashCodeimpl(this.activeBorderColor) * 31) + Color.m3501hashCodeimpl(this.activeContentColor)) * 31) + Color.m3501hashCodeimpl(this.activeContainerColor)) * 31) + Color.m3501hashCodeimpl(this.inactiveBorderColor)) * 31) + Color.m3501hashCodeimpl(this.inactiveContentColor)) * 31) + Color.m3501hashCodeimpl(this.inactiveContainerColor)) * 31) + Color.m3501hashCodeimpl(this.disabledActiveBorderColor)) * 31) + Color.m3501hashCodeimpl(this.disabledActiveContentColor)) * 31) + Color.m3501hashCodeimpl(this.disabledActiveContainerColor)) * 31) + Color.m3501hashCodeimpl(this.disabledInactiveBorderColor)) * 31) + Color.m3501hashCodeimpl(this.disabledInactiveContentColor)) * 31) + Color.m3501hashCodeimpl(this.disabledInactiveContainerColor);
    }

    /* JADX INFO: renamed from: copy-2qZNXz8, reason: not valid java name */
    public final SegmentedButtonColors m1851copy2qZNXz8(long activeContainerColor, long activeContentColor, long activeBorderColor, long inactiveContainerColor, long inactiveContentColor, long inactiveBorderColor, long disabledActiveContainerColor, long disabledActiveContentColor, long disabledActiveBorderColor, long disabledInactiveContainerColor, long disabledInactiveContentColor, long disabledInactiveBorderColor) {
        return new SegmentedButtonColors(activeContainerColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? activeContainerColor : this.activeContainerColor, activeContentColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? activeContentColor : this.activeContentColor, activeBorderColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? activeBorderColor : this.activeBorderColor, inactiveContainerColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? inactiveContainerColor : this.inactiveContainerColor, inactiveContentColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? inactiveContentColor : this.inactiveContentColor, inactiveBorderColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? inactiveBorderColor : this.inactiveBorderColor, disabledActiveContainerColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? disabledActiveContainerColor : this.disabledActiveContainerColor, disabledActiveContentColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? disabledActiveContentColor : this.disabledActiveContentColor, disabledActiveBorderColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? disabledActiveBorderColor : this.disabledActiveBorderColor, disabledInactiveContainerColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? disabledInactiveContainerColor : this.disabledInactiveContainerColor, disabledInactiveContentColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? disabledInactiveContentColor : this.disabledInactiveContentColor, disabledInactiveBorderColor != Color.INSTANCE.m3530getUnspecified0d7_KjU() ? disabledInactiveBorderColor : this.disabledInactiveBorderColor, null);
    }
}
