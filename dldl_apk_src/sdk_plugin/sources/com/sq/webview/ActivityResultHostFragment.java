package com.sq.webview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ActivityResultHostFragment extends Fragment {
    private static final String HOLDER_TAG = "ActivityResultHostFragment";
    private IActivityResult mIActivityResult;

    public interface IActivityResult {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    @Override // android.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IActivityResult iActivityResult = this.mIActivityResult;
        if (iActivityResult != null) {
            iActivityResult.onActivityResult(requestCode, resultCode, data);
        }
    }

    private static ActivityResultHostFragment createHolderFragment(FragmentManager fragmentManager) {
        ActivityResultHostFragment activityResultHostFragment = new ActivityResultHostFragment();
        fragmentManager.beginTransaction().add(activityResultHostFragment, HOLDER_TAG).commitAllowingStateLoss();
        return activityResultHostFragment;
    }

    private static ActivityResultHostFragment holderFragmentFor(FragmentManager fm, IActivityResult activityResultImp) {
        ActivityResultHostFragment activityResultHostFragmentFindHolderFragment = findHolderFragment(fm);
        if (activityResultHostFragmentFindHolderFragment != null) {
            return activityResultHostFragmentFindHolderFragment;
        }
        ActivityResultHostFragment activityResultHostFragmentCreateHolderFragment = createHolderFragment(fm);
        activityResultHostFragmentCreateHolderFragment.setCallback(activityResultImp);
        return activityResultHostFragmentCreateHolderFragment;
    }

    public static ActivityResultHostFragment holderFragmentFor(Activity activity, IActivityResult IActivityResult2) {
        return holderFragmentFor(activity.getFragmentManager(), IActivityResult2);
    }

    private static ActivityResultHostFragment findHolderFragment(FragmentManager manager) {
        if (manager.isDestroyed()) {
            throw new IllegalStateException("Can't access ViewModels from onDestroy");
        }
        Fragment fragmentFindFragmentByTag = manager.findFragmentByTag(HOLDER_TAG);
        if (fragmentFindFragmentByTag != null && !(fragmentFindFragmentByTag instanceof ActivityResultHostFragment)) {
            throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
        }
        return (ActivityResultHostFragment) fragmentFindFragmentByTag;
    }

    public void setCallback(IActivityResult IActivityResult2) {
        this.mIActivityResult = IActivityResult2;
    }
}
