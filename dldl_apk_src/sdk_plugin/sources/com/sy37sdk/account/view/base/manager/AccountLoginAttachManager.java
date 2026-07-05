package com.sy37sdk.account.view.base.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.NavigationUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sy37sdk.account.age.AppropriateAge;
import com.sy37sdk.account.age.AppropriateAgeCacheHelper;
import com.sy37sdk.account.age.AppropriateAgeManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.view.base.view.AccountLoginAttachView;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginAttachManager {
    private static final String TAG = "AccountLoginAttach";
    private int ageViewHeight;
    private int ageViewWidth;
    private AccountLoginAttachView attachView;
    private int authPageHeight;
    private int authPageWidth;
    private int horizontalMargin;
    private boolean isFinish;
    private boolean isShown;
    private ImageView ivAgeAppropriate;
    private Context mContext;
    private WindowManager mWindowManager;
    private int navigationBarHeight;
    private int offsetLandscapeX;
    private int offsetLandscapeY;
    private int offsetPortraitX;
    private int offsetPortraitY;
    private int orientation;
    private int screenHeight;
    private int screenWidth;
    private int statusBarHeight;
    private int verticalMargin;

    public AccountLoginAttachManager(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.attachView = new AccountLoginAttachView(this.mContext);
        init();
    }

    private void init() {
        this.orientation = this.mContext.getResources().getConfiguration().orientation;
        this.navigationBarHeight = NavigationUtils.getNavigationBarHeight(this.mContext);
        this.statusBarHeight = StatusBarUtil.getStatusBarHeight(this.mContext);
        this.authPageHeight = DisplayUtil.dip2px(this.mContext, EntranceManager.getInstance().supportWxEntrance(this.mContext) ? 310.0f : 270.0f);
        this.authPageWidth = DisplayUtil.dip2px(this.mContext, 320.0f);
        this.screenWidth = DisplayUtil.getScreenWidth(this.mContext);
        int screenHeight = DisplayUtil.getScreenHeight(this.mContext);
        this.screenHeight = screenHeight;
        int iMin = Math.min(this.screenWidth, screenHeight) / 10;
        this.ageViewWidth = iMin;
        this.ageViewHeight = (iMin * 62) / 48;
        int i = this.screenWidth;
        int i2 = this.authPageWidth;
        this.offsetPortraitX = (i - i2) / 2;
        int i3 = this.screenHeight;
        int i4 = this.authPageHeight;
        this.offsetPortraitY = (i3 - i4) / 2;
        this.offsetLandscapeX = (i - i2) / 2;
        this.offsetLandscapeY = (i3 - i4) / 2;
        this.horizontalMargin = DisplayUtil.dip2px(this.mContext, 20.0f);
        this.verticalMargin = DisplayUtil.dip2px(this.mContext, 20.0f);
        LogUtil.i(TAG, "show: screenWidth=" + this.screenWidth + " screenHeight=" + this.screenHeight);
        LogUtil.i(TAG, "show: offsetPortraitX=" + this.offsetPortraitX + " offsetPortraitY=" + this.offsetPortraitY + " offsetLandscapeX=" + this.offsetLandscapeX + " offsetLandscapeY=" + this.offsetLandscapeY);
        StringBuilder sb = new StringBuilder();
        sb.append("show: authPageWidth=");
        sb.append(this.authPageWidth);
        sb.append(" authPageHeight=");
        sb.append(this.authPageHeight);
        LogUtil.i(TAG, sb.toString());
        LogUtil.i(TAG, "show: statusBarHeight=" + this.statusBarHeight + " navigationBarHeight=" + this.navigationBarHeight);
        LogUtil.i(TAG, "show: horizontalMargin=" + this.horizontalMargin + " verticalMargin=" + this.verticalMargin);
    }

    public void attachView() {
        try {
            if (this.isFinish) {
                return;
            }
            this.isShown = true;
            attachCustomerView();
            attachAgeAppropriateView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attachCustomerView() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 1000, 552, 1);
        layoutParams.gravity = 8388693;
        if (this.orientation == 2) {
            layoutParams.x = -(this.offsetPortraitX - (this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f)));
            layoutParams.y = -(this.offsetPortraitY - DisplayUtil.dip2px(this.mContext, 6.0f));
        } else {
            layoutParams.x = -(this.offsetPortraitX - DisplayUtil.dip2px(this.mContext, 12.0f));
            layoutParams.y = -(this.offsetPortraitY - (this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f)));
        }
        this.mWindowManager.addView(this.attachView, layoutParams);
    }

    private void attachAgeAppropriateView() {
        List<String> timing;
        AppropriateAge appropriateAge = AppropriateAgeCacheHelper.getAppropriateAge(this.mContext);
        if (appropriateAge == null || !appropriateAge.isStatus() || (timing = appropriateAge.getTiming()) == null || !timing.contains("1")) {
            return;
        }
        this.ivAgeAppropriate = new ImageView(this.mContext);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(this.ageViewWidth, this.ageViewHeight, 1000, 552, 1);
        layoutParams.gravity = 8388693;
        int location = appropriateAge.getLocation();
        if (location == 2) {
            layoutParams.gravity = 8388661;
            if (this.orientation == 2) {
                layoutParams.x = -(this.offsetLandscapeX - (this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f)));
                layoutParams.y = -(this.offsetLandscapeY - this.verticalMargin);
            } else {
                layoutParams.x = -(this.offsetPortraitX - this.horizontalMargin);
                layoutParams.y = -(this.offsetPortraitY - this.verticalMargin);
            }
        } else if (location == 3) {
            layoutParams.gravity = 8388691;
            if (this.orientation == 2) {
                layoutParams.x = -(this.offsetLandscapeX - (this.statusBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f)));
                layoutParams.y = -(this.offsetLandscapeY - (this.statusBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f)));
            } else {
                layoutParams.x = -(this.offsetPortraitX - this.horizontalMargin);
                layoutParams.y = -(this.offsetPortraitY - (this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f)));
            }
        } else if (location == 4) {
            layoutParams.gravity = 8388693;
            if (this.orientation == 2) {
                layoutParams.x = -(this.offsetLandscapeX - (this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 18.0f)));
                layoutParams.y = -(this.offsetLandscapeY - (this.verticalMargin + DisplayUtil.dip2px(this.mContext, 55.0f)));
            } else {
                layoutParams.x = -(this.offsetPortraitX - this.horizontalMargin);
                layoutParams.y = -(this.offsetPortraitY - (this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 80.0f)));
            }
        } else {
            layoutParams.gravity = 8388659;
            if (this.orientation == 2) {
                layoutParams.x = -(this.offsetLandscapeX - this.horizontalMargin);
                layoutParams.y = -(this.offsetLandscapeY - this.verticalMargin);
            } else {
                layoutParams.x = -(this.offsetPortraitX - this.horizontalMargin);
                layoutParams.y = -this.offsetPortraitY;
            }
        }
        this.ivAgeAppropriate.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LogUtil.i(TAG, "show: x= " + layoutParams.x + "  y= " + layoutParams.y);
        new AsyncImageLoader(this.mContext).loadDrawable(appropriateAge.getIcon(), this.ivAgeAppropriate, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.view.base.manager.AccountLoginAttachManager.1
            @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
            public void imageLoaded(Bitmap bitmap, ImageView imageView, String str) {
                imageView.setImageBitmap(bitmap);
                LogUtil.i("适龄图片加载成功");
            }
        });
        this.ivAgeAppropriate.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.base.manager.AccountLoginAttachManager.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppropriateAgeManager.getInstance().showAppropriateAgeDialog(AccountLoginAttachManager.this.mContext);
            }
        });
        this.mWindowManager.addView(this.ivAgeAppropriate, layoutParams);
    }

    public void hide() {
        if (this.isFinish) {
            return;
        }
        this.isShown = false;
        ImageView imageView = this.ivAgeAppropriate;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        AccountLoginAttachView accountLoginAttachView = this.attachView;
        if (accountLoginAttachView != null) {
            accountLoginAttachView.setVisibility(8);
        }
    }

    public void resume() {
        if (this.isFinish) {
            return;
        }
        this.isShown = true;
        ImageView imageView = this.ivAgeAppropriate;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        AccountLoginAttachView accountLoginAttachView = this.attachView;
        if (accountLoginAttachView != null) {
            accountLoginAttachView.setVisibility(0);
        }
    }

    public boolean isShown() {
        return this.isShown;
    }

    public void detachView() {
        try {
            this.isShown = false;
            if (this.mWindowManager == null) {
                return;
            }
            if (this.ivAgeAppropriate != null) {
                this.mWindowManager.removeViewImmediate(this.ivAgeAppropriate);
            }
            if (this.attachView != null) {
                this.mWindowManager.removeViewImmediate(this.attachView);
            }
            this.mWindowManager = null;
            this.isFinish = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isFinish() {
        return this.isFinish;
    }
}
