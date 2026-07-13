package com.bumptech.glide.manager;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideExperiments;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RequestManagerRetriever implements Handler.Callback {
    private static final RequestManagerFactory DEFAULT_FACTORY = new RequestManagerFactory() { // from class: com.bumptech.glide.manager.RequestManagerRetriever.1
        @Override // com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory
        public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    };
    private static final String FRAGMENT_INDEX_KEY = "key";
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int HAS_ATTEMPTED_TO_ADD_FRAGMENT_TWICE = 1;
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
    private static final String TAG = "RMRetriever";
    private volatile RequestManager applicationManager;
    private final RequestManagerFactory factory;
    private final FrameWaiter frameWaiter;
    private final Handler handler;
    private final LifecycleRequestManagerRetriever lifecycleRequestManagerRetriever;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
    final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();
    private final ArrayMap<View, Fragment> tempViewToSupportFragment = new ArrayMap<>();
    private final ArrayMap<View, android.app.Fragment> tempViewToFragment = new ArrayMap<>();
    private final Bundle tempBundle = new Bundle();

    public interface RequestManagerFactory {
        RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context);
    }

    public RequestManagerRetriever(RequestManagerFactory requestManagerFactory, GlideExperiments glideExperiments) {
        requestManagerFactory = requestManagerFactory == null ? DEFAULT_FACTORY : requestManagerFactory;
        this.factory = requestManagerFactory;
        this.handler = new Handler(Looper.getMainLooper(), this);
        this.lifecycleRequestManagerRetriever = new LifecycleRequestManagerRetriever(requestManagerFactory);
        this.frameWaiter = buildFrameWaiter(glideExperiments);
    }

    private static FrameWaiter buildFrameWaiter(GlideExperiments glideExperiments) {
        if (!HardwareConfigState.HARDWARE_BITMAPS_SUPPORTED || !HardwareConfigState.BLOCK_HARDWARE_BITMAPS_WHEN_GL_CONTEXT_MIGHT_NOT_BE_INITIALIZED) {
            return new DoNothingFirstFrameWaiter();
        }
        if (glideExperiments.isEnabled(GlideBuilder.WaitForFramesAfterTrimMemory.class)) {
            return new FirstFrameAndAfterTrimMemoryWaiter();
        }
        return new FirstFrameWaiter();
    }

    private RequestManager getApplicationManager(Context context) {
        if (this.applicationManager == null) {
            synchronized (this) {
                if (this.applicationManager == null) {
                    this.applicationManager = this.factory.build(Glide.get(context.getApplicationContext()), new ApplicationLifecycle(), new EmptyRequestManagerTreeNode(), context.getApplicationContext());
                }
            }
        }
        return this.applicationManager;
    }

    public RequestManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return get((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                ContextWrapper contextWrapper = (ContextWrapper) context;
                if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                    return get(contextWrapper.getBaseContext());
                }
            }
        }
        return getApplicationManager(context);
    }

    public RequestManager get(FragmentActivity fragmentActivity) {
        if (Util.isOnBackgroundThread()) {
            return get(fragmentActivity.getApplicationContext());
        }
        assertNotDestroyed(fragmentActivity);
        this.frameWaiter.registerSelf(fragmentActivity);
        boolean zIsActivityVisible = isActivityVisible(fragmentActivity);
        return this.lifecycleRequestManagerRetriever.getOrCreate(fragmentActivity, Glide.get(fragmentActivity.getApplicationContext()), fragmentActivity.getLifecycle(), fragmentActivity.getSupportFragmentManager(), zIsActivityVisible);
    }

    public RequestManager get(Fragment fragment) {
        Preconditions.checkNotNull(fragment.getContext(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getContext().getApplicationContext());
        }
        if (fragment.getActivity() != null) {
            this.frameWaiter.registerSelf(fragment.getActivity());
        }
        androidx.fragment.app.FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        Context context = fragment.getContext();
        return this.lifecycleRequestManagerRetriever.getOrCreate(context, Glide.get(context.getApplicationContext()), fragment.getLifecycle(), childFragmentManager, fragment.isVisible());
    }

    @Deprecated
    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        if (activity instanceof FragmentActivity) {
            return get((FragmentActivity) activity);
        }
        assertNotDestroyed(activity);
        this.frameWaiter.registerSelf(activity);
        return fragmentGet(activity, activity.getFragmentManager(), null, isActivityVisible(activity));
    }

    public RequestManager get(View view) {
        if (Util.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activityFindActivity = findActivity(view.getContext());
        if (activityFindActivity == null) {
            return get(view.getContext().getApplicationContext());
        }
        if (activityFindActivity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activityFindActivity;
            Fragment fragmentFindSupportFragment = findSupportFragment(view, fragmentActivity);
            return fragmentFindSupportFragment != null ? get(fragmentFindSupportFragment) : get(fragmentActivity);
        }
        android.app.Fragment fragmentFindFragment = findFragment(view, activityFindActivity);
        if (fragmentFindFragment == null) {
            return get(activityFindActivity);
        }
        return get(fragmentFindFragment);
    }

    private static void findAllSupportFragmentsWithViews(Collection<Fragment> collection, Map<View, Fragment> map) {
        if (collection == null) {
            return;
        }
        for (Fragment fragment : collection) {
            if (fragment != null && fragment.getView() != null) {
                map.put(fragment.getView(), fragment);
                findAllSupportFragmentsWithViews(fragment.getChildFragmentManager().getFragments(), map);
            }
        }
    }

    private Fragment findSupportFragment(View view, FragmentActivity fragmentActivity) {
        this.tempViewToSupportFragment.clear();
        findAllSupportFragmentsWithViews(fragmentActivity.getSupportFragmentManager().getFragments(), this.tempViewToSupportFragment);
        View viewFindViewById = fragmentActivity.findViewById(R.id.content);
        Fragment fragment = null;
        while (!view.equals(viewFindViewById) && (fragment = this.tempViewToSupportFragment.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.tempViewToSupportFragment.clear();
        return fragment;
    }

    @Deprecated
    private android.app.Fragment findFragment(View view, Activity activity) {
        this.tempViewToFragment.clear();
        findAllFragmentsWithViews(activity.getFragmentManager(), this.tempViewToFragment);
        View viewFindViewById = activity.findViewById(R.id.content);
        android.app.Fragment fragment = null;
        while (!view.equals(viewFindViewById) && (fragment = this.tempViewToFragment.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.tempViewToFragment.clear();
        return fragment;
    }

    @Deprecated
    private void findAllFragmentsWithViews(FragmentManager fragmentManager, ArrayMap<View, android.app.Fragment> arrayMap) {
        if (Build.VERSION.SDK_INT >= 26) {
            for (android.app.Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    arrayMap.put(fragment.getView(), fragment);
                    findAllFragmentsWithViews(fragment.getChildFragmentManager(), arrayMap);
                }
            }
            return;
        }
        findAllFragmentsWithViewsPreO(fragmentManager, arrayMap);
    }

    @Deprecated
    private void findAllFragmentsWithViewsPreO(FragmentManager fragmentManager, ArrayMap<View, android.app.Fragment> arrayMap) {
        android.app.Fragment fragment;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            this.tempBundle.putInt("key", i);
            try {
                fragment = fragmentManager.getFragment(this.tempBundle, "key");
            } catch (Exception unused) {
                fragment = null;
            }
            if (fragment == null) {
                return;
            }
            if (fragment.getView() != null) {
                arrayMap.put(fragment.getView(), fragment);
                findAllFragmentsWithViews(fragment.getChildFragmentManager(), arrayMap);
            }
            i = i2;
        }
    }

    private static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private static void assertNotDestroyed(Activity activity) {
        if (activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @Deprecated
    public RequestManager get(android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        }
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        }
        if (fragment.getActivity() != null) {
            this.frameWaiter.registerSelf(fragment.getActivity());
        }
        return fragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    @Deprecated
    RequestManagerFragment getRequestManagerFragment(Activity activity) {
        return getRequestManagerFragment(activity.getFragmentManager(), null);
    }

    private RequestManagerFragment getRequestManagerFragment(FragmentManager fragmentManager, android.app.Fragment fragment) {
        RequestManagerFragment requestManagerFragment = this.pendingRequestManagerFragments.get(fragmentManager);
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (requestManagerFragment2 == null) {
            requestManagerFragment2 = new RequestManagerFragment();
            requestManagerFragment2.setParentFragmentHint(fragment);
            this.pendingRequestManagerFragments.put(fragmentManager, requestManagerFragment2);
            fragmentManager.beginTransaction().add(requestManagerFragment2, FRAGMENT_TAG).commitAllowingStateLoss();
            this.handler.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return requestManagerFragment2;
    }

    @Deprecated
    private RequestManager fragmentGet(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        RequestManagerFragment requestManagerFragment = getRequestManagerFragment(fragmentManager, fragment);
        RequestManager requestManager = requestManagerFragment.getRequestManager();
        if (requestManager == null) {
            requestManager = this.factory.build(Glide.get(context), requestManagerFragment.getGlideLifecycle(), requestManagerFragment.getRequestManagerTreeNode(), context);
            if (z) {
                requestManager.onStart();
            }
            requestManagerFragment.setRequestManager(requestManager);
        }
        return requestManager;
    }

    SupportRequestManagerFragment getSupportRequestManagerFragment(androidx.fragment.app.FragmentManager fragmentManager) {
        return getSupportRequestManagerFragment(fragmentManager, null);
    }

    private static boolean isActivityVisible(Context context) {
        Activity activityFindActivity = findActivity(context);
        return activityFindActivity == null || !activityFindActivity.isFinishing();
    }

    private SupportRequestManagerFragment getSupportRequestManagerFragment(androidx.fragment.app.FragmentManager fragmentManager, Fragment fragment) {
        SupportRequestManagerFragment supportRequestManagerFragment = this.pendingSupportRequestManagerFragments.get(fragmentManager);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (supportRequestManagerFragment2 == null) {
            supportRequestManagerFragment2 = new SupportRequestManagerFragment();
            supportRequestManagerFragment2.setParentFragmentHint(fragment);
            this.pendingSupportRequestManagerFragments.put(fragmentManager, supportRequestManagerFragment2);
            fragmentManager.beginTransaction().add(supportRequestManagerFragment2, FRAGMENT_TAG).commitAllowingStateLoss();
            this.handler.obtainMessage(2, fragmentManager).sendToTarget();
        }
        return supportRequestManagerFragment2;
    }

    private boolean verifyOurFragmentWasAddedOrCantBeAdded(FragmentManager fragmentManager, boolean z) {
        RequestManagerFragment requestManagerFragment = this.pendingRequestManagerFragments.get(fragmentManager);
        RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (requestManagerFragment2 == requestManagerFragment) {
            return true;
        }
        if (requestManagerFragment2 != null && requestManagerFragment2.getRequestManager() != null) {
            throw new IllegalStateException("We've added two fragments with requests! Old: " + requestManagerFragment2 + " New: " + requestManagerFragment);
        }
        if (!z && !fragmentManager.isDestroyed()) {
            FragmentTransaction fragmentTransactionAdd = fragmentManager.beginTransaction().add(requestManagerFragment, FRAGMENT_TAG);
            if (requestManagerFragment2 != null) {
                fragmentTransactionAdd.remove(requestManagerFragment2);
            }
            fragmentTransactionAdd.commitAllowingStateLoss();
            this.handler.obtainMessage(1, 1, 0, fragmentManager).sendToTarget();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "We failed to add our Fragment the first time around, trying again...");
            }
            return false;
        }
        if (Log.isLoggable(TAG, 5)) {
            if (fragmentManager.isDestroyed()) {
                Log.w(TAG, "Parent was destroyed before our Fragment could be added");
            } else {
                Log.w(TAG, "Tried adding Fragment twice and failed twice, giving up!");
            }
        }
        requestManagerFragment.getGlideLifecycle().onDestroy();
        return true;
    }

    private boolean verifyOurSupportFragmentWasAddedOrCantBeAdded(androidx.fragment.app.FragmentManager fragmentManager, boolean z) {
        SupportRequestManagerFragment supportRequestManagerFragment = this.pendingSupportRequestManagerFragments.get(fragmentManager);
        SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (supportRequestManagerFragment2 == supportRequestManagerFragment) {
            return true;
        }
        if (supportRequestManagerFragment2 != null && supportRequestManagerFragment2.getRequestManager() != null) {
            throw new IllegalStateException("We've added two fragments with requests! Old: " + supportRequestManagerFragment2 + " New: " + supportRequestManagerFragment);
        }
        if (!z && !fragmentManager.isDestroyed()) {
            androidx.fragment.app.FragmentTransaction fragmentTransactionAdd = fragmentManager.beginTransaction().add(supportRequestManagerFragment, FRAGMENT_TAG);
            if (supportRequestManagerFragment2 != null) {
                fragmentTransactionAdd.remove(supportRequestManagerFragment2);
            }
            fragmentTransactionAdd.commitNowAllowingStateLoss();
            this.handler.obtainMessage(2, 1, 0, fragmentManager).sendToTarget();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "We failed to add our Fragment the first time around, trying again...");
            }
            return false;
        }
        if (fragmentManager.isDestroyed()) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Parent was destroyed before our Fragment could be added, all requests for the destroyed parent are cancelled");
            }
        } else if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "ERROR: Tried adding Fragment twice and failed twice, giving up and cancelling all associated requests! This probably means you're starting loads in a unit test with an Activity that you haven't created and never create. If you're using Robolectric, create the Activity as part of your test setup");
        }
        supportRequestManagerFragment.getGlideLifecycle().onDestroy();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean handleMessage(android.os.Message r7) {
        /*
            r6 = this;
            int r0 = r7.arg1
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L8
            r0 = 1
            goto L9
        L8:
            r0 = 0
        L9:
            int r3 = r7.what
            r4 = 0
            if (r3 == r1) goto L25
            r5 = 2
            if (r3 == r5) goto L14
            r7 = r4
            r1 = 0
            goto L39
        L14:
            java.lang.Object r7 = r7.obj
            androidx.fragment.app.FragmentManager r7 = (androidx.fragment.app.FragmentManager) r7
            boolean r0 = r6.verifyOurSupportFragmentWasAddedOrCantBeAdded(r7, r0)
            if (r0 == 0) goto L36
            java.util.Map<androidx.fragment.app.FragmentManager, com.bumptech.glide.manager.SupportRequestManagerFragment> r0 = r6.pendingSupportRequestManagerFragments
            java.lang.Object r4 = r0.remove(r7)
            goto L38
        L25:
            java.lang.Object r7 = r7.obj
            android.app.FragmentManager r7 = (android.app.FragmentManager) r7
            boolean r0 = r6.verifyOurFragmentWasAddedOrCantBeAdded(r7, r0)
            if (r0 == 0) goto L36
            java.util.Map<android.app.FragmentManager, com.bumptech.glide.manager.RequestManagerFragment> r0 = r6.pendingRequestManagerFragments
            java.lang.Object r4 = r0.remove(r7)
            goto L38
        L36:
            r7 = r4
            r1 = 0
        L38:
            r2 = 1
        L39:
            r0 = 5
            java.lang.String r3 = "RMRetriever"
            boolean r0 = android.util.Log.isLoggable(r3, r0)
            if (r0 == 0) goto L57
            if (r1 == 0) goto L57
            if (r4 != 0) goto L57
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Failed to remove expected request manager fragment, manager: "
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            android.util.Log.w(r3, r7)
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerRetriever.handleMessage(android.os.Message):boolean");
    }
}
