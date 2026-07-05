package com.taptap.sdk.db.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ListUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jo\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00042!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u00072\u0006\u0010\f\u001a\u0002H\u00052!\u0010\r\u001a\u001d\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00050\u0007¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/db/utils/ListUtils;", "", "()V", "updateOrInsert", "", "E", "selector", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "", "newElement", "reducer", "origin", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ListUtils {
    public static final ListUtils INSTANCE = new ListUtils();

    private ListUtils() {
    }

    public final <E> List<E> updateOrInsert(List<? extends E> list, Function1<? super E, Boolean> selector, E e, Function1<? super E, ? extends E> reducer) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Intrinsics.checkNotNullParameter(reducer, "reducer");
        Iterator<? extends E> it = list.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (selector.invoke(it.next()).booleanValue()) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            List<E> mutableList = CollectionsKt.toMutableList((Collection) list);
            mutableList.set(i, reducer.invoke(mutableList.get(i)));
            return mutableList;
        }
        return CollectionsKt.plus((Collection<? extends E>) list, e);
    }
}
