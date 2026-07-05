package com.duowan.kiwi.barrage.render.area;

import com.duowan.kiwi.barrage.trace.AbsTrace;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AnimationListenerImpl implements OnAnimationListener {
    private WeakReference<AbsTrace> mLastTarget = null;

    protected void onLastItemEnd(AbsTrace absTrace) {
    }

    public void setTarget(AbsTrace absTrace) {
        AbsTrace absTrace2;
        WeakReference<AbsTrace> weakReference = this.mLastTarget;
        if (weakReference != null && (absTrace2 = weakReference.get()) != null) {
            absTrace2.setListener(null);
        }
        this.mLastTarget = new WeakReference<>(absTrace);
    }

    @Override // com.duowan.kiwi.barrage.render.area.OnAnimationListener
    public void onAnimationEnd(AbsTrace absTrace) {
        AbsTrace absTrace2 = this.mLastTarget.get();
        if (absTrace2 != null) {
            onLastItemEnd(absTrace2);
        }
    }
}
