package com.bytedance.bdtracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedList<j3> f227a = new LinkedList<>();
    public final LinkedList<String> b = new LinkedList<>();

    public int a(ArrayList<j3> arrayList) {
        int size;
        synchronized (this.f227a) {
            size = this.f227a.size();
            arrayList.addAll(this.f227a);
            this.f227a.clear();
        }
        return size;
    }

    public void a(j3 j3Var) {
        synchronized (this.f227a) {
            if (this.f227a.size() > 300) {
                this.f227a.poll();
            }
            this.f227a.add(j3Var);
        }
    }

    public void a(String[] strArr) {
        synchronized (this.b) {
            if (this.b.size() > 300) {
                this.b.poll();
            }
            this.b.addAll(Arrays.asList(strArr));
        }
    }
}
