package com.sq.webview.hooks;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.sq.webview.ActivityResultHostFragment;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.util.WebLogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileChoseWebHook extends SimpleWebHook {
    private static final int FILE_CHOOSER_RESULT_CODE = 10000;
    private final ActivityResultHostFragment mFragment;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;

    public FileChoseWebHook(Activity context) {
        this.mFragment = ActivityResultHostFragment.holderFragmentFor(context, new ActivityResultHostFragment.IActivityResult() { // from class: com.sq.webview.hooks.-$$Lambda$bxTGBZ5MVGKh9db6nH_GsTonHsA
            @Override // com.sq.webview.ActivityResultHostFragment.IActivityResult
            public final void onActivityResult(int i, int i2, Intent intent) {
                this.f$0.onActivityResult(i, i2, intent);
            }
        });
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.uploadMessageAboveL = filePathCallback;
        openFileChooserActivity();
        return true;
    }

    private void openFileChooserActivity() {
        WebLogUtil.i("openImageChooserActivity call");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        try {
            this.mFragment.startActivityForResult(Intent.createChooser(intent, "Image Chooser"), 10000);
        } catch (Exception e) {
            WebLogUtil.e("openFileChooserActivity error");
            WebLogUtil.e(e.getMessage());
        }
    }

    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        Uri[] uriArr;
        if (requestCode != 10000 || this.uploadMessageAboveL == null) {
            return;
        }
        if (resultCode != -1 || intent == null) {
            uriArr = null;
        } else {
            String dataString = intent.getDataString();
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                uriArr = new Uri[clipData.getItemCount()];
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    uriArr[i] = clipData.getItemAt(i).getUri();
                }
            } else {
                uriArr = null;
            }
            if (dataString != null) {
                uriArr = new Uri[]{Uri.parse(dataString)};
            }
        }
        this.uploadMessageAboveL.onReceiveValue(uriArr);
        this.uploadMessageAboveL = null;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 10000) {
            return true;
        }
        if (this.uploadMessage == null && this.uploadMessageAboveL == null) {
            return false;
        }
        Uri data2 = (data == null || resultCode != -1) ? null : data.getData();
        if (this.uploadMessageAboveL != null) {
            onActivityResultAboveL(requestCode, resultCode, data);
            return true;
        }
        ValueCallback<Uri> valueCallback = this.uploadMessage;
        if (valueCallback == null) {
            return true;
        }
        valueCallback.onReceiveValue(data2);
        this.uploadMessage = null;
        return true;
    }
}
