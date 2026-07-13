package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cy.yyjia.zhe28.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LotteryMessageAdapter extends BaseAdapter {
    private List<String> data;
    private final Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public LotteryMessageAdapter(Context mContext, List<String> data) {
        this.data = data == null ? new ArrayList<>() : data;
        this.mContext = mContext;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<String> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public String getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(1, 14.0f);
        textView.setTextColor(this.mContext.getResources().getColor(R.color.color_text_1));
        textView.setText(this.data.get(position));
        textView.setGravity(17);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return textView;
    }

    public List<String> getData() {
        return this.data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
