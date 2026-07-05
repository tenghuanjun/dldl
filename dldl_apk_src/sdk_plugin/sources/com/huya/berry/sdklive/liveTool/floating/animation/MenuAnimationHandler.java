package com.huya.berry.sdklive.liveTool.floating.animation;

import android.animation.Animator;
import android.graphics.Point;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.huya.berry.sdklive.liveTool.floating.FloatingActionMenu;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class MenuAnimationHandler {
    protected FloatingActionMenu menu;

    protected enum ActionType {
        OPENING,
        CLOSING
    }

    public abstract boolean isAnimating();

    protected abstract void setAnimating(boolean z);

    public void setMenu(FloatingActionMenu floatingActionMenu) {
        this.menu = floatingActionMenu;
    }

    public void animateMenuOpening(Point point) {
        if (this.menu == null) {
            throw new NullPointerException("MenuAnimationHandler cannot animate without a valid FloatingActionMenu.");
        }
    }

    public void animateMenuClosing(Point point) {
        if (this.menu == null) {
            throw new NullPointerException("MenuAnimationHandler cannot animate without a valid FloatingActionMenu.");
        }
    }

    protected void restoreSubActionViewAfterAnimation(FloatingActionMenu.Item item, ActionType actionType) {
        ViewGroup.LayoutParams layoutParams = item.view.getLayoutParams();
        item.view.setTranslationX(0.0f);
        item.view.setTranslationY(0.0f);
        item.view.setRotation(0.0f);
        item.view.setScaleX(1.0f);
        item.view.setScaleY(1.0f);
        item.view.setAlpha(1.0f);
        FloatingActionMenu floatingActionMenu = this.menu;
        if (floatingActionMenu == null || floatingActionMenu.getOverlayContainer() == null) {
            return;
        }
        if (actionType == ActionType.OPENING) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            if (this.menu.isSystemOverlay()) {
                WindowManager.LayoutParams layoutParams3 = (WindowManager.LayoutParams) this.menu.getOverlayContainer().getLayoutParams();
                layoutParams2.setMargins(item.x - layoutParams3.x, item.y - layoutParams3.y, 0, 0);
            } else {
                layoutParams2.setMargins(item.x, item.y, 0, 0);
            }
            item.view.setLayoutParams(layoutParams2);
            return;
        }
        if (actionType == ActionType.CLOSING) {
            Point actionViewCenter = this.menu.getActionViewCenter();
            FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams;
            if (this.menu.isSystemOverlay()) {
                WindowManager.LayoutParams layoutParams5 = (WindowManager.LayoutParams) this.menu.getOverlayContainer().getLayoutParams();
                layoutParams4.setMargins((actionViewCenter.x - layoutParams5.x) - (item.width / 2), (actionViewCenter.y - layoutParams5.y) - (item.height / 2), 0, 0);
            } else {
                layoutParams4.setMargins(actionViewCenter.x - (item.width / 2), actionViewCenter.y - (item.height / 2), 0, 0);
            }
            item.view.setLayoutParams(layoutParams4);
            this.menu.removeViewFromCurrentContainer(item.view);
            if (this.menu.isSystemOverlay() && this.menu.getOverlayContainer().getChildCount() == 0) {
                this.menu.detachOverlayContainer();
            }
        }
    }

    public class LastAnimationListener implements Animator.AnimatorListener {
        public LastAnimationListener() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            MenuAnimationHandler.this.setAnimating(true);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MenuAnimationHandler.this.setAnimating(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            MenuAnimationHandler.this.setAnimating(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            MenuAnimationHandler.this.setAnimating(true);
        }
    }
}
