package com.sq.tools.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class EventCollection implements Serializable {
    private static final long serialVersionUID = 20210616021L;
    final List<FiledTools> eventsList = new ArrayList();

    EventCollection() {
    }

    synchronized void add(FiledTools filedTools) {
        this.eventsList.add(filedTools);
    }

    synchronized void add(List<FiledTools> list) {
        if (!list.isEmpty()) {
            this.eventsList.addAll(list);
        }
    }

    synchronized void add(EventCollection eventCollection) {
        if (!eventCollection.eventsList.isEmpty()) {
            this.eventsList.addAll(eventCollection.eventsList);
        }
    }

    synchronized void clear() {
        this.eventsList.clear();
    }

    public synchronized FiledTools get(int i) {
        return this.eventsList.get(i);
    }

    public synchronized int size() {
        return this.eventsList.size();
    }

    public synchronized boolean isEmpty() {
        return this.eventsList.size() == 0;
    }

    public synchronized JSONArray toJsonArray() {
        JSONArray jSONArray;
        jSONArray = new JSONArray();
        for (int i = 0; i < this.eventsList.size(); i++) {
            jSONArray.put(this.eventsList.get(i).toJsonObject());
        }
        return jSONArray;
    }
}
