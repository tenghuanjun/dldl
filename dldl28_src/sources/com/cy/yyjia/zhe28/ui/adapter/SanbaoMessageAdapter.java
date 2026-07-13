package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.databinding.ItemSanbaoMessageBinding;
import com.cy.yyjia.zhe28.domain.SanbaoRuleBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SanbaoMessageAdapter extends BaseAdapter {
    private List<SanbaoRuleBean.Message> data;
    private final Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public SanbaoMessageAdapter(Context mContext, List<SanbaoRuleBean.Message> data) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<SanbaoRuleBean.Message> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public SanbaoRuleBean.Message getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSanbaoMessageBinding itemSanbaoMessageBinding = (ItemSanbaoMessageBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.item_sanbao_message, null, false);
        itemSanbaoMessageBinding.setData(this.data.get(position));
        return itemSanbaoMessageBinding.getRoot();
    }

    public List<SanbaoRuleBean.Message> getData() {
        return this.data;
    }

    public void setData(List<SanbaoRuleBean.Message> data) {
        this.data = data;
    }
}
