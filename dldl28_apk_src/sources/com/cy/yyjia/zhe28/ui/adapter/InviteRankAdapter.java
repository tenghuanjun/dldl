package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.databinding.ItemInviteRankBinding;
import com.cy.yyjia.zhe28.domain.InviteRankBean;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InviteRankAdapter extends BaseAdapter {
    private List<InviteRankBean> data;
    private final Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public InviteRankAdapter(Context mContext, List<InviteRankBean> data) {
        this.data = data == null ? new ArrayList<>() : data;
        this.mContext = mContext;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<InviteRankBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public InviteRankBean getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemInviteRankBinding itemInviteRankBinding = (ItemInviteRankBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.item_invite_rank, parent, false);
        itemInviteRankBinding.setData(getData().get(position));
        return itemInviteRankBinding.getRoot();
    }

    public List<InviteRankBean> getData() {
        return this.data;
    }

    public void setData(List<InviteRankBean> data) {
        this.data = data;
    }
}
