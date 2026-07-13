package com.transitionseverywhere.utils;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FloatProperty<T> extends android.util.FloatProperty<T> {
    public FloatProperty() {
        super(null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.FloatProperty, android.util.Property
    public Float get(T t) {
        return Float.valueOf(0.0f);
    }
}
