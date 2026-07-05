package com.huya.berry.modifytitle;

import android.app.Activity;
import com.duowan.auk.util.L;
import com.huya.berry.modifytitle.api.IModifyTitleService;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ModifyTitleService extends AbsService implements IModifyTitleService {
    public static final String TAG = "ModifyTitleService";
    private InputTitleFragment mInputTitleFragment;

    @Override // com.huya.berry.modifytitle.api.IModifyTitleService
    public void showInputLiveTitleDialog(Activity activity) {
        if (activity == null) {
            L.error(TAG, "Activity is null");
            return;
        }
        if (this.mInputTitleFragment == null) {
            this.mInputTitleFragment = InputTitleFragment.getInstance(activity.getFragmentManager());
        }
        this.mInputTitleFragment.show(activity.getFragmentManager(), InputTitleFragment.TAG);
    }

    @Override // com.huya.berry.modifytitle.api.IModifyTitleService
    public void hideInputLiveTitleDialog() {
        InputTitleFragment inputTitleFragment = this.mInputTitleFragment;
        if (inputTitleFragment == null) {
            return;
        }
        inputTitleFragment.dismissAllowingStateLoss();
        this.mInputTitleFragment = null;
    }
}
