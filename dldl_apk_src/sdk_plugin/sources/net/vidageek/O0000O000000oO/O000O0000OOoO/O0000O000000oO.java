package net.vidageek.O0000O000000oO.O000O0000OOoO;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class O0000O000000oO<T> implements net.vidageek.O0000O000000oO.O000O0000OOoO.O0000O000000oO.O0000O000000oO<T> {
    private final List<T> O0000O000000oO;

    public O0000O000000oO(List<T> list) {
        this.O0000O000000oO = Collections.unmodifiableList(list);
    }

    @Override // java.util.List
    public void add(int i, T t) {
        this.O0000O000000oO.add(i, t);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T t) {
        return this.O0000O000000oO.add(t);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        return this.O0000O000000oO.addAll(i, collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        return this.O0000O000000oO.addAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.O0000O000000oO.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.O0000O000000oO.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.O0000O000000oO.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.O0000O000000oO.equals(obj);
    }

    @Override // java.util.List
    public T get(int i) {
        return this.O0000O000000oO.get(i);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.O0000O000000oO.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.O0000O000000oO.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.O0000O000000oO.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return this.O0000O000000oO.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.O0000O000000oO.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return this.O0000O000000oO.listIterator();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i) {
        return this.O0000O000000oO.listIterator(i);
    }

    @Override // java.util.List
    public T remove(int i) {
        return this.O0000O000000oO.remove(i);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        return this.O0000O000000oO.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.O0000O000000oO.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.O0000O000000oO.retainAll(collection);
    }

    @Override // java.util.List
    public T set(int i, T t) {
        return this.O0000O000000oO.set(i, t);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.O0000O000000oO.size();
    }

    @Override // java.util.List
    public List<T> subList(int i, int i2) {
        return this.O0000O000000oO.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.O0000O000000oO.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        return (E[]) this.O0000O000000oO.toArray(eArr);
    }

    public String toString() {
        return this.O0000O000000oO.toString();
    }
}
