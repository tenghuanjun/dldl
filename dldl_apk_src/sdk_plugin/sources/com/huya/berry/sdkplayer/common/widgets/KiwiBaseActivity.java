package com.huya.berry.sdkplayer.common.widgets;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ui.BaseActivity;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KiwiBaseActivity extends BaseActivity {
    public static float sMarkChannelBrightness = -1.0f;
    public static boolean sUiShown;
    private View mBackBtn;
    private View mCancelBtn;
    private View mCloseBtn;
    private View mDivider;
    private ImageButton mIbtnMore;
    private ImageButton mIbtnRefresh;
    private ImageButton mIbtnShare;
    protected Set<KeyDownListener> mKeyDownListeners = new HashSet();
    private Button mRightBtn;
    private TextView mTitle;

    public interface KeyDownListener {
        boolean onKeyDown(int i, KeyEvent keyEvent);
    }

    protected boolean enableActionbarRightButtonVisiable() {
        return false;
    }

    protected int getActionbarRightButtonColor() {
        return -9605779;
    }

    protected CharSequence getActionbarRightButtonText() {
        return "";
    }

    protected void onActionbarRightButtonClick(View view) {
    }

    protected void onMoreButtonClick(View view) {
    }

    protected void onRefreshButtonClick(View view) {
    }

    protected void onShareButtonClick(View view) {
    }

    protected boolean showCloseButton() {
        return false;
    }

    protected boolean showMoreButton() {
        return false;
    }

    protected boolean showRefreshButton() {
        return false;
    }

    protected boolean showShareButton() {
        return false;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        notifyMainUiShown(z);
    }

    protected void notifyMainUiShown(boolean z) {
        if (!z || sUiShown) {
            return;
        }
        sUiShown = true;
        ArkUtils.send(new MainUiSHown(System.currentTimeMillis(), getClass()));
    }

    private void finishActivity() {
        try {
            hideSoftKeyboard();
            onBackPressed();
        } catch (Exception e) {
            ArkUtils.crashIfDebug(e, "onOptionsItemSelected crashed", new Object[0]);
            finish();
        }
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // android.app.Activity
    public void setTitle(int i) {
        super.setTitle(i);
        TextView textView = this.mTitle;
        if (textView != null) {
            textView.setText(i);
        }
    }

    public void setBackBtnVisible(boolean z) {
        View view = this.mBackBtn;
        if (view == null) {
            return;
        }
        view.setVisibility(z ? 0 : 8);
    }

    public void setDividerVisible(boolean z) {
        View view = this.mDivider;
        if (view == null) {
            return;
        }
        view.setVisibility(z ? 0 : 4);
    }

    @Override // android.app.Activity
    public void setTitleColor(int i) {
        super.setTitleColor(i);
        this.mTitle.setTextColor(i);
    }

    protected void onBackButtonClick(View view) {
        finishActivity();
    }

    public void setActionbarRightButtonText(CharSequence charSequence) {
        Button button = this.mRightBtn;
        if (button != null) {
            button.setText(charSequence);
        }
    }

    public void setActionbarRightButtonTextColor(int i) {
        Button button = this.mRightBtn;
        if (button != null) {
            button.setTextColor(i);
        }
    }

    protected void setShareButtonVisibility(boolean z) {
        this.mIbtnShare.setVisibility(z ? 0 : 8);
    }

    protected void setRefreshButtonVisibility(boolean z) {
        this.mIbtnRefresh.setVisibility(z ? 0 : 8);
    }

    protected void setClosButtonVisibility(boolean z) {
        this.mCloseBtn.setVisibility(z ? 0 : 8);
    }

    protected void setCancelButtonVisibility(boolean z) {
        this.mCancelBtn.setVisibility(z ? 0 : 8);
    }

    protected void setMoreButtonVisibility(boolean z) {
        this.mIbtnMore.setVisibility(z ? 0 : 8);
    }

    public static class MainUiSHown {
        public Class mClz;
        public long mTime;

        public MainUiSHown(long j, Class cls) {
            this.mTime = j;
            this.mClz = cls;
        }
    }

    protected void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getApplication().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(new ContextWrapper(context) { // from class: com.huya.berry.sdkplayer.common.widgets.KiwiBaseActivity.1
            @Override // android.content.ContextWrapper, android.content.Context
            public Object getSystemService(String str) {
                if ("audio".equals(str)) {
                    return getApplicationContext().getSystemService(str);
                }
                return super.getSystemService(str);
            }
        });
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Iterator<KeyDownListener> it = this.mKeyDownListeners.iterator();
        while (it.hasNext()) {
            if (it.next().onKeyDown(i, keyEvent)) {
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void addKeyListener(KeyDownListener keyDownListener) {
        this.mKeyDownListeners.add(keyDownListener);
    }

    public void removeKeyListener(KeyDownListener keyDownListener) {
        this.mKeyDownListeners.remove(keyDownListener);
    }
}
