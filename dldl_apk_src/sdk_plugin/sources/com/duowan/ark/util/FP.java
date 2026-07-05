package com.duowan.ark.util;

import android.util.Pair;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FP {

    public interface BinaryFunc<R, A, B> {
        R apply(A a, B b);
    }

    public interface UnaryFunc<R, A> {
        R apply(A a);
    }

    public static int ord(boolean z) {
        return z ? 1 : 0;
    }

    public static String ref(String str) {
        return str == null ? "" : str;
    }

    public static abstract class Pred<A> implements UnaryFunc<Boolean, A> {
        public abstract boolean pred(A a);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.duowan.ark.util.FP.UnaryFunc
        public Boolean apply(A a) {
            return Boolean.valueOf(pred(a));
        }
    }

    public static abstract class Eq<A> implements BinaryFunc<Boolean, A, A> {
        public abstract boolean eq(A a, A a2);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.duowan.ark.util.FP.BinaryFunc
        public Boolean apply(A a, A a2) {
            return Boolean.valueOf(eq(a, a2));
        }
    }

    public static class Tuple<A, B, C> {
        public A a;
        public B b;
        public C c;

        public Tuple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static <A, B, C> Tuple<A, B, C> makeTuple(A a, B b, C c) {
        return new Tuple<>(a, b, c);
    }

    public static <E> Pred<E> negate(final Pred<E> pred) {
        return new Pred<E>() { // from class: com.duowan.ark.util.FP.1
            @Override // com.duowan.ark.util.FP.Pred
            public boolean pred(E e) {
                return !pred.pred(e);
            }
        };
    }

    public static int limit(int i, int i2, int i3) {
        return Math.min(Math.max(i2, i), i3);
    }

    public static <E> E find(Pred<E> pred, List<E> list) {
        if (empty(list)) {
            return null;
        }
        for (E e : list) {
            if (pred.pred(e)) {
                return e;
            }
        }
        return null;
    }

    public static <E> E find(final E e, List<E> list) {
        return (E) find((Pred) new Pred<E>() { // from class: com.duowan.ark.util.FP.2
            @Override // com.duowan.ark.util.FP.Pred
            public boolean pred(E e2) {
                return e2.equals(e);
            }
        }, (List) list);
    }

    public static <E> int findIndex(Pred<E> pred, List<E> list) {
        int length = length(list);
        int i = 0;
        while (i < length && !pred.pred(list.get(i))) {
            i++;
        }
        if (i == length) {
            return -1;
        }
        return i;
    }

    public static <K, V> V lookup(K k, List<Pair<K, V>> list) {
        if (empty(list)) {
            return null;
        }
        for (Pair<K, V> pair : list) {
            if (k == pair.first) {
                return (V) pair.second;
            }
        }
        return null;
    }

    public static <E> E lookup(int i, SparseArray<E> sparseArray) {
        if (empty((SparseArray<?>) sparseArray)) {
            return null;
        }
        return sparseArray.get(i);
    }

    public static <E> List<E> nubBy(final Eq<E> eq, List<E> list) {
        ArrayList arrayList = new ArrayList();
        if (!empty(list)) {
            for (final E e : list) {
                if (find((Pred) new Pred<E>() { // from class: com.duowan.ark.util.FP.3
                    @Override // com.duowan.ark.util.FP.Pred
                    public boolean pred(E e2) {
                        return eq.eq(e, e2);
                    }
                }, (List) arrayList) == null) {
                    arrayList.add(e);
                }
            }
        }
        return arrayList;
    }

    public static <E> List<E> nub(List<E> list) {
        return nubBy(new Eq<E>() { // from class: com.duowan.ark.util.FP.4
            @Override // com.duowan.ark.util.FP.Eq
            public boolean eq(E e, E e2) {
                return e2.equals(e);
            }
        }, list);
    }

    public static boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean empty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static boolean empty(SparseArray<?> sparseArray) {
        return sparseArray == null || sparseArray.size() == 0;
    }

    public static boolean empty(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static boolean empty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean empty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static int size(Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    public static int size(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return charSequence.length();
    }

    public static <T> int size(T[] tArr) {
        if (tArr == null) {
            return 0;
        }
        return tArr.length;
    }

    public static int size(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public static int length(Collection<?> collection) {
        return size(collection);
    }

    public static int length(CharSequence charSequence) {
        return size(charSequence);
    }

    public static <T> int length(T[] tArr) {
        return size(tArr);
    }

    public static int length(int[] iArr) {
        return size(iArr);
    }

    public static <E> List<E> add(List<E> list, E e) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(e);
        return list;
    }

    public static <E> Pair<List<E>, List<E>> span(Pred<E> pred, List<E> list) {
        return Pair.create(takeWhile(pred, list), dropWhile(pred, list));
    }

    public static <E> List<E> take(int i, List<E> list) {
        ArrayList arrayList = new ArrayList();
        if (!empty(list) && i > 0) {
            arrayList.addAll(list.subList(0, Math.min(i, length(list))));
        }
        return arrayList;
    }

    public static String take(int i, String str) {
        return str.substring(0, limit(i, 0, length(str)));
    }

    public static <K, V> Map<K, V> take(int i, Map<K, V> map) {
        HashMap map2 = new HashMap();
        for (K k : map.keySet()) {
            int i2 = i - 1;
            if (i > 0) {
                map2.put(k, map.get(k));
            }
            i = i2;
        }
        return map2;
    }

    public static <E> List<E> takeWhile(Pred<E> pred, List<E> list) {
        int length = length(list);
        int i = 0;
        while (i < length && pred.pred(list.get(i))) {
            i++;
        }
        return take(i, list);
    }

    public static <E> List<E> drop(int i, List<E> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && i <= length(list)) {
            arrayList.addAll(list.subList(Math.max(0, i), length(list)));
        }
        return arrayList;
    }

    public static String drop(int i, String str) {
        return (str == null || i > length(str)) ? "" : str.substring(Math.max(0, i));
    }

    public static <E> List<E> dropWhile(Pred<E> pred, List<E> list) {
        int length = length(list);
        for (int i = 0; i < length && pred.pred(list.get(i)); i++) {
        }
        return drop(length, list);
    }

    public static <E> E first(List<E> list) {
        if (empty(list)) {
            return null;
        }
        return list.get(0);
    }

    public static <E> E last(List<E> list) {
        if (empty(list)) {
            return null;
        }
        return list.get(lastIndex(list));
    }

    public static int lastIndex(List<?> list) {
        if (empty(list)) {
            return -1;
        }
        return list.size() - 1;
    }

    public static <E> E first(Collection<E> collection) {
        if (empty((Collection<?>) collection)) {
            return null;
        }
        return collection.iterator().next();
    }

    public static <E> List<E> toList(Collection<? extends E> collection) {
        return empty(collection) ? new ArrayList() : new ArrayList(collection);
    }

    public static <T> List<T> toList(T t) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        return arrayList;
    }

    public static <T> List<T> toList(T[] tArr) {
        ArrayList arrayList = new ArrayList();
        if (!empty(tArr)) {
            for (T t : tArr) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static List<Integer> toList(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (!empty(iArr)) {
            for (int i : iArr) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    public static <E> List<Pair<Integer, E>> toList(SparseArray<E> sparseArray) {
        ArrayList arrayList = new ArrayList();
        if (!empty((SparseArray<?>) sparseArray)) {
            for (int i = 0; i < sparseArray.size(); i++) {
                arrayList.add(Pair.create(Integer.valueOf(sparseArray.keyAt(i)), sparseArray.valueAt(i)));
            }
        }
        return arrayList;
    }

    public static int[] toArray(Collection<Integer> collection) {
        int[] iArr = new int[length(collection)];
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    public static <E> List<E> ref(List<E> list) {
        return list == null ? new ArrayList() : list;
    }

    public static <A, B> List<Pair<A, B>> zip(List<A> list, List<B> list2) {
        ArrayList arrayList = new ArrayList();
        if (!empty(list) && !empty(list2)) {
            Iterator<A> it = list.iterator();
            Iterator<B> it2 = list2.iterator();
            while (it.hasNext() && it2.hasNext()) {
                arrayList.add(Pair.create(it.next(), it2.next()));
            }
        }
        return arrayList;
    }

    public static boolean eq(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static <T> void convert(T[] tArr, Object[] objArr) {
        for (int i = 0; i < objArr.length; i++) {
            tArr[i] = objArr;
        }
    }

    public static <T> List<T> concat(List<T> list, List<T> list2) {
        List<T> listRef = ref(list);
        listRef.addAll(ref(list2));
        return listRef;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> unionBy(Eq<T> eq, List<T> list, List<T> list2) {
        List<T> listRef = ref(list2);
        if (empty(list)) {
            return listRef;
        }
        for (T t : listRef) {
            boolean z = false;
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (eq.eq(it.next(), t)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                list.add(t);
            }
        }
        return list;
    }

    public static <T> List<T> union(List<T> list, List<T> list2) {
        return unionBy(new Eq<T>() { // from class: com.duowan.ark.util.FP.5
            @Override // com.duowan.ark.util.FP.Eq
            public boolean eq(T t, T t2) {
                return FP.eq(t, t2);
            }
        }, list, list2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <A, B> List<B> map(UnaryFunc<B, A> unaryFunc, List<A> list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ref(list).iterator();
        while (it.hasNext()) {
            arrayList.add(unaryFunc.apply(it.next()));
        }
        return arrayList;
    }

    public static <E> List<E> filter(Pred<E> pred, List<E> list) {
        ArrayList arrayList = new ArrayList();
        for (E e : list) {
            if (pred.pred(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> List<E> insert(Comparator<E> comparator, E e, List<E> list) {
        int iBinarySearch = Collections.binarySearch(list, e, comparator);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        list.add((-iBinarySearch) - 1, e);
        return list;
    }

    public static <E> List<E> sort(Comparator<E> comparator, List<E> list) {
        List<E> listRef = ref(list);
        Collections.sort(listRef, comparator);
        return listRef;
    }

    public static int sum(Integer[] numArr) {
        int iIntValue = 0;
        for (Integer num : numArr) {
            iIntValue += num.intValue();
        }
        return iIntValue;
    }

    public static long sum(Long[] lArr) {
        long jLongValue = 0;
        for (Long l : lArr) {
            jLongValue += l.longValue();
        }
        return jLongValue;
    }

    public static int sum(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            iIntValue += it.next().intValue();
        }
        return iIntValue;
    }

    public static long sum(List<Long> list, Long l) {
        Iterator<Long> it = list.iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            jLongValue += it.next().longValue();
        }
        return jLongValue;
    }

    public static class M {
        public static <K, V> List<Pair<K, V>> toList(Map<K, V> map) {
            ArrayList arrayList = new ArrayList();
            if (!FP.empty((Map<?, ?>) map)) {
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    arrayList.add(Pair.create(entry.getKey(), entry.getValue()));
                }
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static <K extends Comparable<?>, V> Map<K, V> fromList(List<Pair<K, V>> list) {
            TreeMap treeMap = new TreeMap();
            if (!FP.empty(list)) {
                for (Pair<K, V> pair : list) {
                    treeMap.put(pair.first, pair.second);
                }
            }
            return treeMap;
        }

        public static <V> Map<Integer, V> fromList(SparseArray<V> sparseArray) {
            TreeMap treeMap = new TreeMap();
            if (!FP.empty((SparseArray<?>) sparseArray)) {
                for (int i = 0; i < sparseArray.size(); i++) {
                    treeMap.put(Integer.valueOf(sparseArray.keyAt(i)), sparseArray.valueAt(i));
                }
            }
            return treeMap;
        }
    }
}
