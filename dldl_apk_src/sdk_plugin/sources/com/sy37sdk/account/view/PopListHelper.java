package com.sy37sdk.account.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.config.IConfigMod;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.view.AccountAdapter;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PopListHelper {
    private static final float MAX_VISIBLE_ITEM = 3.5f;
    private AccountAdapter accountAdapter;
    private List<UserInfo> accountList;
    private PopupWindow mPopupWindow;

    public interface ItemClickListener {
        void onDelete(UserInfo userInfo);

        void onSelect(UserInfo userInfo);
    }

    public void initAccountList(Context context, List<UserInfo> list, int i, int i2, ItemClickListener itemClickListener) {
        initAccountList(context, list, i, i2, itemClickListener, null);
    }

    public void initAccountList(final Context context, List<UserInfo> list, int i, int i2, final ItemClickListener itemClickListener, PopupWindow.OnDismissListener onDismissListener) {
        float size;
        this.accountList = list;
        ListView listView = new ListView(context);
        listView.setCacheColorHint(-1);
        listView.setDivider(null);
        listView.setFocusable(false);
        listView.setVerticalScrollBarEnabled(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sy37sdk.account.view.PopListHelper.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j) {
                int size2 = (PopListHelper.this.accountList.size() - i3) - 1;
                if (PopListHelper.this.mPopupWindow != null) {
                    PopListHelper.this.mPopupWindow.dismiss();
                }
                itemClickListener.onSelect((UserInfo) PopListHelper.this.accountList.get(size2));
            }
        });
        List<UserInfo> list2 = this.accountList;
        if (list2 == null || list2.isEmpty()) {
            size = 0.0f;
        } else {
            size = ((float) this.accountList.size()) < MAX_VISIBLE_ITEM ? i2 * this.accountList.size() : i2 * MAX_VISIBLE_ITEM;
        }
        PopupWindow popupWindow = new PopupWindow((View) listView, i, DisplayUtil.dip2px(context, size), true);
        this.mPopupWindow = popupWindow;
        popupWindow.setFocusable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setSoftInputMode(32);
        this.mPopupWindow.setBackgroundDrawable(context.getResources().getDrawable(SqResUtils.getIdByName("sysq_bg_account_pop", "drawable", context)));
        AccountAdapter accountAdapter = new AccountAdapter(context, this.accountList, new AccountAdapter.DeleteListener() { // from class: com.sy37sdk.account.view.PopListHelper.2
            @Override // com.sy37sdk.account.view.AccountAdapter.DeleteListener
            public void onDelete(int i3) {
                UserInfo userInfo = (UserInfo) PopListHelper.this.accountList.get(i3);
                if (userInfo.getUname().equals(AccountCache.getUsername(context))) {
                    IConfigMod config = ModHelper.getConfig();
                    if (config != null) {
                        config.getCommonConfig().setUserId("");
                    }
                    AccountCache.setUserInfo(context, null);
                }
                PopListHelper.this.accountList.remove(i3);
                PopListHelper.this.accountAdapter.notifyDataSetChanged();
                ItemClickListener itemClickListener2 = itemClickListener;
                if (itemClickListener2 != null) {
                    itemClickListener2.onDelete(userInfo);
                }
                if (PopListHelper.this.accountList == null || PopListHelper.this.accountList.size() < 1) {
                    PopListHelper.this.mPopupWindow.dismiss();
                }
            }
        });
        this.accountAdapter = accountAdapter;
        listView.setAdapter((ListAdapter) accountAdapter);
        this.accountAdapter.notifyDataSetChanged();
        this.mPopupWindow.setOnDismissListener(onDismissListener);
    }

    public void showAccountList(View view) {
        List<UserInfo> list;
        if (view != null && this.mPopupWindow != null && (list = this.accountList) != null && list.size() > 0) {
            this.mPopupWindow.showAsDropDown(view, 0, DisplayUtil.dip2px(view.getContext(), 6.0f));
        } else {
            LogUtil.i("account list size is 0");
        }
    }

    public List<UserInfo> getAccountList() {
        return this.accountList;
    }

    public void hideAccountList() {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.mPopupWindow.dismiss();
    }

    public boolean isShowing() {
        PopupWindow popupWindow = this.mPopupWindow;
        return popupWindow != null && popupWindow.isShowing();
    }
}
