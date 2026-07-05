package com.sqwan.common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.sqwan.base.L;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class KeyBoardUtils {
    long endtime;
    private ViewTreeObserver.OnGlobalLayoutListener globalListener;
    int phoneHeight;
    private View rootView;
    private int rootViewVisibleHeight;
    long starttime;
    private final String TAG = getClass().getSimpleName();
    private boolean isShowing = false;

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardHide(int i);

        void keyBoardShow(int i);

        void viewChanged(int i);
    }

    public void addListener(Context context, final OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        if (context instanceof Activity) {
            removeListener();
            View decorView = ((Activity) context).getWindow().getDecorView();
            this.rootView = decorView;
            if (decorView != null) {
                this.globalListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sqwan.common.util.KeyBoardUtils.1
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener2;
                        Rect rect = new Rect();
                        KeyBoardUtils.this.rootView.getWindowVisibleDisplayFrame(rect);
                        int iHeight = rect.height();
                        LogUtil.i(KeyBoardUtils.this.TAG, String.format("visibleHeight:%d,rootViewVisibleHeight:%d", Integer.valueOf(iHeight), Integer.valueOf(KeyBoardUtils.this.rootViewVisibleHeight)));
                        if (KeyBoardUtils.this.rootViewVisibleHeight == 0) {
                            KeyBoardUtils.this.rootViewVisibleHeight = iHeight;
                            KeyBoardUtils.this.phoneHeight = iHeight;
                            LogUtil.i(KeyBoardUtils.this.TAG, "初始化高度：rootViewVisibleHeight: " + KeyBoardUtils.this.rootViewVisibleHeight);
                            return;
                        }
                        if (KeyBoardUtils.this.rootViewVisibleHeight == iHeight) {
                            return;
                        }
                        if (KeyBoardUtils.this.rootViewVisibleHeight - iHeight <= KeyBoardUtils.this.phoneHeight / 4) {
                            if (iHeight - KeyBoardUtils.this.rootViewVisibleHeight <= KeyBoardUtils.this.phoneHeight / 4) {
                                if (KeyBoardUtils.this.isShowing) {
                                    KeyBoardUtils.this.endtime = System.currentTimeMillis();
                                    if (KeyBoardUtils.this.endtime - KeyBoardUtils.this.starttime > 200 && (onSoftKeyBoardChangeListener2 = onSoftKeyBoardChangeListener) != null) {
                                        onSoftKeyBoardChangeListener2.viewChanged(iHeight - KeyBoardUtils.this.rootViewVisibleHeight);
                                    }
                                }
                                KeyBoardUtils.this.rootViewVisibleHeight = iHeight;
                                return;
                            }
                            OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener3 = onSoftKeyBoardChangeListener;
                            if (onSoftKeyBoardChangeListener3 != null) {
                                onSoftKeyBoardChangeListener3.keyBoardHide(iHeight - KeyBoardUtils.this.rootViewVisibleHeight);
                            }
                            KeyBoardUtils.this.isShowing = false;
                            KeyBoardUtils.this.rootViewVisibleHeight = iHeight;
                            return;
                        }
                        OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener4 = onSoftKeyBoardChangeListener;
                        if (onSoftKeyBoardChangeListener4 != null) {
                            onSoftKeyBoardChangeListener4.keyBoardShow(KeyBoardUtils.this.rootViewVisibleHeight - iHeight);
                            KeyBoardUtils.this.starttime = System.currentTimeMillis();
                        }
                        KeyBoardUtils.this.isShowing = true;
                        KeyBoardUtils.this.rootViewVisibleHeight = iHeight;
                    }
                };
                this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(this.globalListener);
            }
        }
    }

    public void removeListener() {
        View view;
        if (this.globalListener == null || (view = this.rootView) == null) {
            return;
        }
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this.globalListener);
    }

    public boolean dispatchTouchEvent(Activity activity, MotionEvent motionEvent, List<? extends View> list) {
        if (!this.isShowing || isTouchView(Collections.singletonList(activity.getCurrentFocus()), motionEvent) || isTouchView(list, motionEvent)) {
            return false;
        }
        hideKeyboard(activity);
        return true;
    }

    private boolean isTouchView(List<? extends View> list, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        for (View view : list) {
            if (view != null) {
                view.getLocationOnScreen(iArr);
                int i = iArr[0];
                int i2 = iArr[1];
                if (motionEvent.getX() > i && motionEvent.getX() < i + view.getWidth() && motionEvent.getY() > i2 && motionEvent.getY() < i2 + view.getHeight()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void hideKeyboard(Context context) {
        if (context instanceof Activity) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public void hideInputKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showInputKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 0);
    }

    public void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) L.getApplicationContext().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public void showSoftInput(View view) {
        showSoftInput(view, 0);
    }

    public void showSoftInput(View view, int i) {
        InputMethodManager inputMethodManager = (InputMethodManager) L.getApplicationContext().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, i, new ResultReceiver(new Handler()) { // from class: com.sqwan.common.util.KeyBoardUtils.2
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i2, Bundle bundle) {
                if (i2 == 1 || i2 == 3) {
                    KeyBoardUtils.this.toggleSoftInput();
                }
            }
        });
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) L.getApplicationContext().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 0);
    }

    public void hideSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) L.getApplicationContext().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
