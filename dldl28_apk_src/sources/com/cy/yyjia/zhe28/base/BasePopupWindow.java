package com.cy.yyjia.zhe28.base;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.PopupWindowCompat;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.ClickAction;
import com.cy.yyjia.zhe28.base.ContextAction;
import com.cy.yyjia.zhe28.base.HandlerAction;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BasePopupWindow extends PopupWindow implements ContextAction, HandlerAction, ClickAction, PopupWindow.OnDismissListener {
    private final Context mContext;
    private List<OnDismissListener> mDismissListeners;
    private PopupBackground mPopupBackground;
    private List<OnShowListener> mShowListeners;

    public interface OnClickListener<V extends View> {
        void onClick(BasePopupWindow popupWindow, V view);
    }

    public interface OnDismissListener {
        void onDismiss(BasePopupWindow popupWindow);
    }

    public interface OnShowListener {
        void onShow(BasePopupWindow popupWindow);
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ int getColor(int i) {
        return ContextCompat.getColor(getContext(), i);
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ Drawable getDrawable(int i) {
        return ContextCompat.getDrawable(getContext(), i);
    }

    @Override // com.cy.yyjia.zhe28.base.HandlerAction
    public /* synthetic */ Handler getHandler() {
        return HandlerAction.HANDLER;
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ Resources getResources() {
        return getContext().getResources();
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ String getString(int i) {
        return getContext().getString(i);
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ String getString(int i, Object... objArr) {
        return getResources().getString(i, objArr);
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ Object getSystemService(Class cls) {
        return ContextCompat.getSystemService(getContext(), cls);
    }

    @Override // com.cy.yyjia.zhe28.base.ClickAction, android.view.View.OnClickListener
    public /* synthetic */ void onClick(View view) {
        ClickAction.CC.$default$onClick(this, view);
    }

    @Override // com.cy.yyjia.zhe28.base.HandlerAction
    public /* synthetic */ boolean post(Runnable runnable) {
        return postDelayed(runnable, 0L);
    }

    @Override // com.cy.yyjia.zhe28.base.HandlerAction
    public /* synthetic */ boolean postAtTime(Runnable runnable, long j) {
        return HandlerAction.HANDLER.postAtTime(runnable, this, j);
    }

    @Override // com.cy.yyjia.zhe28.base.HandlerAction
    public /* synthetic */ boolean postDelayed(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postDelayed(this, runnable, j);
    }

    @Override // com.cy.yyjia.zhe28.base.HandlerAction
    public /* synthetic */ void removeCallbacks() {
        HandlerAction.HANDLER.removeCallbacksAndMessages(this);
    }

    @Override // com.cy.yyjia.zhe28.base.ClickAction
    public /* synthetic */ void setOnClickListener(int... iArr) {
        ClickAction.CC.$default$setOnClickListener(this, iArr);
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ void startActivity(Intent intent) {
        ContextAction.CC.$default$startActivity(this, intent);
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public /* synthetic */ void startActivity(Class cls) {
        startActivity(new Intent(getContext(), (Class<?>) cls));
    }

    public BasePopupWindow(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.cy.yyjia.zhe28.base.ContextAction
    public Context getContext() {
        return this.mContext;
    }

    @Override // android.widget.PopupWindow
    @Deprecated
    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        if (listener == null) {
            return;
        }
        addOnDismissListener(new DismissListenerWrapper(listener));
    }

    public void addOnShowListener(OnShowListener listener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
        }
        this.mShowListeners.add(listener);
    }

    public void addOnDismissListener(OnDismissListener listener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this);
        }
        this.mDismissListeners.add(listener);
    }

    public void removeOnShowListener(OnShowListener listener) {
        List<OnShowListener> list = this.mShowListeners;
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
        this.mShowListeners = listeners;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnDismissListeners(List<OnDismissListener> listeners) {
        super.setOnDismissListener(this);
        this.mDismissListeners = listeners;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list != null) {
            Iterator<OnDismissListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDismiss(this);
            }
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (isShowing() || getContentView() == null) {
            return;
        }
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            Iterator<OnShowListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onShow(this);
            }
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (isShowing() || getContentView() == null) {
            return;
        }
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            Iterator<OnShowListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onShow(this);
            }
        }
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        removeCallbacks();
        super.dismiss();
    }

    @Override // com.cy.yyjia.zhe28.base.ClickAction
    public <V extends View> V findViewById(int i) {
        return (V) getContentView().findViewById(i);
    }

    @Override // android.widget.PopupWindow
    public int getWindowLayoutType() {
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getWindowLayoutType();
        }
        return PopupWindowCompat.getWindowLayoutType(this);
    }

    @Override // android.widget.PopupWindow
    public void setWindowLayoutType(int type) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setWindowLayoutType(type);
        } else {
            PopupWindowCompat.setWindowLayoutType(this, type);
        }
    }

    @Override // android.widget.PopupWindow
    public void setOverlapAnchor(boolean overlapAnchor) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setOverlapAnchor(overlapAnchor);
        } else {
            PopupWindowCompat.setOverlapAnchor(this, overlapAnchor);
        }
    }

    public void setBackgroundDimAmount(float dimAmount) {
        float f = 1.0f - dimAmount;
        if (isShowing()) {
            setActivityAlpha(f);
        }
        if (this.mPopupBackground == null && f != 1.0f) {
            PopupBackground popupBackground = new PopupBackground();
            this.mPopupBackground = popupBackground;
            addOnShowListener(popupBackground);
            addOnDismissListener(this.mPopupBackground);
        }
        PopupBackground popupBackground2 = this.mPopupBackground;
        if (popupBackground2 != null) {
            popupBackground2.setAlpha(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActivityAlpha(float alpha) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            final WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(attributes.alpha, alpha);
            valueAnimatorOfFloat.setDuration(300L);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.cy.yyjia.zhe28.base.BasePopupWindow$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BasePopupWindow.lambda$setActivityAlpha$0(attributes, activity, valueAnimator);
                }
            });
            valueAnimatorOfFloat.start();
        }
    }

    static /* synthetic */ void lambda$setActivityAlpha$0(WindowManager.LayoutParams layoutParams, Activity activity, ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (fFloatValue != layoutParams.alpha) {
            layoutParams.alpha = fFloatValue;
            activity.getWindow().setAttributes(layoutParams);
        }
    }

    public static class Builder<B extends Builder> implements ContextAction, ClickAction {
        private static final int DEFAULT_ANCHORED_GRAVITY = 8388659;
        private float mBackgroundDimAmount;
        private SparseArray<OnClickListener> mClickArray;
        private View mContentView;
        private final Context mContext;
        private List<OnDismissListener> mOnDismissListeners;
        private List<OnShowListener> mOnShowListeners;
        private BasePopupWindow mPopupWindow;
        private int mXOffset;
        private int mYOffset;
        private int mAnimations = 0;
        private int mGravity = 8388659;
        private int mWidth = -2;
        private int mHeight = -2;
        private boolean mTouchable = true;
        private boolean mFocusable = true;
        private boolean mOutsideTouchable = false;

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ int getColor(int i) {
            return ContextCompat.getColor(getContext(), i);
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ Drawable getDrawable(int i) {
            return ContextCompat.getDrawable(getContext(), i);
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ Resources getResources() {
            return getContext().getResources();
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ String getString(int i) {
            return getContext().getString(i);
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ String getString(int i, Object... objArr) {
            return getResources().getString(i, objArr);
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ Object getSystemService(Class cls) {
            return ContextCompat.getSystemService(getContext(), cls);
        }

        @Override // com.cy.yyjia.zhe28.base.ClickAction, android.view.View.OnClickListener
        public /* synthetic */ void onClick(View view) {
            ClickAction.CC.$default$onClick(this, view);
        }

        @Override // com.cy.yyjia.zhe28.base.ClickAction
        public /* synthetic */ void setOnClickListener(int... iArr) {
            ClickAction.CC.$default$setOnClickListener(this, iArr);
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ void startActivity(Intent intent) {
            ContextAction.CC.$default$startActivity(this, intent);
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public /* synthetic */ void startActivity(Class cls) {
            startActivity(new Intent(getContext(), (Class<?>) cls));
        }

        public Builder(Context context) {
            this.mContext = context;
        }

        public B setGravity(int gravity) {
            this.mGravity = Gravity.getAbsoluteGravity(gravity, getResources().getConfiguration().getLayoutDirection());
            return this;
        }

        public B setWidth(int width) {
            this.mWidth = width;
            if (isCreated()) {
                this.mPopupWindow.setWidth(width);
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
                this.mPopupWindow.setHeight(height);
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

        public B setTouchable(boolean touchable) {
            this.mTouchable = touchable;
            return this;
        }

        public B setFocusable(boolean focusable) {
            this.mFocusable = focusable;
            return this;
        }

        public B setOutsideTouchable(boolean touchable) {
            this.mOutsideTouchable = touchable;
            return this;
        }

        public B setXOffset(int offset) {
            this.mXOffset = offset;
            return this;
        }

        public B setYOffset(int offset) {
            this.mYOffset = offset;
            return this;
        }

        public B setAnimStyle(int id) {
            this.mAnimations = id;
            if (isCreated()) {
                this.mPopupWindow.setAnimationStyle(id);
            }
            return this;
        }

        public B setBackgroundDimAmount(float dimAmount) {
            this.mBackgroundDimAmount = dimAmount;
            if (isShowing()) {
                this.mPopupWindow.setBackgroundDimAmount(dimAmount);
            }
            return this;
        }

        public B addOnShowListener(OnShowListener listener) {
            if (isCreated()) {
                this.mPopupWindow.addOnShowListener(listener);
            } else {
                if (this.mOnShowListeners == null) {
                    this.mOnShowListeners = new ArrayList();
                }
                this.mOnShowListeners.add(listener);
            }
            return this;
        }

        public B addOnDismissListener(OnDismissListener listener) {
            if (isCreated()) {
                this.mPopupWindow.addOnDismissListener(listener);
            } else {
                if (this.mOnDismissListeners == null) {
                    this.mOnDismissListeners = new ArrayList();
                }
                this.mOnDismissListeners.add(listener);
            }
            return this;
        }

        public B setText(int i, int i2) {
            return (B) setText(i, getString(i2));
        }

        public B setText(int id, CharSequence text) {
            ((TextView) findViewById(id)).setText(text);
            return this;
        }

        public B setTextColor(int id, int color) {
            ((TextView) findViewById(id)).setTextColor(color);
            return this;
        }

        public B setHint(int i, int i2) {
            return (B) setHint(i, getString(i2));
        }

        public B setHint(int id, CharSequence text) {
            ((TextView) findViewById(id)).setHint(text);
            return this;
        }

        public B setVisibility(int id, int visibility) {
            findViewById(id).setVisibility(visibility);
            return this;
        }

        public B setBackground(int i, int i2) {
            return (B) setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        public B setBackground(int id, Drawable drawable) {
            findViewById(id).setBackground(drawable);
            return this;
        }

        public B setImageDrawable(int i, int i2) {
            return (B) setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        public B setImageDrawable(int id, Drawable drawable) {
            ((ImageView) findViewById(id)).setImageDrawable(drawable);
            return this;
        }

        public B setOnClickListener(int id, OnClickListener listener) {
            if (isCreated()) {
                View viewFindViewById = this.mPopupWindow.findViewById(id);
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

        public BasePopupWindow create() {
            if (this.mContentView == null) {
                throw new IllegalArgumentException("are you ok?");
            }
            if (this.mGravity == 8388659) {
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
            BasePopupWindow basePopupWindowCreatePopupWindow = createPopupWindow(this.mContext);
            this.mPopupWindow = basePopupWindowCreatePopupWindow;
            basePopupWindowCreatePopupWindow.setContentView(this.mContentView);
            this.mPopupWindow.setWidth(this.mWidth);
            this.mPopupWindow.setHeight(this.mHeight);
            this.mPopupWindow.setAnimationStyle(this.mAnimations);
            this.mPopupWindow.setTouchable(this.mTouchable);
            this.mPopupWindow.setFocusable(this.mFocusable);
            this.mPopupWindow.setOutsideTouchable(this.mOutsideTouchable);
            int i2 = 0;
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mPopupWindow.setBackgroundDimAmount(this.mBackgroundDimAmount);
            List<OnShowListener> list = this.mOnShowListeners;
            if (list != null) {
                this.mPopupWindow.setOnShowListeners(list);
            }
            List<OnDismissListener> list2 = this.mOnDismissListeners;
            if (list2 != null) {
                this.mPopupWindow.setOnDismissListeners(list2);
            }
            while (true) {
                SparseArray<OnClickListener> sparseArray = this.mClickArray;
                if (sparseArray == null || i2 >= sparseArray.size()) {
                    break;
                }
                this.mContentView.findViewById(this.mClickArray.keyAt(i2)).setOnClickListener(new ViewClickWrapper(this.mClickArray.valueAt(i2)));
                i2++;
            }
            return this.mPopupWindow;
        }

        public BasePopupWindow showAsDropDown(View anchor) {
            if (!isCreated()) {
                create();
            }
            this.mPopupWindow.showAsDropDown(anchor, this.mXOffset, this.mYOffset, this.mGravity);
            return this.mPopupWindow;
        }

        public BasePopupWindow showAtLocation(View parent) {
            if (!isCreated()) {
                create();
            }
            this.mPopupWindow.showAtLocation(parent, this.mGravity, this.mXOffset, this.mYOffset);
            return this.mPopupWindow;
        }

        @Override // com.cy.yyjia.zhe28.base.ContextAction
        public Context getContext() {
            return this.mContext;
        }

        public boolean isCreated() {
            return this.mPopupWindow != null;
        }

        public boolean isShowing() {
            BasePopupWindow basePopupWindow = this.mPopupWindow;
            return basePopupWindow != null && basePopupWindow.isShowing();
        }

        public void dismiss() {
            BasePopupWindow basePopupWindow = this.mPopupWindow;
            if (basePopupWindow != null) {
                basePopupWindow.dismiss();
            }
        }

        protected BasePopupWindow createPopupWindow(Context context) {
            return new BasePopupWindow(context);
        }

        public View getContentView() {
            return this.mContentView;
        }

        public B setContentView(int i) {
            return (B) setContentView(LayoutInflater.from(this.mContext).inflate(i, (ViewGroup) new FrameLayout(this.mContext), false));
        }

        public B setContentView(View view) {
            this.mContentView = view;
            if (isCreated()) {
                this.mPopupWindow.setContentView(view);
            } else {
                View view2 = this.mContentView;
                if (view2 != null) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                        setWidth(layoutParams.width);
                        setHeight(layoutParams.height);
                    }
                    if (this.mGravity == 8388659) {
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

        @Override // com.cy.yyjia.zhe28.base.ClickAction
        public <V extends View> V findViewById(int i) {
            View view = this.mContentView;
            if (view == null) {
                throw new IllegalStateException("are you ok?");
            }
            return (V) view.findViewById(i);
        }

        public BasePopupWindow getPopupWindow() {
            return this.mPopupWindow;
        }

        public final void post(Runnable r) {
            if (isShowing()) {
                this.mPopupWindow.post(r);
            } else {
                addOnShowListener(new ShowPostWrapper(r));
            }
        }

        public final void postDelayed(Runnable r, long delayMillis) {
            if (isShowing()) {
                this.mPopupWindow.postDelayed(r, delayMillis);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(r, delayMillis));
            }
        }

        public final void postAtTime(Runnable r, long uptimeMillis) {
            if (isShowing()) {
                this.mPopupWindow.postAtTime(r, uptimeMillis);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(r, uptimeMillis));
            }
        }
    }

    private static class PopupBackground implements OnShowListener, OnDismissListener {
        private float mAlpha;

        private PopupBackground() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlpha(float alpha) {
            this.mAlpha = alpha;
        }

        @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow popupWindow) {
            popupWindow.setActivityAlpha(this.mAlpha);
        }

        @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnDismissListener
        public void onDismiss(BasePopupWindow popupWindow) {
            popupWindow.setActivityAlpha(1.0f);
        }
    }

    private static final class DismissListenerWrapper extends SoftReference<PopupWindow.OnDismissListener> implements OnDismissListener {
        private DismissListenerWrapper(PopupWindow.OnDismissListener referent) {
            super(referent);
        }

        @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnDismissListener
        public void onDismiss(BasePopupWindow popupWindow) {
            if (get() != null) {
                get().onDismiss();
            }
        }
    }

    private static final class ViewClickWrapper implements View.OnClickListener {
        private final BasePopupWindow mBasePopupWindow;
        private final OnClickListener mListener;

        private ViewClickWrapper(BasePopupWindow popupWindow, OnClickListener listener) {
            this.mBasePopupWindow = popupWindow;
            this.mListener = listener;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View v) {
            this.mListener.onClick(this.mBasePopupWindow, v);
        }
    }

    private static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable r) {
            this.mRunnable = r;
        }

        @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow dialog) {
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

        @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow dialog) {
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

        @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow dialog) {
            if (this.mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.postAtTime(this.mRunnable, this.mUptimeMillis);
            }
        }
    }
}
