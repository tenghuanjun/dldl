package com.sqwan.common.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ViewUtils;
import java.util.Objects;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoadingExDialog extends Dialog {
    private CharSequence mMessage;
    TextView textView;

    public LoadingExDialog(Context context) {
        super(context, SqResUtils.getStyleId(context, "FullScreenDialogTransparentStyle"));
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sy37_base_progress_dialog", "layout"));
        this.textView = (TextView) findViewById(getIdByName("msg", SqTrackCommonKey.id));
        ((Window) Objects.requireNonNull(getWindow())).setBackgroundDrawableResource(R.color.transparent);
    }

    protected int getIdByName(String str, String str2) {
        try {
            return SqResUtils.getIdByName(str, str2, getContext());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    @Override // android.app.Dialog
    public void show() {
        StatusBarUtil.hideSystemUI(getWindow());
        super.show();
    }

    public void setMessage(CharSequence charSequence) {
        TextView textView;
        this.mMessage = charSequence;
        if (!TextUtils.isEmpty(charSequence) && (textView = this.textView) != null) {
            ViewUtils.show(textView);
            this.textView.setText(this.mMessage);
        } else {
            ViewUtils.gone(this.textView);
        }
    }
}
