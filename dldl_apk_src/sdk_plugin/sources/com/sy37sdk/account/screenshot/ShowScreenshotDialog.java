package com.sy37sdk.account.screenshot;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ShowScreenshotDialog extends Dialog {
    private Bitmap mBitmap;
    private ImageView mIvScreenshot;

    public ShowScreenshotDialog(Context context, Bitmap bitmap) {
        this(context, SqResUtils.getIdByName("Dialog", "style", context));
        this.mBitmap = bitmap;
    }

    public ShowScreenshotDialog(Context context, int i) {
        super(context, i);
    }

    protected ShowScreenshotDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        View viewInflate = LayoutInflater.from(context).inflate(SqResUtils.getIdByName("sy37_show_screenshot_dialog", "layout", context), (ViewGroup) null);
        setContentView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        initView(viewInflate, context);
    }

    private void initView(View view, Context context) {
        ImageView imageView = (ImageView) view.findViewById(SqResUtils.getIdByName("iv_screenshot", SqTrackCommonKey.id, context));
        this.mIvScreenshot = imageView;
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        view.findViewById(SqResUtils.getIdByName("iv_close", SqTrackCommonKey.id, context)).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.screenshot.ShowScreenshotDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ShowScreenshotDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        LogUtil.d("ScreenshotTest", "dismiss");
        super.dismiss();
    }
}
