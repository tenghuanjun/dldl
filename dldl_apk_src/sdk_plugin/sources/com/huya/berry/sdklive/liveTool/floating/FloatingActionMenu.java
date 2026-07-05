package com.huya.berry.sdklive.liveTool.floating;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.utils.NotchUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUtil;
import com.huya.berry.sdklive.liveTool.floating.animation.DefaultAnimationHandler;
import com.huya.berry.sdklive.liveTool.floating.animation.MenuAnimationHandler;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.msdk.api.IMUrl;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FloatingActionMenu {
    private static final String TAG = "FloatWindow";
    private boolean animated;
    private MenuAnimationHandler animationHandler;
    private int endAngle;
    private View mainActionView;
    private boolean open = false;
    private OrientationEventListener orientationListener;
    private FrameLayout overlayContainer;
    private int radius;
    private int startAngle;
    private int startX;
    private int startY;
    private MenuStateChangeListener stateChangeListener;
    private List<Item> subActionItems;
    private boolean systemOverlay;

    public interface MenuStateChangeListener {
        void onMenuClosed(FloatingActionMenu floatingActionMenu);

        void onMenuOpened(FloatingActionMenu floatingActionMenu);
    }

    public FloatingActionMenu(View view, int i, int i2, int i3, List<Item> list, MenuAnimationHandler menuAnimationHandler, boolean z, MenuStateChangeListener menuStateChangeListener, boolean z2) {
        this.mainActionView = view;
        this.startAngle = i;
        this.endAngle = i2;
        this.radius = i3;
        this.subActionItems = list;
        this.animationHandler = menuAnimationHandler;
        this.animated = z;
        this.systemOverlay = z2;
        this.stateChangeListener = menuStateChangeListener;
        if (menuAnimationHandler != null) {
            menuAnimationHandler.setMenu(this);
        }
        if (z2) {
            if (this.overlayContainer == null) {
                this.overlayContainer = new FrameLayout(view.getContext()) { // from class: com.huya.berry.sdklive.liveTool.floating.FloatingActionMenu.1
                    @Override // android.view.ViewGroup, android.view.View
                    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
                            if (FloatingActionMenu.this.isOpen()) {
                                FloatingActionMenu.this.toggle();
                            }
                            return true;
                        }
                        return super.dispatchKeyEvent(keyEvent);
                    }
                };
            }
            getWindowManager().addView(this.overlayContainer, calculateOverlayContainerParams());
            this.overlayContainer.setVisibility(8);
        } else {
            this.overlayContainer = null;
        }
        for (Item item : list) {
            if (item.width == 0 || item.height == 0) {
                if (z2) {
                    throw new RuntimeException("Sub action views cannot be added without definite width and height.");
                }
                addViewToCurrentContainer(item.view);
                item.view.setAlpha(0.0f);
                item.view.post(new ItemViewQueueListener(item));
            }
        }
        if (z2) {
            OrientationEventListener orientationEventListener = new OrientationEventListener(view.getContext(), 2) { // from class: com.huya.berry.sdklive.liveTool.floating.FloatingActionMenu.2
                private int lastState = -1;

                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int i4) {
                    int rotation = FloatingActionMenu.this.getWindowManager().getDefaultDisplay().getRotation();
                    if (rotation != this.lastState) {
                        this.lastState = rotation;
                        if (FloatingActionMenu.this.isOpen()) {
                            FloatingActionMenu.this.close(false);
                        }
                    }
                }
            };
            this.orientationListener = orientationEventListener;
            orientationEventListener.enable();
        }
    }

    public static WindowManager.LayoutParams getDefaultSystemWindowParams() {
        WindowManager.LayoutParams defaultSystemWindowParams = SystemUtil.getDefaultSystemWindowParams(-2, -2);
        defaultSystemWindowParams.flags = 256;
        defaultSystemWindowParams.format = 1;
        defaultSystemWindowParams.gravity = 51;
        return defaultSystemWindowParams;
    }

    public void open(boolean z) {
        WindowManager.LayoutParams layoutParams;
        MenuAnimationHandler menuAnimationHandler;
        Point pointCalculateItemPositions = calculateItemPositions();
        if (this.systemOverlay) {
            attachOverlayContainer();
            layoutParams = (WindowManager.LayoutParams) this.overlayContainer.getLayoutParams();
        } else {
            layoutParams = null;
        }
        if (z && (menuAnimationHandler = this.animationHandler) != null) {
            if (menuAnimationHandler.isAnimating()) {
                return;
            }
            for (int i = 0; i < this.subActionItems.size(); i++) {
                if (this.subActionItems.get(i).view.getParent() != null) {
                    throw new RuntimeException("All of the sub action items have to be independent from a parent.");
                }
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.subActionItems.get(i).width, this.subActionItems.get(i).height, 51);
                if (this.systemOverlay) {
                    layoutParams2.setMargins((pointCalculateItemPositions.x - (layoutParams != null ? layoutParams.x : 0)) - (this.subActionItems.get(i).width / 2), (pointCalculateItemPositions.y - (layoutParams != null ? layoutParams.y : 0)) - (this.subActionItems.get(i).height / 2), 0, 0);
                } else {
                    layoutParams2.setMargins(pointCalculateItemPositions.x - (this.subActionItems.get(i).width / 2), pointCalculateItemPositions.y - (this.subActionItems.get(i).height / 2), 0, 0);
                }
                addViewToCurrentContainer(this.subActionItems.get(i).view, layoutParams2);
            }
            this.animationHandler.animateMenuOpening(pointCalculateItemPositions);
        } else {
            for (int i2 = 0; i2 < this.subActionItems.size(); i2++) {
                FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(this.subActionItems.get(i2).width, this.subActionItems.get(i2).height, 51);
                if (this.systemOverlay) {
                    layoutParams3.setMargins(this.subActionItems.get(i2).x - (layoutParams != null ? layoutParams.x : 0), this.subActionItems.get(i2).y - (layoutParams != null ? layoutParams.y : 0), 0, 0);
                    this.subActionItems.get(i2).view.setLayoutParams(layoutParams3);
                } else {
                    layoutParams3.setMargins(this.subActionItems.get(i2).x, this.subActionItems.get(i2).y, 0, 0);
                    this.subActionItems.get(i2).view.setLayoutParams(layoutParams3);
                }
                addViewToCurrentContainer(this.subActionItems.get(i2).view, layoutParams3);
            }
        }
        this.open = true;
        MenuStateChangeListener menuStateChangeListener = this.stateChangeListener;
        if (menuStateChangeListener != null) {
            menuStateChangeListener.onMenuOpened(this);
        }
    }

    public void close(boolean z) {
        MenuAnimationHandler menuAnimationHandler;
        if (z && (menuAnimationHandler = this.animationHandler) != null) {
            if (menuAnimationHandler.isAnimating()) {
                return;
            } else {
                this.animationHandler.animateMenuClosing(getActionViewCenter());
            }
        } else {
            for (int i = 0; i < this.subActionItems.size(); i++) {
                removeViewFromCurrentContainer(this.subActionItems.get(i).view);
            }
            detachOverlayContainer();
        }
        this.open = false;
        MenuStateChangeListener menuStateChangeListener = this.stateChangeListener;
        if (menuStateChangeListener != null) {
            menuStateChangeListener.onMenuClosed(this);
        }
    }

    public void toggle() {
        toggle(this.animated);
    }

    public void toggle(boolean z) {
        if (this.open) {
            close(z);
        } else {
            open(z);
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isSystemOverlay() {
        return this.systemOverlay;
    }

    public FrameLayout getOverlayContainer() {
        return this.overlayContainer;
    }

    public void destroy() {
        FrameLayout frameLayout = this.overlayContainer;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
            if (this.overlayContainer.getParent() != null) {
                getWindowManager().removeView(this.overlayContainer);
            }
            this.overlayContainer = null;
        }
    }

    public void updateItemPositions() {
        if (isOpen()) {
            calculateItemPositions();
            for (int i = 0; i < this.subActionItems.size(); i++) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.subActionItems.get(i).width, this.subActionItems.get(i).height, 51);
                layoutParams.setMargins(this.subActionItems.get(i).x, this.subActionItems.get(i).y, 0, 0);
                this.subActionItems.get(i).view.setLayoutParams(layoutParams);
            }
        }
    }

    public void updatePosition(int i, int i2, boolean z) {
        if (NotchUtil.needCalNotchSize(ArkValue.gContext) && z) {
            this.startX = i - NotchUtil.getNotchSize(ArkValue.gContext);
        } else {
            this.startX = i;
        }
        this.startY = i2;
        L.info(TAG, "updatePosition:" + this.startX + "," + this.startY);
    }

    private Point getActionViewCoordinates() {
        int[] iArr = new int[2];
        this.mainActionView.getLocationOnScreen(iArr);
        if (!this.systemOverlay) {
            Rect rect = new Rect();
            getActivityContentView().getWindowVisibleDisplayFrame(rect);
            iArr[0] = iArr[0] - (getScreenSize().x - getActivityContentView().getMeasuredWidth());
            iArr[1] = iArr[1] - ((rect.height() + rect.top) - getActivityContentView().getMeasuredHeight());
        }
        if (NotchUtil.needCalNotchSize(ArkValue.gContext)) {
            iArr[0] = iArr[0] - NotchUtil.getNotchSize(ArkValue.gContext);
        }
        return new Point(iArr[0], iArr[1]);
    }

    public Point getActionViewCenter() {
        Point point = new Point(this.startX, this.startY);
        point.x += this.mainActionView.getMeasuredWidth() / 2;
        point.y += this.mainActionView.getMeasuredHeight() / 2;
        return point;
    }

    private Point calculateItemPositions() {
        int size;
        Point actionViewCenter = getActionViewCenter();
        RectF rectF = new RectF(actionViewCenter.x - this.radius, actionViewCenter.y - this.radius, actionViewCenter.x + this.radius, actionViewCenter.y + this.radius);
        Path path = new Path();
        path.addArc(rectF, this.startAngle, this.endAngle - r3);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        if (Math.abs(this.endAngle - this.startAngle) >= 360 || this.subActionItems.size() <= 1) {
            size = this.subActionItems.size();
        } else {
            size = this.subActionItems.size() - 1;
        }
        for (int i = 0; i < this.subActionItems.size(); i++) {
            float[] fArr = {0.0f, 0.0f};
            pathMeasure.getPosTan((i * pathMeasure.getLength()) / size, fArr, null);
            this.subActionItems.get(i).x = ((int) fArr[0]) - (this.subActionItems.get(i).width / 2);
            this.subActionItems.get(i).y = ((int) fArr[1]) - (this.subActionItems.get(i).height / 2);
        }
        return actionViewCenter;
    }

    public int getRadius() {
        return this.radius;
    }

    public List<Item> getSubActionItems() {
        return this.subActionItems;
    }

    public View getActivityContentView() {
        try {
            return ((Activity) this.mainActionView.getContext()).getWindow().getDecorView().findViewById(R.id.content);
        } catch (ClassCastException unused) {
            throw new ClassCastException("Please provide an Activity context for this FloatingActionMenu.");
        }
    }

    public WindowManager getWindowManager() {
        return (WindowManager) this.mainActionView.getContext().getSystemService("window");
    }

    private void addViewToCurrentContainer(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.systemOverlay) {
            if (view.getParent() == null) {
                this.overlayContainer.addView(view, layoutParams);
                return;
            } else {
                this.overlayContainer.updateViewLayout(view, layoutParams);
                return;
            }
        }
        try {
            if (layoutParams != null) {
                ((ViewGroup) getActivityContentView()).addView(view, (FrameLayout.LayoutParams) layoutParams);
            } else {
                ((ViewGroup) getActivityContentView()).addView(view);
            }
        } catch (ClassCastException unused) {
            throw new ClassCastException("layoutParams must be an instance of FrameLayout.LayoutParams.");
        }
    }

    public void attachOverlayContainer() {
        FrameLayout frameLayout = this.overlayContainer;
        if (frameLayout == null) {
            return;
        }
        try {
            WindowManager.LayoutParams layoutParamsCalculateOverlayContainerParams = (WindowManager.LayoutParams) frameLayout.getLayoutParams();
            if (layoutParamsCalculateOverlayContainerParams == null) {
                layoutParamsCalculateOverlayContainerParams = calculateOverlayContainerParams();
                this.overlayContainer.setLayoutParams(layoutParamsCalculateOverlayContainerParams);
            } else {
                calculateOverlayContainerParams(layoutParamsCalculateOverlayContainerParams);
            }
            getWindowManager().updateViewLayout(this.overlayContainer, layoutParamsCalculateOverlayContainerParams);
            this.overlayContainer.setClickable(false);
            this.overlayContainer.setVisibility(0);
        } catch (SecurityException unused) {
            throw new SecurityException("Your application must have SYSTEM_ALERT_WINDOW permission to create a system window.");
        }
    }

    private WindowManager.LayoutParams calculateOverlayContainerParams() {
        return calculateOverlayContainerParams(getDefaultSystemWindowParams());
    }

    private WindowManager.LayoutParams calculateOverlayContainerParams(WindowManager.LayoutParams layoutParams) {
        int i = 9999;
        int i2 = 9999;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.subActionItems.size(); i5++) {
            int i6 = this.subActionItems.get(i5).x;
            int i7 = this.subActionItems.get(i5).y;
            if (i6 < i) {
                i = i6;
            }
            if (i7 < i2) {
                i2 = i7;
            }
            if (this.subActionItems.get(i5).width + i6 > i3) {
                i3 = i6 + this.subActionItems.get(i5).width;
            }
            if (this.subActionItems.get(i5).height + i7 > i4) {
                i4 = i7 + this.subActionItems.get(i5).height;
            }
        }
        layoutParams.width = i3 - i;
        layoutParams.height = i4 - i2;
        layoutParams.x = i;
        layoutParams.y = i2;
        layoutParams.gravity = 51;
        return layoutParams;
    }

    public void detachOverlayContainer() {
        FrameLayout frameLayout = this.overlayContainer;
        if (frameLayout == null) {
            return;
        }
        frameLayout.setVisibility(8);
    }

    public int getStatusBarHeight() {
        int identifier = this.mainActionView.getContext().getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return this.mainActionView.getContext().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public void addViewToCurrentContainer(View view) {
        addViewToCurrentContainer(view, null);
    }

    public void removeViewFromCurrentContainer(View view) {
        if (this.systemOverlay) {
            FrameLayout frameLayout = this.overlayContainer;
            if (frameLayout == null) {
                return;
            }
            frameLayout.removeView(view);
            return;
        }
        if (getActivityContentView() == null) {
            return;
        }
        ((ViewGroup) getActivityContentView()).removeView(view);
    }

    private Point getScreenSize() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    public void setStateChangeListener(MenuStateChangeListener menuStateChangeListener) {
        this.stateChangeListener = menuStateChangeListener;
    }

    public void setAngle(int i, int i2) {
        this.startAngle = i;
        this.endAngle = i2;
    }

    public static class Item {
        public float alpha;
        public int height;
        public View view;
        public int width;
        public int x = 0;
        public int y = 0;

        public Item(View view, int i, int i2) {
            this.view = view;
            this.width = i;
            this.height = i2;
            this.alpha = view.getAlpha();
        }
    }

    public static class Builder {
        private View actionView;
        private boolean animated;
        private MenuAnimationHandler animationHandler;
        private int endAngle;
        private int radius;
        private int startAngle;
        private MenuStateChangeListener stateChangeListener;
        private List<Item> subActionItems;
        private boolean systemOverlay;

        public Builder(Context context, boolean z) {
            this.subActionItems = new ArrayList();
            this.radius = context.getResources().getDimensionPixelSize(ResourceUtil.getDimenResIDByName(SqR.dimen.hyberry_action_menu_radius));
            this.startAngle = 180;
            this.endAngle = 270;
            this.animationHandler = new DefaultAnimationHandler();
            this.animated = true;
            this.systemOverlay = z;
        }

        public Builder(Context context) {
            this(context, false);
        }

        public Builder setStartAngle(int i) {
            this.startAngle = i;
            return this;
        }

        public Builder setEndAngle(int i) {
            this.endAngle = i;
            return this;
        }

        public Builder setRadius(int i) {
            this.radius = i;
            return this;
        }

        public Builder addSubActionView(View view, int i, int i2) {
            this.subActionItems.add(new Item(view, i, i2));
            return this;
        }

        public Builder addSubActionView(View view) {
            if (this.systemOverlay) {
                throw new RuntimeException("Sub action views cannot be added without definite width and height. Please use other methods named addSubActionView");
            }
            return addSubActionView(view, 0, 0);
        }

        public Builder addSubActionView(int i, Context context) {
            View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, (ViewGroup) null, false);
            viewInflate.measure(0, 0);
            return addSubActionView(viewInflate, viewInflate.getMeasuredWidth(), viewInflate.getMeasuredHeight());
        }

        public Builder setAnimationHandler(MenuAnimationHandler menuAnimationHandler) {
            this.animationHandler = menuAnimationHandler;
            return this;
        }

        public Builder enableAnimations() {
            this.animated = true;
            return this;
        }

        public Builder disableAnimations() {
            this.animated = false;
            return this;
        }

        public Builder setStateChangeListener(MenuStateChangeListener menuStateChangeListener) {
            this.stateChangeListener = menuStateChangeListener;
            return this;
        }

        public Builder setSystemOverlay(boolean z) {
            this.systemOverlay = z;
            return this;
        }

        public Builder attachTo(View view) {
            this.actionView = view;
            return this;
        }

        public FloatingActionMenu build() {
            return new FloatingActionMenu(this.actionView, this.startAngle, this.endAngle, this.radius, this.subActionItems, this.animationHandler, this.animated, this.stateChangeListener, this.systemOverlay);
        }
    }

    public class ActionViewClickListener implements View.OnClickListener {
        public ActionViewClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FloatingActionMenu floatingActionMenu = FloatingActionMenu.this;
            floatingActionMenu.toggle(floatingActionMenu.animated);
        }
    }

    private class ItemViewQueueListener implements Runnable {
        private static final int MAX_TRIES = 10;
        private Item item;
        private int tries = 0;

        public ItemViewQueueListener(Item item) {
            this.item = item;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.item.view.getMeasuredWidth() == 0 && this.tries < 10) {
                this.item.view.post(this);
                return;
            }
            Item item = this.item;
            item.width = item.view.getMeasuredWidth();
            Item item2 = this.item;
            item2.height = item2.view.getMeasuredHeight();
            this.item.view.setAlpha(this.item.alpha);
            FloatingActionMenu.this.removeViewFromCurrentContainer(this.item.view);
        }
    }
}
