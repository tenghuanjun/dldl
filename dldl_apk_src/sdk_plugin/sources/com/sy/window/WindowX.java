package com.sy.window;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sy.window.ScreenOrientationMonitor;
import com.sy.window.WindowX;
import com.sy.window.draggable.BaseDraggable;
import com.sy.window.draggable.MovingDraggable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WindowX<X extends WindowX<?>> implements Runnable, ScreenOrientationMonitor.OnScreenOrientationCallback {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private Context mContext;
    private ViewGroup mDecorView;
    private BaseDraggable mDraggable;
    private int mDuration;
    private ActivityLifecycle mLifecycle;
    private OnWindowLifecycle mListener;
    private ScreenOrientationMonitor mScreenOrientationMonitor;
    private boolean mShowing;
    private final Runnable mUpdateRunnable;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;

    public interface OnClickListener<V extends View> {
        void onClick(WindowX<?> windowX, V v);
    }

    public interface OnLongClickListener<V extends View> {
        boolean onLongClick(WindowX<?> windowX, V v);
    }

    public interface OnTouchListener<V extends View> {
        boolean onTouch(WindowX<?> windowX, V v, MotionEvent motionEvent);
    }

    public interface OnWindowLifecycle {

        /* JADX INFO: renamed from: com.sy.window.WindowX$OnWindowLifecycle$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onWindowCancel(OnWindowLifecycle onWindowLifecycle, WindowX windowX) {
            }

            public static void $default$onWindowRecycler(OnWindowLifecycle onWindowLifecycle, WindowX windowX) {
            }

            public static void $default$onWindowShow(OnWindowLifecycle onWindowLifecycle, WindowX windowX) {
            }

            public static void $default$onWindowVisibilityChanged(OnWindowLifecycle onWindowLifecycle, WindowX windowX, int i) {
            }
        }

        void onWindowCancel(WindowX<?> windowX);

        void onWindowRecycler(WindowX<?> windowX);

        void onWindowShow(WindowX<?> windowX);

        void onWindowVisibilityChanged(WindowX<?> windowX, int i);
    }

    public WindowX(Activity activity) {
        this((Context) activity);
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if ((attributes.flags & 1024) != 0 || (window.getDecorView().getSystemUiVisibility() & 4) != 0) {
            addWindowFlags(1024);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            setLayoutInDisplayCutoutMode(attributes.layoutInDisplayCutoutMode);
        }
        setSystemUiVisibility(attributes.systemUiVisibility);
        setWindowType(1999);
        this.mLifecycle = new ActivityLifecycle(this, activity);
    }

    public WindowX(Application application) {
        this((Context) application);
        if (Build.VERSION.SDK_INT >= 26) {
            setWindowType(2038);
        } else {
            setWindowType(2003);
        }
    }

    private WindowX(Context context) {
        this.mUpdateRunnable = new Runnable() { // from class: com.sy.window.-$$Lambda$1YOj_5hfZQy_a9EQdQAH7KYwiYE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.update();
            }
        };
        this.mContext = context;
        this.mDecorView = new WindowLayout(context);
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mWindowParams = layoutParams;
        layoutParams.height = -2;
        this.mWindowParams.width = -2;
        this.mWindowParams.format = -3;
        this.mWindowParams.windowAnimations = android.R.style.Animation.Toast;
        this.mWindowParams.packageName = context.getPackageName();
        this.mWindowParams.flags = 40;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWidth(int i) {
        View childAt;
        ViewGroup.LayoutParams layoutParams;
        this.mWindowParams.width = i;
        if (this.mDecorView.getChildCount() > 0 && (layoutParams = (childAt = this.mDecorView.getChildAt(0)).getLayoutParams()) != null && layoutParams.width != i) {
            layoutParams.width = i;
            childAt.setLayoutParams(layoutParams);
        }
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setHeight(int i) {
        View childAt;
        ViewGroup.LayoutParams layoutParams;
        this.mWindowParams.height = i;
        if (this.mDecorView.getChildCount() > 0 && (layoutParams = (childAt = this.mDecorView.getChildAt(0)).getLayoutParams()) != null && layoutParams.height != i) {
            layoutParams.height = i;
            childAt.setLayoutParams(layoutParams);
        }
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setGravity(int i) {
        this.mWindowParams.gravity = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setXOffset(int i) {
        this.mWindowParams.x = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setYOffset(int i) {
        this.mWindowParams.y = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setOutsideTouchable(boolean z) {
        if (z) {
            addWindowFlags(40);
        } else {
            removeWindowFlags(40);
        }
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setBackgroundDimAmount(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("amount must be a value between 0 and 1");
        }
        this.mWindowParams.dimAmount = f;
        if (f != 0.0f) {
            addWindowFlags(2);
        } else {
            removeWindowFlags(2);
        }
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X addWindowFlags(int i) {
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.flags = i | layoutParams.flags;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X removeWindowFlags(int i) {
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.flags = (~i) & layoutParams.flags;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWindowFlags(int i) {
        this.mWindowParams.flags = i;
        postUpdate();
        return this;
    }

    public boolean hasWindowFlags(int i) {
        return (i & this.mWindowParams.flags) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWindowType(int i) {
        this.mWindowParams.type = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setAnimStyle(int i) {
        this.mWindowParams.windowAnimations = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setSoftInputMode(int i) {
        this.mWindowParams.softInputMode = i;
        removeWindowFlags(8);
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWindowToken(IBinder iBinder) {
        this.mWindowParams.token = iBinder;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWindowAlpha(float f) {
        this.mWindowParams.alpha = f;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setVerticalMargin(float f) {
        this.mWindowParams.verticalMargin = f;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setHorizontalMargin(float f) {
        this.mWindowParams.horizontalMargin = f;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setBitmapFormat(int i) {
        this.mWindowParams.format = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setSystemUiVisibility(int i) {
        this.mWindowParams.systemUiVisibility = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setVerticalWeight(float f) {
        this.mWindowParams.verticalWeight = f;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setLayoutInDisplayCutoutMode(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mWindowParams.layoutInDisplayCutoutMode = i;
            postUpdate();
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setPreferredDisplayModeId(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mWindowParams.preferredDisplayModeId = i;
            postUpdate();
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWindowTitle(CharSequence charSequence) {
        this.mWindowParams.setTitle(charSequence);
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setScreenBrightness(float f) {
        this.mWindowParams.screenBrightness = f;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setButtonBrightness(float f) {
        this.mWindowParams.buttonBrightness = f;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setPreferredRefreshRate(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mWindowParams.preferredRefreshRate = f;
            postUpdate();
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setColorMode(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mWindowParams.setColorMode(i);
            postUpdate();
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setBlurBehindRadius(int i) {
        if (Build.VERSION.SDK_INT >= 31) {
            this.mWindowParams.setBlurBehindRadius(i);
            addWindowFlags(4);
            postUpdate();
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setScreenOrientation(int i) {
        this.mWindowParams.screenOrientation = i;
        postUpdate();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setWindowParams(WindowManager.LayoutParams layoutParams) {
        this.mWindowParams = layoutParams;
        postUpdate();
        return this;
    }

    public X setDraggable() {
        return (X) setDraggable(new MovingDraggable());
    }

    public X setDraggable(BaseDraggable baseDraggable) {
        return (X) setDraggable(baseDraggable, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setDraggable(BaseDraggable baseDraggable, boolean z) {
        this.mDraggable = baseDraggable;
        if (baseDraggable != null) {
            removeWindowFlags(16);
            removeWindowFlags(512);
            if (isShowing()) {
                update();
                baseDraggable.start(this);
            }
        }
        if (z) {
            if (this.mScreenOrientationMonitor == null) {
                this.mScreenOrientationMonitor = new ScreenOrientationMonitor(this.mContext.getResources().getConfiguration());
            }
            this.mScreenOrientationMonitor.registerCallback(this.mContext, this);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setDuration(int i) {
        this.mDuration = i;
        if (isShowing() && this.mDuration != 0) {
            removeCallbacks(this);
            postDelayed(this, this.mDuration);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setOnWindowLifecycle(OnWindowLifecycle onWindowLifecycle) {
        this.mListener = onWindowLifecycle;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setDecorView(ViewGroup viewGroup) {
        this.mDecorView = viewGroup;
        return this;
    }

    public X setContentView(int i) {
        return (X) setContentView(LayoutInflater.from(this.mContext).inflate(i, this.mDecorView, false));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setContentView(View view) {
        int i;
        int i2;
        if (this.mDecorView.getChildCount() > 0) {
            this.mDecorView.removeAllViews();
        }
        this.mDecorView.addView(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        boolean z = false;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.bottomMargin = 0;
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.rightMargin = 0;
        }
        if (this.mWindowParams.gravity == 0) {
            if ((layoutParams instanceof FrameLayout.LayoutParams) && (i2 = ((FrameLayout.LayoutParams) layoutParams).gravity) != -1) {
                this.mWindowParams.gravity = i2;
            }
            if ((layoutParams instanceof LinearLayout.LayoutParams) && (i = ((LinearLayout.LayoutParams) layoutParams).gravity) != -1) {
                this.mWindowParams.gravity = i;
            }
            if (this.mWindowParams.gravity == 0) {
                this.mWindowParams.gravity = 17;
            }
        }
        if (layoutParams != null) {
            if (this.mWindowParams.width == -2 && this.mWindowParams.height == -2) {
                z = true;
            }
            WindowManager.LayoutParams layoutParams2 = this.mWindowParams;
            layoutParams2.width = z ? layoutParams.width : layoutParams2.width;
            WindowManager.LayoutParams layoutParams3 = this.mWindowParams;
            layoutParams3.height = z ? layoutParams.height : layoutParams3.height;
        }
        postUpdate();
        return this;
    }

    public void showAsDropDown(View view) {
        showAsDropDown(view, 80);
    }

    public void showAsDropDown(View view, int i) {
        showAsDropDown(view, i, 0, 0);
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (this.mDecorView.getChildCount() == 0 || this.mWindowParams == null) {
            throw new IllegalArgumentException("WindowParams and view cannot be empty");
        }
        if (Build.VERSION.SDK_INT >= 17) {
            i = Gravity.getAbsoluteGravity(i, view.getResources().getConfiguration().getLayoutDirection());
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        this.mWindowParams.gravity = 8388659;
        this.mWindowParams.x = (iArr[0] - rect.left) + i2;
        this.mWindowParams.y = (iArr[1] - rect.top) + i3;
        if ((i & 3) == 3) {
            int width = this.mDecorView.getWidth();
            if (width == 0) {
                width = this.mDecorView.getMeasuredWidth();
            }
            if (width == 0) {
                this.mDecorView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                width = this.mDecorView.getMeasuredWidth();
            }
            this.mWindowParams.x -= width;
        } else if ((i & 5) == 5) {
            this.mWindowParams.x += view.getWidth();
        }
        if ((i & 48) == 48) {
            int height = this.mDecorView.getHeight();
            if (height == 0) {
                height = this.mDecorView.getMeasuredHeight();
            }
            if (height == 0) {
                this.mDecorView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                height = this.mDecorView.getMeasuredHeight();
            }
            this.mWindowParams.y -= height;
        } else if ((i & 80) == 80) {
            this.mWindowParams.y += view.getHeight();
        }
        show();
    }

    public void show() {
        if (this.mDecorView.getChildCount() == 0 || this.mWindowParams == null) {
            throw new IllegalArgumentException("WindowParams and view cannot be empty");
        }
        if (this.mShowing) {
            update();
            return;
        }
        Context context = this.mContext;
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 17 && ((Activity) this.mContext).isDestroyed()) {
                return;
            }
        }
        try {
            if (this.mDecorView.getParent() != null) {
                this.mWindowManager.removeViewImmediate(this.mDecorView);
            }
            this.mWindowManager.addView(this.mDecorView, this.mWindowParams);
            this.mShowing = true;
            if (this.mDuration != 0) {
                removeCallbacks(this);
                postDelayed(this, this.mDuration);
            }
            if (this.mDraggable != null) {
                this.mDraggable.start(this);
            }
            if (this.mLifecycle != null) {
                this.mLifecycle.register();
            }
            if (this.mListener != null) {
                this.mListener.onWindowShow(this);
            }
        } catch (WindowManager.BadTokenException | IllegalArgumentException | IllegalStateException | NullPointerException unused) {
        }
    }

    public void cancel() {
        if (this.mShowing) {
            try {
                if (this.mLifecycle != null) {
                    this.mLifecycle.unregister();
                }
                this.mWindowManager.removeViewImmediate(this.mDecorView);
                removeCallbacks(this);
                if (this.mListener != null) {
                    this.mListener.onWindowCancel(this);
                }
            } catch (IllegalArgumentException | IllegalStateException | NullPointerException unused) {
            } catch (Throwable th) {
                this.mShowing = false;
                throw th;
            }
            this.mShowing = false;
        }
    }

    public void postUpdate() {
        if (isShowing()) {
            removeCallbacks(this.mUpdateRunnable);
            post(this.mUpdateRunnable);
        }
    }

    public void update() {
        if (isShowing()) {
            try {
                this.mWindowManager.updateViewLayout(this.mDecorView, this.mWindowParams);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public void recycle() {
        if (isShowing()) {
            cancel();
        }
        ScreenOrientationMonitor screenOrientationMonitor = this.mScreenOrientationMonitor;
        if (screenOrientationMonitor != null) {
            screenOrientationMonitor.unregisterCallback(this.mContext);
        }
        OnWindowLifecycle onWindowLifecycle = this.mListener;
        if (onWindowLifecycle != null) {
            onWindowLifecycle.onWindowRecycler(this);
        }
        this.mListener = null;
        this.mContext = null;
        this.mDecorView = null;
        this.mWindowManager = null;
        this.mWindowParams = null;
        this.mLifecycle = null;
        this.mDraggable = null;
    }

    public int getVisibility() {
        return this.mDecorView.getVisibility();
    }

    public void setVisibility(int i) {
        if (this.mDecorView.getVisibility() == i) {
            return;
        }
        this.mDecorView.setVisibility(i);
        OnWindowLifecycle onWindowLifecycle = this.mListener;
        if (onWindowLifecycle != null) {
            onWindowLifecycle.onWindowVisibilityChanged(this, i);
        }
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public WindowManager getWindowManager() {
        return this.mWindowManager;
    }

    public WindowManager.LayoutParams getWindowParams() {
        return this.mWindowParams;
    }

    public Context getContext() {
        return this.mContext;
    }

    public View getDecorView() {
        return this.mDecorView;
    }

    public View getContentView() {
        if (this.mDecorView.getChildCount() == 0) {
            return null;
        }
        return this.mDecorView.getChildAt(0);
    }

    public int getViewWidth() {
        return getDecorView().getWidth();
    }

    public int getViewHeight() {
        return getDecorView().getHeight();
    }

    public <V extends View> V findViewById(int i) {
        return (V) this.mDecorView.findViewById(i);
    }

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this.mContext, cls));
    }

    public void startActivity(Intent intent) {
        if (!(this.mContext instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.mContext.startActivity(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setVisibility(int i, int i2) {
        findViewById(i).setVisibility(i2);
        return this;
    }

    public X setText(int i) {
        return (X) setText(android.R.id.message, i);
    }

    public X setText(int i, int i2) {
        return (X) setText(i, this.mContext.getResources().getString(i2));
    }

    public X setText(CharSequence charSequence) {
        return (X) setText(android.R.id.message, charSequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setText(int i, CharSequence charSequence) {
        ((TextView) findViewById(i)).setText(charSequence);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setTextColor(int i, int i2) {
        ((TextView) findViewById(i)).setTextColor(i2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setTextSize(int i, float f) {
        ((TextView) findViewById(i)).setTextSize(f);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setTextSize(int i, int i2, float f) {
        ((TextView) findViewById(i)).setTextSize(i2, f);
        return this;
    }

    public X setHint(int i, int i2) {
        return (X) setHint(i, this.mContext.getResources().getString(i2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setHint(int i, CharSequence charSequence) {
        ((TextView) findViewById(i)).setHint(charSequence);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setHintColor(int i, int i2) {
        ((TextView) findViewById(i)).setHintTextColor(i2);
        return this;
    }

    public X setBackground(int i, int i2) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = this.mContext.getDrawable(i2);
        } else {
            drawable = this.mContext.getResources().getDrawable(i2);
        }
        return (X) setBackground(i, drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setBackground(int i, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            findViewById(i).setBackground(drawable);
        } else {
            findViewById(i).setBackgroundDrawable(drawable);
        }
        return this;
    }

    public X setImageDrawable(int i, int i2) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = this.mContext.getDrawable(i2);
        } else {
            drawable = this.mContext.getResources().getDrawable(i2);
        }
        return (X) setImageDrawable(i, drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public X setImageDrawable(int i, Drawable drawable) {
        ((ImageView) findViewById(i)).setImageDrawable(drawable);
        return this;
    }

    public Handler getHandler() {
        return HANDLER;
    }

    public boolean post(Runnable runnable) {
        return postDelayed(runnable, 0L);
    }

    public boolean postDelayed(Runnable runnable, long j) {
        if (j < 0) {
            j = 0;
        }
        return postAtTime(runnable, SystemClock.uptimeMillis() + j);
    }

    public boolean postAtTime(Runnable runnable, long j) {
        return HANDLER.postAtTime(runnable, this, j);
    }

    public void removeCallbacks(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }

    public void removeCallbacksAndMessages() {
        HANDLER.removeCallbacksAndMessages(this);
    }

    public X setOnClickListener(OnClickListener<? extends View> onClickListener) {
        return (X) setOnClickListener(this.mDecorView, onClickListener);
    }

    public X setOnClickListener(int i, OnClickListener<? extends View> onClickListener) {
        return (X) setOnClickListener(findViewById(i), onClickListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private X setOnClickListener(View view, OnClickListener<? extends View> onClickListener) {
        removeWindowFlags(16);
        view.setClickable(true);
        view.setOnClickListener(new ViewClickWrapper(this, onClickListener));
        return this;
    }

    public X setOnLongClickListener(OnLongClickListener<? extends View> onLongClickListener) {
        return (X) setOnLongClickListener(this.mDecorView, onLongClickListener);
    }

    public X setOnLongClickListener(int i, OnLongClickListener<? extends View> onLongClickListener) {
        return (X) setOnLongClickListener(findViewById(i), onLongClickListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private X setOnLongClickListener(View view, OnLongClickListener<? extends View> onLongClickListener) {
        removeWindowFlags(16);
        view.setClickable(true);
        view.setOnLongClickListener(new ViewLongClickWrapper(this, onLongClickListener));
        return this;
    }

    public X setOnTouchListener(OnTouchListener<? extends View> onTouchListener) {
        return (X) setOnTouchListener(this.mDecorView, onTouchListener);
    }

    public X setOnTouchListener(int i, OnTouchListener<? extends View> onTouchListener) {
        return (X) setOnTouchListener(findViewById(i), onTouchListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private X setOnTouchListener(View view, OnTouchListener<? extends View> onTouchListener) {
        removeWindowFlags(16);
        view.setEnabled(true);
        view.setOnTouchListener(new ViewTouchWrapper(this, onTouchListener));
        return this;
    }

    @Override // java.lang.Runnable
    public void run() {
        cancel();
    }

    @Override // com.sy.window.ScreenOrientationMonitor.OnScreenOrientationCallback
    public void onScreenOrientationChange(int i) {
        BaseDraggable baseDraggable;
        if (isShowing() && (baseDraggable = this.mDraggable) != null) {
            baseDraggable.onScreenOrientationChange();
        }
    }
}
