package com.sy37sdk.account.policy.view;

import android.content.Context;
import android.widget.TextView;
import com.snail.antifake.deviceid.ShellAdbUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionDialog extends AuthBaseDialog {
    private OnBtnClickListener mListener;
    private String permissionDes;
    private String sdcarddes;
    private String simdes;
    private TextView tvProtol;

    public interface OnBtnClickListener {
        void onClick();
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getContainerLayout() {
        return "sy37_layout_auth_permission";
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected String getTitle() {
        return "权限申请";
    }

    public PermissionDialog(Context context) {
        super(context);
        this.simdes = "设备信息：读取设备唯一标识用于保护账号安全";
        this.sdcarddes = "存储权限：实现账号、图片的缓存和使用，图片保存与分享";
        this.permissionDes = this.simdes + ShellAdbUtils.COMMAND_LINE_END + this.sdcarddes;
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void doEngine() {
        this.tvProtol = (TextView) findViewById(findId("tvProtol"));
        this.tvCancel.setText("退出游戏");
        this.tvOk.setText("确认");
        this.tvCancel.setSelected(false);
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void onClickOk() {
        OnBtnClickListener onBtnClickListener = this.mListener;
        if (onBtnClickListener != null) {
            onBtnClickListener.onClick();
        }
        dismiss();
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog
    protected void onClickCancel() {
        dismiss();
        exit();
    }

    @Override // com.sy37sdk.account.policy.view.AuthBaseDialog, android.app.Dialog
    public void show() {
        super.show();
        setDescText();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setDescText() {
        /*
            r3 = this;
            android.content.Context r0 = r3.mContext
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L3b
            com.sqwan.common.util.PermissionHelper r0 = com.sqwan.common.util.PermissionHelper.getInstance()
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            boolean r0 = r0.checkPermission(r1)
            java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
            if (r0 != 0) goto L21
            com.sqwan.common.util.PermissionHelper r0 = com.sqwan.common.util.PermissionHelper.getInstance()
            boolean r0 = r0.checkPermission(r2)
            if (r0 != 0) goto L21
            java.lang.String r0 = r3.permissionDes
            goto L3d
        L21:
            com.sqwan.common.util.PermissionHelper r0 = com.sqwan.common.util.PermissionHelper.getInstance()
            boolean r0 = r0.checkPermission(r1)
            if (r0 != 0) goto L2e
            java.lang.String r0 = r3.simdes
            goto L3d
        L2e:
            com.sqwan.common.util.PermissionHelper r0 = com.sqwan.common.util.PermissionHelper.getInstance()
            boolean r0 = r0.checkPermission(r2)
            if (r0 != 0) goto L3b
            java.lang.String r0 = r3.sdcarddes
            goto L3d
        L3b:
            java.lang.String r0 = ""
        L3d:
            android.widget.TextView r1 = r3.tvProtol
            r1.setText(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.policy.view.PermissionDialog.setDescText():void");
    }

    public void setBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.mListener = onBtnClickListener;
    }
}
