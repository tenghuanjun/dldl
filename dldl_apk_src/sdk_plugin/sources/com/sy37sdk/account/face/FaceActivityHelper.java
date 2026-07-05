package com.sy37sdk.account.face;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.sq.tools.Logger;
import com.sqwan.common.util.CutoutUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import notchtools.geek.com.notchtools.NotchTools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceActivityHelper {
    public static final void requestFull(Activity activity) {
        try {
            activity.setRequestedOrientation(1);
            activity.requestWindowFeature(1);
            final Window window = activity.getWindow();
            window.setSoftInputMode(19);
            window.getDecorView().setSystemUiVisibility(1028);
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
            }
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sy37sdk.account.face.FaceActivityHelper.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    StatusBarUtil.hideSystemUI(window);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void initStatusView(Activity activity) {
        try {
            View viewFindViewById = activity.findViewById(SqResUtils.getId(activity, "status_view"));
            if (viewFindViewById == null) {
                return;
            }
            int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(activity.getWindow());
            int notchHeight = NotchTools.getFullScreenTools().getNotchHeight(activity.getWindow());
            boolean zHasNotchScreen = CutoutUtil.hasNotchScreen(activity);
            Logger.info("statusHeight=" + statusHeight + " notchHeight=" + notchHeight + " 是否刘海屏：" + zHasNotchScreen, new Object[0]);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
            if (zHasNotchScreen) {
                layoutParams.height = statusHeight;
            } else {
                layoutParams.height = 0;
            }
            viewFindViewById.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
