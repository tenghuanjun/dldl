package com.sy37sdk.account.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.UserInfo;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountAdapter extends BaseAdapter {
    private List<UserInfo> accountList;
    private Context context;
    private DeleteListener deleteListener;

    public interface DeleteListener {
        void onDelete(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public AccountAdapter(Context context, List<UserInfo> list, DeleteListener deleteListener) {
        this.context = context;
        this.accountList = list;
        this.deleteListener = deleteListener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<UserInfo> list = this.accountList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf((this.accountList.size() - i) - 1);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final int size = (this.accountList.size() - i) - 1;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(SqResUtils.getIdByName("sysq_account_item", "layout", this.context), (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.tvUserName = (TextView) view.findViewById(SqResUtils.getIdByName("fg_name", SqTrackCommonKey.id, this.context));
            viewHolder.deleteBtn = view.findViewById(SqResUtils.getIdByName("fg_delete", SqTrackCommonKey.id, this.context));
            viewHolder.ivAccountType = (ImageView) view.findViewById(SqResUtils.getIdByName("iv_account_type", SqTrackCommonKey.id, this.context));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        UserInfo userInfo = this.accountList.get(size);
        String loginType = userInfo.getLoginType();
        byte b = -1;
        int iHashCode = loginType.hashCode();
        if (iHashCode != 50) {
            if (iHashCode == 51 && loginType.equals("3")) {
                b = 1;
            }
        } else if (loginType.equals("2")) {
            b = 0;
        }
        if (b == 0) {
            viewHolder.tvUserName.setText(userInfo.getMobile());
            viewHolder.ivAccountType.setImageResource(SqResUtils.getDrawableId(this.context, "sysq_item_account_phone"));
        } else if (b != 1) {
            viewHolder.tvUserName.setText(TextUtils.isEmpty(userInfo.getAlias()) ? userInfo.getUname() : userInfo.getAlias());
            viewHolder.ivAccountType.setImageResource(SqResUtils.getDrawableId(this.context, "sysq_ic_account"));
        } else {
            viewHolder.tvUserName.setText(userInfo.getUname());
            viewHolder.ivAccountType.setImageResource(SqResUtils.getDrawableId(this.context, "sysq_item_account_wechat"));
        }
        viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.AccountAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AccountAdapter.this.deleteListener != null) {
                    AccountAdapter.this.deleteListener.onDelete(size);
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        private View deleteBtn;
        private ImageView ivAccountType;
        private TextView tvUserName;

        private ViewHolder() {
        }
    }
}
