package com.huya.berry.gamesdk.loading;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LoadingFragment extends BaseDialogFragment {
    public static final String TAG = "LoadingFragment";

    public static LoadingFragment getInstance(FragmentManager fragmentManager) {
        LoadingFragment loadingFragment = (LoadingFragment) fragmentManager.findFragmentByTag(TAG);
        return loadingFragment == null ? new LoadingFragment() : loadingFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("hyberry.Widget.Loading.Dialog"));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_loading_dialog), (ViewGroup) null);
    }

    public void show(FragmentManager fragmentManager) {
        if (isAdded()) {
            return;
        }
        super.show(fragmentManager, TAG);
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }
}
