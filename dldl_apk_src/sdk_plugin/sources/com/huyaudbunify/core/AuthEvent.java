package com.huyaudbunify.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.duowan.live.common.utils.LoginUtils;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.route.FunctionRouter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AuthEvent {
    private static final int json_err_code = -11501;
    private static final String json_err_desc = "解析数据失败";
    private static final int timeout_err_code = -99;
    static boolean useSeparateTimeoutEvent = true;

    public interface OpCmd {
        public static final int OP_ANONYMOUS_EVENT = 5;
        public static final int OP_CHECK_MOD_PWD = 10;
        public static final int OP_CHECK_REGISTER = 12;
        public static final int OP_CHECK_USER = 13;
        public static final int OP_CREDIT_RENEW = 7;
        public static final int OP_INVALID_EVENT = -1;
        public static final int OP_LOGIN_EVENT = 1;
        public static final int OP_OPEN_APP_CHECK = 100;
        public static final int OP_OPEN_LOGIN = 101;
        public static final int OP_QRCODE_CANCEL = 16;
        public static final int OP_QRCODE_CHECK = 14;
        public static final int OP_QRCODE_CONFIRM = 15;
        public static final int OP_QUERY_EVENT = 4;
        public static final int OP_REFRESH_PIC_EVENT = 3;
        public static final int OP_REGISTER = 9;
        public static final int OP_SEND_SMS_EVENT = 2;
        public static final int OP_SMS_MOD_PWD = 11;
        public static final int OP_TIME_OUT = 6;
        public static final int OP_VERIFY_SMSCODE = 8;
    }

    public static class ThirdPartyInfo implements Serializable {
        private static final long serialVersionUID = 14111082204340701L;
        public String gender;
        public String imageUrl;
        public String nickname;
        public String openidYYuid;
        public String reserve1;
        public String reserve2;
        public String reserve3;
        public String uid;
        public String unionId;
    }

    public interface UIAction {
        public static final int CREDIT_INVALID = 3;
        public static final int FAILED = 1;
        public static final int NEXT_VERIFY = 2;
        public static final int OPEN_URL = 6;
        public static final int SERVER_HAS_NOT_RECEIVED_SMS = 5;
        public static final int SUCCESS = 0;
        public static final int VERIFY_FAILED = 4;
    }

    public static class NextVerify implements Serializable {
        private static final long serialVersionUID = 14282082204340701L;
        public String data;
        public int dataType;
        public int strategy = 0;
        public String selectTitle = "";
        public String promptTitle = "";
        public String promptContent = "";

        public Bitmap getPictureCodeBitmap() {
            try {
                byte[] bArrDecode = Base64.decode(this.data, 0);
                return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public String getJSContent() {
            try {
                return new String(Base64.decode(this.data, 0));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class AuthBaseEvent implements Serializable {
        private static final long serialVersionUID = 1428443204340701L;
        protected int OP_CMD = -1;
        protected int json_ver = 0;
        protected String json = "{}";
        public String extParam = "";
        private transient JSONObject mRootJsonObject = new JSONObject();

        public String getContext() {
            return null;
        }

        public String getUid() {
            return null;
        }

        public String getUser() {
            return null;
        }

        protected JSONObject getParentJsonObject() {
            return this.mRootJsonObject;
        }

        protected int opCmd() {
            return this.OP_CMD;
        }

        protected int getJsonVer() {
            return this.json_ver;
        }

        public void unmarshall(byte[] bArr) {
            try {
                this.json = new String(bArr);
                JSONObject jSONObject = new JSONObject(this.json);
                this.mRootJsonObject = jSONObject;
                this.OP_CMD = jSONObject.optInt("op_cmd");
                this.json_ver = this.mRootJsonObject.optInt("json_ver");
            } catch (JSONException e) {
                e.printStackTrace();
                this.OP_CMD = -1;
                this.mRootJsonObject = null;
                if (bArr == null) {
                    return;
                }
                int length = bArr.length;
            }
        }
    }

    public static class LoginEvent extends AuthBaseEvent {
        public static final int OP_CMD = 1;
        private static final long serialVersionUID = -5793167123773932482L;
        public String appCommonData;
        public String context;
        public String credit;
        public String description;
        public String emailMask;
        public int errCode;
        public String mobileMask;
        public String passport;
        public int regOrigin;
        public Map<String, String> thirdParams;
        public ThirdPartyInfo thirdPartyInfo;
        public int uiAction;
        public String uid;
        public String user;
        public String userId;
        public int userIdState;
        public String yyid;
        public boolean isNewUser = false;
        public boolean needModifyPassword = false;
        public ArrayList<NextVerify> nextVerifies = null;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 1;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUid() {
            return this.uid;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUser() {
            return this.user;
        }

        public Bitmap getPictureCodeBitmap() {
            ArrayList<NextVerify> arrayList = this.nextVerifies;
            if (arrayList == null) {
                return null;
            }
            for (NextVerify nextVerify : arrayList) {
                if (nextVerify.strategy == 1) {
                    return nextVerify.getPictureCodeBitmap();
                }
            }
            return null;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("login_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.uid = null;
                this.yyid = null;
                this.passport = null;
                this.mobileMask = null;
                this.credit = null;
                this.context = null;
                this.nextVerifies = null;
                this.thirdPartyInfo = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.uid = jSONObjectOptJSONObject.optString("uid");
            this.yyid = jSONObjectOptJSONObject.optString("yyid");
            this.passport = jSONObjectOptJSONObject.optString("passport");
            this.mobileMask = jSONObjectOptJSONObject.optString("mobile_mask");
            this.emailMask = jSONObjectOptJSONObject.optString("email_mask");
            this.credit = jSONObjectOptJSONObject.optString("credit");
            this.isNewUser = "1".equals(jSONObjectOptJSONObject.optString("new_user"));
            this.needModifyPassword = "1".equals(jSONObjectOptJSONObject.optString("need_modpwd"));
            this.context = jSONObjectOptJSONObject.optString("context");
            this.nextVerifies = new ArrayList<>();
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("next_verify");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject2 != null) {
                        NextVerify nextVerify = new NextVerify();
                        nextVerify.strategy = jSONObjectOptJSONObject2.optInt("strategy");
                        nextVerify.selectTitle = jSONObjectOptJSONObject2.optString("select_title");
                        nextVerify.promptTitle = jSONObjectOptJSONObject2.optString("prompt_title");
                        nextVerify.promptContent = jSONObjectOptJSONObject2.optString("prompt_content");
                        nextVerify.dataType = jSONObjectOptJSONObject2.optInt("data_type");
                        nextVerify.data = jSONObjectOptJSONObject2.optString(FunctionRouter.KEY_DATA);
                        this.nextVerifies.add(nextVerify);
                    }
                }
            }
            JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("3rd");
            if (jSONObjectOptJSONObject3 != null) {
                ThirdPartyInfo thirdPartyInfo = new ThirdPartyInfo();
                this.thirdPartyInfo = thirdPartyInfo;
                thirdPartyInfo.uid = jSONObjectOptJSONObject3.optString("3rd_uid");
                this.thirdPartyInfo.nickname = jSONObjectOptJSONObject3.optString("3rd_nickname");
                this.thirdPartyInfo.imageUrl = jSONObjectOptJSONObject3.optString("3rd_img");
                this.thirdPartyInfo.gender = jSONObjectOptJSONObject3.optString("3rd_gen");
                this.thirdPartyInfo.unionId = jSONObjectOptJSONObject3.optString("3rd_unionid");
                this.thirdPartyInfo.openidYYuid = jSONObjectOptJSONObject3.optString("busiYyuid");
                this.thirdPartyInfo.reserve1 = jSONObjectOptJSONObject3.optString("reserve1");
                this.thirdPartyInfo.reserve2 = jSONObjectOptJSONObject3.optString("reserve2");
                this.thirdPartyInfo.reserve3 = jSONObjectOptJSONObject3.optString("reserve3");
            }
        }
    }

    public static class SmsModPwdEvent extends AuthBaseEvent {
        public static final int OP_CMD = 11;
        private static final long serialVersionUID = -496199085361871679L;
        public String context;
        public String credit;
        public String description;
        public String emailMask;
        public int errCode;
        public String mobileMask;
        public ArrayList<NextVerify> nextVerifies = null;
        public String passport;
        public int uiAction;
        public String uid;
        public String user;
        public String yyid;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 11;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUid() {
            return this.uid;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUser() {
            return this.user;
        }

        public LoginEvent toLoginEvent() {
            LoginEvent loginEvent = new LoginEvent();
            loginEvent.errCode = this.errCode;
            loginEvent.description = this.description;
            loginEvent.user = this.user;
            loginEvent.uid = this.uid;
            loginEvent.yyid = this.yyid;
            loginEvent.passport = this.passport;
            loginEvent.mobileMask = this.mobileMask;
            loginEvent.emailMask = this.emailMask;
            loginEvent.credit = this.credit;
            loginEvent.isNewUser = false;
            loginEvent.needModifyPassword = false;
            loginEvent.nextVerifies = null;
            loginEvent.thirdPartyInfo = null;
            loginEvent.uiAction = this.uiAction;
            loginEvent.context = this.context;
            return loginEvent;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("modpwd_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.uid = null;
                this.yyid = null;
                this.passport = null;
                this.mobileMask = null;
                this.credit = null;
                this.context = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.uid = jSONObjectOptJSONObject.optString("uid");
            this.yyid = jSONObjectOptJSONObject.optString("yyid");
            this.passport = jSONObjectOptJSONObject.optString("passport");
            this.mobileMask = jSONObjectOptJSONObject.optString("mobile_mask");
            this.emailMask = jSONObjectOptJSONObject.optString("email_mask");
            this.credit = jSONObjectOptJSONObject.optString("credit");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class SendSmsEvent extends AuthBaseEvent {
        public static final int OP_CMD = 2;
        private static final long serialVersionUID = -5490440308837193652L;
        public String context;
        public String description;
        public int errCode;
        public int uiAction;
        public ArrayList<NextVerify> nextVerifies = null;
        public boolean isUserExist = true;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 2;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        public Bitmap getPictureCodeBitmap() {
            ArrayList<NextVerify> arrayList = this.nextVerifies;
            if (arrayList == null) {
                return null;
            }
            for (NextVerify nextVerify : arrayList) {
                if (nextVerify.strategy == 1) {
                    return nextVerify.getPictureCodeBitmap();
                }
            }
            return null;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("sendsms_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.context = null;
                this.nextVerifies = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.isUserExist = jSONObjectOptJSONObject.optInt("is_user_exist", 999) != 0;
            this.context = jSONObjectOptJSONObject.optString("context");
            this.nextVerifies = new ArrayList<>();
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("next_verify");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject2 != null) {
                        NextVerify nextVerify = new NextVerify();
                        nextVerify.strategy = jSONObjectOptJSONObject2.optInt("strategy");
                        nextVerify.selectTitle = jSONObjectOptJSONObject2.optString("select_title");
                        nextVerify.promptTitle = jSONObjectOptJSONObject2.optString("prompt_title");
                        nextVerify.promptContent = jSONObjectOptJSONObject2.optString("prompt_content");
                        nextVerify.dataType = jSONObjectOptJSONObject2.optInt("data_type");
                        nextVerify.data = jSONObjectOptJSONObject2.optString(FunctionRouter.KEY_DATA);
                        this.nextVerifies.add(nextVerify);
                    }
                }
            }
        }
    }

    public static class VerifySmsCodeEvent extends AuthBaseEvent {
        public static final int OP_CMD = 8;
        private static final long serialVersionUID = 2349204824162301847L;
        public String context;
        public String description;
        public int errCode;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 8;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("verify_smscode_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.context = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class RegisterEvent extends AuthBaseEvent {
        public static final int OP_CMD = 9;
        private static final long serialVersionUID = 3860264007062893452L;
        public String context;
        public String description;
        public int errCode;
        public String passport;
        public int uiAction;
        public String uid;
        public String user;
        public String yyid;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 9;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUid() {
            return this.uid;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUser() {
            return this.user;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("register_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.context = null;
                this.uid = null;
                this.yyid = null;
                this.passport = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uid = jSONObjectOptJSONObject.optString("uid");
            this.yyid = jSONObjectOptJSONObject.optString("yyid");
            this.passport = jSONObjectOptJSONObject.optString("passport");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class CheckModPwdEvent extends AuthBaseEvent {
        public static final int OP_CMD = 10;
        private static final long serialVersionUID = 1435840643431856670L;
        public String context;
        public String description;
        public String emailMask;
        public int errCode;
        public boolean isLoginMobile = false;
        public String mobileMask;
        public int uiAction;
        public String url;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 10;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("check_modpwd_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.context = null;
                this.mobileMask = null;
                this.emailMask = null;
                this.url = null;
                this.isLoginMobile = false;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.mobileMask = jSONObjectOptJSONObject.optString("mobile_mask");
            this.emailMask = jSONObjectOptJSONObject.optString("email_mask");
            this.url = jSONObjectOptJSONObject.optString("url");
            this.isLoginMobile = "1".equals(jSONObjectOptJSONObject.optString("is_login_mobile"));
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class CheckRegisterEvent extends AuthBaseEvent {
        public static final int OP_CMD = 12;
        private static final long serialVersionUID = 1435840643431877558L;
        public String context;
        public String description;
        public int errCode;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 12;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("check_reg_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.context = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class RefreshPicEvent extends AuthBaseEvent {
        public static final int OP_CMD = 3;
        private static final long serialVersionUID = 6944767824313949482L;
        public String context;
        public String description;
        public int errCode;
        public String pic;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 3;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        public Bitmap getPictureCodeBitmap() {
            try {
                byte[] bArrDecode = Base64.decode(this.pic, 0);
                return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("refreshpic_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.pic = null;
                this.context = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.pic = jSONObjectOptJSONObject.optString("pic");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class QueryEvent extends AuthBaseEvent {
        public static final int OP_CMD = 4;
        private static final long serialVersionUID = 8986369062898690362L;
        public String appid;
        public String context;
        public String description;
        public int errCode;
        public String errMsg;
        public String otp;
        public String token;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 4;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("query_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.appid = null;
                this.token = null;
                this.otp = null;
                this.context = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.errMsg = jSONObjectOptJSONObject.optString("errmsg");
            this.appid = jSONObjectOptJSONObject.optString("appid");
            this.token = jSONObjectOptJSONObject.optString("token");
            this.otp = jSONObjectOptJSONObject.optString("otp");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    @Deprecated
    public static class CreditRenewEvent extends AuthBaseEvent {
        public static final int OP_CMD = 7;
        private static final long serialVersionUID = 7288254494360141668L;
        public String credit;
        public String passport;
        public String uid;
        public String userId;
        public int userIdState;
        public String yyid;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 7;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUid() {
            return this.uid;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("credit_renew");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.uid = null;
                this.yyid = null;
                this.passport = null;
                this.credit = null;
                return;
            }
            this.uid = jSONObjectOptJSONObject.optString("uid");
            this.yyid = jSONObjectOptJSONObject.optString("yyid");
            this.passport = jSONObjectOptJSONObject.optString("passport");
            this.credit = jSONObjectOptJSONObject.optString("credit");
        }
    }

    public static class QRCodeCheckEvent extends AuthBaseEvent {
        public static final int OP_CMD = 14;
        private static final long serialVersionUID = 328843360141668L;
        public String authAppId;
        public String authAppInfo;
        public String context;
        public String description;
        public int errCode;
        public String passport;
        public String pcContext;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 14;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("qrcode_check_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.authAppId = null;
                this.authAppInfo = null;
                this.pcContext = null;
                this.passport = null;
                this.context = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.authAppId = jSONObjectOptJSONObject.optString("appid_auth");
            this.authAppInfo = jSONObjectOptJSONObject.optString("appinfo_auth");
            this.pcContext = jSONObjectOptJSONObject.optString("pc_context");
            this.passport = jSONObjectOptJSONObject.optString("passort");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class QRCodeConfirmEvent extends AuthBaseEvent {
        public static final int OP_CMD = 15;
        private static final long serialVersionUID = 2134567860141668L;
        public String context;
        public String description;
        public int errCode;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 15;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("qrcode_confirm_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
            } else {
                this.errCode = jSONObjectOptJSONObject.optInt("errcode");
                this.description = jSONObjectOptJSONObject.optString("description");
                this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
                this.context = jSONObjectOptJSONObject.optString("context");
            }
        }
    }

    public static class QRCodeCancelEvent extends AuthBaseEvent {
        public static final int OP_CMD = 16;
        private static final long serialVersionUID = 12435363254668L;
        public String context;
        public String description;
        public int errCode;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 16;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("qrcode_cancel_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
            } else {
                this.errCode = jSONObjectOptJSONObject.optInt("errcode");
                this.description = jSONObjectOptJSONObject.optString("description");
                this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
                this.context = jSONObjectOptJSONObject.optString("context");
            }
        }
    }

    public static class AnonymousEvent extends AuthBaseEvent {
        public static final int OP_CMD = 5;
        private static final long serialVersionUID = -7783307654193055887L;
        public String context;
        public String description;
        public int errCode;
        public String passport;
        public String passwdSha1;
        public String picData;
        public int uiAction;
        public String uid;
        public String yyid;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 5;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUid() {
            return this.uid;
        }

        public Bitmap getPictureCodeBitmap() {
            String str = this.picData;
            if (str == null) {
                return null;
            }
            try {
                byte[] bArrDecode = Base64.decode(str, 0);
                return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("anonymous_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.uid = null;
                this.yyid = null;
                this.passport = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.uid = jSONObjectOptJSONObject.optString("uid");
            this.yyid = jSONObjectOptJSONObject.optString("yyid");
            this.passport = jSONObjectOptJSONObject.optString("passport");
            this.passwdSha1 = jSONObjectOptJSONObject.optString(LoginUtils.PASSWORD);
            this.picData = jSONObjectOptJSONObject.optString("pic");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class TimeoutEvent extends AuthBaseEvent {
        public static final int OP_CMD = 6;
        private static final long serialVersionUID = -5840156307025829903L;
        public String context;
        public String description;
        public String detail;
        private String op_cmd;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 6;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("timeout");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.context = null;
                return;
            }
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.detail = jSONObjectOptJSONObject.optString("detail");
            this.context = jSONObjectOptJSONObject.optString("context");
            this.op_cmd = jSONObjectOptJSONObject.optString("op_cmd");
            String.format(Locale.getDefault(), "timeout desc:%s, detail:%s", this.description, this.detail);
        }
    }

    public static class OpenCheckAppEvent extends AuthBaseEvent {
        public static final int OP_CMD = 100;
        private static final long serialVersionUID = 63241791231674323L;
        public String appIcon;
        public String appName;
        public int appType;
        public String context;
        public String description;
        public int errCode;
        public boolean hasAuth;
        public int uiAction;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 100;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("app_check_res");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.appName = jSONObjectOptJSONObject.optString("app_name");
            this.appIcon = jSONObjectOptJSONObject.optString("app_icon");
            this.hasAuth = "1".equals(jSONObjectOptJSONObject.optString("app_authed"));
            this.appType = jSONObjectOptJSONObject.optInt("app_type");
            this.context = jSONObjectOptJSONObject.optString("context");
        }
    }

    public static class OpenLoginEvent extends AuthBaseEvent {
        public static final int OP_CMD = 101;
        private static final long serialVersionUID = -5793167123773932482L;
        public String accessCode;
        public int appType;
        public String context;
        public String credit;
        public String description;
        public String emailMask;
        public int errCode;
        public String mobileMask;
        public String openid;
        public String passport;
        public String thirdPartyCredit;
        public int uiAction;
        public String uid;
        public String user;
        public String yyid;
        public boolean isNewUser = false;
        public boolean needModifyPassword = false;
        public ArrayList<NextVerify> nextVerifies = null;

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public int opCmd() {
            return 101;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getContext() {
            return this.context;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUid() {
            return this.uid;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public String getUser() {
            return this.user;
        }

        public Bitmap getPictureCodeBitmap() {
            ArrayList<NextVerify> arrayList = this.nextVerifies;
            if (arrayList == null) {
                return null;
            }
            for (NextVerify nextVerify : arrayList) {
                if (nextVerify.strategy == 1) {
                    return nextVerify.getPictureCodeBitmap();
                }
            }
            return null;
        }

        @Override // com.huyaudbunify.core.AuthEvent.AuthBaseEvent
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            JSONObject parentJsonObject = getParentJsonObject();
            JSONObject jSONObjectOptJSONObject = parentJsonObject == null ? null : parentJsonObject.optJSONObject("login_res_auth");
            if (parentJsonObject == null || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("uiaction")) {
                this.errCode = AuthEvent.json_err_code;
                this.description = AuthEvent.json_err_desc;
                this.uiAction = 1;
                this.uid = null;
                this.yyid = null;
                this.passport = null;
                this.mobileMask = null;
                this.credit = null;
                this.context = null;
                this.nextVerifies = null;
                this.accessCode = null;
                this.thirdPartyCredit = null;
                return;
            }
            this.errCode = jSONObjectOptJSONObject.optInt("errcode");
            this.description = jSONObjectOptJSONObject.optString("description");
            this.uiAction = jSONObjectOptJSONObject.optInt("uiaction");
            this.uid = jSONObjectOptJSONObject.optString("uid");
            this.yyid = jSONObjectOptJSONObject.optString("yyid");
            this.passport = jSONObjectOptJSONObject.optString("passport");
            this.mobileMask = jSONObjectOptJSONObject.optString("mobile_mask");
            this.emailMask = jSONObjectOptJSONObject.optString("email_mask");
            this.credit = jSONObjectOptJSONObject.optString("credit");
            this.isNewUser = "1".equals(jSONObjectOptJSONObject.optString("new_user"));
            this.needModifyPassword = "1".equals(jSONObjectOptJSONObject.optString("need_modpwd"));
            this.thirdPartyCredit = jSONObjectOptJSONObject.optString("3rd_credit");
            this.appType = jSONObjectOptJSONObject.optInt("app_type");
            this.openid = jSONObjectOptJSONObject.optString(SqConstants.OPEN_ID);
            this.accessCode = jSONObjectOptJSONObject.optString("access_code");
            this.context = jSONObjectOptJSONObject.optString("context");
            this.nextVerifies = new ArrayList<>();
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("next_verify");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject2 != null) {
                        NextVerify nextVerify = new NextVerify();
                        nextVerify.strategy = jSONObjectOptJSONObject2.optInt("strategy");
                        nextVerify.selectTitle = jSONObjectOptJSONObject2.optString("select_title");
                        nextVerify.promptTitle = jSONObjectOptJSONObject2.optString("prompt_title");
                        nextVerify.promptContent = jSONObjectOptJSONObject2.optString("prompt_content");
                        nextVerify.dataType = jSONObjectOptJSONObject2.optInt("data_type");
                        nextVerify.data = jSONObjectOptJSONObject2.optString(FunctionRouter.KEY_DATA);
                        this.nextVerifies.add(nextVerify);
                    }
                }
            }
        }
    }
}
