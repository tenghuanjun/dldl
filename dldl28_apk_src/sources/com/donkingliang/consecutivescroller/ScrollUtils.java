package com.donkingliang.consecutivescroller;

import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ScrollUtils {
    static Method computeVerticalScrollExtentMethod;
    static Method computeVerticalScrollOffsetMethod;
    static Method computeVerticalScrollRangeMethod;
    private static final Rect mBounds = new Rect();

    /* JADX WARN: Multi-variable type inference failed */
    static int computeVerticalScrollOffset(View view) {
        View scrolledView = getScrolledView(view);
        if (scrolledView instanceof ScrollingView) {
            return ((ScrollingView) scrolledView).computeVerticalScrollOffset();
        }
        try {
            if (computeVerticalScrollOffsetMethod == null) {
                Method declaredMethod = View.class.getDeclaredMethod("computeVerticalScrollOffset", null);
                computeVerticalScrollOffsetMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            Object objInvoke = computeVerticalScrollOffsetMethod.invoke(scrolledView, null);
            if (objInvoke != null) {
                return ((Integer) objInvoke).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrolledView.getScrollY();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static int computeVerticalScrollRange(View view) {
        View scrolledView = getScrolledView(view);
        if (scrolledView instanceof ScrollingView) {
            return ((ScrollingView) scrolledView).computeVerticalScrollRange();
        }
        try {
            if (computeVerticalScrollRangeMethod == null) {
                Method declaredMethod = View.class.getDeclaredMethod("computeVerticalScrollRange", null);
                computeVerticalScrollRangeMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            Object objInvoke = computeVerticalScrollRangeMethod.invoke(scrolledView, null);
            if (objInvoke != null) {
                return ((Integer) objInvoke).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrolledView.getHeight();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static int computeVerticalScrollExtent(View view) {
        View scrolledView = getScrolledView(view);
        if (scrolledView instanceof ScrollingView) {
            return ((ScrollingView) scrolledView).computeVerticalScrollExtent();
        }
        try {
            if (computeVerticalScrollExtentMethod == null) {
                Method declaredMethod = View.class.getDeclaredMethod("computeVerticalScrollExtent", null);
                computeVerticalScrollExtentMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            Object objInvoke = computeVerticalScrollExtentMethod.invoke(scrolledView, null);
            if (objInvoke != null) {
                return ((Integer) objInvoke).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrolledView.getHeight();
    }

    static int getScrollTopOffset(View view) {
        if (isConsecutiveScrollerChild(view) && canScrollVertically(view, -1)) {
            return Math.min(-computeVerticalScrollOffset(view), -1);
        }
        return 0;
    }

    static int getScrollBottomOffset(View view) {
        if (isConsecutiveScrollerChild(view) && canScrollVertically(view, 1)) {
            return Math.max((computeVerticalScrollRange(view) - computeVerticalScrollOffset(view)) - computeVerticalScrollExtent(view), 1);
        }
        return 0;
    }

    static boolean canScrollHorizontally(View view) {
        return isConsecutiveScrollerChild(view) && (view.canScrollHorizontally(1) || view.canScrollHorizontally(-1));
    }

    static boolean canScrollVertically(View view) {
        return isConsecutiveScrollerChild(view) && (canScrollVertically(view, 1) || canScrollVertically(view, -1));
    }

    static boolean canScrollVertically(View view, int direction) {
        boolean reverseLayout;
        int itemCount;
        int i;
        View scrolledView = getScrolledView(view);
        if (scrolledView.getVisibility() == 8) {
            return false;
        }
        if (scrolledView instanceof AbsListView) {
            return ((AbsListView) scrolledView).canScrollList(direction);
        }
        if (scrolledView instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) scrolledView;
            if ((recyclerView.canScrollHorizontally(1) || recyclerView.canScrollHorizontally(-1)) && !recyclerView.canScrollVertically(direction)) {
                return false;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (layoutManager != null && adapter != null && adapter.getItemCount() > 0) {
                if (layoutManager instanceof LinearLayoutManager) {
                    reverseLayout = ((LinearLayoutManager) layoutManager).getReverseLayout();
                } else {
                    reverseLayout = layoutManager instanceof StaggeredGridLayoutManager ? ((StaggeredGridLayoutManager) layoutManager).getReverseLayout() : false;
                }
                if (reverseLayout) {
                    if (direction < 0) {
                        itemCount = adapter.getItemCount();
                        i = itemCount - 1;
                    }
                    i = 0;
                } else {
                    if (direction > 0) {
                        itemCount = adapter.getItemCount();
                        i = itemCount - 1;
                    }
                    i = 0;
                }
                if (layoutManager.findViewByPosition(i) == null) {
                    return true;
                }
                int childCount = recyclerView.getChildCount();
                if (direction > 0) {
                    for (int i2 = childCount - 1; i2 >= 0; i2--) {
                        View childAt = recyclerView.getChildAt(i2);
                        Rect rect = mBounds;
                        recyclerView.getDecoratedBoundsWithMargins(childAt, rect);
                        if (rect.bottom > recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                            return true;
                        }
                    }
                    return false;
                }
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt2 = recyclerView.getChildAt(i3);
                    Rect rect2 = mBounds;
                    recyclerView.getDecoratedBoundsWithMargins(childAt2, rect2);
                    if (rect2.top < recyclerView.getPaddingTop()) {
                        return true;
                    }
                }
            }
            return false;
        }
        return scrolledView.canScrollVertically(direction);
    }

    static List<View> getTouchViews(View rootView, int touchX, int touchY) {
        ArrayList arrayList = new ArrayList();
        addTouchViews(arrayList, rootView, touchX, touchY);
        return arrayList;
    }

    private static void addTouchViews(List<View> views, View view, int touchX, int touchY) {
        if (isConsecutiveScrollerChild(view) && isTouchPointInView(view, touchX, touchY)) {
            views.add(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    addTouchViews(views, viewGroup.getChildAt(i), touchX, touchY);
                }
            }
        }
    }

    static boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return x >= i && x <= view.getMeasuredWidth() + i && y >= i2 && y <= view.getMeasuredHeight() + i2;
    }

    static int getRawX(View rootView, MotionEvent ev, int pointerIndex) {
        float x;
        if (Build.VERSION.SDK_INT >= 29) {
            x = ev.getRawX(pointerIndex);
        } else {
            rootView.getLocationOnScreen(new int[2]);
            x = r0[0] + ev.getX(pointerIndex);
        }
        return (int) x;
    }

    static int getRawY(View rootView, MotionEvent ev, int pointerIndex) {
        float y;
        if (Build.VERSION.SDK_INT >= 29) {
            y = ev.getRawY(pointerIndex);
        } else {
            rootView.getLocationOnScreen(new int[2]);
            y = r0[1] + ev.getY(pointerIndex);
        }
        return (int) y;
    }

    static List<Integer> getScrollOffsetForViews(List<View> views) {
        ArrayList arrayList = new ArrayList();
        Iterator<View> it = views.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(computeVerticalScrollOffset(it.next())));
        }
        return arrayList;
    }

    static boolean equalsOffsets(List<Integer> offsets1, List<Integer> offsets2) {
        if (offsets1.size() != offsets2.size()) {
            return false;
        }
        int size = offsets1.size();
        for (int i = 0; i < size; i++) {
            if (!offsets1.get(i).equals(offsets2.get(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean isConsecutiveScrollerChild(View view) {
        if (view == null) {
            return false;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConsecutiveScrollerLayout.LayoutParams) {
            return ((ConsecutiveScrollerLayout.LayoutParams) layoutParams).isConsecutive;
        }
        return true;
    }

    static View getScrolledView(View view) {
        View scrollChild = getScrollChild(view);
        while (scrollChild instanceof IConsecutiveScroller) {
            View currentScrollerView = ((IConsecutiveScroller) scrollChild).getCurrentScrollerView();
            if (scrollChild == currentScrollerView) {
                return currentScrollerView;
            }
            scrollChild = currentScrollerView;
        }
        return scrollChild;
    }

    static View getScrollChild(View view) {
        int i;
        View viewFindViewById;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if ((layoutParams instanceof ConsecutiveScrollerLayout.LayoutParams) && (i = ((ConsecutiveScrollerLayout.LayoutParams) layoutParams).scrollChild) != -1 && (viewFindViewById = view.findViewById(i)) != null) {
                return viewFindViewById;
            }
        }
        return view;
    }

    static boolean startInterceptRequestLayout(RecyclerView view) {
        if (!"InterceptRequestLayout".equals(view.getTag())) {
            return false;
        }
        try {
            Method declaredMethod = RecyclerView.class.getDeclaredMethod("startInterceptRequestLayout", null);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(view, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    static void stopInterceptRequestLayout(RecyclerView view) {
        if ("InterceptRequestLayout".equals(view.getTag())) {
            try {
                Method declaredMethod = RecyclerView.class.getDeclaredMethod("stopInterceptRequestLayout", Boolean.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, false);
            } catch (Exception unused) {
            }
        }
    }

    static boolean isConsecutiveScrollParent(View view) {
        while ((view.getParent() instanceof ViewGroup) && !(view.getParent() instanceof ConsecutiveScrollerLayout)) {
            view = (View) view.getParent();
        }
        if (view.getParent() instanceof ConsecutiveScrollerLayout) {
            return isConsecutiveScrollerChild(view);
        }
        return false;
    }

    static boolean isHorizontalScroll(View rootView, int touchX, int touchY) {
        for (View view : getTouchViews(rootView, touchX, touchY)) {
            if (view.canScrollHorizontally(1) || view.canScrollHorizontally(-1)) {
                return true;
            }
        }
        return false;
    }

    static boolean isTouchNotTriggerScrollStick(View rootView, int touchX, int touchY) {
        List<ConsecutiveScrollerLayout> inTouchCSLayout = getInTouchCSLayout(rootView, touchX, touchY);
        for (int size = inTouchCSLayout.size() - 1; size >= 0; size--) {
            ConsecutiveScrollerLayout consecutiveScrollerLayout = inTouchCSLayout.get(size);
            View topViewInTouch = getTopViewInTouch(consecutiveScrollerLayout, touchX, touchY);
            if (topViewInTouch != null && consecutiveScrollerLayout.isStickyView(topViewInTouch) && consecutiveScrollerLayout.theChildIsStick(topViewInTouch) && !((ConsecutiveScrollerLayout.LayoutParams) topViewInTouch.getLayoutParams()).isTriggerScroll) {
                return true;
            }
        }
        return false;
    }

    static List<ConsecutiveScrollerLayout> getInTouchCSLayout(View rootView, int touchX, int touchY) {
        ArrayList arrayList = new ArrayList();
        for (View view : getTouchViews(rootView, touchX, touchY)) {
            if (view instanceof ConsecutiveScrollerLayout) {
                arrayList.add((ConsecutiveScrollerLayout) view);
            }
        }
        return arrayList;
    }

    static View getTopViewInTouch(ConsecutiveScrollerLayout csl, int touchX, int touchY) {
        int childCount = csl.getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = csl.getChildAt(i);
            if (childAt.getVisibility() == 0 && isTouchPointInView(childAt, touchX, touchY) && (view == null || ViewCompat.getZ(childAt) > ViewCompat.getZ(view) || (ViewCompat.getZ(childAt) == ViewCompat.getZ(view) && csl.getDrawingPosition(childAt) > csl.getDrawingPosition(view)))) {
                view = childAt;
            }
        }
        return view;
    }
}
