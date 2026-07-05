package com.duowan.auk.asignal;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SetProperty<E> extends Property<Set<E>> {
    public SetProperty() {
        this(Collections.emptySet());
    }

    public SetProperty(String str) {
        this(Collections.emptySet(), str);
    }

    public SetProperty(Set<E> set) {
        super(set);
    }

    public SetProperty(Set<E> set, String str) {
        super(set, str);
    }
}
