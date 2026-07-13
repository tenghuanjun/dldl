package com.transitionseverywhere.utils;

import android.util.Property;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class IntProperty<T> extends Property<T, Integer> {
    public abstract void setValue(T t, int i);

    public IntProperty() {
        super(Integer.class, null);
    }

    @Override // android.util.Property
    public final void set(T t, Integer num) {
        setValue(t, num.intValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.Property
    public Integer get(T t) {
        return 0;
    }

    public Property<T, Integer> optimize() {
        return new android.util.IntProperty<T>(null) { // from class: com.transitionseverywhere.utils.IntProperty.1
            @Override // android.util.IntProperty
            public void setValue(T t, int i) {
                IntProperty.this.setValue(t, i);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.util.Property
            public Integer get(T t) {
                return IntProperty.this.get((Object) t);
            }
        };
    }
}
