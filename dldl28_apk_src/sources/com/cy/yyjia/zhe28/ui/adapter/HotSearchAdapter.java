package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public class HotSearchAdapter extends BaseAdapter {
    private List<TypeBean> data = new ArrayList();
    private final Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public HotSearchAdapter(Context mContext) {
        this.mContext = mContext;
        requestData();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<TypeBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public TypeBean getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(this.mContext.getResources().getColor(R.color.color_text_3));
        textView.setText(this.data.get(position).getName());
        textView.setGravity(16);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return textView;
    }

    public List<TypeBean> getData() {
        return this.data;
    }

    public void setData(List<TypeBean> data) {
        this.data = data;
    }

    public void requestData() {
        Repository.INSTANCE.getHotSearchGames(new Function1() { // from class: com.cy.yyjia.zhe28.ui.adapter.HotSearchAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$requestData$0((List) obj);
            }
        }, new Function1() { // from class: com.cy.yyjia.zhe28.ui.adapter.HotSearchAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HotSearchAdapter.lambda$requestData$1((Exception) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$requestData$0(List list) {
        setData(list);
        notifyDataSetChanged();
        return null;
    }

    static /* synthetic */ Unit lambda$requestData$1(Exception exc) {
        Log.e("invoke: ", exc.getLocalizedMessage());
        return null;
    }
}
