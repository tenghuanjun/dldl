package com.sqwan.m;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.demo.base.ToastUtil;
import com.parameters.share.ShareImageInfo;
import com.parameters.share.ShareMessage;
import com.parameters.share.ShareTextInfo;
import com.parameters.share.ShareWebInfo;
import com.plugin.standard.BaseActivity;
import com.sqwan.msdk.SQwanCore;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.order.nat.trade.NativePayWay;
import com.sy37sdk.share.UrlConstant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ShareDemoActivity extends BaseActivity implements View.OnClickListener {
    private final SQResultListener mShareListener = new SQResultListener() { // from class: com.sqwan.m.ShareDemoActivity.1
        public void onSuccess(Bundle bundle) {
            ToastUtil.showToast(ShareDemoActivity.this, "分享成功");
        }

        public void onFailture(int i, String str) {
            ToastUtil.showToast(ShareDemoActivity.this, str);
        }
    };
    private EditText shareJsEditText;
    private EditText shareSystemEditText;
    private Object sqCommonJsInterfaceInstance;

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super/*android.app.Activity*/.onCreate(bundle);
        setContentView(2131296371);
        this.shareJsEditText = (EditText) findViewById(2131165380);
        this.shareSystemEditText = (EditText) findViewById(2131165381);
        findViewById(2131165280).setOnClickListener(this);
        findViewById(2131165278).setOnClickListener(this);
        findViewById(2131165279).setOnClickListener(this);
        findViewById(2131165277).setOnClickListener(this);
        findViewById(2131165276).setOnClickListener(this);
        findViewById(2131165275).setOnClickListener(this);
        findViewById(2131165274).setOnClickListener(this);
        findViewById(2131165284).setOnClickListener(this);
        findViewById(2131165283).setOnClickListener(this);
        findViewById(2131165285).setOnClickListener(this);
        findViewById(2131165282).setOnClickListener(this);
        findViewById(2131165281).setOnClickListener(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131165280) {
            setShareJsContent(this.shareJsEditText, "way", NativePayWay.PWAY_KEY_WECHAT);
        } else if (id == 2131165278) {
            setShareJsContent(this.shareJsEditText, "way", "moments");
        } else if (id == 2131165279) {
            setShareJsContent(this.shareJsEditText, "way", "qq");
        } else if (id == 2131165277) {
            setShareJsContent(this.shareJsEditText, "type", "1");
        } else if (id == 2131165276) {
            setShareJsContent(this.shareJsEditText, "type", "2");
        } else if (id == 2131165275) {
            invokeMethod(getSQCommonJsInterfaceInstance(), UrlConstant.KEY_SHARE, String.class, this.shareJsEditText.getText().toString());
        } else if (id == 2131165274) {
            try {
                JSONObject jSONObject = new JSONObject(this.shareJsEditText.getText().toString());
                String strOptString = jSONObject.optString("type");
                ShareMessage shareMessage = new ShareMessage();
                if ("1".equals(strOptString)) {
                    ShareImageInfo shareImageInfo = new ShareImageInfo();
                    shareImageInfo.setBitmap(readBitmap(2131100340));
                    shareMessage.setShareMessage(shareImageInfo);
                } else if ("2".equals(strOptString)) {
                    ShareWebInfo shareWebInfo = new ShareWebInfo();
                    shareWebInfo.setThumbBmp(readBitmap(2131100340));
                    shareWebInfo.setTitle(jSONObject.optString("title"));
                    shareWebInfo.setPageUrl(jSONObject.optString("landingPageUrl"));
                    shareWebInfo.setDesc(jSONObject.optString("desc"));
                    shareMessage.setShareMessage(shareWebInfo);
                } else {
                    ToastUtil.showToast(this, "暂不支持的分享类型：" + strOptString);
                    return;
                }
                String strOptString2 = jSONObject.optString("way");
                if (NativePayWay.PWAY_KEY_WECHAT.equals(strOptString2)) {
                    shareMessage.setPlatform(1);
                } else if ("moments".equals(strOptString2)) {
                    shareMessage.setPlatform(2);
                } else if ("qq".equals(strOptString2)) {
                    shareMessage.setPlatform(3);
                } else {
                    ToastUtil.showToast(this, "暂不支持的分享平台类型：" + strOptString);
                    return;
                }
                shareMessage.setSkipPreview(true);
                SQwanCore.getInstance().share(shareMessage, this.mShareListener);
            } catch (JSONException e) {
                e.printStackTrace();
                ToastUtil.showToast(this, "Json 解析出错");
            }
        }
        if (id == 2131165284) {
            setShareJsContent(this.shareSystemEditText, "shareType", "1");
            return;
        }
        if (id == 2131165283) {
            setShareJsContent(this.shareSystemEditText, "shareType", "2");
            return;
        }
        if (id == 2131165285) {
            setShareJsContent(this.shareSystemEditText, "shareType", "3");
            return;
        }
        if (id == 2131165282) {
            invokeMethod(getSQCommonJsInterfaceInstance(), "shareToSystem", String.class, this.shareSystemEditText.getText().toString());
            return;
        }
        if (id == 2131165281) {
            try {
                JSONObject jSONObject2 = new JSONObject(this.shareSystemEditText.getText().toString());
                String strOptString3 = jSONObject2.optString("shareType");
                ShareMessage shareMessage2 = new ShareMessage();
                if ("1".equals(strOptString3)) {
                    ShareImageInfo shareImageInfo2 = new ShareImageInfo();
                    shareImageInfo2.setBitmap(readBitmap(2131100340));
                    shareMessage2.setShareMessage(shareImageInfo2);
                } else if ("2".equals(strOptString3)) {
                    ShareWebInfo shareWebInfo2 = new ShareWebInfo();
                    shareWebInfo2.setThumbBmp(readBitmap(2131100340));
                    shareWebInfo2.setTitle(jSONObject2.optString("shareTitle"));
                    shareWebInfo2.setPageUrl(jSONObject2.optString("shareLinkUrl"));
                    shareWebInfo2.setDesc(jSONObject2.optString("shareText"));
                    shareMessage2.setShareMessage(shareWebInfo2);
                } else if ("3".equals(strOptString3)) {
                    ShareTextInfo shareTextInfo = new ShareTextInfo();
                    shareTextInfo.setText(jSONObject2.optString("shareText"));
                    shareMessage2.setShareMessage(shareTextInfo);
                } else {
                    ToastUtil.showToast(this, "暂不支持的分享类型：" + strOptString3);
                    return;
                }
                shareMessage2.setPlatform(4);
                shareMessage2.setSkipPreview(true);
                SQwanCore.getInstance().share(shareMessage2, this.mShareListener);
            } catch (JSONException e2) {
                e2.printStackTrace();
                ToastUtil.showToast(this, "Json 解析出错");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setShareJsContent(EditText editText, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(editText.getText().toString());
            jSONObject.putOpt(str, str2);
            editText.setText(jSONObject.toString(2));
            ToastUtil.showToast(this, "修改 " + str + " 值成功");
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtil.showToast(this, "Json 解析出错");
        }
    }

    private Bitmap readBitmap(int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(getResources().openRawResource(i), null, options);
    }

    private Object getSQCommonJsInterfaceInstance() {
        if (this.sqCommonJsInterfaceInstance == null) {
            try {
                Class<?> clsLoadClass = getClassLoader().loadClass("com.sqwan.common.webview.SQWebView");
                this.sqCommonJsInterfaceInstance = getClassLoader().loadClass("com.sqwan.common.webview.SQCommonJsInterface").getConstructor(clsLoadClass).newInstance(clsLoadClass.getConstructor(Context.class).newInstance(this));
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                ToastUtil.showToast(this, "反射获取 SQCommonJsInterface 失败");
            }
        }
        return this.sqCommonJsInterfaceInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void invokeMethod(Object obj, String str, Class<?> cls, Object... objArr) {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, cls);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, objArr);
            ToastUtil.showToast(this, "反射执行方法成功");
        } catch (IllegalAccessException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            e.printStackTrace();
            ToastUtil.showToast(this, "反射执行方法失败");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Resources getResources() {
        return SQwanCore.getInstance().getResources(super/*android.app.Activity*/.getResources());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ClassLoader getClassLoader() {
        return SQwanCore.getInstance().getClassLoader(super/*android.app.Activity*/.getClassLoader());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivity(Intent intent) {
        SQwanCore.getInstance().startActivity(intent);
        super/*android.app.Activity*/.startActivity(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivityForResult(Intent intent, int i) {
        SQwanCore.getInstance().startActivityForResult(intent, i);
        super/*android.app.Activity*/.startActivityForResult(intent, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super/*android.app.Activity*/.onRequestPermissionsResult(i, strArr, iArr);
        SQwanCore.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onActivityResult(int i, int i2, Intent intent) {
        super/*android.app.Activity*/.onActivityResult(i, i2, intent);
        SQwanCore.getInstance().onActivityResult(i, i2, intent);
    }
}
