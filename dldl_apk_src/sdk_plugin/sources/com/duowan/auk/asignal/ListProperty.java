package com.duowan.auk.asignal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ListProperty<E> extends Property<List<E>> {
    public ListProperty() {
        this(new ArrayList());
    }

    public ListProperty(String str) {
        this(new ArrayList(), str);
    }

    public ListProperty(List<E> list) {
        super(list);
    }

    public ListProperty(List<E> list, String str) {
        super(list, str);
    }
}
