package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoUserBinding;
import com.cy.yyjia.zhe28.domain.TaskResult;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QiandaoUserAdapter extends BaseAdapter {
    private List<TaskResult.User> data = new ArrayList();
    private final Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public QiandaoUserAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<TaskResult.User> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public TaskResult.User getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemQiandaoUserBinding itemQiandaoUserBinding = (ItemQiandaoUserBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.item_qiandao_user, null, false);
        itemQiandaoUserBinding.setData(this.data.get(position));
        return itemQiandaoUserBinding.getRoot();
    }

    public List<TaskResult.User> getData() {
        return this.data;
    }

    public void setData(List<TaskResult.User> data) {
        this.data = data;
    }
}
