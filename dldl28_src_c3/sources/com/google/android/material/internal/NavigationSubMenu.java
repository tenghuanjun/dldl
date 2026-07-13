package com.google.android.material.internal;

import android.content.Context;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class NavigationSubMenu extends SubMenuBuilder {
    public NavigationSubMenu(Context context, NavigationMenu navigationMenu, MenuItemImpl menuItemImpl) {
        super(context, navigationMenu, menuItemImpl);
    }

    public void onItemsChanged(boolean z) {
        super.onItemsChanged(z);
        getParentMenu().onItemsChanged(z);
    }
}
