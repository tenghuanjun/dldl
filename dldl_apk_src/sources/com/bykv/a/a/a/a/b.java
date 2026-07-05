package com.bykv.a.a.a.a;

import android.util.SparseArray;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b {
    public static final ValueSet a = a(0).b();
    public static final Bridge b = new a();
    private final SparseArray<Object> c;
    private ValueSet d;

    private b(SparseArray<Object> sparseArray, ValueSet valueSet) {
        this.c = sparseArray;
        this.d = valueSet;
    }

    private b(SparseArray<Object> sparseArray) {
        this.c = sparseArray;
    }

    public static final b a() {
        return new b(new SparseArray());
    }

    public static final b a(int i) {
        return new b(new SparseArray(i));
    }

    public static final b a(ValueSet valueSet) {
        return new b(new SparseArray(), valueSet);
    }

    public b a(int i, Object obj) {
        this.c.put(i, obj);
        return this;
    }

    public b a(int i, String str) {
        this.c.put(i, str);
        return this;
    }

    public b a(int i, int i2) {
        this.c.put(i, Integer.valueOf(i2));
        return this;
    }

    public b a(int i, double d) {
        this.c.put(i, Double.valueOf(d));
        return this;
    }

    public b a(int i, boolean z) {
        this.c.put(i, Boolean.valueOf(z));
        return this;
    }

    public b a(int i, long j) {
        this.c.put(i, Long.valueOf(j));
        return this;
    }

    public b a(int i, float f) {
        this.c.put(i, Float.valueOf(f));
        return this;
    }

    public ValueSet b() {
        return new C0012b(this.c, this.d);
    }

    /* JADX INFO: renamed from: com.bykv.a.a.a.a.b$b, reason: collision with other inner class name */
    private static final class C0012b implements ValueSet {
        private final SparseArray<Object> a;
        private ValueSet b;
        private int c;

        private C0012b(SparseArray<Object> sparseArray, ValueSet valueSet) {
            this.c = -1;
            this.a = sparseArray;
            this.b = valueSet;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public <T> T[] arrayValue(int i, Class<T> cls) {
            Object obj = this.a.get(i);
            if (obj == null) {
                ValueSet valueSet = this.b;
                if (valueSet != null) {
                    return (T[]) valueSet.arrayValue(i, cls);
                }
                return null;
            }
            Class<?> cls2 = obj.getClass();
            if (cls2.isArray() && cls.isAssignableFrom(cls2.getComponentType())) {
                return (T[]) ((Object[]) obj);
            }
            return null;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public <T> T objectValue(int i, Class<T> cls) {
            Object obj = this.a.get(i);
            if (obj == null) {
                ValueSet valueSet = this.b;
                if (valueSet != null) {
                    return (T) valueSet.objectValue(i, cls);
                }
                return null;
            }
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            if (cls.isInstance(obj)) {
                return (T) obj;
            }
            return null;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public String stringValue(int i) {
            return stringValue(i, null);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public int intValue(int i) {
            return intValue(i, 0);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public boolean booleanValue(int i) {
            return booleanValue(i, false);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public long longValue(int i) {
            return longValue(i, 0L);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public float floatValue(int i) {
            return floatValue(i, 0.0f);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public double doubleValue(int i) {
            ValueSet valueSet;
            Object obj = this.a.get(i);
            if (obj == null && (valueSet = this.b) != null) {
                return valueSet.doubleValue(i);
            }
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            if (obj instanceof Double) {
                return ((Double) obj).doubleValue();
            }
            return 0.0d;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public String stringValue(int i, String str) {
            ValueSet valueSet;
            Object obj = this.a.get(i);
            if (obj != null || (valueSet = this.b) == null) {
                return (obj instanceof ValueSet.ValueGetter ? ((ValueSet.ValueGetter) obj).get() : obj) instanceof String ? obj.toString() : str;
            }
            return valueSet.stringValue(i, str);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public int intValue(int i, int i2) {
            ValueSet valueSet;
            Object obj = this.a.get(i);
            if (obj == null && (valueSet = this.b) != null) {
                return valueSet.intValue(i, i2);
            }
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Integer ? ((Integer) obj).intValue() : i2;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public boolean booleanValue(int i, boolean z) {
            ValueSet valueSet;
            Object obj = this.a.get(i);
            if (obj == null && (valueSet = this.b) != null) {
                return valueSet.booleanValue(i, z);
            }
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public long longValue(int i, long j) {
            ValueSet valueSet;
            Object obj = this.a.get(i);
            if (obj == null && (valueSet = this.b) != null) {
                return valueSet.longValue(i, j);
            }
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Long ? ((Long) obj).longValue() : j;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public float floatValue(int i, float f) {
            ValueSet valueSet;
            Object obj = this.a.get(i);
            if (obj == null && (valueSet = this.b) != null) {
                return valueSet.floatValue(i, f);
            }
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Float ? ((Float) obj).floatValue() : f;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public boolean containsKey(int i) {
            ValueSet valueSet;
            int iIndexOfKey = this.a.indexOfKey(i);
            if (iIndexOfKey >= 0 || (valueSet = this.b) == null) {
                return iIndexOfKey >= 0;
            }
            return valueSet.containsKey(i);
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public Set<Integer> keys() {
            int size = this.a.size();
            HashSet hashSet = new HashSet();
            for (int i = 0; i < size; i++) {
                hashSet.add(Integer.valueOf(this.a.keyAt(i)));
            }
            ValueSet valueSet = this.b;
            if (valueSet != null) {
                hashSet.addAll(valueSet.keys());
            }
            this.c = hashSet.size();
            return hashSet;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public boolean isEmpty() {
            return size() <= 0;
        }

        @Override // com.bykv.vk.openvk.api.proto.ValueSet
        public int size() {
            if (this.c < 0) {
                keys();
            }
            return this.c;
        }
    }

    private static final class a implements Bridge {
        private a() {
        }

        @Override // com.bykv.vk.openvk.api.proto.Bridge
        public ValueSet values() {
            return b.a;
        }

        @Override // com.bykv.vk.openvk.api.proto.Caller
        public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
            if (cls == Boolean.class) {
                return (T) Boolean.FALSE;
            }
            if (cls == Integer.TYPE || cls == Integer.class) {
                return (T) new Integer(0);
            }
            if (cls == Long.TYPE || cls == Long.class) {
                return (T) new Long(0L);
            }
            if (cls == Double.TYPE || cls == Double.class) {
                return (T) new Double(0.0d);
            }
            if (cls == Float.TYPE || cls == Float.class) {
                return (T) new Float(0.0f);
            }
            return null;
        }
    }
}
