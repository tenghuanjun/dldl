package com.cmic.sso.sdk;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class AuthThemeConfig {
    public static final String PLACEHOLDER = "$$运营商条款$$";
    public static final String PLACEHOLDER2 = "$$《运营商条款》$$";
    private String activityIn;
    private String activityOut;
    private String authPageActIn;
    private String authPageActOut;
    private com.mobile.auth.e.b backPressedListener;
    private String checkTipText;
    private int checkedImgHeight;
    private String checkedImgPath;
    private int checkedImgWidth;
    private int clauseBaseColor;
    private int clauseColor;
    private int clauseLayoutResID;
    private String clauseLayoutReturnID;
    private String clauseName;
    private String clauseName2;
    private String clauseUrl;
    private String clauseUrl2;
    private View contentView;
    private boolean isLightColor;
    private boolean isPrivacyTextGravityCenter;
    private int layoutResID;
    private String logBtnBackgroundPath;
    private int logBtnHeight;
    private int logBtnMarginLeft;
    private int logBtnMarginRight;
    private int logBtnOffsetY;
    private int logBtnOffsetY_B;
    private String logBtnText;
    private int logBtnTextColor;
    private int logBtnTextSize;
    private int logBtnWidth;
    private com.mobile.auth.e.c loginClickListener;
    private int navColor;
    private int navReturnImgHeight;
    private String navReturnImgPath;
    private ImageView.ScaleType navReturnImgScaleType;
    private int navReturnImgWidth;
    private int navTextColor;
    private int navTextSize;
    private int numFieldOffsetY;
    private int numFieldOffsetY_B;
    private int numberColor;
    private int numberOffsetX;
    private int numberSize;
    private String privacy;
    private int privacyMarginLeft;
    private int privacyMarginRight;
    private int privacyOffsetY;
    private int privacyOffsetY_B;
    private boolean privacyState;
    private int privacyTextSize;
    private int statusBarColor;
    private int themeId;
    private String uncheckedImgPath;
    private int windowBottom;
    private int windowHeight;
    private int windowWidth;
    private int windowX;
    private int windowY;

    public static class Builder {
        private String activityIn;
        private String activityOut;
        private String authPageActIn;
        private String authPageActOut;
        private com.mobile.auth.e.b backPressedListener;
        private String checkTipText;
        private String clauseLayoutReturnID;
        private com.mobile.auth.e.c loginClickListener;
        private int windowHeight;
        private int windowWidth;
        private int windowX;
        private int windowY;
        private int statusBarColor = 0;
        private boolean isLightColor = false;
        private View contentView = null;
        private int layoutResID = -1;
        private int clauseLayoutResID = -1;
        private int navTextSize = 17;
        private int navTextColor = -1;
        private int navColor = -16742704;
        private String navReturnImgPath = "return_bg";
        private int navReturnImgWidth = -2;
        private int navReturnImgHeight = -2;
        private ImageView.ScaleType navReturnImgScaleType = ImageView.ScaleType.CENTER;
        private int numberSize = 18;
        private int numberColor = -16742704;
        private int numberOffsetX = 0;
        private int numFieldOffsetY = Opcodes.INVOKESTATIC;
        private int numFieldOffsetY_B = 0;
        private String logBtnText = "本机号码一键登录";
        private int logBtnTextSize = 15;
        private int logBtnTextColor = -1;
        private String logBtnBackgroundPath = "umcsdk_login_btn_bg";
        private int logBtnWidth = -1;
        private int logBtnHeight = 36;
        private int logBtnMarginLeft = 46;
        private int logBtnMarginRight = 46;
        private int logBtnOffsetY = 254;
        private int logBtnOffsetY_B = 0;
        private String checkedImgPath = "umcsdk_check_image";
        private String uncheckedImgPath = "umcsdk_uncheck_image";
        private int checkBoxImgWidth = 9;
        private int checkBoxImgHeight = 9;
        private boolean privacyState = false;
        private String privacy = "登录即同意$$运营商条款$$并使用本机号码登录";
        private String clauseName = null;
        private String clauseUrl = null;
        private String clauseName2 = null;
        private String clauseUrl2 = null;
        private int privacyTextSize = 10;
        private int clauseBaseColor = -10066330;
        private int clauseColor = -16007674;
        private boolean isPrivacyTextGravityCenter = false;
        private int privacyMarginLeft = 52;
        private int privacyMarginRight = 52;
        private int privacyOffsetY = 0;
        private int privacyOffsetY_B = 30;
        private int windowBottom = 0;
        private int themeId = -1;

        public AuthThemeConfig build() {
            return new AuthThemeConfig(this);
        }

        public Builder setAuthContentView(View view) {
            this.contentView = view;
            this.layoutResID = -1;
            return this;
        }

        public Builder setAuthLayoutResID(int i) {
            this.layoutResID = i;
            this.contentView = null;
            return this;
        }

        public Builder setAuthPageActIn(String str, String str2) {
            this.authPageActIn = str;
            this.activityOut = str2;
            return this;
        }

        public Builder setAuthPageActOut(String str, String str2) {
            this.authPageActOut = str2;
            this.activityIn = str;
            return this;
        }

        public Builder setAuthPageWindowMode(int i, int i2) {
            this.windowWidth = i;
            this.windowHeight = i2;
            return this;
        }

        public Builder setAuthPageWindowOffset(int i, int i2) {
            this.windowX = i;
            this.windowY = i2;
            return this;
        }

        public Builder setBackPressedListener(com.mobile.auth.e.b bVar) {
            this.backPressedListener = bVar;
            return this;
        }

        public Builder setCheckBoxImgPath(String str, String str2, int i, int i2) {
            this.checkedImgPath = str;
            this.uncheckedImgPath = str2;
            this.checkBoxImgWidth = i;
            this.checkBoxImgHeight = i2;
            return this;
        }

        public Builder setCheckTipText(String str) {
            if (TextUtils.isEmpty(str) || str.length() > 100) {
                str = "请勾选同意服务条款";
            }
            this.checkTipText = str;
            return this;
        }

        public Builder setCheckedImgPath(String str) {
            this.checkedImgPath = str;
            return this;
        }

        public Builder setClauseColor(int i, int i2) {
            this.clauseBaseColor = i;
            this.clauseColor = i2;
            return this;
        }

        public Builder setClauseLayoutResID(int i, String str) {
            this.clauseLayoutResID = i;
            this.clauseLayoutReturnID = str;
            return this;
        }

        public Builder setLogBtn(int i, int i2) {
            this.logBtnWidth = i;
            this.logBtnHeight = i2;
            return this;
        }

        public Builder setLogBtnClickListener(com.mobile.auth.e.c cVar) {
            this.loginClickListener = cVar;
            return this;
        }

        public Builder setLogBtnImgPath(String str) {
            this.logBtnBackgroundPath = str;
            return this;
        }

        public Builder setLogBtnMargin(int i, int i2) {
            this.logBtnMarginLeft = i;
            this.logBtnMarginRight = i2;
            return this;
        }

        public Builder setLogBtnOffsetY(int i) {
            this.logBtnOffsetY = i;
            this.logBtnOffsetY_B = 0;
            return this;
        }

        public Builder setLogBtnOffsetY_B(int i) {
            this.logBtnOffsetY_B = i;
            this.logBtnOffsetY = 0;
            return this;
        }

        public Builder setLogBtnText(String str) {
            if (!TextUtils.isEmpty(str) && !Pattern.compile("^\\s*\\n*$").matcher(str).matches()) {
                this.logBtnText = str;
            }
            return this;
        }

        public Builder setLogBtnText(String str, int i, int i2) {
            if (!TextUtils.isEmpty(str) && !Pattern.compile("^\\s*\\n*$").matcher(str).matches()) {
                this.logBtnText = str;
            }
            this.logBtnTextColor = i;
            this.logBtnTextSize = i2;
            return this;
        }

        public Builder setLogBtnTextColor(int i) {
            this.logBtnTextColor = i;
            return this;
        }

        public Builder setNavColor(int i) {
            this.navColor = i;
            return this;
        }

        public Builder setNavTextColor(int i) {
            this.navTextColor = i;
            return this;
        }

        public Builder setNavTextSize(int i) {
            this.navTextSize = i;
            return this;
        }

        public Builder setNumFieldOffsetY(int i) {
            this.numFieldOffsetY = i;
            this.numFieldOffsetY_B = 0;
            return this;
        }

        public Builder setNumFieldOffsetY_B(int i) {
            this.numFieldOffsetY_B = i;
            this.numFieldOffsetY = 0;
            return this;
        }

        public Builder setNumberColor(int i) {
            this.numberColor = i;
            return this;
        }

        public Builder setNumberOffsetX(int i) {
            this.numberOffsetX = i;
            return this;
        }

        public Builder setNumberSize(int i) {
            if (i > 8) {
                this.numberSize = i;
            }
            return this;
        }

        public Builder setPrivacyAlignment(String str, String str2, String str3, String str4, String str5) {
            if (str.contains(AuthThemeConfig.PLACEHOLDER) || str.contains(AuthThemeConfig.PLACEHOLDER2)) {
                this.privacy = str;
                this.clauseName = str2;
                this.clauseUrl = str3;
                this.clauseName2 = str4;
                this.clauseUrl2 = str5;
            }
            return this;
        }

        public Builder setPrivacyMargin(int i, int i2) {
            this.privacyMarginLeft = i;
            this.privacyMarginRight = i2;
            return this;
        }

        public Builder setPrivacyOffsetY(int i) {
            this.privacyOffsetY = i;
            this.privacyOffsetY_B = 0;
            return this;
        }

        public Builder setPrivacyOffsetY_B(int i) {
            this.privacyOffsetY_B = i;
            this.privacyOffsetY = 0;
            return this;
        }

        public Builder setPrivacyState(boolean z) {
            this.privacyState = z;
            return this;
        }

        public Builder setPrivacyText(int i, int i2, int i3, boolean z) {
            this.privacyTextSize = i;
            this.clauseBaseColor = i2;
            this.clauseColor = i3;
            this.isPrivacyTextGravityCenter = z;
            return this;
        }

        public Builder setStatusBar(int i, boolean z) {
            this.statusBarColor = i;
            this.isLightColor = z;
            return this;
        }

        public Builder setThemeId(int i) {
            this.themeId = i;
            return this;
        }

        public Builder setUncheckedImgPath(String str) {
            this.uncheckedImgPath = str;
            return this;
        }

        public Builder setWindowBottom(int i) {
            this.windowBottom = i;
            return this;
        }
    }

    private AuthThemeConfig(Builder builder) {
        this.statusBarColor = builder.statusBarColor;
        this.isLightColor = builder.isLightColor;
        this.contentView = builder.contentView;
        this.layoutResID = builder.layoutResID;
        this.clauseLayoutResID = builder.clauseLayoutResID;
        this.clauseLayoutReturnID = builder.clauseLayoutReturnID;
        this.navTextSize = builder.navTextSize;
        this.navTextColor = builder.navTextColor;
        this.navColor = builder.navColor;
        this.navReturnImgPath = builder.navReturnImgPath;
        this.navReturnImgWidth = builder.navReturnImgWidth;
        this.navReturnImgHeight = builder.navReturnImgHeight;
        this.navReturnImgScaleType = builder.navReturnImgScaleType;
        this.numberSize = builder.numberSize;
        this.numberColor = builder.numberColor;
        this.numberOffsetX = builder.numberOffsetX;
        this.numFieldOffsetY = builder.numFieldOffsetY;
        this.numFieldOffsetY_B = builder.numFieldOffsetY_B;
        this.logBtnText = builder.logBtnText;
        this.logBtnTextSize = builder.logBtnTextSize;
        this.logBtnTextColor = builder.logBtnTextColor;
        this.logBtnBackgroundPath = builder.logBtnBackgroundPath;
        this.logBtnWidth = builder.logBtnWidth;
        this.logBtnHeight = builder.logBtnHeight;
        this.logBtnMarginLeft = builder.logBtnMarginLeft;
        this.logBtnMarginRight = builder.logBtnMarginRight;
        this.logBtnOffsetY = builder.logBtnOffsetY;
        this.logBtnOffsetY_B = builder.logBtnOffsetY_B;
        this.checkTipText = builder.checkTipText;
        this.backPressedListener = builder.backPressedListener;
        this.loginClickListener = builder.loginClickListener;
        this.checkedImgPath = builder.checkedImgPath;
        this.uncheckedImgPath = builder.uncheckedImgPath;
        this.checkedImgWidth = builder.checkBoxImgWidth;
        this.checkedImgHeight = builder.checkBoxImgHeight;
        this.privacyState = builder.privacyState;
        this.privacy = builder.privacy;
        this.clauseName = builder.clauseName;
        this.clauseUrl = builder.clauseUrl;
        this.clauseName2 = builder.clauseName2;
        this.clauseUrl2 = builder.clauseUrl2;
        this.privacyTextSize = builder.privacyTextSize;
        this.clauseBaseColor = builder.clauseBaseColor;
        this.clauseColor = builder.clauseColor;
        this.isPrivacyTextGravityCenter = builder.isPrivacyTextGravityCenter;
        this.privacyMarginLeft = builder.privacyMarginLeft;
        this.privacyMarginRight = builder.privacyMarginRight;
        this.privacyOffsetY = builder.privacyOffsetY;
        this.privacyOffsetY_B = builder.privacyOffsetY_B;
        this.authPageActIn = builder.authPageActIn;
        this.activityOut = builder.activityOut;
        this.authPageActOut = builder.authPageActOut;
        this.activityIn = builder.activityIn;
        this.windowWidth = builder.windowWidth;
        this.windowHeight = builder.windowHeight;
        this.windowX = builder.windowX;
        this.windowY = builder.windowY;
        this.windowBottom = builder.windowBottom;
        this.themeId = builder.themeId;
    }

    public String getActivityIn() {
        return this.activityIn;
    }

    public String getActivityOut() {
        return this.activityOut;
    }

    public String getAuthPageActIn() {
        return this.authPageActIn;
    }

    public String getAuthPageActOut() {
        return this.authPageActOut;
    }

    public com.mobile.auth.e.b getBackPressedListener() {
        return this.backPressedListener;
    }

    public String getCheckTipText() {
        return this.checkTipText;
    }

    public int getCheckedImgHeight() {
        return this.checkedImgHeight;
    }

    public String getCheckedImgPath() {
        return this.checkedImgPath;
    }

    public int getCheckedImgWidth() {
        return this.checkedImgWidth;
    }

    public int getClauseBaseColor() {
        return this.clauseBaseColor;
    }

    public int getClauseColor() {
        return this.clauseColor;
    }

    public int getClauseLayoutResID() {
        return this.clauseLayoutResID;
    }

    public String getClauseLayoutReturnID() {
        return this.clauseLayoutReturnID;
    }

    public String getClauseName() {
        return this.clauseName;
    }

    public String getClauseName2() {
        return this.clauseName2;
    }

    public String getClauseUrl() {
        return this.clauseUrl;
    }

    public String getClauseUrl2() {
        return this.clauseUrl2;
    }

    public View getContentView() {
        return this.contentView;
    }

    public int getLayoutResID() {
        return this.layoutResID;
    }

    public String getLogBtnBackgroundPath() {
        return this.logBtnBackgroundPath;
    }

    public int getLogBtnHeight() {
        return this.logBtnHeight;
    }

    public int getLogBtnMarginLeft() {
        return this.logBtnMarginLeft;
    }

    public int getLogBtnMarginRight() {
        return this.logBtnMarginRight;
    }

    public int getLogBtnOffsetY() {
        return this.logBtnOffsetY;
    }

    public int getLogBtnOffsetY_B() {
        return this.logBtnOffsetY_B;
    }

    public String getLogBtnText() {
        return this.logBtnText;
    }

    public int getLogBtnTextColor() {
        return this.logBtnTextColor;
    }

    public int getLogBtnTextSize() {
        return this.logBtnTextSize;
    }

    public int getLogBtnWidth() {
        return this.logBtnWidth;
    }

    public com.mobile.auth.e.c getLoginClickListener() {
        return this.loginClickListener;
    }

    public int getNavColor() {
        return this.navColor;
    }

    public int getNavReturnImgHeight() {
        return this.navReturnImgHeight;
    }

    public String getNavReturnImgPath() {
        return this.navReturnImgPath;
    }

    public ImageView.ScaleType getNavReturnImgScaleType() {
        return this.navReturnImgScaleType;
    }

    public int getNavReturnImgWidth() {
        return this.navReturnImgWidth;
    }

    public int getNavTextColor() {
        return this.navTextColor;
    }

    public int getNavTextSize() {
        return this.navTextSize;
    }

    public int getNumFieldOffsetY() {
        return this.numFieldOffsetY;
    }

    public int getNumFieldOffsetY_B() {
        return this.numFieldOffsetY_B;
    }

    public int getNumberColor() {
        return this.numberColor;
    }

    public int getNumberOffsetX() {
        return this.numberOffsetX;
    }

    public int getNumberSize() {
        return this.numberSize;
    }

    public String getPrivacy() {
        return this.privacy;
    }

    public int getPrivacyMarginLeft() {
        return this.privacyMarginLeft;
    }

    public int getPrivacyMarginRight() {
        return this.privacyMarginRight;
    }

    public int getPrivacyOffsetY() {
        return this.privacyOffsetY;
    }

    public int getPrivacyOffsetY_B() {
        return this.privacyOffsetY_B;
    }

    public int getPrivacyTextSize() {
        return this.privacyTextSize;
    }

    public int getStatusBarColor() {
        return this.statusBarColor;
    }

    public int getThemeId() {
        return this.themeId;
    }

    public String getUncheckedImgPath() {
        return this.uncheckedImgPath;
    }

    public int getWindowBottom() {
        return this.windowBottom;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public int getWindowX() {
        return this.windowX;
    }

    public int getWindowY() {
        return this.windowY;
    }

    public boolean isLightColor() {
        return this.isLightColor;
    }

    public boolean isPrivacyState() {
        return this.privacyState;
    }

    public boolean isPrivacyTextGravityCenter() {
        return this.isPrivacyTextGravityCenter;
    }
}
