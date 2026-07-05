package com.sy37sdk.account.face;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.CheckClassUtils;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.face.data.FaceVerifyData;
import com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity;
import com.sy37sdk.account.face.ui.FaceVerifyExitDialog;
import com.sy37sdk.account.face.ui.FaceVerifyWarningDialog;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifyManager {
    private static final String TAG = "【FaceVerify】";
    private static FaceVerifyManager sInstance;
    private FaceVerifyData faceVerifyData;
    private SQResultListener faceVerifyResultListener;
    private FaceVerifyWarningDialog faceVerifyWarningDialog;
    protected Context mContext;

    private FaceVerifyManager(Context context) {
        this.mContext = context;
    }

    public void setFaceVerifyData(FaceVerifyData faceVerifyData) {
        this.faceVerifyData = faceVerifyData;
    }

    public FaceVerifyData getFaceVerifyData() {
        return this.faceVerifyData;
    }

    public static FaceVerifyManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (FaceVerifyManager.class) {
                if (sInstance == null) {
                    sInstance = new FaceVerifyManager(context);
                }
            }
        }
        return sInstance;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        SQResultListener sQResultListener;
        SQLog.v("【FaceVerify】onActivityResult: " + i);
        if (i != 10000 || i2 != 10001 || intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras.getBoolean(FaceVerifyConfirmActivity.BUNDLE_VERIFY_NEED_SHOW)) {
            showFaceVerifyWarningDialog(this.faceVerifyResultListener);
        }
        if (!extras.getBoolean(FaceVerifyConfirmActivity.BUNDLE_VERIFY_STATUS, false) || (sQResultListener = this.faceVerifyResultListener) == null) {
            return;
        }
        sQResultListener.onSuccess(new Bundle());
    }

    public void handleFaceVerify(SQResultListener sQResultListener) {
        this.faceVerifyResultListener = sQResultListener;
        if (!isAliFaceActivityExist()) {
            SQLog.w("【FaceVerify】不满足人脸认证版本");
            if (sQResultListener != null) {
                sQResultListener.onSuccess(new Bundle());
                return;
            }
            return;
        }
        checkNeedFaceVerify(sQResultListener);
    }

    public void showFaceVerifyWarningDialog(SQResultListener sQResultListener) {
        if (this.faceVerifyWarningDialog == null) {
            FaceVerifyWarningDialog faceVerifyWarningDialog = new FaceVerifyWarningDialog(this.mContext, sQResultListener);
            this.faceVerifyWarningDialog = faceVerifyWarningDialog;
            faceVerifyWarningDialog.setCancelable(false);
        }
        this.faceVerifyWarningDialog.show();
    }

    public void dismissFaceVerifyWarningDialog() {
        FaceVerifyWarningDialog faceVerifyWarningDialog = this.faceVerifyWarningDialog;
        if (faceVerifyWarningDialog != null) {
            faceVerifyWarningDialog.dismiss();
        }
    }

    public void showExitFaceVerifyDialog(Context context, FaceVerifyExitDialog.OnClickExitListener onClickExitListener) {
        FaceVerifyExitDialog faceVerifyExitDialog = new FaceVerifyExitDialog(context);
        faceVerifyExitDialog.setCancelable(false);
        faceVerifyExitDialog.setOnClickExitListener(onClickExitListener);
        faceVerifyExitDialog.show();
    }

    public void checkNeedFaceVerify(final SQResultListener sQResultListener) {
        new AccountRequestManager(this.mContext).needFaceVerify(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.face.FaceVerifyManager.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onSuccess(new Bundle());
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                try {
                    String string = jSONObject.toString();
                    UrlConstant.refreshFaceUrls(string);
                    FaceVerifyData faceVerifyDataJsonToObject = FaceVerifyData.jsonToObject(string);
                    FaceVerifyManager.this.setFaceVerifyData(faceVerifyDataJsonToObject);
                    if (faceVerifyDataJsonToObject.isNeedVerify()) {
                        SQLog.w("【FaceVerify】需要人脸认证");
                        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.faceVerify, SqTrackBtn.SqTrackBtnExt.faceVerify);
                        FaceVerifyManager.this.showFaceVerifyWarningDialog(sQResultListener);
                    } else {
                        SQLog.d("【FaceVerify】不需要人脸认证");
                        if (sQResultListener != null) {
                            sQResultListener.onSuccess(new Bundle());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    SQResultListener sQResultListener2 = sQResultListener;
                    if (sQResultListener2 != null) {
                        sQResultListener2.onSuccess(new Bundle());
                    }
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onSuccess(new Bundle());
                }
            }
        });
    }

    private boolean isAliFaceActivityExist() {
        return CheckClassUtils.classExist("com.aliyun.aliyunface.ui.ToygerActivity");
    }
}
