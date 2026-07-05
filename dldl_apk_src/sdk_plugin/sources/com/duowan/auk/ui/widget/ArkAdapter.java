package com.duowan.auk.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import com.huya.mtp.utils.FP;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class ArkAdapter<T> extends BaseAdapter implements Filterable {
    private int[] mDropDownResources;
    private Filter mFilter;
    private LayoutInflater mInflater;
    private final Object mLock;
    private boolean mNotifyOnChange;
    private List<T> mObjects;
    private ArrayList<T> mOriginalValues;
    private int[] mResources;

    protected abstract void bindView(View view, T t, int i);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ArkAdapter(Context context, int i, List<T> list) {
        this(context, list, i);
    }

    public ArkAdapter(Context context, int... iArr) {
        this(context, new ArrayList(), iArr);
    }

    public ArkAdapter(Context context, List<T> list, int... iArr) {
        this.mLock = new Object();
        this.mNotifyOnChange = true;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mDropDownResources = iArr;
        this.mResources = iArr;
        this.mObjects = list;
    }

    public void setmObjects(List<T> list) {
        this.mObjects = list;
    }

    public void add(T t) {
        synchronized (this.mLock) {
            List<T> availableList = getAvailableList();
            if (availableList != null) {
                availableList.add(t);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void addAll(Collection<? extends T> collection) {
        synchronized (this.mLock) {
            addAllIfNotEmpty(collection);
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void addAll(T... tArr) {
        synchronized (this.mLock) {
            addAllIfNotEmpty(tArr);
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void insert(T t, int i) {
        synchronized (this.mLock) {
            List<T> availableList = getAvailableList();
            if (availableList != null) {
                if (i < 0) {
                    i = 0;
                }
                if (i > availableList.size()) {
                    i = availableList.size();
                }
                availableList.add(i, t);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void replace(Collection<? extends T> collection) {
        synchronized (this.mLock) {
            clearAllData();
            addAllIfNotEmpty(collection);
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void remove(T t) {
        synchronized (this.mLock) {
            List<T> availableList = getAvailableList();
            if (availableList != null) {
                availableList.remove(t);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    private List<T> getAvailableList() {
        ArrayList<T> arrayList = this.mOriginalValues;
        return arrayList != null ? arrayList : this.mObjects;
    }

    @Deprecated
    public List<T> getDataSource() {
        return getAvailableList();
    }

    public List<T> getDataSourceCopy() {
        List<T> availableList = getAvailableList();
        if (availableList != null) {
            return new ArrayList(availableList);
        }
        return null;
    }

    public void clear() {
        synchronized (this.mLock) {
            clearAllData();
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void sort(Comparator<? super T> comparator) {
        synchronized (this.mLock) {
            List<T> availableList = getAvailableList();
            if (availableList != null) {
                Collections.sort(availableList, comparator);
            }
        }
        if (this.mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    private void clearAllData() {
        List<T> availableList = getAvailableList();
        if (availableList != null) {
            availableList.clear();
        }
    }

    private void addAllIfNotEmpty(Collection<? extends T> collection) {
        List<T> availableList;
        if (FP.empty(collection) || (availableList = getAvailableList()) == null) {
            return;
        }
        availableList.addAll(collection);
    }

    private void addAllIfNotEmpty(T... tArr) {
        List<T> availableList;
        if (FP.empty(tArr) || (availableList = getAvailableList()) == null) {
            return;
        }
        Collections.addAll(availableList, tArr);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mNotifyOnChange = true;
    }

    public void setNotifyOnChange(boolean z) {
        this.mNotifyOnChange = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.mObjects;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        List<T> list = this.mObjects;
        if (list == null || i >= list.size()) {
            return null;
        }
        return this.mObjects.get(i);
    }

    public int getPosition(T t) {
        List<T> list = this.mObjects;
        if (list != null) {
            return list.indexOf(t);
        }
        return -1;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return createViewFromResource(i, view, viewGroup, this.mResources[getItemViewType(i)]);
    }

    protected boolean conformFilter(T t, CharSequence charSequence) {
        return t.toString().toLowerCase(Locale.CHINA).contains(charSequence.toString().toLowerCase(Locale.CHINA));
    }

    private View createViewFromResource(int i, View view, ViewGroup viewGroup, int i2) {
        if (view == null) {
            view = this.mInflater.inflate(i2, viewGroup, false);
        }
        bindView(view, getItem(i), i);
        return view;
    }

    public void setDropDownViewResource(int... iArr) {
        this.mDropDownResources = iArr;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return createViewFromResource(i, view, viewGroup, this.mDropDownResources[getItemViewType(i)]);
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArkAdapterFilter();
        }
        return this.mFilter;
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return this.mResources.length;
    }

    private class ArkAdapterFilter extends Filter {
        private ArkAdapterFilter() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            ArrayList arrayList;
            ArrayList arrayList2;
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (ArkAdapter.this.mOriginalValues == null) {
                synchronized (ArkAdapter.this.mLock) {
                    ArkAdapter.this.mOriginalValues = new ArrayList(ArkAdapter.this.mObjects);
                }
            }
            if (charSequence == null || charSequence.length() == 0) {
                synchronized (ArkAdapter.this.mLock) {
                    arrayList = new ArrayList(ArkAdapter.this.mOriginalValues);
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
            } else {
                synchronized (ArkAdapter.this.mLock) {
                    arrayList2 = new ArrayList(ArkAdapter.this.mOriginalValues);
                }
                int size = arrayList2.size();
                ArrayList arrayList3 = new ArrayList();
                for (int i = 0; i < size; i++) {
                    Object obj = arrayList2.get(i);
                    if (ArkAdapter.this.conformFilter(obj, charSequence)) {
                        arrayList3.add(obj);
                    }
                }
                filterResults.values = arrayList3;
                filterResults.count = arrayList3.size();
            }
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            ArkAdapter.this.mObjects = (List) filterResults.values;
            if (filterResults.count > 0) {
                ArkAdapter.this.notifyDataSetChanged();
            } else {
                ArkAdapter.this.notifyDataSetInvalidated();
            }
        }
    }
}
