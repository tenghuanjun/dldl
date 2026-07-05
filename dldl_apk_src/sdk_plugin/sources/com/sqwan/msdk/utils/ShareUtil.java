package com.sqwan.msdk.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.sdk.Platform;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ShareUtil {
    private static final int THUMB_SIZE = 150;
    private static final int TIMELINE_SUPPORTED_VERSION = 553779201;
    private static final int WXSCENE_SESSION = 0;
    private static final int WXSCEN_ETIMELINE = 1;
    private static final int WX_RES_CODE_FAILTERE = 1;
    private static final int WX_RES_CODE_SUCCSE = 0;
    private static Bitmap bmp;
    private static String description;
    private static RelativeLayout fatherView;
    private static String handlerDescription;
    private static String handlerImageUrl;
    private static WXMediaMessage handlerMSG;
    private static int handlerResourceType;
    private static String handlerTitle;
    private static IWXAPI handlerWXAPI;
    private static int handlerWXType;
    private static String handlerWebUrl;
    private static int resourceType;
    private static Bundle shareBundle;
    private static String shareUrl;
    private static String thumbUrl;
    private static String title;
    private static String wxAppid;
    private static Context wxContext;
    private static PopupWindow wxPopupWindow;
    private static int wxType;
    private AlertDialog.Builder builder;
    private Dialog wxShareDialog;
    private static SendMessageToWX.Req handlerReq = new SendMessageToWX.Req();
    private static ProgressDialog wxProgree = null;
    private static boolean isFnish = false;
    private static boolean isHidingByMethod = false;
    private static int share_wx_layout = 0;
    private static Handler handler = new Handler() { // from class: com.sqwan.msdk.utils.ShareUtil.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            ShareUtil.showWXSharePopupWindow(ShareUtil.wxContext);
        }
    };
    private static Handler wxHandler = new Handler(Looper.getMainLooper()) { // from class: com.sqwan.msdk.utils.ShareUtil.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.arg1 == 0) {
                if (ShareUtil.handlerResourceType == 0) {
                    WXWebpageObject wXWebpageObject = new WXWebpageObject();
                    wXWebpageObject.webpageUrl = ShareUtil.handlerWebUrl;
                    WXMediaMessage unused = ShareUtil.handlerMSG = new WXMediaMessage(wXWebpageObject);
                    ShareUtil.handlerMSG.title = ShareUtil.handlerTitle;
                    ShareUtil.handlerMSG.description = ShareUtil.handlerDescription;
                    ShareUtil.handlerReq.transaction = ShareUtil.buildTransaction("webpage");
                } else {
                    WXImageObject wXImageObject = new WXImageObject(ShareUtil.bmp);
                    WXMediaMessage unused2 = ShareUtil.handlerMSG = new WXMediaMessage();
                    ShareUtil.handlerMSG.mediaObject = wXImageObject;
                    ShareUtil.handlerReq.transaction = ShareUtil.buildTransaction("img");
                }
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(ShareUtil.bmp, ShareUtil.THUMB_SIZE, ShareUtil.THUMB_SIZE, true);
                ShareUtil.bmp.recycle();
                ShareUtil.handlerMSG.thumbData = ShareUtil.bmpToByteArray(bitmapCreateScaledBitmap, true);
                System.out.println("handler...");
                if (ShareUtil.isValidContext(ShareUtil.wxContext) && ShareUtil.wxProgree != null && ShareUtil.wxProgree.isShowing()) {
                    ShareUtil.wxProgree.dismiss();
                }
                ShareUtil.handlerReq.message = ShareUtil.handlerMSG;
                ShareUtil.handlerReq.scene = ShareUtil.handlerWXType != 1 ? 0 : 1;
                ShareUtil.handlerWXAPI.sendReq(ShareUtil.handlerReq);
            }
        }
    };
    private static Runnable netRunnable = new Runnable() { // from class: com.sqwan.msdk.utils.ShareUtil.7
        @Override // java.lang.Runnable
        public void run() {
            try {
                Bitmap unused = ShareUtil.bmp = BitmapFactory.decodeStream(new URL(ShareUtil.handlerImageUrl).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.arg1 = 0;
            ShareUtil.wxHandler.sendMessage(message);
        }
    };

    public static View onCreate(Context context, Bundle bundle) {
        wxContext = context;
        shareBundle = bundle;
        isFnish = false;
        View viewInflate = null;
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("wxappid");
            wxAppid = string;
            IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(context, string, false);
            handlerWXAPI = iwxapiCreateWXAPI;
            iwxapiCreateWXAPI.handleIntent(((Activity) context).getIntent(), (IWXAPIEventHandler) context);
            int idByName = MultiSDKUtils.getIdByName("sy37_wxentry_main", "layout", context.getPackageName(), context);
            int idByName2 = MultiSDKUtils.getIdByName("wxentry_main_id", SqTrackCommonKey.id, context.getPackageName(), context);
            viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(idByName, (ViewGroup) null);
            fatherView = (RelativeLayout) viewInflate.findViewById(idByName2);
            return viewInflate;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return viewInflate;
        }
    }

    public static void onWindowFocusChanged(Context context) {
        wxContext = context;
        handler.sendEmptyMessageAtTime(0, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showWXSharePopupWindow(final Context context) {
        isHidingByMethod = false;
        LogUtil.w("PopupWindow为空，初始化");
        int idByName = MultiSDKUtils.getIdByName("sy37_mypopwindow_anim_style", "style", context.getPackageName(), context);
        int idByName2 = MultiSDKUtils.getIdByName("sy37_share_wx_dialog", "layout", context.getPackageName(), context);
        int idByName3 = MultiSDKUtils.getIdByName("share_wx_sessions", SqTrackCommonKey.id, context.getPackageName(), context);
        int idByName4 = MultiSDKUtils.getIdByName("share_wx_online", SqTrackCommonKey.id, context.getPackageName(), context);
        share_wx_layout = MultiSDKUtils.getIdByName("share_wx_view", SqTrackCommonKey.id, context.getPackageName(), context);
        final View viewInflate = ((Activity) context).getLayoutInflater().inflate(idByName2, (ViewGroup) null);
        PopupWindow popupWindow = new PopupWindow(viewInflate, -1, -2, true);
        wxPopupWindow = popupWindow;
        popupWindow.setTouchable(true);
        wxPopupWindow.setOutsideTouchable(true);
        wxPopupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        wxPopupWindow.getContentView().setFocusableInTouchMode(true);
        wxPopupWindow.getContentView().setFocusable(true);
        wxPopupWindow.setAnimationStyle(idByName);
        ((LinearLayout) viewInflate.findViewById(share_wx_layout)).getBackground().setAlpha(80);
        wxPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.sqwan.msdk.utils.ShareUtil.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (!ShareUtil.isHidingByMethod) {
                    Platform.shareListener.onFailture(203, "用户主动取消分享");
                }
                ShareUtil.hideLoginDialog();
                ShareUtil.finishActivity(context);
            }
        });
        viewInflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.sqwan.msdk.utils.ShareUtil.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = viewInflate.findViewById(ShareUtil.share_wx_layout).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && y < top) {
                    ShareUtil.wxPopupWindow.dismiss();
                }
                return true;
            }
        });
        ImageView imageView = (ImageView) viewInflate.findViewById(idByName3);
        ((ImageView) viewInflate.findViewById(idByName4)).setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.utils.ShareUtil.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShareUtil.hideLoginDialog();
                ShareUtil.sendToWX(ShareUtil.shareBundle, 1, context);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.utils.ShareUtil.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShareUtil.hideLoginDialog();
                ShareUtil.sendToWX(ShareUtil.shareBundle, 0, context);
            }
        });
        System.out.println("微信弹窗fatherView=" + fatherView);
        System.out.println("isFnish=" + isFnish);
        if (isFnish) {
            return;
        }
        wxPopupWindow.showAtLocation(fatherView, 81, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendToWX(Bundle bundle, int i, Context context) {
        title = bundle.getString("title");
        description = bundle.getString("description");
        shareUrl = bundle.getString("shareUrl");
        thumbUrl = bundle.getString("thumbUrl");
        int i2 = bundle.getInt("resourceType");
        resourceType = i2;
        shareWXLine(context, handlerWXAPI, title, description, shareUrl, thumbUrl, i2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hideLoginDialog() {
        isHidingByMethod = true;
        PopupWindow popupWindow = wxPopupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        wxPopupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void finishActivity(Context context) {
        isFnish = true;
        ((Activity) context).finish();
    }

    public static void onResp(int i, Context context) {
        if (i == -4) {
            onRespMessage(1, "发送被拒绝", context);
            return;
        }
        if (i == -2) {
            onRespMessage(1, "取消发送", context);
        } else if (i == 0) {
            onRespMessage(0, "成功", context);
        } else {
            onRespMessage(1, "发送失败", context);
        }
    }

    private static void onRespMessage(int i, String str, Context context) {
        if (i == 0) {
            if (Platform.shareListener != null) {
                Platform.shareListener.onSuccess(new Bundle());
            }
        } else if (Platform.shareListener != null) {
            Platform.shareListener.onFailture(203, str);
        }
        finishActivity(context);
    }

    public static void shareWXLine(Context context, IWXAPI iwxapi, String str, String str2, String str3, String str4, int i, int i2) {
        if (str2.length() > 1024) {
            str2 = str2.substring(0, 1024);
        }
        if (str.length() > 512) {
            str = str.substring(0, 512);
        }
        wxContext = context;
        int wXAppSupportAPI = iwxapi.getWXAppSupportAPI();
        handlerImageUrl = str4;
        handlerWXAPI = iwxapi;
        handlerWXType = i2;
        handlerResourceType = i;
        handlerDescription = str2;
        handlerTitle = str;
        handlerWebUrl = str3;
        if (wXAppSupportAPI >= TIMELINE_SUPPORTED_VERSION) {
            try {
                if (!str4.startsWith(IDataSource.SCHEME_HTTP_TAG)) {
                    sendToSDCardResource(iwxapi, str4, i2);
                    return;
                }
                if (isValidContext(context)) {
                    System.out.println("加载中。。。");
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    wxProgree = progressDialog;
                    progressDialog.setMessage("加载中请稍后!");
                    wxProgree.show();
                }
                new Thread(netRunnable).start();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ViewUtils.showToast(context, "当前微信版本不支持分享,请下载最新版本的微信");
    }

    private static void sendToSDCardResource(IWXAPI iwxapi, String str, int i) {
        if (!new File(str).exists()) {
            ViewUtils.showToast(wxContext, "图片路径不存在");
            return;
        }
        WXImageObject wXImageObject = new WXImageObject();
        wXImageObject.setImagePath(str);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeFile, THUMB_SIZE, THUMB_SIZE, true);
        bitmapDecodeFile.recycle();
        wXMediaMessage.thumbData = bmpToByteArray(bitmapCreateScaledBitmap, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = wXMediaMessage;
        req.scene = i != 1 ? 0 : 1;
        iwxapi.sendReq(req);
    }

    private static void sendTextToWx(IWXAPI iwxapi, int i, String str) {
        System.out.println("wechat--text");
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = "永恒纪元开新服了";
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = str;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = wXMediaMessage;
        req.scene = i;
        iwxapi.sendReq(req);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String buildTransaction(String str) {
        if (str == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return str + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(Bitmap bitmap, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println("---------------------wx-result:" + byteArray.length);
        System.out.println("---------------------wx-result-kb:" + (byteArray.length / 1024));
        while (byteArray.length / 1024 > 32) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            if (i >= 10) {
                i -= 10;
            } else {
                ViewUtils.showToast(wxContext, "图片过大分享失败");
            }
        }
        if (z) {
            bitmap.recycle();
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidContext(Context context) {
        System.out.println("is comeing");
        Activity activity = (Activity) context;
        if (!activity.isDestroyed() && !activity.isFinishing()) {
            return true;
        }
        System.out.println("Activity is invalid. isDestoryed-->" + activity.isDestroyed() + " isFinishing-->" + activity.isFinishing());
        return false;
    }
}
