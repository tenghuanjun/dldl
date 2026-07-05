package com.huya.berry.sdklive.liveTool.floating.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Point;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.huya.berry.sdklive.liveTool.floating.FloatingActionMenu;
import com.huya.berry.sdklive.liveTool.floating.animation.MenuAnimationHandler;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DefaultAnimationHandler extends MenuAnimationHandler {
    protected static final int DURATION = 200;
    protected static final int LAG_BETWEEN_ITEMS = 20;
    private boolean animating;

    public DefaultAnimationHandler() {
        setAnimating(false);
    }

    @Override // com.huya.berry.sdklive.liveTool.floating.animation.MenuAnimationHandler
    public void animateMenuOpening(Point point) {
        super.animateMenuOpening(point);
        setAnimating(true);
        ObjectAnimator objectAnimator = null;
        for (int i = 0; i < this.menu.getSubActionItems().size(); i++) {
            this.menu.getSubActionItems().get(i).view.setScaleX(0.0f);
            this.menu.getSubActionItems().get(i).view.setScaleY(0.0f);
            this.menu.getSubActionItems().get(i).view.setAlpha(0.0f);
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.menu.getSubActionItems().get(i).view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, (this.menu.getSubActionItems().get(i).x - point.x) + (this.menu.getSubActionItems().get(i).width / 2)), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, (this.menu.getSubActionItems().get(i).y - point.y) + (this.menu.getSubActionItems().get(i).height / 2)), PropertyValuesHolder.ofFloat((Property<?, Float>) View.ROTATION, 720.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, 1.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, 1.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.ALPHA, 1.0f));
            objectAnimatorOfPropertyValuesHolder.setDuration(200L);
            objectAnimatorOfPropertyValuesHolder.setInterpolator(new OvershootInterpolator(0.9f));
            objectAnimatorOfPropertyValuesHolder.addListener(new SubActionItemAnimationListener(this.menu.getSubActionItems().get(i), MenuAnimationHandler.ActionType.OPENING));
            if (i == 0) {
                objectAnimator = objectAnimatorOfPropertyValuesHolder;
            }
            objectAnimatorOfPropertyValuesHolder.setStartDelay((this.menu.getSubActionItems().size() - i) * 20);
            objectAnimatorOfPropertyValuesHolder.start();
        }
        if (objectAnimator != null) {
            objectAnimator.addListener(new MenuAnimationHandler.LastAnimationListener());
        }
    }

    @Override // com.huya.berry.sdklive.liveTool.floating.animation.MenuAnimationHandler
    public void animateMenuClosing(Point point) {
        super.animateMenuOpening(point);
        setAnimating(true);
        ObjectAnimator objectAnimator = null;
        for (int i = 0; i < this.menu.getSubActionItems().size(); i++) {
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.menu.getSubActionItems().get(i).view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, -((this.menu.getSubActionItems().get(i).x - point.x) + (this.menu.getSubActionItems().get(i).width / 2))), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, -((this.menu.getSubActionItems().get(i).y - point.y) + (this.menu.getSubActionItems().get(i).height / 2))), PropertyValuesHolder.ofFloat((Property<?, Float>) View.ROTATION, -720.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, 0.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, 0.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.ALPHA, 0.0f));
            objectAnimatorOfPropertyValuesHolder.setDuration(200L);
            objectAnimatorOfPropertyValuesHolder.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimatorOfPropertyValuesHolder.addListener(new SubActionItemAnimationListener(this.menu.getSubActionItems().get(i), MenuAnimationHandler.ActionType.CLOSING));
            if (i == 0) {
                objectAnimator = objectAnimatorOfPropertyValuesHolder;
            }
            objectAnimatorOfPropertyValuesHolder.setStartDelay((this.menu.getSubActionItems().size() - i) * 20);
            objectAnimatorOfPropertyValuesHolder.start();
        }
        if (objectAnimator != null) {
            objectAnimator.addListener(new MenuAnimationHandler.LastAnimationListener());
        }
    }

    @Override // com.huya.berry.sdklive.liveTool.floating.animation.MenuAnimationHandler
    public boolean isAnimating() {
        return this.animating;
    }

    @Override // com.huya.berry.sdklive.liveTool.floating.animation.MenuAnimationHandler
    protected void setAnimating(boolean z) {
        this.animating = z;
    }

    protected class SubActionItemAnimationListener implements Animator.AnimatorListener {
        private MenuAnimationHandler.ActionType actionType;
        private FloatingActionMenu.Item subActionItem;

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public SubActionItemAnimationListener(FloatingActionMenu.Item item, MenuAnimationHandler.ActionType actionType) {
            this.subActionItem = item;
            this.actionType = actionType;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            DefaultAnimationHandler.this.restoreSubActionViewAfterAnimation(this.subActionItem, this.actionType);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            DefaultAnimationHandler.this.restoreSubActionViewAfterAnimation(this.subActionItem, this.actionType);
        }
    }
}
