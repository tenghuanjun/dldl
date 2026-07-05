package com.duowan.ark.bind;

import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class ViewBinder<V, VO> {
    private Looper mDeliverLooper;

    public abstract boolean bindView(V v, VO vo);

    public ViewBinder() {
        this(false);
    }

    public ViewBinder(boolean z) {
        this(z ? null : Looper.getMainLooper());
    }

    public ViewBinder(Looper looper) {
        this.mDeliverLooper = looper;
    }

    public void setDeliverLooper(Looper looper) {
        this.mDeliverLooper = looper;
    }

    public Looper getDeliverLooper() {
        return this.mDeliverLooper;
    }

    public <BO> ViewBinder<V, BO> convert(final DataConverter<VO, BO> dataConverter) {
        return new ViewBinder<V, BO>() { // from class: com.duowan.ark.bind.ViewBinder.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.duowan.ark.bind.ViewBinder
            public boolean bindView(V v, BO bo) {
                return ViewBinder.this.bindView(v, dataConverter.convert(bo));
            }

            @Override // com.duowan.ark.bind.ViewBinder
            public Looper getDeliverLooper() {
                return ViewBinder.this.getDeliverLooper();
            }
        };
    }
}
