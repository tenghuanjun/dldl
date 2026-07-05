package com.sq.diagnostic.assistant.ui;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sq.diagnostic.assistant.R;
import com.sq.diagnostic.assistant.ui.action.ActivityAction;
import com.sq.diagnostic.assistant.ui.action.ClickAction;
import com.sq.diagnostic.assistant.ui.action.HandlerAction;
import com.sq.diagnostic.assistant.ui.action.KeyboardAction;
import com.sq.diagnostic.assistant.ui.action.ResourcesAction;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseDialog extends Dialog implements ActivityAction, ResourcesAction, HandlerAction, ClickAction, KeyboardAction, DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private List<OnCancelListener> mCancelListeners;
    private List<OnDismissListener> mDismissListeners;
    private final ListenersWrapper<BaseDialog> mListeners;
    private List<OnShowListener> mShowListeners;

    public interface OnCancelListener {
        void onCancel(BaseDialog baseDialog);
    }

    public interface OnClickListener<V extends View> {
        void onClick(BaseDialog baseDialog, V v);
    }

    public interface OnCreateListener {
        void onCreate(BaseDialog baseDialog);
    }

    public interface OnDismissListener {
        void onDismiss(BaseDialog baseDialog);
    }

    public interface OnKeyListener {
        boolean onKey(BaseDialog baseDialog, KeyEvent keyEvent);
    }

    public interface OnShowListener {
        void onShow(BaseDialog baseDialog);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction
    public /* synthetic */ Activity getActivity() {
        return ActivityAction.CC.$default$getActivity(this);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ResourcesAction
    public /* synthetic */ Resources getResources() {
        return getContext().getResources();
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ResourcesAction
    public /* synthetic */ String getString(int i) {
        return getContext().getString(i);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ResourcesAction
    public /* synthetic */ String getString(int i, Object... objArr) {
        return getResources().getString(i, objArr);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.KeyboardAction
    public /* synthetic */ void hideKeyboard(View view) {
        KeyboardAction.CC.$default$hideKeyboard(this, view);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ClickAction, android.view.View.OnClickListener
    public /* synthetic */ void onClick(View view) {
        ClickAction.CC.$default$onClick(this, view);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.HandlerAction
    public /* synthetic */ boolean post(Runnable runnable) {
        return postDelayed(runnable, 0L);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.HandlerAction
    public /* synthetic */ boolean postAtTime(Runnable runnable, long j) {
        return HandlerAction.CC.getHandler().postAtTime(runnable, this, j);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.HandlerAction
    public /* synthetic */ boolean postDelayed(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postDelayed(this, runnable, j);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.HandlerAction
    public /* synthetic */ void removeCallbacks() {
        HandlerAction.CC.getHandler().removeCallbacksAndMessages(this);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.HandlerAction
    public /* synthetic */ void removeCallbacks(Runnable runnable) {
        HandlerAction.CC.getHandler().removeCallbacks(runnable);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
    public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, int... iArr) {
        ClickAction.CC.$default$setOnClickListener(this, onClickListener, iArr);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
    public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, View... viewArr) {
        ClickAction.CC.$default$setOnClickListener(this, onClickListener, viewArr);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
    public /* synthetic */ void setOnClickListener(int... iArr) {
        setOnClickListener(this, iArr);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
    public /* synthetic */ void setOnClickListener(View... viewArr) {
        setOnClickListener(this, viewArr);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.KeyboardAction
    public /* synthetic */ void showKeyboard(View view) {
        KeyboardAction.CC.$default$showKeyboard(this, view);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction
    public /* synthetic */ void startActivity(Intent intent) {
        ActivityAction.CC.$default$startActivity(this, intent);
    }

    @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction
    public /* synthetic */ void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getContext(), (Class<?>) cls));
    }

    public BaseDialog(Context context) {
        super(context, R.style.AssistantBaseDialogTheme);
        this.mListeners = new ListenersWrapper<>(this);
    }

    public View getContentView() {
        View viewFindViewById = findViewById(android.R.id.content);
        if (!(viewFindViewById instanceof ViewGroup)) {
            return viewFindViewById;
        }
        ViewGroup viewGroup = (ViewGroup) viewFindViewById;
        return viewGroup.getChildCount() == 1 ? viewGroup.getChildAt(0) : viewFindViewById;
    }

    public void setWidth(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = i;
        window.setAttributes(attributes);
    }

    public void setHeight(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = i;
        window.setAttributes(attributes);
    }

    public void setXOffset(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = i;
        window.setAttributes(attributes);
    }

    public void setYOffset(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.y = i;
        window.setAttributes(attributes);
    }

    public int getGravity() {
        Window window = getWindow();
        if (window == null) {
            return 0;
        }
        return window.getAttributes().gravity;
    }

    public void setGravity(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(i);
    }

    public int getWindowAnimations() {
        Window window = getWindow();
        if (window == null) {
            return 0;
        }
        return window.getAttributes().windowAnimations;
    }

    public void setBackgroundDimEnabled(boolean z) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        if (z) {
            window.addFlags(2);
        } else {
            window.clearFlags(2);
        }
    }

    public void setBackgroundDimAmount(float f) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setDimAmount(f);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        removeCallbacks();
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            hideKeyboard(currentFocus);
        }
        super.dismiss();
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        if (onShowListener == null) {
            return;
        }
        addOnShowListener(new ShowListenerWrapper(onShowListener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (onCancelListener == null) {
            return;
        }
        addOnCancelListener(new CancelListenerWrapper(onCancelListener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        if (onDismissListener == null) {
            return;
        }
        addOnDismissListener(new DismissListenerWrapper(onDismissListener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        super.setOnKeyListener(new KeyListenerWrapper(onKeyListener));
    }

    public void addOnShowListener(OnShowListener onShowListener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
            super.setOnShowListener(this.mListeners);
        }
        this.mShowListeners.add(onShowListener);
    }

    public void addOnCancelListener(OnCancelListener onCancelListener) {
        if (this.mCancelListeners == null) {
            this.mCancelListeners = new ArrayList();
            super.setOnCancelListener(this.mListeners);
        }
        this.mCancelListeners.add(onCancelListener);
    }

    public void addOnDismissListener(OnDismissListener onDismissListener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this.mListeners);
        }
        this.mDismissListeners.add(onDismissListener);
    }

    public void removeOnShowListener(OnShowListener onShowListener) {
        List<OnShowListener> list = this.mShowListeners;
        if (list == null) {
            return;
        }
        list.remove(onShowListener);
    }

    public void removeOnCancelListener(OnCancelListener onCancelListener) {
        List<OnCancelListener> list = this.mCancelListeners;
        if (list == null) {
            return;
        }
        list.remove(onCancelListener);
    }

    public void removeOnDismissListener(OnDismissListener onDismissListener) {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list == null) {
            return;
        }
        list.remove(onDismissListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnShowListeners(List<OnShowListener> list) {
        super.setOnShowListener(this.mListeners);
        this.mShowListeners = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnCancelListeners(List<OnCancelListener> list) {
        super.setOnCancelListener(this.mListeners);
        this.mCancelListeners = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnDismissListeners(List<OnDismissListener> list) {
        super.setOnDismissListener(this.mListeners);
        this.mDismissListeners = list;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        if (this.mShowListeners == null) {
            return;
        }
        for (int i = 0; i < this.mShowListeners.size(); i++) {
            this.mShowListeners.get(i).onShow(this);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        if (this.mCancelListeners == null) {
            return;
        }
        for (int i = 0; i < this.mCancelListeners.size(); i++) {
            this.mCancelListeners.get(i).onCancel(this);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mDismissListeners == null) {
            return;
        }
        for (int i = 0; i < this.mDismissListeners.size(); i++) {
            this.mDismissListeners.get(i).onDismiss(this);
        }
    }

    public static class Builder<B extends Builder<?>> implements ActivityAction, ResourcesAction, ClickAction, KeyboardAction {
        private final Activity mActivity;
        private int mAnimStyle;
        private float mBackgroundDimAmount;
        private boolean mBackgroundDimEnabled;
        private final List<OnCancelListener> mCancelListeners;
        private boolean mCancelable;
        private boolean mCanceledOnTouchOutside;
        private SparseArray<OnClickListener<?>> mClickArray;
        private View mContentView;
        private final Context mContext;
        private OnCreateListener mCreateListener;
        private BaseDialog mDialog;
        private final List<OnDismissListener> mDismissListeners;
        private int mGravity;
        private int mHeight;
        private OnKeyListener mKeyListener;
        private final List<OnShowListener> mShowListeners;
        private int mThemeId;
        private int mWidth;
        private int mXOffset;
        private int mYOffset;

        @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction
        public /* synthetic */ Activity getActivity() {
            return ActivityAction.CC.$default$getActivity(this);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ResourcesAction
        public /* synthetic */ Resources getResources() {
            return getContext().getResources();
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ResourcesAction
        public /* synthetic */ String getString(int i) {
            return getContext().getString(i);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ResourcesAction
        public /* synthetic */ String getString(int i, Object... objArr) {
            return getResources().getString(i, objArr);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.KeyboardAction
        public /* synthetic */ void hideKeyboard(View view) {
            KeyboardAction.CC.$default$hideKeyboard(this, view);
        }

        public /* synthetic */ void onClick(View view) {
            ClickAction.CC.$default$onClick(this, view);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
        public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, int... iArr) {
            ClickAction.CC.$default$setOnClickListener(this, onClickListener, iArr);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
        public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, View... viewArr) {
            ClickAction.CC.$default$setOnClickListener(this, onClickListener, viewArr);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
        public /* synthetic */ void setOnClickListener(int... iArr) {
            setOnClickListener(this, iArr);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
        public /* synthetic */ void setOnClickListener(View... viewArr) {
            setOnClickListener(this, viewArr);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.KeyboardAction
        public /* synthetic */ void showKeyboard(View view) {
            KeyboardAction.CC.$default$showKeyboard(this, view);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction
        public /* synthetic */ void startActivity(Intent intent) {
            ActivityAction.CC.$default$startActivity(this, intent);
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction
        public /* synthetic */ void startActivity(Class<? extends Activity> cls) {
            startActivity(new Intent(getContext(), (Class<?>) cls));
        }

        public Builder(Activity activity) {
            this((Context) activity);
        }

        public Builder(Context context) {
            this.mThemeId = 0;
            this.mAnimStyle = 0;
            this.mWidth = -2;
            this.mHeight = -2;
            this.mGravity = 0;
            this.mCancelable = true;
            this.mCanceledOnTouchOutside = true;
            this.mBackgroundDimEnabled = true;
            this.mBackgroundDimAmount = 0.5f;
            this.mShowListeners = new ArrayList();
            this.mCancelListeners = new ArrayList();
            this.mDismissListeners = new ArrayList();
            this.mContext = context;
            this.mActivity = getActivity();
        }

        public B setContentView(int i) {
            return (B) setContentView(LayoutInflater.from(this.mContext).inflate(i, (ViewGroup) new FrameLayout(this.mContext), false));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setContentView(View view) {
            int i;
            if (view == null) {
                throw new IllegalArgumentException("are you ok?");
            }
            this.mContentView = view;
            if (isCreated()) {
                this.mDialog.setContentView(view);
                return this;
            }
            ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
            if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                setWidth(layoutParams.width);
                setHeight(layoutParams.height);
            }
            if (this.mGravity == 0) {
                if (layoutParams instanceof FrameLayout.LayoutParams) {
                    int i2 = ((FrameLayout.LayoutParams) layoutParams).gravity;
                    if (i2 != -1) {
                        setGravity(i2);
                    }
                } else if ((layoutParams instanceof LinearLayout.LayoutParams) && (i = ((LinearLayout.LayoutParams) layoutParams).gravity) != 0) {
                    setGravity(i);
                }
                if (this.mGravity == 0) {
                    setGravity(17);
                }
            }
            return this;
        }

        public B setContentView(String str) {
            return (B) setContentView(this.mContext.getResources().getIdentifier(str, "layout", this.mContext.getPackageName()));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setThemeStyle(int i) {
            this.mThemeId = i;
            if (isCreated()) {
                throw new IllegalStateException("are you ok?");
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setWidth(int i) {
            this.mWidth = i;
            if (isCreated()) {
                this.mDialog.setWidth(i);
                return this;
            }
            View view = this.mContentView;
            ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.width = i;
                this.mContentView.setLayoutParams(layoutParams);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setHeight(int i) {
            this.mHeight = i;
            if (isCreated()) {
                this.mDialog.setHeight(i);
                return this;
            }
            View view = this.mContentView;
            ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.height = i;
                this.mContentView.setLayoutParams(layoutParams);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setGravity(int i) {
            this.mGravity = Gravity.getAbsoluteGravity(i, getResources().getConfiguration().getLayoutDirection());
            if (isCreated()) {
                this.mDialog.setGravity(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setXOffset(int i) {
            this.mXOffset = i;
            if (isCreated()) {
                this.mDialog.setXOffset(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setYOffset(int i) {
            this.mYOffset = i;
            if (isCreated()) {
                this.mDialog.setYOffset(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setCancelable(boolean z) {
            this.mCancelable = z;
            if (isCreated()) {
                this.mDialog.setCancelable(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setCanceledOnTouchOutside(boolean z) {
            this.mCanceledOnTouchOutside = z;
            if (isCreated() && this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackgroundDimEnabled(boolean z) {
            this.mBackgroundDimEnabled = z;
            if (isCreated()) {
                this.mDialog.setBackgroundDimEnabled(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackgroundDimAmount(float f) {
            this.mBackgroundDimAmount = f;
            if (isCreated()) {
                this.mDialog.setBackgroundDimAmount(f);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOnCreateListener(OnCreateListener onCreateListener) {
            this.mCreateListener = onCreateListener;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B addOnShowListener(OnShowListener onShowListener) {
            this.mShowListeners.add(onShowListener);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B addOnCancelListener(OnCancelListener onCancelListener) {
            this.mCancelListeners.add(onCancelListener);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B addOnDismissListener(OnDismissListener onDismissListener) {
            this.mDismissListeners.add(onDismissListener);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOnKeyListener(OnKeyListener onKeyListener) {
            this.mKeyListener = onKeyListener;
            if (isCreated()) {
                this.mDialog.setOnKeyListener(onKeyListener);
            }
            return this;
        }

        public B setText(int i, int i2) {
            return (B) setText(i, getString(i2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setText(int i, CharSequence charSequence) {
            ((TextView) findViewById(i)).setText(charSequence);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setTextColor(int i, int i2) {
            ((TextView) findViewById(i)).setTextColor(i2);
            return this;
        }

        public B setHint(int i, int i2) {
            return (B) setHint(i, getString(i2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setHint(int i, CharSequence charSequence) {
            ((TextView) findViewById(i)).setHint(charSequence);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setVisibility(int i, int i2) {
            findViewById(i).setVisibility(i2);
            return this;
        }

        public B setBackground(int i, int i2) {
            Drawable drawable;
            if (Build.VERSION.SDK_INT >= 21) {
                drawable = this.mContext.getDrawable(i2);
            } else {
                drawable = this.mContext.getResources().getDrawable(i2);
            }
            return (B) setBackground(i, drawable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackground(int i, Drawable drawable) {
            findViewById(i).setBackground(drawable);
            return this;
        }

        public B setImageDrawable(int i, int i2) {
            Drawable drawable;
            if (Build.VERSION.SDK_INT >= 21) {
                drawable = this.mContext.getDrawable(i2);
            } else {
                drawable = this.mContext.getResources().getDrawable(i2);
            }
            return (B) setBackground(i, drawable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setImageDrawable(int i, Drawable drawable) {
            ((ImageView) findViewById(i)).setImageDrawable(drawable);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOnClickListener(int i, OnClickListener<?> onClickListener) {
            View viewFindViewById;
            if (this.mClickArray == null) {
                this.mClickArray = new SparseArray<>();
            }
            this.mClickArray.put(i, onClickListener);
            if (isCreated() && (viewFindViewById = this.mDialog.findViewById(i)) != null) {
                viewFindViewById.setOnClickListener(new ViewClickWrapper(onClickListener));
            }
            return this;
        }

        public BaseDialog create() {
            if (this.mContentView == null) {
                throw new IllegalArgumentException("are you ok?");
            }
            if (isShowing()) {
                dismiss();
            }
            if (this.mGravity == 0) {
                this.mGravity = 17;
            }
            BaseDialog baseDialogCreateDialog = createDialog(this.mContext);
            this.mDialog = baseDialogCreateDialog;
            baseDialogCreateDialog.setContentView(this.mContentView);
            this.mDialog.setCancelable(this.mCancelable);
            if (this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
            }
            this.mDialog.setOnShowListeners(this.mShowListeners);
            this.mDialog.setOnCancelListeners(this.mCancelListeners);
            this.mDialog.setOnDismissListeners(this.mDismissListeners);
            this.mDialog.setOnKeyListener(this.mKeyListener);
            Window window = this.mDialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = this.mWidth;
                attributes.height = this.mHeight;
                attributes.gravity = this.mGravity;
                attributes.x = this.mXOffset;
                attributes.y = this.mYOffset;
                attributes.windowAnimations = this.mAnimStyle;
                if (this.mBackgroundDimEnabled) {
                    window.addFlags(2);
                    window.setDimAmount(this.mBackgroundDimAmount);
                } else {
                    window.clearFlags(2);
                }
                window.setAttributes(attributes);
            }
            int i = 0;
            while (true) {
                SparseArray<OnClickListener<?>> sparseArray = this.mClickArray;
                if (sparseArray == null || i >= sparseArray.size()) {
                    break;
                }
                View viewFindViewById = this.mContentView.findViewById(this.mClickArray.keyAt(i));
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(new ViewClickWrapper(this.mClickArray.valueAt(i)));
                }
                i++;
            }
            Activity activity = this.mActivity;
            if (activity != null) {
                DialogLifecycle.with(activity, this.mDialog);
            }
            OnCreateListener onCreateListener = this.mCreateListener;
            if (onCreateListener != null) {
                onCreateListener.onCreate(this.mDialog);
            }
            return this.mDialog;
        }

        public void show() {
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed()) {
                return;
            }
            if (!isCreated()) {
                create();
            }
            if (isShowing()) {
                return;
            }
            this.mDialog.show();
        }

        public void dismiss() {
            BaseDialog baseDialog;
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed() || (baseDialog = this.mDialog) == null) {
                return;
            }
            baseDialog.dismiss();
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ActivityAction, com.sq.diagnostic.assistant.ui.action.ResourcesAction
        public Context getContext() {
            return this.mContext;
        }

        public boolean isCreated() {
            return this.mDialog != null;
        }

        public boolean isShowing() {
            return isCreated() && this.mDialog.isShowing();
        }

        protected BaseDialog createDialog(Context context) {
            return new BaseDialog(context);
        }

        public final void post(Runnable runnable) {
            if (isShowing()) {
                this.mDialog.post(runnable);
            } else {
                addOnShowListener(new ShowPostWrapper(runnable));
            }
        }

        public final void postDelayed(Runnable runnable, long j) {
            if (isShowing()) {
                this.mDialog.postDelayed(runnable, j);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(runnable, j));
            }
        }

        public final void postAtTime(Runnable runnable, long j) {
            if (isShowing()) {
                this.mDialog.postAtTime(runnable, j);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(runnable, j));
            }
        }

        public View getContentView() {
            return this.mContentView;
        }

        @Override // com.sq.diagnostic.assistant.ui.action.ClickAction
        public <V extends View> V findViewById(int i) {
            View view = this.mContentView;
            if (view == null) {
                throw new IllegalStateException("are you ok?");
            }
            return (V) view.findViewById(i);
        }

        public <V extends View> V findViewById(String str) {
            View view = this.mContentView;
            if (view == null) {
                throw new IllegalStateException("are you ok?");
            }
            return (V) view.findViewById(view.getResources().getIdentifier(str, SqTrackCommonKey.id, this.mContext.getPackageName()));
        }

        public String getString(String str) {
            return this.mContentView.getContext().getString(this.mContentView.getResources().getIdentifier(str, "string", this.mContext.getPackageName()));
        }

        public BaseDialog getDialog() {
            return this.mDialog;
        }
    }

    private static final class DialogLifecycle implements Application.ActivityLifecycleCallbacks, OnShowListener, OnDismissListener {
        private Activity mActivity;
        private BaseDialog mDialog;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void with(Activity activity, BaseDialog baseDialog) {
            new DialogLifecycle(activity, baseDialog);
        }

        private DialogLifecycle(Activity activity, BaseDialog baseDialog) {
            this.mActivity = activity;
            baseDialog.addOnShowListener(this);
            baseDialog.addOnDismissListener(this);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.mActivity != activity) {
                return;
            }
            unregisterActivityLifecycleCallbacks();
            this.mActivity = null;
            BaseDialog baseDialog = this.mDialog;
            if (baseDialog == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            this.mDialog.removeOnDismissListener(this);
            if (this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            this.mDialog = baseDialog;
            registerActivityLifecycleCallbacks();
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog baseDialog) {
            this.mDialog = null;
            unregisterActivityLifecycleCallbacks();
        }

        private void registerActivityLifecycleCallbacks() {
            Activity activity = this.mActivity;
            if (activity == null) {
                return;
            }
            activity.getApplication().registerActivityLifecycleCallbacks(this);
        }

        private void unregisterActivityLifecycleCallbacks() {
            Activity activity = this.mActivity;
            if (activity == null) {
                return;
            }
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    private static final class ListenersWrapper<T extends DialogInterface.OnShowListener & DialogInterface.OnCancelListener & DialogInterface.OnDismissListener> extends SoftReference<T> implements DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
        private ListenersWrapper(T t) {
            super(t);
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            if (get() == null) {
                return;
            }
            get().onShow(dialogInterface);
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            if (get() == null) {
                return;
            }
            get().onCancel(dialogInterface);
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (get() == null) {
                return;
            }
            get().onDismiss(dialogInterface);
        }
    }

    private static final class ViewClickWrapper implements View.OnClickListener {
        private final BaseDialog mDialog;
        private final OnClickListener mListener;

        private ViewClickWrapper(BaseDialog baseDialog, OnClickListener onClickListener) {
            this.mDialog = baseDialog;
            this.mListener = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            OnClickListener onClickListener = this.mListener;
            if (onClickListener == null) {
                return;
            }
            onClickListener.onClick(this.mDialog, view);
        }
    }

    private static final class ShowListenerWrapper extends SoftReference<DialogInterface.OnShowListener> implements OnShowListener {
        private ShowListenerWrapper(DialogInterface.OnShowListener onShowListener) {
            super(onShowListener);
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (get() == null) {
                return;
            }
            get().onShow(baseDialog);
        }
    }

    private static final class CancelListenerWrapper extends SoftReference<DialogInterface.OnCancelListener> implements OnCancelListener {
        private CancelListenerWrapper(DialogInterface.OnCancelListener onCancelListener) {
            super(onCancelListener);
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnCancelListener
        public void onCancel(BaseDialog baseDialog) {
            if (get() == null) {
                return;
            }
            get().onCancel(baseDialog);
        }
    }

    private static final class DismissListenerWrapper extends SoftReference<DialogInterface.OnDismissListener> implements OnDismissListener {
        private DismissListenerWrapper(DialogInterface.OnDismissListener onDismissListener) {
            super(onDismissListener);
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog baseDialog) {
            if (get() == null) {
                return;
            }
            get().onDismiss(baseDialog);
        }
    }

    private static final class KeyListenerWrapper implements DialogInterface.OnKeyListener {
        private final OnKeyListener mListener;

        private KeyListenerWrapper(OnKeyListener onKeyListener) {
            this.mListener = onKeyListener;
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            OnKeyListener onKeyListener = this.mListener;
            if (onKeyListener == null || !(dialogInterface instanceof BaseDialog)) {
                return false;
            }
            return onKeyListener.onKey((BaseDialog) dialogInterface, keyEvent);
        }
    }

    private static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            baseDialog.post(this.mRunnable);
        }
    }

    private static final class ShowPostDelayedWrapper implements OnShowListener {
        private final long mDelayMillis;
        private final Runnable mRunnable;

        private ShowPostDelayedWrapper(Runnable runnable, long j) {
            this.mRunnable = runnable;
            this.mDelayMillis = j;
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            baseDialog.postDelayed(this.mRunnable, this.mDelayMillis);
        }
    }

    private static final class ShowPostAtTimeWrapper implements OnShowListener {
        private final Runnable mRunnable;
        private final long mUptimeMillis;

        private ShowPostAtTimeWrapper(Runnable runnable, long j) {
            this.mRunnable = runnable;
            this.mUptimeMillis = j;
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            baseDialog.postAtTime(this.mRunnable, this.mUptimeMillis);
        }
    }
}
