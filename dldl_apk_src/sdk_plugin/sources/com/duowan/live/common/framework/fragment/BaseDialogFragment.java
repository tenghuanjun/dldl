package com.duowan.live.common.framework.fragment;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BaseDialogFragment extends DialogFragment implements LifecycleOwner {
    protected DialogFragmentEventWrapper dialogFragmentEventWrapper;
    private static String TAG = BaseDialogFragment.class.getSimpleName();
    private static final String BASE_CLASS_NAME = BaseDialogFragment.class.getName();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private boolean mRegisterSignalAuto = true;
    protected Handler mHandler = new Handler();

    public interface IDialogFragmentEventListener {
        void onDismiss(Object obj);
    }

    protected void onCreatePresenter() {
    }

    protected void onDestroyPresenter() {
    }

    protected <T extends View> T findViewById(int i) {
        if (getView() != null) {
            return (T) getView().findViewById(i);
        }
        return null;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onCreatePresenter();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        try {
            super.onActivityCreated(bundle);
        } catch (Exception e) {
            L.error(TAG, (Throwable) e);
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        if (this.mRegisterSignalAuto) {
            BaseApi.getSignalCenterApi().register(this);
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStop() {
        if (this.mRegisterSignalAuto) {
            BaseApi.getSignalCenterApi().unregister(this);
        }
        super.onStop();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    protected void setRegisterSignalAuto(boolean z) {
        this.mRegisterSignalAuto = z;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        onDestroyPresenter();
        super.onDestroy();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        FragmentTransaction fragmentTransactionBeginTransaction;
        if (isAdded()) {
            dismissAllowingStateLoss();
        } else {
            if (fragmentManager == null || fragmentManager.findFragmentByTag(str) != null || (fragmentTransactionBeginTransaction = fragmentManager.beginTransaction()) == null) {
                return;
            }
            fragmentTransactionBeginTransaction.add(this, str).commitAllowingStateLoss();
        }
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogFragmentEventWrapper dialogFragmentEventWrapper = this.dialogFragmentEventWrapper;
        if (dialogFragmentEventWrapper != null) {
            dialogFragmentEventWrapper.fireDismissEvent();
        }
    }

    public void setEventListener(IDialogFragmentEventListener iDialogFragmentEventListener) {
        if (this.dialogFragmentEventWrapper == null) {
            this.dialogFragmentEventWrapper = new DialogFragmentEventWrapper();
        }
        this.dialogFragmentEventWrapper.setOuterListener(iDialogFragmentEventListener);
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public class DialogFragmentEventWrapper implements IDialogFragmentEventListener {
        private Object dismissObjectData;
        private IDialogFragmentEventListener outerListener;

        public DialogFragmentEventWrapper() {
        }

        public void setOuterListener(IDialogFragmentEventListener iDialogFragmentEventListener) {
            this.outerListener = iDialogFragmentEventListener;
        }

        public void fireDismissEvent() {
            IDialogFragmentEventListener iDialogFragmentEventListener = this.outerListener;
            if (iDialogFragmentEventListener != null) {
                iDialogFragmentEventListener.onDismiss(this.dismissObjectData);
            }
        }

        @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment.IDialogFragmentEventListener
        public void onDismiss(Object obj) {
            IDialogFragmentEventListener iDialogFragmentEventListener = this.outerListener;
            if (iDialogFragmentEventListener != null) {
                iDialogFragmentEventListener.onDismiss(obj);
            }
        }

        public void setDimissObjectData(Object obj) {
            this.dismissObjectData = obj;
        }
    }
}
