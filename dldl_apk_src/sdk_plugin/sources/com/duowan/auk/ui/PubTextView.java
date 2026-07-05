package com.duowan.auk.ui;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import com.duowan.auk.util.L;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PubTextView extends LinearLayout {
    private volatile int addNum;
    private LayoutTransition layoutTransition;
    private int limit_height;
    private Queue<View> mQueue;
    private Queue<String> mTextQueue;
    private PubViewGenerator mViewGenerator;
    private int maxNum;
    private float[] startAlpha;

    public static class PubViewGenerator {
        public void bindView(View view, String str, String str2) {
        }

        public View createView() {
            return null;
        }
    }

    static /* synthetic */ int access$010(PubTextView pubTextView) {
        int i = pubTextView.addNum;
        pubTextView.addNum = i - 1;
        return i;
    }

    public PubTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.limit_height = (getScreenRealHeight((Activity) getContext()) / 3) * 2;
        this.addNum = 0;
        this.maxNum = 100;
        this.mQueue = new LinkedList();
        this.mTextQueue = new LinkedList();
        this.startAlpha = new float[]{1.0f, 0.9f, 0.5f, 0.2f, 0.0f};
        init();
    }

    private void init() {
        setOrientation(1);
        LayoutTransition layoutTransition = new LayoutTransition();
        this.layoutTransition = layoutTransition;
        layoutTransition.setDuration(0, 300L);
        this.layoutTransition.setDuration(2, 0L);
        this.layoutTransition.setStartDelay(0, 0L);
        this.layoutTransition.setAnimator(1, null);
        this.layoutTransition.setAnimator(4, null);
        this.layoutTransition.setAnimator(3, null);
        if (16 < Build.VERSION.SDK_INT) {
            this.layoutTransition.disableTransitionType(3);
            this.layoutTransition.disableTransitionType(1);
        }
        this.layoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() { // from class: com.duowan.auk.ui.PubTextView.1
            @Override // android.animation.LayoutTransition.TransitionListener
            public void startTransition(LayoutTransition layoutTransition2, ViewGroup viewGroup, View view, int i) {
            }

            @Override // android.animation.LayoutTransition.TransitionListener
            public void endTransition(LayoutTransition layoutTransition2, ViewGroup viewGroup, View view, int i) {
                if (i == 0) {
                    PubTextView.this.getChildCount();
                    if (PubTextView.this.addNum > PubTextView.this.maxNum) {
                        PubTextView.access$010(PubTextView.this);
                        View childAt = PubTextView.this.getChildAt(0);
                        PubTextView.this.removeViewAt(0);
                        if (childAt.getParent() == null) {
                            L.debug(PubTextView.class, "add child to cache queue");
                            ((ObjectAnimator) childAt.getTag()).cancel();
                            childAt.setAlpha(1.0f);
                            PubTextView.this.mQueue.offer(childAt);
                        }
                    }
                }
            }
        });
        setLayoutTransition(this.layoutTransition);
    }

    public void insertPriorityText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mTextQueue.offer(str);
    }

    public void notifyTextChange(String str) {
        while (this.mTextQueue.size() > 0) {
            addText(str, "");
        }
    }

    public boolean addText(String str, String str2) {
        ObjectAnimator objectAnimatorOfFloat;
        if (this.mViewGenerator == null) {
            return false;
        }
        if (this.layoutTransition.isRunning()) {
            L.debug(PubTextView.class, "transition is running");
            return false;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (getHeight() > this.limit_height && this.maxNum == 100) {
            this.maxNum = this.addNum;
        }
        View viewCreateView = null;
        if (this.mQueue.size() > 0) {
            viewCreateView = this.mQueue.peek();
            objectAnimatorOfFloat = (ObjectAnimator) viewCreateView.getTag();
        } else {
            objectAnimatorOfFloat = null;
        }
        if (viewCreateView == null || viewCreateView.getParent() != null) {
            viewCreateView = this.mViewGenerator.createView();
            L.debug(PubTextView.class, "create view");
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewCreateView, "alpha", this.startAlpha);
            objectAnimatorOfFloat.setInterpolator(new AccelerateInterpolator());
            objectAnimatorOfFloat.setStartDelay(OAIDHelper.TIMEOUT);
            objectAnimatorOfFloat.setDuration(OAIDHelper.TIMEOUT);
            viewCreateView.setTag(objectAnimatorOfFloat);
        } else {
            L.debug(PubTextView.class, "use a recycle view");
            this.mQueue.poll();
            L.debug(PubTextView.class, "mqueue size: " + this.mQueue.size());
        }
        if (this.mTextQueue.size() > 0) {
            str2 = this.mTextQueue.poll();
        }
        this.mViewGenerator.bindView(viewCreateView, str, str2);
        addView(viewCreateView);
        objectAnimatorOfFloat.start();
        this.addNum++;
        return true;
    }

    public void setViewGenerator(PubViewGenerator pubViewGenerator) {
        this.mViewGenerator = pubViewGenerator;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mQueue.clear();
        this.mTextQueue.clear();
    }

    public static int getScreenRealHeight(Activity activity) {
        if (activity == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return getScreenRealSizeAPI17(activity).y;
        }
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            return getRawSize(activity, "getRawHeight", "getScreenRealHeight");
        }
        return getScreenSizeAPILower(activity, true);
    }

    private static Point getScreenRealSizeAPI17(Activity activity) {
        Point point = new Point();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        }
        return point;
    }

    private static int getRawSize(Activity activity, String str, String str2) {
        try {
            return ((Integer) Display.class.getMethod(str, new Class[0]).invoke(activity.getWindowManager().getDefaultDisplay(), new Object[0])).intValue();
        } catch (Exception e) {
            L.error("SystemUI", "SystemUI " + str2 + " Exception " + e.toString());
            return -1;
        }
    }

    private static int getScreenSizeAPILower(Activity activity, boolean z) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return z ? displayMetrics.heightPixels : displayMetrics.widthPixels;
    }
}
