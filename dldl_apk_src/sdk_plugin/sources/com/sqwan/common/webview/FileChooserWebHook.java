package com.sqwan.common.webview;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sqwan.base.ActivityResultListener;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ViewUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FileChooserWebHook extends SimpleWebHook {
    public void log(String str, String str2) {
        LogUtil.i("FileChooserWebHook#" + str + ": " + str2);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.WEB_JUMP_PIC_SELECT);
        Activity activity = ViewUtils.getActivity(webView);
        if (activity == null) {
            return true;
        }
        Intent intentCreateIntent = fileChooserParams.createIntent();
        String[] acceptTypes = fileChooserParams.getAcceptTypes();
        boolean z = fileChooserParams.getMode() == 1;
        if (acceptTypes != null && acceptTypes.length > 0 && !TextUtils.isEmpty(acceptTypes[0])) {
            intentCreateIntent.putExtra("android.intent.extra.MIME_TYPES", acceptTypes);
        }
        intentCreateIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        activity.startActivityForResult(Intent.createChooser(intentCreateIntent, fileChooserParams.getTitle()), 10000);
        EventDispatcher.getInstance().addActivityResultListener(new ActivityResultListener() { // from class: com.sqwan.common.webview.FileChooserWebHook.1
            @Override // com.sqwan.base.ActivityResultListener
            public void onResult(OnActivityResultEvent onActivityResultEvent) {
                if (onActivityResultEvent.getRequestCode() != 10000) {
                    return;
                }
                EventDispatcher.getInstance().removeActivityResultListener(this);
                FileChooserWebHook.this.log("onShowFileChooser", "call OnActivityResult");
                if (valueCallback == null) {
                    FileChooserWebHook.this.log("onShowFileChooser", "filePathCallback == null");
                    return;
                }
                int resultCode = onActivityResultEvent.getResultCode();
                Intent intent = onActivityResultEvent.getIntent();
                ArrayList arrayList = new ArrayList();
                if (resultCode == -1 && intent != null) {
                    Uri data = intent.getData();
                    if (data != null) {
                        arrayList.add(data);
                        FileChooserWebHook.this.log("onShowFileChooser", "uri = " + data);
                    } else {
                        ClipData clipData = intent.getClipData();
                        if (clipData != null) {
                            FileChooserWebHook.this.log("onShowFileChooser", "clipData = " + clipData);
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                arrayList.add(clipData.getItemAt(i).getUri());
                            }
                        } else {
                            FileChooserWebHook.this.log("onShowFileChooser", "clipData = null");
                        }
                    }
                }
                valueCallback.onReceiveValue(arrayList.toArray(new Uri[0]));
                FileChooserWebHook.this.log("onShowFileChooser", "call filePathCallback.onReceiveValue");
            }
        });
        return true;
    }
}
