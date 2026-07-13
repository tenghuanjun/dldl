package com.cy.yyjia.zhe28.base;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.cy.yyjia.zhe28.base.BaseDialog;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDialogFragment extends DialogFragment {
    private static long sLastTime;
    private static String sShowTag;
    private BaseDialog mDialog;

    public BaseDialogFragment() {
    }

    public BaseDialogFragment(BaseDialog dialog) {
        this.mDialog = dialog;
        if (dialog == null) {
            throw new IllegalArgumentException("The dialog box cannot be empty");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        super.onActivityCreated(savedInstanceState);
        if (this.mDialog == null) {
            dismissAllowingStateLoss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return this.mDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog getDialog() {
        return this.mDialog;
    }

    public void show(Fragment fragment) {
        FragmentManager fragmentManager = fragment.getFragmentManager();
        if (fragmentManager != null) {
            show(fragmentManager, fragment.getClass().getName());
        }
    }

    public void show(FragmentActivity activity) {
        show(activity.getSupportFragmentManager(), activity.getClass().getName());
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String tag) {
        if (isRepeatedShow(tag) || manager.isStateSaved()) {
            return;
        }
        super.show(manager, tag);
    }

    @Override // androidx.fragment.app.DialogFragment
    public int show(FragmentTransaction transaction, String tag) {
        if (isRepeatedShow(tag) || isStateSaved()) {
            return -1;
        }
        return super.show(transaction, tag);
    }

    protected boolean isRepeatedShow(String tag) {
        boolean z = tag.equals(sShowTag) && SystemClock.uptimeMillis() - sLastTime < 500;
        sShowTag = tag;
        sLastTime = SystemClock.uptimeMillis();
        return z;
    }

    public static class Builder<B extends Builder> extends BaseDialog.Builder<B> {
        public String TAG;
        private final FragmentActivity mActivity;
        private BaseDialogFragment mDialogFragment;

        public Builder(FragmentActivity activity) {
            super(activity);
            this.TAG = activity.getLocalClassName();
            this.mActivity = activity;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.Builder
        public void toast(String text) {
            Toast.makeText(this.mActivity, text, 0).show();
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.Builder
        public void log(String text) {
            Log.e(this.TAG, text);
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.Builder
        public void netFail(Exception e) {
            toast(e.getLocalizedMessage());
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.Builder
        public B setContentView(View view) {
            return (B) super.setContentView(view);
        }

        protected FragmentActivity getActivity() {
            return this.mActivity;
        }

        protected BaseDialogFragment getDialogFragment() {
            return this.mDialogFragment;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.Builder
        public BaseDialog show() {
            BaseDialog baseDialogCreate = create();
            BaseDialogFragment baseDialogFragmentCreateDialogFragment = createDialogFragment(baseDialogCreate);
            this.mDialogFragment = baseDialogFragmentCreateDialogFragment;
            baseDialogFragmentCreateDialogFragment.setCancelable(baseDialogCreate.isCancelable());
            this.mDialogFragment.show(this.mActivity.getSupportFragmentManager(), getFragmentTag());
            return baseDialogCreate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.cy.yyjia.zhe28.base.BaseDialog.Builder
        public void dismiss() {
            hideSoftKeyboard();
            this.mDialogFragment.dismissAllowingStateLoss();
        }

        protected BaseDialogFragment createDialogFragment(BaseDialog dialog) {
            return new BaseDialogFragment(dialog);
        }

        protected String getFragmentTag() {
            return getClass().getName();
        }

        private void hideSoftKeyboard() {
            InputMethodManager inputMethodManager;
            View contentView = getContentView();
            if (contentView == null || (inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method")) == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(contentView.getWindowToken(), 0);
        }
    }
}
