package com.bytedance.framwork.core.sdklib.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class ListUtils {

    public interface ComparableDiffType<A, B> {
        boolean equals(A a2, B b);
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static String listToString(Collection collection, String str) {
        if (collection == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : collection) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(String.valueOf(obj));
        }
        return sb.toString();
    }

    public static <L, O> boolean removeAll(Collection<L> collection, O o, ComparableDiffType<? super L, O> comparableDiffType) {
        Iterator<L> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (comparableDiffType.equals(it.next(), o)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static String[] toArray(Set<String> set) {
        String[] strArr = new String[set.size()];
        Iterator<String> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next();
            i++;
        }
        return strArr;
    }
}
