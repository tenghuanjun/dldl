package com.sy37sdk.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.utils.AppUtils;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ExitDialog extends Dialog {
    private Context context;
    private AsyncImageLoader imageLoader;
    private ExitCallBack mCallback;
    private Drawable mImg;
    private String mImgPath;
    private String mPackageName;
    private String mUrl;

    public interface ExitCallBack {
        void exit();
    }

    public ExitDialog(Context context, ExitCallBack exitCallBack) {
        this(context, Util.getIdByName("Dialog", "style", context.getPackageName(), context), exitCallBack);
        this.context = context;
        this.mCallback = exitCallBack;
    }

    ExitDialog(Context context, int i, ExitCallBack exitCallBack) {
        super(context, i);
        this.imageLoader = new AsyncImageLoader(context);
        this.mCallback = exitCallBack;
        this.context = context;
    }

    ExitDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener, ExitCallBack exitCallBack) {
        super(context, z, onCancelListener);
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(Util.getIdByName("sy37_dialog_exit", "layout", this.context.getPackageName(), this.context), (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(Util.getIdByName("sy37_img_exit_res", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        Button button = (Button) viewInflate.findViewById(Util.getIdByName("sy37_btn_exit_game", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        Button button2 = (Button) viewInflate.findViewById(Util.getIdByName("sy37_btn_back_game", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        Drawable drawable = this.mImg;
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            this.imageLoader.loadDrawable(this.mImgPath, imageView, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.widget.ExitDialog.1
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public void imageLoaded(Bitmap bitmap, ImageView imageView2, String str) {
                    imageView2.setImageBitmap(bitmap);
                }
            });
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.widget.ExitDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ExitDialog.this.mPackageName == null || "".equals(ExitDialog.this.mPackageName) || !Util.checkAppInstalled(ExitDialog.this.context, ExitDialog.this.mPackageName)) {
                    if (ExitDialog.this.mUrl == null || "".equals(ExitDialog.this.mUrl)) {
                        return;
                    }
                    AppUtils.toUri(ExitDialog.this.context, ExitDialog.this.mUrl);
                    ExitDialog.this.dismiss();
                    return;
                }
                AppUtils.startAppFromPackage(ExitDialog.this.context, ExitDialog.this.mPackageName);
                ExitDialog.this.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.widget.ExitDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExitDialog.this.dismiss();
                ExitDialog.this.mCallback.exit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.widget.ExitDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.i("取消退出，返回游戏");
                ExitDialog.this.dismiss();
            }
        });
        setContentView(viewInflate, new ViewGroup.LayoutParams(-2, -2));
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setPkn(String str) {
        this.mPackageName = str;
    }

    public void setImageDrawable(Drawable drawable) {
        this.mImg = drawable;
    }

    public void setImagePath(String str) {
        this.mImgPath = str;
    }
}
