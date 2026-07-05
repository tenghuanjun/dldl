package com.duowan.auk.ui;

import android.app.Activity;
import android.app.Fragment;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.duowan.auk.ui.annotation.IAFragment;
import com.duowan.auk.ui.annotation.IAHelper;
import com.duowan.auk.ui.utils.UILog;
import com.duowan.auk.ui.widget.IGetLayoutId;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkFragment extends Fragment implements IGetLayoutId, LifecycleOwner {
    private static final String BASE_CLASS_NAME = ArkFragment.class.getName();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override // com.duowan.auk.ui.widget.IGetLayoutId
    public int getLayoutId() {
        return 0;
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        UILog.lifecycle("onAttach", this);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UILog.lifecycle("onCreate", this);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        UILog.lifecycle("onCreateView", this);
        IAFragment iAFragment = (IAFragment) getClass().getAnnotation(IAFragment.class);
        if (iAFragment != null) {
            View viewInflate = layoutInflater.inflate(iAFragment.value(), viewGroup, false);
            IAHelper.init(this, viewInflate, BASE_CLASS_NAME);
            return viewInflate;
        }
        int layoutId = getLayoutId();
        if (layoutId == 0) {
            return null;
        }
        View viewInflate2 = layoutInflater.inflate(layoutId, viewGroup, false);
        IAHelper.init(this, viewInflate2, BASE_CLASS_NAME);
        return viewInflate2;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        UILog.lifecycle("onActivityCreated", this);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        UILog.lifecycle("onStart", this);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        UILog.lifecycle("onResume", this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        UILog.lifecycle("onPause", this);
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        UILog.lifecycle("onDestroyView", this);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        UILog.lifecycle("onDestroy", this);
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        UILog.lifecycle("onDetach", this);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        UILog.lifecycle("onStop", this);
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
