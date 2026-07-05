package master.flame.danmaku.danmaku.model.android;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Danmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Danmakus implements IDanmakus {
    private BaseDanmaku endItem;
    private BaseDanmaku endSubItem;
    public Collection<BaseDanmaku> items;
    private IDanmakus.BaseComparator mComparator;
    private boolean mDuplicateMergingEnabled;
    private Object mLockObject;
    private volatile AtomicInteger mSize;
    private int mSortType;
    private BaseDanmaku startItem;
    private BaseDanmaku startSubItem;
    private Danmakus subItems;

    public Danmakus() {
        this(0, false);
    }

    public Danmakus(int i) {
        this(i, false);
    }

    public Danmakus(int i, boolean z) {
        this(i, z, null);
    }

    public Danmakus(int i, boolean z, IDanmakus.BaseComparator baseComparator) {
        this.mSize = new AtomicInteger(0);
        this.mSortType = 0;
        this.mLockObject = new Object();
        if (i == 0) {
            if (baseComparator == null) {
                baseComparator = new IDanmakus.TimeComparator(z);
            }
        } else if (i == 1) {
            baseComparator = new IDanmakus.YPosComparator(z);
        } else {
            baseComparator = i == 2 ? new IDanmakus.YPosDescComparator(z) : null;
        }
        if (i == 4) {
            this.items = new LinkedList();
        } else {
            this.mDuplicateMergingEnabled = z;
            baseComparator.setDuplicateMergingEnabled(z);
            this.items = new TreeSet(baseComparator);
            this.mComparator = baseComparator;
        }
        this.mSortType = i;
        this.mSize.set(0);
    }

    public Danmakus(Collection<BaseDanmaku> collection) {
        this.mSize = new AtomicInteger(0);
        this.mSortType = 0;
        this.mLockObject = new Object();
        setItems(collection);
    }

    public Danmakus(boolean z) {
        this(0, z);
    }

    public void setItems(Collection<BaseDanmaku> collection) {
        if (this.mDuplicateMergingEnabled && this.mSortType != 4) {
            synchronized (this.mLockObject) {
                this.items.clear();
                this.items.addAll(collection);
                collection = this.items;
            }
        } else {
            this.items = collection;
        }
        if (collection instanceof List) {
            this.mSortType = 4;
        }
        this.mSize.set(collection == null ? 0 : collection.size());
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean addItem(BaseDanmaku baseDanmaku) {
        synchronized (this.mLockObject) {
            if (this.items != null) {
                try {
                    if (this.items.add(baseDanmaku)) {
                        this.mSize.incrementAndGet();
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean removeItem(BaseDanmaku baseDanmaku) {
        if (baseDanmaku == null) {
            return false;
        }
        if (baseDanmaku.isOutside()) {
            baseDanmaku.setVisibility(false);
        }
        synchronized (this.mLockObject) {
            if (!this.items.remove(baseDanmaku)) {
                return false;
            }
            this.mSize.decrementAndGet();
            return true;
        }
    }

    private Collection<BaseDanmaku> subset(long j, long j2) {
        Collection<BaseDanmaku> collection;
        if (this.mSortType == 4 || (collection = this.items) == null || collection.size() == 0) {
            return null;
        }
        if (this.subItems == null) {
            Danmakus danmakus = new Danmakus(this.mDuplicateMergingEnabled);
            this.subItems = danmakus;
            danmakus.mLockObject = this.mLockObject;
        }
        if (this.startSubItem == null) {
            this.startSubItem = createItem("start");
        }
        if (this.endSubItem == null) {
            this.endSubItem = createItem("end");
        }
        this.startSubItem.setTime(j);
        this.endSubItem.setTime(j2);
        return ((SortedSet) this.items).subSet(this.startSubItem, this.endSubItem);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public IDanmakus subnew(long j, long j2) {
        Collection<BaseDanmaku> collectionSubset = subset(j, j2);
        if (collectionSubset == null || collectionSubset.isEmpty()) {
            return null;
        }
        return new Danmakus(new LinkedList(collectionSubset));
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public IDanmakus sub(long j, long j2) {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null || collection.size() == 0) {
            return null;
        }
        if (this.subItems == null) {
            if (this.mSortType == 4) {
                Danmakus danmakus = new Danmakus(4);
                this.subItems = danmakus;
                danmakus.mLockObject = this.mLockObject;
                synchronized (this.mLockObject) {
                    this.subItems.setItems(this.items);
                }
            } else {
                Danmakus danmakus2 = new Danmakus(this.mDuplicateMergingEnabled);
                this.subItems = danmakus2;
                danmakus2.mLockObject = this.mLockObject;
            }
        }
        if (this.mSortType == 4) {
            return this.subItems;
        }
        if (this.startItem == null) {
            this.startItem = createItem("start");
        }
        if (this.endItem == null) {
            this.endItem = createItem("end");
        }
        if (this.subItems != null && j - this.startItem.getActualTime() >= 0 && j2 <= this.endItem.getActualTime()) {
            return this.subItems;
        }
        this.startItem.setTime(j);
        this.endItem.setTime(j2);
        synchronized (this.mLockObject) {
            this.subItems.setItems(((SortedSet) this.items).subSet(this.startItem, this.endItem));
        }
        return this.subItems;
    }

    private BaseDanmaku createItem(String str) {
        return new Danmaku(str);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public int size() {
        return this.mSize.get();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public void clear() {
        synchronized (this.mLockObject) {
            if (this.items != null) {
                this.items.clear();
                this.mSize.set(0);
            }
        }
        if (this.subItems != null) {
            this.subItems = null;
            this.startItem = createItem("start");
            this.endItem = createItem("end");
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public BaseDanmaku first() {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        if (this.mSortType == 4) {
            return (BaseDanmaku) ((LinkedList) this.items).peek();
        }
        return (BaseDanmaku) ((SortedSet) this.items).first();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public BaseDanmaku last() {
        Collection<BaseDanmaku> collection = this.items;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        if (this.mSortType == 4) {
            return (BaseDanmaku) ((LinkedList) this.items).peekLast();
        }
        return (BaseDanmaku) ((SortedSet) this.items).last();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean contains(BaseDanmaku baseDanmaku) {
        Collection<BaseDanmaku> collection = this.items;
        return collection != null && collection.contains(baseDanmaku);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public boolean isEmpty() {
        Collection<BaseDanmaku> collection = this.items;
        return collection == null || collection.isEmpty();
    }

    private void setDuplicateMergingEnabled(boolean z) {
        this.mComparator.setDuplicateMergingEnabled(z);
        this.mDuplicateMergingEnabled = z;
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public void setSubItemsDuplicateMergingEnabled(boolean z) {
        this.mDuplicateMergingEnabled = z;
        this.endItem = null;
        this.startItem = null;
        if (this.subItems == null) {
            Danmakus danmakus = new Danmakus(z);
            this.subItems = danmakus;
            danmakus.mLockObject = this.mLockObject;
        }
        this.subItems.setDuplicateMergingEnabled(z);
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public Collection<BaseDanmaku> getCollection() {
        return this.items;
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public void forEachSync(IDanmakus.Consumer<? super BaseDanmaku, ?> consumer) {
        synchronized (this.mLockObject) {
            forEach(consumer);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public void forEach(IDanmakus.Consumer<? super BaseDanmaku, ?> consumer) {
        consumer.before();
        Iterator<BaseDanmaku> it = this.items.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BaseDanmaku next = it.next();
            if (next != null) {
                int iAccept = consumer.accept(next);
                if (iAccept == 1) {
                    break;
                }
                if (iAccept == 2) {
                    it.remove();
                    this.mSize.decrementAndGet();
                } else if (iAccept == 3) {
                    it.remove();
                    this.mSize.decrementAndGet();
                    break;
                }
            }
        }
        consumer.after();
    }

    @Override // master.flame.danmaku.danmaku.model.IDanmakus
    public Object obtainSynchronizer() {
        return this.mLockObject;
    }
}
