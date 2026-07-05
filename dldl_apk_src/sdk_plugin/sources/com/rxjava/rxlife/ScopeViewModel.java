package com.rxjava.rxlife;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ScopeViewModel extends AndroidViewModel implements Scope {
    private CompositeDisposable mDisposables;

    @Override // com.rxjava.rxlife.Scope
    public void onScopeEnd() {
    }

    public ScopeViewModel(Application application) {
        super(application);
    }

    @Override // com.rxjava.rxlife.Scope
    public void onScopeStart(Disposable disposable) {
        addDisposable(disposable);
    }

    private void addDisposable(Disposable disposable) {
        CompositeDisposable compositeDisposable = this.mDisposables;
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            this.mDisposables = compositeDisposable;
        }
        compositeDisposable.add(disposable);
    }

    private void dispose() {
        CompositeDisposable compositeDisposable = this.mDisposables;
        if (compositeDisposable == null) {
            return;
        }
        compositeDisposable.dispose();
    }

    @Override // android.arch.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        dispose();
    }
}
