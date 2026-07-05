package com.huya.berry.sdklivelist;

import android.app.Dialog;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.duowan.auk.ArkUtils;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.huya.berry.gamesdk.utils.PermissionTool;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.live.LiveCallback;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AlertWindowDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    public static String TAG = AlertWindowDialogFragment.class.getSimpleName();
    private TextView mCancle;
    private boolean mIsLow = true;
    private TextView mMessage;
    private CheckBox mRemember;
    private TextView mSubmit;
    private TextView mTitle;

    public static AlertWindowDialogFragment getInstance(FragmentManager fragmentManager) {
        AlertWindowDialogFragment alertWindowDialogFragment = (AlertWindowDialogFragment) fragmentManager.findFragmentByTag(TAG);
        return alertWindowDialogFragment == null ? new AlertWindowDialogFragment() : alertWindowDialogFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsLow = Build.VERSION.SDK_INT < 23;
        setCancelable(true);
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-2, -2);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().requestWindowFeature(1);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_alert_window_tip), viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSubmit = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.button_positive));
        this.mCancle = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.button_neutral));
        this.mTitle = (TextView) findViewById(ResourceUtil.getIdResIDByName("title"));
        this.mMessage = (TextView) findViewById(ResourceUtil.getIdResIDByName("message"));
        if (this.mIsLow) {
            this.mTitle.setVisibility(0);
            CheckBox checkBox = (CheckBox) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.remeber_cb));
            this.mRemember = checkBox;
            checkBox.setVisibility(0);
            this.mTitle.setText(ResourceUtil.getStringResIDByName(SqR.string.hyberry_alert_window_permission_huya_vivo_tip_title));
            this.mMessage.setText(ResourceUtil.getStringResIDByName(SqR.string.hyberry_alert_window_permission_huya_vivo_tip));
        } else {
            this.mTitle.setVisibility(8);
            this.mMessage.setText(ResourceUtil.getStringResIDByName(SqR.string.hyberry_alert_window_permission_huya_tip));
            this.mMessage.setTextColor(getResources().getColor(ResourceUtil.getColorResIDByName(SqR.color.hyberry_gray22)));
        }
        this.mSubmit.setOnClickListener(this);
        this.mCancle.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.button_neutral)) {
            saveRemember(false);
            ArkUtils.send(new LiveCallback.AlertWindowPermissionCancel());
            dismissAllowingStateLoss();
        } else if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.button_positive)) {
            saveRemember(true);
            ArkUtils.send(new LiveCallback.AlertWindowPermissionRequest());
            dismissAllowingStateLoss();
        }
    }

    private void saveRemember(boolean z) {
        if (this.mIsLow) {
            PermissionTool.setIgnoreDrawOverlays(this.mRemember.isChecked());
        }
    }
}
