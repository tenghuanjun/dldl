package com.sy37sdk.account.face.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliyun.aliyunface.api.ZIMCallback;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.api.ZIMFacadeBuilder;
import com.aliyun.aliyunface.api.ZIMResponse;
import com.plugin.standard.BaseActivity;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tools.Logger;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.base.L;
import com.sqwan.common.data.SpannableEntity;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.SpannableHelper;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.activebefore.ActiveBeforeManager;
import com.sy37sdk.account.face.FaceActivityHelper;
import com.sy37sdk.account.face.OnFaceVerifyListener;
import com.sy37sdk.account.face.ui.NetErrorDialog;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifyConfirmActivity extends BaseActivity {
    public static final String BUNDLE_USER_CARD = "user_card";
    public static final String BUNDLE_USER_NAME = "user_name";
    public static final String BUNDLE_VERIFY_NEED_SHOW = "verify_need_show";
    public static final String BUNDLE_VERIFY_STATUS = "verify_status";
    public static final String BUNDLE_VERIFY_TIP = "verify_tip";
    public static final int REQUEST_CODE = 10000;
    public static final int RESULT_CODE = 10001;
    private static final int RETRY_COUNT = 1;
    public static final boolean VERIFY_NEED_FINISH = false;
    public static final int VERIFY_STATUS_FAIL = 0;
    public static final int VERIFY_STATUS_SUCCESS = 1;
    private View backView;
    private View helpView;
    private boolean isClause;
    private ImageView ivClause;
    private CameraPermissionDialog permissionDialog;
    private View privacyView;
    private TextView tvClause;
    private TextView tvConfirmTip;
    private TextView tvUserCard;
    private TextView tvUserName;
    private TextView tvVerify;
    private String userCard;
    private String userName;
    private VerifyValidLoadingDialog verifyValidLoadingDialog;
    private String[] permission = {"android.permission.CAMERA"};
    private String[] permissionDesc = {"相机权限：协助用户找回账号密码和运用人脸识别验证用户身份"};
    private String verifyTip = "";
    private int retryCount = 0;

    static /* synthetic */ int access$508(FaceVerifyConfirmActivity faceVerifyConfirmActivity) {
        int i = faceVerifyConfirmActivity.retryCount;
        faceVerifyConfirmActivity.retryCount = i + 1;
        return i;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FaceActivityHelper.requestFull((Activity) getContext());
        setContentView(SqResUtils.getLayoutId(getContext(), "sysq_face_confirm_activity"));
        FaceActivityHelper.initStatusView((Activity) getContext());
        initData();
        initView();
        initEvent();
    }

    private void initView() {
        this.backView = findViewById(SqResUtils.getId(getContext(), "back"));
        this.helpView = findViewById(SqResUtils.getId(getContext(), "help"));
        this.tvConfirmTip = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_confirm_tip"));
        this.tvUserName = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_user_name"));
        this.tvUserCard = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_user_card"));
        this.privacyView = findViewById(SqResUtils.getId(getContext(), "ll_privacy"));
        this.ivClause = (ImageView) findViewById(SqResUtils.getId(getContext(), "iv_clause"));
        this.tvVerify = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_start_verify"));
        this.tvClause = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_clause"));
        this.tvConfirmTip.setText(this.verifyTip);
        this.tvUserName.setText(this.userName);
        this.tvUserCard.setText(this.userCard);
        setSpannable();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            this.userName = extras.getString(BUNDLE_USER_NAME);
            this.userCard = extras.getString(BUNDLE_USER_CARD);
            this.verifyTip = extras.getString(BUNDLE_VERIFY_TIP);
        }
        if (TextUtils.isEmpty(this.userName)) {
            this.userName = "";
        }
        if (TextUtils.isEmpty(this.userCard)) {
            this.userCard = "";
        }
        if (TextUtils.isEmpty(this.verifyTip)) {
            this.verifyTip = "";
        }
    }

    private void initEvent() {
        this.backView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_VERIFY_NEED_SHOW, true);
                ((Activity) FaceVerifyConfirmActivity.this.getContext()).setResult(10001, intent);
                FaceVerifyConfirmActivity.this.finish();
            }
        });
        this.helpView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppUtils.toSQWebUrl(L.getActivity(), UrlConstant.FACE_VERIFY_HELP_PAGE, "帮助");
            }
        });
        this.privacyView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceVerifyConfirmActivity.this.toggleClause();
            }
        });
        this.tvVerify.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FaceVerifyConfirmActivity.this.isClause) {
                    FaceVerifyConfirmActivity.this.handlePermission();
                } else {
                    ToastUtil.showToast("请勾选同意用户协议和隐私条款");
                }
            }
        });
    }

    private void setSpannable() {
        final String str = ActiveBeforeManager.getInstance().userProtocolInfo.userprotol;
        final String str2 = ActiveBeforeManager.getInstance().userProtocolInfo.privacyprotol;
        SpannableEntity spannableEntity = new SpannableEntity();
        spannableEntity.setLink(str);
        spannableEntity.setTag("《用户协议》");
        spannableEntity.setOnClickListener(new SpannableEntity.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.5
            @Override // com.sqwan.common.data.SpannableEntity.OnClickListener
            public void onClick() {
                SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(FaceVerifyConfirmActivity.this.getContext());
                sQWebViewDialog.setCancelable(true);
                sQWebViewDialog.setUrl(str);
                sQWebViewDialog.show();
            }
        });
        SpannableEntity spannableEntity2 = new SpannableEntity();
        spannableEntity2.setLink(str2);
        spannableEntity2.setTag("《隐私条款》");
        spannableEntity2.setOnClickListener(new SpannableEntity.OnClickListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.6
            @Override // com.sqwan.common.data.SpannableEntity.OnClickListener
            public void onClick() {
                SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(FaceVerifyConfirmActivity.this.getContext());
                sQWebViewDialog.setCancelable(true);
                sQWebViewDialog.setUrl(str2);
                sQWebViewDialog.show();
            }
        });
        SpannableHelper.handleSpannableHighLineClick(this.tvClause, SqResUtils.getStringByName(getContext(), "sysq_txt_face_verify_privacy"), spannableEntity, spannableEntity2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleClause() {
        if (this.isClause) {
            this.ivClause.setImageResource(SqResUtils.getDrawableId(getContext(), "sysq_ic_check_face_verify_normal"));
            this.isClause = false;
        } else {
            this.ivClause.setImageResource(SqResUtils.getDrawableId(getContext(), "sysq_ic_check_face_verify_selected"));
            this.isClause = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVerify() {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition);
        ZIMFacade.install(getContext());
        requestCertifyId();
    }

    private void requestCertifyId() {
        new AccountRequestManager(getContext()).requestCertifyId(ZIMFacade.getMetaInfos(getContext()), new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.7
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                Logger.error("request certify id failed, response bean: %s", str);
                ToastUtil.showToast(str);
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, "-1");
                map.put(SqTrackKey.reason_fail, "请求certifyId失败 " + str2);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                try {
                    FaceVerifyConfirmActivity.this.startCertifyFace(jSONObject.optString("certify_id"));
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("请求认证失败");
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.fail_code, "-1");
                    map.put(SqTrackKey.reason_fail, "请求certifyId 后台返回数据解析错误 " + e.getMessage());
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                Logger.info("请求人脸验证id onFailure：code=" + i + " msg=" + str, new Object[0]);
                FaceVerifyConfirmActivity.this.showNetErrorDialog();
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, i + "");
                map.put(SqTrackKey.reason_fail, "请求certifyId网络错误 " + str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCertifyFace(final String str) {
        ZIMFacade zIMFacadeCreate = ZIMFacadeBuilder.create(getContext());
        HashMap map = new HashMap();
        map.put("ext_params_key_screen_orientation", "ext_params_val_screen_port");
        zIMFacadeCreate.verify(str, false, map, new ZIMCallback() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.8
            public boolean response(ZIMResponse zIMResponse) {
                Logger.info("人脸验证回调：msg = " + zIMResponse.msg + " code = " + zIMResponse.code + " reason = " + zIMResponse.reason + " deviceToken = " + zIMResponse.deviceToken + " videoFilePath = " + zIMResponse.videoFilePath, new Object[0]);
                if (!zIMResponse.reason.contains("Z1024") || FaceVerifyConfirmActivity.this.retryCount >= 1) {
                    FaceVerifyConfirmActivity.this.validateCertifyFace(str, zIMResponse);
                } else {
                    HashMap map2 = new HashMap();
                    map2.put(SqTrackKey.fail_code, "1001-Z1024");
                    map2.put(SqTrackKey.reason_fail, "SDK认证流程正在进行中，请等待本地认证流程完成后再发起新调用。");
                    map2.put(SqTrackKey.face_verify_id, str);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map2);
                    FaceVerifyConfirmActivity.access$508(FaceVerifyConfirmActivity.this);
                    FaceVerifyConfirmActivity.this.startVerify();
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateCertifyFace(final String str, final ZIMResponse zIMResponse) {
        if (zIMResponse.code == 2006 || zIMResponse.code == 1000) {
            showVerifyValidLoadingDialog();
        }
        new AccountRequestManager(getContext()).checkValidCertify(str, new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.9
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                FaceVerifyConfirmActivity.this.dismissVerifyValidLoadingDialog();
                Logger.error("request validateCertifyFace failed, response bean: %s", str2);
                FaceVerifyConfirmActivity.this.showVerifyFail();
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, "-1");
                map.put(SqTrackKey.reason_fail, "验证失败");
                map.put(SqTrackKey.face_verify_id, str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                FaceVerifyConfirmActivity.this.dismissVerifyValidLoadingDialog();
                try {
                    int iOptInt = jSONObject.optInt("passed");
                    String strOptString = jSONObject.optString("passed_msg");
                    if (iOptInt == 1) {
                        HashMap map = new HashMap();
                        map.put(SqTrackKey.face_verify_id, str);
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_succ, map);
                        FaceVerifyConfirmActivity.this.showVerifySuccess();
                    } else {
                        HashMap map2 = new HashMap();
                        map2.put(SqTrackKey.fail_code, zIMResponse.code + "-" + zIMResponse.reason);
                        map2.put(SqTrackKey.reason_fail, "验证不通过 " + strOptString + " - " + zIMResponse.msg);
                        map2.put(SqTrackKey.face_verify_id, str);
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map2);
                        if (1003 == zIMResponse.code) {
                            SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_FACE_CANCEL);
                            ((IAccountMod) ModHelper.get(IAccountMod.class)).webEnLogin(false);
                            Intent intent = new Intent();
                            intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_VERIFY_NEED_SHOW, false);
                            ((Activity) FaceVerifyConfirmActivity.this.getContext()).setResult(10001, intent);
                            FaceVerifyConfirmActivity.this.finish();
                        } else if (2006 == zIMResponse.code) {
                            FaceVerifyConfirmActivity.this.showVerifyFail();
                        } else if (TextUtils.isEmpty(zIMResponse.msg)) {
                            ToastUtil.showToast(zIMResponse.code + "-" + zIMResponse.reason + " 认证失败");
                        } else {
                            ToastUtil.showToast(zIMResponse.code + "- " + zIMResponse.msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("数据解析错误，认证失败");
                    HashMap map3 = new HashMap();
                    map3.put(SqTrackKey.fail_code, "-1");
                    map3.put(SqTrackKey.reason_fail, "验证发生数据解析错误 " + e.getMessage());
                    map3.put(SqTrackKey.face_verify_id, str);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map3);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                FaceVerifyConfirmActivity.this.dismissVerifyValidLoadingDialog();
                FaceVerifyConfirmActivity.this.showNetErrorDialog();
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, i + "");
                map.put(SqTrackKey.reason_fail, "验证发生网络错误 " + str2);
                map.put(SqTrackKey.face_verify_id, str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.face_recognition_fail, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNetErrorDialog() {
        NetErrorDialog netErrorDialog = new NetErrorDialog(getContext());
        netErrorDialog.setOnClickNetListener(new NetErrorDialog.OnClickNetListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.10
            @Override // com.sy37sdk.account.face.ui.NetErrorDialog.OnClickNetListener
            public void clickRight() {
            }

            @Override // com.sy37sdk.account.face.ui.NetErrorDialog.OnClickNetListener
            public void clickLeft() {
                AppUtils.toSQWebUrl(L.getActivity(), UrlConstant.FACE_VERIFY_CUSTOMER_PAGE, "客服");
            }
        });
        netErrorDialog.show();
    }

    private void showVerifyValidLoadingDialog() {
        if (this.verifyValidLoadingDialog == null) {
            VerifyValidLoadingDialog verifyValidLoadingDialog = new VerifyValidLoadingDialog(getContext());
            this.verifyValidLoadingDialog = verifyValidLoadingDialog;
            verifyValidLoadingDialog.setCancelable(false);
        }
        this.verifyValidLoadingDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissVerifyValidLoadingDialog() {
        VerifyValidLoadingDialog verifyValidLoadingDialog = this.verifyValidLoadingDialog;
        if (verifyValidLoadingDialog != null) {
            verifyValidLoadingDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVerifySuccess() {
        FaceVerifySuccessDialog faceVerifySuccessDialog = new FaceVerifySuccessDialog(getContext());
        faceVerifySuccessDialog.setOnFaceVerifyListener(new OnFaceVerifyListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.11
            @Override // com.sy37sdk.account.face.OnFaceVerifyListener
            public void onVerifyFail(String str) {
            }

            @Override // com.sy37sdk.account.face.OnFaceVerifyListener
            public void onVerifySuccess() {
                Intent intent = new Intent();
                intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_VERIFY_NEED_SHOW, false);
                intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_VERIFY_STATUS, true);
                ((Activity) FaceVerifyConfirmActivity.this.getContext()).setResult(10001, intent);
                FaceVerifyConfirmActivity.this.finish();
            }
        });
        faceVerifySuccessDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVerifyFail() {
        FaceVerifyFailDialog faceVerifyFailDialog = new FaceVerifyFailDialog(getContext());
        faceVerifyFailDialog.setOnFaceVerifyListener(new OnFaceVerifyListener() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.12
            @Override // com.sy37sdk.account.face.OnFaceVerifyListener
            public void onVerifySuccess() {
            }

            @Override // com.sy37sdk.account.face.OnFaceVerifyListener
            public void onVerifyFail(String str) {
                Intent intent = new Intent();
                intent.putExtra(FaceVerifyConfirmActivity.BUNDLE_VERIFY_NEED_SHOW, false);
                ((Activity) FaceVerifyConfirmActivity.this.getContext()).setResult(10001, intent);
                FaceVerifyConfirmActivity.this.finish();
            }
        });
        faceVerifyFailDialog.show();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionHelper.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePermission() {
        if (!PermissionHelper.getInstance().checkPermissions(this.permission)) {
            PermissionHelper.getInstance().requestPermissions(getContext(), this.permission, this.permissionDesc, 1110, new PermissionHelper.PermissionCallback() { // from class: com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity.13
                @Override // com.sqwan.common.util.PermissionHelper.PermissionCallback
                public void onRequestPermissionsResult(String[] strArr, int[] iArr) {
                    int length = iArr.length;
                    boolean z = false;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        if (iArr[i] != 0) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                    if (z) {
                        FaceVerifyConfirmActivity.this.showPermissionTipDialog();
                    } else {
                        FaceVerifyConfirmActivity.this.startVerify();
                    }
                }
            });
        } else {
            startVerify();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionTipDialog() {
        if (this.permissionDialog == null) {
            this.permissionDialog = new CameraPermissionDialog(getContext());
        }
        this.permissionDialog.show();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            Intent intent = new Intent();
            intent.putExtra(BUNDLE_VERIFY_NEED_SHOW, true);
            ((Activity) getContext()).setResult(10001, intent);
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
