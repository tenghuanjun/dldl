package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cy.yyjia.zhe28.domain.BossServerBean;
import com.cy.yyjia.zhe28.ui.activity.EventDetailActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Welfare3NewsAdapter extends BaseAdapter {
    private List<BossServerBean.Privilege> data = new ArrayList();
    private final Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public Welfare3NewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<BossServerBean.Privilege> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public BossServerBean.Privilege getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(this.mContext);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(Color.parseColor("#E6735C"));
        textView.setText(this.data.get(position).getTitle());
        textView.setGravity(16);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.Welfare3NewsAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getView$0(position, view);
            }
        });
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getView$0(int i, View view) {
        Intent intent = new Intent(this.mContext, (Class<?>) EventDetailActivity.class);
        intent.putExtra("newsId", this.data.get(i).getId());
        this.mContext.startActivity(intent);
    }

    public List<BossServerBean.Privilege> getData() {
        return this.data;
    }

    public void setData(List<BossServerBean.Privilege> data) {
        this.data = data;
    }
}
