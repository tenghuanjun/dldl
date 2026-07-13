package com.cy.yyjia.zhe28.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.cy.yyjia.zhe28.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDialog extends AppCompatDialog implements DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private List<OnCancelListener> mCancelListeners;
    private boolean mCancelable;
    private List<OnDismissListener> mDismissListeners;
    private final Object mHandlerToken;
    private final ListenersWrapper<BaseDialog> mListeners;
    private List<OnShowListener> mShowListeners;

    public static final class AnimStyle {
        public static final int BOTTOM = 2131820818;
        static final int DEFAULT = 2131820882;
        public static final int IOS = 2131820827;
        public static final int LEFT = 2131820828;
        public static final int NO_ANIM = 0;
        public static final int RIGHT = 2131820864;
        public static final int SCALE = 2131820882;
        public static final int TOAST = 16973828;
        public static final int TOP = 2131821235;
    }

    public interface OnCancelListener {
        void onCancel(BaseDialog dialog);
    }

    public interface OnClickListener<V extends View> {
        void onClick(BaseDialog dialog, V view);
    }

    public interface OnDismissListener {
        void onDismiss(BaseDialog dialog);
    }

    public interface OnKeyListener {
        boolean onKey(BaseDialog dialog, KeyEvent event);
    }

    public interface OnShowListener {
        void onShow(BaseDialog dialog);
    }

    public BaseDialog(Context context) {
        this(context, R.style.BaseDialogStyle);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mHandlerToken = Integer.valueOf(hashCode());
        this.mListeners = new ListenersWrapper<>(this);
        this.mCancelable = true;
    }

    public View getContentView() {
        return findViewById(android.R.id.content);
    }

    protected boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean flag) {
        this.mCancelable = flag;
        super.setCancelable(flag);
    }

    public int getGravity() {
        Window window = getWindow();
        if (window != null) {
            return window.getAttributes().gravity;
        }
        return 0;
    }

    public void setGravity(int gravity) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(gravity);
        }
    }

    public void setWidth(int width) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = width;
            window.setAttributes(attributes);
        }
    }

    public void setHeight(int height) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = height;
            window.setAttributes(attributes);
        }
    }

    public void setWindowAnimations(int id) {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(id);
        }
    }

    public void setBackgroundDimEnabled(boolean enabled) {
        Window window = getWindow();
        if (window != null) {
            if (enabled) {
                window.addFlags(2);
            } else {
                window.clearFlags(2);
            }
        }
    }

    public void setBackgroundDimAmount(float dimAmount) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(dimAmount);
        }
    }

    public final boolean post(Runnable r) {
        return postDelayed(r, 0L);
    }

    public final boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        return HANDLER.postAtTime(r, this.mHandlerToken, uptimeMillis);
    }

    @Override // android.app.Dialog
    public void hide() {
        if (getCurrentFocus() != null) {
            dismiss();
        } else {
            super.hide();
        }
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnShowListener(DialogInterface.OnShowListener listener) {
        if (listener == null) {
            return;
        }
        addOnShowListener(new ShowListenerWrapper(listener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        if (listener == null) {
            return;
        }
        addOnCancelListener(new CancelListenerWrapper(listener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        if (listener == null) {
            return;
        }
        addOnDismissListener(new DismissListenerWrapper(listener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnKeyListener(DialogInterface.OnKeyListener listener) {
        super.setOnKeyListener(listener);
    }

    public void setOnKeyListener(OnKeyListener listener) {
        super.setOnKeyListener(new KeyListenerWrapper(listener));
    }

    public void addOnShowListener(OnShowListener listener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
            super.setOnShowListener(this.mListeners);
        }
        this.mShowListeners.add(listener);
    }

    public void addOnCancelListener(OnCancelListener listener) {
        if (this.mCancelListeners == null) {
            this.mCancelListeners = new ArrayList();
            super.setOnCancelListener(this.mListeners);
        }
        this.mCancelListeners.add(listener);
    }

    public void addOnDismissListener(OnDismissListener listener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this.mListeners);
        }
        this.mDismissListeners.add(listener);
    }

    public void removeOnShowListener(OnShowListener listener) {
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            list.remove(listener);
        }
    }

    public void removeOnCancelListener(OnCancelListener listener) {
        List<OnCancelListener> list = this.mCancelListeners;
        if (list != null) {
            list.remove(listener);
        }
    }

    public void removeOnDismissListener(OnDismissListener listener) {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list != null) {
            list.remove(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnShowListeners(List<OnShowListener> listeners) {
        super.setOnShowListener(this.mListeners);
        this.mShowListeners = listeners;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnCancelListeners(List<OnCancelListener> listeners) {
        super.setOnCancelListener(this.mListeners);
        this.mCancelListeners = listeners;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnDismissListeners(List<OnDismissListener> listeners) {
        super.setOnDismissListener(this.mListeners);
        this.mDismissListeners = listeners;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            Iterator<OnShowListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onShow(this);
            }
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        List<OnCancelListener> list = this.mCancelListeners;
        if (list != null) {
            Iterator<OnCancelListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onCancel(this);
            }
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list != null) {
            Iterator<OnDismissListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDismiss(this);
            }
        }
        HANDLER.removeCallbacksAndMessages(this.mHandlerToken);
    }

    public static class Builder<B extends Builder> {
        private SparseArray<Drawable> mBackgroundArray;
        private SparseArray<OnClickListener> mClickArray;
        private View mContentView;
        private final Context mContext;
        private BaseDialog mDialog;
        private SparseArray<Drawable> mImageArray;
        private List<OnCancelListener> mOnCancelListeners;
        private List<OnDismissListener> mOnDismissListeners;
        private OnKeyListener mOnKeyListener;
        private List<OnShowListener> mOnShowListeners;
        private SparseArray<CharSequence> mTextArray;
        private SparseIntArray mVisibilityArray;
        private int mThemeId = R.style.BaseDialogStyle;
        private int mAnimations = 0;
        private int mGravity = 0;
        private int mWidth = -2;
        private int mHeight = -2;
        private boolean mBackgroundDimEnabled = true;
        private float mBackgroundDimAmount = 0.5f;
        private boolean mCancelable = true;
        private boolean mCanceledOnTouchOutside = true;

        public Builder(Context context) {
            this.mContext = context;
        }

        public void toast(String text) {
            Toast.makeText(this.mContext, text, 0).show();
        }

        public void log(String text) {
            Log.e("wancms", text);
        }

        public void netFail(Exception e) {
            toast(e.getLocalizedMessage());
        }

        public B setThemeStyle(int id) {
            if (isCreated()) {
                throw new IllegalStateException("are you ok?");
            }
            this.mThemeId = id;
            return this;
        }

        public B setGravity(int gravity) {
            int absoluteGravity = Gravity.getAbsoluteGravity(gravity, getResources().getConfiguration().getLayoutDirection());
            this.mGravity = absoluteGravity;
            if (isCreated()) {
                this.mDialog.setGravity(absoluteGravity);
            }
            return this;
        }

        public B setWidth(int width) {
            this.mWidth = width;
            if (isCreated()) {
                this.mDialog.setWidth(width);
            } else {
                View view = this.mContentView;
                ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
                if (layoutParams != null) {
                    layoutParams.width = width;
                    this.mContentView.setLayoutParams(layoutParams);
                }
            }
            return this;
        }

        public B setHeight(int height) {
            this.mHeight = height;
            if (isCreated()) {
                this.mDialog.setHeight(height);
            } else {
                View view = this.mContentView;
                ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
                if (layoutParams != null) {
                    layoutParams.height = height;
                    this.mContentView.setLayoutParams(layoutParams);
                }
            }
            return this;
        }

        public B setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            if (isCreated()) {
                this.mDialog.setCancelable(cancelable);
            }
            return this;
        }

        public B setCanceledOnTouchOutside(boolean cancel) {
            this.mCanceledOnTouchOutside = cancel;
            if (isCreated() && this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(cancel);
            }
            return this;
        }

        public B setAnimStyle(int id) {
            this.mAnimations = id;
            if (isCreated()) {
                this.mDialog.setWindowAnimations(id);
            }
            return this;
        }

        public void setBackgroundDimEnabled(boolean enabled) {
            this.mBackgroundDimEnabled = enabled;
            if (isCreated()) {
                this.mDialog.setBackgroundDimEnabled(enabled);
            }
        }

        public void setBackgroundDimAmount(float dimAmount) {
            this.mBackgroundDimAmount = dimAmount;
            if (isCreated()) {
                this.mDialog.setBackgroundDimAmount(dimAmount);
            }
        }

        public B addOnShowListener(OnShowListener listener) {
            if (isCreated()) {
                this.mDialog.addOnShowListener(listener);
            } else {
                if (this.mOnShowListeners == null) {
                    this.mOnShowListeners = new ArrayList();
                }
                this.mOnShowListeners.add(listener);
            }
            return this;
        }

        public B addOnCancelListener(OnCancelListener listener) {
            if (isCreated()) {
                this.mDialog.addOnCancelListener(listener);
            } else {
                if (this.mOnCancelListeners == null) {
                    this.mOnCancelListeners = new ArrayList();
                }
                this.mOnCancelListeners.add(listener);
            }
            return this;
        }

        public B addOnDismissListener(OnDismissListener listener) {
            if (isCreated()) {
                this.mDialog.addOnDismissListener(listener);
            } else {
                if (this.mOnDismissListeners == null) {
                    this.mOnDismissListeners = new ArrayList();
                }
                this.mOnDismissListeners.add(listener);
            }
            return this;
        }

        public B setOnKeyListener(OnKeyListener listener) {
            if (isCreated()) {
                this.mDialog.setOnKeyListener(listener);
            } else {
                this.mOnKeyListener = listener;
            }
            return this;
        }

        public B setText(int i, int i2) {
            return (B) setText(i, getString(i2));
        }

        public B setText(int id, CharSequence text) {
            if (isCreated()) {
                TextView textView = (TextView) this.mDialog.findViewById(id);
                if (textView != null) {
                    textView.setText(text);
                }
            } else {
                if (this.mTextArray == null) {
                    this.mTextArray = new SparseArray<>();
                }
                this.mTextArray.put(id, text);
            }
            return this;
        }

        public B setTextHint(int i, int i2) {
            return (B) setTextHint(i, getString(i2));
        }

        public B setTextHint(int id, CharSequence text) {
            if (isCreated()) {
                TextView textView = (TextView) this.mDialog.findViewById(id);
                if (textView != null) {
                    textView.setHint(text);
                }
            } else {
                if (this.mTextArray == null) {
                    this.mTextArray = new SparseArray<>();
                }
                this.mTextArray.put(id, text);
            }
            return this;
        }

        public B setVisibility(int id, int visibility) {
            if (isCreated()) {
                View viewFindViewById = this.mDialog.findViewById(id);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(visibility);
                }
            } else {
                if (this.mVisibilityArray == null) {
                    this.mVisibilityArray = new SparseIntArray();
                }
                this.mVisibilityArray.put(id, visibility);
            }
            return this;
        }

        public B setBackground(int i, int i2) {
            return (B) setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        public B setBackground(int id, Drawable drawable) {
            if (isCreated()) {
                View viewFindViewById = this.mDialog.findViewById(id);
                if (viewFindViewById != null) {
                    viewFindViewById.setBackground(drawable);
                }
            } else {
                if (this.mBackgroundArray == null) {
                    this.mBackgroundArray = new SparseArray<>();
                }
                this.mBackgroundArray.put(id, drawable);
            }
            return this;
        }

        public B setImageDrawable(int i, int i2) {
            return (B) setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        public B setImageDrawable(int id, Drawable drawable) {
            if (isCreated()) {
                ImageView imageView = (ImageView) this.mDialog.findViewById(id);
                if (imageView != null) {
                    imageView.setImageDrawable(drawable);
                }
            } else {
                if (this.mImageArray == null) {
                    this.mImageArray = new SparseArray<>();
                }
                this.mImageArray.put(id, drawable);
            }
            return this;
        }

        public B setOnClickListener(int id, OnClickListener listener) {
            if (isCreated()) {
                View viewFindViewById = this.mDialog.findViewById(id);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(new ViewClickWrapper(listener));
                }
            } else {
                if (this.mClickArray == null) {
                    this.mClickArray = new SparseArray<>();
                }
                this.mClickArray.put(id, listener);
            }
            return this;
        }

        public BaseDialog create() {
            if (this.mContentView == null) {
                throw new IllegalArgumentException("Dialog layout cannot be empty");
            }
            if (this.mGravity == 0) {
                this.mGravity = 17;
            }
            if (this.mAnimations == 0) {
                int i = this.mGravity;
                if (i == 3) {
                    this.mAnimations = R.style.LeftAnimStyle;
                } else if (i == 5) {
                    this.mAnimations = R.style.RightAnimStyle;
                } else if (i == 48) {
                    this.mAnimations = R.style.TopAnimStyle;
                } else if (i == 80) {
                    this.mAnimations = R.style.BottomAnimStyle;
                } else {
                    this.mAnimations = R.style.ScaleAnimStyle;
                }
            }
            BaseDialog baseDialogCreateDialog = createDialog(this.mContext, this.mThemeId);
            this.mDialog = baseDialogCreateDialog;
            baseDialogCreateDialog.setContentView(this.mContentView);
            this.mDialog.setCancelable(this.mCancelable);
            if (this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
            }
            Window window = this.mDialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = this.mWidth;
                attributes.height = this.mHeight;
                attributes.gravity = this.mGravity;
                attributes.windowAnimations = this.mAnimations;
                window.setAttributes(attributes);
                if (this.mBackgroundDimEnabled) {
                    window.addFlags(2);
                    window.setDimAmount(this.mBackgroundDimAmount);
                } else {
                    window.clearFlags(2);
                }
            }
            List<OnShowListener> list = this.mOnShowListeners;
            if (list != null) {
                this.mDialog.setOnShowListeners(list);
            }
            List<OnCancelListener> list2 = this.mOnCancelListeners;
            if (list2 != null) {
                this.mDialog.setOnCancelListeners(list2);
            }
            List<OnDismissListener> list3 = this.mOnDismissListeners;
            if (list3 != null) {
                this.mDialog.setOnDismissListeners(list3);
            }
            OnKeyListener onKeyListener = this.mOnKeyListener;
            if (onKeyListener != null) {
                this.mDialog.setOnKeyListener(onKeyListener);
            }
            int i2 = 0;
            int i3 = 0;
            while (true) {
                SparseArray<CharSequence> sparseArray = this.mTextArray;
                if (sparseArray == null || i3 >= sparseArray.size()) {
                    break;
                }
                ((TextView) this.mContentView.findViewById(this.mTextArray.keyAt(i3))).setText(this.mTextArray.valueAt(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                SparseIntArray sparseIntArray = this.mVisibilityArray;
                if (sparseIntArray == null || i4 >= sparseIntArray.size()) {
                    break;
                }
                this.mContentView.findViewById(this.mVisibilityArray.keyAt(i4)).setVisibility(this.mVisibilityArray.valueAt(i4));
                i4++;
            }
            int i5 = 0;
            while (true) {
                SparseArray<Drawable> sparseArray2 = this.mBackgroundArray;
                if (sparseArray2 == null || i5 >= sparseArray2.size()) {
                    break;
                }
                this.mContentView.findViewById(this.mBackgroundArray.keyAt(i5)).setBackground(this.mBackgroundArray.valueAt(i5));
                i5++;
            }
            int i6 = 0;
            while (true) {
                SparseArray<Drawable> sparseArray3 = this.mImageArray;
                if (sparseArray3 == null || i6 >= sparseArray3.size()) {
                    break;
                }
                ((ImageView) this.mContentView.findViewById(this.mImageArray.keyAt(i6))).setImageDrawable(this.mImageArray.valueAt(i6));
                i6++;
            }
            while (true) {
                SparseArray<OnClickListener> sparseArray4 = this.mClickArray;
                if (sparseArray4 == null || i2 >= sparseArray4.size()) {
                    break;
                }
                this.mContentView.findViewById(this.mClickArray.keyAt(i2)).setOnClickListener(new ViewClickWrapper(this.mClickArray.valueAt(i2)));
                i2++;
            }
            return this.mDialog;
        }

        public BaseDialog show() {
            BaseDialog baseDialogCreate = create();
            baseDialogCreate.show();
            return baseDialogCreate;
        }

        protected boolean isCreated() {
            return this.mDialog != null;
        }

        protected boolean isShowing() {
            return isCreated() && this.mDialog.isShowing();
        }

        protected void dismiss() {
            BaseDialog baseDialog = this.mDialog;
            if (baseDialog != null) {
                baseDialog.dismiss();
            }
        }

        protected BaseDialog createDialog(Context context, int themeId) {
            return new BaseDialog(context, themeId);
        }

        protected final void post(Runnable r) {
            if (isShowing()) {
                this.mDialog.post(r);
            } else {
                addOnShowListener(new ShowPostWrapper(r));
            }
        }

        protected final void postDelayed(Runnable r, long delayMillis) {
            if (isShowing()) {
                this.mDialog.postDelayed(r, delayMillis);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(r, delayMillis));
            }
        }

        protected final void postAtTime(Runnable r, long uptimeMillis) {
            if (isShowing()) {
                this.mDialog.postAtTime(r, uptimeMillis);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(r, uptimeMillis));
            }
        }

        protected Context getContext() {
            return this.mContext;
        }

        protected Resources getResources() {
            return this.mContext.getResources();
        }

        protected String getString(int id) {
            return this.mContext.getString(id);
        }

        protected int getColor(int id) {
            return ContextCompat.getColor(getContext(), id);
        }

        protected Drawable getDrawable(int id) {
            return ContextCompat.getDrawable(this.mContext, id);
        }

        protected View getContentView() {
            return this.mContentView;
        }

        public B setContentView(int i) {
            return (B) setContentView(LayoutInflater.from(this.mContext).inflate(i, (ViewGroup) new FrameLayout(this.mContext), false));
        }

        public B setContentView(View view) {
            this.mContentView = view;
            if (isCreated()) {
                this.mDialog.setContentView(view);
            } else {
                View view2 = this.mContentView;
                if (view2 != null) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                        setWidth(layoutParams.width);
                        setHeight(layoutParams.height);
                    }
                    if (this.mGravity == 0) {
                        if (layoutParams instanceof FrameLayout.LayoutParams) {
                            setGravity(((FrameLayout.LayoutParams) layoutParams).gravity);
                        } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                            setGravity(((LinearLayout.LayoutParams) layoutParams).gravity);
                        } else {
                            setGravity(17);
                        }
                    }
                }
            }
            return this;
        }

        protected <V extends View> V findViewById(int i) {
            View view = this.mContentView;
            if (view == null) {
                throw new IllegalStateException("are you ok?");
            }
            return (V) view.findViewById(i);
        }

        protected BaseDialog getDialog() {
            return this.mDialog;
        }

        protected <T> T getSystemService(Class<T> cls) {
            return (T) ContextCompat.getSystemService(this.mContext, cls);
        }
    }

    private static final class ListenersWrapper<T extends DialogInterface.OnShowListener & DialogInterface.OnCancelListener & DialogInterface.OnDismissListener> extends WeakReference<T> implements DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
        private ListenersWrapper(T referent) {
            super(referent);
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialog) {
            if (get() != null) {
                ((DialogInterface.OnShowListener) get()).onShow(dialog);
            }
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            if (get() != null) {
                ((DialogInterface.OnCancelListener) ((DialogInterface.OnShowListener) get())).onCancel(dialog);
            }
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialog) {
            if (get() != null) {
                ((DialogInterface.OnDismissListener) ((DialogInterface.OnShowListener) get())).onDismiss(dialog);
            }
        }
    }

    private static final class ViewClickWrapper implements View.OnClickListener {
        private final BaseDialog mDialog;
        private final OnClickListener mListener;

        private ViewClickWrapper(BaseDialog dialog, OnClickListener listener) {
            this.mDialog = dialog;
            this.mListener = listener;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View v) {
            this.mListener.onClick(this.mDialog, v);
        }
    }

    private static final class ShowListenerWrapper implements OnShowListener {
        private final DialogInterface.OnShowListener mListener;

        private ShowListenerWrapper(DialogInterface.OnShowListener listener) {
            this.mListener = listener;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnShowListener
        public void onShow(BaseDialog dialog) {
            DialogInterface.OnShowListener onShowListener = this.mListener;
            if (onShowListener != null) {
                onShowListener.onShow(dialog);
            }
        }
    }

    private static final class CancelListenerWrapper implements OnCancelListener {
        private final DialogInterface.OnCancelListener mListener;

        private CancelListenerWrapper(DialogInterface.OnCancelListener listener) {
            this.mListener = listener;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnCancelListener
        public void onCancel(BaseDialog dialog) {
            DialogInterface.OnCancelListener onCancelListener = this.mListener;
            if (onCancelListener != null) {
                onCancelListener.onCancel(dialog);
            }
        }
    }

    private static final class DismissListenerWrapper implements OnDismissListener {
        private final DialogInterface.OnDismissListener mListener;

        private DismissListenerWrapper(DialogInterface.OnDismissListener listener) {
            this.mListener = listener;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog dialog) {
            DialogInterface.OnDismissListener onDismissListener = this.mListener;
            if (onDismissListener != null) {
                onDismissListener.onDismiss(dialog);
            }
        }
    }

    private static final class KeyListenerWrapper implements DialogInterface.OnKeyListener {
        private final OnKeyListener mListener;

        private KeyListenerWrapper(OnKeyListener listener) {
            this.mListener = listener;
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            OnKeyListener onKeyListener = this.mListener;
            if (onKeyListener == null || !(dialog instanceof BaseDialog)) {
                return false;
            }
            onKeyListener.onKey((BaseDialog) dialog, event);
            return false;
        }
    }

    private static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable r) {
            this.mRunnable = r;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnShowListener
        public void onShow(BaseDialog dialog) {
            if (this.mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.post(this.mRunnable);
            }
        }
    }

    private static final class ShowPostDelayedWrapper implements OnShowListener {
        private final long mDelayMillis;
        private final Runnable mRunnable;

        private ShowPostDelayedWrapper(Runnable r, long delayMillis) {
            this.mRunnable = r;
            this.mDelayMillis = delayMillis;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnShowListener
        public void onShow(BaseDialog dialog) {
            if (this.mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.postDelayed(this.mRunnable, this.mDelayMillis);
            }
        }
    }

    private static final class ShowPostAtTimeWrapper implements OnShowListener {
        private final Runnable mRunnable;
        private final long mUptimeMillis;

        private ShowPostAtTimeWrapper(Runnable r, long uptimeMillis) {
            this.mRunnable = r;
            this.mUptimeMillis = uptimeMillis;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnShowListener
        public void onShow(BaseDialog dialog) {
            if (this.mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.postAtTime(this.mRunnable, this.mUptimeMillis);
            }
        }
    }
}
