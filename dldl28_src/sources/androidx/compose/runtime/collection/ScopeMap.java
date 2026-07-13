package androidx.compose.runtime.collection;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import androidx.exifinterface.media.ExifInterface;
import com.lzy.okgo.cache.CacheEntity;
import com.tencent.connect.common.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScopeMap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\u0011\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0086\u0002J4\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\u0017H\u0086\bJ\u001b\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u001dJ.\u0010\u001e\u001a\u00020\r2#\b\u0004\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00130\u0017H\u0086\bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006 "}, d2 = {"Landroidx/compose/runtime/collection/ScopeMap;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "map", "Landroidx/collection/MutableScatterMap;", "getMap", "()Landroidx/collection/MutableScatterMap;", "size", "", "getSize", "()I", "add", "", CacheEntity.KEY, Constants.PARAM_SCOPE, "(Ljava/lang/Object;Ljava/lang/Object;)V", "clear", "contains", "", "element", "forEachScopeOf", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeScope", "(Ljava/lang/Object;)V", "removeScopeIf", "predicate", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScopeMap<T> {
    public static final int $stable = 8;
    private final MutableScatterMap<Object, Object> map = ScatterMapKt.mutableScatterMapOf();

    public final MutableScatterMap<Object, Object> getMap() {
        return this.map;
    }

    public final int getSize() {
        return this.map.get_size();
    }

    public final void add(Object key, T scope) {
        MutableScatterMap<Object, Object> mutableScatterMap = this.map;
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(key);
        boolean z = iFindInsertIndex < 0;
        Object obj = z ? null : mutableScatterMap.values[iFindInsertIndex];
        if (obj != null) {
            if (obj instanceof MutableScatterSet) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.add$lambda$0>");
                ((MutableScatterSet) obj).add(scope);
            } else if (obj != scope) {
                MutableScatterSet mutableScatterSet = new MutableScatterSet(0, 1, null);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.add$lambda$0");
                mutableScatterSet.add(obj);
                mutableScatterSet.add(scope);
                scope = (T) mutableScatterSet;
            }
            scope = (T) obj;
        }
        if (z) {
            int i = ~iFindInsertIndex;
            mutableScatterMap.keys[i] = key;
            mutableScatterMap.values[i] = scope;
            return;
        }
        mutableScatterMap.values[iFindInsertIndex] = scope;
    }

    public final boolean contains(Object element) {
        return this.map.containsKey(element);
    }

    public final void forEachScopeOf(Object key, Function1<? super T, Unit> block) {
        Object obj = getMap().get(key);
        if (obj == null) {
            return;
        }
        if (obj instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            block.invoke(objArr[(i << 3) + i3]);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        } else {
            block.invoke(obj);
        }
    }

    public final void clear() {
        this.map.clear();
    }

    public final boolean remove(Object key, T scope) {
        Object obj = this.map.get(key);
        if (obj == null) {
            return false;
        }
        if (obj instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            boolean zRemove = mutableScatterSet.remove(scope);
            if (zRemove && mutableScatterSet.isEmpty()) {
                this.map.remove(key);
            }
            return zRemove;
        }
        if (!Intrinsics.areEqual(obj, scope)) {
            return false;
        }
        this.map.remove(key);
        return true;
    }

    public final void removeScopeIf(Function1<? super T, Boolean> predicate) {
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        int i3;
        int i4;
        long j;
        boolean zBooleanValue;
        int i5;
        MutableScatterMap<Object, Object> map = getMap();
        long[] jArr3 = map.metadata;
        int length = jArr3.length - 2;
        if (length < 0) {
            return;
        }
        int i6 = 0;
        while (true) {
            long j2 = jArr3[i6];
            char c = 7;
            long j3 = -9187201950435737472L;
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i7 = 8 - ((~(i6 - length)) >>> 31);
                int i8 = 0;
                while (i8 < i7) {
                    if ((j2 & 255) < 128) {
                        int i9 = (i6 << 3) + i8;
                        Object obj = map.keys[i9];
                        Object obj2 = map.values[i9];
                        if (obj2 instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1>");
                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                            Object[] objArr = mutableScatterSet.elements;
                            long[] jArr4 = mutableScatterSet.metadata;
                            int length2 = jArr4.length - 2;
                            jArr2 = jArr3;
                            i2 = length;
                            if (length2 >= 0) {
                                int i10 = 0;
                                while (true) {
                                    long j4 = jArr4[i10];
                                    i4 = i7;
                                    long[] jArr5 = jArr4;
                                    j = -9187201950435737472L;
                                    if ((((~j4) << c) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                        int i12 = 0;
                                        while (i12 < i11) {
                                            if ((j4 & 255) < 128) {
                                                int i13 = (i10 << 3) + i12;
                                                i5 = i6;
                                                if (predicate.invoke(objArr[i13]).booleanValue()) {
                                                    mutableScatterSet.removeElementAt(i13);
                                                }
                                            } else {
                                                i5 = i6;
                                            }
                                            j4 >>= 8;
                                            i12++;
                                            i6 = i5;
                                        }
                                        i3 = i6;
                                        if (i11 != 8) {
                                            break;
                                        }
                                    } else {
                                        i3 = i6;
                                    }
                                    if (i10 == length2) {
                                        break;
                                    }
                                    i10++;
                                    i7 = i4;
                                    jArr4 = jArr5;
                                    i6 = i3;
                                    c = 7;
                                }
                            } else {
                                i3 = i6;
                                i4 = i7;
                                j = -9187201950435737472L;
                            }
                            zBooleanValue = mutableScatterSet.isEmpty();
                        } else {
                            jArr2 = jArr3;
                            i2 = length;
                            i3 = i6;
                            i4 = i7;
                            j = j3;
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1");
                            zBooleanValue = predicate.invoke(obj2).booleanValue();
                        }
                        if (zBooleanValue) {
                            map.removeValueAt(i9);
                        }
                    } else {
                        jArr2 = jArr3;
                        i2 = length;
                        i3 = i6;
                        i4 = i7;
                        j = j3;
                    }
                    j2 >>= 8;
                    i8++;
                    j3 = j;
                    jArr3 = jArr2;
                    length = i2;
                    i7 = i4;
                    i6 = i3;
                    c = 7;
                }
                jArr = jArr3;
                int i14 = length;
                int i15 = i6;
                if (i7 != 8) {
                    return;
                }
                length = i14;
                i = i15;
            } else {
                jArr = jArr3;
                i = i6;
            }
            if (i == length) {
                return;
            }
            i6 = i + 1;
            jArr3 = jArr;
        }
    }

    public final void removeScope(T scope) {
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        int i3;
        int i4;
        long j;
        int i5;
        MutableScatterMap<Object, Object> map = getMap();
        long[] jArr3 = map.metadata;
        int length = jArr3.length - 2;
        if (length < 0) {
            return;
        }
        int i6 = 0;
        while (true) {
            long j2 = jArr3[i6];
            char c = 7;
            long j3 = -9187201950435737472L;
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i7 = 8 - ((~(i6 - length)) >>> 31);
                int i8 = 0;
                while (i8 < i7) {
                    if ((j2 & 255) < 128) {
                        int i9 = (i6 << 3) + i8;
                        Object obj = map.keys[i9];
                        Object obj2 = map.values[i9];
                        boolean zIsEmpty = true;
                        if (obj2 instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1>");
                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                            Object[] objArr = mutableScatterSet.elements;
                            long[] jArr4 = mutableScatterSet.metadata;
                            int length2 = jArr4.length - 2;
                            jArr2 = jArr3;
                            i2 = length;
                            if (length2 >= 0) {
                                int i10 = 0;
                                while (true) {
                                    long j4 = jArr4[i10];
                                    i4 = i7;
                                    long[] jArr5 = jArr4;
                                    j = -9187201950435737472L;
                                    if ((((~j4) << c) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                        int i12 = 0;
                                        while (i12 < i11) {
                                            if ((j4 & 255) < 128) {
                                                int i13 = (i10 << 3) + i12;
                                                i5 = i6;
                                                if (objArr[i13] == scope) {
                                                    mutableScatterSet.removeElementAt(i13);
                                                }
                                            } else {
                                                i5 = i6;
                                            }
                                            j4 >>= 8;
                                            i12++;
                                            i6 = i5;
                                        }
                                        i3 = i6;
                                        if (i11 != 8) {
                                            break;
                                        }
                                    } else {
                                        i3 = i6;
                                    }
                                    if (i10 == length2) {
                                        break;
                                    }
                                    i10++;
                                    i7 = i4;
                                    jArr4 = jArr5;
                                    i6 = i3;
                                    c = 7;
                                }
                            } else {
                                i3 = i6;
                                i4 = i7;
                                j = -9187201950435737472L;
                            }
                            zIsEmpty = mutableScatterSet.isEmpty();
                        } else {
                            jArr2 = jArr3;
                            i2 = length;
                            i3 = i6;
                            i4 = i7;
                            j = j3;
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1");
                            if (obj2 != scope) {
                                zIsEmpty = false;
                            }
                        }
                        if (zIsEmpty) {
                            map.removeValueAt(i9);
                        }
                    } else {
                        jArr2 = jArr3;
                        i2 = length;
                        i3 = i6;
                        i4 = i7;
                        j = j3;
                    }
                    j2 >>= 8;
                    i8++;
                    j3 = j;
                    jArr3 = jArr2;
                    length = i2;
                    i7 = i4;
                    i6 = i3;
                    c = 7;
                }
                jArr = jArr3;
                int i14 = length;
                int i15 = i6;
                if (i7 != 8) {
                    return;
                }
                length = i14;
                i = i15;
            } else {
                jArr = jArr3;
                i = i6;
            }
            if (i == length) {
                return;
            }
            i6 = i + 1;
            jArr3 = jArr;
        }
    }
}
