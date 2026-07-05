package androidx.lifecycle;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData(T t) {
        super(t);
    }

    public MutableLiveData() {
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T t) {
        super.postValue(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }
}
