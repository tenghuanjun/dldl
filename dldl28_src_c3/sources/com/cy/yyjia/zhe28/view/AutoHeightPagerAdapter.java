package com.cy.yyjia.zhe28.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.viewpager.widget.PagerAdapter;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoHeightPagerAdapter.kt */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u0015\u001a\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/view/AutoHeightPagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "Lcom/cy/yyjia/zhe28/view/AutoHeightPager;", "()V", "viewList", "", "Landroid/view/View;", "destroyItem", "", "container", "Landroid/view/ViewGroup;", ImageSelector.POSITION, "", "object", "", "getCount", "getView", "instantiateItem", "isViewFromObject", "", "view", "setViews", "views", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AutoHeightPagerAdapter extends PagerAdapter implements AutoHeightPager {
    public static final int $stable = 8;
    private final List<View> viewList = new ArrayList();

    public Object instantiateItem(ViewGroup container, int position) {
        Intrinsics.checkNotNullParameter(container, "container");
        View view = this.viewList.get(position);
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(object, "object");
        container.removeView((View) object);
    }

    public final void setViews(List<? extends View> views) {
        Intrinsics.checkNotNullParameter(views, "views");
        this.viewList.clear();
        this.viewList.addAll(views);
        notifyDataSetChanged();
    }

    @Override // com.cy.yyjia.zhe28.view.AutoHeightPager
    public View getView(int position) {
        return (View) CollectionsKt.getOrNull(this.viewList, position);
    }

    public int getCount() {
        return this.viewList.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(object, "object");
        return Intrinsics.areEqual(view, object);
    }
}
